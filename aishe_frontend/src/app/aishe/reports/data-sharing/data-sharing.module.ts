import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DataSharingRoutingModule } from './data-sharing-routing.module';
import { DataSharingComponent } from './data-sharing.component';


@NgModule({
  declarations: [
    DataSharingComponent
  ],
  imports: [
    CommonModule,
    DataSharingRoutingModule
  ]
})
export class DataSharingModule { }
