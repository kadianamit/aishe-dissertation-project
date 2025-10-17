import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UpgradeInstitutionRoutingModule } from './upgrade-institution-routing.module';
import { UpgradeInstitutionComponent } from './upgrade-institution.component';
import { MergeInstitutionComponent } from './merge-institution/merge-institution.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { SurveyWiseComponent } from './survey-wise/survey-wise.component';
import { CollegeToUniversityComponent } from './college-to-university/college-to-university.component';
import { StandaloneToCollegeComponent } from './standalone-to-college/standalone-to-college.component';
import { StandaloneToUniversityComponent } from './standalone-to-university/standalone-to-university.component';
import { InactiveStandaloneComponent } from './inactive-standalone/inactive-standalone.component';
import { InactiveDeaffilliateComponent } from './inactive-deaffilliate/inactive-deaffilliate.component';
import { SurveyParticipantComponent } from './survey-participant/survey-participant.component';
import { MatSelectFilterModule } from 'mat-select-filter';
import { MissingUserComponent } from './missing-user/missing-user.component';
import { DeleteReportComponent } from './delete-report/delete-report.component';
import { FeedbackListComponent } from './feedback-list/feedback-list.component';
import { FeedbackListSnoComponent } from './feedback-list-sno/feedback-list-sno.component';
import { UniversityWiseCollegeComponent } from './university-wise-college/university-wise-college.component';
import { UniversityWiseOffcampusComponent } from './university-wise-offcampus/university-wise-offcampus.component';



@NgModule({
  declarations: [
    UpgradeInstitutionComponent,
    MergeInstitutionComponent,
    SurveyWiseComponent,
    CollegeToUniversityComponent,
    StandaloneToCollegeComponent,
    StandaloneToUniversityComponent,
    InactiveStandaloneComponent,
    InactiveDeaffilliateComponent,
    SurveyParticipantComponent,
    MissingUserComponent,
    DeleteReportComponent,
    FeedbackListComponent,
    FeedbackListSnoComponent,
    UniversityWiseCollegeComponent,
    UniversityWiseOffcampusComponent
  ],
  imports: [
    CommonModule,
    UpgradeInstitutionRoutingModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    MatSelectFilterModule
  ]
})
export class UpgradeInstitutionModule { }
