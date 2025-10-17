import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProgressMonitoringRoutingModule } from './progress-monitoring-routing.module';
import { ProgressMonitoringComponent } from './progress-monitoring.component';
import { Report59Component } from './report59/report59.component';
import { Report60Component } from './report60/report60.component';
import { Report61Component } from './report61/report61.component';
import { Report96Component } from './report96/report96.component';
import { Report161Component } from './report161/report161.component';
import { Report162Component } from './report162/report162.component';
import { Report163Component } from './report163/report163.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    ProgressMonitoringComponent,
    Report59Component,
    Report60Component,
    Report61Component,
    Report96Component,
    Report161Component,
    Report162Component,
    Report163Component
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    ProgressMonitoringRoutingModule,
    NgxPaginationModule
  ],
  exports: [
    ProgressMonitoringComponent,
    Report59Component,
    Report60Component,
    Report61Component,
    Report96Component,
    Report161Component,
    Report162Component,
    Report163Component
  ]
})
export class ProgressMonitoringModule { }
