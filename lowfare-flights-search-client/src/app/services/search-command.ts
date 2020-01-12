export class SearchCommand {

  departureAirport: string;
  destinationAirport: string;
  departureDate: string;
  returnDate: string;
  passengers: number;
  currency: string;

  public toString = (): string => {
    return `SearchCommand (
    departureAirport: ${this.departureAirport},
    departureDate: ${this.departureDate},
    returnDate: ${this.returnDate},
    passengers: ${this.passengers},
    currency: ${this.currency},
    )`;
  }
}

