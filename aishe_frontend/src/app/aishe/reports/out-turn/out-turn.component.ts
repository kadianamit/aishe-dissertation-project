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
import {UniversityService}  from 'src/app/service/reports/university.service';
import { DisciplineService } from 'src/app/service/get/discipline.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';


@Component({
  selector: 'app-out-turn',
  templateUrl: './out-turn.component.html',
  styleUrls: ['./out-turn.component.scss']
})
export class OutTurnComponent implements OnInit {

  @ViewChild("child") //to get child component reference
  child?:ReportChildComponent
  programmeData:any=[];
  filterProgramme:any;
  submitted:boolean=false;
  isDataLoading:boolean=false;
    dataTableToggle:boolean=false;
  Showdata12: boolean = false;
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
  fieldsData:any=[];
  userCourseLevel: any;
  filterCourseLevel: any;
  filtereduniversityType:any=[];
  filteredUniversityNameData:any=[]
  filterCollegesName:any=[];
  allStates={stateName:"ALL",stateCode:"ALL"}
  allCollegeType1={ type:"ALL",id:"ALL"}
  allUniversity={name:"ALL",id:"ALL"}
  allBodyType={type:"ALL",id:"ALL"}
  
  allManagementTypes={managementType:"ALL",id:"ALL"}
  alldisciplinGroup={disciplinGrouCategory:"ALL",id:"ALL"}
  alldisciplinName={disciplineGroup:"ALL",id:"ALL"}
  allUniversityType={type:"ALL",id:"ALL"}
  allColleges={id:'ALL',name:'ALL'}
  allLevel={id:"ALL", name:"ALL"};
  allprogramme={id:"ALL", programme:"ALL"}
  allNameWithId={name:"ALL",id:"ALL"}
  filteredBodyTypes:any=[];

  reportFormOutTurn = new FormGroup({
    institution: new FormControl(''),
    surveyYear: new FormControl(''),
    addressStateCode: new FormControl(''),
    universityName:new FormControl(''),
    collegeType: new FormControl(''),
    bodyType: new FormControl(''),
    universityType: new FormControl(''),
    managementType:new FormControl(''),
    programme: new FormControl(''),
  })
  DisciplineGrouppro: any;
  filteredDisciplineGrouppro12: any;
  DisciplineGroup: any;
  filteredDisciplineGroup: any;
  DisciplineNamepro: any;
  filteredDisciplineNamepro: any;
  userData: string | null;
  roleId: any;
  stateCodeDeafult: string;
  isAdminOrMoE: any;
  aisheCode: string;
  splitAisheCode: string;

  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,
  private surveyyearservice:SurveyyearService,
  private fb:FormBuilder,
  public countrystatecityService: CountrystatecityService,
  private collegeService:CollegeService,
   private institutionService:InstitutionService,
  private DisciplineService:DisciplineService,
  private reportService:ReportService,
  private excelService:ExcelService,
  private dialog: MatDialog,
  private courseLevel:LevelService, private UniversityService:UniversityService,public localService: LocalserviceService,public sharedservice: SharedService) {
    this.formControl();
    this.userData= this.localService.getData('userData')
     this.roleId = this.localService.getData('roleId')
        console.log('787role',this.roleId)
         this.stateCodeDeafult = this.localService.getData('stateCode');
  
    console.log(' this.stateCodeDeafult', this.stateCodeDeafult);
     this.aisheCode=localService.getData('aisheCode')
                
                 this.splitAisheCode=this.aisheCode.split('-')[1]
  }

  ngOnInit(): void {
 this.isAdminOrMoE=(this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])
    this.loadStates();
    this.loadSurveyYear()
    this.universityTypeData();
    this.findBodyType();
    this.findManagmentTypeData();
    this.findCollegeTypeData();
    this.findCourseLevel();



  }
  // setAddressStateCodeControlState(): void {
  //   if (this.isAdminOrMoE) {
  //     this.reportFormOutTurn.get('addressStateCode')?.enable();
  //   } else {
  //     this.reportFormOutTurn.get('addressStateCode')?.disable();
  //   }
  // }

  formControl(){
    this.reportFormOutTurn = this.fb.group({
      surveyYear: ['',Validators.required],
      institution:['ALL'],
      addressStateCode:[this.allStates],
      collegeType:[this.allCollegeType1],
      universityType:[this.allUniversityType],
      studyMode : ['ALL'],
      universityName:[this.allUniversity],
      managementType:[this.allManagementTypes],
      bodyType:[this.allBodyType],
      level:[this.allLevel],
      programme:[this.allprogramme],
    })
  }
  get f(): { [key: string]: AbstractControl } {
    return this.reportFormOutTurn.controls
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

  loadStates() {

    let countryCode = utility.getLoginlocalStorageUserData().country


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
    //     this.reportFormOutTurn.patchValue({ addressStateCode: selected });
    //     this.reportFormOutTurn.get('addressStateCode')?.disable();
       
    //   }
    // }
  });
  }

  findCourseLevel(){
    this.courseLevel.getLevelData().subscribe((res)=>{
      this.userCourseLevel=res;
      this.userCourseLevel.splice(0,0,this.allLevel);
      this.filterCourseLevel=this.userCourseLevel.slice();

    });
  }
  findProgrammData(){
    // this.filteredDisciplineNamepro=[];
    // this.filteredDisciplineGrouppro12=[];
    // this.filterProgramme=[]
        this.courseLevel.getprogrammeData(this.reportFormOutTurn.value.level.id).subscribe((res)=>{
          this.programmeData=res;
          this.programmeData.splice(0,0,this.allprogramme);
          this.filterProgramme=this.programmeData.slice();
        });

  }

  findDisciplineGroupByProgramme12(){

    // this.filteredDisciplineNamepro=[];
    // this.filteredDisciplineGrouppro12=[];
let programmeId=this.reportFormOutTurn.value.programme.id;

    this.courseLevel.getDisciplineGroupByProgrammeData(programmeId).subscribe((res)=>{
      this.DisciplineGrouppro=res;
      this.DisciplineGrouppro.splice(0,0,this.alldisciplinGroup)
      this.filteredDisciplineGrouppro12=this.DisciplineGrouppro.slice()


    });
  }

  findDisciplineGroup(){
    this.DisciplineService.getDisciplineGroup().subscribe((res)=>{
    this.DisciplineGroup=res;
    this.DisciplineGroup.splice(0,0,this.alldisciplinGroup);
    this.filteredDisciplineGroup=this.DisciplineGroup.slice()

    });
  }

  findDisciplineNameByGroup(){
    // this.filteredDisciplineNamepro=[];

    let programmeId=this.reportFormOutTurn.value.programme.id;
    let groupName=this.reportFormOutTurn.value.disciplineGroup.id;
    this.courseLevel.getDisciplineNameByGroup(programmeId,groupName).subscribe((res)=>{
      this.DisciplineNamepro=res;
      this.DisciplineNamepro.splice(0,0,this.alldisciplinNamepor)
      this.filteredDisciplineNamepro=this.DisciplineNamepro.slice()

    });
  }
  alldisciplinNamepor(arg0: number, arg1: number, alldisciplinNamepor: any) {
    throw new Error('Method not implemented.');
  }

  // allLevel(arg0: number, arg1: number, allLevel: any) {
  //   throw new Error('Method not implemented.');
  // }

  findUniversityByYearAndState(){

    let event=this.reportFormOutTurn.value.surveyYear;
    let obj=this.reportFormOutTurn.value.addressStateCode?this.reportFormOutTurn.value.addressStateCode:this.stateCodeDeafult;
    let universityType=this.reportFormOutTurn.value.universityType.id
    // if(event && obj){
    //   this.UniversityService.getfindUniversityDataSate(event,obj).subscribe((res) => {
    //     this.UniversityNameData = res;
    //     this.UniversityNameData.splice(0,0,this.allUniversity);
    //     this.filteredUniversityNameData=this.UniversityNameData.slice()
    //   })
    // }
    if(universityType){
      this.UniversityService.getfindUniversityData1(event,obj,universityType).subscribe((res) => {
        this.UniversityNameData = res;
        this.UniversityNameData.splice(0,0,this.allUniversity);
        this.filteredUniversityNameData=this.UniversityNameData.slice()


         if (this.roleId === this.sharedservice.role['University'] && this.reportInfoObj.reportNumber=='Report 53') {
      const selected = this.filteredUniversityNameData.find(
        (s: any) => s.id == this.splitAisheCode
      );
      
      if (selected) {
        this.reportFormOutTurn.patchValue({ universityName: selected });
        this.reportFormOutTurn.get('universityName')?.disable();
        this.reportFormOutTurn.get('universityType')?.disable();
      }
    }
      })
    }
    //console.log(this.reportFilterForm.value.surveyYear);
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

      // findCollegeName(){
      //   let universityNameId=this.reportFormOutTurn.value.universityName.id;
      //   let collegeType=this.reportFormOutTurn.value.collegeType.id
      //   if(universityNameId){
      //     this.collegeName=[];
      //       this.collegeService.getCollege(this.reportFormOutTurn.value.universityName.id).subscribe((res)=>{
      //         this.collegeName=res;
      //         this.collegeName.splice(0,0,this.allCollegeName);
      //         this.filterCollegesName=this.collegeName.slice();
      //       })
      //     }
      //      if(collegeType){
      //       this.collegeName=[];
      //       this.collegeService.getcollegenamebyType(universityNameId,collegeType).subscribe((res)=>{
      //         this.collegeName=res;
      //         this.collegeName.splice(0,0,this.allCollegeName);
      //         this.filterCollegesName=this.collegeName.slice();
      //       })
      //      }
      // }


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
      this.dataTableToggle = false;
    this.isDataLoading=false;
    this.Showdata12=false;
    this.formControl();
    this.child?.onReset();
    //  if (!this.isAdminOrMoE && !this.userData) {
    //   const selected = this.filterStatesOption.find(
    //     (s: any) => s.stateCode == this.stateCodeDeafult
    //   );
    //   if (selected) {
    //     this.reportFormOutTurn.patchValue({ addressStateCode: selected });
    //     this.reportFormOutTurn.get('addressStateCode')?.disable();
      
    //   }
    // }else{
    //    this.child?.onReset();
    // }
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
    if(this.reportFormOutTurn.valid){
    this.child?.getReportDataTable();
    this.generateFieldsTableForExcel();
  }
}
}
