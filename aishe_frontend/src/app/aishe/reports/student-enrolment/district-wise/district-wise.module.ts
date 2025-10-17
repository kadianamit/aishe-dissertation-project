import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DistrictWiseRoutingModule } from './district-wise-routing.module';
import { DistrictWiseComponent } from './district-wise.component';
import { Report107Component } from './report107/report107.component';
import { Report107aComponent } from './report107a/report107a.component';
import { Report107bComponent } from './report107b/report107b.component';
import { Report107cComponent } from './report107c/report107c.component';
import { Report108Component } from './report108/report108.component';
import { Report108aComponent } from './report108a/report108a.component';
import { Report108bComponent } from './report108b/report108b.component';
import { Report108cComponent } from './report108c/report108c.component';
import { NgxPaginationModule } from 'ngx-pagination';



@NgModule({
  declarations: [
    DistrictWiseComponent,
    Report107aComponent,
    Report107cComponent,
    Report108aComponent,
    Report108cComponent,
    Report107Component,
    Report107bComponent,
    Report108Component,
    Report108bComponent
  ],
  imports: [
    CommonModule,
    DistrictWiseRoutingModule,
    NgxPaginationModule
  ],exports:[
    Report107Component,
    Report107aComponent,
    Report107bComponent,
    Report107cComponent,
    Report108aComponent,
    Report108cComponent,
    Report108Component,
    Report108bComponent
  ]
})
export class DistrictWiseModule { }
