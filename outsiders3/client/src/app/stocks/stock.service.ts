import { Injectable } from '@angular/core';
import { Stock } from "./stock";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {StockLive} from "./stock-live";
import {StockSymbol} from "./StockSymbol";

@Injectable()
export class StockService {

  private apiUrl = 'http://localhost:8080/stocks';
  private liveApiUrl = 'http://localhost:8080/metadata';

  constructor(private http: HttpClient) {}


    findById(id: number): Observable<Stock> {
      return this.http.get(this.apiUrl + '/' + id)
        .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
    }

    //method to get stock by symbol for aggregate not on user level
  findBySymbol(stkSymbol: String): Observable<StockSymbol> {
    return this.http.get(this.apiUrl + '/symbol/' + stkSymbol)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

    //method to get live quote from seperate table
    findLive(): Observable<StockLive> {
      return this.http.get(this.liveApiUrl)
        .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
    }


  saveStock(stock: Stock): Observable<Stock> {
    return this.http.post(this.apiUrl, stock)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));  }


  deleteStockById(id: number): Observable<boolean> {
    return this.http.delete(this.apiUrl + '/' + id)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));  }

  updateStock(stock: Stock): Observable<Stock> {
    return this.http.put(this.apiUrl, stock)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));  }

  findAll(): Observable<Stock[]>  {
    return this.http.get(this.apiUrl)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

  }
}
