import {
  AfterContentChecked,
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  Inject,
  OnDestroy,
  OnInit,
} from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-universal-search-details-dialog',
  templateUrl: './universal-search-details-dialog.component.html',
  styleUrls: ['./universal-search-details-dialog.component.scss'],
})
export class UniversalSearchDetailsDialogComponent implements OnInit {
  allData: any;
  heading: any;
  cancelButtonText = 'Close';
  lastUpdatedDcfDetails: any = [];
  aisheCode: any;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<ConfirmDialogComponent>,
    public authService: AuthService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    // if (this.data.surveyYears) {
      this.lastUpdatedDcfDetails = this.data?.type.map((item: any) => {
        return {
          ...item,
          aisheCode:this?.data?.aisheCode,
          updatedRecord:item,
          surveyYear:item.surveyYear,
          uploaderId:item.uploaderId,
          uploadDateTime:item.uploadDateTime
        };
      });
     
    // } else {
    //   this.allData = this.data.array[0];
    //   this.aisheCode = this.data.array['0'].aisheCode;
    //   this.heading = this.data.type;
    //   if (
    //     this.allData.aisheCode &&
    //     this.heading == 'User Last Uploaded DCF Details'
    //   ) {
    //     this.lastUpdatedDcfDetails = this.data.detail;
    //   }
    // }
  }
}
