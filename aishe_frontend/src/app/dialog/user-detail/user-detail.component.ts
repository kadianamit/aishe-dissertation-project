import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { MailerService } from 'src/app/shared/mailer.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {
  confirmButtonText = "Submit"
  registation: FormGroup | any;
  status: any = null
  userId: string | null;
  remark: any = "";
  view: boolean = false;
  edit: boolean = false;
  userRoleList: any[] = [];
  districtList: any[] = [];
  stateList: any[] = [];
  variables: any[] = [];
  variables1: any[] = [];
  isFormInvalid: boolean = false
  roleList: any[] = [];
  approvalVal: any;
  disApprovalVal: any;
  inactiveVal: any;
  showInactive: boolean = false;
  constructor(public dialogRef: MatDialogRef<UserDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public authService: AuthService,
    public sharedService: SharedService, public mailer: MailerService, public fb: FormBuilder,public localService: LocalserviceService) {
    let emailRegex: RegExp = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    let userIdRegex: RegExp = /^[a-zA-Z0-9.]{8,16}$/;
    let passwordRegex: RegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&<>`~^()-_+=|"'{}\s]{8,}$/;;
    let mobileNoRegex: RegExp = /^[0]?[123456789]\d{9}$/
    this.userId = this.localService.getData('userId')
    if (this.data.action === 'view') {
      this.view = true;
      //return;
    } if (this.data.action === 'edit') {
      this.edit = true;
    }
    if (this.data.userStatus !== 1 && this.data.userStatus !== 3) {
      this.status = this.data.userStatus
    }
    this.approvalVal = this.data.userStatusList.find((e: any) => e.id === 2).id
    this.inactiveVal = this.data.userStatusList.find((e: any) => e.id === 3).id
    this.disApprovalVal = this.data.userStatusList.find((e: any) => e.id === 4).id
    if (this.data.userStatusName === 'Approved') {
      this.showInactive = false;
    } else if(this.data.userStatusName === 'Inactive') {
      this.showInactive = false;
    }else{
      this.showInactive = true;
    }
    this.status = this.data.userStatus
    this.registation = this.fb.group({
      userTypeId: ['', []],
      universityName: ['', []],
      aisheCode: ['', []],
      alternateEmailId: ['', [Validators.pattern(emailRegex)]],
      phone: ['', [Validators.pattern(mobileNoRegex)]],
      emailId: ['', [Validators.required, Validators.pattern(emailRegex)]],
      firstName: ['', [Validators.required]],
      gender: ['', [Validators.required]],
      middleName: ['', []],
      lastName: ['', []],
      mobile: ['', [Validators.required, Validators.pattern(mobileNoRegex)]],
      addressLine1: ['', []],
      addressLine2: ['', []],
      stateName: '',
      districtName: '',
      city: ['', []],
      roleName: ['', []],
      districtCode: ['', [Validators.required]],
      stateCode: ['', [Validators.required]],
      roleId: ['', [Validators.required]],
      role: ['', [Validators.required]]
    })
    if (this.data.role === '1' && this.data.roleId === parseInt(this.sharedService.role['Apex User'])) {
      this.registation.get('stateCode')?.clearValidators();
      this.registation.get('stateCode')?.updateValueAndValidity()
    }
    this.registation.patchValue(this.data)
    if (this.data.selectedIndex === 0 || this.data.selectedIndex === 1 || this.data.selectedIndex === 2) {
      this.filterData(this.data.role, 'add')
    }
  }

  ngOnInit(): void {
    this.stateList = this.data.stateList;
    this.districtList = this.data.districtList
  }
  filterData(role: any, event: any) {
    if (event === 'change') {
      this.registation.get('roleId')?.setValue('')
    }
    if (role === '1' && this.data?.subRoleLevel1) {
      this.roleList = []
      this.roleList = [...this.data?.subRoleLevel1]
      let i = this.data?.subRoleLevel1.findIndex((ele: any) => ele.id === parseInt(this.sharedService.role['State Nodal Officer']))
      this.roleList.splice(i, 1)
    } else if (role === '2' && this.data?.subRoleLevel2) {
      this.roleList = []
      this.roleList = [...this.data?.subRoleLevel2]
      let i = this.data?.subRoleLevel2.findIndex((ele: any) => ele.id === parseInt(this.sharedService.role['University']))
      this.roleList.splice(i, 1)
      this.roleList.push({
        id: parseInt(this.sharedService.role['State Nodal Officer']),
        roleName: 'State Nodal Officer'
      })
      this.roleList.push({
        id: parseInt(this.sharedService.role['Apex User']),
        roleName: 'Apex User'
      })
    } else {
      this.roleList = []
      this.roleList = [...this.data?.subRoleLevel3]
    }
  }
  getDistrict(state: any) {
    this.districtList = [];
    this.variables1 = [];
    this.authService.getDistrict(state).subscribe(
      (res) => {
        if (res && res.length) {
          this.variables1 = res;
          this.variables1 = this.variables1.sort((a, b) => a.name > b.name ? 1 : -1);
          this.districtList = this.variables1.slice();
        }
      },
      (err) => { }
    );
  }


  approveDis() { 
     let data1=this.data.userStatusName==="Inactive"?"Activate":this.data.userStatusName==="New Registration"?'Approve/Disapprove':this.data.userStatusName==="Disapproved"?'Approve':'Inactive'
    if (this.data.userStatus === 1 || this.data.userStatus === 3) {
      if(this.data.userStatus === this.status){
        this.sharedService.showValidationMessage(`Please choose ${data1} !!!`);
        return;
      }
    
    }if (this.data.userStatus === 4) {
      if(this.data.userStatus === this.status){
        this.sharedService.showValidationMessage(`Please choose ${data1} !!!`);
        return;
      }
    }
    if (this.data.userStatus === 2) {
    if (this.data.userStatus === this.status) {
      let data11=this.data.userStatusList[this.status].status
      this.sharedService.showValidationMessage(`Please choose ${data11}d !!!`);
      return;
    }
  }
    if (this.remark.trim() === "") {
      this.sharedService.showValidationMessage('Please enter Remarks !!!');
      return;
    }
    if (this.data.userStatus === 2) {
      if (this.status === 2) {
        this.sharedService.showValidationMessage('Already Approved')
        return
      }
    }

    let payload = {
      "aisheCode": this.data.aisheCode,
      "approvedBy": this.userId,
      "message": this.remark,
      "status": this.status,
      "userId": this.data.userId
    }
    this.authService.approvDisapproved(payload).subscribe(res => {
      if (res.status === 200) {
        this.mailer.sendEmail('UserManagement', this.data, this.status === this.approvalVal ? true : false).subscribe(res => {
        }, err => {
        })
        if (payload.status === this.approvalVal) {
          let dm=payload.status === this.approvalVal?'Approved/Active':data1
          this.sharedService.showSuccessMessage(`User has been ${dm} Successfully !!`);
        } else if(payload.status === this.disApprovalVal){
          this.sharedService.showSuccessMessage('User has been Disapproved Successfully !!');
        }else{
          this.sharedService.showSuccessMessage('User has been Inactive Successfully !!');
        }

        this.dialogRef.close(true);

      }
    }, err => {

    })
  }
  downloadPdf(data: any, fileName: string) {
    let uint8_data = _base64ToArrayBuffer(data);
    var ba = new Uint8Array(uint8_data);
    var blob = new Blob([ba], { type: 'application/pdf' });
    if (window.navigator && (window.navigator as any).msSaveOrOpenBlob) {
      (window.navigator as any).msSaveOrOpenBlob(blob);
    } else {
      var a = document.createElement("a");
      document.body.appendChild(a);
      var fileURL = URL.createObjectURL(blob);
      a.href = fileURL;
      a.download = fileName;
      //filename
      a.click();
    }
    function _base64ToArrayBuffer(base64: string) {
      var binary_string = window.atob(base64);
      var len = binary_string.length;
      var bytes = new Uint8Array(len);
      for (var i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
      }
      return bytes.buffer;
    }
  }
  getExistSNO() {
    return new Promise<string>((resolve, reject) => {
      this.authService.snoExist(this.registation.controls['roleId'].value, this.registation.controls['stateCode'].value).subscribe(
        (res) => {
          if (res.SnoExist === 'YES') {
            this.sharedService.showValidationMessagePDF('State Nodal Officer already exists');
          }
          resolve(res.SnoExist)
        },
        (err) => {
          reject('Error in Api')
        }
      );
    })

  }
  saveUserDetails() {

    if (parseInt(this.registation.controls['roleId'].value) === parseInt(this.sharedService.role['State Nodal Officer'])) {
      this.getExistSNO().then(res => {
        if (res === 'YES') {
          // this.sharedService.showValidationMessagePDF('State Nodal Officer already exists');
          return;
        }
      })
    }
    if (this.registation.controls['role'].value === '2' || this.registation.controls['role'].value === '3') {
      this.registation.get('stateCode')?.setValidators(Validators.required);
      this.registation.get('stateCode')?.updateValueAndValidity()
    } if (this.registation.controls['role'].value === '1' && parseInt(this.registation.controls['roleId'].value) === parseInt(this.sharedService.role['Apex User'])) {
      this.registation.get('stateCode')?.clearValidators();
      this.registation.get('stateCode')?.updateValueAndValidity()
    } else {
      this.registation.get('stateCode')?.setValidators(Validators.required);
      this.registation.get('stateCode')?.updateValueAndValidity()
    }
    if (this.registation.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    let payload = {
      "addressDistrictCode": this.registation.controls['districtCode'].value,
      "addressLine1": this.registation.controls['addressLine1'].value,
      "addressLine2": this.registation.controls['addressLine2'].value,
      "addressStateCode": this.registation.controls['role'].value === '1' && parseInt(this.registation.controls['roleId'].value) === parseInt(this.sharedService.role['Apex User']) ? '' : this.registation.controls['stateCode'].value,
      "aisheCode": this.registation.controls['aisheCode'].value,
      "altEmailId": this.registation.controls['alternateEmailId'].value,
      "city": this.registation.controls['city'].value,
      "emailId": this.registation.controls['emailId'].value,
      "firstName": this.registation.controls['firstName'].value,
      "genderId": parseInt(this.registation.controls['gender'].value),
      "ipAddress": "",
      "isApproved": this.data.isApproved,
      "lastName": this.registation.controls['lastName'].value,
      "middleName": this.registation.controls['middleName'].value,
      "phoneLandline": this.registation.controls['phone'].value,
      "phoneMobile": this.registation.controls['mobile'].value,
      "registrationDatetime": "",
      "roleId": parseInt(this.registation.controls['roleId'].value),
      "stateCode": this.registation.controls['role'].value === '1' && parseInt(this.registation.controls['roleId'].value) === parseInt(this.sharedService.role['Apex User']) ? '' : this.registation.controls['stateCode'].value,
      "stateLevelBody": this.data.stateLevelBody,
      "stateLevelBodyInstitute": this.data.stateLevelBodyInstitute,
      "statusId": this.data.userStatus,
      "stdCode": this.data.stdCode ? this.data.stdCode : this.registation.controls['phone'].value,
      "userId": this.data.userId,
    }

    this.authService.updateUser(payload).subscribe(res => {
      if (res.status === 201) {
        this.sharedService.showSuccessMessage('Record successfully updated !!!');
        this.dialogRef.close(true)
      }


    }, err => {

    })
  }
}
