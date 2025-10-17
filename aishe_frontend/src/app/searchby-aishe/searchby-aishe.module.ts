import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SearchbyAisheRoutingModule } from './searchby-aishe-routing.module';
import { SearchbyAisheComponent } from './searchby-aishe.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from '../angular-material.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { SharedModule } from '../sharedModule/shared/shared.module';


@NgModule({
  declarations: [
    SearchbyAisheComponent
  ],
  imports: [
    CommonModule,
    SearchbyAisheRoutingModule,
    AngularMaterialModule,
    FormsModule,ReactiveFormsModule,
    NgxPaginationModule,
    SharedModule
  ]
})
export class SearchbyAisheModule { }
