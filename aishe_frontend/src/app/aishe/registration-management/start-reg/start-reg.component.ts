import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, PatternValidator, Validators } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, ThemePalette } from '@angular/material/core';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/service/format-datepicker';
import { SharedService } from 'src/app/shared/shared.service';
import { RegManagementService } from '../reg-management.service';
import * as moment from 'moment';
import { NGX_MAT_DATE_FORMATS, NgxMatDateFormats } from '@angular-material-components/datetime-picker';
import { NGX_MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular-material-components/moment-adapter';

// If using Moment
const CUSTOM_DATE_FORMATS: NgxMatDateFormats = {
  parse: {
    dateInput: 'DD/MM/YYYY HH:mm A'
  },
  display: {
    dateInput: 'DD/MM/YYYY HH:mm A',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY'
  }
};
@Component({
  selector: 'app-start-reg',
  templateUrl: './start-reg.component.html',
  styleUrls: ['./start-reg.component.scss'],
  providers: [
    // {provide: NgxMatDateAdapter,useClass: AppDateAdapter},
    { provide: NGX_MAT_DATE_FORMATS, useValue: CUSTOM_DATE_FORMATS }
  ]
})

export class StartRegComponent implements OnInit {


  maxDate: Date;
  minDate: any;
  fromDate: any;
  toDate: any;
  datePipe: DatePipe = new DatePipe('en-GB');
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  tableSize: number[] = [10, 20, 30, 40, 50];


  @ViewChild('picker') picker: any;
  public dateControl = new FormControl();
  public dateControlMinMax = new FormControl();
  public disabled = false;
  public showSpinners = true;
  public showSeconds = false;
  public touchUi = false;
  public enableMeridian = true;
  // public minDate: moment.Moment;
  // public maxDate: moment.Moment;
  public stepHour = 1;
  public stepMinute = 1;
  public stepSecond = 1;
  public color: ThemePalette = 'primary';
  // surveyYearList=[{"year":"2024-2025"},{"year":"2023-2024"},{"year":"2022-2023"},{"year":"2021-2022"},{"year":"2020-2021"},{"year":"2019-2020"},{"year":"2018-2019"},{"year":"2017-2018"},{"year":"2016-2017"},{"year":"2015-2016"},{"year":"2014-2015"}];
  formStartReg!: FormGroup;
  isFormInvalid!: boolean;
  userDataTable: any[] = [];
  isEdit: boolean = false;
  @ViewChild('myForm', { static: false }) myForm!: NgForm;
  year: any;
  validationMsg: any;
  id: any;
  minEndDate!: Date;
  date_time!: string;
  constructor(private fb: FormBuilder, private sharedservice: SharedService, private regMangementService: RegManagementService) {
    this.maxDate = new Date();
    this.minDate = new Date();
  }

  ngOnInit(): void {
    this.createForm();
    this.getRegistionData();
  }
  generateYearRegex() {
    const currentYear = this.year;
    const endYear = currentYear + 13;
    this.validationMsg = this.year + '-' + (+this.year + 13);
    const regex = `^(${currentYear}|${Array.from({ length: 13 }, (_, i) => currentYear + i + 1).join('|')})$`;
    // console.log(new RegExp(regex));
    return new RegExp(regex);
  }
  createForm() {
    this.formStartReg = this.fb.group({
      surveyYearValue: ['', [Validators.required]],
      fromDate: ['', [Validators.required]],
      toDate: [''],
      remarks: ['', [Validators.required]]
    })
  }
  clearToDate() {
    this.formStartReg.get('toDate')?.setValue(null);
  }
  clearFromDate() {
    this.formStartReg.get('fromDate')?.setValue(null);
  }
  submit() {
    if (this.formStartReg.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
      let formData = this.formStartReg.getRawValue();
      let fromDate: any; let toDate: any = null;
      if (formData.fromDate) {
        fromDate = this.datePipe.transform(formData.fromDate.toLocaleString(), 'dd/MM/yyyy hh:mm:ss a');
      }
      if (this.formStartReg.value.toDate) {
        toDate = this.datePipe.transform(this.formStartReg.value.toDate.toLocaleString(), 'dd/MM/yyyy hh:mm:ss a');
      }

      let surveyYear = this.formStartReg.getRawValue().surveyYearValue;
      // console.log(fromDate,toDate,surveyYear)
      let id = this.isEdit ? this.id : 0;
      let payload = {
        "id": id,
        "endDate": toDate,
        "startDate": fromDate,
        "surveyYear": +surveyYear,
        "remarks": formData.remarks
      }

      // call API here
      this.regMangementService.createReg(payload).subscribe((res) => {
        if (res.status === 200) {
          this.sharedservice.showSuccessMessage(res.message);
          this.getRegistionData();
          this.reset();
        }
      }, err => {
        this.sharedservice.showError(err.error.message);

      })
    }
  }
  reset() {
    this.isEdit = false;
    this.disabled = false;
    this.minDate = new Date();
    this.myForm.resetForm();
    this.createForm();
    this.formStartReg.controls['surveyYearValue'].enable();
    this.formStartReg.controls['fromDate'].enable();
  }
  getRegistionData() {
    this.regMangementService.getActivityData().subscribe((res) => {
      if (res.data) {
        this.userDataTable = res.data;
        this.userDataTable.forEach((element: any) => {
          let splitSurvey = element.surveyYear.toString().substring(2, 4);
          let intSurvey = parseInt(splitSurvey)
          let a = intSurvey + 1;
          element['surveyYearFY'] = element.surveyYear + '-' + a;
          if (element.isActive === null) {
            element.addClass = 'red';
            element.status = 'Permanent Closed';
          } else if (element.isActive === false) {
            element.addClass = 'green';

            if (element.endDateString) {
              if (this.convertDateString(element.endDateString) < new Date()) {
                element.status = 'Closed';
                element.addClass = 'changeStatusColor';
              } else {
                element.status = 'Active';
              }
            }else{
              element.status = 'Active';
            }
          } else {
            element.addClass = '';
            element.status = 'Upcoming';
          }

        });


        this.year = this.userDataTable[this.userDataTable.length - 1]?.surveyYear;
        const pattern = Validators.pattern(this.generateYearRegex());
        this.formStartReg.get('surveyYearValue')?.setValidators([pattern, Validators.required]);
        this.formStartReg.get('surveyYearValue')?.updateValueAndValidity();
      }
    })
  }
  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
    //this.getData1();
  }
  handlePageChange(event: number) {
    this.page = event;
    let fgh = parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * fgh),
      (this.EndLimit = this.StartLimit + fgh);
    var a = Math.ceil(this.userDataTable.length / fgh);
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + fgh, this.userDataTable.length);
    } else {
      this.pageData = Math.min(this.StartLimit + fgh, this.userDataTable.length);
    }
  }

  compareFn(t1: any, t2: any): boolean {
    return t1 && t2 ? t1 === t2 : t1 === t2;
  }

  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  edit(data: any) {
    this.isEdit = true;
    this.id = data.id;
    this.disabled = true;
    this.minDate = null;
    if (data) {
      this.formStartReg.controls['surveyYearValue'].disable();
      this.formStartReg.controls['remarks'].setValue(data.remarks);
      this.maxDate = this.convertDateString(data.startDateString);
      this.formStartReg.controls['surveyYearValue'].setValue(data.surveyYear.toString());
      this.formStartReg.controls['fromDate'].setValue(data.startDateString ? this.convertDateString(data.startDateString) : '');
      this.formStartReg.controls['toDate'].setValue(data.endDateString ? this.convertDateString(data.endDateString) : '');

    }
  }
  alphaNumberOnly(e: any) {  // Accept only alpha numerics, not special characters 
    var regex = new RegExp("[0-9]");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
      if (e.target.value.substr(-1) === ' ' && e.code === 'Space') {
        e.preventDefault();
      }
      return true;
    }

    e.preventDefault();
    return false;
  }
  minimumDate(): any {
    if (this.formStartReg.controls['fromDate'].value) {
      const dateObj = new Date(this.formStartReg.controls['fromDate'].value.toLocaleString());
      const day = dateObj.getDate();
      const month = dateObj.getMonth() + 1;
      const year = dateObj.getFullYear();
      const formattedDate = `${String(day).padStart(2, '0')}-${String(month).padStart(2, '0')}-${year}`;

      return this.addOneDay(year,month,day);
    }
  }
  addOneDay(year: any,month:any,day:any) {
    let date = new Date(year,month-1,day);
    date.setDate(date.getDate());
    return date;
  }
  convertDateString(inputDateStr: any) {
    let [datePart, timePart, aa] = inputDateStr?.split(' ');
    timePart = timePart + ' ' + aa;
    let [day, month, year] = datePart.split('/').map(Number);
    let [time, period] = timePart.split(' ');
    let [hours, minutes, seconds] = time.split(':').map(Number);
    if (period === 'PM' && hours !== 12) {
      hours += 12;
    } else if (period === 'AM' && hours === 12) {
      hours = 0;
    }
    let date = new Date(year, month - 1, day, hours, minutes, seconds);
    // return new Date(date.toString());
    return date
  }
  handleToDate(event: any) {
    const m = event.value._d;
    if (m) {
      var year = m.getFullYear();
      var month = m.getMonth() + 1;
      month = +month < 10 ? '0' + month : month;
      var day = m.getDate();
      day = +day < 10 ? '0' + day : day
      let hour = m.getHours();
      hour = +hour < 10 ? '0' + hour : hour
      let minute = m.getMinutes();
      minute = +minute < 10 ? '0' + minute : minute
      let second = m.getSeconds();
      second = +second < 10 ? '0' + second : second
      this.minEndDate = new Date(year, month, day);
      this.date_time = `${year}-${month}-${day}T${hour}:${minute}:${second}.000Z`
      // this.surveyForm.get('startDate')?.setValue(m)
      
    }
  }
  checkStatus(data: any): any {
    return (data.status == 'closed' && data.isActive == false) ? true : false;
  }
  delete(id: number) {
    let payload={
      id:id
    }
    this.regMangementService.deleteSurvey(payload).subscribe(res => {

    }, err => {

    })
  }
}

