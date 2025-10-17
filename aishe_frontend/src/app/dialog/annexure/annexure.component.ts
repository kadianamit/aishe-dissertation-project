import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-annexure',
  templateUrl: './annexure.component.html',
  styleUrls: ['./annexure.component.scss']
})
export class AnnexureComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<AnnexureComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any,public sharedService:SharedService) {
    
  }
  ngOnInit(): void {
  }

}
