import { NgModule } from '@angular/core';
import { CommonModule, TitleCasePipe } from '@angular/common';

import { UserRegistrationRoutingModule } from './user-registration-routing.module';
import { UserRegistrationComponent } from './user-registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from '../angular-material.module';
import { SharedModule } from '../sharedModule/shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    UserRegistrationComponent
  ],
  imports: [
    CommonModule,
    NgbModule,
    UserRegistrationRoutingModule,
    AngularMaterialModule,SharedModule,FormsModule,ReactiveFormsModule
  ],
  providers:[TitleCasePipe]
})
export class UserRegistrationModule { }
