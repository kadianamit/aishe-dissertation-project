import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { NotificationService } from 'src/app/service/reports/notification.service';
import { Subject } from 'rxjs';
import { SharedService } from 'src/app/shared/shared.service';
import { MustMatch } from 'src/app/shared/custome-validators';

@Component({
  selector: 'app-user-request',
  templateUrl: './user-request.component.html',
  styleUrls: ['./user-request.component.scss']
})
export class UserRequestComponent implements OnInit {
  checkLocked: any;
  checkLockedButton: boolean = false;
  checkSaveStatus: boolean = false;
  changeDoc: boolean = false;
  isDisabled: boolean = false;
  bankDocumentName: any;
  blob: any;
  fileSize: any = 0
  fileSizeUnit: number = 5120;
  myFiles: string[] = [];
  myFilesName: any = '';
  fileSizeExceed: any;
  uploadedMedia: Array<any> = [];
  selectedIndex: any = 0;
  eligibilityCheck: boolean = false;
  isFormInvalid: boolean = false;
  showButtonLock: boolean = false;
  showTableStatus: boolean = false;
  universityData: any
  instituteName: any
  userID: any;
  role: any;
  mobile: any;
  isFormInvalidA: boolean = false;
  statusData: any;
  username: any
  firstName: any;
  roleName: any;
  Ro: any;
  showMessage: any;
  dcfUploadStatus: any;
  bankAccountStatus: any;
  transactionStatus: any;
  approvalStatusList: any;
  University: number = 10000;
  College: number = 5000;
  standaloneInstitutions: number = 2000;
  fundPerUniversity: any;
  totalFund: any;
  aisheCode: any;
  disabledPage: boolean = false;
  userDetail = new FormGroup({

  })

  dataSource: Array<any> = []; statusId: any;
  ;
  regex = /^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$/;// /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/; 
  // reg = /^[A-Za-z]{4}[0-9]{6,7}$/;

  // reg=/^[A-Za-z]{4}[A-Z0-9]{7}$/;
  reg = /^[A-Za-z]{4}[A-Za-z0-9]{7}$/;;
  constructor(public authService: AuthService, public fb: FormBuilder, public notificationService: NotificationService,
    public sharedService: SharedService
  ) {
    this.userDetail = this.fb.group({
      nameofUser: ({ value: '', disabled: true }),
      nameOfInstitute: ({ value: '', disabled: true }),
      ifsc_code: ['', [Validators.required, Validators.pattern(this.reg)]],
      pan: ['', [Validators.required, Validators.pattern(this.regex)]],
      email: ({ value: '', disabled: true }),
      bankName: ['', [Validators.required]],
      accountHolderName: ['', [Validators.required, Validators.maxLength(75)]],
      surveyYear: ['', [Validators.required]],
      mobile: ({ value: '', disabled: true }),
      roleId: ({ value: this.roleName, disabled: true }),
      aisheCode: ({ value: this.aisheCode, disabled: true }),
      bankAddress: ['', [Validators.required]],
      pincode: ['', [Validators.required]],

      accountNumber: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(18)]],
      reaccountNumber: ['', [Validators.required]],
    }, {
      validator: MustMatch('accountNumber', 'reaccountNumber'),
    });
    this.sharedService.getUserDetails.subscribe(res => {
      if (res !== 0) {
        this.instituteName = res.instituteName
        this.userID = res.userId
        this.role = res.roleId
        this.aisheCode = res.aisheCode
        this.username = res.name
        this.userDetail.get('nameofUser')?.setValue(res.name)
        this.userDetail.get('nameOfInstitute')?.setValue(res.instituteName)
        this.userDetail.get('email')?.setValue(res.email)
        this.userDetail.get('mobile')?.setValue(res.mobile)
        this.userDetail.get('aisheCode')?.setValue(this.aisheCode)
        this.getuserRole()
      }
    })

  }

  ngOnInit(): void {
    console.log('')

  }
  onKeypressEvent(event: any, inputLength: any) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }

  panValidator(control: AbstractControl): { [key: string]: any } | null {
    const regex = /([A-Z]){5}([0-9]){4}([A-Z]){1}$/;

    if (control.value && !regex.test(control.value)) {
      return { 'invalidPan': true };
    }

    return null;
  }


  get f(): { [key: string]: AbstractControl } {
    return this.userDetail.controls;
  }
  tabIndex = 0;

  tabSelected(event: any) {
    this.selectedIndex = event.index
    if (this.selectedIndex === 0) {
      this.userDetail.reset();
    } if (this.selectedIndex === 1) {
      this.eligibilityCheck = false;

    }
  }


  getuserRole() {
    this.authService.getUserRole().subscribe(res => {
      if (res && res.length) {
        var role = res.find((ele: any) => ele.roleId === this.role)
        this.roleName = role.roleName;
        this.userDetail.get('roleId')?.setValue(this.roleName)
      }
    }, err => {

    })
  }


  DisablePaste(event: any) {
    event.preventDefault();
  }
  getUniversityData() {
    let aisheCode1 = this.aisheCode?.split("-")[1];
    this.authService.getUniversityRemu(aisheCode1, this.userDetail.value.surveyYear).subscribe((res) => {
      if (res.status === 200) {
        this.universityData = res?.data
        if (this.universityData?.totalAffiliatedCollege >= 50) {
          this.eligibilityCheck = true;
          this.showMessage = false
          this.getuserdata()
        } else {
          this.showMessage = true;
          this.eligibilityCheck = false;
        }
      }
    }, error => { })
  }

  checkEligibility(event: any) {


    this.authService.surveyYearsRemuneration(this.aisheCode, event).subscribe(res => {
      if (res?.status === 200) {

        if (res?.data !== null) {

          if (res.data?.length > 0 && res?.data) {

            this.eligibilityCheck = true;
            this.showMessage = false;
            this.getuserdata()

          } else {

            if (this.role !== 7) {
              this.showMessage = true;
              this.eligibilityCheck = false;
            }

            if (this.role === 7) {
              this.getUniversityData();
            }

          }
        } else {
          if (this.role === 7) {
            this.getUniversityData();
          }
          if (this.role !== 7) {
            this.showMessage = true;
            this.eligibilityCheck = false;
          }
        }
      }
    })
  }
  checkEligibilityStatus(event: any) {
    this.authService.getRemunerationStatus(event, this.userID).subscribe((res) => {
      if (res.status === 200) {
        this.statusData = res.data;
        this.dcfUploadStatus = res.data.dcfUploadStatus
        this.bankAccountStatus = res.data.bankAccountStatus;
        this.transactionStatus = res.data.transactionStatus;
        this.approvalStatusList = res.data.approvalStatusList;
        this.showTableStatus = true;
      }
    })

  }
  getuserdata() {

    this.authService.getAccountDetails(this.userDetail.value.surveyYear, this.aisheCode).subscribe((res) => {
      if (res.data && res.data.length > 0) {
        this.statusId = res.data.statusId
        if (res.data[0].statusId === 12 || res.data[0].statusId === 13 || res.data[0].statusId === 3 || res.data[0].statusId === 2 || res.data[0].statusId === 6) {
          this.showButtonLock = true;
          this.checkLocked = res.data[0].statusId;
          this.userDetail.get('pan')?.disable();
          this.userDetail.get('accountHolderName')?.disable();
          this.userDetail.get('accountNumber')?.disable();
          this.userDetail.get('reaccountNumber')?.disable();
          this.userDetail.get('bankName')?.disable();
          this.userDetail.get('ifsc_code')?.disable();
          this.userDetail.get('bankAddress')?.disable();
          this.userDetail.get('pincode')?.disable();

        }else if (res.data[0].statusId === 1 || res.data[0].statusId === 8 || res.data[0].statusId === 9 || res.data[0].statusId === 10 || res.data[0].statusId === 11) {
          this.showButtonLock = false;
          this.userDetail.get('pan')?.enable();
          this.userDetail.get('accountHolderName')?.enable();
          this.userDetail.get('accountNumber')?.enable();
          this.userDetail.get('reaccountNumber')?.enable();
          this.userDetail.get('bankName')?.enable();
          this.userDetail.get('ifsc_code')?.enable();
          this.userDetail.get('bankAddress')?.enable();
          this.userDetail.get('pincode')?.enable();
        }else{
          this.showButtonLock = false;
          this.userDetail.get('pan')?.enable();
          this.userDetail.get('accountHolderName')?.enable();
          this.userDetail.get('accountNumber')?.enable();
          this.userDetail.get('reaccountNumber')?.enable();
          this.userDetail.get('bankName')?.enable();
          this.userDetail.get('ifsc_code')?.enable();
          this.userDetail.get('bankAddress')?.enable();
          this.userDetail.get('pincode')?.enable();
        }
        this.dataSource = res.data[0]?.track;
        this.userDetail.patchValue(res.data[0]);
        this.userDetail.get('pan')?.setValue(res.data[0]?.pan);
        this.userDetail.get('accountHolderName')?.setValue(res.data['0']?.accountHolderName);
        this.userDetail.get('reaccountNumber')?.setValue(res.data['0']?.accountNumber);
        this.userDetail.get('accountNumber')?.setValue(res.data['0']?.accountNumber);
        this.userDetail.get('bankName')?.setValue(res.data['0']?.bankName);
        this.userDetail.get('ifsc_code')?.setValue(res.data['0']?.ifsc_code);
        this.userDetail.get('bankAddress')?.setValue(res.data['0']?.bankAddress);
        this.userDetail.get('pincode')?.setValue(res.data['0']?.pincode);
        this.bankDocumentName = res.data[0]?.bankDocument;
        this.downloadPdf(res.data[0]?.bankDocument)
        this.myFilesName = res.data[0]?.bankDocumentName
      } else {
        this.isFormInvalid = false;
        this.showButtonLock = false;
        this.myFilesName = '';
        this.userDetail.get('pan')?.setValue('');
        this.userDetail.get('accountHolderName')?.setValue('');
        this.userDetail.get('reaccountNumber')?.setValue('');
        this.userDetail.get('accountNumber')?.setValue('');
        this.userDetail.get('bankName')?.setValue('');
        this.userDetail.get('ifsc_code')?.setValue('');
        this.userDetail.get('bankAddress')?.setValue('');
        this.userDetail.get('pincode')?.setValue('')
      }

    });
  }

  compareFiles(input: any, output: any) {
    for (var i = 0; i < input.length; i++) {

    }
  }
  downloadPdf(data: any) {
    let uint8_data = _base64ToArrayBuffer(data);
    var ba = new Uint8Array(uint8_data);
    this.blob = new Blob([ba], { type: 'application/pdf' });
    function _base64ToArrayBuffer(base64: any) {
      var binary_string = window.atob(base64);
      var len = binary_string.length;
      var bytes = new Uint8Array(len);
      for (var i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
      }
      return bytes.buffer;
    }
  }

  tab: any
  viewPdf(data: any, fileName: string) {
    let uint8_data = _base64ToArrayBuffer(data);
    var ba = new Uint8Array(uint8_data);
    var blob = new Blob([ba], { type: 'application/pdf' });
    const url = window.URL.createObjectURL(blob);
    this.tab = window.open(url, fileName);
    this.tab.location.href = url;

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

  async getFileDetails(e: any) {
    this.myFiles = [];
    this.myFilesName = '';
    //this.uploadedMedia = [];
    for (var i = 0; i < e.target.files.length; i++) {
      if (e.target.files[i].size > 5242880) {
        this.fileSizeExceed = true;
        this.notificationService.showValidationMessage('File size should be less than 5MB.')
        return;
      }

      else {
        this.changeDoc = true;
        this.fileSizeExceed = false;
        this.myFiles.push(e.target.files[i]);
        this.myFilesName += e.target.files[i].name;
      }
      if (!(e.target.files.length - 1 === i)) {
        this.myFilesName += ',';
      }
    }
    const target = e.target as HTMLInputElement;
    this.processFiles(target.files);
  }
  processFiles(files: any) {
    for (const file of files) {
      if (file.type != 'application/pdf') {
        this.notificationService.showValidationMessage(
          'Please upload pdf file only!!!'
        );
        this.myFilesName = '';
        this.myFiles = [];
        return;
      }
      var reader = new FileReader();
      reader.readAsDataURL(file); // read file as data url
      reader.onload = (event: any) => {
        // called once readAsDataURL is completed
        this.myFilesName = file.name;
        this.uploadedMedia.push({
          FileName: file.name,
          FileSize:
            this.getFileSize(file.size) + ' ' + this.getFileSizeUnit(file.size),
          FileType: file.type,
          FileUrl: event.target.result,
          FileProgessSize: 0,
          FileProgress: 0,
          ngUnsubscribe: new Subject<any>(),
        });

        this.startProgress(file, this.uploadedMedia.length - 1);
      };
    }
  }
  async startProgress(file: any, index: number) {
    let filteredFile = this.uploadedMedia
      .filter((u: any, index: number) => index === index)
      .pop();

    if (filteredFile != null) {
      let fileSize = this.getFileSize(file.size);
      let fileSizeInWords = this.getFileSizeUnit(file.size);

      for (var f = 0; f < fileSize + fileSize * 0.0001; f += fileSize * 0.01) {
        filteredFile.FileProgessSize = f.toFixed(2) + ' ' + fileSizeInWords;
        var percentUploaded = Math.round((f / fileSize) * 100);
        filteredFile.FileProgress = percentUploaded;
        await this.fakeWaiter(Math.floor(Math.random() * 35) + 1);
      }
    }
  }
  getFileSizeUnit(fileSize: number) {
    let fileSizeInWords = 'bytes';

    if (fileSize > 0) {
      if (fileSize < this.fileSizeUnit) {
        fileSizeInWords = 'bytes';
      } else if (fileSize < this.fileSizeUnit * this.fileSizeUnit) {
        fileSizeInWords = 'KB';
      } else if (
        fileSize <
        this.fileSizeUnit * this.fileSizeUnit * this.fileSizeUnit
      ) {
        fileSizeInWords = 'MB';
      }
    }

    return fileSizeInWords;
  }
  fakeWaiter(ms: number) {
    return new Promise((resolve) => {
      setTimeout(resolve, ms);
    });
  }
  getFileSize(fileSize: number): number {
    this.fileSize = fileSize;
    if (this.fileSize / 1024 / 1024 > 4) {
      console.log(this.fileSize / 1024 / 1024 > 4)
    }
    if (fileSize > 0) {
      if (fileSize < this.fileSizeUnit * this.fileSizeUnit) {
        fileSize = parseFloat((fileSize / this.fileSizeUnit).toFixed(2));
      } else if (
        fileSize <
        this.fileSizeUnit * this.fileSizeUnit * this.fileSizeUnit
      ) {
        fileSize = parseFloat(
          (fileSize / this.fileSizeUnit / this.fileSizeUnit).toFixed(2)
        );
      }
    }

    return fileSize;
  }


  saveAccountDetails() {
    if (this.userDetail.invalid) {
      this.isFormInvalid = true;
      if (this.userDetail.value.accountNumber && this.userDetail.value.reaccountNumber) {
        if (this.userDetail.value.accountNumber !== this.userDetail.value.reaccountNumber) {
          this.notificationService.showError('Account Number does not match')
        }
      }

      this.isFormInvalid = true;
      return;
    }

    this.isFormInvalid = false
    if (!this.blob && !this.myFilesName && this.myFiles.length <= 0) {
      this.notificationService.showValidationMessage('Please upload document !!!')
      return;
    }
    const formdata: FormData = new FormData();
    if (this.changeDoc) {
      for (var i = 0; i < this.myFiles.length; i++) {
        formdata.append('file', this.myFiles[i]);
      }

    } else {

      formdata.append('file', this.blob, this.myFilesName);
    }

    const formDataValue = { ...this.userDetail.value };
    formDataValue.pan = formDataValue.pan.toUpperCase();
    this.authService.saveRemuneration(formDataValue, formdata).subscribe((res) => {
      if (res.status === 200) {
        this.checkSaveStatus = true;
        this.getuserdata();
        this.notificationService.showSuccess('Account details save successfully')
        
      }

    }, erro => {
      if (erro.error.status === 422) {
        this.notificationService.showValidationMessage(erro.error.message)
      }
    });

  }

  resetForm() {
    this.getuserdata();
  }
  numberOnly(e: any) {  // Accept only alpha numerics, not special characters 
    var regex = new RegExp("^[0-9 ]+$");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
      return true;
    }

    e.preventDefault();
    return false;
  }

  alphaOnly(e: any) {  // Accept only alpha numerics, not special characters 
    var regex = new RegExp("^[a-zA-Z ]+$");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
      return true;
    }

    e.preventDefault();
    return false;
  }
  saveLock() {
    if (!this.checkSaveStatus) {
      this.notificationService.showValidationMessage('Please Save the Details!!!')
      return;
    }
    let temp = [
      {
        "aisheCode": this.aisheCode,
        "status": 13,
        "surveyYear": parseInt(this.userDetail.value.surveyYear),
        "userId": this.userID
      }
    ]
    this.authService.saveRemunerationStatus(temp).subscribe((res: any) => {
      if (res.status === 200) {
        this.getuserdata();
        this.sharedService.showSuccessMessage("Successfully Locked Data!");
      }
    })

  }

}
