import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Params } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { NotificationService } from 'src/app/service/reports/notification.service';
import { MustMatch } from 'src/app/shared/custome-validators';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-reset-data-user',
  templateUrl: './reset-data-user.component.html',
  styleUrls: ['./reset-data-user.component.scss']
})
export class ResetDataUserComponent implements OnInit {
  password: FormGroup;
  captchaText: any;
  encodeCaptcha: any;
  isFormInvalid: boolean = false
  id: any;
  show: boolean | undefined;
  constructor(public fb: FormBuilder, public route: ActivatedRoute,
    public authService: AuthService, public sharedService: SharedService,
    public sanitizer: DomSanitizer, public encrypt: EncryptDecrypt,public notify:NotificationService) {

    let passwordRegex: RegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&<>`~^()-_+=|"'{}\s]{8,}$/;;
    this.password = this.fb.group({
      password: ['', [Validators.required, Validators.pattern(passwordRegex)]],
      confirmPassword: ['', [Validators.required]],
      captcha: ['', [Validators.required]],

    }, {
      validator: MustMatch('password', 'confirmPassword')
    });
    this.route.params.forEach((params: Params) => {
      this.sharedService.global_loader = true;
      this.id = +params['id'];
    });
  }
  ngOnInit(): void {
    this.getCaptcha();
  }
  getCaptcha() {
    this.authService.getCaptchaText().subscribe((resp: any) => {
      this.captchaText = this.sanitizer.bypassSecurityTrustUrl(resp.capcha);
      this.encodeCaptcha = resp.data;
    }, err => {
    });
  }
  verifyCaptcha() {
    if (this.password.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true
      return;
    } else {
      this.isFormInvalid = false
    }
    this.authService.verifyGetCaptcha(this.password.controls['captcha'].value, this.encodeCaptcha).subscribe(res => {
      if (res.message == 'Captcha Valid') {
        this.resetPassword();
      }
      else {
        this.getCaptcha();
        this.sharedService.showValidationMessage('Invalid Captcha !!!')
      }
    }, err => {
    })
  }
  resetPassword() {
      let payload = {
        "confirmPassword": this.encrypt.getEncryptedValue(this.password.controls['confirmPassword'].value),
        "userId": this.id,
      }
    this.authService.resertdataUserPass(payload).subscribe(res => {
    const respond = this.encrypt.getDecryptedValue(res.statusCode);
      if (!respond) {
        this.notify.showError("invalid response");
        return;
      }
      if (respond == 'AI004') {
        const description = this.encrypt.getDecryptedValue(res.description);
        this.notify.showError(description);
        
      }if(respond == 'AI001'){
        let ele = {
          resetPass: true
        }
        this.sharedService.reLogin(ele)
      }
      
      
    }, err => {

    })
  }
  showpassword() {
    this.show = !this.show;
  }
}
