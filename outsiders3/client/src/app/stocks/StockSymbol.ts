export class StockSymbol {

  id: number;
  stkSymbol: string;
  avgEntry: number;
  percentOwn: number;
  percentUp: number;
  avgTarget: number;
  percentTime: number;

  constructor(id: number, stkSymbol: string, avgEntry: number, percentOwn: number, percentUp: number, avgTarget: number, percentTime: number) {
    this.id = id;
    this.stkSymbol = stkSymbol;
    this.avgEntry = avgEntry;
    this.percentOwn = percentOwn;
    this.percentUp = percentUp;
    this.avgTarget = avgTarget;
    this.percentTime = percentTime;
  }

}



