import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/angular-material.module';

import { InstitutionContactDetailsRoutingModule } from './institution-contact-details-routing.module';
import { InstitutionContactDetailsComponent } from './institution-contact-details.component';
import { Report156Component } from './report156/report156.component';
import { Report167Component } from './report167/report167.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    InstitutionContactDetailsComponent,
    Report156Component,
    Report167Component
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    InstitutionContactDetailsRoutingModule,
     NgxPaginationModule
  ], exports: [
    InstitutionContactDetailsComponent,
    Report156Component,
    Report167Component
  ]
})
export class InstitutionContactDetailsModule { }
