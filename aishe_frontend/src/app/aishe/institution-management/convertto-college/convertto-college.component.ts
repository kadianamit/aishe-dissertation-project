import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import { Common } from 'src/app/common/common';
import { LocalserviceService } from 'src/app/service/localservice.service';
@Component({
  selector: 'app-convertto-college',
  templateUrl: './convertto-college.component.html',
  styleUrls: ['./convertto-college.component.scss']
})
export class ConverttoCollegeComponent implements OnInit {

  @Input()
  instiFormData: any;

  id:any;
NameData:any;
Name1:any;
stateName:any;
universityTypeV:any;
districtName:any;
  showTable: boolean = false;
  userDataTable: any[] = [];
  userData:any[] = [];
  filteredState:any[] = [];
  dataDistrict:any[] = [];
  filteredDistrict:any;
  universityTypefilter:any;
  universityTypeData:any[]=[];
  tabledata: boolean = false;
  validation:boolean = false;
  userData1: any = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  roleId: any;
  collegeTypeData:any[]=[];
  managmentData:any;
  filteredcollegeTypeData:any[]=[];
  filteredmanagmentData:any;
  isConvertCollege: boolean = false;
  filteredmanagementT:any;
  tableSize: number[] = [10, 20, 30, 40, 50];
  showEdit:boolean=false;
  surveyYear1:any;
  submitted:boolean = false;
  UniSurveyYear:any;
  surveyYearList:any;
  state:any;
  locationData:any;
  universityList:any[]=[];
  filtereduniversityList:any;
  validPattern = /^[a-zA-Z !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{2,500}$/;

 form = new FormGroup({
    surveyYear:new FormControl('', Validators.required),
    collegestateName: new FormControl('', Validators.required),
    universitystateName:new FormControl('', Validators.required),
    districtName: new FormControl('', Validators.required),
    collegeType: new FormControl('', Validators.required),
    universityValue: new FormControl('', Validators.required),
    location: new FormControl('', Validators.required),
    CollegeName:new FormControl('', [Validators.required,Common.noWhitespaceValidator]),
    managementType: new FormControl('', Validators.required),
    remarks: new FormControl('',  [Validators.required,Validators.maxLength(2000),Common.noWhitespaceValidator]),
    isDCFApplicable: new FormControl( )
  });
  constructor(
    public sharedservice: SharedService,
    private institutionmanagement: InstitutionmanagementService, private dialog: MatDialog,public localService:LocalserviceService
  ) {}

  ngOnInit(): void {
    this.institutionmanagement.getState().subscribe((res) => {
      this.userData = res;
      this.filteredState = this.userData.slice();
    });
    this.findData();
    this.loadSurveyYear();
    this.getCollegeTypeData();
    this.findManagmentType();
    this.getLocation();
  }

  getLocation() {
    this.institutionmanagement.getLocation().subscribe((res) => {
      this.locationData = res.location;
    });
  }
    get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  
  alphaNumberOnly (e:any) {  // Accept only alpha numerics, not special characters 
    var regex = new RegExp("[a-zA-Z ]");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {  
      if (e.target.value.substr(-1) === ' ' && e.code === 'Space') {
        e.preventDefault();
       }else if (e.target.value.substr(-1) === ''){
         return str.split('').join(' ');
       }
        return true;
    }

    e.preventDefault();
    return false;
  }

  backClicked(){
    this.showTable = true;
    this.showEdit = false;
    this.isConvertCollege=false;
    this.submitted = false;
    this.form.reset();
  }
  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  loadSurveyYear(){
    this.institutionmanagement.getSurveyYearList().subscribe({
      next:(res)=>{
        this.surveyYearList=res;
      //   if(this.selectedSurveyYear){
      //   this.surveyYearValue.splice(0,0,this.selectedSurveyYear);
      // }

      }
    })
  }
  resetdata(): void {
    this.userDataTable=[];
    this.page = 1;
    this.pageSize= 10;
    this.handlePageChange(this.page);
    this.searchText = null;
    this.pageSize=10;
  }
  findData() {
    this.searchText=null;
    if (!this.instiFormData.value.surveyYearValue) {
      this.sharedservice.showError('Please Select Survey Year');
      return;
    }
    if (!this.instiFormData.value.stateValue) {
      this.sharedservice.showError('Please Select State');
      return;
    }

    let data = {
      stateValue: this.instiFormData.value.stateValue?this.instiFormData.value.stateValue.trim():'',
      surveyYearValue: this.instiFormData.value.surveyYearValue.split('-')[0],
      universityType: this.instiFormData.value.universityType?this.instiFormData.value.universityType.trim():'',
    };
    this.institutionmanagement.getViewUniversity(data).subscribe((res) => {
      this.userDataTable = res.universityListBean;
      this.showTable = true;
      this.page = 1;
      this.handlePageChange(this.page);
      if (res.statusCode == 'AISH099') {
        this.sharedservice.showError(res.statusDesc);
      }
    });
  }
  loadUniversityData(){

    if(this.form.value.surveyYear){
      this.UniSurveyYear=this.form.value.surveyYear.split('-')[0];
    }

        this.state=  this.form.value.universitystateName;
        if(this.UniSurveyYear && this.state){
        this.institutionmanagement.getUniversity(this.state, this.UniSurveyYear).subscribe({
          next: (res) =>{
            this.universityList = res;
            this.filtereduniversityList = this.universityList.slice();
          }
        })
      }

    }
    getCollegeTypeData() {
      this.institutionmanagement.getCollegeType().subscribe((res) => {
        this.collegeTypeData = res;
        this.filteredcollegeTypeData = this.collegeTypeData.slice();
      });

    }
  findManagmentType() {
    let payload={type:'C'}
    this.institutionmanagement.getmanagmentType2(payload).subscribe((res) => {
      this.managmentData = res;
     // console.log(res)
      this.filteredmanagementT = this.managmentData.slice();
    });
  }
  getdistrictName() {
    let stateName1 = this.userData.filter(
      (data: any) => data.name === this.form.value.collegestateName
    );
    if(stateName1.length>0){
    this.institutionmanagement
      .getdistrict(stateName1[0].stateCode)
      .subscribe((res) => {
        this.dataDistrict = res;
        this.filteredDistrict = this.dataDistrict.slice();
        // console.log('this.form.value.stateName', this.dataDistrict);
      });
    }
    // console.log('this.form.value.stateName', this.form.value.stateName);
  }

  loadUniversityType() {
    this.institutionmanagement.getUniversityType().subscribe((res) => {
      this.universityTypeData = res;
      this.universityTypefilter = this.universityTypeData.slice();
    });
  }

  compareFn(t1: any, t2: any): boolean {
    return t1 && t2 ? t1 === t2 : t1 === t2;
  }


  ConverttoColleg(data:any){
    if(data.isDcfApplicable != 'false'){
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        width:'34%',
        data: {
          message1: 'edit',
          surveyYear:this.instiFormData.value.surveyYearValue,
        },
      });
      return;
    }
    this.isConvertCollege=true;
    this.showTable = false;
    this.showEdit = true;
    this.surveyYear1 = this.instiFormData.value.surveyYearValue;
    this.id = data.aishecode;
    this.NameData = data.name;
    this.Name1 = data.name;
    this.stateName = data.statename;
    this.universityTypeV = data.universityType;
    this.districtName = data.districtname;
    // this.form.controls['universityType'].setValue(this.universityTypeV);
    // this.form.controls['districtName'].setValue(this.districtName);
    // this.form.controls['stateName'].setValue(this.stateName);
    //this.getdistrictName();
  }

  submit(){

    if(this.validation == true){
      this.form.reset()
      this.validation = false;
     }
    this.submitted = true;

    let managementType = this.managmentData.filter(
      (person:any) => person.id === this.form.value.managementType
    );
    let collegeType1=this.collegeTypeData.filter((data:any) => data.id === this.form.value.collegeType);
  let universityType2=this.universityList.filter((data:any)=>data.id ===this.form.value.universityValue)
  let stateCode=this.userData.filter((data:any)=>data.name===this.form.value.collegestateName);
  let districtCode=this.dataDistrict.filter((data:any)=>data.name===this.form.value.districtName)

    if (this.form.valid) {
let locationCode=this.locationData.filter((data:any)=>data.id === this.form.value.location)
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        data: {
          message:
            'Details of the Institute are as below, Please confirm the details.',
          state: this.form.value.collegestateName,
          district: this.form.value.districtName,
          Name: this.form.value.CollegeName.trim(),
          remarks: this.form.value.remarks.trim(),
          newCollegeType:collegeType1[0].type,
          university: universityType2[0].name,
          managementId :managementType[0].managementType,
          surveyYear:this.form.value.surveyYear.slice(0,4),
          location: locationCode[0].name,
          isDCFApplicable:this.form.value.isDCFApplicable? this.form.value.isDCFApplicable :"false",
          universityType: 'converttocollege',
        },
      });

      let userData  = {
         universityId:this.id,
          districtCode:districtCode[0].distCode,
          collegeIsDcfApplicable:this.form.value.isDCFApplicable? this.form.value.isDCFApplicable :"false",
          collegeName:this.form.value.CollegeName.trim(),
          stateCode: stateCode[0].stateCode,
          typeId:this.form.value.collegeType,
          collegeUniversityId:this.form.value.universityValue,
          remarks:this.form.value.remarks.trim(),
          managementId :this.form.value.managementType,
          location:this.form.value.location,
          surveyYear:this.form.value.surveyYear.slice(0,4),
          userId:this.localService.getData('userId')
         }

        // console.log("acc ",userData)
       // call save api accordingly
        dialogRef.afterClosed().subscribe((confirmed: boolean) => {
          if (confirmed) {
            this.institutionmanagement
              .postConverttoCollege(userData)
              .subscribe((res) => {
                this.validation= true;
                this.submitted = false;
                if (res.statusCode == 'AISH011') {
                  this.sharedservice.showError(
                    'This institution has already participated in the Current AISHE Survey.Please delete the survey data in order to convert to college.'
                  );
                }
                if (res.statusCode == 'AISH024') {
                  this.sharedservice.showError(res.statusDesc);
                }
                if (res.statusCode == 'AISH001') {
                  this.createValidationForm()
                  this.form.reset();

                  const dialogRef = this.dialog.open(ConfirmDialogComponent,{
                    data: {
                      message1:'Your request has been processed successfully!!',
                      data1: res.college,
                      action:'1',
                      showData:'upgradeCollege',
                    }
                  });
                  this.sharedservice.showSuccessMessage(
                    'College Data Added Successfully'
                  );
                  this.showTable = true;

                }

                if (res.statusCode == 'AISH102') {
                  this.validation = false;
                  this.sharedservice.showError(
                    'University Name Already Exists'
                  );

                }
                if (res.statusCode == 'AISH002') {
                  this.validation = false;
                  this.sharedservice.showError(res.statusDesc);
                }
                this.backClicked();
                this.form.reset();
                this.findData();
              });
          }
        });

   }
    if (this.form.invalid) {
      this.sharedservice.showError(
        'Invalid details, Please provide valid details.'
      );
    }
  }

  createValidationForm(){
    Object.keys(this.form.controls).forEach((key) => {
      const control = this.form.controls[key];
      control.setErrors(null);
  });

  }

  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
    //this.findData();
  }

  handlePageChange(event: number) {
    this.page = event;
    let fgh = parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * fgh),
      (this.EndLimit = this.StartLimit + fgh);
    var a = Math.ceil(this.userDataTable.length / fgh);
    if (a === event) {
      this.pageData = Math.min(
        this.StartLimit + fgh,
        this.userDataTable.length
      );
    } else {
      this.pageData = Math.min(
        this.StartLimit + fgh,
        this.userDataTable.length
      );
    }
  }
}
