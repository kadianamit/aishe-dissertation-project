import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';

@Component({
  selector: 'app-update-view',
  templateUrl: './update-view.component.html',
  styleUrls: ['./update-view.component.scss']
})
export class UpdateViewComponent implements OnInit {
  cisoForm: FormGroup;
  applicationForm: FormGroup
  confirmButtonText = "Update"
  cancelButtonText = "Close"
  isFormInvalid: boolean = false;
  addOrUpdate: string = 'Add'
  constructor(public dialogRef: MatDialogRef<UpdateViewComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any, public authService: AuthService,
    public sharedService: SharedService, public fb: FormBuilder) {
    let emailRegex: RegExp = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    let mobileNoRegex: RegExp = /^[0]?[123456789]\d{9}$/;
    let websitePattern: RegExp = /^(http[s]?:\/\/){0,1}(www\.){0,1}[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}[\.]{0,1}/

    this.cisoForm = this.fb.group({
      agencyCode: ['', []],
      agencyName: ['', []],
      name: ['', [Validators.required]],
      mobile: ['', [Validators.required, Validators.pattern(mobileNoRegex)]],
      emailId: ['', [Validators.required, Validators.pattern(emailRegex)]],
      officeAddress: ['', [Validators.required]],
      post: ['', [Validators.required]],
      designation: ['', [Validators.required]],
      remarks: ['', []],
      emailIdAlternate: ['', [Validators.pattern(emailRegex)]],
      mobileAlternate: ['', [Validators.pattern(mobileNoRegex)]],
    })
    this.applicationForm = this.fb.group({
      id: 0,
      departmentName:['',[Validators.required]],
      applicationName: ['', [Validators.required]],
      remarks: ['', []],
      websiteUrl: ['', [Validators.required, Validators.pattern(websitePattern)]],
    })
  }

  ngOnInit(): void {
    if (this.element.data.page === 'CISO') {
      if (this.element.data['emailIdAlternate'] === null || this.element.data['emailIdAlternate'] === 'null') {
        this.element.data['emailIdAlternate'] = ''
      } if (this.element.data['mobileAlternate'] === null || this.element.data['mobileAlternate'] === 'null') {
        this.element.data['mobileAlternate'] = ''
      } if (this.element.data['remarks'] === null || this.element.data['remarks'] === 'null') {
        this.element.data['remarks'] = ''
      }
      this.cisoForm.patchValue(this.element.data)
    } if (this.element.data.page === 'Website') {
      this.addOrUpdate = this.element.data.addOrUpdate
      if (this.addOrUpdate !== 'Add') {
        this.applicationForm.patchValue(this.element.data)
      }
    }if(this.element.data.page === 'Agency'){
      
    }

  }
  update(data: any) {
    if (this.cisoForm.invalid) {
      this.isFormInvalid = true;
      this.sharedService.showWarning();
      return
    } else {
      this.isFormInvalid = false;
    }
    for (let index = 0; index < this.element.list.length; index++) {
      if (this.element.i !== index) {
        if (this.element.list[index].agencyCode.toUpperCase() === data.agencyCode.toUpperCase() && this.element.list[index].post === data.post) {
          this.sharedService.showValidationMessage('Duplicate record not allowed !!!');
          return;
        }
      }
    }
    let payload = {
      name: data.name,
      priority: this.element.data.priority,
      agencyCode: data.agencyCode,
      agencyName: data.agencyName,
      mobile: data.mobile,
      emailId: data.emailId,
      emailIdAlternate: data.emailIdAlternate,
      post: data.post,
      mobileAlternate: data.mobileAlternate,
      designation: data.designation,
      officeAddress: data.officeAddress,
      remarks: data.remarks
    }

    this.authService.saveCisoForm(payload).subscribe(res => {
      if (res.status === 200) {
        this.sharedService.showSuccess();
        this.dialogRef.close(true)
      }
    }, err => {

    })
  }
  save(data: any) {
    if (this.applicationForm.invalid) {
      this.isFormInvalid = true;
      this.sharedService.showWarning();
      return
    } else {
      this.isFormInvalid = false;
    }
    for (let index = 0; index < this.element.list.length; index++) {
      if (this.element.i !== index) {
        if (this.element.list[index].applicationName.toUpperCase() === data.applicationName.toUpperCase() && this.element.list[index].websiteUrl === data.websiteUrl) {
          this.sharedService.showValidationMessage('Duplicate record not allowed !!!');
          return;
        }
      }
    }
    this.authService.saveUpdateWebsite(data).subscribe(res => {
      if (res.status === 200) {
        this.sharedService.showSuccess();
        this.dialogRef.close(true)
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