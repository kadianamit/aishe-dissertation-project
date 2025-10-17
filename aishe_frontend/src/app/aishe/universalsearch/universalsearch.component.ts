import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UniversalSearchDetailsDialogComponent } from 'src/app/dialog/universal-search-details-dialog/universal-search-details-dialog.component';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';

@Component({
  selector: 'app-universalsearch',
  templateUrl: './universalsearch.component.html',
  styleUrls: ['./universalsearch.component.scss']
})
export class UniversalsearchComponent implements OnInit {
  aisheCode: string = ''
  errorMessage: string = ''
  name: string = '';
  currentStatus: string = '';
  lastUpdated: any = '';
  userId: string = '';
  latestDcfUploadSurveyyear: string = '';
  isDcfApplicable: string = '';
  array: Array<any> = [];
  lengthMessage: string = ''
  lastUpdatedDcfDetails: any;
  searchStatus: any;
  constructor(public authService: AuthService, private dialog: MatDialog, public router: Router, public localStore1: EncryptDecrypt) { }

  ngOnInit(): void {
    console.log('')
  }
  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
  // getDetails() {
  //   let value = this.aisheCode.split(/[\W\d]+/).join("")
  //   if (value.toUpperCase() !== 'C' && value.toUpperCase() !== 'S' && value.toUpperCase() !== 'U') {
  //     this.errorMessage = 'Please enter valid AISHE Code';
  //     return;
  //   }else{
  //     this.errorMessage=''
  //   }
  //   let payload = {
  //     aisheCode: this.aisheCode.toUpperCase(),
  //     universalSearch: true
  //   }
  //   this.authService.getUniversalSearch(payload).subscribe(res => {
  //     this.array = res;
  //     if (this.array && this.array.length) {
  //       this.lengthMessage = ''
  //       this.name = res['0'].name;
  //       this.currentStatus = res['0'].currentStatus;
  //       this.lastUpdated = res['0'].lastUpdated;
  //       this.userId = res['0'].userId,
  //       this.latestDcfUploadSurveyyear = res['0'].latestDcfUploadSurveyyear,
  //       this.isDcfApplicable = res['0'].isDcfApplicable;
  //       this.getLastUploadedDCFDetail(res['0'].aisheCode);
  //     } else {
  //       this.lengthMessage = 'No Record'
  //     }

  //   }, err => {

  //   })
  // }
  getDetails() {
    let value = this.aisheCode.split(/[\W\d]+/).join("")
    if (value.toUpperCase() !== 'C' && value.toUpperCase() !== 'S' && value.toUpperCase() !== 'U') {
      this.errorMessage = 'Please enter valid AISHE Code';
      return;
    } else {
      this.errorMessage = ''
    }
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
    }
    this.authService.getUniversalSearch(payload).subscribe(res => {
      this.array = res;
      if (this.array && this.array.length) {

        this.searchStatus = this.array[0].status;
        this.lengthMessage = ''
      } else {
        this.lengthMessage = 'No Record'
      }

    }, err => {

    })
  }
  openSurveyYearDialog(value: any, array: any){

    const dialogRef = this.dialog.open(UniversalSearchDetailsDialogComponent, {
      width: '50%',
      data: {
      aisheCode: value?.aisheCode,
       // detail: this.lastUpdatedDcfDetails,
        type: value?.formUpload,
        surveyYears:true
      },
    });
    return;
  }
  openDialog(value: any, array: any) {
    const dialogRef = this.dialog.open(UniversalSearchDetailsDialogComponent, {
      width: '50%',
      data: {
        array: array,
        detail: this.lastUpdatedDcfDetails,
        type: value
      },
    });
    return;

  }
  getLastUploadedDCFDetail(aisheCode: any) {
    let payload: any = { 'aisheCode': aisheCode }
    this.authService.getDetailOfLastUpdateDCF(payload).subscribe((res: any) => {
      this.lastUpdatedDcfDetails = res;
    })
  }
  nextPage() {
    let value1 = this.aisheCode.split(/[\D\g]+/).join("")
    let value = this.localStore1.getEncryptedValue(value1);

    let value2 = this.aisheCode.split(/[\W\d]+/).join("")

    if (value2.toUpperCase() === 'U' && this.searchStatus === 'Active') {
      this.router.navigate(['/aishe/Institution-Management/searchUniversity', value]);

    } else if (value2.toUpperCase() === 'C' && this.searchStatus === 'Active') {
      this.router.navigate(['/aishe/Institution-Management', value])
    } else if (value2.toUpperCase() === 'S' && this.searchStatus === 'Active') {
      this.router.navigate(['/aishe/Institution-Management/searchStandalone', value])
    } else if (this.searchStatus === 'Merge') {
      let aisheCode = this.aisheCode.toUpperCase()
      this.router.navigate(['/aishe/report/searchMerge', this.localStore1.getEncryptedValue(aisheCode)]);
    } else if (value2.toUpperCase() === 'C' && this.searchStatus == 'Upgraded to University') {
      let aisheCode = this.aisheCode.toUpperCase()
      this.router.navigate(['/aishe/report/searchCollegeToUniversity', this.localStore1.getEncryptedValue(aisheCode)])
    } else if (value2.toUpperCase() === 'S' && this.searchStatus == 'Upgraded to University') {
      let aisheCode = this.aisheCode.toUpperCase()
      this.router.navigate(['/aishe/report/searchStandaloneToUniversity', this.localStore1.getEncryptedValue(aisheCode)])
    } else if (this.searchStatus == 'Upgraded to College') {
      let aisheCode = this.aisheCode.toUpperCase()
      this.router.navigate(['/aishe/report/searchStandaloneToCollege', this.localStore1.getEncryptedValue(aisheCode)])
    } else if (this.searchStatus == 'InActive') {
      let aisheCode = this.aisheCode.toUpperCase()
      this.router.navigate(['/aishe/report/searchInactiveStandalone', this.localStore1.getEncryptedValue(aisheCode)])
    } else if (this.searchStatus == 'De-Affiliated') {
      let aisheCode = this.aisheCode.toUpperCase()
      this.router.navigate(['/aishe/report/searchInactiveDeaffilliate', this.localStore1.getEncryptedValue(aisheCode)])
    }


  }
}
