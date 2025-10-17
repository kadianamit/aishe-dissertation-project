import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DailogmessageComponent } from '../dailogmessage/dailogmessage.component';
import { MatDialogActions, MatDialogClose, MatDialogContent, MatDialogModule, MatDialogTitle } from '@angular/material/dialog';



@NgModule({
  declarations: [
    DailogmessageComponent
  ],
  imports: [
    CommonModule,
    MatDialogModule,

  ],exports:[
    MatDialogClose,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    DailogmessageComponent
  ]
})
export class MessageModule { }
