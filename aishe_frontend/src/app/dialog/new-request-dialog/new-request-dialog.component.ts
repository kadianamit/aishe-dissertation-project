import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-new-request-dialog',
  templateUrl: './new-request-dialog.component.html',
  styleUrls: ['./new-request-dialog.component.scss']
})
export class NewRequestDialogComponent implements OnInit {
  showMessageS:boolean=false
  confirmButtonText = "YES";
  cancelButtonText = "NO"
  constructor(public dialogRef: MatDialogRef<NewRequestDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any) {
      console.log(this.element)
     }

  ngOnInit(): void {
  }
  standalone(value:boolean){
if(value){
  this.showMessageS= true;
}else{
  this.showMessageS= false;

}
  }
}
