import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OutTurnComponent } from './out-turn.component';

const routes: Routes = [{ path: '', component: OutTurnComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OutTurnRoutingModule { }
