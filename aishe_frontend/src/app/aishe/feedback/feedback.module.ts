import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FeedbackRoutingModule } from './feedback-routing.module';
import { FeedbackComponent } from './feedback.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';



@NgModule({
  declarations: [
    FeedbackComponent,
  
  ],
  imports: [
    CommonModule,
    FeedbackRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule,FormsModule,NgxPaginationModule
  ]
})
export class FeedbackModule { }
