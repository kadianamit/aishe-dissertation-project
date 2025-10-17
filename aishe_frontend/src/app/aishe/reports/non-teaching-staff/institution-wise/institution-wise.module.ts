import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstitutionWiseRoutingModule } from './institution-wise-routing.module';
import { InstitutionWiseComponent } from './institution-wise.component';
import { Report27Component } from './report27/report27.component';
import { Report28Component } from './report28/report28.component';
import { Report25Component } from './report25/report25.component';
import { Report26Component } from './report26/report26.component';
import { Report29Component } from './report29/report29.component';
import { Report30Component } from './report30/report30.component';
import { Report135Component } from './report135/report135.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    InstitutionWiseComponent,
    Report27Component,
    Report28Component,
    Report25Component,
    Report26Component,
    Report29Component,
    Report30Component,
    Report135Component
  ],
  imports: [
    CommonModule,
    InstitutionWiseRoutingModule,
    NgxPaginationModule
  ],exports:[
    Report25Component,
    Report26Component,
    Report27Component,
    Report28Component,
    Report29Component,
    Report30Component,
    Report135Component,
  ]
})
export class InstitutionWiseModule { }
