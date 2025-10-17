import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { NotificationService } from 'src/app/service/reports/notification.service';
import { MustMatch } from 'src/app/shared/custome-validators';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss'],
})
export class ChangePasswordComponent implements OnInit {
  comparereMessage: string = '';
  cancelButtonText: string = 'Close';
  confirmButtonText = 'Change Password';
  userId: string | null;
  password: FormGroup;
  captchaText: any;
  encodeCaptcha: any;
  show: boolean | undefined;
  show1: boolean | undefined;
  isFormInvalid: boolean = false;
  userData: any;
  constructor(
    public dialogRef: MatDialogRef<ChangePasswordComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any,
    public fb: FormBuilder,
    public authService: AuthService,
    public sharedService: SharedService,
    public sanitizer: DomSanitizer,
    public encrypt: EncryptDecrypt,
    public router: Router,
    public notify: NotificationService,
    public localService: LocalserviceService
  ) {
    this.userId = this.localService.getData('userId');
    let passwordRegex: RegExp =
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&<>`~^()-_+=|"'{}\s]{8,}$/;
    this.password = this.fb.group(
      {
        oldPassword: ['', [Validators.required]],
        newPassword: [
          '',
          [Validators.required, Validators.pattern(passwordRegex)],
        ],
        confirmPassword: ['', [Validators.required]],
        userId: [this.userId, []],
        captcha: ['', [Validators.required]],
      },
      {
        validator: MustMatch('newPassword', 'confirmPassword'),
      }
    );
    this.userData = this.localService.getData('userData');
  }
  ngOnInit(): void {
    this.getCaptcha();
  }
  change(data: any) {
    if (this.userData !== 'true') {
      // let payload = {
      //   oldPassword: this.encrypt.getEncryptedValue(data.oldPassword),
      //   newPassword: this.encrypt.getEncryptedValue(data.newPassword),
      //   confirmPassword: this.encrypt.getEncryptedValue(data.confirmPassword),
      //   // userId: data.userId,
      // };
      let payload = {
        oldPassword: this.encrypt.getEncryptedValue(data.oldPassword),
        newPassword: this.encrypt.getEncryptedValue(data.newPassword),
        userId: data.userId
      };

      this.authService.changePassword(payload).subscribe(
        (res) => {
          const status = this.encrypt.getDecryptedValue(res.status);
          const message = this.encrypt.getDecryptedValue(res.message);
          this.comparereMessage = message;
          if (this.comparereMessage) {
          }
          if (status === '200') {
            this.dialogRef.close(true);
            let ele = {
              changePass: true,
            };
            this.sharedService.reLogin(ele);
          }else{
            this.getCaptcha();
          }
        },
        (err) => {
          this.getCaptcha();
          const status = this.encrypt.getDecryptedValue(err.error.status);
          const message = this.encrypt.getDecryptedValue(err.error.message);
          if (status === '400') {
            this.sharedService.showValidationMessage(
              'The old password you have entered is incorrect'
            );
          }
        }
      );
    } else {
      let payload = {
        confirmPassword: this.encrypt.getEncryptedValue(data.confirmPassword),
        userId: this.userId,
        userPassword: this.encrypt.getEncryptedValue(data.oldPassword),
      };
      this.authService.changePasswordDataUser(payload).subscribe(
        (res) => {
          const statusCode = this.encrypt.getDecryptedValue(res.statusCode);
          if (statusCode == 'AI004') {
            const message = this.encrypt.getDecryptedValue(res.description);
            this.notify.showError(message);
            this.getCaptcha();
          }
          if (statusCode == 'AI001') {
            this.dialogRef.close(true);
            let ele = {
              changePass: true,
            };
            this.sharedService.reLogin(ele);
          }
        },
        (err) => {}
      );
    }
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
  verifyCaptcha(data: any) {
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
            this.change(data);
          } else {
            this.sharedService.showValidationMessage('Invalid Captcha !!!');
          }
        },
        (err) => {}
      );
  }
  showpassword() {
    this.show = !this.show;
  }
  showpassword1() {
    this.show1 = !this.show1;
  }
}
