import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListOfReportingInstitutionsComponent } from './list-of-reporting-institutions.component';

const routes: Routes = [{ path: '', component: ListOfReportingInstitutionsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ListOfReportingInstitutionsRoutingModule { }
