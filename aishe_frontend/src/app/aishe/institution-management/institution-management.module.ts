
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InstitutionManagementRoutingModule } from './institution-management-routing.module';
import { InstitutionManagementComponent } from './institution-management.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { SharedModule } from 'src/app/sharedModule/shared/shared.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { DataViewComponent } from './data-view/data-view.component';
import { DataListComponent } from './data-list/data-list.component';
import { DeAffiliateComponent } from './de-affiliate/de-affiliate.component';
 import { ApprovedUniversityListComponent } from './approved-university-list/approved-university-list.component';
import { SearchFilterPipe } from 'src/app/institution-directory/search-filter.pipe';
import { NewAffiliationComponent } from './new-affiliation/new-affiliation.component';
import { StandaloneComponent } from './standalone/standalone.component';
import { StandaloneViewComponent } from './standalone-view/standalone-view.component';
import { StandaloneEditComponent } from './standalone-edit/standalone-edit.component';
import { StandaloneApproveComponent } from './standalone-approve/standalone-approve.component';
import { StandaloneApproveListComponent } from './standalone-approve-list/standalone-approve-list.component';
import { ApproveComponent } from './approve/approve.component';
import { UniversityComponent } from './university/university.component';
import { UniversityViewComponent } from './university-view/university-view.component';
import { UniversityEditComponent } from './university-edit/university-edit.component';
import { MergecollegesComponent } from './mergecolleges/mergecolleges.component';
import { DeletecollegeComponent } from './deletecollege/deletecollege.component';
import { UniversityDeleteComponent } from './university-delete/university-delete.component';
import { UpgradeUniversityComponent } from './upgrade-university/upgrade-university.component';
import { StandaloneStatusComponent } from './standalone-status/standalone-status.component';
import { UpgradetocollegeComponent } from './upgradetocollege/upgradetocollege.component';
import { ConverttoCollegeComponent } from './convertto-college/convertto-college.component';
import { UpgradeStandalonetoUniversityComponent } from './upgrade-standaloneto-university/upgrade-standaloneto-university.component';
import { FormvalidationDirective } from 'src/app/shared/directive/formvalidation.directive';
import { InsitutionalReportComponent } from './institutional-report/institutional-report.component';
import { ActiveinactiveuniversityComponent } from './activeinactiveuniversity/activeinactiveuniversity.component';
import { TrackApproveStatusComponent } from './track-approve-status/track-approve-status.component';
import { CheckSimilarInsitituteComponent } from './check-similar-insititute/check-similar-insititute.component';
import { RequestForShiftUnversityComponent } from './request-for-shift-unversity/request-for-shift-unversity.component';
import { ApproveRejectUniversityListComponent } from './approve-reject-university-list/approve-reject-university-list.component';
import { InstitutionManagementDashboardComponent } from './institution-management-dashboard/institution-management-dashboard.component';
import { RejectDetailComponent } from './reject-detail/reject-detail.component';
import { StandaloneRejectedReqComponent } from './standalone-rejected-req/standalone-rejected-req.component';







@NgModule({
  declarations: [
    InstitutionManagementComponent,
    DataViewComponent,
    DataListComponent,
    DeAffiliateComponent,
    ApproveComponent,
    ApprovedUniversityListComponent,
    SearchFilterPipe,
    NewAffiliationComponent,
    StandaloneComponent,
    StandaloneViewComponent,
    StandaloneEditComponent,
    StandaloneApproveComponent,
    StandaloneApproveListComponent,
    UniversityComponent,
    UniversityViewComponent,
    UniversityEditComponent,
    MergecollegesComponent,
    DeletecollegeComponent,
    UniversityDeleteComponent,
    UpgradeUniversityComponent,
   FormvalidationDirective,
    StandaloneStatusComponent,
    UpgradetocollegeComponent,
    ConverttoCollegeComponent,
    UpgradeStandalonetoUniversityComponent,
    InsitutionalReportComponent,
    ActiveinactiveuniversityComponent,
    TrackApproveStatusComponent,
    CheckSimilarInsitituteComponent,
    RequestForShiftUnversityComponent,
    ApproveRejectUniversityListComponent,
    InstitutionManagementDashboardComponent,
    RejectDetailComponent,
    StandaloneRejectedReqComponent
  ],
  imports: [
    CommonModule,
    InstitutionManagementRoutingModule,
    NgxPaginationModule,
    AngularMaterialModule,SharedModule,FormsModule,ReactiveFormsModule
  ]

})
export class InstitutionManagementModule { }
