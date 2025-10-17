import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Common } from 'src/app/common/common';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { UniversityService } from 'src/app/service/reports/university.service';
import { SharedService } from 'src/app/shared/shared.service';
@Component({
  selector: 'app-upgrade-standaloneto-university',
  templateUrl: './upgrade-standaloneto-university.component.html',
  styleUrls: ['./upgrade-standaloneto-university.component.scss']
})
export class UpgradeStandalonetoUniversityComponent implements OnInit {
  @Input()
  instiFormData:any;
   userId:any;
   aisheCode:any=''
   showclgUpgrade:boolean = false;
   obj:any;
   surveyYear:any
   submitted:boolean =false;
   searchText:any;
   page: number = 1;
   pageSize: any = 5;
   pageData: number = 5;
   EndLimit: number = 15;
   StartLimit: number = 0;
   tableSize: number[] = [5, 10, 15, 20];
   showTable:boolean=false
  showDataTable: any = false;
  dataCollege: any;
  showSimilarCollege: boolean = false;
  count: number = 0;
  alldata: any;
  isApproveList1: boolean = false;
  dataSimilarCollege: any[] = [];
  isUpgradetoUniversity: boolean = false;
  validation: boolean = false;

   filteredDistrict: any;
  state:any;
  id:any
  selectedColleges:Array<any> = [];
  showMergeTable:boolean = false
  surveyYear1:any
  dataDistrict:any
  UniversityList:any;
  universityList:any;
  UniSurveyYear:any

  roleId:any;

  userData: any = [];
  headerData:any=''
  userDataTable:any = [];
  isMerge:boolean=false
  managmentData:any;
  managementType:any;
  filteredmanagementT:any[]=[];

  surveyYearList: any;

  surveyYearValue:any
  collegeTypeData:any
  collegeName: any | null;
  collegeType: any | null;
  districtName: any | null;
  stateName: any | null;
  filteredcollegeTypeData:any
  filtereduniversityList:any
  filteredUniversityList:any
  filteredState:any;
  locationData:any;
  validPattern = /^[a-zA-Z !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{2,200}$/;
  form = new FormGroup({
    universityName: new FormControl('', [Validators.required ,Validators.pattern(this.validPattern),Common.noWhitespaceValidator]),

    stateName: new FormControl('', Validators.required),
    districtName: new FormControl('', Validators.required),
    remarks: new FormControl('',[
      Validators.required, 
      Validators.maxLength(2000),
      //  Validators.minLength(3),
       Common.noWhitespaceValidator]),

    surveyYearValue:new FormControl('', Validators.required),
    universityType:new FormControl('', Validators.required),
    location:new FormControl('',Validators.required),   
    isDCFApplicable:new FormControl()
  });
  isFormInvalid: any;
   constructor(private institutionmanagement:InstitutionmanagementService,
     public sharedservice: SharedService ,
     private dialog: MatDialog,
  public universityService: UniversityService,public localService:LocalserviceService) {
      
     }

  ngOnInit(): void {
    this.roleId = this.localService.getData('roleId');
    this.userId = this.localService.getData('userId');
    this.loadUniversityType()
    this.surveyYears()
    this.upgradeUniversityList();
    this.getLocation();
  }
  

  getLocation() {
    this.institutionmanagement.getLocation().subscribe((res) => {
      this.locationData = res.location;
    });
  }
  loadUniversityType(){
    this.institutionmanagement.getUniversityType().subscribe((res)=>{
    this.UniversityList = res;
    this.filteredUniversityList=this.UniversityList.slice();
    });
    }
  
  resetdata(){
    this.form.reset();
    this.showDataTable=[];
    this.pageSize = 10;
    this.searchText=[];
    this.page = 1;
    this.handlePageChange(this.page);
  }

  getState(){
    this.institutionmanagement.getState().subscribe((res) => {
      this.userData = res;
      this.filteredState = this.userData.slice();
    });
  }

  surveyYears() {
    this.institutionmanagement.getSurveyYearList().subscribe((res) => {
      this.surveyYearList = res;
    });
  }
  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }




  // loadUniversityData(){ 
  // if(this.form.value.surveyYearValue){
  //   this.UniSurveyYear=this.form.value.surveyYearValue.split('-')[0];
  // }
     
  //     this.state=  this.roleId=='1' || this.roleId=='26' ? this.form.value.stateName.stateCode :this.localService.getData('stateCode') ;
  //     if(this.UniSurveyYear && this.state){
  //     this.institutionmanagement.getUniversity(this.state, this.UniSurveyYear).subscribe({
  //       next: (res) =>{
  //         this.UniversityList = res;
  //         console.log("sc", this.UniversityList)
  //         this.filteredUniversityList = this.UniversityList.slice();
  //       }
  //     })
  //   }

  // }
  getdistrictName() {
    let stateName1 = this.userData.filter(
      (data:any) => data.name === this.form.value?.stateName?.name
    );

    this.institutionmanagement
      .getdistrict( stateName1['0'].stateCode)
      .subscribe((res) => {
        this.dataDistrict = res;
        this.filteredDistrict = this.dataDistrict.slice();
      });
  }

  backClicked(): void {
    this.showTable = true
    // this.showDataTable=false;
    this.isUpgradetoUniversity = false;
  this.form.reset();
this.submitted = false;
  this.showclgUpgrade =false;
  this.showMergeTable =false;
  }



  upgradeUniversityList(){
    this.searchText=null;
    var formData;
    // if(!this.instiFormData.value.surveyYearValue){
    //   this.sharedservice.showError('Please Select Survey Year');
    //   return;
    //  }
    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    if((!this.instiFormData.value.stateValue&& this.localService.getData('roleId')=='26'|| !this.instiFormData.value.stateValue&&this.localService.getData('roleId')=='1') && this.instiFormData.value.aisheCodeValue==null){
      this.sharedservice.showError('Please Select State');
      return;
     }
    formData={
      "standaloneType":this.instiFormData.value.standaloneType==' '?this.instiFormData.value.standaloneType.trim():this.instiFormData.value.standaloneType,
      "surveyYearValue":this.instiFormData.value.surveyYearValue?this.instiFormData.value.surveyYearValue.split('-')[0]:'',
      "standaloneValue":this.instiFormData.value.standaloneValue?this.instiFormData.value.standaloneValue:'',
      "selectedStateCode":this.instiFormData.value.stateValue && this.localService.getData('roleId')=='26'|| this.localService.getData('roleId')=='1'? this.instiFormData.value.stateValue.trim():this.localService.getData('stateCode'),
      "districtValue":this.instiFormData.value.districtValue?this.instiFormData.value.districtValue.trim():'',
      "aisheCodeValue":this.instiFormData.value.aisheCodeValue?this.instiFormData.value.aisheCodeValue:'',
    };
    this.institutionmanagement.getStandaloneViewData(formData).subscribe((res) =>{
      if(res.statusCode==="AISH001"){
        this.showTable = true;
        this.showDataTable =res.standaloneCollegeListBean
        this.page = 1;
        this.handlePageChange(this.page);
      }
      if(res.statusCode==="AISH099"){
        this.page = 1;
        this.handlePageChange(this.page);
        this.showDataTable=[];
        this.showTable = true;
        this.sharedservice.showError(
          res.statusDesc
        );
      }
      
    })
  }
    
         clgUpgrade(data:any) {
          this.headerData = data;
          this.showMergeTable=true;
          this.isUpgradetoUniversity = true;
          this.showclgUpgrade = true;
          this.showTable = false;
          this.aisheCode = data.aishecode.toString()
            }



            submit(){
              if(this.validation == true){
                this.form.reset()
                this.validation = false;
               }
              this.submitted = true;
              if (this.form.valid) {
               let location = this.locationData.filter((location:any)=>location.id == this.form.value.location);
                const dialogRef = this.dialog.open(ConfirmDialogComponent, {
                  data: {
                    message:
                      'Details of the Institute are as below, Please confirm the detailss.',
                      state: this.form.value.stateName.name ,
                      district: this.form.value.districtName.name,
                       Name: this.form.value.universityName.trim(),
                      remarks: this.form.value.remarks.trim(),
                      location: location[0].name,
                      university:this.form.value.universityType.type,
                      surveyYear:this.form.value.surveyYearValue.slice(0,4).trim(),
                      isDCFApplicable:this.form.value.isDCFApplicable? this.form.value.isDCFApplicable:'false',
               
                    universityType: 'standaloneUniversity',
                  },
                });
                
                 
                 let userData  = { 
                  newUniversityName:this.form.value.universityName.trim(),
                  newUniversitydistrictCode:this.form.value.districtName.distCode,
                  newUniversityisDcfApplicable:this.form.value.isDCFApplicable? this.form.value.isDCFApplicable:'false',
                  newUniversitystateCode:this.form.value.stateName.stateCode,
                  newUniversitytypeId:this.form.value.universityType.id,
                  oldStandaloneId:this.headerData.aishecode.toString(),
                  location:this.form.value.location,
                  remarks:this.form.value.remarks.trim(),
                  surveyYear:this.form.value.surveyYearValue.slice(0,4).trim(),
                  userId:this.userId
                 }
          
                  //call save api accordingly
                  dialogRef.afterClosed().subscribe((confirmed: boolean) => {
                    if (confirmed) {
                      this.institutionmanagement
                        .upgradeStandalonetoUniversity(userData)
                        .subscribe((res) => {
                          this.submitted = false;
                          this.validation= true;
                          if (res.statusCode == 'AISH011') {
                            this.sharedservice.showError(
                              'This institution has already participated in the Current AISHE Survey.Please delete the survey data in order to upgrade to university.'
                            );
                          }
                          if (res.statusCode == 'AISH001') {
                            this.createValidationForm();
                            this.form.reset();
                           
                            const dialogRef = this.dialog.open(ConfirmDialogComponent,{
                              data: {
                                message1:'Your request has been processed successfully!!',
                                data1: res.university
                                ,
                                action:'1',
                                showData:'standaloneUniversity',
                              }
                            });
                            this.sharedservice.showSuccessMessage(
                              'Standalone Upgraded to University Successfully'
                            );
                            this.showTable = true;
                            this.isUpgradetoUniversity  = false
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
                          this.loadUniversityType();
                          this.backClicked();
                          this.upgradeUniversityList();
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
            this.page=1;
            this.handlePageChange(this.page);
          }
          handlePageChange(event: number) {
    
            this.page = event;
            let fgh = parseInt(this.pageSize);
            (this.StartLimit = (this.page - 1) * fgh),
              (this.EndLimit = this.StartLimit + fgh);
            var a = Math.ceil(this.showDataTable.length / fgh);
            if (a === event) {
              this.pageData = Math.min(this.StartLimit + fgh, this.showDataTable.length);
            } else {
              this.pageData = Math.min(this.StartLimit + fgh, this.showDataTable.length);
            }
          }
    
}
