import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
  forgotPass: FormGroup;
  userLabel: string = 'UserId';
  cancelButtonText: string = 'Close'
  confirmButtonText: string = "Verify User";
  isFormInvalid: boolean = false;
  userTypeName: any;
  userType: any[] = [];
  captchaText: any;
  encodeCaptcha: any;
  constructor(public dialogRef: MatDialogRef<ForgotPasswordComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any, public fb: FormBuilder, public cdRef: ChangeDetectorRef,
    public sharedService: SharedService, public sanitizer: DomSanitizer, public encrypt: EncryptDecrypt,
    public authService: AuthService) {
    let userIdRegex: RegExp = /^[a-zA-Z0-9.]{8,16}$/;
    this.forgotPass = this.fb.group({
      // userType: ['', [Validators.required]],
      userId: ['', [Validators.required]],
      captcha: ['', [Validators.required]],
      aisheCode: ['', [Validators.required]]
    });

  }

  ngOnInit(): void {
    setTimeout(() => {
      this.getCaptcha();
    }, 1000);
  }

  sendOTP() {
    if (this.forgotPass.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
      this.sharedService.global_loader = false;
      return;
    }
    else {
      this.isFormInvalid = false;
    }

    this.authService.getUserNoToken(this.forgotPass.controls['userId'].value).subscribe((res: any) => {
      if (res.status === 200) {
        if (this.forgotPass.controls['aisheCode'].value !== res.data.aisheCode) {
          this.sharedService.showValidationMessage('Please enter valid aishe code !!!');
          return;
        } else {
          let textMobile = res.data.mobile.toString();
          let resultMobile = textMobile.substring(2, 7).replace('XXXXX');
          let m = textMobile.replace(resultMobile, 'XXXXX')
          let text = res.data.email;
          var posFrom = text.indexOf('@');
          var b = text.substring(2, posFrom);
          var e = text.replace(b, 'XXXXX');
          let element = {
            mobileX: m,
            emailX: e,
            email: res.data.email,
            mobile: res.data.mobile,
            userId: res.data.userId
          }
          this.dialogRef.close(true);
          this.sharedService.openPassword(element)
        }

        //  this.send(element);
        // this.send('10.7danish@gmail.com')

      }
    }, err => {

    })
  }
  send(element: any) {
    this.authService.sendForgotPasswordEmail(element).subscribe(res => {
      if (res.status === 200) {
        this.dialogRef.close(true);
        this.sharedService.showSuccessMessage(res.message);

        this.sharedService.openPassword(element)
      }
    }, err => {

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
    this.authService.verifyGetCaptcha(this.forgotPass.controls['captcha'].value, this.encodeCaptcha).subscribe(res => {
      if (res.message == 'Captcha Valid') {
        this.sendOTP()
      }
      else {
        this.sharedService.showValidationMessage('Invalid Captcha !!!')
      }
    }, err => {
    })
  }
}
