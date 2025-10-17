import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstitutionDirectoryComponent } from './institution-directory.component';
import { UniversityDetailComponent } from './university-detail/university-detail.component';

const routes: Routes = [{ path: '', component: InstitutionDirectoryComponent },

  // {path:'universityDetails/:type',component:UniversityDetailComponent},

  {path:'universityDetails/:type/:id',component:UniversityDetailComponent},
  {path:'collegeDetails/:type/:id',component:UniversityDetailComponent},
  {path:'standaloneDetails/:type/:id',component:UniversityDetailComponent},

]
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstitutionDirectoryRoutingModule { }
