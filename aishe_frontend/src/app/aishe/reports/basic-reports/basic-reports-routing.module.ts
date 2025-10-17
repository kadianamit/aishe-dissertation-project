import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BasicReportsComponent } from './basic-reports.component';

const routes: Routes = [{ path: '', component: BasicReportsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BasicReportsRoutingModule { }
