import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentEnrolmentRoutingModule } from './student-enrolment-routing.module';
import { StudentEnrolmentComponent } from './student-enrolment.component';
import { Report100Component } from './report100/report100.component';
import { Report100aComponent } from './report100a/report100a.component';
import { Report100bComponent } from './report100b/report100b.component';
import { Report100cComponent } from './report100c/report100c.component';
import { Report101Component } from './report101/report101.component';
import { Report101aComponent } from './report101a/report101a.component';
import { Report101bComponent } from './report101b/report101b.component';
import { Report101cComponent } from './report101c/report101c.component';
import { Report102Component } from './report102/report102.component';
import { Report102aComponent } from './report102a/report102a.component';
import { Report102bComponent } from './report102b/report102b.component';
import { Report102cComponent } from './report102c/report102c.component';
import { Report103Component } from './report103/report103.component';
import { Report103aComponent } from './report103a/report103a.component';
import { Report103bComponent } from './report103b/report103b.component';
import { Report103cComponent } from './report103c/report103c.component';
import { Report104Component } from './report104/report104.component';
import { Report104aComponent } from './report104a/report104a.component';
import { Report104bComponent } from './report104b/report104b.component';
import { Report104cComponent } from './report104c/report104c.component';
import { Report105Component } from './report105/report105.component';
import { Report105aComponent } from './report105a/report105a.component';
import { Report105bComponent } from './report105b/report105b.component';
import { Report105cComponent } from './report105c/report105c.component';
import { Report106Component } from './report106/report106.component';
import { Report106aComponent } from './report106a/report106a.component';
import { Report106bComponent } from './report106b/report106b.component';
import { Report106cComponent } from './report106c/report106c.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    StudentEnrolmentComponent,
    Report100Component,
    Report100aComponent,
    Report100bComponent,
    Report100cComponent,
    Report101Component,
    Report101aComponent,
    Report101bComponent,
    Report101cComponent,
    Report102Component,
    Report102aComponent,
    Report102bComponent,
    Report102cComponent,
    Report103Component,
    Report103aComponent,
    Report103bComponent,
    Report103cComponent,
    Report104Component,
    Report104aComponent,
    Report104bComponent,
    Report104cComponent,
    Report105Component,
    Report105aComponent,
    Report105bComponent,
    Report105cComponent,
    Report106Component,
    Report106aComponent,
    Report106bComponent,
    Report106cComponent
  ],
  imports: [
    CommonModule,
    StudentEnrolmentRoutingModule,
    NgxPaginationModule
  ],
  exports:[
    Report100Component,
    Report100aComponent,
    Report100bComponent,
    Report100cComponent,
    Report101Component,
    Report101aComponent,
    Report101bComponent,
    Report101cComponent,
    Report102Component,
    Report102aComponent,
    Report102bComponent,
   Report102cComponent,
   Report103Component,
   Report103aComponent,
   Report103bComponent,
   Report103cComponent,
   Report104Component,
   Report104aComponent,
   Report104bComponent,
    Report104cComponent,
    Report105Component,
    Report105aComponent,
    Report105bComponent,
    Report105cComponent,
    Report106Component,
    Report106aComponent,
    Report106bComponent,
    Report106cComponent
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
})
export class StudentEnrolmentModule { }
