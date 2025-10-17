import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RequestAddInstituteComponent } from './component/request-add-institute/request-add-institute.component';
// import { ErrorComponent } from './error/error.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ForgotUserComponent } from './forgot-user/forgot-user.component';
import { PasswordComponent } from './password/password.component';
// import { ServiceDownComponent } from './service-down/service-down.component';
import { UnderConstructionComponent } from './under-construction/under-construction.component';
import { AboutMoeComponent } from './component/about-moe/about-moe.component';
import { ContactusComponent } from './component/contactus/contactus.component';
import { ScholarshipSchemeComponent } from './scholarship-scheme/scholarship-scheme.component';
import { FormComponent } from './form/form.component';
import { TrackRequestComponent } from './track-request/track-request.component';
import { DeactivatedGuard } from 'src/gaurd/deactivated.guard';


const routes: Routes = [
 // { path: '**', component: ServiceDownComponent },
  { path: '', redirectTo: 'institutionallogin', pathMatch: 'full' },
  //  { path: '', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
  // { path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
  // { path: 'app', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
  { path: 'contact', component: ContactusComponent },
  { path: 'aboutmoe', component: AboutMoeComponent },
  { path: 'requestAddInstitute', component: RequestAddInstituteComponent },
  { path: 'form/:id/:encrypted', component: FormComponent },
  { path: 'scholarshipScheme', component: ScholarshipSchemeComponent },
  { path: 'institutionallogin', loadChildren: () => import('./institutional-login/institutional-login.module').then(m => m.InstitutionalLoginModule) },
  { path: 'notworking', component: UnderConstructionComponent },
  // { path: 'error', component: ServiceDownComponent },
  { path: 'aishe', loadChildren: () => import('./aishe/aishe.module').then(m => m.AisheModule) },
  // { path: 'error', component: ErrorComponent },
  { path: 'institutionDirectory', loadChildren: () => import('./institution-directory/institution-directory.module').then(m => m.InstitutionDirectoryModule) },
  { path: 'forgot_password', component: ForgotPasswordComponent },
  { path: 'password/:userId', component: PasswordComponent },
  { path: 'forgot_user', component: ForgotUserComponent },
  { path: 'userRegistration/:user', loadChildren: () => import('./user-registration/user-registration.module').then(m => m.UserRegistrationModule) },
  { path: 'details', loadChildren: () => import('./details/details.module').then(m => m.DetailsModule) },
  { path: 'datauserlogin', loadChildren: () => import('./data-user-login/data-user-login.module').then(m => m.DataUserLoginModule) },
  { path: 'track-request', component: TrackRequestComponent },
 { path: 'searchbyAishe', loadChildren: () => import('./searchby-aishe/searchby-aishe.module').then(m => m.SearchbyAisheModule) }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true ,onSameUrlNavigation: 'reload'}),],
  exports: [RouterModule]
})
export class AppRoutingModule { }
