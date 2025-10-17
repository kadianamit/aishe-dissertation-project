import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForeignComponent } from './foreign.component';
import { ApproveForeignInstitutionComponent } from './approve-foreign-institution/approve-foreign-institution.component';
import { AddForeignInstitutionComponent } from './add-foreign-institution/add-foreign-institution.component';


const routes: Routes = [
  { path: '', component: ForeignComponent,
  // children: [
  //   { path: 'add', component: AddForeignInstitutionComponent },
  //   { path: 'approve', component: ApproveForeignInstitutionComponent }
  // ]
  },
  
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ForeignRoutingModule { }
