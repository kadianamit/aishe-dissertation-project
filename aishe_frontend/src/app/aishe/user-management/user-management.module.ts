import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserManagementRoutingModule } from './user-management-routing.module';
import { UserManagementComponent } from './user-management.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectFilterModule } from 'mat-select-filter';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { SharedModule } from 'src/app/sharedModule/shared/shared.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { NewRegistartionComponent } from './new-registartion/new-registartion.component';
import { UserComponent } from './user/user.component';


@NgModule({
  declarations: [
    UserManagementComponent,
    NewRegistartionComponent,
    UserComponent
    
  ],
  imports: [
    CommonModule,
    UserManagementRoutingModule,MatSelectFilterModule,NgxPaginationModule,
    AngularMaterialModule,SharedModule,FormsModule,ReactiveFormsModule
  ],
 
})
export class UserManagementModule { 
}
