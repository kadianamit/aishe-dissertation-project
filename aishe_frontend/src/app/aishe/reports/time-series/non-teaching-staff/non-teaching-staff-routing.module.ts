import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NonTeachingStaffComponent } from './non-teaching-staff.component';

const routes: Routes = [{ path: '', component: NonTeachingStaffComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NonTeachingStaffRoutingModule { }
