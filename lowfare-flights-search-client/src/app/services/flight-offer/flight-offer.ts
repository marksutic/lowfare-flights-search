import {Price} from "./price";
import {Airline} from "./airline";

export class FlightOffer {
  id: string;
  origin: string;
  destination: string;
  numberOfTransfers: number;
  departureDate: string;
  returnDate: string;
  numberOfPassengers: number;
  price: Price;
  airline: Airline;
  departure: Date;
  arrival: Date;
}
