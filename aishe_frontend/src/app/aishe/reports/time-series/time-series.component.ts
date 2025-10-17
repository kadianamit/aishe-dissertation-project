import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, AbstractControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { CollegeService } from 'src/app/service/reports/college.service';
import { CountrystatecityService } from 'src/app/service/reports/countrystatecity.service';
import { ExcelService } from 'src/app/service/reports/excel.service';
import { LevelService } from 'src/app/service/get/level.service';
import { InstitutionService } from 'src/app/service/reports/institution.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { DisciplineService } from 'src/app/service/get/discipline.service';
import { UniversityService } from 'src/app/service/reports/university.service'
import { throws } from 'assert';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
@Component({
  selector: 'app-time-series',
  templateUrl: './time-series.component.html',
  styleUrls: ['./time-series.component.scss']
})
export class TimeSeriesComponent implements OnInit {
  submitted: boolean = false
  isDataLoading: boolean = false
  dataTableToggle: boolean = false
  Showdata12: boolean = false
  showPdfButton: any | false | boolean
  surveyYearOption: any
  districtOption: any
  filterSurveyYearOption: any;
    userData: string | null;
  filterStatesOption: any
  filterdStates: any //for text based search
  filterDistrictsOption: any
  filterDistricts: any
  filterUniversityOption: any
  filterUniversities: any
  filterUniversityTypes: any
  filteredUniversityTypes: any
  filterCollegesOption: any
  filterColleges: any
  institutions: any = []
  filterinstitutions: any[] = []
  designationData: any
  filterDesignation: any
  bodyTypes: any
  nonTeachingStaff: any = []
  filteredBodyTypes: any
  nonTeacherStaffTypeData: any
  filteredNonTeacherStaffTypeData: any
  programmeData: any = [];
  filterProgramme: any[] = [];
  UniversityNameData: any = []
  allStates = { stateName: "ALL", stateCode: "ALL" }
  allDistrict = { DistrictName: "ALL", districtCode: "ALL" }
  allUniversity = { name: "ALL", id: "ALL" }
  allBodyType = { type: "ALL", id: "ALL" }
  allCollegeType = { type: "ALL", id: "ALL" }
  allPosts = { designation: "ALL", id: "ALL" }
  allManagementTypes = { managementType: "ALL", id: "ALL" }
  alldisciplinGroup = { disciplinGrouCategory: "ALL", id: "ALL" }
  alldisciplinName = { disciplineGroup: "ALL", id: "ALL" }
  allUniversityType = { type: "ALL", id: "ALL" }
  allColleges = { id: 'ALL', name: 'ALL' }
  allnonTeacherStaffType = { staffType: "ALL", id: "ALL" }
  allTypeWithId = { type: "ALL", id: "ALL" }
  allNameWithId = { name: "ALL", id: "ALL" }
  nonTeacherStaffTypeGroup: any
  filterNonTeacherStaffTypeGroup1: any = [];
  filterNonTeacherStaffTypeGroup: any
  allNonTeacherStaffTypeGroup = { id: "ALL", staffGroup: "ALL" };
  allCollegeName = { name: "ALL", id: "ALL" };
  allprogramme = { id: "ALL", programme: "ALL" }
  institutionName = { id: "ALL", name: "ALL" }
  inputObj: any = {};
  reportFilterForm: FormGroup | any;
  courseMode: Array<any> = [{ id: 'ALL', value: 'ALL' }, { id: '1', value: 'GENERAL' }, { id: '2', value: 'SELF-FINANCING' }]
  studyMode: Array<any> = [{ id: 'ALL', value: 'ALL' }, { id: '1', value: 'REGULAR' }, { id: '2', value: 'DISTANCE' }]
  socialCategory: Array<any> = [{ id: 'ALL', value: 'ALL' }, { id: '1', value: 'SC' }, { id: '2', value: 'ST' }, { id: '3', value: 'OBC' }]
  religiousCategory: Array<any> = [{ id: 'ALL', value: 'ALL' }, { id: '1', value: 'Muslim' }, { id: '2', value: 'OTHER MINORITY' }, { id: '3', value: 'PWD' }]

  @ViewChild("child") //to get child component reference
  child?: ReportChildComponent

  universityType1: any;
  filtereduniversityType: any
  filteredUniversityNameData: any[] = [];
  filteredDisciplineGroup: any;
  DisciplineGroup: any = []
  DisciplineName: any = []
  filterDisciplineName: any[] = [];
  dataBodyType: any = [];
  managmentTypeData: any = [];
  filtermanagmentTypeData: any = [];
  collegeType: any;
  collegeName: any = [];
  filterCollegesName: any[] = [];
  userCourseLevel: any = [];
  filterCourseLevel: any;
  allLevel = { id: "ALL", name: "ALL" };
  //allProgramme={id:"ALL", name:"ALL"};
  fieldsData: any = [];
  DisciplineGrouppro: any;
  filteredDisciplineGrouppro12: any[] = [];
 
  roleId: string;
  isAdminOrMoE: any;
  stateCodeDeafult: any;
  aisheCode:any;
  splitAisheCode:any;
  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,
    private surveyyearservice: SurveyyearService,
    private fb: FormBuilder,
    public countrystatecityService: CountrystatecityService,
    private UniversityService: UniversityService,
    private collegeService: CollegeService,
    private institutionService: InstitutionService,
    private reportService: ReportService,
    private DisciplineService: DisciplineService,
    private excelService: ExcelService,
    private courseLevel: LevelService,
    private dialog: MatDialog, public localService: LocalserviceService, public sharedservice: SharedService) {
    this.userData = this.localService.getData('userData');
    this.formControl();
    this.roleId = this.localService.getData('roleId')

    this.stateCodeDeafult = this.localService.getData('stateCode');
    this.aisheCode = localService.getData('aisheCode')

    this.splitAisheCode = this.aisheCode.split('-')[1]
  }



  ngOnInit(): void {
    this.isAdminOrMoE = (this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])

    this.loadSurveyYear();
    this.loadStates()
    this.loadStaffType();
    this.loadNonTeachingStaffGroup();
    this.loadDesignation();
    this.findBodyType();
    this.loadUniversityTypeData()
    this.findManagmentTypeData();
    this.findCollegeTypeData();
    this.findDisciplineGroup();
    this.findCourseLevel();
    //this.findCollegeName()
  }
  formControl() {
    this.reportFilterForm = this.fb.group({
      surveyYear: ['', Validators.required],
      addressStateCode: [this.allStates],
      addressDistrictCode: [this.allDistrict],
      universityName: [this.allUniversity],
      universityType: [this.allUniversityType],
      socialCategory: [this.allTypeWithId.id],
      studyMode: [this.allTypeWithId.id],
      religiousCategory: [this.allTypeWithId.id],
      courseMode: [this.allTypeWithId.id],
      managementType: [this.allManagementTypes],
      collegeType: [this.allCollegeType],
      collegeName: [this.allCollegeName],
      level: [this.allLevel],
      disciplineGroup: [this.alldisciplinGroup],
      disciplineName: [this.alldisciplinName],
      bodyType: [this.allBodyType],
      institution: ['ALL'],
      staffType: [this.allnonTeacherStaffType],
      staffGroup: [this.allNonTeacherStaffTypeGroup],
      selectionMode: ['ALL'],
      designation: [this.allPosts],
      constitutedFromCollege: [],
      institutionName: [this.allNameWithId],
      programme: [this.allprogramme]
    })
  }
  get f(): { [key: string]: AbstractControl } {
    return this.reportFilterForm.controls
  }

  loadStates() {

    let countryCode = utility.getLoginlocalStorageUserData().country
    // console.log("countryCode ", countryCode);

    this.countrystatecityService.getstate(countryCode).subscribe((res) => {

      this.filterStatesOption = res;
      this.filterStatesOption.unshift(this.allStates); // optional "All States"

      this.filterdStates = [...this.filterStatesOption];

  // if (!this.isAdminOrMoE && !this.userData) {
  //     const selected = this.filterStatesOption.find(
  //       (s: any) => s.stateCode == this.stateCodeDeafult
  //     );
  //     if (selected) {
  //       this.reportFilterForm.patchValue({ addressStateCode: selected });
  //       this.reportFilterForm.get('addressStateCode')?.disable();
      
  //     }
  //   }
  });
 

  }

  loadSurveyYear() {
    this.surveyyearservice.getdatasurveyyear().subscribe((res) => {
      this.surveyYearOption = res
      if (this.userData === "true") {
        let dataY = res.slice(2)
        this.surveyYearOption = dataY
        this.filterSurveyYearOption = this.surveyYearOption
      } else {
        this.filterSurveyYearOption = this.surveyYearOption.slice()
      }
    })
  }


  loadUniversityTypeData() {
    this.UniversityService.getUniversityTypes().subscribe((res) => {

      this.universityType1 = res;
      this.universityType1.splice(0, 0, this.allUniversityType)
      this.filtereduniversityType = this.universityType1.slice()
    });
  }
  loadDesignation() {

    this.reportService.getDesignation().subscribe((res) => {

      this.designationData = res;
      this.designationData.splice(0, 0, this.allPosts);

      this.filterDesignation = this.designationData.slice();

    });

  }

  //call this method on change of survey Year or State or University type field
  //for report 109A
  findUniversityByYearAndState() {
    let surveyYear = this.reportFilterForm.value.surveyYear;
    let stateObj = this.reportFilterForm.value.addressStateCode? this.reportFilterForm.value.addressStateCode : this.stateCodeDeafult;;
    let universityType = this.reportFilterForm.value.universityType.id ? this.reportFilterForm.value.universityType.id : 'ALL';

    let arrey = this.filteredUniversityNameData.filter((els: any) => els.id === this.reportFilterForm.value.universityName.id);
    if (arrey && arrey.length) {
      this.reportFilterForm.get("universityName")?.setValue(this.allUniversity);
    }

    if (surveyYear) {
      this.UniversityService.getfindUniversityData1(surveyYear, stateObj, universityType).subscribe((res) => {
        this.UniversityNameData = res;
        this.UniversityNameData.splice(0, 0, this.allUniversity);
        this.filteredUniversityNameData = this.UniversityNameData.slice();

         if (this.roleId === this.sharedservice.role['University']) {
          const selected = this.filteredUniversityNameData.find(
            (s: any) => s.id == this.splitAisheCode
          );

          if (selected) {
            this.reportFilterForm.patchValue({ universityName: selected });
            this.reportFilterForm.get('universityName')?.disable();
            this.reportFilterForm.get('universityType')?.disable();
          }
        }
      });
    }
  }

  findManagmentTypeData() {
    this.reportService.getManagmentTypeData().subscribe((res) => {
      this.managmentTypeData = res;
      this.managmentTypeData.splice(0, 0, this.allManagementTypes)
      this.filtermanagmentTypeData = this.managmentTypeData.slice();


    });
  }

  findCollegeTypeData() {
    this.reportService.getCollegeTypeData().subscribe((res) => {
      this.collegeType = res;
      this.collegeType.splice(0, 0, this.allCollegeType);
      this.filterColleges = this.collegeType.slice();
    });
  }

  findCollegeName() {
    let universityNameId = this.reportFilterForm.value.universityName.id;
    let collegeType = this.reportFilterForm.value.collegeType.id
    let surveyYear = this.reportFilterForm.value.surveyYear;

    let arrey = this.filterCollegesName.filter((el: any) => el.id === this.reportFilterForm.value.collegeName.id);
    if (arrey && arrey.length) {
      this.reportFilterForm.get("collegeName").setValue(this.allCollegeName);
    }


    this.collegeService.getcollegenamebyType(surveyYear, universityNameId, collegeType).subscribe((res) => {
      this.collegeName = res;
      this.collegeName.splice(0, 0, this.allCollegeName);
      this.filterCollegesName = this.collegeName.slice();
    })

  }
  findCourseLevel() {
    this.courseLevel.getLevelData().subscribe((res) => {
      this.userCourseLevel = res;
      this.userCourseLevel.splice(0, 0, this.allLevel);
      this.filterCourseLevel = this.userCourseLevel.slice();

    });
  }
  findProgrammData() {
    let levelData = this.reportFilterForm.value.level.id;
    let arrey = this.filterProgramme.filter((el: any) => el.id === this.reportFilterForm.value.programme.id);
    if (arrey && arrey.length) {
      this.reportFilterForm.get("programme")?.setValue(this.allprogramme);
    }
    this.courseLevel.getprogrammeData(levelData).subscribe((res) => {
      this.programmeData = res;
      console.log(this.programmeData);
      this.programmeData.splice(0, 0, this.allprogramme);
      this.filterProgramme = this.programmeData.slice();
    });

  }

  findDisciplineGroupByProgramme12() {

    let arrey = this.filteredDisciplineGrouppro12.filter((els: any) => els.id === this.reportFilterForm.value.disciplineGroup.id);
    if (arrey && arrey.length) {
      this.reportFilterForm.get("disciplineGroup")?.setValue(this.alldisciplinGroup);
    }
    let programmeId = this.reportFilterForm.value.programme.id;

    this.courseLevel.getDisciplineGroupByProgrammeData(programmeId).subscribe((res) => {
      this.DisciplineGrouppro = res;
      this.DisciplineGrouppro.splice(0, 0, this.alldisciplinGroup)
      this.filteredDisciplineGrouppro12 = this.DisciplineGrouppro.slice()


    });
  }


  findDisciplineGroup() {
    this.DisciplineService.getDisciplineGroup().subscribe((res) => {
      this.DisciplineGroup = res;
      this.DisciplineGroup.splice(0, 0, this.alldisciplinGroup);
      this.filteredDisciplineGrouppro12 = this.DisciplineGroup.slice()
    });
  }

  findDisciplineName() {

    let arrey = this.filterDisciplineName.filter((els: any) => els.id === this.reportFilterForm.value.disciplineName.id);
    if (arrey && arrey.length) {
      this.reportFilterForm.get("disciplineName")?.setValue(this.alldisciplinName);
    }
    this.DisciplineService.getDisciplineName(this.reportFilterForm.value.disciplineGroup.id).subscribe((res) => {
      this.DisciplineName = res;
      this.DisciplineName.splice(0, 0, this.alldisciplinName);
      this.filterDisciplineName = this.DisciplineName.slice()

    });
  }

  findBodyType() {
    this.reportService.getBodyType().subscribe((res) => {
      this.dataBodyType = res;
      this.dataBodyType.splice(0, 0, this.allBodyType),
        this.filteredBodyTypes = this.dataBodyType.slice();
    });
  }

  loadStaffType() {

    this.reportService.getNonTeacherStaffType().subscribe((res) => {
      this.nonTeacherStaffTypeData = res;
      this.filteredNonTeacherStaffTypeData = this.nonTeacherStaffTypeData.slice();
      this.filteredNonTeacherStaffTypeData.splice(0, 0, this.allnonTeacherStaffType);

    });

  }

  loadNonTeachingStaffGroup() {

    this.reportService.getNonTeachingStaffGroup().subscribe((res) => {
      this.nonTeacherStaffTypeGroup = res;
      this.filterNonTeacherStaffTypeGroup1 = this.nonTeacherStaffTypeGroup.slice();
      this.filterNonTeacherStaffTypeGroup1.splice(0, 0, this.allNonTeacherStaffTypeGroup);

    });

  }

  findInstitutionNames() {

    let surveyYear = this.reportFilterForm.value.surveyYear;
    let stateName = this.reportFilterForm.value.addressStateCode.stateCode;
    let bodyTypes = this.reportFilterForm.value.bodyType.id;
    let arrey = this.filterinstitutions.filter((el: any) => el.id === this.reportFilterForm.value.institutionName.id);
    if (arrey && arrey.length) {
      this.reportFilterForm.get("institutionName")?.setValue(this.allNameWithId);
    }

    if (surveyYear) {
      this.institutionService.getInstbyStateCodeAndBody(surveyYear, stateName, bodyTypes).subscribe((res) => {
        this.institutions = res;
        this.institutions.splice(0, 0, this.allNameWithId);
        this.filterinstitutions = this.institutions.slice();
      });
    }



  }

  onReset(): void {
    this.dataTableToggle = false;
    this.isDataLoading = false;
    this.Showdata12 = false;
    // this.child?.onReset()
    this.formControl();
    this.findUniversityByYearAndState() ;
    this.loadStates();
//  if (!this.isAdminOrMoE && !this.userData) {
//       const selected = this.filterStatesOption.find(
//         (s: any) => s.stateCode == this.stateCodeDeafult
//       );
//       if (selected) {
//         this.reportFilterForm.patchValue({ addressStateCode: selected });
//         this.reportFilterForm.get('addressStateCode')?.disable();
      
//       }
//     }else{
//        this.child?.onReset();
//     }
  }

  doNothing() {
    // alert("nothing");
    return
  }
  // to export excel file from
  exportToExcel() {
    this.excelService.exportToExcel(this.reportInfoObj.reportNumber, this.child?.tableColumns);
  }

  generateFieldsTableForExcel() {
    let fieldsArr = document.forms[0].querySelectorAll<HTMLElement>('mat-form-field');
    let index = 0;
    for (let i = 0; i < fieldsArr.length; i++, index++) {
      let leftLbl = fieldsArr[i].innerText.split('\n')[1];
      let leftVal = fieldsArr[i].innerText.split('\n')[0];
      i++;
      let rightLbl = fieldsArr[i]?.innerText.split('\n')[1];
      let rightVal = fieldsArr[i]?.innerText.split('\n')[0];

      this.fieldsData[index] = { label1: leftLbl, value1: leftVal, label2: rightLbl, value2: rightVal }

    }
  }
  submit() {

    this.submitted = true;
    if (this.reportFilterForm.valid) {
      this.child?.getReportDataTable();
      // this.generateFieldsTableForExcel();
    }
  }

  courseChange(x: any, data: any, type: string) {

    if (type === 'courseMode') {
      let ModeName = data.filter((coureModeName: any) => coureModeName.id == x).map((x: any) => x.value)[0];
      return this.inputObj['courseModeName'] = ModeName;
    } else if (type === 'studyMode') {
      let ModeName = data.filter((studyModeName: any) => studyModeName.id == x).map((x: any) => x.value)[0];
      return this.inputObj['studyModeName'] = ModeName;
    }
    else if (type === 'socialCategory') {
      let ModeName = data.filter((socialCategory: any) => socialCategory.id == x).map((x: any) => x.value)[0];
      return this.inputObj['socialCategory'] = ModeName;
    }
    else if (type === 'religiousCategory') {
      let ModeName = data.filter((religiousCategory: any) => religiousCategory.id == x).map((x: any) => x.value)[0];
      return this.inputObj['religiousCategory'] = ModeName;
    }

  }
}
