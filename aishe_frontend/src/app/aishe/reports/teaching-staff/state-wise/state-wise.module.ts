import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StateWiseRoutingModule } from './state-wise-routing.module';
import { StateWiseComponent } from './state-wise.component';
import { Report15Component } from './report15/report15.component';
import { Report16Component } from './report16/report16.component';
import { Report138Component } from './report138/report138.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    StateWiseComponent,
    Report15Component,
    Report16Component,
    Report138Component
  ],
  imports: [
    CommonModule,
    StateWiseRoutingModule,
    NgxPaginationModule
  ],
  exports:[
    Report15Component,
    Report16Component,
    Report138Component
  ]
})
export class StateWiseModule { }
