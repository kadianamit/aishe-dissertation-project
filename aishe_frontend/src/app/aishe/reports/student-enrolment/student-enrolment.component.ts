import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, AbstractControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { LevelService } from 'src/app/service/get/level.service';
import { CollegeService } from 'src/app/service/reports/college.service';
import { CountrystatecityService } from 'src/app/service/reports/countrystatecity.service';
import { ExcelService } from 'src/app/service/reports/excel.service';
import { InstitutionService } from 'src/app/service/reports/institution.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { UniversityService } from 'src/app/service/reports/university.service';
import { DisciplineService } from 'src/app/service/get/discipline.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-student-enrolment',
  templateUrl: './student-enrolment.component.html',
  styleUrls: ['./student-enrolment.component.scss']
})
export class StudentEnrolmentComponent implements OnInit {
  submitted:boolean=false;
  isDataLoading:boolean=false
  dataTableToggle:boolean=false
  Showdata12: boolean = false
  showPdfButton:any|false|boolean
  surveyYearOption:any
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
  filterColleges:any=[]
  filterStandaloneOption:any
  filterInstitutes:any
  designationData: any
  filterDesignation:any
  bodyTypes:any
  nonTeachingStaff:any=[]
  filteredBodyTypes:any
  nonTeacherStaffTypeData:any
  filteredNonTeacherStaffTypeData:any
  UniversityNameData:any=[]
  allStates={stateName:"ALL",stateCode:"ALL"}
  allCollegeName={ name:"ALL",id:"ALL"}
  allUniversity={name:"ALL",id:"ALL"}
  allBodyType={type:"ALL",id:"ALL"}
  allCollegeType={type:"ALL",id:"ALL"}
  allPosts={designation:"ALL",id:"ALL"}
  allManagementTypes={managementType:"ALL",id:"ALL"}
  alldisciplinGroup={disciplinGrouCategory:"ALL",id:"ALL"}
  alldisciplinName={disciplineGroup:"ALL",id:"ALL"}
  allUniversityType={type:"ALL",id:"ALL"}
  allColleges={id:'ALL',name:'ALL'}
  allinstitution={id:'ALL',name:'ALL'}
  allnonTeacherStaffType={staffType:"ALL",id:"ALL"}
  allTypeWithId={type:"ALL",id:"ALL"}
 nonTeacherStaffTypeGroup:any
  filterNonTeacherStaffTypeGroup:any
  allNonTeacherStaffTypeGroup={id:"ALL", staffGroup:"ALL"}
  allLeve={id:"ALL", name:"ALL"}
  allprogramme={id:"ALL", programme:"ALL"}
  alldisciplinNamepor={id:"ALL", disciplineGroup:"ALL"}

  reportFilterForm = new FormGroup({
    surveyYear: new FormControl(''),
    addressStateCode: new FormControl(''),
    addressDistrictCode: new FormControl(''),
    universityType: new FormControl(''),
    universityName:new FormControl(''),
    religiousCategoryPWD: new FormControl(''),
    socialCategory: new FormControl(''),
    studyMode: new FormControl(''),
    courseMode:new FormControl(''),
    disciplineGroup:new FormControl(''),
    disciplineName: new FormControl(''),
    bodyType:new FormControl(''),
    managementType:new FormControl(''),
    institutionName:new FormControl(' '),
    level: new FormControl(''),
    programme: new FormControl(''),
    collegeType:new FormControl(''),
    collegeName:new FormControl(''),
    institution:new FormControl(''),
  })

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
  filterCollegesName:any=[];
  userCourseLevel: any=[];
  filterCourseLevel:any;
  programmeData:any=[];
  filterProgramme:any;
  filteredDisciplineGrouppro12:any
  DisciplineGrouppro:any=[]
  filteredDisciplineNamepro:any;
  DisciplineNamepro:any=[];

  fieldsData:any=[];
  institutions: any;
  filterinstitutions: any;
  userData: string | null;
  isAdminOrMoE: any;
  roleId: any;
  stateCodeDeafult: any;
  aisheCode: string;
  splitAisheCode: string;

  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,
  private surveyyearservice:SurveyyearService,
  private fb:FormBuilder,
  public countrystatecityService: CountrystatecityService,
  private UniversityService:UniversityService,
  private collegeService:CollegeService,
  private institutionService:InstitutionService,
  private reportService:ReportService,
  private DisciplineService:DisciplineService,
  private excelService:ExcelService,
  private dialog: MatDialog,
  private courseLevel:LevelService,public localService: LocalserviceService,public sharedservice:SharedService) {
    this.formControl();
    this.userData= this.localService.getData('userData');
     this.roleId = this.localService.getData('roleId')
        console.log('787role',this.roleId)
         this.stateCodeDeafult = this.localService.getData('stateCode');
  
    console.log(' this.stateCodeDeafult', this.stateCodeDeafult);
    this.aisheCode=localService.getData('aisheCode')
                
                 this.splitAisheCode=this.aisheCode.split('-')[1]
                
  }

  ngOnInit(): void {
 this.isAdminOrMoE=(this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])

this.findInstitutionNames();
    this.loadSurveyYear()
    this.loadStates()
    this.loadStaffType()
    this.universityTypeData();
    this.findDisciplineGroup();
    this.findBodyType();
    this.findManagmentTypeData();
    this.findCollegeTypeData();
    this.findCourseLevel();
// this.setAddressStateCodeControlState();

  }
  // setAddressStateCodeControlState(): void {
  //   if (this.isAdminOrMoE) {
  //     this.reportFilterForm.get('addressStateCode')?.enable();
  //   } else {
  //     this.reportFilterForm.get('addressStateCode')?.disable();
  //   }
  // }
  formControl(){
    this.reportFilterForm = this.fb.group({
      surveyYear: ['',Validators.required],
      addressStateCode:[this.allStates],
      addressDistrictCode:['ALL'],
      universityCode:['ALL'],
      collegeName:[this.allCollegeName],
      institutionName:['ALL'],
      designation:['ALL'],
      selectionMode:['ALL'],
      managementType:[this.allManagementTypes],
      managementName:['ALL'],
      collegeType:[this.allCollegeType],
      bodyType:[this.allBodyType],
      universityName:[this.allUniversity],
      programme:[this.allprogramme],
      institutionType:[''],
      staffType:[''],
      staffGroup:[''],
      level:[this.allLeve],
      institution:['ALL'],
      universityType:[this.allUniversityType],
      socialCategory:[this.allTypeWithId.id],
      studyMode:[this.allTypeWithId.id],
      religiousCategory:[this.allTypeWithId.id],
      courseMode:[this.allTypeWithId.id],
      disciplineGroup:[this.alldisciplinGroup],
      disciplineName:[this.alldisciplinName ],

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

  loadStaffType(){


    this.reportService.getNonTeacherStaffType().subscribe((res)=>{
      this.nonTeacherStaffTypeData=res
      this.nonTeacherStaffTypeData.splice(0,0,this.allnonTeacherStaffType)

      this.filteredNonTeacherStaffTypeData = this.nonTeacherStaffTypeData.slice()

    })

  }
  findInstitutionNames(){

    let event=this.reportFilterForm.value.surveyYear;
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
    else if(this.reportFilterForm.value.bodyType && this.reportFilterForm.value.addressStateCode){

      this.institutionService.getInstbyStateCodeAndBody(event, this.reportFilterForm.value.addressStateCode.stateCode, this.reportFilterForm.value.bodyType.id).subscribe((res)=>{
        this.institutions=res;
        this.institutions.splice(0,0,this.allNameWithId);
        this.filterinstitutions=this.institutions.slice();
      });
    }
  }
  allNameWithId(arg0: number, arg1: number, allNameWithId: any) {
    throw new Error('Method not implemented.');
  }

  universityTypeData(){
    this.UniversityService.getUniversityTypes().subscribe((res)=>{

      this.universityType1=res;
      this.universityType1.splice(0,0,this.allUniversityType)
      this.filtereduniversityType=this.universityType1.slice()
    });
  }

  findUniversityByYearAndState(){

    let event=this.reportFilterForm.value.surveyYear;
    let obj=this.reportFilterForm.value.addressStateCode?this.reportFilterForm.value.addressStateCode:this.stateCodeDeafult;
    let universityType=this.reportFilterForm.value.universityType==null?"ALL":this.reportFilterForm.value.universityType.id;
    // if(event && obj){
    //   this.UniversityService.getfindUniversityDataSate(event,obj).subscribe((res) => {
    //     this.UniversityNameData = res;
    //     this.UniversityNameData.splice(0,0,this.allUniversity);
    //     this.filteredUniversityNameData=this.UniversityNameData.slice()
    //   })
    // }
  if (universityType) {
  this.UniversityService.getfindUniversityData1(event, obj, universityType).subscribe((res) => {
    this.UniversityNameData = res;
    this.UniversityNameData.splice(0, 0, this.allUniversity);
    this.filteredUniversityNameData = this.UniversityNameData.slice();

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

   
    //console.log(this.reportFilterForm.value.surveyYear);
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
        let universityNameId=this.reportFilterForm.value.universityName.id;
        let collegeType=this.reportFilterForm.value.collegeType.id
        let surveyYear=this.reportFilterForm.value.surveyYear;
        if(universityNameId){
          this.collegeName=[];
            this.collegeService.getCollege(this.reportFilterForm.value.universityName.id).subscribe((res)=>{
              this.collegeName=res;
              this.collegeName.splice(0,0,this.allCollegeName);
              this.filterCollegesName=this.collegeName.slice();
            })
          }
           if(collegeType){
            this.collegeName=[];
            this.collegeService.getcollegenamebyType(surveyYear,universityNameId,collegeType).subscribe((res)=>{
              this.collegeName=res;
              this.collegeName.splice(0,0,this.allCollegeName);
              this.filterCollegesName=this.collegeName.slice();
            })
           }
      }

      findCourseLevel(){
        this.filteredDisciplineNamepro=[];
        this.filterProgramme=[];
        this.filteredDisciplineGrouppro12=[]
        this.courseLevel.getLevelData().subscribe((res)=>{
          this.userCourseLevel=res;
          this.userCourseLevel.splice(0,0,this.allLeve);
          this.filterCourseLevel=this.userCourseLevel.slice();

        });
      }

      findProgrammData(){
        this.filteredDisciplineNamepro=[];
        this.filteredDisciplineGrouppro12=[];
        this.filterProgramme=[]
            this.courseLevel.getprogrammeData(this.reportFilterForm.value.level.id).subscribe((res)=>{
              this.programmeData=res;
              this.programmeData.splice(0,0,this.allprogramme);
              this.filterProgramme=this.programmeData.slice();
            });

      }

      findDisciplineGroupByProgramme12(){

        this.filteredDisciplineNamepro=[];
        this.filteredDisciplineGrouppro12=[];
let programmeId=this.reportFilterForm.value.programme.id;

        this.courseLevel.getDisciplineGroupByProgrammeData(programmeId).subscribe((res)=>{
          this.DisciplineGrouppro=res;
          this.DisciplineGrouppro.splice(0,0,this.alldisciplinGroup)
          this.filteredDisciplineGrouppro12=this.DisciplineGrouppro.slice()


        });
      }
      findDisciplineNameByGroup(){
        this.filteredDisciplineNamepro=[];
        let programmeId=this.reportFilterForm.value.programme.id;
        let groupName=this.reportFilterForm.value.disciplineGroup.id;
        this.courseLevel.getDisciplineNameByGroup(programmeId,groupName).subscribe((res)=>{
          this.DisciplineNamepro=res;
          this.DisciplineNamepro.splice(0,0,this.alldisciplinNamepor)
          this.filteredDisciplineNamepro=this.DisciplineNamepro.slice()

        });
      }

  onReset(): void {
    this.dataTableToggle = false
    this.Showdata12=false
    this.child?.onReset();
    this.formControl();
//  if (!this.isAdminOrMoE && !this.userData) {
//       const selected = this.filterStatesOption.find(
//         (s: any) => s.stateCode == this.stateCodeDeafult
//       );
//       if (selected) {
//         this.reportFilterForm.patchValue({ addressStateCode: selected });
//         this.reportFilterForm.get('addressStateCode')?.disable();
      
//       }
//     }else{
//        this.child?.onReset();
//     }
    }
    doNothing(){
      return
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
    //  this.generateFieldsTableForExcel();
      this.child?.showPdfButton
    }
  }

}
