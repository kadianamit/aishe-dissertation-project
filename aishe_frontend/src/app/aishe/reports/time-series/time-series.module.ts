import { CUSTOM_ELEMENTS_SCHEMA,NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
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
import { TimeSeriesRoutingModule } from './time-series-routing.module';
import { TimeSeriesComponent } from './time-series.component';
import { NonTeachingStaffModule } from "./non-teaching-staff/non-teaching-staff.module";
import { StudentEnrolmentModule } from "./student-enrolment/student-enrolment.module";
import { TeachingStaffModule } from "./teaching-staff/teaching-staff.module";
import { NgxPaginationModule } from 'ngx-pagination';



@NgModule({
    declarations: [
        TimeSeriesComponent
    ],
    imports: [
        CommonModule,
        TimeSeriesRoutingModule,
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
        NonTeachingStaffModule,
        StudentEnrolmentModule,
        TeachingStaffModule,
        NgxPaginationModule,
        
    ],
    schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ],
})
export class TimeSeriesModule { }
