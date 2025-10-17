import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { NotificationService } from 'src/app/service/reports/notification.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-update-user-dialog',
  templateUrl: './update-user-dialog.component.html',
  styleUrls: ['./update-user-dialog.component.scss']
})
export class UpdateUserDialogComponent implements OnInit {
  userForm:any = FormGroup;
  emailRegex: RegExp = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
  mobileNoRegex: RegExp = /^[123456789]\d{9}$/;
  oldEmail:any;
  oldMob:any;
  oldName:any;
  oldGender:any;
  userId:any;
  isBtnVisible:boolean = true
  isStatus:any;
  isFormInvalid: boolean = false;


  constructor(public dialogRef: MatDialogRef<UpdateUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public fb: FormBuilder, public authService: AuthService, private notification : NotificationService, private sharedService : SharedService) { 
      this.userForm = this.fb.group({
        userId: { value: this.data.userId, disabled: true },
        emailId: [this.data.emailId, [Validators.required, Validators.pattern(this.emailRegex)]],
        gender: [this.data.gender, [Validators.required]],
        mobile: [this.data.mobile, [Validators.required, Validators.pattern(this.mobileNoRegex)]],
        fullName: [this.data.fullName.trim(), [Validators.required]],
       
      })

      this.oldName = this.data.fullName
      this.oldMob = this.data.mobile
      this.oldEmail = this.data.emailId
      this.oldGender = this.data.gender
      this.userId = this.data.userId

    }

  ngOnInit(): void {

  }



  updateUser(data:any){
    if (this.userForm.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true
      return;
    } else {
      this.isFormInvalid = false
    }
    if (this.data?.mobile?.toString() === data.mobile?.toString()) {
      this.oldMob = ''
    } if (this.data?.fullName?.trim() === data.fullName?.trim()) {
      this.oldName = ''
    } if (this.data?.gender === data?.gender) {
      this.oldGender = ''
    }
    if (this.data?.emailId === data?.emailId) {
      this.oldEmail = ''
    }
 
    if (data.emailId === this.data?.emailId && data.fullName === this.data?.fullName && data.gender === this.data?.gender && data.mobile === this.data?.mobile && data.emailId === this.data?.emailId) {
      this.notification.showValidationMessage('Please update');
      return;
    }

    if(data.fullName?.trim() === ''){
      this.notification.showValidationMessage('Please enter required name field!!!');
      return;
    }

   let isUpdated:any = true
    let payload = {
      "oldEmailId": this.oldEmail,
      "oldFirstName": this.oldName?.trim(),
      "oldGender": 1,
      "oldMobile": this.oldMob === null?'':this.oldMob,
      "name": data.fullName?.trim(),
      "genderId": +data.gender,
      "emailId": data.emailId,
      "phoneMobile": data.mobile?.toString(),
       "userId": this.userId,
    }
    this.authService.editUser(payload, isUpdated).subscribe(res =>{
      if(res.status === 201){
        this.isStatus = 'Success'
        this.notification.showSuccess(res.message)
        this.closeDialog()
      }
      

    }, (err) => {
      this.sharedService.showValidationMessage(err.error.message);
    })
  }

  closeDialog(){
    let status = this.isStatus
    this.dialogRef.close({ success: false, status})
  }

  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }

}
