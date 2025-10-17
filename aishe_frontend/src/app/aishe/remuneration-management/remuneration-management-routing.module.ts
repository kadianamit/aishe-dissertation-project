import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RemunerationManagementComponent } from './remuneration-management.component';


const routes: Routes = [{ path: '', component: RemunerationManagementComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RemunerationManagementRoutingModule { }
