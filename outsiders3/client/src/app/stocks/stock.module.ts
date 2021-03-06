import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StockRoutingModule } from './stock-routing.module';
import { StockListComponent } from './stock-list/stock-list.component';
import { StockCreateComponent } from './stock-create/stock-create.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { StockSymbolComponent } from './stock-symbol/stock-symbol.component';

@NgModule({
  imports: [
    CommonModule,
    StockRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [StockListComponent, StockCreateComponent, StockSymbolComponent]
})
export class StockModule { }
