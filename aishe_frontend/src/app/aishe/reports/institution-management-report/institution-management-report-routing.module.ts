import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstitutionManagementReportComponent } from './institution-management-report.component';

const routes: Routes = [{ path: '', component: InstitutionManagementReportComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstitutionManagementReportRoutingModule { }
