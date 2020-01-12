import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MessageService} from "../message.service";
import {BaseService} from "../base-service";
import {Observable} from "rxjs";
import {Airport} from "../airport/airport";
import {catchError, map, tap} from "rxjs/operators";
import {Currency} from "./currency";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CurrencyService extends BaseService {

  private currenciesUrl = `${environment.baseUrl}/currencies`;

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
    super();
  }

  private log(message: string) {
    this.messageService.add(`CurrencyService: ${message}`);
  }

  getCurrencies(): Observable<Currency[]> {
    return this.http.get<Currency[]>(this.currenciesUrl).pipe(
      tap(_ => this.log(`get currencies called`)),
      map(data => data['result']),
      catchError(this.handleError<Currency[]>('getCurrencies', []))
    );
  }

}
