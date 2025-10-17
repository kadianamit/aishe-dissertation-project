import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import { Common } from 'src/app/common/common';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-standalone-edit',
  templateUrl: './standalone-edit.component.html',
  styleUrls: ['./standalone-edit.component.scss']
})
export class StandaloneEditComponent implements OnInit {

  @Input()
  instiFormData:any;
  surveyYear1:any
  id:any
  Name1:any
  NameData:any
  stateName:any
  districtName:any
  Standalonetype:any
  standalonTypeData:any
  standalonTypefilter:any[] = [];
  isEdit:boolean = false;
  showTable:boolean = false;
  userData:any
  dataDistrict:any
  filteredDistrict:any[] = [];
  filteredState:any[] = [];
  userDataTable:any[] = [];
  showEdit:any = false;
  tabledata: boolean = false;
  userData1: any = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  roleId:any=this.localService.getData('roleId');
  dataCheck:boolean = false;
  managmentData:any;
  filteredmanagementT:any[]=[];
showStandalone: boolean = false;
submitted = false;
  tableSize: number[] = [10, 20, 30, 40, 50];
  managementType:any;
  locationData:any
  location:any;
  validPattern = /^[a-zA-Z !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{1,250}$/;
  form = new FormGroup({
    surveyYearValue:new FormControl('',Validators.required),
    Name: new FormControl('',[Validators.required ,Validators.pattern(this.validPattern)]),
    standaloneType:new FormControl(),
    stateName: new FormControl(),
    districtName: new FormControl('', Validators.required),
    managementType:new FormControl('',[]),
    location:new FormControl('',[]),
    remarks: new FormControl('',[
      Validators.required,
      Validators.maxLength(2000),
      // Validators.minLength(3)
    ]),
  });
  isFormInvalid: any;
  surveyYearList:any;
  constructor(private dialog: MatDialog,private institutionmanagement: InstitutionmanagementService,public sharedservice: SharedService,public localService:LocalserviceService,public auth: AuthService) { }

  ngOnInit(): void {
    this.roleId=this.localService.getData('roleId');
    this.findData();
    this.getInstitutionTypeData();
    this.findManagmentType();
    this. getSateData();
    this.getLocation();
    this.surveyYear();
  }

  getLocation() {
    this.institutionmanagement.getLocation().subscribe((res) => {
      this.locationData = res.location;
    });
  }
  districtfilter(){
    this.dataCheck=true;
    this.form.controls['districtName'].reset()
    }
  getSateData(){
    this.institutionmanagement.getState().subscribe((res) => {
      this.userData = res;
      this.filteredState = this.userData.slice();
    });
  }
  get f() {
    return this.form.controls;
  }
  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  findManagmentType() {
    let standalone='S'
    this.auth.getManagementTypeOwnershipData(standalone).subscribe((res)=>{
    this.managmentData=res
    //console.log(res)
    this.filteredmanagementT=this.managmentData.slice();
    });
  }
  getdistrictName() {
    let stateName1 = this.userData.filter(
      (data:any) => data.name === this.form.value.stateName
    );
    this.institutionmanagement
      .getdistrict(stateName1['0']?.stateCode)
      .subscribe((res) => {
        this.dataDistrict = res;
        this.filteredDistrict = this.dataDistrict.slice();
       // console.log('this.form.value.stateName', this.dataDistrict);
      });
    // console.log('this.form.value.stateName', this.form.value.stateName);
  }
  getInstitutionTypeData() {
    this.institutionmanagement.getSubRoll(this.roleId).subscribe((res) => {
      this.standalonTypeData = res[0].stateBodies;
      this.standalonTypefilter = this.standalonTypeData.slice();
    });

  }
  resetdata(){
    this.userDataTable=[];
    this.pageSize=10;
    this.searchText=null;
    this.page = 1;
    this.handlePageChange(this.page);
  }
  findData(){
    this.searchText=null;
    var formData;
    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    if(!this.instiFormData.value.surveyYearValue){
      this.sharedservice.showError('Please Select Survey Year');
      return;
     }
    if((!this.instiFormData.value.stateValue&& this.localService.getData('roleId')=='26'||!this.instiFormData.value.stateValue&&this.localService.getData('roleId')=='1') && this.instiFormData.value.aisheCodeValue==null){
      this.sharedservice.showError('Please Select State');
      return;
     }
    formData={
      "standaloneType":this.instiFormData.value.standaloneType==' '?this.instiFormData.value.standaloneType.trim():this.instiFormData.value.standaloneType,
      "surveyYearValue":this.instiFormData.value.surveyYearValue?this.instiFormData.value.surveyYearValue.split('-')[0]:'',
      "standaloneValue":this.instiFormData.value.standaloneValue?this.instiFormData.value.standaloneValue:'',
      "selectedStateCode":this.instiFormData.value.stateValue && this.localService.getData('roleId')=='26'|| this.localService.getData('roleId')=='1'? this.instiFormData.value.stateValue.trim():this.localService.getData('stateCode'),
      "districtValue":'',
      "aisheCodeValue":this.instiFormData.value.aisheCodeValue
    };
    this.institutionmanagement.getStandaloneViewData(formData).subscribe((res) =>{
     if(res.statusCode==="AISH001"){
      this.userDataTable =res.standaloneCollegeListBean
      this.page = 1;
      this.handlePageChange(this.page);
     }
      this.showTable = true;
     

      if(res.statusCode==='AISH099' || res.statusCode==='AISH024' || res.statusCode==='AISH025' ){
        this.userDataTable=[];
        this.page = 1;
        this.handlePageChange(this.page);
        this.sharedservice.showError(res.statusDesc);
      }
    })
  }

  alphaNumberOnly (e:any) {  // Accept only alpha numerics, not special characters 
    var regex = new RegExp("[a-zA-Z ,]");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
      if (e.target.value.substr(-1) === ' ' && e.code === 'Space') {
        e.preventDefault();
       } 
        return true;
    }

    e.preventDefault();
    return false;
  }
  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
    //this.getData1();
  }

  handlePageChange(event: number) {
    this.page = event;
    let fgh = parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * fgh),
      (this.EndLimit = this.StartLimit + fgh);
    var a = Math.ceil(this.userDataTable.length / fgh);
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + fgh, this.userDataTable.length);
    } else {
      this.pageData = Math.min(this.StartLimit + fgh, this.userDataTable.length);
    }
  }

  compareFn(t1: any, t2: any): boolean {

    return t1 && t2 ? t1 === t2 : t1 === t2;
    }

  editUser(data:any){

    if(data.isDcfApplicable =="true"){
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        width: '33%',
        data: {
          message1: 'edit',
          surveyYear:this.instiFormData.value.surveyYearValue,
        },
      });
      return;
    }
 
    this.showEdit= true;
    this.showTable = false;
    this.isEdit=true;
   // console.log("qioahd ihdfi",data);
    this.surveyYear1=this.instiFormData.value.surveyYearValue;
    this.id=data.aishecode;
    this.NameData=data.name;
    this.Name1=data.name;
    this.Standalonetype=data.bodyType;
    this.stateName=data.statename;
    this.managementType=data.managementType;
    this.districtName=data.districtname;
    this.location=data.location;
    this.form.controls["surveyYearValue"].setValue(this.surveyYear1);
    this.form.controls["managementType"].setValue(data.managementId);
    this.form.controls["location"].setValue(this.location);
    this.form.controls["standaloneType"].setValue(this.Standalonetype);
    this.form.controls["districtName"].setValue(this.districtName);
    this.form.controls["stateName"].setValue(this.stateName);
    this.getdistrictName();
  }

  backClicked(){
    this.showTable = true;
    this.isEdit=false;
    this.showEdit= false;
    this.submitted = false;
    this.form.reset()
  }
  checkForWhiteSpace(){
    let aremark=this.form.get('remarks')?.value?.trim();
    this.form.get('remarks')?.setValue(aremark);
  }
  
  submit(){
    this.checkForWhiteSpace();
    this.submitted = true;
   if (this.form.valid) {
    let stateName1 = this.userData.filter(
      (data:any) => data.name === this.form.value.stateName
    );

//console.log("Upendra",stateName1['0'].stateCode)
    let districtName1 = this.dataDistrict.filter(
      (person:any) => person.name === this.form.value.districtName
    );
   // console.log("Upendra districtName1",districtName1['0'].distCode)
    let standaloneType1 = this.standalonTypeData.filter(
      (person:any) => person.type === this.form.value.standaloneType
    );
    let managementType1 = this.managmentData.filter(
      (person:any) => person.id === this.form.value.managementType
    );

    let location= this.locationData.filter((data:any) => data.name === this.form.value.location);
    if (
      this.Standalonetype === this.form.value.standaloneType &&
      this.Name1 === this.form.value.Name &&
      this.stateName === this.form.value.stateName &&
      this.districtName ===  this.form.value.districtName &&
      this.managementType===this.form.value.managementType &&
      this.form.value.remarks 
      // && this.location===this.form.value.location
    ) {
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        width: '20%',
        data: {
          message: 'Details of the Institute are same as previous!!!',
          s1:'12'
        },
      });
      return;
    }
    if (this.form.invalid) {
      this.sharedservice.showError(
        'Invalid details, Please provide valid details.'
      );
      return;
    }

    if (
      this.form.value.Name ||
      this.form.value.standaloneType ||
      this.form.value.stateName ||
      this.form.value.districtName ||
      this.form.value.managementType||
      this.form.value.remarks || this.form.value.location
    ) {
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        data: {
          message:
            'Details of the Institute are as below, Please confirm the details.',
          state: this.form.value.stateName,
          district: this.form.value.districtName,
          managementType:managementType1.length>0?managementType1['0'].managementType:'',
          collegeType12: this.form.value.standaloneType,
          Name: this.form.value.Name,
          remarks: this.form.value.remarks,
          // Location:this.form.value.location,
          standalone: 'standalone',
        },
      });

      let userData = {
        districtCode:this.form.value.districtName?districtName1['0'].distCode:'',
        id:this.id,
        ipAddress: 'string',
        name: this.form.value.Name.trim(),
        managementId:this.form.value.managementType?managementType1['0'].id:'',
        remarks: this.form.value.remarks,
        // location:this.form.value.location?location[0].id:this.form.value.location,
        stateBodyId:this.form.value.standaloneType?standaloneType1['0'].id:'',
        stateCode: this.form.value.stateName?stateName1['0'].stateCode:'',
        surveyYear:this.form.value.surveyYearValue.split('-')[0],
        userId: this.localService.getData('userId'),
      };

      //call save api accordingly
      dialogRef.afterClosed().subscribe((confirmed: boolean) => {
        if (confirmed) {

          this.institutionmanagement
            .postStandaloneEditData(userData)
            .subscribe((res) => {
              this.submitted = false;
              if (res.statusCode == 'AISH011') {
                this.sharedservice.showSuccessMessage(
                  'Editing Standalone Data not allowed'
                );
                //this.form.reset();
              }
              if (res.statusCode == 'AISH102') {
                this.sharedservice.showError(
                  'Institution Name Already Exists'
                );
                //this.form.reset();
              }
              if (res.statusCode == 'AISH001') {
                this.sharedservice.showSuccessMessage(
                  'Standalone Data Edited Successfully'
                );


                this.findData();
                this.backClicked();
                this.form.reset();
               // userData='';
                //this.router.navigate(['/aishe/Institution-Management']);
              }
            });
        }
      });
    }
  }


  }

  surveyYear() {
    this.institutionmanagement.getSurveyYearListPrivate().subscribe((res) => {
      this.surveyYearList = res;
      //console.log("this.surveyYearList:",this.surveyYearList)
    });
  }
}
