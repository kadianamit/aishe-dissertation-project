import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NumberOfInstitutionsComponent } from './number-of-institutions.component';

const routes: Routes = [{ path: '', component: NumberOfInstitutionsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NumberOfInstitutionsRoutingModule { }
