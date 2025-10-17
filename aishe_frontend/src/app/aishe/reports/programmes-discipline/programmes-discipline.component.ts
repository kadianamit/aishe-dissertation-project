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
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-programmes-discipline',
  templateUrl: './programmes-discipline.component.html',
  styleUrls: ['./programmes-discipline.component.scss']
})
export class ProgrammesDisciplineComponent implements OnInit {
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
  allLevel={id:"ALL", name:"ALL"};
  filteredDistrict: any;
  reportFilterForm :  FormGroup|any;
  dataDistrict: any[] = [];
  alldistrictsName={districtName:"ALL",districtCode:"ALL"}
  @ViewChild("child") //to get child component reference
  child?:ReportChildComponent

  universityType1:any;
  filtereduniversityType:any[]=[]
  filteredUniversityNameData:any[]=[];
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
  DistrictsData:any=[];
  filteredDistrictsData:any[]=[];
  fieldsData:any=[];
  userData: string | null;
  isAdminOrMoE: any;
  stateCodeDeafult: any;
  roleId: any;
  splitAisheCode: any;
  aisheCode: string;

  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,private institutionmanagement: InstitutionmanagementService,
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
  private dialog: MatDialog,public localService: LocalserviceService,public sharedservice:SharedService) {
    this.userData= this.localService.getData('userData')
     this.roleId = this.localService.getData('roleId')
        console.log('787role',this.roleId)
         this.stateCodeDeafult = this.localService.getData('stateCode');
  
    console.log(' this.stateCodeDeafult', this.stateCodeDeafult);
      this.aisheCode=localService.getData('aisheCode')
      this.splitAisheCode=this.aisheCode.split('-')[1]
                  this.roleId = this.localService.getData('roleId')
        console.log('787role',this.roleId)
  
this.formControl();
  }


 ngOnInit(): void {
 this.isAdminOrMoE=(this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])
    // this.collegeName.splice(0,0,this.allCollegeName);
    // this.filterCollegesName=this.collegeName.slice();
    // this.institutions.splice(0,0,this.allNameWithId);
    // this.filterinstitutions=this.institutions.slice();
    // this.DisciplineName.splice(0,0,this.alldisciplinName);
    // this.filterDisciplineName=this.DisciplineName.slice()
this.findInstitutionNames();
    this.loadSurveyYear();
    this.loadStates()
    //this.loadDistrict()
    if(this.reportInfoObj.reportNumber=='Report 109A'
    ||this.reportInfoObj.reportNumber=='Report 109B'
    || this.reportInfoObj.reportNumber=='Report 110A'
    || this.reportInfoObj.reportNumber=='Report 110B'
    || this.reportInfoObj.reportNumber=='Report 110C'
    || this.reportInfoObj.reportNumber=='Report 121A'
    || this.reportInfoObj.reportNumber=='Report 121B'
    || this.reportInfoObj.reportNumber=='Report 120'
    || this.reportInfoObj.reportNumber=='Report 126A'
    || this.reportInfoObj.reportNumber=='Report 126B'
    || this.reportInfoObj.reportNumber=='Report 65'
    || this.reportInfoObj.reportNumber=='Report 150A'
    || this.reportInfoObj.reportNumber=='Report 150B'
    )
    {
      this.loadUniversityTypeData();

    }
    if(this.reportInfoObj.reportNumber=='Report 109B'
    || this.reportInfoObj.reportNumber=='Report 110B'
    || this.reportInfoObj.reportNumber=='Report 109C'
    || this.reportInfoObj.reportNumber=='Report 110C'
    || this.reportInfoObj.reportNumber=='Report 121B'
    || this.reportInfoObj.reportNumber=='Report 121C'
    || this.reportInfoObj.reportNumber=='Report 126B'
    || this.reportInfoObj.reportNumber=='Report 126C'
    || this.reportInfoObj.reportNumber=='Report 65'
    || this.reportInfoObj.reportNumber=='Report 150B'
    || this.reportInfoObj.reportNumber=='Report 150C'
    )
    {
      this.findManagmentTypeData();
      this.findCollegeTypeData();
    }
    // if(this.reportInfoObj.reportNumber=='Report 126B')
    //  {
    //   this.findCollegeName();
    //  }
    if(this.reportInfoObj.reportNumber=='Report 110'
    || this.reportInfoObj.reportNumber=='Report 110A'
    || this.reportInfoObj.reportNumber=='Report 110B'
    || this.reportInfoObj.reportNumber=='Report 110C'
    || this.reportInfoObj.reportNumber=='Report 120'
    || this.reportInfoObj.reportNumber=='Report 126'
    || this.reportInfoObj.reportNumber=='Report 126A'
    || this.reportInfoObj.reportNumber=='Report 126B'
    || this.reportInfoObj.reportNumber=='Report 126C'
    || this.reportInfoObj.reportNumber=='Report 109'
    )
    {
      this.findDisciplineGroup();
      this.findCourseLevel();
    }
    if(this.reportInfoObj.reportNumber=='Report 109C'
    || this.reportInfoObj.reportNumber=='Report 110C'
    || this.reportInfoObj.reportNumber=='Report 121C'
    || this.reportInfoObj.reportNumber=='Report 126C'
    || this.reportInfoObj.reportNumber=='Report 38C'
    || this.reportInfoObj.reportNumber=='Report 65'
    || this.reportInfoObj.reportNumber=='Report 150C'
    )
    {
    this.findBodyType();
    }
    if(this.reportInfoObj.reportNumber=='Report 65'
    )
    {
      this.findUniversityByYearAndState();
      }
  // this.setAddressStateCodeControlState();

  }
  // setAddressStateCodeControlState(): void {
  //   if (this.isAdminOrMoE) {
  //     this.reportFilterForm.get('addressStateCode')?.enable();
  //   } else {
  //     this.reportFilterForm.get('addressStateCode')?.disable();
  //   }
  // }

  // getdistrictName() {
    
  //   if(this.reportFilterForm.value.addressStateCode){
  //   this.institutionmanagement
  //     .getdistrict(this.reportFilterForm.value.addressStateCode.stateCode)
  //     .subscribe((res) => {
  //       this.dataDistrict = res;
  //       this.filteredDistrict = this.dataDistrict.slice();
  //     });
  //   }
  // }
  finddistectdata(){
    this.reportService.getDistrictsData(this.reportFilterForm.value.addressStateCode).subscribe((res)=>{
this.DistrictsData=res;
this.DistrictsData.splice(0,0,this.alldistrictsName)
this.filteredDistrictsData=this.DistrictsData.slice();
    })
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
      institution:['ALL'],
      districtCode:['ALL'],
      districtsName:['ALL'],
    })
  }
  get f(): { [key: string]: AbstractControl } {
    return this.reportFilterForm.controls
  }

  loadStates() {

    let countryCode = utility.getLoginlocalStorageUserData().country
   // console.log("countryCode ", countryCode);

    this.countrystatecityService.getstate(countryCode).subscribe((res) => {

      this.filterStatesOption = res
     this.filterStatesOption.unshift(this.allStates); // optional "All States"

    this.filterdStates = [...this.filterStatesOption];

    // ✅ Only apply default & disable when NOT SysAdmin
    // if (!this.isAdminOrMoE && !this.userData) {
    //   const selected = this.filterStatesOption.find(
    //     (s: any) => s.stateCode == this.stateCodeDeafult
    //   );
    //   if (selected) {
    //     this.reportFilterForm.patchValue({ addressStateCode: selected });
    //     this.reportFilterForm.get('addressStateCode')?.disable();
       
    //   }
    // }
  });

  }

  loadSurveyYear(){
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
    
    this.UniversityNameData=[];
   this.filteredUniversityNameData=[];
    let surveyYear=this.reportFilterForm.value.surveyYear;
    let stateObj=this.reportFilterForm.value.addressStateCode?this.reportFilterForm.value.addressStateCode:this.stateCodeDeafult;
    // let universityType=this.reportFilterForm.value.universityType.id;
    let universityType=this.reportFilterForm.value.universityType==null?"ALL":this.reportFilterForm.value.universityType.id;

   
    
// let arrey =this.filteredUniversityNameData.filter((el:any)=>el.id===this.reportFilterForm.value.universityName.id);
// if(arrey&& arrey.length){
// this.reportFilterForm.get('universityName')?.setValue(this.allUniversity)
// }

    // if(surveyYear && stateObj && universityType){
    if(universityType){

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
        this.collegeService.getcollegenamebyType(surveyYear,universityNameId,collegeType).subscribe((res)=>{
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
    if(this.reportFilterForm.value.addressStateCode){
      this.institutionService.getInstitutes(this.reportFilterForm.value.addressStateCode.stateCode).subscribe((res)=>{
      this.institutions=res;
      this.institutions.splice(0,0,this.allNameWithId);
      this.filterinstitutions=this.institutions.slice();
      });
    }
    else if(this.reportFilterForm.value.bodyType){
      this.institutionService.getInstByStateBodyId(this.reportFilterForm.value.bodyType.id).subscribe((res)=>{
      this.institutions=res;
      this.institutions.splice(0,0,this.allNameWithId);
      this.filterinstitutions=this.institutions.slice();

      });
    }
    if(this.reportFilterForm.value.bodyType && this.reportFilterForm.value.addressStateCode){

      this.institutionService.getInstbyStateCodeAndBody(surveyYear, this.reportFilterForm.value.addressStateCode.stateCode, this.reportFilterForm.value.bodyType.id).subscribe((res)=>{
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

}
