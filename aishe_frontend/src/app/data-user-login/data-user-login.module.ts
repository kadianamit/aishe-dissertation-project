import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DataUserLoginRoutingModule } from './data-user-login-routing.module';
import { DataUserLoginComponent } from './data-user-login.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AngularMaterialModule } from '../angular-material.module';
import { ForgotDataUserPasswordComponent } from './forgot-data-user-password/forgot-data-user-password.component';
import { ResetDataUserComponent } from './reset-data-user/reset-data-user.component';
import { DataUserComponent } from './data-user/data-user.component';


@NgModule({
  declarations: [
    DataUserLoginComponent,
    ForgotDataUserPasswordComponent,
    ResetDataUserComponent,
    DataUserComponent
  ],
  imports: [
    CommonModule,
    DataUserLoginRoutingModule,
    AngularMaterialModule,ReactiveFormsModule,FormsModule
  ]
})
export class DataUserLoginModule { }
