import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-nta',
  templateUrl: './nta.component.html',
  styleUrls: ['./nta.component.scss']
})
export class NtaComponent implements OnInit {
  q5_1List: Array<any> = []
  q5_2List: Array<any> = []
  q5List: Array<any> = []

  id: any = 0;
  isEligible: boolean = false
  "instituteName": string | null;
  "instituteType": string | null;
  aisheCode: string | null;
  "q2": any = null;
  "q3": any = null;
  "q4": any = null;
  "q5_1": number = 0;
  "q5_10": any = null;
  "q5_11": any = null;
  "q5_12": any = null;
  "q5_13": any = null;
  "q5_14": any = null;
  "q5_15": any = null;
  "q5_16": any = null;
  "q5_17": any = null;
  "q5_18": any = null;
  "q5_2": any = '';
  "q5_3": any = '';
  "q5_4": any = null;
  "q5_5": any = null;
  "q5_6": any = null;
  "q5_7": any = null;
  "q5_8": any = null;
  "q5_9": any = null;
  "q6_1": any = null;
  "q6_2": any = null;
  "q6_3": any = null;
  "q6_4": any = null;
  "q6_5": any = null;
  "q6_6": any = null;
  latitude: any;
  longitude: any
  question: boolean = false
  constructor(public sharedService: SharedService, public auth: AuthService, public localService: LocalserviceService) {
    this.latitude = this.localService.getData('latitude');
    this.longitude = this.localService.getData('longitude');
    this.instituteName = this.localService.getData('instituteName');
    this.instituteType = this.localService.getData('loginMode');
    this.aisheCode = this.localService.getData('aisheCode');
  }

  ngOnInit(): void {
    this.getQuestionAnswer()
  }
  modelChanged1(value: any) {
    if (value > 1) {
      this.q5_1List = []
      for (let index = 0; index < value; index++) {
        this.q5_1List.push({
          lab: 0
        })
        this.q5_2List.push({
          lab: 0
        })
      }
    } else {
      this.q5_1List = []
      this.q5_2List = []
    }
  }
  modelChanged2(value: any) {
    if (value > 1) {
      this.q5_2List = []
      for (let index = 0; index < value; index++) {
        this.q5_2List.push({
          lab: 0
        })
      }
    } else {
      this.q5_2List = []
    }
  }
  getQuestionAnswer() {
    this.auth.getNTAAnswer(this.localService.getData('aisheCode')).subscribe(res => {
      if (res.data.message === 'Success') {
        this.isEligible = res.data.participateInSurvey
        this.id = res.data.id;
        this.q2 = res.data.q2;
        this.q3 = res.data.q3;
        this.q4 = res.data.q4;
        this.q5_1 = res.data.q5_1;
        this.q5_10 = res.data.q5_10;
        this.q5_11 = res.data.q5_11;
        this.q5_12 = res.data.q5_12;
        this.q5_13 = res.data.q5_13;
        this.q5_14 = res.data.q5_14;
        this.q5_15 = res.data.q5_15;
        this.q5_16 = res.data.q5_16;
        this.q5_17 = res.data.q5_17;
        this.q5_18 = res.data.q5_18;
        this.q5_2 = res.data.q5_2;
        this.q5_3 = res.data.q5_3;
        this.q5_4 = res.data.q5_4;
        this.q5_5 = res.data.q5_5;
        this.q5_6 = res.data.q5_6;
        this.q5_7 = res.data.q5_7;
        this.q5_8 = res.data.q5_8;
        this.q5_9 = res.data.q5_9;
        this.q6_1 = res.data.q6_1;
        this.q6_2 = res.data.q6_2;
        this.q6_3 = res.data.q6_3;
        this.q6_4 = res.data.q6_4;
        this.q6_5 = res.data.q6_5;
        this.q6_6 = res.data.q6_6;
        this.q5List = res.data.common_q5_1;
        this.localService.saveData('latitude', res.data?.latitude);
        this.localService.saveData('longitude', res.data?.longitude);
      } if (res.message === 'NO RECORD EXIST') {
        this.isEligible = false
        let data = {
          nta: true
        }
        this.sharedService.savePopUp(data).subscribe(res => {
          if (res) {

          }
        })
      } if (res.data.message === 'Elligible') {
        this.isEligible = true
      }


    }, err => {

    })
  }
  save() {
    if (!this.latitude) {
      this.sharedService.showValidationMessage('Please enter latitude !!!')
      return;
    } if (!this.longitude) {
      this.sharedService.showValidationMessage('Please enter longitude !!!')
      return;
    }
    // this.auth.checkLatLong(this.longitude, this.latitude).subscribe(res => {

    // }, err => {

    // })

    var latitude = null;
    var longitude = null;

    if (this.latitude || this.longitude) {
      let c: boolean = this.validationLatLong(this.latitude, this.longitude);
      if (!c) {
        return;
      } else {
        latitude = this.latitude
        longitude = this.longitude
        this.save1(this.latitude, this.longitude)
      }
    }
  }
  validationLatLong(latitude: any, longitude: any) {
    // latitude 6-38
    // longitude 68 - 98
    if (latitude) {
      var lat = latitude.toString().split('.');
      let l = 0;
      if (lat[1]) {
        l = lat[1].length;
      }
      if (parseInt(lat[0]) < 6 || parseInt(lat[0]) > 38 || l < 5) {
        this.sharedService.showValidationMessage('Latitude [Range: 6.00000 - 38.00000] ,Values must contain minimum of 5 digits after the decimal point.')
        return false;
      }
    } if (longitude) {
      var long = longitude.toString().split('.');

      let lg = 0;

      if (long[1]) {
        lg = long[1].length;
      }
      if (parseInt(long[0]) < 68 || parseInt(long[0]) > 98 || lg < 5) {
        this.sharedService.showValidationMessage('Longitude [Range: 68.00000 - 98.00000] ,Values must contain minimum of 5 digits after the decimal point.')

        return false;
      }
    }

    return true;
  }
  save1(latitude: any, longitude: any) {
    if (this.q2 === null || this.q2 === undefined || this.q2 === '') {
      this.sharedService.showValidationMessage('Please choose serial no 2 !!!');
      return
    } if (this.q3 === null || this.q3 === undefined || this.q3 === '') {
      this.sharedService.showValidationMessage('Please choose serial no 3 !!!');
      return
    }
    for (let index = 0; index < this.q5List.length; index++) {
      if (!this.q5List[index].q5_2) {
        this.sharedService.showValidationMessage('Please enter serial no 5.2')
        return;
      } if (!this.q5List[index].q5_3) {
        this.sharedService.showValidationMessage('Please enter serial no 5.3')
        return;
      }

    }
    let payload = {
      participateInSurvey: this.isEligible,
      aisheCode: this.localService.getData('aisheCode'),
      "id": this.id,
      "instituteName": this.localService.getData('instituteName'),
      "instituteType": this.localService.getData('loginMode'),
      "q2": this.q2,
      "q3": this.q3,
      "q4": this.q4,
      "q5_1": this.q5_1,
      "q5_10": this.q5_10,
      "q5_11": this.q5_11,
      "q5_12": this.q5_12,
      "q5_13": this.q5_13,
      "q5_14": this.q5_14,
      "q5_15": this.q5_15,
      "q5_16": this.q5_16,
      "q5_17": this.q5_17,
      "q5_18": this.q5_18,
      "q5_4": this.q5_4,
      "q5_5": this.q5_5,
      "q5_6": this.q5_6,
      "q5_7": this.q5_7,
      "q5_8": this.q5_8,
      "q5_9": this.q5_9,
      "q6_1": this.q6_1,
      "q6_2": this.q6_2,
      "q6_3": this.q6_3,
      "q6_4": this.q6_4,
      "q6_5": this.q6_5,
      "q6_6": this.q6_6,
      common_q5_1: this.q5List,
      longitude: longitude,
      latitude: latitude
    }
    this.auth.saveNTAAnswer(payload).subscribe(res => {
      if (res.status === 200) {
        this.sharedService.showSuccess();
        this.getQuestionAnswer()
      }
    }, err => {

    })
  }
  reset() {

  }
  addRow() {
    if (this.q5List.length === 99) {
      this.sharedService.showValidationMessage('maximum 2 digits will be accepted');
      return
    }

    this.q5List.push({
      q5_2: '',
      q5_3: ''
    })
    this.q5_1++;

  }
  delete(index: number) {
    this.q5_1--
    this.q5List.splice(index, 1)
  }
  change3(value: any) {
    if (!value) {
      this.q4 = null
    }
  }
  change4(value: any) {
    if (value) {
      this.q5List.push({
        q5_2: '',
        q5_3: ''
      })
      this.q5_1++
      this.question = false;
      this.q6_1 = null,
        this.q6_2 = null,
        this.q6_3 = null,
        this.q6_4 = null,
        this.q6_5 = null,
        this.q6_6 = null
    } else {
      this.q5_1 = 0
      this.q5List = [];
      this.q5_10 = null,
        this.q5_11 = null,
        this.q5_12 = null,
        this.q5_13 = null,
        this.q5_14 = null,
        this.q5_15 = null,
        this.q5_16 = null,
        this.q5_17 = null,
        this.q5_18 = null,
        this.q5_4 = null,
        this.q5_5 = null,
        this.q5_6 = null,
        this.q5_7 = null,
        this.q5_8 = null,
        this.q5_9 = null
      this.question = true;
    }
  }
  change5_4(value: any) {
    if (!value) {
      this.q5_5 = null
    }
  }
  change5_8(value: any) {
    if (!value) {
      this.q5_9 = null
    }
  }
  change6_1(value: any) {
    if (!value) {
      this.q6_2 = null
    }
  }
  change6_3(value: any) {
    if (!value) {
      this.q6_4 = null
    }
  }
  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
  openpopUp() {
    this.sharedService.annexure()
  }
}
