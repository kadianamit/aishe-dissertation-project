import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WebDcfComponent } from '../form-management/web-dcf/web-dcf.component';
import { BasicComponent } from './basic/basic.component';
import { ProgrameComponent } from './programe/programe.component';
import { WedDemoDcfComponent } from './wed-demo-dcf.component';
import { EnrolmentComponent } from './enrolment/enrolment.component';
import { ForeignEnrolmentComponent } from './foreign-enrolment/foreign-enrolment.component';
import { TeachingDemoDfcComponent } from './teaching-demo-dfc/teaching-demo-dfc.component';



const routes: Routes = [
  { path: '', component: WedDemoDcfComponent,
    children: [
      { path: 'basic', component: BasicComponent },
      { path: 'programe', component: ProgrameComponent },
      { path: 'enrolment', component: EnrolmentComponent },
      { path: 'foreignEnrolment', component: ForeignEnrolmentComponent },
      { path: 'teaching', component: TeachingDemoDfcComponent },
    ]
    },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WedDemoDcfRoutingModule { }
