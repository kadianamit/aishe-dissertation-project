import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NumberOfInstitutionsRoutingModule } from './number-of-institutions-routing.module';
import { NumberOfInstitutionsComponent } from './number-of-institutions.component';
import { Report6Component } from './report6/report6.component';
import { Report7Component } from './report7/report7.component';
import { Report8Component } from './report8/report8.component';
import { Report9Component } from './report9/report9.component';
import { Report13Component } from './report13/report13.component';
import { Report14Component } from './report14/report14.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Report10Component } from './report10/report10.component';
import { Report12Component } from './report12/report12.component';


@NgModule({
  declarations: [
    NumberOfInstitutionsComponent,
    Report6Component,
    Report7Component,
    Report8Component,
    Report9Component,
    Report13Component,
    Report14Component,
    Report10Component,
    Report12Component
  ],
  imports: [
    CommonModule,
    FormsModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    NumberOfInstitutionsRoutingModule
  ]
})
export class NumberOfInstitutionsModule { }
