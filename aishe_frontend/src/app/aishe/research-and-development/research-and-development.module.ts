import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResearchAndDevelopmentRoutingModule } from './research-and-development-routing.module';
import { ResearchAndDevelopmentComponent } from './research-and-development.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { SharedModule } from 'src/app/sharedModule/shared/shared.module';
import { ViewRADComponent } from './view-rad/view-rad.component';
import { EditRADComponent } from './edit-rad/edit-rad.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ApproveRadComponent } from './approve-rad/approve-rad.component';
import { AddResearchAndDevelopmentComponent } from './add-research-and-development/add-research-and-development.component';
import { RejectRadComponent } from './reject-rad/reject-rad.component';





@NgModule({
  declarations: [
    ResearchAndDevelopmentComponent,
    ViewRADComponent,
    EditRADComponent,
    ApproveRadComponent,
    AddResearchAndDevelopmentComponent,
    RejectRadComponent,

  ],
  imports: [
    CommonModule,
    ResearchAndDevelopmentRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    FormsModule,
    MatSelectFilterModule,
    SharedModule,
    NgxPaginationModule,
    MatFormFieldModule
  ]
})
export class ResearchAndDevelopmentModule { }
