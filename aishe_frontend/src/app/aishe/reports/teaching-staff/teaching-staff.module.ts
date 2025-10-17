import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeachingStaffRoutingModule } from './teaching-staff-routing.module';
import { TeachingStaffComponent } from './teaching-staff.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { StateWiseModule } from './state-wise/state-wise.module';
import { DistrictWiseModule } from './district-wise/district-wise.module';
import { InstitutionWiseModule } from './institution-wise/institution-wise.module';


@NgModule({
    declarations: [
        TeachingStaffComponent
    ],
    imports: [
        CommonModule,
        AngularMaterialModule,
        ReactiveFormsModule,
        TeachingStaffRoutingModule,
        StateWiseModule,
        DistrictWiseModule,
        InstitutionWiseModule,
        
    ]
})

export class TeachingStaffModule { }