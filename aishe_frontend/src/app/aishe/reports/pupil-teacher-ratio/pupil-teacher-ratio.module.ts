import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PupilTeacherRatioRoutingModule } from './pupil-teacher-ratio-routing.module';
import { PupilTeacherRatioComponent } from './pupil-teacher-ratio.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { Report64Component } from './report64/report64.component';
import { Report144Component } from './report144/report144.component';
import { Report143Component } from './report143/report143.component';
import { NgxPaginationModule } from 'ngx-pagination';



@NgModule({
  declarations: [
    PupilTeacherRatioComponent,
    Report64Component,
    Report144Component,
    Report143Component,

  ],
  imports: [
    CommonModule,
    PupilTeacherRatioRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    MatSelectFilterModule,
    NgxPaginationModule
  ],exports:[
    PupilTeacherRatioComponent,
    Report64Component,
    Report144Component,
    Report143Component
  ]
})
export class PupilTeacherRatioModule { }
