import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProgressMoniterRoutingModule } from './progress-moniter-routing.module';
import { ProgressMoniterComponent } from './progress-moniter.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { SharedModule } from 'src/app/sharedModule/shared/shared.module';
import { SummaryComponent } from './summary/summary.component';
import { DayWiseComponent } from './day-wise/day-wise.component';
import { MoniteringComponent } from './monitering/monitering.component';
import { StateWiseMonitoringComponent } from './state-wise-monitoring/state-wise-monitoring.component';


@NgModule({
  declarations: [
    ProgressMoniterComponent,
    SummaryComponent,
    DayWiseComponent,
    MoniteringComponent,
    StateWiseMonitoringComponent
  ],
  imports: [
    CommonModule,
    ProgressMoniterRoutingModule,MatSelectFilterModule,
    AngularMaterialModule,SharedModule,FormsModule,ReactiveFormsModule
  ]
})
export class ProgressMoniterModule { }
