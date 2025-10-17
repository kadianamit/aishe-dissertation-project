import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApprovingAuthorityComponent } from './approving-authority/approving-authority.component';
import { DetailsComponent } from './details.component';
import { KnowAisheCodeComponent } from './know-aishe-code/know-aishe-code.component';

const routes: Routes = [{
  path: '', component: DetailsComponent,
  children: [
    { path: 'approvingAuthority', component: ApprovingAuthorityComponent },
    { path: 'knowAisheCode', component: KnowAisheCodeComponent },
    
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DetailsRoutingModule { }
