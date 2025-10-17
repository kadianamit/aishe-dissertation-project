import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormManagementRoutingModule } from './form-management-routing.module';
import { FormManagementComponent } from './form-management.component';
import { WebDcfComponent } from './web-dcf/web-dcf.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { SharedModule } from 'src/app/sharedModule/shared/shared.module';
import { DocumentComponent } from './document/document.component';
import { SpecialPermissionComponent } from './special-permission/special-permission.component';
import { UnlockWebDCFComponent } from './unlock-web-dcf/unlock-web-dcf.component';


@NgModule({
  declarations: [
    FormManagementComponent,
    WebDcfComponent,
    DocumentComponent,
    SpecialPermissionComponent,
    UnlockWebDCFComponent,
  ],
  imports: [
    CommonModule,
    FormManagementRoutingModule,MatSelectFilterModule,
    AngularMaterialModule,SharedModule,FormsModule,ReactiveFormsModule
  ]
})
export class FormManagementModule { }
