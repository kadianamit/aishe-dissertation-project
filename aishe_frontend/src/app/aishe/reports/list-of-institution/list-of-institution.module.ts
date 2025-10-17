import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ListOfInstitutionRoutingModule } from './list-of-institution-routing.module';
import { ListOfInstitutionComponent } from './list-of-institution.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { Report141Component } from './report141/report141.component';
import { Report146Component } from './report146/report146.component';
import { Report66Component } from './report66/report66.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    ListOfInstitutionComponent,
    Report66Component,
    Report141Component,
    Report146Component
  ],
  imports: [
    CommonModule,
    ListOfInstitutionRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    MatSelectFilterModule,
    NgxPaginationModule
  ],exports:[
    ListOfInstitutionComponent,
  ]
})
export class ListOfInstitutionModule { }
