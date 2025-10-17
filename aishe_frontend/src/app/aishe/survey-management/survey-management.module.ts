import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SurveyManagementRoutingModule } from './survey-management-routing.module';
import { SurveyManagementComponent } from './survey-management.component';
import { SurveyLogComponent } from './survey-log/survey-log.component';
import { SurveyActionComponent } from './survey-action/survey-action.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule, NgxMatNativeDateModule  } from '@angular-material-components/datetime-picker';
import { NgxMatMomentModule } from '@angular-material-components/moment-adapter';
@NgModule({
  declarations: [
    SurveyManagementComponent,
    SurveyLogComponent,
    SurveyActionComponent
  ],
  imports: [
    CommonModule,
    SurveyManagementRoutingModule,AngularMaterialModule,FormsModule,ReactiveFormsModule,NgxPaginationModule,NgxMatDatetimePickerModule, 
    NgxMatTimepickerModule, NgxMatNativeDateModule,NgxMatMomentModule
  ]
})
export class SurveyManagementModule { }
