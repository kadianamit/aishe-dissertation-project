import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DataUserLoginComponent } from './data-user-login.component';
import { ForgotDataUserPasswordComponent } from './forgot-data-user-password/forgot-data-user-password.component';
import { ResetDataUserComponent } from './reset-data-user/reset-data-user.component';
import { DataUserComponent } from './data-user/data-user.component';

const routes: Routes = [
  {
    path: '', component: DataUserLoginComponent,
    children: [
      { path:'',component:DataUserComponent},
      { path: 'forgotPassword', component: ForgotDataUserPasswordComponent },
      { path: 'resetPassword/:id', component: ResetDataUserComponent }
    ]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DataUserLoginRoutingModule { }
