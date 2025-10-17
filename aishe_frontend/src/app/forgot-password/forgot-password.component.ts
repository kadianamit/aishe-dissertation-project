import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Params, Router } from '@angular/router';
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
  id: any
  constructor(public fb: FormBuilder, public cdRef: ChangeDetectorRef,
    public sharedService: SharedService, public sanitizer: DomSanitizer, public encrypt: EncryptDecrypt,
    public authService: AuthService, public router: Router,public route: ActivatedRoute) {
    let userIdRegex: RegExp = /^[a-zA-Z0-9.]{8,16}$/;
    this.forgotPass = this.fb.group({
      // userType: ['', [Validators.required]],
      userId: ['', [Validators.required]],
      captcha: ['', [Validators.required]],
      aisheCode: ['', [Validators.required]],
      userTypeId: ['', [Validators.required]]
    });
    // this.route.params.forEach((params: Params) => {
    //   this.sharedService.global_loader = true;
    //   this.id = +params['id'];
    //   if (this.id === 0) {
    //     this.forgotPass.get('aisheCode')?.setValidators([Validators.required])
    //     this.forgotPass.get('aisheCode')?.updateValueAndValidity();
    //   } else {
    //     this.forgotPass.get('aisheCode')?.clearValidators()
    //     this.forgotPass.get('aisheCode')?.updateValueAndValidity();
    //   }

    // });
  }

  ngOnInit(): void {
    this.getCaptcha();
  }
  getUserType(value: any) {
    if (value === '1' || value === '2') { 
      this.forgotPass.get('aisheCode')?.clearValidators()
      this.forgotPass.get('aisheCode')?.updateValueAndValidity();
    }else{
      this.forgotPass.get('aisheCode')?.setValidators([Validators.required])
        this.forgotPass.get('aisheCode')?.updateValueAndValidity();
    }
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
        this.verifyUser()
      }
      else {
        this.sharedService.showValidationMessage('Invalid Captcha !!!')
      }
    }, err => {
    })
  }
  verifyUser() {
    
   
    if (this.forgotPass.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
     // this.sharedService.global_loader = false;
      return;
    }
    else {
      this.isFormInvalid = false;
    }

    this.authService.getUserNoToken(this.forgotPass.controls['userId'].value)?.subscribe((res: any) => {
      if (res.status === 200) {
        if (this.forgotPass.controls['userTypeId'].value === '3') {
          this.getCaptcha();
          if (this.forgotPass.controls['aisheCode'].value.toUpperCase() !== res.data.aisheCode) {
            this.sharedService.showValidationMessage('Please enter valid User Id or aishe code!!!');
            return;
          } else {
            
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
            this.router.navigate(['/password', res.data.userId]);
            // this.sharedService.openPassword(element)
          }
        } else {
          if (
            res.data.roleId === 7 || 
            res.data.roleId === 12 ||
            res.data.roleId === 13 ||
            res.data.roleId === 14 ||
            res.data.roleId === 15 ||
            res.data.roleId === 20 ||
            res.data.roleId === 21 ||
            res.data.roleId === 23 ||
            res.data.roleId === 24) {
            this.sharedService.showValidationMessage(
              'You are not autorized !!!'
            );
            return;
          }
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
          this.router.navigate(['/password', res.data.userId]);
          // this.sharedService.openPassword(element)
        }

        //  this.send(element);
        // this.send('10.7danish@gmail.com')
    
      }
    }, err => {
      this.getCaptcha();
      if (err.status === 404) {
        this.sharedService.showValidationMessage('Please enter valid User Id or AISHE Code')
      }
    })
  }
  send(element: any) {
    this.authService.sendForgotPasswordEmail(element).subscribe(res => {
      if (res.status === 200) {
        this.sharedService.showSuccessMessage(res.message);
        this.sharedService.openPassword(element)
      }
    }, err => {

    })
  }

}
