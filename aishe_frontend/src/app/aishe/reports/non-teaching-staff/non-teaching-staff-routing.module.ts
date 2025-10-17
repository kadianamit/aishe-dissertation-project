import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NonTeachingStaffComponent } from './non-teaching-staff.component';

const routes: Routes = [{ path: '', component: NonTeachingStaffComponent }, { path: 'district-wise', loadChildren: () => import('./district-wise/district-wise.module').then(m => m.DistrictWiseModule) }, { path: 'institution-wise', loadChildren: () => import('./institution-wise/institution-wise.module').then(m => m.InstitutionWiseModule) }, { path: 'state-wise', loadChildren: () => import('./state-wise/state-wise.module').then(m => m.StateWiseModule) }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NonTeachingStaffRoutingModule { }
