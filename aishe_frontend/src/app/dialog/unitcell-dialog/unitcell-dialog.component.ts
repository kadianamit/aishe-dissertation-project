import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-unitcell-dialog',
  templateUrl: './unitcell-dialog.component.html',
  styleUrls: ['./unitcell-dialog.component.scss']
})
export class UnitcellDialogComponent implements OnInit {
  message: string = "Are you sure you want to delete this record?";
  confirmButtonText = "Yes";
  cancelButtonText = "Cancel";
 constructor(
    public dialogRef: MatDialogRef<UnitcellDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    console.log(this.data)
  }
  onConfirmClick(){
    this.dialogRef.close(true)
  }
}
