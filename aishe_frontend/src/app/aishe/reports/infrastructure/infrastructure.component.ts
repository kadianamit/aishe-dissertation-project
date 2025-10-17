import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, AbstractControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
// import { CollegeService } from 'src/app/service/college.service';
// import { CountrystatecityService } from 'src/app/service/countrystatecity.service';
// import { ExcelService } from 'src/app/service/excel.service';
import { LevelService } from 'src/app/service/get/level.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { CollegeService } from 'src/app/service/reports/college.service';
import { CountrystatecityService } from 'src/app/service/reports/countrystatecity.service';
import { ExcelService } from 'src/app/service/reports/excel.service';
import { InstitutionService } from 'src/app/service/reports/institution.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { UniversityService } from 'src/app/service/reports/university.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-infrastructure',
  templateUrl: './infrastructure.component.html',
  styleUrls: ['./infrastructure.component.scss']
})
export class InfrastructureComponent implements OnInit {

  @ViewChild("child") //to get child component reference
  child?: ReportChildComponent

  submitted: boolean = false;
  isDataLoading: boolean = false
  surveyYearOption: any
  filterSurveyYearOption: any = [];
  filterStatesOption: any;
  filterdStates: any;
  UniversityNameData: any;
  managmentTypeData: any;
  collegeType: any;
  collegeName: any;
  dataBodyType: any;
  institutions: any;
  filtermanagmentTypeData: any = [];
  filterColleges: any = [];
  allCollegeType: any = [];
  filterinstitutions: any = [];
  universityType1: any;
  filtereduniversityType: any = [];
  filteredUniversityNameData: any = []
  filterCollegesName: any = [];
  allStates = { stateName: "ALL", stateCode: "ALL" }
  allCollegeType1 = { type: "ALL", id: "ALL" }
  allUniversity = { name: "ALL", id: "ALL" }
  allBodyType = { type: "ALL", id: "ALL" }


  allManagementTypes = { managementType: "ALL", id: "ALL" }
  alldisciplinGroup = { disciplinGrouCategory: "ALL", id: "ALL" }
  alldisciplinName = { disciplineGroup: "ALL", id: "ALL" }
  allUniversityType = { type: "ALL", id: "ALL" }
  allColleges = { id: 'ALL', name: 'ALL' }
  allCollegeName = { id: 'ALL', name: 'ALL' }
  allNameWithId = { id: 'ALL', name: 'ALL' }
  institutionName1 = { name: 'ALL', id: 'ALL' }

  filteredBodyTypes: any = [];
  //reportForm:FormGroup|any;
  reportForm = new FormGroup({
    institution: new FormControl(''),
    surveyYear: new FormControl('', [Validators.required]),
    addressStateCode: new FormControl(''),
    universityName: new FormControl(''),
    collegeType: new FormControl(''),
    bodyType: new FormControl(''),
    universityType: new FormControl(''),
    managementType: new FormControl(''),
    collegeName: new FormControl(''),
    affiliated: new FormControl(''),
  })
  fieldsData: any = [];
  userData: string | null;
  roleId: any;
  stateCodeDeafult: any;
  isAdminOrMoE: any;
  aisheCode: string;
  splitAisheCode: string;
  constructor(@Inject(MAT_DIALOG_DATA) public reportInfoObj: any,
    private surveyyearservice: SurveyyearService,
    private fb: FormBuilder,
    public countrystatecityService: CountrystatecityService,
    private collegeService: CollegeService,
    private institutionService: InstitutionService,
    private reportService: ReportService,
    private dialog: MatDialog,
    private excelService: ExcelService,
    private courseLevel: LevelService, private UniversityService: UniversityService, public localService: LocalserviceService, public sharedservice: SharedService) {
    this.userData = this.localService.getData('userData')
    this.roleId = this.localService.getData('roleId')
    console.log('787role', this.roleId)
    this.stateCodeDeafult = this.localService.getData('stateCode');
    console.log('nnmm', this.stateCodeDeafult);
    this.aisheCode = localService.getData('aisheCode')

    this.splitAisheCode = this.aisheCode.split('-')[1]
    this.formControl();
  }

  ngOnInit(): void {
      this.roleId = this.localService.getData('roleId')
    
    this.isAdminOrMoE = (this.roleId === this.sharedservice.role['SysAdmin'] || this.roleId === this.sharedservice.role['Ministry of Education'])

    this.loadStates();
    this.loadSurveyYear()
    this.universityTypeData();

    this.findBodyType();
    this.findManagmentTypeData();
    this.findCollegeTypeData();

  }

  formControl() {
    this.reportForm = this.fb.group({
      surveyYear: ['', Validators.required],
      addressStateCode: [this.allStates],
      collegeType: [this.allCollegeType1],
      universityType: [this.allUniversityType],
      universityName: [this.allUniversity],
      managementType: [this.allManagementTypes],
      bodyType: [this.allBodyType],
      institutionName: [this.institutionName1],
      collegeName: [this.allCollegeName],
      affiliated: ['ALL'],
      institution: ['ALL'],

    });
    if (this.roleId == this.sharedservice.role['University']) {
      this.reportForm.get('institution')?.setValue('College');
      this.reportForm.get('institution')?.disable();
    }
  }
  compareFunction(o1: any, o2: any) {
    return (o1.name == o2.name && o1.id == o2.id);
  }
  compareFunction1(o1: any, o2: any) {
    return (o1.stateName == o2.stateName && o1.stateCode == o2.stateCode);
  }
  get f(): { [key: string]: AbstractControl } {
    return this.reportForm.controls
  }
  loadSurveyYear() {
    if (this.userData === "true") {
    this.surveyyearservice.getdatasurveyyearDataUser().subscribe((res: any) => {
      // Get a sliced version starting from the 3rd element
      const dataY = res.slice(2);
      this.surveyYearOption = dataY;
      this.filterSurveyYearOption = dataY;
    });
  } else {
    this.surveyyearservice.getdatasurveyyear().subscribe((res: any) => {
      this.surveyYearOption = res;
      this.filterSurveyYearOption = res;
    });
  }
  }

  loadStates() {
    const countryCode = utility.getLoginlocalStorageUserData().country;
    this.countrystatecityService.getstate(countryCode).subscribe((res) => {
      this.filterStatesOption = res || [];
      this.filterStatesOption.unshift(this.allStates);
      this.filterdStates = [...this.filterStatesOption];

      // if (!this.isAdminOrMoE && !this.userData) {
      //   const selected = this.filterStatesOption.find(
      //     (s: any) => s.stateCode === this.stateCodeDeafult
      //   );

      //   if (selected) {
      //     this.reportForm.get('addressStateCode')?.patchValue(selected);
      //     this.reportForm.get('addressStateCode')?.disable();

      //   }
      // }
    });
  }
  findUniversityByYearAndState() {
    //this.reportForm.controls['universityName'].reset();
    let arrey = this.filteredUniversityNameData.filter((els: any) => els.id === this.reportForm.value.universityName.id);
    if (arrey && arrey.length) {
      this.reportForm.get("universityName")?.setValue(this.allUniversity);
    }
    let event = this.reportForm.value.surveyYear;
    let obj = this.reportForm.value.addressStateCode ? this.reportForm.value.addressStateCode : this.stateCodeDeafult;
    let universityType = this.reportForm.value.universityType.id

    if (universityType) {
      this.UniversityService.getfindUniversityData1(event, obj, universityType).subscribe((res) => {
        this.UniversityNameData = res;
        this.UniversityNameData.splice(0, 0, this.allUniversity);
        this.filteredUniversityNameData = this.UniversityNameData.slice();


        if (this.roleId === this.sharedservice.role['University']) {
          const selected = this.filteredUniversityNameData.find(
            (s: any) => s.id == this.splitAisheCode
          );

          if (selected) {
            this.reportForm.patchValue({ universityName: selected });
            this.reportForm.get('universityName')?.disable();
            this.reportForm.get('universityType')?.disable();
          }
        }
      })
    }
    //console.log(this.reportFilterForm.value.surveyYear);
  }
  handleSurveyYearSelection() {
    const institution = this.f['institution'].value;
    const report = this.reportInfoObj.reportNumber?.trim();

    if (institution === 'College' || report === 'Report 56' || report === 'Report 57' || report === 'Report 111B') {
      this.findUniversityByYearAndState();
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
      this.collegeType.splice(0, 0, this.allCollegeType1);
      this.filterColleges = this.collegeType.slice();
    });
  }

  findCollegeName() {
    let arrey = this.filterCollegesName.filter((els: any) => els.id === this.reportForm.value.collegeName.id);
    if (arrey && arrey.length) {
      this.reportForm.get("collegeName")?.setValue(this.allCollegeName);
    }

    this.collegeService.getCollege(this.reportForm.value.universityName.id).subscribe((res) => {
      this.collegeName = res;
      this.collegeName.splice(0, 0, this.allCollegeName);
      this.filterCollegesName = this.collegeName.slice();
    })
  }

  universityTypeData() {

    this.UniversityService.getUniversityTypes().subscribe((res) => {

      this.universityType1 = res;
      this.universityType1.splice(0, 0, this.allUniversityType)
      this.filtereduniversityType = this.universityType1.slice()
    });
  }

  findBodyType() {
    this.reportService.getBodyType().subscribe((res) => {
      this.dataBodyType = res;
      this.dataBodyType.splice(0, 0, this.allBodyType),
        this.filteredBodyTypes = this.dataBodyType.slice();
    });
  }

  findInstitutionNames() {
    let arrey = this.filterinstitutions.filter((els: any) => els.id === this.reportForm.value.institutionName.id);
    if (arrey && arrey.length) {
      this.reportForm.get("institutionName")?.setValue(this.allNameWithId);
    }
    // this.reportForm.controls['institutionName'].reset();
    if (this.reportForm.value.bodyType && this.reportForm.value.addressStateCode) {
      let event = this.reportForm.value.surveyYear;
      this.institutionService.getInstbyStateCodeAndBody(event, this.reportForm.value.addressStateCode.stateCode, this.reportForm.value.bodyType.id).subscribe((res) => {
        this.institutions = res;
        this.institutions.splice(0, 0, this.allNameWithId);
        this.filterinstitutions = this.institutions.slice();
      });
    }
  }
  doNothing() {

  }

  onReset() {
    this.formControl();
    this.child?.onReset();
     this.loadStates();
    //   if (!this.isAdminOrMoE && !this.userData) {
    //   const selected = this.filterStatesOption.find(
    //     (s: any) => s.stateCode == this.stateCodeDeafult
    //   );
    //   if (selected) {
    //     this.reportInfoObj.patchValue({ addressStateCode: selected });
    //     this.reportInfoObj.get('addressStateCode')?.disable();
      
    //   }
    // }else{
    //    this.child?.onReset();
    // }
  
  }
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
    if (this.reportForm.valid) {
      this.child?.getReportDataTable();
      //this.generateFieldsTableForExcel();
    }
  }
}
