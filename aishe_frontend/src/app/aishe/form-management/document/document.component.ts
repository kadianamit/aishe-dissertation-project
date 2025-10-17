import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.scss']
})
export class DocumentComponent implements OnInit {
  downloadForm: FormGroup
  isFormInvalid: boolean = false
  roleId: any;
  showMsg: boolean | undefined
  nodalOfficerName: any;
  pageStatusList: any[] = [];
  showField: boolean | undefined
  surveyYearList: Array<any> = []
  constructor(public authService: AuthService, public sharedService: SharedService, public fb: FormBuilder, public localService: LocalserviceService) {
    this.downloadForm = this.fb.group({
      aisheCode: ['', []],
      institutionType: ['C', []],
      surveyYear: ['', [Validators.required]],
      documentValue: ['', [Validators.required]]
    })
    this.roleId = this.localService.getData('roleId')
    this.getSurveyYear()
  }

  ngOnInit(): void {
    if (this.roleId === this.sharedService.role['College']
      || this.roleId === this.sharedService.role['Polytechnic']
      || this.roleId === this.sharedService.role['Nursing (Diploma) Institute']
      || this.roleId === this.sharedService.role['Teacher Training (Diploma) Institute']
      || this.roleId === this.sharedService.role['Standalone Institution Under Ministry']
      || this.roleId === this.sharedService.role['PGDM']
      || this.roleId === this.sharedService.role['Paramedical Institute']
      || this.roleId === this.sharedService.role['Hotel Management and Catering Institute']
      || this.roleId === this.sharedService.role['University']) {
      this.downloadForm.get('aisheCode')?.setValue(this.localService.getData('aisheCode'))
      this.downloadForm.get('institutionType')?.setValue(this.localService.getData('loginMode'))
    }
    if (this.roleId === this.sharedService.role['SysAdmin'] || this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['State Nodal Officer']) {
      this.showField = true;
    } else {
      this.showField = false;
    }
  }
  download() {
    let aisheCode = ''
    if (this.roleId === this.sharedService.role['SysAdmin'] || this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['State Nodal Officer']) {
      aisheCode = this.downloadForm.controls['institutionType'].value + '-' + this.downloadForm.controls['aisheCode'].value
    } else {
      aisheCode = this.downloadForm.controls['aisheCode'].value
    }
    let x: any = aisheCode.split('-');
    let aisheId = x[1];
    let loginMode = x[0];
    if (this.downloadForm.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false
    }
    let survey = this.downloadForm.controls['surveyYear'].value

    if (parseInt(survey) < 2020) {
      this.getDownloadData(aisheCode, loginMode)
    } else {
      let payload = {
        'surveyYear': this.downloadForm.controls['surveyYear'].value,
        'aisheCode': aisheId,
        'instituteType': loginMode
      }
      this.authService.getLockStatus(payload).subscribe(data => {
        if (data['final_lock'] === true) {
          this.showMsg = false;
          this.getDownloadData(aisheCode, loginMode)
        } else {
          this.showMsg = true;
        }


      }, err => {

      })

    }

  }
  getDownloadData(aisheCode: any, loginMode: any) {
    if (this.downloadForm.controls['documentValue'].value === 'CERTIFICATE') {
      // let survey = this.downloadForm.controls['surveyYear'].value
      // if (parseInt(survey) === 2023) {
      //   return;
      // }
      let payload = {
        instituteType: this.downloadForm.controls['institutionType'].value,
        aisheCode: aisheCode,
        surveyYear: this.downloadForm.controls['surveyYear'].value,
      }
      this.authService.getOfficerList(payload).subscribe(res => {
        if (res.data && res.data.length) {
          res.data.forEach((ele: any) => {
            if (ele.universityPk.officerType === 'NO') {
              this.nodalOfficerName = ele.officerName;
              this.getCertificate(aisheCode)
            }
          })
        }
      }, err => {

      })

    } else if (this.downloadForm.controls['documentValue'].value === 'DATA CAPTURE FORMAT(DCF)') {

      let payload = {
        aisheCode: aisheCode,
        surveyYear: this.downloadForm.controls['surveyYear'].value,
      }
      let survey = this.downloadForm.controls['surveyYear'].value

      if (parseInt(survey) <= 2019) {
        let type = loginMode === 'C'?'COLLEGE INSTITUTION':loginMode === 'S'?'STANDALONE INSTITUTION':'University'
        this.authService.getDownloadFiletill2019(payload, type)
      } else {
        this.authService.getDownloadFile1(payload, loginMode)
      }


    } else if (this.downloadForm.controls['documentValue'].value === 'TEACHING INFORMATION FORMAT(TIF)') {

      let payload = {
        aisheCode: aisheCode,
        surveyYear: this.downloadForm.controls['surveyYear'].value,
      }
      this.authService.getDownloadFile2(payload, loginMode)
    } else {
      let payload = {
        aisheCode: aisheCode,
        surveyYear: this.downloadForm.controls['surveyYear'].value,
      }
      this.authService.getDownloadFile3(payload, loginMode)
    }
  }
  getCertificate(aisheCode: any) {
    let payload = {
      aisheCode: aisheCode,
      surveyYear: this.downloadForm.controls['surveyYear'].value,
      institutionName: "",
      nodalOfficerName: this.nodalOfficerName
    }
    this.authService.getCertificateDownload(payload)
  }
  getSurveyYear() {
    this.authService.getSurveyYear().subscribe(res => {
      this.surveyYearList = []
      this.surveyYearList = res;
     this.surveyYearList = this.surveyYearList.filter(item => !['2010', '2024', '2025'].includes(item.surveyYear)).sort((a, b) => +a.surveyYear - +b.surveyYear);

    }, err => {

    })
    // }else{
    //   this.surveyYearList = [];
    //   this.surveyYearList.push({
    //     surveyYear:'2020',
    //     surveyYearValue:'2020-21'
    //   })
    //   this.surveyYearList.push({
    //     surveyYear:'2021',
    //     surveyYearValue:'2021-22'
    //   })
    // }

  }
}
