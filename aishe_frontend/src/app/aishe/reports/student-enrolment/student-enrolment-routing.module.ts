import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentEnrolmentComponent } from './student-enrolment.component';

const routes: Routes = [{ path: '', component: StudentEnrolmentComponent },
{ path: 'institutionWise', loadChildren: () => import('./institution-wise/institution-wise.module').then(m => m.InstitutionWiseModule) },

{ path: 'districtWise', loadChildren: () => import('./district-wise/district-wise.module').then(m => m.DistrictWiseModule) },

{ path: 'stateWise', loadChildren: () => import('./state-wise/state-wise.module').then(m => m.StateWiseModule) },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentEnrolmentRoutingModule { }
