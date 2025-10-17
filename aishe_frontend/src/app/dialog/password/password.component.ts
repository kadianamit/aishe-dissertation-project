import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { DomSanitizer } from '@angular/platform-browser';
import { MustMatch } from 'src/app/shared/custome-validators';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.scss']
})
export class PasswordComponent implements OnInit {
  password: FormGroup;
  cancelButtonText: string = 'Close';
  confirmButtonText = "Submit";
  isFormInvalid: boolean = false;
  captchaText: any;
  encodeCaptcha: any;
  display: any;
  show: boolean = false;
  latestId: number = 0;
  emailOtp: boolean = false;
  mobileOtp: boolean = false;
  successVerifyOTP: boolean = false;
  successVerifyMobileOTP: boolean = false;
  hide: boolean = true;
  hideE: boolean = true;
  displayE: any;
  constructor(public dialogRef: MatDialogRef<PasswordComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any, public fb: FormBuilder,
    public authService: AuthService, public sharedService: SharedService,
    public sanitizer: DomSanitizer, public encrypt: EncryptDecrypt) {
    let passwordRegex: RegExp = /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,16}/;
    this.password = this.fb.group({
      password: ['', [Validators.required, Validators.pattern(passwordRegex)]],
      confirmPassword: ['', [Validators.required]],
      loginId: [element.userId, []],
      captcha: ['', [Validators.required]],
      email: { value: element.email, disabled: true },
      mobile: { value: element.mobile, disabled: true },
      mobileOtp: ['', [Validators.required]],
      emailOtp: ['', [Validators.required]],
      mobileX: {value:element.mobileX,disabled:true},
      emailX: {value:element.emailX,disabled:true}
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });
  }

  ngOnInit(): void {
   // setTimeout(() => {
      this.getCaptcha();
      //  this.sendEmailOTP();
      // this.sendMobileOTP()
   // }, 1000);
  }
  forgot(data: any) {
  this.getCaptcha()
    let payload = {
      password: this.encrypt.getEncryptedValue(data.password),
      confirmPassword: this.encrypt.getEncryptedValue(data.confirmPassword),
      loginId: data.loginId,
      emailOtp: data.emailOtp,
      mobileOtp: data.mobileOtp
    }
    this.authService.forgotPassword(payload).subscribe(res => {
      if (res.status === 200) {
        this.sharedService.showSuccessMessage('Your password has been changed !!!');
        this.dialogRef.close(true)
      }
    }, err => {
      if (err.error.status === 422) {
        this.getCaptcha()
        this.sharedService.showValidationMessage('Enter Otp For Mobile and Email Both')
      }
    })
  }
  getCaptcha() {
    this.authService.getCaptchaText().subscribe((resp: any) => {
      this.captchaText = this.sanitizer.bypassSecurityTrustUrl(resp.capcha);
      this.encodeCaptcha = resp.data;
    }, err => {
    });
  }
  verifyCaptcha(data: any) {
    this.authService.verifyGetCaptcha(this.password.controls['captcha'].value, this.encodeCaptcha).subscribe(res => {
      if (res.message == 'Captcha Valid') {
        if (this.successVerifyMobileOTP || this.successVerifyOTP) {
          this.forgot(data);
        }else{
          if(this.password.invalid){
            this.sharedService.showValidationMessage('Please enter required field')
          }
        }
      }
      else {
        this.sharedService.showValidationMessage('Invalid Captcha !!!')
      }
    }, err => {
    })
  }

  resendOTP() {
    // this.element
    //let email = '10.7danish@gmail.com'
    this.authService.sendForgotPasswordEmail(this.element.email).subscribe(res => {
      if (res.status === 200) {
        this.timer(2)
        this.sharedService.showSuccessMessage(res.message);
      }
    }, err => {

    })
  }

  showpassword() {
    this.show = !this.show;
  }
  sendEmailOTP() {
    this.authService
      .sendEmail(
        this.password.controls['email'].value, this.latestId).subscribe((res) => {
          if (res.status === 200) {
            this.latestId = res.data;
            this.emailOtp = true;

            this.sharedService.showSuccessMessage(`OTP has been sent successfully to ${this.password.controls['emailX'].value}.Please enter the same OTP.`);
            this.timerE(1);
          }
        },
          (err) => { }
        );
  }

  verifyEmailOTP() {
    this.authService
      .verifyEOTP(
        this.password.controls['email'].value,
        this.password.controls['emailOtp'].value
      )
      .subscribe(
        (res) => {
          if (res.status === 200) {
            this.sharedService.verifyEOTP();
            this.successVerifyOTP = true;
            this.hideE = false;
            this.emailOtp = false;
          }
        },
        (err) => { }
      );
  }
  sendMobileOTP() {
    this.authService
      .sendMobile(
        this.password.controls['mobile'].value,
        this.latestId
      )
      .subscribe(
        (res) => {
          if (res.status === 200) {
            this.latestId = res.data;

            this.mobileOtp = true;
            this.sharedService.showSuccessMessage(`OTP has been sent successfully to ${this.password.controls['mobileX'].value}.Please enter the same OTP.`);
            this.timer(1);
          }
        },
        (err) => { }
      );
  }
  verifyMobileOTP() {
    this.authService
      .verifyMOTP(
        this.password.controls['mobile'].value,
        this.password.controls['mobileOtp'].value
      )
      .subscribe(
        (res) => {
          if (res.status === 200) {
            this.sharedService.verifyMOTP();
            this.successVerifyMobileOTP = true;
            this.hide = false;
            this.mobileOtp = false;
          }
        },
        (err) => { }
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
}
