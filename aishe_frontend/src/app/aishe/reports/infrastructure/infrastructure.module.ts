
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InfrastructureRoutingModule } from './infrastructure-routing.module';
import { InfrastructureComponent } from './infrastructure.component';
import { MatSelectFilterModule } from 'mat-select-filter';
import { ReactiveFormsModule } from '@angular/forms';
import { Report56Component } from './report56/report56.component';
import { Report57Component } from './report57/report57.component';
import { Report58Component } from './report58/report58.component';
import { Report111Component } from './report111/report111.component';
import { Report111aComponent } from './report111a/report111a.component';
import { Report111bComponent } from './report111b/report111b.component';
import { Report111cComponent } from './report111c/report111c.component';
import { Report131Component } from './report131/report131.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { Report112Component } from './report112/report112.component';
import { Report139Component } from './report139/report139.component';
import { Report140Component } from './report140/report140.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    InfrastructureComponent,
    Report56Component,
    Report57Component,
    Report58Component,
    Report111Component,
    Report111aComponent,
    Report111bComponent,
    Report111cComponent,
    Report131Component,
    Report112Component,
    Report139Component,
    Report140Component
  ],
  imports: [
    CommonModule,
    InfrastructureRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    MatSelectFilterModule,
    NgxPaginationModule
  ],exports:[
    InfrastructureComponent,
    Report56Component,
    Report57Component,
    Report58Component,
    Report111Component,
    Report111aComponent,
    Report111bComponent,
    Report111cComponent,
    Report131Component
  ]
})
export class InfrastructureModule { }
