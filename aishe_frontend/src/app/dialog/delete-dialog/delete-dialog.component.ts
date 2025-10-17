import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.scss']
})
export class DeleteDialogComponent implements OnInit {
  message: string = "Are you sure you want to delete this record?";
  confirmButtonText = "Yes";
  cancelButtonText = "Cancel";
  remarks: any;
  constructor(
    public dialogRef: MatDialogRef<DeleteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      if(data){
        this.message = data.message || this.message;
        if (data.buttonText) {
          this.confirmButtonText = data.buttonText.ok || this.confirmButtonText;
          this.cancelButtonText = data.buttonText.cancel || this.cancelButtonText;
        }
          }
  }
  ngOnInit(): void {
  }
  // onConfirmClick(): void {
  //  if(this.remarks && this.remarks.trim().length !==0)this.dialogRef.close(this.remarks.trim());
  // }
  onConfirmClick(){
    this.dialogRef.close(true)
  }
}
