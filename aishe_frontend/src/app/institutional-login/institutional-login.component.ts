import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormGroup,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { SharedService } from '../shared/shared.service';
import { AuthService } from '../service/auth.service';
import { EncryptDecrypt } from '../service/encrypt-decrypt';
import { DomSanitizer } from '@angular/platform-browser';
import { CookieService } from 'ngx-cookie-service';
import { LocalserviceService } from '../service/localservice.service';


@Component({
  selector: 'app-institutional-login',
  templateUrl: './institutional-login.component.html',
  styleUrls: ['./institutional-login.component.scss'],
})
export class InstitutionalLoginComponent  {
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
  passwordRegex: RegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&<>`~^()-_+=|"'{}\s]{8,}$/;
  constructor(
    public authService: AuthService,
    public sharedservice: SharedService,
    public fb: FormBuilder,
    public encrypt: EncryptDecrypt,
    public router: Router,
    public activatedRoute: ActivatedRoute,
    public sanitizer: DomSanitizer,
    public Cookie: CookieService,
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
    this.sub = this.activatedRoute.params.subscribe((params) => {
      this.type = +params['type'];
      // console.log(this.type)
      if (this.type == 1) {
        // console.log('ok')
      }
      if (this.type == 2) {
        //console.log('okk')
      }
    });
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
        this.sharedservice.global_loader = false;
        this.captchaText = this.sanitizer.bypassSecurityTrustUrl(resp.capcha);
        this.encodeCaptcha = resp.data;
      },
      (err: any) => {
        this.sharedservice.global_loader = false;
      }
    );
  }
  login() {
    // if(this.Cookie.get('countLogin')){
    //   alert('Already logged in');
    //   return;
    // }

    //this.sharedservice.global_loader = true;
    if (this.loginForm.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      //this.sharedservice.global_loader = false;
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
              this.getUserStatus();
            } else {

              this.getCaptcha()
             // this.sharedservice.global_loader = false;
              this.sharedservice.showValidationMessage('Invalid Captcha !!!');
            }
          },
          (err: any) => {
            //this.sharedservice.global_loader = false;
          }
        );
    }
  }

  getUserStatus() {
    this.authService
      .getUserNoToken(this.loginForm.controls['username'].value)
      .subscribe(
        (res: any) => {
          if (res.status === 200) {
            if (res.data?.statusId === 4) {
              let ele = {
                isApproved: true
              }
              this.sharedservice.savePopUp(ele)
            } if (res.data?.statusId === 2) {
              this.getVerifyUser(res.data?.isPasswordChange);
            } if (res.data?.statusId === 3) {
              let ele = {
                isInactive: true
              }
              this.sharedservice.savePopUp(ele)
            }
            if (res.data?.statusId === 1) {
              let ele = {
                isRegistarion: true
              }
              this.sharedservice.savePopUp(ele)
            }
            if (res.data?.statusId === null) {
              this.sharedservice.showValidationMessage(
                res.data?.message
              );

            }
            this.getCaptcha()
            // if (res.data.isApproved === 0 && res.data.statusId === 4) {
            //   let ele = {
            //     isApproved: true
            //   }

            //   this.sharedservice.savePopUp(ele)
            // } if (res.data.isApproved === 1 && res.data.statusId === 2) {
            //   this.getVerifyUser();
            // } if (res.data.statusId === 3) {
            //   let ele = {
            //     isInactive:true
            //   }
            //   this.sharedservice.savePopUp(ele)
            // }
            // if(res.data.isApproved === 0 && res.data.statusId === 1){
            //   let ele = {
            //     isRegistarion:true
            //   }
            //   this.sharedservice.savePopUp(ele)
            // }
          }
        }, err => {
          if (err.error.status === 404) {
            this.sharedservice.showValidationMessage(
              'Please enter valid User Id or Password'
            );
          }
        })
  }

  getUserDetails() {

    this.sharedservice.global_loader = true;
    this.authService
      .getUser(this.loginForm.controls['username'].value)
      .subscribe(
        (res: any) => {
          this.sharedservice.global_loader = false;
          if (res.status === 200) {
            // this.localService.saveData('userType', 'MoE Officer');
            this.localService.saveData('userId', res.data.userId);
            this.userDetails = {
              addressLine1: res.data.addressLine1,
              addressLine2: res.data.addressLine2,
              aisheCode: res.data.aisheCode,
              cityName: res.data.city,
              districtCode: res.data.districtId,
              districtName: res.data.districtName,
              email: res.data.email,
              name: res.data.name,

              instituteName: res.data.instituteName,
              lsy: res.data.lsy,
              minlsy: res.data.minlsy,
              mobile: res.data.mobile,
              roleId: res.data.roleId,
              stateCode: res.data.stateCode,//04-02-2025 change stateId to stateCode
              stateName: res.data.stateName,
              userId: res.data.userId,
              phoneLandline: res.data.phoneLandline,
              stdCode: res.data.stdCode,
              stateLevelBody: res.data.stateLevelBody,
              bcryptPassword: res.data.bcryptPassword,
              latitude: res.data.latitude,
              longitude: res.data.longitude,
              final: res.data?.finalSubmit,
              stateLevelBody11: res.data.stateLevelBody

            };
            this.sharedservice.setUserDetails(this.userDetails);
            this.localService.saveData('stateCode', res.data.stateCode);

            this.localService.saveData('universityId', res.data.aisheCode);
            this.localService.saveData('districtCode', res.data.districtId);
            this.localService.saveData('stateLevelBody', res.data.stateLevelBody);
            this.localService.saveData('mobile', res.data.mobile);
            this.localService.saveData('email', res.data.email);

            this.localService.saveData('name', res.data.name);
            // this.localService.saveData('final',res.data?.finalSubmit)

            // if (
            //   this.userDetails.roleId !== 7 &&
            //   this.userDetails.roleId !== 12 &&
            //   this.userDetails.roleId !== 13 &&
            //   this.userDetails.roleId !== 14 &&
            //   this.userDetails.roleId !== 15 &&
            //   this.userDetails.roleId !== 20 &&
            //   this.userDetails.roleId !== 21 &&
            //   this.userDetails.roleId !== 23 &&
            //   this.userDetails.roleId !== 26 &&
            //   this.userDetails.roleId !== 24 &&
            //   this.userDetails.roleId !== 1 &&
            //   this.userDetails.roleId !== 22 &&
            //   this.userDetails.roleId !== 6) {
            //   this.sharedservice.showValidationMessage(
            //     'Please enter valid User Id or Password'
            //   );
            //   return;
            // }


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
            this.localService.saveData('roleId', this.userDetails.roleId);

            this.localService.saveData('userId', this.userDetails.userId);
            this.localService.saveData('lsy', this.userDetails.lsy);
            this.localService.saveData('minlsy', this.userDetails.minlsy)


            // this.Cookie.set('countLogin', '1');
            this.router.navigateByUrl('/aishe/home');
          } else {
            this.getCaptcha()
            this.sharedservice.showValidationMessage('Invalid UserId');
          }
        },
        (err: any) => {
          this.sharedservice.global_loader = false;
          this.sharedservice.apiNotRespond();
        }
      );
  }

  getVerifyUser(isPasswordChange:boolean) {
   // this.sharedservice.global_loader = true;
    let reqdata = {
      username: this.loginForm.controls['username'].value,
      password: this.encrypt.getEncryptedValue(
        this.loginForm.controls['password'].value
      ),
    };
    this.authService.getToken(reqdata.username, reqdata.password).subscribe(
      (res: any) => {
     //   this.sharedservice.global_loader = false;
        let getToken = res.token;
        let tokenarray = getToken.split('Bearer ');
        let token = tokenarray[tokenarray.length - 1];
        this.localService.saveData('en','encryptedVal')
        sessionStorage.setItem('token', token);
        sessionStorage.setItem('encrypted', reqdata.password);
        this.localService.saveData('userId', this.loginForm.controls['username'].value)
        this.sharedservice.getTokenInfoData('tokenSet');
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
          var userData = 'false';
          this.localService.saveData('userData', JSON.parse(userData))
          this.getUserDetails();

        }

        // if(this.passwordRegex.test(this.loginForm.controls['password'].value)){
        //   var userData = 'false';
        // this.localService.saveData('userData', JSON.parse(userData))
        //   this.getUserDetails();
        //   this.localService.saveData('change','false')
        // }else{
        //   this.localService.saveData('userId', this.loginForm.controls['username'].value)
        //   this.localService.saveData('change','true')
        //   this.sharedservice.openChangePass('')
        // }
        // this.auditLog();
      },
      (err: { error: { status: number } }) => {
        if (err.error.status === 401) {
          this.getCaptcha()
          this.sharedservice.showValidationMessage(
            'Please enter valid User Id or Password'
          );
        }
        this.sharedservice.global_loader = false;
      }
    );
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
  // loginViaAshie() {
  //   if (this.loginviaAshiebool) {
  //     this.loginTypeName = "MoU User";
  //     this.loginviaAshiebool = false;
  //   } else {
  //     this.loginTypeName = "AISHE";
  //     this.loginviaAshiebool = true;
  //   }

  // }
  getUserType(id: number) {
    if (id === 1) {
      this.userLabel = 'AISHE CODE';
    } else {
      this.userLabel = 'User Id';
    }
  }

}
