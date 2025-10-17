import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AisheComponent } from './aishe.component';
import { EditRegistrationComponent } from './edit-registration/edit-registration.component';
import { PrefilledDetailsComponent } from './prefilled-details/prefilled-details.component';
import { ProgrammeManagementComponent } from './programme-management/programme-management.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MoeManagementComponent } from './moe-management/moe-management.component';
import { GeoComponent } from './geo/geo.component';
import { DeactivatedGuard } from 'src/gaurd/deactivated.guard';
import { RequestForShiftUnversityComponent } from './institution-management/request-for-shift-unversity/request-for-shift-unversity.component';
import { DocumentManagementComponent } from './document-management/document-management.component';
import { ApproveRejectUniversityListComponent } from './institution-management/approve-reject-university-list/approve-reject-university-list.component';
import { DetailUnitCellComponent } from './detail-unit-cell/detail-unit-cell.component';


const routes: Routes = [
  {
    path: '',
    component: AisheComponent,
    children: [
      { path: 'moe_management', component: MoeManagementComponent },
      { path: 'editRegistration', component: EditRegistrationComponent },
      { path: 'home', component: PrefilledDetailsComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'programme-management', component: ProgrammeManagementComponent },
      {
        path: 'formManagement',
        loadChildren: () =>
          import('./form-management/form-management.module').then(
            (m) => m.FormManagementModule
          ),
      },
      {
        path: 'userManagement',
        loadChildren: () =>
          import('./user-management/user-management.module').then(
            (m) => m.UserManagementModule
          ),
      },
      {
        path: 'Institution-Management',
        loadChildren: () =>
          import('./institution-management/institution-management.module').then(
            (m) => m.InstitutionManagementModule
          ),
      },
      {
        path: 'Institution-Management/:id',
        loadChildren: () =>
          import('./institution-management/institution-management.module').then(
            (m) => m.InstitutionManagementModule
          ),
      },
      {
        path: 'registration-management',
        loadChildren: () =>
          import('./registration-management/registration-management.module').then(
            (m) => m.RegistrationManagementModule
          ),
      },
      {
        path: 'progress',
        loadChildren: () =>
          import('./progress-moniter/progress-moniter.module').then(
            (m) => m.ProgressMoniterModule
          ),
      },
      {
        path: 'programme-management',
        loadChildren: () =>
          import('./programme-management/programme-management.component').then(
            (m) => m.ProgrammeManagementComponent
          ),
      },
      {
        path: 'reports',
        loadChildren: () =>
          import('./reports/reports.module').then((m) => m.ReportsModule),
      },
      {
        path: 'reportsInst/:id',
        loadChildren: () =>
          import('./reports/reports.module').then((m) => m.ReportsModule),
      },
      {
        path: 'reports2020',
        loadChildren: () =>
          import('./reports/reports.module').then((m) => m.ReportsModule),
      },
      {
        path: 'ciso',
        loadChildren: () =>
          import('./ciso/ciso.module').then((m) => m.CisoModule),
      },
      {
        path: 'surveyManagement',
        loadChildren: () =>
          import('./survey-management/survey-management.module').then(
            (m) => m.SurveyManagementModule
          ),
      },
      {
        path: 'ntaQuestionnaire',
        loadChildren: () => import('./nta/nta.module').then((m) => m.NtaModule),
      },
      {
        path: 'remunerationManagement',
        loadChildren: () =>
          import(
            './remuneration-management/remuneration-management.module'
          ).then((m) => m.RemunerationManagementModule),
      },
      {
        path: 'scholarship',
        loadChildren: () =>
          import('./scholarship/scholarship.module').then(
            (m) => m.ScholarshipModule
          ),
      },
      {
        path: 'foreign_ins',
        loadChildren: () =>
          import('./foreign/foreign.module').then((m) => m.ForeignModule),
      },

      {
        path: 'dcf_demo',
        loadChildren: () =>
          import('./wed-demo-dcf/wed-demo-dcf.module').then((m) => m.WedDemoDcfModule),
      },

      {
        path: 'emailSmsMang',
        loadChildren: () =>
          import('./email-sms-management/email-sms-management.module').then(m => m.EmailSmsManagementModule)
      },
      {
        path: 'enrolmentEstimation',
        loadChildren: () =>
          import('./enrolment-estimation/enrolment-estimation.module').then(m => m.EnrolmentEstimationModule)
      },
      {
        path: 'universalsearch',
        loadChildren: () =>
          import('./universalsearch/universalsearch.module').then(m => m.UniversalsearchModule)
      },
      { path: 'geoLocation', component: GeoComponent },
      { path: 'report', loadChildren: () => import('./upgrade-institution/upgrade-institution.module').then(m => m.UpgradeInstitutionModule) },
      { path: 'research_and_development', loadChildren: () => import('./research-and-development/research-and-development.module').then(m => m.ResearchAndDevelopmentModule) },
      { path: 'feedback', loadChildren: () => import('./feedback/feedback.module').then(m => m.FeedbackModule) },

      { path: 'requestForShiftUnversity', component: RequestForShiftUnversityComponent },
      { path: 'approveRejectUniversity', component: ApproveRejectUniversityListComponent },
      { path: 'documentManagement', component: DocumentManagementComponent },
      { path: 'datasharingreq', loadChildren: () => import('./datasharing/datasharing.module').then(m => m.DatasharingModule) },
      { path: 'detailUnitCell', component: DetailUnitCellComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AisheRoutingModule { }
