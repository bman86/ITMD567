import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StockListComponent} from "./stock-list/stock-list.component";
import {StockCreateComponent} from "./stock-create/stock-create.component";
import {StockSymbolComponent} from "./stock-symbol/stock-symbol.component";

const routes: Routes = [
  {path: 'stock', component: StockListComponent},
  {path: 'stock/create', component: StockCreateComponent},
  {path: 'stock/edit/:id', component: StockCreateComponent},
  {path: 'stock/symbol/:symbl', component: StockSymbolComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StockRoutingModule { }
