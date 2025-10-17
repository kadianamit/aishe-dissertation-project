import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstitutionalLoginRoutingModule } from './institutional-login-routing.module';
import { InstitutionalLoginComponent } from './institutional-login.component';
import { AngularMaterialModule } from '../angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../sharedModule/shared/shared.module';




@NgModule({
  declarations: [
    InstitutionalLoginComponent,
  ],
  imports: [
    CommonModule,
    InstitutionalLoginRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule
    
  ],
  providers:[]
})
export class InstitutionalLoginModule { }
