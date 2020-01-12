import {Component, OnInit} from '@angular/core';
import {MessageService} from "../services/message.service";
import {Airport} from "../services/airport/airport";
import {AirportService} from "../services/airport/airport.service";
import {Currency} from "../services/currency/currency";
import {CurrencyService} from "../services/currency/currency.service";
import {SearchCommand} from "../services/search-command";
import {LowfareFlightsService} from "../services/lowfare-flights.service";
import {FlightOffer} from "../services/flight-offer/flight-offer";
import DateUtil from "../shared/date-util";

@Component({
  selector: 'app-search-menu',
  templateUrl: './search-menu.component.html',
  styleUrls: ['./search-menu.component.css']
})
export class SearchMenuComponent implements OnInit {

  oneWay: string = 'true';
  departureOptions: AutocompleteAirport[] = [];
  departureAirport: AutocompleteAirport;
  destinationOptions: AutocompleteAirport[] = [];
  destinationAirport: AutocompleteAirport;
  departureDate: Date;
  returnDate: Date;
  passengers: number = 1;
  currencies: Currency[] = [];
  selectedCurrency: Currency = new Currency('EUR');

  flightOffers: FlightOffer[] = [];

  constructor(
    private messageService: MessageService,
    private airportService: AirportService,
    private currencyService: CurrencyService,
    private lowfareFlightsService: LowfareFlightsService) {
  }

  ngOnInit(): void {
    this.currencyService.getCurrencies().subscribe(currencies => this.currencies = currencies);
  }

  isReturnDateDisabled(): boolean {
    return this.oneWay == 'true';
  }

  filterDepartureAirports(event) {
    this.airportService.searchAirports(event.query).subscribe((airports: Airport[]) => {
      this.departureOptions = airports.map((_: Airport) => new AutocompleteAirport(_));
    })
  }

  filterDestinationAirports(event) {
    this.airportService.searchAirports(event.query).subscribe((airports: Airport[]) => {
      this.destinationOptions = airports.map((_: Airport) => new AutocompleteAirport(_));
    })
  }

  switchAirports() {
    let tmp = this.departureAirport;
    this.departureAirport = this.destinationAirport;
    this.destinationAirport = tmp;
  }

  search(): void {
    let searchCommand: SearchCommand = {
      currency: this.selectedCurrency.iso,
      departureAirport: this.departureAirport.airport.iataCode,
      departureDate: DateUtil.toIsoString(this.departureDate),
      destinationAirport: this.destinationAirport.airport.iataCode,
      passengers: this.passengers,
      returnDate: this.returnDate ? DateUtil.toIsoString(this.returnDate) : ''
    };
    console.log(searchCommand);
    this.printLog();
    this.lowfareFlightsService.searchFlightOffers(searchCommand).subscribe(res => this.flightOffers = res)
  }

  printLog(): void {
    this.messageService.add(`SearchMenuComponent: ${this.oneWay},
     ${this.departureAirport.description}
     ${this.destinationAirport.description}
     ${this.departureDate}
     ${this.passengers}
     ${this.selectedCurrency.iso}
     `);
  }

  isOkToSearch(): boolean {
    if (this.oneWay == 'true') {
      if (this.departureAirport && this.destinationAirport && this.departureDate && this.passengers) {
        this.returnDate = null;
        return true;
      }
    } else {
      if (this.departureAirport && this.destinationAirport && this.departureDate && this.returnDate && this.passengers) {
        return true;
      }
    }
    return false;
  }
}

class AutocompleteAirport {
  airport: Airport;
  description: string;

  constructor(airport: Airport) {
    this.airport = airport;
    this.description = `${airport.iataCode} (${airport.city})`
  }
}
