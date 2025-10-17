import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder, AbstractControl } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { type } from 'os';
import { finalize } from 'rxjs';
import { DailogmessageComponent } from 'src/app/common/dailogmessage/dailogmessage.component';
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
  selector: 'app-non-teaching-staff',
  templateUrl: './non-teaching-staff.component.html',
  styleUrls: ['./non-teaching-staff.component.scss']
})
export class NonTeachingStaffComponent implements OnInit {

  submitted:boolean=false;
  filterSurveyYearOption:any;
  filterStatesOption:any;
  filterdStates:any; //for text based search
  filterDistrictsOption:any;
  filterStatesOption1:any=[]
  filterUniversityOption:any;
  filterUniversities:any;
  filterUniversityTypes:any;
  filteredUniversityTypes1:any=[];
  filterCollegesOption:any;
  filterColleges:any;
  bodyTypes:any;
  showPdfButton:false|boolean|any;
  nonTeachingStaff:any=[];
  filteredBodyTypes:any;
  nonTeacherStaffTypeData:any;
  filteredNonTeacherStaffTypeData:any;
  UniversityNameData:any=[];
  allStates={stateName:"ALL",stateCode:"ALL"};
  allUniversity={name:"ALL",id:"ALL"};
  allBodyType={type:"ALL",id:"ALL"};
  allCollegeType={type:"ALL",id:"ALL"};
  allPosts={designation:"ALL",id:"ALL"};
  allManagementTypes={managementType:"ALL",id:"ALL"};
  allColleges={id:'ALL',name:'ALL'};
  allnonTeacherStaffType={staffType:"ALL",id:"ALL"};
  allTypeWithId={type:"ALL",id:"ALL"};
 nonTeacherStaffTypeGroup:any;
  filterNonTeacherStaffTypeGroup1:any=[];
  allNonTeacherStaffTypeGroup={id:"ALL",staffGroup:"ALL"};
  CollegeData1:any=[]
  arrAllNotRequired = ['Report 1',
      'Report 2',
      'Report 3',
      'Report 6',
      'Report 7',
      'Report 8',
      'Report 9',
      'Report 14',    ];

  reportTableData:any;
  tableColumns:any[]=[];
  Showdata12: false | boolean | undefined;
  filterUniversityNameData:any = [];


  summaryColumns:any;
  summaryData:any;
CollegeData:any=[];
filtermanagmentTypeData:any=[];
managmentTypeData:any=[];
SeachCollege:any=[];
  dataTableToggle:boolean=false;

  reportFilterForm = new FormGroup({
    surveyYear: new FormControl('', Validators.required),
    addressStateCode: new FormControl(''),
    addressDistrictCode: new FormControl(''),
    universityCode: new FormControl(''),
    collegeCode: new FormControl(''),
    universityTypeCode: new FormControl(''),
    managementName: new FormControl(''),
    collegeType: new FormControl(''),
    Body: new FormControl(''),
    universityName: new FormControl(''),
    staffType: new FormControl(''),
    affiliated:new FormControl(''),
  })


  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  @ViewChild("child") //to get child component reference
  child?:ReportChildComponent
  isDataLoading:boolean=false;

  isUniversity:boolean=true;
  isCollege:boolean=true;
  isStandalone:boolean=true;
  reportDataForm=new FormBuilder().group({
    isUniversity:[this.isUniversity],
    isCollege:[this.isCollege],
    isStandalone:[this.isStandalone],
  });

  fieldsData:any=[];
  userData: string | null;
  surveyYearOption: any;
 roleId: string;
  stateCodeDeafult: string;
  isAdminOrMoE: any;
  constructor(@Inject(MAT_DIALOG_DATA) public obj: any, @Inject(MAT_DIALOG_DATA) public reportInfoObj: any,
  private surveyyearservice:SurveyyearService,
  private fb:FormBuilder,
  public countrystatecityService: CountrystatecityService,
  private UniversityService:UniversityService,
  private reportService:ReportService,
  private excelService:ExcelService,
  private dialog: MatDialog,public localService: LocalserviceService,public sharedservice:SharedService) {
this.formControl();
this.userData= this.localService.getData('userData');
    this.roleId = this.localService.getData('roleId')
        console.log('787role',this.roleId)
         this.stateCodeDeafult = this.localService.getData('stateCode');
  
    console.log(' this.stateCodeDeafult', this.stateCodeDeafult)
  }


  ngOnInit(): void {
 this.isAdminOrMoE=(this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])



    this.loadUniversityType();
    this.collegeTypeData();
    this.managmenttypeData();
    this.loadSurveyYear();
    this.loadStates();
    this.loadBodyTypes();
    this.loadStaffType();
    this.loadNonTeachingStaffGroup();
    //  this.setAddressStateCodeControlState();

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
      addressDistrictCode:[''],
      universityCode:[''],
      collegeCode:[''],
      universityTypeCode:[this.allTypeWithId],
      managementName:[this.allManagementTypes],
      collegeType:[this.allCollegeType],
      Body:[this.allBodyType],
      universityName:[this.allUniversity],
      institution:['ALL'],  //institute for report 19
      institutionType:['ALL'],  //institute for report 19
      staffType:[this.allnonTeacherStaffType],
      staffGroup:[this.allNonTeacherStaffTypeGroup],
      affiliated:['ALL'],

    });
  }
  compareFunction(o1: any, o2: any) {
    return (o1.name == o2.name && o1.id == o2.id);
   }
  get f(): { [key: string]: AbstractControl } {
    return this.reportFilterForm.controls;
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

let countryCode = utility.getLoginlocalStorageUserData().country;
this.countrystatecityService.getstate(countryCode).subscribe((res) => {
  this.filterStatesOption = res;
    this.filterStatesOption.unshift(this.allStates); // optional "All States"

    this.filterStatesOption = [...this.filterStatesOption];
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


 showSelectInput(){
  this.dialog.open(DailogmessageComponent,
    {data:{
      message:"Please select all inputs"
    }});
 }
 showNoDataFound(){
  this.dialog.open(DailogmessageComponent,{
    data:{
      message:'No Data found'
    }
  });
 }
 onReset(): void {
  this.dataTableToggle = false;
  this.Showdata12=false;
  this.showPdfButton=false;
  this.formControl();
this.child?.onReset();
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
        this.filteredUniversityTypes1= this.filterUniversityTypes.slice();
        this.filteredUniversityTypes1.splice(0,0,this.allTypeWithId);

      });

    }


    public selectedUniversityType(uniObj: { type: string }): string {

      if(uniObj ==null){
        return '';
      }
      console.log("selected uniObj type: ", uniObj);
      return uniObj.type;

    }

  collegeTypeData(){
    this.reportService.getCollegeTypeData().subscribe((res)=>{
      this.CollegeData=res;
      this.CollegeData1=this.CollegeData.slice();
      this.CollegeData1.splice(0,0,this.allCollegeType);

    })
  }




  managmenttypeData(){
    this.reportService.getManagmentTypeData().subscribe((res)=>{
      this.managmentTypeData=res;
      this.filtermanagmentTypeData=this.managmentTypeData.slice();
      this.filtermanagmentTypeData.splice(0,0,this.allManagementTypes)


    });
  }

  loadBodyTypes(){

    this.reportService.getBodyType().subscribe((res)=>{
      this.bodyTypes=res;
      this.filteredBodyTypes=this.bodyTypes.slice();
      this.filteredBodyTypes.splice(0,0,this.allBodyType);

    });

  }


  findUniversityByYearAndState(){
    let event=this.reportFilterForm.value.surveyYear;
    let obj=this.reportFilterForm.value.addressStateCode;
    let universityType=this.reportFilterForm.value.universityTypeCode.id
    let arrey=this.filterUniversityNameData.filter((els:any)=>els.id===this.reportFilterForm.value.universityName.id);
    if(arrey && arrey.length){
      this.reportFilterForm.get("universityName")?.setValue(this.allUniversity);
    }


if(universityType){
    this.UniversityService?.getfindUniversityData1(event,obj,universityType).subscribe((res) => {
      this.UniversityNameData = res;
      this.filterUniversityNameData=this.UniversityNameData.slice();
      this.filterUniversityNameData.splice(0,0,this.allUniversity);
    })
  }
  }

  loadStaffType(){

    this.reportService.getNonTeacherStaffType().subscribe((res)=>{
      this.nonTeacherStaffTypeData=res;
     this.filteredNonTeacherStaffTypeData=this.nonTeacherStaffTypeData.slice();
     this.filteredNonTeacherStaffTypeData.splice(0,0,this.allnonTeacherStaffType);

    });

  }

  loadNonTeachingStaffGroup(){

     this.reportService.getNonTeachingStaffGroup().subscribe((res)=>{
       this.nonTeacherStaffTypeGroup=res;
       this.filterNonTeacherStaffTypeGroup1=this.nonTeacherStaffTypeGroup.slice();
       this.filterNonTeacherStaffTypeGroup1.splice(0,0,this.allNonTeacherStaffTypeGroup);

     });

  }

  exportToExcel(): void{
    this.excelService.exportToExcel(this.reportInfoObj.reportNumber,this.tableColumns);

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
  doNothing(){
return;
  }

  submit(){

    this.submitted=true;

    if(this.reportFilterForm.valid){
    this.child?.getReportDataTable();
    //this.generateFieldsTableForExcel();
  }

}
}
