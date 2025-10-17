


import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { Common } from 'src/app/common/common';
import {Sort, MatSortModule} from '@angular/material/sort';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { ViewProgramComponent } from 'src/app/dialog/view-program/view-program.component';
import { GetService } from 'src/app/service/get/get.service';
import { Router } from '@angular/router';
const calculateColumnWidthsData = (data:any) => {
  return data[0].map((_: any, colIndex:any) =>
    ({ wch: Math.max(...data.map((row:any) => (row[colIndex] ? row[colIndex].toString().length : 0))) })
  );
};
@Component({
  selector: 'app-approve',
  templateUrl: './approve.component.html',
  styleUrls: ['./approve.component.scss'],
})
export class ApproveComponent implements OnInit {
  showView:boolean=false
  @Input()
  instiFormData: any;

  @Output() resetChild= new EventEmitter<any>()
  showTablesApprove: boolean = false;
  id: any;
  collegeNameA: any;
  labelData1: any[] = [];
  dataCollege: any[] = [];
  similarmatTableData: boolean = false;
  SimilarDataTableDataTable:Array<any>=[]
  requestId:any;
  idR: any;
  obj: any;
  isStatusId:any = 1;
  //  valueSNO:any;
  userForm = new FormGroup({
    Name:new FormControl('',[Validators.required]),
    stateName:new FormControl('',[Validators.required]),
    districtName:new FormControl('',[Validators.required]),
    instituteType:new FormControl('',[Validators.required]),
    managementType:new FormControl('',[Validators.required]),
    universityId:new FormControl('',[Validators.required]),

    surveyYear:new FormControl('',[Validators.required]),
    aremarks: new FormControl('', [
      Validators.required,
      Validators.maxLength(2000),
    ]),
    approveR: new FormControl('', Validators.required),
    alldocumentsvalid: new FormControl('', Validators.requiredTrue),
    location:new FormControl('same',[]),
    universityState:new FormControl('')
  });
  isSubmitted = false;
  affiliateData1: boolean = false;
  alldata: any[] = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 15;
  count: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  hostel: any[] = [];
  tableSize: number[] = [10, 20, 30, 40, 50];
  aisheCode: string = '';
  stateList: any;
  selectedState = '--';
  universityList: any;
  selectYear: any;
  approvalHistoryData: any;
  surveyYearValue: string = '';
  surveyYear: any = '';
  btnstate: boolean = false;
  showButton: boolean = false;
  showButton1: boolean = true;
  selectedYearValue: any = '';
  selectedStatus: any = '';
  showTable: boolean = false;
  roleId1: any;
  state: any;
  surveyYearList: any;
  filtereduniversityList: any;
  UniSurveyYear: any;
  isApprove: boolean = false;
  dataEmail: any;
  formData: any;
  Formdata = new FormGroup({
    selectedYearValue: new FormControl(),
    collegeType: new FormControl(),
    universityValue: new FormControl(),
  });

  institutionM: any;
  router: any;
  isFormInvalid: any;
  approvalResponse: any;
  stateCode: any;
  universityId: any;
  sortedData: any;
  requestStatus: any;

  submitted:any=false;
  NameData:any;
  userData:any;
  filteredState:any[] = [];
  dataDistrict:any
  filteredDistrict:any[] = [];
  dataCheck:boolean = false;
  standalonTypefilter:any[] = [];
  standalonTypeData:any;
  filteredmanagementT:any[]=[];
  managmentData:any;
  collegeTypeData: any[] = [];
  filteredcollegeTypeData: any;
  showViewDetailOnly: boolean=true;
  previousStateId: any;
  previousUniversityId: any;
  previousDistrictCode: any;
  previousFilteredState: any;
  previousBool: boolean=false;
  filteredState2: any;
  userData2: any;
  affiliatingUniversityStateCode: any;
  takeAction!: boolean;
  tableHeaders:any;
  viewDetailOnly=true;
  constructor(
    private dialog: MatDialog,
    private instManagementService: InstitutionmanagementService,
    public sharedservice: SharedService,public localService:LocalserviceService,
    private getService : GetService,private router1: Router
  ) {
    this.sharedservice.global_loader = true;
    this.roleId1 = this.localService.getData('roleId');
    this.stateCode=this.localService.getData('stateCode');
    this.universityId=this.localService.getData('universityId');
  }

  ngOnInit(): void {
    this.getApproveList();
    this.loadSurveyYear();
    this.findManagmentType();
    this.getSubrolls();
    this.getStateData();
    if(this.roleId1 ==='26' || this.roleId1 === '1'){
      this.userForm.get('universityId')?.setValidators([Validators.required]);
      this.userForm.get('universityId')?.updateValueAndValidity();
      this.getCollegeTypeData();
    }else{
      this.userForm.get('Name')?.clearValidators();
      this.userForm.get('Name')?.updateValueAndValidity();
      this.userForm.get('stateName')?.clearValidators();
      this.userForm.get('stateName')?.updateValueAndValidity();
      this.userForm.get('districtName')?.clearValidators();
      this.userForm.get('districtName')?.updateValueAndValidity();
      this.userForm.get('instituteType')?.clearValidators();
      this.userForm.get('instituteType')?.updateValueAndValidity();
      this.userForm.get('managementType')?.clearValidators();
      this.userForm.get('managementType')?.updateValueAndValidity();
      this.userForm.get('surveyYear')?.clearValidators();
      this.userForm.get('surveyYear')?.updateValueAndValidity();
      this.userForm.get('universityId')?.clearValidators();
      this.userForm.get('universityId')?.updateValueAndValidity();
    }
  }

  get f(): { [key: string]: AbstractControl } {
    return this.userForm.controls;
  }

  resetData() {
    this.alldata = [];
    this.page = 1;
    this.pageSize = 10;
    this.searchText = null;
    this.handlePageChange(this.page);
  }
  surveyYearData() {
    this.instManagementService.getSurveyYearList().subscribe((res) => {
      this.surveyYearList = res;
    });
  }

  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  getApproveList() {
    console.log(this.instiFormData.value)
    // console.log(this.formatDate(this.instiFormData.value.toDate));
    let toDate:any=null;let fromDate:any=null;

    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
      this.takeAction=this.instiFormData.get('pendingLevel')?.value!=3 && this.instiFormData.get('pendingLevel')?.value!=4 && this.instiFormData.get('pendingLevel')?.value!=1;
    }
    if(this.instiFormData.value.toDate!=null && this.instiFormData.value.toDate!=''){
     toDate=this.formatDate(this.instiFormData.value.toDate)
    }
    if(this.instiFormData.value.fromDate!=null && this.instiFormData.value.fromDate!=''){
      fromDate=this.formatDate(this.instiFormData.value.fromDate)
    }
    this.searchText = null;
    let universityValue = this.instiFormData.value.universityValue!=null ? this.instiFormData.value.universityValue.trim() : '';
    
    let stateCode:any='';
    if(this.roleId1=='26' || this.roleId1=='1'){
      stateCode=this.instiFormData.value.stateValue ? this.instiFormData.value.stateValue.trim() : '';
    }
    //MoE case
    if (this.localService.getData('roleId') == '26' || this.localService.getData('roleId') == '1') {
      if (!this.instiFormData.value.surveyYearValue && !this.instiFormData.value.RequestId) {
        this.sharedservice.showError('Please Select Survey Year');
        return;
       }
      // if (
      //   !this.instiFormData.value.stateValue &&
      //   !this.instiFormData.value.RequestId
      // ) {
      //   this.sharedservice.showError('Please Select State');
      //   return;
      // }
     
      if (this.instiFormData.value.RequestId) {
        this.formData = {
          RequestId: this.instiFormData.value.RequestId,
          surveyYearApprove: this.instiFormData.value.surveyYearValue,
          stateValue: stateCode,
          collegeType: this.instiFormData.value.collegeType ? this.instiFormData.value.collegeType : '',
          universityValue: universityValue,
        };
      }
      if (this.instiFormData.value.surveyYearValue) {
        this.formData = {
          RequestId: this.instiFormData.value.RequestId,
          surveyYearApprove:
            this.instiFormData.value.surveyYearValue.split('-')[0],
          stateValue: stateCode,
          collegeType: this.instiFormData.value.collegeType ? this.instiFormData.value.collegeType : '',
          universityValue: universityValue,
        };
      }
    }

    //SNO or UNO
    if (this.localService.getData('roleId') == '6' ||this.localService.getData('roleId') == '7') {
      if (!this.instiFormData.value.surveyYearValue && !this.instiFormData.value.RequestId) {
        this.sharedservice.showError('Please Select Survey Year');
        return;
      }
      if(this.roleId1==='6'){//SNO
        stateCode=this.localService.getData('stateCode');
      }
      universityValue=universityValue==''?this.localService.getData('id'):universityValue;
      if (this.instiFormData.value.surveyYearValue) {
        this.formData = {
          RequestId: this.instiFormData.value.RequestId,
          surveyYearApprove:
            this.instiFormData.value.surveyYearValue.split('-')[0],
          collegeType: this.instiFormData.value.collegeType ? this.instiFormData.value.collegeType : '',
          "stateValue": stateCode,
          universityValue:universityValue,
        };
      }
      if (this.instiFormData.value.RequestId) {
        this.formData = {
          RequestId: this.instiFormData.value.RequestId,
          surveyYearApprove: '1',
          collegeType: this.instiFormData.value.collegeType ? this.instiFormData.value.collegeType : '',
          universityValue:universityValue,
          
        };
      }
      if ( this.instiFormData.value.RequestId && this.instiFormData.value.surveyYearValue) {
        this.formData = {
          RequestId: this.instiFormData.value.RequestId,
          surveyYearApprove:
            this.instiFormData.value.surveyYearValue.split('-')[0],
          collegeType: this.instiFormData.value.collegeType ? this.instiFormData.value.collegeType : '',
          universityValue:universityValue,
          "stateValue": stateCode,
        };
      }
      if (this.roleId1 == "7") {//UNO
        this.formData = {
          "RequestId": this.instiFormData.value.RequestId ? this.instiFormData.value.RequestId : '',
          "universityValue": universityValue,
          "stateValue": stateCode,
          "surveyYearApprove":
            this.instiFormData.value.surveyYearValue ? this.instiFormData.value.surveyYearValue.split('-')[0] : '',
          collegeType: this.instiFormData.value.collegeType ? this.instiFormData.value.collegeType : '',
        }
      }
      if (this.roleId1 == "6") {//SNO
        this.formData = {
          "RequestId": this.instiFormData.value.RequestId ? this.instiFormData.value.RequestId : '',
          "stateValue": stateCode,
          "surveyYearApprove":
            this.instiFormData.value.surveyYearValue ? this.instiFormData.value.surveyYearValue.split('-')[0] : '',
          collegeType: this.instiFormData.value.collegeType ? this.instiFormData.value.collegeType : '',
          'universityValue':universityValue,
        }
      }

    }
      //  console.log(this.formData)
    if (this.formData) {
      //MoE
      if(this.roleId1=='26' || this.roleId1=='1'){
        this.formData.toDate=toDate;
        this.formData.fromDate=fromDate;
      }
      if((this.instiFormData.get('pendingLevel')?.value==2 || this.instiFormData.get('pendingLevel')?.value==3 || this.instiFormData.get('pendingLevel')?.value==4) && (this.roleId1=='26' || this.roleId1=='1' || this.roleId1=='6' || this.roleId1=='7')){
        this.instManagementService
        .getapproveCollegeList1MoE(this.formData,this.instiFormData.value)
        .subscribe((res: any) => {
          if (res) {
            this.showTable = true;
            this.isApprove = false;
            this.alldata = res?.approvalCollegeListBean;
            this.sortedData=res?.approvalCollegeListBean;
            this.page = 1;
            this.handlePageChange(this.page);
          }

          if (res.statusCode === 'AISH099') {
            this.showTable = true;
            this.sharedservice.showError(res.statusDesc);
            this.alldata = [];
          }
        });
      }else{
        this.instManagementService
        .getapproveCollegeList1(this.formData,this.instiFormData.value)
        .subscribe((res: any) => {
          if (res) {
            this.showTable = true;
            this.isApprove = false;
            this.alldata = res?.approvalCollegeListBean;
            this.sortedData=res?.approvalCollegeListBean;
            if(this.localService.getData('viewDetailOfRequestId')){
              this.approveCollege(this.sortedData[0],this.sortedData[0].approvalresp,true);
              this.viewDetailOnly=false;
            }

            this.page = 1;
            this.handlePageChange(this.page);
          }

          if (res.statusCode === 'AISH099') {
            this.showTable = true;
            this.sharedservice.showError(res.statusDesc);
            this.alldata = [];
          }
        });
      }

    }
  }

  loadUniversityData() {
    this.UniSurveyYear = '0';
    // this.userForm.controls['universityId'].setValue(null);
    if(this.userForm.value.surveyYear){
      this.UniSurveyYear=this.userForm.value.surveyYear.split('-')[0];
    }
    if(this.userForm.get('location')?.value=='same'){
     this.state=this.userForm.get('stateName')?.value;
    }else{
      this.state=  this.roleId1=='6' || this.roleId1=='7' ? this.localService.getData('stateCode') : this.userForm.value.universityState;
    }

    // console.log(this.previousBool,this.state)
    if (this.state) {
      this.instManagementService
        .getUniversity(this.state, this.UniSurveyYear)
        .subscribe({
          next: (res) => {
            this.universityList = res;
            this.filtereduniversityList = this.universityList.slice();
          },
          
        });

    }
  }

  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
  }

  handlePageChange(event: any): void {
    this.page = event;
    let page1 = parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * page1),
      (this.EndLimit = this.StartLimit + page1);
    var a = Math.ceil(this.alldata?.length / page1);
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + page1, this.alldata?.length);
    } else {
      this.pageData = Math.min(this.StartLimit + page1, this.alldata?.length);
    }
  }
  back(): void {
    // this.sharedservice.global_loader = true;
    // this.location.back();
    this.showTablesApprove = false;
    this.showTable = true;
    this.isApprove = false;
    this.userForm.reset();
  }
  backToSummaryReport(){
   let viewDetailOfRequestId=JSON.parse(this.localService.getData('viewDetailOfRequestId'));
   let backURL=JSON.parse(this.localService.getData('searchStateData'));
   backURL.status=true;
   viewDetailOfRequestId.status=null;
   this.localService.saveData('viewDetailOfRequestId',JSON.stringify(viewDetailOfRequestId));
   this.localService.saveData('searchStateData',JSON.stringify(backURL))
   this.router1.navigate([backURL.backurl])
  }

  approveCollege(event: any,approvalresp:any,showView?:any) {
    // console.log('event', event)
    this.requestId = event.id
    this.isSubmitted = false;
    this.showTablesApprove = true;
    this.showTable = false;
    this.isApprove = true;
    this.approvalResponse=approvalresp;
    let surveyYear=this.instiFormData.value?.surveyYearValue?this.instiFormData.value?.surveyYearValue.split('-')[0]:0;
    this.instManagementService
      .getCollegeApprovalRequestDetails(
        event.id,
        surveyYear
      )
      .subscribe((res) => {
        this.obj = res.collegeApprovalRequestDetailsBean;
        this.userForm.controls['Name'].setValue(this.obj?.collegeName);
        this.userForm.controls['stateName'].setValue(this.obj?.stateCode);
        this.getdistrictName();
        this.userForm.controls['districtName'].setValue(this.obj?.districtCode);
        this.userForm.controls['instituteType'].setValue(parseInt(this.obj?.collegeTypeId));
        this.userForm.controls['managementType'].setValue(this.obj?.collegeManagementTypeId);
        this.userForm.controls['universityId'].setValue(this.obj?.affiliatingUniversityAisheCode.split('-')[1]);
        

        // this.previousStateId=this.userForm.get('stateName')?.value;
        this.previousUniversityId=this.userForm.get('universityId')?.value;
        // this.previousDistrictCode=this.userForm.get('districtName')?.value;
        // this.previousFilteredState=this.filteredState;
        this.userForm.get('universityState')?.setValue(this.obj?.affiliatingUniversityStateCode);
        this.previousBool=false;
        this.affiliatingUniversityStateCode=this.obj?.affiliatingUniversityStateCode;
        if(this.obj?.stateCode==this.obj?.affiliatingUniversityStateCode){
          this.userForm.get('location')?.setValue('same');
          this.previousBool=false;
        }else{
          this.userForm.controls['location'].setValue('others');
          let stateCode=this.userForm.get('stateName')?.value;
          if(stateCode){
            // this.userForm.get('location')?.setValue('other');
            this.filteredState2=this.filteredState.filter((data)=>data.stateCode!=stateCode);
            // console.log(this.filteredState2)
            this.userData2=this.userData.filter((data:any)=>data.stateCode!=stateCode);
            this.userForm.get('universityState')?.setValue(this.affiliatingUniversityStateCode);
            this.previousBool=true;
          }
        }
        this.loadUniversityData();
        if (res.collegeApprovalRequestDetailsBean.similarCollege.length > 0) {
          // this.SimilarDataTableDataTable = res.collegeApprovalRequestDetailsBean.similarCollege
          this.similarmatTableData = true;
        }else{
          // this.SimilarDataTableDataTable=[]
          this.similarmatTableData = false;
        }

        if(showView){//show only view
         this.showViewDetailOnly=false;
        }else{//false show only with approve field
          
          this.showViewDetailOnly=true;
        }
        if(!showView){
          this.showTablesApprove = true;
          this.showTable = false;
          // this.isApproved = true;
          this.showView=true;
        }else{
          this.showTablesApprove = true;
          this.showTable = false;
          // this.isApproved = true;
          this.showView=false;
        }
      //  this.instManagementService.getRequestId(this.obj?.requestId).subscribe((res)=>{
      //   console.log(res)
      //  })
      });

  }

 

  loadSurveyYear() {
    this.instManagementService.getSurveyYearList().subscribe({
      next: (res) => {
        this.surveyYearList = res;
      },
    });
  }
  downloadPdf(base64: string) {
    const byteArray = new Uint8Array(
      atob(base64)
        .split('')
        .map((char) => char.charCodeAt(0))
    );

    const pdfData = new Blob([byteArray], { type: 'application/pdf' });
    const fileURL = URL.createObjectURL(pdfData); //create random url to render on browser
    //window.open(fileURL);

    let pdfName = 'viewDocument.pdf';
    // Construct the 'a' element
    let link = document.createElement('a');
    // link.download = pdfName;
    link.target = '_blank';

    // Construct the URI
    link.href = fileURL;
    document.body.appendChild(link);
    link.click();

    // Cleanup the DOM
    document.body.removeChild(link);
  }


  statusId(statusId:any){
    this.isStatusId = statusId;
    if(statusId == 2){
      // console.log('2')
      this.userForm.get('alldocumentsvalid')?.setErrors(null)
    }
    else{
      this.userForm.get('alldocumentsvalid')?.setValidators([Validators.required])
    }

  }

  Onsubmit() {
    // console.log(this.userForm)
    this.checkForWhiteSpace();
    let surveyYear=this.instiFormData.value?.surveyYearValue?this.instiFormData.value?.surveyYearValue.split('-')[0]:0;
    if(this.roleId1 === '26' || this.roleId1 === '1'){
      this.userForm.controls['surveyYear'].setValidators(Validators.required);
      this.userForm.controls['surveyYear'].updateValueAndValidity();
    }
    
    // this.userForm.value.currentSurveyYear =this.instiFormData.value.surveyYearValue.split('-')[0];
    this.userForm.value.currentSurveyYear=surveyYear;
    this.isSubmitted = true;
    //  console.log("this.userForm.value kj",this.userForm.value);
    if (this.userForm.invalid) {
      this.sharedservice.showError('All field required!');
      return;
    }
    if (this.userForm.valid) {
      var dataApprove = this.userForm.value.approveR;
      //open confirm dialog
      // const dialogRef = this.dialog.open(ConfirmDialogComponent,{
      //   data: {
      //     message: "Please confirm before approving/rejecting this request!!!",
      //     action:'1'
      //   }
      // });
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        data: {
          message: this.userForm.value.approveR,
          action: '1s',
          Approved: 'Approved1',
        },
      });

      //call save api accordingly
      dialogRef.afterClosed().subscribe((confirmed: boolean) => {
        if (confirmed) {
          this.userForm.value.requestId = this.obj['requestId'];
          let payload=this.userForm.value;
          delete payload.universityState;
          this.instManagementService
            .postcollageApprovedByUniversity(payload)
            .subscribe((res) => {

              if (res.statusCode == 'AISH001') {
                if (dataApprove === '1' ) {//approved
                  let body:any='';
                  if(this.roleId1=='26' || this.roleId1=='1'){
                    body= 'The request for the request id:' + this.obj.requestId + ' has been approved.\n The new AISHE CODE alloted to the institution '
                    + res?.college?.name + ' is ' + res?.college?.id + '.';
                  }else{
                    body= 'The request for the request id:' + this.obj.requestId + ' has been approved and forwarded to next level.';
                  }
                  this.dataEmail = {
                    body:body,
                    emailAddress: res?.email,
                    subject: 'Request approval status',
                  };
                }
                if (dataApprove === '2') {//reject

                  this.dataEmail = {
                    body: 'The request for the request id:' + this.obj.requestId + ' has been Rejected.',
                    emailAddress: res?.email,
                    subject: 'Request approval status',

                  };
                }
                this.sharedservice.showSuccessMessage(
                  'Your request has been processed successfully!!'
                );
                //this.sharedservice.global_loader = false;
                const dialogRef = this.dialog.open(ConfirmDialogComponent, {
                  data: {
                    message1: 'Your request has been processed successfully!!',
                    data1: res?.college,
                    action: '1',
                    showData: 'approvalRejected',
                  },
                });
                dialogRef.afterClosed().subscribe((confirmed: boolean) => {
                  if (confirmed) {
                    this.instManagementService.postSendemail(this.dataEmail).subscribe((res) => {
                      // if (res) {
                      //   alert(this.dataEmail.body)
                      // }
                    });
                  }

                });
                dialogRef.afterClosed().subscribe((result) => {
                  if (result) {
                    this.isApprove = false;
                    this.getApproveList();
                    this.showTablesApprove = false;
                    this.showTable = true;
                    this.userForm.reset();
                  }
                });
                //navigate to approve page on successfully save {"statusCode":"AISH002","statusDesc":"Invalid details, Please provide valid details."}
                // this.router.navigateByUrl('aishe/Institution-Management/approve')
              }
              if (res.statusCode == 'AISH002') {
                this.sharedservice.showError(res.statusDesc);
              }
            },(error) => {
              // console.log(error)
              this.sharedservice.showError(error.error.message);
            });
        }

        dialogRef.close(true);
      });
      dialogRef.afterClosed().subscribe((result) => {
        if (result) {
          this.isApprove = false;
          this.getApproveList();
          this.showTablesApprove = false;
          this.showTable = true;
          this.userForm.reset();
        }
      });
    }
  }
  sortData(sort: Sort) {
    const data = this.alldata.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'stateName':
          return this.compare(a.stateName, b.stateName, isAsc);
        case 'districtName':
          return this.compare(a.districtName, b.districtName, isAsc);
        case 'collegeName':
          return this.compare(a.collegeName, b.collegeName, isAsc);  
        case 'collegeType':
          return this.compare(a.collegeType, b.collegeType, isAsc);  
        case 'universityName':
          return this.compare(a.universityName, b.universityName, isAsc);      
        case 'id':
          return this.compare(a.id, b.id, isAsc);     
        default:
          return 0;
      }
    });
  }


compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
checkForWhiteSpace(){
  let aremark=this.userForm.get('aremarks')?.value?.trim();
  this.userForm.get('aremarks')?.setValue(aremark);
}
statusReceived(msg:any){
  // console.log(msg)
 this.requestStatus=msg
 if(msg.UNO==='Pending'){
  this.userForm.disable()
 }else{
  this.userForm.enable();
 }

}
alphaNumberOnly (e:any) {  // Accept only alpha numerics, not special characters 
  var regex = new RegExp("[a-zA-Z ]");
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
getdistrictName() {
  this.userForm.get('universityId')?.setValue('');
  let stateName1 = this.userData.filter(
    (data:any) => data.name === this.userForm.value.stateName
  );
  this.instManagementService
    .getdistrict(this.userForm.get('stateName')?.value)
    .subscribe((res) => {
      this.dataDistrict = res;
      this.filteredDistrict = this.dataDistrict.slice();
     // console.log('this.form.value.stateName', this.dataDistrict);
    });
  // console.log('this.form.value.stateName', this.form.value.stateName);
}
districtfilter(){
  this.dataCheck=true;
  this.userForm.controls['districtName'].reset()
  }
compareFn(t1: any, t2: any): boolean {

  return t1 && t2 ? t1 === t2 : t1 === t2;
  }
  getSubrolls(){
    let boolean=true;

    this.instManagementService
    .getSubRoll(this.roleId1).subscribe((res)=>{
      this.standalonTypeData = res[0].stateBodies;
      //console.log("this.institutionmanagement.getStandalonInstitution",  this.collegeTypeData)
      this.standalonTypefilter = this.standalonTypeData?.slice();
      res[0].stateBodies.length==1?this.userForm.controls['instituteType'].setValue(res[0].stateBodies[0]?.id):'';
    })
  }
  findManagmentType() {
    let payload={type:'C'}
    this.instManagementService.getmanagmentType2(payload).subscribe((res)=>{
    this.managmentData=res
    this.filteredmanagementT=this.managmentData.slice();
    });

  }
  getStateData() {
    this.instManagementService.getState().subscribe((res) => {
      this.userData = res;
      this.filteredState = this.userData.slice();
    });
  }
  getCollegeTypeData() {
    this.instManagementService.getCollegeType().subscribe((res) => {
      this.collegeTypeData = res;
      this.filteredcollegeTypeData = this.collegeTypeData.slice();
    },err=>{
      
    });
  
  }
  OtherStateSelected(event:any){
  //  console.log(event.value)

   if(event.value==='others'){
    this.previousBool=true;
    let stateCode=this.userForm.get('stateName')?.value;
    if(stateCode){
      this.loadUniversityData();
      // this.userForm.get('location')?.setValue('other');
      this.filteredState2=this.filteredState.filter((data)=>data.stateCode!=stateCode);
      // console.log(this.filteredState2)
      this.userData2=this.userData.filter((data:any)=>data.stateCode!=stateCode);
      this.userForm.get('universityState')?.setValidators(Validators.required);
      this.userForm.get('universityState')?.updateValueAndValidity();
      this.userForm.get('universityState')?.setValue(this.affiliatingUniversityStateCode);
       this.userForm.get('universityId')?.setValue(this.previousUniversityId);
    }
   }  
   if(event.value==='same'){
    this.userForm.get('universityState')?.setValidators(null);
    this.userForm.get('universityState')?.updateValueAndValidity();
    this.loadUniversityData();
    this.userForm.get('universityId')?.setValue(this.previousUniversityId);
    // console.log(this.userForm.value)
    this.previousBool=false;


   }

  }
  formatDate(date: string | Date): string {
    const parsedDate = new Date(date);

    if (isNaN(parsedDate.getTime())) {
      throw new Error('Invalid date');
    }

    const day = String(parsedDate.getDate()).padStart(2, '0');
    const month = String(parsedDate.getMonth() + 1).padStart(2, '0'); // Months are 0-based
    const year = parsedDate.getFullYear();

    return `${day}/${month}/${year}`;
  }
  downloadExcel() {
    this.tableHeaders = ['SNO','Request Id', 'College Name', 'University Name','Request Submitted','College Type', 'State Name','District Name'];
    const tableData = this.sortedData.map((row:any, i:any) => [
      // i+1,
      row.id, 
      row.collegeName, 
      row.universityName, 
      row.requestSubmittedOn,
      row.collegeType,
      row.stateName,
      row.districtName,
    ]);
  
   this.tableHeaders.shift();
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      // excelName:'Merged Insitutions',
      downloadExcelName:'College Approve/Reject Request',
      setHeaderCollumnWidths:[
        { wpx: 80 },//Request Id
        { wpx: 400 }, //College Name
        { wpx: 400 },//University Name,
        { wpx: 160 },//Request Submitted
        { wpx: 110 },//College Type
        { wpx: 80 },// State Name
        { wpx: 80 },//District Name


         ],
       headerStyle:{
        font: {
          bold: true,
          color: { rgb: 'FFFFFF' }, // White text color
        },
        fill: {
          fgColor: { rgb: '4CAF50' }, // Green background color
        },
        alignment: {
          horizontal: 'center',
          vertical: 'center'
        }
      }  
    }
   this.sharedservice.downloadExcel(param);
  }


  openCheckSimilarDialog(){
    // console.log(this.obj?.collegeDistrict)
    let payload = {
      underState : true,
      requestId : this.requestId
    }
    this.getService.getSimilarInsitute(payload).subscribe(res =>{
      if(res.length > 0){
        this.SimilarDataTableDataTable = res;
        let ele = {
          similarIsVisible : true,
          eData : this.SimilarDataTableDataTable,
          key : 'C',
          stateDistric:1,
          district:this.obj?.collegeDistrict,
          state:this.obj?.collegeState,
        }
      
        this.sharedservice.viewProgram(ele)
      }
      else{
        this.SimilarDataTableDataTable = []
        this.sharedservice.showError('Record Not Found!!');
      }
    },err=>{
      
    })
}
}
