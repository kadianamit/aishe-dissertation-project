import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { KnowYourInstitutionsComponent } from './know-your-institutions.component';

const routes: Routes = [{ path: '', component: KnowYourInstitutionsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class KnowYourInstitutionsRoutingModule { }
