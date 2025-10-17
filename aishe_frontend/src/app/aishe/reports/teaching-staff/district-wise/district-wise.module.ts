import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DistrictWiseRoutingModule } from './district-wise-routing.module';
import { DistrictWiseComponent } from './district-wise.component';
import { Report116Component } from './report116/report116.component';
import { Report116aComponent } from './report116a/report116a.component';
import { Report116bComponent } from './report116b/report116b.component';
import { Report116cComponent } from './report116c/report116c.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    DistrictWiseComponent,
    Report116Component,
    Report116aComponent,
    Report116bComponent,
    Report116cComponent
  ],
  imports: [
    CommonModule,
    DistrictWiseRoutingModule,
    NgxPaginationModule
  ],
  exports:[
    Report116Component,
    Report116aComponent,
    Report116bComponent,
    Report116cComponent
  ]
})
export class DistrictWiseModule { }
