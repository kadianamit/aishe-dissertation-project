import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'cfs-session-expire-dialog',
  templateUrl: './session-expire-dialog.component.html',
  styleUrls: ['./session-expire-dialog.component.scss']
})
export class SessionExpireDialogComponent implements OnInit {
  confirmButtonText = "Login";
  constructor(public dialogRef: MatDialogRef<SessionExpireDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any, public router: Router,public localService:LocalserviceService) { }

  ngOnInit(): void {
  }
  login() {
    this.localService.clearData();
    this.dialogRef.close();
    window.location.href = environment.production?environment.aisheMain:'http://localhost:4200/';
}
}
