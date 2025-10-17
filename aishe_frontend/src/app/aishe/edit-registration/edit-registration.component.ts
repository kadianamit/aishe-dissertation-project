import { TitleCasePipe } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { catchError, map, of, timeout } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { NotificationService } from 'src/app/service/reports/notification.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-edit-registration',
  templateUrl: './edit-registration.component.html',
  styleUrls: ['./edit-registration.component.scss'],
})
export class EditRegistrationComponent implements OnInit, OnDestroy {
  editReg!: FormGroup;
  disabledLockDataOfficer: boolean = false;
  dataUserRegistation: FormGroup;
  display: any;
  displayE: any;
  isFormInvalid: boolean = false;
  latestId: number = 0;
  emailOtpVarify: string = "";
  mobileOtpVarify: string = "";
  emailOtp: boolean = false;
  mobileOtp: boolean = false;
  successVerifyOTP: boolean = true;
  successVerifyMobileOTP: boolean = true;
  hide: boolean = true;
  hideE: boolean = true;
  hideButton: boolean = false;
  disabledEmail: boolean = true;
  disabledMobile: boolean = true;
  districtList: any[] = [];
  stateList: any[] = [];
  variables: any[] = [];
  variables1: any[] = [];
  userDetails: any;
  stateCode: any;
  aisheId: any;
  loginMode: any;
  emailVerified: boolean = false;
  mobileVerified: boolean = false;
  userEmail: any;
  userMobile: any;
  userData: string | null;
  pincodeRegex: RegExp = /^[1-9]{1}[0-9]{5}$/;
  phonePattern = /^[0-9]{10,10}$/;
  pattLog = /^[0-9]+(?:\.[0-9]+)?$/;
  emailRegex: RegExp =
    /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
  websitePattern: RegExp =
    /^(http[s]?:\/\/){0,1}(www\.){0,1}[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}[\.]{0,1}/;
  mobileNoRegex: RegExp = /^[0]?[123456789]\d{9}$/
  filteredList1: any = [];
  filterdCountry: any = [];
  filterdState: any = [];
  filterdDistrict: any = [];
  usertype: any = [];
  country: any = [];
  state: any = [];
  district: any = [];

  instituteCategory: any;
  firstName: any;
  lastName: any;
  middleName: any;
  showInCollege: any;
  showInStandalone: any;
  showInUniv: any;
  universityList: any[] = [];
  selectedAreas: any;
  userDetailsSubs: any;
  mobileVerifiedDate: any;
  emailVerifiedDate: any;
  mobileVerifiedData: any;
  emailVerifiedData: any;
  isGeospatialDataVerified: boolean = false;
  subDistrict: any;
  titleList: Array<any> = [];
  constructor(
    public fb: FormBuilder,
    public sharedService: SharedService,
    public auth: AuthService,
    private titleCase: TitleCasePipe,
    public notify: NotificationService,
    public sharedservice: SharedService,
    public localService: LocalserviceService,
    private encrypt: EncryptDecrypt
  ) {
    this.userData = this.localService.getData('userData');
    this.aisheId = this.localService.getData('aisheCode');
    this.instituteCategory = this.localService.getData('loginMode');
    if (this.instituteCategory === 'C') {
      this.showInUniv = false;
      this.showInCollege = true;
      this.showInStandalone = false;
    } else if (this.instituteCategory === 'S') {
      this.showInUniv = false;
      this.showInCollege = false;
      this.showInStandalone = true;
    } else {
      this.showInUniv = true;
      this.showInCollege = false;
      this.showInStandalone = false;
    }

    this.dataUserRegistation = this.fb.group({
      emailId: ['', [Validators.required, Validators.pattern(this.emailRegex)]],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      country: ['', Validators.required],
      addressStateCode: ['', Validators.required],
      addressDistrictCode: ['', Validators.required],
      stdCode: { value: '', disabled: true },
      phoneMobile: [
        '',
        [Validators.required, Validators.pattern(this.phonePattern)],
      ],
      userType: ['', Validators.required],
    });
    if (this.userData !== 'true') {
      this.userDetailsSubs = this.sharedService.getUserDetails.subscribe(
        (res) => {
          if (res != 0) {
            this.userDetails = res;
            // this.firstName=res.firstName;
            // this.lastName=res.lastName;
            // this.middleName=res.middleName;
            this.userEmail = res.email;
            this.userMobile = res.mobile;
            if (res.email) {
              this.disabledEmail = false;
            }
            if (res.mobile) {
              this.disabledMobile = false;
            }
            this.stateCode = res.stateCode;

            // this.getState()
          }
        }
      );
    } else {
      this.getusertype();
      this.getcountry();
    }
    this.buildEditReg();
    this.getInstituteDetail();
    // this.getTitle();
  }
  ngOnInit(): void {
    if (this.userData !== 'true') {
      this.editReg.get('email')?.valueChanges.subscribe((value) => {
        if (this.editReg.get('email')?.valid) {
          this.disabledEmail = false;
        } else {
          this.disabledEmail = true;
        }
      });
      this.editReg.get('mobileNo')?.valueChanges.subscribe((value) => {
        if (this.editReg.get('mobileNo')?.valid) {
          this.disabledMobile = false;
        } else {
          this.disabledMobile = true;
        }
      });
    }
  }
  getusertype() {
    this.auth.getusertype().subscribe((res) => {
      this.usertype = res;
      this.filteredList1 = this.usertype.slice();
    });
  }
  getTitle() {
    this.auth.getTitleListPrivate().subscribe(
      (res) => {
        this.titleList = res;
      },
      (err) => {}
    );
  }

  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
  restrictNumeric(event: { charCode: number }) {
    return event.charCode == 8 || event.charCode == 0
      ? null
      : event.charCode >= 48 && event.charCode <= 57;
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
        // this.editRegistration.get('mobile')?.enable();
        // this.editRegistration.get('mobile')?.updateValueAndValidity();
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
        // this.editRegistration.get('email')?.enable();
        // this.editRegistration
        //   .get('email')
        //   ?.updateValueAndValidity();
        clearInterval(timer);
      }
    }, 1000);
  }

  sendEmailOTP() {
    const email = this.encrypt.getEncryptedValue(
      this.editReg.controls['email'].value
    );
    this.auth.sendEmail(email, this.latestId).subscribe(
      (res) => {
        const statusCode = this.encrypt.getDecryptedValue(res.status);
        const latestId12 = this.encrypt.getDecryptedValue(res.data1);
        const Message = this.encrypt.getDecryptedValue(res.data);
        const otpId1 = this.encrypt.getDecryptedValue(res.data2);
        if (statusCode === '200' && +latestId12) {
          this.latestId = +latestId12;
          this.emailOtpVarify = otpId1;
          this.emailOtp = true;
          this.emailVerified = false;
          this.editReg.get('emailOtp')?.setValidators(Validators.required);
          this.editReg.get('emailOtp')?.updateValueAndValidity();
          this.editReg.get('email')?.disable();
          this.editReg.get('email')?.updateValueAndValidity();
          this.sharedService.showSuccessMessage(Message);
          this.timerE(1);
        }
      },
      (err) => {}
    );
  }

  verifyEmailOTP() {
    const email = this.encrypt.getEncryptedValue(
      this.editReg.controls['email'].value
    );
    const emailOtp = this.encrypt.getEncryptedValue(
      this.editReg.controls['emailOtp'].value
    );
    if( this.editReg.controls['emailOtp'].value !== this.emailOtpVarify){
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.auth
      .verifyEOTP(email, emailOtp)
      .pipe(
        timeout(1000),
        map((res) => {
          const decryptedResponse = {
            statusCode: this.encrypt.getDecryptedValue(res.status),
            latestId1: this.encrypt.getDecryptedValue(res.data1),
            emailOtpVarify12: this.encrypt.getDecryptedValue(res.data2),
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

          const { statusCode, latestId1, emailOtpVarify12 } = decryptedResponse;
          if (
            this.latestId !== +latestId1 &&
            this.emailOtpVarify !== emailOtpVarify12
          ) {
            this.sharedService.showValidationMessage('Invalid OTP');
            return;
          }

          if (
            statusCode === '200' &&
            this.latestId === +latestId1 &&
            this.emailOtpVarify === emailOtpVarify12
          ) {
            this.editReg.get('mobileOtp')?.setValue('');
            this.editReg.get('emailOtp')?.clearValidators();
            this.editReg.get('emailOtp')?.updateValueAndValidity();
            this.sharedService.verifyEOTP();
            this.successVerifyOTP = true;
            this.hideE = false;
            this.emailOtp = false;
            this.emailVerified = true;
            this.emailVerifiedData = true;
            this.latestId = 0;
          }
        },
        (err) => {
          const statusCode = this.encrypt.getDecryptedValue(err.error.status);
          if (statusCode === '400') {
            this.sharedService.showValidationMessage('Invalid OTP');
          }
        }
      );
  }

  sendMobileOTP() {
    const mobileNo = this.encrypt.getEncryptedValue(
      this.editReg.controls['mobileNo'].value
    );
    this.auth.sendMobile(mobileNo, this.latestId).subscribe(
      (res) => {
        const statusCode = this.encrypt.getDecryptedValue(res.status);
        const Message = this.encrypt.getDecryptedValue(res.data);
        const latestId16 = this.encrypt.getDecryptedValue(res.data1);
        const mobileOtp= this.encrypt.getDecryptedValue(res.data2)
        if (statusCode === '200' && +latestId16 && mobileOtp) {
          this.latestId = +latestId16;
          this.mobileOtpVarify= mobileOtp;
          this.editReg.get('mobileOtp')?.setValidators(Validators.required);
          this.editReg.get('mobileOtp')?.updateValueAndValidity();
          this.editReg.get('mobile')?.disable();
          this.editReg.get('mobile')?.updateValueAndValidity();
          this.mobileOtp = true;
          this.sharedService.showSuccessMessage(Message);
          this.mobileVerified = false;
          this.timer(1);
        }
      },
      (err) => {}
    );
  }

  verifyMobileOTP() {
    const mobileNo = this.encrypt.getEncryptedValue(
      this.editReg.controls['mobileNo'].value
    );
    const mobileOtp = this.encrypt.getEncryptedValue(
      this.editReg.controls['mobileOtp'].value
    );
    if( this.editReg.controls['mobileOtp'].value !== this.mobileOtpVarify){
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.auth.verifyMOTP(mobileNo, mobileOtp).pipe(timeout(1000),map((res)=>{
      const decryptedResponse = {
        statusCode: this.encrypt.getDecryptedValue(res.status),
        latestId1: this.encrypt.getDecryptedValue(res.data1),
        mobileOtpVarify: this.encrypt.getDecryptedValue(res.data2),
      };
      return decryptedResponse;
    }),  catchError((err) => {
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
    })).subscribe(
      (decryptedResponse) => {

        if (!decryptedResponse) return; // Stop execution if an error occurred

        const { statusCode, latestId1, mobileOtpVarify } = decryptedResponse;
        if (
          this.latestId !== +latestId1 &&
          this.mobileOtpVarify !== mobileOtpVarify
        ) {
          this.sharedService.showValidationMessage('Invalid OTP');
          return;
        }

        if (
          statusCode === '200' &&
          this.latestId === +latestId1 &&
          this.mobileOtpVarify === mobileOtpVarify
        ) {
          this.editReg.get('mobileOtp')?.setValue('');
          this.editReg.get('mobileOtp')?.clearValidators();
          this.editReg.get('mobileOtp')?.updateValueAndValidity();
          this.sharedService.verifyMOTP();
          this.successVerifyMobileOTP = true;
          this.hide = false;
          this.mobileOtp = false;
          this.mobileVerified = true;
          this.mobileVerifiedData = true;
          this.latestId = 0;
        }
      },
      (err) => {
        const statusCode = this.encrypt.getDecryptedValue(err.error.status);
        if (statusCode === '400') {
          this.sharedService.showValidationMessage('Invalid OTP');
        }
      }
    );
  }
  getState() {
    this.auth.getState().subscribe(
      (res) => {
        if (res && res.length) {
          this.variables = [];
          this.variables = res;
          this.stateList = this.variables.slice();
          this.getDistrict(this.stateCode);
        }
      },
      (err) => {}
    );
  }
  getDistrict(state: any) {
    this.districtList = [];
    this.variables1 = [];
    this.auth.getDistrict(state).subscribe(
      (res) => {
        if (res && res.length) {
          this.variables1 = res;
          this.districtList = this.variables1.slice();
        }
      },
      (err) => {}
    );
  }
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
        this.sharedService.showValidationMessage(
          'Latitude [Range: 6.00000 - 38.00000] ,Values must contain maximum of 5 digits after the decimal point.'
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
        this.sharedService.showValidationMessage(
          'Longitude [Range: 68.00000 - 98.00000] ,Values must contain maximum of 5 digits after the decimal point.'
        );

        return false;
      }
    }

    return true;
  }
  // update(data: any) {

  //   if (this.editRegistration.controls['email'].value !== this.userEmail) {
  //     if (!this.emailVerified){
  //       this.sharedService.showValidationMessage('Please verify email !!!');
  //       return;
  //     }else{
  //       this.emailVerified=true
  //     }
  //   }else{
  //     this.emailVerified=true
  //   }
  //   if (parseInt(this.editRegistration.controls['mobile'].value) !== parseInt(this.userMobile)) {
  //     if (!this.mobileVerified){
  //       this.sharedService.showValidationMessage('Please verify mobile number !!!');
  //       return;
  //     }else{
  //       this.mobileVerified = true
  //     }
  //   }else{
  //     this.mobileVerified=true
  //   }

  //   if (this.editRegistration.invalid) {
  //     this.sharedService.showWarning();
  //     this.isFormInvalid = true;
  //     return;
  //   } else {
  //     this.isFormInvalid = false
  //   }
  //   var latitude = null;
  //   var longitude = null;
  //   var constructedArea = null;
  //   var totalArea = null;
  //   if (this.editRegistration.controls['latitude'].value || this.editRegistration.controls['longitude'].value) {
  //     let c: boolean = this.validationLatLong(this.editRegistration.controls['latitude'].value, this.editRegistration.controls['longitude'].value);
  //     if (!c) {
  //       return;
  //     } else {
  //       latitude = this.editRegistration.controls['latitude'].value
  //       longitude = this.editRegistration.controls['longitude'].value
  //     }
  //   }

  //   let payload = {
  //     // "instituteHeadDesignation": data.instituteHeadDesignation,
  //     // "instituteHeadEmail": data.instituteHeadEmail,
  //     // "instituteHeadMobile":data.instituteHeadMobile,
  //     // "instituteHeadName": data.instituteHeadName,
  //     // "instituteHeadTelephone": data.instituteHeadTelephone,
  //     "latitude": latitude,
  //     "longitude": longitude,
  //     "emailVerified": this.emailVerified,
  //     "mobileVerified": this.mobileVerified,
  //     "addressLine1": data.addressLine1,
  //     "addressLine2": data.addressLine2,
  //     "aisheCode": data.aisheCode,
  //     "city": data.cityName,
  //     "email": this.editRegistration.controls['email'].value,
  //     "firstName": data.firstName,
  //     "lastName": data.lastName,
  //     "middleName": data.middleName,
  //     "mobile": this.editRegistration.controls['mobile'].value,
  //     "phoneLandline": data.phoneLandline,
  //     "stdCode": data.stdCode,
  //     "stateCode": data.stateCode,
  //     "stateId": data.stateCode,
  //     "districtId": data.districtCode,
  //     "userId": this.localService.getData('userId'),
  //     "website":data.website,

  //   }
  //   this.auth.updateUserRecord(payload).subscribe((res: any) => {
  //     if (res.status === 201) {
  //       this.emailVerified=false;
  //       this.mobileVerified=false;
  //       this.emailOtp = false;
  //       this.mobileOtp = false;
  //       this.successVerifyOTP = false;
  //       this.successVerifyMobileOTP = false;
  //       this.hide = true;
  //       this.hideE = true;
  //       this.editRegistration.get('mobile')?.enable();
  //       this.editRegistration
  //         .get('mobile')
  //         ?.updateValueAndValidity();
  //       this.editRegistration.get('email')?.enable();
  //       this.editRegistration
  //         .get('email')
  //         ?.updateValueAndValidity();
  //       this.sharedService.userDetails();
  //       this.getUserDetails(this.localService.getData('userId'))
  //     } if (res.status === 401) {
  //       this.sharedService.showValidationMessage('Please Verify Your Email or Mobile Or Both!!')
  //     }

  //   }, err => {
  //     // this.emailVerified=false
  //     // this.mobileVerified=false
  //   })
  // }
  getUserDetails(userId: any) {
    this.sharedService.global_loader = true;
    this.auth.getUser(userId).subscribe(
      (res: any) => {
        this.sharedService.global_loader = false;
        if (res.status === 200) {
          this.firstName = res.data.firstName;
          this.lastName = res.data.lastName;
          this.middleName = res.data.middleName;
          this.localService.saveData('userType', 'MoE Officer');
          this.userDetails = {
            addressLine1: res.data.addressLine1,
            addressLine2: res.data.addressLine2,
            latitude: res.data.latitudee,
            longitude: res.data.longitudee,
            // instituteHeadDesignation:res.data.instituteHeadDesignation,
            // instituteHeadEmail:res.data.instituteHeadEmail,
            // instituteHeadMobile:res.data.instituteHeadMobile,
            // instituteHeadName:res.data.instituteHeadName,
            // instituteHeadTelephone:res.data.instituteHeadTelephone,
            aisheCode: res.data.aisheCode,
            cityName: res.data.city,
            districtCode: res.data.districtId,
            districtName: res.data.districtName,
            email: res.data.email,
            firstName: res.data.firstName,
            middleName: res.data.middleName ? res.data.middleName : '',
            lastName: res.data.lastName ? res.data.lastName : '',
            instituteName: res.data.instituteName,
            lsy: res.data.lsy,
            mobile: res.data.mobile,
            roleId: res.data.roleId,
            stateCode: res.data.stateId,
            stateName: res.data.stateName,
            userId: res.data.userId,
            phoneLandline: res.data.phoneLandline,
            stdCode: res.data.stdCode,
          };
          if (this.userDetails.aisheCode) {
            let data = this.userDetails.aisheCode;
            let insName = this.userDetails.instituteName + '(' + data + ')';
            this.localService.saveData('insName', insName);
            this.localService.saveData(
              'instituteName',
              this.userDetails.instituteName
            );
            this.localService.saveData('aisheCode', this.userDetails.aisheCode);

            let x: any = data?.split('-');
            this.aisheId = x[1];
            this.loginMode = x[0];
            this.localService.saveData('loginMode', x[0]);
            this.localService.saveData('id', x[1]);
          } else {
            this.localService.saveData('aisheCode', '');
            this.localService.saveData('instituteName', '');
            this.localService.saveData('loginMode', '');
          }

          this.localService.saveData('userId', this.userDetails.userId);
          this.sharedService.setUserDetails(this.userDetails);
        } else {
          this.sharedService.showValidationMessage('Invalid UserId');
        }
      },
      (err: any) => {
        this.sharedService.global_loader = false;
        this.sharedService.apiNotRespond();
      }
    );
  }
  getcountry() {
    this.auth.getcountryPrivate().subscribe((res) => {
      this.country = res;
      this.filterdCountry = this.country.slice();
      this.getUserDetailsDataUser();
    });
  }
  loadDistricts(stateCode: any) {
    this.auth.getDistrict(stateCode).subscribe(
      (res) => {
        this.district = res;
        this.filterdDistrict = this.district.slice();
      },
      (err) => {}
    );
  }
  loadStates(value: any) {
    let isdCode = this.country.find((ele: any) => ele.id === value).isdCode;
    this.dataUserRegistation.get('stdCode')?.setValue(isdCode);
    this.auth.getState().subscribe(
      (res) => {
        this.state = res;
        this.filterdState = this.state.slice();
      },
      (err) => {}
    );
  }
  submit() {
    let userData = {
      addressDistrictCode:
        this.dataUserRegistation.controls['addressDistrictCode'].value,
      addressStateCode:
        this.dataUserRegistation.controls['addressStateCode'].value,
      country: this.dataUserRegistation.controls['country'].value,
      emailId: this.dataUserRegistation.controls['emailId'].value,
      firstName: this.titleCase.transform(
        this.dataUserRegistation.value.firstName
      ),
      lastName: this.titleCase.transform(
        this.dataUserRegistation.value.lastName
      ),
      phoneMobile: this.dataUserRegistation.controls['phoneMobile'].value,
      stdCode: this.dataUserRegistation.controls['stdCode'].value,
      userType: this.dataUserRegistation.controls['userType'].value,
      userId: this.localService.getData('userId'),
    };
    this.auth.regiData(userData).subscribe((res) => {
      if (res != null && res.statusCode == 'ER001') {
        this.notify.showError(
          'Email id already exists please try another Email Id'
        );
      }
      if (res.statusCode == 'AI001') {
        this.sharedService.userDetails();
      }
    });
  }
  getUserDetailsDataUser() {
    this.auth.getDataUserById(this.localService.getData('email')).subscribe(
      (res: any) => {
        this.sharedservice.global_loader = false;
        if (res.status.statusCode === 'AI001') {
          var userData = 'true';

          this.userDetails = {
            country: res.user.country,
            districtCode: res.user.addressDistrictCode,
            email: res.user.emailId,
            firstName: res.user.firstName,
            lastName: res.user.lastName ? res.user.lastName : '',
            mobile: res.user.phoneMobile,
            stateCode: res.user.addressStateCode,
            userId: res.user.userId,
            phoneLandline: res.user.phoneLandline,
            userType: res.user.userType.trim(''),
            stdCode: res.user.stdCode,
            stateName: res.stateName,
            districtName: res.districtName,
            userTypeName: res.userTypeName,
            countryName: res.countryName,
          };
          this.localService.saveData('userData', JSON.parse(userData));
          this.localService.saveData('email', this.userDetails.email);
          this.localService.saveData('firstName', this.userDetails.firstName);
          this.localService.saveData('lastName', this.userDetails.lastName);
          this.localService.saveData('stateCode', this.userDetails.stateCode);
          this.localService.saveData(
            'districtCode',
            this.userDetails.districtCode
          );
          this.localService.saveData('mobile', this.userDetails.mobile);
          this.localService.saveData('userType', this.userDetails.userType);
          this.localService.saveData('country', this.userDetails.country);
          this.localService.saveData('stdCode', this.userDetails.stdCode);
          this.localService.saveData('userId', this.userDetails.userId);
          this.localService.saveData('stateName', this.userDetails.stateName);
          this.localService.saveData(
            'districtName',
            this.userDetails.districtName
          );
          this.localService.saveData(
            'userTypeName',
            this.userDetails.userTypeName
          );
          this.localService.saveData(
            'countryName',
            this.userDetails.countryName
          );
          this.sharedservice.setUserDetails(this.userDetails);
          let user = {
            phoneMobile: this.localService.getData('mobile'),
            emailId: this.localService.getData('email'),
            addressStateCode: this.localService.getData('stateCode'),
            addressDistrictCode: this.localService.getData('districtCode'),
            firstName: this.localService.getData('firstName'),
            lastName: this.localService.getData('lastName'),
            country: this.localService.getData('country'),
            userType: this.localService.getData('userType'),
            stdCode: this.localService.getData('stdCode'),
            stateName: this.localService.getData('stateName'),
            districtName: this.localService.getData('districtName'),
            userTypeName: this.localService.getData('userTypeName'),
            countryName: this.localService.getData('countryName'),
          };

          this.dataUserRegistation.patchValue(user);
          this.dataUserRegistation
            .get('country')
            ?.setValue(Number(user.country));
          this.dataUserRegistation
            .get('userType')
            ?.setValue(Number(user.userType));

          this.loadStates(this.dataUserRegistation.controls['country'].value);
          if (this.dataUserRegistation.controls['addressDistrictCode'].value) {
            this.loadDistricts(
              this.dataUserRegistation.controls['addressStateCode'].value
            );
          }
        }
      },
      (err: any) => {
        this.sharedservice.global_loader = false;
        this.sharedservice.apiNotRespond();
      }
    );
  }

  //*********Institutional Detail */
  saveEditReg() {
    if (this.editReg.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    // if (this.editReg.controls['email'].value !== this.userEmail) {
    //   if (!this.emailVerified) {
    //     this.sharedService.showValidationMessage('Please verify email !!!');
    //     return;
    //   }
      
    // } else {
    //   this.emailVerified = true;
    // }
    if (
      parseInt(this.editReg.controls['mobileNo'].value) !==
      parseInt(this.userMobile)
    ) {
      if (!this.mobileVerified) {
        this.sharedService.showValidationMessage(
          'Please verify mobile number !!!'
        );
        return;
      }
      
    } else {
      this.mobileVerified = true;
    }
    var latitude = this.editReg.controls['latitude'].value;
    var longitude = this.editReg.controls['longitude'].value;
    // if (this.editReg.controls['latitude'].value || this.editReg.controls['longitude'].value) {
    //   let c: boolean = this.validationLatLong(this.editReg.controls['latitude'].value, this.editReg.controls['longitude'].value);
    //   if (!c) {
    //     return;
    //   } else {
    //     latitude = this.editReg.controls['latitude'].value
    //     longitude = this.editReg.controls['longitude'].value
    //   }
    // }
    let data = this.editReg.getRawValue();
    let payload = {};
    payload = {
      managementId: data.managementType,
      ownerShipId: data.ownershipType,

      instituteHeadDesignation: data.directordesignation,
      instituteHeadEmail: data.directorEmail,
      instituteHeadMobile: data.directorMobile,
      instituteHeadName: data.directorName,
      instituteHeadTelephone: data.directorTelephone?.toString(),

      nodalOfficerName: data.nodalOfficerName,
      nodalOfficerDesignation: data.designation,
      email: data.email,
      mobile: data.mobileNo,
      phoneLandline: data.landLine,

      locationId: data.location,
      addressLine1: data.addLine1,
      addressLine2: data.addLine2,
      city: data.city,
      stateId: data.stateCode,
      stateCode: data.stateCode,
      districtId: data.districtCode,
      blockId: data.blockId,
      ulbId: data.ulbId,
      pinCode: data.pinCode,
      latitude: data.latitude,
      longitude: data.longitude,

      aisheCode: this.aisheId,
      userId: this.localService.getData('userId'),
      firstName: data.nodalOfficerName,
      // "middleName":this.middleName,
      // "lastName":this.lastName,
      mobileVerified: this.mobileVerified,
      emailVerified: this.emailVerified,
      website: data.website,
      universityId: data.universityId,
      isGeospatialDataVerified: this.isGeospatialDataVerified,
      subDistrictId: this.subDistrict,
      //  nodalOfficerTitleId: data.nodalOfficerTitleId,
      //  headOfficerTitleId: data.headOfficerTitleId,
       nodalOfficerTitleId: null,
       headOfficerTitleId: null,
    };

    this.auth.updateUserRecord(payload).subscribe(
      (res: any) => {
        if (res.status === 201) {
          this.hideButton = false;
          this.successVerifyOTP = true;
          this.successVerifyMobileOTP = true;
          this.editReg.disable();
          this.disabledEmail = false;
          this.disabledMobile = false;
          this.mobileVerified = false;
          this.emailVerified = false;
          this.sharedService.showSuccess();
          this.getInstituteDetail();
        }
      },
      (err) => {}
    );
  }

  buildEditReg() {
    this.editReg = this.fb.group({
      id: 0,
      // nodalOfficerTitleId: ['', [Validators.required]],
      // headOfficerTitleId: ['', [Validators.required]],
      name: { value: '', disabled: true },
      instituteType: { value: '', disabled: true },
      managementType: { value: '', disabled: false },
      ownershipType: { value: '', disabled: false },
      directorName: [
        '',
        [Validators.required, Validators.pattern(/^[a-zA-Z\s.]*$/)],
      ],
      directordesignation: ['', []],
      directorEmail: [
        '',
        [Validators.required, Validators.pattern(this.emailRegex)],
      ],
      directorMobile: ['', [Validators.required,Validators.pattern(this.mobileNoRegex)]],
      directorTelephone: [''],
      nodalOfficerName: [
        '',
        [Validators.required, Validators.pattern(/^[a-zA-Z\s.]*$/)],
      ],
      designation: [''],
      email: ['', [Validators.required, Validators.pattern(this.emailRegex)]],
      mobileNo: ['', [Validators.required,Validators.pattern(this.mobileNoRegex)]],
      landLine: [''],
      location: [''],
      addLine1: { value: '', disabled: true },
      addLine2: { value: '', disabled: true },
      city: { value: '', disabled: true },
      country: [{ value: 'India', disabled: true }],
      stateCode: [{ value: '', disabled: true }],
      districtCode: [{ value: '', disabled: true }],
      blockId: [''],
      ulbId: [''],
      pinCode: ['', []],
      longitude: ['', []],
      latitude: ['', []],
      website: ['', []],

      //verified emailopt and mobileopt
      emailOtp: [''],
      mobileOtp: ['', []],
      universityId: [{ value: '', disabled: true }],
    });
    this.editReg.disable();
  }
  getInstituteDetail() {
    let payload = {
      aisheCode: this.aisheId,
    };
    this.auth.getInstituteEditDetailPrivate(payload).subscribe((res) => {
      // this.editReg.patchValue(res);
      this.isGeospatialDataVerified = res['0']?.isGeospatialDataVerified;
      (this.userEmail = res[0]?.nodalOfficerEmail),
        (this.userMobile = res['0']?.nodalOfficerMobile),
        // this.editReg
        //   .get('nodalOfficerTitleId')
        //   ?.setValue(res[0]?.nodalOfficerTitleId?.id);
      // this.editReg
      //   .get('headOfficerTitleId')
      //   ?.setValue(res[0]?.headOfficerTitleId?.id);
      this.editReg.get('name')?.setValue(res[0]?.name);
      this.editReg.get('id')?.setValue(res[0]?.id);
      this.editReg.get('instituteType')?.setValue(res[0]?.instituteType);
      this.editReg.get('managementType')?.setValue(res[0]?.management?.id);
      this.editReg.get('ownershipType')?.setValue(res[0]?.ownership?.id);

      this.editReg
        .get('directordesignation')
        ?.setValue(res[0]?.headOfficerDesignation);
      this.editReg.get('directorEmail')?.setValue(res[0]?.headOfficerEmail);
      this.editReg.get('directorMobile')?.setValue(res[0]?.headOfficerMobile);
      this.editReg.get('directorName')?.setValue(res[0]?.headOfficerName);
      this.editReg
        .get('directorTelephone')
        ?.setValue(res[0]?.headOfficerTelephone);

      this.editReg.get('nodalOfficerName')?.setValue(res[0]?.nodalOfficerName);
      this.editReg
        .get('designation')
        ?.setValue(res[0]?.nodalOfficerDesignation);
      this.editReg.get('email')?.setValue(res[0]?.nodalOfficerEmail);
      this.editReg.get('mobileNo')?.setValue(res[0]?.nodalOfficerMobile);
      this.editReg.get('landLine')?.setValue(res[0]?.nodalOfficerTelephone);

      this.editReg.get('location')?.setValue(res[0]?.locationId);

      this.editReg.get('addLine1')?.setValue(res[0]?.addressLine1);
      this.editReg.get('addLine2')?.setValue(res[0]?.addressLine2);
      this.editReg.get('city')?.setValue(res[0]?.city);
      this.editReg.get('country')?.setValue('INDIA');
      this.editReg.get('stateId')?.setValue(res[0]?.state?.stateCode);
      this.editReg.get('districtCode')?.setValue(res[0]?.district?.distCode);
      this.editReg.get('stateCode')?.setValue(res[0]?.state?.stateCode);

      this.editReg.get('blockId')?.setValue(res[0]?.blockId?.id);
      this.editReg.get('ulbId')?.setValue(res[0]?.ulbId?.id);
      this.editReg.get('pinCode')?.setValue(res[0]?.pinCode);
      this.editReg.get('latitude')?.setValue(res[0]?.latitude);
      this.editReg.get('longitude')?.setValue(res[0]?.longitude);
      this.editReg.get('website')?.setValue(res[0]?.website);
      this.editReg.get('universityId')?.setValue(res[0]?.universityId);
      this.localService.saveData('mobile', res[0]?.nodalOfficerMobile);
      this.localService.saveData('email', res[0]?.nodalOfficerEmail);
      this.localService.saveData('name', res[0]?.nodalOfficerName);
      this.mobileVerifiedDate = res[0]?.mobileVerifiedDate;
      this.emailVerifiedDate = res[0]?.emailVerifiedDate;
      this.mobileVerifiedData = res[0]?.mobileVerified;
      this.emailVerifiedData = res[0]?.emailVerified;

      (this.userDetails.addressLine1 = res[0]?.addressLine1),
        (this.userDetails.addressLine2 = res[0]?.addressLine2),
        (this.userDetails.aisheCode = res[0]?.aisheCode),
        (this.userDetails.cityName = res[0]?.city),
        (this.userDetails.districtCode = res[0]?.district?.distCode),
        (this.userDetails.districtName = res[0]?.district?.name),
        (this.userDetails.email = res[0]?.nodalOfficerEmail),
        (this.userDetails.name = res[0]?.nodalOfficerName),
        (this.userDetails.instituteName = res[0]?.name),
        (this.userDetails.mobile = res[0]?.nodalOfficerMobile),
        (this.userDetails.stateCode = res[0]?.state?.stateCode),
        (this.userDetails.stateName = res[0]?.state?.name),
        (this.userDetails.phoneLandline = res[0]?.nodalOfficerTelephone),
        (this.subDistrict = res[0]?.subDistrict?.id),
        this.sharedservice.setUserDetails(this.userDetails);
    });
  }

  ngOnDestroy(): void {
    if (this.userDetailsSubs) {
      this.userDetailsSubs.unsubscribe();
    }
  }
  showEnabled() {
    this.hideButton = true;
    this.successVerifyOTP = false;
    this.successVerifyMobileOTP = false;
    this.editReg.enable();
  }
}
