import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DataSharingComponent } from './data-sharing.component';

const routes: Routes = [{ path: '', component: DataSharingComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DataSharingRoutingModule { }
