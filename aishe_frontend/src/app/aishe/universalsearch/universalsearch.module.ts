import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UniversalsearchRoutingModule } from './universalsearch-routing.module';
import { UniversalsearchComponent } from './universalsearch.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    UniversalsearchComponent
  ],
  imports: [
    CommonModule,
    UniversalsearchRoutingModule,
    AngularMaterialModule,
    FormsModule
  ]
})
export class UniversalsearchModule { }
