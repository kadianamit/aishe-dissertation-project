import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegistrationManagementRoutingModule } from './registration-management-routing.module';
import { RegistrationManagementComponent } from './registration-management.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { SharedModule } from 'src/app/sharedModule/shared/shared.module';
import { StartRegComponent } from './start-reg/start-reg.component';
import { ViewRegComponent } from './view-reg/view-reg.component';
import { RegManagementService } from './reg-management.service';



@NgModule({
  declarations: [
    RegistrationManagementComponent,
    StartRegComponent,
    ViewRegComponent
  ],
  imports: [
    CommonModule,
    RegistrationManagementRoutingModule,
    NgxPaginationModule,
    AngularMaterialModule,SharedModule,FormsModule,ReactiveFormsModule
  ],
  providers: [RegManagementService] 
})
export class RegistrationManagementModule { }
