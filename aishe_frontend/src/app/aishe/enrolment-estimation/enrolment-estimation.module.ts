import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnrolmentEstimationRoutingModule } from './enrolment-estimation-routing.module';
import { EnrolmentEstimationComponent } from './enrolment-estimation.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/sharedModule/shared/shared.module';


@NgModule({
  declarations: [
    EnrolmentEstimationComponent
  ],
  imports: [
    CommonModule,
    EnrolmentEstimationRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule
  ]
})
export class EnrolmentEstimationModule { }
