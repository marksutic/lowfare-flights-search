import {Observable, of} from "rxjs";

export class BaseService {

  protected handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      return of(result as T); // app keeps running by returning an empty result.
    };
  }
}
