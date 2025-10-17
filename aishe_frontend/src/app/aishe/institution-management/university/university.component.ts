import { filter } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import {
  FormGroup,
  FormControl,
  AbstractControl,
  Validators,
  NgForm,
  FormGroupDirective,
} from '@angular/forms';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import { SidebarComponent } from 'src/app/sidebar/sidebar.component';
import { UniversityViewComponent } from '../university-view/university-view.component';
import { UniversityEditComponent } from '../university-edit/university-edit.component';
import { UniversityDeleteComponent } from '../university-delete/university-delete.component';
import { ConverttoCollegeComponent } from '../convertto-college/convertto-college.component';
import { Common } from 'src/app/common/common';
import { InsitutionalReportComponent } from '../institutional-report/institutional-report.component';
import { ActiveinactiveuniversityComponent } from '../activeinactiveuniversity/activeinactiveuniversity.component';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { ActivatedRoute, Router } from '@angular/router';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
@Component({
  selector: 'app-university',
  templateUrl: './university.component.html',
  styleUrls: ['./university.component.scss'],
})
export class UniversityComponent implements OnInit {
  @ViewChild('view')
  view?: UniversityViewComponent;

  @ViewChild('Reports')
  showReport?: InsitutionalReportComponent;

  @ViewChild('ConvertCollege')
  ConvertCollege?: ConverttoCollegeComponent;

  @ViewChild('status')
  status1?: ActiveinactiveuniversityComponent;

  @ViewChild('datasidebar') //to get data
  datasidebar?: SidebarComponent;

  @ViewChild('edit') //to get data list component reference
  edit?: UniversityEditComponent;
  surveyYearAllList: any;
  universityCode: any;
  @ViewChild('delete')
  delete?: UniversityDeleteComponent;

  showEdit: boolean = false;
  //vregex = "[a-zA-Z ]";
  addUniversityform = new FormGroup({
    surveyYear: new FormControl('', Validators.required),
    name: new FormControl('', [
      Validators.required,
      Validators.maxLength(255),
      Common.noWhitespaceValidator,
      Validators.pattern('^[a-zA-Z ]+$'),
    ]),
    typeId: new FormControl('', Validators.required),
    subTypeId: new FormControl('', []),
    stateCode: new FormControl('', Validators.required),
    districtCode: new FormControl('', Validators.required),
    location: new FormControl('', [Validators.required,]),
    remarks: new FormControl('', [
      Validators.required,
      Validators.maxLength(2000),
      // Validators.minLength(3),
      Common.noWhitespaceValidator,
    ]),
    isDCFApplicable: new FormControl(false, [Validators.requiredTrue]),

    headOfficerDesignation: new FormControl(''),
    headOfficerEmail: new FormControl('', [
      Validators.required,
      Validators.email,
    ]),
    headOfficerGender: new FormControl('',[Validators.required]),
    headOfficerMobile: new FormControl('', [
      Validators.required,
      Validators.maxLength(10),
      Validators.pattern('^[0-9]+$'),
    ]),
    headOfficerName: new FormControl('',[Validators.pattern('^[a-zA-Z ]+$'),Validators.required]),
    headOfficerTelephone: new FormControl('', [

    ]),

    nodalOfficerDesignation: new FormControl('',[Validators.required]),
    nodalOfficerEmail: new FormControl('', [
      Validators.required,
      Validators.email,
    ]),
    nodalOfficerGender: new FormControl('',[Validators.required]),
    nodalOfficerMobile: new FormControl('', [
      Validators.required,
      Validators.maxLength(10),
      Validators.pattern('^[0-9]+$'),
    ]),
    nodalOfficerName: new FormControl('',[Validators.pattern('^[a-zA-Z ]+$'),Validators.required]),
    nodalOfficerTelephone: new FormControl('', []),

    addressLine1: new FormControl('',Validators.required),
    addressLine2: new FormControl(''),
    blockId: new FormControl(''),
    city: new FormControl('',[Validators.required]),
    ownerShipId: new FormControl('',[Validators.required]),
    subDistrictId: new FormControl('',[Validators.required]),
    ulbId: new FormControl(''),
    website: new FormControl('',[Validators.maxLength(255)]),
    pinCode: new FormControl('',[Validators.required,Validators.pattern('^[0-9]+$')]),
  });

  userData: any[] = [];
  dataDistrict: any[] = [];
  collegeTypeData: any[] = [];
  selectedIndexView: boolean = false;
  selectedIndexAdd: boolean = false;
  collegeName: any | null;
  collegeType: any | null;
  districtName: any | null;
  stateName: any | null;
  filteredState: any;
  filteredDistrict: any;
  selectedIndexUniversityDelete: any = false;
  filteredcollegeTypeData: any;
  submitted: boolean = false;
  stateList: any;
  pageSize: any = 10;
  searchText: any;
  tableSize: number[] = [0, 10, 20, 30, 40, 50];
  universityType: any;
  universityTypefilter: any;
  selectedIndex: any = 0;
  validation: boolean = false;
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  selectedIndexEdit: boolean = false;
  page: number = 1;
  stateListfilter: any[] = [];
  showReportIndex: boolean = false;
  selectedIndexStatus: boolean = false;
  selectedIndexView11: any;
  surveyYearList: any;
  selectedYearValue: any = '';
  surveyYearValue: any = '';
  UniSurveyYear: any = null;
  state: any;
  surveyYearOption: any;
  filterSurveyYearOption: any[] = [];
  userId: any = null;
  standalonTypeData: any;
  standalonTypefilter: any[] = [];
  universityList: any = [];
  filtereduniversityList: any;
  locationData: any;
  selectedIndexConvertCollege: boolean = false;
  accessibilityData1: any;
  roleId: any;
  type: string = 'University';
  listGender: any;
  listOwnership: any;
  listUrban: any;
  listBlock: any;
  subDistrictList: any;
  variable2: any;
  arr: string[] = [];
  subtype:any;
  subtypefilter:any;
  instiForm = new FormGroup({
    searchText: new FormControl(''),
    aisheCodeValue: new FormControl(),
    surveyYearValue: new FormControl('', Validators.required),
    stateValue: new FormControl('', Validators.required),
    universityType: new FormControl(),
    districtname: new FormControl(),
    RequestId: new FormControl(),
    status: new FormControl('', Validators.required),
    toDate: new FormControl(''),
    fromDate: new FormControl(''),
  });
  @ViewChild(FormGroupDirective) formDirective!: FormGroupDirective;
  @ViewChild('form', { static: false }) form!: NgForm;
  constructor(
    public http: HttpClient,
    private dialog: MatDialog,
    public sharedservice: SharedService,
    private surveyyearservice: SurveyyearService,
    private institutionmanagement: InstitutionmanagementService,
    public localService: LocalserviceService,
    private router: Router,
    private route: ActivatedRoute,
    public localStore1: EncryptDecrypt,
    public authService: AuthService
  ) {
    this.sharedservice.global_loader = true;
  }

  ngOnInit() {
    let universityCode = this.route.snapshot.paramMap.get('id');
    if (universityCode) {
      this.universityCode = this.localStore1?.getDecryptedValue(universityCode);
     // console.log(this.universityCode);
      if (this.universityCode) {
        this.viewurlData();
      }
    }

    this.roleId = this.localService.getData('roleId');
    this.userId = this.localService.getData('userId');

    this.surveyYear();
    this.loadSurveyYear();
    this.getLocation();
    this.loadState();
    this.loadUniversityType();
    this.getInstitutionManagementRole();
    this.getGender1();
    this.getOwnership1();
  }
  isInvalid(controlName: string): boolean {
    const control = this.addUniversityform.controls[controlName];
    return control.invalid && (control.touched || this.submitted);
  }
  getGender1() {
    this.authService.getGender().subscribe((res) => {
      this.listGender = res;
    });
  }
  getOwnership1() {
    this.authService.getOwnership('U').subscribe((res: any) => {
      this.listOwnership = res;
    });
  }
  getUrban1(e: any) {
    this.authService.getUrban(e.distCode).subscribe((res: any) => {
      this.listUrban = res;
    });
  }
  getBlock1(e: any) {
    this.authService.getBlock(e.distCode).subscribe((res: any) => {
      this.listBlock = res;
    });
  }
  getSubDistrict(e: any) {
    let payload = {
      //lgdStateCode: e.stateLgdId,
      lgdDistrictCode: e.lgdDistCode,
    };
    this.authService.getSubDistrictList(payload).subscribe(
      (res) => {
        this.variable2 = res;
        this.subDistrictList = this.variable2.slice();
        if (this.subDistrictList.length === 0) {
          this.subDistrictList.push({ id: 99999, name: 'Not Applicable' });
        }
      },
      (err) => {}
    );
  }
  getInstitutionManagementRole() {
    this.sharedservice.sidebarAccessibility.subscribe((res) => {
      if (res) {
        this.accessibilityData1 = res[0];
      }
    });
  }
  getLocation() {
    this.institutionmanagement.getLocation().subscribe((res) => {
      this.locationData = res.location;
    });
  }
  get f() {
    return this.addUniversityform.controls;
  }
  get f1(): { [key: string]: AbstractControl } {
    return this.instiForm.controls;
  }
  tabIndex = 0;

  tabSelected(event: any) {
    // console.log(event.index)
    this.selectedIndex = event.index;
    if (this.selectedIndex === 0) {
      this.selectedIndexView = false;
      this.selectedIndexConvertCollege = false;
      this.showReportIndex = false;
      this.selectedIndexStatus = false;
      this.selectedIndexEdit = false;
      this.selectedIndexUniversityDelete = false;
    }
    if (this.selectedIndex === 1) {
      this.submitted = false;
      this.clearValidation();
      this.instiForm.reset();
      this.addUniversityform.reset();
      this.universityList = '';
      this.selectedIndexConvertCollege = false;
      this.showReportIndex = false;
      this.selectedIndexStatus = false;
      this.selectedIndexEdit = false;
      this.selectedIndexUniversityDelete = false;
    }
    if (this.selectedIndex === 1) {
      this.clearValidation();
      this.submitted = false;
      this.instiForm.reset();
      this.addUniversityform.reset();
      this.showReportIndex = false;
      this.selectedIndexStatus = false;
      this.selectedIndexView = false;
      this.selectedIndexConvertCollege = false;
      this.selectedIndexAdd = false;
      this.selectedIndexUniversityDelete = false;
    }
    if (this.selectedIndex === 2) {
      this.clearValidation();
      this.addUniversityform.reset();
      this.formDirective.control.markAsUntouched;
      this.formDirective.resetForm();
      this.form.resetForm();
      this.selectedIndexConvertCollege = false;
      this.selectedIndexView = false;
      this.selectedIndexEdit = false;
      this.showReportIndex = false;
      this.selectedIndexStatus = false;
      this.selectedIndexUniversityDelete = false;
    }
    if (this.selectedIndex === 3) {
      this.clearValidation();
      this.submitted = false;
      this.instiForm.reset();
      this.addUniversityform.reset();
      this.selectedIndexConvertCollege = false;
      this.selectedIndexUniversityDelete = false;
      this.selectedIndexAdd = false;
      this.showReportIndex = false;
      this.selectedIndexStatus = false;
      this.selectedIndexView = false;
      this.selectedIndexEdit = false;
    }
    if (this.selectedIndex === 4) {
      this.surveyYearAll();
      this.clearValidation();
      this.submitted = false;
      this.instiForm.reset();
      this.addUniversityform.reset();
      this.showReportIndex = false;
      this.selectedIndexStatus = false;
      this.selectedIndexConvertCollege = false;
      this.selectedIndexUniversityDelete = false;
      this.selectedIndexAdd = false;
      this.selectedIndexView = false;
      this.selectedIndexEdit = false;
    }
    if (this.selectedIndex === 5) {
      this.surveyYearAll();
      this.showReportIndex = true;
      this.selectedIndexStatus = false;
      this.clearValidation();
      this.submitted = false;
      this.instiForm.reset();
      this.addUniversityform.reset();
      this.selectedIndexConvertCollege = false;
      this.selectedIndexUniversityDelete = false;
      this.selectedIndexAdd = false;
      this.selectedIndexView = false;
      this.selectedIndexEdit = false;
    }
    if (this.selectedIndex === 6) {
      this.surveyYearAll();
      // this.loadSurveyYearInactive();
      this.showReportIndex = false;
      this.selectedIndexStatus = true;
      this.clearValidation();
      this.submitted = false;
      this.instiForm.reset();
      this.addUniversityform.reset();
      this.selectedIndexConvertCollege = false;
      this.selectedIndexUniversityDelete = false;
      this.selectedIndexAdd = false;
      this.selectedIndexView = false;
      this.selectedIndexEdit = false;
    }
  }

  clearValidation() {
    this.instiForm.controls['surveyYearValue'].clearValidators();
    this.instiForm.controls['stateValue'].clearValidators();
    this.instiForm.controls['status'].clearValidators();
    this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
    this.instiForm.controls['stateValue'].updateValueAndValidity();
    this.instiForm.controls['status'].updateValueAndValidity();
  }
  updateValidator() {
    if (this.instiForm.controls['searchText'].value == null) {
      this.instiForm.controls['surveyYearValue'].setValidators(
        Validators.required
      );
      this.instiForm.controls['status'].setValidators(Validators.required);
      if (this.selectedIndex == 0 || this.selectedIndex == 1) {
        this.instiForm.controls['stateValue'].clearValidators();
        this.instiForm.controls['stateValue'].updateValueAndValidity();
      } else {
        this.instiForm.controls['stateValue'].setValidators(
          Validators.required
        );
        this.instiForm.controls['stateValue'].updateValueAndValidity();
      }

      this.instiForm.controls['surveyYearValue'].updateValueAndValidity();

      this.instiForm.controls['status'].updateValueAndValidity();
    }
  }
  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
  surveyYear() {
    this.institutionmanagement.getSurveyYearListPrivate().subscribe((res) => {
      this.surveyYearList = res;
    });
  }
  surveyYearAll() {
    this.institutionmanagement.getYearList().subscribe((res) => {
      this.surveyYearAllList = res;
    });
  }
  loadSurveyYearInactive() {
    this.surveyyearservice.getdatasurveyyear().subscribe((res) => {
      let obj = { surveyYear: '2022', surveyYearValue: '2022-23' };
      // res.unshift(obj)
      this.surveyYearOption = res;

      this.filterSurveyYearOption = this.surveyYearOption.slice();
    });
  }

  getInstitutionTypeData() {
    this.institutionmanagement
      .getStandalonInstitutionTypeData()
      .subscribe((res) => {
        this.standalonTypeData = res;
        this.standalonTypefilter = this.standalonTypeData.slice();
      });
  }

  loadSurveyYear() {
    this.institutionmanagement.getYearList().subscribe({
      next: (res) => {
        this.surveyYearValue = res;
        //   if(this.selectedSurveyYear){
        //   this.surveyYearValue.splice(0,0,this.selectedSurveyYear);
        // }
      },
    });
  }
  loadState() {
    this.institutionmanagement.getStatePrivate().subscribe((res) => {
      this.stateList = res;
      this.stateListfilter = this.stateList.slice();
    });
  }

  temp: any = [];
  loadUniversityType(): void {
    this.institutionmanagement.getUniversityType().subscribe((res: any[]) => {
      this.universityType = res?.filter(({ id }) => id !== '12') || [];
      this.universityTypefilter = [...this.universityType];
      this.temp = [...res];
    });
  }

loadSubType(e: any): void {
  this.arr = e.id === "01" ? ["07", "12"] : e.id === "07" ? ["01", "12"] : [];
  const data = this.temp?.filter((element: any) => this.arr.includes(element.id)) || [];
   this.subtype=[...data]
}

  loadUniversity() {
    this.UniSurveyYear =
      this.instiForm.value.surveyYearValue.surveyYearValue?.split('-')[0];

    this.state = this.instiForm.value.stateValue;
    if (this.UniSurveyYear && this.state) {
      this.institutionmanagement
        .getUniversity(this.state, this.UniSurveyYear)
        .subscribe({
          next: (res) => {
            this.universityList = res;
          },
        });
    }
  }
  key: any;

  alphaNumberOnly(e: any) {
    // Allow only alphanumeric characters (letters and numbers), no special characters except space
    var regex = new RegExp('^[a-zA-Z0-9 ]$');
    var str = String.fromCharCode(e.which || e.keyCode);

    if (regex.test(str)) {
      // Prevent consecutive spaces
      if (e.target.value.substr(-1) === ' ' && e.code === 'Space') {
        e.preventDefault();
      }
      return true;
    }

    e.preventDefault();
    return false;
  }

  checkstuff(event: any) {
    if (event.target.value.substr(-1) === ' ' && event.code === 'Space') {
      event.preventDefault();
    }

    //  if ((this.key >= 15 && this.key <= 64) || (this.key >= 123) || (this.key >= 96 && this.key <= 105)) {
    //   event.preventDefault();
    // }
  }

  getdistrictName() {
    if (this.addUniversityform.value.stateCode) {
      this.institutionmanagement
        .getdistrict(this.addUniversityform.value.stateCode.stateCode)
        .subscribe((res) => {
          this.dataDistrict = res;
          this.filteredDistrict = this.dataDistrict.slice();
        });
    }
  }
  checkForWhiteSpace() {
    let aremark = this.addUniversityform.get('remarks')?.value?.trim();
    this.addUniversityform.get('remarks')?.setValue(aremark);
  }
  //submit form to university data
  addUniversity() {
    this.checkForWhiteSpace();
    if (this.validation == true) {
      this.addUniversityform.reset();
      this.validation = false;
    }
    this.submitted = true;
    if (this.addUniversityform.invalid) {
      this.sharedservice.showError(
        'Invalid details, Please provide valid details.'
      );
      return;
    }

    if (this.addUniversityform.valid) {
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        data: {
          message:
            'Details of the Institute are as below, Please confirm the details.',
          state: this.addUniversityform.value.stateCode.name,
          district: this.addUniversityform.value.districtCode.name,
          Name: this.addUniversityform.value.name.trim(),
          remarks: this.addUniversityform.value.remarks.trim(),
          university: this.addUniversityform.value.typeId.type,
          Location: this.addUniversityform.value.location,
          surveyYear: this.addUniversityform.value.surveyYear
            .slice(0, 4)
            .trim(),
          isDCFApplicable: this.addUniversityform.value.isDCFApplicable
            ? this.addUniversityform.value.isDCFApplicable
            : 'false',
          universityType: 'addUniversityType',
        },
      });
      let formData = this.addUniversityform.value;

      //call save api accordingly
      let userData = {
        name: this.addUniversityform.value.name ?? '',
        stateCode: formData?.stateCode?.stateCode ?? '',
        districtCode: formData?.districtCode?.distCode ?? '',
        typeId: formData?.typeId?.id?.toString() ?? '',
        surveyYear: formData?.surveyYear ? formData.surveyYear.slice(0, 4).trim() : '',
        remarks: formData?.remarks ?? '',
        location: this.addUniversityform.value.location ?? '',
        isDCFApplicable: formData?.isDCFApplicable
          ? this.addUniversityform.value?.isDCFApplicable ?? 'false'
          : 'false',
        userId: this.userId ?? '',

        addressLine1: this.addUniversityform.value.addressLine1 ?? '',
        addressLine2: this.addUniversityform.value.addressLine2 ?? '',
        blockId: +this.addUniversityform.value?.blockId?.id || 0,
        city: this.addUniversityform.value.city ?? '',

        headOfficerDesignation: this.addUniversityform.value.headOfficerDesignation ?? '',
        headOfficerEmail: this.addUniversityform.value.headOfficerEmail ?? '',
        headOfficerGender: this.addUniversityform.value.headOfficerGender ?? '',
        headOfficerMobile: this.addUniversityform.value.headOfficerMobile ?? '',
        headOfficerName: this.addUniversityform.value.headOfficerName ?? '',
        headOfficerTelephone: this.addUniversityform.value.headOfficerTelephone ?? '',
        subTypes: this.addUniversityform.value?.subTypeId?.map((num:any)=> num.id) ?? [],

        nodalOfficerDesignation: this.addUniversityform.value.nodalOfficerDesignation ?? '',
        nodalOfficerEmail: this.addUniversityform.value.nodalOfficerEmail ?? '',
        nodalOfficerGender: this.addUniversityform.value.nodalOfficerGender ?? '',
        nodalOfficerMobile: this.addUniversityform.value.nodalOfficerMobile ?? '',
        nodalOfficerName: this.addUniversityform.value.nodalOfficerName ?? '',
        nodalOfficerTelephone: this.addUniversityform.value.nodalOfficerTelephone ?? '',

        ownerShipId: this.addUniversityform.value.ownerShipId?.id?.toString() ?? '',
        pinCode: this.addUniversityform.value.pinCode ?? '',
        subDistrictId: this.addUniversityform.value.subDistrictId?.id ?? '',
        ulbId: this.addUniversityform.value.ulbId?.id ?? '',
        website: this.addUniversityform.value?.website ?? '',
      };

      console.log(userData);
      dialogRef.afterClosed().subscribe((confirmed: boolean) => {
        if (confirmed) {
          this.institutionmanagement
            .postUniversityAddData(userData)
            .subscribe((res) => {
              this.submitted = false;
              this.validation = true;
              if (res.statusCode == 'AISH011') {
                this.sharedservice.showError(
                  'Adding University Data not allowed'
                );
                //this.addUniversityform.reset();
              }
              if (res.statusCode == 'AISH102') {
                this.validation = false;
                this.sharedservice.showError('University Name Already Exists');
              }
              if (res.statusCode == 'AISH001'|| res.statusCode==='AISH100') {
                this.addUniversityform.reset();
                this.createValidationForm();
                this.sharedservice.showSuccessMessage(
                  'University Data Added Successfully'
                );
                //{"statusCode":"AISH001","newAisheCode":{"Name: ":"Adv","AisheCode: ":"1335"}}

                const dialogRef = this.dialog.open(ConfirmDialogComponent, {
                  data: {
                    message1: 'Your request has been processed successfully!!',
                    dataUniversity: res.newAisheCode,
                    newAisheCode: 'aisheCodeNow',
                  },
                });
              }
              if (res.statusCode == 'AISH002') {
                this.validation = false;
                this.sharedservice.showError(res.statusDesc);
              }
            });
        }
      });
    }
  }
  reports() {
    alert('reports');
    this.showReportIndex = true;
  }
  viewurlData() {
    this.instiForm.get('searchText')?.setValue(this.universityCode);
    this.findOnView();
  }
  findOnView() {
    this.selectedIndexView = true;
    this.updateValidator();
    this.view?.findUniversityData();
  }
  findOnEdit() {
    this.updateValidator();
    this.edit?.findData();
    this.selectedIndexEdit = true;
  }
  deleteUniversity() {
    this.updateValidator();
    this.selectedIndexUniversityDelete = true;
    this.delete?.findData();
  }

  createValidationForm() {
    Object.keys(this.addUniversityform.controls).forEach((key) => {
      const control = this.addUniversityform.controls[key];
      control.setErrors(null);
    });
  }
  converttoColleg() {
    this.updateValidator();
    this.ConvertCollege?.findData();
    this.selectedIndexConvertCollege = true;
  }

  findReport(reportType: any) {
    this.updateValidator();
    this.showReport?.findData(reportType);
    this.showReportIndex = true;
  }

  findOnStatus() {
    this.updateValidator();
    this.status1?.findData();
    this.selectedIndexStatus = true;
  }
  searchTextValue() {
    //  console.log(this.instiForm.controls['searchText'].value)
    if (this.instiForm.controls['searchText'].value.length > 0) {
      this.instiForm.controls['stateValue'].clearValidators();
      this.instiForm.controls['stateValue'].updateValueAndValidity();

      this.instiForm.controls['surveyYearValue'].clearValidators();
      this.instiForm.controls['surveyYearValue'].updateValueAndValidity();
    } else {
      this.updateValidator();
    }
  }

  isLocation(value: any) {
   // this.loadState();
    this.addUniversityform.get('districtCode')?.reset();
    this.addUniversityform.get('subDistrictId')?.reset()
    this.addUniversityform.get('stateCode')?.reset()

    if (value === 'R') {
      this.addUniversityform.get('blockId')?.setValidators([Validators.required])
      this.addUniversityform.get('ulbId')?.clearValidators()
    } else {
      this.addUniversityform.get('ulbId')?.setValidators([Validators.required])
      this.addUniversityform.get('blockId')?.clearValidators()
    }
    this.addUniversityform.get('blockId')?.updateValueAndValidity()
    this.addUniversityform.get('ulbId')?.updateValueAndValidity()
  }
}
