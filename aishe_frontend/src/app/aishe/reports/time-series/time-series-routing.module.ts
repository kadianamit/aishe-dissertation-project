import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TimeSeriesComponent } from './time-series.component';

const routes: Routes = [{ path: '', component: TimeSeriesComponent }, { path: 'NonTeachingStaff', loadChildren: () => import('./non-teaching-staff/non-teaching-staff.module').then(m => m.NonTeachingStaffModule) }, { path: 'StudentEnrolment', loadChildren: () => import('./student-enrolment/student-enrolment.module').then(m => m.StudentEnrolmentModule) }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TimeSeriesRoutingModule { }
