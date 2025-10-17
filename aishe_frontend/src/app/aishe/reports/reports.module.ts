import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportsRoutingModule } from './reports-routing.module';
import { ReportsComponent } from './reports.component';
import { StudentEnrolmentModule } from './student-enrolment/student-enrolment.module';
import { ProgrammesDisciplineModule } from './programmes-discipline/programmes-discipline.module';
import { NonTeachingStaffModule } from './non-teaching-staff/non-teaching-staff.module';
import { TeachingStaffModule } from './teaching-staff/teaching-staff.module';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { TimeSeriesModule } from './time-series/time-series.module';
import { FinanceModule } from './finance/finance.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { BasicReportsModule } from './basic-reports/basic-reports.module';
import { NumberOfInstitutionsModule } from './number-of-institutions/number-of-institutions.module';
import { OutTurnModule } from './out-turn/out-turn.module';
import { InfrastructureModule } from './infrastructure/infrastructure.module';
import { ListOfInstitutionModule } from './list-of-institution/list-of-institution.module';
import { PupilTeacherRatioModule } from './pupil-teacher-ratio/pupil-teacher-ratio.module';
import { ListOfReportingInstitutionsModule } from './list-of-reporting-institutions/list-of-reporting-institutions.module';
import { GenderRatioModule } from './gender-ratio/gender-ratio.module';
import { UgcReportsModule } from './ugc-reports/ugc-reports.module';
import { RemunerationModule } from './remuneration/remuneration.module';
import { DataUserModule } from './data-user/data-user.module';
import { InstitutionContactDetailsModule } from './institution-contact-details/institution-contact-details.module';
import { ProgressMonitoringModule } from './progress-monitoring/progress-monitoring.module';
import { OthersModule } from './others/others.module';
import { DataSharingModule } from './data-sharing/data-sharing.module';
import { KnowYourInstitutionsModule } from './know-your-institutions/know-your-institutions.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/sharedModule/shared/shared.module';
import { InstitutionManagementReportRoutingModule } from './institution-management-report/institution-management-report-routing.module';
import { InstitutionManagementReportModule } from './institution-management-report/institution-management-report.module';


@NgModule({
  declarations: [
    ReportsComponent
  ],
  imports: [
    CommonModule,
    ReportsRoutingModule,
    StudentEnrolmentModule,
    ProgrammesDisciplineModule,
    NonTeachingStaffModule,
    TeachingStaffModule,
    AngularMaterialModule,
    TimeSeriesModule,
    FinanceModule,
    NgxPaginationModule,
    BasicReportsModule,
    NumberOfInstitutionsModule,
    OutTurnModule,
    InfrastructureModule,
    ListOfInstitutionModule,
    PupilTeacherRatioModule,
    ListOfReportingInstitutionsModule,
    GenderRatioModule,
    UgcReportsModule,
    RemunerationModule,
    DataUserModule,
    InstitutionContactDetailsModule,
    ProgressMonitoringModule,
    OthersModule,
    DataSharingModule,
    KnowYourInstitutionsModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule,
    InstitutionManagementReportModule,
       InstitutionManagementReportRoutingModule
    
  ], exports: [ReportsComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
})
export class ReportsModule {
}
