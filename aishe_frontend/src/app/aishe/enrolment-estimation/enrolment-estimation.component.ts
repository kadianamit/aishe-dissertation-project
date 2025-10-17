import { Component, OnInit } from '@angular/core';
import { Observable, Subject, takeUntil } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-enrolment-estimation',
  templateUrl: './enrolment-estimation.component.html',
  styleUrls: ['./enrolment-estimation.component.scss']
})
export class EnrolmentEstimationComponent implements OnInit {
  unSubscribeSubject = new Subject()
  tempListData: Array<any> = [];
  listData: Array<any> = [];
  stateList: Array<any> = [];
  districtList: Array<any> = []
  stateCode: any = ''
  stateListArray: Array<any> = [];
  surveyYear: number = 2022;
  id: number = 0;
  showField: boolean = false
  editButton: boolean = false
  roleId: string | null;
  checked1: any;
  constructor(public authService: AuthService, public sharedService: SharedService,public localService:LocalserviceService) {
    this.roleId = this.localService.getData('roleId')
  }

  ngOnInit(): void {
    this.getState();
    this.getEnrolmentEst();
  }
  getState() {
    this.authService.getStatePrivate().subscribe(
      (res) => {
        if (res && res.length) {
          this.stateListArray = [];
          this.stateListArray = res;
          this.stateListArray = this.stateListArray.sort((a, b) => a.name > b.name ? 1 : -1)
          this.stateList = this.stateListArray.slice();

        }
      },
      (err) => { }
    );
  }
  getDistrictData(value: any) {
    let payload = {
      stateCode: value,
      surveyYear: this.surveyYear
    }
    this.authService.getDistrictWiseEnrol(payload).subscribe(
      (res) => {
        if (res['UG And PG ENROLLMENT'] && res['UG And PG ENROLLMENT'].length) {
          this.districtList = res['UG And PG ENROLLMENT'];
          this.districtList = this.districtList.map((v) => ({ ...v, checked: false }));
          if (this.editButton) {
            this.districtList.forEach(e => {
              this.listData.forEach(o => {
                o.districtCode.forEach((ele: any) => {
                  if (ele === e.districtCode) {
                    e['checked'] = true
                  }
                })
              })
            })
          }
        }
      },
      (err) => { }
    );
  }

  getEnrolmentEst() {
    let payload = {}
    if (this.sharedService.role['Ministry of Education'] === this.roleId || this.sharedService.role['SysAdmin'] === this.roleId) {
      payload = {
        surveYear: this.surveyYear,
        // staeCode:this.stateCode
      }
    } else {
      payload = {
        surveYear: this.surveyYear
      }
    }

    this.authService.getEnrolEstimation(payload).pipe(takeUntil(this.unSubscribeSubject)).subscribe({
      next: (res: any) => {
        if (res && res.length) {
          this.listData = res;
        }
      }, error: (error: any) => {

      }
    })
  }
  checkbox(e: any) {
    let data = e.target.checked;
    if (data) {
      this.districtList.forEach(e => {
        e['checked'] = true
      })
    } else {
      this.districtList.forEach(e => {
        e['checked'] = false
      })
    }
  }
  save() {
    const districtCode = [];
    if (this.id === 0) {
      let result = this.districtList.filter(o => this.listData.some((i) => o.state_code === i.state?.stateCode));
      if (result.length !== 0) {
        this.sharedService.showValidationMessage('Duplicate record not allowed !!!');
        return;
      }
    }

    for (let index = 0; index < this.districtList.length; index++) {
      if (this.stateCode === this.districtList[index].state_code) {
        if (this.districtList[index].checked) {
          districtCode.push(this.districtList[index].districtCode)
        }
      }

    }
    let payload = {
      "districtCode": districtCode,
      "id": this.id,
      "stateCode": this.stateCode,
      "surveyYear": this.surveyYear
    }
    this.authService.saveEnrolEstimation(payload).pipe(takeUntil(this.unSubscribeSubject)).subscribe({
      next: (res: any) => {
        if (res.status === 200) {
          this.sharedService.showSuccess()
          this.getEnrolmentEst();
          this.editButton = false
        }
      },
      error: (error: any) => {
        console.error('Error:', error);
      },
    })

  }
  close() {

  }
  addRecord(value: boolean) {
    this.showField = value
    this.editButton = false
    this.stateCode = ''
    this.districtList = []
  }
  edit(stateCode: any, id: any) {
    this.id = id
    this.editButton = true
    this.getDistrictData(stateCode);
    this.showField = true;
    this.stateCode = stateCode;

  }
  downloadReort() {
    const distCode = []
    for (let index = 0; index < this.listData.length; index++) {
      distCode.push(this.listData[index].districtCode)
    }
    let payload = {
      surveyYear: this.surveyYear,
      districtCode: distCode
    }
    this.authService.downalodEstimationReport(payload)
  }
}
