import { ViewportScroller } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';


@Component({
  selector: 'app-enrolment',
  templateUrl: './enrolment.component.html',
  styleUrls: ['./enrolment.component.scss']
})
export class EnrolmentComponent implements OnInit {
  view: boolean = true;

  minBreakup: boolean = false;
  formData: FormGroup;
  otherMinBreakupArray: FormArray;
  perPageData: Array<any> = [];
  pageData: number = 0;
  pageSize: number = 15;
  page: number = 1;
  StartLimit: number = 0;
  EndLimit: number = 15;
  tempEnrollData: any[] = [];
  enrollData: Array<any> = [];
  loginMode: string | undefined;
  searchText: any;
  aisheCode: any;
  modeId: number = 1;
  currentSurveyYear: number = 2022;
  whetherOnlineProg: boolean = false;
  whetherProgThroughPrivate: boolean = false;
  whetherOffCampus: boolean = false;
  languageList: Array<any> = [];
  matLabel: any;
  establishmentYear: any;
  approvingUnivList: Array<any> = [];
  statuatoryBodyList: Array<any> = [];
  showInCollege: boolean = false;
  showInStandalone: boolean = false;
  showInUniv: boolean = false;
  latestSurveyYear: number = 2021;
  modeList: Array<any> = [];
  whetherProgThroughDistance: boolean = false;
  mode: any;
  showCard: boolean = false;
  addEdit: string = 'Update';
  girl_exclusive: any;
  boy_exclusive: any;
  tempBoys: any;
  tempGirls: any;
  tempDiscipline: Array<any> = []
  yearList: any[] = [];
  monthList: any[] = [];
  showMonthList: boolean = true;
  showYearList: boolean = true;
  couseStatus: any;
  disciplineSubject: any[] = [];
  variables5: any[] = [];
  departmentList: any[] = [];
  tempDepartment: any[] = [];
  facultyList: any[] = [];
  disciplineSubjectArray: any[] = [];
  offCampusArray: any[] = [];
  variables1: any[] = [];
  variables2: any[] = [];
  variables3: any[] = [];
  variables4: any[] = [];
  offCampusAvailable: boolean = false
  offCampusList: Array<any> = []
  regionalCentreList: Array<any> = []
  filterDiscipline1: Array<any> = [];
  isFormInvalid: boolean = false
  disabledLockData: boolean = true
  totalEnrolEWSEx: number = 0;
  totalEnrolEWS: number = 0
  totalStudent: number = 0;
  totalMales: number = 0;
  totalFemales: number = 0;
  totalTransgender: number = 0;
  totalSeatEarmarked: number = 0
  totalMalesEx: number = 0
  totalTransgenderEx: number = 0;
  totalFemaleEx: number = 0;
  teachingStaffs: Array<any> = []
  filterDepartmentList: any[] = []
  facultyName: any;
  noRecords: boolean = true;
 

  filterFaculty: any;
  filterDepartment: any;
  dropdownDsg: any;
  dropdownLevel: any;
  dropdownProgramName: any;
  dropdownAppointment: any;
  tempDesignation: any[] = [];
  filterDesignation: any[] = [];
  filterFacultyList: any[] = [];
  filterEmployeeList: any[] = [];
  dropdownJob: any;
  boardGroupFilter: any[] = []
  boardCategoryFilter: any[] = []
  dropdownName: any;
  variables6: any[] = []
  variables7: any[] = []
  variables8: any[] = []
  variables9: any[] = []
  variables10: any[] = []

  jobStatusListFilter: any[] = [];
  programmeList: any[] = [];
  levelFilter: any[] = [];
  boardCatorgryFilter: any[] = [];
  boardNameFilter: any[] = [];
  userId: any;
  employeeName: string = '';
  departmentName: string = '';
  filter: any
  dropdownBoardCategory: any;
  dropdownBoardName: any;
  instituteName: string | undefined;



  constructor(
    public authService: AuthService, public sharedService: SharedService,
    public viewportScroller: ViewportScroller, public fb: FormBuilder,public localService:LocalserviceService
  ) {
    if (this.localService.getData('loginMode') && this.localService.getData('aisheCode')) {
      this.aisheCode = this.localService.getData('aisheCode')
      this.instituteName = this.localService.getData('institutionName')
      this.getBoolean()
    }
    
    // this.getFacultyList()
    this.modeList = this.sharedService.modeList;
    this.modeList.push({
      id: 6,
      name: 'Regional Centre(s) Distance Mode'
    })
    this.formData = this.fb.group({
      offCampusName: '',
      offcampusId: null,
      'onlineId': null,
      "draft": false,
      "isUpdated": true,
      "regionalCentreId": [null, []],
      "regionalCentreName": ['', []],
      "id": [0, []],
      "deptId": ['', []],
      "deptName": [null, []],
      "discipline": ['', []],
      "month": [null, []],
      "year": [null, []],
      "facultyId": ['', []],
      "facultyName": ['', []],
      "levelName": ['', []],
      "levelId": ['', []],
      "disciplineGroupCategory": ['', []],
      "disciplineGroupDiscipline": ['', []],
      "programme": ['', []],
      "programmeId": ['', []],
      "courseId": [null, []],
      "surveyYear": this.currentSurveyYear,

      // "seatsEarmarkedEws": [0, []],
      // "seatsEarmarkedGeneral": [0, []],
      // "seatsEarmarkedObc": [0, []],
      // "seatsEarmarkedSc": [0, []],
      // "seatsEarmarkedSt": [0, []],
      // "seatsEarmarkedTotal": [0, []],
      "generalMale": [0, []],
      "generalFemale": [0, []],
      "generalTransgender": [0, []],
      "generalTotal": [0, []],
      "ewsMale": [0, []],
      "ewsFemale": [0, []],
      "ewsTransgender": [0, []],
      "ewsTotal": [0, []],
      "obcMale": [0, []],
      "obcFemale": [0, []],
      "obcTransgender": [0, []],
      "obcTotal": [0, []],
      "scMale": [0, []],
      "scFemale": [0, []],
      "scTransgender": [0, []],
      "scTotal": [0, []],
      "stMale": [0, []],
      "stFemale": [0, []],
      "stTransgender": [0, []],
      "stTotal": [0, []],
      "totalMale": [0, []],
      "totalFemale": [0, []],
      "totalTransgender": [0, []],
      "totalTotal": [0, []],
      // "totalRemark": [0, []],
      "pwdGeneralMale": [0, []],
      "pwdGeneralFemale": [0, []],
      "pwdGeneralTransgender": [0, []],
      "pwdGeneralTotal": [0, []],
      "pwdEwsMale": [0, []],
      "pwdEwsFemale": [0, []],
      "pwdEwsTransgender": [0, []],
      "pwdEwsTotal": [0, []],
      "pwdObcMale": [0, []],
      "pwdObcFemale": [0, []],
      "pwdObcTransgender": [0, []],
      "pwdObcTotal": [0, []],
      "pwdScMale": [0, []],
      "pwdScFemale": [0, []],
      "pwdScTransgender": [0, []],
      "pwdScTotal": [0, []],
      "pwdStMale": [0, []],
      "pwdStFemale": [0, []],
      "pwdStTransgender": [0, []],
      "pwdStTotal": [0, []],
      "pwdTotalMale": [0, []],
      "pwdTotalFemale": [0, []],
      "pwdTotalTransgender": [0, []],
      "pwdTotalTotal": [0, []],
      // "pwdRemarks": [0, []],
      "muslimGeneralMale": [0, []],
      "muslimGeneralFemale": [0, []],
      "muslimGeneralTransgender": [0, []],
      "muslimGeneralTotal": [0, []],
      "muslimEwsMale": [0, []],
      "muslimEwsFemale": [0, []],
      "muslimEwsTransgender": [0, []],
      "muslimEwsTotal": [0, []],
      "muslimObcMale": [0, []],
      "muslimObcFemale": [0, []],
      "muslimObcTransgender": [0, []],
      "muslimObcTotal": [0, []],
      "muslimScMale": [0, []],
      "muslimScFemale": [0, []],
      "muslimScTransgender": [0, []],
      "muslimScTotal": [0, []],
      "muslimStMale": [0, []],
      "muslimStFemale": [0, []],
      "muslimStTransgender": [0, []],
      "muslimStTotal": [0, []],
      "muslimTotalMale": [0, []],
      "muslimTotalFemale": [0, []],
      "muslimTotalTransgender": [0, []],
      "muslimTotalTotal": [0, []],
      // "muslimRemark": [0, []],
      "otherMinorityGeneralMale": [0, []],
      "otherMinorityGeneralFemale": [0, []],
      "otherMinorityGeneralTransgender": [0, []],
      "otherMinorityGeneralTotal": [0, []],
      "otherMinorityEwsMale": [0, []],
      "otherMinorityEwsFemale": [0, []],
      "otherMinorityEwsTransgender": [0, []],
      "otherMinorityEwsTotal": [0, []],
      "otherMinorityObcMale": [0, []],
      "otherMinorityObcFemale": [0, []],
      "otherMinorityObcTransgender": [0, []],
      "otherMinorityObcTotal": [0, []],
      "otherMinorityScMale": [0, []],
      "otherMinorityScFemale": [0, []],
      "otherMinorityScTransgender": [0, []],
      "otherMinorityScTotal": [0, []],
      "otherMinorityStMale": [0, []],
      "otherMinorityStFemale": [0, []],
      "otherMinorityStTransgender": [0, []],
      "otherMinorityStTotal": [0, []],
      "otherMinorityTotalMale": [0, []],
      "otherMinorityTotalFemale": [0, []],
      "otherMinorityTotalTransgender": [0, []],
      "otherMinorityTotalTotal": [0, []],
      // "otherRemarks": [0, []],
      "otherMinorityBreakupAvailable": [false, []],
      "otherMinBreakup": this.fb.array([
        this.fb.group(
          {
            id: [0, []],
            otherMinorityId: [null, []],
            generalMale: [0, []],
            generalFemale: [0, []],
            generalTransgender: [0, []],
            generalTotal: [0, []],
            scMale: [0, []],
            scFemale: [0, []],
            scTransgender: [0, []],
            scTotal: [0, []],
            obcMale: [0, []],
            obcTotal: [0, []],
            obcFemale: [0, []],
            obcTransgender: [0, []],
            ewsTotal: [0, []],
            ewsFemale: [0, []],
            ewsMale: [0, []],
            ewsTransgender: [0, []],
            stMale: [0, []],
            stFemale: [0, []],
            stTransgender: [0, []],
            stTotal: [0, []],
            totalMale: [0, []],
            totalFemale: [0, []],
            totalTransgender: [0, []],
            totalTotal: [0, []],
            // otherRemarks: 0
          }
        )
      ]),
      "courseStatus": ['false']
    });

    this.otherMinBreakupArray = this.formData.controls['otherMinBreakup'] as FormArray;
  }



  ngOnInit(): void {
  }
  creatControl() {

  }
  getBoolean() {
    this.mode = this.modeId;
    let x: any = this.aisheCode.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    if (loginMode === 'U') {
      this.showInUniv = true;
      this.showInCollege = false;
      this.showInStandalone = false;
    } else if (loginMode === 'S') {
      this.showInUniv = false;
      this.showInCollege = false;
      this.showInStandalone = true;
    } else {
      this.showInUniv = false;
      this.showInCollege = true;
      this.showInStandalone = false;
    }
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId
    };
    this.authService.booleanValue(payload).subscribe(res => {
      this.whetherOnlineProg = res.isOfferOnlineProgram;
      this.whetherProgThroughPrivate = res.programmeThroughPrivateExternal;
      this.whetherOffCampus = res.offerCampusAvailable;
      this.whetherProgThroughDistance = res.offerDistanceProgramme;
      this.offCampusAvailable = res.offerCampusAvailable
      if (this.modeId === 1 || this.modeId === 2 || this.modeId === 3 || this.modeId === 5) {
        this.getStudentEnrolled();
      } else if (this.modeId === 4) {
        this.getStudentEnrolledO();
      } else {
        if (this.regionalCentreList.length === 0) {
          this.getDetailsRegionalCenter();

        }
        this.getStudentEnrolledRegional();
      }
    }, err => {
    });
  }
  getStudentEnrolledO() {
    this.mode = this.modeId;
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode?.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId
    };
    this.authService.getEnrollmentListO(payload).subscribe(res => {
      this.totalStudent = 0;
      this.totalMales = 0;
      this.totalFemales = 0;
      this.totalTransgender = 0;
      this.totalEnrolEWSEx = 0;
      this.totalEnrolEWS = 0
      this.totalMalesEx = 0
      this.totalTransgenderEx = 0;
      this.totalFemaleEx = 0
      this.enrollData = [];
      this.tempEnrollData = [];

      if (res.data && res.data.length) {
        res.data.forEach((ele: any) => {

          ele['offcampusId'] = ele.offcampus?.id;
          ele['minority'] = ele.otherMinorityBreakupAvailable;
          ele['disciplineName'] = `(${ele.levelName})` + `(${ele.programme})` + `(${ele.disciplineGroupCategory})` + `(${ele.disciplineGroupDiscipline})` + `${ele.discipline}`;
          if (ele.offcampus?.offcampusAisheCode) {
            ele['offcampusName'] = ele.offcampus['name']?.concat(" ", '(C-', ele.offcampus['offcampusAisheCode'], ')');
          } else {
            ele['offCampusName'] = ele.offcampus?.name;
          }

          ele['isUpdated'] = true;
          ele['draft'] = false;
          if (!ele.facultyName) {
            ele['facultyId'] = 0;
            ele['facultyName'] = 'No Faculty';
          } if (!ele.deptName) {
            ele.deptId = 0;
            ele['deptName'] = 'No Department';
          }
        });
        this.enrollData = res.data;
        this.tempEnrollData = [...this.enrollData];
        this.calculateTotal()
        this.handlePageChange(this.page);
      } else {
      }
    }, err => {
    });
  }
  getStudentEnrolledRegional() {
    this.mode = this.modeId;
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId
    };
    this.authService.getEnrollmentRegionalList(payload).subscribe(res => {
      this.totalStudent = 0;
      this.totalMales = 0;
      this.totalFemales = 0;
      this.totalTransgender = 0;
      this.totalEnrolEWSEx = 0;
      this.totalEnrolEWS = 0
      this.totalMalesEx = 0
      this.totalTransgenderEx = 0;
      this.totalFemaleEx = 0
      this.enrollData = [];
      this.tempEnrollData = [];
      if (res['RC ENROLLMENT'] && res['RC ENROLLMENT'].length) {
        res['RC ENROLLMENT'].forEach((ele: any) => {

          ele['isUpdated'] = true;
          ele['draft'] = false;
          if (ele.course.faculty === null) {
            ele['facultyId'] = 0;
            ele['facultyName'] = 'No Faculty';
          } else {
            ele['facultyId'] = ele.course.faculty.id;
            ele['facultyName'] = ele.course.faculty.facultyName;
          } if (ele.course.department === null) {
            ele['deptId'] = 0;
            ele['deptName'] = 'No Department';
          } else {
            ele['deptId'] = ele.course.department.departmentId;
            ele['deptName'] = ele.course.department.departmentName;
          }
          ele['courseId'] = ele.course.id;
          ele['levelId'] = ele.course.levelId.id;
          ele['levelName'] = ele.course.levelId.level;
          ele['programme'] = ele.course.programmeId.programme;
          ele['disciplineGroupDiscipline'] = ele.course.broadDisciplineGroup.discipline;
          ele['disciplineGroupCategory'] = ele.course.broadDisciplineGroupCategoryId.category;
          ele['discipline'] = ele.course.discipline;
          ele['regionalCentreId'] = ele.regionalCenter.id;
          ele['regionalCentreName'] = ele.regionalCenter.name,
            ele['disciplineName'] = `(${ele.levelName})` + `(${ele.programme})` + `(${ele.disciplineGroupCategory})` + `(${ele.disciplineGroupDiscipline})` + `${ele.discipline}`;

        });
        this.enrollData = res['RC ENROLLMENT'];
        this.tempEnrollData = [...this.enrollData];
        this.calculateTotal()
        this.handlePageChange(this.page);
      }
    }, err => {
    });
  }
  getEnrolment() {
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId
    };
  }
  handlePageChange(event: any) {
    this.page = event;
    this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
      this.EndLimit = this.StartLimit + Number(this.pageSize);
    var a = Math.ceil(this.enrollData.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.enrollData.length);
    } else {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.enrollData.length - 1);
    }

  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1);
  }
  async updateResults() {
    this.enrollData = [];
    this.enrollData = this.searchByValue(this.tempEnrollData);
    this.handlePageChange(this.page = 1);
  }

  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        // item.name.trim();
        return (item.facultyName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.deptName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.levelName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.programme.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.disciplineGroupCategory.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.disciplineGroupDiscipline.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.discipline.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.year?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.month?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()));
      }
    });
  }
  getStudentEnrolled() {
    this.mode = this.modeId;
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode?.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId
    };
    this.authService.getEnrollmentList(payload).subscribe(res => {
      this.totalStudent = 0;
      this.totalMales = 0;
      this.totalFemales = 0;
      this.totalTransgender = 0;
      this.totalEnrolEWSEx = 0;
      this.totalEnrolEWS = 0
      this.totalMalesEx = 0
      this.totalTransgenderEx = 0;
      this.totalFemaleEx = 0
      this.enrollData = [];
      this.tempEnrollData = [];

      if (res && res.length) {
        res.forEach((ele: any) => {
          ele['minority'] = ele.otherMinorityBreakupAvailable;
          ele['disciplineName'] = `(${ele.levelName})` + `(${ele.programme})` + `(${ele.disciplineGroupCategory})` + `(${ele.disciplineGroupDiscipline})` + `${ele.discipline}`;
          ele['isUpdated'] = true;
          ele['draft'] = false;
          if (!ele.facultyName) {
            ele['facultyId'] = 0;
            ele['facultyName'] = 'No Faculty';
          } if (!ele.deptName) {
            ele.deptId = 0;
            ele['deptName'] = 'No Department';
          }
        });
        this.enrollData = res;
        this.tempEnrollData = [...this.enrollData];
        this.removeDuplicateElement(this.enrollData)
        this.handlePageChange(this.page);
        this.calculateTotal()
      } else {
      }
    }, err => {
    });
  }
  viewData(data: any) {
    this.mode = this.modeId;
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode?.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId,
      enrollmentId: data.id
    };
    this.showCard = true;
    this.addEdit = 'Update';
    this.viewportScroller.scrollToPosition([0, 0]);
    this.formData.patchValue(data);
    this.formData.controls['year'].setValue(data.year);
    this.formData.controls['month'].setValue(parseInt(data.month));
    this.getProgramDetails(data.courseId);
    this.hideSeat(data.year);
    if (this.showInUniv) {
      this.getFilterCourseThroughDepartment(data.deptId);
      this.getNoFaculty(data.facultyId);
    }
    if (data.otherMinorityBreakupAvailable) {

      this.authService.getOtherMinorityBreakup(data.id).subscribe(res => {
        this.otherMinBreakup(res);
      }, err => {
      });
    } else {
      this.minBreakup = false;
    }
  }
  getFilterCourseThroughDepartment(departmentId: any) {
    let facultyId = this.formData.controls['facultyId'].value;
    if (facultyId === 0) {
      facultyId = null
    }
    if (departmentId === 0) {
      if (facultyId !== '') {
        this.disciplineSubject = this.tempDiscipline.filter(ele => ele.depId === null && ele.facId === facultyId);
      } else {
        this.disciplineSubject = this.tempDiscipline.filter(ele => ele.depId === null);

      }
    } else {
      if (facultyId !== '') {
        this.disciplineSubject = this.tempDiscipline.filter(ele => ele.depId === departmentId && ele.facId === facultyId);
      } else {
        this.disciplineSubject = this.tempDiscipline.filter(ele => ele.depId === departmentId);
      }
    }
  }
  getNoFaculty(facultyId: any) {
    if (facultyId === 0) {
      facultyId = null
      this.variables5 = this.tempDepartment.filter((ele: any) => ele.facultyId === facultyId)
      this.departmentList = this.variables5.slice()
    } else if (facultyId) {
      this.variables5 = this.tempDepartment.filter((ele: any) => ele.facultyId === facultyId)
      this.departmentList = this.variables5.slice();
    } else {
      this.variables5 = []
      this.departmentList = []
    }
  }
  otherMinBreakup(otherMinBreakup: any) {
    this.minBreakup = true;
    this.otherMinBreakupArray.clear();
    for (let x of otherMinBreakup) {
      this.otherMinBreakupArray.push(
        this.fb.group(
          {
            id: x.id,
            otherMinorityId: x.otherMinorityId,
            generalMale: x.generalMale,
            generalFemale: x.generalFemale,
            generalTransgender: x.generalTransgender,
            generalTotal: x.generalTotal,
            scMale: x.scMale,
            scFemale: x.scFemale,
            scTransgender: x.scTransgender,
            scTotal: x.scTotal,
            obcMale: x.obcMale,
            obcTotal: x.obcTotal,
            obcFemale: x.obcFemale,
            obcTransgender: x.obcTransgender,
            ewsTotal: x.ewsTotal,
            ewsFemale: x.ewsFemale,
            ewsMale: x.ewsMale,
            ewsTransgender: x.ewsTransgender,
            stMale: x.stMale,
            stFemale: x.stFemale,
            stTransgender: x.stTransgender,
            stTotal: x.stTotal,
            totalMale: x.totalMale,
            totalFemale: x.totalFemale,
            totalTransgender: x.totalTransgender,
            totalTotal: x.totalTotal,
            // otherRemarks: 0
          }
        )
      );
    }
  }
  getProgramDetails(courseId: any) {
    // this.seatEarmarked = false;
    if (this.addEdit === 'Add') {
      this.formData.controls['year'].setValue(null);
      this.formData.controls['month'].setValue(null);
    }

    let form = this.tempDiscipline.find(ele => ele.id === courseId)
    this.couseStatus = form.courseStatus;
    this.formData.patchValue({
      "disciplineGroupCategory": form?.broadDisciplineGroupCategoryId?.category,
      "disciplineGroupDiscipline": form?.broadDisciplineGroup?.discipline,
      "courseId": courseId,
      "discipline": form.discipline,
      "levelName": form.levelId.level,
      "levelId": form.levelId.id,
      "programme": form.programmeId.programme,
      "programmeId": form.programmeId.id,
      "facultyName": form.faculty?.facultyName,
      "deptName": form.department?.departmentName,
      "courseStatus": form.courseStatus,
    })
    var yearCount = form.yearOfStart - this.currentSurveyYear;
    var positiveVal = Math.abs(yearCount)
    this.yearList = [];
    this.monthList = []
    var count = 1;
    if (form.levelId.id === "1") {
      this.showMonthList = false;
      this.showYearList = false;
      this.formData.controls['year'].setValue(1);
      this.formData.controls['month'].setValue(0);
    }

    else {
      this.showMonthList = false;
      this.showYearList = true;
    }



    if (form.levelId.id !== "1") {

      var yearCount = form.yearOfStart - this.currentSurveyYear;
      var positiveVal = Math.abs(yearCount)
      this.yearList = []
      var count = 1;
      if (form.yearOfStart === 'Prior to 2010' || form.yearOfStart === `Prior to ${this.establishmentYear}`) {
        form.yearOfStart = 2010
      }
      if (form.yearOfStart === 2016 || form.yearOfStart < 2016) {
        for (let index = 0; index < form.durationYear; index++) {
          this.yearList.push((count++).toString())
        }
      } else {
        if (form.durationYear >= positiveVal + 1) {
          for (let index = 0; index < positiveVal + 1; index++) {
            this.yearList.push((count++).toString())
          }
        } else {
          for (let index = 0; index < form.durationYear; index++) {
            this.yearList.push((count++).toString())
          }
        }
      }
      if (form.levelId.id === '7' || form.levelId.id === '6') {
        this.monthList.push(form.durationMonth)
        if (this.yearList.length === 0) {
          if (form.durationYear === 0) {
            this.showYearList = false;
            this.yearList.push(form.durationYear)
            this.formData.controls['year'].setValue(0);//
          }
        } else {
          this.showYearList = true;
        }
        if (this.monthList.length === 0) {
          if (form.durationMonth === 0) {
            this.showMonthList = false;
            this.formData.controls['month'].setValue(0);
          }
        } else {
          this.showMonthList = true
        }
        if (this.formData.controls['year'].value !== null && this.formData.controls['year'].value !== undefined && this.formData.controls['year'].value !== '') {
          if (this.formData.controls['year'].value !== 0 && this.formData.controls['year'].value !== '0') {
            this.showMonthList = false
            this.formData.controls['month'].setValue(0);
          }
        }

        if (this.formData.controls['year'].value === 0 || this.formData.controls['year'].value === '0') {
          this.showYearList = false
          this.formData.controls['year'].setValue(0);
        }
      }
      if (form.levelId.id === '2' || form.levelId.id === '3' || form.levelId.id === '4' || form.levelId.id === '5' || form.levelId.id === '8') {
        this.formData.controls['month'].setValue(0);
      }
      if (form.levelId.id === "8" && (form.programmeId.id === '216' || form.programmeId.id === '217' || form.programmeId.id === '086')) {
        this.showMonthList = false;
        this.showYearList = false;
        this.formData.controls['year'].setValue(1);
        this.formData.controls['month'].setValue(0);
      }

    } else {

    }

  }
  getProgramme() {
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId
    }
    this.authService.getProgrammeList(payload).subscribe(
      (res) => {
        this.facultyList = [];
        this.departmentList = [];
        this.tempDepartment = [];
        this.disciplineSubjectArray = []
        this.tempDiscipline = []
        this.disciplineSubject = []
        if (res.data.length && res.data) {
          res.data.forEach((element: { [x: string]: any; surveyYear: number; disciplineName: string; levelId: { level: any; }; programmeId: { programme: any; }; broadDisciplineGroupCategoryId: { category: any; }; broadDisciplineGroup: { discipline: any; }; discipline: string; faculty: { id: any; }; department: { departmentId: any; departmentName: any; }; offcampus: { [x: string]: any; offcampusAisheCode: any; id: any; name: any; }; }) => {
            if (element.surveyYear === this.currentSurveyYear) {
              element.disciplineName = `(${element.levelId.level})` + `(${element.programmeId.programme})` + `(${element.broadDisciplineGroupCategoryId.category})` + `(${element.broadDisciplineGroup.discipline})` + element.discipline
              // element['disciplineName'] = element.discipline + `(${element.levelId.level})`
              element['facId'] = element.faculty ? element.faculty.id : null
              element['depId'] = element.department ? element.department.departmentId : null

              this.disciplineSubjectArray.push(element)
              this.tempDiscipline.push(element)
              this.disciplineSubject = this.disciplineSubjectArray.slice()

              if (element.faculty) {
                this.facultyList.push(element.faculty)
              }
              if (element.department) {
                this.departmentList.push({
                  departmentId: element.department.departmentId,
                  departmentName: element.department.departmentName,
                  facultyId: element.faculty ? element.faculty.id : null
                });
                this.tempDepartment = [...this.departmentList]
              }
              // if(element.programmeId){
              //   this.disciplineSubject.push(element.programmeId)
              // }
              if (this.offCampusAvailable && this.matLabel === 'Off Campus Centres') {
                if (element.offcampus) {
                  if (element.offcampus.offcampusAisheCode) {
                    this.offCampusArray.push({
                      offcampusName: element.offcampus['name']?.concat(" ", '(C-', element.offcampus['offcampusAisheCode'], ')'),
                      id: element.offcampus.id
                    })
                  } else {
                    this.offCampusArray.push({
                      offcampusName: element.offcampus.name,
                      id: element.offcampus.id
                    })
                  }
                }
              }
            }
          });
        }

        this.facultyList = this.variables1.slice();

        this.departmentList = this.variables5.slice();

        if (this.offCampusAvailable && this.modeId === 4) {
          this.offCampusList = this.offCampusArray.slice()
        }

      }, err => {

      })
  }
  hideSeat(value: any) {

    if (value === '1') {
      this.boy_exclusive = this.tempBoys;
      this.girl_exclusive = this.tempGirls;
    } else {
      this.boy_exclusive = true;
      this.girl_exclusive = true;
    }
  }

  hideSeat1(value: any) {
    this.showMonthList = false
    this.formData.controls['month'].setValue(0);
    // this.setValue(this.formData, this.breakupCount.breakupForm);
    this.minBreakup = false;
    this.otherMinBreakupArray.clear();
    if (value === '1') {
      this.boy_exclusive = this.tempBoys;
      this.girl_exclusive = this.tempGirls;
    } else {

      this.boy_exclusive = true;
      this.girl_exclusive = true;
    }
  }

  hideMonth(value: any) {
    this.showYearList = false
    this.formData.controls['year'].setValue(0);
  }
  getFacultyList() {
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId
    }
    this.authService.getFacultyList(payload).subscribe(res => {
      if (res.data && res.data.length) {
        this.facultyList = []
        // res.data.unshift({
        //   id: 0,
        //   facultyName: 'No Faculty'
        // });
        this.variables1 = res.data;
        this.facultyList = this.variables1.slice()
      } else {
        this.facultyList = [];
      }
    }, err => {

    })
  }
  getDepartment() {
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId
    }
    this.authService.getDepartmentList(payload).subscribe(res => {

      if (res.data && res.data.length) {
        this.tempDepartment = res.data;
      }
    }, err => {
    })
  }
  getDetailsRegionalCenter() {
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId
    }
    this.authService.getRegionalCenter(payload).subscribe(res => {
      this.regionalCentreList = res.data.regionalCenters;
    }, err => {

    })
  }
  getFilterCourseThroughFaculty(facultyId: any) {
    this.getNoFaculty(facultyId);
    this.formData.controls['deptId'].setValue('')

    if (this.matLabel !== 'Off Campus Centres') {
      if (facultyId === 0) {
        this.disciplineSubject = [];
        this.disciplineSubject = this.tempDiscipline.filter(ele => ele.faculty === null);
      } else {
        this.disciplineSubject = [];
        this.disciplineSubject = this.tempDiscipline.filter(ele => ele.faculty?.id === facultyId);
      }

      this.filterDiscipline1 = [...this.disciplineSubject];
    } else {
      if (facultyId === 0) {
        this.disciplineSubject = this.tempDiscipline.filter(ele => ele.faculty === null && ele.offcampus?.id === this.formData.controls['offcampusId'].value);
      } else {
        this.disciplineSubject = this.tempDiscipline.filter(ele => ele.faculty?.id === facultyId && ele.offcampus?.id === this.formData.controls['offcampusId'].value);
      }
    }
    // if (facultyId === 0) {
    //   this.disciplineSubject = [];
    //   this.disciplineSubject = this.tempDiscipline.filter(ele => ele.faculty === null);
    // } else {
    //   this.disciplineSubject = [];
    //   this.disciplineSubject = this.tempDiscipline.filter(ele => ele.faculty?.id === facultyId);
    // }

    //this.filterDiscipline1 = [...this.disciplineSubject]

    // if(this.formData.controls.deptId.value !== ''){
    //   this.getFilterCourseThroughDepartment(this.formData.controls['deptId'].value)
    // }

  }
  calculateTotal() {
    this.totalStudent = 0;
    this.totalMales = 0;
    this.totalFemales = 0;
    this.totalTransgender = 0;
    this.totalEnrolEWSEx = 0;
    this.totalEnrolEWS = 0
    this.totalMalesEx = 0
    this.totalTransgenderEx = 0;
    this.totalFemaleEx = 0
    // this.totalSeatEarmarked = 0;
    for (const data of this.enrollData) {
      // this.totalSeatEarmarked += data.seatsEarmarkedTotal,
      this.totalStudent += data.totalTotal,
        this.totalFemales += data.totalFemale,
        this.totalTransgender += data.totalTransgender,
        this.totalMales += data.totalMale
      this.totalMalesEx += data.totalMale - data.ewsMale
      this.totalFemaleEx += data.totalFemale - data.ewsFemale
      this.totalTransgenderEx += data.totalTransgender - data.ewsTransgender
      // this.totalEnrolEWS += data.totalTotal + data.ewsMale + data.ewsFemale + data.ewsTransgender

    }
    this.totalEnrolEWS = this.totalStudent;
    this.totalEnrolEWSEx = this.totalMalesEx + this.totalFemaleEx + this.totalTransgenderEx
    // this.totalEnrolEWSEx = this.totalEnrolEWS
  }
  onKeypressEvent(event: any, inputLength: any) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }




  allFilter(value: any) {
    let depArr1: any = []
    let depArr2: any = []
    let depArr3: any = []
    let depArr4: any = []
    this.filter = {}
    if (this.facultyName) {
      this.filter['facultyName'] = this.facultyName
      this.enrollData = this.tempEnrollData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )

      let fac: any;
      if (this.facultyName === 0) {
        fac = 'No Faculty'
      } else {
        fac = this.facultyName
      }
      if (this.showInUniv) {
        this.variables5 = this.enrollData.filter(ele => ele.facultyName === fac)
        this.variables5.filter(function (item) {
          var dep = depArr1.findIndex((z: any) => (z.departmentName === item.deptName))
          if (dep <= -1) {
            depArr1.push({ departmentName: item.deptName });
          }


          return null;
        });
        let index = depArr1.findIndex((ele: any) => ele.departmentName === 'No Department')
        if (index !== -1) {
          depArr1.splice(index, 1);
          depArr1.unshift({ departmentName: 'No Department' });
        }
        this.variables5 = []
        this.filterDepartmentList = []
        this.variables5 = [...depArr1]
        this.filterDepartmentList = this.variables5.slice()

      }
    }
    if (this.departmentName) {
      this.filter['deptName'] = this.departmentName
      this.enrollData = this.tempEnrollData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )
    }
    if (this.dropdownLevel) {
      this.filter['levelName'] = this.dropdownLevel
      this.enrollData = this.tempEnrollData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )
      this.variables10 = this.enrollData.filter(ele => ele.levelName === this.dropdownLevel)
      this.variables10.filter(function (item) {
        var per = depArr3.findIndex((z: any) => (z.programName === item.programme))
        if (per <= -1) {
          depArr3.push({ programName: item.programme,levelName:item.levelName });
        }


        return null;
      });

      this.variables7 = []
      this.programmeList = []
      this.variables7 = [...depArr3]
      this.programmeList = this.variables7.slice()
    }

    if (this.dropdownProgramName) {
      this.filter['programme'] = this.dropdownProgramName
      this.enrollData = this.tempEnrollData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )
      this.variables7 = this.enrollData.filter(ele => ele.programme === this.dropdownProgramName)
      this.variables7.filter(function (item) {
        var per = depArr4.findIndex((z: any) => (z.boardCategory === item.disciplineGroupCategory))
        if (per <= -1) {
          depArr4.push({ boardCategory: item.disciplineGroupCategory });
        }


        return null;
      });

      this.variables8 = []
      this.boardCatorgryFilter = []
      this.variables8 = [...depArr4]
      this.boardCatorgryFilter = this.variables8.slice()
    }


    

    if (this.dropdownBoardCategory) {
      this.filter['disciplineGroupCategory'] = this.dropdownBoardCategory
      this.enrollData = this.tempEnrollData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )
     
      this.variables9 = this.enrollData.filter((ele: any) => ele.disciplineGroupCategory === this.dropdownBoardCategory)
      this.variables9.filter(function (item) {
        var dep = depArr2.findIndex((z: any) => (z.boardName === item.disciplineGroupDiscipline))
        if (dep <= -1) {
          depArr2.push({
            boardName: item.disciplineGroupDiscipline
           
          });
        }


        return null;
      });

      this.variables9 = []
      this.boardNameFilter = []
      this.variables9 = [...depArr2]
      this.boardNameFilter = this.variables9.slice()
    }
    if (this.dropdownBoardName) {
      this.filter['disciplineGroupDiscipline'] = this.dropdownBoardName
      this.enrollData = this.tempEnrollData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )
    }
    
    this.calculateTotal()
    this.handlePageChange(this.page = 1)
  }

  reload() {
    this.filter = ''
    this.employeeName = ''
    this.teachingStaffs = [];
    this.facultyName = '';
    this.departmentName = '';
    this.dropdownDsg = '';
    this.filterFaculty = '';
    this.dropdownJob = '';
    this.dropdownName = '';
    this.dropdownAppointment = "";
    this.dropdownBoardCategory = "";
    this.dropdownBoardName = "";
    this.dropdownLevel = "";
    this.dropdownProgramName = ""
    this.enrollData = [...this.tempEnrollData]
    this.removeDuplicateElement(this.enrollData)
    this.calculateTotal()
    // this.removeDuplicateElement(this.tempEnrollData);
    this.handlePageChange(this.page = 1)
  }


  removeDuplicateElement(data: any) {
    var empArr: any = [];
    var facArr: any = [];
    var depArr: any = [];
    var designationArr: any = []
    var jobArr: any = []
    var levelArr: any[] = []
    var appointmentArr: any[] = []
    var boardCatogryArr: any[] = []
    var boardNameArr: any[] = []
    data.filter(function (item: any) {
      var emp = empArr.findIndex((x: any) => (x.name === item.name));
      var fac = facArr.findIndex((y: any) => (y.facultyName === item.facultyName))
      var dep = depArr.findIndex((z: any) => (z.departmentName === item.deptName))
      var designation = designationArr.findIndex((w: any) => w.designationName === item.designationName)
      var job = jobArr.findIndex((z: any) => (z.jobStatus === item.jobStatus))
      var appointment = appointmentArr.findIndex((z: any) => z.programName === item.programme)
      var level = levelArr.findIndex((z: any) => z.levelName === item.levelName)
      var boardCategory = boardCatogryArr.findIndex((z: any) => z.boardCategory === item.disciplineGroupCategory)
      var boardName = boardNameArr.findIndex((z: any) => z.boardName === item.disciplineGroupDiscipline)

      if (emp <= -1) {
        empArr.push({ name: item.name });
      } if (fac <= -1) {
        facArr.push({ facultyName: item.facultyName });
      } if (dep <= -1) {
        depArr.push({ departmentName: item.deptName });
      } if (designation <= -1) {
        designationArr.push({ designationName: item.designationName });
      }
      // } if (job <= -1) {
      //   jobArr.push({ jobStatus: item.jobStatus });

      // }
      if (appointment <= -1) {
        appointmentArr.push({ programName: item.programme });

      }
      if (level <= -1) {
        levelArr.push({ levelName: item.levelName }
          
        );

      }
      if (boardCategory <= -1) {
        boardCatogryArr.push({ boardCategory: item.disciplineGroupCategory });

      }
      if (boardName <= -1) {
        boardNameArr.push({ boardName: item.disciplineGroupDiscipline });

      }
      return null;
    });
    this.variables3 = []
    this.filterEmployeeList = []
    this.variables3 = [...empArr]
    this.filterEmployeeList = this.variables3.slice()
    this.variables4 = []
    this.filterFacultyList = []
    this.variables4 = [...facArr]
    this.filterFacultyList = this.variables4.slice()
    this.variables5 = []
    this.filterDepartmentList = []
    this.variables5 = [...depArr]
    this.filterDepartmentList = this.variables5.slice()
    this.variables6 = []
    this.filterDesignation = []
    this.variables6 = [...designationArr]
    this.filterDesignation = this.variables6.slice()
    this.jobStatusListFilter = [...jobArr]




    this.variables7 = []
    this.programmeList = []
    this.variables7 = [...appointmentArr]

    this.programmeList = this.variables7


    this.variables8 = []
    this.boardCatorgryFilter = []
    this.variables8 = [...boardCatogryArr]
    this.boardCatorgryFilter = this.removeDuplicates(this.variables8, '8');

    this.variables9 = []
    this.boardNameFilter = []
    this.variables9 = [...boardNameArr]
    this.boardNameFilter = this.removeDuplicates(this.variables9, '9');

    this.variables10 = []
    this.levelFilter = []
    this.variables10 = [...levelArr]
    this.levelFilter = this.variables10.slice();
    this.handlePageChange(this.page = 1)
  }

  removeDuplicates(arr: any[], key: any): any {
    let seen = new Set();

    if (key == '8') {
      return arr.filter(item => {
        let duplicate = seen.has(item.boardCategory);
        seen.add(item.boardCategory);
        return !duplicate;
      });
    }
    else if (key == '9') {
      return arr.filter(item => {
        let duplicate = seen.has(item.boardName);
        seen.add(item.boardName);
        return !duplicate;
      });
    }
  }



}
