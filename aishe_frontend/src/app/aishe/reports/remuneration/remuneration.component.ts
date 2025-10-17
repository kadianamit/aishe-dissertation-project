import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, AbstractControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { CountrystatecityService } from 'src/app/service/reports/countrystatecity.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import * as _moment from 'moment';
import { DateAdapter } from '@angular/material/core';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { SharedService } from 'src/app/shared/shared.service';

// tslint:disable-next-line:no-duplicate-imports
//import {default as _rollupMoment} from 'moment';

const moment =  _moment;


@Component({
  selector: 'app-remuneration',
  templateUrl: './remuneration.component.html',
  styleUrls: ['./remuneration.component.scss'],
  

})
export class RemunerationComponent implements OnInit {
  submitted:boolean=false
  isDataLoading:boolean=false
  dataTableToggle:boolean=false
  Showdata12: boolean = false
  showPdfButton:any|false|boolean
  surveyYearOption:any
  maxDate: Date | any;
  date: Date | any;
  allStates={stateName:"ALL",stateCode:"ALL"}


  reportFilterForm :  FormGroup;

  @ViewChild("child") //to get child component reference
  child?:ReportChildComponent

  filterStatesOption:any=[];
  collegeType:any;
  collegeName:any=[];
  filterCollegesName:any;
  filterSurveyYearOption:any=[];
  startDate = new Date();
  filterCourseLevel:any;

  filterdStates:any=[];

  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,private dateAdapter: DateAdapter<Date>,
  private surveyyearservice:SurveyyearService,private sharedservice:SharedService,
  private fb:FormBuilder,
  public countrystatecityService: CountrystatecityService,
) {
  this.dateAdapter.setLocale('en-GB');

    this.reportFilterForm = this.fb.group({
      surveyYear: ['',Validators.required],
      addressStateCode:[this.allStates],
      institution:['ALL'],
      datePickerStart: [''],
      datePickerEnd: ['']

    })
  }


 ngOnInit(): void {

    this.loadSurveyYear();
    this.loadStates()
    // this.maxDate = new Date();
    // this.maxDate.setMonth(this.maxDate.getMonth() - 2 * 21);
  }

  get f(): { [key: string]: AbstractControl } {
    return this.reportFilterForm.controls
  }
  

  loadStates() {

    let countryCode = utility.getLoginlocalStorageUserData().country
   // console.log("countryCode ", countryCode);

    this.countrystatecityService.getstate(countryCode).subscribe((res) => {

      this.filterStatesOption = res
      this.filterStatesOption.splice(0,0,this.allStates)

      this.filterdStates = this.filterStatesOption.slice()

    })


  }

  public onDateChange(event: MatDatepickerInputEvent<Date>): void {
  let startDate  = this.reportFilterForm.value.datePickerStart;
  let endDate  = this.reportFilterForm.value.datePickerEnd;

  if((startDate > endDate ) && (!!startDate && !!endDate)){
    this.sharedservice.showError('Start Date can not be less than End Date');
    this.reportFilterForm.controls['datePickerStart'].reset();
    this.reportFilterForm.controls['datePickerEnd'].reset();
  }
  }

  loadSurveyYear(){
    this.surveyyearservice.getdatasurveyyear().subscribe((res)=>{
      this.surveyYearOption=res;
      this.filterSurveyYearOption=  this.surveyYearOption.slice(2,this.surveyYearOption.length  )
    })
  }




  onReset(): void {
    this.dataTableToggle = false;
    this.isDataLoading=false;
    this.Showdata12=false;
    this.child?.onReset()

    }
    doNothing(){
      // alert("nothing");
      return
    }


    // generateFieldsTableForExcel() {
    //   let fieldsArr = document.forms[0].querySelectorAll<HTMLElement>('mat-form-field');
    //   let index=0;
    //   for(let i=0;i<fieldsArr.length;i++,index++){
    //     let leftLbl = fieldsArr[i].innerText.split('\n')[1];
    //     let leftVal = fieldsArr[i].innerText.split('\n')[0];
    //     i++;
    //     let rightLbl = fieldsArr[i]?.innerText.split('\n')[1];
    //     let rightVal = fieldsArr[i]?.innerText.split('\n')[0];

    //     this.fieldsData[index]={label1:leftLbl,value1:leftVal,label2:rightLbl,value2:rightVal}

    //   }
    // }
    submit(){

      this.submitted=true;
      if(this.reportFilterForm.valid){
      this.child?.getReportDataTable();
    }
  }

}
