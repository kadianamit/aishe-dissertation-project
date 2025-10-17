import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StateWiseRoutingModule } from './state-wise-routing.module';
import { StateWiseComponent } from './state-wise.component';
import { Report23Component } from './report23/report23.component';
import { Report24Component } from './report24/report24.component';
import { Report136Component } from './report136/report136.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    StateWiseComponent,
    Report23Component,
    Report24Component,
    Report136Component
  ],
  imports: [
    CommonModule,
    StateWiseRoutingModule,
    NgxPaginationModule
  ],exports: [StateWiseComponent,
    Report23Component,
    Report24Component,
    Report136Component]
})
export class StateWiseModule { }
