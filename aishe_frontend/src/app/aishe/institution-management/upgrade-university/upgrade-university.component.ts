import { Component, Input, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared/shared.service';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { FormGroup, FormControl, AbstractControl, Validators } from '@angular/forms';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { Common } from 'src/app/common/common';
import { LocalserviceService } from 'src/app/service/localservice.service';
@Component({
  selector: 'app-upgrade-university',
  templateUrl: './upgrade-university.component.html',
  styleUrls: ['./upgrade-university.component.scss']
})
export class UpgradeUniversityComponent implements OnInit {
  @Input()
  instiFormData: any;
  dataDeaffiliation: any[] = [];
  reasonName: string[] = [];
  state:any
  id:any
  isDeAffiliate: boolean = false;
  showEditTables:boolean = false
  surveyYear1:any
  count: number = 0;
  dataDistrict:any
  universityList:any;
  UniSurveyYear:any
  tabledata: boolean = true;
  userData1: any = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  hostel: any[] = [];
  roleId:any;
  userData: any = [];
  headerData:any;
  userDataTable:any = [];
  isUpgradeData:boolean=false
  managmentData:any;
  managementType:any;
  filteredmanagementT:any[]=[];
  tableSize: number[] = [10, 20, 30, 40, 50];
  submitted = false;
  usercollegeTypeData: any[] = [];
  dataUniversityId1: any[] = [];
  surveyYearList: any;
  selectedYearValue: any = '';
  showTable: boolean = false;
  surveyYearValue:any
  collegeTypeData:any
  collegeName: any | null;
  collegeType: any | null;
  districtName: any | null;
  filteredDistrict: any;
  userId:any;
  stateName: any;
  collegeName1:any
  filteredcollegeTypeData:any
  filtereduniversityList:any
  filteredState:any;
  filterDeaffiliation: any;
  UpgradeData: boolean = false;
  universityType:any;
  universityTypefilter:any
  
  form = new FormGroup({
    universityName: new FormControl('',[Validators.required]),
    stateName: new FormControl('',[Validators.required]),
    districtName: new FormControl('',[Validators.required]),
    remarks: new FormControl('', [
      Validators.required,
      Validators.maxLength(2000),
      // Validators.minLength(3)
    ]),
    isDCFApplicable:new FormControl(),
    location: new FormControl('',[Validators.required]),
    universityType: new FormControl('',Validators.required),
    surveyYear:new FormControl('',Validators.required),
  });
  isupgradeUniversity: boolean =false;
  remarks: any;
  surveyY: any;
  deafiliationReason: any;
  locationData:any;
  isFormInvalid: any;
  
  constructor(
    public sharedservice: SharedService,
    private dialog: MatDialog,
    private institutionmanagement: InstitutionmanagementService,public localService:LocalserviceService
  ) {}
  ngOnInit(): void {
    this.roleId = this.localService.getData('roleId')
    this.userId = this.localService.getData('userId');
    this.findData()  
    this.getDeaffiliationReason()
    this.surveyYear()
   // this.getdistrictName()
    this.getState()
    this.loadUniversityType()
    this.getLocation()
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }
  getLocation() {
    this.institutionmanagement.getLocation().subscribe((res) => {
      this.locationData = res.location;
    });
  }
  getDeaffiliationReason() {
    this.institutionmanagement.getReasonDeaffileate().subscribe((res) => {
      this.dataDeaffiliation = res.reasonId;
      this.filterDeaffiliation = this.dataDeaffiliation.slice();
    });
  }
  loadUniversityType(){
    this.institutionmanagement.getUniversityType().subscribe((res)=>{
    this.universityType = res;
    this.universityTypefilter=this.universityType.slice();
    });
    }
   getdistrictName() {
    let stateName1 = this.userData.filter(
      (data:any) => data.name === this.form.value.stateName.name
    );

    this.institutionmanagement
      .getdistrict( stateName1['0'].stateCode)
      .subscribe((res) => {
        this.dataDistrict = res;
        this.filteredDistrict = this.dataDistrict.slice();
      });
  }
  alphaNumberOnly (e:any) {  // Accept only alpha numerics, not special characters 

    // console.log("alphaNumberOnly", e.key);
    var regex = new RegExp("[a-zA-Z ]");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
       this.count+=1;
      if(e.target.value.substr(-1) === ' ' && this.count ===1 && e.key===' '){
      
        return true;
      }else if (e.target.value.substr(-1) === ' ' && e.code === 'Space') {
      // 
        e.preventDefault();
       } 
        return true;
    }

    e.preventDefault();
    return false;
  }

  compareFn(t1: any, t2: any): boolean {

    return t1 && t2 ? t1 === t2 : t1 === t2;
    }
      getState(){
        this.institutionmanagement.getState().subscribe((res) => {
          this.userData = res;
          this.filteredState = this.userData.slice();
        });
      }
      surveyYear() {
        this.institutionmanagement.getSurveyYearList().subscribe((res) => {
          this.surveyYearList = res;
        });
      }



  resetbutton(){
    this.userDataTable = [];
    this.pageSize = 10;
    this.searchText=null;
    this.page = 1;
    this.handlePageChange(this.page);
  }
  loadUniversityData(){ 
    if(this.form.value.surveyYearValue){
      this.UniSurveyYear=this.form.value.surveyYearValue.split('-')[0];
    }}
  findData(){
    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    this.searchText=null;
     this.resetbutton();
    //console.log(this.instiFormData.value);
       if(this.instiFormData.value.aisheCodeValue){
        var aisheId=this.instiFormData.value.aisheCodeValue;
       }
      
    // if(!this.instiFormData.value.surveyYearValue){
    //   this.sharedservice.showError('Please Select Survey Year')
    //   return;
    // }
  if(this.instiFormData.value.aisheCode){
    if(!this.instiFormData.value.stateValue ){
      this.sharedservice.showError('Please Select State')
      return;
    }
  }

    // if(!this.instiFormData.value.universityValue ){
    //   this.sharedservice.showError('Please Select University Name')
    //   return;
    // }
    var formData;
    if(this.roleId==='26' || this.roleId==='1'){
      formData={
       "collegeType":this.instiFormData.value.collegeType?this.instiFormData.value.collegeType:'',
       "surveyYearValue":'',
       "universityValue":'',
       "locationType":this.instiFormData.value.locationType,
       "selectedStateCode":this.instiFormData.value.stateValue?this.instiFormData.value.stateValue.trim():'',
       "districtValue":this.instiFormData.value.districtValue?this.instiFormData.value.districtValue.trim():'',
       "reasonName":this.instiFormData.value.deafiliationReason,
       "aisheCodeValue":aisheId?aisheId:'',
       "reasonId":this.instiFormData.value.reasonId ?? '' 
     };
   }
  
    
       // console.log('this.localService.getData(',formData)
      
        this.institutionmanagement.getDeaffiliationCollegeList(formData).subscribe((res) =>{
          
          this.showTable = true;
          this.userDataTable =res.deaffiliationCollegeListBeans
        
          this.isUpgradeData=false;
          this.page = 1;
          this.handlePageChange(this.page);

          if(res.statusCode=="AISH099"){    
              this.sharedservice.showError(res.statusDesc)
          }
    
        })
      }

      back(): void {
        this.showTable = true;
        this.UpgradeData = false;
        this.isupgradeUniversity = false;
        this.form.reset();
        this.submitted = false;
      }
   
      upgradeUniversity(data: any) {
        this.isupgradeUniversity=true;
        this.showTable = false;
        this.UpgradeData = true;
        this.collegeName = data.collegeName;
        this.districtName = data.districtName;
        this.stateName = data.stateName;
        this.remarks=data.remarks;
        this.reasonName=data.reasonName;
        this.collegeType=data.collegeType;
      //  this.instiFormData.value.surveyYearValue.split('-')[0],
        this.id = data.id;
        this.headerData= data;
        this.universityType=data.universityType;
        this.surveyY = data.surveyYear;
        // this.form.controls['reasonName'].setValue('');
        // this.form.controls['reasonRemarks'].setValue('');
      }
      update() {
        this.submitted = true;
    
        let location1 = this.locationData.filter((data :any) => data.id === this.form.value.location);
        if (this.form.valid) {
               
          const dialogRef = this.dialog.open(ConfirmDialogComponent, {
            data: {
              message:
                'Details of the Institute are as below, Please confirm the detailss.',
              state: this.form.value.stateName.name ,
              district: this.form.value.districtName.name,
              Name: this.headerData.collegeName.trim(),
              remarks: this.form.value.remarks.trim(),
              universitytype:this.form.value.universityType.type,
              universityName: this.form.value.universityName,
              location: location1[0].name,
              surveyYear:this.form.value.surveyYear.slice(0,4).trim(),
              isDCFApplicable:this.form.value.isDCFApplicable?'Yes':'No',
           
              universityType: 'upgradeUniversity',
            },
          });
          
           
           let userData  = { 
            newUniversityName:this.form.value.universityName.trim(),
            newUniversitydistrictCode:this.form.value.districtName.distCode,
            newUniversityisDcfApplicable:this.form.value.isDCFApplicable? this.form.value.isDCFApplicable:'false',
            newUniversitystateCode:this.form.value.stateName.stateCode,
            newUniversitytypeId:this.form.value.universityType.id,
            oldCollegeId:this.headerData.id,
            newUniversityLocation:location1[0].id,
            remarks:this.form.value.remarks.trim(),
            surveyYear:this.form.value.surveyYear.slice(0,4),
            userId:this.userId
           }
    
            //call save api accordingly
            dialogRef.afterClosed().subscribe((confirmed: boolean) => {
              if (confirmed) {
                this.institutionmanagement
                  .postUpgradeUniversity(userData)
                  .subscribe((res) => {
                    this.submitted = false;
                    if (res.statusCode == 'AISH011') {
                      this.sharedservice.showError(
                        'This institution has already participated in the Current AISHE Survey.Please delete the survey data in order to upgrade to university.'
                      );
                    }
                    if (res.statusCode == 'AISH001') {
              // console.log(res.university);
                      this.form.reset();
                      // this.findData();
                      // this.back();
                      const dialogRef = this.dialog.open(ConfirmDialogComponent,{
                        data: {
                          message1:'Your request has been processed successfully!!',
                          data1: res.university,
                          action:'1',
                          showData:'upgradeUni12',
                        }
                      });
                      this.sharedservice.showSuccessMessage(
                        'College Data Added Successfully'
                      );
                      this.showTable = true;
                      this.isupgradeUniversity  = false
                    }
    
                    if (res.statusCode == 'AISH102') {
                      this.sharedservice.showError(
                        'University Name Already Exists'
                      );
    
                    }
                    if (res.statusCode == 'AISH002') {
                      this.sharedservice.showError(res.statusDesc);
                    }
                    //this.loadUniversityData();
                    this.back();
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
  
      
}



