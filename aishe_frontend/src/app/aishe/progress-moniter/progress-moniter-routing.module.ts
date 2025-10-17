import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DayWiseComponent } from './day-wise/day-wise.component';
import { MoniteringComponent } from './monitering/monitering.component';
import { ProgressMoniterComponent } from './progress-moniter.component';
import { SummaryComponent } from './summary/summary.component'
import { StateWiseMonitoringComponent } from './state-wise-monitoring/state-wise-monitoring.component';


const routes: Routes = [{
  path: '', component: ProgressMoniterComponent,
  children: [
    { path: 'summary', component: SummaryComponent },
    { path: 'monitering', component: MoniteringComponent },
    { path: 'day-wise', component: DayWiseComponent },
    {path:'stateWiseMonitoring', component: StateWiseMonitoringComponent}
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProgressMoniterRoutingModule { }
