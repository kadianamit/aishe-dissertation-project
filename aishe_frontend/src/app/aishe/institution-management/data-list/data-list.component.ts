import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import {
  FormGroup,
  FormControl,
  AbstractControl,
  Validators,
} from '@angular/forms';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { SharedService } from 'src/app/shared/shared.service';
import { MatDialog } from '@angular/material/dialog';
import { Common } from 'src/app/common/common';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { AuthService } from 'src/app/service/auth.service';
@Component({
  selector: 'app-data-list',
  templateUrl: './data-list.component.html',
  styleUrls: ['./data-list.component.scss'],
})
export class DataListComponent implements OnInit {
  @Input()
  instiFormData: any;
  filteredDistrict: any;
  state: any;
  id: any;
  showEditTables: boolean = false;
  surveyYear1: any;
  count: number = 0;
  dataDistrict: any;
  universityList: any;
  UniSurveyYear: any;
  tabledata: boolean = false;
  userData1: any = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  //hostel: any[] = [];
  roleId: any;
  userData: any = [];
  userDataTable: any = [];
  isEdit: boolean = false;
  managmentData: any;
  managementType: any;
  filteredmanagementT: any[] = [];
  tableSize: number[] = [10, 20, 30, 40, 50];
  submitted = false;
  usercollegeTypeData: any[] = [];
  dataUniversityId1: any[] = [];
  surveyYearList: any;
  selectedYearValue: any = '';
  showTable: boolean = false;
  surveyYearValue: any;
  collegeTypeData: any;
  collegeName: any | null;
  collegeType: any | null;
  districtName: any | null;
  stateName: any | null;
  collegeName1: any;
  location: any;
  filteredcollegeTypeData: any;
  filtereduniversityList: any;
  filteredState: any;
  dataCheck: any;
  locationData: any;
  validPattern = /^[a-zA-Z !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?\u00A0\u200B]{1,350}$/;
  form = new FormGroup({
    surveyYearValue: new FormControl('', [Validators.required]),
    collegeName: new FormControl('', [
      Validators.required,
      Validators.pattern(this.validPattern),
    ]),
    collegeType: new FormControl(),
    stateName: new FormControl(),
    districtName: new FormControl('', [Validators.required]),
    remarks: new FormControl('', [
      Validators.required,
      Validators.maxLength(2000),
      //Validators.minLength(3),
    ]),
    managementType: new FormControl('', []),
    location: new FormControl('', []),
    isDCFApplicable: new FormControl('', []),
  });
  isFormInvalid: any;
  locationEdit: any;
  isDcfApplicable: any;
  universityId: any;
  constructor(
    public sharedservice: SharedService,
    private dialog: MatDialog,
    private institutionmanagement: InstitutionmanagementService, public localService: LocalserviceService, public auth: AuthService
  ) {
    this.roleId = this.localService.getData('roleId');
  }

  ngOnInit() {

    this.findDataEdit1();
    this.getLocation();
    this.findManagmentType();
    this.getState();
    this.getCollegeType();
    this.surveyYear();

  }

  findDataEdit1() {
    //  console.log(this.instiFormData)
    this.searchText = null;
    this.userDataTable = [];
    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    if (!this.instiFormData.value.surveyYearValue) {
      this.sharedservice.showError('Please Select Survey Year');
      return;
    }
    if (
      (!this.instiFormData.value.stateValue &&
        this.roleId === '26' &&
        !this.instiFormData.value.aisheCodeValue) ||
      (!this.instiFormData.value.stateValue &&
        this.roleId === '1' &&
        !this.instiFormData.value.aisheCodeValue)
    ) {
      this.sharedservice.showError('Please Select State');
      return;
    }
    if (
      ((!this.instiFormData.value.universityValue && this.roleId === '6') ||
        (!this.instiFormData.value.universityValue && this.roleId === '26') ||
        (!this.instiFormData.value.universityValue && '1' === this.localService.getData('roleId'))) &&
      !this.instiFormData.value.aisheCodeValue
    ) {
      this.sharedservice.showError('Please Select University Name');
      return;
    }

    if (this.instiFormData.value.aisheCodeValue) {
      // var aisheId = this.instiFormData.value.aisheCodeValue.replace(/\D/g, '');
      var aisheId = this.instiFormData.value.aisheCodeValue;
    }
    var formData;
    let aisheCode: any = this.localService.getData('aisheCode');
    let aisheCode1 = aisheCode.replace(/\D/g, '');
    formData = {
      collegeType: this.instiFormData.value.collegeType ? this.instiFormData.value.collegeType : '',
      surveyYearValue: this.instiFormData.value.surveyYearValue.split('-')[0],
      universityValue: this.instiFormData.value.universityValue ? this.instiFormData.value.universityValue.trim() : aisheCode1,
      collegeLocation: this.instiFormData.value.collegeLocation ? this.instiFormData.value.collegeLocation : '',
      selectedStateCode: this.instiFormData.value.stateValue ? this.instiFormData.value.stateValue.trim() : '',
      aisheCodeValue: aisheId,
    };
    if (this.roleId == '6') {//SNO
      formData.selectedStateCode = this.localService.getData('stateCode');
    }
    if (this.roleId == '7') {//UNO
      formData.universityValue = this.localService.getData('id');
    }
    if (this.instiFormData.value.collegeLocation) {
      formData.selectedStateCode = this.instiFormData.value.stateValue ? this.instiFormData.value.stateValue : this.localService.getData('stateCode');
    }
    // console.log(formData)
    if (formData) {
      this.institutionmanagement
        .getinstitutionCollegeData1(formData)
        .subscribe((res) => {
          this.showTable = true;
          this.userDataTable = res.universityCollegeListBean;
          this.isEdit = false;
          this.page = 1;
          this.handlePageChange(this.page);
          if (res.statusCode == 'AISH099') {
            this.DataReset();

            this.sharedservice.showError(res.statusDesc);
          }
        });
    }
  }



  getCollegeType() {
    this.institutionmanagement.getCollegeType().subscribe((res) => {
      this.collegeTypeData = res;
      this.filteredcollegeTypeData = this.collegeTypeData.slice();
    });
  }
  getState() {
    this.institutionmanagement.getState().subscribe((res) => {
      this.userData = res;
      this.filteredState = this.userData.slice();
      //this.filteredState = this.userData.filter((AZS:any) => AZS.name == this.stateName);
    });
  }

  getLocation() {
    this.institutionmanagement.getLocation().subscribe((res) => {
      this.locationData = res.location;
    });
  }
  districtfilter() {
    this.dataCheck = true;
    this.form.controls['districtName'].reset();
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }
  findManagmentType() {

    let college = 'c'
    // this.institutionmanagement.getmanagmentType().subscribe((res) => {
    this.auth.getManagementTypeOwnershipData(college).subscribe((res) => {

      this.managmentData = res;
      //console.log(res)
      this.filteredmanagementT = this.managmentData.slice();
    });
  }
  DataReset() {
    this.userDataTable = [];
    this.searchText = null;
    this.pageSize = 10;
    // this.page = 1;
    // this.handlePageChange(this.page);
  }
  applyFilter(filterValue: any) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  alphaNumberOnly(e: any) {
    // Accept only alpha numerics, not special characters
    var regex = new RegExp('[a-zA-Z ,]');
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

  getdistrictName() {
    let stateName1 = this.userData.filter(
      (data: any) => data.name === this.form.value.stateName
    );

    this.institutionmanagement
      .getdistrict(stateName1['0'].stateCode)
      .subscribe((res) => {
        this.dataDistrict = res;
        this.filteredDistrict = this.dataDistrict.slice();
      });
  }
  editUser(event: any) {
    if (event.isDcfApplicable !== 'false') {
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        width: '34%',
        data: {
          message1: 'edit',
          surveyYear: this.instiFormData.value.surveyYearValue,
        },
      });
      return;
    }
    this.showEditTables = true;
    this.isEdit = true;
    this.showTable = false;
    this.id = event.id;
    this.collegeName = event.collegeName;
    this.collegeName1 = event.collegeName;
    this.collegeType = event.collegeType;
    this.districtName = event.districtName;
    this.isDcfApplicable = event.isDcfApplicable;
    // this.locationEdit=event.location;
    this.stateName = event.stateName;
    this.location = event.location;
    this.managementType = event.managementType;
    this.universityId = event.universityId;
    this.surveyYear1 = this.instiFormData.value.surveyYearValue;
    this.form.controls['surveyYearValue'].setValue(this.surveyYear1);
    this.form.controls['location'].setValue(this.location);
    this.form.controls['collegeType'].setValue(this.collegeType);
    this.form.controls['collegeName'].setValue(event?.collegeName);
    this.form.controls['districtName'].setValue(this.districtName);
    this.form.controls['remarks'].setValue('');
    this.form.controls['managementType'].setValue(event.managementID);
    this.form.controls['stateName'].setValue(this.stateName);
    this.form.controls['isDCFApplicable'].setValue(event.isDcf === 'true' ? true : false);

    this.getdistrictName();
  }
  compareFn(t1: any, t2: any): boolean {
    return t1 && t2 ? t1 === t2 : t1 === t2;
  }

  backClicked(): void {
    this.showEditTables = false;
    this.showTable = true;
    this.isEdit = false;
    this.submitted = false;
    this.form.reset();
  }

  submit() {
    this.checkForWhiteSpace();
    this.submitted = true;

    this.form.value.id = this.id;
    this.form.value.surveyYear =
      this.form.value.surveyYearValue.split('-')[0];

    if (this.form.value.remarks && this.form.value.districtName) {
      let stateName1 = this.userData.filter(
        (data: any) => data.name === this.form.value.stateName
      );

      let districtName1 = this.dataDistrict.filter(
        (person: any) => person.name === this.form.value.districtName
      );
      let collegeType1 = this.collegeTypeData.filter(
        (person: any) => person.type === this.form.value.collegeType
      );
      let MType1 = this.managmentData.filter(
        (person: any) =>
          person.id === this.form.value.managementType
      );
      // let locationData = this.locationData.filter(
      //   (locationData1: any) => locationData1.name === this.form.value.location
      // );

      if (
        this.collegeName1 === this.form.value.collegeName &&
        this.collegeType === this.form.value.collegeType &&
        this.stateName === this.form.value.stateName &&
        this.districtName === this.form.value.districtName &&
        this.managementType === this.form.value.managementType &&
        this.form.value.remarks && this.location === this.form.value.location
      ) {
        const dialogRef = this.dialog.open(ConfirmDialogComponent, {
          width: '20%',
          data: {
            message: 'Details of the Institute are same as previous!!!',
            s1: '12',
          },
        });

        return;
      }
      // let str = this.form.controls['collegeName'].value
      // const cleanStr = str.replace(/[\u200B\u200C\u200D\u00A0]/g, '');
      // this.form.get('collegeName')?.setValue(cleanStr)
      if (this.form.invalid) {
        this.sharedservice.showError(
          'Invalid details, Please provide valid details.'
        );
        return;
      }
      if (
        this.form.value.collegeName ||
        this.form.value.collegeType ||
        this.form.value.stateName ||
        this.form.value.districtName ||
        this.form.value.remarks ||
        this.form.value.managementType ||
        this.form.value.location
      ) {
        const dialogRef = this.dialog.open(ConfirmDialogComponent, {
          data: {
            message:
              'Details of the Institute are as below, Please confirm the details.',
            state: this.form.value.stateName,
            district: this.form.value.districtName,
            collegeType12: this.form.value.collegeType,
            managementType: MType1.length > 0 ? MType1[0]?.management : '',
            collegeName: this.form.value.collegeName.trim(),
            remarks: this.form.value.remarks.trim(),
            isDcfApplicable: this.form.value?.isDCFApplicable.toString(),
            // Location: this.form.value.location,
            s: '1',
          },
        });

        let userData = {
          districtCode: districtName1['0'].distCode,
          id: this.form.value.id,
          ipAddress: 'string',
          managementId: this.form.value.managementType
            ? MType1['0'].id
            : this.form.value.managementType,
          name: this.form.value.collegeName.trim(),
          remarks: this.form.value.remarks.trim(),
          // location: this.form.value.location?locationData[0].id:'',
          stateCode: stateName1['0'].stateCode,
          surveyYear: this.form.value.surveyYear,
          typeId: collegeType1['0'].id,
          userId: this.localService.getData('userId'),
          isDcfApplicable: this.form.value?.isDCFApplicable.toString(),
          universityId: this.universityId,
        };

        // console.log('userData', userData);
        //call save api accordingly
        dialogRef.afterClosed().subscribe((confirmed: boolean) => {
          if (confirmed) {
            this.institutionmanagement
              .postEditCollege(userData)
              .subscribe((res) => {
                if (res.statusCode == 'AISH011') {
                  this.sharedservice.showSuccessMessage(
                    'Editing College Data not allowed'
                  );
                  //this.form.reset();
                }
                if (res.statusCode == 'AISH102') {
                  this.sharedservice.showError(`College Name Already Exists with AISHE Code C-${res?.college?.id} in District ${districtName1['0'].name}`);
                  //this.form.reset();
                }
                if (res.statusCode == 'AISH001') {
                  this.sharedservice.showSuccessMessage(
                    'College Data Edited Successfully'
                  );
                  this.findDataEdit1();
                  this.backClicked();
                  //this.router.navigate(['/aishe/Institution-Management']);
                }
              });
          }
        });
      }
    }

  }

  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
    //this.getData1();
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
  checkForWhiteSpace() {
    let aremark = this.form.get('remarks')?.value?.trim();
    this.form.get('remarks')?.setValue(aremark);
  }
  surveyYear() {
    this.institutionmanagement.getSurveyYearListPrivate().subscribe((res) => {
      this.surveyYearList = res;
    });
  }
}
