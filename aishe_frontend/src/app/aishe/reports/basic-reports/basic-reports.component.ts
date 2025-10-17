import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, AbstractControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { DisciplineService } from 'src/app/service/get/discipline.service';
import { LevelService } from 'src/app/service/get/level.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { CollegeService } from 'src/app/service/reports/college.service';
import { CountrystatecityService } from 'src/app/service/reports/countrystatecity.service';
import { ExcelService } from 'src/app/service/reports/excel.service';
import { InstitutionService } from 'src/app/service/reports/institution.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { UniversityService } from 'src/app/service/reports/university.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-basic-reports',
  templateUrl: './basic-reports.component.html',
  styleUrls: ['./basic-reports.component.scss']
})
export class BasicReportsComponent implements OnInit {
  submitted:boolean=false;
  isDataLoading:boolean=false
  dataTableToggle:boolean=false
  Showdata12: boolean = false
  showPdfButton:boolean = false;
  surveyYearOption:any;
  filterSurveyYearOption:any;
  filterStatesOption:any;
  filterdStates:any;
  filterStandaloneOption:any;
  filterInstitutes:any;
  filterUniversityOption:any;
  filterUniversities:any;
  filterCollegesOption:any;
  filterColleges:any;

  // allStates={stateName:"--ALL--",stateCode:"ALL"}
  // allUniversity={name:"--ALL--",id:"ALL"}
  // allColleges={id:'ALL',name:'--ALL--'};

  reportFilterForm :  FormGroup;

  @ViewChild("child") //to get child component reference
  child?:ReportChildComponent

  fieldsData:any=[];
  userData: string | null;

  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,
  private surveyyearservice:SurveyyearService,
  private fb:FormBuilder,
  public countrystatecityService: CountrystatecityService,
  private UniversityService:UniversityService,
  private collegeService:CollegeService,
  private institutionService:InstitutionService,
  private reportService:ReportService,
  private DisciplineService:DisciplineService,
  private courseLevel:LevelService,
  private excelService:ExcelService,
  private dialog: MatDialog,
  public sharedservice: SharedService,public localService: LocalserviceService) {
    this.userData= this.localService.getData('userData');
    //this.sharedservice.global_loader = true;

    this.reportFilterForm = this.fb.group({
      surveyYear: ['',Validators.required],
      addressStateCode:['',Validators.required],
      universityCode:['',Validators.required],
      instituteCode:['',Validators.required],
      collegeCode:['',Validators.required],

      })
      if(this.reportInfoObj.reportNumber=="Report 1"){
        this.f['instituteCode'].disable();
        this.f['collegeCode'].disable();

      }
      if(this.reportInfoObj.reportNumber=="Report 2")
    {
      this.f['instituteCode'].disable();
    }
    if(this.reportInfoObj.reportNumber=="Report 3")
    {
      this.f['collegeCode'].disable();
      this.f['universityCode'].disable();
    }
  }


 ngOnInit(): void {

    this.loadSurveyYear();
    this.loadStates();
    this.loadUniversity();
    this.loadStandaloneInstitutes();
  }

  get f(): { [key: string]: AbstractControl } {
    return this.reportFilterForm.controls
  }

loadSurveyYear() {
  if (this.userData === "true") {
    this.surveyyearservice.getdatasurveyyearDataUser().subscribe((res: any) => {
      // Get a sliced version starting from the 3rd element
      const dataY = res.slice(2);
      this.surveyYearOption = dataY;
      this.filterSurveyYearOption = dataY;
    });
  } else {
    this.surveyyearservice.getdatasurveyyear().subscribe((res: any) => {
      this.surveyYearOption = res;
      this.filterSurveyYearOption = res;
    });
  }
}


  loadStates() {

    let countryCode = utility.getLoginlocalStorageUserData().country
   // console.log("countryCode ", countryCode);

    this.countrystatecityService.getstate(countryCode).subscribe((res) => {

      this.filterStatesOption = res
      // this.filterStatesOption.splice(0,0,this.allStates)

      this.filterdStates = this.filterStatesOption.slice()

    })

  }



  loadUniversity(){

    this.reportFilterForm.controls['universityCode'].setValue('');
    this.filterUniversityOption=[];
    this.filterUniversities=[];
      if(this.reportFilterForm.value.addressStateCode==null || !this.reportFilterForm.value.addressStateCode.stateCode){
      return;
    }

    let stateCode = this.reportFilterForm.value.addressStateCode.stateCode;

    console.log("loadUniversity stateCode: ", stateCode);

    this.isDataLoading=true;


    this.UniversityService.getdataaishe(stateCode).pipe(
      finalize(()=>{
        this.isDataLoading=false;
      })
      ).subscribe((res) => {
        // console.log("response : ",res);

      if(!res || res.length == 0){
        return;
      }

      this.filterUniversityOption = res;
      this.filterUniversities = this.filterUniversityOption.slice();


    });
  }

  loadStandaloneInstitutes(){

    //set empty on states change
    this.reportFilterForm.controls['instituteCode'].setValue('');
    this.filterStandaloneOption=[];
    if(!this.reportFilterForm.value.addressStateCode.stateCode){
      return;
    }

    let stateCode=this.reportFilterForm.value.addressStateCode.stateCode
    console.log("Selected stateCode: ",stateCode);
    this.isDataLoading=true;
    this.institutionService.getInstitutes(stateCode).pipe(
      finalize(()=>{
        this.isDataLoading=false;
      })
    ).subscribe(res=>{
      if(!res || res.length==0){
      return;
      }
      this.filterStandaloneOption = res;

      this.filterInstitutes = this.filterStandaloneOption.slice();

    });

  }

  loadCollege(){

    console.log("called loadCollege ");
    this.reportFilterForm.controls['collegeCode'].setValue('');
    this.filterCollegesOption=[];
    this.filterColleges=[];
    if(this.reportFilterForm.value.universityCode==null || !this.reportFilterForm.value.universityCode.id){
    return;
    }
    let universityId=this.reportFilterForm.value.universityCode.id;

    console.log("universityId: ",universityId);
    this.isDataLoading=true;

    this.collegeService.getCollege(universityId).pipe(
      finalize(()=>{
        this.isDataLoading=false;
      })
    ).subscribe(res=>{
      console.log("loadCollege res ",res);

      if(!res || res.length==0){
         return;
      }

      this.filterCollegesOption = res;
      // this.filterCollegesOption.splice(0,0,this.allColleges);

      this.filterColleges = this.filterCollegesOption.slice();

    });

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
// to export excel file from

exportToExcel(){
      this.reportInfoObj.fieldsData = this.fieldsData;
        this.excelService.exportExcel(this.reportInfoObj);
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
}
