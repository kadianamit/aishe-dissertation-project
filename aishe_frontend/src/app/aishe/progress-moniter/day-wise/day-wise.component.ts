import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { AuthService } from 'src/app/service/auth.service';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/service/format-datepicker';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-day-wise',
  templateUrl: './day-wise.component.html',
  styleUrls: ['./day-wise.component.scss'],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ],
})
export class DayWiseComponent implements OnInit {
  totalFormUploaded: any[] = [];
  surveyYear: any;
  minDate: any;
  maxDate: any
  fromDate: any;
  toDate: any;
  currentSurveyYear: any;
  surveyYearList: any[] = [];
  month: any = 0
  dayWiseList: any[] = [];
  startYear: any
  endYear: any
  year: any;
  yearList: any[] = []
  collegeTotal: any=0;
  standaloneTotal: any=0;
  universityTotal: any=0;
  totalTotal: any=0;
  constructor(public authService: AuthService, public sharedService: SharedService) {
    this.maxDate = new Date();
    this.surveyYear = this.sharedService.currentSurveyfiscal;
    this.year = this.sharedService.currentSurveyYear;
  }

  ngOnInit(): void {
    this.getYear(this.surveyYear)
    this.getSurveyYear()
  }
  getSurveyYear(){
    this.authService.getSurveyYear().subscribe(res => {
      this.surveyYearList = res
    },err=>{
  
    })
  }
  getSummaryMonitering(value: any) {
    var date = new Date(), y = date.getFullYear(), m = date.getMonth();
    this.fromDate = new Date(this.year, this.month, 1);
    this.toDate = new Date(this.year, this.month + 1, 0);
    if (this.fromDate !== null) {
      let day: string = this.fromDate.getDate().toString();
      day = +day < 10 ? '0' + day : day;
      let month: string = (this.fromDate.getMonth() + 1).toString();
      month = +month < 10 ? '0' + month : month;
      let year = this.fromDate.getFullYear();
      this.fromDate = `${day}/${month}/${year}`;
    }

    if (this.toDate !== null) {
      let day: string = this.toDate.getDate().toString();
      day = +day < 10 ? '0' + day : day;
      let month: string = (this.toDate.getMonth() + 1).toString();
      month = +month < 10 ? '0' + month : month;
      let year = this.toDate.getFullYear();
      this.toDate = `${day}/${month}/${year}`;
    }

    let payload = {
      surveyYear: this.surveyYear.substring(0, 4),
      fromDate: this.fromDate,
      toDate: this.toDate
    }
    this.authService.getDayWiseMonitering(payload).subscribe(res => {
      this.totalFormUploaded = res.data.dayWise;
      this.totalFormUploaded.forEach(element => {
        if (element.college === null) {
          element.college = 0
        }
        if (element.standalone === null) {
          element.standalone = 0
        }
        if (element.university === null) {
          element.university = 0
        }
        element['total'] = element.college + element.standalone + element.university
      });
      this.collegeTotal = this.totalFormUploaded.reduce((sum, item) => sum + item.college, 0);
      this.standaloneTotal = this.totalFormUploaded.reduce((sum, item) => sum + item.standalone, 0);
      this.universityTotal = this.totalFormUploaded.reduce((sum, item) => sum + item.university, 0);
      this.totalTotal = this.totalFormUploaded.reduce((sum, item) => sum + item.total, 0);
      // let array = [res.data.college.dayWise.length, res.data.standalone.dayWise.length, res.data.university.dayWise.length]
      // const max = array.reduce((a, b) => Math.max(a, b));
      //  for (let i = 0; i < max; i++) {
      //   for (let j = 0; j < res.data.college.dayWise.length; j++) {
      //     this.totalFormUploaded[j]['collegeCount']=res.data.college.dayWise[j].totalCount
      //     this.totalFormUploaded[j]['date']=res.data.college.dayWise[j].totalCount
      //   }
      //   for (let k = 0; k < res.data.standalone.dayWise.length; k++) {
      //       this.totalFormUploaded[k]['standaloneCount']= res.data.standalone.dayWise[k].totalCount,
      //       this.totalFormUploaded[k]['date']= res.data.standalone.dayWise[k].date
      //   }
      //   for (let l = 0; l < res.data.university.dayWise.length; l++) {
      //     this.totalFormUploaded[l]['universityCount']= res.data.university.dayWise[l].totalCount,
      //       this.totalFormUploaded[l]['date']= res.data.university.dayWise[l].date
      //   }
      //  }
      // res.data.college.dayWise.forEach((obj: any) => {
      //   let countC = 0
      //   countC = obj.totalCount + countC
      //   this.totalFormUploaded.push({
      //     collegeCount: obj.totalCount,
      //     date: obj.date,
      //     total: countC
      //   })
      // })
      // let countS = 0
      // res.data.standalone.dayWise.forEach((obj: any) => {
      //   countS = obj.totalCount + countS
      //   this.totalFormUploaded.push({
      //     standaloneCount: obj.totalCount,
      //     date: obj.date,
      //     total: countS
      //   })
      // })
      // let countU = 0
      // res.data.university.dayWise.forEach((obj: any) => {
      //   countU = obj.totalCount + countU
      //   this.totalFormUploaded.push({
      //     universityCount: obj.totalCount,
      //     date: obj.date,
      //     total: countU
      //   })
      // })
    }, err => {

    })
  }
  getYear(value: any) {
    this.yearList = [];
    this.authService.getStartEndYear(value.substring(0, 4)).subscribe(res => {
      this.startYear = res.data.startYear;
      this.endYear = res.data.endYear;
      if(this.startYear !== this.endYear){
        this.yearList.push(this.startYear, this.endYear)
      }else{
      this.yearList.push(this.startYear)

      }
      this.getSummaryMonitering(this.month)

    }, err => {

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

}
