import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';
import * as moment from 'moment';
import { ThemePalette } from '@angular/material/core';
import {
  NgxMatDateFormats,
  NGX_MAT_DATE_FORMATS
} from '@angular-material-components/datetime-picker';
import { LocalserviceService } from 'src/app/service/localservice.service';

// If using Moment
const CUSTOM_DATE_FORMATS: NgxMatDateFormats = {
  parse: {
    dateInput: 'DD/MM/YYYY HH:mm:ss'
  },
  display: {
    dateInput: 'DD/MM/YYYY HH:mm:ss',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY'
  }
};
@Component({
  selector: 'app-survey-action',
  templateUrl: './survey-action.component.html',
  styleUrls: ['./survey-action.component.scss'],
  providers: [
    // {provide: NgxMatDateAdapter,useClass: AppDateAdapter},
    { provide: NGX_MAT_DATE_FORMATS, useValue: CUSTOM_DATE_FORMATS }
  ],
})
export class SurveyActionComponent implements OnInit {
  @ViewChild('picker') picker: any;
  @ViewChild('picker1') picker1: any;
  date_time: any;
  public date: any;
  public disabled = true;
  public showSpinners = true;
  public showSeconds = true;
  public touchUi = false;
  public enableMeridian = true;
  public minDate: any;
  public minEndDate: any;
  public stepHour = 1;
  public stepMinute = 1;
  public stepSecond = 1;
  public color: ThemePalette = 'primary';
  buttonName: string = 'Create'
  surveyForm: FormGroup;
  surveyYearList: Array<any> = [];
  surveyActionList: Array<any> = [];
  minSurvey: any;
  maxSurvey: any
  isFormInvalid: boolean = false;
  showSelectServey: boolean = false
  registartionStartDate: any;
  specialStartDate: any
  // public startDate = new FormControl();
  // public endDate = new FormControl();
  notSurveyCreated:boolean=false
  specialPerNotCreated:boolean=false
  public options = [
    { value: true, label: 'True' },
    { value: false, label: 'False' }
  ];

  public listColors = ['primary', 'accent', 'warn'];

  public stepHours = [1, 2, 3, 4, 5];
  public stepMinutes = [1, 5, 10, 15, 20, 25];
  public stepSeconds = [1, 5, 10, 15, 20, 25];
  constructor(private fb: FormBuilder, public sharedService: SharedService, public authService: AuthService,
    public errorMatcher: CustomErrorStateMatcher,public localService: LocalserviceService) {
    this.minSurvey = new Date()
    this.surveyForm = this.fb.group({
      surveyAction: ['', [Validators.required]],
      surveyYear: ['', [Validators.required]],
      startDate: ['', []],
      endDate: ['', []]

    })
  }


  ngOnInit(): void {
    this.surveyAction();
    this.getSurveyYear();
  }
  getActivity() {
    let activity = this.surveyActionList.find((ele: any) => ele.id === 5).id;
    if (activity === this.surveyForm.controls['surveyAction'].value) {
      this.showSelectServey = false;
      this.surveyForm.get('startDate')?.setValue('');
      // this.surveyForm.get('startDate')?.updateValueAndValidity();
      this.surveyForm.get('endDate')?.setValue('');
      // this.surveyForm.get('endDate')?.updateValueAndValidity();
    } else {
      this.showSelectServey = true;
    }
    this.surveyForm.get('startDate')?.enable();
    this.surveyForm.get('startDate')?.updateValueAndValidity();
    this.surveyForm.get('endDate')?.enable();
    this.surveyForm.get('endDate')?.updateValueAndValidity();
    if (this.surveyForm.controls['surveyAction'].value === 6 || this.surveyForm.controls['surveyAction'].value === 9 || this.surveyForm.controls['surveyAction'].value === 12) {
      this.surveyForm.get('endDate')?.disable();
      this.surveyForm.get('endDate')?.updateValueAndValidity();
      this.buttonName = 'Update'
    }
    if (this.surveyForm.controls['surveyAction'].value === 7 || this.surveyForm.controls['surveyAction'].value === 10 || this.surveyForm.controls['surveyAction'].value === 13) {
      this.surveyForm.get('startDate')?.disable();
      this.surveyForm.get('startDate')?.updateValueAndValidity();
      this.buttonName = 'Update'
    }
    if (this.surveyForm.controls['surveyAction'].value === 14) {
      // this.surveyForm.get('startDate')?.clearValidators();
      // this.surveyForm.get('startDate')?.updateValueAndValidity();
      this.buttonName = 'Freeze'
    } if (this.surveyForm.controls['surveyAction'].value === 5 || this.surveyForm.controls['surveyAction'].value === 8 || this.surveyForm.controls['surveyAction'].value === 11) {
      this.buttonName = 'Create'
    }
    this.surveyForm.get('startDate')?.setValue('');
    this.surveyForm.get('endDate')?.setValue('');
    this.getSurveyLog()
  }

  getSurveyYear() {
    this.authService.getIsSurveyFreezed(false).subscribe(res => {
      this.surveyYearList = []
      this.surveyYearList = res.data;
      this.surveyYearList?.forEach(ele => {
        var splitSurvey = (ele.surveyYear).toString().substring(2, 4);
        var intSurvey = parseInt(splitSurvey)
        var a = intSurvey + 1;
        ele['surveyYearValue'] = ele.surveyYear + '-' + a;
      })
    }, err => {

    })
  }
  surveyAction() {
    this.authService.getSurveyAction().subscribe(res => {
      this.surveyActionList = []
      this.surveyActionList = res.data;
      this.surveyActionList.splice(0, 4)
    }, err => {

    })
  }
  handleSurveyYear(event: any) {
    const m = event.value;
    if (m) {
      this.maxSurvey = m;
    }
  }
  getSurveyLog() {
    if (this.surveyForm.controls['surveyYear'].value) {
      this.authService.getSurveyData(this.surveyForm.controls['surveyYear'].value).subscribe(res => {
        if ((this.surveyForm.controls['surveyAction'].value === 6) || (this.surveyForm.controls['surveyAction'].value === 7)) {
          if (res.data['0'].registrationStartDate) {
            const [dateStringStart, timeStringStart] = res.data['0'].registrationStartDate.split(' ')
            const [dayStart, monthStart, yearStart] = dateStringStart.split('/')
            let timeStart = timeStringStart.split(":")
            let mStart = parseInt(monthStart) - 1
            let hhStart = timeStart['0']
            let mmStart = timeStart['1']
            let ssStart = timeStart['2']
            this.surveyForm.get('startDate')?.setValue(new Date(parseInt(yearStart), mStart, parseInt(dayStart), hhStart, mmStart, ssStart))

          } if (res.data['0'].registrationEndDate) {
            const [dateStringEnd, timeStringEnd] = res.data['0'].registrationEndDate.split(' ')
            const [dayEnd, monthEnd, yearEnd] = dateStringEnd.split('/')
            let timeEnd = timeStringEnd.split(":")
            let mEnd = parseInt(monthEnd) - 1
            let hhEnd = timeEnd['0']
            let mmEnd = timeEnd['1']
            let ssEnd = timeEnd['2']
            this.surveyForm.get('endDate')?.setValue(new Date(parseInt(yearEnd), mEnd, parseInt(dayEnd), hhEnd, mmEnd, ssEnd))
          }
        } if ((this.surveyForm.controls['surveyAction'].value === 9) || (this.surveyForm.controls['surveyAction'].value === 10)) {
          if (res.data['0'].startDate) {
            const [dateStringStart, timeStringStart] = res.data['0'].startDate.split(' ')
            const [dayStart, monthStart, yearStart] = dateStringStart.split('/')
            let timeStart = timeStringStart.split(":")
            let mStart = parseInt(monthStart) - 1
            let hhStart = timeStart['0']
            let mmStart = timeStart['1']
            let ssStart = timeStart['2']
            this.surveyForm.get('startDate')?.setValue(new Date(parseInt(yearStart), mStart, parseInt(dayStart), hhStart, mmStart, ssStart))
            this.notSurveyCreated = false;
          } else {
            this.notSurveyCreated = true;
          }
          if (res.data['0'].endDate) {
            const [dateStringEnd, timeStringEnd] = res.data['0'].endDate.split(' ')
            const [dayEnd, monthEnd, yearEnd] = dateStringEnd.split('/')
            let timeEnd = timeStringEnd.split(":")
            let mEnd = parseInt(monthEnd) - 1
            let hhEnd = timeEnd['0']
            let mmEnd = timeEnd['1']
            let ssEnd = timeEnd['2']
            this.surveyForm.get('endDate')?.setValue(new Date(parseInt(yearEnd), mEnd, parseInt(dayEnd), hhEnd, mmEnd, ssEnd))
          }
        } if ((this.surveyForm.controls['surveyAction'].value === 12) || (this.surveyForm.controls['surveyAction'].value === 13)) {
          if (res.data['0'].specialPermissionStartDate) {
            const [dateStringStart, timeStringStart] = res.data['0'].specialPermissionStartDate.split(' ')
            const [dayStart, monthStart, yearStart] = dateStringStart.split('/')
            let timeStart = timeStringStart.split(":")
            let mStart = parseInt(monthStart) - 1
            let hhStart = timeStart['0']
            let mmStart = timeStart['1']
            let ssStart = timeStart['2']
            this.surveyForm.get('startDate')?.setValue(new Date(parseInt(yearStart), mStart, parseInt(dayStart), hhStart, mmStart, ssStart));
            this.specialPerNotCreated=false
          }else{
            this.specialPerNotCreated=true
          } 
          if (res.data['0'].specialPermissionEndDate) {
            const [dateStringEnd, timeStringEnd] = res.data['0'].specialPermissionEndDate.split(' ')
            const [dayEnd, monthEnd, yearEnd] = dateStringEnd.split('/')
            let timeEnd = timeStringEnd.split(":")
            let mEnd = parseInt(monthEnd) - 1
            let hhEnd = timeEnd['0']
            let mmEnd = timeEnd['1']
            let ssEnd = timeEnd['2']
            this.surveyForm.get('endDate')?.setValue(new Date(parseInt(yearEnd), mEnd, parseInt(dayEnd), hhEnd, mmEnd, ssEnd))
          }
        }
        if ((this.surveyForm.controls['surveyAction'].value === 8)) {
          const [dateStringStart, timeStringStart] = res.data['0'].registrationStartDate.split(' ')
          const [dayStart, monthStart, yearStart] = dateStringStart.split('/')
          let timeStart = timeStringStart.split(":")
          let mStart = parseInt(monthStart) - 1
          let hhStart = timeStart['0']
          let mmStart = timeStart['1']
          let ssStart = timeStart['2']

          this.registartionStartDate = new Date(parseInt(yearStart), mStart, parseInt(dayStart), hhStart, mmStart, ssStart)
        } if (this.surveyForm.controls['surveyAction'].value === 11) {
          const [dateStringStart, timeStringStart] = res.data['0'].registrationStartDate.split(' ')
          const [dayStart, monthStart, yearStart] = dateStringStart.split('/')
          let timeStart = timeStringStart.split(":")
          let mStart = parseInt(monthStart) - 1
          let hhStart = timeStart['0']
          let mmStart = timeStart['1']
          let ssStart = timeStart['2']
          this.specialStartDate = new Date(parseInt(yearStart), mStart, parseInt(dayStart), hhStart, mmStart, ssStart)

        }
        if (this.surveyForm.controls['startDate'].value) {
          let event = {
            value: {
              _d: this.surveyForm.controls['startDate'].value
            }
          }
          this.handleToDate(event)
        }
      }, err => {

      })
    }
  }
  action() {
    if (this.surveyForm.controls['surveyAction'].value === 6 || this.surveyForm.controls['surveyAction'].value === 9
      || this.surveyForm.controls['surveyAction'].value === 12 || this.surveyForm.controls['surveyAction'].value === 8
      || this.surveyForm.controls['surveyAction'].value === 5) {
      this.getServerTime()
    } else {
      this.savedata()
    }


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
  handleEndDate(event: any) {
    const n = event.value._d
    // this.surveyForm.get('endDate')?.setValue(n)
  }
  getServerTime() {
    this.authService.getDateTime().subscribe(res => {
      const [dateStringEnd, timeStringEnd] = res.split(' ')
      const [dayEnd, monthEnd, yearEnd] = dateStringEnd.split('/')
      let timeEnd = timeStringEnd.split(":")
      let mEnd = monthEnd
      let hhEnd = timeEnd['0']
      let mmEnd = timeEnd['1']
      let ssEnd = timeEnd['2']
      const date = `${yearEnd}-${mEnd}-${dayEnd}T${hhEnd}:${mmEnd}:${ssEnd}.000Z`
      if (this.date_time) {
        // const dateIsAfter = moment('2023-06-01T16:14:32.000Z').isAfter(moment('2023-05-24T04:11:55.000Z'));
        const dateIsAfter = moment(this.date_time).isAfter(moment(date));
        if (!dateIsAfter) {
          this.sharedService.showValidationMessage('Back date not allowed !!!');
        } else {
          this.savedata()
        }
      } else {
        this.savedata()
      }

    }, err => {

    })
  }
  savedata() {
    if (this.surveyForm.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    if(!this.surveyForm.controls['startDate'].value){
      this.sharedService.showValidationMessage('Please enter start date !!!')
      return;
    }
    if ((this.surveyForm.controls['surveyAction'].value === 6) || (this.surveyForm.controls['surveyAction'].value === 7)) {
      if (!this.surveyForm.controls['startDate'].value) {
        this.sharedService.showValidationMessage('Registartion not created !!!')
        return;
      }
    } if ((this.surveyForm.controls['surveyAction'].value === 9) || (this.surveyForm.controls['surveyAction'].value === 10)) {
      if (this.notSurveyCreated) {
        this.sharedService.showValidationMessage('Survey not created !!!')
        return;
      }
    } if ((this.surveyForm.controls['surveyAction'].value === 12) || (this.surveyForm.controls['surveyAction'].value === 13)) {
      if (this.specialPerNotCreated) {
        this.sharedService.showValidationMessage('Special Permission not created !!!')
        return;
      }
    }
    if ((this.surveyForm.controls['surveyAction'].value === 8)) {
      if (this.registartionStartDate) {
        var year = this.registartionStartDate.getFullYear();
        var month = this.registartionStartDate.getMonth() + 1;
        month = +month < 10 ? '0' + month : month;
        var day = this.registartionStartDate.getDate();
        day = +day < 10 ? '0' + day : day
        let hour = this.registartionStartDate.getHours();
        hour = +hour < 10 ? '0' + hour : hour
        let minute = this.registartionStartDate.getMinutes();
        minute = +minute < 10 ? '0' + minute : minute
        let second = this.registartionStartDate.getSeconds();
        second = +second < 10 ? '0' + second : second
        this.minEndDate = new Date(year, month, day);
        var regDate = `${year}-${month}-${day}T${hour}:${minute}:${second}.000Z`

        const isDate = moment(this.date_time).isAfter(moment(regDate));
        if (!isDate) {
          this.sharedService.showValidationMessage('Date should not be smaller than registration start date')
          return;
        }
      }

    } if ((this.surveyForm.controls['surveyAction'].value === 11)) {
      if (this.specialStartDate) {
        var year = this.specialStartDate.getFullYear();
        var month = this.specialStartDate.getMonth() + 1;
        month = +month < 10 ? '0' + month : month;
        var day = this.specialStartDate.getDate();
        day = +day < 10 ? '0' + day : day
        let hour = this.specialStartDate.getHours();
        hour = +hour < 10 ? '0' + hour : hour
        let minute = this.specialStartDate.getMinutes();
        minute = +minute < 10 ? '0' + minute : minute
        let second = this.specialStartDate.getSeconds();
        second = +second < 10 ? '0' + second : second
        this.minEndDate = new Date(year, month, day);
        var specialDate = `${year}-${month}-${day}T${hour}:${minute}:${second}.000Z`

        const isSpecialDate = moment(this.date_time).isAfter(moment(specialDate));
        if (!isSpecialDate) {
          this.sharedService.showValidationMessage('Date should not be smaller than survey start date')
          return;
        }
      }

    }
    // console.log(moment(new Date(this.surveyForm.controls['endDate'].value)).format("DD/MM/YYYY hh:mm:ss A"))
    // let endDate;
    // let day: string = this.surveyForm.controls['endDate'].value.getDate().toString();
    // day = +day < 10 ? '0' + day : day;
    // let month: string = (this.surveyForm.controls['endDate'].value.getMonth() + 1).toString();
    // month = +month < 10 ? '0' + month : month;
    // let year = this.surveyForm.controls['endDate'].value.getFullYear();
    // let hour = this.surveyForm.controls['endDate'].value.getHours();
    // let minute = this.surveyForm.controls['endDate'].value.getMinutes();
    // let second = this.surveyForm.controls['endDate'].value.getSeconds();
    // endDate = `${day}/${month}/${year}`;
    // endDate.toString();
    // let startDate;
    // let day1: string = this.surveyForm.controls['startDate'].value.getDate().toString();
    // day1 = +day1 < 10 ? '0' + day1 : day1;
    // let month1: string = (this.surveyForm.controls['startDate'].value.getMonth() + 1).toString();
    // month1 = +month1 < 10 ? '0' + month1 : month1;
    // let year1 = this.surveyForm.controls['startDate'].value.getFullYear();
    // startDate = `${day1}/${month1}/${year1}`;
    // startDate.toString();

    let payload = {
      "endDate": this.surveyForm.controls['endDate'].value ? moment(new Date(this.surveyForm.controls['endDate'].value)).format("DD/MM/YYYY hh:mm:ss A") : '',
      "startDate": this.surveyForm.controls['startDate'].value ? moment(new Date(this.surveyForm.controls['startDate'].value)).format("DD/MM/YYYY hh:mm:ss A") : '',
      "surveyCreationType": this.surveyForm.controls['surveyAction'].value,
      "surveyYear": this.surveyForm.controls['surveyYear'].value,
      "userId": this.localService.getData('userId')
    }
    this.authService.saveSurvey(payload).subscribe(res => {
      if (res.status === 201) {
        this.sharedService.showSuccess();
        this.getSurveyYear();
      }
    }, err => {
      if (err.status === 422) {
        this.sharedService.showValidationMessage(err.error.data.message)
      }
    })
  }
}
