import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InsManagSummaryRoutingModule } from './ins-manag-summary-routing.module';
import { InsManagSummaryComponent } from './ins-manag-summary.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { InstitutionSummaryDialogComponent } from 'src/app/dialog/institution-summary-dialog/institution-summary-dialog.component';


@NgModule({
  declarations: [
    InsManagSummaryComponent,InstitutionSummaryDialogComponent
  ],
  imports: [
    CommonModule,
    InsManagSummaryRoutingModule,
    NgxPaginationModule,
    ReactiveFormsModule,FormsModule,AngularMaterialModule
  ]
})
export class InsManagSummaryModule { }
