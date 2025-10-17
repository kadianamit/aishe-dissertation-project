import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResearchAndDevelopmentComponent } from './research-and-development.component';
import { ViewRADComponent } from './view-rad/view-rad.component';
import { ApproveRadComponent } from './approve-rad/approve-rad.component';
import { AddResearchAndDevelopmentComponent } from './add-research-and-development/add-research-and-development.component';
import { RejectRadComponent } from './reject-rad/reject-rad.component';

const routes: Routes = [{
  path: '', component: ResearchAndDevelopmentComponent,
  children: [
    { path: 'add', component: AddResearchAndDevelopmentComponent },
    { path: 'view', component: ViewRADComponent },
    { path: 'approveRND', component: ApproveRadComponent },
    {path:'rejectRND', component:RejectRadComponent}
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResearchAndDevelopmentRoutingModule { }
