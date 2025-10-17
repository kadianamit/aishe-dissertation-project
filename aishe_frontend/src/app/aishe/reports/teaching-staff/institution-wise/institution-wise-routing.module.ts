import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstitutionWiseComponent } from './institution-wise.component';

const routes: Routes = [{ path: '', component: InstitutionWiseComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstitutionWiseRoutingModule { }
