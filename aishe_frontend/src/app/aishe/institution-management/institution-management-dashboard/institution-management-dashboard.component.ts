import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { AuthService } from 'src/app/service/auth.service';
import { GetService } from 'src/app/service/get/get.service';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import * as moment from 'moment';
@Component({
  selector: 'app-institution-management-dashboard',
  templateUrl: './institution-management-dashboard.component.html',
  styleUrls: ['./institution-management-dashboard.component.scss']
})
export class InstitutionManagementDashboardComponent implements OnInit {
  @Input() message: any;
  instiFormData: any;
  showTable2: boolean = false;
  showTable: boolean = false;
  showTable1: boolean = false;
pageSizeWork = 15;
  pageIndexWork = 0;

  pageSizeState = 15;
  pageIndexState = 0;
 
  listData: any
  totalPendinglCount: number = 0
  totalRejectedRequestCount: number = 0
  userDataTable: Array<any> = []
  userDataTableListUpdate:Array<any>=[];
  rejectRequestCount: Array<any> = [];
  totalCollegeRejectedRequest: any;
  totalStandaloneRejectedRequest: any;
  collegeRejectedRequestByUno:any;
  standaloneRejectedRequestBySectorial:any;
  collegeRejectedRequestBySno:any;
  standaloneRejectedRequestBySno:any;
  rejectRequestList: Array<any> = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  page: number = 1;

  similarmatTableData: boolean = false;
  showViewDetailOnly: boolean = true;
  showTablesApprove: boolean = false;
  requestId: any;
  isSubmitted = false;
  approvalResponse: any;
  requestIs: any;
  isStatusId: any = 1;
  submitted: any = false;
  surveyYear: any;
  surveyYearList: any;
  obj: any;
  roleId1: any;
  takeAction!: boolean;
  viewDetailOnly: boolean = true;
  requestStatus: any;
  showSimilarDataTable: boolean = false;
  isApproved: boolean = false;
  showView: boolean = true;
  filteredState: any[] = [];
  SimilarDataTableDataTable: any[] = [];
  standalonTypefilter: any[] = [];
  userData: any;
  dataDistrict: any
  filteredDistrict: any;
  standalonTypeData: any;
  filteredmanagementT: any[] = [];
  managmentData: any;
  isFormInvalid: any;
  dataEmail: any;
  searchText: any;
  filtereduniversityList: any;
  universityList: any;
  previousBool: boolean = false;
  filteredState2: any;
  userData2: any;
  collegeTypeData: any[] = [];
  filteredcollegeTypeData: any;
  affiliatingUniversityStateCode: any;
  previousUniversityId: any;
  dataCheck: boolean = false;
  isStandaloneUno:any
  rejectRequestListUpdate:any [] = []
  userForm = new FormGroup({
    Name: new FormControl('', []),
    stateName: new FormControl('', []),
    districtName: new FormControl('', []),
    standaloneType: new FormControl('', []),
    managementType: new FormControl('', []),
    surveyYear: new FormControl('', []),

    aRemarks: new FormControl('', [
      Validators.required,
      Validators.maxLength(2000),
      Validators.pattern(/.*[a-zA-Z].*/)
      // Validators.minLength(3),
    ]),

    approveR: new FormControl('', Validators.required),

    alldocumentsvalid: new FormControl('', Validators.required),
  });

  userForm1 = new FormGroup({
    Name: new FormControl('', []),
    stateName: new FormControl('', []),
    location: new FormControl('same', []),
    universityState: new FormControl(''),
    universityId: new FormControl('', [Validators.required]),
    districtName: new FormControl('', []),
    instituteType: new FormControl('', [Validators.required]),
    managementType: new FormControl('', []),
    // standaloneType: new FormControl('', []),
    surveyYear: new FormControl('', []),
    aremarks: new FormControl('', [
      Validators.required,
      Validators.maxLength(2000),
      Validators.pattern(/.*[a-zA-Z].*/)
      // Validators.minLength(3),
    ]),

    approveR: new FormControl('', Validators.required),

    alldocumentsvalid: new FormControl('', Validators.required),
  });
  roleId: any;
  UniSurveyYear: any;
  state: any;
  rejectedRequestDisplayCount: any;
  approvedCollegeCount:any;
  isStandaloneApprove: any;
roleIdMoe:any
  constructor(public auth: AuthService, public router: Router, private dialog: MatDialog, private getService: GetService, public sharedservice: SharedService, private instManagementService: InstitutionmanagementService, private institutionmanagement: InstitutionmanagementService, public localService: LocalserviceService, public sharedService: SharedService) {
    this.roleId1 = this.localService.getData('roleId');
    this.roleId = this.localService.getData('roleId');

    this.isStandaloneUno=(this.roleId===this.sharedService.role['Directorate of Technical Education'] || this.roleId===this.sharedService.role['State Nursing Body/Council'] || this.roleId===this.sharedService.role['State Council of Educational Research and Training'] || this.roleId===this.sharedService.role['State Paramedical Council'] || this.roleId===this.sharedService.role['National Council for Hotel Management and Catering Technology'])

    this.isStandaloneApprove=(this.roleId=== this.sharedService.role['State Paramedical Council']||this.roleId=== this.sharedService.role['Directorate of Technical Education']|| this.roleId=== this.sharedService.role['State Nursing Body/Council']|| this.roleId=== this.sharedService.role['State Council of Educational Research and Training']|| this.roleId=== this.sharedService.role['State Nodal Officer'] || this.roleId=== this.sharedService.role['National Council for Hotel Management and Catering Technology'])
  }

  ngOnInit(): void {
    this.totalRejectedRequestList();
    this.totalRejectedRequest();
    this.getInstituteCount();
    this.totalPending();
    this.getStateData();
    this.getSubrolls();
    this.surveyYearData();
    this.findManagmentType();

    // college

    // this.getSubrollsCollege();

    if (this.message === 'College') {
      if (this.roleId1 === '26' || this.roleId1 === '1') {
        this.userForm.get('universityId')?.setValidators([Validators.required]);
        this.userForm.get('universityId')?.updateValueAndValidity();
        this.getCollegeTypeData();
      } else {
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
  }
  get f(): { [key: string]: AbstractControl } {
    return this.userForm.controls;
  }
  get f1(): { [key: string]: AbstractControl } {
    return this.userForm1.controls;
  }
  getInstituteCount() {
    let roleId = this.localService.getData('roleId')
    let stateCode = this.localService.getData('stateCode')
    let universityId = this.localService.getData('stateLevelBody')
    let payload = {
      surveyYear: '2020',
      stateCode: (roleId === this.sharedService.role['SysAdmin'] || roleId === this.sharedService.role['Ministry of Education'] || roleId === this.sharedService.role['University'] || roleId === this.sharedService.role['National Council for Hotel Management and Catering Technology']) ? '' : stateCode,
      universityId: roleId === this.sharedService.role['University'] ? universityId : ''
    }
    this.auth.getInstitutionCount(payload).subscribe(res => {
      this.listData = this.message === 'College' ? res.collegeTypeCount : this.message === 'Standalone' ? res.standaloneTypeCount : this.message === 'research' ? res.rndInstituteCount : res.universityTypeCount
this.approvedCollegeCount=this.getTotalInstituteCount()
    }, err => {

    })
  }
getTotalInstituteCount(): number | string {
   let roleId = this.localService.getData('roleId')
   if(roleId=== this.sharedService.role['SysAdmin']){
      this.roleIdMoe= this.sharedService.role['Ministry of Education']
    }
  if (this.message === 'College') {
    return this.listData?.totalColleges;
  }
  if(this.message ==='Standalone'  &&  !this.isStandaloneApprove) {
    return this.listData?.totalStandalone
  }
  if (this.message ==='Standalone'  && this.roleId===this.sharedService.role['State Paramedical Council']) {
    return  this.listData?.paramedical;
  }
  if (this.message ==='Standalone'  && this.roleId===this.sharedService.role['Directorate of Technical Education']) {
    return this.listData?.polytechnic;
  }
   if ( this.message ==='Standalone'  && this.roleId===this.sharedService.role['State Nursing Body/Council']) {
    return this.listData?.nursing;
  }
   if (this.message ==='Standalone'  && this.roleId===this.sharedService.role['State Council of Educational Research and Training']) {
    return this.listData?.teacherTraining;
  }
   if ( this.message ==='Standalone'  && this.roleId===this.sharedService.role['State Nodal Officer']) {
    return this.listData?.totalStandalone;
  }
   if (this.message ==='Standalone'  && this.roleId===this.sharedService.role['National Council for Hotel Management and Catering Technology'] ) {
    return this.listData?.hotelManagementAndCatering;
  }
  //  if (this.message ==='Standalone'  && this.roleId===this.sharedService.role['State Nodal Officer']) {
  //   return this.listData?.pgdmInstitutes;
  // }
  //   if (this.message ==='Standalone'  && this.roleId===this.sharedService.role['State Nodal Officer']) {
  //   return this.listData?.pharmacyInstitutions;
  // }
  //   if (this.message ==='Standalone'  && this.roleId===this.sharedService.role['State Nodal Officer']) {
  //   return this.listData?.institutionUnderRehabilitationCouncilOfIndia;
  // }

  return '';
}

  getData(type: number) {
    if (type === 0) {
      this.showTable1 = true;
      this.showTable = false;
      this.showTable2 = false;
      this.showTablesApprove= false;
    } else if (type === 1) {
      this.showTable = true;
      this.showTable1 = false;
      this.showTable2 = false;
        this.showTablesApprove= false;
    } else {
      this.showTable2 = true;
      this.showTable1 = false;
      this.showTable = false;
        this.showTablesApprove= false;
    }
  }

  totalPending() {
      let universityId = this.localService.getData('stateLevelBody')
    let roleId = this.localService.getData('roleId')
    let stateCode = this.localService.getData('stateCode')
    // Declare payload first
    if(roleId=== this.sharedService.role['SysAdmin']){
      roleId= this.sharedService.role['Ministry of Education']
    }
  let payload: any = {
    universityId: roleId === this.sharedService.role['University'] ? universityId : '',
    stateCode:
  
      roleId === this.sharedService.role['Ministry of Education'] ||
      roleId === this.sharedService.role['University'] ||roleId === this.sharedService.role['National Council for Hotel Management and Catering Technology']
        ? ''
        : stateCode,
  };

  // Add role key conditionally
  if (this.message === 'Standalone') {
    payload.approvalRoleId = roleId;
  } else {
    payload.approverRoleId = roleId;
  }
    this.auth.getPendingIns(payload, this.message).subscribe(res => {
      this.userDataTable = res.approvalStandaloneListBean ? res.approvalStandaloneListBean : res.approvalCollegeListBean;
      console.log('this.userDataTable')
      // this.totalPendinglCount = res.approvalStandaloneListBean?.length ? res.approvalStandaloneListBean?.length : res.approvalCollegeListBean?.length
      this.totalPendinglCount = (res.approvalStandaloneListBean ?? res.approvalCollegeListBean)?.length ?? 0;

this. updatePaginatedDataPending()
    }, err => {

    })
  }

  totalRejectedRequest() {
         let universityId = this.localService.getData('stateLevelBody')
         console.log('universityid0',universityId);
         console.log('test',this.sharedService.role['University'])
    let roleId = this.localService.getData('roleId')
    let stateCode = this.localService.getData('stateCode');
     if(roleId=== this.sharedService.role['SysAdmin']){
      roleId= this.sharedService.role['Ministry of Education']
    }
    let payload = {
      listType: 'TOTAL',
      responseType: 'COUNT',
      type: this.message.toUpperCase(),
      approverRoleId:roleId=== '1' ? -1: roleId,
      stateCode:(roleId === this.sharedService.role['SysAdmin'] || roleId === this.sharedService.role['Ministry of Education'] || roleId === this.sharedService.role['University'] || roleId === this.sharedService.role['National Council for Hotel Management and Catering Technology']) ? '' : stateCode,
      universityId:roleId === this.sharedService.role['University'] ? universityId : '',
    }
    this.auth.getRejectedRequest(payload, this.message).subscribe((res: any) => {
      this.rejectRequestCount = res.data;

      console.log(this.rejectRequestCount);
      this.totalCollegeRejectedRequest = this.rejectRequestCount[0].totalCollegeRejectedRequest;
      this.totalStandaloneRejectedRequest = this.rejectRequestCount[0].totalStandaloneRejectedRequest

      this.collegeRejectedRequestByUno=this.rejectRequestCount[0].collegeRejectedRequestByUno;
      this.standaloneRejectedRequestBySectorial=this.rejectRequestCount[0].standaloneRejectedRequestBySectorial;

      this.collegeRejectedRequestBySno=this.rejectRequestCount[0].collegeRejectedRequestBySno;
      this.standaloneRejectedRequestBySno=this.rejectRequestCount[0].standaloneRejectedRequestBySno;

       this.rejectedRequestDisplayCount = this.getRejectedRequestCount();
    })
  }
getRejectedRequestCount(): number | string {
   let roleId = this.localService.getData('roleId')
   if(roleId=== this.sharedService.role['SysAdmin']){
      roleId= this.sharedService.role['Ministry of Education']
    }
  if (this.message === 'College' && roleId===this.sharedService.role['Ministry of Education']) {
    return this.totalCollegeRejectedRequest;
  }
  if(this.message ==='Standalone'  &&  roleId===this.sharedService.role['Ministry of Education']) {
    return this.totalStandaloneRejectedRequest;
  }
  if (this.message === 'College' && this.roleId===this.sharedService.role['University']) {
    return this.collegeRejectedRequestByUno;
  }
  if (this.message === 'Standalone' && this.isStandaloneUno) {
    return this.standaloneRejectedRequestBySectorial;
  }
  if (this.message === 'College' && this.roleId===this.sharedService.role['State Nodal Officer']) {
    return this.collegeRejectedRequestBySno;
  }
  if (this.message === 'Standalone' && this.roleId===this.sharedService.role['State Nodal Officer']) {
    return this.standaloneRejectedRequestBySno;
  }

  return '';
}

  totalRejectedRequestList() {
          let universityId = this.localService.getData('stateLevelBody')
    let roleId = this.localService.getData('roleId')
    let stateCode = this.localService.getData('stateCode')
     if(roleId=== this.sharedService.role['SysAdmin']){
      roleId= this.sharedService.role['Ministry of Education']
    }
    let payload = {
      listType: 'TOTAL',
      responseType: 'LIST',
      type: this.message.toUpperCase(),
       approverRoleId:roleId=== '1' ? -1: roleId,
      stateCode:(roleId === this.sharedService.role['SysAdmin'] || roleId === this.sharedService.role['Ministry of Education'] || roleId === this.sharedService.role['University'] || roleId === this.sharedService.role['National Council for Hotel Management and Catering Technology']) ? '' : stateCode,
      universityId:roleId === this.sharedService.role['University'] ? universityId : '',
    }
    this.auth.getRejectedRequestList(payload, this.message).subscribe((res: any) => {
    
   const sortedList = res.data.sort((a: any, b: any) => {
      const dateA = moment(a.actionOn, 'DD/MM/YYYY hh:mm:ss A').valueOf();
      const dateB = moment(b.actionOn, 'DD/MM/YYYY hh:mm:ss A').valueOf();
      return dateB - dateA; // Descending
    });

    this.rejectRequestList = sortedList;
      this.updatePaginatedDataState();
      console.log(this.rejectRequestList)

    })
  }
  getdistrictName(distictCode?: any) {
    let stateName1 = this.userData.filter(
      (data: any) => data.name === this.userForm.value.stateName
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
  getdistrictName1(distictCode?: any) {
    let stateName1 = this.userData.filter(
      (data: any) => data.name === this.userForm1.value.stateName
    );
    this.institutionmanagement
      .getdistrict(this.userForm1.get('stateName')?.value)
      .subscribe((res) => {
        this.dataDistrict = res;
        this.filteredDistrict = this.dataDistrict.slice();
        // console.log('this.form.value.stateName', this.dataDistrict);
      });
    // console.log('this.form.value.stateName', this.form.value.stateName);
  }
  editUser(data: any, showView?: any) {
    this.requestId = data.id ? data.id : data.requestId
    this.isSubmitted = false;
    this.approvalResponse = data?.approvalresp;
    // let surveyYearCheck = this.instiFormData.value.surveryApprove ? this.instiFormData.value.surveryApprove.split('-')[0] : 0;
    let surveyYearCheck = "0";

    let formdata = {
      RequestId: data.id ? data.id : data.requestId,
      surveyYear: surveyYearCheck,
    };
    this.requestIs = data.id;
    this.surveyYear = surveyYearCheck;
    if (this.message === "Standalone") {
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
          this.showTablesApprove = true;

          // if (
          //   res.standaloneApprovalRequestDetailsBean.similarCollege.length > 0
          // ) {
          this.SimilarDataTableDataTable =
            res.standaloneApprovalRequestDetailsBean.similarCollege;
          this.showSimilarDataTable = true;
          this.showTablesApprove = true;
          // }
          // else {
          //   this.SimilarDataTableDataTable = []
          // }
          if (!showView) {
            this.showTablesApprove = true;
            this.showTable = false;
              this.showTable2 = false;
            this.isApproved = true;
            this.showView = true;
          } else {
            this.showTablesApprove = true;
            this.showTable = false;
              this.showTable2 = false;
            this.isApproved = true;
            this.showView = false;
          }

        });
    } else {
      this.institutionmanagement
        .getCollegeApprovalRequestDetails(data.id ? data.id : data.requestId,
          surveyYearCheck,)
        .subscribe((res) => {
          this.obj = res.collegeApprovalRequestDetailsBean;
          // this.userForm.controls['Name'].setValue(this.obj?.collegeName);
          // this.userForm.controls['stateName'].setValue(this.obj?.stateCode);
          // this.getdistrictName();
          // this.userForm.controls['districtName'].setValue(this.obj?.districtCode);
          // this.userForm.controls['standaloneType'].setValue(parseInt(this.obj?.collegeTypeId));
          // this.userForm.controls['managementType'].setValue(this.obj?.collegeManagementTypeId);
          // this.showTablesApprove = true;
          this.userForm1.controls['Name'].setValue(this.obj?.collegeName);
          this.userForm1.controls['stateName'].setValue(this.obj?.stateCode);
          this.getdistrictName();
          this.userForm1.controls['districtName'].setValue(this.obj?.districtCode);
          this.userForm1.controls['instituteType'].setValue(parseInt(this.obj?.collegeTypeId));
          this.userForm1.controls['managementType'].setValue(this.obj?.collegeManagementTypeId);
          this.userForm1.controls['universityId'].setValue(this.obj?.affiliatingUniversityAisheCode.split('-')[1]);


          // this.previousStateId=this.userForm.get('stateName')?.value;
          this.previousUniversityId = this.userForm1.get('universityId')?.value;
          // this.previousDistrictCode=this.userForm.get('districtName')?.value;
          // this.previousFilteredState=this.filteredState;
          this.userForm1.get('universityState')?.setValue(this.obj?.affiliatingUniversityStateCode);
          this.previousBool = false;
          this.affiliatingUniversityStateCode = this.obj?.affiliatingUniversityStateCode;
          if (this.obj?.stateCode == this.obj?.affiliatingUniversityStateCode) {
            this.userForm1.get('location')?.setValue('same');
            this.previousBool = false;
          } else {
            this.userForm1.controls['location'].setValue('others');
            let stateCode = this.userForm1.get('stateName')?.value;
            if (stateCode) {
              // this.userForm.get('location')?.setValue('other');
              this.filteredState2 = this.filteredState.filter((data) => data.stateCode != stateCode);
              // console.log(this.filteredState2)
              this.userData2 = this.userData.filter((data: any) => data.stateCode != stateCode);
              this.userForm1.get('universityState')?.setValue(this.affiliatingUniversityStateCode);
              this.previousBool = true;
            }
          }
          this.loadUniversityData();
          if (res.collegeApprovalRequestDetailsBean.similarCollege.length > 0) {
            // this.SimilarDataTableDataTable = res.collegeApprovalRequestDetailsBean.similarCollege
            this.similarmatTableData = true;
          } else {
            // this.SimilarDataTableDataTable=[]
            this.similarmatTableData = false;
          }

          if (showView) {//show only view
            this.showViewDetailOnly = false;
          } else {//false show only with approve field

            this.showViewDetailOnly = true;
          }
          // if (
          //   res.standaloneApprovalRequestDetailsBean.similarCollege.length > 0
          // ) {
          // this.SimilarDataTableDataTable =
          //   res.collegeApprovalRequestDetailsBean.similarCollege;
          // this.showSimilarDataTable = true;
          this.showTablesApprove = true;
          // }
          // else {
          //   this.SimilarDataTableDataTable = []
          // }
          if (!showView) {
            this.showTablesApprove = true;
              this.showTable = false;
              this.showTable2 = false;
            this.isApproved = true;
            this.showView = true;
          } else {
            this.showTablesApprove = true;
              this.showTable = false;
              this.showTable2 = false;
            this.isApproved = true;
            this.showView = false;
          }

        });
    }
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
  backToSummaryReport() {
    // console.log(JSON.parse(this.localService.getData('searchStateData'))); 
    let viewDetailOfRequestId = JSON.parse(this.localService.getData('viewDetailOfRequestId'));
    viewDetailOfRequestId.status = null;
    this.localService.saveData('viewDetailOfRequestId', JSON.stringify(viewDetailOfRequestId));
    let backURL = JSON.parse(this.localService.getData('searchStateData'));
    backURL.status = true;
    this.localService.saveData('searchStateData', JSON.stringify(backURL))
    this.router.navigate([backURL.backurl])
  }
  alphaNumberOnly(e: any) {  // Accept only alpha numerics, not special characters 
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

  statusReceived(msg: any) {
    this.requestStatus = msg;
    // console.log(msg)
    // console.log(this.obj?.stateBodyTypeName)
    //  if(msg.UNO==='Pending' && this.obj?.stateBodyTypeName!='Other'){
    //   this.userForm.disable()
    //  }else{
    //   this.userForm.enable();
    //  }

  }

  back() {
    this.showTablesApprove = false;
    this.showTable = true;
    this.isApproved = false;
    this.isSubmitted = false;
    if (this.message === 'Standalone') {
      this.userForm.reset();
    } else {
      this.userForm1.reset();
    }

  }

  statusId(statusId: any) {
    this.isStatusId = statusId
    if (statusId == 2) {
      // console.log('2')
      if (this.message === 'Standalone') {
        this.userForm.get('alldocumentsvalid')?.setErrors(null)
      } else {
        this.userForm1.get('alldocumentsvalid')?.setErrors(null)
      }

    }
    else {
      if (this.message === 'Standalone') {
        this.userForm.get('alldocumentsvalid')?.setValidators([Validators.required])
      } else {
        this.userForm1.get('alldocumentsvalid')?.setValidators([Validators.required])
      }

    }


    // console.log('statusId', statusId)
  }
  compareFn(t1: any, t2: any): boolean {

    return t1 && t2 ? t1 === t2 : t1 === t2;
  }

  getStateData() {
    this.institutionmanagement.getState().subscribe((res) => {
      this.userData = res;
      this.filteredState = this.userData.slice();
    });
  }
  districtfilter() {
    this.dataCheck = true;
    this.userForm.controls['districtName'].reset()
  }
  districtfilter1() {
    this.dataCheck = true;
    this.userForm1.controls['districtName'].reset()
  }
  getSubrolls() {
    let boolean = true;

    this.institutionmanagement
      .getSubRoll(this.roleId1).subscribe((res) => {
        this.standalonTypeData = res[0].stateBodies;
        //console.log("this.institutionmanagement.getStandalonInstitution",  this.collegeTypeData)
        this.standalonTypefilter = this.standalonTypeData.slice();
        if (this.message === 'Standalone') {
          res[0].stateBodies.length == 1 ? this.userForm.controls['standaloneType'].setValue(res[0].stateBodies[0]?.id) : '';

        } else {
          res[0].stateBodies.length == 1 ? this.userForm1.controls['standaloneType'].setValue(res[0].stateBodies[0]?.id) : '';

        }
      })

    // 

  }

  findManagmentType() {
    const payload = { type: this.message === 'Standalone' ? 'S' : 'C' };

    this.institutionmanagement.getmanagmentType2(payload).subscribe((res) => {
      this.managmentData = res;
      this.filteredmanagementT = this.managmentData.slice();
    });
  }

  //  findManagmentType() {
  //   let payload={type:'C'}
  //   this.instManagementService.getmanagmentType2(payload).subscribe((res)=>{
  //   this.managmentData=res
  //   this.filteredmanagementT=this.managmentData.slice();
  //   });

  // }

  surveyYearData() {
    this.instManagementService.getSurveyYearList().subscribe((res) => {
      this.surveyYearList = res;
      // console.log('rrrr', this.surveyYearList)
    });
  }
  checkForWhiteSpace() {
    let aremark = this.userForm.get('aRemarks')?.value?.trim();
    this.userForm.get('aRemarks')?.setValue(aremark);
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

  Onsubmit() {
    if (this.message === "Standalone") {
      // console.log("onSubmit",this.userForm)
      this.checkForWhiteSpace();

      // this.userForm.value.currentSurveyYear;
      // this.instiFormData.value.surveryApprove.split('-')[0];
      this.isSubmitted = true;
      let formData = {
        approverId: this.localService.getData('userId'),
        approverRoleId: this.localService.getData('roleId'),
        ipAddress: 'string',
        isDCFApplicable: true,
        remark: this.userForm.value.aRemarks.trim(),
        requestId: this.requestIs,
        statusId: this.userForm.value.approveR,
        surveyYear: this.userForm.value.surveyYear ? this.userForm.value.surveyYear.split('-')[0] : 0,

        name: this.userForm.value.Name ? this.userForm.value.Name : null,
        stateCode: this.userForm.value.stateName ? this.userForm.value.stateName : null,
        districtCode: this.userForm.value.districtName ? this.userForm.value.districtName : null,
        instituteType: this.userForm.value.standaloneType ? this.userForm.value.standaloneType?.toString() : null,
        managementId: this.userForm.value.managementType ? this.userForm.value.managementType : null,

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
                      // this.findDataList();
                      this.totalPending();
                       this.totalRejectedRequestList();
    this.totalRejectedRequest();
    this.getInstituteCount();
                    }
                  });

                }
                if (res.statusCode == 'AISH002') {
                  this.sharedservice.showError(res.statusDesc);
                }
              }, (error) => {
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
            // this.findDataList();
            this.totalPending();
             this.totalRejectedRequestList();
    this.totalRejectedRequest();
    this.getInstituteCount();
          }
        });
      }
    } else {
      this.checkForWhiteSpace();
      // let surveyYear=this.instiFormData.value?.surveyYearValue?this.instiFormData.value?.surveyYearValue.split('-')[0]:0;
      if (this.roleId1 === '26' || this.roleId1 === '1') {
        this.userForm1.controls['surveyYear'].setValidators(Validators.required);
        this.userForm1.controls['surveyYear'].updateValueAndValidity();
      }
      // surveyYear: this.userForm1.value.surveyYear ? this.userForm1.value.surveyYear.split('-')[0] : 0,
      // this.userForm1.value.currentSurveyYear =this.instiFormData.value.surveyYearValue.split('-')[0];
      this.userForm1.value.currentSurveyYear = this.userForm1.value.surveyYear ? this.userForm1.value.surveyYear.split('-')[0] : 0,
        this.isSubmitted = true;
      //  console.log("this.userForm1.value kj",this.userForm1.value);
      if (this.userForm1.invalid) {
        this.sharedservice.showError('All field required!');
        return;
      }
      if (this.userForm1.valid) {
        var dataApprove = this.userForm1.value.approveR;
        //open confirm dialog
        // const dialogRef = this.dialog.open(ConfirmDialogComponent,{
        //   data: {
        //     message: "Please confirm before approving/rejecting this request!!!",
        //     action:'1'
        //   }
        // });
        const dialogRef = this.dialog.open(ConfirmDialogComponent, {
          data: {
            message: this.userForm1.value.approveR,
            action: '1s',
            Approved: 'Approved1',
          },
        });

        //call save api accordingly
        dialogRef.afterClosed().subscribe((confirmed: boolean) => {
          if (confirmed) {
            this.userForm1.value.requestId = this.obj['requestId'];
            let payload = this.userForm1.value;
            delete payload.universityState;
            this.instManagementService
              .postcollageApprovedByUniversity(payload)
              .subscribe((res) => {

                if (res.statusCode == 'AISH001') {
                  if (dataApprove === '1') {//approved
                    let body: any = '';
                    if (this.roleId1 == '26' || this.roleId1 == '1') {
                      body = 'The request for the request id:' + this.obj.requestId + ' has been approved.\n The new AISHE CODE alloted to the institution '
                        + res?.college?.name + ' is ' + res?.college?.id + '.';
                    } else {
                      body = 'The request for the request id:' + this.obj.requestId + ' has been approved and forwarded to next level.';
                    }
                    this.dataEmail = {
                      body: body,
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
                      // this.isApprove = false;
                      // this.getApproveList();
                      this.showTablesApprove = false;
                      this.showTable = true;
                      this.isApproved = false;
                      this.showTablesApprove = false;
                      this.showTable = true;
                      this.userForm1.reset();
                      this.totalPending();
                       this.totalRejectedRequestList();
    this.totalRejectedRequest();
    this.getInstituteCount();
                    }
                  });
                  //navigate to approve page on successfully save {"statusCode":"AISH002","statusDesc":"Invalid details, Please provide valid details."}
                  // this.router.navigateByUrl('aishe/Institution-Management/approve')
                }
                if (res.statusCode == 'AISH002') {
                  this.sharedservice.showError(res.statusDesc);
                }
              }, (error) => {
                // console.log(error)
                this.sharedservice.showError(error.error.message);
              });
          }

          dialogRef.close(true);
        });
        dialogRef.afterClosed().subscribe((result) => {
          if (result) {
            this.showTablesApprove = false;
            this.showTable = true;
            this.isApproved = false;
            this.userForm1.reset();
            // this.findDataList();
            this.totalPending();
             this.totalRejectedRequestList();
    this.totalRejectedRequest();
    this.getInstituteCount();
          }
        });
      }
    }

  }
  // findDataList() {
  //   console.log(this.instiFormData)
  //   this.searchText = '';
  //   let toDate: any = null; let fromDate: any = null;
  //   let formData: any;
  //   if (this.instiFormData.invalid) {
  //     this.sharedservice.showWarning();
  //     this.isFormInvalid = true;
  //     return;
  //   } else {
  //     this.isFormInvalid = false;
  //     if (this.localService.getData('viewDetailOfRequestId')) {
  //       this.instiFormData.controls['pendingLevel'].setValue('');
  //       this.instiFormData.controls['surveryApprove'].setValue('');
  //       this.instiFormData.controls['standaloneType'].setValue('');
  //       this.instiFormData.controls['stateValue'].setValue('');
  //       this.instiFormData.controls['surveyYearValue'].setValue('');
  //       this.instiFormData.controls['stateApprove'].setValue('');
  //       this.instiFormData.controls['districtValue'].setValue('');
  //     }
  //     if (this.roleId1 == '26' || this.roleId1 == '1') {
  //       this.takeAction = true;
  //     } else {
  //       this.takeAction = this.instiFormData.get('pendingLevel')?.value != 3 && this.instiFormData.get('pendingLevel')?.value != 4 && this.instiFormData.get('pendingLevel')?.value != 1;
  //     }

  //   }
  //   if (this.instiFormData.value.toDate != null && this.instiFormData.value.toDate != '') {
  //     toDate = this.formatDate(this.instiFormData.value.toDate)
  //   }
  //   if (this.instiFormData.value.fromDate != null && this.instiFormData.value.fromDate != '') {
  //     fromDate = this.formatDate(this.instiFormData.value.fromDate)
  //   }
  //   if (!this.instiFormData.value.surveryApprove && this.instiFormData.value.RequestId == null) {
  //     this.sharedservice.showError('Please Select Survey Year');
  //     return;
  //   }

  //   if (this.instiFormData.value.RequestId != null && (this.roleId == '26' || this.roleId == '1' || this.roleId == '6' || this.roleId == '8' || this.roleId == '9' || this.roleId == '10' || this.roleId == '11')) {
  //     this.instiFormData.controls['surveryApprove'].clearValidators();
  //     this.instiFormData.controls['surveryApprove'].updateValueAndValidity();
  //   } else {
  //     this.instiFormData.controls['surveryApprove'].setValidators([Validators.required]);
  //     this.instiFormData.controls['surveryApprove'].updateValueAndValidity();
  //   }
  //   // if (
  //   //   (((!this.instiFormData.value.stateApprove &&
  //   //     this.localService.getData('roleId') == '26') ||
  //   //   (!this.instiFormData.value.stateApprove &&
  //   //     this.localService.getData('roleId') == '1')) &&
  //   //     !this.instiFormData.value.RequestId)
  //   // ) {
  //   //   this.sharedservice.showError('Please Select State');
  //   //   return;
  //   // }
  //   if (
  //     (this.instiFormData.value &&
  //       (this.instiFormData.value.stateApprove == null || this.instiFormData.value.stateApprove == '') &&
  //       this.localService.getData('roleId') == '26') ||
  //     ((this.instiFormData.value.stateApprove == null || this.instiFormData.value.stateApprove == '') &&
  //       this.localService.getData('roleId') == '1')
  //   ) {
  //     formData = {
  //       approvalRoleId: this.localService.getData('roleId'),
  //       requestId: this.instiFormData.value.RequestId ? this.instiFormData.value.RequestId : null,
  //       stateCode: '',
  //       surveyYear: this.instiFormData.value.surveryApprove ? this.instiFormData.value.surveryApprove.split('-')[0] : '',
  //       districtValue: this.instiFormData.value.districtValue ? this.instiFormData.value.districtValue.trim() : '',
  //       standaloneType: this.instiFormData.value.standaloneType == ' ' ? this.instiFormData.value.standaloneType.trim() : this.instiFormData.value.standaloneType == null ? '' : this.instiFormData.value.standaloneType,
  //       pendingLevel: this.instiFormData.value.pendingLevel ? this.instiFormData.value.pendingLevel : '',
  //     };
  //   }
  //   if (
  //     (this.instiFormData.value &&
  //       this.instiFormData.value.stateApprove &&
  //       this.localService.getData('roleId') == '26') ||
  //     (this.instiFormData.value.stateApprove &&
  //       this.localService.getData('roleId') == '1')
  //   ) {
  //     formData = {
  //       approvalRoleId: this.localService.getData('roleId'),
  //       requestId: this.instiFormData.value.RequestId ? this.instiFormData.value.RequestId : '',
  //       stateCode:
  //         (this.instiFormData.value.stateApprove &&
  //           this.localService.getData('roleId') == '26') ||
  //           (this.instiFormData.value.stateApprove &&
  //             this.localService.getData('roleId') == '1')
  //           ? this.instiFormData.value.stateApprove
  //           : this.localService.getData('stateCode'),
  //       surveyYear: this.instiFormData.value.surveryApprove ? this.instiFormData.value.surveryApprove.split('-')[0] : '',
  //       districtValue: this.instiFormData.value.districtValue ? this.instiFormData.value.districtValue.trim() : '',
  //       standaloneType: this.instiFormData.value.standaloneType == ' ' ? this.instiFormData.value.standaloneType.trim() : this.instiFormData.value.standaloneType == null ? '' : this.instiFormData.value.standaloneType,
  //       pendingLevel: this.instiFormData.value.pendingLevel ? this.instiFormData.value.pendingLevel : '',
  //     };
  //   }

  //   if (this.instiFormData.value && this.localService.getData('roleId') == '6') {
  //     formData = {
  //       approvalRoleId: this.localService.getData('roleId'),
  //       requestId: this.instiFormData.value.RequestId ? this.instiFormData.value.RequestId : '',
  //       stateCode: this.localService.getData('stateCode'),
  //       surveyYear: this.instiFormData.value.surveryApprove ? this.instiFormData.value.surveryApprove.split('-')[0] : '',
  //       districtValue: this.instiFormData.value.districtValue ? this.instiFormData.value.districtValue.trim() : '',
  //       standaloneType: this.instiFormData.value.standaloneType == ' ' ? this.instiFormData.value.standaloneType.trim() : this.instiFormData.value.standaloneType == null ? '' : this.instiFormData.value.standaloneType,

  //     };
  //   }
  //   if (
  //     this.instiFormData.value.RequestId &&
  //     !this.instiFormData.value.surveryApprove
  //   ) {
  //     let stateCode: any;
  //     if (this.roleId == '6') {//SNO
  //       stateCode = this.localService.getData('stateCode');
  //     } else {
  //       // for MOE
  //       stateCode = this.instiFormData.value.stateValue ? this.instiFormData.value.stateValue : '';
  //     }
  //     formData = {
  //       approvalRoleId: this.localService.getData('roleId'),
  //       requestId: this.instiFormData.value.RequestId ? this.instiFormData.value.RequestId : '',
  //       stateCode: stateCode,
  //       surveyYear: '',
  //       districtValue: this.instiFormData.value.districtValue ? this.instiFormData.value.districtValue.trim() : '',
  //       standaloneType: this.instiFormData.value.standaloneType == ' ' ? this.instiFormData.value.standaloneType.trim() : this.instiFormData.value.standaloneType == null ? '' : this.instiFormData.value.standaloneType,
  //     };
  //   }
  //   if (this.instiFormData.value.surveryApprove && this.localService.getData('roleId') == '8' || this.localService.getData('roleId') == '9' || this.localService.getData('roleId') == '10' || this.localService.getData('roleId') == '11') {
  //     formData = {
  //       surveyYear: this.instiFormData.value.surveryApprove ? this.instiFormData.value.surveryApprove.split('-')[0] : '',
  //       requestId: this.instiFormData.value.RequestId ? this.instiFormData.value.RequestId : null,
  //       approvalRoleId: this.localService.getData('roleId'),
  //       stateCode: this.localService.getData('stateCode'),
  //       districtValue: this.instiFormData.value.districtValue ? this.instiFormData.value.districtValue.trim() : '',
  //       standaloneType: this.instiFormData.value.standaloneType == ' ' ? this.instiFormData.value.standaloneType.trim() : this.instiFormData.value.standaloneType == null ? '' : this.instiFormData.value.standaloneType,
  //     }
  //   }
  //   if (this.roleId == '22') {//NCHMCT
  //     formData = {
  //       surveyYear: this.instiFormData.value.surveryApprove ? this.instiFormData.value.surveryApprove.split('-')[0] : '',
  //       requestId: this.instiFormData.value.RequestId ? this.instiFormData.value.RequestId : null,
  //       approvalRoleId: this.localService.getData('roleId'),
  //       stateCode: '',
  //       districtValue: this.instiFormData.value.districtValue ? this.instiFormData.value.districtValue.trim() : '',
  //       standaloneType: this.instiFormData.value.standaloneType == ' ' ? this.instiFormData.value.standaloneType.trim() : this.instiFormData.value.standaloneType == null ? '' : this.instiFormData.value.standaloneType,
  //     }
  //   }
  //   if (this.roleId1 == '1' || this.roleId1 == '26') {
  //     formData.toDate = toDate ? toDate : null;
  //     formData.fromDate = fromDate ? fromDate : null;
  //   }
  //   //  console.log(formData)
  //   if ((this.roleId1 == '1' || this.roleId == '26' || this.roleId == '6' || this.roleId == '10' || this.roleId == '9' || this.roleId == '8' || this.roleId == '11' || this.roleId == '22') && (this.instiFormData.get('pendingLevel')?.value == 2 || this.instiFormData.get('pendingLevel')?.value == 3 || this.instiFormData.get('pendingLevel')?.value == 4)) {
  //     if (this.instiFormData.get('pendingLevel')?.value == 3) {//sectorial level
  //       formData.approvalRoleId = 100;
  //     }
  //     if (this.instiFormData.get('pendingLevel')?.value == 4) {//SNO level
  //       formData.approvalRoleId = 6;
  //     }
  //     if (this.instiFormData.get('pendingLevel')?.value == 4 && this.roleId == '6') {//find MoE level
  //       formData.approvalRoleId = 1;//MoE
  //     }
  //     this.institutionmanagement
  //       .getStandaloneApprovalListMoE(formData)
  //       .subscribe((res) => {
  //         if (res.statusCode === 'AISH099') {
  //           this.sharedservice.showError(res.statusDesc);
  //           this.userDataTable = [];
  //           this.showTable = true;
  //           this.page = 1;
  //           this.handlePageChange(this.page);
  //         }
  //         if (res.statusCode === 'AISH001') {
  //           this.userDataTable = res.approvalStandaloneListBean;
  //           this.showTable = true;
  //           this.page = 1;
  //           this.handlePageChange(this.page);
  //         }
  //       });
  //   } else {
  //     if (this.roleId == '6') {
  //       formData.approvalRoleId = 6;
  //     }
  //     this.institutionmanagement
  //       .getStandaloneApprovalList(formData)
  //       .subscribe((res) => {
  //         if (res.statusCode === 'AISH099') {
  //           this.sharedservice.showError(res.statusDesc);
  //           this.userDataTable = [];
  //           this.showTable = true;
  //           this.page = 1;
  //           this.handlePageChange(this.page);
  //         }
  //         if (res.statusCode === 'AISH001') {
  //           this.userDataTable = res.approvalStandaloneListBean;
  //           if (this.localService.getData('viewDetailOfRequestId')) {
  //             this.editUser(this.userDataTable[0], true);
  //             this.viewDetailOnly = false;
  //           }
  //           this.showTable = true;
  //           this.page = 1;
  //           this.handlePageChange(this.page);
  //         }
  //       });
  //   }

  // }

  openCheckSimilarDialog() {
    let payload = {
      underState: true,
      requestId: this.requestId
    }
    this.getService.getSimilarInsitute(payload).subscribe((res: any) => {
      if (res.length > 0) {
        this.SimilarDataTableDataTable = res
        let ele = {
          similarIsVisible: true,
          eData: this.SimilarDataTableDataTable,
          key: 'S'
        }
        this.sharedservice.viewProgram(ele)
      }
      else {
        this.SimilarDataTableDataTable = []
        this.sharedservice.showError('Record Not Found!!');
      }
    }, err => {

    })
  }
  openCheckSimilarDialogCollege() {

    // console.log(this.obj?.collegeDistrict)
    let payload = {
      underState: true,
      requestId: this.requestId
    }
    this.getService.getSimilarInsitute(payload).subscribe(res => {
      if (res.length > 0) {
        this.SimilarDataTableDataTable = res;
        let ele = {
          similarIsVisible: true,
          eData: this.SimilarDataTableDataTable,
          key: 'C',
          stateDistric: 1,
          district: this.obj?.collegeDistrict,
          state: this.obj?.collegeState,
        }

        this.sharedservice.viewProgram(ele)
      }
      else {
        this.SimilarDataTableDataTable = []
        this.sharedservice.showError('Record Not Found!!');
      }
    }, err => {

    })

  }

  loadUniversityData() {
    this.UniSurveyYear = '0';
    // this.userForm.controls['universityId'].setValue(null);
    if (this.userForm1.value.surveyYear) {
      this.UniSurveyYear = this.userForm1.value.surveyYear.split('-')[0];
    }
    if (this.userForm1.get('location')?.value == 'same') {
      this.state = this.userForm1.get('stateName')?.value;
    } else {
      this.state = this.roleId1 == '6' || this.roleId1 == '7' ? this.localService.getData('stateCode') : this.userForm1.value.universityState;
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
  OtherStateSelected(event: any) {
    //  console.log(event.value)

    if (event.value === 'others') {
      this.previousBool = true;
      let stateCode = this.userForm.get('stateName')?.value;
      if (stateCode) {
        this.loadUniversityData();
        // this.userForm.get('location')?.setValue('other');
        this.filteredState2 = this.filteredState.filter((data) => data.stateCode != stateCode);
        // console.log(this.filteredState2)
        this.userData2 = this.userData.filter((data: any) => data.stateCode != stateCode);
        this.userForm.get('universityState')?.setValidators(Validators.required);
        this.userForm.get('universityState')?.updateValueAndValidity();
        this.userForm.get('universityState')?.setValue(this.affiliatingUniversityStateCode);
        this.userForm.get('universityId')?.setValue(this.previousUniversityId);
      }
    }
    if (event.value === 'same') {
      this.userForm.get('universityState')?.setValidators(null);
      this.userForm.get('universityState')?.updateValueAndValidity();
      this.loadUniversityData();
      this.userForm.get('universityId')?.setValue(this.previousUniversityId);
      // console.log(this.userForm.value)
      this.previousBool = false;


    }

  }

  getCollegeTypeData() {
    this.instManagementService.getCollegeType().subscribe((res) => {
      this.collegeTypeData = res;
      this.filteredcollegeTypeData = this.collegeTypeData.slice();
    }, err => {

    });

  }

 onPageChangeWork(event: PageEvent) {
  console.log('ee', event)
      this.pageIndexWork = event.pageIndex;
      this.pageSizeWork = event.pageSize;
      this.updatePaginatedDataState();
    }
  updatePaginatedDataState() {
      const startIndex = this.pageIndexWork * this.pageSizeWork;
      const endIndex = startIndex + this.pageSizeWork;
      this.rejectRequestListUpdate = this.rejectRequestList.slice(startIndex, endIndex);
       // Debugging
  console.log("Paginated data:", this.rejectRequestListUpdate.map(d => d.actionOn));
    }

    onPageChangePending(event: PageEvent) {
  console.log('ee', event)
      this.pageIndexWork = event.pageIndex;
      this.pageSizeWork = event.pageSize;
      this.updatePaginatedDataPending();
    }
  updatePaginatedDataPending() {
      const startIndex = this.pageIndexWork * this.pageSizeWork;
      const endIndex = startIndex + this.pageSizeWork;
      this.userDataTableListUpdate = this.userDataTable.slice(startIndex, endIndex);
    }
  // getSubrollsCollege() {
  //   let boolean = true;

  //   this.instManagementService
  //     .getSubRoll(this.roleId1).subscribe((res) => {
  //       this.standalonTypeData = res[0].stateBodies;
  //       //console.log("this.institutionmanagement.getStandalonInstitution",  this.collegeTypeData)
  //       this.standalonTypefilter = this.standalonTypeData?.slice();
  //       res[0].stateBodies.length == 1 ? this.userForm1.controls['instituteType'].setValue(res[0].stateBodies[0]?.id) : '';
  //     })



  // }

}
