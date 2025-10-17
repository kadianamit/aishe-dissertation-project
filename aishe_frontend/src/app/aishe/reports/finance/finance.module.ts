import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FinanceRoutingModule } from './finance-routing.module';
import { FinanceComponent } from './finance.component';
import { Report62Component } from './report62/report62.component';
import { Report63Component } from './report63/report63.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    FinanceComponent,
    Report62Component,
    Report63Component
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    FinanceRoutingModule,
    NgxPaginationModule
  ], exports: [
    Report62Component,
    Report63Component
  ]
})
export class FinanceModule { }
