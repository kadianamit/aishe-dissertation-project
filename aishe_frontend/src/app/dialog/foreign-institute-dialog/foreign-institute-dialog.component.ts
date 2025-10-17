import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-foreign-institute-dialog',
  templateUrl: './foreign-institute-dialog.component.html',
  styleUrls: ['./foreign-institute-dialog.component.scss']
})
export class ForeignInstituteDialogComponent implements OnInit {
  message: string = " Are you sure to final submit this information? Once submitted, you can not edit/update this record.";
  confirmButtonText = "Yes";
  cancelButtonText = "Cancel";
  remarks: any;
  constructor(
    public dialogRef: MatDialogRef<ForeignInstituteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}
  ngOnInit(): void {
  
  }
   onConfirmClick(){
    this.dialogRef.close(true)
  }
}