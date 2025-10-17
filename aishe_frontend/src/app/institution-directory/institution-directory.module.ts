import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { InstitutionDirectoryRoutingModule } from './institution-directory-routing.module';
import { InstitutionDirectoryComponent } from './institution-directory.component';
import { UniversityDetailComponent } from './university-detail/university-detail.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DetailsViewComponent } from './details-view/details-view.component';


@NgModule({
  declarations: [
    InstitutionDirectoryComponent,
    UniversityDetailComponent,
    DetailsViewComponent,

  ],
  imports: [
    CommonModule,
    InstitutionDirectoryRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    AngularMaterialModule,
  ],

})
export class InstitutionDirectoryModule { }
