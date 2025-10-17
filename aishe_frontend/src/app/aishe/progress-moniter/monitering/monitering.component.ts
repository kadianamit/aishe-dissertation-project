import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { AuthService } from 'src/app/service/auth.service';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/service/format-datepicker';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
@Component({
  selector: 'app-monitering',
  templateUrl: './monitering.component.html',
  styleUrls: ['./monitering.component.scss'],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ],
})
export class MoniteringComponent implements OnInit {
  surveyYear: any;
  states: any;
  fromDate: any;
  toDate: any;
  surveyYearList: any[] = [];
  variables: any[] = [];
  stateList: any[] = [];
  progressMoniterListU: any[] = []
  progressMoniterListC: any[] = []
  progressMoniterListS: any[] = []
  maxDate: any;
  minDate: any;
  aisheId: any;
  instituteType: string = 'ALL';
  currentSurveyYear: any;
  totalFormExU: number = 0;
  totalFormExC: number = 0;
  totalFormExS: number = 0;
  totalFormUploadC: number = 0;
  totalFormUploadS: number = 0;
  totalFormUploadU: number = 0;
  totalFormExPerU: number = 0;
  totalFormExPerC: number = 0;
  totalFormExPerS: number = 0;
  totalFormPendingC: number = 0;
  totalFormPendingS: number = 0;
  totalFormPendingU: number = 0;
  pendingFormU: number = 0;
  pendingFormS: number = 0;
  pendingFormC: number = 0;
  roleId: any
  list: any[] = [];
  downloadSurveyYear:any
  constructor(public authService: AuthService, public sharedService: SharedService, public localService: LocalserviceService) {
    this.roleId = this.localService.getData('roleId');
    // this.reload()
    this.maxDate = new Date();
    this.getSurveyYear();
    this.getState();

  }

  ngOnInit(): void {
  }
  getSurveyYear() {
    this.authService.getSurveyYear().subscribe(res => {
      this.surveyYearList = res
      this.surveyYear = this.sharedService.currentSurveyfiscal;
      this.downloadSurveyYear = this.sharedService.currentSurveyYear
      this.reload()
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
  getState() {
    this.authService.getStatePrivate().subscribe(
      (res) => {
        if (res && res.length) {
          this.variables = [];
          this.variables = res;
          this.stateList = this.variables.slice();
        }
      },
      (err) => { }
    );
  }
  getProgressMoniter() {


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
    var aisheId = null;
    if (!this.surveyYear) {
      this.sharedService.showValidationMessage('Please select survey year');
      return;
    }
    var surveyYear = this.surveyYear.substring(0, 4)

    if (!this.aisheId) {
      aisheId = null
    } else {
      aisheId = this.aisheId
    }

    let payload = {
      surveyYear: surveyYear,
      stateCode: this.states,
      universityId: aisheId,
      institutionType: this.instituteType,
      fromDate: fromDate,
      toDate: toDate,
      roleId: this.roleId

    }
    this.authService.getProgress(payload).subscribe(res => {
      if (res.data && res.data.length) {
        this.downloadSurveyYear = surveyYear 
        this.list = []
        this.list = res.data;
        this.list = this.list.sort((a, b) => a.typeName > b.typeName ? 1 : -1);
        this.reset()
        this.progressMoniterListS = [];
        this.progressMoniterListU = [];
        this.progressMoniterListC = [];

        this.list.forEach((ele: any) => {
          ele['totalFormExpectedPer'] = Math.round(ele.totalFormUploaded / ele.totalFormExpected * 100);
          if (isNaN(ele.totalFormExpectedPer)) {
            ele.totalFormExpectedPer = 0
          }
          if (ele.formType === 'University') {
            this.pendingFormU = ele.totalFormExpected - ele.totalFormUploaded;
            this.totalFormPendingU = this.pendingFormU + this.totalFormPendingU
            this.totalFormExU = ele.totalFormExpected + this.totalFormExU;
            this.totalFormUploadU = ele.totalFormUploaded + this.totalFormUploadU;
            this.totalFormExPerU = Math.round(this.totalFormUploadU / this.totalFormExU * 100);
            if (isNaN(this.totalFormExPerU)) {
              this.totalFormExPerU = 0
            }
            this.progressMoniterListU.push({
              formType: ele.formType,
              typeName: ele.typeName,
              totalFormUploaded: ele.totalFormUploaded,
              totalFormExpected: ele.totalFormExpected,
              totalFormExpectedPer: ele.totalFormExpectedPer,
              surveyYear: payload.surveyYear,
              stateCode: payload.stateCode,
              universityId: payload.universityId,
              institutionType: 'UNIVERSITY',
              fromDate: payload.fromDate,
              toDate: payload.toDate,
              typeId: ele.typeId,
              roleId: this.roleId,
              pendingForm: this.pendingFormU

            })
          } if (ele.formType === 'College') {
            this.pendingFormC = ele.totalFormExpected - ele.totalFormUploaded;
            this.totalFormPendingC = this.pendingFormC + this.totalFormPendingC
            this.totalFormExC = ele.totalFormExpected + this.totalFormExC;
            this.totalFormUploadC = ele.totalFormUploaded + this.totalFormUploadC;
            this.totalFormExPerC = Math.round(this.totalFormUploadC / this.totalFormExC * 100);
            if (isNaN(this.totalFormExPerC)) {
              this.totalFormExPerC = 0
            }
            this.progressMoniterListC.push({
              formType: ele.formType,
              typeName: ele.typeName,
              totalFormUploaded: ele.totalFormUploaded,
              totalFormExpected: ele.totalFormExpected,
              totalFormExpectedPer: ele.totalFormExpectedPer,
              surveyYear: payload.surveyYear,
              stateCode: payload.stateCode,
              universityId: payload.universityId,
              institutionType: 'COLLEGE',
              fromDate: payload.fromDate,
              toDate: payload.toDate,
              typeId: ele.typeId,
              roleId: this.roleId,
              pendingForm: this.pendingFormC
            })
          }
          if (ele.formType === 'Standalone') {
            this.pendingFormS = ele.totalFormExpected - ele.totalFormUploaded;
            this.totalFormPendingS = this.pendingFormS + this.totalFormPendingS;
            this.totalFormExS = ele.totalFormExpected + this.totalFormExS;
            this.totalFormUploadS = ele.totalFormUploaded + this.totalFormUploadS;
            this.totalFormExPerS = Math.round(this.totalFormUploadS / this.totalFormExS * 100);
            if (isNaN(this.totalFormExPerS)) {
              this.totalFormExPerS = 0
            }
            this.progressMoniterListS.push({
              formType: ele.formType,
              typeName: ele.typeName,
              totalFormUploaded: ele.totalFormUploaded,
              totalFormExpected: ele.totalFormExpected,
              totalFormExpectedPer: ele.totalFormExpectedPer,
              surveyYear: payload.surveyYear,
              stateCode: payload.stateCode,
              universityId: payload.universityId,
              institutionType: 'STANDALONE',
              fromDate: payload.fromDate,
              toDate: payload.toDate,
              typeId: ele.typeId,
              roleId: this.roleId,
              pendingForm: this.pendingFormS
            })
          }
        })
      } else {
        this.reset()
        this.progressMoniterListS = [];
        this.progressMoniterListU = [];
        this.progressMoniterListC = []
      }
    })
  }
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
    let data = this.localService.getData('universityId')
    if (data !== null) {
      let x: any = data?.split('-');
      this.aisheId = x[1];
    }
    if (this.roleId === '7') {
      this.instituteType = 'COLLEGE';
      this.states = null;
    }
    else if (this.roleId === '2') {
      this.instituteType = 'UNIVERSITY'
      this.states = null;
    }
    else if (this.roleId === '8' || this.roleId === '9' || this.roleId === '10' || this.roleId === '11') {
      this.instituteType = 'STANDALONE';
      this.states = this.localService.getData('stateCode')
    } else if (this.roleId === '6') {
      this.states = this.localService.getData('stateCode')
    } else {
      this.instituteType = 'ALL';
      this.states = null;
    }
    //
    this.toDate = null;
    this.fromDate = null;
    // this.surveyYear = '2020-21';

    // this.surveyYear = '2022-23';
    this.getProgressMoniter()
  }
 
  reset() {
    this.totalFormExU = 0;
    this.totalFormExC = 0;
    this.totalFormExS = 0;
    this.totalFormUploadC = 0;
    this.totalFormUploadS = 0;
    this.totalFormUploadU = 0;
    this.totalFormExPerU = 0;
    this.totalFormExPerC = 0;
    this.totalFormExPerS = 0;
    this.totalFormPendingC = 0;
    this.totalFormPendingU = 0;
    this.totalFormPendingS = 0
  }
  openPopUp(ele: any, formType: any) {
    let textVal=''
    const survey=this.surveyYearList.find(e=>e.surveyYear === ele.surveyYear)?.surveyYearValue
    const state = this.stateList.find(e=>e.stateCode ===  ele.stateCode)?.name
    if (formType === 'FORM_EXPECTED') {
    textVal = `(Expected Forms in AISHE ${survey})`
      if (ele.totalFormExpected === 0) {
        return;
      }
    } else if (formType === 'FORM_SUBMITTED') {
       textVal = `(Submitted Forms in AISHE ${survey})`
      if (ele.totalFormUploaded === 0) {
        return;
      }
    }else {
       textVal = `(Not Submitted Forms in AISHE ${survey})`
    }

    let payload = {
      surveyYear: ele.surveyYear,
      stateCode: ele.stateCode,
      universityId: ele.universityId,
      institutionType: ele.institutionType,
      fromDate: ele.fromDate,
      toDate: ele.toDate,
      typeId: ele.typeId,
      formType: formType,
      totalFormExpected: ele.totalFormExpected,
      totalFormUploaded: ele.totalFormUploaded,
      searchText: null,
      text:textVal,
      typeName:ele.typeName,
      stateName:state?state:''
    }
    this.authService.getProgressById(payload).subscribe(res => {
      if (res.data && res.data.length) {
        if (formType === 'FORM_EXPECTED') {
          res.data['total'] = ele.totalFormExpected
        } if (formType === 'FORM_SUBMITTED') {
          res.data['total'] = ele.totalFormUploaded
        }

        this.sharedService.progressMoniterDialog(res.data, payload)
      }
    }, err => {

    })
  }
  downloadFinalSubmit(type: any, action: any) {
    let payload = {
      'action': action,
      'instituteType': type,
      'surveyYear': this.downloadSurveyYear,
      "stateCode": this.roleId === this.sharedService.role['State Nodal Officer'] || this.roleId === this.sharedService.role['University'] ? this.localService.getData('stateCode') : this.states?this.states:'ALL',
    }
    let fileName = action + '_' + (type == 'U' ? 'University' : type == 'S' ? 'Standalone' : 'College');
    this.authService.getWebdcfLogReports(payload).subscribe((res: any) => {
      console.log(res)
      const blob = new Blob([res], { type: 'application/vnd.ms-excel' });
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      link.download = fileName + '_' + 'List.xlsx'; // Set the desired file name
      link.click();
      window.URL.revokeObjectURL(link.href);
    },
      (error) => {
        console.error('Error downloading the file', error);
      }
    );

  }

}



