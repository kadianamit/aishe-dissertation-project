import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { SharedService } from 'src/app/shared/shared.service';
import { LocalserviceService } from '../service/localservice.service';
import { catchError, map, of, pipe, timeout } from 'rxjs';

@Component({
  selector: 'app-forgot-user',
  templateUrl: './forgot-user.component.html',
  styleUrls: ['./forgot-user.component.scss'],
})
export class ForgotUserComponent implements OnInit {
  captchaText: any;
  encodeCaptcha: any;
  forgotUser: FormGroup;
  isFormInvalid: boolean = false;
  showPage: boolean = false;
  mobileX: any;
  emailX: any;
  email: any;
  mobile: any;
  userId: any;
  mobileOtpVarify: string = '';
  password: FormGroup;
  isFormPInvalid: boolean = false;
  captchaText1: any;
  encodeCaptcha1: any;
  display: any;
  show: boolean = false;
  latestId: number = 0;
  emailOtp: boolean = false;
  emailOtpVarify: string = '';
  mobileOtp: boolean = false;
  successVerifyOTP: boolean = false;
  successVerifyMobileOTP: boolean = false;
  hide: boolean = true;
  hideE: boolean = true;
  displayE: any;
  showUser: boolean = false;
  messageEligible: boolean | undefined;
  instituteName: any;
  constructor(
    public fb: FormBuilder,
    public sharedService: SharedService,
    public sanitizer: DomSanitizer,
    public encrypt: EncryptDecrypt,
    public authService: AuthService,
    public router: Router,
    public localService: LocalserviceService
  ) {
    this.forgotUser = this.fb.group({
      captcha: ['', [Validators.required]],
      aisheCode: ['', [Validators.required]],
    });
    this.password = this.fb.group({
      captcha: ['', [Validators.required]],
      email: ['', []],
      mobile: ['', []],
      mobileOtp: ['', []],
      emailOtp: ['', []],
      mobileX: ['', []],
      emailX: ['', []],
    });
  }

  ngOnInit(): void {
    this.getCaptcha();
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
    this.authService
      .verifyGetCaptcha(
        this.forgotUser.controls['captcha'].value,
        this.encodeCaptcha
      )
      .subscribe(
        (res) => {
          if (res.message == 'Captcha Valid') {
            this.verifyUser();
          } else {
            this.sharedService.showValidationMessage('Invalid Captcha !!!');
          }
        },
        (err) => {}
      );
  }
  verifyUser() {
    this.getCaptcha()
    if (this.forgotUser.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
     // this.sharedService.global_loader = false;
      return;
    } else {
      this.isFormInvalid = false;
    }
    this.authService
      .checkAisheCode(this.forgotUser.controls['aisheCode'].value.toUpperCase())
      .subscribe(
        (res) => {
          if (res && res.length) {
            if (res['0'].userId) {
              this.showPage = true;
              this.instituteName = res['0'].instituteName;
              let textMobile = res['0'].mobile.toString();
              let resultMobile = textMobile.substring(2, 7).replace('XXXXX');
              this.mobileX = textMobile.replace(resultMobile, 'XXXXX');
              let text = res['0'].email;
              var posFrom = text.indexOf('@');
              var b = text.substring(2, posFrom);
              this.emailX = text.replace(b, 'XXXXX');
              (this.email = res['0'].email),
                (this.mobile = res['0'].mobile),
                (this.userId = res['0'].userId),
                this.password.get('mobile')?.setValue(this.mobile);
              this.password.get('email')?.setValue(this.email);
              this.password.get('mobileX')?.disable();
              this.password.get('mobileX')?.setValue(this.mobileX);
              this.password.get('emailX')?.disable();
              this.password.get('emailX')?.setValue(this.emailX);
              this.getCaptcha1();
            } else {
              this.sharedService.showValidationMessage(res['0'].message);
            }
          }
        },
        (err) => {}
      );
  }
  getCaptcha1() {
    this.authService.getCaptchaText().subscribe(
      (resp: any) => {
        this.captchaText = this.sanitizer.bypassSecurityTrustUrl(resp.capcha);
        this.encodeCaptcha = resp.data;
      },
      (err) => {}
    );
  }
  verifyCaptcha1() {
    this.getCaptcha1();
    this.authService
      .verifyGetCaptcha(
        this.password.controls['captcha'].value,
        this.encodeCaptcha
      )
      .subscribe(
        (res) => {
          if (res.message == 'Captcha Valid') {
            if (this.password.controls['emailOtp'].value) {
              this.verifyEmailOTP();
            }
            if (this.password.controls['mobileOtp'].value) {
              this.verifyMobileOTP();
            }
          } else {
            this.sharedService.showValidationMessage('Invalid Captcha !!!');
          }
        },
        (err) => {}
      );
  }
  sendEmailOTP() {
    const email = this.encrypt.getEncryptedValue(
      this.password.controls['email'].value
    );
    this.authService.sendEmail(email, this.latestId).subscribe(
      (res) => {
        let statusCode = this.encrypt.getDecryptedValue(res.status);
        let Message = this.encrypt.getDecryptedValue(res.data);
        const latestId1 = this.encrypt.getDecryptedValue(res.data1);
        const EmailOTP = this.encrypt.getDecryptedValue(res.data2);
        if (statusCode === '200' && +latestId1 && EmailOTP) {
          this.latestId = +latestId1;
          this.emailOtpVarify = EmailOTP;
          this.emailOtp = true;

          this.sharedService.showSuccessMessage(Message);
          this.timerE(1);
        }
      },
      (err) => {
        const statusCode = this.encrypt.getDecryptedValue(err.error.status);
        let Message = this.encrypt.getDecryptedValue(err.error.data);
        this.sharedService.showSuccessMessage(Message);
        if (statusCode === '') {
          this.sharedService.showSuccessMessage(Message);
        }
      }
    );
  }


  verifyEmailOTP() {
    const email = this.encrypt.getEncryptedValue(
      this.password.controls['email'].value
    );
    const emailOTP = this.encrypt.getEncryptedValue(
      this.password.controls['emailOtp'].value
    );
    if (this.password.controls['emailOtp'].value !== this.emailOtpVarify) {
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.authService
      .verifyEOTP(email, emailOTP)
      .pipe(
        // Set a 1-second timeout for the response
        timeout(1000),
        map((res) => {
          const decryptedResponse = {
            statusCode: this.encrypt.getDecryptedValue(res.status),
            latestId1: this.encrypt.getDecryptedValue(res.data1),
            emailOtpVarify12: this.encrypt.getDecryptedValue(res.data2),
          };
          return decryptedResponse;
        }),
        // Catch errors, including timeout error
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
         this.getCaptcha();
          return of(null); // Return an empty observable to stop further execution
        })
      )
      .subscribe(
        (decryptedRes) => {
          if (!decryptedRes) return; // Stop execution if an error occurred

          const { statusCode, latestId1, emailOtpVarify12 } = decryptedRes;
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
            this.sharedService.verifyEOTP();
            this.successVerifyOTP = true;
            this.hideE = false;
            this.emailOtp = false;
            this.checkAisheCode();
            this.showUser = true;
            this.latestId = 0;
          }
        },
        (err) => {
          let statusCode = this.encrypt.getDecryptedValue(err.error.status);
          if (statusCode === '422') {
            this.sharedService.showValidationMessage('Invalid OTP');
          }
        }
      );
  }
  sendMobileOTP() {
    const mobile = this.encrypt.getEncryptedValue(
      this.password.controls['mobile'].value
    );
    this.authService.sendMobile(mobile, this.latestId).subscribe(
      (res) => {
        let statusCode = this.encrypt.getDecryptedValue(res.status);
        let Message = this.encrypt.getDecryptedValue(res.data);
        const latestId12 = this.encrypt.getDecryptedValue(res.data1);
        const MobileOTP = this.encrypt.getDecryptedValue(res.data2);
        if (statusCode === '200' && latestId12 && MobileOTP) {
          this.latestId = +latestId12;
          this.mobileOtpVarify = MobileOTP;
          this.mobileOtp = true;
          this.sharedService.showSuccessMessage(Message);
          this.timer(1);
        }
      },
      (err) => {}
    );
  }

  verifyMobileOTP() {
    const mobile = this.encrypt.getEncryptedValue(
      this.password.controls['mobile'].value
    );
    const mobileOtp = this.encrypt.getEncryptedValue(
      this.password.controls['mobileOtp'].value
    );
    if (this.password.controls['mobileOtp'].value !== this.mobileOtpVarify) {
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.authService
      .verifyMOTP(mobile, mobileOtp)
      .pipe(
        timeout(1000),
        map((res) => {
          const decryptedResponse = {
            statusCode: this.encrypt.getDecryptedValue(res.status),
            latestId1: this.encrypt.getDecryptedValue(res.data1),
            data12: this.encrypt.getDecryptedValue(res.data2),
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
          const latestId1 = this.encrypt.getDecryptedValue(err.error?.data);
          if (statusCode === '422' && latestId1 === 'Problem Occurred!') {
            this.sharedService.showValidationMessage('Invalid OTP');
          }
          this.getCaptcha();
          return of(null); // Return an empty observable to stop further execution
        })
      )
      .subscribe(
        (decryptedRes) => {
          if (!decryptedRes) return; // If an error occurred, stop execution

          const { statusCode, latestId1, data12 } = decryptedRes;
          if (this.latestId !== +latestId1 && this.mobileOtpVarify !== data12) {
            this.sharedService.showValidationMessage('Invalid OTP');
            return;
          }

          if (
            statusCode === '200' &&
            this.latestId === +latestId1 &&
            this.mobileOtpVarify === data12
          ) {
            this.sharedService.verifyMOTP();
            this.latestId = 0;
            this.mobileOtpVarify = '';

            this.successVerifyMobileOTP = true;
            this.hide = false;
            this.mobileOtp = false;
            this.checkAisheCode();
            this.showUser = true;
          }
        },
        (err) => {
          this.getCaptcha();
          let statusCode = this.encrypt.getDecryptedValue(err.error.status);
          if (statusCode === '400') {
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
  checkAisheCode() {
    this.authService
      .findEligible(
        this.forgotUser.controls['aisheCode'].value.toUpperCase(),
        '2022'
      )
      .subscribe(
        (res) => {
          if (res) {
            if (res['Institute Details For CSY'].elligible !== 'Yes') {
              this.messageEligible = true;
            } else {
              this.messageEligible = false;
            }
            let lsy = res['Institute Details For CSY'].lsy;
            this.localService.saveData('surveyYear', lsy);
          } else {
            this.sharedService.showValidationMessage(
              'There is no approved user for this institute !!!!'
            );
          }
        },
        (err) => {}
      );
  }
}
