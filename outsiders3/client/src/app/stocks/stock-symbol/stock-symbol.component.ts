import { Component, OnInit } from '@angular/core';
import { StockSymbol} from "../StockSymbol";
import { StockService} from "../stock.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-stock-symbol',
  templateUrl: './stock-symbol.component.html',
  styleUrls: ['./stock-symbol.component.css'],
  providers: [StockService]
})
export class StockSymbolComponent implements OnInit {

  stkSymbol: string;
  stockSymbol: StockSymbol;

  private sub: any;

  constructor(private route: ActivatedRoute,
              private stockService: StockService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.stkSymbol = params['symbl']
    });


    if (this.stkSymbol) {
      this.stockService.findBySymbol(this.stkSymbol).subscribe(
        stockSymbol => {
          this.stockSymbol = stockSymbol;
        },
        err => {
          console.log(err);
        }
      );
    }

  }
}
