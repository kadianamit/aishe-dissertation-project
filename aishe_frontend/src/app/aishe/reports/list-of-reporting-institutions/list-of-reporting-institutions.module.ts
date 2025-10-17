import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ListOfReportingInstitutionsRoutingModule } from './list-of-reporting-institutions-routing.module';
import { ListOfReportingInstitutionsComponent } from './list-of-reporting-institutions.component';
import { Report4Component } from './report4/report4.component';
import { Report5Component } from './report5/report5.component';
import { Report142Component } from './report142/report142.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { ListOfInstitutionModule } from "../list-of-institution/list-of-institution.module";
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
    declarations: [
        ListOfReportingInstitutionsComponent,
        Report4Component,
        Report5Component,
        Report142Component
    ],
    imports: [
        CommonModule,
        ListOfReportingInstitutionsRoutingModule,
        AngularMaterialModule,
        ReactiveFormsModule,
        MatSelectFilterModule,
        ListOfInstitutionModule,
        NgxPaginationModule
    ],exports:[
      ListOfReportingInstitutionsComponent,
    ]
})
export class ListOfReportingInstitutionsModule { }
