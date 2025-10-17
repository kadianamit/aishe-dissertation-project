import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListOfInstitutionComponent } from './list-of-institution.component';

const routes: Routes = [{ path: '', component: ListOfInstitutionComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ListOfInstitutionRoutingModule { }
