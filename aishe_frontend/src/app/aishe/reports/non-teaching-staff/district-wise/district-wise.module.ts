import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DistrictWiseRoutingModule } from './district-wise-routing.module';
import { DistrictWiseComponent } from './district-wise.component';
import { Report117Component } from './report117/report117.component';
import { Report117aComponent } from './report117a/report117a.component';
import { Report117bComponent } from './report117b/report117b.component';
import { Report117cComponent } from './report117c/report117c.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    DistrictWiseComponent,
    Report117Component,
    Report117aComponent,
    Report117bComponent,
    Report117cComponent,
   
  ],
  imports: [
    CommonModule,
    NgxPaginationModule,
    DistrictWiseRoutingModule
  ],exports:[Report117Component,Report117aComponent,Report117bComponent,Report117cComponent],
})
export class DistrictWiseModule { }
