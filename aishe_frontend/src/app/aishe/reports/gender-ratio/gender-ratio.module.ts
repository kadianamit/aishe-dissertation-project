import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GenderRatioRoutingModule } from './gender-ratio-routing.module';
import { GenderRatioComponent } from './gender-ratio.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { Report44Component } from './report44/report44.component';
import { Report45Component } from './report45/report45.component';
import { Report46Component } from './report46/report46.component';
import { Report47aComponent } from './report47a/report47a.component';
import { Report47bComponent } from './report47b/report47b.component';
import { Report47cComponent } from './report47c/report47c.component';
import { Report48Component } from './report48/report48.component';
import { Report49Component } from './report49/report49.component';
import { Report51aComponent } from './report51a/report51a.component';
import { Report51bComponent } from './report51b/report51b.component';
import { Report50Component } from './report50/report50.component';
import { Report51Component } from './report51/report51.component';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    GenderRatioComponent,
    Report44Component,
    Report45Component,
    Report46Component,
    Report47aComponent,
    Report47bComponent,
    Report47cComponent,
    Report48Component,
    Report49Component,
    Report51aComponent,
    Report51bComponent,
    Report50Component,
    Report51Component
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    GenderRatioRoutingModule,
    NgxPaginationModule
  ],
})
export class GenderRatioModule { }
