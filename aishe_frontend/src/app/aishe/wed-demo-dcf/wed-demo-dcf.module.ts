import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WedDemoDcfRoutingModule } from './wed-demo-dcf-routing.module';
import { WedDemoDcfComponent } from './wed-demo-dcf.component';
import { BasicComponent } from './basic/basic.component';
import { ProgrameComponent } from './programe/programe.component';

import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { ForeignEnrolmentComponent } from './foreign-enrolment/foreign-enrolment.component';
import { EnrolmentComponent } from './enrolment/enrolment.component';
import { TeachingDemoDfcComponent } from './teaching-demo-dfc/teaching-demo-dfc.component';




@NgModule({
  declarations: [
    WedDemoDcfComponent,
    BasicComponent,
    ProgrameComponent,
    EnrolmentComponent,
    ForeignEnrolmentComponent,
    TeachingDemoDfcComponent
  ],
  imports: [
    
    CommonModule,
    WedDemoDcfRoutingModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule
  
    
  ],
  exports: [BasicComponent]
})
export class WedDemoDcfModule { }
