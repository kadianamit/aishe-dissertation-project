import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RemunerationManagementRoutingModule } from './remuneration-management-routing.module';
import { RemunerationManagementComponent } from './remuneration-management.component';
import { UserRequestComponent } from './user-request/user-request.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { SharedModule } from 'src/app/sharedModule/shared/shared.module';
import { UppercaseDirective } from './uppercase.directive';
import { RemunerationReportComponent } from './remuneration-report/remuneration-report.component';



@NgModule({
  declarations: [
    RemunerationManagementComponent,
    UserRequestComponent,
    UppercaseDirective,
    RemunerationReportComponent
  ],
  imports: [
    CommonModule,
    RemunerationManagementRoutingModule,
    NgxPaginationModule,
    AngularMaterialModule,SharedModule,FormsModule,ReactiveFormsModule,
  ]
})
export class RemunerationManagementModule { }
