import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-special-permission',
  templateUrl: './special-permission.component.html',
  styleUrls: ['./special-permission.component.scss']
})
export class SpecialPermissionComponent implements OnInit {
  specialPermission: FormGroup;
  isFormInvalid: boolean = false;
  messageEligible: boolean = false
  constructor(public fb: FormBuilder, public sharedService: SharedService, public authService: AuthService,public localService:LocalserviceService) {
    this.specialPermission = this.fb.group({
      instituteType: ['', [Validators.required]],
      aisheCode: ['', [Validators.required]],
      surveyYear: { value: '2021', disabled: true },
      specialPermission: false,
      remarks:['',[Validators.required]]
    })
  }

  ngOnInit(): void {
  }
  checkAisheCode(data: any) {
    let aisheCode = data.instituteType + '-' + data.aisheCode
    if (this.specialPermission.invalid) {
      this.sharedService.showValidationMessage('Please Enter AISHE Code');
      this.isFormInvalid = true;
      return
    } else {
      this.isFormInvalid = false;
    }
    this.authService.findEligible(aisheCode, '2021').subscribe(res => {
      if (res) {
        if (res['Institute Details For CSY'].elligible !== 'Yes') {
          this.messageEligible = true;
          this.sharedService.showValidationMessage('You are not Eligible for the survey year 2021-22');
          return;
        } else {
          this.permission(aisheCode)
        }
      } else {
        this.sharedService.showValidationMessage('There is no approved user for this institute !!!!')
      }

    }, err => {

    })
  }
  permission(aisheCode: any) {
    let payload = {
      surveyYear: this.specialPermission.controls['surveyYear'].value,
      instituteType: this.specialPermission.controls['instituteType'].value,
      aisheCode: aisheCode,
      specialPermission: this.specialPermission.controls['specialPermission'].value,
      userId:this.localService.getData('userId'),
      remarks:this.specialPermission.controls['remarks'].value
    }
    this.authService.assignSpecialPermission(payload).subscribe(res => {
      if (res.status === 200) {
        this.sharedService.showSuccess();
        this.specialPermission.reset();
      }
    }, err => {

    })
  }
  restrictNumeric(event: any) {
    return (event.charCode == 8 || event.charCode == 0) ? null : event.charCode >= 48 && event.charCode <= 57;
  }

}
