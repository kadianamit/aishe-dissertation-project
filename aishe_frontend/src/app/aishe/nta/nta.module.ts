import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NtaRoutingModule } from './nta-routing.module';
import { NtaComponent } from './nta.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/angular-material.module';


@NgModule({
  declarations: [
    NtaComponent
  ],
  imports: [
    CommonModule,
    NtaRoutingModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class NtaModule { }
