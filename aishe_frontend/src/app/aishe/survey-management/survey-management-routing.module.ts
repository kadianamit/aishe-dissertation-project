import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SurveyManagementComponent } from './survey-management.component';
import { SurveyActionComponent } from './survey-action/survey-action.component';
import { SurveyLogComponent } from './survey-log/survey-log.component';

const routes: Routes = [{
  path: '', component: SurveyManagementComponent,
  children: [
    { path: 'surveyAction', component: SurveyActionComponent },
    { path: 'surveyLog', component: SurveyLogComponent }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SurveyManagementRoutingModule { }
