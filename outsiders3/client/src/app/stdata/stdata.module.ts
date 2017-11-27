import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StdataRoutingModule } from './stdata-routing.module';
import { StksymbolComponent } from './stksymbol/stksymbol.component';

@NgModule({
  imports: [
    CommonModule,
    StdataRoutingModule
  ],
  declarations: [StksymbolComponent]
})
export class StdataModule { }
