import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstitutionManagementComponent } from './institution-management.component';
import { StandaloneComponent } from './standalone/standalone.component';
import { UniversityComponent } from './university/university.component';
import { CheckSimilarInsitituteComponent } from './check-similar-insititute/check-similar-insititute.component';


const routes: Routes = [

  { path: '', component: InstitutionManagementComponent },
  { path: 'standalone', component: StandaloneComponent },
  { path: 'searchStandalone/:id', component: StandaloneComponent },
  { path: 'university', component: UniversityComponent },
  { path: 'searchUniversity/:id', component: UniversityComponent },
  { path: 'summary/:id', loadChildren: () => import('./ins-manag-summary/ins-manag-summary.module').then(m => m.InsManagSummaryModule) },
  { path: 'summaryar/:id', loadChildren: () => import('./ins-manag-summary/ins-manag-summary.module').then(m => m.InsManagSummaryModule) },
  { path: 'checkSimilar', component: CheckSimilarInsitituteComponent },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstitutionManagementRoutingModule { }
