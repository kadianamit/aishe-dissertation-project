import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { DomSanitizer } from '@angular/platform-browser';
import { MustMatch } from 'src/app/shared/custome-validators';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { ActivatedRoute, Params } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Console } from 'console';
import { catchError, map, of, timeout } from 'rxjs';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.scss'],
})
export class PasswordComponent implements OnInit {
  messageRespon: any = [];
  mobileOtpVarify: string = '';
  password: FormGroup;
  isFormInvalid: boolean = false;
  captchaText: any;
  encodeCaptcha: any;
  display: any;
  show: boolean = false;
  latestId: number = 0;
  emailOtp: boolean = false;
  mobileOtp: boolean = false;
  emailOtpVarify: string = '';
  successVerifyOTP: boolean = false;
  successVerifyMobileOTP: boolean = false;
  hide: boolean = true;
  hideE: boolean = true;
  displayE: any;
  userId: any;
  constructor(
    public fb: FormBuilder,
    public route: ActivatedRoute,
    public authService: AuthService,
    public sharedService: SharedService,
    public sanitizer: DomSanitizer,
    public encrypt: EncryptDecrypt
  ) {
    this.route.params.forEach((params: Params) => {
      this.sharedService.global_loader = true;
      this.userId = params['userId'];
      this.verifyUser(this.userId);
    });
    let passwordRegex: RegExp =
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&<>`~^()-_+=|"'{}\s]{8,}$/;
    this.password = this.fb.group(
      {
        password: [
          '',
          [Validators.required, Validators.pattern(passwordRegex)],
        ],
        confirmPassword: ['', [Validators.required]],
        loginId: [this.userId, []],
        captcha: ['', [Validators.required]],
        email: ['', []],
        mobile: ['', []],
        mobileOtp: ['', []],
        // emailOtp: ['', []],
        mobileX: ['', []],
        emailX: ['', []],
      },
      {
        validator: MustMatch('password', 'confirmPassword'),
      }
    );
  }
  ngOnInit(): void {
    this.getCaptcha();
  }
  verifyUser(userId: any) {
    this.authService.getUserNoToken(userId).subscribe(
      (res: any) => {

        if ( res.status=== 200) {
          let textMobile = this.encrypt.getDecryptedValue(res.data?.mobile);
          let resultMobile = textMobile?.substring(2, 7);
          let m = textMobile?.replace(resultMobile, 'XXXXX')
          let textEMAIL = this.encrypt.getDecryptedValue(res.data.email);
         var posFrom = textEMAIL?.indexOf('@');
         var b = textEMAIL?.substring(2, posFrom);
         var e = textEMAIL?.replace(b, 'XXXXX');
         let element = {
           mobileX: m,
           emailX: e,
           email: textEMAIL,
           mobile: textMobile,
           userId: res.data.userId
         }

          this.password.get('mobile')?.setValue(element.mobile);
          this.password.get('email')?.setValue(element.email);
          this.password.get('mobileX')?.disable();
          this.password.get('mobileX')?.setValue(element.mobileX);
          this.password.get('emailX')?.disable();
          this.password.get('emailX')?.setValue(element.emailX);
        }
      },
      (err) => {}
    );
  }

  forgot() {
    if (this.password.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    let payload = {
      password: this.encrypt.getEncryptedValue(
        this.password.controls['password'].value
      ),
      confirmPassword: this.encrypt.getEncryptedValue(
        this.password.controls['confirmPassword'].value
      ),
      loginId: this.userId,
     // emailOtp: this.encrypt.getEncryptedValue(this.password.controls['emailOtp']?.value),
      email: this.encrypt.getEncryptedValue(this.password.controls['email']?.value),
      mobileOtp: this.encrypt.getEncryptedValue(this.password.controls['mobileOtp']?.value),
      mobile: this.encrypt.getEncryptedValue(this.password.controls['mobile']?.value),
    };

    this.authService.forgotPassword(payload).subscribe(
      (res) => {
        if (res.status === 200) {
          this.successVerifyOTP = true;
          this.sharedService.showSuccessMessage(
            'Your password has been changed !!!'
          );
          setTimeout(() => {
            window.location.href = environment.aisheHome;
          }, 2000);
          // window.location.href = environment.aisheHome;
        }
      },
      (err) => {
        if (err.error.status === 422) {
          this.sharedService.showValidationMessage(
            'Enter Otp For Mobile and Email Both'
          );
        }
      }
    );
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

    if (this.password.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    this.authService
      .verifyGetCaptcha(
        this.password.controls['captcha'].value,
        this.encodeCaptcha
      )
      .subscribe(
        (res) => {
          if (res.message == 'Captcha Valid') {
            // if (this.password.controls['emailOtp'].value) {
            //   this.verifyEmailOTP();
            // }
            if (this.password.controls['mobileOtp'].value) {
              this.verifyMobileOTP();
            }
            if (
              // !this.password.controls['emailOtp'].value &&
              !this.password.controls['mobileOtp'].value
            ) {
              this.sharedService.showValidationMessage(
                `Please Verify  Mobile OTP !!!`
              );
            }
          } else {
            this.sharedService.showValidationMessage('Invalid Captcha !!!');
          }
        },
        (err) => {}
      );
  }

  resendOTP() {
    // this.element
    //let email = '10.7danish@gmail.com'
    this.authService
      .sendForgotPasswordEmail(this.password.controls['email'].value)
      .subscribe(
        (res) => {
          if (res.status === 200) {
            this.timer(2);
            this.sharedService.showSuccessMessage(res.message);
          }
        },
        (err) => {}
      );
  }

  showpassword() {
    this.show = !this.show;
  }
  sendEmailOTP() {
    this.emailOtpVarify = '';
    let email = this.encrypt.getEncryptedValue(
      this.password.controls['email'].value
    );
    this.authService.sendEmail(email, this.latestId).subscribe(
      (res) => {
        const statusCode = this.encrypt.getDecryptedValue(res.status);
        const emailOtpVarify12 = this.encrypt.getDecryptedValue(res.data2);
        const latestId = this.encrypt.getDecryptedValue(res.data1);
        if (statusCode === '200' && emailOtpVarify12 && +latestId) {
          this.latestId = +latestId;
          this.emailOtpVarify = emailOtpVarify12;
          this.emailOtp = true;
          const message = this.encrypt.getDecryptedValue(res.data);
          this.sharedService.showSuccessMessage(message);
          this.timerE(1);
        }
      },
      (err) => {
        const statusCode = this.encrypt.getDecryptedValue(err.error.status);
        const message = this.encrypt.getDecryptedValue(err.error.message);
        this.messageRespon.push({
          messageEr: message,
          statuseEr: statusCode,
        });
        if (statusCode === '400') {
          this.sharedService.showSuccessMessage(message);
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
      this.getCaptcha();
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.authService
      .verifyEOTP(email, emailOTP)
      .pipe(
        // Set a 1-second timeout for the response
        timeout(2000),
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
          return of(null); // Return an empty observable to stop further execution
        })
      )
      .subscribe((decryptedRes) => {
        if (!decryptedRes) return; // Stop execution if an error occurred

        const { statusCode, latestId1, emailOtpVarify12 } = decryptedRes;
        if (
          this.latestId !== +latestId1 &&
          this.emailOtpVarify !== emailOtpVarify12
        ) {
          this.getCaptcha();
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
          this.forgot();
          this.latestId = 0;
        }
      });
  }

  sendMobileOTP() {
    this.mobileOtpVarify = '';
    const data=this.password.controls['mobile']?.value.toString();
    let mobile = this.encrypt.getEncryptedValue(
      data
    );
    this.authService.sendMobile(mobile, this.latestId).subscribe(
      (res) => {
        const statusCode = this.encrypt.getDecryptedValue(res.status);
        const data2 = this.encrypt.getDecryptedValue(res.data2);
        const latestId1 = this.encrypt.getDecryptedValue(res.data1);
        if (statusCode === '200' && +latestId1 && +data2) {
          this.latestId = +latestId1;
          this.mobileOtpVarify = data2;
          this.mobileOtp = true;
          this.sharedService.showSuccessMessage(
            `OTP has been sent successfully to ${this.password.controls['mobile'].value}.Please enter the same OTP.`
          );
          this.timer(1);
        }
      },
      (err) => {
        const statusCode = this.encrypt.getDecryptedValue(err.error.status);
        const latestId1 = this.encrypt.getDecryptedValue(err.error.data);
        if (statusCode === '422' && latestId1 === 'Problem Occurred!') {
          this.sharedService.showValidationMessage('Invalid OTP');
        }
      }
    );
  }

  verifyMobileOTP() {
    const mobile = this.encrypt.getEncryptedValue(
      this.password.controls['mobile']?.value
    );
    const mobileOtp = this.encrypt.getEncryptedValue(
      this.password.controls['mobileOtp']?.value
    );
    if (this.password.controls['mobileOtp'].value !== this.mobileOtpVarify) {
      this.getCaptcha();
      this.sharedService.showValidationMessage('Invalid OTP');
      return;
    }
    this.authService
      .verifyMOTP(mobile, mobileOtp)
      .pipe(
        // Add a 1-second timeout
        timeout(2000),
        map((res) => {
          const decryptedResponse = {
            statusCode: this.encrypt.getDecryptedValue(res.status),
            latestId1: this.encrypt.getDecryptedValue(res.data1),
            data12: this.encrypt.getDecryptedValue(res.data2),
          };
          return decryptedResponse;
        }),
        // Handle timeout error or other errors
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
          return of(null); // Return an empty observable to stop further execution
        })
      )
      .subscribe((decryptedRes) => {
        if (!decryptedRes) return; // If an error occurred, stop execution

        const { statusCode, latestId1, data12 } = decryptedRes;
        if (this.latestId !== +latestId1 && this.mobileOtpVarify !== data12) {
          this.getCaptcha();
          this.sharedService.showValidationMessage('Invalid OTP');
          return;
        }

        if (
          statusCode === '200' &&
          this.latestId === +latestId1 &&
          this.mobileOtpVarify === data12
        ) {
          this.sharedService.verifyMOTP();
          this.forgot();
          this.latestId = 0;
          this.mobileOtpVarify = '';
        }
      });
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
