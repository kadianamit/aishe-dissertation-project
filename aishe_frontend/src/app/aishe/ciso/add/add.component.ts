import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';
@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent implements OnInit {
  cisoForm: FormGroup
  inValidAishe: boolean = false
  isFormInvalid: boolean = false;
  listData: Array<any> = []
  constructor(public authService: AuthService, public fb: FormBuilder, public sharedService: SharedService,
    public errorMatcher: CustomErrorStateMatcher) {
    let emailRegex: RegExp = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    let mobileNoRegex: RegExp = /^[0]?[123456789]\d{9}$/
    this.cisoForm = this.fb.group({
      name: ['', [Validators.required]],
      priority: ['', []],
      agencyCode: ['', [Validators.required]],
      agencyName: ['', [Validators.required]],
      mobile: ['', [Validators.required, Validators.pattern(mobileNoRegex)]],
      email: ['', [Validators.required, Validators.pattern(emailRegex)]],
      alternateEmail: ['', [Validators.pattern(emailRegex)]],
      post: ['', [Validators.required]],
      alternateMobile: ['', [Validators.pattern(mobileNoRegex)]],
      designation: ['', [Validators.required]],
      address: ['', [Validators.required]],
      remark: ['', []]

    })
  }

  ngOnInit(): void {
    this.getCiso();
  }
  getCiso() {
    this.authService.getCisoList().subscribe(res => {
      this.listData = res.data
    }, err => {

    })
  }
  changeFun(value: any) {
    if (value !== '3') {
      this.cisoForm.get('agencyName')?.disable();
      this.cisoForm.get('agencyName')?.updateValueAndValidity()
    } else {
      this.cisoForm.get('agencyName')?.enable();
      this.cisoForm.get('agencyName')?.updateValueAndValidity()
    }
  }
  getDetails() {
    // let x = this.cisoForm.controls['agencyCode'].value.toUpperCase();
    // let aisheId = x[1];
    // let loginMode = x[0];
    // if (loginMode !== 'U' && loginMode !== 'S' && loginMode !== 'C') {
    //   this.inValidAishe = true
    //   return;
    // } else {
    //   this.inValidAishe = false
    // }
    // this.authService.getInstituteName(this.cisoForm.controls['agencyCode'].value.toUpperCase()).subscribe(res => {
    //   this.cisoForm.get('agencyName')?.setValue(res.data.instituteName);


    // }, err => {
    //   if (err.status === 404) {
    //     this.inValidAishe = true
    //     return;
    //   }
    // })
  }
  save() {
    if (this.cisoForm.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true
      return;
    } else {
      this.isFormInvalid = false
    }
    const duplicate = this.listData.filter((ele: any) => ele.agencyCode.toUpperCase() === this.cisoForm.controls['agencyCode'].value.toUpperCase() && ele.post === this.cisoForm.controls['post'].value)
    if (duplicate.length !== 0) {
      this.sharedService.showValidationMessage('Duplicate record not allowed !!!');
      return;
    }
    let payload = {

      "agencyCode": this.cisoForm.controls['agencyCode'].value,
      "agencyName": this.cisoForm.controls['agencyName'].value,
      "designation": this.cisoForm.controls['designation'].value,
      "emailId": this.cisoForm.controls['email'].value,
      "emailIdAlternate": this.cisoForm.controls['alternateEmail'].value,
      "mobile": this.cisoForm.controls['mobile'].value,
      "mobileAlternate": this.cisoForm.controls['alternateMobile'].value,
      "name": this.cisoForm.controls['name'].value,
      "officeAddress": this.cisoForm.controls['address'].value,
      "post": this.cisoForm.controls['post'].value,
      "priority": this.cisoForm.controls['priority'].value,
      "remarks": this.cisoForm.controls['remark'].value

    }
    this.authService.saveCisoForm(payload).subscribe(res => {
      if (res.status === 200) {
        this.cisoForm.get('agencyName')?.enable();
        this.cisoForm.get('agencyName')?.updateValueAndValidity()
        this.listData.push({
          agencyCode: payload.agencyCode,
          agencyName: payload.agencyName,
          designation: payload.designation,
          emailId: payload.emailId,
          emailIdAlternate: payload.emailIdAlternate,
          mobile: payload.mobile,
          mobileAlternate: payload.mobileAlternate,
          name: payload.name,
          officeAddress: payload.officeAddress,
          post: payload.post,
          priority: payload.priority,
          remarks: payload.remarks,
        })
        this.cisoForm.reset();
        this.sharedService.showSuccess();
      }
    }, err => {

    })
  }
  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
}
