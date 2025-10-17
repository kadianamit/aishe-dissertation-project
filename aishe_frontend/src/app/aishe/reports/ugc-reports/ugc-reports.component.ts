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
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-ugc-reports',
  templateUrl: './ugc-reports.component.html',
  styleUrls: ['./ugc-reports.component.scss']
})
export class UgcReportsComponent implements OnInit {
  submitted:boolean=false
  isDataLoading:boolean=false
  dataTableToggle:boolean=false
  Showdata12: boolean = false
  showPdfButton:any|false|boolean;
     userData: string | null;
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
  allPosts={designation:"ALL",id:"ALL"}
  allManagementTypes={managementType:"ALL",id:"ALL"}
  alldisciplinGroup={disciplinGrouCategory:"ALL",id:"ALL"}
  alldisciplinName={disciplineGroup:"ALL",id:"ALL"}
  allUniversityType={type:"ALL",id:"ALL"}
  allColleges={id:'ALL',name:'ALL'}
  allnonTeacherStaffType={staffType:"ALL",id:"ALL"}
  allTypeWithId={type:"ALL",id:"ALL"}
  allNameWithId={name:"ALL",id:"ALL"}
 nonTeacherStaffTypeGroup:any
  filterNonTeacherStaffTypeGroup:any
  allNonTeacherStaffTypeGroup={id:"ALL",staffGroup:"ALL"};
  allCollegeName={ name:"ALL",id:"ALL"};
  allLevel={ name:"ALL",id:"ALL"};
  reportFilterForm :  FormGroup|any;
  inputObj :any= {};
  courseMode:Array<any> = [{id:'ALL', value:'ALL'},{id:'1', value:'GENERAL'},{id:'2', value:'SELF-FINANCING'}]
  studyMode:Array<any> =[{id:'ALL', value:'ALL'},{id:'1', value:'REGULAR'},{id:'2', value:'DISTANCE'}]
  socialCategory:Array<any> =[{id:'ALL', value:'ALL'},{id:'1', value:'SC'},{id:'2', value:'ST'},{id:'3', value:'OBC'}]
  religiousCategory:Array<any> =[{id:'ALL', value:'ALL'},{id:'1', value:'Muslim'},{id:'2', value:'OTHER MINORITY'},{id:'3', value:'PWD'}]

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

  fieldsData:any=[];
  stateCodeDeafult: any;
  isAdminOrMoE: any;
  roleId: string;

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
  private excelService:ExcelService,public localService: LocalserviceService,
  public sharedservice:SharedService,
  private dialog: MatDialog) {

    this.formControl();
     this.roleId = this.localService.getData('roleId')
this.userData= this.localService.getData('userData')
    this.stateCodeDeafult = this.localService.getData('stateCode');
  }


 ngOnInit(): void {
 this.isAdminOrMoE = (this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])

    this.loadSurveyYear();
    this.loadStates()
    this.loadUniversityTypeData();
    this.findManagmentTypeData();
     this.findCollegeTypeData();
     this.findDisciplineGroup();
     this.findCollegeName();
     this.findCourseLevel();
     this.findBodyType();

  }

  formControl(){
    this.reportFilterForm = this.fb.group({
      surveyYear: ['',Validators.required],
      addressStateCode:[this.allStates],
      addressDistrictCode:[this.allDistrict],
      universityName:[this.allUniversity],
      universityType:[this.allUniversityType],
      socialCategory:[this.allTypeWithId.id],
      studyMode:[this.allTypeWithId.id],
      religiousCategory:[this.allTypeWithId.id],
      courseMode:[this.allTypeWithId.id],
      managementType:[this.allManagementTypes],
      collegeType:[this.allCollegeType],
      collegeName:[this.allCollegeName],
      level:[this.allLevel],
      disciplineGroup:[this.alldisciplinGroup],
      disciplineName:[this.alldisciplinName],
      bodyType:[this.allBodyType],
      institution:[this.allNameWithId.id]

    })
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
    let stateObj=this.reportFilterForm.value.addressStateCode;
    let universityType=this.reportFilterForm.value.universityType.id;

    // if(surveyYear && stateObj && !universityType)
    //   {
    //   this.UniversityService.getfindUniversityDataSate(surveyYear,stateObj).subscribe((res) => {
    //     this.UniversityNameData = res;
    //     this.UniversityNameData.splice(0,0,this.allUniversity);
    //     this.filteredUniversityNameData=this.UniversityNameData.slice()
    //   });
    // }
     if(surveyYear && stateObj && universityType){
      this.UniversityService.getfindUniversityData1(surveyYear,stateObj,universityType).subscribe((res) => {
        this.UniversityNameData = res;
        this.UniversityNameData.splice(0,0,this.allUniversity);
        this.filteredUniversityNameData=this.UniversityNameData.slice()
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

  findCollegeName(){
    let surveyYear=this.reportFilterForm.value.surveyYear;
    let universityNameId=this.reportFilterForm.value.universityName.id;
    let collegeType=this.reportFilterForm.value.collegeType.id

    if(universityNameId){
      this.collegeName=[];
        this.collegeService.getCollege(this.reportFilterForm.value.universityName.id).subscribe((res)=>{
          this.collegeName=res;
          this.collegeName.splice(0,0,this.allCollegeName);
          this.filterCollegesName=this.collegeName.slice();
        })
      }
    if(universityNameId && collegeType){
        this.collegeName=[];
        this.collegeService.getcollegenamebyType(surveyYear, universityNameId,collegeType).subscribe((res)=>{
          this.collegeName=res;
          this.collegeName.splice(0,0,this.allCollegeName);
          this.filterCollegesName=this.collegeName.slice();
        })
       }
  }

  findCourseLevel(){
    this.courseLevel.getLevelData().subscribe((res)=>{
      this.userCourseLevel=res;
      this.userCourseLevel.splice(0,0,this.allLevel);
      this.filterCourseLevel=this.userCourseLevel.slice();
      console.log(res)
    });
  }

  findDisciplineGroup(){
    this.DisciplineService.getDisciplineGroup().subscribe((res)=>{
    this.DisciplineGroup=res;
    this.DisciplineGroup.splice(0,0,this.alldisciplinGroup);
    this.filteredDisciplineGroup=this.DisciplineGroup.slice()

    });
  }

  findDisciplineName(){

    this.DisciplineService.getDisciplineName(this.reportFilterForm.value.disciplineGroup.id).subscribe((res)=>{
      this.DisciplineName=res;
      this.DisciplineName.splice(0,0,this.alldisciplinName);
      this.filterDisciplineName=this.DisciplineName.slice()

      });
  }

  findBodyType(){
    this.reportService.getBodyType().subscribe((res)=>{
      this.dataBodyType=res;
      this.dataBodyType.splice(0,0,this.allBodyType),
      this.filteredBodyTypes=this.dataBodyType.slice();
    });
  }

  findInstitutionNames(){
    let surveyYear=this.reportFilterForm.value.surveyYear;
    // if(this.reportFilterForm.value.addressStateCode){
    //   this.institutionService.getInstitutes(this.reportFilterForm.value.addressStateCode.stateCode).subscribe((res)=>{
    //   this.institutions=res;
    //   this.institutions.splice(0,0,this.allNameWithId);
    //   this.filterinstitutions=this.institutions.slice();
    //   });
    // }
    // else if(this.reportFilterForm.value.bodyType){
    //   this.institutionService.getInstByStateBodyId(this.reportFilterForm.value.bodyType.id).subscribe((res)=>{
    //   this.institutions=res;
    //   this.institutions.splice(0,0,this.allNameWithId);
    //   this.filterinstitutions=this.institutions.slice();

    //   });
    // }
     if(this.reportFilterForm.value.bodyType && this.reportFilterForm.value.addressStateCode){

      this.institutionService.getInstbyStateCodeAndBody(surveyYear,this.reportFilterForm.value.addressStateCode.stateCode, this.reportFilterForm.value.bodyType.id).subscribe((res)=>{
        this.institutions=res;
        this.institutions.splice(0,0,this.allNameWithId);
        this.filterinstitutions=this.institutions.slice();
      });
    }
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

  courseChange(x:any, data:any , type:string){
    // debugger
    if(type === 'courseMode'){
    let ModeName = data.filter((coureModeName:any)=>coureModeName.id == x).map((x:any)=>x.value)[0];
    return this.inputObj['courseModeName'] = ModeName;
  }else if(type==='studyMode'){
    let ModeName = data.filter((studyModeName:any)=>studyModeName.id == x).map((x:any)=>x.value)[0];
   return this.inputObj['studyModeName'] = ModeName;
  }
  else if(type==='socialCategory'){
    let ModeName = data.filter((socialCategory:any)=>socialCategory.id == x).map((x:any)=>x.value)[0];
   return this.inputObj['socialCategory'] = ModeName;
  }
  else if(type==='religiousCategory'){
    let ModeName = data.filter((religiousCategory:any)=>religiousCategory.id == x).map((x:any)=>x.value)[0];
   return this.inputObj['religiousCategory'] = ModeName;
  }

  }

}
