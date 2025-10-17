import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchbyAisheComponent } from './searchby-aishe.component';

const routes: Routes = [{ path: '', component: SearchbyAisheComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SearchbyAisheRoutingModule { }
