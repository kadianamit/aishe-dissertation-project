import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmailSmsManagementComponent } from './email-sms-management.component';
import { DeactivatedGuard } from 'src/gaurd/deactivated.guard';

const routes: Routes = [{ path: '', component: EmailSmsManagementComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmailSmsManagementRoutingModule { }
