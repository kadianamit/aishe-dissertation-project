import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EnrolmentEstimationComponent } from './enrolment-estimation.component';


const routes: Routes = [{ path: '', component: EnrolmentEstimationComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EnrolmentEstimationRoutingModule { }
