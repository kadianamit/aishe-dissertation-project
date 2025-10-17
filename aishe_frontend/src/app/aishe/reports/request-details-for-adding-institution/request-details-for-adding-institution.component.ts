import { Component, Inject, Input, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { CountrystatecityService } from 'src/app/service/reports/countrystatecity.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { SharedService } from 'src/app/shared/shared.service';
import { AuthService } from '../../../service/auth.service';
import { ExcelService } from 'src/app/service/reports/excel.service';

@Component({
  selector: 'app-request-details-for-adding-institution',
  templateUrl: './request-details-for-adding-institution.component.html',
  styleUrls: ['./request-details-for-adding-institution.component.scss']
})
export class RequestDetailsForAddingInstitutionComponent implements OnInit {
  reportFilterForm :  FormGroup;
  submitted:boolean=false;
  allStates={stateName:"ALL",stateCode:"ALL"};
  filterSurveyYearOption:any=[];
  surveyYearOption:any;
  maxDate: Date | any;
  date: Date | any;
  filterStatesOption:any=[];
  fieldsData:any=[];
  filterdStates:any=[];
  remunationStatusList:any
  remunationStatusList1:any
  userData: string | null;
  @ViewChild("child") //to get child component reference
  child?:ReportChildComponent;
  tomorrow = new Date();
  @Input() max: any;
  allremunationStatus: any;
  roleId:any;
  stateCodeDeafult:any;
  isAdminOrMoE:any;
  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,private dateAdapter: DateAdapter<Date>,
  private surveyyearservice:SurveyyearService,private sharedservice:SharedService,
  private fb:FormBuilder,
  public countrystatecityService: CountrystatecityService,private excelService:ExcelService,public localService: LocalserviceService,public authService :AuthService) {
         this.roleId = this.localService.getData('roleId')
       
         this.stateCodeDeafult = this.localService.getData('stateCode');
    this.userData= this.localService.getData('userData')
  
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.reportFilterForm = this.fb.group({
      // surveyYear: ['',Validators.required],
      addressStateCode:[this.allStates,Validators.required],
      datePickerStart: ['',Validators.required],
      datePickerEnd: ['',Validators.required],
      status:['',Validators.required],

    })
   }
   get f(): { [key: string]: AbstractControl } {
    return this.reportFilterForm.controls
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

remunationStatus(){
  this.authService.getReportRemunerationStatus().subscribe((res)=>{
    this.remunationStatusList1 = res.data.slice(0,3);
    this.allremunationStatus = [{id:"ALL",status:"ALL"}];
    this.remunationStatusList=[...this.allremunationStatus,...this.remunationStatusList1];



    console.log('iuuu',this.remunationStatusList)
  })
}

   loadSurveyYear(){
    this.surveyyearservice.getdatasurveyyear().subscribe((res)=>{
      this.surveyYearOption=res;
      if(this.userData==="true"){
        let dataY= res.slice(2)
         this.surveyYearOption=dataY
         this.filterSurveyYearOption=  this.surveyYearOption
       }else{
         // Create the new entries for 2024 and 2023
 let newEntries = [
   { "surveyYear": "2024", "surveyYearValue": "2024-25" },
  //  { "surveyYear": "2023", "surveyYearValue": "2023-24" }
 ];
       this.filterSurveyYearOption=  [...newEntries, ...this.surveyYearOption];
       console.log('dhaan year ',this.filterSurveyYearOption)
       }
     })
  }
  loadStates() {

    let countryCode = utility.getLoginlocalStorageUserData().country
   // console.log("countryCode ", countryCode);

    this.countrystatecityService.getstate(countryCode).subscribe((res) => {

      this.filterStatesOption = res
     this.filterStatesOption.unshift(this.allStates); // optional "All States"

    this.filterdStates = [...this.filterStatesOption];
  // if (!this.isAdminOrMoE && !this.userData) {
  //     const selected = this.filterStatesOption.find(
  //       (s: any) => s.stateCode == this.stateCodeDeafult
  //     );
  //     if (selected) {
  //       this.reportFilterForm.patchValue({ addressStateCode: selected });
  //       this.reportFilterForm.get('addressStateCode')?.disable();
      
  //     }
  //   }
  });


  }
  ngOnInit(): void {
    this.isAdminOrMoE=(this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])

    // this.loadSurveyYear();
    this.loadStates();
    this.remunationStatus();
  }
  exportToExcel(){
    this.excelService.exportToExcel(this.reportInfoObj.reportNumber,this.child?.tableColumns);
  }

  generateFieldsTableForExcel() {
    let fieldsArr = document.forms[0].querySelectorAll<HTMLElement>('mat-form-field');
    let index=0;
    for(let i=0;i<fieldsArr.length;i++,index++){
      let leftLbl = fieldsArr[i].innerText.split('\n')[1];
      let leftVal = fieldsArr[i].innerText.split('\n')[0];
      i++;
      let rightLbl = fieldsArr[i]?.innerText.split('\n')[1];
      let rightVal = fieldsArr[i]?.innerText.split('\n')[0];

      this.fieldsData[index]={label1:leftLbl,value1:leftVal,label2:rightLbl,value2:rightVal}

    }
  }
  submit(){
    this.submitted=true;
    if(this.reportFilterForm.valid){
      this.child?.getReportDataTable();
      this.generateFieldsTableForExcel();
 
  }
}
onReset(){
  this.reportFilterForm.reset();
  this.child?.onReset()
  //  if (!this.isAdminOrMoE && !this.userData) {
  //     const selected = this.filterStatesOption.find(
  //       (s: any) => s.stateCode == this.stateCodeDeafult
  //     );
  //     if (selected) {
  //       this.reportFilterForm.patchValue({ addressStateCode: selected });
  //       this.reportFilterForm.get('addressStateCode')?.disable();
      
  //     }
  //   }else{
  //      this.child?.onReset();
  //   }
}
}
