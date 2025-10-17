import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-view-user-dialog',
  templateUrl: './view-user-dialog.component.html',
  styleUrls: ['./view-user-dialog.component.scss']
})
export class ViewUserDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ViewUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

}
