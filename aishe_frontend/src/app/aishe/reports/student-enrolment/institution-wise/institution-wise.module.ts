import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstitutionWiseRoutingModule } from './institution-wise-routing.module';
import { InstitutionWiseComponent } from './institution-wise.component';
import { Report38Component } from './report38/report38.component';
import { Report115Component } from './report115/report115.component';
import { Report42Component } from './report42/report42.component';
import { Report40Component } from './report40/report40.component';
import { Report41Component } from './report41/report41.component';
import { Report127Component } from './report127/report127.component';
import { Report127aComponent } from './report127a/report127a.component';
import { Report36Component } from './report36/report36.component';
import { Report37Component } from './report37/report37.component';
import { Report127bComponent } from './report127b/report127b.component';
import { Report127cComponent } from './report127c/report127c.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    InstitutionWiseComponent,
    Report38Component,
    Report115Component,
    Report42Component,
    Report40Component,
    Report41Component,
    Report127Component,
    Report127aComponent,
    Report36Component,
    Report37Component,
    Report127bComponent,
    Report127cComponent
  ],
  imports: [
    CommonModule,
    InstitutionWiseRoutingModule,
    NgxPaginationModule
  ],exports:[
    Report38Component,
    Report115Component,
    Report42Component,
    Report40Component,
    Report41Component,
    Report127Component,
    Report127aComponent,
    Report36Component,
    Report37Component,
    Report127bComponent,
    Report127cComponent
  ]
})
export class InstitutionWiseModule { }
