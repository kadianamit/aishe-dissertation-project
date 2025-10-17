import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RemunerationComponent } from './remuneration.component';

const routes: Routes = [{ path: '', component: RemunerationComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RemunerationRoutingModule { }
