import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NonTeachingStaffRoutingModule } from './non-teaching-staff-routing.module';
import { NonTeachingStaffComponent } from './non-teaching-staff.component';
import { Report122Component } from './report122/report122.component';
import { Report122aComponent } from './report122a/report122a.component';
import { Report122cComponent } from './report122c/report122c.component';
import { Report123Component } from './report123/report123.component';
import { Report122bComponent } from './report122b/report122b.component';
import { Report123aComponent } from './report123a/report123a.component';
import { Report123bComponent } from './report123b/report123b.component';
import { Report123cComponent } from './report123c/report123c.component';
import { Report172Component } from './report172/report172.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    NonTeachingStaffComponent,
    Report122Component,
    Report122aComponent,
    Report122bComponent,
    Report122cComponent,
    Report123Component,
    Report123aComponent,
    Report123bComponent,
    Report123cComponent,
    Report172Component
  ],
  imports: [
    CommonModule,
    NonTeachingStaffRoutingModule,
    NgxPaginationModule
  ],
  exports:[
    Report122Component,
    Report122aComponent,
    Report122bComponent,
    Report122cComponent,
    Report123Component,
    Report123aComponent,
    Report123bComponent,
    Report123cComponent,
    Report172Component
  ], schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
})
export class NonTeachingStaffModule { }
