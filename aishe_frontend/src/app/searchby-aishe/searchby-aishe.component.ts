import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UniversalSearchDetailsDialogComponent } from '../dialog/universal-search-details-dialog/universal-search-details-dialog.component';
import { AuthService } from '../service/auth.service';
import { SharedService } from '../shared/shared.service';

@Component({
  selector: 'app-searchby-aishe',
  templateUrl: './searchby-aishe.component.html',
  styleUrls: ['./searchby-aishe.component.scss'],
})
export class SearchbyAisheComponent implements OnInit {
  aisheCode: string = '';
  surveyYear: string = '';
  errorMessage: string = '';
  name: string = '';
  currentStatus: string = '';
  lastUpdated: any = '';
  userId: string = '';
  latestDcfUploadSurveyyear: string = '';
  isDcfApplicable: string = '';
  dataList: any = [];
  lengthMessage: string = '';
  lastUpdatedDcfDetails: any;
  checkInstiteType:string = '';
  constructor(public authService: AuthService, private dialog: MatDialog, public sharedService:SharedService) {}

  ngOnInit(): void {
    console.log('');
  }
  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }else{
       this.errorMessage = '';
    }
  }
 
  getDetails() {
    this.dataList=[];
    let value = this.aisheCode.split(/[\W\d]+/).join('');
    this.checkInstiteType=value;
    if (
      value.toUpperCase() !== 'C' &&
      value.toUpperCase() !== 'S' &&
      value.toUpperCase() !== 'U'
    ) {
      this.errorMessage = 'Please enter valid AISHE Code';
      return;
    } else {
      this.errorMessage = '';
    }
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
      surveyYear: '2022',
    };
    this.authService.getSearchbyAISHE(payload).subscribe(
      (res: any) => {
        this.dataList = res.serviceResponse.data;
        console.log(this.dataList);
      },
      (err) => {}
    );
  }

  openDialog(value: any, array: any) {
    const dialogRef = this.dialog.open(UniversalSearchDetailsDialogComponent, {
      width: '50%',
      data: {
        array: array,
        detail: this.lastUpdatedDcfDetails,
        type: value,
      },
    });
    return;
  }
  getLastUploadedDCFDetail(aisheCode: any) {
    let payload: any = { aisheCode: aisheCode };
    this.authService.getDetailOfLastUpdateDCF(payload).subscribe((res: any) => {
      this.lastUpdatedDcfDetails = res;
    });
  }
}
