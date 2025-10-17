

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-web-dcf',
  templateUrl: './web-dcf.component.html',
  styleUrls: ['./web-dcf.component.scss']
})
export class WebDcfComponent implements OnInit {
  webDCF: FormGroup
  isFormInvalid: boolean = false
  hideButton: boolean = false
  isEligible: boolean = true
  showAisheCodeInputField: boolean = false

  surveyYear: any = []
  minlength: string = 'Please enter minimum 1 digit';
  required: boolean = false;
  messageEligible: boolean | undefined
  admin: boolean | undefined;
  userId: any;
  lsy: any;
  roleId: any;
  stateCode: any
  constructor(public authService: AuthService, public sharedService: SharedService, public fb: FormBuilder, public localService: LocalserviceService) {
    this.webDCF = this.fb.group({
      aisheCode: ['', []],
      institutionType: ['C', [Validators.minLength(1)]],
      surveyYear: ['', [Validators.required]]

    })
    this.sharedService.getUserDetails.subscribe(res => {
      if (res != 0) {
        this.userId = res.userId,
          this.roleId = res.roleId.toString()
        if (this.roleId !== this.sharedService.role['SysAdmin'] && this.roleId !== this.sharedService.role['State Nodal Officer']
          && this.roleId !== this.sharedService.role['Ministry of Education']) {
          this.admin = false
          // this.specialPermission()
          this.getSurveyYear();
        } else {
          this.admin = true
        }
      }
    })
    this.roleId = this.localService.getData('roleId')
    this.stateCode = this.localService.getData('stateCode')


    // else {
    //   this.applicable = false
    //   this.downloadForm.get('aisheCode')?.setValidators(Validators.required)
    // }
  }

  ngOnInit(): void {

    if (this.roleId === this.sharedService.role['SysAdmin'] || this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['State Nodal Officer']) {
      this.showAisheCodeInputField = true;
      this.webDCF.get('aisheCode')?.setValidators(Validators.required)
      this.webDCF.get('surveyYear')?.clearValidators()
      this.webDCF.get('surveyYear')?.updateValueAndValidity()
      this.webDCF.get('institutionType')?.setValidators(Validators.required)
      this.required = false
    } else {
      this.required = true

    }

    this.localService.getData('userId');
    this.localService.getData('lsy');
    this.localService.getData('minlsy')
    // this.sharedservice.setUserDetails(this.userDetails);

  }
  getSurveyYear() {
    this.authService.getSurveyYearListPrivate().subscribe(res => {
      let surveyYearList = res
      surveyYearList.forEach((element: any) => {
        if (element === this.sharedService.currentSurveyfic) {
          var splitSurvey = element.substring(0, 5)
          var a = element.substring(7, 9);
          this.surveyYear = [splitSurvey + a];
        }
      });
      if (this.surveyYear.length === 0) {
        this.specialPermission()
        // this.sharedService.alert().subscribe(res=>{
        //   if(res){

        //   }
        // })
      }

    }, err => {

    })
  }
  specialPermission() {
    let aisheCode = this.localService.getData('aisheCode')
    this.authService.findEligible(aisheCode, this.sharedService.currentSurveyYear).subscribe(res => {
      if (res) {
        if (res['Institute Details For CSY'].specialPermission) {
          this.surveyYear.push(this.sharedService.currentSurveyfiscal)
        } else {
          this.sharedService.alert(true)
        }
      }
    }, err => {

    })
  }
  selectSurveyYear(event: any) {
    let aisheCode = this.localService.getData('aisheCode')
    this.authService.findEligible(aisheCode, event).subscribe(res => {
      if (res) {
        if (res['Institute Details For CSY'].elligible !== 'Yes') {
          this.messageEligible = true;
          this.sharedService.showValidationMessage(`You are not Eligible for the survey year ${this.sharedService.currentSurveyfiscal}`);
          return;
        } else {
          this.messageEligible = false;
          this.isEligible = false
        }
        let lsy = res['Institute Details For CSY'].lsy
        this.lsy = lsy
        this.localService.saveData('surveyYear', lsy)
      }
    }, err => {

    })
  }
  checkAisheCode(data: any) {
    let aisheCode = data.institutionType + '-' + data.aisheCode
    if (this.webDCF.invalid) {
      this.sharedService.showValidationMessage('Please Enter AISHE Code');
      this.isFormInvalid = true;
      return
    } else {
      this.isFormInvalid = false;
    }
    this.authService.findEligible(aisheCode, this.sharedService.currentSurveyYear).subscribe(res => {
      if (res['Institute Details For CSY'] !== null) {
        if (res['Institute Details For CSY'].elligible !== 'Yes') {
          this.messageEligible = true;
          this.sharedService.showValidationMessage(`You are not Eligible for the survey year ${this.sharedService.currentSurveyfiscal}`);
          return;
        } else {
          this.messageEligible = false;
          this.isEligible = false
        }
        let lsy = res['Institute Details For CSY'].lsy
        this.lsy = lsy
        this.localService.saveData('surveyYear', lsy)
        if (this.sharedService.role['State Nodal Officer'] === this.roleId) {
          if (res['Institute Details For CSY']['stateCode'] === this.stateCode) {
            this.goToDCF(data)
          } else {
            this.sharedService.showValidationMessage('Unauthorized !!!');
            return;
          }
        } else {
          this.goToDCF(data)
        }
      } else {
        this.sharedService.showValidationMessage('There is no approved user for this institute !!!!')
        this.hideButton = false
      }
      // console.log("this.messageEligible", res)
    }, err => {

    })
  }
  // selectSurveyYear(event: any) {
  //     this.authService.findEligible().subscribe(res => {
  //       if (res && res.length) {
  //         if(res['0'].message !== 'Elligible'){
  //           this.messageEligible=true;
  //           this.sharedService.showValidationMessage('You are not Eligible for the survey year 2021-22');
  //           return;
  //         }else{
  //           this.messageEligible=false;
  //           this.isEligible=false
  //         }
  //         let lsy = res['0'].lsy
  //         let minlsy = res['0'].minlsy

  //           if(minlsy !== null){
  //             if(lsy === 2021){
  //               this.localService.saveData('surveyYear',minlsy)
  //             }else{
  //               this.localService.saveData('surveyYear',lsy)
  //             }
  //           }else{
  //             if(lsy === 2020){
  //               this.localService.saveData('surveyYear',lsy)
  //             }else{
  //               this.localService.saveData('surveyYear','2021')
  //             }
  //       }
  //     }
  //     }, err => {

  //     })
  // }

  // checkAisheCode(data: any) {
  //   let aisheCode = data.institutionType + '-' + data.aisheCode
  //     if (this.webDCF.invalid) {
  //       this.sharedService.showValidationMessage('Please Enter AISHE Code');
  //       this.isFormInvalid = true;
  //       return
  //     } else {
  //       this.isFormInvalid = false;
  //     }
  //     this.authService.checkAisheCode(aisheCode).subscribe(res => {
  //       if (res && res.length) {
  //         if(res['0'].message !== 'Elligible'){
  //           this.messageEligible=true;
  //           this.sharedService.showValidationMessage('You are not Eligible for the survey year 2021-22');
  //           return;
  //         }else{
  //           this.messageEligible=false;
  //           this.isEligible=false
  //         }
  //         let lsy = res['0'].lsy;
  //         let minlsy = res['0'].minlsy
  //         if(minlsy !== null){ 
  //           if(lsy === 2021){ 
  //             this.localService.saveData('surveyYear',minlsy)
  //           }else{
  //             this.localService.saveData('surveyYear',lsy)
  //           }
  //         }else{
  //           if(lsy === 2020){
  //             this.localService.saveData('surveyYear',lsy)
  //           }else{
  //             this.localService.saveData('surveyYear','2021')
  //           }
  //     }
  //         this.goToDCF(data)
  //       } else {
  //         this.sharedService.showValidationMessage('There is no approved user for this institute !!!!')
  //         this.hideButton = false
  //       }
  //     }, err => {

  //     })
  //   }



  goToDCF(data: any) {
    if (this.webDCF.invalid) {
      this.sharedService.showValidationMessage('Please Enter AISHE Code');
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    if (this.roleId !== this.sharedService.role['SysAdmin'] && this.roleId !== this.sharedService.role['State Nodal Officer']
      && this.roleId !== this.sharedService.role['Ministry of Education']) {
      // let surveyYear = data.surveyYear.substring(0, 4)
      window.open(`${environment.dcfURL}${this.localService.getData('aisheCode')}/30265/${this.lsy}/${this.sharedService.currentSurveyYear}/${this.userId}/${this.roleId}/${sessionStorage.getItem('encrypted')}`)
    } else {
      let aisheCode = data.institutionType + '-' + data.aisheCode
      window.open(`${environment.dcfURL}${aisheCode}/30265/${this.lsy}/${this.sharedService.currentSurveyYear}/${this.userId}/${this.roleId}/${sessionStorage.getItem('encrypted')}`)

    }
  }
  restrictNumeric(event: any) {
    return (event.charCode == 8 || event.charCode == 0) ? null : event.charCode >= 48 && event.charCode <= 57;
  }



  // validationCheck(event:any){
  //   if(event === 'S'){
  //     this.webDCF.get('institutionType')?.setValidators(Validators.minLength(1));
  //     this.webDCF.get('institutionType')?.updateValueAndValidity();
  //     this.minlength = 'Please enter minimum 1 digit'
  //   }else{
  //     this.webDCF.get('institutionType')?.clearValidators()
  //     this.webDCF.get('institutionType')?.setValidators(Validators.minLength(4));
  //     this.webDCF.get('institutionType')?.updateValueAndValidity();
  //     this.minlength = 'Please enter minimum 4 digit'
  //   }
  // }
}
