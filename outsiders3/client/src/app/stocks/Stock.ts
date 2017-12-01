export class Stock {

  id: number;
  symbl: string;
  own: boolean;
  entryPrice: number;
  up: boolean;
  target: number;
  time: string;

  constructor(id: number, symbl: string, own: boolean, entryPrice: number, up: boolean, target: number, time: string ){
    this.id = id;
    this.symbl = symbl;
    this.own = own;
    this.entryPrice = entryPrice;
    this.up = up;
    this.target = target;
    this.time = time;
}

}

