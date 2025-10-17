import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentEnrolmentComponent } from './student-enrolment.component';

const routes: Routes = [{ path: '', component: StudentEnrolmentComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentEnrolmentRoutingModule { }
