import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSelectFilterModule } from 'mat-select-filter';
import { OthersRoutingModule } from './others-routing.module';
import { OthersComponent } from './others.component';
import { Report128Component } from './report128/report128.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { Report130Component } from './report130/report130.component';
import { Report132Component } from './report132/report132.component';
import { Report113Component } from './report113/report113.component';
import { Report114Component } from './report114/report114.component';
import { Report118Component } from './report118/report118.component';
import { Report119Component } from './report119/report119.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    OthersComponent,
    Report128Component,
    Report130Component,
    Report132Component,
    Report113Component,
    Report114Component,
    Report118Component,
    Report119Component
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    OthersRoutingModule,
    MatSelectFilterModule,
    NgxPaginationModule
  ],
  exports:[
    OthersComponent,
    Report128Component,
    Report130Component,
    Report132Component,
    Report113Component,
    Report114Component,
    Report118Component,
    Report119Component
  ]
})

export class OthersModule { }
