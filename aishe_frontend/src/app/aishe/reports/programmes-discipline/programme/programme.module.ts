import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { ProgrammeRoutingModule } from './programme-routing.module';
import { ProgrammeComponent } from './programme.component';
import { Report110Component } from './report110/report110.component';
import { Report110aComponent } from './report110a/report110a.component';
import { Report38bComponent } from './report38b/report38b.component';
import { Report110bComponent } from './report110b/report110b.component';
import { Report110cComponent } from './report110c/report110c.component';
import { Report120Component } from './report120/report120.component';
import { Report126Component } from './report126/report126.component';
import { Report126aComponent } from './report126a/report126a.component';
import { Report126bComponent } from './report126b/report126b.component';
import { Report126cComponent } from './report126c/report126c.component';
import { Report38cComponent } from './report38c/report38c.component';
import { Report65Component } from './report65/report65.component';
import { Report150Component } from './report150/report150.component';
import { Report150aComponent } from './report150a/report150a.component';
import { Report150bComponent } from './report150b/report150b.component';
import { Report150cComponent } from './report150c/report150c.component';


@NgModule({
  declarations: [
    ProgrammeComponent,
    Report38bComponent,
    Report110Component,
    Report110aComponent,
    Report110bComponent,
    Report110cComponent,
    Report120Component,
    Report126Component,
    Report126aComponent,
    Report126bComponent,
    Report126cComponent,
    Report38cComponent,
    Report65Component,
    Report150Component,
    Report150aComponent,
    Report150bComponent,
    Report150cComponent,
  ],
  imports: [
    CommonModule,
    ProgrammeRoutingModule,
    NgxPaginationModule
  ], exports:[
    Report38bComponent,
    Report110Component,
    Report110aComponent,
    Report110bComponent,
    Report110cComponent,
    Report120Component,
    Report126Component,
    Report126aComponent,
    Report126bComponent,
    Report126cComponent,
    Report65Component,
    Report38cComponent,
    Report150Component,
    Report150aComponent,
    Report150bComponent,
    Report150cComponent,
  ]
})
export class ProgrammeModule { }
