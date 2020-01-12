import {Component, Input, OnInit} from '@angular/core';
import {FlightOffer} from "../services/flight-offer/flight-offer";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  @Input() flightOffers: FlightOffer[];

  cols: any[];

  constructor() {
  }

  ngOnInit() {
    this.cols = [
      { field: 'flight.airline.businessName', header: 'Airline' },
      { field: 'flight.origin', header: 'Origin' },
      { field: 'flight.destination', header: 'Destination' },
      { field: 'flight.numberOfTransfers', header: 'Transfers' },
      { field: 'flight.departure', header: 'Departure' },
      { field: 'flight.arrival', header: 'Arrival' },
      { field: 'flight.numberOfPassengers', header: 'Passengers' },
      { field: 'flight.price.currency.iso', header: 'Currency' },
      { field: 'flight.price.price', header: 'Price' }
    ];
  }

}
