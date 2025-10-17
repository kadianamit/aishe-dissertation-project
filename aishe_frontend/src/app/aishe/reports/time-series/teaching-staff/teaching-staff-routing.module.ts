import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeachingStaffComponent } from './teaching-staff.component';

const routes: Routes = [{ path: '', component: TeachingStaffComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeachingStaffRoutingModule { }
