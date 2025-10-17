import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DistrictWiseComponent } from './district-wise.component';

const routes: Routes = [{ path: '', component: DistrictWiseComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DistrictWiseRoutingModule { }
