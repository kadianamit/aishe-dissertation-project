import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from 'src/app/angular-material.module';

import { UgcReportsRoutingModule } from './ugc-reports-routing.module';
import { UgcReportsComponent } from './ugc-reports.component';
import { Report133Component } from './report133/report133.component';
import { Report134Component } from './report134/report134.component';
import { Report134aComponent } from './report134a/report134a.component';
import { Report151Component } from './report151/report151.component';
import { Report152Component } from './report152/report152.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    UgcReportsComponent,
    Report133Component,
    Report134Component,
    Report134aComponent,
    Report151Component,
    Report152Component
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    MatSelectFilterModule,
    ReactiveFormsModule,
    UgcReportsRoutingModule,
    NgxPaginationModule
  ],exports:[
    UgcReportsComponent,
    Report133Component,
    Report134Component,
    Report134aComponent,
    Report151Component,
    Report152Component
  ]
})
export class UgcReportsModule { }
