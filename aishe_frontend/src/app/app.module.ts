import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { DatePipe } from '@angular/common';
import { EncryptDecrypt } from './service/encrypt-decrypt';
import { SharedService } from './shared/shared.service';
import { MatSelectFilterModule } from 'mat-select-filter';
import { SharedModule } from './sharedModule/shared/shared.module';
import { RequestAddInstituteComponent } from './component/request-add-institute/request-add-institute.component';
import { Interceptor } from './interceptor/interceptor.interceptor';
import { DataTablesModule } from 'angular-datatables';
import { NgxPaginationModule } from 'ngx-pagination';
import { ReportsModule } from './aishe/reports/reports.module';
import { ConfirmDialogComponent } from './dialog/confirm-dialog/confirm-dialog.component';
import { FormManagementModule } from './aishe/form-management/form-management.module';
import {CookieService } from 'ngx-cookie-service'
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { PasswordComponent } from './password/password.component';
import { ForgotUserComponent } from './forgot-user/forgot-user.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MailerService } from './shared/mailer.service';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule, NgxMatNativeDateModule } from '@angular-material-components/datetime-picker';
 import { NgxMatMomentModule } from '@angular-material-components/moment-adapter';
import { Menu } from './menu';
import { FooterComponent } from './footer/footer.component';
import { AboutMoeComponent } from './component/about-moe/about-moe.component';
import { ContactusComponent } from './component/contactus/contactus.component';
import { ScholarshipSchemeComponent } from './scholarship-scheme/scholarship-scheme.component';
import { FormComponent } from './form/form.component';
import { TrackRequestComponent } from './track-request/track-request.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import {FormatTimePipe} from './top-bar/top-bar.component'
@NgModule({
  declarations: [
    AppComponent,
    RequestAddInstituteComponent,
     ConfirmDialogComponent,
     ForgotPasswordComponent,
     PasswordComponent,
     ForgotUserComponent,
     FooterComponent,
     AboutMoeComponent,
     ContactusComponent,
     ScholarshipSchemeComponent,
     FormComponent,
     TrackRequestComponent,
     TopBarComponent,
     FormatTimePipe
    
  ],
  imports: [
    NgbModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    ReportsModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    MatSelectFilterModule,
    SharedModule,
    DataTablesModule,
    NgxPaginationModule,
    FormManagementModule,
    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    NgxMatNativeDateModule,
    NgxMatMomentModule,
    ToastrModule.forRoot({
      timeOut: 5000, maxOpened: 1,
      positionClass: 'toast-top-right', preventDuplicates: true, progressBar: true,
      progressAnimation: "decreasing"
    }),
  ],
  providers: [
    Menu,
    MailerService,
    SharedService,
    CookieService,
    DatePipe,
    EncryptDecrypt,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {

}
