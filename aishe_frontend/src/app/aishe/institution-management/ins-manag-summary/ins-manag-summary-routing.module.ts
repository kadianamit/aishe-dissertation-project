import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InsManagSummaryComponent } from './ins-manag-summary.component';

const routes: Routes = [{ path: '', component: InsManagSummaryComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InsManagSummaryRoutingModule { }
