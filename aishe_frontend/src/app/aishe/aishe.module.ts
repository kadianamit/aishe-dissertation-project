import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule, TitleCasePipe } from '@angular/common';

import { AisheRoutingModule } from './aishe-routing.module';
import { AisheComponent } from './aishe.component';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from '../angular-material.module';
import { SharedModule } from '../sharedModule/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditRegistrationComponent } from './edit-registration/edit-registration.component';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { SidebarService } from '../service/sidebar.service';
import { PrefilledDetailsComponent } from './prefilled-details/prefilled-details.component';
import { InstitutionManagementModule } from './institution-management/institution-management.module';
import { ReportsModule } from './reports/reports.module';
import { ProgrammeManagementComponent } from './programme-management/programme-management.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AllownumberanddecimalDirective } from '../shared/directive/allownumberanddecimal.directive';
import { MoeManagementComponent } from './moe-management/moe-management.component';
import { RegistrationManagementModule } from './registration-management/registration-management.module';
import { GeoComponent } from './geo/geo.component';
import { HeaderComponent } from './header/header.component';
import { ResetDataUserComponent } from './reset-data-user/reset-data-user.component';
import { DocumentManagementComponent } from './document-management/document-management.component';
import { DetailUnitCellComponent } from './detail-unit-cell/detail-unit-cell.component';





// import { Reports2020Module } from './reports2020/reports2020.module';


@NgModule({
  declarations: [
    AisheComponent,
    EditRegistrationComponent,
    SidebarComponent,
    PrefilledDetailsComponent,
    ProgrammeManagementComponent,
    DashboardComponent,
    AllownumberanddecimalDirective,
    MoeManagementComponent,
    GeoComponent,
    HeaderComponent,
    ResetDataUserComponent,
    DocumentManagementComponent,
    DetailUnitCellComponent,
 
   
    
    
  ],  
  imports: [
    CommonModule,
    AisheRoutingModule,
    MatSelectFilterModule,
    InstitutionManagementModule,
    RegistrationManagementModule,
    ReportsModule,
    AngularMaterialModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
  providers:[TitleCasePipe]
})
export class AisheModule { }
