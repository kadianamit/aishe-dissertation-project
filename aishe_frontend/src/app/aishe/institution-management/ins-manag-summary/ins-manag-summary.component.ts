import { data } from 'jquery';
import { DatePipe } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { Subject } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';
import {
  APP_DATE_FORMATS,
  AppDateAdapter,
} from 'src/app/service/format-datepicker';
import { SharedService } from 'src/app/shared/shared.service';
import { RegManagementService } from '../../registration-management/reg-management.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ins-manag-summary',
  templateUrl: './ins-manag-summary.component.html',
  styleUrls: ['./ins-manag-summary.component.scss'],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS },
  ],
})
export class InsManagSummaryComponent implements OnInit, OnDestroy {
  surveyYear: any = '';
  endDate: any = '';
  startDate: any = '';
  startApprovedDate: any;
  endApprovedDate: any;
  tomorrow = new Date();
  maxDate = new Date(this.tomorrow);
  isDatePickerDisabled = true;
  roleId: any;
  minDateToFinish = new Subject<string>();
  max: any;
  obj: any = {
    listType: '',
    responseType: '',
    surveyYear: '',
    type: '',
  };
  listData: any = {
    totalCollegeRequest: 0,
    collegeApprovedRequest: 0,
    collegePendingRequest: 0,
    collegeRejectedRequest: 0,
    totalStandaloneRequest: 0,
    approvedStandaloneRequest: 0,
    pendingStandaloneRequest: 0,
    rejectedStandaloneRequest: 0,
    standaloneApprovedRequestBySectorial: 0,
    standaloneRejectedRequestBySectorial: 0,
    standaloneApprovedRequestBySno: 0,
    standaloneRejectedRequestBySno: 0,
    standaloneApprovedByMoe: 0,
    standaloneRejectedByMoe: 0,

    collegeApprovedByMoe: 0,
    collegeRejectedByMoe: 0,
  };
  surveyYearList: Array<any> = [];
  totalCollegeRequest: number = 0;
  totaleApprovedRequest: number = 0;
  totalPendingRequest: number = 0;
  totalRejectedRequest: number = 0;
  datePipe: DatePipe = new DatePipe('en-GB');
  userDataTable: any;
  userDateTable: any;
  startApprovedDate1: Date | '' = new Date();
  firstDate: Date | '' = new Date();
  lastDate: Date | '' = new Date();
  urlData: any = '';
  parentData: any = null;
  searchStateData: any;
  constructor(
    public sharedService: SharedService,
    public authService: AuthService,
    private regMangementService: RegManagementService,
    public localService: LocalserviceService,
    private route: ActivatedRoute
  ) {
    this.minDateToFinish.subscribe((r) => {
      this.maxDate = new Date(r);
    });
    this.roleId = this.localService.getData('roleId');
  }

  ngOnInit(): void {
    const urlData1 = this.route.snapshot.params;
    this.urlData = urlData1['id'];
    // console.log(this.urlData)
    this.getSurveyYear();
    if (this.localService.getData('searchStateData') && this.urlData == 'Onboarding') {
      this.searchStateData = JSON.parse(this.localService.getData('searchStateData'));
      // console.log(this.localService.getData('searchStateData'))
      // console.log(JSON.parse(this.localService.getData('countData')));
      // console.log(JSON.parse(this.localService.getData('parentData')));
      if (this.searchStateData.status == true) {
        this.startDate = this.searchStateData.obj.startDate;
        this.endDate = this.searchStateData.obj.endDate;
        this.surveyYear = this.convertYearRange(this.searchStateData.obj.surveyYear)
        // this.submit('TOTAL', 'COUNT', 'COLLEGE',this.urlData);
        this.listData = JSON.parse(this.localService.getData('countData'));
        this.parentData = JSON.parse(this.localService.getData('parentData'));
        (this.totalCollegeRequest =
          this.listData.totalCollegeRequest +
          this.listData.totalStandaloneRequest),
          (this.totaleApprovedRequest =
            this.listData.collegeApprovedRequest +
            this.listData.approvedStandaloneRequest),
          (this.totalPendingRequest = this.listData.collegePendingRequest + this.listData.pendingStandaloneRequest),
          (this.totalRejectedRequest =
            this.listData.collegeRejectedRequest +
            this.listData.rejectedStandaloneRequest);
      } else {
        sessionStorage.removeItem('searchStateData');
        sessionStorage.removeItem('countData');
        sessionStorage.removeItem('parentData');
        this.listData.totalCollegeRequest = 0;
        this.listData.collegeApprovedRequest = 0;
        this.listData.collegePendingRequest = 0;
        this.listData.collegeRejectedRequest = 0;
        this.listData.totalStandaloneRequest = 0;
        this.listData.approvedStandaloneRequest = 0;
        this.listData.pendingStandaloneRequest = 0;
        this.listData.rejectedStandaloneRequest = 0;
        this.listData.standaloneApprovedRequestBySectorial = 0;
        this.listData.standaloneRejectedRequestBySectorial = 0;
        this.listData.standaloneApprovedRequestBySno = 0;
        this.listData.standaloneRejectedRequestBySno = 0;
        this.listData.standaloneApprovedByMoe = 0;
        this.listData.standaloneRejectedByMoe = 0;
        this.listData.collegeApprovedByMoe = 0;
        this.listData.collegeRejectedByMoe = 0;
      }
    } else {
      this.listData.standaloneApprovedByMoe = 0
      this.listData.collegeApprovedByMoe = 0
      this.onReset();
    }
  }
  ngOnDestroy(): void {
    // this.searchStateData=JSON.parse(this.localService.getData('searchStateData'));
    // this.searchStateData.status=false;
    // this.localService.saveData('searchStateData',this.searchStateData);
  }
  onDateChange(e: any): void {
    this.minDateToFinish.next(e.value.toString());
  }
  onDateChangeA(e: any) {
    // console.log(new Date(e.value));
  }
  getInstitutionSummary(listType: string, responseType: string, type: string) {
    this.obj.surveyYear = this.surveyYear.substring(0, 4);
    // this.datePipe.transform(this.reportFilterForm.value.datePickerStart, 'dd/MM/YYYY')
    (this.obj.listType = listType),
      (this.obj.responseType = responseType),
      (this.obj.type = type),
      (this.obj.startDate = this.datePipe.transform(
        this.startDate ? this.startDate : this.startApprovedDate,
        'dd/MM/YYYY'
      )),
      (this.obj.endDate = this.datePipe.transform(
        this.endDate ? this.endDate : this.endApprovedDate,
        'dd/MM/YYYY'
      )),
      this.authService
        .getInstitutioSummaryReport(this.obj, this.urlData)
        .subscribe(
          (res) => {
            if (this.obj.responseType === 'LIST') {
              this.getInstituteType(
                type,
                res.data,
                this.obj.type,
                this.obj.listType,
                this.obj.surveyYear,
                this.urlData
              );
              this.localService.saveData('listData', JSON.stringify({ 'type1': type, 'data': res.data, 'type': this.obj.type, 'listType': this.obj.listType, 'surveyYear': this.obj.surveyyear, 'urlData': this.urlData }));
              // this.sharedService.instituteSummaryReport(res.data,this.obj.type,this.obj.listType)
            } else {//COUNT
              this.listData = res.data;
              (this.totalCollegeRequest =
                this.listData.totalCollegeRequest +
                this.listData.totalStandaloneRequest),
                (this.totaleApprovedRequest =
                  this.listData.collegeApprovedRequest +
                  this.listData.approvedStandaloneRequest),
                (this.totalPendingRequest = this.listData.collegePendingRequest + this.listData.pendingStandaloneRequest),
                (this.totalRejectedRequest =
                  this.listData.collegeRejectedRequest +
                  this.listData.rejectedStandaloneRequest);



              if (res.data) {
                this.localService.saveData('countData', JSON.stringify(res.data));
              } else {
                this.localService.saveData('countData', JSON.stringify({ 'test': null }));
              }
              if (this.localService.getData('searchStateData')) {
                this.getInstitutionSummary(this.searchStateData.obj.listType, this.searchStateData.obj.responseType, this.searchStateData.obj.type)
              }
            }
          },
          (err) => {
            this.totalCollegeRequest = 0;
            this.totaleApprovedRequest = 0;
            this.totalPendingRequest = 0;
            this.totalRejectedRequest = 0;
            this.listData = {
              totalCollegeRequest: 0,
              collegeApprovedRequest: 0,
              collegePendingRequest: 0,
              collegeRejectedRequest: 0,
              totalStandaloneRequest: 0,
              approvedStandaloneRequest: 0,
              pendingStandaloneRequest: 0,
              rejectedStandaloneRequest: 0,
              collegeApprovedByMoe: 0,
              standaloneApprovedByMoe: 0
            };
            this.sharedService.showError('No Record Found');
          }
        );
  }

  getSurveyYear() {
    this.authService.getSurveyYear().subscribe(
      (res) => {
        this.surveyYearList = res;
      },
      (err) => { }
    );
  }

  getInstituteType(
    value: string,
    response: any,
    type: any,
    listType: any,
    surveyYear: any,
    urlData: any
  ) {
    if (value === 'COLLEGE') {
      this.obj.startDate = this.startDate;
      this.obj.endDate = this.endDate;
      this.localService.saveData('searchStateData', JSON.stringify({ 'obj': this.obj, 'urlData': this.urlData, 'backurl': '/aishe/Institution-Management/summary/Onboarding', 'status': false }))
      this.authService.getCollegeType()?.subscribe(
        (res) => {
          if (res && res.length) {
            res.unshift({
              id: 'ALL',
              type: 'ALL',
            });
            this.parentData = { 'list': response, 'type': type, 'listType': listType, 'instituteTypeList': res, 'surveyYear': surveyYear, 'urlData': urlData }
            // console.log("college data:",this.parentData)
            this.localService.saveData('parentData', JSON.stringify(this.parentData));
            // this.sharedService.instituteSummaryReport(
            //   response,
            //   type,
            //   listType,
            //   res,
            //   surveyYear,
            //   urlData
            // );

          }
        },
        (err) => { }
      );
    } else {//stanalone
      this.obj.startDate = this.startDate;
      this.obj.endDate = this.endDate;
      this.localService.saveData('searchStateData', JSON.stringify({ 'obj': this.obj, 'urlData': this.urlData, 'backurl': '/aishe/Institution-Management/summary/Onboarding', 'status': false }))
      this.authService.getStandAloneBody()?.subscribe(
        (res) => {
          if (res && res.length) {
            res.unshift({
              id: 'ALL',
              type: 'ALL',
            });
            this.parentData = { 'list': response, 'type': type, 'listType': listType, 'instituteTypeList': res, 'surveyYear': surveyYear, 'urlData': urlData }
            this.localService.saveData('parentData', JSON.stringify(this.parentData));
            // console.log("standalone data:",this.parentData)
            // this.sharedService.instituteSummaryReport(
            //   response,
            //   type,
            //   listType,
            //   res,
            //   surveyYear,
            //   urlData
            // );
          }
        },
        (err) => { }
      );
    }
  }
  array: Array<any> = []
  array1: Array<any> = []
  submit(listType: string, responseType: string, type: string, data: any) {
    if (data === 'ApprovedReject') {
      // let survey: any = []
      // this.surveyYear.map((e: any) => {
      //   survey.push(e.substring(0, 4))
      // })
      this.obj.surveyYear = this.surveyYear.substring(0, 4);
      let payload = {
        surveyYear: this.obj.surveyYear,
        listType: listType,
        responseType: responseType,
        type: type,
        startDate: this.datePipe.transform(
          this.startApprovedDate,
          'dd/MM/YYYY'
        ),
        endDate: this.datePipe.transform(this.endApprovedDate, 'dd/MM/YYYY'),
      };

      this.authService.getSummaryData1(payload).subscribe((res) => {
        if (res.data) {
          // res.data.forEach((ele: any) => {
            // this.array.push({
            //   collegeApprovedByMoe:ele.collegeApprovedByMoe,
            //   collegeRejectedRequest:ele.collegeRejectedRequest,
            //   surveyYear:ele.surveyYear
            // })
            // this.array1.push({
            //   standaloneApprovedByMoe:ele.standaloneApprovedByMoe,
            //   rejectedStandaloneRequest:ele.rejectedStandaloneRequest,
            //   surveyYear:ele.surveyYear
            // })
          // });
          this.listData.totalCollegeRequest = res.data.totalCollegeRequest;
          this.listData.totaleApprovedRequest = res.data.totaleApprovedRequest;
          this.listData.totalPendingRequest = res.data.totalPendingRequest;
          this.listData.totalRejectedRequest = res.data.totalRejectedRequest;
          this.listData.collegeApprovedRequest = res.data.collegeApprovedRequest;
          this.listData.collegeRejectedRequest = res.data.collegeRejectedRequest;
          this.listData.approvedStandaloneRequest =
            res.data.approvedStandaloneRequest;
          this.listData.rejectedStandaloneRequest =
            res.data.rejectedStandaloneRequest;
          this.listData.standaloneApprovedRequestBySectorial =
            res.data.standaloneApprovedRequestBySectorial;
          this.listData.standaloneRejectedRequestBySectorial =
            res.data.standaloneRejectedRequestBySectorial;
          this.listData.standaloneApprovedRequestBySno =
            res.data.standaloneApprovedRequestBySno;
          this.listData.standaloneRejectedRequestBySno =
            res.data.standaloneRejectedRequestBySno;
          this.listData.standaloneApprovedByMoe =
            res.data.standaloneApprovedByMoe;
          this.listData.standaloneRejectedByMoe =
            res.data.standaloneRejectedByMoe;
          this.listData.collegeApprovedRequestByUno =
            res.data.collegeApprovedRequestByUno;
          this.listData.collegeRejectedRequestByUno =
            res.data.collegeRejectedRequestByUno;
          this.listData.collegeApprovedRequestBySno =
            res.data.collegeApprovedRequestBySno;
          this.listData.collegeRejectedRequestBySno =
            res.data.collegeRejectedRequestBySno;
          this.listData.collegeApprovedByMoe = res.data.collegeApprovedByMoe;
          this.listData.collegeRejectedByMoe = res.data.collegeRejectedByMoe;
        }
      });
    }
    if (data === 'Onboarding') {
      if (this.surveyYear) {
        this.getInstitutionSummary(listType, responseType, type);
      } else {
        this.sharedService.showError('Please Select Surveyear');
      }
    }
  }

  onReset() {
    this.isDatePickerDisabled = true;
    this.surveyYear = '';
    this.startDate = '';
    this.startApprovedDate = '';
    this.endApprovedDate = '';
    this.endDate = '';
    this.firstDate = '';
    this.lastDate = '';
    this.totalCollegeRequest = 0;
    this.totaleApprovedRequest = 0;
    this.totalPendingRequest = 0;
    this.totalRejectedRequest = 0;
    this.totalCollegeRequest = 0;
    this.listData = {
      totalCollegeRequest: 0,
      collegeApprovedRequest: 0,
      collegePendingRequest: 0,
      collegeRejectedRequest: 0,
      totalStandaloneRequest: 0,
      approvedStandaloneRequest: 0,
      pendingStandaloneRequest: 0,
      rejectedStandaloneRequest: 0,
      collegeApprovedByMoe: 0,
      standaloneApprovedByMoe: 0
    };
    sessionStorage.removeItem('searchStateData');
    sessionStorage.removeItem('countData');
    sessionStorage.removeItem('parentData');
    this.parentData = null;
  }
  yearChange(year: any) {
    if (this.urlData !== 'ApprovedReject') {
      const yearValue = year?.split('-')[0]
      this.startDate = '';
      this.startApprovedDate = '';
      this.endApprovedDate = '';
      this.endDate = '';
      this.firstDate = '';
      this.lastDate = '';
      this.isDatePickerDisabled = false;
      let payload = { 'surveyYear': yearValue };
      this.regMangementService.getActivityDataParam(payload)?.subscribe((res: any) => {
        if (res.data) {
          this.userDataTable = res.data;
          this.userDateTable = this.userDataTable.filter((data: any) => {
            return data.surveyYear == this.surveyYear.substring(0, 4);
          });
          if (this.userDateTable.length > 0) {
            const firstDateString = this.userDateTable[0]?.startDateString;
            const lastDateString = this.userDateTable[0]?.endDateString;

            // Set firstDate
            if (firstDateString) {
              const firstDateParts = firstDateString?.split(' ')[0].split('/');
              this.firstDate = new Date(
                +firstDateParts[2],
                +firstDateParts[1] - 1,
                +firstDateParts[0]
              );
            }
            if (lastDateString) {
              const lastDateParts = lastDateString?.split(' ')[0].split('/');
              this.lastDate = new Date(
                +lastDateParts[2],
                +lastDateParts[1] - 1,
                +lastDateParts[0]
              );
            }
          } else {
            console.warn('No data found for the selected survey year.');
          }
        } else {
          console.error('No data returned from the API.');
        }
      });
    }
  }
  convertYearRange(year: any): string {
    let yr = Number(year) + 1;
    let str = yr.toString();
    let final = str.slice(-2);
    return year + "-" + final;
  }
}
