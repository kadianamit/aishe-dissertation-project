import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { MustMatch } from 'src/app/shared/custome-validators';
import { MailerService } from 'src/app/shared/mailer.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';

@Component({
  selector: 'app-new-registartion',
  templateUrl: './new-registartion.component.html',
  styleUrls: ['./new-registartion.component.scss']
})
export class NewRegistartionComponent implements OnInit {
  userMsg: string = `The USER ID must contain 8-16 characters.Valid characters are letters (a-z), numbers (0-9) and dot(.)`
  passwordMessage: string = "Password must be of minimum 8 characters long with at least one Upper case, one lower case alphabet, one numeric value and  one special characters"
  registation: FormGroup;
  loginForm: FormGroup
  isFormInvalid: boolean = false;
  captchaText: any;
  encodeCaptcha: any;
  latestId: number = 0;
  show: boolean = false;
  userExists: boolean = false;
  universityId: any;
  inValidAishe: boolean = false
  showDetails: boolean = false
  userRoleList: any[] = [];
  districtList: any[] = [];
  stateList: any[] = [];
  variables: any[] = [];
  variables1: any[] = [];
  showButton: boolean = false
  showOtherDetails: boolean = false
  userActive:boolean=false
  constructor(public fb: FormBuilder, public authService: AuthService, public sharedService: SharedService,
    public sanitizer: DomSanitizer, public encrypt: EncryptDecrypt, public errorMatcher: CustomErrorStateMatcher, public mailerService: MailerService) {
    let emailRegex: RegExp = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    let userIdRegex: RegExp = /^[a-zA-Z0-9.]{8,16}$/;
    let passwordRegex: RegExp =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{7,15}$/;
    let mobileNoRegex: RegExp = /^[0]?[123456789]\d{9}$/
    this.registation = this.fb.group({
      userTypeId: ['', [Validators.required]],
      universityName: { value: '', disabled: true },
      aisheCode: ['', []],
      alternateEmail: ['', [Validators.pattern(emailRegex)]],
      alternateMobile: ['', [Validators.pattern(mobileNoRegex)]],
      emailId: ['', [Validators.required, Validators.pattern(emailRegex)]],
      firstNameUser: ['', [Validators.required]],
      genderId: ['', [Validators.required]],
      middleNameUser: ['', []],
      lastNameUser: ['', []],
      mobileNo: ['', [Validators.required, Validators.pattern(mobileNoRegex)]],
      addressLine1: ['', [Validators.required]],
      addressLine2: ['', [Validators.required]],
      stateName: { value: '', disabled: true },
      districtName: { value: '', disabled: true },
      cityName: ['', []],
      roleName: ['', []],
      districtCode: ['', []],
      stateCode: ['', []],
      roleId: ['', [Validators.required]],
      aisheCode1: { value: '', disabled: true },

    })
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
  }

  ngOnInit(): void {
    this.getCaptcha();
    this.getState();
  }
  getState() {
    this.authService.getStatePrivate().subscribe(
      (res) => {
        if (res && res.length) {
          this.variables = [];
          this.variables = res;
          this.variables = this.variables.sort((a, b) => a.name > b.name ? 1 : -1);
          this.stateList = this.variables.slice();

        }
      },
      (err) => { }
    );
  }
  getDistrict(state: any) {
    this.districtList = [];
    this.variables1 = [];
    this.authService.getDistrict(state).subscribe(
      (res) => {
        if (res && res.length) {
          this.variables1 = res;
          this.variables1 = this.variables1.sort((a, b) => a.name > b.name ? 1 : -1);
          this.districtList = this.variables1.slice();
        }
      },
      (err) => { }
    );
  }
  getUserRole(value: any) {
    this.userActive=false
    if (value === '1') {
      this.showOtherDetails = true
    } else {
      this.showOtherDetails = false
    }
    if (value !== '3') {
      this.showDetails = false
      this.registation.get('aisheCode')?.clearValidators()
      this.registation.get('aisheCode')?.updateValueAndValidity()
      this.registation.get('districtCode')?.clearValidators()
      this.registation.get('districtCode')?.updateValueAndValidity()
      this.clearFormData()
      this.formValidation();
      this.authService.getUserRoleId(value).subscribe(res => {
        this.userRoleList = res
        if (value === '1') {
          const index = this.userRoleList.findIndex((ele: any) => ele.roleId.toString() === this.sharedService.role['State Nodal Officer'])
          this.userRoleList.splice(index, 1);
          this.userRoleList = this.userRoleList.sort((a, b) => a.roleName > b.roleName ? 1 : -1);
          this.userRoleList.unshift({
            roleName: 'Ministry of Education',
            roleId: this.sharedService.role['Ministry of Education']
          })
        } else if (value === '2') {
          const index = this.userRoleList.findIndex((ele: any) => ele.roleId.toString() === this.sharedService.role['University'])
          this.userRoleList.splice(index, 1)
          this.userRoleList = this.userRoleList.sort((a, b) => a.roleName > b.roleName ? 1 : -1);
          this.userRoleList.unshift({
            roleName: 'Apex User',
            roleId: this.sharedService.role['Apex User']
          })
          this.userRoleList.unshift({
            roleName: 'State Nodal Officer',
            roleId: this.sharedService.role['State Nodal Officer']
          })
        }
      }, err => {

      })
    } else {
      this.userRoleList = [];
      this.userRoleList.push({
        roleId: this.sharedService.role['University'],
        roleName: 'University Nodal Officer'
      })
      this.registation.get('aisheCode')?.setValidators(Validators.required)
      this.registation.get('aisheCode')?.updateValueAndValidity()
      this.registation.get('districtCode')?.setValidators(Validators.required)
      this.registation.get('districtCode')?.updateValueAndValidity()
      this.clearFormData()

    }

  }

  formValidation() {
    this.registation.get('addressLine1')?.setValidators(Validators.required)
    this.registation.get('addressLine2')?.setValidators(Validators.required)
    this.registation.get('stateCode')?.setValidators(Validators.required)
    this.registation.get('cityName')?.setValidators(Validators.required)
    this.registation.get('addressLine1')?.enable()
    this.registation.get('addressLine1')?.updateValueAndValidity()
    this.registation.get('addressLine2')?.enable()
    this.registation.get('addressLine2')?.updateValueAndValidity()
    this.registation.get('cityName')?.enable()
    this.registation.get('cityName')?.updateValueAndValidity()
    this.registation.get('stateCode')?.enable()
    this.registation.get('stateCode')?.updateValueAndValidity()


  }

  getCaptcha() {
    this.authService.getCaptchaText().subscribe((resp: any) => {
      this.captchaText = this.sanitizer.bypassSecurityTrustUrl(resp.capcha);
      this.encodeCaptcha = resp.data;
    }, err => {
    });
  }
  verifyCaptcha() {
    if (this.registation.invalid || this.loginForm.invalid) {
      this.isFormInvalid = true;
      this.sharedService.showWarning();
      return;
    } else {
      this.isFormInvalid = false
    }

    this.authService.verifyGetCaptcha(this.loginForm.controls['captcha'].value, this.encodeCaptcha).subscribe(res => {
      if (res.message == 'Captcha Valid') {
        this.getExistUser()
      } else {
        this.sharedService.showValidationMessage('Invalid Captcha !!!')
      }
    }, err => {
    })
  }


  getUserDetails() {

    let payload = {
      aisheCode: this.registation.controls['aisheCode'].value.toUpperCase()
    }
    this.authService.getInstituteEditDetail(payload).subscribe((res) => {
      if (res && res.length) {
        this.showDetails = true
        this.showOtherDetails = true
        this.inValidAishe = false
        this.registation.get('instituteName')?.setValue(res['0'].name)
        this.registation.get('addressLine1')?.setValue(res['0'].addressLine1)
        this.registation.get('addressLine2')?.setValue(res['0'].addressLine2)
        this.registation.get('stateName')?.setValue(res[0]?.state?.name)
        this.registation.get('districtName')?.setValue(res['0']?.district?.name)
        this.registation.get('cityName')?.setValue(res['0']?.city)
        this.registation.get('roleName')?.setValue(res['0'].roleName)
        this.registation.get('aisheCode1')?.setValue(res['0'].aisheCode)
        this.registation.get('stateCode')?.setValue(res['0']?.state?.stateCode)
        this.registation.get('districtCode')?.setValue(res['0']?.district?.distCode)
        this.registation.get('universityName')?.setValue(res['0'].name)
      }
    }, err => {

    });
  }

  clearFormData() {
    this.registation.get('instituteName')?.setValue('')
    this.registation.get('aisheCode1')?.setValue('')
    this.registation.get('addressLine1')?.setValue('')
    this.registation.get('addressLine2')?.setValue('')
    this.registation.get('stateName')?.setValue('')
    this.registation.get('districtName')?.setValue('')
    this.registation.get('cityName')?.setValue('')
    this.registation.get('roleName')?.setValue('')
    this.registation.get('stateCode')?.setValue('')
    this.registation.get('districtCode')?.setValue('')
    // this.registation.get('roleId')?.setValue('')
    this.registation.get('universityName')?.setValue('')
  }
  password() {
    this.show = !this.show;
  }
  clearState() {
    if (this.registation.controls['roleId'].value === '6' || this.registation.controls['roleId'].value === '7') {
      this.showOtherDetails = false
    } else {
      this.showOtherDetails = true
    }
    this.registation.get('stateCode')?.setValue('')
  }
  getExistUser() {
    this.authService.userExist(this.loginForm.controls['userId'].value).subscribe(
      (res) => {
        if (res.UserExist === 'YES') {
          this.sharedService.showValidationMessagePDF('User already exists');
        } else {
          this.saveUserDetails()
        }
      },
      (err) => { }
    );
  }
  getUserByAISHE() {
    let x: any = this.registation.controls['aisheCode'].value?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    if (loginMode.toUpperCase() === 'C' || loginMode.toUpperCase() === 'S') {
      this.inValidAishe = true;
      return;
    } if (loginMode.toUpperCase() !== 'U' && loginMode.toUpperCase() !== 'S' && loginMode.toUpperCase() !== 'C') {
      this.inValidAishe = true;
      return;
    }
    let payload = {
      aisheCode: this.registation.controls['aisheCode'].value?.toUpperCase()
    }
    this.authService.getUserDetailsByAisheCode(payload).subscribe(res => {
      if (res.data) {
        if (res.data?.statusId === 2) {
          this.userActive = true
        } else {
          this.userActive = false
          this.getUserDetails()
        }
      }

    }, err => {

    })
  }

  getExistSNO() {
    this.authService.snoExist(this.registation.controls['roleId'].value, this.registation.controls['stateCode'].value).subscribe(
      (res) => {
        if (res.SnoExist === 'YES') {
          this.sharedService.showValidationMessagePDF('State Nodal Officer already exists');
        } else {
        }
      },
      (err) => { }
    );
  }

  restrictNumeric(event: { charCode: number }) {
    return event.charCode == 8 || event.charCode == 0
      ? null
      : event.charCode >= 48 && event.charCode <= 57;
  }
  saveUserDetails() {

    if (this.registation.controls['roleId'].value === this.sharedService.role['University']) {
      let x: any = this.registation.controls['aisheCode'].value?.split('-');
      var aisheId = x[1];
      var statelevelBody = null;
      var statelevelBodyIns = null
      var loginMode = x[0];
      if (loginMode.toUpperCase() === 'U') {
        statelevelBody = aisheId;
        statelevelBodyIns = null
      }
    }

    if (this.registation.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true
      return
    } else {
      this.isFormInvalid = false
    }
    let payload = {
      "addressDistrictCode": this.registation.controls['districtCode'].value,
      "addressLine1": this.registation.controls['addressLine1'].value,
      "addressLine2": this.registation.controls['addressLine2'].value,
      "addressStateCode": this.registation.controls['stateCode'].value,
      "aisheCode": this.registation.controls['aisheCode'].value ? this.registation.controls['aisheCode'].value.toUpperCase() : null,
      "altEmailId": this.registation.controls['alternateEmail'].value,
      "bcryptPassword": this.encrypt.getEncryptedValue(
        this.loginForm.controls['password'].value
      ),
      "city": this.registation.controls['cityName'].value,
      "confirmBcryptPassword": this.encrypt.getEncryptedValue(
        this.loginForm.controls['confirmPassword'].value
      ),
      "emailId": this.registation.controls['emailId'].value,
      "name": this.registation.controls['firstNameUser'].value,
      "genderId": this.registation.controls['genderId'].value,
      "ipAddress": "",
      "isApproved": 1,
      "statusId": 2,
      // "lastName": this.registation.controls['lastNameUser'].value,
      // "middleName": this.registation.controls['middleNameUser'].value,
      "phoneLandline": this.registation.controls['alternateMobile'].value,
      "phoneMobile": this.registation.controls['mobileNo'].value,
      "registrationDatetime": "",
      "roleId": this.registation.controls['roleId'].value,
      "stateCode": this.registation.controls['userTypeId'].value === '1' && this.registation.controls['roleId'].value === parseInt(this.sharedService.role['Apex User']) ? '' : this.registation.controls['stateCode'].value,
      "stateLevelBody": statelevelBody,
      "stateLevelBodyInstitute": statelevelBodyIns,
      "stdCode": 0,
      "userId": (this.loginForm.controls['userId'].value).toLowerCase()
    }

    this.authService.saveUserRegistrationNewPrivate(payload).subscribe(res => {
      if (res.status === 201) {
        this.showOtherDetails=false
        this.showDetails = false
        this.registation.reset();
        this.loginForm.reset();
        let ele = {
          registrationSuccess: false,
          newRegister: true,
          success: false,
          confirm: false,
          eligible: false
        }
        this.sharedService.savePopUp(ele)


        // this.mailerService.sendEmail('NewUserRegister', this.registation.controls['roleName'].value, this.loginForm.controls['userId'].value).subscribe(res => {
        //   if(res.status === 200){

        //   }
        // })
        // this.sharedService.showSuccessMessage('Your request for User Registration has been saved successfully !!!')
      }


    }, err => {

    })
  }

  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }

}
