import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DataUserComponent } from './data-user.component';

const routes: Routes = [{ path: '', component: DataUserComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DataUserRoutingModule { }
