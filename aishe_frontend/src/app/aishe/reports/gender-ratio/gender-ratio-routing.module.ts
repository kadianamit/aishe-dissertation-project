import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GenderRatioComponent } from './gender-ratio.component';

const routes: Routes = [{ path: '', component: GenderRatioComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GenderRatioRoutingModule { }
