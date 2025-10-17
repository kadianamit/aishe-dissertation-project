import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { routes } from 'src/app/shared/shared.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-success-message',
  templateUrl: './success-message.component.html',
  styleUrls: ['./success-message.component.scss']
})
export class SuccessMessageComponent implements OnInit {
  public routers: typeof routes = routes;
  confirmButtonText = "YES";
  cancelButtonText = "NO"
  constructor(public dialogRef: MatDialogRef<SuccessMessageComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any,public router:Router) {
    console.log('element', this.element)  
     }

  ngOnInit(): void {
  }
  save() {
    this.dialogRef.close(true)
  }
  close(){
        this.dialogRef.close(true)
        window.location.href=environment.aisheHome
  }
  dataUser(){
    this.dialogRef.close(true)
    this.router.navigate([this.routers.DATAUSERLOGIN])
  }
  ntaClosed(){
    this.dialogRef.close(true)
  }
  closeResearch(){
    this.dialogRef.close(true)
  }
}
