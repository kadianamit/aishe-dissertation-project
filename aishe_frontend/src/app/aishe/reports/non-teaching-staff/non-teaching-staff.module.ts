import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NonTeachingStaffRoutingModule } from './non-teaching-staff-routing.module';
import { NonTeachingStaffComponent } from './non-teaching-staff.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { StateWiseModule } from './state-wise/state-wise.module';
import { DistrictWiseModule } from './district-wise/district-wise.module';
import { InstitutionWiseModule } from './institution-wise/institution-wise.module';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    NonTeachingStaffComponent
  ],
  imports: [
    CommonModule,
    NonTeachingStaffRoutingModule,
    MatCardModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatMenuModule,
    AngularMaterialModule,
    ReactiveFormsModule,
     MatExpansionModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatListModule,
    StateWiseModule,
    DistrictWiseModule,
     InstitutionWiseModule,
     NgxPaginationModule
  ]
})
export class NonTeachingStaffModule { }
