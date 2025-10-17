import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UnderConstructionComponent } from '../under-construction/under-construction.component';
import { InstitutionalLoginComponent } from './institutional-login.component';

const routes: Routes = [
  { path: '', component:InstitutionalLoginComponent },
  { path: 'notworking', component:UnderConstructionComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstitutionalLoginRoutingModule { }
