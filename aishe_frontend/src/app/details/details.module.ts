import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DetailsRoutingModule } from './details-routing.module';
import { DetailsComponent } from './details.component';
import { ApprovingAuthorityComponent } from './approving-authority/approving-authority.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { AngularMaterialModule } from '../angular-material.module';
import { SharedModule } from '../sharedModule/shared/shared.module';
import { KnowAisheCodeComponent } from './know-aishe-code/know-aishe-code.component';


@NgModule({
  declarations: [
    DetailsComponent,
    ApprovingAuthorityComponent,
    KnowAisheCodeComponent
  ],
  imports: [
    CommonModule,
    DetailsRoutingModule,
    AngularMaterialModule,
    FormsModule,ReactiveFormsModule,
    NgxPaginationModule,
    SharedModule
  ]
})
export class DetailsModule { }
