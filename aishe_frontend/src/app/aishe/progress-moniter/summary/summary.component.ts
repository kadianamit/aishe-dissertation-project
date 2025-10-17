import { Component, OnInit } from '@angular/core';
 import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { AuthService } from 'src/app/service/auth.service';
 import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/service/format-datepicker';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.scss'],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ],
})
export class SummaryComponent implements OnInit {
  totalBasicFilledU: any;
  totalFormUploadedU: any;
  totalFormUploadedS: any;
  totalBasicFilledS: any;
  totalFormUploadedC: any
  totalBasicFilledC: any
  minDate: any;
  maxDate: any
  fromDate: any;
  toDate: any;
  currentSurveyYear: any;
  surveyYearList: any[] = [];
  surveyYear: any;
  constructor(public authService: AuthService, public sharedService: SharedService) {
   
    this.maxDate = new Date();
  }

  ngOnInit(): void {
    this.getSurveyYear();
    this.reload();
  }
  getSummaryMonitering(surveyYear:any) {
    var fromDate = null
    var toDate = null
    if (this.fromDate !== null) {
      let day: string = this.fromDate.getDate().toString();
      day = +day < 10 ? '0' + day : day;
      let month: string = (this.fromDate.getMonth() + 1).toString();
      month = +month < 10 ? '0' + month : month;
      let year = this.fromDate.getFullYear();
      fromDate = `${day}/${month}/${year}`;
    }

    if (this.toDate !== null) {
      let day: string = this.toDate.getDate().toString();
      day = +day < 10 ? '0' + day : day;
      let month: string = (this.toDate.getMonth() + 1).toString();
      month = +month < 10 ? '0' + month : month;
      let year = this.toDate.getFullYear();
      toDate = `${day}/${month}/${year}`;
    }
    if (fromDate !== null && toDate === null) {
      this.sharedService.showValidationMessage('Please select To date !!!!');
      return;
    }
    let payload = {
      surveyYear: surveyYear.substring(0, 4),
    }
    this.authService.getSummary(payload).subscribe(res => {
      if (res.data.university) {
        this.totalBasicFilledU = res.data.university.totalBasicFilled,
          this.totalFormUploadedU = res.data.university.totalFormUploaded
      } if (res.data.college) {
        this.totalBasicFilledC = res.data.college.totalBasicFilled,
          this.totalFormUploadedC = res.data.college.totalFormUploaded
      } if (res.data.standalone) {
        this.totalBasicFilledS = res.data.standalone.totalBasicFilled,
          this.totalFormUploadedS = res.data.standalone.totalFormUploaded
      }
    }, err => {

    })
  }
  getSurveyYear(){
    this.authService.getSurveyYear().subscribe(res => {
      this.surveyYearList = res
    },err=>{
  
    })
  }
  // getSurveyYear() {
  //   this.authService.getSurveyYearList().subscribe(res => {

  //     let surveyYear = res
  //     surveyYear.forEach((element: any) => {
  //       if (element <= "2021-2022") {
  //         var splitSurvey = element.substring(0, 5);
  //         this.currentSurveyYear = element.substring(0, 4)
  //         var a = element.substring(7, 9);
  //         // this.surveyYearList.unshift([splitSurvey + a])
  //       }
  //     });
  //     for (let index = 2011; index < parseInt(this.currentSurveyYear) + 1; index++) {
  //       let i = index + 1;
  //       let a = index + '-' + i;
  //       let b = a.toString().substring(7, 9)
  //       this.surveyYearList.push(index + '-' + b)
  //     }
  //   }, err => {

  //   })
  // }
  handleToDate(event: any) {
    const m = event.value;
    if (m) {
      var year = m.getFullYear();
      var month = m.getMonth();
      var day = m.getDate();
      this.minDate = new Date(year, month, day);
    }
  }
  reload() {
    this.fromDate = null;
    this.toDate = null;
    this.surveyYear = this.sharedService.currentSurveyfiscal;
    this.getSummaryMonitering(this.surveyYear);
  }

}
