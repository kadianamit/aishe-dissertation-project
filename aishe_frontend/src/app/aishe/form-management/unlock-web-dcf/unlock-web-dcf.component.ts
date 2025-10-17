import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { GetService } from 'src/app/service/get/get.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { PostService } from 'src/app/service/post/post.service';
import { SharedService } from 'src/app/shared/shared.service';
import { environment } from 'src/environments/environment';
//import {ConfirmDialogComponent} from 'src/app/dialog/confirm-dialog/confirm-dialog.component'
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
@Component({
  selector: 'app-unlock-web-dcf',
  templateUrl: './unlock-web-dcf.component.html',
  styleUrls: ['./unlock-web-dcf.component.scss'],
})
export class UnlockWebDCFComponent implements OnInit {
  blob: any;
  variables: any;
  stateList: any = [];
  institutionTypeUn: any;
  institutionTypeDele: any;
  selectedIndex: number = 0;
  reportLog!: FormGroup;
  unlockWebDCF: FormGroup;
  sepcialPermissionsForm!: FormGroup;
  deleteSurveyYears!: FormGroup;
  isFormInvalid: boolean = false;
  hideButton: boolean = false;
  isEligible: boolean = true;
  showAisheCodeInputField: boolean = false;
  roleId: string | null;
  surveyYear: any = [];
  minlength: string = 'Please enter minimum 1 digit';
  required: boolean = false;
  messageEligible: boolean | undefined;
  admin: boolean | undefined;
  userId: any;
  logDownload: any;
  userDetails: any;
  stateCode: any;
  currentSurveyYears: any = [];
  constructor(
    public getService: GetService,
    public authService: AuthService,
    public sharedService: SharedService,
    public fb: FormBuilder,
    public postService: PostService,
    public localService: LocalserviceService,
    private dialog: MatDialog
  ) {
    this.sharedService.getUserDetails.subscribe((res) => {
      this.stateCode = res.stateCode;
    });
    this.sepcialPermissionsForm = this.fb.group({
      institutionTypeSP: ['', []],
      aisheCode: ['', []],
      surveyYear: ['', []],
      specialPermission: ['', []],
      remarks: ['', []],
    });

    this.unlockWebDCF = this.fb.group({
      aisheCode: ['', []],
      institutionType: ['C', [Validators.minLength(1)]],
      surveyYear: ['', [Validators.required]],
      remarks: ['', []],
    });
    this.reportLog = this.fb.group({
      institutionTypeR: ['', []],
      aisheCode: ['', []],
      surveyYear: ['', []],
      logReport: ['', []],
      remarks: ['', []],
      orderBy: ['', []],
      stateCode: ['', []],
    });
    this.deleteSurveyYears = this.fb.group({
      institutionTypeDelete: ['', []],
      aisheCodeDelete: ['', []],
      surveyYearDelete: ['', []],
      remarksDelete: ['', []],
    });
    this.roleId = this.localService.getData('roleId');
    if (
      this.roleId !== this.sharedService.role['SysAdmin'] &&
      this.roleId !== this.sharedService.role['State Nodal Officer'] &&
      this.roleId !== this.sharedService.role['Ministry of Education']
    ) {
      this.admin = false;
      this.getSurveyYear();
    } else {
      this.admin = true;
    }
    if (this.roleId === this.sharedService.role['State Nodal Officer']) {
      this.reportLog.get('stateCode')?.setValue(this.stateCode);
    }
  }

  ngOnInit(): void {
    if (
      this.roleId === this.sharedService.role['SysAdmin'] ||
      this.roleId === this.sharedService.role['Ministry of Education'] ||
      this.roleId === this.sharedService.role['State Nodal Officer']
    ) {
      this.showAisheCodeInputField = true;
      this.unlockWebDCF.get('aisheCode')?.setValidators(Validators.required);
      this.unlockWebDCF.get('surveyYear')?.clearValidators();
      this.unlockWebDCF.get('surveyYear')?.updateValueAndValidity();
      this.unlockWebDCF
        .get('institutionType')
        ?.setValidators(Validators.required);
      this.required = false;
    } else {
      this.required = true;
    }

    this.localService.getData('userId');
    this.localService.getData('lsy');
    this.localService.getData('minlsy');
    this.getState();
    this.institutionTypeUn = ['ALL', ...this.sharedService.institutionTypeList];
    this.institutionTypeDele = [
      ...this.sharedService.institutionTypeList,
    ].filter((item: any) => {
      return item !== 'U';
    });
    this.currentSurveyYears = [this.sharedService.currentSurveyfiscal];
  }
  tabSelected(data: any) {
    if (data.index === 0) {
      this.resetForm();
      //this.deleteSurveyYears.reset();
      this.reportLog.reset();
      this.sepcialPermissionsForm.reset();
    } else if (data.index === 1) {
      this.resetForm();
      //  this.deleteSurveyYears.reset();
      this.unlockWebDCF.reset();
      this.reportLog.reset();
    } else if (data.index === 2) {
      this.resetForm();
      this.unlockWebDCF.reset();
      this.sepcialPermissionsForm.reset();
      //this.deleteSurveyYears.reset();
    } else if (data.index === 3) {
      //this.deleteSurveyYears.get('institutionTypeDelete')?.setValue('C');
      this.unlockWebDCF.reset();
      this.sepcialPermissionsForm.reset();
      this.reportLog.reset();
    }
  }
  getSurveyYear() {
    this.authService.getSurveyYearListPrivate().subscribe(
      (res) => {
        let surveyYearList = res;
        surveyYearList.forEach((element: any) => {
          if (element === this.sharedService.currentSurveyfic) {
            var splitSurvey = element.substring(0, 5);
            var a = element.substring(7, 9);
            this.surveyYear = [splitSurvey + a];
          }
        });
        if (this.surveyYear.length === 0) {
          this.specialPermission();
        }
      },
      (err) => { }
    );
  }

  getState() {
    this.authService.getStatePrivate().subscribe(
      (res) => {
        if (res && res.length) {
          this.variables = [{ stateCode: 'ALL', name: 'ALL' }, ...res];
          this.stateList = this.variables.slice();
        }
      },
      (err) => { }
    );
  }
  specialPermission() {
    let aisheCode = this.localService.getData('aisheCode');
    this.authService.findEligible(aisheCode, 2022).subscribe(
      (res) => {
        if (res) {
          if (res['Institute Details For CSY'].specialPermission) {
            this.surveyYear.push(this.sharedService.currentSurveyfiscal);
          } else {
            this.sharedService.alert(true);
          }
        }
      },
      (err) => { }
    );
  }
  sepcialPermissionsSave() {
    let temp = {
      instituteType: this.sepcialPermissionsForm.value.institutionTypeSP,
      aisheCode: this.sepcialPermissionsForm.value.aisheCode,
      surveyYear: this.sepcialPermissionsForm.value.surveyYear,
      specialPermission: this.sepcialPermissionsForm.value.specialPermission,
      userKey: 'NIC@Aishe123',
      username: 'aishe.nic',
      remarks: this.sepcialPermissionsForm.value.remarks,
    };
    this.postService.saveSpecialPermissions(temp).subscribe((res) => {
      if (res.status === 200) {
        this.sharedService.showSuccessMessage(res.message);
      }
    });
  }
  unlockWebDCFSave() {
    if (this.sharedService.role['State Nodal Officer'] === this.roleId) {
      this.getDetails()
    } else {
      this.unlock()
    }
  }
  getDetails() {
    let aisheCode = this.unlockWebDCF.value.institutionType + '-' + this.unlockWebDCF.value.aisheCode
    this.authService.getInstituteDetail(aisheCode).subscribe(res => {
      if (this.stateCode === res['0'].stateCode) {
        this.unlock()
      }else{
        this.sharedService.showValidationMessage('Unauthorized !!!');
      }

    }, err => {

    })
  }
  unlock() {
    let temp = {
      aishecode: this.unlockWebDCF.value.aisheCode,
      institutionType: this.unlockWebDCF.value.institutionType,
      surveyYear: parseInt(this.unlockWebDCF.value.surveyYear),
      userKey: 'NIC@Aishe123',
      userName: 'aishe.nic',
      remarks: this.unlockWebDCF.value.remarks,
    };
    this.postService.unlockDCF(temp).subscribe((res: any) => {
      if (res.status === 200) {
        this.sharedService.showSuccessMessage(res.message);
      }
    }, err => {

    });
  }
  downloadLog() {
    let temp = {
      instituteType: this.reportLog.value.institutionTypeR,
      stateCode: this.reportLog.value.stateCode,
      surveyYear: this.reportLog.value.surveyYear,
      logReport: this.reportLog.value.logReport,
      orderBy: this.reportLog.value.orderBy,
    };

    this.getService.downloadFile(temp).subscribe(
      (response) => {
        const contentDisposition = response.headers.get('content-disposition');
        const filename = contentDisposition
          ? contentDisposition.split(';')[1].split('=')[1].trim()
          : this.reportLog.value.logReport;
        const blob = new Blob([response.body], {
          type: 'application/vnd.ms-excel',
        });
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = filename;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      },
      (err) => { }
    );
  }


  goToDCF(data: any) {
    if (this.unlockWebDCF.invalid) {
      this.sharedService.showValidationMessage('Please Enter AISHE Code');
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    if (
      this.roleId !== this.sharedService.role['SysAdmin'] &&
      this.roleId !== this.sharedService.role['State Nodal Officer'] &&
      this.roleId !== this.sharedService.role['Ministry of Education']
    ) {
      window.open(
        `${environment.dcfURL}${this.localService.getData(
          'aisheCode'
        )}/30265/${this.localService.getData(
          'surveyYear'
        )}/2023/${this.localService.getData(
          'userId'
        )}/${this.localService.getData('roleId')}/${sessionStorage.getItem(
          'encrypted'
        )}`
      );
    } else {
      let aisheCode = data.institutionType + '-' + data.aisheCode;
      window.open(
        `${environment.dcfURL}${aisheCode}/30265/${this.localService.getData(
          'surveyYear'
        )}/2022/${this.localService.getData(
          'userId'
        )}/${this.localService.getData('roleId')}/${sessionStorage.getItem(
          'encrypted'
        )}`
      );
    }
  }
  restrictNumeric(event: any) {
    return event.charCode == 8 || event.charCode == 0
      ? null
      : event.charCode >= 48 && event.charCode <= 57;
  }
  deleteSurveyYearsData() {
    this.validation();
    if (this.deleteSurveyYears.invalid) {
      this.isFormInvalid = true;
      this.sharedService.showValidationMessage('Please filled all field');
      return;
    }
    if (this.deleteSurveyYears.valid) {
      const dialogBox = this.sharedService.dialog.open(
        ConfirmDialogComponent,
        {
          width: '18%',
          data: {
            message1: "Are you sure, you want to delete this?",
            deAffiliate: 'deAffiliate',
          },
        }
      );
      this.isFormInvalid = false;
      dialogBox.afterClosed().subscribe((confirm) => {
        if (confirm) {
          const insti = this.deleteSurveyYears.value.institutionTypeDelete;
          const aishe = this.deleteSurveyYears.value.aisheCodeDelete;
          const aisheCode = insti + '-' + aishe;
          const currentSurvey =
            this.deleteSurveyYears.value.surveyYearDelete.split('-')[0];
          let tem = {
            aisheCode: aisheCode,
            currentSurveyYear: parseInt(currentSurvey),
            remarks: this.deleteSurveyYears.value.remarksDelete,
          };
          this.authService.deleteSurveyYearsData(tem).subscribe(
            (res) => {
              if (res.status === 200) {
                this.sharedService.showSuccessMessage(res.message);
                //  this.deleteSurveyYears.reset()
                // this.deleteSurveyYears.value.institutionTypeDelete.reset();
                // this.deleteSurveyYears.controls['institutionTypeDelete'].reset();
                // this.deleteSurveyYears.controls['aisheCodeDelete'].reset();
                // this.deleteSurveyYears.controls['surveyYearDelete'].reset();
                this.resetForm();
              }
            },
            (err) => {
              this.sharedService.showValidationMessage(err.error.message);
            }
          );
        }
      });
    }
  }

  resetForm() {
    this.deleteSurveyYears.reset();
    this.isFormInvalid = false;

    // Reset validation state for all controls
    Object.keys(this.deleteSurveyYears.controls).forEach((key) => {
      const control = this.deleteSurveyYears.get(key);
      control?.setErrors(null);
      control?.markAsPristine();
      control?.markAsUntouched();
      control?.updateValueAndValidity();
    });
    this.removeValidation();
  }
  validation() {
    this.deleteSurveyYears
      .get('institutionTypeDelete')
      ?.setValidators([Validators.required]);
    this.deleteSurveyYears
      .get('institutionTypeDelete')
      ?.updateValueAndValidity();
    this.deleteSurveyYears
      .get('aisheCodeDelete')
      ?.setValidators([Validators.required]);
    this.deleteSurveyYears.get('aisheCodeDelete')?.updateValueAndValidity();
    this.deleteSurveyYears
      .get('surveyYearDelete')
      ?.setValidators([Validators.required]);
    this.deleteSurveyYears.get('surveyYearDelete')?.updateValueAndValidity();
    this.deleteSurveyYears
      .get('remarksDelete')
      ?.setValidators([Validators.required]);
    this.deleteSurveyYears.get('remarksDelete')?.updateValueAndValidity();
  }

  removeValidation() {
    // Clear validators for all controls
    this.deleteSurveyYears.get('institutionTypeDelete')?.clearValidators();
    this.deleteSurveyYears.get('aisheCodeDelete')?.clearValidators();
    this.deleteSurveyYears.get('surveyYearDelete')?.clearValidators();
    this.deleteSurveyYears.get('remarksDelete')?.clearValidators();

    // Update value and validity for all controls
    Object.keys(this.deleteSurveyYears.controls).forEach((key) => {
      const control = this.deleteSurveyYears.get(key);
      control?.updateValueAndValidity();
    });
  }
}
