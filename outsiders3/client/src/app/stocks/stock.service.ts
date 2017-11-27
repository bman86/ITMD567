import { Injectable } from '@angular/core';
import { Stock } from "./stock";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class StockService {

  private apiUrl = 'http://localhost:8080/stocks';


  constructor(private http: HttpClient) {}


    findById(id: number): Observable<Stock> {
      return this.http.get(this.apiUrl + '/' + id)
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
