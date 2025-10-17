import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.scss']
})
export class FeedbackComponent implements OnInit {
  feedBack: FormGroup
  feedbackList: Array<any> = [];
  saveOrUpdate: string = 'Save';
  menuList: Array<any> = [];
  subMenuList: Array<any> = [];
  pageList: Array<any> = []
  allList: Array<any> = [];
  tempList: Array<any> = [];
  searchText: any = ''
  isFormInvalid: boolean = false
  constructor(public fb: FormBuilder, public authService: AuthService, public sharedService: SharedService, public localServivce: LocalserviceService) {
    this.feedBack = this.fb.group({
      'id': 0,
      'menu': ['', [Validators.required]],
      'submenu': ['', []],
      'page': ['', [Validators.required]],
      'remarks': ['', [Validators.required]],
      'genericordcf': [false, [Validators.required]]
    })
    this.getFeedBack();
    this.allList = this.sharedService.pageDetailsU;
    this.menuList = this.allList.filter((a, i) => this.allList.findIndex((s) => a.menuName === s.menuName) === i)
  }

  ngOnInit(): void {

  }
  getFeedBack() {
    let payload = {
      year: this.sharedService.currentSurveyYear,
      stateCode: this.localServivce.getData('stateCode'),
      roleId: this.localServivce.getData('roleId')
    }
    this.authService.getPageStatus(payload).subscribe(res => {
      if (res.data != null) {
        this.feedbackList = res.data;
        this.tempList = [...this.feedbackList]
      }
    }, err => {
    })
  }
  saveFeedBack(data: any) {
    if (!data.genericordcf) {
      if (this.feedBack.invalid) {
        this.sharedService.showWarning();
        this.isFormInvalid = true
        return;
      } else {
        this.isFormInvalid = false
      }
    } else {
      if (this.feedBack.controls['remarks'].value?.trim('') === '') {
        this.sharedService.showValidationMessage('Please enter remarks');
        return;
      }
    }
    if (data.remarks?.trim('').length < 20) {
      this.sharedService.showValidationMessage('Comments should not be less than 20 character !!!');
      return;
    }

    let payload = {
      "aishe_code": '',
      "id": data.id,
      "is_status": true,
      "menu": data.menu,
      "page": data.page,
      "remarks": data.remarks,
      "submenu": data.submenu,
      "survey_year": this.sharedService.currentSurveyYear,
      "stateCode": this.localServivce.getData('stateCode'),
      "roleId": this.localServivce.getData('roleId')
    }
    this.authService.saveFeedBackForm(payload).subscribe(res => {
      if (res.status === 200) {
        this.sharedService.showSuccess();
        this.getFeedBack();
        this.reset();
      }
    }, err => {
    })
  }
  edit(data: any) {
    this.feedBack.patchValue({
      "id": data.id,
      "menu": data.menu,
      "page": data.page,
      "remarks": data.remarks,
      "submenu": data.submenu,
      "genericordcf": data.menu ? false : true
    })
    this.saveOrUpdate = 'Update'
    this.subMenuList = this.allList.filter(e => e.menuName === data.menu)
    this.subMenuList = this.subMenuList.filter((a, i) => this.subMenuList.findIndex((s) => a.subMenu === s.subMenu) === i)
    this.pageList = this.allList.filter(e => e.subMenu === data.submenu && e.menuName === this.feedBack.controls['menu'].value)
  }
  reset() {
    this.feedBack.reset();
    this.saveOrUpdate = 'Save'
    this.feedBack.get('id')?.setValue(0)
  }

  getSubMenu(value: any) {
    this.feedBack.get('submenu')?.setValue('')
    this.feedBack.get('page')?.setValue('')
    this.subMenuList = this.allList.filter(e => e.menuName === value)
    this.subMenuList = this.subMenuList.filter((a, i) => this.subMenuList.findIndex((s) => a.subMenu === s.subMenu) === i)
  }
  getPageList(value: any) {
    this.feedBack.get('page')?.setValue('')
    this.pageList = this.allList.filter(e => e.subMenu === value && e.menuName === this.feedBack.controls['menu'].value)
  }
  onKeypressEvent(event: any, inputLength: any) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
  onChange(event: any) {
    this.sharedService.pageSize = parseInt(event);
    this.handlePageChange(this.sharedService.page = 1);
  }
  handlePageChange(event: any) {
    this.sharedService.page = event;
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize);
    var a = Math.ceil(this.feedbackList.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.feedbackList.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.feedbackList.length - 1);
    }
  }
  async updateResults() {
    this.feedbackList = []
    this.feedbackList = this.searchByValue(this.tempList);
    this.handlePageChange(this.sharedService.page = 1)
  }
  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.aishe_code?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.institutionName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.menu?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.submenu?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.page?.includes(this.searchText.trim().toLowerCase()))
          || (item.remarks?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.dateTime?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
}
