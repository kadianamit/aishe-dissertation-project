import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstitutionManagementReportRoutingModule } from './institution-management-report-routing.module';
import { InstitutionManagementReportComponent } from './institution-management-report.component';
import { Report154Component } from './report154/report154.component';
import { Report155Component } from './report155/report155.component';
import { Report164Component } from './report164/report164.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    InstitutionManagementReportComponent,
    Report154Component,
    Report155Component,
    Report164Component
  ],
  imports: [
    CommonModule,
    InstitutionManagementReportRoutingModule,
    CommonModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    MatSelectFilterModule,
    NgxPaginationModule
  ]
  ,exports:[
    InstitutionManagementReportComponent
  ]
})
export class InstitutionManagementReportModule { }
