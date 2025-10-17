import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstitutionContactDetailsComponent } from './institution-contact-details.component';

const routes: Routes = [{ path: '', component: InstitutionContactDetailsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstitutionContactDetailsRoutingModule { }
