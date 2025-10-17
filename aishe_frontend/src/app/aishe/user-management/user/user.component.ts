
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/service/format-datepicker';
import { SharedService } from 'src/app/shared/shared.service';
import { UserManagementComponent } from '../user-management.component';
import { UserManagementService } from '../user-management.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { UpdateUserDialogComponent } from 'src/app/dialog/update-user-dialog/update-user-dialog.component';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ],
})
export class UserComponent implements OnInit {
  searchControl: FormControl;
  userStausList: any[] = [];
  aisheCode: any;
  stateCode: any;
  stateLevelBody: any;
  userDataDeo: any[] = [];
  userDataSno: any[] = [];
  institutionType: any;
  selectedIndex: any = 0;
  tempuserDataDeo: any[] = [];
  tempuserDataSno: any[] = [];
  displayStyle: any = "none";
  disablelist: any = false;
  statuslist: any[] = [];
  statuslistOrg: any[] = [];
  surveyYear: any
  sharedservice: any;
  roleId: any;
  userDetails: any;
  disableLevelZA: boolean = false
  authorized: boolean = false;
  authorizedLevel2: boolean = false
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  userData: any[] = [];
  tempUserData: any[] = [];
  officers: any;
  userStatus: any = 1;
  officersList: any[] = [];
  stateListArray: any[] = [];
  stateList: any[] = []
  userId: any;
  levelId: number = 1;
  surveyYearList: any[] = []
  subRoleLevel1: any[] = [];
  subRoleLevel2: any[] = [];
  subRoleLevel3: any[] = [];
  userRole: any[] = []
  deoLevel: any[] = []
  currentSurveyYear: any;
  formUploadStatus: any = 'ALL';
  instituteType: any = 'ALL';
  loginMode: any
  sno: boolean = false;
  deoApplicable: boolean = false
  levelOne: boolean = false;
  levelTwo: boolean = false;
  levelThree: boolean = false;
  levelFour: boolean = false;
  selfDetailsLevel3: boolean = false
  selfDetailsLevel2: boolean = false
  selfDetailsLevel1: boolean = true
  stateMandatory: any
  maxDate: Date;
  minDate: any;
  fromDate: any = null;
  toDate: any = null;
  hideAction: boolean = false
  universityList: any[] = [];
  universityListArray: any[] = []
  universityId: any = null;
  isState: boolean = false
  isUniversity: boolean = false
  payload: any
  showUniversity: boolean = false
  showAisheCode: boolean = false;
  searchText: any = null
  isUserStatus: boolean | undefined
  userPrevillage: Array<any> = [];
  evntName: any;
  showNote: any = false;
  showEdit = true;
  constructor(private dialog: MatDialog, public authService: AuthService, public sharedService: SharedService, public fb: FormBuilder,
    private userService: UserManagementService, public localService: LocalserviceService) {
    this.stateCode = this.localService.getData('stateCode');
    this.aisheCode = this.localService.getData('aisheCode');
    this.roleId = this.localService.getData('roleId');
    console.log('this.roleId', this.roleId)
    this.userId = this.localService.getData('userId');
    this.loginMode = this.localService.getData('loginMode');
    this.maxDate = new Date();
    this.isStateVisible()
    this.searchControl = new FormControl();
  }



  ngOnInit(): void {

    this.getUserStatus();
    this.getUserRolePermission();
    this.getState();
    this.getUserRolePrevillage()
    if (this.roleId === this.sharedService.role['University'] || this.roleId === this.sharedService.role['College']) {
      this.stateLevelBody = this.localService.getData('stateLevelBody');
    }
  }
  getUserRolePrevillage() {
    this.authService.getPrevillagePrivate().subscribe(res => {
      this.userPrevillage = res;
    }, err => {

    })
  }
  isStateVisible() {
    if (this.roleId === this.sharedService.role['Ministry of Education']
      || this.roleId === this.sharedService.role['University Grants Commission']
      || this.roleId === this.sharedService.role['All India Council for Technical Education']
      || this.roleId === this.sharedService.role['SysAdmin']
      || this.roleId === this.sharedService.role['Indian Nursing Council']
      || this.roleId === this.sharedService.role['National Council For Teacher Education']
      || this.roleId === this.sharedService.role['National Paramedical Council']
      || this.roleId === this.sharedService.role['Apex User']
      || this.roleId === this.sharedService.role['National Council for Hotel Management and Catering Technology']
      || this.roleId === this.sharedService.role['University']) {
      this.isState = true
    } else {
      this.isState = false
    }
  }
  isUniversityVisible() {
    if (this.roleId === this.sharedService.role['Ministry of Education']
      || this.roleId === this.sharedService.role['University Grants Commission']
      || this.roleId === this.sharedService.role['All India Council for Technical Education']
      || this.roleId === this.sharedService.role['SysAdmin']
      || this.roleId === this.sharedService.role['Apex User']
      || this.roleId === this.sharedService.role['State Nodal Officer']
    ) {
      if (this.roleId === this.sharedService.role['State Nodal Officer']) {
        this.getUniversityList(this.sharedService.role['University'], this.stateCode)
      }
      this.isUniversity = true
    } else {
      this.isUniversity = false
    }
  }
  tabSelected(event: any) {
    this.StartLimit = 0;
    this.EndLimit = 25;
    this.pageData = 0;
    this.pageSize = 25;
    this.page = 1;
    this.clear();
    this.statuslist = [];
    this.statuslist = [...this.statuslistOrg];

    if (this.officers.toString() === this.sharedService.role['University']) {
      this.showUniversity = true;
    } else {
      this.showUniversity = false
    }
    if (this.officers.toString() === this.sharedService.role['Directorate of Technical Education'] || this.officers.toString() === this.sharedService.role['State Nursing Body/Council']
      || this.officers.toString() === this.sharedService.role['State Council of Educational Research and Training'] || this.officers.toString() === this.sharedService.role['State Paramedical Council']) {
      this.showAisheCode = false;
    } else {
      this.showAisheCode = true
    }
    this.selectedIndex = event.index
    if (this.selectedIndex === 0) {//level1
      let checkBoolValue = this.roleId == this.sharedService.role['Ministry of Education']
        || this.roleId == this.sharedService.role['SysAdmin'];
      this.showEdit = checkBoolValue ? true : false;//only MoE can Edit
      if (this.roleId === this.sharedService.role['SysAdmin'] || this.roleId === this.sharedService.role['Ministry of Education']) {
        this.officers = this.subRoleLevel1['0'].id
        this.hideAction = false
        this.selfDetailsLevel1 = false;
        this.userStatus = 2
        this.selfUserDetail('');
      } if (this.roleId === this.sharedService.role['State Nodal Officer']) {
        this.selfDetailsLevel1 = false;
        this.hideAction = true
        this.selfUserDetail('');
        this.userStatus = 2
      } if (this.roleId === this.sharedService.role['Apex User']) {
        this.selfDetailsLevel1 = false;
        if (!this.stateCode) {
          this.stateCode = null
        }
        this.hideAction = true
        this.userStatus = 2
        this.officers = this.subRoleLevel1['0'].id
        this.selfUserDetail('');
      }

    }
    if (this.selectedIndex === 1) {//level2
      let checkBoolValue = this.roleId == this.sharedService.role['Ministry of Education']
        || this.roleId == this.sharedService.role['SysAdmin']
        || this.roleId == this.sharedService.role['State Nodal Officer'];
      this.showEdit = checkBoolValue ? true : false;//MoE,SNO
      this.isUniversityVisible();
      this.isStateVisible()
      if (this.roleId === this.sharedService.role['SysAdmin'] || this.roleId === this.sharedService.role['Ministry of Education']) {
        this.stateCode = null;
        this.hideAction = false
      } else {
        this.stateCode = this.localService.getData('stateCode')
      }
      if (this.subRoleLevel2 && this.subRoleLevel2.length) {
        this.officers = this.subRoleLevel2['0'].id;
      }


      //   if (this.roleId === this.sharedService.role['Directorate of Technical Education'] || this.roleId === this.sharedService.role['State Nursing Body/Council']
      //   || this.roleId === this.sharedService.role['State Council of Educational Research and Training'] || this.roleId === this.sharedService.role['State Paramedical Council']) {
      //   this.showAisheCode = false;
      // } else {
      //   this.showAisheCode = true
      // }
      /**Action Perform */
      // if (this.roleId === this.sharedService.role['Ministry of Education']
      //   || this.roleId === this.sharedService.role['University Grants Commission']
      //   || this.roleId === this.sharedService.role['All India Council for Technical Education']
      //   || this.roleId === this.sharedService.role['SysAdmin']
      //   || this.roleId === this.sharedService.role['Indian Nursing Council']
      //   || this.roleId === this.sharedService.role['State Nodal Officer']
      //   || this.roleId === this.sharedService.role['National Council For Teacher Education']
      //   || this.roleId === this.sharedService.role['National Paramedical Council']
      //   || this.roleId === this.sharedService.role['National Council for Hotel Management and Catering Technology']
      // ) {
      //   this.hideAction = false
      // }
      if (this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['SysAdmin']

      ) {
        this.hideAction = false
      } else {
        this.hideAction = true
        this.isState = false
      }
      if (this.roleId === this.sharedService.role['Ministry of Education']
        || this.roleId === this.sharedService.role['University Grants Commission']
        || this.roleId === this.sharedService.role['All India Council for Technical Education']
        || this.roleId === this.sharedService.role['SysAdmin']
        || this.roleId === this.sharedService.role['Apex User']
        || this.roleId === this.sharedService.role['State Nodal Officer']
      ) {

      } else {
        this.selfUserDetail('');
      }
    }
    if (this.selectedIndex === 2) {//level3
      let checkBoolValue = this.roleId == this.sharedService.role['Ministry of Education']
        || this.roleId == this.sharedService.role['SysAdmin']
        || this.roleId == this.sharedService.role['State Nodal Officer'];
      this.showEdit = checkBoolValue ? true : false;
      if (this.subRoleLevel3 && this.subRoleLevel3.length) {
        this.officers = this.subRoleLevel3['0'].id;
      }
      this.isUniversityVisible();
      this.isStateVisible();
      if (this.roleId === this.sharedService.role['SysAdmin'] || this.roleId === this.sharedService.role['Ministry of Education']) {
        this.stateCode = null;
      } else {
        this.stateCode = this.localService.getData('stateCode')
      }
      if (this.roleId === this.sharedService.role['Ministry of Education']
        || this.roleId === this.sharedService.role['University Grants Commission']
        || this.roleId === this.sharedService.role['All India Council for Technical Education']
        || this.roleId === this.sharedService.role['SysAdmin']
        || this.roleId === this.sharedService.role['Apex User']
        || this.roleId === this.sharedService.role['State Nodal Officer']
      ) {
      } else {
        this.selfUserDetail('');
      }
      /**Action Perform */
      if (this.roleId === this.sharedService.role['Ministry of Education']
        || this.roleId === this.sharedService.role['University Grants Commission']
        || this.roleId === this.sharedService.role['All India Council for Technical Education']
        || this.roleId === this.sharedService.role['SysAdmin']
        || this.roleId === this.sharedService.role['Indian Nursing Council']
        || this.roleId === this.sharedService.role['State Nodal Officer']
        || this.roleId === this.sharedService.role['National Council For Teacher Education']
        || this.roleId === this.sharedService.role['National Paramedical Council']
        || this.roleId === this.sharedService.role['National Council for Hotel Management and Catering Technology']
        || this.roleId === this.sharedService.role['National Paramedical Council']) {
        this.hideAction = false
      }
    }
    if (this.selectedIndex === 3) {//level4
      this.showEdit = false;
      if (this.deoApplicable) {
        if (this.deoLevel && this.deoLevel.length) {
          this.officers = this.deoLevel['0'].id;
        }
        this.hideAction = false
        this.getuserListLevelDeo('')
      }
    }
    if (this.selectedIndex === 4) {//sno
      let checkBoolValue = this.roleId == this.sharedService.role['Ministry of Education']
        || this.roleId == this.sharedService.role['SysAdmin'];

      this.showEdit = checkBoolValue ? true : false;;
      if (this.statuslist[0].status === 'New Registration') {
        this.statuslist.shift();
      }
      this.isSNO()
      this.stateCode = this.localService.getData('stateCode');
      if (this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['SysAdmin']) {
        this.hideAction = false
        this.stateCode = 0
        this.isState = true;
        this.isUserStatus = true;
        this.userStatus = 2//show approved list 
      } else {
        this.isUserStatus = false
        this.isState = false;
        this.userStatus = 2
        this.hideAction = true
      }
      if (this.roleId === this.sharedService.role['Apex User']) {
        this.isState = false;
        this.isUserStatus = false;
        if (!this.stateCode) {
          this.stateCode = null
        }
        // this.stateCode = 0
      }
      this.getuserListSno('find')
      // }
    }
    if (this.roleId === this.sharedService.role['Apex User']) {
      if (!this.stateCode) {
        this.stateCode = null
      }
    }
  }
  isSNO() {
    if (this.roleId === this.sharedService.role['Indian Nursing Council']
      || this.roleId === this.sharedService.role['University Grants Commission']
      || this.roleId === this.sharedService.role['All India Council for Technical Education']
      || this.roleId === this.sharedService.role['National Council For Teacher Education']
      || this.roleId === this.sharedService.role['National Paramedical Council']
      || this.roleId === this.sharedService.role['National Council for Hotel Management and Catering Technology']) {
      this.sno = false
    } else {
      this.sno = true
    }
  }
  selfUserDetail(evnt: any) {
    this.evntName = evnt
    this.StartLimit = 0;
    this.EndLimit = 25;
    this.pageData = 0;
    this.pageSize = 25;
    this.page = 1;
    if (this.officers.toString() === this.sharedService.role['University']) {
      this.showUniversity = true;
    } else {
      this.showUniversity = false
    }
    this.payload = ''
    var userStatus = 'ALL'
    if (this.userStatus === 1) {
      userStatus = 'New_REGISTRATION'
    } if (this.userStatus === 2) {
      userStatus = 'USER_APPROVED'

    } if (this.userStatus === 3) {
      userStatus = 'INACTIVE'
    } if (this.userStatus === 4) {
      userStatus = 'USER_DISAPPROVED'
    }
    var fromDate = null
    var toDate = null
    if (this.fromDate !== null) {
      let day: string = this.fromDate.getDate().toString();
      day = +day < 10 ? '0' + day : day;
      let month: string = (this.fromDate.getMonth() + 1).toString();
      month = +month < 10 ? '0' + month : month;
      let year = this.fromDate.getFullYear();
      fromDate = `${day}/${month}/${year}`;
    }

    if (this.toDate !== null) {
      let day: string = this.toDate.getDate().toString();
      day = +day < 10 ? '0' + day : day;
      let month: string = (this.toDate.getMonth() + 1).toString();
      month = +month < 10 ? '0' + month : month;
      let year = this.toDate.getFullYear();
      toDate = `${day}/${month}/${year}`;
    }
    if (this.selectedIndex === 0) {
      this.levelId = 1;
      if (this.roleId === this.sharedService.role['SysAdmin'] || this.roleId === this.sharedService.role['Ministry of Education']) {

        this.payload = {
          roleId: this.officers,
          stateCode: null,
          userStatus: userStatus,
        }
      } if (this.roleId === this.sharedService.role['State Nodal Officer']) {
        this.payload = {
          roleId: this.sharedService.role['Ministry of Education'],
          stateCode: null,
          userStatus: 'USER_APPROVED',
        }

      }
      if (this.roleId === this.sharedService.role['Apex User']) {
        this.payload = {
          roleId: this.officers,
          stateCode: this.stateCode ? this.stateCode : null,
          userStatus: userStatus,
        }
      }

      this.getuserListLevel1();


    }
    if (this.selectedIndex === 1) {
      if (this.loginMode === 'C') {
        this.instituteType = 'COLLEGE'
      } else if (this.loginMode === 'U') {
        this.instituteType = 'UNIVERSITY'
      } else if (this.loginMode === 'S') {
        this.instituteType = 'STANDALONE'
      } else {
        this.instituteType = 'ALL'
      }
      var stateCode = null;
      if (this.stateCode === null) {
        stateCode = null
      } else {
        stateCode = this.stateCode
      }
      if (this.roleId === this.sharedService.role['University']
        || this.roleId === this.sharedService.role['Directorate of Technical Education']
        || this.roleId === this.sharedService.role['State Nursing Body/Council']
        || this.roleId === this.sharedService.role['State Council of Educational Research and Training']
        || this.roleId === this.sharedService.role['State Paramedical Council']) {
        this.selfDetailsLevel2 = true;
        this.hideAction = true;
        this.isState = false
        this.payload = {
          roleId: this.roleId,
          userStatus: 'USER_APPROVED',
          formUploadStatus: 'ALL',
          stateCode: stateCode,
          aisheCode: this.aisheCode ? this.aisheCode : null,
          stateLevelBody: this.universityId,
          instituteType: this.instituteType ? this.instituteType : null,
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
          isApproved: true
        }
        this.getuserListLevel2()
      } if (this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['SysAdmin']) {

        this.payload = {
          roleId: this.officers,
          userStatus: userStatus,
          formUploadStatus: 'ALL',
          stateCode: stateCode,
          aisheCode: this.aisheCode ? this.aisheCode : null,
          stateLevelBody: this.universityId,
          instituteType: this.instituteType ? this.instituteType : null,
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,

        }
        this.getuserListLevel2()
      } if (this.roleId === this.sharedService.role['College']) {
        this.showAisheCode = true;
        this.selfDetailsLevel2 = true;
        this.hideAction = true;
        this.isState = false
        this.payload = {
          roleId: this.sharedService.role['University'],
          userStatus: 'USER_APPROVED',
          formUploadStatus: 'ALL',
          stateCode: stateCode,
          aisheCode: null,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: 'ALL',
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
        this.getuserListLevel2()
      } if (this.roleId === this.sharedService.role['Polytechnic'] || this.roleId === this.sharedService.role['PGDM']) {
        this.showAisheCode = false;
        this.selfDetailsLevel2 = true;
        this.hideAction = true;
        this.isState = false
        this.payload = {
          roleId: this.sharedService.role['Directorate of Technical Education'],
          userStatus: 'USER_APPROVED',
          formUploadStatus: 'ALL',
          stateCode: stateCode,
          aisheCode: null,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: 'ALL',
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
        this.getuserListLevel2()
      }
      if (this.roleId === this.sharedService.role['Nursing (Diploma) Institute']) {
        this.showAisheCode = false;
        this.selfDetailsLevel2 = true;
        this.hideAction = true;
        this.isState = false
        this.payload = {
          roleId: this.sharedService.role['State Nursing Body/Council'],
          userStatus: 'USER_APPROVED',
          formUploadStatus: 'ALL',
          stateCode: stateCode,
          aisheCode: null,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: 'ALL',
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
        this.getuserListLevel2()
      } if (this.roleId === this.sharedService.role['Teacher Training (Diploma) Institute']) {
        this.showAisheCode = false;
        this.selfDetailsLevel2 = true;
        this.hideAction = true;
        this.isState = false
        this.payload = {
          roleId: this.sharedService.role['State Council of Educational Research and Training'],
          userStatus: 'USER_APPROVED',
          formUploadStatus: 'ALL',
          stateCode: stateCode,
          aisheCode: null,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: 'ALL',
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
        this.getuserListLevel2()
      }
      if (this.roleId === this.sharedService.role['Standalone Institution Under Ministry']) {
        this.selfDetailsLevel2 = true;
        this.hideAction = true;
        this.isState = false
        this.payload = {
          roleId: this.sharedService.role['State Nursing Body/Council'],
          userStatus: 'USER_APPROVED',
          formUploadStatus: 'ALL',
          stateCode: stateCode,
          aisheCode: null,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: 'ALL',
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
      }
      if (this.roleId === this.sharedService.role['Paramedical Institute']) {
        this.showAisheCode = false;
        this.selfDetailsLevel2 = true;
        this.hideAction = true;
        this.isState = false
        this.payload = {
          roleId: this.sharedService.role['State Paramedical Council'],
          userStatus: 'USER_APPROVED',
          formUploadStatus: 'ALL',
          stateCode: stateCode,
          aisheCode: null,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: 'ALL',
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
        this.getuserListLevel2()
      } if (this.roleId === this.sharedService.role['Hotel Management and Catering Institute']) {
        this.showAisheCode = false;
        this.selfDetailsLevel2 = true;
        this.hideAction = true;
        this.isState = false
        this.payload = {
          roleId: this.sharedService.role['National Council for Hotel Management and Catering Technology'],
          userStatus: 'USER_APPROVED',
          formUploadStatus: 'ALL',
          stateCode: null,
          aisheCode: null,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: 'ALL',
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
        this.getuserListLevel2()
      }

      if (this.roleId === this.sharedService.role['State Nodal Officer']) {
        this.payload = {
          roleId: this.officers,
          userStatus: userStatus,
          formUploadStatus: 'ALL',
          stateCode: stateCode,
          aisheCode: null,
          stateLevelBody: this.universityId,
          instituteType: 'ALL',
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
        this.getuserListLevel2()
      }
      if (this.roleId === this.sharedService.role['Apex User']) {
        this.payload = {
          roleId: this.officers,
          userStatus: userStatus,
          formUploadStatus: 'ALL',
          stateCode: this.stateCode ? this.stateCode : null,
          aisheCode: null,
          stateLevelBody: this.universityId,
          instituteType: 'ALL',
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
        this.getuserListLevel2()
      }
      if (this.roleId === this.sharedService.role['University Grants Commission']
        || this.roleId === this.sharedService.role['All India Council for Technical Education']
        || this.roleId === this.sharedService.role['Indian Nursing Council']
        || this.roleId === this.sharedService.role['National Council For Teacher Education']
        || this.roleId === this.sharedService.role['National Paramedical Council']
        || this.roleId === this.sharedService.role['National Council for Hotel Management and Catering Technology']) {
        this.selfDetailsLevel2 = true
        this.payload = {
          roleId: this.officers,
          userStatus: 'USER_APPROVED',
          formUploadStatus: 'ALL',
          stateCode: null,
          aisheCode: null,
          stateLevelBody: this.universityId,
          instituteType: 'ALL',
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
        this.getuserListLevel2()
      }

    }
    if (this.selectedIndex === 2) {
      let aisheCode = null;
      if (this.roleId === this.sharedService.role['University']) {
        this.instituteType = 'COLLEGE'
        this.universityId = this.localService.getData('id')
      } else if (this.roleId === this.sharedService.role['College']) {
        this.instituteType = 'COLLEGE'
        aisheCode = this.aisheCode
      } else if (this.roleId === this.sharedService.role['Polytechnic']
        || this.roleId === this.sharedService.role['Nursing (Diploma) Institute']
        || this.roleId === this.sharedService.role['Teacher Training (Diploma) Institute']
        || this.roleId === this.sharedService.role['Standalone Institution Under Ministry']
        || this.roleId === this.sharedService.role['PGDM']
        || this.roleId === this.sharedService.role['Paramedical Institute']
        || this.roleId === this.sharedService.role['Hotel Management and Catering Institute']
        || this.roleId === this.sharedService.role['Pharmacy Institutions']
        || this.roleId === this.sharedService.role['Instititions under Rehabilitation Council of India']) {
        this.instituteType = 'STANDALONE'
        aisheCode = this.aisheCode
      } else {
        this.instituteType = 'ALL'
      }
      var stateCode = null;
      if (this.stateCode === null) {
        stateCode = null
      } else {
        stateCode = this.stateCode
      }
      var fromDate = null
      var toDate = null
      if (this.fromDate !== null) {
        let day: string = this.fromDate.getDate().toString();
        day = +day < 10 ? '0' + day : day;
        let month: string = (this.fromDate.getMonth() + 1).toString();
        month = +month < 10 ? '0' + month : month;
        let year = this.fromDate.getFullYear();
        fromDate = `${day}/${month}/${year}`;
      }

      if (this.toDate !== null) {
        let day: string = this.toDate.getDate().toString();
        day = +day < 10 ? '0' + day : day;
        let month: string = (this.toDate.getMonth() + 1).toString();
        month = +month < 10 ? '0' + month : month;
        let year = this.toDate.getFullYear();
        toDate = `${day}/${month}/${year}`;
      }

      if (this.roleId === this.sharedService.role['College']
        || this.roleId === this.sharedService.role['Polytechnic']
        || this.roleId === this.sharedService.role['Nursing (Diploma) Institute']
        || this.roleId === this.sharedService.role['Teacher Training (Diploma) Institute']
        || this.roleId === this.sharedService.role['Standalone Institution Under Ministry']
        || this.roleId === this.sharedService.role['PGDM']
        || this.roleId === this.sharedService.role['Paramedical Institute']
        || this.roleId === this.sharedService.role['Hotel Management and Catering Institute']
        || this.roleId === this.sharedService.role['Pharmacy Institutions']
        || this.roleId === this.sharedService.role['Instititions under Rehabilitation Council of India']) {
        this.selfDetailsLevel3 = true;
        this.hideAction = true;
        this.payload = {
          roleId: this.roleId,
          userStatus: 'USER_APPROVED',
          formUploadStatus: 'ALL',
          stateCode: stateCode,
          aisheCode: aisheCode,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: this.instituteType ? this.instituteType : null,
          universityId: this.universityId,
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
      } if (this.roleId === this.sharedService.role['University']
        || this.roleId === this.sharedService.role['Directorate of Technical Education']
        || this.roleId === this.sharedService.role['State Nursing Body/Council']
        || this.roleId === this.sharedService.role['State Council of Educational Research and Training']
        || this.roleId === this.sharedService.role['State Paramedical Council']
        || this.roleId === this.sharedService.role['National Council for Hotel Management and Catering Technology']) {
        this.officers = this.subRoleLevel3['0']?.id;
        this.selfDetailsLevel3 = false;
        this.hideAction = false;
        this.payload = {
          roleId: this.officers,
          userStatus: userStatus,
          formUploadStatus: 'ALL',
          stateCode: stateCode === 0 ? null : stateCode,
          aisheCode: aisheCode,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: this.instituteType ? this.instituteType : null,
          universityId: this.universityId,
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
      } if (this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['SysAdmin']) {
        this.selfDetailsLevel3 = false;
        this.hideAction = false;
        this.payload = {
          roleId: this.officers,
          userStatus: userStatus,
          formUploadStatus: 'ALL',
          stateCode: this.stateCode ? this.stateCode : null,
          aisheCode: aisheCode,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: this.instituteType ? this.instituteType : null,
          universityId: this.universityId,
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
      } if (this.roleId === this.sharedService.role['State Nodal Officer']) {
        this.selfDetailsLevel3 = false;
        this.hideAction = false;
        this.payload = {
          roleId: this.officers,
          userStatus: userStatus,
          formUploadStatus: 'ALL',
          stateCode: stateCode,
          aisheCode: aisheCode,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: this.instituteType ? this.instituteType : null,
          universityId: this.universityId,
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
      }
      if (this.roleId === this.sharedService.role['Apex User']) {
        this.payload = {
          roleId: this.officers,
          userStatus: userStatus,
          formUploadStatus: 'ALL',
          stateCode: null,
          aisheCode: aisheCode,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: this.instituteType ? this.instituteType : null,
          universityId: this.universityId,
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
      }
      if (this.roleId === this.sharedService.role['University Grants Commission']
        || this.roleId === this.sharedService.role['All India Council for Technical Education']
        || this.roleId === this.sharedService.role['Indian Nursing Council']
        || this.roleId === this.sharedService.role['National Council For Teacher Education']
        || this.roleId === this.sharedService.role['National Paramedical Council']
        || this.roleId === this.sharedService.role['National Council for Hotel Management and Catering Technology']) {
        this.selfDetailsLevel3 = false;
        this.hideAction = false;
        this.payload = {
          roleId: this.officers,
          userStatus: userStatus,
          formUploadStatus: 'ALL',
          stateCode: this.stateCode ? this.stateCode : null,
          aisheCode: aisheCode,
          stateLevelBody: this.stateLevelBody ? this.stateLevelBody : null,
          instituteType: this.instituteType ? this.instituteType : null,
          universityId: this.universityId,
          fromDate: fromDate,
          toDate: toDate ? toDate : fromDate,
        }
      }
      this.getuserListLevel3();
    }
    if (this.payload.roleId.toString() === this.sharedService.role['Directorate of Technical Education'] || this.payload.roleId.toString() === this.sharedService.role['State Nursing Body/Council']
      || this.payload.roleId.toString() === this.sharedService.role['State Council of Educational Research and Training'] || this.payload.roleId.toString() === this.sharedService.role['State Paramedical Council']
      || this.payload.roleId.toString() === this.sharedService.role['National Council for Hotel Management and Catering Technology']) {
      this.showAisheCode = false;
    } else {
      this.showAisheCode = true
    }
  }
  handleToDate(event: any) {
    const m = event.value;
    if (m) {
      var year = m.getFullYear();
      var month = m.getMonth();
      var day = m.getDate();
      this.minDate = new Date(year, month, day);
    }
  }
  openAlertDialog() {
    const dialogRef = this.dialog.open(UserManagementComponent, {
      data: {
        message: 'HelloWorld',
        buttonText: {
          cancel: 'Done'
        }
      },
    });
  }
  getUserRoleList() {
    this.authService.getUserRole().subscribe(res => {
      this.userRole = res;

      // this.getSurveyYear();
    }, err => {

    })
  }
  getUserRolePermission() {
    var roleId = null
    this.authService.getLevelAction(this.roleId, false).subscribe(res => {
      this.officersList = res;
      if (this.roleId !== this.sharedService.role['Apex User']) {
        this.subRoleLevel1 = this.officersList['0'].subRoleLevel1;
        this.subRoleLevel2 = this.officersList['0'].subRoleLevel2;
        this.subRoleLevel3 = this.officersList['0'].subRoleLevel3;
      } else {
        this.subRoleLevel1 = [{ id: 2, roleName: 'University Grants Commission' },
        { id: 3, roleName: 'All India Council for Technical Education' },
        { id: 4, roleName: 'Indian Nursing Council' },
        { id: 5, roleName: 'National Council For Teacher Education' },
        { id: 6, roleName: 'State Nodal Officer' },
        { id: 18, roleName: 'National Paramedical Council' },
        { id: 19, roleName: 'Apex User' },
        { id: 22, roleName: 'National Council for Hotel Management and Catering Technology' }]
        this.subRoleLevel2 = [{ id: 7, roleName: 'University' },
        { id: 8, roleName: 'Directorate of Technical Education' },
        { id: 9, roleName: 'State Nursing Body/Council' },
        { id: 10, roleName: 'State Council of Educational Research and Training' },
        { id: 11, roleName: 'State Paramedical Council' }]
        this.subRoleLevel3 = [{ id: 12, roleName: 'College' },
        { id: 13, roleName: 'Polytechnic' },
        { id: 14, roleName: 'Nursing (Diploma) Institute' },
        { id: 15, roleName: 'Teacher Training (Diploma) Institute' },
        { id: 20, roleName: 'Standalone Institution Under Ministry' },
        { id: 21, roleName: 'PGDM' },
        { id: 23, roleName: 'Paramedical Institute' },
        { id: 24, roleName: 'Hotel Management and Catering Institute' },
        { id: 28, roleName: 'Pharmacy Institutions' },
        { id: 29, roleName: 'Instititions Under Rehabilitation Council of India' }]
      
        
      }

      this.officersList['0'].levelApplicable.forEach((ele: any) => {
        if (ele === 1) {
          this.levelOne = true;
        } if (ele === 2) {
          this.levelTwo = true;
        } if (ele === 3) {
          this.levelThree = true;
        } if (ele === 4) {
          this.levelFour = true;
        }
      })
      // this.sno = this.officersList['0'].sno;
      this.stateMandatory = this.officersList['0'].stateMandatory
      this.deoApplicable = this.officersList['0'].deoApplicable
      this.deoLevel = this.officersList['0'].deoLevel
      let event = {
        index: 0
      }
      this.tabSelected(event)


      // this.officersList = this.officersList.sort((a, b) => a.roleName > b.roleName ? 1 : -1);
    }, err => {

    })
  }



  getState() {
    this.authService.getStatePrivate().subscribe(
      (res) => {
        if (res && res.length) {
          this.stateListArray = [];
          this.stateListArray = res;
          this.stateListArray = this.stateListArray.sort((a, b) => a.name > b.name ? 1 : -1);
          this.stateList = this.stateListArray.slice();
        }
      },
      (err) => { }
    );
  }
  getSurveyYear() {
    this.authService.getSurveyYearList().subscribe(res => {
      let surveyYear = res
      var splitSurvey = surveyYear['0'].substring(0, 5);
      this.currentSurveyYear = surveyYear['0'].substring(0, 4)
      var a = surveyYear['0'].substring(7, 9);
      this.surveyYear = splitSurvey + a
      // }
      // });
      for (let index = 2010; index < parseInt(this.currentSurveyYear) + 1; index++) {
        let i = index + 1;
        let a = index + '-' + i;
        let b = a.toString().substring(7, 9)
        this.surveyYearList.push(index + '-' + b)
      }

    }, err => {

    })
  }
  insti() {
    this.disablelist = true;
  }
  closeinsti() {
    this.disablelist = false;
  }

  getUserStatus() {
    this.userService.getUserStatusList().subscribe((result: any) => {

      this.statuslist = result;
      this.statuslistOrg = result;
      // this.statuslist.unshift({
      //   id: 0,
      //   status: 'ALL'
      // })

    }, err => {

    })
  }

  clear() {
    this.officers = '';
    this.userStatus = 1;
    this.surveyYear = '2021-22'
    this.userData = [];
    this.tempUserData = [];
    this.fromDate = null;
    this.toDate = null
    this.searchText = null;
    this.universityListArray = []
    this.universityList = [];
    this.universityId = null
  }
  getDataOnPageSize(event: any) {
    this.pageSize = Number(event);
    if (this.selectedIndex === 0 || this.selectedIndex === 1 || this.selectedIndex === 2) {
      this.selfUserDetail('')
    } if (this.selectedIndex === 3) {
      this.getuserListLevelDeo('')
    } if (this.selectedIndex === 4) {
      this.getuserListSno('')
    }
  }
  getData(event: any) {
    this.page = parseInt(event);
    if (this.selectedIndex === 0 || this.selectedIndex === 1 || this.selectedIndex === 2) {
      this.selfUserDetail('')
    } if (this.selectedIndex === 3) {
      this.getuserListLevelDeo('')
    } if (this.selectedIndex === 4) {
      this.getuserListSno('')
    }
  }
  getuserListLevel1() {
    if (this.payload.userStatus === 'USER_APPROVED') {
      this.payload['isApproved'] = true
    } else {
      this.payload['isApproved'] = false
    }
    this.userService.getUserListLevel1(this.payload).subscribe((res) => {
      if (res.data == "") {
        if (this.evntName == 'find') {
          this.sharedService.showError("No records !!");
          this.userData = []
        }
      }
      else {
        this.userData = res.data;
        this.userData = this.userData.sort((a, b) => a.stateName > b.stateName ? 1 : -1);
        this.tempUserData = [...this.userData]
        this.handlePageChange(this.page)
      }
      console.log('userData', this.userData)


    }, err => {

    });
  }
  getuserListLevel2() {
    //   if (this.roleId !== this.sharedService.role['Ministry of Education'] && this.roleId !== this.sharedService.role['SysAdmin']) {
    //   if (this.officers === parseInt(this.sharedService.role['University'])) {
    //     if (this.isUniversity) {
    //       if (!this.universityId) {
    //         this.sharedService.showValidationMessage('Please select university !!!');
    //         return;
    //       }
    //     }
    //   }
    // }
    this.userService.getuserListL2(this.payload).subscribe((res) => {
      if (res.data == "") {
        if (this.evntName == 'find') {
          this.sharedService.showError("No records !!");
          this.userData = []
        }
      }
      else {
        this.userData = res.data;
        this.userData = this.userData.sort((a, b) => a.stateName > b.stateName ? 1 : -1);
        this.tempUserData = [...this.userData]
        this.handlePageChange(this.page)
      }
    }, err => {

    });

  }



  getUniversityList(officers: any, value: any) {

    if (officers.toString() === this.sharedService.role['College'] || officers.toString() === this.sharedService.role['University']) {
      var userStatus = 'ALL'
      if (this.userStatus === 1) {
        userStatus = 'New_REGISTRATION'
      } if (this.userStatus === 2) {
        userStatus = 'USER_APPROVED'

      } if (this.userStatus === 3) {
        userStatus = 'INACTIVE'
      } if (this.userStatus === 4) {
        userStatus = 'USER_DISAPPROVED'
      } if (this.userStatus === 0) {
        userStatus = 'ALL'
      }

      var stateCode = null;
      if (this.stateCode === null) {
        stateCode = null
      } else {
        stateCode = this.stateCode
      }

      let payload = {
        roleId: this.sharedService.role['University'],
        page: this.page,
        pageSize: 2000,
        stateCode: stateCode,
      }

      this.userService.getAffliatedUniversity(payload).subscribe(res => {
        if (res.data && res.data.length) {
          this.removeDuplicateElement(res.data)
        }
      }, err => {

      })
    } else {
      this.universityListArray = []
      this.universityList = [];
      this.universityId = null
    }
  }
  removeDuplicateElement(data: any) {
    var universityArr: any[] = []
    data.filter(function (item: any) {
      var university = universityArr.findIndex(x => (x.universityName === item.universityName));
      if (university <= -1) {
        if (item.aisheCode !== null) {
          let x: any = item.aisheCode.split('-');
          var aisheId = x[1];
        }

        if (item.universityName !== 'null' && item.universityName !== null) {
          universityArr.push({ universityName: item.universityName, aisheId: aisheId });

        }
      }
      return null;
    });
    this.universityListArray = []
    this.universityList = []
    this.universityListArray = [...universityArr]
    this.universityListArray = this.universityListArray.sort((a, b) => a.universityName > b.universityName ? 1 : -1);
    this.universityList = this.universityListArray.slice()
  }

  getuserListLevel3() {
    if (this.stateCode === null || this.stateCode === "") {
      this.sharedService.showValidationMessage('Please select state !!!');
      return;
    }
    // this.payload['stateCode'] = null
    this.userService.getuserListL3(this.payload).subscribe((res) => {
      if (res.data == "") {
        if (this.evntName == 'find') {
          this.sharedService.showError("No records !!");
          this.userData = []
        }
      }
      else {
        this.userData = res.data;
        this.userData = this.userData.sort((a, b) => a.stateName > b.stateName ? 1 : -1);
        this.tempUserData = [...this.userData]
        this.handlePageChange(this.page)
      }
    }, err => {

    });
  }



  getuserListLevelDeo(evnt: any) {
    this.evntName = evnt
    this.StartLimit = 0;
    this.EndLimit = 25;
    this.pageData = 0;
    this.pageSize = 25;
    this.page = 1;
    this.userData = [];
    this.tempUserData = [];
    var userStatus = 'ALL'
    if (this.roleId === this.sharedService.role['University']) {
      this.instituteType = 'UNIVERSITY'
    } else if (this.roleId === this.sharedService.role['College']) {
      this.instituteType = 'COLLEGE'
    } else if (this.roleId === this.sharedService.role['Polytechnic']
      || this.roleId === this.sharedService.role['Nursing (Diploma) Institute']
      || this.roleId === this.sharedService.role['Teacher Training (Diploma) Institute']
      || this.roleId === this.sharedService.role['Standalone Institution Under Ministry']
      || this.roleId === this.sharedService.role['PGDM']
      || this.roleId === this.sharedService.role['Paramedical Institute']
      || this.roleId === this.sharedService.role['Hotel Management and Catering Institute']) {
      this.instituteType = 'STANDALONE'

    } else {
      this.instituteType = 'ALL'
    }
    if (this.userStatus === 1) {
      userStatus = 'New_REGISTRATION'
    } if (this.userStatus === 2) {
      userStatus = 'USER_APPROVED'

    } if (this.userStatus === 3) {
      userStatus = 'INACTIVE'
    } if (this.userStatus === 4) {
      userStatus = 'USER_DISAPPROVED'
    } if (this.userStatus === 0) {
      userStatus = 'ALL'
    }
    var stateCode = null
    if (this.stateMandatory) {
      stateCode = this.localService.getData('stateCode')
    }
    let payload = {
      roleId: this.sharedService.role['Data Entry Operator'],
      userStatus: userStatus,
      formUploadStatus: 'ALL',
      stateCode: stateCode,
      aisheCode: this.aisheCode ? this.aisheCode : null,
      instituteType: this.instituteType ? this.instituteType : 'ALL',
      deoRoleId: this.officers ? this.officers : this.roleId,
    }
    this.userService.getuserListDeo(payload).subscribe((res) => {
      if (res.data == "") {
        if (this.evntName == 'find') {
          this.sharedService.showError("No records !!");
          this.userData = []
        }
      }
      else {
        this.userData = res.data;
        this.userData = this.userData.sort((a, b) => a.stateName > b.stateName ? 1 : -1);
        this.tempUserData = [...this.userData]
        this.handlePageChange(this.page)
      }

    }, err => {

    });

  }

  getuserListSno(evnt: any) {
    this.evntName = evnt
    this.StartLimit = 0;
    this.EndLimit = 25;
    this.pageData = 0;
    this.pageSize = 25;
    this.page = 1;
    var userStatus = 'ALL'
    this.userData = [];
    this.tempUserData = [];
    if (this.userStatus === 1) {
      userStatus = 'New_REGISTRATION'
    }
    if (this.userStatus === 2) {
      userStatus = 'USER_APPROVED'

    } if (this.userStatus === 3) {
      userStatus = 'INACTIVE'
    } if (this.userStatus === 4) {
      userStatus = 'USER_DISAPPROVED'
    } if (this.userStatus === 0) {
      userStatus = 'ALL'
    }
    this.payload = ''
    console.log(this.statuslist)
    this.payload = {
      roleId: this.sharedService.role['State Nodal Officer'],
      userStatus: userStatus,
      aisheCode: null,
      instituteType: 'ALL',
      stateCode: this.stateCode ? this.stateCode : null,
    }
    // if (this.roleId === this.sharedService.role['SysAdmin'] || this.roleId === this.sharedService.role['Ministry of Education']) {
    //   this.payload['stateCode'] = null
    // } else {
    //   this.payload['stateCode'] = this.stateCode
    // }
    this.userService.getuserSno(this.payload).subscribe((res) => {
      if (res.data == "") {
        if (this.evntName == 'find') {
          this.sharedService.showError("No records !!");
          this.userData = []
        }
      }
      else {
        this.userData = res.data;
        this.userData = this.userData.sort((a, b) => a.stateName > b.stateName ? 1 : -1);
        this.tempUserData = [...this.userData]
        this.handlePageChange(this.page)
      }
    }, err => {

    });

  }


  reset(level: any) {
    if (level == 'level1') {
      this.officers = ""
      this.userStatus = ""
      this.userData = []
      this.selfUserDetail('')

    }
    if (level == 'level2') {

      this.officers = ""
      this.userStatus = ""
      this.stateCode = ""
      this.universityId = null
      this.fromDate = null
      this.toDate = null
      this.userData = []
      this.selfUserDetail('')

    }
    if (level == 'level3') {

      this.officers = ""
      this.userStatus = ""
      this.stateCode = ""
      this.universityId = null
      this.fromDate = null
      this.toDate = null
      this.userData = []
      this.selfUserDetail('')
    }
    if (level == 'deo') {

      this.officers = ""
      this.userStatus = ""
      this.userData = []
      this.selfUserDetail('')

    }
    if (level == 'sno') {
      this.stateCode = ""
      this.userStatus = ""
      this.userData = []
      this.selfUserDetail('')

    }
  }


  reload() {
    // this.officers = '';
    this.userStatus = 1
    this.fromDate = null;
    this.toDate = null;
    this.universityId = null
    this.formUploadStatus = 'ALL';
    this.page = 1;
    this.pageSize = 25;
    this.stateCode = null
    this.universityListArray = []
    this.universityList = [];
    this.universityId = null
    if (this.loginMode === 'C') {
      this.instituteType = 'COLLEGE'
    } else if (this.loginMode === 'U') {
      this.instituteType = 'UNIVERSITY'
    } else if (this.loginMode === 'S') {
      this.instituteType = 'STANDALONE'
    } else {
      this.instituteType = 'ALL'
    }
    if (this.roleId === this.sharedService.role['University'] || this.roleId === this.sharedService.role['College']) {
      this.stateCode = this.localService.getData('stateCode');
      this.stateLevelBody = this.localService.getData('stateLevelBody');
    }
    if (this.selectedIndex === 0 || this.selectedIndex === 1) {
      this.selfUserDetail('');
    } if (this.selectedIndex === 3) {
      if (this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['SysAdmin']) {
        this.stateCode = null
        this.officers = 1
      }
      this.getuserListLevelDeo('');
    } if (this.selectedIndex === 4) {
      if (this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['SysAdmin']) {
        this.stateCode = null
      } else {
        this.stateCode = this.localService.getData('stateCode');
      }
      this.getuserListSno('')
    }
  }



  handlePageChange(event: any) {
    this.page = event
    this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
      this.EndLimit = this.StartLimit + Number(this.pageSize)
    var a = Math.ceil(this.userData.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.userData.length);
    } else {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.userData.length - 1);
    }

  }



  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1)
  }

  async updateResults() {
    this.userData = []
    this.userData = this.searchByValue(this.tempUserData);
    this.handlePageChange(this.page = 1)
  }



  searchByValue(userData: any) {
    return userData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.userId?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.fullName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.stateName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.instituteName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.universityName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.userStatusName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.mobile?.toString().includes(this.searchText.trim()))
          || (item.emailId?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.aisheCode?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  openConfirmDialog(item: any, action: any) {
    if ((item.userStatus === 1 || item.userStatus === 3) && (item.aisheCode && action !== 'view')) {
      let payload = {
        aisheCode: item.aisheCode?.toUpperCase()
      }
      this.authService.getUserDetailsByAisheCode(payload).subscribe(res => {
        if (res.data?.aisheCode) {
          if (res.data?.statusId === 2) {
            res.data['user'] = true;
            this.showNote = true;
            item.activeUserName = res.data['name'];
            item.activeUserAisheCode = res.data['aisheCode'];
            item.activeUserUserId = res.data['userId'];
            item.activeUserInstituteName = res.data['instituteName'];
            // this.sharedService.viewUser(res.data)
            this.openPopUp(item, action)
          } else {
            this.showNote = false;
            this.openPopUp(item, action)
          }
        } else {
          this.showNote = false;
          this.openPopUp(item, action)
        }
      }, err => {

      })
    } else {
      this.showNote = false;
      this.openPopUp(item, action)
    }
  }

  openPopUp(item: any, action: any) {
    if (this.selectedIndex === 0) {
      if (parseInt(this.sharedService.role['State Nodal Officer']) === item.roleId) {
        item['role'] = '2'

      } else {
        item['role'] = '1'

      }
    } else if (this.selectedIndex === 1) {
      item['role'] = '2'
    } else if (this.selectedIndex === 2) {
      item['role'] = '3'
      if (action === 'action') {
        if (!item.aisheCode) {
          this.sharedService.showValidationMessage('Please approve this institute');
          return;
        }
      }

    }
    item['userStatusList'] = [...this.statuslist]
    if (this.subRoleLevel1 && this.subRoleLevel1.length) {
      item['subRoleLevel1'] = [...this.subRoleLevel1];
    } if (this.subRoleLevel2 && this.subRoleLevel2.length) {
      item['subRoleLevel2'] = [...this.subRoleLevel2];
    } if (this.subRoleLevel3 && this.subRoleLevel3.length) {
      item['subRoleLevel3'] = [...this.subRoleLevel3];
    }
    item['action'] = action
    if (item.stateCode !== '0') {
      this.authService.getDistrict(item.stateCode).subscribe(
        (res) => {
          if (res && res.length) {
            item['districtList'] = res;
            item['stateList'] = this.stateList;
            item['action'] = action
            item['selectedIndex'] = this.selectedIndex
            item['showAisheCode'] = this.showAisheCode
            item['showNote'] = this.showNote;

            this.sharedService.userDetail(item).subscribe(res => {
              if (res === true) {
                if (this.selectedIndex === 0 || this.selectedIndex === 1 || this.selectedIndex === 2) {
                  this.selfUserDetail('');
                } if (this.selectedIndex === 3) {
                  this.getuserListLevelDeo('');
                } if (this.selectedIndex === 4) {
                  this.getuserListSno('');
                }
                // item.userStatus = 2
                // item.userStatusName = 'Active'
                // return;
              }
              // else if (res === false) {
              //   item.userStatus = 4
              //   item.userStatusName = 'Disapproved'
              //   return;
              // }
            })
          }
        },
        (err) => { }
      );
    } else {
      this.sharedService.userDetail(item).subscribe(res => {
        if (res === true) {
          if (this.selectedIndex === 0 || this.selectedIndex === 1 || this.selectedIndex === 2) {
            this.selfUserDetail('');
          } if (this.selectedIndex === 3) {
            this.getuserListLevelDeo('');
          } if (this.selectedIndex === 4) {
            this.getuserListSno('');
          }

        }

      })
    }
  }
  getDistrict(state: any) {

  }
  assignRole() {
    let array = [...this.userPrevillage]
    this.sharedService.assign(array).subscribe(res => {
      if (res) {

      }
    })
  }
  clearStartDate() {
    this.fromDate = null;
  }
  clearEndDate() {
    this.toDate = null
  }


  openUserDialog(data: any) {
    this.dialog.open(UpdateUserDialogComponent, {
      disableClose: true,
      width: '50%',
      height: 'auto',
      data: data,
    }).afterClosed().subscribe((result) => {
      if (result.status === 'Success') {
        if (this.selectedIndex === 0) {
          this.getuserListLevel1()

        }
        if (this.selectedIndex === 1) {
          this.getuserListLevel2()

        }
        if (this.selectedIndex === 2) {
          this.getuserListLevel3()

        }
        if (this.selectedIndex === 3) {
          this.getuserListLevelDeo('')

        }
        if (this.selectedIndex === 4) {
          this.getuserListSno('')

        }
      }

    });
  }
}


