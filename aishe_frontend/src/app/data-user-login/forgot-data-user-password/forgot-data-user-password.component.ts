import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, Validators } from '@angular/forms';
import { NotificationService } from '../../service/reports/notification.service';
import { AuthService } from '../../service/auth.service';
import { SharedService, routes } from '../../shared/shared.service';
import { DomSanitizer } from '@angular/platform-browser';
import { MailerService } from '../../shared/mailer.service';
@Component({
  selector: 'app-forgot-data-user-password',
  templateUrl: './forgot-data-user-password.component.html',
  styleUrls: ['./forgot-data-user-password.component.scss']
})
export class ForgotDataUserPasswordComponent implements OnInit {
  public routers: typeof routes = routes;
  forgotForm: FormGroup | any;
  registrationForm: any;
  captchaInputText: string = "";
  captchaText: any;
  isFormInvalid: boolean = false;
  encodeCaptcha: any;
  userId: any;

  constructor(private fb: FormBuilder, private authService: AuthService, private notify: NotificationService,
    public sharedService: SharedService, public sanitizer: DomSanitizer, public mailerService: MailerService) { }

  ngOnInit(): void {
    this.getCaptcha();
    this.forgotForm = this.fb.group({
      emailId: ['', [Validators.required]],
      captcha: ['', Validators.required],
    });


  }

  getCaptcha() {
    this.authService.getCaptchaText().subscribe((resp: any) => {
      this.captchaText = this.sanitizer.bypassSecurityTrustUrl(resp.capcha);
      this.encodeCaptcha = resp.data;
    }, err => {
    });
  }
  verifyCaptcha(data: any) {
    this.authService.verifyGetCaptcha(this.forgotForm.controls['captcha'].value, this.encodeCaptcha).subscribe(res => {
      if (res.message == 'Captcha Valid') {
        this.getUserDetailsDataUser()
      }
      else {
        this.sharedService.showValidationMessage('Invalid Captcha !!!')
      }
    }, err => {
    })
  }

  get f() {
    return this.forgotForm.controls;
  }

  onSubmit() {
    this.authService.forgotDataUserPassword(this.forgotForm.value.emailId).subscribe((res) => {
      if (res.statusCode == 'AI005') {
        this.notify.showError(res.description);
      }
      if (res.statusCode == 'AI004') {
        this.notify.showSuccess(res.description);
      }
      if (res.statusCode == 'AI006') {
        this.notify.showError(res.description);
      }

    });
  }


  getUserDetailsDataUser() {
    this.getCaptcha();
    this.authService.getDataUserById(this.forgotForm.value.emailId).subscribe((res: any) => {
      if (res.status.statusCode === "AI001") {
        this.userId = res.user.userId
        this.sendEmail();
      }if(res.status.statusCode === 'ER002'){
        this.sharedService.showValidationMessage(res.status.description)
      }
      (err: any) => {

        this.sharedService.apiNotRespond();
      }
    })

  }
  sendEmail() {
    let user = {
      emailId: this.forgotForm.value.emailId,
      userId:this.userId
    }
    
    this.mailerService.sendEmail('forgotPassword', user, true).subscribe(res => {
      if (res.status === 200) {
        let ele = {
          forgotPass: true,
         
        }
        this.sharedService.savePopUp(ele)
      }
    }, err => {
    })
  }
}
