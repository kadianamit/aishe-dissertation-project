import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProgrammesDisciplineRoutingModule } from './programmes-discipline-routing.module';
import { ProgrammesDisciplineComponent } from './programmes-discipline.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { DisciplineModule } from './discipline/discipline.module';
import { ProgrammeModule } from './programme/programme.module';


@NgModule({
  declarations: [
    ProgrammesDisciplineComponent,
  ],
  imports: [
    CommonModule,
    ProgrammesDisciplineRoutingModule,
    DisciplineModule,
    ProgrammeModule,
    AngularMaterialModule,
    MatSelectFilterModule,
    ReactiveFormsModule,
  ],
  exports:[
    DisciplineModule,
    ProgrammeModule,
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],

})
export class ProgrammesDisciplineModule { }
