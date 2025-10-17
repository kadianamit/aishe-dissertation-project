import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProgressMonitoringComponent } from './progress-monitoring.component';

const routes: Routes = [{ path: '', component: ProgressMonitoringComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProgressMonitoringRoutingModule { }
