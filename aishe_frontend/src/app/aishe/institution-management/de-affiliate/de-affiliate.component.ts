import { filter } from 'rxjs';
import {
  Component,
  Input,
  OnInit,
  OnChanges,
  SimpleChanges,
  AfterViewInit,
} from '@angular/core';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { SharedService } from 'src/app/shared/shared.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'app-de-affiliate',
  templateUrl: './de-affiliate.component.html',
  styleUrls: ['./de-affiliate.component.scss'],
})
export class DeAffiliateComponent implements OnInit,AfterViewInit  {
  @Input() instiFormData: any;
  @Input() DeAffiliateFormData: Boolean = false;
  dataDeaffiliation: any[] = [];
  reasonName: string[] = [];
  // form = new FormGroup({
  //   oldAffiliatedUniversity: new FormControl(''),
  //   newAffiliatedUniversity: new FormControl(''),
  //   reasonName: new FormControl('', Validators.required),
  //   reasonRemarks: new FormControl('', [Validators.maxLength(2000)]),
  // });
  form: FormGroup | any;
  isDeAffiliate: boolean = false;
  collegeName: any | null;
  stateName: any;
  searchText: any;
  districtName: any;
  id: any | null;
  submitted:boolean = false;
  filterDeaffiliation: any;
  surveyY: any;
  state: any;
  count: number = 0;
  universityList: any;
  UniSurveyYear: any;
  tabledata: boolean = false;
  userData1: any = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  page: number = 1;
  roleId: any;
  userData: any = [];
  tableSize: number[] = [10, 20, 30, 40, 50];

  usercollegeTypeData: any[] = [];
  dataUniversityId1: any[] = [];
  surveyYearList: any;
  selectedYearValue: any = '';
  universityId12: any;
  surveyYearValue: any;
  collegeTypeData: any;
  filteredcollegeTypeData: any;
  filtereduniversityList: any;
  showTable: boolean = false;
  EditData: boolean = false;
  constructor(
    public router: Router,
    private institutionmanagement: InstitutionmanagementService,
    public sharedservice: SharedService,
    public localService: LocalserviceService,
    private dialog: MatDialog,
    public fb: FormBuilder
  ) {
    this.roleId = this.localService.getData('roleId');
    //this.sharedservice.global_loader = true;
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      oldAffiliatedUniversity: [{ value: '', disabled: true }],
      newAffiliatedUniversity: [''],
      reasonName: ['', Validators.required],
      reasonRemarks: ['', [Validators.maxLength(2000)]],
      isInactiveUser:[true,[Validators.required]],
    });
    if (this.DeAffiliateFormData) {
      this.form.reset();
      // this.dataDeaffiliateDe();
      /// this.loadUniversityData();
      const reasonNameControl = this.form.controls['reasonName'];
      this.form.controls['newAffiliatedUniversity'].addValidators([Validators.required]);

      reasonNameControl.disable();
    //  reasonNameControl.clearValidators();
      reasonNameControl.updateValueAndValidity()
      this.form.controls['newAffiliatedUniversity'].updateValueAndValidity();
    } else {
      this.form.reset();
      this.dataDeaffiliate();
      this.form.controls['reasonRemarks'].addValidators([Validators.required]);
      this.form.controls['reasonRemarks'].updateValueAndValidity();
    }
    this.getDeaffiliationReason();
  }
  ngAfterViewInit() {
    setTimeout(() => {
      this.form.patchValue({ isInactiveUser: true });
    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  resetData1() {
    this.userData = [];
    this.searchText = null;
    this.page = 1;
    this.pageSize = 10;
    this.handlePageChange(this.page);
  }
  dataDeaffiliateDe() {//shift university
    this.resetData1();
    this.userData = [];
    this.searchText = null;
    var formData: any;
    // if (this.instiFormData.value.aisheCodeValue) {
    //   var aisheId = this.instiFormData.value.aisheCodeValue.replace(/\D/g, '');
    // }
    let aisheId = this.instiFormData.value.aisheCodeValue
      ? this.instiFormData.value.aisheCodeValue
      : '';
    if (!this.instiFormData.value.surveyYearValue) {
      this.sharedservice.showError('Please Select Survey Year');
      return;
    }
    if (this.localService.getData('roleId') === '7') {
      //UNO
      let aisheCode: any = this.localService.getData('aisheCode');
      let aisheCode1 = aisheCode;
      formData = {
        collegeType: this.instiFormData.value.collegeType
          ? this.instiFormData.value.collegeType
          : '',
        surveyYearValue: this.instiFormData.value.surveyYearValue
          ? this.instiFormData.value.surveyYearValue.split('-')[0]
          : '',
        universityValue: this.localService
          .getData('universityId')
          ?.split('-')[1],
        collegeLocation: this.instiFormData.value.collegeLocation
          ? this.instiFormData.value.collegeLocation
          : '',
        aisheCodeValue: aisheId,
        selectedStateCode: this.instiFormData.value.stateValue
          ? this.instiFormData.value.stateValue
          : '',
      };
    }
    if (this.localService.getData('roleId') === '6') {
      //SNO
      if (
        !this.instiFormData.value.universityValue &&
        !this.instiFormData.value.aisheCodeValue
      ) {
        this.sharedservice.showError('Please Select University');
        return;
      }
      formData = {
        collegeType: this.instiFormData.value.collegeType
          ? this.instiFormData.value.collegeType
          : '',
        surveyYearValue: this.instiFormData.value.surveyYearValue.split('-')[0],
        selectedStateCode: this.localService.getData('stateCode'),
        universityValue: this.instiFormData.value.universityValue
          ? this.instiFormData.value.universityValue.trim()
          : '',
        collegeLocation: this.instiFormData.value.collegeLocation
          ? this.instiFormData.value.collegeLocation
          : '',
        aisheCodeValue: aisheId,
      };
    }
    if (
      this.localService.getData('roleId') === '26' || //MoE
      this.localService.getData('roleId') === '1'
    ) {
      if (
        !this.instiFormData.value.stateValue &&
        !this.instiFormData.value.aisheCodeValue
      ) {
        this.sharedservice.showError('Please Select State');
        return;
      }
      if (
        !this.instiFormData.value.universityValue &&
        !this.instiFormData.value.aisheCodeValue
      ) {
        this.sharedservice.showError('Please Select University');
        return;
      }

      formData = {
        collegeType: this.instiFormData.value.collegeType,
        surveyYearValue: this.instiFormData.value.surveyYearValue
          ? this.instiFormData.value.surveyYearValue.split('-')[0]
          : '',
        selectedStateCode: this.instiFormData.value.stateValue
          ? this.instiFormData.value.stateValue.trim()
          : '',
        universityValue: this.instiFormData.value.universityValue
          ? this.instiFormData.value.universityValue.trim()
          : '',
        collegeLocation: this.instiFormData.value.collegeLocation
          ? this.instiFormData.value.collegeLocation
          : '',
        aisheCodeValue: aisheId,
      };
    }
    if (this.instiFormData.value.collegeLocation) {
      formData.selectedStateCode = this.instiFormData.value.stateValue
        ? this.instiFormData.value.stateValue
        : this.localService.getData('stateCode');
    }
    if (formData) {
      this.institutionmanagement
        .getinstitutionCollegeData1(formData)
        .subscribe((res) => {
          this.userData = res.universityCollegeListBean;
          if (res.statusCode == 'AISH099') {
            this.sharedservice.showError(res.statusDesc);
            this.showTable = true;
          }
          if (res.statusCode == 'AISH001') {
            this.showTable = true;
            this.EditData = false;
            this.isDeAffiliate = false;
            this.page = 1;
            this.handlePageChange(this.page);
          }
          if (res.statusCode == 'AISH002') {
            this.sharedservice.showError(res.statusDesc);
          }
        });
    }
  }

  dataDeaffiliate() {
    this.resetData1();
    this.userData = [];
    this.searchText = null;
    var formData: any;
    // if (this.instiFormData.value.aisheCodeValue) {
    //   var aisheId = this.instiFormData.value.aisheCodeValue.replace(/\D/g, '');
    // }
    let aisheId = this.instiFormData.value.aisheCodeValue
      ? this.instiFormData.value.aisheCodeValue
      : '';
    if (!this.instiFormData.value.surveyYearValue) {
      this.sharedservice.showError('Please Select Survey Year');
      return;
    }
    if (this.localService.getData('roleId') === '7') {
      //UNO
      let aisheCode: any = this.localService.getData('aisheCode');
      let aisheCode1 = aisheCode;
      formData = {
        collegeType: this.instiFormData.value.collegeType
          ? this.instiFormData.value.collegeType
          : '',
        surveyYearValue: this.instiFormData.value.surveyYearValue
          ? this.instiFormData.value.surveyYearValue.split('-')[0]
          : '',
        universityValue: this.localService
          .getData('universityId')
          ?.split('-')[1],
        collegeLocation: this.instiFormData.value.collegeLocation
          ? this.instiFormData.value.collegeLocation
          : '',
        aisheCodeValue: aisheId,
        selectedStateCode: this.instiFormData.value.stateValue
          ? this.instiFormData.value.stateValue
          : '',
      };
    }
    if (this.localService.getData('roleId') === '6') {
      //SNO
      if (
        !this.instiFormData.value.universityValue &&
        !this.instiFormData.value.aisheCodeValue
      ) {
        this.sharedservice.showError('Please Select University');
        return;
      }
      formData = {
        collegeType: this.instiFormData.value.collegeType
          ? this.instiFormData.value.collegeType
          : '',
        surveyYearValue: this.instiFormData.value.surveyYearValue.split('-')[0],
        selectedStateCode: this.localService.getData('stateCode'),
        universityValue: this.instiFormData.value.universityValue
          ? this.instiFormData.value.universityValue.trim()
          : '',
        collegeLocation: this.instiFormData.value.collegeLocation
          ? this.instiFormData.value.collegeLocation
          : '',
        aisheCodeValue: aisheId,
      };
    }
    if (
      this.localService.getData('roleId') === '26' || //MoE
      this.localService.getData('roleId') === '1'
    ) {
      if (
        !this.instiFormData.value.stateValue &&
        !this.instiFormData.value.aisheCodeValue
      ) {
        this.sharedservice.showError('Please Select State');
        return;
      }
      if (
        !this.instiFormData.value.universityValue &&
        !this.instiFormData.value.aisheCodeValue
      ) {
        this.sharedservice.showError('Please Select University');
        return;
      }

      formData = {
        collegeType: this.instiFormData.value.collegeType,
        surveyYearValue: this.instiFormData.value.surveyYearValue
          ? this.instiFormData.value.surveyYearValue.split('-')[0]
          : '',
        selectedStateCode: this.instiFormData.value.stateValue
          ? this.instiFormData.value.stateValue.trim()
          : '',
        universityValue: this.instiFormData.value.universityValue
          ? this.instiFormData.value.universityValue.trim()
          : '',
        collegeLocation: this.instiFormData.value.collegeLocation
          ? this.instiFormData.value.collegeLocation
          : '',
        aisheCodeValue: aisheId,
      };
    }
    if (this.instiFormData.value.collegeLocation) {
      formData.selectedStateCode = this.instiFormData.value.stateValue
        ? this.instiFormData.value.stateValue
        : this.localService.getData('stateCode');
    }
    if (formData) {
      this.institutionmanagement
        .getinstitutionCollegeData1(formData)
        .subscribe((res) => {
          this.userData = res.universityCollegeListBean;
          if (res.statusCode == 'AISH099') {
            this.sharedservice.showError(res.statusDesc);
            this.showTable = true;
          }
          if (res.statusCode == 'AISH001') {
            this.showTable = true;
            this.EditData = false;
            this.isDeAffiliate = false;
            this.page = 1;
            this.handlePageChange(this.page);
          }
          if (res.statusCode == 'AISH002') {
            this.sharedservice.showError(res.statusDesc);
          }
        });
    }
  }

  getDeaffiliationReason() {
    this.institutionmanagement.getReasonDeaffileate().subscribe((res) => {
      if (this.DeAffiliateFormData) {
       this.form.get('reasonName').setValue(1)
       //this.form.controls['reasonName'].setValue(1)
        const data = res.reasonId.slice();
        const res1 = data.filter((item: any) => item.id === 1);
        this.dataDeaffiliation = res1;
        this.filterDeaffiliation = this.dataDeaffiliation.slice();
      } else {
        this.dataDeaffiliation = res.reasonId;
        this.filterDeaffiliation = this.dataDeaffiliation.slice();
      }
    });
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
    var a = Math.ceil(this.userData.length / fgh);
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + fgh, this.userData.length);
    } else {
      this.pageData = Math.min(this.StartLimit + fgh, this.userData.length);
    }
  }

  back(): void {
    this.showTable = true;
    this.EditData = false;
    this.isDeAffiliate = false;
   // this.form.reset();
    this.form.controls['newAffiliatedUniversity'].setValue('')
    this.DeAffiliateFormData?this.form.controls['reasonName'].setValue(1):this.form.controls['reasonName'].setValue('');
  }
  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  deAffiliate(data: any) {


    if (data.isDcfApplicable != 'false') {
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        width: '34%',
        data: {
          message1: 'De-Affiliate',
          surveyYear: this.instiFormData.value.surveyYearValue,
        },
      });
      return;
    }
    this.submitted = false;
    this.form.controls['newAffiliatedUniversity'].setValue('')
    this.isDeAffiliate = true;
    this.showTable = false;
    this.EditData = true;
    this.collegeName = data.collegeName;
    this.districtName = data.districtName;
    this.stateName = data.stateName;
    this.surveyY = this.instiFormData.value.surveyYearValue;
    this.id = data.id;
    this.DeAffiliateFormData?this.form.controls['reasonName'].setValue(1):this.form.controls['reasonName'].setValue('');
    this.form.controls['reasonRemarks'].setValue('');
    this.universityId12 = data.universityId;
    this.loadUniversityData();
    this.form.controls['oldAffiliatedUniversity'].setValue(data.universityName);
  }

  update() {
    if (this.form.invalid) {
      this.sharedservice.showError('All fields are required');
      this.submitted=true;
      return;
    }
    this.checkForWhiteSpace();
    this.submitted = false;
    if (this.form.valid) {
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        width: '22%',
        data: {
          message1: this.DeAffiliateFormData
            ? 'Are you sure, you want to shifted to Other University?'
            : 'Are you sure, you want to De-Affiliate?',
          deAffiliate: 'deAffiliate',
        },
      });
      dialogRef.afterClosed().subscribe((confirm) => {
        if (confirm) {
          this.submitted = false;
          let data = {};
          if (this.DeAffiliateFormData) {
            //const c = 'C';
            data = {
              collegeAisheCode: 'C-' + this.id,
              reasonId: 1,
              surveyYear: this.surveyY ? this.surveyY.split('-')[0] : '',
              universityAisheCode:
                'U-' + this.form.value.newAffiliatedUniversity,
              roleId: 1,
            };
            this.institutionmanagement.postDeaffiliateData1(data).subscribe(
              (res) => {
                if (res.status === 401) {
                  this.sharedservice.showSuccessMessage(res.message);
                }
                if (res.status === 200) {

                  this.showTable = true;
                  this.EditData = false;
                  this.isDeAffiliate = false;
                  this.submitted = false;
                  this.dataDeaffiliate();
                  this.sharedservice.showSuccessMessage(res.message);
                }
                if (res.statusCode == 'AISH001') {
                  this.sharedservice.showSuccessMessage(
                    'Deaffiliated Successfully'
                  );
                  this.showTable = true;
                  this.EditData = false;
                  this.isDeAffiliate = false;
                  this.dataDeaffiliate();
                }
                if (res.statusCode == 'AISH011') {
                  this.sharedservice.showError('This institution has already participated in the Current DCF Survey');
                }
                if (res.statusCode == 'AISH027') {
                  this.sharedservice.showError(res.statusDesc);
                }
                if (res.statusCode == 'AISH099') {
                  this.sharedservice.showError(res.statusDesc);
                }
                if (res.statusCode == 'AISH002') {
                  this.sharedservice.showError(res.statusDesc);
                }
              },
              (err) => {
                if (err.error.status === 401) {
                  this.sharedservice.showError(err.error.message);
                }
              }
            );
          } else if (!this.DeAffiliateFormData) {
            data = {
              id: this.id,
              reasonId: this.form.value.reasonName,
              remarks: this.form.value.reasonRemarks,
              isInactiveUser:this.form.value.isInactiveUser,
              surveyYear: this.surveyY ? this.surveyY.split('-')[0] : '',
              userId: this.localService.getData('userId'),
              universityId: this.localService.getData('id'),
            };

            this.institutionmanagement
              .postDeaffiliateData(data)
              .subscribe((res) => {
                if (res.status === 200) {
                  this.sharedservice.showSuccessMessage(res.message);
                }
                if (res.statusCode == 'AISH001') {
                  this.sharedservice.showSuccessMessage(
                    'Deaffiliated Successfully'
                  );
                  this.showTable = true;
                  this.EditData = false;
                  this.isDeAffiliate = false;
                  this.dataDeaffiliate();
                }
                if (res.statusCode == 'AISH011') {
                  this.sharedservice.showError('This institution has already participated in the Current DCF Survey');
                }
                if (res.statusCode == 'AISH027') {
                  this.sharedservice.showError(res.statusDesc);
                }
                if (res.statusCode == 'AISH099') {
                  this.sharedservice.showError(res.statusDesc);
                }
                if (res.statusCode == 'AISH002') {
                  this.sharedservice.showError(res.statusDesc);
                }
              });
          }
        }
      });
    } else {
      this.sharedservice.showError('All fields are required');
    }
  }
  checkForWhiteSpace() {
    let aremark = this.form.get('reasonRemarks')?.value?.trim();
    this.form.get('reasonRemarks')?.setValue(aremark);
  }

  loadUniversityData() {
    this.institutionmanagement.getUniversity('', 0).subscribe((res) => {
      if (res) {
        const ds = res;
        this.form.controls['oldAffiliatedUniversity'];
        const data = ds.slice();
        const temp = data.filter(
          (item: any) => item.id !== this.universityId12
        );
        this.universityList = temp;
        this.filtereduniversityList = this.universityList.slice();
      }
    });
  }
}
