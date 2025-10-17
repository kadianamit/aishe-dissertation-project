import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { DataUserRoutingModule } from './data-user-routing.module';
import { DataUserComponent } from './data-user.component';
import { Report97Component } from './report97/report97.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    DataUserComponent,
    Report97Component
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    DataUserRoutingModule,
    NgxPaginationModule
  ], exports: [
    DataUserComponent,
    Report97Component
  ]
})
export class DataUserModule { }
