import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OutTurnRoutingModule } from './out-turn-routing.module';
import { OutTurnComponent } from './out-turn.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { Report52Component } from './report52/report52.component';
import { Report55Component } from './report55/report55.component';
import { Report55aComponent } from './report55a/report55a.component';
import { Report55bComponent } from './report55b/report55b.component';
import { Report53Component } from './report53/report53.component';
import { Report54Component } from './report54/report54.component';
import { Report145Component } from './report145/report145.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({


  declarations: [
    OutTurnComponent,
    Report52Component,
    Report55Component,
    Report55bComponent,
       Report55aComponent,
       Report53Component,
       Report54Component,
       Report145Component,
  ],
  imports: [
    CommonModule,
    OutTurnRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    MatSelectFilterModule,
    NgxPaginationModule
  ],exports:[
    OutTurnComponent,
    Report52Component,
    Report55Component,
    Report55aComponent,
    Report55bComponent,
    Report53Component,
    Report54Component,
    Report145Component,
    
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
})
export class OutTurnModule { }
