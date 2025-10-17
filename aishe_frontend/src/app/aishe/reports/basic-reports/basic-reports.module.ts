import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BasicReportsRoutingModule } from './basic-reports-routing.module';
import { BasicReportsComponent } from './basic-reports.component';
import { Report1Component } from './report1/report1.component';
import { Report2Component } from './report2/report2.component';
import { Report3Component } from './report3/report3.component';
// import { MaterialModule } from 'src/app/material.module';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    BasicReportsComponent,
    Report1Component,
    Report2Component,
    Report3Component
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
   // ReactiveFormsModule,
    BasicReportsRoutingModule,
  ]
})
export class BasicReportsModule { }
