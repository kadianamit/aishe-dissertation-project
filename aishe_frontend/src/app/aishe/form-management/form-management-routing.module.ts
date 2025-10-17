import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DocumentComponent } from './document/document.component';
import { FormManagementComponent } from './form-management.component';
import { SpecialPermissionComponent } from './special-permission/special-permission.component';
import { WebDcfComponent } from './web-dcf/web-dcf.component';
import { UnlockWebDCFComponent } from './unlock-web-dcf/unlock-web-dcf.component';
import { DeactivatedGuard } from 'src/gaurd/deactivated.guard';

const routes: Routes = [{
  path: '', component: FormManagementComponent,
  children: [
    { path: 'fill_web_dcf', component: WebDcfComponent },
    { path: 'document', component: DocumentComponent },
    { path: 'specialPermission', component: SpecialPermissionComponent },
    {path: 'unlockWebDcf', component: UnlockWebDCFComponent}
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FormManagementRoutingModule { }
