import { Component, Input, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import { Common } from 'src/app/common/common';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { GetService } from 'src/app/service/get/get.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-standalone-approve',
  templateUrl: './standalone-approve.component.html',
  styleUrls: ['./standalone-approve.component.scss'],
})
export class StandaloneApproveComponent implements OnInit {
  @Input()
  instiFormData: any;
  userDataTable: any[] = [];
  tableSize: number[] = [10, 20, 30, 40, 50];
  showTable: boolean = false;
  searchText: any;
  SimilarDataTableDataTable: any[] = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  page: number = 1;
  roleId: any;
  isSubmitted = false;
  obj: any;
  dataEmail:any;
  requestIs: any;
  isStatusId:any = 1;
  surveyYear: any;
  isApproved: boolean = false;
  showSimilarDataTable: boolean = false;
  showTablesApprove: boolean = false;
  surveyYearList: any;
  requestId:any;
  
  userForm = new FormGroup({
    Name:new FormControl('',[]),
    stateName:new FormControl('',[]),
    districtName:new FormControl('',[]),
    standaloneType:new FormControl('',[]),
    managementType:new FormControl('',[]),
    surveyYear:new FormControl('',[]),

    aRemarks: new FormControl('', [
      Validators.required,
      Validators.maxLength(2000),
      // Validators.minLength(3),
    ]),

    approveR: new FormControl('', Validators.required),

    alldocumentsvalid: new FormControl('', Validators.required),
  });
  approvalResponse: any;
  requestStatus: any;
  isFormInvalid: any;

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
  roleId1:any;
  showView: boolean=true;
  takeAction!: boolean;
  tableHeaders:any;
  viewDetailOnly: boolean=true;
  constructor(
    private institutionmanagement: InstitutionmanagementService,
    public sharedservice: SharedService,
    private dialog: MatDialog,
    private instManagementService: InstitutionmanagementService,public localService:LocalserviceService,
    private getService : GetService,public router: Router
  ) {
    this.roleId1 = this.localService.getData('roleId');
    this.roleId=this.localService.getData('roleId');
  }

  ngOnInit(): void {
    // console.log(this.instiFormData.value);
    this.findDataList();
    this.findManagmentType();
    this.getSubrolls();
    this.getStateData();
    this.surveyYearData()
  }

  get f(): { [key: string]: AbstractControl } {
    return this.userForm.controls;
  }
  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  back() {
    this.showTablesApprove = false;
    this.showTable = true;
    this.isApproved = false;
    this.isSubmitted = false;
    this.userForm.reset();
  }
  backToSummaryReport(){
    // console.log(JSON.parse(this.localService.getData('searchStateData'))); 
    let viewDetailOfRequestId=JSON.parse(this.localService.getData('viewDetailOfRequestId'));
    viewDetailOfRequestId.status=null;
    this.localService.saveData('viewDetailOfRequestId',JSON.stringify(viewDetailOfRequestId));
    let backURL=JSON.parse(this.localService.getData('searchStateData'));
    backURL.status=true;
    this.localService.saveData('searchStateData',JSON.stringify(backURL))
    this.router.navigate([backURL.backurl])
   }
  resetdata() {
    this.userDataTable = [];
    this.searchText = null;
    this.pageSize = 10;
    this.page = 1;
    this.handlePageChange(this.page);
  }

  surveyYearData() {
    this.instManagementService.getSurveyYearList().subscribe((res) => {
      this.surveyYearList = res;
      // console.log('rrrr', this.surveyYearList)
    });
  }

  findDataList() {
    console.log(this.instiFormData)
    this.searchText = null;
    let toDate:any=null;let fromDate:any=null;
    let formData:any;
    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
      if(this.localService.getData('viewDetailOfRequestId')){
        this.instiFormData.controls['pendingLevel'].setValue('');
        this.instiFormData.controls['surveryApprove'].setValue('');
        this.instiFormData.controls['standaloneType'].setValue('');
        this.instiFormData.controls['stateValue'].setValue('');
        this.instiFormData.controls['surveyYearValue'].setValue('');
        this.instiFormData.controls['stateApprove'].setValue('');
        this.instiFormData.controls['districtValue'].setValue('');
      }
      if(this.roleId1=='26' || this.roleId1=='1'){
        this.takeAction=true;
      }else{
        this.takeAction=this.instiFormData.get('pendingLevel')?.value!=3 && this.instiFormData.get('pendingLevel')?.value!=4 && this.instiFormData.get('pendingLevel')?.value!=1;
      }

    }
    if(this.instiFormData.value.toDate!=null && this.instiFormData.value.toDate!=''){
      toDate=this.formatDate(this.instiFormData.value.toDate)
     }
     if(this.instiFormData.value.fromDate!=null && this.instiFormData.value.fromDate!=''){
       fromDate=this.formatDate(this.instiFormData.value.fromDate)
     }
    if (!this.instiFormData.value.surveryApprove && this.instiFormData.value.RequestId==null){
      this.sharedservice.showError('Please Select Survey Year');
      return;
    }

    if(this.instiFormData.value.RequestId!=null && (this.roleId=='26' || this.roleId=='1' || this.roleId=='6' || this.roleId=='8' || this.roleId=='9' || this.roleId=='10' || this.roleId=='11')){
       this.instiFormData.controls['surveryApprove'].clearValidators();
       this.instiFormData.controls['surveryApprove'].updateValueAndValidity();
    }else{
      this.instiFormData.controls['surveryApprove'].setValidators([Validators.required]);
      this.instiFormData.controls['surveryApprove'].updateValueAndValidity();
    }
    // if (
    //   (((!this.instiFormData.value.stateApprove &&
    //     this.localService.getData('roleId') == '26') ||
    //   (!this.instiFormData.value.stateApprove &&
    //     this.localService.getData('roleId') == '1')) &&
    //     !this.instiFormData.value.RequestId)
    // ) {
    //   this.sharedservice.showError('Please Select State');
    //   return;
    // }
    if (
      (this.instiFormData.value &&
        (this.instiFormData.value.stateApprove==null || this.instiFormData.value.stateApprove=='') &&
        this.localService.getData('roleId') == '26') ||
      ((this.instiFormData.value.stateApprove==null || this.instiFormData.value.stateApprove=='') &&
        this.localService.getData('roleId') == '1')
    ){
      formData = {
        approvalRoleId: this.localService.getData('roleId'),
        requestId: this.instiFormData.value.RequestId?this.instiFormData.value.RequestId:null,
        stateCode:'',
        surveyYear:this.instiFormData.value.surveryApprove?this.instiFormData.value.surveryApprove.split('-')[0]:'',
        districtValue:this.instiFormData.value.districtValue?this.instiFormData.value.districtValue.trim():'',
        standaloneType:this.instiFormData.value.standaloneType==' '?this.instiFormData.value.standaloneType.trim():this.instiFormData.value.standaloneType==null?'':this.instiFormData.value.standaloneType,
        pendingLevel:this.instiFormData.value.pendingLevel?this.instiFormData.value.pendingLevel:'',
      }; 
    }
    if (
      (this.instiFormData.value &&
        this.instiFormData.value.stateApprove &&
        this.localService.getData('roleId') == '26') ||
      (this.instiFormData.value.stateApprove &&
        this.localService.getData('roleId') == '1')
    ) {
      formData = {
        approvalRoleId: this.localService.getData('roleId'),
        requestId: this.instiFormData.value.RequestId?this.instiFormData.value.RequestId:'',
        stateCode:
          (this.instiFormData.value.stateApprove &&
            this.localService.getData('roleId') == '26') ||
          (this.instiFormData.value.stateApprove &&
            this.localService.getData('roleId') == '1')
            ? this.instiFormData.value.stateApprove
            : this.localService.getData('stateCode'),
        surveyYear:this.instiFormData.value.surveryApprove?this.instiFormData.value.surveryApprove.split('-')[0]:'',
        districtValue:this.instiFormData.value.districtValue?this.instiFormData.value.districtValue.trim():'',
        standaloneType:this.instiFormData.value.standaloneType==' '?this.instiFormData.value.standaloneType.trim():this.instiFormData.value.standaloneType==null?'':this.instiFormData.value.standaloneType,
        pendingLevel:this.instiFormData.value.pendingLevel?this.instiFormData.value.pendingLevel:'',
      };
    }

    if (this.instiFormData.value && this.localService.getData('roleId') == '6') {
      formData = {
        approvalRoleId: this.localService.getData('roleId'),
        requestId: this.instiFormData.value.RequestId?this.instiFormData.value.RequestId:'',
        stateCode: this.localService.getData('stateCode'),
        surveyYear:this.instiFormData.value.surveryApprove?this.instiFormData.value.surveryApprove.split('-')[0]:'',
        districtValue:this.instiFormData.value.districtValue?this.instiFormData.value.districtValue.trim():'',
        standaloneType:this.instiFormData.value.standaloneType==' '?this.instiFormData.value.standaloneType.trim():this.instiFormData.value.standaloneType==null?'':this.instiFormData.value.standaloneType,
        
      };
    }
    if (
      this.instiFormData.value.RequestId &&
      !this.instiFormData.value.surveryApprove
    ) {
      let stateCode:any;
      if(this.roleId=='6'){//SNO
         stateCode=this.localService.getData('stateCode');
      }else{
        // for MOE
        stateCode=this.instiFormData.value.stateValue?this.instiFormData.value.stateValue:'';
      }
      formData = {
        approvalRoleId: this.localService.getData('roleId'),
        requestId: this.instiFormData.value.RequestId?this.instiFormData.value.RequestId:'',
        stateCode: stateCode,
        surveyYear: '',
        districtValue:this.instiFormData.value.districtValue?this.instiFormData.value.districtValue.trim():'',
        standaloneType:this.instiFormData.value.standaloneType==' '?this.instiFormData.value.standaloneType.trim():this.instiFormData.value.standaloneType==null?'':this.instiFormData.value.standaloneType,
      };
    }
    if(this.instiFormData.value.surveryApprove && this.localService.getData('roleId') == '8' || this.localService.getData('roleId') == '9' || this.localService.getData('roleId') == '10' ||this.localService.getData('roleId') == '11'){
       formData={
        surveyYear:this.instiFormData.value.surveryApprove?this.instiFormData.value.surveryApprove.split('-')[0]:'',
        requestId:this.instiFormData.value.RequestId?this.instiFormData.value.RequestId:null,
        approvalRoleId: this.localService.getData('roleId'),
        stateCode: this.localService.getData('stateCode'),
        districtValue:this.instiFormData.value.districtValue?this.instiFormData.value.districtValue.trim():'',
        standaloneType:this.instiFormData.value.standaloneType==' '?this.instiFormData.value.standaloneType.trim():this.instiFormData.value.standaloneType==null?'':this.instiFormData.value.standaloneType,
       }
    }
    if(this.roleId=='22'){//NCHMCT
      formData={
        surveyYear:this.instiFormData.value.surveryApprove?this.instiFormData.value.surveryApprove.split('-')[0]:'',
        requestId:this.instiFormData.value.RequestId?this.instiFormData.value.RequestId:null,
        approvalRoleId: this.localService.getData('roleId'),
        stateCode: '',
        districtValue:this.instiFormData.value.districtValue?this.instiFormData.value.districtValue.trim():'',
        standaloneType:this.instiFormData.value.standaloneType==' '?this.instiFormData.value.standaloneType.trim():this.instiFormData.value.standaloneType==null?'':this.instiFormData.value.standaloneType,
       }
    }
    if(this.roleId1=='1' || this.roleId1=='26'){
      formData.toDate=toDate?toDate:null;
      formData.fromDate=fromDate?fromDate:null;
    }
    //  console.log(formData)
    if((this.roleId1=='1' || this.roleId=='26' || this.roleId=='6' || this.roleId=='10' || this.roleId=='9' || this.roleId=='8' || this.roleId=='11' || this.roleId=='22') &&  (this.instiFormData.get('pendingLevel')?.value==2 ||this.instiFormData.get('pendingLevel')?.value==3 ||this.instiFormData.get('pendingLevel')?.value==4 )){
      if(this.instiFormData.get('pendingLevel')?.value==3){//sectorial level
        formData.approvalRoleId=100;
      }
      if(this.instiFormData.get('pendingLevel')?.value==4){//SNO level
        formData.approvalRoleId=6;
      }
      if(this.instiFormData.get('pendingLevel')?.value==4 && this.roleId=='6'){//find MoE level
        formData.approvalRoleId=1;//MoE
      }
      this.institutionmanagement
      .getStandaloneApprovalListMoE(formData)
      .subscribe((res) => {
        if (res.statusCode === 'AISH099') {
          this.sharedservice.showError(res.statusDesc);
          this.userDataTable = [];
          this.showTable = true;
          this.page = 1;
          this.handlePageChange(this.page);
        }
        if (res.statusCode === 'AISH001') {
          this.userDataTable = res.approvalStandaloneListBean;
          this.showTable = true;
          this.page = 1;
          this.handlePageChange(this.page);
        }
      });
    }else{
      if(this.roleId=='6'){
        formData.approvalRoleId=6;
      }
      this.institutionmanagement
      .getStandaloneApprovalList(formData)
      .subscribe((res) => {
        if (res.statusCode === 'AISH099') {
          this.sharedservice.showError(res.statusDesc);
          this.userDataTable = [];
          this.showTable = true;
          this.page = 1;
          this.handlePageChange(this.page);
        }
        if (res.statusCode === 'AISH001') {
          this.userDataTable = res.approvalStandaloneListBean;
          if(this.localService.getData('viewDetailOfRequestId')){
          this.editUser(this.userDataTable[0],true); 
          this.viewDetailOnly=false;
          }
          this.showTable = true;
          this.page = 1;
          this.handlePageChange(this.page);
        }
      });
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

  editUser(data: any,showView?:any) {
    this.requestId = data.id
    this.isSubmitted = false;
    this.approvalResponse=data?.approvalresp;
    let surveyYearCheck=this.instiFormData.value.surveryApprove?this.instiFormData.value.surveryApprove.split('-')[0]:0;
    let formdata = {
      RequestId: data.id,
      surveyYear: surveyYearCheck,
    };
    this.requestIs = data.id;
    this.surveyYear = surveyYearCheck;

    this.institutionmanagement
      .getStandaloneApprovalReject(formdata)
      .subscribe((res) => {
        this.obj = res.standaloneApprovalRequestDetailsBean;
        this.userForm.controls['Name'].setValue(this.obj?.collegeName);
        this.userForm.controls['stateName'].setValue(this.obj?.stateCode);
        this.getdistrictName();
        this.userForm.controls['districtName'].setValue(this.obj?.districtCode);
        this.userForm.controls['standaloneType'].setValue(parseInt(this.obj?.collegeTypeId));
        this.userForm.controls['managementType'].setValue(this.obj?.collegeManagementTypeId);
        if (
          res.standaloneApprovalRequestDetailsBean.similarCollege.length > 0
        ) {
          this.SimilarDataTableDataTable =
            res.standaloneApprovalRequestDetailsBean.similarCollege;
          this.showSimilarDataTable = true;
     
        }
        else{
          this.SimilarDataTableDataTable = []
        }
        if(!showView){
          this.showTablesApprove = true;
          this.showTable = false;
          this.isApproved = true;
          this.showView=true;
        }else{
          this.showTablesApprove = true;
          this.showTable = false;
          this.isApproved = true;
          this.showView=false;
        }

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
  checkForWhiteSpace(){
    let aremark=this.userForm.get('aRemarks')?.value?.trim();
    this.userForm.get('aRemarks')?.setValue(aremark);
  }


  statusId(statusId:any){
    this.isStatusId = statusId
    if(statusId == 2){
      // console.log('2')
      this.userForm.get('alldocumentsvalid')?.setErrors(null)
    }
    else{
      this.userForm.get('alldocumentsvalid')?.setValidators([Validators.required])
    }
     
    
// console.log('statusId', statusId)
  }


  Onsubmit() {
    
    // console.log("onSubmit",this.userForm)
    this.checkForWhiteSpace();
 
    this.userForm.value.currentSurveyYear;
    this.instiFormData.value.surveryApprove.split('-')[0];
    this.isSubmitted = true;
    let formData = {
      approverId: this.localService.getData('userId'),
      approverRoleId: this.localService.getData('roleId'),
      ipAddress: 'string',
      isDCFApplicable: true,
      remark: this.userForm.value.aRemarks.trim(),
      requestId: this.requestIs,
      statusId: this.userForm.value.approveR,
      surveyYear: this.userForm.value.surveyYear?this.userForm.value.surveyYear.split('-')[0]:0,
      
      name:this.userForm.value.Name?this.userForm.value.Name:null,
      stateCode:this.userForm.value.stateName?this.userForm.value.stateName:null,
      districtCode:this.userForm.value.districtName?this.userForm.value.districtName:null,
      instituteType:this.userForm.value.standaloneType?this.userForm.value.standaloneType?.toString():null,
      managementId:this.userForm.value.managementType?this.userForm.value.managementType:null,

    };

    //  console.log("JSON.stringify()",formData)

    if (this.userForm.invalid) {
      this.sharedservice.showError('All field required!');
      return;
    }
    if (this.userForm.valid) {
      //open confirm dialog
      var dataApprove = this.userForm.value.approveR;
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

          this.institutionmanagement
            .poststandaloneApprovedBySNO(formData)
            .subscribe((res) => {
              if (res.statusCode == 'AISH001') {
                if (dataApprove === '1') {
                  this.dataEmail = {
                    body: 'The request for the request id:' + this.obj.requestId + ' has been approved.\n The new AISHE CODE alloted to the institution '
                      + res.standalone?.name + ' is ' + res.standalone?.id + '.',
                    emailAddress: res?.email,
                    subject: 'Request approval status',
                  };
                }
                if (dataApprove === '2') {

                  this.dataEmail = {
                    body: 'The request for the request id:' + this.obj.requestId + ' has been Rejected.',
                    emailAddress: res.email,
                    subject: 'Request approval status',

                  };
                }
                // console.log(this.dataEmail);
                const dialogRef = this.dialog.open(ConfirmDialogComponent, {
                  data: {
                    message1: 'Your request has been processed successfully!!',
                    data1: res.standalone,
                    action: '1',
                    showData: 'approvalstandalone',
                  },
                });
                dialogRef.afterClosed().subscribe((confirmed: boolean) => {
                  if (confirmed) {
                   this.institutionmanagement.postSendemail(this.dataEmail).subscribe((res) => {
                      // if (res) {
                      //   alert(this.dataEmail.body)
                      // }
                   });
                  }

                });
                dialogRef.afterClosed().subscribe((result) => {
                  if (result) {
                    this.showTablesApprove = false;
                    this.showTable = true;
                    this.isApproved = false;
                    this.userForm.reset();
                    this.findDataList();
                  }
                });
                
              }
              if (res.statusCode == 'AISH002') {
                this.sharedservice.showError(res.statusDesc);
              }
            },(error) => {
              // console.log(error)
              this.sharedservice.showError(error.error.message);
            });
        }

        dialogRef.close();
      });
      dialogRef.afterClosed().subscribe((result) => {
        if (result) {
          this.showTablesApprove = false;
          this.showTable = true;
          this.isApproved = false;
          this.userForm.reset();
          this.findDataList();
        }
      });
    }
  }

  statusReceived(msg:any){
    this.requestStatus=msg;
    // console.log(msg)
    // console.log(this.obj?.stateBodyTypeName)
  //  if(msg.UNO==='Pending' && this.obj?.stateBodyTypeName!='Other'){
  //   this.userForm.disable()
  //  }else{
  //   this.userForm.enable();
  //  }
  
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
  getdistrictName(distictCode?:any) {
    let stateName1 = this.userData.filter(
      (data:any) => data.name === this.userForm.value.stateName
    );
    this.institutionmanagement
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
  
      this.institutionmanagement
      .getSubRoll(this.roleId1).subscribe((res)=>{
        this.standalonTypeData = res[0].stateBodies;
        //console.log("this.institutionmanagement.getStandalonInstitution",  this.collegeTypeData)
        this.standalonTypefilter = this.standalonTypeData.slice();
        res[0].stateBodies.length==1?this.userForm.controls['standaloneType'].setValue(res[0].stateBodies[0]?.id):'';
      })
    }
    findManagmentType() {
      let payload={type:'S'}
      this.institutionmanagement.getmanagmentType2(payload).subscribe((res)=>{
      this.managmentData=res
      this.filteredmanagementT=this.managmentData.slice();
      });
    }
    getStateData() {
      this.institutionmanagement.getState().subscribe((res) => {
        this.userData = res;
        this.filteredState = this.userData.slice();
      });
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
      this.tableHeaders = ['SNO','Request Id', 'Institute Name', 'Institution Type','State Name','District Name','Request Submitted'];
      const tableData = this.userDataTable.map((row:any, i:any) => [
        // i+1,
        row.id, 
        row.collegeName, 
        row.collegeType, 
     
        row.stateName,
        row.districtName,
        row.requestSubmittedOn,
      ]);
    
     this.tableHeaders.shift();
      let param={
        tableHeaders:this.tableHeaders,
        tableData:tableData,
        // excelName:'Merged Insitutions',
        downloadExcelName:'Standalone Approve/Reject Request',
        setHeaderCollumnWidths:[
          { wpx: 70 },//Request Id
          { wpx: 440 }, //College Name
          { wpx: 120 },//collegeType,
          { wpx: 88 },//stateName
          { wpx: 120 },//districtName
          { wpx: 125 },// requestSubmittedOn
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
          let payload = {
            underState : true,
            requestId : this.requestId
          }
          this.getService.getSimilarInsitute(payload).subscribe(res =>{
            if(res.length > 0){
              this.SimilarDataTableDataTable = res
              let ele = {
                similarIsVisible : true,
                eData : this.SimilarDataTableDataTable,
                key : 'S'
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
