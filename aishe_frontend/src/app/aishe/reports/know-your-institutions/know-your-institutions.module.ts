import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { KnowYourInstitutionsRoutingModule } from './know-your-institutions-routing.module';
import { KnowYourInstitutionsComponent } from './know-your-institutions.component';


@NgModule({
  declarations: [
    KnowYourInstitutionsComponent
  ],
  imports: [
    CommonModule,
    KnowYourInstitutionsRoutingModule
  ]
})
export class KnowYourInstitutionsModule { }
