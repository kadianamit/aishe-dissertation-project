import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, AbstractControl, Validators, FormControl } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { CountrystatecityService } from 'src/app/service/reports/countrystatecity.service';
import { ExcelService } from 'src/app/service/reports/excel.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { UniversityService } from 'src/app/service/reports/university.service';
import { SharedService } from 'src/app/shared/shared.service';


@Component({
  selector: 'app-finance',
  templateUrl: './finance.component.html',
  styleUrls: ['./finance.component.scss']
})
export class FinanceComponent implements OnInit {

  @ViewChild("child") //to get child component reference
  child?:ReportChildComponent

  submitted:boolean=false;
  isDataLoading:boolean=false
  surveyYearOption:any
  filterSurveyYearOption:any=[];
   userData: string | null;
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
  allManagementTypes={managementType:"ALL",id:"ALL"}
  allUniversityType={type:"ALL",id:"ALL"}
  allColleges={id:'ALL',name:'ALL'}
  allNameWithId={name:"ALL",id:"ALL"}

  filteredBodyTypes:any=[];
  reportFormlistofInst:FormGroup|any;

  fieldsData:any=[];
filterUniversityNameData: any;
  isAdminOrMoE: any;
  roleId: any;
  stateCodeDeafult: any;
//bodyTypes: any;
  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,
  private surveyyearservice:SurveyyearService,
  private fb:FormBuilder,
  public countrystatecityService: CountrystatecityService,
  private excelService:ExcelService,
  private reportService:ReportService,
 private UniversityService:UniversityService,public localService: LocalserviceService,
   public sharedservice:SharedService) {
this.formControl();
  this.roleId = this.localService.getData('roleId')
 this.userData= this.localService.getData('userData')
    this.stateCodeDeafult = this.localService.getData('stateCode');
  }
  ngOnInit(): void {
     this.isAdminOrMoE = (this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])

    this.loadStates();
    this.loadSurveyYear()
    this.universityTypeData();

    this.findBodyType();
    this.findManagmentTypeData();
    this.findCollegeTypeData();   
    
  }

  formControl(){
    this.reportFormlistofInst = this.fb.group({
      surveyYear: ['',Validators.required],
      institution:['ALL'],
      addressStateCode:[this.allStates],
      collegeType:[this.allCollegeType1],
      universityType:[this.allUniversityType],
      universityName:[this.allUniversity],
      managementType:[this.allManagementTypes],
      bodyType:[this.allBodyType],
    })
  }

  get f(): { [key: string]: AbstractControl } {
    return this.reportFormlistofInst.controls
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
  //       this.reportFormlistofInst.patchValue({ addressStateCode: selected });
  //       this.reportFormlistofInst.get('addressStateCode')?.disable();
      
  //     }
  //   }
  });

  }
  findUniversityByYearAndState(){

    let event=this.reportFormlistofInst.value.surveyYear;
    let obj=this.reportFormlistofInst.value.addressStateCode;
   // let universityType=this.reportFormlistofInst.value.universityType.id
   
      this.UniversityService.getfindUniversityDataSate(event,obj).subscribe((res) => {
        this.UniversityNameData = res;
        this.UniversityNameData.splice(0,0,this.allUniversity);
        this.filteredUniversityNameData=this.UniversityNameData.slice()
      })
    
    // if(universityType){
    //   this.UniversityService.getfindUniversityData1(event,obj,universityType).subscribe((res) => {
    //     this.UniversityNameData = res;
    //     this.UniversityNameData.splice(0,0,this.allUniversity);
    //     this.filteredUniversityNameData=this.UniversityNameData.slice()
    //   })
   // }
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
    this.formControl();
    this.child?.onReset();
    //  if (!this.isAdminOrMoE && !this.userData) {
    //   const selected = this.filterStatesOption.find(
    //     (s: any) => s.stateCode == this.stateCodeDeafult
    //   );
    //   if (selected) {
    //     this.reportFormlistofInst.patchValue({ addressStateCode: selected });
    //     this.reportFormlistofInst.get('addressStateCode')?.disable();
      
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
    if(this.reportFormlistofInst.valid){
    this.child?.getReportDataTable();
   // this.generateFieldsTableForExcel();
  }
}
}
