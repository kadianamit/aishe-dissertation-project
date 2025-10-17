import { Component,Input,OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import { AuthService } from 'src/app/service/auth.service';
import { Router } from '@angular/router';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'app-new-affiliation',
  templateUrl: './new-affiliation.component.html',
  styleUrls: ['./new-affiliation.component.scss']
})
export class NewAffiliationComponent implements OnInit {

  @Input()
  instiFormData:any;
  remarks:any
  affiliateData1: boolean = false;
  findtable: boolean = false;
  showTable: boolean=false;
  showformdata: boolean = false;
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 15;
  count: number = 0;
  pageSize: any = 10;
  searchText:any;
  alldata: any;
  page: number = 1;
  tableSize: number[] = [10,20,30,40,50];
  surveyYearList: any;
  selectedYearValue:any
  id:any
  collegeName:any
  collegeType:any
  districtName:any;
  reasonName:any;
  stateName:any
  universityName:any
  universityId:any
  submitted: boolean= false;
  selectYear: string="--";
  aisheCode: string="";
  selectedState: string="--";
  selectedUniversity:string="--";
  selectedSurveyYear:any;
  surveyYearValue:any;
  UniSurveyYear: any;
  state: any;
  stateList:any;
  stateListfilter:any
  universityList:any;
  filtereduniversityList:any;
  isAffiliate:boolean=false;
  surveyYearCompare:any;
  roleId:any
  formData=new FormGroup({
    aisheCodeValue:new FormControl(),
    surveyYearValue: new FormControl('',Validators.required),
    universityValue: new FormControl('',Validators.required),
    remarks: new FormControl('', [
      Validators.required,
      Validators.maxLength(2000),
      // Validators.minLength(3),
     
    ]),
    stateValue: new FormControl('',Validators.required),

  });
  isFormInvalid: any;
  stateCode: any;
  constructor(private dialog: MatDialog,
    private instManagementService: InstitutionmanagementService,
    public sharedservice: SharedService,
    private institutionmanagement: InstitutionmanagementService,
    private authService: AuthService,public localService:LocalserviceService
  ) { 
    this.stateCode=this.localService.getData('stateCode');
    this.roleId=this.localService.getData('roleId');
    this.universityId=this.localService.getData('universityId');
   }

  ngOnInit(): void {

   this.loadSurveyYear();
    this.getdatalist();
    this.loadState();


  }

  get f(): { [key: string]: AbstractControl } {
    return this.formData.controls;
  }
  loadSurveyYear(){
    this.institutionmanagement.getSurveyYearList().subscribe({
      next:(res)=>{
        this.surveyYearList=res;
      }
    })
  }
  loadState() {
    this.institutionmanagement.getState().subscribe((res) => {
    this.stateList = res;
    this.stateListfilter=this.stateList.slice();
      });
  }

  loadUniversityData12(){
    if(this.formData.value.surveyYearValue){
      if(this.formData.value.surveyYearValue.split('-')[0] < this.surveyYearCompare ){
 
        this.sharedservice.showError("Please select a survery year above this survery year.");
        this.formData.controls['surveyYearValue'].reset();
        this.universityList=[];
        this.filtereduniversityList=[];
  return;
      }
    this.UniSurveyYear=this.formData.value.surveyYearValue.split('-')[0];
    this.state=  this.roleId=='6' ? this.localService.getData('stateCode'):this.formData.value.stateValue;
    if(this.UniSurveyYear && this.state){
    this.institutionmanagement.getUniversity(this.state, this.UniSurveyYear).subscribe({
      next: (res) =>{
        this.universityList = res;
        this.filtereduniversityList = this.universityList.slice();
      }
    })
  }
}
  }
  getdatalist() { //C-57671
    //  console.log(this.instiFormData)
    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    // if (!this.instiFormData.value.aisheCodeValue) {
    //   this.sharedservice.showError("Please enter AISHE Code.");
    // }
   
      // let aisheId = this.instiFormData.value?this.instiFormData.value.aisheCodeValue.replace(/\D/g, ''):'';
      //  console.log(this.instiFormData.value)
    let payload={
         "surveyYearValue":this.instiFormData.value.surveyYearValue?this.instiFormData.value.surveyYearValue.split('-')[0]:'',
         "aisheCodeValue":this.instiFormData.value.aisheCodeValue?this.instiFormData.value.aisheCodeValue:'',
         "stateValue":this.instiFormData.value.stateValue?this.instiFormData.value.stateValue.trim():'',
         "universityValue":this.instiFormData.value.universityValue?this.instiFormData.value.universityValue:'',
         "collegeType":this.instiFormData.value.collegeType?this.instiFormData.value.collegeType:'',
         "districtValue":this.instiFormData.value.districtValue?this.instiFormData.value.districtValue.trim():'',
         "reasonId":this.instiFormData.value.reasonId?this.instiFormData.value.reasonId:'',
    }
    payload.aisheCodeValue=payload?.aisheCodeValue?.trim();
    if(this.instiFormData.value.aisheCodeValue){
      payload.surveyYearValue='';
      payload.stateValue='';
      payload.universityValue='';
      payload.collegeType='';
      payload.districtValue='';
    }
    if(this.roleId=="6"){//SNO
        // payload.stateValue=this.stateCode;
        payload.districtValue=this.instiFormData.value.districtValue?this.instiFormData.value.districtValue:'';
        if(this.instiFormData.value.aisheCodeValue){
          payload.surveyYearValue='';
          payload.stateValue=''
          payload.universityValue='';
          payload.collegeType='';
          payload.districtValue='';
        }
    }
    if(this.roleId=="7"){//UNO
        // payload.stateValue=this.stateCode;
        payload.districtValue=this.instiFormData.value.districtValue?this.instiFormData.value.districtValue:'';
        if(this.instiFormData.value.aisheCodeValue){
          payload.surveyYearValue='';
          payload.stateValue='';
          payload.universityValue='';
          payload.collegeType='';
          payload.districtValue='';
        }
    }
    // console.log(payload)
      this.instManagementService.getDeaffiliationCollegeList12(payload).subscribe((res) => {
        if (res.statusCode === "AISH001") {
          this.showTable = true;
          this.alldata=[];
          this.alldata = res.deaffiliationCollegeListBeans

          this.pageSize=10;
          this.page = 1;
          this.handlePageChange(this.page);
          // this.showformdata = true;
          // this.isAffiliate = true;

          // this.id = res.deaffiliationCollegeListBeans[0].id;
          // this.collegeName = res.deaffiliationCollegeListBeans[0].collegeName;
          // this.collegeType = res.deaffiliationCollegeListBeans[0].collegeType;
          // this.reasonName = res.deaffiliationCollegeListBeans[0].reasonName;
          // this.districtName = res.deaffiliationCollegeListBeans[0].districtName;
          // this.stateName = res.deaffiliationCollegeListBeans[0].stateName;
          // this.universityName = res.deaffiliationCollegeListBeans[0].universityName;
          // this.universityId = res.deaffiliationCollegeListBeans[0].universityId;
          // this.surveyYearCompare = res.deaffiliationCollegeListBeans['0'].surveyYear
        }
        if (res.statusCode === 'AISH099') {
          this.sharedservice.showError(res.statusDesc);
          this.showTable = false
        }
      })
    
  }


  back(): void {
    this.showTable=true;
    this.showformdata=false;
    this.isAffiliate=false;
    this.formData.reset();
  }



  compareSurveyYear(e:any){
    // if(this.formData.value.surveyYearValue){
    //   if(this.surveyYearCompare > this.formData.value.surveyYearValue.split('-')[0]){
 
    //     this.sharedservice.showError("Please select a survery year above this survery year.");
    //     this.formData.controls['surveyYearValue'].reset();
  
    //   }
    //   return;
    // }
 
  }
  checkForWhiteSpace(){
    let aremark=this.formData.get('remarks')?.value?.trim();
    this.formData.get('remarks')?.setValue(aremark);
  }

  submitData(){ 
    // console.log(this.formData.value)
    this.checkForWhiteSpace();
    this.submitted=true;
    if (this.formData.value.surveyYearValue && this.formData.value.remarks.trim().length < 2001){
   
    if(!this.formData.value.surveyYearValue){
      this.sharedservice.showError('Please Select Survey Year')
      return;
    }
    if(!this.formData.value.stateValue && (this.roleId==='26'||this.roleId==='1')){
      this.sharedservice.showError('Please Select State')
      return;
    }
    if(!this.formData.value.universityValue && (this.roleId==='26'||this.roleId==='1')){
      this.sharedservice.showError('Please Select University')
      return;
    }
    if(!this.formData.value.remarks ){
      this.sharedservice.showError('Please Select Remarks')
      return;
    }

    if(this.formData.value.surveyYearValue && this.formData.value.remarks.trim()){
 
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '16%',
      data: {
        message1: 'Are you sure you want to Affiliated?',
        deAffiliate: 'deAffiliate',
      },
    })
    dialogRef.afterClosed().subscribe((confirm:any) => {
      if(confirm){
        let  aisheCode: any = this.localService.getData('aisheCode');
        let aisheCode1 = aisheCode.replace(/\D/g, '');
         let formData12 = {
             "collegeName": this.collegeName,
             "id":this.id,
           
             "remark": this.formData.value.remarks.trim(),
             "surveyYear":this.formData.value.surveyYearValue?this.formData.value.surveyYearValue.split('-')[0]:'',
             "universityId":this.formData.value?.universityValue?.id ? this.formData.value.universityValue.id:aisheCode1,
             "userId": this.localService.getData('userId'),
           }
    

   this.instManagementService.postCollegeAffilication(formData12).subscribe(
      {
        next: (res) => {
          if (res.statusCode == "AISH099") {
            this.sharedservice.showError("No records !!");

          }
          else if (res.statusCode == "AISH001") {

            this.sharedservice.showSuccessMessage('Affiliated successfully');
            this.localService.saveData('doRefresh','ok');
            //this.showTable=true;
            this.showformdata=false
            this.isAffiliate=false;
           // this.getdatalist();
            this.formData.reset();
          }
          else if (res.statusCode == "AISH011") {
            this.sharedservice.showError("Access not allowed");

          }
          else if (res.statusCode == "AISH101") {
            this.sharedservice.showError("College Already Affiliated");
          }

          else if (res.statusCode == "AISH100") {
            this.sharedservice.showError('Unexcepted error')

          }
        },
        error: (err) => {
          console.log(" exception: ", err)
          this.sharedservice.showError('Unexcepted error')
        }
      })
     
      
}
    
})}
    }else {
      this.sharedservice.showError('All fields are required'); 
    }
  }

  onConfirmClick(): void {

    // if (!this.selectedYearValue) {

    //   this.sharedservice.showError('Please select survey year');
    //   return;

    // }

  //  let formData12 = {
  //    "collegeName": this.collegeName,
  //    "id":this.id,
  //    "ipAddress": "string",
  //    "remark": remark,
  //    "surveyYear":this.selectedYearValue.split('-')[0],
  //    "universityId": this.universityId,
  //    "userId": this.localService.getData('userId'),
  //  }
//console.log('ertyuino io',this.formData.value);
    //let year = this.selectedYearValue.split('-')[0];//2021

    // this.instManagementService.postCollegeAffilication(formData12).subscribe(
    //   {
    //     next: (res) => {
    //       if (res.statusCode == "AISH099") {
    //         this.sharedservice.showError("No records !!");

    //       }
    //       else if (res.statusCode == "AISH001") {

    //         this.sharedservice.showSuccessMessage('Affiliated successfully');
    //         this.localService.saveData('doRefresh','ok');
    //         this.showTable=true;
    //         this.showformdata=false
    //         this.isAffiliate=false;
    //         this.getdatalist();
    //       }
    //       else if (res.statusCode == "AISH011") {
    //         this.sharedservice.showError("Access not allowed");

    //       }
    //       else if (res.statusCode == "AISH101") {
    //         this.sharedservice.showError("College Already Affiliated");
    //       }

    //       else if (res.statusCode == "AISH100") {
    //         this.sharedservice.showError('Unexcepted error')

    //       }
    //     },
    //     error: (err) => {
    //       console.log(" exception: ", err)
    //       this.sharedservice.showError('Unexcepted error')
    //     }
    //   }
    // );
  }


  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page=1;
    this.handlePageChange(this.page);
  }


  handlePageChange(event: any): void {
    this.page = event;
    let page1=parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * page1),
      (this.EndLimit = this.StartLimit + page1);
    var a = Math.ceil(this.alldata.length / page1);
    if (a === event) {
      this.pageData = Math.min(
        this.StartLimit + page1,
        this.alldata.length
      );
    } else {
      this.pageData = Math.min(
        this.StartLimit + page1,
        this.alldata.length - 1
      );
    }
  }
  deaffilate(data:any){
         this.showTable = false;
         this.showformdata = true;
         this.isAffiliate = true;

          this.id = data.id;
          this.collegeName = data.collegeName;
          this.collegeType = data.collegeType;
          this.reasonName = data.reasonName;
          this.districtName = data.districtName;
          this.stateName = data.stateName;
          this.universityName = data.universityName;
          this.universityId = data.universityId;
          this.surveyYearCompare = data.surveyYear
  }

}
