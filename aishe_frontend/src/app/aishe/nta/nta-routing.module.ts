import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NtaComponent } from './nta.component';

const routes: Routes = [{ path: '', component: NtaComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NtaRoutingModule { }
