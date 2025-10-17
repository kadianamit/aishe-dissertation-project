/* eslint-disable @angular-eslint/use-lifecycle-interface */
import {
  Component,
  ElementRef,
  Input,
  OnInit,
  QueryList,
  ViewChildren,
} from '@angular/core';
import { SharedService } from 'src/app/shared/shared.service';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import {
  FormGroup,
  FormControl,
  AbstractControl,
  Validators,
} from '@angular/forms';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { Common } from 'src/app/common/common';
import { MatDialog } from '@angular/material/dialog';
import { UniversityService } from 'src/app/service/reports/university.service';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'app-mergecolleges',
  templateUrl: './mergecolleges.component.html',
  styleUrls: ['./mergecolleges.component.scss'],
})
export class MergecollegesComponent implements OnInit {
  @Input()
  instiFormData: any;
  filteredDistrict: any;
  state: any;
  id: any;
  selectedColleges: Array<any> = [];
  showMergeTable: boolean = false;
  surveyYear1: any;
  dataDistrict: any;
  UniversityList: any;
  universityList: any;
  UniSurveyYear: any;
  StartLimit: number = 0;
  pageData: number = 20;
  EndLimit: number = 0;
  pageSize: any = 5;
  page: number = 1;
  roleId: any;
  userId: any;
  userData: any = [];
  searchText: any;
  userDataTable: any = [];
  isMerge: boolean = false;
  managmentData: any;
  managementType: any;
  filteredmanagementT: any[] = [];
  tableSize: number[] = [5, 10, 15, 20];
  submitted = false;
  surveyYearList: any;
  showTable: boolean = false;
  surveyYearValue: any;
  collegeTypeData: any;
  collegeName: any | null;
  collegeType: any | null;
  districtName: any | null;
  stateName: any | null;
  filteredcollegeTypeData: any;
  filtereduniversityList: any;
  filteredUniversityList: any;
  locationData: any;
  @ViewChildren('checkboxes') checkboxes!: QueryList<ElementRef>;

  filteredState: any;
  form = new FormGroup({
    collegeName: new FormControl('', [
      Validators.required,
      Common.noWhitespaceValidator,
    ]),
    collegeType: new FormControl('', Validators.required),
    stateName: new FormControl('', Validators.required),
    districtName: new FormControl('', Validators.required),
    remarks: new FormControl('', [
      Validators.required,
      Validators.maxLength(2000),
      // Validators.minLength(3),
      Common.noWhitespaceValidator,
    ]),
    managementType: new FormControl('', [Validators.required]),
    location: new FormControl('', [Validators.required]),
    surveyYearValue: new FormControl('', Validators.required),
    universityType: new FormControl('', Validators.required),
    isDCFApplicable: new FormControl(),
  });
  isFormInvalid:any;

  constructor(
    public sharedservice: SharedService,
    private dialog: MatDialog,
    private institutionmanagement: InstitutionmanagementService,
    public universityService: UniversityService,public localService:LocalserviceService
  ) {}

  ngOnInit() {
    this.roleId = this.localService.getData('roleId');
    this.userId = this.localService.getData('userId');
    this.findManagmentType();
    this.institutionmanagement.getCollegeType().subscribe((res) => {
      this.collegeTypeData = res;
      this.filteredcollegeTypeData = this.collegeTypeData.slice();
    });
    
    this.surveyYear();
    this.findData();
    this.getLocation();
  }

  getLocation() {
    this.institutionmanagement.getLocation().subscribe((res) => {
      this.locationData = res.location;
    });
  }
  getState() {
    this.institutionmanagement.getState().subscribe((res) => {
      this.userData = res;
      this.filteredState = this.userData.slice();
    });
  }
  surveyYear() {
    this.institutionmanagement.getSurveyYearList().subscribe((res) => {
      this.surveyYearList = res;
    });
  }
  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }
  findManagmentType() {
    let payload={type:'C'}
    this.institutionmanagement.getmanagmentType2(payload).subscribe((res) => {
      this.managmentData = res;
      this.filteredmanagementT = this.managmentData.slice();
    });
  }
  findData1() {
    this.userDataTable = [];
    this.pageSize = 10;
    this.searchText = null;
    this.page = 1;
    this.handlePageChange(this.page);
  }

  loadUniversityData() {
    if (this.form.value.surveyYearValue) {
      this.UniSurveyYear = this.form.value.surveyYearValue.split('-')[0];
    }

    this.state =
      this.roleId == '1' || this.roleId == '26'
        ? this.form.value.stateName.stateCode
        : this.localService.getData('stateCode');
    if (this.UniSurveyYear && this.state) {
      this.institutionmanagement
        .getUniversity(this.state, this.UniSurveyYear)
        .subscribe({
          next: (res) => {
            this.UniversityList = res;
            // console.log('sc', this.UniversityList);
            this.filteredUniversityList = this.UniversityList.slice();
          },
        });
    }
    this.getdistrictName();
  }

  resetData() {
    this.userDataTable = [];
    this.searchText = null;
    this.page = 1;
    this.pageSize = 5;
    this.selectedColleges = [];
    this.handlePageChange(this.page);
  }

  findData() {
    
    if (this.instiFormData.value.aisheCodeValue) {
      var aisheId = this.instiFormData.value.aisheCodeValue;
    }
    // if (!this.instiFormData.value.surveyYearValue) {
    //   this.sharedservice.showError('Please Select Survey Year');
    //   return;
    // }
    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    if (!this.instiFormData.value.stateValue && (this.roleId === '26' || this.roleId === '1')) {
      this.sharedservice.showError('Please Select State');
      return;
    }

    // if (
    //   (!this.instiFormData.value.universityValue &&
    //     this.roleId === '6' &&
    //     !this.instiFormData.value.aisheCodeValue) ||
    //   (!this.instiFormData.value.universityValue &&
    //     (this.roleId === '26' || this.roleId === '1') &&
    //     !this.instiFormData.value.aisheCodeValue)
    // ) {
    //   this.sharedservice.showError('Please Select University Name');
    //   return;
    // }
    var formData;
    if (this.roleId === '26' || this.roleId === '1') {
      formData = {
        collegeType: this.instiFormData.value.collegeType?this.instiFormData.value.collegeType:'',
        surveyYearValue: this.instiFormData.value.surveyYearValue?this.instiFormData.value.surveyYearValue.split('-')[0]:'',
        universityValue: this.instiFormData.value.universityValue?this.instiFormData.value.universityValue.trim():'',
        locationType: this.instiFormData.value.locationType,
        selectedStateCode: this.instiFormData.value.stateValue?this.instiFormData.value.stateValue.trim():'',
        aisheCodeValue: aisheId?aisheId:'',
        reasonId: this.instiFormData.value.reasonId ?this.instiFormData.value.reasonId:'',
        districtValue:this.instiFormData.value.districtValue?this.instiFormData.value.districtValue:'',
      };
    }

    this.institutionmanagement
      .getDeaffiliationCollegeList(formData)
      .subscribe((res) => {
        this.userDataTable=[];
        this.selectedColleges = [];
        this.showTable = true;
        this.userDataTable = res.deaffiliationCollegeListBeans;
        this.isMerge = false;
        this.page = 1;

        this.handlePageChange(this.page);
        if (res.statusCode == 'AISH099') {
          this.findData1();

          this.sharedservice.showError(res.statusDesc);
        }
      });
      this.searchText=null;
  }

  getdistrictName() {
    let stateName1 = this.userData.filter(
      (data: any) => data.name === this.form.value.stateName.name
    );

    this.institutionmanagement
      .getdistrict(stateName1['0'].stateCode)
      .subscribe((res) => {
        this.dataDistrict = res;
        this.filteredDistrict = this.dataDistrict.slice();
      });
  }
  merge() {
    this.showMergeTable = true;
    this.isMerge = true;
    this.showTable = false;
  }
  compareFn(t1: any, t2: any): boolean {
    return t1 && t2 ? t1 === t2 : t1 === t2;
  }

  backClicked(): void {
    this.showMergeTable = false;
    this.showTable = true;
    this.isMerge = false;
    this.selectedColleges = [];
    this.submitted = false;
    this.form.reset();
  }

  mergeCollgeData() {
    this.findData();
  }

  checkBox(e: any, data: any) {
    if (!e.checked) {
      let index = this.selectedColleges.findIndex((x: any) => x.id == data.id);
      this.selectedColleges.splice(index, 1);
    } else {
      this.selectedColleges.push(data);
      // console.log(this.selectedColleges[0].collegeName);
    }
  }

  resetCheckbox() {
    this.checkboxes.forEach((element) => {
      element.nativeElement.checked = false;
    });
    this.selectedColleges.length = 0;
  }
  toggleCheckBox(elementId: any) {
    return this.selectedColleges
      .map((data: any) => data.id)
      .indexOf(elementId) != -1
      ? true
      : false;
  }

  submit() {
    this.submitted = true;
    if (this.form.valid) {
      let location = this.locationData.filter(
        (location1: any) => location1.id === this.form.value.location
      );
for( var i = 0; i < this.selectedColleges.length; i++){
  if (
    this.selectedColleges[i].collegeName ===
      this.form.value.collegeName.trim()
  ) {
    this.sharedservice.showError('Same colleges name is not allowed');
    this.form.controls['collegeName'].reset();
    return;
  }
}
      
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        disableClose: true,
        data: {
          message:
            'Details of the Institute are as below, Please confirm the detailss.',
          state: this.form.value.stateName.name,
          district: this.form.value.districtName.name,
          Name: this.form.value.collegeName.trim(),
          remarks: this.form.value.remarks.trim(),
          newCollegeType: this.form.value.collegeType.type,
          university: this.form.value.universityType.name,
          surveyYear: this.form.value.surveyYearValue.slice(0, 4).trim(),
          isDCFApplicable: this.form.value.isDCFApplicable
            ? this.form.value.isDCFApplicable
            : 'false',
          managementType: this.form.value?.managementType?.managementType,
          location: location[0].name,
          universityType: 'mergeUniversityType',
        },
      });

      let userData = {
        aisheCodes: this.selectedColleges.map((x) => x.id),
        newCollegeDistrict: this.form.value.districtName.distCode,
        newCollegeIsDcfApplicable: this.form.value.isDCFApplicable
          ? this.form.value.isDCFApplicable
          : 'false',
        newCollegeName: this.form.value.collegeName.trim(),
        newCollegeState: this.form.value.stateName.stateCode,
        newCollegeType: this.form.value.collegeType.id.toString(),
        newCollegeUniversityId: this.form.value.universityType.id,
        remarks: this.form.value.remarks.trim(),
        newCollegeLocation: this.form.value.location,
        newCollegeManagementType: this.form.value?.managementType?.id,
        surveyYear: this.form.value.surveyYearValue.slice(0, 4).trim(),
        userId: this.userId,
      };

      //call save api accordingly
      dialogRef.afterClosed().subscribe((confirmed: boolean) => {
        if (confirmed) {
          this.institutionmanagement
            .mergeCollegesData(userData)
            .subscribe((res) => {
              this.submitted = false;
              if (res.statusCode == 'AISH011') {
                this.sharedservice.showError(
                  'Adding University Data not allowed'
                );
              }
              if (res.statusCode == 'AISH001') {
                this.selectedColleges = [];
                this.form.reset();

                const dialogRef = this.dialog.open(ConfirmDialogComponent, {
                  data: {
                    message1: 'Your request has been processed successfully!!',
                    data1: res.newAisheCode,
                    action: '1',
                    showData: 'mergeCollege',
                  },
                });
                this.sharedservice.showSuccessMessage(
                  'University Data Added Successfully'
                );
              }

              if (res.statusCode == 'AISH102') {
                this.sharedservice.showError('University Name Already Exists');
              }
              if (res.statusCode == 'AISH026') {
                this.sharedservice.showError(res.statusDesc);
              }
              if (res.statusCode == 'AISH002') {
                this.sharedservice.showError(res.statusDesc);
              }
              this.findData();
              this.backClicked();
            });
        }
      });
    }
    if (this.form.invalid) {
      this.sharedservice.showError(
        'Invalid details, Please provide valid details.'
      );
    }
  }

  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
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
}
