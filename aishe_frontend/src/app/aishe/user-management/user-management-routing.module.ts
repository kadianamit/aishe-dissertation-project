import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewRegistartionComponent } from './new-registartion/new-registartion.component';
import { UserManagementComponent } from './user-management.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  {
    path: '', component: UserManagementComponent,
    children: [
      
      { path: 'user', component: UserComponent },
      { path: 'userRegistration', component: NewRegistartionComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserManagementRoutingModule { }
