import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, AbstractControl, Validators, FormControl } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { CountrystatecityService } from 'src/app/service/reports/countrystatecity.service';
import { ExcelService } from 'src/app/service/reports/excel.service';

import { LevelService } from 'src/app/service/get/level.service';
import { CollegeService } from 'src/app/service/reports/college.service';
import { InstitutionService } from 'src/app/service/reports/institution.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { UniversityService } from 'src/app/service/reports/university.service';
import { SharedService } from 'src/app/shared/shared.service';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'app-progress-monitoring',
  templateUrl: './progress-monitoring.component.html',
  styleUrls: ['./progress-monitoring.component.scss']
})
export class ProgressMonitoringComponent implements OnInit {

  @ViewChild("child") //to get child component reference
  child?:ReportChildComponent
  allCollegeName={ name:"ALL",id:"ALL"};
  submitted:boolean=false;
  isDataLoading:boolean=false
  surveyYearOption:any
  filterSurveyYearOption:any=[];
  filterStatesOption:any;
  filterdStates:any=[];
  UniversityNameData:any;
  managmentTypeData:any;
  collegeType:any;
  collegeName:any;
  dataBodyType:any;
  filtermanagmentTypeData:any=[];
  filterColleges:any=[];
  allCollegeType:any=[];
  universityType1:any;
  filtereduniversityType:any=[];
  filteredUniversityNameData:any=[]
  filterCollegesName:any=[];
  allStates={stateName:"ALL",stateCode:"ALL"}
  allCollegeType1={ type:"ALL",id:"ALL"}
  allUniversity={name:"ALL",id:"ALL"}
  allBodyType={type:"ALL",id:"ALL"}
  institutions:any=[]
  filterinstitutions:any
  allManagementTypes={managementType:"ALL",id:"ALL"}
  alldisciplinGroup={disciplinGrouCategory:"ALL",id:"ALL"}
  alldisciplinName={disciplineGroup:"ALL",id:"ALL"}
  universityName={name:"ALL",id:"ALL"}
  allUniversityType={type:"ALL",id:"ALL"}
  allColleges={id:'ALL',name:'ALL'}
  allNameWithId={name:"ALL",id:"ALL"}

x:any
    filteredBodyTypes:any=[];

  reportFormlistofInst = new FormGroup({
    institution: new FormControl(''),
    surveyYear: new FormControl(''),
    addressStateCode: new FormControl(''),
    universityName:new FormControl(''),
    collegeType: new FormControl(''),
    bodyType: new FormControl(''),
    universityType: new FormControl(''),
    managementType:new FormControl(''),
  })
  fieldsData:any=[];
filterUniversityNameData: any;
bodyTypes: any;
  roleId: string;
  stateCodeDeafult: any;
  isAdminOrMoE:any
  aisheCode: any;
  splitAisheCode: any;
  userData: any;
  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,
  private surveyyearservice:SurveyyearService,
  private fb:FormBuilder,
  public countrystatecityService: CountrystatecityService,
  private excelService:ExcelService,
  private collegeService:CollegeService,private sharedService:SharedService,
  private institutionService:InstitutionService,
  private reportService:ReportService,
  private dialog: MatDialog,
  private courseLevel:LevelService, private UniversityService:UniversityService,public localService: LocalserviceService,
     public sharedservice:SharedService) {
     this.roleId = this.localService.getData('roleId')
this.aisheCode = localService.getData('aisheCode')
this.userData= this.localService.getData('userData');

    this.splitAisheCode = this.aisheCode.split('-')[1];
    this.stateCodeDeafult = this.localService.getData('stateCode');
  }

  ngOnInit(): void {
 this.isAdminOrMoE = (this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])
    this.loadStates();
    this.loadSurveyYear()
    this.universityTypeData();
//this.findInstitutionNames();
    // this.filterinstitutions=this.institutions.slice();
    this.findBodyType();
    this.findManagmentTypeData();
    this.findCollegeTypeData();
    this.formControl()

  }

  formControl(){
    this.reportFormlistofInst = this.fb.group({
      surveyYear: ['',Validators.required],
      institution:['ALL'],
      addressStateCode:[this.allStates],
      collegeType:['ALL'],
      collegeName:['ALL'],
      universityType:[this.allUniversityType],
      universityName:[this.universityName],
      managementName:['ALL'],
      bodyType:['ALL'],
    })
  }
  get f(): { [key: string]: AbstractControl } {
    return this.reportFormlistofInst.controls
  }

  findInstitutionNames(){
    let event=this.reportFormlistofInst.value.surveyYear;
    if(this.reportFormlistofInst.value.addressStateCode){
      this.institutionService.getInstitutes(this.reportFormlistofInst.value.addressStateCode.stateCode).subscribe((res)=>{
      this.institutions=res;
      this.institutions.splice(0,0,this.allNameWithId);
      this.filterinstitutions=this.institutions.slice();
      });
    }
    else if(this.reportFormlistofInst.value.bodyType){
      this.institutionService.getInstByStateBodyId(this.reportFormlistofInst.value.bodyType.id).subscribe((res)=>{
      this.institutions=res;
      this.institutions.splice(0,0,this.allNameWithId);
      this.filterinstitutions=this.institutions.slice();

      });
    }
    else if(this.reportFormlistofInst.value.bodyType && this.reportFormlistofInst.value.addressStateCode){

      this.institutionService.getInstbyStateCodeAndBody(event,this.reportFormlistofInst.value.addressStateCode.stateCode, this.reportFormlistofInst.value.bodyType.id).subscribe((res)=>{
        this.institutions=res;
        this.institutions.splice(0,0,this.allNameWithId);
        this.filterinstitutions=this.institutions.slice();
      });
    }
  }

  loadSurveyYear(){
    
    this.surveyyearservice.getdatasurveyyear().subscribe((res)=>{
      this.surveyYearOption=res
    //   this.filterSurveyYearOption=  this.surveyYearOption.slice()
    //   let arr = ['Report 161','Report 162','Report 163','Report 96'];
    //   if(arr.includes(this.reportInfoObj.reportNumber.trim())){
    //     this.filterSurveyYearOption=this.filterSurveyYearOption.slice(2,this.filterSurveyYearOption.length)
    //   }
     
  
    // })
     if (this.userData === "true") {
        let dataY = res.slice(2)
        this.surveyYearOption = dataY
        this.filterSurveyYearOption = this.surveyYearOption
      } else {
        this.filterSurveyYearOption = this.surveyYearOption.slice()
      }
    })
  }

  loadStates() {

    let countryCode = utility.getLoginlocalStorageUserData().country
   // console.log("countryCode ", countryCode);

    this.countrystatecityService.getstate(countryCode).subscribe((res) => {

    this.filterStatesOption = res;
      this.filterStatesOption.unshift(this.allStates); // optional "All States"

      this.filterdStates = [...this.filterStatesOption];

  // if (!this.isAdminOrMoE && !this.userData) {
  //     const selected = this.filterStatesOption.find(
  //       (s: any) => s.stateCode == this.stateCodeDeafult
  //     );
  //     if (selected) {
  //       this.reportFormlistofInst.patchValue({ addressStateCode: selected });
  //       this.reportFormlistofInst.get('addressStateCode')?.disable();
      
  //     }
  //   }
  });
  }
  findUniversityByYearAndState(){
    let event=this.reportFormlistofInst.value.surveyYear;
    let obj=this.reportFormlistofInst.value.addressStateCode?this.reportFormlistofInst.value.addressStateCode:this.stateCodeDeafult;
    let universityType=this.reportFormlistofInst.value.universityType.id
    if(event && obj){
      this.UniversityService.getfindUniversityDataSate(event,obj).subscribe((res) => {
        this.UniversityNameData = res;
        this.UniversityNameData.splice(0,0,this.allUniversity);
        this.filteredUniversityNameData=this.UniversityNameData.slice()
      })
    }
    if(universityType){
      this.UniversityService.getfindUniversityData1(event,obj,universityType).subscribe((res) => {
        this.UniversityNameData = res;
        this.UniversityNameData.splice(0,0,this.allUniversity);
        this.filteredUniversityNameData=this.UniversityNameData.slice();
        if (this.roleId === this.sharedservice.role['University']) {
          const selected = this.filteredUniversityNameData.find(
            (s: any) => s.id == this.splitAisheCode
          );

          if (selected) {
            this.reportFormlistofInst.patchValue({ universityName: selected });
            this.reportFormlistofInst.get('universityName')?.disable();
            this.reportFormlistofInst.get('universityType')?.disable();
          }
        }
      })
    }
    //console.log(this.reportFormlistofInst.value.surveyYear);
      }

      findManagmentTypeData(){
        this.reportService.getManagmentTypeData().subscribe((res)=>{
          this.managmentTypeData=res;
          this.managmentTypeData.splice(0,0,this.allManagementTypes)
          this.filtermanagmentTypeData=this.managmentTypeData.slice();

        });
      }

      findCollegeTypeData(){
        this.reportService.getCollegeTypeData().subscribe((res)=>{
this.collegeType=res;
this.collegeType.splice(0,0,this.allCollegeType1);
this.filterColleges=this.collegeType.slice();
        });
      }

      universityTypeData(){

        this.UniversityService.getUniversityTypes().subscribe((res)=>{

          this.universityType1=res;
          this.universityType1.splice(0,0,this.allUniversityType)
          this.filtereduniversityType=this.universityType1.slice()
        });
      }

      findBodyType(){
        this.reportService.getBodyType().subscribe((res)=>{
          this.dataBodyType=res;
          this.dataBodyType.splice(0,0,this.allBodyType),
          this.filteredBodyTypes=this.dataBodyType.slice();
        });
      }

  onReset(){
this.child?.onReset();
this.formControl();
//  if (!this.isAdminOrMoE && !this.userData) {
//       const selected = this.filterStatesOption.find(
//         (s: any) => s.stateCode == this.stateCodeDeafult
//       );
//       if (selected) {
//         this.reportFormlistofInst.patchValue({ addressStateCode: selected });
//         this.reportFormlistofInst.get('addressStateCode')?.disable();
      
//       }
//     }else{
//        this.child?.onReset();
//     }
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

  findCollegeName(){
    let universityNameId=this.reportFormlistofInst.value.universityName.id;
    let collegeType=this.reportFormlistofInst.value.collegeType.id

    let surveyYear=this.reportFormlistofInst.value.surveyYear;
    if(universityNameId){
      this.collegeName=[];
        this.collegeService.getCollege(this.reportFormlistofInst.value.universityName.id).subscribe((res)=>{
          this.collegeName=res;
          this.collegeName.splice(0,0,this.allCollegeName);
          this.filterCollegesName=this.collegeName.slice();
        })
      }
    if(universityNameId && collegeType){
        this.collegeName=[];
        this.collegeService.getcollegenamebyType(surveyYear,universityNameId,collegeType).subscribe((res)=>{
          this.collegeName=res;
          this.collegeName.splice(0,0,this.allCollegeName);
          this.filterCollegesName=this.collegeName.slice();
        })
       }
  }

  submit(){

    this.submitted=true;
    if(this.reportFormlistofInst.valid){
    this.child?.getReportDataTable();
   // this.generateFieldsTableForExcel();
  }
}
}
