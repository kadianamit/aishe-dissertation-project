import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProgrammesDisciplineComponent } from './programmes-discipline.component';


const routes: Routes = [{ path: '', component: ProgrammesDisciplineComponent ,
children:[
  { path: 'discipline', loadChildren: () => import('./discipline/discipline.module').then(m => m.DisciplineModule) },
  { path: 'programme', loadChildren: () => import('./programme/programme.module').then(m => m.ProgrammeModule) }
],

}];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProgrammesDisciplineRoutingModule { }
