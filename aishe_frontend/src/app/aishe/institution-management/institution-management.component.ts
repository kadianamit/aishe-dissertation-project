import { Component, OnDestroy, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SharedService } from 'src/app/shared/shared.service';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { DeAffiliateComponent } from './de-affiliate/de-affiliate.component';
import { DataListComponent } from './data-list/data-list.component';
import { MergecollegesComponent } from './mergecolleges/mergecolleges.component'
import { DataViewComponent } from './data-view/data-view.component';
import { ApproveComponent } from './approve/approve.component';
import { ApprovedUniversityListComponent } from './approved-university-list/approved-university-list.component';
import { NewAffiliationComponent } from './new-affiliation/new-affiliation.component';
import { DeletecollegeComponent } from './deletecollege/deletecollege.component';
import { UpgradeUniversityComponent } from './upgrade-university/upgrade-university.component';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { InsitutionalReportComponent } from './institutional-report/institutional-report.component';
import { Accessibility } from './accessibility';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { ActivatedRoute } from '@angular/router';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/service/format-datepicker';
import { MatTabGroup } from '@angular/material/tabs';

@Component({
  selector: 'app-institution-management',
  templateUrl: './institution-management.component.html',
  styleUrls: ['./institution-management.component.scss'],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ],
})
export class InstitutionManagementComponent implements OnInit, OnDestroy,AfterViewInit {

  @ViewChild("view")
  view?: DataViewComponent;

  @ViewChild("deAffiliateChild") //to get deAffiliate component reference
  deAffiliateChild?: DeAffiliateComponent;
  @ViewChild("upgradeUniversity") //to get data
  upgradeUniversity?: UpgradeUniversityComponent;

  @ViewChild("edit") //to get data list component reference
  edit?: DataListComponent;

  @ViewChild("merge") //to get data list component reference
  merge?: MergecollegesComponent;

  @ViewChild("deleteCollege")
  deleteCollege?: DeletecollegeComponent;

  @ViewChild("Reports")
  showReport?: InsitutionalReportComponent;

  @ViewChild("affiliate")
  affiliate?: NewAffiliationComponent;

  @ViewChild("approvelist")
  approvelist?: ApprovedUniversityListComponent;

  @ViewChild("approve")
  approve?: ApproveComponent;
  passValue: boolean = false;
  showEdit: boolean = false;
  userData: any[] = [];
  dataDistrict: any[] = [];
  collegeTypeData: any[] = [];
  selectedIndexView: boolean = false;
  collegeName: any | null;
  collegeType: any | null;
  districtName: any | null;
  stateName: any | null;
  filteredState: any;
  filteredDistrict: any;
  locationType: any;
  dataDeaffiliation: any[] = [];
  filteredcollegeTypeData: any;
  submitted: boolean = false;
  stateList: any[] = [];
  filterStateList: any[] = [];
  selectedIndexApproveList: boolean = false;
  selectIndexMerge: boolean = false;
  pageSize: any = 10;
  searchText: any;
  tableSize: number[] = [10, 20, 30, 40, 50];
  showReportIndex: boolean = false;

  selectedIndex: any = 0;
  roleId: any;
  StartLimit: number = 0
  EndLimit: number = 25;
  pageData: number = 0;
  selectedIndexEdit: boolean = false;
  page: number = 1;

  filterDeaffiliation: any
  surveyYearList: any;
  selectedYearValue: any = '';
  surveyYearValue: any
  UniSurveyYear: any
  selectedIndexDelete: boolean = false;
  selectedIndexUpgradeUniversity: boolean = false;
  state: any;
  surveyYearOption: any;
  filterSurveyYearOption: any
  selectedIndexApprove: boolean = false;
  universityList: any;
  selectedIndexAffiliate: any = false;
  filtereduniversityList: any[] = [];
  selectedIndexDeAffiliate: any = false;
  selectedIndexDeAffiliateAff: any = false;
  stateCode: any;
  pendingLevel1: any = [{ 'id': 1, 'name': 'All Level Pending' }, { 'id': 2, 'name': 'MoE Level Pending' }, { 'id': 3, 'name': 'UNO Level Pending' }, { 'id': 4, 'name': 'SNO Level Pending' }];
  pendingLevel2: any = [{ 'id': 1, 'name': 'All Level Pending' }, { 'id': 2, 'name': 'UNO Level Pending' }];
  pendingLevel3: any = [{ 'id': 1, 'name': 'All Level Pending' }, { 'id': 2, 'name': 'SNO Level Pending' }, { 'id': 3, 'name': 'UNO Level Pending' }, { 'id': 4, 'name': 'MoE Level Pending' }];
  pendingLevel: any = [];
  @ViewChild('tabGroup') tabGroup!: MatTabGroup;
  showRejectDetail=false;
  rejectedRequestId:any;
  instiForm = new FormGroup({
    searchText: new FormControl(),
    aisheCodeValue: new FormControl(),
    surveyYearValue: new FormControl('', Validators.required),
    stateValue: new FormControl('', Validators.required),
    collegeType: new FormControl(''),
    universityValue: new FormControl('', Validators.required),
    collegeLocation: new FormControl(),
    RequestId: new FormControl(),
    reasonId: new FormControl(),
    surveyYearApprove: new FormControl(),
    districtValue: new FormControl(),
    pendingLevel: new FormControl(),
    fromDate: new FormControl(),
    toDate: new FormControl(),
    DeAffiliateDe: new FormControl()
  });
  districtList: any;
  filterDistrictList: any;
  statusFilter: any;
  accessibilityData: any;
  surveyYearAllList: any;
  collegeCode: any;
  tabIndex = 0;
  viewDetailOfRequestId:any;
  viewDetailOnly=true;
  type:string='College'

  constructor(public http: HttpClient,
    public sharedservice: SharedService, private surveyyearservice: SurveyyearService,
    private institutionmanagement: InstitutionmanagementService, public accessibility: Accessibility, public localStore: LocalserviceService, public localStore1: EncryptDecrypt,
    public localService: LocalserviceService, private route: ActivatedRoute) {

    this.sharedservice.global_loader = true;
  }


  ngOnInit() {
    let collegeCode = this.route.snapshot.paramMap.get('id');
    if (collegeCode) {
      this.collegeCode = this.localStore1?.getDecryptedValue(collegeCode);
      if (this.collegeCode) {
        this.tabIndex=1;
        this.selectedIndex=1;
        this.viewurlData()
      }
    }
    //   let universityCode = this.route.snapshot.paramMap.get('id');
    //  if(universityCode){
    //   this.universityCode=this.localStore1?.getDecryptedValue(universityCode)
    //   console.log(this.universityCode);
    //   if(this.universityCode){
    //     this.viewurlData()
    //   }
    //  }
    this.stateCode = this.localService.getData('stateCode');
    //   this.localService.saveData('test',this.localStore1.getEncryptedValue('jksldfjk'))
    //  console.log(this.localStore1.getDecryptedValue(this.localService.getData('test')))
    //   this.localStore.saveData('id', 'jk123');
    //   console.log('decrypted data', this.localStore.getData('id'));
    this.roleId = this.localService.getData('roleId');
    this.rollBasedValidation();
    this.surveyYear();
    this.getCollegeTypeData();
    // this.loadState();
    // this.loadSurveyYear();
    this.statusFilterData();
    this.getInstitutionManagementRole();
    this.surveyYearAll();
    // this.getSubrolls();
    if (this.roleId == '26' || this.roleId == '1') {//MoE
      this.pendingLevel = this.pendingLevel1;
    } else if (this.roleId == '7') {//UNO
      this.pendingLevel = this.pendingLevel2;
    } else if (this.roleId == '6') {//SNO
      this.pendingLevel = this.pendingLevel3;
    }
    if(this.localService.getData('viewDetailOfRequestId')){
      console.log(JSON.parse(this.localService.getData('viewDetailOfRequestId')))
      this.viewDetailOfRequestId=JSON.parse(this.localService.getData('viewDetailOfRequestId'));
      if(this.viewDetailOfRequestId.status=='PENDING'){
        this.viewDetailOnly=false;
        this.findOnApproveByURL();
        this.tabIndex=3;
        this.selectedIndex=3;
      }
      if(this.viewDetailOfRequestId.status=='APPROVED'){
        this.viewDetailOnly=false;
        this.findOnViewByURL();
        this.tabIndex=1;
        this.selectedIndex=1;
      }
      if(this.viewDetailOfRequestId.status=='REJECTED'){
        this.viewDetailOnly=false;
        this.tabIndex=10;
        this.selectedIndex=10;
        this.sendDetailRequest(this.viewDetailOfRequestId.requestId);
      }

    }
  
  }
  ngOnDestroy(){
    // this.localService.removeData('viewDetailOfRequestId');
    // this.viewDetailOnly=true;
  }
  getInstitutionManagementRole() {
    this.sharedservice.sidebarAccessibility.subscribe((res:any) => {
      if (res) {
        this.accessibilityData = res[0];
      }
    })
  }
  getSubrolls() {
    this.institutionmanagement.getSubRoll(this.roleId).subscribe((res) => {
      // console.log(res)
    })
  }
  saveDraft() {
    //     this.instiForm.controls['surveyYearValue'].setValidators([]);
    // this.instiForm.controls['stateValue'].setValidators([]);
    this.instiForm.clearAsyncValidators();
    this.instiForm.updateValueAndValidity();
    this.instiForm.controls['surveyYearValue'].removeValidators; //Just a fake implementation for demonstration
    this.instiForm.controls['aisheCodeValue'].removeValidators;
    this.instiForm.controls['surveyYearValue'].removeValidators;
    this.instiForm.controls['stateValue'].removeValidators;
    this.instiForm.controls['collegeType'].removeValidators;
    this.instiForm.controls['universityValue'].removeValidators;
    this.instiForm.controls['RequestId'].removeValidators;
    this.instiForm.controls['reasonId'].removeValidators;
    this.instiForm.controls['surveyYearApprove'].removeValidators;
    this.instiForm.controls['collegeLocation'].removeValidators;
  }

  clearUserAsyncValidators() {
    this.instiForm.clearAsyncValidators();
    this.instiForm.updateValueAndValidity();
  }

  clearValidation() {
    this.instiForm.controls['surveyYearValue'].clearValidators();
    this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
    this.instiForm.controls['stateValue'].clearValidators();
    this.instiForm.controls['stateValue'].updateValueAndValidity();
    this.instiForm.controls['universityValue'].clearValidators();
    this.instiForm.controls['universityValue'].updateValueAndValidity();
  }
  updateValidator() {
    if (this.instiForm.controls['RequestId'].value == null && this.roleId != '7' && this.roleId != '26' && this.roleId != '1' && this.roleId != '6') {
      this.instiForm.controls['surveyYearValue'].setValidators(Validators.required);
      this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
    }


  }
  getDeaffiliationReason() {
    this.institutionmanagement.getReasonDeaffileate().subscribe((res) => {
      this.dataDeaffiliation = res.reasonId;
      this.filterDeaffiliation = this.dataDeaffiliation.slice();
    });
  }



  tabSelected(event: any) {
    this.passValue = false;

    if(this.localService.getData('viewDetailOfRequestId')){
      sessionStorage.removeItem('viewDetailOfRequestId');
      this.viewDetailOnly=true;
    }
    // console.log(event.index)
    this.selectedIndex = event.index
     if (this.selectedIndex === 0) {
      //Dashboard
      this.filtereduniversityList = [];
      this.loadState();
      this.saveDraft()
      this.clearValidation();
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');
      this.getDeaffiliationReason();
      this.instiForm.reset();
      this.validationForDelete();
      this.UniSurveyYear = null;
      this.state = null;
      this.selectedIndexUpgradeUniversity = false;
      this.showReportIndex = false;
      this.selectIndexMerge = false;
      this.selectedIndexEdit = false;
      this.selectedIndexDelete = false;
      this.selectedIndexDeAffiliate = false
      this.selectedIndexView = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexApproveList = false;
      this.selectedIndexApprove = false;
      this.selectedIndexDeAffiliateAff = false;

    }
    if (this.selectedIndex === 1) {//View
      this.saveDraft()
      this.filtereduniversityList = [];
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');
      this.clearValidation();
      this.instiForm.reset();
      this.callViewValidation();
      this.UniSurveyYear = null;
      this.state = null;
      this.showReportIndex = false;
      this.selectedIndexDelete = false;
      this.selectedIndexUpgradeUniversity = false;
      this.selectIndexMerge = false;
      this.selectedIndexApprove = false;
      this.selectedIndexEdit = false;
      this.selectedIndexApproveList = false;
      // this.selectIndexMerge = false;
      this.selectedIndexDeAffiliate = false;
      this.selectedIndexDeAffiliateAff = false;
      this.selectedIndexAffiliate = false;

    }
    if (this.selectedIndex === 2) {
      //Edit
      this.filtereduniversityList = [];
      this.saveDraft()
      this.clearValidation();
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');

      this.instiForm.reset();
      this.callEditValidation();
      this.UniSurveyYear = null;
      this.state = null;
      this.showReportIndex = false;
      this.selectedIndexView = false;
      this.selectIndexMerge = false;
      this.selectedIndexDelete = false;
      this.selectedIndexUpgradeUniversity = false;
      this.selectedIndexApprove = false;
      this.selectedIndexDeAffiliate = false;
      this.selectedIndexApproveList = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexDeAffiliateAff = false;

    }
    if (this.selectedIndex === 3) {
      //approve/reject
      this.saveDraft()
      this.clearValidation();
      this.filtereduniversityList = [];
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');

      this.instiForm.reset();
      this.callApproveRejectValidation();
      this.UniSurveyYear = null;
      this.state = null;
      this.showReportIndex = false;
      this.selectIndexMerge = false;
      this.locationType = null;
      this.selectedIndexApproveList = false;
      this.selectedIndexView = false;
      this.selectedIndexDelete = false;
      this.selectedIndexUpgradeUniversity = false;
      this.selectedIndexEdit = false;
      this.selectedIndexDeAffiliate = false
      this.selectedIndexAffiliate = false;
      this.selectedIndexDeAffiliateAff = false;
    }
    if (this.selectedIndex === 4) {
      //affiliate

      this.filtereduniversityList = [];
      this.saveDraft()
      this.clearValidation();
      this.loadState();
      this.getDeaffiliationReason();
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');

      this.instiForm.reset();
      this.callAffiliateValidation();
      this.UniSurveyYear = null;
      this.state = null;
      this.showReportIndex = false;
      this.selectIndexMerge = false;
      // this.state='';
      this.selectedIndexApproveList = false;
      this.selectedIndexDeAffiliate = false
      this.selectedIndexView = false;
      this.selectedIndexDelete = false;
      this.selectedIndexUpgradeUniversity = false;
      this.selectedIndexApprove = false;
      this.selectedIndexEdit = false;
      this.selectedIndexDeAffiliateAff = false;

    }
    if (this.selectedIndex === 5) {
      //De-affiliate
      this.passValue = false;
      this.filtereduniversityList = [];
      this.clearValidation();
      this.saveDraft()
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');

      this.instiForm.reset();
      this.callDeAffiliateValidation();
      this.UniSurveyYear = null;
      this.state = null;
      this.showReportIndex = false;
      this.selectIndexMerge = false;
      this.selectedIndexApproveList = false;
      this.selectedIndexView = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexApprove = false;
      this.selectedIndexDelete = false;
      this.selectedIndexUpgradeUniversity = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexEdit = false;
      this.instiForm.value.DeAffiliateDe = false
      this.selectedIndexDeAffiliateAff = false;
    }
    if (this.selectedIndex === 6) {
      //De-affiliate and shift university validation are same
      this.passValue = true;
      this.instiForm.value.DeAffiliateDe = true
      this.filtereduniversityList = [];
      this.clearValidation();
      this.saveDraft()
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');

      this.instiForm.reset();
      this.callDeAffiliateValidation();
      this.UniSurveyYear = null;
      this.state = null;
      this.showReportIndex = false;
      this.selectIndexMerge = false;
      this.selectedIndexApproveList = false;
      this.selectedIndexView = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexApprove = false;
      this.selectedIndexDelete = false;
      this.selectedIndexUpgradeUniversity = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexEdit = false;
      this.selectedIndexDeAffiliateAff = false;
    }
    if (this.selectedIndex === 7 && !(this.roleId === '1' || this.roleId === '26')) {
      //merging college
      this.filtereduniversityList = [];
      this.saveDraft();
      this.loadState();
      this.clearValidation();
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');

      this.instiForm.reset();

      this.UniSurveyYear = null;
      this.state = null;
      this.showReportIndex = false;
      this.selectIndexMerge = false;
      this.selectedIndexEdit = false;
      this.selectedIndexDeAffiliate = false
      this.selectedIndexView = false;
      this.selectedIndexDelete = false;
      this.selectedIndexUpgradeUniversity = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexApprove = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexDeAffiliateAff = false;
    }

    if (this.selectedIndex === '7' && (this.roleId === '1' || this.roleId === '26')) {
      //alert('iop');//approved list
      this.loadState();
      this.saveDraft()
      this.clearValidation();
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');
      this.filtereduniversityList = [];
      this.instiForm.reset();
      this.UniSurveyYear = null;
      this.state = null;
      this.showReportIndex = false;
      this.loadSurveyYear();
      this.getDeaffiliationReason();
      this.validationOfMergingColleges();
      this.selectedIndexEdit = false;
      this.selectedIndexDeAffiliate = false
      this.selectedIndexView = false;
      this.selectIndexMerge = false;
      this.selectedIndexDelete = false;
      this.selectedIndexUpgradeUniversity = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexApprove = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexApproveList = false;
      this.selectedIndexDeAffiliateAff = false;
    }

    if (this.selectedIndex === 8) {
      //Delete
      this.filtereduniversityList = [];
      this.loadState();
      this.saveDraft()
      this.clearValidation();
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');
      this.getDeaffiliationReason();
      this.instiForm.reset();
      this.validationForDelete();
      this.UniSurveyYear = null;
      this.state = null;
      this.selectedIndexUpgradeUniversity = false;
      this.showReportIndex = false;
      this.selectIndexMerge = false;
      this.selectedIndexEdit = false;
      this.selectedIndexDelete = false;
      this.selectedIndexDeAffiliate = false
      this.selectedIndexView = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexApproveList = false;
      this.selectedIndexApprove = false;
      this.selectedIndexDeAffiliateAff = false;

    }
    if (this.selectedIndex === 9) {
     //upgrade to university
      this.filtereduniversityList = [];
      this.loadState();
      this.saveDraft()
      this.clearValidation();
      this.getDeaffiliationReason();
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');
      this.instiForm.reset();
      this.validationForUpGradToUniversity();
      this.UniSurveyYear = null;
      this.state = null;
      this.showReportIndex = false;
      this.selectedIndexDeAffiliate = false
      this.selectIndexMerge = false;
      this.selectedIndexEdit = false;
      this.selectedIndexView = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexDelete = false;
      this.selectedIndexApprove = false;
      this.selectedIndexApproveList = false;
      this.selectedIndexDeAffiliateAff = false;

    }
    if (this.selectedIndex === 11) {

      this.filtereduniversityList = [];
      this.saveDraft()
      this.clearValidation();
      this.instiForm.controls['aisheCodeValue'].setValue('');
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['RequestId'].setValue('');
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['surveyYearApprove'].setValue('');

      this.instiForm.reset();
      this.UniSurveyYear = null;
      this.state = null;
      this.showReportIndex = true;
      this.selectedIndexDeAffiliate = false
      this.selectIndexMerge = false;
      this.selectedIndexEdit = false;
      this.selectedIndexView = false;
      this.selectedIndexAffiliate = false;
      this.selectedIndexDelete = false;
      this.selectedIndexApprove = false;
      this.selectedIndexApproveList = false;
      this.selectedIndexUpgradeUniversity = false;
      this.selectedIndexDeAffiliateAff = false;
    }
  }

  loadSurveyYear() {
    this.surveyyearservice.getdatasurveyyear().subscribe((res) => {
      let obj = { surveyYear: '2022', surveyYearValue: '2022-23' }
      res.unshift(obj)
      this.surveyYearOption = res;

      this.filterSurveyYearOption = this.surveyYearOption.slice()
    })
  }

  surveyYear() {
    this.institutionmanagement.getSurveyYearListPrivate().subscribe((res) => {
      this.surveyYearList = res;
    });
  }
  surveyYearAll() {
    this.institutionmanagement.getYearList().subscribe((res) => {
      // console.log(res)
      this.surveyYearAllList = res;
    });
  }


  loadUniversityData() {

    this.instiForm.controls['universityValue'].reset();
    if (this.roleId == '6') {


      if (this.instiForm.value.surveyYearValue) {
        this.UniSurveyYear = this.instiForm.value.surveyYearValue.split('-')[0];
      }

      this.instiForm.get('aisheCodeValue')?.setValue('');
      this.instiForm.get('RequestId')?.setValue('');
      if (this.roleId == '1' || this.roleId == '26') {
        // this.instiForm.controls['universityValue'].setValidators([Validators.required]);
        // this.instiForm.controls['universityValue'].updateValueAndValidity
      }

      this.state = this.roleId == '6' || this.roleId == '7' ? this.localService.getData('stateCode') : this.instiForm.value.stateValue;
      if (this.UniSurveyYear && this.state && this.instiForm.value.surveyYearValue) {
        this.institutionmanagement.getUniversity(this.state, this.UniSurveyYear).subscribe((res) => {

          if (res) {
            this.universityList = res;
            this.filtereduniversityList = this.universityList.slice();
          }

        }
        )
      }
    }
    if (this.roleId == '1' || this.roleId == '26') {
      this.state = this.roleId == '6' || this.roleId == '7' ? this.localService.getData('stateCode') : this.instiForm.value.stateValue;
      this.UniSurveyYear = 0;
      this.state = this.state != "" ? this.state : 'ALL';
      if (this.state) {
        this.institutionmanagement.getUniversity(this.state, this.UniSurveyYear).subscribe((res) => {

          if (res) {
            this.universityList = res;
            this.filtereduniversityList = this.universityList.slice();
          }

        }
        )

      }
    }

  }

  getCollegeTypeData() {
    this.institutionmanagement.getCollegeTypePrivate().subscribe((res) => {
      this.collegeTypeData = res;
      this.filteredcollegeTypeData = this.collegeTypeData.slice();
    });

  }


  loadState() {
    if (this.roleId === '26' || this.roleId === '1' || this.roleId === '7' || this.roleId === '6') {
      this.institutionmanagement.getStatePrivate().subscribe((res) => {

        this.stateList = res;
        this.filterStateList = this.stateList.slice();
      });
    }
  }
  loadDistrict(stateId: any) {
    if (this.roleId == '26' || this.roleId == '1' || this.roleId == '7' || this.roleId == '6') {
      this.institutionmanagement.getDistrict(stateId.value).subscribe((res) => {

        this.districtList = res;
        this.filterDistrictList = this.districtList.slice();
      });
    }
  }


  get f(): { [key: string]: AbstractControl } {
    return this.instiForm.controls;
  }
  resetform() {
    this.instiForm.reset();
    this.instiForm.get('collegeType')?.setValue('');
  }
  viewurlData() {
    this.instiForm.get('searchText')?.setValue(this.collegeCode)
    this.selectedIndexView = true;
    this.view?.findData();
  }

  findOnView() {
    this.updateValidator()
    this.selectedIndexView = true;
    this.view?.findData();

  }
  findOnViewByURL() {
    // this.updateValidator()
    this.instiForm.get('searchText')?.setValue(this.viewDetailOfRequestId.aisheCode);
    // this.instiForm.controls['surveyYearValue'].clearValidators();
    // this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
    // this.instiForm.controls['stateValue'].clearValidators();
    // this.instiForm.controls['stateValue'].updateValueAndValidity();
    // this.instiForm.controls['universityValue'].clearValidators();
    // this.instiForm.controls['universityValue'].updateValueAndValidity();
    this.instiForm.controls['pendingLevel'].clearValidators();
    this.instiForm.controls['pendingLevel'].updateValueAndValidity();
    this.instiForm.controls['surveyYearValue'].setValue(' ');
    this.instiForm.controls['stateValue'].setValue(' ');
    this.instiForm.controls['universityValue'].setValue(' ');
    this.selectedIndexView = true;
    this.view?.findData();

  }

  findOnApprove() {
    this.updateValidator()
    this.selectedIndexApprove = true
    this.approve?.getApproveList();

  }
  findOnApproveByURL() {
    this.updateValidator();
    // this.selectedIndex=2;
    // this.tabIndex=2;
    // this.tabSelected({index:2});
    this.instiForm.get('RequestId')?.setValue(this.viewDetailOfRequestId.requestId);
    this.instiForm.get('surveyYearValue')?.setValue(this.viewDetailOfRequestId.surveyYear);
    this.instiForm.get('stateValue')?.clearValidators();
    this.instiForm.get('stateValue')?.updateValueAndValidity();
    this.instiForm.get('universityValue')?.clearValidators();
    this.instiForm.get('universityValue')?.updateValueAndValidity();
    this.instiForm.get('pendingLevel')?.clearValidators();
    this.instiForm.get('pendingLevel')?.updateValueAndValidity();
    this.selectedIndexApprove = true
    this.approve?.getApproveList();

  }
  editData() {
    this.selectedIndexEdit = true
    this.updateValidator()
    this.edit?.findDataEdit1();
  }

  findOnAffiliate() {
    this.updateValidator()
    this.selectedIndexAffiliate = true
    this.affiliate?.getdatalist();

  }
  findOnDeAffiliate() {
    this.passValue = false;
    this.updateValidator()
    this.selectedIndexDeAffiliate = true;
    this.selectedIndexDeAffiliateAff = false;
    this.deAffiliateChild?.dataDeaffiliate();

  }

  findOnDeAffiliateDe() {//shift university
    this.passValue = true;
    this.updateValidator()
    this.selectedIndexDeAffiliate = false;
    this.selectedIndexDeAffiliateAff = true;
    this.deAffiliateChild?.dataDeaffiliateDe();
  }
  findOnApprovedList() {
    this.updateValidator()
    this.instiForm.controls['stateValue'].clearValidators();
    this.instiForm.controls['universityValue'].clearValidators();
    this.instiForm.controls['stateValue'].updateValueAndValidity();
    this.instiForm.controls['universityValue'].updateValueAndValidity();
    this.selectedIndexApproveList = true
    this.approvelist?.approvalUniversity();

  }

  mergeColleges() {
    this.updateValidator()
    this.selectIndexMerge = true
    this.merge?.mergeCollgeData();


  }

  deleteCollegeData() {
    this.updateValidator()
    this.selectedIndexDelete = true
    this.deleteCollege?.deleteCollegeData();

  }

  upgradeUniversityData() {
    this.updateValidator()
    this.selectedIndexUpgradeUniversity = true
    this.upgradeUniversity?.findData();

  }
  findReport(reportType: any) {
    this.updateValidator()
    this.showReport?.findData(reportType);
    this.showReportIndex = true;
  }
  //************************************************** */
  editCollegeValidation() {
    // this.instiForm.controls['aisheCodeValue'].valueChanges.subscribe((res)=>{
    //   console.log(res)
    // })
    if (this.instiForm.controls['aisheCodeValue'].value) {
      this.instiForm.controls['stateValue'].clearValidators();
      this.instiForm.controls['stateValue'].markAsUntouched();
      this.instiForm.controls['stateValue'].markAsPristine();
      this.instiForm.controls['stateValue'].updateValueAndValidity();


      this.instiForm.controls['universityValue'].clearValidators();
      this.instiForm.controls['universityValue'].markAsUntouched();
      this.instiForm.controls['universityValue'].markAsPristine();
      this.instiForm.controls['universityValue'].updateValueAndValidity();

      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');

    } else {
      if (this.roleId == '6') {//SNO
        this.instiForm.controls['universityValue'].setValidators(Validators.required);
        this.instiForm.controls['universityValue'].updateValueAndValidity();
      }
      if (this.roleId == '26' || this.roleId == '1') {//MoE
        this.instiForm.controls['stateValue'].setValidators(Validators.required);
        this.instiForm.controls['stateValue'].updateValueAndValidity();

        this.instiForm.controls['universityValue'].setValidators(Validators.required);
        this.instiForm.controls['universityValue'].updateValueAndValidity();
      }



    }
  }
  callViewValidation() {
    if (this.roleId == '26' || this.roleId == '1') {
      this.instiForm.controls['stateValue'].setValidators(Validators.required);
      this.instiForm.controls['stateValue'].updateValueAndValidity();

      this.instiForm.controls['universityValue'].setValidators(Validators.required);
      this.instiForm.controls['universityValue'].updateValueAndValidity();


    }
    if (this.roleId == '6') {//sno
      this.instiForm.get('stateValue')?.setValue(this.stateCode);
      this.instiForm.controls['universityValue'].setValidators(Validators.required);
      this.instiForm.controls['universityValue'].updateValueAndValidity();
    }
    if (this.roleId == '7') {//uno
      this.instiForm.controls['universityValue'].clearValidators;
      this.instiForm.controls['universityValue'].updateValueAndValidity();
    }
    this.instiForm.controls['collegeType'].setValue('');
    this.instiForm.controls['districtValue'].clearValidators();
    this.instiForm.controls['districtValue'].updateValueAndValidity();
    this.instiForm.controls['pendingLevel'].clearValidators();
    this.instiForm.controls['pendingLevel'].updateValueAndValidity();
  }
  callEditValidation() {
    if (this.roleId === '26' || this.roleId === '1') {
      this.instiForm.controls['stateValue'].setValidators(Validators.required);
      this.instiForm.controls['stateValue'].updateValueAndValidity();
    }
    if (this.roleId === '26' || this.roleId === '1' || this.roleId == '6') {
      this.instiForm.controls['universityValue'].setValidators(Validators.required);
      this.instiForm.controls['universityValue'].updateValueAndValidity();
    }
    // if(this.roleId==='7'){//uno
    //   this.instiForm.controls['universityValue'].clearValidators();
    //   this.instiForm.controls['universityValue'].updateValueAndValidity();
    // }
    this.instiForm.controls['collegeType'].setValue('');
    this.instiForm.controls['districtValue'].clearValidators();
    this.instiForm.controls['districtValue'].updateValueAndValidity();
    this.instiForm.controls['pendingLevel'].clearValidators();
    this.instiForm.controls['pendingLevel'].updateValueAndValidity();
  }
  callApproveRejectValidation() {
    this.instiForm.controls['surveyYearValue'].setValidators(Validators.required);
    this.instiForm.controls['surveyYearValue'].updateValueAndValidity();

    if (this.roleId === '26' || this.roleId === '1') {
      // this.instiForm.controls['stateValue'].setValidators(Validators.required);
      // this.instiForm.controls['stateValue'].updateValueAndValidity();
      this.instiForm.controls['pendingLevel'].setValidators(Validators.required);
      this.instiForm.controls['pendingLevel'].updateValueAndValidity();
    }
    this.instiForm.controls['collegeType'].setValue('');

  }
  isRequstId() {
    if (this.instiForm.controls['RequestId'].value) {
      this.instiForm.controls['surveyYearValue'].clearValidators();
      this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
      this.instiForm.controls['surveyYearValue'].setValue('');

      this.instiForm.controls['pendingLevel'].clearValidators();
      this.instiForm.controls['pendingLevel'].updateValueAndValidity();
      this.instiForm.controls['pendingLevel'].setValue('');

      if (this.roleId === '26' || this.roleId === '1') {
        this.instiForm.controls['stateValue'].clearValidators();
        this.instiForm.controls['stateValue'].updateValueAndValidity();
        this.instiForm.controls['stateValue'].setValue('');
        this.instiForm.controls['pendingLevel'].clearValidators();
        this.instiForm.controls['pendingLevel'].updateValueAndValidity();

      }
      this.instiForm.controls['universityValue'].clearValidators();
      this.instiForm.controls['universityValue'].updateValueAndValidity();
      this.instiForm.controls['universityValue'].setValue('');



    } else {
      this.callApproveRejectValidation();
    }
  }
  statusFilterData() {
    this.institutionmanagement.getStatePrivate().subscribe((res) => {
      this.statusFilter = res;
    });
  }
  callAffiliateValidation() {
    if (this.roleId === '26' || this.roleId === '1') {//MoE
      this.instiForm.controls['surveyYearValue'].clearValidators();
      this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
      this.instiForm.controls['surveyYearValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['pendingLevel'].clearValidators();
      this.instiForm.controls['pendingLevel'].updateValueAndValidity();

      if (!this.instiForm.controls['aisheCodeValue'].value) {
        this.instiForm.controls['stateValue'].setValidators(Validators.required);
        this.instiForm.controls['stateValue'].updateValueAndValidity();

        this.instiForm.controls['districtValue'].setValidators(Validators.required);
        this.instiForm.controls['districtValue'].updateValueAndValidity();
      }


    }

    if (this.roleId == '6') {//SNO
      // this.instiForm.controls['universityValue'].setValidators(Validators.required);
      // this.instiForm.controls['universityValue'].updateValueAndValidity();
      this.instiForm.controls['surveyYearValue'].clearValidators();
      this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
      this.instiForm.controls['surveyYearValue'].setValue('');

      this.instiForm.controls['stateValue'].setValidators(Validators.required);
      this.instiForm.controls['stateValue'].updateValueAndValidity();
    }
    if (this.roleId === '7') {//UNO
      this.instiForm.controls['surveyYearValue'].clearValidators();
      this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
      this.instiForm.controls['surveyYearValue'].setValue('');


      this.instiForm.controls['stateValue'].setValidators(Validators.required);
      this.instiForm.controls['stateValue'].updateValueAndValidity();

    }


  }
  findThrougAisheCodeAffiliate() {
    if (this.instiForm.controls['aisheCodeValue'].value) {
      this.instiForm.controls['stateValue'].clearValidators();
      this.instiForm.controls['stateValue'].updateValueAndValidity();
      this.instiForm.controls['stateValue'].setValue('');

      this.instiForm.controls['districtValue'].clearValidators();
      this.instiForm.controls['districtValue'].updateValueAndValidity();
      this.instiForm.controls['reasonId'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
    } else {
      this.callAffiliateValidation();
    }

  }
  callDeAffiliateValidation() {
    this.instiForm.controls['stateValue'].setValidators(Validators.required);
    this.instiForm.controls['stateValue'].updateValueAndValidity();

    this.instiForm.controls['universityValue'].setValidators(Validators.required);
    this.instiForm.controls['universityValue'].updateValueAndValidity();

    this.instiForm.controls['collegeType'].setValue('');
    this.instiForm.controls['pendingLevel'].clearValidators();
    this.instiForm.controls['pendingLevel'].updateValueAndValidity();
  }

  findThrougAisheCodeDeAffiliate() {
    if (this.instiForm.controls['aisheCodeValue'].value) {
      this.instiForm.controls['stateValue'].clearValidators();
      this.instiForm.controls['stateValue'].updateValueAndValidity();

      this.instiForm.controls['universityValue'].clearValidators();
      this.instiForm.controls['universityValue'].updateValueAndValidity();

      this.instiForm.controls['stateValue'].setValue('');
      this.instiForm.controls['universityValue'].setValue('');
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['pendingLevel'].clearValidators();
      this.instiForm.controls['pendingLevel'].updateValueAndValidity();
    } else {
      this.callAffiliateValidation();
    }

  }
  validationForDelete() {
    if (this.roleId == 26 || this.roleId == 1) {
      // this.instiForm.controls['surveyYearValue'].setValidators(Validators.required);
      // this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
      this.instiForm.controls['stateValue'].setValidators(Validators.required);
      this.instiForm.controls['stateValue'].updateValueAndValidity();
      this.instiForm.controls['districtValue'].setValidators(Validators.required);
      this.instiForm.controls['districtValue'].updateValueAndValidity();

      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['pendingLevel'].clearValidators();
      this.instiForm.controls['pendingLevel'].updateValueAndValidity();
    }

  }
  validationForUpGradToUniversity() {
    if (this.roleId == 26 || this.roleId == 1) {
      this.instiForm.controls['stateValue'].setValidators(Validators.required);
      this.instiForm.controls['stateValue'].updateValueAndValidity();
      this.instiForm.controls['districtValue'].setValidators(Validators.required);
      this.instiForm.controls['districtValue'].updateValueAndValidity();
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['pendingLevel'].clearValidators();
      this.instiForm.controls['pendingLevel'].updateValueAndValidity();
    }
  }
  validationOfMergingColleges() {
    if (this.roleId == 26 || this.roleId == 1) {
      this.instiForm.controls['stateValue'].setValidators(Validators.required);
      this.instiForm.controls['stateValue'].updateValueAndValidity();
      this.instiForm.controls['surveyYearValue'].clearValidators();
      this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
      this.instiForm.controls['districtValue'].clearValidators();
      this.instiForm.controls['districtValue'].updateValueAndValidity();
      this.instiForm.controls['collegeType'].setValue('');
      this.instiForm.controls['pendingLevel'].clearValidators();
      this.instiForm.controls['pendingLevel'].updateValueAndValidity();
    }
  }

  rollBasedValidation() {
    if (this.roleId == 6) {
      this.instiForm.controls['stateValue'].clearValidators();
      this.instiForm.controls['stateValue'].updateValueAndValidity();
    }
    if (this.roleId == 7) {
      this.instiForm.controls['stateValue'].clearValidators();
      this.instiForm.controls['stateValue'].updateValueAndValidity();

      this.instiForm.controls['universityValue'].clearValidators();
      this.instiForm.controls['universityValue'].updateValueAndValidity();
      this.instiForm.controls['pendingLevel'].clearValidators();
      this.instiForm.controls['pendingLevel'].updateValueAndValidity();
    }
  }
  alphaNumberOnly(e: any) {  // Accept only alpha numerics, not special characters
    var regex = new RegExp("[0-9]");
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
  seachTextValue() {
    // console.log(this.instiForm.controls['searchText'].value.length>0)
    if (this.instiForm.controls['searchText'].value.length > 0) {
      this.instiForm.controls['surveyYearValue'].clearValidators();
      this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
      this.instiForm.controls['stateValue'].clearValidators();
      this.instiForm.controls['stateValue'].updateValueAndValidity();
      this.instiForm.controls['universityValue'].clearValidators();
      this.instiForm.controls['universityValue'].updateValueAndValidity();
      this.instiForm.controls['pendingLevel'].clearValidators();
      this.instiForm.controls['pendingLevel'].updateValueAndValidity();
    } else {
      this.callViewValidation();
    }
  }
  
 
  getRejectedList() {
    if (!this.instiForm.controls['surveyYearValue'].value) {
      this.sharedservice.showValidationMessage('Please select survey year !!!');
      return;
    }
    let payload = {
      listType: 'REJECTED',
      responseType: 'LIST',
      surveyYear: this.instiForm.controls['surveyYearValue'].value,
      type:'COLLEGE'
    }
    this.institutionmanagement.getRejected(payload).subscribe(res => {
      this.alldata = res.data;
      this.tempUserList = [...this.alldata]
      this.handlePageChange(this.sharedservice.page = 1)
    }, err => {

    })
  }
  alldata:Array<any>=[];
  tempUserList:Array<any>=[]
  handlePageChange(event: any) {
    this.sharedservice.page = event
    this.sharedservice.StartLimit = ((this.sharedservice.page - 1) * Number(this.sharedservice.pageSize)),
      this.sharedservice.EndLimit = this.sharedservice.StartLimit + Number(this.sharedservice.pageSize)
    var a = Math.ceil(this.alldata.length / Number(this.sharedservice.pageSize));
    if (a === event) {
      this.sharedservice.pageData = Math.min(this.sharedservice.StartLimit + Number(this.sharedservice.pageSize), this.alldata.length);
    } else {
      this.sharedservice.pageData = Math.min(this.sharedservice.StartLimit + Number(this.sharedservice.pageSize), this.alldata.length - 1);
    }

  }
  sendDetailRequest(requestId:any){
    // console.log(requestId)
    this.showRejectDetail=true;
    this.rejectedRequestId=requestId;
  }
  onStatusChanged(value: boolean) {
    this.showRejectDetail = value;
    // console.log('Child sent:', value);
  }

  onChange(event: any) {
    this.sharedservice.pageSize = parseInt(event);
    this.handlePageChange(this.sharedservice.page = 1)
  }

  async updateResults() {
    this.alldata = []
    this.alldata = this.searchByValue(this.tempUserList);
    this.handlePageChange(this.sharedservice.page = 1)
  }
  searchByValue(data: any) {
    return data.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.requestId?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.name?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.institutionTypeName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.actionOn?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.actionBy?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.remarks?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          // || (item.submittedOn?.toString().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  ngAfterViewInit() {
    if(this.localService.getData('viewDetailOfRequestId')){
      // if(this.viewDetailOfRequestId.status=='PENDING'){
      //   setTimeout(() => {
      //     this.tabGroup.selectedIndex = 3; 
      //   });
      // }
      // if(this.viewDetailOfRequestId.status=='APPROVED'){
      //   setTimeout(() => {
      //     this.tabGroup.selectedIndex = 1; 
      //   });
      // }
    }
  }
}
