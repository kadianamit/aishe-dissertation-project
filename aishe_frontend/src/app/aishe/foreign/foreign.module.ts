import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ForeignRoutingModule } from './foreign-routing.module';
import { ForeignComponent } from './foreign.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ApproveForeignInstitutionComponent } from './approve-foreign-institution/approve-foreign-institution.component';
import { AddForeignInstitutionComponent } from './add-foreign-institution/add-foreign-institution.component';
import { RejectForeignListComponent } from './reject-foreign-list/reject-foreign-list.component';
import { ApproveRejectForeignComponent } from './approve-reject-foreign/approve-reject-foreign.component';
import { EditForeignInstituteComponent } from './edit-foreign-institute/edit-foreign-institute.component';
import { ForeignDashboardComponent } from './foreign-dashboard/foreign-dashboard.component';


@NgModule({
  declarations: [
    ForeignComponent,
    ApproveForeignInstitutionComponent,
    AddForeignInstitutionComponent,
    RejectForeignListComponent,
    ApproveRejectForeignComponent,
    EditForeignInstituteComponent,
    ForeignDashboardComponent
  ],
  imports: [
    CommonModule,
    ForeignRoutingModule,
    AngularMaterialModule,
    NgxPaginationModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class ForeignModule { }
