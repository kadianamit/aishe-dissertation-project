import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReportsComponent } from './reports.component';


const routes: Routes = [{
  path: '', component: ReportsComponent,
},

{ path: 'RequestDetailsForAddingInstitution', loadChildren: () => import('./request-details-for-adding-institution/request-details-for-adding-institution.module').then(m => m.RequestDetailsForAddingInstitutionModule) },


]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReportsRoutingModule { }
