import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstitutionWiseRoutingModule } from './institution-wise-routing.module';
import { InstitutionWiseComponent } from './institution-wise.component';
import { Report19Component } from './report19/report19.component';
import { Report20Component } from './report20/report20.component';
import { Report17Component } from './report17/report17.component';
import { Report18Component } from './report18/report18.component';
import { Report21Component } from './report21/report21.component';
import { Report22Component } from './report22/report22.component';
import { Report22aComponent } from './report22a/report22a.component';
import { Report137Component } from './report137/report137.component';
import { Report165Component } from './report165/report165.component';
import { Report166Component } from './report166/report166.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    InstitutionWiseComponent,
    Report19Component,
    Report20Component,
    Report17Component,
    Report18Component,
    Report21Component,
    Report22Component,
    Report22aComponent,
    Report137Component,
    Report165Component,
    Report166Component,
    
  ],
  imports: [
    CommonModule,
    InstitutionWiseRoutingModule,
    NgxPaginationModule
  ],
  exports:[
    Report19Component,
    Report20Component,
    Report17Component,
    Report18Component,
    Report21Component,
    Report22Component,
    Report22aComponent,
    Report137Component,
    Report165Component,
    Report166Component
  ]
})
export class InstitutionWiseModule { }
