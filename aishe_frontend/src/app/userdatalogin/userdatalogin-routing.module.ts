import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserdataloginComponent } from './userdatalogin.component';

const routes: Routes = [{ path: '', component: UserdataloginComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserdataloginRoutingModule { }
