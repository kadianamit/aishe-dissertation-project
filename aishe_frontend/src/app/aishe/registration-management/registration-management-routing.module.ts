import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationManagementComponent } from './registration-management.component';
import { StartRegComponent } from './start-reg/start-reg.component';
import { ViewRegComponent } from './view-reg/view-reg.component';


const routes: Routes = [
  { path: '', component: RegistrationManagementComponent,
  children:[ 
    { path:'start',component:StartRegComponent},
    { path:'view',component:ViewRegComponent},
 ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegistrationManagementRoutingModule { }
