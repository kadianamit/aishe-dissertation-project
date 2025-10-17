import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';


@Component({
  selector: 'app-add-research-and-development',
  templateUrl: './add-research-and-development.component.html',
  styleUrls: ['./add-research-and-development.component.scss']
})
export class AddResearchAndDevelopmentComponent implements OnInit {
  @ViewChild('fileInput', { static: false }) myVar1: ElementRef | undefined;
  fileSizeUnit: number = 5120;
  myFiles: string[] = [];
  myFilesName: any = '';
  fileSizeExceed: any;
  uploadedMedia: Array<any> = [];
  changeDoc: boolean = false;
  fileSize: any = 0
  blob: any;
  researchForm: FormGroup;
  unSubscribeSubject: any;
  stateList: Array<any> = [];
  stateListArray: Array<any> = [];
  districtList: Array<any> = []
  districtListArray: Array<any> = []
  ministryArray: Array<any> = []
  ministryList: Array<any> = [];
  genderList: Array<any> = [];
  show: boolean | undefined;
  showField: boolean | undefined
  isFormInvalid: boolean | undefined
  errorMsg: string = 'This institute is not applicable under this category. You may opt to add either as a College or Standalone or University.'
  showErrorMsg: boolean = false
  aleadyExist: boolean = false
  disabledUpload: boolean | undefined
  surveyYearList: Array<any> = []
  constructor(public fb: FormBuilder, public authService: AuthService, public sharedService: SharedService,
    public encrypt: EncryptDecrypt, public localService: LocalserviceService, public errorMatcher: CustomErrorStateMatcher) {
    let phonePattern: RegExp = /^[0-9]{10,10}$/;
    let emailRegex: RegExp = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    this.researchForm = this.fb.group({
      totalno: ['', []],
      instituteTypeCorS: ['R', []],
      instituteName: ['', [Validators.required]],
      stateCode: ['', [Validators.required]],
      districtCode: ['', [Validators.required]],
      firstName: ['', [Validators.required]],
      personLine1: ['', []],
      directorEmail: ['', [Validators.required, Validators.pattern(emailRegex)]],
      directorMobile: ['', [Validators.required, Validators.pattern(phonePattern)]],
      directorGender: ['', [Validators.required]],
      directorDesignation: ['', [Validators.required]],
      directorName: ['', [Validators.required]],
      personDesignation: ['', [Validators.required]],
      personEmail: ['', [Validators.required, Validators.pattern(emailRegex)]],
      personMobile: ['', [Validators.required, Validators.pattern(phonePattern)]],
      personLandline: ['', []],
      stdCode: ['', []],
      personGender: ['', [Validators.required]],
      personPincode: ['', []],
      ministryId: ['', [Validators.required]],
      // surveyYear: ['',[Validators.required]],
    }
    )
    this.disableControl()
  }

  ngOnInit(): void {
    this.getStateList();
    this.getMinistryList();
    this.getGenderList();
    this.surveyYear()
  }
  surveyYear() {
    this.authService.getSurveyYearListPrivate().subscribe((res) => {
      this.surveyYearList = res;
    }, err => {

    });
  }
  getStateList() {
    this.authService.getStatePrivate().subscribe(res => {
      if (res && res.length) {
        this.stateListArray = res;
        this.stateList = this.stateListArray.slice()
      }
    }, err => {

    })
  }
  getDistrictList(value: any) {
    this.getSimilarIns()
    this.authService.getDistrict(value).subscribe(res => {
      if (res && res.length) {
        this.districtListArray = res;
        this.districtList = this.districtListArray.slice()
      }
    }, err => {

    })
  }
  getMinistryList() {
    this.authService.administrativeMinistry().subscribe(res => {
      this.ministryArray = res;
      this.ministryList = this.ministryArray.slice()
    }, err => {

    })
  }
  getGenderList() {
    this.authService.getGender().subscribe(res => {
      this.genderList = res;
    }, err => {

    })
  }
  save() {
    if (this.researchForm.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true
      return;
    } else {
      this.isFormInvalid = false
    }
    if (!this.blob && !this.myFilesName && this.myFiles.length <= 0) {
      this.sharedService.showValidationMessage('Please upload document !!!')
      return;
    }
    const formData: FormData = new FormData();
    if (this.changeDoc) {
      for (var i = 0; i < this.myFiles.length; i++) {
        formData.append('file', this.myFiles[i]);
      }
    } else {
      if (this.blob) {
        formData.append('file', this.blob, this.myFilesName);
      }
    }
    let data = this.researchForm.getRawValue()
    let telephone = ''
    if (data.stdCode?.length > 5) {
      this.sharedService.showValidationMessage('STD Code should not be more than 5 digit !!!');
      return;
    }
    if (data.personLandline?.length > 13) {
      this.sharedService.showValidationMessage('Phone Number should not be more than 13 digit !!!');
      return;
    }
    
    if (data.stdCode && data.personLandline) {
      telephone = data.stdCode.toString().concat("-", data.personLandline.toString());
    }

    let payload = {
      instituteTypeCorS: data.instituteTypeCorS,
      instituteName: data.instituteName?.toUpperCase(),
      stateCode: data.stateCode,
      districtCode: data.districtCode,
      firstName: data.firstName,
      personLine1: data.personLine1,
      directorEmail: data.directorEmail,
      directorMobile: data.directorMobile,
      directorGender: data.directorGender,
      directorDesignation: data.directorDesignation,
      directorName: data.directorName,
      personDesignation: data.personDesignation,
      personEmail: data.personEmail,
      personMobile: data.personMobile,
      personLandline: telephone ? telephone : '',
      // stdCode: data.stdCode,
      personGender: data.personGender,
      personPincode: data.personPincode,
      personName: data.firstName,
      ministryId: data.ministryId
      // surveyYear:data.surveyYear
    }
    formData.append('requestVo', JSON.stringify(payload))
    this.authService.saveRequestForm(formData).subscribe(res => {
      if (res.status === 200) {
        this.apprequestDetails(res.data)
      }
    }, err => {
    })
  }
  async getFileDetails(e: any) {
    this.myFiles = [];
    this.myFilesName = '';
    for (var i = 0; i < e.target.files.length; i++) {
      // if (e.target.files[i].size > 5242880) {
      //   this.fileSizeExceed = true;
      //   this.sharedService.showValidationMessage('File should be in pdf format only & less than 5MB.')
      //   return;
      // }
      let size: number = e.target.files[i].size / 1024 / 1024;
      size = parseInt(size.toFixed(2));
      if (size > 2) {
        this.myFilesName = ''
        this.myFiles = []
        this.fileSizeExceed = true;
        this.sharedService.showValidationMessage('File should be in pdf format only & less than 2MB. !!!');
        return;
      } else {
        this.changeDoc = true;
        this.fileSizeExceed = false;
        this.myFiles.push(e.target.files[i]);
        this.myFilesName += e.target.files[i].name;
      }
      if (!(e.target.files.length - 1 === i)) {
        this.myFilesName += ',';
      }
    }
  }
  onKeypressEvent(event: any, inputLength: any) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
  reset() {
    this.researchForm.reset();
    this.researchForm.get('instituteTypeCorS')?.setValue('R')
    this.showField = false
    this.myFiles = [];
    this.myFilesName = '';
    this.aleadyExist = false
    this.disableControl()
  }
  password() {
    this.show = !this.show;
  }
  total() {
    if (this.researchForm.controls['totalno'].value === 0) {
      this.showField = true
      this.showErrorMsg = false
    } else {
      this.showField = false
      this.showErrorMsg = true
    }
  }
  apprequestDetails(requestId: any) {
    let payload = {
      "approverRoleId": this.localService.getData('roleId'),
      "remark": "",
      "requestId": requestId,
      "statusId": 1

    }
    this.authService.approveReq(payload).subscribe(res => {
      if (res.statusCode === 'AISH001') {
        var ele = {
          research: true,
          aisheCode: res.college?.id,
        }
        this.sharedService.savePopUp(ele).subscribe(res => {
          this.reset()
        })
      } else {
        this.sharedService.showValidationMessage(res.statusDesc)
      }
    }, err => {

    })
  }
  getSimilarIns() {
    let data = this.researchForm.getRawValue()
    if (data.instituteName.trim('') === '') {
      this.disableControl()
    }
    if (data.instituteName && data.ministryId && data.stateCode && data.districtCode) {
      let payload = {
        instituteType: data.instituteTypeCorS,
        districtCode: data.districtCode,
        instituteName: data.instituteName,
      }
      this.authService.getInstituteNameExist(payload).subscribe(res => {
        if (res && res.length) {
          this.aleadyExist = true
          this.disableControl()
          let ele = {
            research: true
          }
          this.sharedService.errorMessage(ele)
        } else {
          this.aleadyExist = false
          this.enableControl()
        }
      }, err => {

      })

    } else {

    }
  }
  disableControl() {
    // this.researchForm.get('surveyYear')?.disable()
    // this.researchForm.get('surveyYear')?.updateValueAndValidity()
    this.researchForm.get('personLine1')?.disable()
    this.researchForm.get('personLine1')?.updateValueAndValidity()
    this.researchForm.get('personPincode')?.disable()
    this.researchForm.get('personPincode')?.updateValueAndValidity()
    this.researchForm.get('firstName')?.disable()
    this.researchForm.get('firstName')?.updateValueAndValidity()
    this.researchForm.get('personGender')?.disable()
    this.researchForm.get('personGender')?.updateValueAndValidity()
    this.researchForm.get('personEmail')?.disable()
    this.researchForm.get('personEmail')?.updateValueAndValidity()
    this.researchForm.get('personMobile')?.disable()
    this.researchForm.get('personMobile')?.updateValueAndValidity()
    this.researchForm.get('personDesignation')?.disable()
    this.researchForm.get('personDesignation')?.updateValueAndValidity()
    this.researchForm.get('personLandline')?.disable()
    this.researchForm.get('personLandline')?.updateValueAndValidity()
    this.researchForm.get('stdCode')?.disable()
    this.researchForm.get('stdCode')?.updateValueAndValidity()
    this.researchForm.get('directorName')?.disable()
    this.researchForm.get('directorName')?.updateValueAndValidity()
    this.researchForm.get('directorEmail')?.disable()
    this.researchForm.get('directorEmail')?.updateValueAndValidity()
    this.researchForm.get('directorMobile')?.disable()
    this.researchForm.get('directorMobile')?.updateValueAndValidity()
    this.researchForm.get('directorGender')?.disable()
    this.researchForm.get('directorGender')?.updateValueAndValidity()
    this.researchForm.get('directorDesignation')?.disable()
    this.researchForm.get('directorDesignation')?.updateValueAndValidity()
    this.disabledUpload = true
  }
  enableControl() {
    // this.researchForm.get('surveyYear')?.enable()
    // this.researchForm.get('surveyYear')?.updateValueAndValidity()
    this.researchForm.get('personLine1')?.enable()
    this.researchForm.get('personLine1')?.updateValueAndValidity()
    this.researchForm.get('personPincode')?.enable()
    this.researchForm.get('personPincode')?.updateValueAndValidity()
    this.researchForm.get('firstName')?.enable()
    this.researchForm.get('firstName')?.updateValueAndValidity()
    this.researchForm.get('personGender')?.enable()
    this.researchForm.get('personGender')?.updateValueAndValidity()
    this.researchForm.get('personEmail')?.enable()
    this.researchForm.get('personEmail')?.updateValueAndValidity()
    this.researchForm.get('personMobile')?.enable()
    this.researchForm.get('personMobile')?.updateValueAndValidity()
    this.researchForm.get('personDesignation')?.enable()
    this.researchForm.get('personDesignation')?.updateValueAndValidity()
    this.researchForm.get('personLandline')?.enable()
    this.researchForm.get('personLandline')?.updateValueAndValidity()
    this.researchForm.get('stdCode')?.enable()
    this.researchForm.get('stdCode')?.updateValueAndValidity()
    this.researchForm.get('directorName')?.enable()
    this.researchForm.get('directorName')?.updateValueAndValidity()
    this.researchForm.get('directorEmail')?.enable()
    this.researchForm.get('directorEmail')?.updateValueAndValidity()
    this.researchForm.get('directorMobile')?.enable()
    this.researchForm.get('directorMobile')?.updateValueAndValidity()
    this.researchForm.get('directorGender')?.enable()
    this.researchForm.get('directorGender')?.updateValueAndValidity()
    this.researchForm.get('directorDesignation')?.enable()
    this.researchForm.get('directorDesignation')?.updateValueAndValidity()
    this.disabledUpload = false
  }

  ngOnDestroy(): void {
    this.unSubscribeSubject?.unsubscribe();
  }
}