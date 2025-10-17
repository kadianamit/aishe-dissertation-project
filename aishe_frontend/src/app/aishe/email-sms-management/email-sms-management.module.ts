import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmailSmsManagementRoutingModule } from './email-sms-management-routing.module';
import { EmailSmsManagementComponent } from './email-sms-management.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { SearchFilterPipe } from 'src/app/institution-directory/search-filter.pipe';



@NgModule({
  declarations: [
    EmailSmsManagementComponent,
  ],
  imports: [
    CommonModule,
    EmailSmsManagementRoutingModule,
    ReactiveFormsModule,FormsModule,
    AngularMaterialModule,
    NgxPaginationModule
  ]
})
export class EmailSmsManagementModule { }
