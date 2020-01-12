import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {BaseService} from "./base-service";
import {HttpClient, HttpParams} from "@angular/common/http";
import {MessageService} from "./message.service";
import {FlightOffer} from "./flight-offer/flight-offer";
import {map} from "rxjs/operators";
import {SearchCommand} from "./search-command";
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LowfareFlightsService extends BaseService {

  private flightsOffersUrl = `${environment.baseUrl}/flight-offers`;

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
    super();
  }

  searchFlightOffers(searchCommand: SearchCommand): Observable<FlightOffer[]> {
    let params = new HttpParams()
      .set('origin', searchCommand.departureAirport)
      .set('destination', searchCommand.destinationAirport)
      .set('departureDate', searchCommand.departureDate)
      .set('adults', searchCommand.passengers.toString())
      .set('currency', searchCommand.currency);
    if (searchCommand.returnDate != null) {
      params = params.set('returnDate', searchCommand.returnDate)
    }
    return this.http.get(this.flightsOffersUrl, {params}).pipe(
      map(res => res["result"])
    )
  }

}
