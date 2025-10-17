import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddComponent } from './add/add.component';
import { ApplicationComponent } from './application/application.component';
import { CisoComponent } from './ciso.component';
import { ViewComponent } from './view/view.component';
import { AgencyListComponent } from './agency-list/agency-list.component';
import { NotfilledcisoComponent } from './notfilledciso/notfilledciso.component';
import { NetworkDeviceComponent } from './network-device/network-device.component';
import { SwitchComponent } from './switch/switch.component';
import { ServerComponent } from './server/server.component';
import { SystemComponent } from './system/system.component';
import { CisoUploadFileComponent } from './ciso-upload-file/ciso-upload-file.component';

const routes: Routes = [{
  path: '', component: CisoComponent,
  children: [
    { path: 'add', component: AddComponent },
    { path: 'view', component: ViewComponent },
    { path: 'application', component: ApplicationComponent },
    { path: 'agencyList', component: AgencyListComponent },
    { path: 'notFilledCISO', component: NotfilledcisoComponent },
    { path: 'switchDetails', component: SwitchComponent },
    { path: 'serverDetails', component: ServerComponent },
    { path: 'systemDetails', component: SystemComponent },
    { path: 'uploadFile', component: CisoUploadFileComponent },
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CisoRoutingModule { }
