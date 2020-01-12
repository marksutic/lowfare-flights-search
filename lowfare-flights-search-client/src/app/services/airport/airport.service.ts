import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {MessageService} from '../message.service';
import {HttpClient} from '@angular/common/http';
import {Airport} from "./airport";
import {catchError, map, tap} from 'rxjs/operators';
import {BaseService} from "../base-service";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AirportService extends BaseService {

  private airportsUrl = `${environment.baseUrl}/airports`;

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
    super();
  }

  private log(message: string) {
    this.messageService.add(`AirportService: ${message}`);
  }

  searchAirports(term: string): Observable<Airport[]> {
    const searchUrl = `${this.airportsUrl}?name=${term}`;
    this.log(`searchUrl="${searchUrl}"`);
    if (!term.trim()) {
      return of([]); // return empty Airport array
    }
    return this.http.get<Airport[]>(searchUrl).pipe(
      tap(_ => this.log(`found airports matching "${term}"`)),
      map(data => data['result']),
      catchError(this.handleError<Airport[]>('searchAirports', []))
    );
  }

}
