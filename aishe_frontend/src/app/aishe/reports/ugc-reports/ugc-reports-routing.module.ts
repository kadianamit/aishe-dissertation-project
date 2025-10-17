import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UgcReportsComponent } from './ugc-reports.component';

const routes: Routes = [{ path: '', component: UgcReportsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UgcReportsRoutingModule { }
