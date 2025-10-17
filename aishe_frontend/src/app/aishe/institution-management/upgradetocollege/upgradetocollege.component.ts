import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { UniversityService } from 'src/app/service/reports/university.service';
import { SharedService } from 'src/app/shared/shared.service';
import { Common } from 'src/app/common/common';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'app-upgradetocollege',
  templateUrl: './upgradetocollege.component.html',
  styleUrls: ['./upgradetocollege.component.scss']
})
export class UpgradetocollegeComponent implements OnInit {


  @Input()
  instiFormData:any;
   userId:any;
   aisheCode:any=''
   showclgUpgrade:boolean = false;
   obj:any;
   validation:boolean = false;
   surveyYear:any
   submitted:boolean =false;
   searchText:any;
   page: number = 1;
   pageSize: any = 10;
   pageData: number = 10;
   EndLimit: number = 20;
   StartLimit: number = 0;
   tableSize: number[] = [10, 15, 20];
   showTable:boolean=false
 showDataTable:any=false;
 dataCollege:any;
 showSimilarCollege:boolean = false;
   count: number = 0;
   alldata: any;
   isApproveList1:boolean=false;
   dataSimilarCollege:any[] = [];
   isUpgradetoClg:boolean = false;


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
  form = new FormGroup({
    collegeName: new FormControl('', [Validators.required]),
    collegeType: new FormControl('', Validators.required),
    stateName: new FormControl('', Validators.required),
    districtName: new FormControl('', Validators.required),
    remarks: new FormControl('',[
      Validators.required,
       Validators.maxLength(2000), 
      //  Validators.minLength(3),
       Common.noWhitespaceValidator]),
    managementType:new FormControl('', Validators.required),
    surveyYearValue:new FormControl('', Validators.required),
    universityType:new FormControl('',Validators.required),
    collegeStateName:new FormControl('',Validators.required),   
    isDCFApplicable:new FormControl(),
    location: new FormControl('', Validators.required)
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
    this.findManagmentType();
    this.institutionmanagement.getCollegeType().subscribe((res) => {
      this.collegeTypeData = res;
      this.filteredcollegeTypeData = this.collegeTypeData.slice();
    });
  
    this.surveyYears()
    this.upgradeList();
    this.getLocation();

  }

  getLocation() {
    this.institutionmanagement.getLocation().subscribe((res) => {
      this.locationData = res.location;
    });
  }


  alphaNumberOnly (e:any) { 
    // Accept only alpha numerics, not special characters 
   var regex = new RegExp("[a-zA-Z ]");
   var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
   if (regex.test(str)) {
   
   if(e.charCode==='32'){
   
     return true;
   }else if (e.target.value.substr(-1) === ' ' && e.code === 'Space') {
   // 
     e.preventDefault();
    } 
    this.count+=1;
     return true;
 }
   e.preventDefault();
   return false;
 }

  resetdata(){
    this.form.reset();
    this.showDataTable=[];
    this.pageSize=10;
    this.page = 1;
    this.searchText=null;
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
  findManagmentType() {
    let payload={type:'S'}
    this.institutionmanagement.getmanagmentType2(payload).subscribe((res)=>{
    this.managmentData=res
    this.filteredmanagementT=this.managmentData.slice();
    });
  }
  findData1(){
    this.userDataTable = [];
  }


  loadUniversityData(){ 
  if(this.form.value.surveyYearValue){
    this.UniSurveyYear=this.form.value.surveyYearValue.split('-')[0];
  }
     
      this.state=  this.roleId=='1' || this.roleId=='26' || this.roleId=='6' ? this.form.value?.stateName?.stateCode :this.localService.getData('stateCode') ;
      if(this.UniSurveyYear && this.state){
      this.institutionmanagement.getUniversity(this.state, this.UniSurveyYear).subscribe({
        next: (res) =>{
          this.UniversityList = res;
          // console.log("sc", this.UniversityList)
          this.filteredUniversityList = this.UniversityList.slice();
        }
      })
    }

  }
  getdistrictName() {
    let stateName1 = this.userData.filter((data:any) => data.name === this.form.value?.collegeStateName?.name);

    this.institutionmanagement
      .getdistrict( stateName1['0']?.stateCode)
      .subscribe((res) => {
        this.dataDistrict = res;
        this.filteredDistrict = this.dataDistrict.slice();
      });
  }

  backClicked(): void {
    this.showTable = true
    this.isUpgradetoClg = false;
    this.form.reset();
    this.showclgUpgrade = false;
    this.showMergeTable = false;
  }



  upgradeList(){
    this.searchText=null;
    var formData;
    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    // if(!this.instiFormData.value.surveyYearValue){
    //   this.sharedservice.showError('Please Select Survey Year');
    //   return;
    //  }
    if((!this.instiFormData.value.stateValue&& this.localService.getData('roleId')=='26'||!this.instiFormData.value.stateValue&&this.localService.getData('roleId')=='1') && this.instiFormData.value.aisheCodeValue==null){
      this.sharedservice.showError('Please Select State');
      return;
     }
    formData={
      "standaloneType":this.instiFormData.value.standaloneType==' '?this.instiFormData.value.standaloneType.trim():this.instiFormData.value.standaloneType,
      "surveyYearValue":this.instiFormData.value.surveyYearValue?this.instiFormData.value.surveyYearValue.split('-')[0]:'',
      "standaloneValue":this.instiFormData.value.standaloneValue,
      "selectedStateCode":this.instiFormData.value.stateValue && this.localService.getData('roleId')=='26'|| this.instiFormData.value.stateValue&&this.localService.getData('roleId')=='1'? this.instiFormData.value.stateValue.trim():this.localService.getData('stateCode'),
      "districtValue":this.instiFormData.value.districtValue?this.instiFormData.value.districtValue.trim():'',
      "aisheCodeValue":this.instiFormData.value.aisheCodeValue?this.instiFormData.value.aisheCodeValue:'',
    };
    this.institutionmanagement.getStandaloneViewData(formData).subscribe((res) =>{
      
      // this.showTable = true;
      // console.log(this.showDataTable);
      // this.showDataTable =res.standaloneCollegeListBean
      // this.page = 1;
      // this.handlePageChange(this.page);
      if(res.statusCode ==="AISH099"){
        this.sharedservice.showError(res.statusDesc)
        this.showDataTable=[];
        this.showTable=true;
        this.page = 1;
        this.handlePageChange(this.page);
      }
      if(res.statusCode==="AISH001"){
        this.showDataTable=res.standaloneCollegeListBean;
        this.showTable=true;
        this.page = 1;
        this.handlePageChange(this.page);
      }
    })
  }
  
    
    
         clgUpgrade(data:any) {
          // console.log(data)
          this.headerData = data;
          this.collegeName = data.name
          this.showMergeTable=true;
          this.isUpgradetoClg = true;
          this.showclgUpgrade = true;
          this.showTable = false;
          this.aisheCode = data.aishecode.toString()
            }



            submit(){
              // console.log(this.form)
              if(this.validation == true){
                this.form.reset()
                this.validation = false;
               }
              this.submitted = true;
              if (this.form.valid) {
               let location1= this.locationData.filter((data:any) => data.id===this.form.value.location) ;
                const dialogRef = this.dialog.open(ConfirmDialogComponent, {
                  data: {
                    message:
                      'Details of the Institute are as below, Please confirm the detailss.',
                    state: this.form.value.collegeStateName.name,
                    district: this.form.value.districtName.name,
                    Name: this.form.value.collegeName.trim(),
                    remarks: this.form.value.remarks.trim(),
                    newCollegeType:this.form.value.collegeType.type,
                    university: this.form.value.universityType.name,
                    surveyYear:this.form.value.surveyYearValue.slice(0,4).trim(),
                    location: location1[0].name,
                    isDCFApplicable:this.form.value.isDCFApplicable? this.form.value.isDCFApplicable:'false',
                    managementType :this.form.value?.managementType?.managementType,
                    universityType: 'mergeUniversityType',
                  },
                });
                
                 
                 let userData  = { 
                  standaloneId:this.aisheCode,
                  collegeDistrictCode:this.form.value.districtName.distCode,
                  collegeIsDcfApplicable:this.form.value.isDCFApplicable? this.form.value.isDCFApplicable:'false',
                  collegeName:this.form.value.collegeName.trim(),
                  collegeStateCode: this.form.value.collegeStateName.stateCode,
                  collegeTypeId:this.form.value.collegeType.id,
                  collegeUniversityId:this.form.value.universityType.id,
                  remarks:this.form.value.remarks.trim(),
                  location:this.form.value.location,
                  collegeManagementId :this.form.value?.managementType?.id,
                  surveyYear:this.form.value.surveyYearValue.slice(0,4).trim(),
                  userId:this.userId
                 }
          
                  //call save api accordingly
                  dialogRef.afterClosed().subscribe((confirmed: boolean) => {
                    if (confirmed) {
                      this.institutionmanagement
                        .upGradeStandaloneToCollege(userData)
                        .subscribe((res) => {
                          this.submitted = false;
                          this.validation= true;
                          if (res.statusCode == 'AISH011') {
                            this.sharedservice.showError(
                              'This institution has already participated in the Current AISHE Survey.Please delete the survey data in order to upgrade to college.'
                            );
                          }
                          if (res.statusCode == 'AISH001') {
                     
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
                            this.isUpgradetoClg  = false
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
                          this.loadUniversityData();
                          this.backClicked();
                          this.upgradeList();
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
