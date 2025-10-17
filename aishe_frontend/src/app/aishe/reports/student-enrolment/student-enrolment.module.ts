import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentEnrolmentRoutingModule } from './student-enrolment-routing.module';
import { StudentEnrolmentComponent } from './student-enrolment.component';
import { DistrictWiseModule } from './district-wise/district-wise.module';
import { InstitutionWiseModule } from './institution-wise/institution-wise.module';
import { StateWiseModule } from './state-wise/state-wise.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    StudentEnrolmentComponent
  ],
  imports: [
    CommonModule,
    StudentEnrolmentRoutingModule,
    StateWiseModule,
    DistrictWiseModule,
    InstitutionWiseModule,
    AngularMaterialModule,
    MatSelectFilterModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],exports:[
    StateWiseModule,
    DistrictWiseModule,
    InstitutionWiseModule
  ]
})
export class StudentEnrolmentModule { }
