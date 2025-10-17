import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DatasharingComponent } from './datasharing.component';

const routes: Routes = [{ path: '', component: DatasharingComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DatasharingRoutingModule { }
