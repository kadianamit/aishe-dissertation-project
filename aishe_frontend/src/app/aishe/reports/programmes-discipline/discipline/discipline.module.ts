import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DisciplineRoutingModule } from './discipline-routing.module';
import { DisciplineComponent } from './discipline.component';
import { Report38aComponent } from './report38a/report38a.component';
import { Report109Component } from './report109/report109.component';
import { Report109aComponent } from './report109a/report109a.component';
import { Report109bComponent } from './report109b/report109b.component';
import { Report109cComponent } from './report109c/report109c.component';
import { Report121Component } from './report121/report121.component';
import { Report121aComponent } from './report121a/report121a.component';
import { Report121bComponent } from './report121b/report121b.component';
import { Report121cComponent } from './report121c/report121c.component';
import { NgxPaginationModule } from 'ngx-pagination';



@NgModule({
  declarations: [
    DisciplineComponent,
    Report38aComponent,
    Report109Component,
    Report109aComponent,
    Report109bComponent,
    Report109cComponent,
    Report121Component,
    Report121aComponent,
    Report121bComponent,
    Report121cComponent,

  ],
  imports: [
    CommonModule,
    DisciplineRoutingModule,
    NgxPaginationModule
  ],
  exports:[
    Report38aComponent,
    Report109Component,
    Report109aComponent,
    Report109cComponent,
    Report109bComponent,
    Report121Component,
    Report121aComponent,
    Report121bComponent,
    Report121cComponent,
  ]
})
export class DisciplineModule { }
