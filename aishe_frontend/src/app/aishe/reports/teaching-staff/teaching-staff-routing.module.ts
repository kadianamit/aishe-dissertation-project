import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeachingStaffComponent } from './teaching-staff.component';

const routes: Routes = [{ path: '', component: TeachingStaffComponent },
{ path: 'stateWise', loadChildren: () => import('./state-wise/state-wise.module').then(m => m.StateWiseModule) },
{ path: 'districtWise', loadChildren: () => import('./district-wise/district-wise.module').then(m => m.DistrictWiseModule) },
{ path: 'institutionWise', loadChildren: () => import('./institution-wise/institution-wise.module').then(m => m.InstitutionWiseModule) }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeachingStaffRoutingModule { }
