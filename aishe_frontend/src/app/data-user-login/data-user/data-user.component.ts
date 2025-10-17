import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { routes, SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-data-user',
  templateUrl: './data-user.component.html',
  styleUrls: ['./data-user.component.scss']
})
export class DataUserComponent implements OnInit {
  public routers: typeof routes = routes;
  captchaText: any;
  loginForm: FormGroup;
  isFornInvalid: any;
  show: boolean;
  loginTypeName = 'AISHE';
  loginviaAshiebool = true;
  userLabel: string = 'User Id';
  disabledField: boolean = true;
  isFormInvalid: boolean = false;
  encodeCaptcha: any;
  userDetails: any;
  userTypeName: any;
  userType: any[] = [];
  aisheId: any;
  loginMode: any;
  sub: any;
  type: any;
  cipherText: any;
  passwordRegex: RegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&<>`~^()-_+=|"'{}\s]{8,}$/;
  constructor(
    public authService: AuthService,
    public sharedservice: SharedService,
    public fb: FormBuilder,
    public encrypt: EncryptDecrypt,
    public router: Router,
    public activatedRoute: ActivatedRoute,
    public sanitizer: DomSanitizer,
    public localService:LocalserviceService
  ) {
    this.loginForm = this.fb.group({
      // userTypeId: ['', [Validators.required]],
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      captcha: ['', [Validators.required]],
    });
    this.show = false;
    this.getCaptcha();
  }

  ngOnInit() {


  }

  password() {
    this.show = !this.show;
  }
  checkCapcha(control: AbstractControl): any {
    if (control.value && this.loginForm) {
      let val = control.value;
      if (val.toUpperCase() != this.captchaText) {
        return { captchaNotValidError: true };
      }
    }
  }
  getCaptcha() {
    this.authService.getCaptchaText().subscribe(
      (resp: any) => {
        //this.sharedservice.global_loader = false;
        this.captchaText = this.sanitizer.bypassSecurityTrustUrl(resp.capcha);
        this.encodeCaptcha = resp.data;
      },
      (err: any) => {
        //this.sharedservice.global_loader = false;
      }
    );
  }
  login() {
   // this.sharedservice.global_loader = true;
    if (this.loginForm.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      //this.sharedservice.global_loader = false;
      this.getCaptcha()
      return;
    } else {
      this.isFormInvalid = false;
      let captchaText = this.loginForm.controls['captcha'].value;
      this.authService
        .verifyGetCaptcha(captchaText, this.encodeCaptcha)
        .subscribe(
          (res: { message: string }) => {
            if (res.message == 'Captcha Valid') {
             // this.sharedservice.global_loader = false;
              this.getUserDetails()
              // this.getVerifyUser();
            } else {
              this.getCaptcha()
             // this.sharedservice.global_loader = false;
              this.sharedservice.showValidationMessage('Invalid Captcha !!!');
            }
          },
          (err: any) => {
          //  this.sharedservice.global_loader = false;
          }
        );
    }
  }


  getUserDetails() {
    this.authService
      .getDataUserById(this.loginForm.value.username)
      .subscribe(
        (res: any) => {
        //  this.sharedservice.global_loader = false;
          if (res.status.statusCode === "AI001") {
            if (res.user.isApproved === 0) {

              this.sharedservice.showSuccessMessage('Your Email Id is disapproved !!!');
            } if (res.user.isApproved === 1) {
              this.getVerifyUser(res.user.isPasswordChange,res)
              
            }

          } if (res.status.statusCode === 'ER002') {
            this.sharedservice.showError(res.status.description);
            this.getCaptcha()
          }
        },
        (err: any) => {
        //  this.sharedservice.global_loader = false;
          this.sharedservice.apiNotRespond();
        }
      );
  }

  getVerifyUser(isPasswordChange:boolean,data:any) {
   // this.sharedservice.global_loader = true;
    let username = this.loginForm.controls['username'].value;
    let reqdata = {
      username: this.loginForm.controls['username'].value,
      password: this.encrypt.getEncryptedValue(
        this.loginForm.controls['password'].value
      ),
      usertype: 'Data User'
    };
    this.authService.getDataUserToken(reqdata.username, reqdata.password, reqdata.usertype).subscribe(
      (res: any) => {
       // this.sharedservice.global_loader = false;
        let getToken = res.token;
        let tokenarray = getToken.split('Bearer ');
        let token = tokenarray[tokenarray.length - 1];
        sessionStorage.setItem('token', token);
        var userData = 'true';
          this.localService.saveData('userData', JSON.parse(userData))
          this.localService.saveData('userId', data.user.userId)
        if (!isPasswordChange) {
          
          this.localService.saveData('change','true')
          this.sharedservice.openChangePass(this.loginForm.controls['username'].value).subscribe(res => {
            if (res) {
              this.loginForm.reset()
              this.getCaptcha()
            }
          })
        }else{
          this.localService.saveData('change','false')
          this.getUserFullDetails(data);
          
        }
        // this.getUserDetails();
        // this.auditLog();
      },
      (err: { error: { status: number } }) => {
        if (err.error.status === 401) {
          this.getCaptcha()
          this.sharedservice.showValidationMessage(
            'Your User Credential is Incorrect'
          );
        }
      // this.sharedservice.global_loader = false;
      }
    );
  }
getUserFullDetails(res:any){
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
    stdCode:res.user.stdCode,
    stateName:res.stateName,
    districtName:res.districtName,
    userTypeName:res.userTypeName,
    countryName:res.countryName,
    genderName:res.user.genderName
  };
  
  this.localService.saveData('email', this.userDetails.email)
  this.localService.saveData('firstName', this.userDetails.firstName)
  this.localService.saveData('lastName', this.userDetails.lastName)
  this.localService.saveData('stateCode', this.userDetails.stateCode)
  this.localService.saveData('districtCode', this.userDetails.districtCode)
  this.localService.saveData('mobile', this.userDetails.mobile)
  this.localService.saveData('userType', this.userDetails.userType)
  this.localService.saveData('country', this.userDetails.country)
  this.localService.saveData('stdCode',this.userDetails.stdCode)
  this.localService.saveData('userId', this.userDetails.userId);
  this.localService.saveData('stateName', this.userDetails.stateName);
  this.localService.saveData('districtName', this.userDetails.districtName);
  this.localService.saveData('userTypeName', this.userDetails.userTypeName);
  this.localService.saveData('countryName', this.userDetails.countryName);
  this.localService.saveData('genderName',this.userDetails.genderName);
  this.sharedservice.setUserDetails(this.userDetails);
  
  this.router.navigateByUrl('/aishe/home');
}
  auditLog() {
    let payload = {
      bcryptPassword: sessionStorage.getItem('token'),
      type: 'LOGIN',
      userId: this.loginForm.controls['username'].value,
    };
    // this.authService.auditLog(payload).subscribe((res: { status: number; }) => {
    //   if (res.status === 200) {
    //     // this.router.navigateByUrl('/aishe/home');
    //   }
    // }, (err: any) => {
    //   console.log(err)
    // })
  }
  clearData() {
    this.loginForm.reset();
  }
}
 
