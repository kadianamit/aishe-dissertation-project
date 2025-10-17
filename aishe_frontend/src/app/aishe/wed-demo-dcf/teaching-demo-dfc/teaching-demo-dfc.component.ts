import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { GetService } from 'src/app/service/get/get.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-teaching-demo-dfc',
  templateUrl: './teaching-demo-dfc.component.html',
  styleUrls: ['./teaching-demo-dfc.component.scss']
})
export class TeachingDemoDfcComponent implements OnInit {

  teachingStaffs: Array<any> = []
  showInCollege: any;
  showInStandalone: any;
  showInUniv: any;
  filterDepartmentList: any[] = []
  facultyName: any;
  noRecords: boolean = true;
  tempStaff: any = [];
  StartLimit: number = 0;
  EndLimit: number = 50;
  pageSize: any = 50;
  page: number = 1;
  pageData: number = 0
  currentSurveyYear: any = 2022
  filterFaculty: any;
  filterDepartment: any;
  dropdownDsg: any;
  dropdownAppointment: any;
  tempDesignation: any[] = [];
  filterDesignation: any[] = [];
  filterFacultyList: any[] = [];
  filterEmployeeList: any[] = [];
  dropdownJob: any;
  boardGroupFilter: any[] = []
  boardCategoryFilter: any[] = []
  latestSurveyYear: any = 2021;
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

  jobStatusListFilter: any[] = [];
  natureAppointmentListFilter: any[] = [];
  boardCatorgryFilter: any[] = [];
  boardNameFilter: any[] = [];
  searchText: any
  loginMode: any;
  userId: any;
  aisheCode: any = "";
  employeeName: string = '';
  departmentName: string = '';
  nirfCat:string=''
  filter: any
  dropdownBoardCategory: any;
  dropdownBoardName: any;
  nirfCategoryArray: Array<any>=[];
  nirfCategoryList: Array<any>=[];
  instituteName:string | undefined
  instituteDetails: string | undefined;
  constructor(private getService: GetService, private formBuilder: FormBuilder, public sharedService: SharedService,public localService:LocalserviceService) {
    if (this.localService.getData('loginMode') && this.localService.getData('aisheCode')) {
      this.aisheCode = this.localService.getData('aisheCode')
      this.instituteName = this.localService.getData('institutionName')
      // this.instituteDetails = this.instituteName.concat(this.aisheCode)
      this.getTeashingStaff()
    }
   }

  ngOnInit(): void {
    this.getNIRFList();
    
  }
  getNIRFList() {
    this.getService.getNIRFCategoryPrivate().subscribe(res => {
      this.nirfCategoryArray = res
      this.nirfCategoryList = this.nirfCategoryArray.slice()
    }, err => {

    })
  }

  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1);
  }
  async updateResults() {
    this.teachingStaffs = []
    this.teachingStaffs = this.searchByValue(this.tempStaff);
    this.handlePageChange(this.page = 1)
  }

  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        // item.name.trim();
        return (item.facultyName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.departmentName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.name.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.jobStatus.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.dateOfChangeInJobStatusString?.includes(this.searchText.trim().toLowerCase()))
          || (item.designationName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }

  removeDuplicateElement(data: any) {
    var empArr: any = [];
    var facArr: any = [];
    var depArr: any = [];
    var designationArr: any = []
    var jobArr: any = []
    var appointmentArr: any[] = []
    var boardCatogryArr: any[] = []
    var boardNameArr: any[] = []
    data.filter(function (item: any) {
      var emp = empArr.findIndex((x: any) => (x.name === item.name));
      var fac = facArr.findIndex((y: any) => (y.facultyName === item.facultyName))
      var dep = depArr.findIndex((z: any) => (z.departmentName === item.departmentName))
      var designation = designationArr.findIndex((w: any) => w.designationName === item.designationName)
      var job = jobArr.findIndex((z: any) => (z.jobStatus === item.jobStatus))
      var appointment = appointmentArr.findIndex((z: any) => z.appointmentName === item.appointmentNature)
      var boardCategory = boardCatogryArr.findIndex((z: any) => z.broadCategory === item.broadCategoryName)
      var boardName = boardNameArr.findIndex((z: any) => z.broadDisciplineGroup === item.broadDisciplineGroupName)

      if (emp <= -1) {
        empArr.push({ name: item.name });
      } if (fac <= -1) {
        facArr.push({ facultyName: item.facultyName });
      } if (dep <= -1) {
        depArr.push({ departmentName: item.departmentName });
      } if (designation <= -1) {
        designationArr.push({ designationName: item.designationName });
      }
      // } if (job <= -1) {
      //   jobArr.push({ jobStatus: item.jobStatus });

      // }
      if (appointment <= -1) {
        appointmentArr.push({ appointmentName: item.appointmentNature });

      }
      if (boardCategory <= -1) {
        boardCatogryArr.push({ boardCategory: item.broadCategoryName });

      }
      if (boardName <= -1) {
        boardNameArr.push({ boardName: item.broadDisciplineGroupName });

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
    this.natureAppointmentListFilter = []
    this.variables7 = [...appointmentArr]

    this.natureAppointmentListFilter = this.removeDuplicates(this.variables7, '7');



    this.variables8 = []
    this.boardCatorgryFilter = []
    this.variables8 = [...boardCatogryArr]

    this.boardCatorgryFilter = this.removeDuplicates(this.variables8, '8');


    this.variables9 = []
    this.boardNameFilter = []
    this.variables9 = [...boardNameArr]

    this.boardNameFilter = this.removeDuplicates(this.variables9, '9');
    this.handlePageChange(this.page = 1)
  }

  removeDuplicates(arr: any[], key: any): any {
    let seen = new Set();


    if (key == '7') {
      return arr.filter(item => {
        let duplicate = seen.has(item.appointmentName);
        seen.add(item.appointmentName);
        return !duplicate;
      });
    }
    else if (key == '8') {
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






  allFilter(value: any) {
    let depArr1: any = []
    let depArr2: any = []
    this.filter = {}
    if (this.facultyName) {
      this.filter['facultyName'] = this.facultyName

      let fac: any = '';
      if (this.facultyName === 0) {
        fac = 'No Faculty'
      } else {
        fac = this.facultyName
      }
      if (this.showInUniv) {

        this.variables5 = this.tempStaff.filter((ele: any) => ele.facultyName === fac)
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




    } if (this.departmentName) {
      this.filter['departmentName'] = this.departmentName
    } if (this.dropdownDsg) {
      this.filter['designationName'] = this.dropdownDsg
    } if (this.dropdownJob) {
      this.filter['jobStatus'] = this.dropdownJob
    } if (this.employeeName) {
      this.filter['name'] = this.employeeName
    }
    if (this.dropdownAppointment) {
      this.filter['appointmentNature'] = this.dropdownAppointment
    }
    if (this.dropdownBoardCategory) {
      this.filter['broadCategoryName'] = this.dropdownBoardCategory
      this.variables9 = this.tempStaff.filter((ele: any) => ele.broadCategoryName === this.dropdownBoardCategory)
      this.variables9.filter(function (item) {
        var dep = depArr2.findIndex((z: any) => (z.boardName.toLowerCase() === item.broadDisciplineGroupName.toLowerCase()))
        if (dep <= -1) {
          depArr2.push({
            boardName: item.broadDisciplineGroupName

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
      this.boardNameFilter = this.variables9.slice()
    }
    if (this.dropdownBoardName) {
      this.filter['broadDisciplineGroupName'] = this.dropdownBoardName
    }

    if(this.nirfCat){
      this.filter['nirfCategoryName'] = this.nirfCat
    }



    this.teachingStaffs = this.tempStaff.filter((i: any) =>
      Object.entries(this.filter).every(([k, v]) => i[k] === v)
    )
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
    this.nirfCat=''
    this.teachingStaffs = [...this.tempStaff]
    this.removeDuplicateElement(this.tempStaff);
    this.handlePageChange(this.page = 1)
  }

  handlePageChange(event: any) {
    this.page = event;
    this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
      this.EndLimit = this.StartLimit + Number(this.pageSize);
    var a = Math.ceil(this.teachingStaffs.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.teachingStaffs.length);
    } else {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.teachingStaffs.length - 1);
    }

  }



  getTeashingStaff() {
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
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
      instituteType: loginMode?.toUpperCase(),
      aisheCode: this.aisheCode?.toUpperCase(),
      currentSurveyYear: 2022,
      latestSurveyYear: 2021,
      isOnod:true
    }
    return new Promise<void>((resolve, reject) => {
      this.getService.getTeashingStaff(payload).subscribe(res => {
        if (res.data && res.data.length) {
          this.teachingStaffs = [];
          this.teachingStaffs = res.data;
          this.teachingStaffs.forEach((ele: any) => {
            ele['nirfCategoryName'] = ele.nirfCategoryName?ele.nirfCategoryName:'Other'
            ele['appointmentNature'] = ele.appointmentNature?.name
            ele['broadCategoryId'] = ele.broadCategory?.id
            ele['broadCategoryName'] = ele.broadCategory?.category
            ele['broadDisciplineGroupId'] = ele.broadDisciplineGroup?.id
            ele['broadDisciplineGroupName'] = ele.broadDisciplineGroup?.discipline
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
            ele['appointmentNatureId'] = ele.appointmentNature?.id
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
            // if (ele.department) {
            //   ele.departmentId = 0;
            //   ele.departmentName = 'No Department'
            // }
            if (ele.whetherVocationalCourse === null || ele.whetherVocationalCourse === "") {
              ele.whetherVocationalCourse = false
            }

            if (ele.dateOfJoiningInstitutionString) {
              var dateAsString1 = ele.dateOfJoiningInstitutionString;
              let split_dateAsString1 = dateAsString1.split('/')
              let final_format1 = new Date(`${split_dateAsString1[2]}-${split_dateAsString1[1]}-${split_dateAsString1[0]}`);
              //this.teachingStaffInformation.controls.dateOfJoining.setValue(final_format1);
              ele['minDOChangeStatus'] = final_format1

            }
            if (ele.dateOfChangeInJobStatusString) {
              var dateAsString2 = ele.dateOfChangeInJobStatusString;
              let split_dateAsString2 = dateAsString2.split('/')
              let final_format2 = new Date(`${split_dateAsString2[2]}-${split_dateAsString2[1]}-${split_dateAsString2[0]}`);
              ele.dateOfChangeInJobStatus = final_format2

            }
            if (ele.dateOfLeavingString) {
              var dateAsString3 = ele.dateOfLeavingString;
              let split_dateAsString3 = dateAsString3.split('/')
              let final_format3 = new Date(`${split_dateAsString3[2]}-${split_dateAsString3[1]}-${split_dateAsString3[0]}`);
              ele.dateOfLeaving = final_format3

            }
          })
          this.tempStaff = [...this.teachingStaffs]
          this.removeDuplicateElement(this.teachingStaffs)
          resolve()
        }
        else {
          this.pageData = 0;
          this.teachingStaffs = [];
          this.noRecords = true;
        }
      }, err => {
        reject('error in get Teaching Staff')
      })
    })





  }


  viewStaff(data: any) {
    data['program'] = false;
    data['staff'] = true;
    data['foreign'] = false;
    data['country'] = 'INDIA'

    data['broadDisciplineGroup'] = data.broadDisciplineGroupName
    data['additionalQualificationName'] = data.additionalQualification?.name
    data['broadDisciplineGroupCategory'] = data.broadCategoryName;
    data['highestQualificationName'] = data.highQualification?.name;
    data['selectionModeName'] = data.teacherSelectionMode?.name;
    data['religiousCommunityName'] = data.religiousCommunity?.name;
    data['natureOfAppointmentName'] = data.appointmentNature;
    data['jobStatusName'] = data.jobStatus;
    data['gender'] = data.gender?.name;
    data['socialCategoryName'] = data.socialCategory?.name
    if (data.dateOfChangeInJobStatus) {
      let day: string = data.dateOfChangeInJobStatus.getDate().toString();
      day = +day < 10 ? '0' + day : day;
      let month: string = (data.dateOfChangeInJobStatus.getMonth() + 1).toString();
      month = +month < 10 ? '0' + month : month;
      let year = data.dateOfChangeInJobStatus.getFullYear();
      var dateOfChangeStatus = `${day}/${month}/${year}`;
      data['dateOfChangeInJob'] = dateOfChangeStatus
    } else {
      data['dateOfChangeInJob'] = 'NA'
    }
    data['program'] = false;

    this.sharedService.viewProgram(data)
  }


}
