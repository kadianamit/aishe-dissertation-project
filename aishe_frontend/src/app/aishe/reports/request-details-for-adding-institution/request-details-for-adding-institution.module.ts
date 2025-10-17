import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RequestDetailsForAddingInstitutionRoutingModule } from './request-details-for-adding-institution-routing.module';
import { RequestDetailsForAddingInstitutionComponent } from './request-details-for-adding-institution.component';
import { Report148Component } from './report148/report148.component';
import { Report149Component } from './report149/report149.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { MatSelectFilterModule } from 'mat-select-filter';
import { ReactiveFormsModule } from '@angular/forms';
import { RemunerationRoutingModule } from '../remuneration/remuneration-routing.module';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    RequestDetailsForAddingInstitutionComponent,
    Report148Component,
    Report149Component
  ],
  imports: [
    CommonModule,
    RequestDetailsForAddingInstitutionRoutingModule,
    AngularMaterialModule,
    MatSelectFilterModule,
    ReactiveFormsModule,
    RemunerationRoutingModule,
    NgxPaginationModule
  ]
})
export class RequestDetailsForAddingInstitutionModule { }
