import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-alert-dialog',
  templateUrl: './alert-dialog.component.html',
  styleUrls: ['./alert-dialog.component.scss']
})
export class AlertDialogComponent implements OnInit {
  confirmButtonText = "Yes"
  cancelButtonText = "Close"
  userId: string | null;
  remark: any
  message:string=''
  constructor(public dialogRef: MatDialogRef<AlertDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any, public authService: AuthService, public sharedService: SharedService,public localService: LocalserviceService) {
    this.userId = this.localService.getData('userId')
    this.message= `This Survey (${sharedService.currentSurveyfiscal}) has been closed.`
  }

  ngOnInit(): void {
  }

  closed() {
    this.dialogRef.close(true)
  }
}
