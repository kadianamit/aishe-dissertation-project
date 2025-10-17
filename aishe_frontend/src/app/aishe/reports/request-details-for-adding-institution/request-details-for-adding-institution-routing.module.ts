import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RequestDetailsForAddingInstitutionComponent } from './request-details-for-adding-institution.component';

const routes: Routes = [{ path: '', component: RequestDetailsForAddingInstitutionComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RequestDetailsForAddingInstitutionRoutingModule { }
