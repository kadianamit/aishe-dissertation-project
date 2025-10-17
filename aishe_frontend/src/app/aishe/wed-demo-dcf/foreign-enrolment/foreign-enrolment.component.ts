import { ViewportScroller } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { NgbPopoverConfig } from '@ng-bootstrap/ng-bootstrap';
// import { CommonService } from 'src/app/common_service/common.service';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { DeleteDialogComponent } from 'src/app/dialog/delete-dialog/delete-dialog.component';
import { SharedService } from 'src/app/shared/shared.service';
// import { BreakupCount, CustomErrorStateMatcher } from 'src/app/utility/validators';
import { AuthService } from 'src/app/service/auth.service';
// import { AuthService } from '../../auth.service';
// import { FormManagementService } from '../form-management.service';
import { LocalserviceService } from 'src/app/service/localservice.service';


@Component({
  selector: 'app-foreign-enrolment',
  templateUrl: './foreign-enrolment.component.html',
  styleUrls: ['./foreign-enrolment.component.scss']
})
export class ForeignEnrolmentComponent implements OnInit {
  perPageData: Array<any> = []
  pageDataF: number = 0;
  pageSizeF: number = 15;
  pageF: number = 1;
  StartLimitF: number = 0;
  EndLimitF: number = 15;
  tempRegularProgrammeData: any[] = [];
  regularProgrammeData: Array<any> = []
  loginMode: string | undefined;
  searchText: any
  aisheCode: any;
  modeId: number = 1
  currentSurveyYear: number = 2022
  whetherOnlineProg: boolean = false;
  whetherProgThroughPrivate: boolean = false;
  whetherOffCampus: boolean = false;
  languageList: Array<any> = []
  matLabel: any
  establishmentYear: any;
  approvingUnivList: Array<any> = [];
  statuatoryBodyList: Array<any> = [];
  showInCollege: boolean = false;
  showInStandalone: boolean = false;
  showInUniv: boolean = false;
  latestSurveyYear: number = 2021
  modeList: Array<any> = []
  whetherProgThroughDistance: boolean = false
  mode:any
  foreignSudentsEnrolled!:FormGroup
  // foreignSudentsEnrolled: FormGroup;
  showHideForm!: FormGroup;
  selectedIndex: any = 0;
  isEnrolled!: boolean;
  countryList: any[] = [];
  foreignData: any[] = [];
 
  course: any[] = [];
  disciplineSubject: any[] = [];
  currentYearCourse: any = [];
  addOrUpdate!: string;
  constantName: any;
  disabledLockData: boolean = false;
  subjectCount: number = 0;
  isFormInvalid: boolean | undefined;
  othersMehtod: boolean = false;
  checkApprovedIntek: boolean = false;
  disabledAll: boolean = false;
  showCard: boolean | undefined;
  totalCount: any = 0;
  totalFemale: any = 0;
  totalStudent: any = 0;
  totalBoys: any = 0;
  totalGirls: any = 0;
  foreignValue: any;
  
  studentAdmissionMethod: any[] = [];
  forFilterCourse: any[] = [];
  tempForeignData: any[] = [];
  filterFacultyList: any[] = [];
  departmentList: any[] = [];
  filterDiscipline: any[] = [];
  filterCountry: any = [];
  temp: any = [];
  facultyId: any;
  discipline: any;
  departmentId: any;
  countryId: any;
  programeLabel: string | undefined;
  pageData: number = 0;
  pageSize: number = 15;
  page: number = 1;
  StartLimit: number = 0;
  EndLimit: number = 15;
  count: number = 0;
  lockCS: boolean | undefined;
  subHeader!: string;
 
  facultyList: any[] = [];
  tempDepartment: any[] = [];
  variables1: any[] = [];
  variables2: any[] = [];
  variables3: any[] = [];
  variables4: any[] = [];
  variables5: any[] = [];
  variables6: any[] = []
  variables7: any[] = []
  variables8: any[] = []
  variables9: any[] = []
  variables10: any[] = []
  countryListFilter: any[] = []
  facultyListD: any[] = [];
  countryListArray: any[] = [];
  disciplineSubjectArray: any[] = [];
  tempDiscipline: any[] = [];
  filterDiscipline1: any[] = [];
  
  addOrEditIndex!: number;
  tempIndex: number | undefined;
  interval: any;
  filterDepartmentList: any[] = [];
  levelFilter:any[] = [];
  natureAppointmentListFilter:any[] = [];
  boardCatorgryFilter:any[] = [];
  boardNameFilter:any[] = [];
  faculty: any;
  department: any;
  girl_exclusive!: boolean;
  boy_exclusive!: boolean;
  draftCount: number = 0
  draftIndex: any;
  
  offCampusAvailable: any;
  onlineAvailable: any;
  
  offCampusList: Array<any> = [];
  offCampusArray: Array<any> = []
  aisheId: string | undefined;
  facultyName: any;
  departmentName: string = '';
  dropdownLevel:any;
  dropdownProgramName:any;
  dropdownBoardCategory: any;
  dropdownBoardName: any;
  filter:any
  tempStaff: any[] = [];
  dropdownDsg: any;
  dropdownJob: any;
  employeeName: any;
  dropdownAppointment: any;
  instituteName: string | undefined;
  constructor(
    private formBuilder: FormBuilder,
    public sharedService: SharedService, public dialog: MatDialog, public config: NgbPopoverConfig, public authService: AuthService, public viewportScroller: ViewportScroller,
    public localService:LocalserviceService) {
    // this.commonService.instructionDialog('foreign');
    config.placement = 'end';
    config.triggers = 'hover';
    if (this.localService.getData('loginMode') && this.localService.getData('aisheCode')) {
      this.aisheCode = this.localService.getData('aisheCode')
      this.instituteName = this.localService.getData('institutionName')
      this.getBoolean()
    }
    
  }
  ngOnInit() {
    this.modeList = this.sharedService.modeList
    // this.addOrUpdate = "Add";
    // this.showInCollege = JSON.parse(this.localService.getData('showInCollege'));
    // this.showInStandalone = JSON.parse(this.localService.getData('showInStandalone'));
    // this.showInUniv = JSON.parse(this.localService.getData('showInUniv'));
    // this.currentSurveyYear = parseInt(this.localService.getData('currentSurveyYear'))
    // this.aisheId = this.localService.getData('id');
    // this.loginMode = this.localService.getData('loginMode');
    this.showHideForm = this.formBuilder.group({
      isForeignEnrolled: false,
      approvedIntakeCapacityStudent: [null, []]
    })
    // this.sharedService.getBasicInfo.subscribe(res => {
    //   if (res != 0) {
    //     this.girl_exclusive = res.girl_exclusive;
    //     this.boy_exclusive = res.boysExclusive;
    //     if (!this.girl_exclusive && !this.boy_exclusive) {
    //       this.boy_exclusive = true;
    //       this.girl_exclusive = true
    //     }
    //   }
    // })
   
    // if (this.loginMode === 'U') {
    //   this.getBooleanValue();

    // } else {
    //   this.matLabel = 'Regular Mode'
    //   this.getLockTabStatus()
    //   this.getProgramme()
    //   this.getForeignData();


    // }
    // this.creatControl()
    // this.getForeignenrollmentavailable();
    // this.getCountry();
    // this.getStudentAdmissionMethod();
    // this.getApproveIntek();
    // this.getFacultyList();
    // this.getDepartment()

  }
  // getOffCentreList() {
  //   this.authService.getOffCentre1(this.aisheId, this.currentSurveyYear).subscribe(res => {
  //     if (res.data && res.data.length) {


  //       res.data.forEach(element => {
  //         if (element.offcampusAisheCode) {
  //           element['offcampusName'] = element['name']?.concat(" ", '(C-', element['offcampusAisheCode'], ')')
  //         } else {
  //           element['offcampusName'] = element.name
  //         }
  //       });
  //       this.offCampusArray = res.data;
  //       this.offCampusList = this.offCampusArray.slice()
  //     }



  //   }, err => {

  //   })
  // }
  // getLockTabStatus() {
  //   this.lockUnlock.getPageStatus().then((res: any) => {
  //     if (res && res.length) {
  //       res.forEach(element => {
  //         if (element.menu === 'Foreign Student Enrolment') {
  //           if(this.loginMode === 'U' && this.aisheId === '0104'){
  //             if(this.selectedIndex === 0){
  //               if(element.tabName === 'NA'){
  //                 this.disabledLockData = element.status;
  //               }
  //             }else{
  //               if(element.tabName === 'Distance Mode'){
  //                 this.disabledLockData = element.status;
  //               }
  //             }
  //           }else{
  //             if(element.tabName === 'NA'){
  //               this.disabledLockData = element.status;
  //             }
  //           }
  //         }
  //       });
  //     }
  //   })
  // }

  // getLockTabStatus() {
  //   this.lockUnlock.getPageStatus().then((res: any) => {
  //     if (res && res.length) {
  //       res.forEach((element) => {
  //         if (element.menu === "Foreign Student Enrolment") {
  //           if (this.showInCollege || this.showInStandalone) {
  //             if (this.matLabel === 'Regular Mode') {
  //               if (element.tabName === "NA") {
  //                 this.disabledLockData = element.status;
  //               }
  //             }
  //           }
  //           else {
  //             if (this.matLabel === 'Regular Mode') {
  //               if (element.tabName === 'NA') {
  //                 this.disabledLockData = element.status;
  //               }
  //             }
  //             if (this.matLabel === 'Distance Mode') {
  //               if (element.tabName === 'Distance Mode') {
  //                 this.disabledLockData = element.status;
  //               }
  //             }
  //             if (this.matLabel === 'Off Campus Centres') {
  //               if (element.tabName === 'Off Campus Centres') {
  //                 this.disabledLockData = element.status;
  //               }
  //             } if (this.matLabel === 'Online Mode') {
  //               if (element.tabName === 'Online Mode') {
  //                 this.disabledLockData = element.status;
  //               }
  //             }
  //           }
  //         }

  //       });

  //     }
  //   });
  // }
  // lockTab() {
  //   if (this.sharedService.draftData.length !== 0) {
  //     this.sharedService.showDraftError();
  //     return;
  //   } 
  //   if (this.matLabel === 'Regular Mode') {
  //     this.lockDataDialog('foreign_enrollment', 'Foreign Student Enrolment')
  //   }if(this.matLabel === 'Distance Mode'){
  //     this.lockDataDialog('foreign_enrollment_distance', 'Foreign Student Enrolment')
  //   }if(this.matLabel === 'Off Campus Centres'){
  //     this.lockDataDialog('foreign_enrollment_offcampus', 'Foreign Student Enrolment')
  //   }if(this.matLabel === 'Online Mode'){
  //     this.lockDataDialog('foreign_enrollment_online', 'Foreign Student Enrolment')
  //   }
    
  // }
  // lockDataDialog(tabConstant, tabName) {
  //   this.commonService.lockDialog().subscribe((result) => {
  //     if (result) {
  //       this.confirmLockTab(tabConstant, tabName);
  //     }
  //   });
  // }


  // confirmLockTab(tabConstant, tabName) {
  //   let payload = {
  //     aishecode: this.aisheId,
  //     constant: tabConstant,
  //     institutionType: this.loginMode,
  //     status: true,
  //     surveyYear: this.currentSurveyYear,
  //   };
  //   this.commonService.lockData(payload, tabName).then((res: any) => {
  //     if (res === 200) {
  //       this.showCard = false;
  //       this.getLockTabStatus();
  //     }
  //   });
  // }
  creatControl() {
    this.foreignSudentsEnrolled = this.formBuilder.group({
      offcampusId: ['', []],
      otherAdmissionMethod: ['', []],
      offCampusAisheCode:['',[]],
      countryName: ['', []],
      countryId: ['', [Validators.required]],
      facultyName: ['', []],
      facultyId: ['', []],
      deptId: ['', []],
      deptName: ['', []],
      levelName: ['', []],
      disciplineGroupDiscipline: ['', []],
      disciplineGroupCategory: ['', []],
      programme: ['', []],
      discipline: ['', []],
      total: [0, [Validators.required]],
      girls: [0, [Validators.required]],
      id: [0, []],
      courseId: [null, [Validators.required]],
      admissionMethodId: ['', [Validators.required]],
      studentsResidingInHostel: [0, [Validators.required]],
    })
    if (this.showInUniv) {
      // this.foreignSudentsEnrolled.get('deptId').setValidators(Validators.required);
      // this.foreignSudentsEnrolled.get('deptId').updateValueAndValidity();
      // this.foreignSudentsEnrolled.get('facultyId').setValidators(Validators.required);
      // this.foreignSudentsEnrolled.get('facultyId').updateValueAndValidity();
    } else {
      // this.foreignSudentsEnrolled.get('deptId').clearValidators();
      // this.foreignSudentsEnrolled.get('deptId').updateValueAndValidity();
      // this.foreignSudentsEnrolled.get('facultyId').clearValidators();
      // this.foreignSudentsEnrolled.get('facultyId').updateValueAndValidity();
    }
  }


  getForeignData() {
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
    this.authService.getForeignStudentList(payload).subscribe(res => {
      this.foreignData = []
      this.totalStudent = 0;
      this.totalGirls = 0;
      this.totalBoys = 0;
      if (res && res.length) {
        this.foreignData = res;
        this.foreignData.forEach(element => {
          element['disciplineName'] = `(${element.levelName})` + `(${element.programme})` + `(${element.disciplineGroupCategory})` + `(${element.disciplineGroupDiscipline})` + `${element.discipline}`

          element['draft'] = false
          this.totalStudent += element.total,
            this.totalGirls += element.girls,
            element['isUpdated'] = true
          if (!element.deptName) {
            element.deptName = 'No Department'
            element.deptId = 0
          } if (!element.facultyName) {
            element.facultyId = 0
            element.facultyName = 'No Faculty'
          }
          if(this.modeId === 4 && this.whetherOffCampus){
            element['offcampusId'] = element.offcampus?.id
            if (element.offcampus?.offcampusAisheCode) {
              element['offcampusName'] = element.offcampus['name']?.concat(" ", '(C-', element.offcampus['offcampusAisheCode'], ')')
            } else {
              element['offCampusName'] = element.offcampus?.name
            }
          }
        });

        this.totalBoys = this.totalStudent - this.totalGirls;
        this.tempForeignData = [...this.foreignData]
        let facultyF = []
        if (this.foreignData.length < 15) {
          facultyF = this.foreignData.slice(0, this.foreignData.length)
        } else {
          facultyF = this.foreignData.slice(0, 15)
        }
        this.pageData = facultyF.length;
        this.tempRegularProgrammeData = [...this.foreignData];
        this.tempStaff = [...this.tempRegularProgrammeData]
        this.removeDuplicateElement(this.foreignData)
        this.handlePageChange(this.pageF = 1)
        this.reload()
      }

      else {
        this.pageData = 0;
        this.pageSize = 15;
        this.page = 1;
        this.StartLimitF = 0;
        this.EndLimit = 15;
        this.pageDataF = 0;
        this.foreignData = [];
        this.tempForeignData = []
        this.tempRegularProgrammeData = []
        this.removeDuplicateElement(this.foreignData)
      }
    })
  }

  removeDuplicateElement(data: any) {
    var empArr: any = [];
    var facArr: any = [];
    var depArr: any = [];
    var designationArr: any = []
    var jobArr: any = []
    var levelArr : any[] = []
    var appointmentArr : any[] = []
    var boardCatogryArr : any[] =[]
    var boardNameArr : any[] =[]
    data.filter(function (item: any) {
      var emp = empArr.findIndex((x: any) => (x.name === item.name));
      var fac = facArr.findIndex((y: any) => (y.facultyName === item.facultyName))
      var dep = depArr.findIndex((z: any) => (z.departmentName === item.deptName))
      var designation = designationArr.findIndex((w: any) => w.designationName === item.designationName)
      var job = jobArr.findIndex((z: any) => (z.jobStatus === item.jobStatus))
      var appointment = appointmentArr.findIndex((z: any) => z.programme === item.programme)
      var level = levelArr.findIndex((z: any) => z.levelName === item.levelName)
      var boardCategory = boardCatogryArr.findIndex((z: any) => z.boardCategory === item.disciplineGroupCategory)
      var boardName = boardNameArr.findIndex((z: any) => z.broadDisciplineGroup === item.disciplineGroupDiscipline)
     
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
        appointmentArr.push({ programme: item.programme});
      }
      if (level <= -1) {
        levelArr.push({ levelName: item.levelName});

      }
      if (boardCategory <= -1) {
        boardCatogryArr.push({ boardCategory: item.disciplineGroupCategory});

      }
      if (boardName <= -1) {
        boardNameArr.push({ boardName: item.disciplineGroupDiscipline});

      }
      return null;
    });
    // this.variables3 = []
    // this.filterEmployeeList = []
    // this.variables3 = [...empArr]
    // this.filterEmployeeList = this.variables3.slice()
    this.variables4 = []
    this.filterFacultyList = []
    this.variables4 = [...facArr]
    this.filterFacultyList = this.variables4.slice()
    this.variables5 = []
    this.filterDepartmentList = []
    this.variables5 = [...depArr]
    // this.filterDepartmentList = this.removeDuplicates(this.variables5, '5');
    this.filterDepartmentList = this.variables5.slice()
    // this.variables6 = []
    // this.filterDesignation = []
    // this.variables6 = [...designationArr]
    // this.filterDesignation = this.variables6.slice()
    // this.jobStatusListFilter = [...jobArr]


    

    this.variables7 = []
    this.natureAppointmentListFilter = []
    this.variables7 = [...appointmentArr]
    this.natureAppointmentListFilter = this.variables7

    this.variables8 = []
    this.boardCatorgryFilter = []
    this.variables8 = [...boardCatogryArr]
    this.boardCatorgryFilter = this.variables8.slice()
   
    this.variables9 = []
    this.boardNameFilter = []
    this.variables9 = [...boardNameArr]
    this.boardNameFilter = this.removeDuplicates(this.variables9, '9');

    this.variables10 = []
    this.levelFilter = []
    this.variables10 = [...levelArr]
    this.levelFilter =this.variables10.slice();
    this.handlePageChange(this.page=1)
  }

  removeDuplicates(arr: any[], key:any): any{
    let seen = new Set();
   
    if(key == '8'){
      return arr.filter(item => {
      let duplicate = seen.has(item.boardCategory);
      seen.add(item.boardCategory);
      return !duplicate;
    });
    }
    else if(key == '9'){
      return arr.filter(item => {
      let duplicate = seen.has(item.boardName);
      seen.add(item.boardName);
      return !duplicate;
    });
    }
  }
  


  clearTimeOut() {
    if (this.interval) {
      clearInterval(this.interval);
    }
  }
  openPopUp() {
    if (this.dialog.openDialogs.length == 0) {
      let dialogRef = this.dialog.open(ConfirmDialogComponent, {
        disableClose: true,
        data: {
          message: "Please save your data?",
          buttonText: {
            ok: "Yes",
            cancel: "No",
          },
        },
      });

      dialogRef.afterClosed().subscribe((result) => {
        if (result) {
          // this.saveData()
        } else {
          if (this.interval) {
            clearInterval(this.interval);
          }
          // this.setTimer()
        }
      })
    }
  }


  reload() {
    this.filter='';
    this.facultyName = '';
    this.departmentName = '';
    this.dropdownLevel = '';
    this.dropdownProgramName = '';
    this.dropdownBoardCategory = "";
    this.dropdownBoardName = "";
    
    // this.countryId = ''
    // this.discipline = null,
    //   this.faculty = '',
    //   this.department = '',
    //   this.filterCountry = [];
    this.foreignData = [];
    this.foreignData = [...this.tempForeignData];
    this.removeDuplicateElement(this.foreignData);
    this.handlePageChange(this.page = 1)
  }


  handlePageChange(event: number) {
    this.page = event
    this.StartLimitF = ((this.page - 1) * this.pageSize),
      this.EndLimit = this.StartLimitF + this.pageSize
    var a = Math.ceil(this.foreignData.length / this.pageSize);
    if (a === event) {
      this.pageDataF = Math.min(this.StartLimitF + this.pageSize, this.foreignData.length);
    } else {
      this.pageDataF = Math.min(this.StartLimitF + this.pageSize, this.foreignData.length - 1);
    }
    this.summaryTable()
  }

  summaryTable() {
    this.totalStudent = 0;
    this.totalGirls = 0;
    this.totalBoys = 0;
    for (const c of this.foreignData) {
      this.totalStudent += c.total,
        this.totalGirls += c.girls
    }
    this.totalBoys = this.totalStudent - this.totalGirls;
  }

  ngOnDestroy() {
    if (this.interval) {
      this.clearTimeOut()
    }
    this.localService.removeData('selectedIndex')
  }
  // onKeypressEvent(event, inputLength) {
  //   if (event.target.value.length + 1 > inputLength) {
  //     event.preventDefault();
  //   }
  // }

  handleInput(event: KeyboardEvent): void {
    event.stopPropagation();
  }
  viewProgram(data:any) {
    // data['admissionMethod'] = this.studentAdmissionMethod.find(ele => ele.id === data.admissionMethodId).method
    data['program'] = false;
    data['staff'] = false;
    data['foreign'] = true;
    data['header'] = 'Foreign Student Enrolment';
    data['subHeader'] = this.subHeader
    this.sharedService.viewProgram(data)
  }
  // showForm(value) {
  //   this.addOrUpdate = 'Add';
  //   this.creatControl()
  //   this.showCard = value
  //   this.addOrEditIndex = null;
  //   this.tempIndex = null
  // }
  // close() {
  //   this.addOrUpdate = 'Add';
  //   this.creatControl();
  //   this.showCard = false;
  //   this.addOrEditIndex = null;
  //   this.tempIndex = null
  // }
  onChange(event:any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page)
  }
  async updateResults() {
    this.foreignData = []
    this.foreignData = this.searchByValue(this.tempForeignData);
    this.handlePageChange(this.page = 1)
  }

  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {

        return (item.facultyName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.deptName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.levelName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.programme.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.disciplineGroupCategory.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.disciplineGroupDiscipline.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.discipline.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.countryName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }


  allFilter(value: any) {
    let depArr1:any=[]
    let depArr2:any=[]
    let depArr3:any=[]
    let depArr4:any=[]
    this.filter={}
    if (this.facultyName){
      this.filter['facultyName']=this.facultyName
      this.foreignData = this.tempStaff.filter((i:any) =>
        Object.entries(this.filter).every(([k, v]) => 
         i[k] === v
     )
       )
        let fac:any;
        if (this.facultyName === 0) {
          fac = 'No Faculty'
        } else {
          fac = this.facultyName
        }
        if (this.showInUniv) {
         this.variables5 = this.foreignData.filter(ele => ele.facultyName === fac)
          this.variables5.filter(function (item) {
            var dep = depArr1.findIndex((z:any) => 
              (z.departmentName.toLowerCase() === item.deptName.toLowerCase()))
            if (dep <= -1) {
              depArr1.push({ departmentName: item.deptName });
            }
  
  
            return null;
          });
          let index = depArr1.findIndex((ele:any) => ele.departmentName === 'No Department')
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
    
    
    if (this.dropdownLevel){
      this.filter['levelName']=this.dropdownLevel
      
        let lav:any;
        if (this.dropdownLevel === 0) {
          lav = 'No Level'
        } else {
          lav = this.dropdownLevel
        }
        if (this.showInUniv) {
         this.variables10= this.tempStaff.filter(ele => 
          ele.levelName === lav
        )
          this.variables10.filter(function (item) {
            var per = depArr3.findIndex((z:any) => 
              (z.programme.toLowerCase() === item.programme.toLowerCase())
          
          )
            if (per <= -1) {depArr3.push({ programme: item.programme });
            }
  
  
            return null;
          });
          let index = depArr3.findIndex((ele:any) => ele.programme === 'No Programe')
          if (index !== -1) {
            depArr3.splice(index, 1);
            depArr3.unshift({ programme: 'No Programe' });
          }
          this.variables7 = []
          this.natureAppointmentListFilter = []
          this.variables7 = [...depArr3]
          this.natureAppointmentListFilter = this.variables7.slice()
          
        }
  }

if (this.dropdownProgramName){
      this.filter['programme']=this.dropdownProgramName
        let perg:any;
        if (this.dropdownProgramName === 0) {
          perg = 'No Programe'
        } else {
          perg = this.dropdownProgramName
        }
        if (this.showInUniv) {
         this.variables7= this.tempStaff.filter(ele => ele.programme === perg)
          this.variables7.filter(function (item) {
            var per = depArr4.findIndex((z:any) => (z.broadCategoryName === item.disciplineGroupCategory))
            if (per <= -1) {
              depArr4.push({ boardCategory: item.disciplineGroupCategory });
            }
  
  
            return null;
          });
          let index = depArr4.findIndex((ele:any) => ele.boardCategory === 'No Category')
          if (index !== -1) {
            depArr4.splice(index, 1);
            depArr4.unshift({ boardCategory: 'No Category' });
          }
          this.variables8 = []
          this.boardCatorgryFilter = []
          this.variables8 = [...depArr4]
          this.boardCatorgryFilter = this.removeDuplicates(this.variables8, '8');
        }
    }


    if (this.departmentName){
      this.filter['deptName']=this.departmentName
    }if (this.dropdownDsg){
      this.filter['designationName']=this.dropdownDsg
    }if (this.dropdownJob){
      this.filter['jobStatus']=this.dropdownJob
    }if (this.employeeName){
      this.filter['name']=this.employeeName
    }
    if (this.dropdownAppointment){
      this.filter['appointmentNature'] = this.dropdownAppointment
    }
    if (this.dropdownBoardCategory){
      this.filter['disciplineGroupCategory']=this.dropdownBoardCategory
      this.variables9 = this.tempStaff.filter((ele:any) => 
        ele.disciplineGroupCategory === this.dropdownBoardCategory
    )
          this.variables9.filter(function (item) {
            var dep = depArr2.findIndex((z:any) => 
              (z.boardName === item.disciplineGroupDiscipline)
          )
            if (dep <= -1) {
              depArr2.push({ boardName: item.disciplineGroupDiscipline

               });
            }
  
  
            return null;
          });
          // let index = depArr1.findIndex((ele:any) => ele.departmentName === 'No Department')
          // if (index !== -1) {
          //   depArr1.splice(index, 1);
          //   depArr1.unshift({ departmentName: 'No Department' });
          // }
          this.variables9 = []
          this.boardNameFilter = []
          this.variables9 = [...depArr2]
          this.boardNameFilter = this.removeDuplicates(this.variables9, '9');
    }
    if (this.dropdownBoardName){
      this.filter['disciplineGroupDiscipline']=this.dropdownBoardName
    }



    this.foreignData = this.tempStaff.filter((i:any) =>
     Object.entries(this.filter).every(([k, v]) => 
      i[k] === v
  )
    )
     this.handlePageChange(this.page = 1)
  }

  getBoolean() {
    this.mode=this.modeId
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
   if(loginMode === 'U'){
    this.showInUniv=true;
    this.showInCollege=false;
    this.showInStandalone=false
   }else if(loginMode === 'S'){
    this.showInUniv=false;
    this.showInCollege=false;
    this.showInStandalone=true
   }else{
    this.showInUniv=false;
    this.showInCollege=true;
    this.showInStandalone=false
   }
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId
    }
    
    this.authService.booleanValue(payload).subscribe(res => {
      this.whetherOnlineProg = res.isOfferOnlineProgram
      this.whetherProgThroughPrivate = res.programmeThroughPrivateExternal
      this.whetherOffCampus = res.offerCampusAvailable
      this.whetherProgThroughDistance = res.offerDistanceProgramme
      this.getForeignData()
    }, err => {

    })
  }
}


