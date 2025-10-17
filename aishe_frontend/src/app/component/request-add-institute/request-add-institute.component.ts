import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { catchError, map, of, Subject, timeout } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { MustMatch } from 'src/app/shared/custome-validators';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-request-add-institute',
  templateUrl: './request-add-institute.component.html',
  styleUrls: ['./request-add-institute.component.scss'],
})
export class RequestAddInstituteComponent implements OnInit {
  @ViewChild('fileInput', { static: false }) myVar1: ElementRef | undefined;
  fileSizeUnit: number = 2048;
  myFiles: string[] = [];
  myFilesName: any = '';
  fileSizeExceed: any;
  uploadedMedia: Array<any> = [];
  instituteBasicInfoForm: FormGroup;
  nodalOfficerForm: FormGroup;
  loginForm: FormGroup;
  contactDetails: FormGroup;
  institutionHead: FormGroup;
  selectedIndex: number = 0;
  stateList: any[] = [];
  variables: any[] = [];
  variables1: any[] = [];
  variables2: any[] = [];
  variables3: any[] = [];
  variables4: any[] = [];
  districtList: any[] = [];
  genderList: any[] = [];
  collegeType: any[] = [];
  managementList: any[] = [];
  institutionList: any[] = [];
  affiliatingStateList: any[] = [];
  affiliatingUniversityList: any[] = [];
  otherAffiliatingUniversityList: any[] = [];
  roleList: any = ([] = []);
  emailOtp: boolean = false;
  mobileOtp: boolean = false;
  successVerifyOTP: boolean = false;
  successVerifyMobileOTP: boolean = false;
  emailOtpVarify: string = '';
  mobileOtpVarify: string = '';
  display: any;
  displayE: any;
  captchaText: any;
  encodeCaptcha: any;
  insAff: boolean = false;
  admissionStarted: boolean = false;
  insType: boolean = true;
  isFormInvalid: boolean = false;
  latestId: number = 0;
  hide: boolean = true;
  hideE: boolean = true;
  disabledEmail: boolean = true;
  disabledMobile: boolean = true;
  admissionYear: any[] = [];
  subBodyType: Array<any> = [];
  typeOfInsitute: any;
  roleId: any;
  errorMsg: string =
    'This institute is not applicable under this category. You may opt to add either as a College or Standalone or University.';
  showErrorMsg: boolean = false;
  showField: boolean | undefined;
  ministryArray: Array<any> = [];
  ministryList: Array<any> = [];
  otherAffilatatedUniversityName: any;
  instituteMsg: string = `Please enter institution name as per the affiliation letter without any
  special characters (only comma(,) is permitted).`;
  passwordMessage: String = `The password must contain 8 -16 characters.
  It should also have one upper case letter, one number and one special character. Valid characters are
  letters (a-z, A-Z), numbers (0-9) and #, @, &, $, =, ~, %, *. Spaces are not permitted. The password is
  case sensitive.`;
  // admissionYear = [
  //   {
  //     id: '2021',
  //     year: '2021-2022',
  //     boolean: true,
  //   },
  //   {
  //     id: '2021',
  //     year: 'Prior to 2021-2022',
  //     boolean: false,
  //   },
  // ];
  show: boolean;
  userExists: boolean = false;
  showOptionField: boolean = false;
  showOptionField1: boolean = false;
  tabIndex = {
    index: 1,
  };
  latitude: any;
  longitude: any;
  subDistrictList: Array<any> = [];
  variable2: Array<any> = [];
  blockList: Array<any> = [];
  variable: Array<any> = [];
  urbanBodyList: Array<any> = [];
  variable1: Array<any> = [];
  blockLgdId: any;
  variables5: Array<any> = [];
  variables6: Array<any> = [];
  variables7: Array<any> = [];
  latLongToken: any;
  stateLgdId: any;
  districtLgdId: any;
  count: number = 0;
  isRegistrationOpen: boolean = true;
  isGeospatialDataVerified: boolean = false;
  msg: string = '';
  isVisible: any = null;
  filteredStatutoryArr: Array<any> = [];
  statutoryValueArr: Array<any> = [];
  affilatedValueArr: Array<any> = [];
  ministryArr: Array<any> = [];
  userTypeId: any;
  affListVisibleArray: any = [];
  statutoryListVisibleArray: any = [];
  itsChekcBox: any;
  itsSearchValueUncheck: any;
  otherAffiliatingUniversityRadio: any;
  stateCode: any;
  filterAffVariable: Array<any> = [];
  affilatedUniArr: Array<any> = [];
  statutoryArr: Array<any> = [];
  affilatedUniversityEditArr: any = [];
  statutoryEditArr: any = [];
  affilatedValue: any = [];
  statuatoryBodyValue: any = [];
  searchTerm: string = '';
  alertMsgCS: string = `I hereby undertake that I will be uploading Data Capture Format (DCF) on AISHE Portal,within
                    stipulated time after the launch of AISHE every year,
                    failing which Ministry of Education may delete the name of my college/Standalone institution
                     from the
                    AISHE
                    Portal. I also do undertake the responsibility of consequences thereof.`
  alertRD: string = 'I hereby undertake that all information provided is accurate and complete to the best of my knowledge'
  verifyMobile: boolean = false
  constructor(
    public auth: AuthService,
    public sharedService: SharedService,
    public sanitizer: DomSanitizer,
    public encrypt: EncryptDecrypt,
    public fb: FormBuilder,
    public errorMatcher: CustomErrorStateMatcher
  ) {
    let websitePattern: RegExp =
      /^(http[s]?:\/\/){0,1}(www\.){0,1}[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}[\.]{0,1}/;
    let passwordRegex: RegExp =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{7,15}$/;
    let regExEmail: RegExp =
      /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    let mobileNoRegex: RegExp = /^[123456789]\d{9}$/;
    let name: RegExp = /^[a-zA-Z\s?]*$/;
    let insName: RegExp = /^[a-zA-Z,\s?]*$/;
    let userIdRegex: RegExp = /^[a-zA-Z0-9.]{8,16}$/;
    this.instituteBasicInfoForm = this.fb.group({
      aisheCode: ['', [Validators.required]],
      aisheRadio: ['', [Validators.required]],
      instituteType: ['', [Validators.required]],
      name: ['', [Validators.required, Validators.pattern(insName)]],
      stateName: ['', [Validators.required]],
      stateCode: ['', []],
      districtName: ['', [Validators.required]],
      districtCode: ['', []],
      totalno: ['', [Validators.required]],
      instituteNameRD: ['', [Validators.required]],
      ministryIdRD: ['', [Validators.required]],
    });
    this.institutionHead = this.fb.group({
      designation: ['', [Validators.required, Validators.pattern(name)]],
      email: ['', [Validators.required, Validators.pattern(regExEmail)]],
      mobile: ['', [Validators.required, Validators.pattern(mobileNoRegex)]],
      institutionalHeadName: ['', [Validators.required, Validators.pattern(name)],],
      gender: ['', [Validators.required]],
      instituteCategory: { value: '', disabled: true },
      state: { value: '', disabled: true },
      district: { value: '', disabled: true },
      instituteName: { value: '', disabled: true }
    });

    this.loginForm = this.fb.group(
      {
        roleId: ['', []],
        userId: ['', [Validators.required, Validators.pattern(userIdRegex)]],
        confirmPassword: ['', [Validators.required]],
        password: [
          '',
          [Validators.required, Validators.pattern(passwordRegex)],
        ],
      },
      {
        validator: MustMatch('password', 'confirmPassword'),
      }
    );
    this.nodalOfficerForm = this.fb.group({
      nodalOfficerMob: [
        '',
        [Validators.required, Validators.pattern(mobileNoRegex)],
      ],
      nodalOfficerEmail: [
        '',
        [Validators.required, Validators.pattern(regExEmail)],
      ],
      // emailOtpNodal: ['', [Validators.required]],
      mobileOtpNodal: ['', [Validators.required]],
      stdCode: ['', []],
      phoneNumber: ['', []],
      phoneLandline: ['', []],
      nodalDesignation: ['', [Validators.required, Validators.pattern(name)]],
      nodalGender: ['', [Validators.required]],
      nodalOfficerName: ['', [Validators.required, Validators.pattern(name)]],
      instituteCategory: { value: '', disabled: true },
      state: { value: '', disabled: true },
      district: { value: '', disabled: true },
      instituteName: { value: '', disabled: true }
    });
    this.contactDetails = this.fb.group({
      yearOfEstablishment: ['', [Validators.required]],
      websiteUrl: ['', [Validators.pattern(websitePattern)]],
      latitude: ['', [Validators.required]],
      longitude: ['', [Validators.required]],
      location: ['', [Validators.required]],
      collegeType: ['', [Validators.required]],
      managementType: ['', [Validators.required]],
      affiliatingUniversityState: ['', [Validators.required]],
      affiliatingUniversityName: ['', [Validators.required]],
      isInstitutionAffiliated: ['', [Validators.required]],
      otherAffiliatedUniversity: ['', [Validators.required]],
      admissionEnrollment: ['', [Validators.required]],
      surveyYear: ['', []],
      surveyYearA: ['', [Validators.required]],
      addressLine1: ['', [Validators.required]],
      addressLine2: ['', [Validators.required]],
      city: ['', [Validators.required, Validators.pattern(name)]],
      underTaking: [null, [Validators.required]],
      instituteCategory: { value: '', disabled: true },
      instituteName: { value: '', disabled: true },
      state: { value: '', disabled: true },
      district: { value: '', disabled: true },
      Verfication: ['', [Validators.required]],
      admissionYear: ['', []],
      pinCode: ['', [Validators.required]],
      stateBodyTypeId: [0, []],
      subDistrictId: ['', [Validators.required]],
      blockId: ['', []],
      ulbId: ['', []],
      statuatoryBody: [{ value: '' }],
      otherAffiliatingUniversity: [{ value: '' }],
      isOtherAffiliatingUniversityStatuatoryBody: [''],
      ministryId: [''],

    });
    this.nodalOfficerForm
      .get('nodalOfficerEmail')
      ?.valueChanges.subscribe((value) => {
        if (this.nodalOfficerForm.get('nodalOfficerEmail')?.valid) {
          this.disabledEmail = false;
        } else {
          this.disabledEmail = true;
        }
      });
    this.nodalOfficerForm
      .get('nodalOfficerMob')
      ?.valueChanges.subscribe((value) => {
        if (this.nodalOfficerForm.get('nodalOfficerMob')?.valid) {
          this.disabledMobile = false;
        } else {
          this.disabledMobile = true;
        }
      });
    this.show = false;
    this.getAdmissionYear();
  }

  ngOnInit(): void {
    // this.verify('')
  }
  add: any[] = [];
  getDetails() {
    let institutionType = null;
    if (this.instituteBasicInfoForm.controls['aisheCode'].value) {
      institutionType = this.instituteBasicInfoForm.controls['aisheCode'].value
        .toUpperCase()
        .split(/[\W\d]+/)
        .join('');
      if (institutionType !== 'C' && institutionType !== 'S') {
        this.sharedService.showValidationMessagePDF(
          'Please enter valid aishe code !!!'
        );
        return;
      }
    } else {
      this.sharedService.showValidationMessagePDF(
        'Please enter aishe code !!!'
      );
      return;
    }

    this.auth
      .getAisheCodeDetails(
        this.instituteBasicInfoForm.controls['aisheCode'].value.toUpperCase(),
        institutionType === 'C' ? 'COLLEGE' : 'STANDALONE'
      )
      .subscribe(
        (res) => {
          if (res.status === 200) {
            let insType = this.instituteBasicInfoForm.controls[
              'aisheCode'
            ].value
              .toUpperCase()
              .split(/[\W\d]+/)
              .join('');
            if (
              res.message ===
              `This Institute(${this.instituteBasicInfoForm.controls[
                'aisheCode'
              ].value.toUpperCase()}) Is Already Active!`
            ) {
              let ele = {
                type: insType,
                inactive: false,
              };
              this.sharedService.newRequest(ele);
            }
            if (
              res.message ===
              `Standalone Institution having this AISHE Code is Inactive/Deaffiliated, Please contact your UNO/SNO!`
            ) {
              let ele = {
                type: insType,
                inactive: true,
              };
              this.sharedService.newRequest(ele);
            }
            if (
              res.message ===
              `COLLEGE having this AISHE Code is Inactive/Deaffiliated, Please contact your UNO/SNO!`
            ) {
              let ele = {
                type: insType,
                inactive: true,
              };
              this.sharedService.newRequest(ele);
            }

            // var ele = {
            //   confirm: false,
            //   success: false,
            //   aisheCode: true,
            //   message: res.message,
            // };
            // this.sharedService.savePopUp(ele);
          }
        },
        (err) => { }
      );
  }
  getAdmissionYear() {
    this.auth.getAdmissionYearList().subscribe(
      (res) => {
        this.add = res['Survey Year For Request Add Institute Activity'];
        this.add.forEach((element) => {
          this.admissionYear.push({
            id: element,
            year: element,
          });
        });
        this.admissionYear.forEach((val) => {
          if (val.id.startsWith('Prior')) {
            val['id'] = val.id.substring(9, 13);
            val['boolean'] = false;
          } else {
            val['id'] = val.id.substring(0, 4);
            val['boolean'] = true;
          }
        });
        if (this.admissionYear.length === 0) {
          this.isRegistrationOpen = false;
        } else {
          this.isRegistrationOpen = true;
          // this.sharedService.alert(false)
          this.getToken();
          this.getCaptcha();
          this.getState();
          this.getManagement();
        }
      },
      (err) => { }
    );
  }
  password() {
    this.show = !this.show;
  }
  getCaptcha() {
    this.auth.getCaptchaText().subscribe(
      (resp: any) => {
        this.captchaText = this.sanitizer.bypassSecurityTrustUrl(resp.capcha);
        this.encodeCaptcha = resp.data;
      },
      (err) => { }
    );
  }
  verifyCaptcha() {
    this.auth
      .verifyGetCaptcha(
        this.contactDetails.controls['Verfication'].value,
        this.encodeCaptcha
      )
      .subscribe(
        (res) => {
          if (res.message == 'Captcha Valid') {
            if (
              this.instituteBasicInfoForm.controls['instituteType'].value ===
              'RD'
            ) {
              this.save();
            } else {
              this.submit();
            }
          } else {
            this.sharedService.showValidationMessage('Invalid captcha');
          }
        },
        (err) => {
          this.sharedService.showValidationMessage(err.error.message)
          this.getCaptcha();
          this.contactDetails.get('Verfication')?.setValue('')
        }
      );
  }
  getState() {
    this.auth.getState().subscribe(
      (res) => {
        if (res && res.length) {
          this.variables = [];
          this.variables = res;
          this.variables4 = res;
          this.stateList = this.variables.slice();
          this.affiliatingStateList = this.variables4.slice();
        }
      },
      (err) => { }
    );
  }
  getManagement() {
    let manageType =
      this.instituteBasicInfoForm.controls['instituteType'].value;
    this.typeOfInsitute = manageType;
    this.auth.getManagementTypeOwnershipData(manageType).subscribe(
      (res) => {
        if (res && res.length) {
          if (
            this.instituteBasicInfoForm.controls['instituteType'].value === 'S'
          ) {
            this.managementList = res.filter((ele: any) => ele.id !== '4');
          } else {
            this.managementList = res;
          }
        }
      },
      (err) => { }
    );
  }
  getCollege() {
    this.collegeType = [];
    this.auth.getCollegeType().subscribe(
      (res) => {
        if (res && res.length) {
          this.collegeType = res.filter(
            (ele: any) => ele.id !== 5 && ele.id !== 6
          );
        }
      },
      (err) => { }
    );
  }
  getRoleID(id: any) {
    console.log(typeof id);
    this.roleId = id;
    if (this.instituteBasicInfoForm.controls['instituteType'].value === 'C') {
      this.loginForm
        .get('roleId')
        ?.setValue(this.sharedService.roleList['7'].roleId);
      this.contactDetails.get('isInstituteUnderNchmct')?.clearValidators();
      this.contactDetails
        .get('isInstituteUnderNchmct')
        ?.updateValueAndValidity();
    } else {
      this.getsubBody(id);
      if (this.contactDetails.controls['collegeType'].value === 6) {
        this.contactDetails
          .get('isInstituteUnderNchmct')
          ?.setValidators(Validators.required);
        this.contactDetails
          .get('isInstituteUnderNchmct')
          ?.updateValueAndValidity();
      } else {
        this.contactDetails.get('isInstituteUnderNchmct')?.clearValidators();
        this.contactDetails
          .get('isInstituteUnderNchmct')
          ?.updateValueAndValidity();
      }

      this.sharedService.roleList.forEach((element) => {
        if (element.id === id) {
          this.loginForm.get('roleId')?.setValue(element.roleId);
        }
      });

      if (this.roleId === 5) {
        this.ministryName();
        this.contactDetails
          .get('ministryId')
          ?.setValidators(Validators.required);
        this.contactDetails.get('ministryId')?.updateValueAndValidity();
      } else {
        this.contactDetails.get('ministryId')?.clearValidators();
        this.contactDetails.get('ministryId')?.updateValueAndValidity();
      }
    }
  }
  getsubBody(id: any) {
    this.auth.subBodyType(id).subscribe(
      (res) => {
        this.subBodyType = res;
      },
      (err) => { }
    );
  }
  getStandAlone() {
    this.collegeType = [];
    this.auth.getStandAloneBody().subscribe(
      (res) => {
        if (res && res.length) {
          this.collegeType = res;
        }
      },
      (err) => { }
    );
  }
  getAffiliatingUniversity(affiliatingUniversityState: any) {
    this.auth
      .affiliatingUniv(
        affiliatingUniversityState,
        this.contactDetails.controls['surveyYear'].value
      )
      .subscribe(
        (res) => {
          if (res && res.length) {
            this.variables2 = [];
            this.variables2 = res;
            this.affiliatingUniversityList = this.variables2.slice();
          }
        },
        (err) => {
          this.variables2 = [];
          this.affiliatingUniversityList = [];
        }
      );
  }
  getAffiliatingUniv(affiliatingUniversityState: any) {
    if (this.contactDetails.controls['surveyYear'].value) {
      this.getAffiliatingUniversity(affiliatingUniversityState);
    }
  }


  getAffilatingUniversityName(id: any) {
    this.getOtherAffiliatedUniversity('ALL')
  }


  getOtherAffiliatedUniversity(value: string) {
    this.auth
      .otherAffiliatingUniv(
        value,
        this.contactDetails.controls['surveyYear'].value
      )
      .subscribe(
        (res) => {
          if (res && res.length) {
            this.variables3 = [];
            this.variables3 = res?.filter((e: any) => e?.id !== this.contactDetails.get('affiliatingUniversityName')?.value);
            this.otherAffiliatingUniversityList = this.variables3?.slice();
          }
        },
        (err) => { }
      );
  }
  getDistrict(state: any) {
    this.instituteBasicInfoForm.get('stateCode')?.setValue(state.stateCode);
    this.contactDetails.get('state')?.setValue(state.name);
    this.nodalOfficerForm.get('state')?.setValue(state.name);
    this.institutionHead.get('state')?.setValue(state.name);

    this.districtList = [];
    this.variables1 = [];
    this.stateLgdId = state.lgdCode;
    this.auth.getDistrict(state.stateCode).subscribe(
      (res) => {
        if (res && res.length) {
          this.variables1 = res;
          this.districtList = this.variables1.slice();
        }
      },
      (err) => { }
    );
  }
  getDistrictName(district: any) {
    this.instituteBasicInfoForm
      .get('districtCode')
      ?.setValue(district.distCode);
    this.contactDetails.get('district')?.setValue(district.name);
    this.nodalOfficerForm.get('district')?.setValue(district.name);
    this.institutionHead.get('district')?.setValue(district.name);
    this.districtLgdId = district.lgdDistCode;
    this.getSubDistrict(district.lgdDistCode);
  }
  getSubDistrict(districtLgdId: any) {
    let payload = {
      lgdDistrictCode: districtLgdId,
    };
    this.auth.getSubDistrictList(payload).subscribe(
      (res) => {
        this.variables5 = res;
        this.subDistrictList = this.variables5.slice();
        if (this.subDistrictList.length === 0) {
          this.subDistrictList.push({ id: 99999, name: 'Not Applicable' });
        }
      },
      (err) => { }
    );
  }
  getExistUser() {
    this.auth.userExist(this.loginForm.controls['userId'].value).subscribe(
      (res) => {
        if (res.UserExist === 'YES') {
          this.sharedService.showValidationMessagePDF('User already exists');
        } else {
          this.getUser();
        }
      },
      (err) => { }
    );
  }
  getUser() {
    this.auth.getUserNoToken(this.loginForm.controls['userId'].value).subscribe(
      (res) => {
        let userId = this.loginForm.controls['userId'].value.toUpperCase();
        if (res.data?.userId) {
          if (res.data?.userId?.toUpperCase() === userId.toUpperCase()) {
            this.sharedService.showValidationMessagePDF('User already exists');
            return;
          } else {
            this.selectedIndex++;
          }
        } else {
          this.selectedIndex++;
        }
      },
      (err) => { }
    );
  }
  getInstituteNameCheck() {
    var nameCheck = true;
    let nameValue = ''
    let upperCaseName = ''
    let insName = ''
    // var instituteName =
    //   this.instituteBasicInfoForm.controls['name']?.value?.toUpperCase();

    if (this.instituteBasicInfoForm.controls['instituteType'].value === 'RD') {
      nameValue = this.instituteBasicInfoForm.controls['instituteNameRD']?.value || '';
      upperCaseName = nameValue.toUpperCase();
    } else {
      nameValue = this.instituteBasicInfoForm.controls['name']?.value || '';
      upperCaseName = nameValue.toUpperCase();
    }


    insName = upperCaseName.replace(/[, ]+/g, '').trim();
    // let payload = {
    //   typeId: this.instituteBasicInfoForm.controls['instituteType'].value,
    //   stateCode: this.instituteBasicInfoForm.controls['stateCode'].value,
    //   districtCode: this.instituteBasicInfoForm.controls['districtCode'].value,
    //   instituteName: insName,
    // };
    // if (data.instituteName && data.ministryId && data.stateCode && data.districtCode) {

    // }
    let payload = {
      instituteType: this.instituteBasicInfoForm.controls['instituteType'].value === 'RD' ? 'R' : this.instituteBasicInfoForm.controls['instituteType'].value,
      districtCode: this.instituteBasicInfoForm.controls['districtCode'].value,
      instituteName: insName,
    }
    this.auth.getInstituteNameExist(payload).subscribe(
      (res) => {
        this.institutionList = [];
        if (res && res.length) {
          this.institutionList = res;
          this.selectedIndex = 0;
        } else {
          this.institutionList = [];
          this.selectedIndex = 1;
        }
      },
      (err) => { }
    );
  }
  tabSelected(event: any) {
    if (event.index === 1) {
      this.getInstituteNameCheck();
    } else {
      this.selectedIndex = event.index;
    }

    if (this.selectedIndex === 1 || this.selectedIndex === 2 || this.selectedIndex === 3) {
      const name = this.instituteBasicInfoForm.controls['instituteNameRD'].value
        ? this.instituteBasicInfoForm.controls['instituteNameRD'].value
        : this.instituteBasicInfoForm.controls['name'].value;
      this.nodalOfficerForm.get('instituteName')?.setValue(name);
      this.institutionHead.get('instituteName')?.setValue(name);
      this.contactDetails.get('instituteName')?.setValue(name);

    }
  }

  nextTab() {
    if (this.instituteBasicInfoForm.controls['instituteType'].value === 'RD') {
      if (this.selectedIndex === 1) {
        if (!this.verifyMobile) {
          this.sharedService.showValidationMessage('Please verify mobile otp');
          this.selectedIndex = 1
        } else {
          this.selectedIndex++
        }
      } else {
        this.selectedIndex++;
      }
    } else {
      this.selectedIndex++;

    }

  }

  timer(minute: any) {
    // let minute = 1;
    let seconds: number = minute * 60;
    let textSec: any = '0';
    let statSec: number = 60;

    const prefix = minute < 10 ? '0' : '';

    const timer = setInterval(() => {
      seconds--;
      if (statSec != 0) statSec--;
      else statSec = 59;

      if (statSec < 10) {
        textSec = '0' + statSec;
      } else textSec = statSec;

      this.display = `${prefix}${Math.floor(seconds / 60)}:${textSec}`;

      if (seconds == 0) {
        this.nodalOfficerForm.get('nodalOfficerMob')?.enable();
        this.nodalOfficerForm.get('nodalOfficerMob')?.updateValueAndValidity();
        clearInterval(timer);
      }
    }, 1000);
  }
  timerE(minute: any) {
    // let minute = 1;
    let seconds: number = minute * 60;
    let textSec: any = '0';
    let statSec: number = 60;

    const prefix = minute < 10 ? '0' : '';

    const timer = setInterval(() => {
      seconds--;
      if (statSec != 0) statSec--;
      else statSec = 59;

      if (statSec < 10) {
        textSec = '0' + statSec;
      } else textSec = statSec;

      this.displayE = `${prefix}${Math.floor(seconds / 60)}:${textSec}`;

      if (seconds == 0) {
        this.nodalOfficerForm.get('nodalOfficerEmail')?.enable();
        this.nodalOfficerForm
          .get('nodalOfficerEmail')
          ?.updateValueAndValidity();
        clearInterval(timer);
      }
    }, 1000);
  }
  sendEmailOTP() {
    const email = this.encrypt.getEncryptedValue(
      this.nodalOfficerForm.controls['nodalOfficerEmail'].value
    );
    this.auth.sendEmail(email, this.latestId).subscribe(
      (res) => {
        const status = this.encrypt.getDecryptedValue(res.status);
        const latestId0 = this.encrypt.getDecryptedValue(res.data1);
        const emailOtpVarify = this.encrypt.getDecryptedValue(res.data2);
        if (status === '200' && +latestId0) {
          this.latestId = +latestId0;
          this.emailOtpVarify = emailOtpVarify;
          this.emailOtp = true;
          this.nodalOfficerForm.get('nodalOfficerEmail')?.disable();
          this.nodalOfficerForm
            .get('nodalOfficerEmail')
            ?.updateValueAndValidity();
          this.sharedService.sendEOTP();
          this.timerE(1);
        }
      },
      (err) => { }
    );
  }

  verifyEmailOTP() {
    const email = this.encrypt.getEncryptedValue(
      this.nodalOfficerForm.controls['nodalOfficerEmail'].value
    );
    const emailOtpNodal = this.encrypt.getEncryptedValue(
      this.nodalOfficerForm.controls['emailOtpNodal'].value
    );
    if (
      this.nodalOfficerForm.controls['emailOtpNodal'].value !==
      this.emailOtpVarify
    ) {
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.auth
      .verifyEOTP(email, emailOtpNodal)
      .pipe(
        timeout(1000),
        map((res) => {
          const decryptedResponse = {
            statusCode: this.encrypt.getDecryptedValue(res.status),
            latestId1: this.encrypt.getDecryptedValue(res.data1),
            emailOtpVarify: this.encrypt.getDecryptedValue(res.data2),
          };
          return decryptedResponse;
        }),
        catchError((err) => {
          if (err.name === 'TimeoutError') {
            this.sharedService.showValidationMessage(
              'Request timed out. Please try again.'
            );
            return of(null); // Return an empty observable to stop further execution
          }

          const statusCode = this.encrypt.getDecryptedValue(err.error?.status);
          const data12 = this.encrypt.getDecryptedValue(err.error?.data);
          if (statusCode === '422' && data12 === 'Problem Occurred!') {
            this.sharedService.showValidationMessage('Invalid OTP');
          }
          return of(null); // Return an empty observable to stop further execution
        })
      )
      .subscribe(
        (decryptedResponse) => {
          if (!decryptedResponse) return; // Stop execution if an error occurred

          const { statusCode, latestId1, emailOtpVarify } = decryptedResponse;
          if (
            this.latestId !== +latestId1 &&
            this.emailOtpVarify !== emailOtpVarify
          ) {
            this.sharedService.showValidationMessage('Invalid OTP');
            return;
          }
          if (
            statusCode === '200' &&
            this.latestId === +latestId1 &&
            this.emailOtpVarify == emailOtpVarify
          ) {
            this.sharedService.verifyEOTP();
            this.successVerifyOTP = true;
            this.hideE = false;
            this.emailOtp = false;
            this.latestId = 0;
          }
        },
        (err) => { }
      );
  }
  sendMobileOTP() {
    const mobile = this.encrypt.getEncryptedValue(
      this.nodalOfficerForm.controls['nodalOfficerMob'].value
    );
    this.auth.sendMobile(mobile, this.latestId).subscribe(
      (res) => {
        const status = this.encrypt.getDecryptedValue(res.status);
        const latestId17 = this.encrypt.getDecryptedValue(res.data1);
        const mobileOtp1 = this.encrypt.getDecryptedValue(res.data2);
        if (status === '200' && +latestId17) {
          this.latestId = +latestId17;
          this.mobileOtpVarify = mobileOtp1;
          this.nodalOfficerForm.get('nodalOfficerMob')?.disable();
          this.nodalOfficerForm
            .get('nodalOfficerMob')
            ?.updateValueAndValidity();
          this.mobileOtp = true;
          this.sharedService.sendMOTP();
          this.timer(1);
        }
      },
      (err) => { }
    );
  }
  verifyMobileOTP() {
    const mobile = this.encrypt.getEncryptedValue(
      this.nodalOfficerForm.controls['nodalOfficerMob'].value
    );
    const mobileOtpNodal = this.encrypt.getEncryptedValue(
      this.nodalOfficerForm.controls['mobileOtpNodal'].value
    );
    if (
      this.nodalOfficerForm.controls['mobileOtpNodal'].value !==
      this.mobileOtpVarify
    ) {
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.auth
      .verifyMOTP(mobile, mobileOtpNodal)
      .pipe(
        timeout(1000),
        map((res) => {
          const response = {
            statusCode: this.encrypt.getDecryptedValue(res.status),
            latestId1: this.encrypt.getDecryptedValue(res.data1),
            mobileOtpVarify1: this.encrypt.getDecryptedValue(res.data2),
          };
          return response;
        }),
        catchError((err) => {
          if (err.name === 'TimeoutError') {
            this.sharedService.showValidationMessage(
              'Request timed out. Please try again.'
            );
            const statusCode = this.encrypt.getDecryptedValue(
              err.error?.status
            );
            const data12 = this.encrypt.getDecryptedValue(err.error?.data);
            if (statusCode === '422' && data12 === 'Problem Occurred!') {
              this.sharedService.showValidationMessage('Invalid OTP');
            }
            return of(null); // Return an empty observable to stop further execution
          }
          return of(null);
        })
      )
      .subscribe(
        (response) => {
          if (!response) return; // Stop execution if an error occurred

          const { statusCode, latestId1, mobileOtpVarify1 } = response;
          if (
            this.latestId !== +latestId1 &&
            this.mobileOtpVarify !== mobileOtpVarify1
          ) {
            this.sharedService.showValidationMessage('Invalid OTP');
            return;
          }
          if (statusCode === '200' && this.latestId === +latestId1) {
            this.sharedService.verifyMOTP();
            this.successVerifyMobileOTP = true;
            this.hide = false;
            this.mobileOtp = false;
            this.latestId = 0;
            this.verifyMobile = true
          }
        },
        (err) => { }
      );
  }
  instituteAff(value: any) {
    if (value === 'YES') {
      this.insAff = true;
      this.contactDetails
        .get('otherAffiliatedUniversity')
        ?.setValidators(Validators.required);
      this.contactDetails
        .get('otherAffiliatedUniversity')
        ?.updateValueAndValidity();
      if (
        this.otherAffiliatingUniversityList.length === 0 &&
        this.contactDetails.controls['surveyYear'].value
      ) {
        this.getOtherAffiliatedUniversity('ALL');
      }
    } else {
      this.contactDetails.get('otherAffiliatedUniversity')?.clearValidators();
      this.contactDetails
        .get('otherAffiliatedUniversity')
        ?.updateValueAndValidity();
      this.insAff = false;
    }
  }
  admission(value: any) {
    this.contactDetails.get('surveyYear')?.setValue(value.id);
    if (
      this.contactDetails.controls['isOtherAffiliatingUniversityStatuatoryBody']
        .value
    ) {
      this.isInstitutionAfilated(true);
    }
    if (
      this.otherAffiliatingUniversityList.length === 0 &&
      this.contactDetails.controls['isInstitutionAffiliated'].value === true
    ) {
      this.getOtherAffiliatedUniversity('ALL');
    }
    if (this.contactDetails.controls['affiliatingUniversityState'].value) {
      this.getAffiliatingUniversity(
        this.contactDetails.controls['affiliatingUniversityState'].value
      );
    }
    for (let index = 0; index < this.admissionYear.length; index++) {
      if (this.admissionYear[index].boolean) {
        if (this.admissionYear[index].year === value.year) {
          this.contactDetails
            .get('admissionYear')
            ?.setValue(this.admissionYear[index].year);
          this.admissionStarted = true;
          this.contactDetails
            .get('admissionEnrollment')
            ?.setValidators(Validators.required);
          this.contactDetails
            .get('admissionEnrollment')
            ?.updateValueAndValidity();
          return;
        }
      } else {
        this.admissionStarted = false;
        this.contactDetails.get('admissionEnrollment')?.clearValidators();
        this.contactDetails
          .get('admissionEnrollment')
          ?.updateValueAndValidity();
        this.contactDetails
          .get('admissionYear')
          ?.setValue(this.admissionYear[index].year);
      }
    }
  }
  instituteType(type: string) {
    this.institutionList = [];
    this.loginForm.get('roleId')?.setValue('');
    this.contactDetails.get('collegeType')?.setValue('');
    this.getManagement();
    if (type === 'C') {
      this.roleList = [];
      this.roleList = this.sharedService.roleList.filter(
        (val: any) => val.name === 'College'
      );
      this.getCollege();
      this.insType = true;
      this.contactDetails.get('instituteCategory')?.setValue('--COLLEGE--');
      this.nodalOfficerForm.get('instituteCategory')?.setValue('--COLLEGE--');
      this.institutionHead.get('instituteCategory')?.setValue('--COLLEGE--');
      this.contactDetails.get('affiliatingUniversityState')?.setValidators(Validators.required);
      this.contactDetails.get('affiliatingUniversityName')?.setValidators(Validators.required);
      this.contactDetails.get('otherAffiliatedUniversity')?.setValidators(Validators.required);
      this.contactDetails.get('isInstitutionAffiliated')?.setValidators(Validators.required);
      this.contactDetails.get('affiliatingUniversityState')?.updateValueAndValidity();
      this.contactDetails.get('affiliatingUniversityName')?.updateValueAndValidity();
      this.contactDetails.get('otherAffiliatedUniversity')?.updateValueAndValidity();
      this.contactDetails.get('isInstitutionAffiliated')?.updateValueAndValidity();
      this.instituteBasicInfoForm.get('totalno')?.clearValidators();
      this.instituteBasicInfoForm.get('totalno')?.updateValueAndValidity();
      this.instituteBasicInfoForm.get('instituteNameRD')?.clearValidators();
      this.instituteBasicInfoForm.get('instituteNameRD')?.updateValueAndValidity();
      this.instituteBasicInfoForm.get('ministryIdRD')?.clearValidators();
      this.instituteBasicInfoForm.get('ministryIdRD')?.updateValueAndValidity();
      this.instituteBasicInfoForm.get('totalno')?.reset();
      this.instituteBasicInfoForm.get('instituteNameRD')?.reset();
      this.instituteBasicInfoForm.get('ministryIdRD')?.reset();
      this.showErrorMsg = false;
      this.showField = false;
    } else if (type === 'S') {
      this.roleList = [];
      this.roleList = this.sharedService.roleList.filter(
        (val: any) => val.name !== 'College'
      );
      this.managementList = this.managementList.filter(
        (ele: any) => ele.management !== 'University'
      );
      this.getStandAlone();
      this.contactDetails.get('instituteCategory')?.setValue('--STAND-ALONE--');
      this.nodalOfficerForm.get('instituteCategory')?.setValue('--STAND-ALONE--');
      this.institutionHead.get('instituteCategory')?.setValue('--STAND-ALONE--');
      this.insType = false;
      this.contactDetails.get('affiliatingUniversityState')?.clearValidators();
      this.contactDetails.get('affiliatingUniversityName')?.clearValidators();
      this.contactDetails.get('otherAffiliatedUniversity')?.clearValidators();
      this.contactDetails.get('isInstitutionAffiliated')?.clearValidators();
      this.contactDetails.get('affiliatingUniversityState')?.updateValueAndValidity();
      this.contactDetails.get('affiliatingUniversityName')?.updateValueAndValidity();
      this.contactDetails.get('otherAffiliatedUniversity')?.updateValueAndValidity();
      this.contactDetails.get('isInstitutionAffiliated')?.updateValueAndValidity();
      this.instituteBasicInfoForm.get('totalno')?.clearValidators();
      this.instituteBasicInfoForm.get('totalno')?.updateValueAndValidity();
      this.instituteBasicInfoForm.get('instituteNameRD')?.clearValidators();
      this.instituteBasicInfoForm.get('instituteNameRD')?.updateValueAndValidity();
      this.instituteBasicInfoForm.get('ministryIdRD')?.clearValidators();
      this.instituteBasicInfoForm.get('ministryIdRD')?.updateValueAndValidity();
      this.instituteBasicInfoForm.get('totalno')?.reset();
      this.instituteBasicInfoForm.get('instituteNameRD')?.reset();
      this.instituteBasicInfoForm.get('ministryIdRD')?.reset();
      this.showErrorMsg = false;
      this.showField = false;
    } else {
      this.getMinistryList();
      this.contactDetails.get('instituteCategory')?.setValue('----Research & Development Institute----');
      this.nodalOfficerForm.get('instituteCategory')?.setValue('----Research & Development Institute----');
      this.institutionHead.get('instituteCategory')?.setValue('----Research & Development Institute----');
      const name = this.instituteBasicInfoForm.controls['instituteNameRD'].value;
      if (name) {
        this.contactDetails.get('instituteName')?.setValue(name);
      }
      this.instituteBasicInfoForm.get('name')?.clearValidators();
      this.instituteBasicInfoForm.get('name')?.updateValueAndValidity();
      this.instituteBasicInfoForm.get('totalno')?.setValidators(Validators.required);
      this.instituteBasicInfoForm.get('instituteNameRD')?.setValidators(Validators.required);
      this.instituteBasicInfoForm.get('ministryIdRD')?.setValidators(Validators.required);
      this.instituteBasicInfoForm.get('totalno')?.updateValueAndValidity();
      this.instituteBasicInfoForm.get('instituteNameRD')?.updateValueAndValidity();
      this.instituteBasicInfoForm.get('ministryIdRD')?.updateValueAndValidity();
    }
  }

  getMinistryList() {
    this.auth.administrativeMinistry().subscribe(
      (res) => {
        this.ministryArray = res;
        this.ministryList = this.ministryArray.slice();
      },
      (err) => { }
    );
  }
  total() {
    if (this.instituteBasicInfoForm.controls['totalno'].value === 0) {
      this.showField = true;
      this.showErrorMsg = false;
    } else {
      // this.sharedService.showValidationMessage('This institute is not applicable under this category. You may opt to add either as a College or Standalone or University.')
      this.showField = false;
      this.showErrorMsg = true;
    }
  }
  restrictNumeric(event: { charCode: number }) {
    return event.charCode == 8 || event.charCode == 0
      ? null
      : event.charCode >= 48 && event.charCode <= 57;
  }
  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
  // reset(){
  //   (<HTMLInputElement>document.getElementById('myfile')).value = "";

  // }
  // pop(){
  //   var ele = {
  //     confirm: false,
  //     success: true,
  //     requestId: 12345
  //   }
  //   this.sharedService.savePopUp(ele)
  // }
  validationLatLong(latitude: any, longitude: any) {
    // latitude 6-38
    // longitude 68 - 98
    if (latitude) {
      var lat = latitude.toString().split('.');
      let l = 0;
      if (lat[1]) {
        l = lat[1].length;
      }
      if (parseInt(lat[0]) < 6 || parseInt(lat[0]) > 38 || l < 5) {
        this.sharedService.showValidationMessagePDF(
          'Latitude [Range: 6.00000 - 38.00000] ,Values must contain minimum of 5 digits after the decimal point.'
        );
        return false;
      }
    }
    if (longitude) {
      var long = longitude.toString().split('.');

      let lg = 0;

      if (long[1]) {
        lg = long[1].length;
      }
      if (parseInt(long[0]) < 68 || parseInt(long[0]) > 98 || lg < 5) {
        this.sharedService.showValidationMessagePDF(
          'Longitude [Range: 68.00000 - 98.00000] ,Values must contain minimum of 5 digits after the decimal point.'
        );

        return false;
      }
    }

    return true;
  }
  submit() {
    if (this.contactDetails.invalid) {
      this.isFormInvalid = true;
      this.sharedService.showError('Please enter required field');
      return;
    } else {
      this.isFormInvalid = false;
      if (
        this.contactDetails.controls['latitude'].value ||
        this.contactDetails.controls['longitude'].value
      ) {
        let c: boolean = this.validationLatLong(
          this.contactDetails.controls['latitude'].value,
          this.contactDetails.controls['longitude'].value
        );
        if (!c) {
          return;
        } else {
          this.latitude = this.contactDetails.controls['latitude'].value;
          this.longitude = this.contactDetails.controls['longitude'].value;
        }

        let payload = {
          lat: this.latitude,
          lon: this.longitude,
        };
        if (this.latLongToken) {
          this.auth.validateLatLong(payload, this.latLongToken).subscribe(
            (res) => {
              if (res.stateId && res.districtId && res.talukaId) {
                // if (parseInt(res.stateId) !== parseInt(this.stateLgdId)) {
                //   this.sharedService.showValidationMessage('Please verify state first OR correct Latitude and Longitude!!!');
                //   return;
                // }
                // if (parseInt(res.districtId) !== parseInt(this.districtLgdId)) {
                //   this.sharedService.showValidationMessage('Please verify district first OR correct Latitude and Longitude!!!');
                //   return;
                // }
                if (
                  this.contactDetails.controls['subDistrictId'].value !== 99999
                ) {
                  if (
                    parseInt(res.stateId) !== this.stateLgdId ||
                    parseInt(res.districtId) !== this.districtLgdId ||
                    parseInt(res.talukaId) !==
                    this.contactDetails.controls['subDistrictId'].value
                  ) {
                    let ele = {
                      showState: true,
                      stateName: res.state,
                      districtName: res.district,
                      subDistrict: res.taluka,
                      latitude: this.latitude,
                      longitude: this.longitude,
                      enteredState: this.contactDetails.controls['state'].value,
                      enteredDistrict:
                        this.contactDetails.controls['district'].value,
                      enterdSubDistrict: this.subDistrictList.find(
                        (g) =>
                          g.id ===
                          this.contactDetails.controls['subDistrictId'].value
                      ).name,
                    };
                    this.sharedService.errorMessage(ele);
                    this.isGeospatialDataVerified = false;
                    this.count++;
                    if (this.count === 2) {
                      this.count = 0;
                      this.finalSubmit();
                      return;
                    }
                  } else {
                    this.isGeospatialDataVerified = true;
                    this.count = 0;
                    this.finalSubmit();
                    return;
                  }
                } else {
                  if (
                    parseInt(res.stateId) !== this.stateLgdId ||
                    parseInt(res.districtId) !== this.districtLgdId
                  ) {
                    let ele = {
                      showState: true,
                      stateName: res.state,
                      districtName: res.district,
                      subDistrict: '',
                      latitude: this.latitude,
                      longitude: this.longitude,
                      enteredState: this.contactDetails.controls['state'].value,
                      enteredDistrict:
                        this.contactDetails.controls['district'].value,
                      enterdSubDistrict: '',
                    };
                    this.sharedService.errorMessage(ele);
                    this.isGeospatialDataVerified = false;
                    this.count++;
                    if (this.count === 2) {
                      this.count = 0;
                      this.finalSubmit();
                      return;
                    }
                  } else {
                    this.isGeospatialDataVerified = true;
                    this.count = 0;
                    this.finalSubmit();
                    return;
                  }
                }
                // if (this.count === 2) {
                //   this.count = 0
                //   this.finalSubmit()
                // }
              } else {
                this.sharedService.showValidationMessage(
                  'Please enter correct Latitude and Longitude !!!'
                );
                this.count++;
                this.isGeospatialDataVerified = false;
                if (this.count === 2) {
                  this.count = 0;
                  this.finalSubmit();
                }
              }
            },
            (err) => {
              this.sharedService.showValidationMessage(
                'Please enter correct Latitude and Longitude !!!'
              );
              this.isGeospatialDataVerified = false;
              if (this.count === 2) {
                this.count = 0;
                this.finalSubmit();
              }
            }
          );
        } else {
          this.finalSubmit();
        }
      }
    }
  }
  finalSubmit() {
    var estYear = this.contactDetails.controls['yearOfEstablishment'].value;
    var a = estYear.toString();
    if (a.length !== 4) {
      this.isFormInvalid = true;
      this.sharedService.showError(
        `Please enter year of establisment in 'YYYY' format !!!`
      );
      return;
    }

    if (this.fileSizeExceed) {
      this.sharedService.showValidationMessagePDF(
        'File size should be less than 2MB.'
      );
      return;
    }
    if (this.myFiles.length === 0) {
      this.sharedService.showValidationMessagePDF('Please upload document !!!');
      return;
    }

    if (this.affilatedValueArr.length == 0) {
      this.affilatedValue = null;
    } else {
      this.affilatedValue = this.affilatedValueArr;
    }

    if (this.statutoryValueArr.length == 0) {
      this.statuatoryBodyValue = null;
    } else {
      this.statuatoryBodyValue = this.statutoryValueArr;
    }

    if (
      (this.otherAffiliatingUniversityRadio == null ||
        this.otherAffiliatingUniversityRadio == undefined) &&
      this.roleId !== 5
    ) {
      this.msg = 'Please Select University/Statutory body Yes/No';
      this.sharedService.showValidationMessage(
        'Please Select University/Statutory body Yes/No'
      );
      return;
    }
    if (
      this.isVisible == true &&
      (this.statuatoryBodyValue == null ||
        this.statuatoryBodyValue.length == 0) &&
      (this.affilatedValue == null || this.affilatedValue.length == 0)
    ) {
      this.msg = 'Please Select At least One Affiliated Body';
      this.sharedService.showValidationMessage(
        'Must Be Selected At least One Affiliated Body.'
      );

      return;
    }

    const formdata: FormData = new FormData();
    for (var i = 0; i < this.myFiles.length; i++) {
      formdata.append('file', this.myFiles[i]);
    }
    var ele = {
      confirm: true,
      success: false,
      requestId: '',
    };
    this.sharedService.savePopUp(ele).subscribe((value: any) => {
      if (value) {
        this.nodalOfficerForm.controls['nodalOfficerName'].value.toUpperCase();
        var stringArray =
          this.nodalOfficerForm.controls['nodalOfficerName'].value.split(' ');
        var firstName = '';
        if (stringArray['0']) {
          firstName = stringArray['0'];
        }

        var middleName = '';
        if (stringArray['1']) {
          middleName = stringArray['1'];
        }

        var lastName = '';
        if (stringArray['2']) {
          stringArray.splice(0, 2);
          lastName = stringArray.toString().replace(/,/g, ' ');
        } else {
          (lastName = middleName), (middleName = '');
        }

        var type = null;
        var body = null;
        var collegeType = null;
        if (
          this.instituteBasicInfoForm.controls['instituteType'].value === 'C'
        ) {
          type = 1;
          collegeType = this.contactDetails.controls['collegeType'].value;
          body = 0;
        } else {
          type = 2;
          body = this.contactDetails.controls['collegeType'].value;
          collegeType = null;
        }
        let telephone = this.nodalOfficerForm.value.stdCode?.toString().concat("-", this.nodalOfficerForm.value.phoneNumber?.toString());
        let payload = {
          websiteUrl: this.contactDetails.controls['websiteUrl'].value,
          yearOfEstablishment: this.contactDetails.controls['yearOfEstablishment'].value,
          lattitude: this.latitude,
          longitude: this.longitude,
          instituteTypeCorS: this.instituteBasicInfoForm.controls['instituteType'].value,
          instituteName: this.instituteBasicInfoForm.controls['name'].value.toUpperCase(),
          stateCode: this.instituteBasicInfoForm.controls['stateCode'].value,
          districtCode: this.instituteBasicInfoForm.controls['districtCode'].value,
          admissionYear: this.contactDetails.controls['admissionYear'].value,
          collegeTypeId: collegeType,
          stateBodyId: body,
          managementId: this.contactDetails.controls['managementType'].value,
          universityId: this.contactDetails.controls['affiliatingUniversityName'].value,
          isAffiliatedToOtherUniversity: this.contactDetails.controls['isInstitutionAffiliated'].value,
          otherAffiliatedUniversityName: this.contactDetails.controls['otherAffiliatedUniversity'].value,
          admissionProcessCompleted: this.contactDetails.controls['admissionEnrollment'].value,
          locationId: this.contactDetails.controls['location'].value,
          type: type,
          roleId: this.loginForm.controls['roleId'].value,
          isDeclarationAccepted: this.contactDetails.controls['underTaking'].value,
          surveyYear: this.contactDetails.controls['surveyYear'].value,
          personLine1: this.contactDetails.controls['addressLine1'].value,
          personLine2: this.contactDetails.controls['addressLine2'].value,
          personCity: this.contactDetails.controls['city'].value,
          personStateCode: this.instituteBasicInfoForm.controls['stateCode'].value,
          personDistrictCode: this.instituteBasicInfoForm.controls['districtCode'].value,
          directorName: this.institutionHead.controls['institutionalHeadName'].value.toUpperCase(),
          directorEmail: this.institutionHead.controls['email'].value,
          directorMobile: this.institutionHead.controls['mobile'].value,
          directorGender: this.institutionHead.controls['gender'].value,
          directorDesignation: this.institutionHead.controls['designation'].value,
          personName: this.nodalOfficerForm.controls['nodalOfficerName'].value.toUpperCase(),
          personDesignation: this.nodalOfficerForm.controls['nodalDesignation'].value,
          personEmail: this.nodalOfficerForm.controls['nodalOfficerEmail'].value,
          personMobile: this.nodalOfficerForm.controls['nodalOfficerMob'].value,
          personLandline: telephone ? telephone : '',
          stdCode: this.nodalOfficerForm.controls['stdCode'].value,
          personGender: this.nodalOfficerForm.controls['nodalGender'].value,
          personPincode: this.contactDetails.controls['pinCode'].value,
          firstName: this.nodalOfficerForm.controls['nodalOfficerName'].value.toUpperCase(),
          middleName: '',
          lastName: '',
          userId: this.loginForm.controls['userId'].value,
          bcryptPassword: this.encrypt.getEncryptedValue(this.loginForm.controls['password'].value),
          stateBodyTypeId: this.contactDetails.controls['stateBodyTypeId'].value ? this.contactDetails.controls['stateBodyTypeId'].value : 0,
          subDistrictId: this.contactDetails.controls['subDistrictId'].value,
          ulbId: this.contactDetails.controls['ulbId'].value,
          blockId: this.contactDetails.controls['blockId'].value,
          isGeospatialDataVerified: this.isGeospatialDataVerified,
          otherAffiliatingUniversity: this.affilatedValue,
          statuatoryBody: this.statuatoryBodyValue,
          isOtherAffiliatingUniversityStatuatoryBody: this.contactDetails.controls['isOtherAffiliatingUniversityStatuatoryBody'].value,
          ministryId: this.contactDetails.controls['ministryId'].value,
        };

        formdata.append('requestVo', JSON.stringify(payload));

        this.auth.saveRequestForm(formdata).subscribe(
          (res) => {
            if (res.status === 200) {
              this.sendSuceessMessage(res.data, payload.instituteName);
              var ele = {
                confirm: false,
                success: true,
                requestId: res.data,
              };
              this.latestId = 0;
              this.sharedService.savePopUp(ele);
              this.uploadedMedia = [];
              this.myFiles = [];
              this.myFilesName = '';
              (<HTMLInputElement>document.getElementById('myfile')).value = '';
              this.isFormInvalid = false;
              this.sharedService.showSuccess();
              this.contactDetails.reset();
              this.loginForm.reset();
              this.instituteBasicInfoForm.reset();
              this.nodalOfficerForm.reset();
              this.institutionHead.reset();
              this.contactDetails
                .get('affiliatingUniversityState')
                ?.setValidators(Validators.required);
              this.contactDetails
                .get('affiliatingUniversityName')
                ?.setValidators(Validators.required);
              this.contactDetails
                .get('otherAffiliatedUniversity')
                ?.setValidators(Validators.required);
              this.contactDetails
                .get('isInstitutionAffiliated')
                ?.setValidators(Validators.required);
              this.contactDetails
                .get('affiliatingUniversityState')
                ?.updateValueAndValidity();
              this.contactDetails
                .get('affiliatingUniversityName')
                ?.updateValueAndValidity();
              this.contactDetails
                .get('otherAffiliatedUniversity')
                ?.updateValueAndValidity();
              this.contactDetails
                .get('isInstitutionAffiliated')
                ?.updateValueAndValidity();
              this.nodalOfficerForm.get('nodalOfficerMob')?.enable();
              this.nodalOfficerForm
                .get('nodalOfficerMob')
                ?.updateValueAndValidity();
              this.nodalOfficerForm.get('nodalOfficerEmail')?.enable();
              this.nodalOfficerForm
                .get('nodalOfficerEmail')
                ?.updateValueAndValidity();
              this.emailOtp = false;
              this.mobileOtp = false;
              this.successVerifyOTP = false;
              this.successVerifyMobileOTP = false;
              this.insAff = false;
              this.admissionStarted = false;
              this.insType = true;
              this.hide = true;
              this.hideE = true;
              this.disabledEmail = true;
              this.disabledMobile = true;
              this.selectedIndex = 0;
            }
          },
          (err) => {
            this.getCaptcha()
            if (err.error.message === 'Incorrect file type, PDF required.') {
              this.sharedService.showValidationMessagePDF(
                'Incorrect file type, PDF required'
              );
            } else {
              this.sharedService.apiNotRespond();
            }
            // this.sharedService.apiNotRespond()
          }
        );
      } else {
        this.getCaptcha();
        this.contactDetails.get('Verfication')?.setValue('')
      }
    });
  }
  sendSuceessMessage(requestId: any, instituteName: any) {
    this.auth.sendMessage(requestId, instituteName).subscribe(
      (res) => { },
      (err) => {
        this.sharedService.apiNotRespond();
      }
    );
  }
  async getFileDetails(e: any) {
    // const buffer = await readChunk(e.target.file, { length: 4100 });

    // console.log(await fileTypeFromBuffer(buffer));
    this.myFiles = [];
    this.myFilesName = '';
    this.uploadedMedia = [];
    for (var i = 0; i < e.target.files.length; i++) {
      let size: number = e.target.files[i].size / 1024 / 1024;
      size = parseInt(size.toFixed(2));
      if (size > 2) {
        this.fileSizeExceed = true;
        this.sharedService.showValidationMessage('File should be in pdf format only & less than 2MB. !!!');
        return;
      } else {
        this.fileSizeExceed = false;
        this.myFiles.push(e.target.files[i]);
        this.myFilesName += e.target.files[i].name;
      }
      if (!(e.target.files.length - 1 === i)) {
        this.myFilesName += ',';
      }
    }
    const target = e.target as HTMLInputElement;
    this.processFiles(target.files);
  }
  processFiles(files: any) {
    for (const file of files) {
      if (file.type != 'application/pdf') {
        this.sharedService.showValidationMessagePDF(
          'Please upload pdf file only!!!'
        );
        this.myFilesName = '';
        this.myFiles = [];
        return;
      }
      var reader = new FileReader();
      reader.readAsDataURL(file); // read file as data url
      reader.onload = (event: any) => {
        // called once readAsDataURL is completed
        this.myFilesName = file.name;
        this.uploadedMedia.push({
          FileName: file.name,
          FileSize:
            this.getFileSize(file.size) + ' ' + this.getFileSizeUnit(file.size),
          FileType: file.type,
          FileUrl: event.target.result,
          FileProgessSize: 0,
          FileProgress: 0,
          ngUnsubscribe: new Subject<any>(),
        });

        this.startProgress(file, this.uploadedMedia.length - 1);
      };
    }
  }
  async startProgress(file: any, index: number) {
    let filteredFile = this.uploadedMedia
      .filter((u: any, index: number) => index === index)
      .pop();

    if (filteredFile != null) {
      let fileSize = this.getFileSize(file.size);
      let fileSizeInWords = this.getFileSizeUnit(file.size);

      for (var f = 0; f < fileSize + fileSize * 0.0001; f += fileSize * 0.01) {
        filteredFile.FileProgessSize = f.toFixed(2) + ' ' + fileSizeInWords;
        var percentUploaded = Math.round((f / fileSize) * 100);
        filteredFile.FileProgress = percentUploaded;
        await this.fakeWaiter(Math.floor(Math.random() * 35) + 1);
      }
    }
  }

  fakeWaiter(ms: number) {
    return new Promise((resolve) => {
      setTimeout(resolve, ms);
    });
  }
  getFileSize(fileSize: number): number {
    if (fileSize > 0) {
      if (fileSize < this.fileSizeUnit * this.fileSizeUnit) {
        fileSize = parseFloat((fileSize / this.fileSizeUnit).toFixed(2));
      } else if (
        fileSize <
        this.fileSizeUnit * this.fileSizeUnit * this.fileSizeUnit
      ) {
        fileSize = parseFloat(
          (fileSize / this.fileSizeUnit / this.fileSizeUnit).toFixed(2)
        );
      }
    }

    return fileSize;
  }

  getFileSizeUnit(fileSize: number) {
    let fileSizeInWords = 'bytes';

    if (fileSize > 0) {
      if (fileSize < this.fileSizeUnit) {
        fileSizeInWords = 'bytes';
      } else if (fileSize < this.fileSizeUnit * this.fileSizeUnit) {
        fileSizeInWords = 'KB';
      } else if (
        fileSize <
        this.fileSizeUnit * this.fileSizeUnit * this.fileSizeUnit
      ) {
        fileSizeInWords = 'MB';
      }
    }

    return fileSizeInWords;
  }
  removeImage(idx: number) {
    this.uploadedMedia = this.uploadedMedia.filter((u, index) => index !== idx);
  }
  showFiled(value: boolean) {
    if (value) {
      this.showOptionField = true;
      this.showOptionField1 = false;
      this.instituteBasicInfoForm
        .get('aisheCode')
        ?.setValidators(Validators.required);
      this.instituteBasicInfoForm.get('aisheCode')?.updateValueAndValidity();
    } else {
      this.showOptionField = false;
      this.showOptionField1 = true;
      this.instituteBasicInfoForm.get('aisheCode')?.setValue('');
      this.instituteBasicInfoForm.get('aisheCode')?.clearValidators();
      this.instituteBasicInfoForm.get('aisheCode')?.updateValueAndValidity();
    }
  }
  isLocation(value: any) {
    if (value === 'R') {
      this.contactDetails.get('blockId')?.setValidators(Validators.required);
      this.contactDetails.get('blockId')?.updateValueAndValidity();
      this.contactDetails.get('ulbId')?.clearValidators();
      this.contactDetails.get('ulbId')?.updateValueAndValidity();
      this.getBlockList(
        this.instituteBasicInfoForm.controls['districtCode'].value
      );
    } else {
      this.contactDetails.get('ulbId')?.setValidators(Validators.required);
      this.contactDetails.get('ulbId')?.updateValueAndValidity();
      this.contactDetails.get('blockId')?.clearValidators();
      this.contactDetails.get('blockId')?.updateValueAndValidity();
      this.getUrbanBodyList(
        this.instituteBasicInfoForm.controls['districtCode'].value
      );
    }
  }
  getBlockList(distCode: any) {
    this.blockList = [];
    this.auth.getBlock(distCode).subscribe(
      (res) => {
        this.variables6 = res;
        this.blockList = this.variables6.slice();
      },
      (err) => { }
    );
  }
  getBlockLgd(value: any) {
    if (value !== 99999) {
      this.blockLgdId = this.blockList.find((e) => e.id === value).lgdBlockCode;
    }
  }
  getUrbanBodyList(distCode: any) {
    this.urbanBodyList = [];
    this.auth.getUrban(distCode).subscribe(
      (res) => {
        // console.log(res)
        this.variables7 = res;
        this.urbanBodyList = this.variables7.slice();
      },
      (err) => { }
    );
  }
  getToken() {
    this.auth.latLongToken().subscribe(
      (res) => {
        this.latLongToken = res.token;
      },
      (err) => { }
    );
  }
  keypress(event: any) {
    var inp = String.fromCharCode(event.keyCode);
    if (/^\d*\.?\d*$/.test(inp)) {
      return true;
    } else {
      event.preventDefault();
      return false;
    }
  }
  back() {
    window.location.href = 'https://demo.he.nic.in/aishehtml';
  }

  isInstitutionAfilated(value: any) {
    if (value == true) {
      this.isVisible = true;
      this.otherAffiliatingUniversityRadio = true;
      this.msg = '';
      // this.editReg.get('isOtherAffiliatingUniversityStatuatoryBody')?.setValue(true)
      if (this.contactDetails.controls['surveyYear'].value) {
        this.getAffilatedUni();
      }
      this.statutoryBody();
    } else {
      this.otherAffiliatingUniversityRadio = false;
      this.isVisible = false;
      // this.editReg.get('isOtherAffiliatingUniversityStatuatoryBody')?.setValue(false)
      // this.editReg.get('ministryId')?.setValue(null)
      this.affilatedValueArr = [];
      this.affilatedUniversityEditArr = null;
      this.statutoryValueArr = [];
      this.affListVisibleArray = [];
      this.statutoryListVisibleArray = [];
      this.statutoryArr = [];
      this.affilatedUniArr = [];
      // this.getAffilatedUni()
      // this.statutoryBody()
      this.statutoryEditArr = null;
      this.contactDetails.get('statuatoryBody')?.setValue([]);
      this.contactDetails.get('otherAffiliatingUniversity')?.setValue([]);
      this.msg = '';
    }
  }

  getOtherAffiliatingBody(e: any) {
    this.otherAffilatatedUniversityName = e;
    this.getAffilatedUni();
  }

  getAffilatedUni() {
    this.auth
      .otherAffiliatingUniversity(
        this.contactDetails.controls['surveyYear'].value
      )
      .subscribe(
        (res) => {
          let data = res;
          this.filterAffVariable = data?.filter(
            (e: any) => e?.name !== this.otherAffilatatedUniversityName
          );

          this.affilatedUniArr = this.filterAffVariable.slice();
          this.affilatedUniArr?.forEach((e: any) => {
            e.id = 'U-' + e.id;
          });
        },
        (err) => { }
      );
  }

  statutoryBody() {
    this.stateCode = this.instituteBasicInfoForm.value.stateCode
    this.auth.statutoryBody().subscribe((res) => {
      let data = res;
      let filterArray = data.filter((item: any) => item.id !== '01');

      let filterState = filterArray.filter(
        (item: any) => item.stateCode === this.stateCode
      );

      let filterArr = filterArray.filter(
        (item: any) => item.stateCode === '00'
      );
      this.statutoryArr = [...filterState, ...filterArr].sort(
        (a: any, b: any) => a.statutoryBody.localeCompare(b.statutoryBody)
      );


    });
  }

  ministryName() {
    this.auth.ministryName().subscribe(
      (res) => {
        this.ministryArr = [];
        this.ministryArr = res;
      },
      (err) => { }
    );
  }

  getStatutoryBody(event: any) {
    const selectedIds = event.value.map((id: any) => id.toString());
    const filterArr = this.statutoryArr.filter((item) =>
      selectedIds.includes(item.id)
    );
    if (selectedIds.length === 0) {
      this.statutoryValueArr = [];
      this.statutoryListVisibleArray = [];
    } else {
      for (let i = 0; i < filterArr.length; i++) {
        const selectedValue = filterArr[i]; // assuming event.value represents a single selection
        const existingIndex = this.statutoryValueArr.findIndex(
          (item: any) => item.id === selectedValue.id
        );
        if (existingIndex === -1) {
          let obj = {
            id: selectedValue.id,
            statuatoryBody: selectedValue.statutoryBody,
          };
          this.statutoryValueArr.push(obj);
          this.statutoryListVisibleArray = this.statutoryValueArr;
        }
      }
      this.statutoryValueArr = this.statutoryValueArr.filter((item: any) =>
        selectedIds.includes(item.id)
      );
      this.statutoryListVisibleArray = this.statutoryValueArr;
    }
  }

  toggleAllSegments(e: any, data: any) {
    this.itsChekcBox = e.source._selected;
    this.itsSearchValueUncheck = data.id;
  }

  getAffiliatingBody(event: any) {
    const selectedIds = event.value.map((id: any) => id.toString());
    const filterArr = this.affilatedUniArr.filter((item) =>
      selectedIds.includes(item.id)
    );
    if (selectedIds.length < 0) {
      this.affilatedValueArr = [];
      this.affListVisibleArray = [];
      return;
    }
    if (this.itsChekcBox) {
      for (let i = 0; i < filterArr.length; i++) {
        const selectedValue = filterArr[i];
        if (!this.affilatedValueArr.includes(selectedValue.id)) {
          let obj = {
            affiliatingUniv: selectedValue.universityNameState,
            affiliatingUnivDistrict: selectedValue.district?.name,
            affiliatingUnivId: selectedValue.id,
            affiliatingUnivState: selectedValue.stateCode?.name,
          };

          this.affilatedValueArr.push(obj);
        }
      }
      this.affilatedValueArr = Array.from(new Set([...this.affilatedValueArr]));
      const uniqueArr = this.affilatedValueArr.filter(
        (item: any, index: any, self: any) =>
          index ===
          self.findIndex(
            (t: any) => t.affiliatingUnivId === item.affiliatingUnivId
          )
      );
      this.affListVisibleArray = [...uniqueArr];
      this.affilatedValueArr = [...this.affListVisibleArray];
    } else {
      this.affilatedValueArr = this.affilatedValueArr.filter(
        (item: any) => item.affiliatingUnivId !== this.itsSearchValueUncheck
      );
      this.affListVisibleArray = [...this.affilatedValueArr];
    }
  }

  reqUserId: any;
  save() {
    if (!this.contactDetails.controls['underTaking'].value) {
      this.sharedService.showValidationMessage('Please confirm undertaking !!!');
      return;
    }
    if (this.contactDetails.value.pinCode?.toString().trim('') === '' || this.contactDetails.value.addressLine1?.trim('') === '') {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }



    if (!this.myFilesName && this.myFiles.length <= 0) {
      this.sharedService.showValidationMessage('Please upload document !!!');
      return;
    }
    const formData: FormData = new FormData();
    for (var i = 0; i < this.myFiles.length; i++) {
      formData.append('file', this.myFiles[i]);
    }
    let telephone = ''
    if (this.nodalOfficerForm.value.phoneNumber) {
      telephone = this.nodalOfficerForm.value.stdCode?.toString().concat("-", this.nodalOfficerForm.value.phoneNumber?.toString());
    }


    let payload = {
      instituteTypeCorS: 'R',
      roleId: '27',
      instituteName: this.instituteBasicInfoForm.value.instituteNameRD,
      stateCode: this.instituteBasicInfoForm.value.stateCode,
      districtCode: this.instituteBasicInfoForm.value.districtCode,
      firstName: this.nodalOfficerForm.value.nodalOfficerName,
      directorEmail: this.institutionHead.controls['email'].value,
      directorMobile: this.institutionHead.controls['mobile'].value,
      directorGender: this.institutionHead.value.gender,
      directorDesignation: this.institutionHead.value.designation,
      directorName: this.institutionHead.value.institutionalHeadName,
      personDesignation: this.nodalOfficerForm.value.nodalDesignation,
      personEmail: this.nodalOfficerForm.controls['nodalOfficerEmail'].value,
      personMobile: this.nodalOfficerForm.controls['nodalOfficerMob'].value,
      personLandline: telephone ? telephone : '',
      personGender: this.nodalOfficerForm.value.nodalGender,
      personPincode: this.contactDetails.value.pinCode,
      personName: this.nodalOfficerForm.value.nodalOfficerName,
      ministryId: this.instituteBasicInfoForm.value.ministryIdRD,
      personLine1: this.contactDetails.value.addressLine1,
      outsideUser: true,
    };

    formData.append('requestVo', JSON.stringify(payload));
    this.auth.saveRequestForm(formData).subscribe((res) => {
      if (res.status === 200) {
        this.getCaptcha();
        this.showErrorMsg = false;
        this.showField = false;
        this.reqUserId = res.data;
        this.verifyMobile = false
        this.selectedIndex = 0;

        this.instituteBasicInfoForm.reset();
        this.institutionHead.reset();
        this.contactDetails.reset();
        this.nodalOfficerForm.reset();
        //  this.loginForm.reset();
        this.showOptionField = false;
        this.showOptionField1 = false;
        this.mobileOtp = false;
        this.hide = true;
        this.successVerifyMobileOTP = false;
        this.myFiles = [];
        this.myFilesName = '';
        this.fileSizeExceed = false;
        this.instituteBasicInfoForm.controls['totalno'].setValue('');
        var ele = {
          confirm: false,
          success: false,
          requestId: this.reqUserId,
          researchReq: true
        };
        this.sharedService.savePopUp(ele)
        this.sharedService.showSuccessMessage(res.message);

      }
    }, err => {
      this.getCaptcha()
    });
  }

  verify(data: any) {
    var ele = {
      confirm: false,
      success: false,
      researchReq: true,
      requestId: '10004',
    };
    this.sharedService.savePopUp(ele);
  }
  allowOnlyDecimal(event: KeyboardEvent): void {
  const input = event.target as HTMLInputElement;
  const inputChar = event.key;
  const currentValue = input.value;
  const allowedKeys = ['Backspace', 'Tab', 'ArrowLeft', 'ArrowRight', 'Delete'];

  if (allowedKeys.includes(inputChar)) return;

  // Allow only one dot
  if (inputChar === '.') {
    if (currentValue.includes('.') || currentValue.length >= 9) {
      event.preventDefault();
    }
    return;
  }

  // Allow digits only
  if (!/^\d$/.test(inputChar) || currentValue.length >= 10) {
    event.preventDefault();
  }
}
}
