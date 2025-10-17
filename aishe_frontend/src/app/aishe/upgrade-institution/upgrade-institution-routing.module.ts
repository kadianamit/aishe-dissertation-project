import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UpgradeInstitutionComponent } from './upgrade-institution.component';
import { MergeInstitutionComponent } from './merge-institution/merge-institution.component';
import { SurveyWiseComponent } from './survey-wise/survey-wise.component';
import { CollegeToUniversityComponent } from './college-to-university/college-to-university.component';
import { StandaloneToCollegeComponent } from './standalone-to-college/standalone-to-college.component';
import { StandaloneToUniversityComponent } from './standalone-to-university/standalone-to-university.component';
import { InactiveStandaloneComponent } from './inactive-standalone/inactive-standalone.component';
import { InactiveDeaffilliateComponent } from './inactive-deaffilliate/inactive-deaffilliate.component';
import { SurveyParticipantComponent } from './survey-participant/survey-participant.component';
import { MissingUserComponent } from './missing-user/missing-user.component';
import { DeleteReportComponent } from './delete-report/delete-report.component';
import { FeedbackListComponent } from './feedback-list/feedback-list.component';
import { FeedbackListSnoComponent } from './feedback-list-sno/feedback-list-sno.component';
import { UniversityWiseCollegeComponent } from './university-wise-college/university-wise-college.component';
import { UniversityWiseOffcampusComponent } from './university-wise-offcampus/university-wise-offcampus.component';

const routes: Routes = [{
  path: '', component: UpgradeInstitutionComponent,
  children: [
    { path: 'merge', component: MergeInstitutionComponent },
    { path: 'searchMerge/:id', component: MergeInstitutionComponent },
    { path: 'surveyWise/:year', component: SurveyWiseComponent },

    { path: 'collegeToUniversity', component: CollegeToUniversityComponent },
    { path: 'searchCollegeToUniversity/:id', component: CollegeToUniversityComponent },

    { path: 'standaloneToUniversity', component: StandaloneToUniversityComponent },
    { path: 'searchStandaloneToUniversity/:id', component: StandaloneToUniversityComponent },

    { path: 'standaloneToCollege', component: StandaloneToCollegeComponent },
    { path: 'searchStandaloneToCollege/:id', component: StandaloneToCollegeComponent },

    { path: 'inactiveStandalone', component: InactiveStandaloneComponent },
    { path: 'searchInactiveStandalone/:id', component: InactiveStandaloneComponent },

    { path: 'inactiveDeaffilliate', component: InactiveDeaffilliateComponent },
    { path: 'searchInactiveDeaffilliate/:id', component: InactiveDeaffilliateComponent },

    { path: 'participatedSurvey', component: SurveyParticipantComponent },
    { path: 'missingUser', component: MissingUserComponent },

    { path: 'delete-report', component: DeleteReportComponent },
    { path: 'feedback_list_hei', component: FeedbackListComponent },
    { path: 'feedback_list_sno', component: FeedbackListSnoComponent },
    {path:'universityWiseCollege',component:UniversityWiseCollegeComponent},
    {path:'universityWiseOffcampus',component:UniversityWiseOffcampusComponent}

  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UpgradeInstitutionRoutingModule { }
