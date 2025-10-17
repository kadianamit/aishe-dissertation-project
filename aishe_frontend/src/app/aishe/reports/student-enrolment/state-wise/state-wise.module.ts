import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StateWiseRoutingModule } from './state-wise-routing.module';
import { StateWiseComponent } from './state-wise.component';
import { Report31Component } from './report31/report31.component';
import { Report32Component } from './report32/report32.component';
import { Report35AComponent } from './report35a/report35a.component';
import { Report35bComponent } from './report35b/report35b.component';
import { Report35cComponent } from './report35c/report35c.component';
import { Report39Component } from './report39/report39.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { Report170Component } from './report170/report170.component';
import { Report32aComponent } from './report32a/report32a.component';
import { Report33Component } from './report33/report33.component';
import { Report34Component } from './report34/report34.component';
import { Report43Component } from './report43/report43.component';
import { Report169Component } from './report169/report169.component';
import { Report32bComponent } from './report32b/report32b.component';
import { NgxPaginationModule } from 'ngx-pagination';



@NgModule({
  declarations: [
    StateWiseComponent,
    Report31Component,
    Report32Component,
    Report35cComponent,
    Report35bComponent,
    Report39Component,
    Report35AComponent,
    Report170Component,
    Report32aComponent,
    Report33Component,
    Report34Component,
    Report43Component,
    Report169Component,
    Report32bComponent
  ],
  imports: [
    CommonModule,
    StateWiseRoutingModule,
    AngularMaterialModule,
    NgxPaginationModule
  ],exports:[
    Report31Component,
    Report32Component,
    Report35AComponent,
    Report35cComponent,
    Report35bComponent,
    Report39Component,
    Report170Component,
    Report32aComponent,
    Report33Component,
    Report34Component,
    Report32aComponent,
    Report32aComponent,
    Report43Component,
    Report169Component,
    Report32bComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class StateWiseModule { }
