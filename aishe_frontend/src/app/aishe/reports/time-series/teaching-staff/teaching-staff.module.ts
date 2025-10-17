import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeachingStaffRoutingModule } from './teaching-staff-routing.module';
import { TeachingStaffComponent } from './teaching-staff.component';
import { Report124Component } from './report124/report124.component';
import { Report124aComponent } from './report124a/report124a.component';
import { Report124bComponent } from './report124b/report124b.component';
import { Report124cComponent } from './report124c/report124c.component';
import { Report125Component } from './report125/report125.component';
import { Report125aComponent } from './report125a/report125a.component';
import { Report125bComponent } from './report125b/report125b.component';
import { Report125cComponent } from './report125c/report125c.component';
import { Report171Component } from './report171/report171.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    TeachingStaffComponent,
    Report124Component,
    Report124aComponent,
    Report124bComponent,
    Report124cComponent,
    Report125Component,
    Report125aComponent,
    Report125bComponent,
    Report125cComponent,
    Report171Component
  ],
  imports: [
    CommonModule,
    TeachingStaffRoutingModule,
    NgxPaginationModule
  ],
  exports:[
    Report124Component,
    Report124aComponent,
    Report124bComponent,
    Report124cComponent,
    Report125Component,
    Report125aComponent,
    Report125bComponent,
    Report125cComponent,
    Report171Component
  ]
})
export class TeachingStaffModule { }
