import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CisoRoutingModule } from './ciso-routing.module';
import { CisoComponent } from './ciso.component';
import { AddComponent } from './add/add.component';
import { ViewComponent } from './view/view.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { SharedModule } from 'src/app/sharedModule/shared/shared.module';
import { UpdateViewComponent } from './update-view/update-view.component';
import { ApplicationComponent } from './application/application.component';
import { AgencyListComponent } from './agency-list/agency-list.component';
import { NotfilledcisoComponent } from './notfilledciso/notfilledciso.component';
import { NetworkDeviceComponent } from './network-device/network-device.component';
import { SwitchComponent } from './switch/switch.component';
import { ServerComponent } from './server/server.component';
import { SystemComponent } from './system/system.component';
import { CisoUploadFileComponent } from './ciso-upload-file/ciso-upload-file.component';


@NgModule({
  declarations: [
    CisoComponent,
    AddComponent,
    ViewComponent,
    UpdateViewComponent,
    ApplicationComponent,
    AgencyListComponent,
    NotfilledcisoComponent,
    NetworkDeviceComponent,
    SwitchComponent,
    ServerComponent,
    SystemComponent,
    CisoUploadFileComponent
  ],
  imports: [
    CommonModule,
    CisoRoutingModule,
    NgxPaginationModule,
    AngularMaterialModule,SharedModule,FormsModule,ReactiveFormsModule
  ]
})
export class CisoModule { }
