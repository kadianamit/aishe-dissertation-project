import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserdataloginRoutingModule } from './userdatalogin-routing.module';
import { UserdataloginComponent } from './userdatalogin.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AngularMaterialModule } from '../angular-material.module';
import { SharedModule } from '../sharedModule/shared/shared.module';


@NgModule({
  declarations: [
    UserdataloginComponent
  ],
  imports: [
    CommonModule,
    UserdataloginRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule
  ]
})
export class UserdataloginModule { }
