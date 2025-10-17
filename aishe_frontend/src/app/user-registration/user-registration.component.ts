import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { SharedService, routes } from 'src/app/shared/shared.service';
import { DomSanitizer } from '@angular/platform-browser';
import { MustMatch } from '../shared/custome-validators';
import { CustomErrorStateMatcher } from '../shared/validation';
import { NgbPopoverConfig } from '@ng-bootstrap/ng-bootstrap';
import { catchError, map, mapTo, of, Subject, timeout } from 'rxjs';
import { MailerService } from '../shared/mailer.service';
import { NotificationService } from '../service/reports/notification.service';
import { TitleCasePipe } from '@angular/common';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { UserAlreadyRegistredComponent } from '../dialog/user-already-registred/user-already-registred.component';
import { MatDialog } from '@angular/material/dialog';
@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.scss'],
})
export class UserRegistrationComponent implements OnInit {
  public routers: typeof routes = routes;
  userMsg: string = `The USER ID must contain 8-16 characters.Valid characters are letters (a-z, A-Z), numbers (0-9) and dot(.)`;
  passwordMessage: string =
    'Password must be of minimum 8 characters long with at least one Upper case, one lower case alphabet, one numeric value and  one special characters';
  registation: FormGroup;
  loginForm: FormGroup;
  dataUserRegistation: FormGroup;
  isFormInvalid: boolean = false;
  captchaText: any;
  encodeCaptcha: any;
  latestId: number = 0;
  emailOtp: boolean = false;
  mobileOtp: boolean = false;
  hide: boolean = false;
  hideE: boolean = false;
  displayE: any;
  display: any;
  show: boolean = false;
  userExists: boolean = false;
  universityId: any;
  inValidAishe: boolean = false;
  emailOTPVarify: string = '';
  mobileOTpVarify: string = '';
  emailOTPVarifyUpate: string = '';
  mobileUpdateOTP: string = '';
  showDetails: boolean = false;
  successVerifyOTP: boolean = false;
  successVerifyMobileOTP: boolean = false;
  hideVerify: boolean = false;
  hideVerifyM: boolean = false;
  @ViewChild('fileInput', { static: false }) myVar1: ElementRef | undefined;
  fileSizeUnit: number = 2048;
  myFiles: string[] = [];
  myFilesName: any = '';
  fileSizeExceed: any;
  uploadedMedia: Array<any> = [];
  isUniversity: boolean = false;
  bodyType: any;
  phonePattern = /^[0-9]{10,10}$/;
  filteredList1: any = [];
  filterdCountry: any = [];
  filterdState: any = [];
  filterdDistrict: any = [];
  usertype: any = [];
  country: any = [];
  state: any = [];
  district: any = [];
  showUser: any;
  showForAllUser: boolean = false;
  filterdCountry1: any;
  constructor(
    public fb: FormBuilder,
    public authService: AuthService,
    public sharedService: SharedService,
    public config: NgbPopoverConfig,
    public notify: NotificationService,
    public sanitizer: DomSanitizer,
    public encrypt: EncryptDecrypt,
    public errorMatcher: CustomErrorStateMatcher,
    public mailerService: MailerService,
    private titleCase: TitleCasePipe,
    public router: Router,
    public route: ActivatedRoute,
    private dialog : MatDialog
  ) {
    this.getcountry();
    config.placement = 'end';
    config.triggers = 'hover';
    let emailRegex: RegExp =
      /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    let userIdRegex: RegExp = /^[a-zA-Z0-9.]{8,16}$/;
    let passwordRegex: RegExp =
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{7,15}$/;
    let mobileNoRegex: RegExp = /^[0]?[123456789]\d{9}$/;
    this.registation = this.fb.group({
      universityName: { value: '', disabled: true },
      aisheCode: ['', [Validators.required]],
      alternateEmail: ['', [Validators.pattern(emailRegex)]],
      alternateMobile: ['', [Validators.pattern(mobileNoRegex)]],
      emailId: ['', [Validators.required, Validators.pattern(emailRegex)]],
      firstNameUser: ['', [Validators.required]],
      genderId: ['', [Validators.required]],
      middleNameUser: ['', []],
      lastNameUser: ['', []],
      mobileNo: ['', [Validators.required, Validators.pattern(mobileNoRegex)]],
      // emailOtp: ['', [Validators.required]],
      mobileOtp: ['', [Validators.required]],
      instituteName: { value: '', disabled: true },
      addressLine1: { value: '', disabled: true },
      addressLine2: { value: '', disabled: true },
      stateName: { value: '', disabled: true },
      districtName: { value: '', disabled: true },
      cityName: { value: '', disabled: true },
      roleName: { value: '', disabled: true },
      districtCode: '',
      stateCode: '',
      roleId: '',
      aisheCode1: { value: '', disabled: true },
    });
    this.loginForm = this.fb.group(
      {
        captcha: ['', [Validators.required]],
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
    this.dataUserRegistation = this.fb.group(
      {
        emailId: ['', [Validators.required, Validators.email]],
        // emailOtp: ['', [Validators.required]],
        firstName: ['', Validators.required],
        // lastName: ['', Validators.required],
        conf_password: ['', Validators.required],
        country: ['', Validators.required],
        addressStateCode: ['', Validators.required],
        addressDistrictCode: ['', Validators.required],
        stdCode: { value: '', disabled: true },
        phoneMobile: [
          '',
          [Validators.required, Validators.pattern(this.phonePattern)],
        ],
        mobileOtp: ['', [Validators.required]],
        userType: ['', Validators.required],
        genderIddataUser:['', Validators.required],
        captcha: ['', [Validators.required]],
        userPassword: [
          '',
          [Validators.required, Validators.pattern(passwordRegex)],
        ],
      },
      {
        validators: MustMatch('userPassword', 'conf_password'),
      }
    );
    if (this.router.url === '/userRegistration/datauser') {
      this.showForAllUser = true;
    } else {
      this.showForAllUser = false;
    }
    // this.route.params.forEach((params: Params) => {
    //   this.showUser = +params['user'];
    //   if (this.showUser === 0) {
    //     this.showForAllUser = true;
    //   } else {
    //     this.showForAllUser = false
    //   }
    // });
  }

  ngOnInit(): void {
    this.getCaptcha();
    this.getusertype();
  }
  getUserReq() {
    let x: any = this.registation.controls['aisheCode'].value
      ?.toUpperCase()
      .split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    if (loginMode === 'U') {
      this.inValidAishe = true;
      this.registation.get('instituteName')?.setValue('');
      this.registation.get('aisheCode1')?.setValue('');
      this.registation.get('addressLine1')?.setValue('');
      this.registation.get('addressLine2')?.setValue('');
      this.registation.get('stateName')?.setValue('');
      this.registation.get('districtName')?.setValue('');
      this.registation.get('cityName')?.setValue('');
      this.registation.get('roleName')?.setValue('');
      this.registation.get('stateCode')?.setValue('');
      this.registation.get('districtCode')?.setValue('');
      this.registation.get('roleId')?.setValue('');
      this.registation.get('universityName')?.setValue('');
      return;
    }
    if (
      loginMode.toUpperCase() !== 'U' &&
      loginMode.toUpperCase() !== 'S' &&
      loginMode.toUpperCase() !== 'C'
    ) {
      this.inValidAishe = true;
      this.registation.get('instituteName')?.setValue('');
      this.registation.get('aisheCode1')?.setValue('');
      this.registation.get('addressLine1')?.setValue('');
      this.registation.get('addressLine2')?.setValue('');
      this.registation.get('stateName')?.setValue('');
      this.registation.get('districtName')?.setValue('');
      this.registation.get('cityName')?.setValue('');
      this.registation.get('roleName')?.setValue('');
      this.registation.get('stateCode')?.setValue('');
      this.registation.get('districtCode')?.setValue('');
      this.registation.get('roleId')?.setValue('');
      this.registation.get('universityName')?.setValue('');
      return;
    }
    this.authService
      .getUserReqAishe(
        this.registation.controls['aisheCode'].value?.toUpperCase()
      )
      .subscribe(
        (res) => {},
        (err) => {}
      );
  }
  getusertype() {
    this.authService.getusertype().subscribe((res) => {
      this.usertype = res;
      this.filteredList1 = this.usertype.slice();
    });
  }

  onKeyUpEvent(event: any): void {
    this.authService.emailVerify(event.target.value).subscribe((res) => {
      if (
        res.statusCode == 'AI007' &&
        res.description ===
          'This Email is already exist. Please choose another emailId'
      ) {
        this.notify.showWarning(res.description, '');
        return res.null;
      }
    });
  }
  getCaptcha() {
    this.authService.getCaptchaText().subscribe(
      (resp: any) => {
        this.captchaText = this.sanitizer.bypassSecurityTrustUrl(resp.capcha);
        this.encodeCaptcha = resp.data;
      },
      (err) => {}
    );
  }
  verifyCaptcha() {
    let captcha = '';
    if (!this.showForAllUser) {
      let x = this.registation.controls['aisheCode'].value
        ?.toUpperCase()
        .split('-');
      let loginMode = x[0];
      if (loginMode === 'C') {
        if (!this.universityId) {
          this.sharedService.showError('University should not be blank');
          return;
        }
      }
      captcha = this.loginForm.controls['captcha'].value;
      if (this.registation.invalid || this.loginForm.invalid) {
        this.isFormInvalid = true;
        this.sharedService.showWarning();
        return;
      } else {
        this.isFormInvalid = false;
      }
      if (this.registation.controls['cityName'].value == '') {
        this.sharedService.showError("City can't be blank");
        return;
      }
      if (this.fileSizeExceed) {
        this.sharedService.showValidationMessage(
          'File size should be less than 2MB.'
        );
        return;
      }
      if (this.myFiles.length === 0) {
        this.sharedService.showValidationMessage('Please upload document !!!');
        return;
      }
    } else {
      captcha = this.dataUserRegistation.controls['captcha'].value;
    }

    this.authService.verifyGetCaptcha(captcha, this.encodeCaptcha).subscribe(
      (res) => {
        if (res.message == 'Captcha Valid') {
          if (!this.showForAllUser) {
            this.getExistUser();
          } else {
            this.submit();
          }
        } else {
          this.sharedService.showValidationMessage('Invalid Captcha !!!');
        }
      },
      (err) => {}
    );
  }
  getUserDetails() {
    let x: any = this.registation.controls['aisheCode'].value?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    if (loginMode === 'U') {
      this.inValidAishe = true;
      this.registation.get('instituteName')?.setValue('');
      this.registation.get('aisheCode1')?.setValue('');
      this.registation.get('addressLine1')?.setValue('');
      this.registation.get('addressLine2')?.setValue('');
      this.registation.get('stateName')?.setValue('');
      this.registation.get('districtName')?.setValue('');
      this.registation.get('cityName')?.setValue('');
      this.registation.get('roleName')?.setValue('');
      this.registation.get('stateCode')?.setValue('');
      this.registation.get('districtCode')?.setValue('');
      this.registation.get('roleId')?.setValue('');
      this.registation.get('universityName')?.setValue('');
      return;
    }
    if (
      loginMode.toUpperCase() !== 'U' &&
      loginMode.toUpperCase() !== 'S' &&
      loginMode.toUpperCase() !== 'C'
    ) {
      this.inValidAishe = true;
      this.registation.get('instituteName')?.setValue('');
      this.registation.get('aisheCode1')?.setValue('');
      this.registation.get('addressLine1')?.setValue('');
      this.registation.get('addressLine2')?.setValue('');
      this.registation.get('stateName')?.setValue('');
      this.registation.get('districtName')?.setValue('');
      this.registation.get('cityName')?.setValue('');
      this.registation.get('roleName')?.setValue('');
      this.registation.get('stateCode')?.setValue('');
      this.registation.get('districtCode')?.setValue('');
      this.registation.get('roleId')?.setValue('');
      this.registation.get('universityName')?.setValue('');
      return;
    }
    this.authService
      .checkAisheCode(
        this.registation.controls['aisheCode'].value.toUpperCase()
      )
      .subscribe(
        (res) => {
          if (res && res.length) {
            if (loginMode.toUpperCase() === 'C') {
              this.isUniversity = true;
            } else {
              this.isUniversity = false;
            }
            this.showDetails = true;
            this.inValidAishe = false;
            this.universityId = res['0'].universityId;
            if (res['0'].instituteName === null) {
              let ele = {
                registrationSuccess: false,
                success: false,
                confirm: false,
                eligible: true,
              };
              this.sharedService.savePopUp(ele);
              this.sharedService.showValidationMessage(
                'Your Institution is not active.Please contact your approving authority !!!'
              );
              return;
            }
            // this.registation.patchValue(res['0'])

            this.registation
              .get('instituteName')
              ?.setValue(res['0'].instituteName);
            this.registation
              .get('addressLine1')
              ?.setValue(res['0'].addressLine1);
            this.registation
              .get('addressLine2')
              ?.setValue(res['0'].addressLine2);
            this.registation.get('stateName')?.setValue(res['0'].stateName);
            this.registation
              .get('districtName')
              ?.setValue(res['0'].districtName);
            this.registation.get('cityName')?.setValue(res['0'].cityName);
            this.registation.get('roleName')?.setValue(res['0'].roleName);
            this.registation.get('aisheCode1')?.setValue(res['0'].aisheCode);
            this.registation.get('stateCode')?.setValue(res['0'].stateCode);
            this.registation
              .get('districtCode')
              ?.setValue(res['0'].districtCode);
            this.registation.get('roleId')?.setValue(res['0'].roleId);
            this.registation
              .get('universityName')
              ?.setValue(res['0'].universityName);
          } else {
            // this.getInstitutionDeatils(loginMode);
            this.registation.get('instituteName')?.setValue('');
            this.registation.get('aisheCode1')?.setValue('');
            this.registation.get('addressLine1')?.setValue('');
            this.registation.get('addressLine2')?.setValue('');
            this.registation.get('stateName')?.setValue('');
            this.registation.get('districtName')?.setValue('');
            this.registation.get('cityName')?.setValue('');
            this.registation.get('roleName')?.setValue('');
            this.registation.get('stateCode')?.setValue('');
            this.registation.get('districtCode')?.setValue('');
            this.registation.get('roleId')?.setValue('');
            this.registation.get('universityName')?.setValue('');
            this.registation.get('pinCode')?.setValue('');
            this.inValidAishe = true;
            this.showDetails = false;
          }
        },
        (err) => {}
      );
  }
  checkforRequest(){
    let payload = {
      aisheCode: this.registation.controls['aisheCode'].value,
    };
    this.authService.userMasterRequestByAisheCode(payload).subscribe((res:any)=>{
      if(res.status===200){
        if(res.data.length>0){
          //already registered user
           this.openDialog(res)
        }else{
          //new user
          this.getInstitutionDeatils();
        }
      }
    })
  }
    openDialog(data:any) {
      this.dialog.open(UserAlreadyRegistredComponent, {
        width: '45%',
        data:  data,
        disableClose: true,
      }).afterClosed().subscribe((result) => {
        // console.log("success",result)
      });
    }
  getInstitutionDeatils() {
    let x: any = this.registation.controls['aisheCode'].value
      ?.toUpperCase()
      .split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    if (loginMode === 'U') {
      this.inValidAishe = true;
      this.registation.get('instituteName')?.setValue('');
      this.registation.get('aisheCode1')?.setValue('');
      this.registation.get('addressLine1')?.setValue('');
      this.registation.get('addressLine2')?.setValue('');
      this.registation.get('stateName')?.setValue('');
      this.registation.get('districtName')?.setValue('');
      this.registation.get('cityName')?.setValue('');
      this.registation.get('roleName')?.setValue('');
      this.registation.get('stateCode')?.setValue('');
      this.registation.get('districtCode')?.setValue('');
      this.registation.get('roleId')?.setValue('');
      this.registation.get('universityName')?.setValue('');
      return;
    }
    if (
      loginMode.toUpperCase() !== 'U' &&
      loginMode.toUpperCase() !== 'S' &&
      loginMode.toUpperCase() !== 'C'
    ) {
      this.inValidAishe = true;
      this.registation.get('instituteName')?.setValue('');
      this.registation.get('aisheCode1')?.setValue('');
      this.registation.get('addressLine1')?.setValue('');
      this.registation.get('addressLine2')?.setValue('');
      this.registation.get('stateName')?.setValue('');
      this.registation.get('districtName')?.setValue('');
      this.registation.get('cityName')?.setValue('');
      this.registation.get('roleName')?.setValue('');
      this.registation.get('stateCode')?.setValue('');
      this.registation.get('districtCode')?.setValue('');
      this.registation.get('roleId')?.setValue('');
      this.registation.get('universityName')?.setValue('');
      return;
    }
    this.authService
      .getAisheCodeDetails(
        this.registation.controls['aisheCode'].value.toUpperCase(),
        loginMode === 'C' ? 'COLLEGE' : 'STANDALONE'
      )
      .subscribe(
        (res) => {
          if (res.status === 200) {
            if (
              res.message ===
              `This Institute(${this.registation.controls[
                'aisheCode'
              ].value.toUpperCase()}) Is Already Active!`
            ) {
              this.getDetails(loginMode);
            } else {
              let ele = {
                registrationSuccess: false,
                success: false,
                confirm: false,
                eligible: false,
                userReg: true,
                userRegMessage: res.message,
              };
              this.showDetails = false;
              this.inValidAishe = false;
              // this.sharedService.savePopUp(ele)
              this.sharedService.showValidationMessage(res.message);
              this.universityId = '';
              this.bodyType = '';
              this.registation.get('instituteName')?.setValue('');
              this.registation.get('aisheCode1')?.setValue('');
              this.registation.get('addressLine1')?.setValue('');
              this.registation.get('addressLine2')?.setValue('');
              this.registation.get('stateName')?.setValue('');
              this.registation.get('districtName')?.setValue('');
              this.registation.get('cityName')?.setValue('');
              this.registation.get('roleName')?.setValue('');
              this.registation.get('stateCode')?.setValue('');
              this.registation.get('districtCode')?.setValue('');
              this.registation.get('roleId')?.setValue('');
              this.registation.get('universityName')?.setValue('');
              return;
            }
            // let insType = this.registation.controls['aisheCode'].value.toUpperCase().split(/[\W\d]+/).join("")
            // if (res.message === `This Institute(${this.registation.controls['aisheCode'].value.toUpperCase()}) Is Already Active!`) {
            //   let ele = {
            //     type: insType,
            //     inactive: false
            //   }
            //   this.sharedService.newRequest(ele)
            // }
            // if (res.message === `Standalone Institution having this AISHE Code is Inactive/Deaffiliated, Please contact your UNO/SNO!`) {
            //   this.sharedService.showValidationMessage('Standalone Institution having this AISHE Code is Inactive/Deaffiliated, Please contact your SNO !!!');
            //   return;

            // }
            // if (res.message === `COLLEGE having this AISHE Code is Inactive/Deaffiliated, Please contact your UNO/SNO!`) {
            //   this.sharedService.showValidationMessage('COLLEGE having this AISHE Code is Inactive/Deaffiliated, Please contact your UNO/SNO!');
            //   return;

            // }
            // if(res.message === 'Standalone Institution having this AISHE Code is upgraded to College having AISHE Code: C-71516"'){
            //   return;
            // }
          }
        },
        (err) => {}
      );
  }
  getDetails(loginMode: string) {
    let payload = {
      aisheCode: this.registation.controls['aisheCode'].value.toUpperCase(),
    };
    this.authService.getInstituteEditDetail(payload).subscribe(
      (res) => {
        if (res && res.length) {
          if (loginMode.toUpperCase() === 'C') {
            this.isUniversity = true;
          } else {
            this.isUniversity = false;
          }
          this.showDetails = true;
          this.inValidAishe = false;
          // if (!res['0'].inMaxOpenSurveyYear) {
          //   let ele = {
          //     registrationSuccess: false,
          //     success: false,
          //     confirm: false,
          //     eligible: true
          //   }
          //   this.sharedService.savePopUp(ele)
          //   this.sharedService.showValidationMessage('Your Institution is not active.Please contact your approving authority !!!');
          //   return;
          // }
          if (loginMode.toUpperCase() === 'S') {
            this.sharedService.roleList.forEach((element) => {
              if (element.id.toString() === res['0'].typeId) {
                this.registation.get('roleId')?.setValue(element.roleId);
                this.registation.get('roleName')?.setValue(element.name);
              }
            });
          } else {
            this.registation
              .get('roleId')
              ?.setValue(this.sharedService.roleList['7'].roleId);
            this.registation.get('roleName')?.setValue('College');
          }
          this.universityId = res['0'].universityId;
          this.bodyType = res['0'].typeId;
          if (!res['0'].addressLine1) {
            this.registation.get('addressLine1')?.enable();
            this.registation
              .get('addressLine1')
              ?.setValidators(Validators.required);
            this.registation.get('addressLine1')?.updateValueAndValidity();
          } else {
            this.registation.get('addressLine1')?.disable();
            this.registation.get('addressLine1')?.clearValidators();
            this.registation.get('addressLine1')?.updateValueAndValidity();
          }
          if (!res['0'].addressLine2) {
            this.registation.get('addressLine2')?.enable();
            this.registation.get('addressLine2')?.updateValueAndValidity();
          } else {
            this.registation.get('addressLine2')?.disable();
            this.registation.get('addressLine2')?.updateValueAndValidity();
          }
          if (!res['0'].city) {
            this.registation.get('cityName')?.enable();
            // this.registation.get('cityName')?.setValidators(Validators.required)
            this.registation.get('cityName')?.updateValueAndValidity();
          } else {
            this.registation.get('cityName')?.disable();
            this.registation.get('cityName')?.updateValueAndValidity();
          }
          this.registation.get('instituteName')?.setValue(res['0'].name);
          this.registation.get('addressLine1')?.setValue(res['0'].addressLine1);
          this.registation.get('addressLine2')?.setValue(res['0'].addressLine2);
          this.registation
            .get('stateName')
            ?.setValue(res['0'].state ? res['0'].state.name : '');
          this.registation
            .get('districtName')
            ?.setValue(res['0'].district ? res['0'].district.name : '');
          this.registation.get('cityName')?.setValue(res['0'].city);
          this.registation
            .get('aisheCode1')
            ?.setValue(
              this.registation.controls['aisheCode'].value.toUpperCase()
            );
          this.registation
            .get('stateCode')
            ?.setValue(res['0'].state ? res['0'].state.stateCode : '');
          this.registation
            .get('districtCode')
            ?.setValue(res['0'].district ? res['0'].district.distCode : '');
          this.registation
            .get('universityName')
            ?.setValue(res['0'].universityName);
        } else {
          this.registation.get('instituteName')?.setValue('');
          this.registation.get('aisheCode1')?.setValue('');
          this.registation.get('addressLine1')?.setValue('');
          this.registation.get('addressLine2')?.setValue('');
          this.registation.get('stateName')?.setValue('');
          this.registation.get('districtName')?.setValue('');
          this.registation.get('cityName')?.setValue('');
          this.registation.get('roleName')?.setValue('');
          this.registation.get('stateCode')?.setValue('');
          this.registation.get('districtCode')?.setValue('');
          this.registation.get('roleId')?.setValue('');
          this.registation.get('universityName')?.setValue('');
          this.inValidAishe = true;
          this.showDetails = false;
        }
      },
      (err) => {}
    );
  }
  password() {
    this.show = !this.show;
  }
  // getExistUser() {
  //   this.authService
  //     .userExist(this.loginForm.controls['userId'].value)
  //     .subscribe(
  //       (res) => {
  //         if (res.UserExist === 'YES') {
  //           this.sharedService.showValidationMessagePDF('User already exists');
  //         } else {
  //           this.saveUserDetails();
  //         }
  //       },
  //       (err) => {}
  //     );
  // }
  uploadDocument() {
    const formdata: FormData = new FormData();
    for (var i = 0; i < this.myFiles.length; i++) {
      formdata.append('file', this.myFiles[i]);
    }
    this.authService
      .uploadNew(formdata, this.loginForm.controls['userId'].value)
      .subscribe(
        (res) => {
          if (res.status === 200) {
            let ele = {
              registrationSuccess: true,
              success: false,
              confirm: false,
              eligible: false,
            };
            this.sharedService.savePopUp(ele);

            let mail = {
              roleName: this.registation.controls['roleName'].value,
              userId: this.loginForm.controls['userId'].value,
            };
            this.mailerService.sendEmail('UserRegister', mail, false).subscribe(
              (res) => {
                if (res.status === 200) {
                }
              },
              (err) => {}
            );
          }
        },
        (err) => {}
      );
  }
  sendEmailOTP() {
    if (!this.registation.controls['emailId'].value) {
      this.sharedService.showValidationMessage('Please enter email');
      return;
    }
    const email = this.encrypt.getEncryptedValue(
      this.registation.controls['emailId'].value
    );
    this.authService.sendEmail(email, this.latestId).subscribe(
      (res) => {
        const status = this.encrypt.getDecryptedValue(res.status);
        const latestId15 = this.encrypt.getDecryptedValue(res.data1);
        const emailOTPVarify1 = this.encrypt.getDecryptedValue(res.data2);
        const Message = this.encrypt.getDecryptedValue(res.data);
        if (status === '200' && +latestId15) {
          this.latestId = +latestId15;
          this.emailOTPVarify = emailOTPVarify1;
          this.emailOtp = true;
          this.hideE = true;
          this.hideVerify = true;
          // this.registation.get('emailId')?.disable();
          // this.registation.get('emailId')?.updateValueAndValidity();
          this.sharedService.showSuccessMessage(Message);
          this.timerE(1);
        }
      },
      (err) => {}
    );
  }

  verifyEmailOTP() {
    const email = this.encrypt.getEncryptedValue(
      this.registation.controls['emailId'].value
    );
    const emailOtp = this.encrypt.getEncryptedValue(
      this.registation.controls['emailOtp'].value
    );
    if (this.registation.controls['emailOtp'].value !== this.emailOTPVarify) {
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.authService
      .verifyEOTP(email, emailOtp)
      .pipe(
        timeout(1000),
        map((res) => {
          const response = {
            statusCode: this.encrypt.getDecryptedValue(res.status),
            latestId1: this.encrypt.getDecryptedValue(res.data1),
            emailOtpVarify: this.encrypt.getDecryptedValue(res.data2),
          };
          return response;
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
          return of(null);
        })
      )
      .subscribe(
        (response) => {
          if (!response) return; // Stop execution if an error occurred
          const { statusCode, latestId1, emailOtpVarify } = response;

          if (
            this.latestId !== +latestId1 &&
            this.emailOTPVarify !== emailOtpVarify
          ) {
            this.sharedService.showValidationMessage('Invalid OTP');
            return;
          }
          if (
            statusCode === '200' &&
            this.latestId === +latestId1 &&
            this.emailOTPVarify === emailOtpVarify
          ) {
            this.sharedService.verifyEOTP();
            this.hideVerify = false;
            this.successVerifyOTP = true;
            this.registation.get('emailOtp')?.disable();
            this.registation.get('emailId')?.disable();
            this.latestId = 0;
            this.emailOTPVarify = '';
          }
        },
        (err) => {
          const statusE = this.encrypt.getDecryptedValue(err.error.status);
          if (statusE === '422') {
            this.sharedService.showValidationMessage('Invalid OTP');
          }
        }
      );
  }
  sendEmailOTPUpdate() {
    if (!this.dataUserRegistation.controls['emailId'].value) {
      this.sharedService.showValidationMessage('Please enter email');
      return;
    }
    const email = this.encrypt.getEncryptedValue(
      this.dataUserRegistation.controls['emailId'].value
    );
    this.authService.sendEmail(email, this.latestId).subscribe(
      (res) => {
        const status = this.encrypt.getDecryptedValue(res.status);
        const latestId1 = this.encrypt.getDecryptedValue(res.data1);
        const Message = this.encrypt.getDecryptedValue(res.data);
        const emailOTPVarifyUpate1 = this.encrypt.getDecryptedValue(res.data2);
        if (status === '200' && +latestId1) {
          this.latestId = +latestId1;
          this.emailOTPVarifyUpate = emailOTPVarifyUpate1;
          this.emailOtp = true;
          this.hideE = true;
          this.hideVerify = true;
          // this.registation.get('emailId')?.disable();
          // this.registation.get('emailId')?.updateValueAndValidity();
          this.sharedService.showSuccessMessage(Message);
          this.timerE(1);
        }
      },
      (err) => {
        const statusE = this.encrypt.getDecryptedValue(err.error.status);
        if (statusE === '400') {
        }
      }
    );
  }

  verifyEmailOTPUpdate() {
    const emailId = this.encrypt.getEncryptedValue(
      this.dataUserRegistation.controls['emailId'].value
    );
    const emailOtp = this.encrypt.getEncryptedValue(
      this.dataUserRegistation.controls['emailOtp'].value
    );
    if (
      this.dataUserRegistation.controls['emailOtp'].value !==
      this.emailOTPVarifyUpate
    ) {
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.authService
      .verifyEOTP(emailId, emailOtp)
      .pipe(
        timeout(1000),
        map((res) => {
          const response = {
            statusCode: this.encrypt.getDecryptedValue(res.status),
            latestId1: this.encrypt.getDecryptedValue(res.data1),
            emailOtpVarifyU: this.encrypt.getDecryptedValue(res.data2),
          };
          return response;
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
          return of(null);
        })
      )
      .subscribe(
        (response) => {
          if (!response) return; // Stop execution if an error occurred
          const { statusCode, latestId1, emailOtpVarifyU } = response;

          if (
            this.latestId !== +latestId1 &&
            this.emailOTPVarifyUpate !== emailOtpVarifyU
          ) {
            this.sharedService.showValidationMessage('Invalid OTP');
            return;
          }
          if (
            statusCode === '200' &&
            this.latestId === +latestId1 &&
            this.emailOTPVarifyUpate === emailOtpVarifyU
          ) {
            this.sharedService.verifyEOTP();
            this.hideVerify = false;
            this.successVerifyOTP = true;
            this.dataUserRegistation.get('emailOtp')?.disable();
            this.dataUserRegistation.get('emailId')?.disable();
            this.latestId = 0;
            this.emailOTPVarifyUpate = '';
            
          }
        },
        (err) => {
          const statusE = this.encrypt.getDecryptedValue(err.error.status);
          if (statusE === '400') {
            this.sharedService.showValidationMessage('Invalid OTP');
          }
        }
      );
  }
  sendMobileOTP() {
    if (!this.registation.controls['mobileNo'].value) {
      this.sharedService.showValidationMessage('Please enter mobile no.');
      return;
    }
    const mobileNo = this.encrypt.getEncryptedValue(
      this.registation.controls['mobileNo'].value
    );
    this.authService.sendMobile(mobileNo, this.latestId).subscribe(
      (res) => {
        const status = this.encrypt.getDecryptedValue(res.status);
        const latestId1 = this.encrypt.getDecryptedValue(res.data1);
        const mobileOTp = this.encrypt.getDecryptedValue(res.data2);

        if (status === '200' && +latestId1 && mobileOTp) {
          this.latestId = +latestId1;
          this.mobileOTpVarify = mobileOTp;
          this.hide = true;
          this.hideVerifyM = true;
          this.mobileOtp = true;
          // this.registation.get('mobileNo')?.disable();
          // this.registation.get('mobileNo')?.updateValueAndValidity();
          this.sharedService.showSuccessMessage(
            `OTP has been sent successfully to ${this.registation.controls['mobileNo'].value}.Please enter the same OTP.`
          );
          this.timer(1);
        }
      },
      (err) => {
        const statusE = this.encrypt.getDecryptedValue(err.error.status);
        if (statusE === '400') {
          // this.sharedService.showValidationMessage('Invalid OTP')
        }
      }
    );
  }
  verifyMobileOTP() {
    const mobileNo = this.encrypt.getEncryptedValue(
      this.registation.controls['mobileNo'].value
    );
    const mobileOtp = this.encrypt.getEncryptedValue(
      this.registation.controls['mobileOtp'].value
    );
    if (this.registation.controls['mobileOtp'].value !== this.mobileOTpVarify) {
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.authService
      .verifyMOTP(mobileNo, mobileOtp)
      .pipe(
        timeout(1000),
        map((res) => {
          const response = {
            statusCode: this.encrypt.getDecryptedValue(res.status),
            latestId1: this.encrypt.getDecryptedValue(res.data1),
            mobileOTpVarify: this.encrypt.getDecryptedValue(res.data2),
          };
          return response;
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
          return of(null);
        })
      )
      .subscribe(
        (response) => {
          if (!response) return; // Stop execution if an error occurred
          const { statusCode, latestId1, mobileOTpVarify } = response;
          if (
            this.latestId !== +latestId1 &&
            this.mobileOTpVarify !== mobileOTpVarify
          ) {
            this.sharedService.showValidationMessage('Invalid OTP');
            return;
          }
          if (
            statusCode === '200' &&
            this.latestId === +latestId1 &&
            this.mobileOTpVarify === mobileOTpVarify
          ) {
            this.sharedService.verifyMOTP();
            this.hideVerifyM = false;
            this.successVerifyMobileOTP = true;
            this.registation.get('mobileOtp')?.disable();
            this.registation.get('mobileNo')?.disable();
            this.latestId = 0;
          }
        },
        (err) => {
          const statusE = this.encrypt.getDecryptedValue(err.error.status);
          if (statusE === '400') {
            this.sharedService.showValidationMessage('Invalid OTP');
          }
        }
      );
  }

  sendMobileOTPUpdate() {
    if (!this.dataUserRegistation.controls['phoneMobile'].value) {
      this.sharedService.showValidationMessage('Please enter mobile no.');
      return;
    }
    const phoneMobile = this.encrypt.getEncryptedValue(
      this.dataUserRegistation.controls['phoneMobile'].value
    );
    this.authService.sendMobile(phoneMobile, this.latestId).subscribe(
      (res) => {
        const latestId12 = this.encrypt.getDecryptedValue(res.data1);
        const status = this.encrypt.getDecryptedValue(res.status);
        const Message = this.encrypt.getDecryptedValue(res.data);
        const mobileUpdateOTP1 = this.encrypt.getDecryptedValue(res.data2);
        if (status === '200' && +latestId12) {
          this.latestId = +latestId12;
          this.mobileUpdateOTP = mobileUpdateOTP1;
          this.hide = true;
          this.hideVerifyM = true;
          this.mobileOtp = true;
          // this.registation.get('mobileNo')?.disable();
          // this.registation.get('mobileNo')?.updateValueAndValidity();
          this.sharedService.showSuccessMessage(
            `OTP has been sent successfully to ${this.dataUserRegistation.controls['phoneMobile'].value}.Please enter the same OTP.`
          );
          this.timer(1);
        }
      },
      (err) => {
        const statusE = this.encrypt.getDecryptedValue(err.error.status);
        if (statusE === '400') {
          //this.sharedService.showValidationMessage('Invalid OTP')
        }
      }
    );
  }
  verifyMobileOTPUpdate() {
    const phoneMobile = this.encrypt.getEncryptedValue(
      this.dataUserRegistation.controls['phoneMobile'].value
    );
    const mobileOtp = this.encrypt.getEncryptedValue(
      this.dataUserRegistation.controls['mobileOtp'].value
    );
    if (
      this.dataUserRegistation.controls['mobileOtp'].value !==
      this.mobileUpdateOTP
    ) {
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.authService.verifyMOTP(phoneMobile, mobileOtp).pipe(timeout(1000),
      map((res) => {
        const response = {
          statusCode: this.encrypt.getDecryptedValue(res.status),
          latestId1: this.encrypt.getDecryptedValue(res.data1),
          mobileOTpVarifyUpdate: this.encrypt.getDecryptedValue(res.data2),
        };
        return response;
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
        return of(null);
    })).subscribe(
      (response) => {
        if (!response) return; // Stop execution if an error occurred
          const { statusCode, latestId1, mobileOTpVarifyUpdate } = response;
        if ((this.latestId !== +latestId1)&&( this.mobileUpdateOTP!==mobileOTpVarifyUpdate)) {
          this.sharedService.showValidationMessage('Invalid OTP');
          return;
        }
        if (statusCode === '200' && (this.latestId === +latestId1)&&( this.mobileUpdateOTP===mobileOTpVarifyUpdate)) {
          this.sharedService.verifyMOTP();
          this.hideVerifyM = false;
          this.successVerifyMobileOTP = true;
          this.dataUserRegistation.get('mobileOtp')?.disable();
          this.dataUserRegistation.get('phoneMobile')?.disable();
          this.latestId = 0;
          this.mobileUpdateOTP=""
        }
      },
      (err) => {
        const statusE = this.encrypt.getDecryptedValue(err.error.status);
        if (statusE === '400') {
          this.sharedService.showValidationMessage('Invalid OTP');
        }
      }
    );
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
        clearInterval(timer);
      }
    }, 1000);
  }
  restrictNumeric(event: { charCode: number }) {
    return event.charCode == 8 || event.charCode == 0
      ? null
      : event.charCode >= 48 && event.charCode <= 57;
  }
  getExistUser() {
    this.authService.userExist(this.loginForm.controls['userId'].value).subscribe(
      (res) => {
        if (res.UserExist === 'YES') {
          this.sharedService.showValidationMessagePDF('User already exists');
        } else {
          this.getUser()

        }
      },
      (err) => { }
    );
  }
  getUser() {
    
    this.authService.getUserNoToken(this.loginForm.controls['userId'].value).subscribe(res => {
      let userId = this.loginForm.controls['userId'].value.toUpperCase()
      if (res.data?.userId) {
        if (res.data?.userId?.toUpperCase() === userId.toUpperCase()) {
          this.sharedService.showValidationMessagePDF('User already exists');
          return;
        } else {
          this.saveUserDetails();
        }
      } else {
        this.saveUserDetails();
      }
    }, err => {

    })
  }
  saveUserDetails() {
    let x: any = this.registation.controls['aisheCode'].value?.split('-');
    var aisheId = x[1];
    var statelevelBody = null;
    var statelevelBodyIns = null;
    var loginMode = x[0];
    if (loginMode.toUpperCase() === 'S') {
      statelevelBody = null;
      statelevelBodyIns = aisheId;
    }
    if (loginMode.toUpperCase() === 'C') {
      statelevelBody = this.universityId;
      statelevelBodyIns = aisheId;
    }
    if (loginMode.toUpperCase() === 'U') {
      statelevelBody = aisheId;
      statelevelBodyIns = null;
    }

    let payload = {
      bodyType: this.bodyType,
      addressDistrictCode: this.registation.controls['districtCode'].value,
      addressLine1: this.registation.controls['addressLine1'].value,
      addressLine2: this.registation.controls['addressLine2'].value,
      addressStateCode: this.registation.controls['stateCode'].value,
      aisheCode: this.registation.controls['aisheCode'].value.toUpperCase(),
      altEmailId: this.registation.controls['alternateEmail'].value,
      bcryptPassword: this.encrypt.getEncryptedValue(
        this.loginForm.controls['password'].value
      ),
      city: this.registation.controls['cityName'].value,
      confirmBcryptPassword: this.encrypt.getEncryptedValue(
        this.loginForm.controls['confirmPassword'].value
      ),
      emailId: this.registation.controls['emailId'].value,
      name: this.registation.controls['firstNameUser'].value,
      genderId: this.registation.controls['genderId'].value,
      ipAddress: '',
      isApproved: 0,
      statusId: 1,
      // "lastName": this.registation.controls['lastNameUser'].value,
      // "middleName": this.registation.controls['middleNameUser'].value,
      phoneLandline: this.registation.controls['alternateMobile'].value,
      phoneMobile: this.registation.controls['mobileNo'].value,
      registrationDatetime: '',
      roleId: this.registation.controls['roleId'].value,
      stateCode: this.registation.controls['stateCode'].value,
      stateLevelBody: statelevelBody,
      stateLevelBodyInstitute: statelevelBodyIns,
      stdCode: 0,
      userId: this.loginForm.controls['userId'].value.toLowerCase(),
    };

    this.authService.saveUserRegistrationNew(payload).subscribe(
      (res) => {
        if (res.status === 201) {
          this.getCaptcha()
          this.uploadDocument();

          // this.sharedService.showSuccessMessage('Your request for User Registration has been saved successfully !!!')
        }
      },
      (err) => {
        this.getCaptcha()
      }
    );
  }

  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
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
  getcountry() {
    this.authService.getcountry().subscribe((res) => {
      this.country = res;
      this.filterdCountry = this.country.slice();
this.filterdCountry1=this.country.find((c:any)=> c.countryCode==='IN');
const i=this.filterdCountry.findIndex((item:any)=>item.countryCode==='IN')
this.filterdCountry.splice(i,1);
this.filterdCountry.unshift(this.filterdCountry1);
// console.log(this.filterdCountry);

       // Set default value 
       const defaultCountry = this.country.find((c:any) => c.name === 'INDIA');
       if (defaultCountry) {
         this.dataUserRegistation.patchValue({ country: defaultCountry });
         this.dataUserRegistation.patchValue({stdCode:defaultCountry.isdCode})
       this.loadStates('+91')
       }
    });
   
  }
  loadDistricts(stateCode: any) {
    this.authService.getDistrict(stateCode).subscribe(
      (res) => {
        this.district = res;
        this.filterdDistrict = this.district.slice();
      },
      (err) => {}
    );
  }
  loadStates(value: any) {
    if(value.isdCode){
      if(value.isdCode==='+91'){

        this.dataUserRegistation.get('addressStateCode')?.setValidators([Validators.required]);
        this.dataUserRegistation.get('addressStateCode')?.updateValueAndValidity();
      
        // Set Validators.required for 'addressDistrictCode' form control
        this.dataUserRegistation.get('addressDistrictCode')?.setValidators([Validators.required]);
        this.dataUserRegistation.get('addressDistrictCode')?.updateValueAndValidity();
        this.authService.getState().subscribe(
          (res) => {
            this.state = res;
            this.filterdState = this.state.slice();
          },
          (err) => {}
        );
      }else{
        this.filterdState=[];
        this.filterdDistrict=[];
     
        this.dataUserRegistation.get('stdCode')?.setValue(value.isdCode);
        this.dataUserRegistation.get('addressStateCode')?.setValue('');
        this.dataUserRegistation.get('addressStateCode')?.clearValidators();
        this.dataUserRegistation.get('addressStateCode')?.updateValueAndValidity();
        this.dataUserRegistation.get('addressDistrictCode')?.setValue('');
        this.dataUserRegistation.get('addressDistrictCode')?.clearValidators();
        this.dataUserRegistation.get('addressDistrictCode')?.updateValueAndValidity();
      }
     
    }else{
    
      this.authService.getState().subscribe(
        (res) => {
          this.state = res;
          this.filterdState = this.state.slice();
        },
        (err) => {}
      );
    }
   
  }
  submit() {
    this.getCaptcha();
    if (this.dataUserRegistation.invalid) {
      this.isFormInvalid = true;
      if (
        !this.dataUserRegistation.controls['mobileOtp'].value 
      ) {
        this.sharedService.showValidationMessage(
          `Please Verify Email Otp 'OR' Mobile OTP !!!`
        );
        return;
      }
      this.sharedService.showWarning();
      return;
    } else {
      this.isFormInvalid = false;
    }

    let userData = {
      addressDistrictCode:
        this.dataUserRegistation.controls['addressDistrictCode'].value,
      addressStateCode:
        this.dataUserRegistation.controls['addressStateCode'].value,
      country: this.dataUserRegistation.controls['country'].value.id,
      emailId: this.dataUserRegistation.controls['emailId'].value,
      genderId:this.dataUserRegistation.controls['genderIddataUser'].value,
      firstName: this.titleCase.transform(
        this.dataUserRegistation.value.firstName
      ),
      // lastName: this.titleCase.transform(
      //   this.dataUserRegistation.value.lastName
      // ),
      phoneMobile: this.dataUserRegistation.controls['phoneMobile'].value,
      stdCode: this.dataUserRegistation.controls['stdCode'].value,
      userPassword: this.encrypt.getEncryptedValue(
        this.dataUserRegistation.controls['userPassword'].value
      ),
      userType: this.dataUserRegistation.controls['userType'].value.id,
      isApproved: 1,
      userId: 0,
    };
    this.authService.regiData(userData).subscribe((res) => {
      if (res != null && res.statusCode == 'ER001') {
        this.notify.showError(
          'Email id already exists please try another Email Id'
        );
      }
      if (res.statusCode == 'AI001') {
        let ele = {
          dataUserRegistation: true,
        };
        this.sharedService.savePopUp(ele);
      }
    });
  }
}
