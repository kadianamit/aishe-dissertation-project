import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChangePasswordComponent } from 'src/app/dialog/change-password/change-password.component';
import { DeleteDialogComponent } from 'src/app/dialog/delete-dialog/delete-dialog.component';
import { PasswordComponent } from 'src/app/dialog/password/password.component';
import { ReloginComponent } from 'src/app/dialog/relogin/relogin.component';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SuccessMessageComponent } from 'src/app/dialog/success-message/success-message.component';
import { ProgressDialogComponent } from 'src/app/dialog/progress-dialog/progress-dialog.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { AlertDialogComponent } from 'src/app/dialog/alert-dialog/alert-dialog.component';
import { UserDetailComponent } from 'src/app/dialog/user-detail/user-detail.component';
import { CollegeDetailsComponent } from 'src/app/dialog/college-details/college-details.component';
import { AssignRoleComponent } from 'src/app/dialog/assign-role/assign-role.component';
import { AnnexureComponent } from 'src/app/dialog/annexure/annexure.component';
import { NewRequestDialogComponent } from 'src/app/dialog/new-request-dialog/new-request-dialog.component';
import { RemunerationStatusDialogComponent } from 'src/app/dialog/remuneration-status-dialog/remuneration-status-dialog.component';
import { ForeignInstituteDialogComponent } from 'src/app/dialog/foreign-institute-dialog/foreign-institute-dialog.component';
import { ErrorComponent } from 'src/app/error/error.component';
import { InstitutionSummaryDialogComponent } from 'src/app/dialog/institution-summary-dialog/institution-summary-dialog.component';
import { UniversalSearchDetailsDialogComponent } from 'src/app/dialog/universal-search-details-dialog/universal-search-details-dialog.component';
import { EmailSmsDialogComponent } from 'src/app/dialog/email-sms-dialog/email-sms-dialog.component';
import { ViewProgramComponent } from 'src/app/dialog/view-program/view-program.component';
import { SessionExpireDialogComponent } from 'src/app/dialog/session-expire-dialog/session-expire-dialog.component';
import { ViewUserDialogComponent } from 'src/app/dialog/view-user-dialog/view-user-dialog.component';
import { UploadedUniversityViewDialogComponent } from 'src/app/dialog/uploaded-university-view-dialog/uploaded-university-view-dialog.component';
import { ForgotPasswordComponent } from 'src/app/dialog/forgot-password/forgot-password.component';
import { ResearchAndDevelopmentRemarksDialogComponent } from 'src/app/dialog/research-and-development-remarks-dialog/research-and-development-remarks-dialog.component';
import { InputNumbersOnlyDirective } from 'src/app/shared/input-numbers-only.directive';
import { UpdateUserDialogComponent } from 'src/app/dialog/update-user-dialog/update-user-dialog.component';
import { StateWiseMonitoringDialogComponent } from 'src/app/dialog/state-wise-monitoring-dialog/state-wise-monitoring-dialog.component';
import { ApproveRejectUniversityDialogComponent } from 'src/app/dialog/approve-reject-university-dialog/approve-reject-university-dialog.component';
import { ApproveRejectDatashaingDialogComponent } from 'src/app/dialog/approve-reject-datashaing-dialog/approve-reject-datashaing-dialog.component';
import { UnitcellDialogComponent } from 'src/app/dialog/unitcell-dialog/unitcell-dialog.component';
import { AllownumberanddecimalDirective } from 'src/app/shared/directive/allownumberanddecimal.directive';



@NgModule({
  declarations: [ApproveRejectUniversityDialogComponent,StateWiseMonitoringDialogComponent,ForgotPasswordComponent,ErrorComponent,ForeignInstituteDialogComponent,NewRequestDialogComponent,
    AnnexureComponent,AssignRoleComponent,UserDetailComponent,ChangePasswordComponent,
    DeleteDialogComponent,PasswordComponent,ReloginComponent,SuccessMessageComponent,
    ProgressDialogComponent,AlertDialogComponent,CollegeDetailsComponent,RemunerationStatusDialogComponent,InputNumbersOnlyDirective,UniversalSearchDetailsDialogComponent,EmailSmsDialogComponent,ViewProgramComponent,SessionExpireDialogComponent,
    ViewUserDialogComponent,UploadedUniversityViewDialogComponent, ResearchAndDevelopmentRemarksDialogComponent, UpdateUserDialogComponent,
  ApproveRejectDatashaingDialogComponent,UnitcellDialogComponent,],
  imports: [
    CommonModule,
    AngularMaterialModule,ReactiveFormsModule,FormsModule,NgxPaginationModule
  ],

  entryComponents:[ApproveRejectUniversityDialogComponent,StateWiseMonitoringDialogComponent,ForgotPasswordComponent,ErrorComponent,ForeignInstituteDialogComponent,NewRequestDialogComponent,ChangePasswordComponent,DeleteDialogComponent,
    AnnexureComponent,PasswordComponent,ReloginComponent,SuccessMessageComponent,ProgressDialogComponent,UserDetailComponent,
    UploadedUniversityViewDialogComponent,CollegeDetailsComponent,AssignRoleComponent,
    UniversalSearchDetailsDialogComponent,SessionExpireDialogComponent,ViewUserDialogComponent,ApproveRejectDatashaingDialogComponent],
  exports:[ApproveRejectUniversityDialogComponent,ForgotPasswordComponent,ErrorComponent,ForeignInstituteDialogComponent,NewRequestDialogComponent,ChangePasswordComponent,DeleteDialogComponent,
    PasswordComponent,AnnexureComponent,ReloginComponent,SuccessMessageComponent,ProgressDialogComponent,AlertDialogComponent,
    UserDetailComponent,CollegeDetailsComponent,AssignRoleComponent,RemunerationStatusDialogComponent,InputNumbersOnlyDirective,
    UniversalSearchDetailsDialogComponent,EmailSmsDialogComponent,ViewProgramComponent,SessionExpireDialogComponent,ViewUserDialogComponent,
    UploadedUniversityViewDialogComponent, ResearchAndDevelopmentRemarksDialogComponent, UpdateUserDialogComponent,UnitcellDialogComponent]

})
export class SharedModule { }
