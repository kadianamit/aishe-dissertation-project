import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PupilTeacherRatioComponent } from './pupil-teacher-ratio.component';

const routes: Routes = [{ path: '', component: PupilTeacherRatioComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PupilTeacherRatioRoutingModule { }
