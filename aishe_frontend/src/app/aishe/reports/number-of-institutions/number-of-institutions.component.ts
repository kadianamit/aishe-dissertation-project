import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { utility } from 'src/app/common/utility';
import { CountrystatecityService } from 'src/app/service/reports/countrystatecity.service';
import { ExcelService } from 'src/app/service/reports/excel.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import { UniversityService } from 'src/app/service/reports/university.service'
@Component({
  selector: 'app-number-of-institutions',
  templateUrl: './number-of-institutions.component.html',
  styleUrls: ['./number-of-institutions.component.scss']
})
export class NumberOfInstitutionsComponent implements OnInit {
  isDataLoading:boolean=false;
  dataTableToggle:boolean=false;
  Showdata12: boolean = false;
  surveyYearOption:any;
  filterSurveyYearOption:any;
  reportFilterForm :  FormGroup|any;
  filterStatesOption:any;
  filterdStates:any;
  allStates={stateName:"ALL",stateCode:"ALL"}
  submitted: boolean=false;
 filteredUniversityNameData: any[] = [];
  fieldsData:any=[];
isAdminOrMoE:any;
isSno:any;
allUniversity = { name: "ALL", id: "ALL" }
  @ViewChild("child") //to get child component reference
  child?:ReportChildComponent
  UniversityNameData: any = []
  isUniversity:boolean=true;
  isCollege:boolean=true;
  isStandalone:boolean=true;
  reportDataForm=new FormBuilder().group({
    isUniversity:[this.isUniversity],
    isCollege:[this.isCollege],
    isStandalone:[this.isStandalone],
  });
  allUniversityType = { type: "ALL", id: "ALL" }

  userData: string | null;
  roleId: string;
  stateCodeDeafult: string;

  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,
    private fb:FormBuilder,
    private surveyyearservice:SurveyyearService,
    public countrystatecityService: CountrystatecityService,
    private excelService:ExcelService,public localService: LocalserviceService,  private UniversityService: UniversityService, public sharedservice: SharedService) {
    this.formControls();
    this.userData= this.localService.getData('userData');
        this.roleId = this.localService.getData('roleId')
        console.log('787role',this.roleId)
          this.stateCodeDeafult = this.localService.getData('stateCode');
  
    console.log(' this.stateCodeDeafult', this.stateCodeDeafult);
  }


  ngOnInit(): void {
    this.isAdminOrMoE=(this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])
    this.isSno=(this.roleId === this.sharedservice.role['State Nodal Officer'])
    this.loadSurveyYear();
    this.loadStates();


  }
  // setAddressStateCodeControlState(): void {
  //   if (this.isAdminOrMoE) {
  //     this.reportFilterForm.get('addressStateCode')?.enable();
  //   } else {
  //     this.reportFilterForm.get('addressStateCode')?.disable();
  //   }
  // }

//  get  isAdminOrMoE(): boolean {
//     return (
//       (this.roleId === this.sharedservice.role['SysAdmin']) ||
//       (this.roleId === this.sharedservice.role['Ministry of Education'])
//     );
//   }
  formControls(){
    this.reportFilterForm = this.fb.group({
      surveyYear: ['',Validators.required],
      // addressStateCode:[this.allStates],
       addressStateCode: [this.allStates],
       universityName: [this.allUniversity],


    })
  }

  get f(): { [key: string]: AbstractControl } {
    return this.reportFilterForm.controls
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

 findUniversityByYearS() {
    let surveyYear = this.reportFilterForm.value.surveyYear;
    let stateObj = this.reportFilterForm.value.addressStateCode?this.reportFilterForm.value.addressStateCode:this.stateCodeDeafult;
    let universityType ='ALL';

    let arrey = this.filteredUniversityNameData.filter((els: any) => els.id === this.reportFilterForm.value.universityName.id);
    if (arrey && arrey.length) {
      this.reportFilterForm.get("universityName")?.setValue(this.allUniversity);
    }

    if (surveyYear) {
      this.UniversityService.getfindUniversityData1(surveyYear, stateObj, universityType).subscribe((res) => {
        this.UniversityNameData = res;
        this.UniversityNameData.splice(0, 0, this.allUniversity);
        this.filteredUniversityNameData = this.UniversityNameData.slice()
      });
    }
  }
  onReset(): void {
    this.dataTableToggle = false;
    // this.isDataLoading=false;
    this.Showdata12=false;
    this.child?.onReset()
    this.formControls()
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
