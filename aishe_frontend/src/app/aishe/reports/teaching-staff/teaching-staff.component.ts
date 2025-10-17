import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, AbstractControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { finalize } from 'rxjs';
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
  selector: 'app-teaching-staff',
  templateUrl: './teaching-staff.component.html',
  styleUrls: ['./teaching-staff.component.scss']
})
export class TeachingStaffComponent implements OnInit {
  submitted:boolean=false;
  isDataLoading:boolean=false;
  dataTableToggle:boolean=false;
  filteredUniversityNameData:any;
  Showdata12: boolean = false;
  surveyYearOption:any;
  filterSurveyYearOption:any;
  reportFilterForm :  FormGroup|any;
  filterStatesOption:any;
  filterdStates:any; //for text based search
  allStates={stateName:"ALL",stateCode:"ALL"};

  designationData: any;
  filterDesignation:any;
  allPosts={designation:"ALL",id:"ALL"};
  allTypeWithId={type:"ALL",id:"ALL"};

  filterUniversityTypes:any;
  filteredUniversityTypes:any;

  fieldsData:any=[];
  managmentTypeData:any=[];
  filterManagmentTypeData:any=[];
  allManagementTypes={managementType:"ALL",id:"ALL"};
  bodyTypes:any;
  filteredBodyTypes:any;
CollegeTypes:any;
filterCollegeTypes:any;
allCollegeType={type:"ALL",id:"ALL"};

UniversityNameData:any=[];
filterUniversityNameData:any=[];
allUniversity={name:"ALL",id:"ALL"};



  @ViewChild("child") //to get child component reference
  child?:ReportChildComponent
  userData: string | null;
  roleId: string;
  stateCodeDeafult: any;
  isAdminOrMoE: any;
  

  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,
    private fb:FormBuilder,
    private surveyyearservice:SurveyyearService,
    private reportService:ReportService,
    private UniversityService:UniversityService,
  public countrystatecityService: CountrystatecityService,
    private excelService:ExcelService,public localService: LocalserviceService,public sharedservice: SharedService) {
      this.formControl();
      this.userData= this.localService.getData('userData')
        this.roleId = this.localService.getData('roleId')
        console.log('787role',this.roleId)
         this.stateCodeDeafult = this.localService.getData('stateCode');
  
    console.log(' this.stateCodeDeafult', this.stateCodeDeafult)
  }

  ngOnInit(): void {
      this.isAdminOrMoE=(this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])
    this.loadSurveyYear();
    this.loadStates();
    this.loadDesignation();
    this.loadUniversityType();
    this.loadBodyTypes();
    this.loadManagmentTypeData();
    this.loadCollegeTypeData();
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
      designation:[this.allPosts],
      addressStateCode:[this.allStates],
      selectionMode:['ALL'],
      universityTypeCode:[this.allTypeWithId],
      managementName:[this.allManagementTypes],
      Body:[this.allTypeWithId],
      collegeType:[this.allCollegeType],
      universityName:[this.allUniversity],
      institution:['ALL'],
      affiliated:['']

    })
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
  onReset(): void {
    this.dataTableToggle = false;
    // this.isDataLoading=false;
    this.Showdata12=false;
    this.child?.onReset()
    this.formControl()

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

    // loadStates() {

    //   let countryCode = utility.getLoginlocalStorageUserData().country
    //  // console.log("countryCode ", countryCode);

    //   this.countrystatecityService.getstate(countryCode).subscribe((res) => {

    //     this.filterStatesOption = res
    //     this.filterStatesOption.splice(0,0,this.allStates)

    //     this.filterdStates = this.filterStatesOption.slice()

    //   })

    // }
     loadStates() {
  const countryCode = utility.getLoginlocalStorageUserData().country;

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

    loadDesignation(){

      this.reportService.getDesignation().subscribe((res) =>{

        this.designationData = res;
        this.designationData.splice(0,0,this.allPosts);

        this.filterDesignation = this.designationData.slice();

      });

    }

    loadUniversityType(){
  
      this.isDataLoading=true;
      this.UniversityService.getUniversityTypes().pipe(
        finalize(()=>{
          this.isDataLoading=false;
        })
      ).subscribe((res) => {

        if(!res || res.length == 0){
          return;
        }

        this.filterUniversityTypes = res;
        this.filterUniversityTypes.splice(0,0,this.allTypeWithId);

        this.filteredUniversityTypes = this.filterUniversityTypes.slice();


      });

    }

    loadBodyTypes(){

      this.reportService.getBodyType().subscribe((res)=>{
        this.bodyTypes=res;
        this.bodyTypes.splice(0,0,this.allTypeWithId);

        this.filteredBodyTypes = this.bodyTypes.slice();

      });

    }

    loadManagmentTypeData(){
      this.reportService.getManagmentTypeData().subscribe((res)=>{
        this.managmentTypeData=res;
        this.managmentTypeData.splice(0,0,this.allManagementTypes)

        this.filterManagmentTypeData = this.managmentTypeData.slice();

      });
    }

    loadCollegeTypeData(){
      this.reportService.getCollegeTypeData().subscribe((res)=>{
        this.CollegeTypes=res;
        this.CollegeTypes.splice(0,0,this.allCollegeType);
        this.filterCollegeTypes = this.CollegeTypes.slice();
      });
    }

    findUniversityByYearAndState(){
      
      this.UniversityNameData=[];
      this.filteredUniversityNameData=[];
       let surveyYear=this.reportFilterForm.value.surveyYear;
       let stateObj=this.reportFilterForm.value.addressStateCode;
       let universityType=this.reportFilterForm.value.universityTypeCode.id;
   
       if(surveyYear && stateObj && !universityType)
         {
         this.UniversityService.getfindUniversityDataSate(surveyYear,stateObj).subscribe((res) => {
           this.UniversityNameData = res;
           this.UniversityNameData.splice(0,0,this.allUniversity);
           this.filteredUniversityNameData=this.UniversityNameData.slice()
         });
       }else if(surveyYear && stateObj && universityType && this.reportFilterForm.value.institution !== 'College Institution'){
         this.UniversityService.getfindUniversityData1(surveyYear,stateObj,universityType).subscribe((res) => {
           this.UniversityNameData = res;
           this.UniversityNameData.splice(0,0,this.allUniversity);
           this.filteredUniversityNameData=this.UniversityNameData.slice()
         });
       } 
      else if(this.reportFilterForm.value.institution == 'College Institution'){
        this.UniversityService.getfindUniversityData1(surveyYear,stateObj,'ALL').subscribe((res) => {
          this.UniversityNameData = res;
          this.UniversityNameData.splice(0,0,this.allUniversity);
          this.filteredUniversityNameData=this.UniversityNameData.slice()
        });
      }
       
  }

  get f(): { [key: string]: AbstractControl } {
    return this.reportFilterForm.controls;
  }
  submit(){

    this.submitted=true;
    if(this.reportFilterForm.valid){
    this.child?.getReportDataTable();
   // this.generateFieldsTableForExcel();
  }
}
}
