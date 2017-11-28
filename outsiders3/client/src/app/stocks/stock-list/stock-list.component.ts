import { Component, OnInit } from '@angular/core';
import {Stock} from "../Stock";
import {StockService} from "../stock.service";
import { Router } from '@angular/router';
import {StockLive} from "../stock-live";

@Component({
  selector: 'app-stock-list',
  templateUrl: './stock-list.component.html',
  styleUrls: ['./stock-list.component.css'],
  providers: [StockService]
})
export class StockListComponent implements OnInit {

  private stocks: Stock[];
  private stockLive: StockLive;

  constructor(private router: Router, private stockService: StockService) { }

  ngOnInit() {
    this.getAllStocks();
    this.getLive();
  }

  getAllStocks(){
    this.stockService.findAll().subscribe(
      stocks => {
        this.stocks = stocks;
      },
      err => {console.log(err);}
    );
  }

  getLive(){
    this.stockService.findLive().subscribe(
      stocklive => {
        this.stockLive = stocklive;
      },
    err => {console.log(err);}

  )
  }

  redirectNewStockPage() {
    this.router.navigate(['/stock/create']);
  }

  redirectEditStockPage(stock: Stock) {
    if (stock) {
      this.router.navigate(['/stock/edit/', stock.id]);
    }
  }

  deleteStock(stock: Stock) {
    if (stock) {
      this.stockService.deleteStockById(stock.id).subscribe(
        response => {
          this.getAllStocks();
          this.router.navigate(['/stock']);
          console.log('done', response);
        }
      );
    }
  }

}

