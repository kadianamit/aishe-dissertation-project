import { ViewportScroller } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-programe',
  templateUrl: './programe.component.html',
  styleUrls: ['./programe.component.scss']
})
export class ProgrameComponent implements OnInit {
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
  mode: any
  teachingStaffs: Array<any> = []
  filterDepartmentList: any[] = []
  facultyName: any;
  noRecords: boolean = true;
  StartLimit: number = 0;
  EndLimit: number = 50;
  pageSize: any = 50;
  page: number = 1;
  pageData: number = 0
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
  dropdownName: any;
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

  jobStatusListFilter: any[] = [];
  programmeList: any[] = [];
  levelFilter: any[] = [];
  boardCategoryFilter: any[] = [];
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
    public viewportScroller: ViewportScroller,public localService:LocalserviceService
  ) {
    this.modeList = this.sharedService.modeList
    if (this.localService.getData('loginMode') && this.localService.getData('aisheCode')) {
      this.aisheCode = this.localService.getData('aisheCode')
      this.instituteName = this.localService.getData('institutionName')
      this.getBoolean()
    }
  }

  ngOnInit() {
  
    this.getIndianLanguage();
  }
  getIndianLanguage() {
    this.authService.getIndianLanguageList().subscribe(res => {
      if (res.length > 0) {
        this.languageList = res
      } else {
        this.languageList = [];
      }

    }, err => {

    })
  }
  getBoolean() {
    this.mode = this.modeId
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    if (loginMode === 'U') {
      this.showInUniv = true;
      this.showInCollege = false;
      this.showInStandalone = false
    } else if (loginMode === 'S') {
      this.showInUniv = false;
      this.showInCollege = false;
      this.showInStandalone = true
    } else {
      this.showInUniv = false;
      this.showInCollege = true;
      this.showInStandalone = false
    }
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      loginMode: loginMode.toUpperCase(),
      modeId: this.modeId
    }
    if (this.loginMode === 'S' || this.loginMode === 'C') {
      if (this.approvingUnivList.length === 0) {
        this.getUniversity();

      } if (this.statuatoryBodyList.length === 0) {
        this.getStatuatoryBody();

      }
    }
    this.authService.booleanValue(payload).subscribe(res => {
      this.whetherOnlineProg = res.isOfferOnlineProgram
      this.whetherProgThroughPrivate = res.programmeThroughPrivateExternal
      this.whetherOffCampus = res.offerCampusAvailable
      this.whetherProgThroughDistance = res.offerDistanceProgramme
      this.getProgThroughFaculty()
    }, err => {

    })
  }
  getUniversity() {
    let payload = {
      currentSurveyYear: this.currentSurveyYear,
      stateCode: 'ALL'
    }
    this.authService.getUniversity(payload).subscribe(
      (res) => {
        this.approvingUnivList = res;

      },
      (err) => { }
    );
  }
  getStatuatoryBody() {
    this.authService.getStatuatoryBodyProgram().subscribe(
      (res) => {
        this.statuatoryBodyList = res;
      },
      (err) => { }
    );
  }



  getProgThroughFaculty() {
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode.toUpperCase(),
      latestSurveyYear: this.latestSurveyYear,
      currentSurveyYear: this.currentSurveyYear,
      instituteType: loginMode.toUpperCase(),
      modeId: this.modeId,
      isOnod:true
    }
    this.authService.getProgrammeList(payload).subscribe(
      (res) => {
        this.regularProgrammeData = [];
        if (res.data.length && res.data) {
          this.regularProgrammeData = res.data;



          this.regularProgrammeData.forEach((ele: any) => {
            ele['programName'] = ele.programmeId?.programme
            ele['broadCategoryId'] = ele.broadDisciplineGroupCategoryId?.id
            ele['broadCategoryName'] = ele.broadDisciplineGroupCategoryId?.category
            ele['broadDisciplineGroupId'] = ele.broadDisciplineGroup?.id
            ele['broadDisciplineGroupName'] = ele.broadDisciplineGroup?.discipline
            ele['levelid'] = ele.levelId?.id
            ele['levelName'] = ele.levelId?.level
            ele['countryId'] = ele.country?.id
            ele['jobStatusId'] = ele.jobStatus?.id
            ele['jobStatus'] = ele.jobStatus?.name
            ele['designationId'] = ele.designation?.designationId
            ele['designationName'] = ele.designation?.designationName
            ele['genderId'] = ele.gender?.id
            ele['teacherSelectionModeId'] = ele.teacherSelectionMode?.id
            ele['religiousCommunityId'] = ele.religiousCommunity?.id
            ele['socialCategoryId'] = ele.socialCategory?.id
            ele['highQualificationId'] = ele.highQualification?.id
            ele['programid'] = ele.programmeId?.id
            if (ele.additionalQualification) {
              ele['additionalQualificationId'] = ele.additionalQualification?.id
            } else {
              ele['additionalQualificationId'] = null
            }
            if (ele.surveyYear === this.currentSurveyYear) {
              ele['isUpdated'] = true
              ele['latestSurveyYear'] = this.currentSurveyYear
            } else {
              ele['isUpdated'] = false
              ele['latestSurveyYear'] = ele.surveyYear
            }
            if (!ele.faculty) {
              ele['facultyId'] = 0;
              if (!ele.facultyName) {
                ele.facultyName = 'No Faculty'
              } else {
                ele.facultyName = ele.facultyName,
                  ele['facultyId'] = 0
              }

            } else {
              ele['facultyName'] = ele.faculty.facultyName;
              ele['facultyId'] = ele.faculty.id
            }
            if (!ele.department) {
              ele['departmentId'] = 0;
              if (!ele.departmentName) {
                ele.departmentName = 'No Department'
              } else {
                ele.departmentName = ele.departmentName,
                  ele['departmentId'] = 0
              }

            } else {
              ele['departmentName'] = ele.department.departmentName;
              ele['departmentId'] = ele.department.departmentId
            }


          })



          this.tempRegularProgrammeData = [...this.regularProgrammeData];
          this.removeDuplicateElement(this.regularProgrammeData)
          this.handlePageChange(this.pageF = 1)
        }
        else {
          this.pageDataF = 0;
          this.regularProgrammeData = [];
          this.tempRegularProgrammeData = [];
          this.perPageData = [];
        }
      },
      (err) => { }
    );
  }

  handlePageChange(event: any) {
    this.pageF = event
    this.StartLimitF = ((this.pageF - 1) * Number(this.pageSizeF)),
      this.EndLimitF = this.StartLimitF + Number(this.pageSizeF)
    var a = Math.ceil(this.regularProgrammeData.length / Number(this.pageSizeF));
    if (a === event) {
      this.pageDataF = Math.min(this.StartLimitF + Number(this.pageSizeF), this.regularProgrammeData.length);
    } else {
      this.pageDataF = Math.min(this.StartLimitF + Number(this.pageSizeF), this.regularProgrammeData.length - 1);
    }

  }

  viewProgram(data: any) {
    let a = null
    let b = null
    if (data.approvingUniversityId && data.approvingStatutoryBodyId) {
      a = this.approvingUnivList.find(ele => ele.id === data.approvingUniversityId)
      b = this.statuatoryBodyList.find(ele => ele.id === data.approvingStatutoryBodyId)
    }
    data['approvingUniversity'] = a
    data['approvingStatutory'] = b
    data['subHeader'] = this.sharedService.modeList.find(e => e.id === this.modeId)?.name;
    data['program'] = true;
    data['staff'] = false;
    data['foreign'] = false;
    data['header'] = 'Details of Programme';
    data['whetherOffCampus'] = this.whetherOffCampus
    data['whetherOnlineProg'] = this.whetherOnlineProg
    data['showInCollege'] = this.showInCollege,
      data['showInUniv'] = this.showInUniv,
      data['showInStandalone'] = this.showInStandalone
    if (data.mediumOfInstructionIndianLanguageId) {
      const languageNames = data.mediumOfInstructionIndianLanguageId.map((id: any) => {
        const language = this.languageList.find(lang => lang.id === id);
        return language ? language.name : null;
      });
      data['mediumOfInstructionIndianLanguageName'] = languageNames;
    }
    data['modeId'] = this.modeId
    this.sharedService.viewProgram(data)
  }


  onChange(event: any) {
    this.pageSizeF = parseInt(event);
    this.handlePageChange(this.pageF = 1)
  }
  async updateResults() {
    this.regularProgrammeData = []
    this.regularProgrammeData = this.searchByValue(this.tempRegularProgrammeData);
    this.handlePageChange(this.pageF = 1)
  }

  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        // item.name.trim();
        return (item.faculty?.facultyName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.department?.departmentName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.levelId?.level.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.programmeId?.programme.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.broadDisciplineGroupCategoryId?.category.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.broadDisciplineGroup?.discipline.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.discipline?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.durationMonth?.toString().includes(this.searchText.trim().toLowerCase()))
          || (item.durationYear?.toString().includes(this.searchText.trim().toLowerCase()))
          || (item.intake?.toString().includes(this.searchText.trim().toLowerCase()))
          || (item.yearOfStart?.toString().includes(this.searchText.trim().toLowerCase()))
          || (item.offcampusName?.toString().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }



  allFilter(value: any) {
    let depArr1: any = []
    let depArr2: any = []
    let depArr3: any = []
    let depArr4: any = []
    this.filter = {}
    if (this.facultyName) {
      this.filter['facultyName'] = this.facultyName
      this.regularProgrammeData = this.tempRegularProgrammeData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )
      let fac: any;
      if (this.facultyName === 0) {
        fac = 'No Faculty'
      } else {
        fac = this.facultyName
      }
      if (this.showInUniv) {
        this.variables5 = this.regularProgrammeData.filter(ele => ele.facultyName === fac)
        this.variables5.filter(function (item) {
          var dep = depArr1.findIndex((z: any) => (z.departmentName.toLowerCase() === item.departmentName.toLowerCase()))
          if (dep <= -1) {
            depArr1.push({ departmentName: item.departmentName });
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
    if (this.dropdownLevel) {
      this.filter['levelName'] = this.dropdownLevel
      this.regularProgrammeData = this.tempRegularProgrammeData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )
      this.variables10 = this.regularProgrammeData.filter(ele => ele.levelName === this.dropdownLevel)
      this.variables10.filter(function (item) {
        var per = depArr3.findIndex((z: any) => (z.programName.toLowerCase() === item.programName.toLowerCase()))
        if (per <= -1) {
          depArr3.push({ programName: item.programName });
        }
        return null;
      });

      this.variables7 = []
      this.programmeList = []
      this.variables7 = [...depArr3]
      this.programmeList = this.variables7.slice()


    }

    if (this.dropdownProgramName) {
      this.filter['programName'] = this.dropdownProgramName
      this.regularProgrammeData = this.tempRegularProgrammeData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )
        this.variables8 = this.regularProgrammeData.filter(ele => ele.programName === this.dropdownProgramName)
        this.variables8.filter(function (item) {
          var per = depArr4.findIndex((z: any) => (z.boardCategory === item.broadCategoryName))
          if (per <= -1) {
            depArr4.push({ boardCategory: item.broadCategoryName });
          }
          return null;
        });
        this.variables8 = []
        this.boardCategoryFilter = []
        this.variables8 = [...depArr4]
        this.boardCategoryFilter = this.variables8.slice()
    }


    if (this.departmentName) {
      this.filter['departmentName'] = this.departmentName
      this.regularProgrammeData = this.tempRegularProgrammeData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )
    } 
    
    if (this.dropdownBoardCategory) {
      this.filter['broadCategoryName'] = this.dropdownBoardCategory
      this.regularProgrammeData = this.tempRegularProgrammeData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )
      this.variables9 = this.regularProgrammeData.filter((ele: any) => ele.broadCategoryName === this.dropdownBoardCategory)
      this.variables9.filter(function (item) {
        var dep = depArr3.findIndex((z: any) => (z.boardName === item.broadDisciplineGroupName))
        if (dep <= -1) {
          depArr2.push({
            boardName: item.broadDisciplineGroupName

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
      this.filter['broadDisciplineGroupName'] = this.dropdownBoardName
      this.regularProgrammeData = this.tempRegularProgrammeData.filter((i: any) =>
        Object.entries(this.filter).every(([k, v]) => i[k] === v)
      )
    }
    
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
    this.regularProgrammeData = [...this.tempRegularProgrammeData]
    this.handlePageChange(this.page = 1)
  }


  removeDuplicateElement(data: any) {
    var facArr: any = [];
    var depArr: any = [];
    var levelArr: any[] = []
    var appointmentArr: any[] = []
    var boardCatogryArr: any[] = []
    var boardNameArr: any[] = []
    data.filter(function (item: any) {
      var fac = facArr.findIndex((y: any) => (y.facultyName === item.facultyName))
      var dep = depArr.findIndex((z: any) => (z.departmentName === item.departmentName))
      var appointment = appointmentArr.findIndex((z: any) => z.programName === item.programName)
      var level = levelArr.findIndex((z: any) => z.levelName === item.levelName)
      var boardCategory = boardCatogryArr.findIndex((z: any) => z.broadCategory === item.broadCategoryName)
      var boardName = boardNameArr.findIndex((z: any) => z.broadDisciplineGroup === item.broadDisciplineGroupName)

     if (fac <= -1) {
        facArr.push({ facultyName: item.facultyName });
      } if (dep <= -1) {
        depArr.push({ departmentName: item.departmentName });
      } 
      
      if (appointment <= -1) {
        appointmentArr.push({ programName: item.programName });

      }
      if (level <= -1) {
        levelArr.push({ levelName: item.levelName });

      }
      if (boardCategory <= -1) {
        boardCatogryArr.push({ boardCategory: item.broadCategoryName });

      }
      if (boardName <= -1) {
        boardNameArr.push({ boardName: item.broadDisciplineGroupName });

      }
      return null;
    });
    this.variables4 = []
    this.filterFacultyList = []
    this.variables4 = [...facArr]
    this.filterFacultyList = this.variables4.slice()
    this.variables5 = []
    this.filterDepartmentList = []
    this.variables5 = [...depArr]
    this.filterDepartmentList = this.variables5.slice()
   
    this.variables7 = []
    this.programmeList = []
    this.variables7 = [...appointmentArr]

    this.programmeList = this.variables7
    this.variables8 = []
    this.boardCategoryFilter = []
    this.variables8 = [...boardCatogryArr]
    this.boardCategoryFilter = this.removeDuplicates(this.variables8, '8');

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



