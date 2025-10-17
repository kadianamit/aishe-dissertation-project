import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, AbstractControl, Validators } from '@angular/forms';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import { StandaloneViewComponent } from '../standalone-view/standalone-view.component';
import { StandaloneEditComponent } from '../standalone-edit/standalone-edit.component';
import { StandaloneApproveComponent } from '../standalone-approve/standalone-approve.component';
import { StandaloneApproveListComponent } from '../standalone-approve-list/standalone-approve-list.component';
import { StandaloneStatusComponent } from '../standalone-status/standalone-status.component';
import { UpgradetocollegeComponent } from '../upgradetocollege/upgradetocollege.component';
import { UpgradeStandalonetoUniversityComponent } from '../upgrade-standaloneto-university/upgrade-standaloneto-university.component';
import { InsitutionalReportComponent } from '../institutional-report/institutional-report.component';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { Accessibility } from '../accessibility';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { ActivatedRoute } from '@angular/router';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/service/format-datepicker';
import { MatTabGroup } from '@angular/material/tabs';
@Component({
  selector: 'app-standalone',
  templateUrl: './standalone.component.html',
  styleUrls: ['./standalone.component.scss'],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ],
})
export class StandaloneComponent implements OnInit,OnDestroy,AfterViewInit {


  @ViewChild("view")
  view?:StandaloneViewComponent;

  @ViewChild("edit") //to get data list component reference
  edit?:StandaloneEditComponent;

  @ViewChild("approve")
  approve?:StandaloneApproveComponent;

  @ViewChild("upgradetoClg")
  upgradetoClg?:UpgradetocollegeComponent;
  @ViewChild("Reports")
  showReport?:InsitutionalReportComponent;


  @ViewChild("upgradetoUniversity")
  upgradetoUniversity?:UpgradeStandalonetoUniversityComponent;

  @ViewChild("approveList1")
  approveList1?:StandaloneApproveListComponent
  //approveList1?:StandaloneApproveListComponent;
@ViewChild("status1")
status1?:StandaloneStatusComponent;

@ViewChild('tabGroup') tabGroup!: MatTabGroup;
  showEdit:boolean=false;
  userData: any[] = [];
  dataDistrict: any[] = [];
  collegeTypeData: any[] = [];
  surveyYearOption:any;
  filterSurveyYearOption:any[] = [];
  selectedIndexView:boolean = false;
  collegeName: any | null;
  collegeType: any | null;
  districtName: any | null;
  stateName: any | null;
  filteredState: any;
  filteredDistrict: any;
  selectedIndexStatus:boolean=false;
  filteredcollegeTypeData: any;
  submitted = false;
  stateList:any[] = [];
  selectedIndexApproveList:boolean=false;
  pageSize: any = 10;
  searchText: any;
  tableSize: number[] = [10, 20, 30, 40, 50];

  selectedIndex: any = 0;
  roleId:any;
  StartLimit:number=0
  EndLimit: number = 25;
  pageData: number = 0;
  selectedIndexEdit:boolean=false;
  page: number = 1;
  filterStateList:any;
  selectedIndexView11:any
  surveyYearList: any;
  selectedYearValue: any = '';
  surveyYearValue:any="";
  UniSurveyYear:any=null
  state:any;
  selectedIndexUpgradetoClg:boolean = false;
  selectedIndexUpgradetoUniversity:boolean = false;
  standalonTypeData:any;
  standalonTypefilter:any[] = [];
  selectedIndexApprove:boolean=false;
  universityList:any=[];
  selectedIndexAffiliate:any=false;
  filtereduniversityList:any
  selectedIndexDeAffiliate:any=false;
  showReportIndex:boolean=false;
  instiForm=new FormGroup({
    searchText:new FormControl(),
    aisheCodeValue:new FormControl(),
    surveyYearValue: new FormControl('',Validators.required),
    stateValue: new FormControl('',Validators.required),
    standaloneType: new FormControl('',Validators.required),
    standaloneValue: new FormControl(),
    stateApprove: new FormControl(),
    surveryApprove: new FormControl(),
    RequestId:new FormControl(),
    status: new FormControl(''),
    districtValue:new FormControl(),
    pendingLevel:new FormControl(),
    fromDate:new FormControl(),
    toDate:new FormControl(),

  });
  loginMode: any='S';
  accessibilityData: any;
  surveyYearAllList: any;
  districtList: any;
  filterDistrictList: any;
  stateBodyData: any;
  showStateBodayDropDown: boolean=false;
  pendingLevel1:any=[{'id':1,'name':'All Level Pending'},{'id':2,'name':'MoE Level Pending'},{'id':4,'name':'SNO Level Pending'},{'id':3,'name':'Sectorial Level Pending'}];
  pendingLevel2:any=[{'id':1,'name':'All Level Pending'},{'id':2,'name':'SNO Level Pending'},{'id':3,'name':'Sectorial Level Pending'},{'id':4,'name':'MoE Level Pending'}];
  pendingLevel3:any=[{'id':1,'name':'All Level Pending'},{'id':2,'name':'SCERT Level Pending'}];
  pendingLevel4:any=[{'id':1,'name':'All Level Pending'},{'id':2,'name':'Nursing Level Pending'}];
  pendingLevel5:any=[{'id':1,'name':'All Level Pending'},{'id':2,'name':'DTE Level Pending'}];
  pendingLevel6:any=[{'id':1,'name':'All Level Pending'},{'id':2,'name':'SPC Level Pending'}];
  pendingLevel7:any=[{'id':1,'name':'All Level Pending'},{'id':2,'name':'NCHMCT Level Pending'}];
  pendingLevel:any;
  standaloneCode: any;
  viewDetailOfRequestId: any;
  viewDetailOnly: boolean=true;
  tabIndex = 0 ;
  type:string='Standalone'
  showRejectDetail: boolean=false;
  rejectedRequestId: any;
  constructor(public http: HttpClient,
    public sharedservice: SharedService,private surveyyearservice:SurveyyearService,private route: ActivatedRoute,public localStore1:EncryptDecrypt,
    private institutionmanagement: InstitutionmanagementService,public accessibility:Accessibility,public localService:LocalserviceService
    ) {
      this.sharedservice.global_loader = true;
      this.loginMode=this.localService.getData('loginMode');
      this.roleId=this.localService.getData('roleId');
   }

  ngOnInit() {
    let standaloneCode = this.route.snapshot.paramMap.get('id');
   if(standaloneCode){
    this.standaloneCode=this.localStore1?.getDecryptedValue(standaloneCode)
    if(this.standaloneCode){
      this.viewurlData()
    }
   }
    this.surveyYear();
    //this.loadSurveyYear();
    this.getInstitutionTypeData();
    this.loadState();
    
    this.getInstitutionManagementRole();
    this.getSubrolls();
    this.surveyYearAll();
    // this.loadSurveyYearInactive();
    if(this.roleId==='22'){
      this.instiForm.controls['standaloneType'].setValue(6);
    }
    if(this.roleId=='26'|| this.roleId=='1'){//MoE
      this.pendingLevel=this.pendingLevel1;
    }else if(this.roleId=='6'){//SNO
      this.pendingLevel=this.pendingLevel2;
    }else if(this.roleId=='10'){//SCERT
      this.pendingLevel=this.pendingLevel3;
    }else if(this.roleId=='9'){//NURSING (SNB/C)
      this.pendingLevel=this.pendingLevel4;
    }else if(this.roleId=='8'){//DTE
      this.pendingLevel=this.pendingLevel5;
    }else if(this.roleId=='11'){//SPC
      this.pendingLevel=this.pendingLevel6;
    }else if(this.roleId=='22'){//SPC
      this.pendingLevel=this.pendingLevel7;
    }
    // console.log(this.roleId)
    if(this.localService.getData('viewDetailOfRequestId')){
      // console.log(JSON.parse(this.localService.getData('viewDetailOfRequestId')))
      this.viewDetailOfRequestId=JSON.parse(this.localService.getData('viewDetailOfRequestId'));
      if(this.viewDetailOfRequestId.status=='PENDING'){
        this.viewDetailOnly=false;
        this.findOnApprove12ByURL();
        this.tabIndex=3;
        this.selectedIndex=3;
      }
      if(this.viewDetailOfRequestId.status=='APPROVED'){
        this.viewDetailOnly=false;
        this.findOnView12ByURL();
        this.tabIndex=1;
        this.selectedIndex=1;
    
      }
      if(this.viewDetailOfRequestId.status=='REJECTED'){
        this.viewDetailOnly=false;
        this.tabIndex=7;
        this.selectedIndex=7;
        this.sendDetailRequest(this.viewDetailOfRequestId.requestId);
      }

    }
  
  }
  ngOnDestroy(): void {
    sessionStorage.removeItem('viewDetailOfRequestId');
    this.viewDetailOnly=true;
  }
  getSubrolls(){
    let boolean=true;

    this.institutionmanagement.getSubRoll(this.roleId).subscribe((res)=>{
      this.standalonTypeData = res[0].stateBodies;
      //console.log("this.institutionmanagement.getStandalonInstitution",  this.collegeTypeData)
      this.standalonTypefilter = this.standalonTypeData.slice();
      res[0].stateBodies.length==1?this.instiForm.controls['standaloneType'].setValue(res[0].stateBodies[0]?.id):'';
    })
  }
  getInstitutionManagementRole(){
    this.sharedservice.sidebarAccessibility.subscribe((res)=>{
      if(res){
        this.accessibilityData=res[0];
      }
    })
  }
  get f(): { [key: string]: AbstractControl } {
    return this.instiForm.controls
  }


  tabSelected(event: any) {
    //  console.log(event.index);
    this.selectedIndex = event.index
    if(this.localService.getData('viewDetailOfRequestId')){
      sessionStorage.removeItem('viewDetailOfRequestId');
      this.viewDetailOnly=true;
    }
    if(this.selectedIndex === 0){
      this.selectedIndexView=false;
      this.selectedIndexUpgradetoClg = false
      this.selectedIndexApprove=false;
      this.selectedIndexEdit=false;
      this.selectedIndexStatus=false;
      this.selectedIndexApproveList=false;
      this.selectedIndexDeAffiliate=false;
      this.selectedIndexAffiliate=false;
      this.selectedIndexUpgradetoUniversity = false;
      this.showStateBodayDropDown=false;
    }
    if (this.selectedIndex === 1) {//view
      this.instiForm.reset();
      this.viewValidations();
      this.universityList="";
      this.selectedIndexUpgradetoClg = false
      this.selectedIndexApprove=false;
      this.selectedIndexEdit=false;
      this.selectedIndexStatus=false;
      this.selectedIndexApproveList=false;
      this.selectedIndexDeAffiliate=false;
      this.selectedIndexAffiliate=false;
      this.selectedIndexUpgradetoUniversity = false;
      this.standalonTypefilter.length==1?this.instiForm.controls['standaloneType'].setValue(this.standalonTypefilter[0]?.id):'';
      this.showStateBodayDropDown=false;
    }
    if (this.selectedIndex === 2 && this.accessibilityData?.sedit) {//Edit
      this.instiForm.reset();
      this.editValidation();
      this.selectedIndexView=false;
      this.showReportIndex =false;
      this.selectedIndexUpgradetoUniversity = false;
      this.selectedIndexUpgradetoClg = false
      this.selectedIndexApprove=false;
     this.selectedIndexDeAffiliate=false;
     this.selectedIndexApproveList=false;
     this.selectedIndexDeAffiliate=false
     this.selectedIndexAffiliate=false;
     this.selectedIndexStatus=false;
     this.standalonTypefilter.length==1?this.instiForm.controls['standaloneType'].setValue(this.standalonTypefilter[0]?.id):'';
     this.showStateBodayDropDown=false;
    }
    if (this.selectedIndex === 3 ||  (this.selectedIndex === 2 && !this.accessibilityData?.sedit) ) {//approve/reject
      this.instiForm.reset({});
      // this.clearValidation();
      this.validationApproveReject();
      this.selectedIndexApproveList=false;
      this.selectedIndexView=false;
      this.showReportIndex =false;
      this.selectedIndexUpgradetoUniversity = false;
      this.selectedIndexUpgradetoClg = false
      this.selectedIndexEdit=false;
      this.selectedIndexDeAffiliate=false;
      this.selectedIndexDeAffiliate=false;
      this.selectedIndexAffiliate=false;
      this.selectedIndexStatus=false;
      if(this.roleId=='22'){
        this.selectedIndexApprove=false;
        this.selectedIndexApproveList=false;
      }
      this.standalonTypefilter.length==1?this.instiForm.controls['standaloneType'].setValue(this.standalonTypefilter[0]?.id):'';
   
    }
    if (this.selectedIndex === 4 ) {//Upgrade to College
      // if((this.roleId === '26' || this.roleId=== '1'))this.loadSurveyYearInactive();
      // this.surveyYearAll();
      // this.instiForm.reset({});
      // this.clearValidation();
      // this.selectedIndexDeAffiliate=false;
      // this.showReportIndex =false;
      // this.selectedIndexView=false;
      // this.selectedIndexUpgradetoClg = false;
      // this.selectedIndexDeAffiliate=false;
      // this.selectedIndexUpgradetoUniversity = false;
      // this.selectedIndexApprove=false;
      // this.selectedIndexEdit=false;
      // this.selectedIndexStatus=false;

      this.instiForm.reset({});
      this.validationUpgradeToCollege();
      this.selectedIndexApproveList=false;
      this.selectedIndexView=false;
      this.showReportIndex =false;
      this.selectedIndexUpgradetoUniversity = false;
      this.selectedIndexUpgradetoClg = false
      this.selectedIndexEdit=false;
      this.selectedIndexDeAffiliate=false;
      this.selectedIndexDeAffiliate=false;
      this.selectedIndexAffiliate=false;
      this.selectedIndexStatus=false;
      this.selectedIndexApprove=false;
      this.showStateBodayDropDown=false;
    }
    if (this.selectedIndex === 5) {//active/inactive
      this.instiForm.reset({});
      // this.clearValidation();
      this.validationActiveInactive();
      this.selectedIndexDeAffiliate=false;
      this.showReportIndex =false
      this.selectedIndexView=false;
      this.selectedIndexApproveList=false;
      //this.selectedIndexDeAffiliate=false
      this.selectedIndexUpgradetoUniversity = false;
      this.selectedIndexUpgradetoClg = false
      this.selectedIndexApprove=false;
      this.selectedIndexEdit=false;
      this.selectedIndexAffiliate=false;
      this.selectedIndexStatus=false;
      this.showStateBodayDropDown=false;
      // if((this.roleId === '26' || this.roleId=== '1' || this.roleId==='6'))this.loadSurveyYearInactive();

    }
    if (this.selectedIndex === 6) {//upgrade to University

      this.instiForm.reset({});
      // this.clearValidation();
      this.validationUpgradeToUniversity();
      this.selectedIndexDeAffiliate=false;
      this.showReportIndex =false;
      this.selectedIndexView=false;
      this.selectedIndexUpgradetoClg = false;
      this.selectedIndexDeAffiliate=false;
      this.selectedIndexUpgradetoUniversity = false;
      this.selectedIndexApprove=false;
      this.selectedIndexEdit=false;
      this.selectedIndexStatus=false;
      this.selectedIndexApproveList=false;
      this.showStateBodayDropDown=false;

    }
    if (this.selectedIndex === 8) {//view log
      this.surveyYearAll();
      this.instiForm.reset({});
      // this.clearValidation();
      this.validationViewLog();
      this.selectedIndexApproveList=false;
      this.selectedIndexView=false;
      this.showReportIndex =true;
      this.selectedIndexUpgradetoUniversity = false;
      this.selectedIndexUpgradetoClg = false
      this.selectedIndexEdit=false;
      this.selectedIndexDeAffiliate=false;
      this.selectedIndexDeAffiliate=false;
      this.selectedIndexAffiliate=false;
      this.selectedIndexStatus=false;
      this.selectedIndexApprove=false;
      this.showStateBodayDropDown=false;
    }
    if (this.selectedIndex === 7) {//rejected list
      //this.showReportIndex =true;
      this.instiForm.reset({});
      this.clearValidation();
      this.selectedIndexApproveList=false;
      this.selectedIndexView=false;
      this.showReportIndex =false;
      this.selectedIndexUpgradetoUniversity = false;
      this.selectedIndexUpgradetoClg = false
      this.selectedIndexEdit=false;
      this.selectedIndexDeAffiliate=false;
      this.selectedIndexDeAffiliate=false;
      this.selectedIndexAffiliate=false;
      this.selectedIndexStatus=false;
      this.showStateBodayDropDown=false;

    }
  }
 clearValidation(){
   this.instiForm.controls['surveyYearValue'].clearValidators();
   this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
   this.instiForm.controls['surveryApprove'].clearValidators();
   this.instiForm.controls['surveryApprove'].updateValueAndValidity();
   this.instiForm.controls['stateValue'].clearValidators();
   this.instiForm.controls['stateValue'].updateValueAndValidity();
   this.instiForm.controls['status'].clearValidators();
   this.instiForm.controls['status'].updateValueAndValidity();

   this.instiForm.controls['standaloneType'].setValidators(Validators.required);
   this.instiForm.controls['standaloneType'].updateValueAndValidity();
 }

 updateValidator(){
  //  this.instiForm.controls['surveyYearValue'].setValidators(Validators.required);
    //  this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
    if(this.selectedIndex!=3 && (this.roleId==='26' || this.roleId==='1') && this.instiForm.controls['aisheCodeValue'].value==null && this.instiForm.controls['searchText'].value==null){
        this.instiForm.controls['stateValue'].setValidators(Validators.required);
        this.instiForm.controls['stateValue'].updateValueAndValidity();
    }else{
         this.instiForm.controls['stateValue'].clearValidators();
         this.instiForm.controls['stateValue'].updateValueAndValidity();
    }

  //  this.instiForm.controls['status'].setValidators(Validators.required);
  //  this.instiForm.controls['status'].updateValueAndValidity();
 }
 editStandaloneValidation(){
  if(this.instiForm.controls['aisheCodeValue'].value){
    this.instiForm.controls['surveyYearValue'].setValidators(Validators.required);
    this.instiForm.controls['surveyYearValue'].updateValueAndValidity();

    this.instiForm.controls['standaloneType'].clearValidators();
    this.instiForm.controls['standaloneType'].updateValueAndValidity();
    this.instiForm.controls['stateValue'].clearValidators();
    this.instiForm.controls['stateValue'].updateValueAndValidity();
  }else{
    this.instiForm.controls['standaloneType'].setValidators(Validators.required);
    this.instiForm.controls['standaloneType'].updateValueAndValidity();
    this.instiForm.controls['stateValue'].setValidators(Validators.required);
    this.instiForm.controls['stateValue'].updateValueAndValidity();
  }
 }
 viewValidations(){
    this.instiForm.controls['surveyYearValue'].setValidators(Validators.required);
    this.instiForm.controls['surveyYearValue'].updateValueAndValidity();

    this.instiForm.controls['standaloneType'].setValidators(Validators.required);
    this.instiForm.controls['standaloneType'].updateValueAndValidity();

    this.instiForm.controls['stateValue'].setValidators(Validators.required);
    this.instiForm.controls['stateValue'].updateValueAndValidity();

    this.instiForm.controls['status'].clearValidators();
    this.instiForm.controls['status'].updateValueAndValidity();
    this.instiForm.controls['surveryApprove'].clearValidators();
    this.instiForm.controls['surveryApprove'].updateValueAndValidity();
    this.instiForm.controls['stateApprove'].clearValidators();
    this.instiForm.controls['stateApprove'].updateValueAndValidity();
    this.instiForm.controls['pendingLevel'].clearValidators();
    this.instiForm.controls['pendingLevel'].updateValueAndValidity();
 }
 editValidation(){
  this.instiForm.controls['surveyYearValue'].setValidators(Validators.required);
  this.instiForm.controls['surveyYearValue'].updateValueAndValidity();

  this.instiForm.controls['standaloneType'].clearValidators();
  this.instiForm.controls['standaloneType'].updateValueAndValidity();
  this.instiForm.controls['surveryApprove'].clearValidators();
  this.instiForm.controls['surveryApprove'].updateValueAndValidity();
  this.instiForm.controls['stateApprove'].clearValidators();
  this.instiForm.controls['stateApprove'].updateValueAndValidity();
  this.instiForm.controls['pendingLevel'].clearValidators();
  this.instiForm.controls['pendingLevel'].updateValueAndValidity();
 }
 validationApproveReject(){
  this.instiForm.controls['surveryApprove'].setValidators(Validators.required);
  this.instiForm.controls['surveryApprove'].updateValueAndValidity();
  if(this.roleId==='26' || this.roleId==='1'){
    this.instiForm.controls['pendingLevel'].setValidators(Validators.required);
    this.instiForm.controls['pendingLevel'].updateValueAndValidity();
    this.instiForm.controls['stateApprove'].clearValidators();
    this.instiForm.controls['stateApprove'].updateValueAndValidity();
  }else{
    this.instiForm.controls['stateApprove'].clearValidators();
    this.instiForm.controls['stateApprove'].updateValueAndValidity();
    this.instiForm.controls['pendingLevel'].clearValidators();
    this.instiForm.controls['pendingLevel'].updateValueAndValidity();
  }


  this.instiForm.controls['standaloneType'].clearValidators();
  this.instiForm.controls['standaloneType'].updateValueAndValidity();
  this.instiForm.controls['surveyYearValue'].clearValidators();
  this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
  this.instiForm.controls['stateValue'].clearValidators();
  this.instiForm.controls['stateValue'].updateValueAndValidity();

 }
 upgradeToCollegeThroughAisheCodeValidation(){
  if(this.instiForm.controls['aisheCodeValue'].value){
    this.instiForm.controls['stateValue'].clearValidators();
    this.instiForm.controls['stateValue'].updateValueAndValidity();
    this.instiForm.controls['districtValue'].clearValidators();
    this.instiForm.controls['districtValue'].updateValueAndValidity();
    this.instiForm.controls['standaloneType'].clearValidators();
    this.instiForm.controls['standaloneType'].updateValueAndValidity();
    this.instiForm.controls['pendingLevel'].clearValidators();
    this.instiForm.controls['pendingLevel'].updateValueAndValidity();
  }else{
    this.instiForm.controls['standaloneType'].setValidators(Validators.required);
    this.instiForm.controls['standaloneType'].updateValueAndValidity();
    this.instiForm.controls['stateValue'].setValidators(Validators.required);
    this.instiForm.controls['stateValue'].updateValueAndValidity();
  }
 }
 validationUpgradeToCollege(){
  this.instiForm.controls['standaloneType'].setValidators(Validators.required);
  this.instiForm.controls['standaloneType'].updateValueAndValidity();

  this.instiForm.controls['surveyYearValue'].clearValidators();
  this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
  this.instiForm.controls['surveryApprove'].clearValidators();
  this.instiForm.controls['surveryApprove'].updateValueAndValidity();
  this.instiForm.controls['stateApprove'].clearValidators();
  this.instiForm.controls['stateApprove'].updateValueAndValidity();
  this.instiForm.controls['status'].clearValidators();
  this.instiForm.controls['status'].updateValueAndValidity();
  this.instiForm.controls['pendingLevel'].clearValidators();
  this.instiForm.controls['pendingLevel'].updateValueAndValidity();
 }
 validationActiveInactive(){
  this.instiForm.controls['status'].setValidators(Validators.required);
  this.instiForm.controls['status'].updateValueAndValidity();
  this.instiForm.controls['surveyYearValue'].setValidators(Validators.required);
  this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
  
  this.instiForm.controls['standaloneType'].clearValidators();
  this.instiForm.controls['standaloneType'].updateValueAndValidity();
  this.instiForm.controls['surveryApprove'].clearValidators();
  this.instiForm.controls['surveryApprove'].updateValueAndValidity();
  this.instiForm.controls['stateApprove'].clearValidators();
  this.instiForm.controls['stateApprove'].updateValueAndValidity();
  this.instiForm.controls['pendingLevel'].clearValidators();
  this.instiForm.controls['pendingLevel'].updateValueAndValidity();
 }
 upgradeToUniversityThroughAisheCodeValidation(){
  if(this.instiForm.controls['aisheCodeValue'].value){
    this.instiForm.controls['stateValue'].clearValidators();
    this.instiForm.controls['stateValue'].updateValueAndValidity();
    this.instiForm.controls['districtValue'].clearValidators();
    this.instiForm.controls['districtValue'].updateValueAndValidity();
    this.instiForm.controls['standaloneType'].clearValidators();
    this.instiForm.controls['standaloneType'].updateValueAndValidity();
    this.instiForm.controls['pendingLevel'].clearValidators();
    this.instiForm.controls['pendingLevel'].updateValueAndValidity();
  }else{
    this.instiForm.controls['standaloneType'].setValidators(Validators.required);
    this.instiForm.controls['standaloneType'].updateValueAndValidity();
    this.instiForm.controls['stateValue'].setValidators(Validators.required);
    this.instiForm.controls['stateValue'].updateValueAndValidity();
  }
 }
 validationUpgradeToUniversity(){
  this.instiForm.controls['standaloneType'].setValidators(Validators.required);
  this.instiForm.controls['standaloneType'].updateValueAndValidity();

  this.instiForm.controls['surveyYearValue'].clearValidators();
  this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
  this.instiForm.controls['surveryApprove'].clearValidators();
  this.instiForm.controls['surveryApprove'].updateValueAndValidity();
  this.instiForm.controls['stateApprove'].clearValidators();
  this.instiForm.controls['stateApprove'].updateValueAndValidity();
  this.instiForm.controls['status'].clearValidators();
  this.instiForm.controls['status'].updateValueAndValidity();
  this.instiForm.controls['pendingLevel'].clearValidators();
  this.instiForm.controls['pendingLevel'].updateValueAndValidity();
 }
 validationViewLog(){
  this.instiForm.controls['surveyYearValue'].setValidators(Validators.required);
  this.instiForm.controls['surveyYearValue'].updateValueAndValidity();

  this.instiForm.controls['surveryApprove'].clearValidators();
  this.instiForm.controls['surveryApprove'].updateValueAndValidity();
  this.instiForm.controls['stateApprove'].clearValidators();
  this.instiForm.controls['stateApprove'].updateValueAndValidity();
  this.instiForm.controls['status'].clearValidators();
  this.instiForm.controls['status'].updateValueAndValidity();
  this.instiForm.controls['standaloneType'].clearValidators();
  this.instiForm.controls['standaloneType'].updateValueAndValidity();
  this.instiForm.controls['stateValue'].clearValidators();
  this.instiForm.controls['stateValue'].updateValueAndValidity();
  this.instiForm.controls['pendingLevel'].clearValidators();
  this.instiForm.controls['pendingLevel'].updateValueAndValidity();

 }
  surveyYear() {
    this.institutionmanagement.getSurveyYearListPrivate().subscribe((res) => {
      this.surveyYearList = res;
      //console.log("this.surveyYearList:",this.surveyYearList)
    });
  }
  surveyYearAll() {
    this.institutionmanagement.getYearList().subscribe((res) => {
      this.surveyYearAllList = res;
      //console.log("this.surveyYearList:",this.surveyYearList)
    });
  }

getInstitutionTypeData() {
  this.institutionmanagement.getStandalonInstitutionTypeData().subscribe((res) => {
    // console.log(res)
    // this.standalonTypeData = res;
    // //console.log("this.institutionmanagement.getStandalonInstitution",  this.collegeTypeData)
    // this.standalonTypefilter = this.standalonTypeData.slice();
  });

}

loadSurveyYearInactive(){
  this.surveyyearservice.getdatasurveyyear().subscribe((res)=>{
    // let obj = {surveyYear:'2022',surveyYearValue:'2022-23'}
    // res.unshift(obj)
    this.surveyYearOption=res;
this.filterSurveyYearOption=  this.surveyYearOption.slice()
  })
}
loadSurveyYear(){
  this.institutionmanagement.getYearList().subscribe({
    next:(res)=>{
      this.surveyYearValue=res;
    //   if(this.selectedSurveyYear){
    //   this.surveyYearValue.splice(0,0,this.selectedSurveyYear);
    // }

    }
  })
}
loadState() {
  this.institutionmanagement.getStatePrivate().subscribe((res) => {
  this.stateList = res;
  this.filterStateList=this.stateList.slice();
  //console.log(this.stateList)
    });
}
viewurlData(){
  this.instiForm.get('searchText')?.setValue(this.standaloneCode)
  this.findOnView12()
 }
findOnView12(){
  this.updateValidator();
  this.selectedIndexView = true;
  this.view?.ViewData();
}
findOnView12ByURL(){
  // this.updateValidator()
  this.instiForm.get('searchText')?.setValue(this.viewDetailOfRequestId.aisheCode);
  this.instiForm.controls['surveyYearValue'].setValue(' ');
  this.instiForm.controls['stateValue'].setValue(' ');
  this.instiForm.controls['standaloneType'].setValue(' ')
  this.selectedIndexView = true;
  this.view?.ViewData();
}


  findOnApprove12(){
    this.updateValidator()
    this.selectedIndexApprove=true;
    this.approve?.findDataList();

  }
  findOnApprove12ByURL(){
    // console.log(this.instiForm)
    this.instiForm.controls['RequestId'].setValue(this.viewDetailOfRequestId.requestId);
    this.instiForm.controls['pendingLevel'].setValue(' ');
    this.instiForm.controls['surveryApprove'].setValue(' ');
    this.instiForm.controls['standaloneType'].setValue(' ');
    this.instiForm.controls['stateValue'].setValue(' ');
    this.instiForm.controls['surveyYearValue'].setValue(' ');
    this.selectedIndexApprove=true;
    this.approve?.findDataList();
  }

  findOnEdit(){
    this.updateValidator()
    this.edit?.findData();
    this.selectedIndexEdit=true
  }

  findOnApprovedList(){
    this.updateValidator()
    this.selectedIndexApproveList=true;
    this.approveList1?.approvalUniversity()
  }
  findOnStatus(){
    this.updateValidator()
    this.status1?.findData();
    this.selectedIndexStatus=true;
  }

  findUpgradeList(){
    this.updateValidator()
    this.selectedIndexUpgradetoClg=true;
    this.upgradetoClg?.upgradeList()
  }
  findUpgradeUniversity(){
    this.updateValidator()
    this.selectedIndexUpgradetoUniversity=true;
    this.upgradetoUniversity?.upgradeUniversityList()
  }

resteFilter(value:any,standalonTypefilter:any){
  // console.log(document.getElementById('input'))
  this.standalonTypefilter=[];
  this.standalonTypefilter = this.standalonTypeData.slice();
}
findReport(reportType:any){
  this.updateValidator()
  this.showReport?.findData(reportType);
  this.showReportIndex = true;
}
alphaNumberOnly (e:any) {  // Accept only alpha numerics, not special characters 
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
isRequstId(){
  if(this.instiForm.controls['RequestId'].value){
    this.instiForm.controls['surveryApprove'].clearValidators(); 
    this.instiForm.controls['surveryApprove'].updateValueAndValidity();
    this.instiForm.controls['pendingLevel'].clearValidators(); 
    this.instiForm.controls['pendingLevel'].updateValueAndValidity();

    if(this.roleId === '26' || this.roleId==='1'){
    this.instiForm.controls['stateApprove'].clearValidators(); 
    this.instiForm.controls['stateApprove'].updateValueAndValidity();

    }
    this.instiForm.controls['surveryApprove'].setValue('');
    this.instiForm.controls['stateApprove'].setValue('');
    this.instiForm.controls['pendingLevel'].setValue('');
    
  }else{
    this.instiForm.controls['surveryApprove'].setValidators(Validators.required);
    this.instiForm.controls['surveryApprove'].updateValueAndValidity();
    this.instiForm.controls['pendingLevel'].setValidators(Validators.required);
    this.instiForm.controls['pendingLevel'].updateValueAndValidity();
  }
}
loadDistrict(stateId:any){
  if(this.roleId=='26' || this.roleId=='1' || this.roleId=='7' || this.roleId=='6'){
    this.institutionmanagement.getDistrict(stateId.value).subscribe((res) => {
    
    this.districtList = res;
    this.filterDistrictList=this.districtList.slice();
      });
    }
}
searchTextValue(){
  // console.log(this.instiForm.controls['searchText'].value)
  if(this.instiForm.controls['searchText'].value){
    this.instiForm.controls['surveyYearValue'].clearValidators();
    this.instiForm.controls['surveyYearValue'].updateValueAndValidity();

    this.instiForm.controls['stateValue'].clearValidators();
    this.instiForm.controls['stateValue'].updateValueAndValidity();
    this.instiForm.controls['standaloneType'].clearValidators();
    this.instiForm.controls['standaloneType'].updateValueAndValidity();
  }else{
    this.viewValidations();
  }
}
getRefStateBodyType(){
  if(this.instiForm.get('standaloneType')?.value==6){
    this.institutionmanagement.getRegStateBodyType().subscribe((res)=>{
      if(res){
        this.stateBodyData=res;
        this.showStateBodayDropDown=true;
        // this.instiForm.controls['stateBodyTypeId'].setValidators([Validators.required])
        // this.instiForm.controls['stateBodyTypeId'].updateValueAndValidity();
      }
    })
  }else{
    this.showStateBodayDropDown=false;
    // this.instiForm.controls['stateBodyTypeId'].setValue(null);
    // this.instiForm.controls['stateBodyTypeId'].setValidators(null);
    // this.instiForm.controls['stateBodyTypeId'].updateValueAndValidity();
  }
}
changeFun(event:any){
  // console.log(event)
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
    type:'STANDALONE'
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
