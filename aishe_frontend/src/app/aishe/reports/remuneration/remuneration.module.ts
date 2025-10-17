import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RemunerationRoutingModule } from './remuneration-routing.module';
import { RemunerationComponent } from './remuneration.component';
import { Report91Component } from './report91/report91.component';
import { Report91aComponent } from './report91a/report91a.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    RemunerationComponent,
    Report91Component,
    Report91aComponent
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    MatSelectFilterModule,
    ReactiveFormsModule,
    RemunerationRoutingModule,
    NgxPaginationModule
  ],exports: [
    RemunerationComponent,
    Report91Component,
    Report91aComponent
  ]
})
export class RemunerationModule { }
