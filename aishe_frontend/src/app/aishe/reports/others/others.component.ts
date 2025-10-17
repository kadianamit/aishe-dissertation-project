import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, AbstractControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { CollegeService } from 'src/app/service/reports/college.service';
import { CountrystatecityService } from 'src/app/service/reports/countrystatecity.service';
import { ExcelService } from 'src/app/service/reports/excel.service';
import { LevelService } from 'src/app/service/get/level.service';
import { InstitutionService } from 'src/app/service/reports/institution.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { DisciplineService } from 'src/app/service/get/discipline.service';
import { UniversityService } from 'src/app/service/reports/university.service'
import { SharedService } from 'src/app/shared/shared.service';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'app-others',
  templateUrl: './others.component.html',
  styleUrls: ['./others.component.scss']
})
export class OthersComponent implements OnInit {
  submitted:boolean=false
  isDataLoading:boolean=false
  dataTableToggle:boolean=false
  Showdata12: boolean = false
  showPdfButton:any|false|boolean
  surveyYearOption:any
  districtOption:any
  filterSurveyYearOption:any
  filterStatesOption:any
  filterdStates:any //for text based search
  filterDistrictsOption:any
  filterDistricts:any
  filterUniversityOption:any
  filterUniversities:any
  filterUniversityTypes:any
  filteredUniversityTypes:any
  filterCollegesOption:any
  filterColleges:any
  institutions:any=[]
  filterinstitutions:any
  designationData: any
  filterDesignation:any
  bodyTypes:any
  nonTeachingStaff:any=[]
  filteredBodyTypes:any
  nonTeacherStaffTypeData:any
  filteredNonTeacherStaffTypeData:any
  UniversityNameData:any=[]
  allStates={stateName:"ALL",stateCode:"ALL"}
  allDistrict={DistrictName:"ALL",districtCode:"ALL"}
  allUniversity={name:"ALL",id:"ALL"}
  allBodyType={type:"ALL",id:"ALL"}
  allCollegeType={type:"ALL",id:"ALL"}
  //allNameWithId={designation:"ALL",id:"ALL"}
  allManagementTypes={managementType:"ALL",id:"ALL"}
  allUniversityType={type:"ALL",id:"ALL"}

  reportFilterForm :  FormGroup|any;

  @ViewChild("child") //to get child component reference
  child?:ReportChildComponent

  universityType1:any;
  filtereduniversityType:any
  filteredUniversityNameData:any;
  filteredDisciplineGroup:any;
  DisciplineGroup:any=[]
  DisciplineName:any=[]
  filterDisciplineName:any;
  dataBodyType:any=[];
  managmentTypeData:any=[];
  filtermanagmentTypeData:any=[];
  collegeType:any;
  collegeName:any=[];
  filterCollegesName:any;
  userCourseLevel:any=[];
  filterCourseLevel:any;
  userData: string | null;
  fieldsData:any=[];
  roleId: any;
isAdminOrMoE:any
  stateCodeDeafult: any;
  aisheCode:any;
  splitAisheCode:any;
  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,
  private surveyyearservice:SurveyyearService,
  private fb:FormBuilder,
  public countrystatecityService: CountrystatecityService,
  private UniversityService:UniversityService,
  private reportService:ReportService,
  private excelService:ExcelService,public localService: LocalserviceService,
public sharedservice:SharedService) {


 this.roleId = this.localService.getData('roleId')
this.userData= this.localService.getData('userData')
    this.stateCodeDeafult = this.localService.getData('stateCode');
    console.log(this.stateCodeDeafult)
     this.aisheCode = localService.getData('aisheCode')

    this.splitAisheCode = this.aisheCode.split('-')[1];
    this.formControl();
  }


 ngOnInit(): void {
      this.isAdminOrMoE = (this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])

    this.loadSurveyYear();
    this.loadStates()
    this.loadUniversityTypeData();
    this.findManagmentTypeData();
      this.findCollegeTypeData();
      this.findBodyType();

  }

  formControl(){
    this.reportFilterForm = this.fb.group({
      surveyYear: ['',Validators.required],
      addressStateCode:[this.allStates],
      addressDistrictCode:[this.allDistrict],
      universityName:[this.allUniversity],
      universityType:[this.allUniversityType],
      managementType:[this.allManagementTypes],
      collegeType:[this.allCollegeType],
      bodyType:[this.allBodyType],
      institution:['ALL'],
      constitutedFromCollege:[]

    });

     if (this.roleId == this.sharedservice.role['University']) {
      this.reportFilterForm.get('institution')?.setValue('College');
      this.reportFilterForm.get('institution')?.disable();
    }
  }

  get f(): { [key: string]: AbstractControl } {
    return this.reportFilterForm.controls
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
  //       this.reportFilterForm.patchValue({ addressStateCode: selected });
  //       this.reportFilterForm.get('addressStateCode')?.disable();
      
  //     }
  //   }
  });

  }

  loadSurveyYear(){
    this.surveyyearservice.getdatasurveyyear().subscribe((res)=>{
      this.surveyYearOption=res
    //   this.filterSurveyYearOption=  this.surveyYearOption.slice()
    // })
     if(this.userData==="true"){
        let dataY= res.slice(2)
         this.surveyYearOption=dataY
         this.filterSurveyYearOption=  this.surveyYearOption
       }else{
       this.filterSurveyYearOption=  this.surveyYearOption.slice()
       }
    })
  }


  loadUniversityTypeData(){
    this.UniversityService.getUniversityTypes().subscribe((res)=>{

      this.universityType1=res;
      this.universityType1.splice(0,0,this.allUniversityType)
      this.filtereduniversityType=this.universityType1.slice()
    });
  }

  //call this method on change of survey Year or State or University type field
  //for report 109A
  findUniversityByYearAndState(){

    let surveyYear=this.reportFilterForm.value.surveyYear;
    let stateObj=this.reportFilterForm.value.addressStateCode?this.reportFilterForm.value.addressStateCode:this.stateCodeDeafult;
    let universityType=this.reportFilterForm.value.universityType.id;

    if(surveyYear && stateObj)
      {
      this.UniversityService.getfindUniversityDataSate(surveyYear,stateObj).subscribe((res) => {
        this.UniversityNameData = res;
        this.UniversityNameData.splice(0,0,this.allUniversity);
        this.filteredUniversityNameData=this.UniversityNameData.slice()
        
      });
    }
     if(surveyYear && stateObj && universityType){
      this.UniversityService.getfindUniversityData1(surveyYear,stateObj,universityType).subscribe((res) => {
        this.UniversityNameData = res;
        this.UniversityNameData.splice(0,0,this.allUniversity);
        this.filteredUniversityNameData=this.UniversityNameData.slice();
         if (this.roleId === this.sharedservice.role['University']) {
          const selected = this.filteredUniversityNameData.find(
            (s: any) => s.id == this.splitAisheCode
          );

          if (selected) {
            this.reportFilterForm.patchValue({ universityName: selected });
            this.reportFilterForm.get('universityName')?.disable();
            this.reportFilterForm.get('universityType')?.disable();
          }
        }
      });
    }
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
this.collegeType.splice(0,0,this.allCollegeType);
this.filterColleges=this.collegeType.slice();
    });
  }

  findBodyType(){
    this.reportService.getBodyType().subscribe((res)=>{
      this.dataBodyType=res;
      this.dataBodyType.splice(0,0,this.allBodyType),
      this.filteredBodyTypes=this.dataBodyType.slice();
    });
  }

  onReset(): void {
    this.dataTableToggle = false;
    this.isDataLoading=false;
    this.Showdata12=false;
    this.child?.onReset();
    this.formControl();
    //  if (!this.isAdminOrMoE && !this.userData) {
    //   const selected = this.filterStatesOption.find(
    //     (s: any) => s.stateCode == this.stateCodeDeafult
    //   );
    //   if (selected) {
    //     this.reportFilterForm.patchValue({ addressStateCode: selected });
    //     this.reportFilterForm.get('addressStateCode')?.disable();
      
    //   }
    // }else{
    //    this.child?.onReset();
    // }

    }
    doNothing(){
      // alert("nothing");
      return
    }
// to export excel file from
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

}
