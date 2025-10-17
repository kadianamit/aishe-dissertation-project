import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UniversalsearchComponent } from './universalsearch.component';

const routes: Routes = [{ path: '', component: UniversalsearchComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UniversalsearchRoutingModule { }
