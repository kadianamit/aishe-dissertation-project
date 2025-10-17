import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DatasharingRoutingModule } from './datasharing-routing.module';
import { DatasharingComponent } from './datasharing.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    DatasharingComponent
  ],
  imports: [
    CommonModule,
    DatasharingRoutingModule,
    NgxPaginationModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class DatasharingModule { }
