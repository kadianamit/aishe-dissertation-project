import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-approving-authority',
  templateUrl: './approving-authority.component.html',
  styleUrls: ['./approving-authority.component.scss']
})
export class ApprovingAuthorityComponent implements OnInit {
  roleId: any;
  stateCode: any;
  stateList: any[] = [];
  roleList: any[] = [];
  stateListArray: any[] = [];
  approvingAuthList: any[] = [];
  tempList: any[] = []
  searchText: string = '';
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  StartLimit: number = 0;
  EndLimit: number = 25;
  authorityId: any;
  universityId: any = null;
  universityList: any[] = [];
  universityListArray: any[] = [];
  authorityList: any[] = [];
  tempauthorityList: Array<any> = [];
  isUniversity: boolean = false;
  isAuthority: boolean = false;
  institutionType: any
  constructor(public authService: AuthService, public sharedService: SharedService) {
    // this.authorityList = this.sharedService.authorityList
    this.getState();
    this.getUserRole();

  }

  ngOnInit(): void {
  }
  getUserRole() {
    this.authService.getLevelAction('', true).subscribe(res => {
      this.tempauthorityList = res;
    }, err => {

    })
  }
  showType(value: any) {
    var authority = [...this.tempauthorityList];
    if (value === 'C') {
      this.authorityList = authority.filter((obj: any) => obj.roleId === parseInt(this.sharedService.role['College']))
      this.roleId = this.authorityList['0'].roleId
    } else if (value === 'U') {
      this.authorityList = authority.filter((obj: any) => obj.roleId === parseInt(this.sharedService.role['University']))
      this.roleId = this.authorityList['0'].roleId
    } else {
      const i = authority.findIndex((obj: any) => obj.roleId === parseInt(this.sharedService.role['College']));
      if (i !== -1) {
        authority.splice(i, 1)
      }
      const j = authority.findIndex((obj: any) => obj.roleId === parseInt(this.sharedService.role['University']));
      if (j !== -1) {
        authority.splice(j, 1)
      }
      this.roleId = ''
      this.authorityList = authority
    }
    this.showUniversity(this.roleId)
  }
  showUniversity(roleId: any) {
    this.stateCode = ''
    if (Number(this.sharedService.role['College']) === roleId) {
      this.isUniversity = true;

    } else {
      this.isUniversity = false;
    }
  }
  getUniversity(stateCode: any) {
    this.universityId = ''
    this.authService.getUniversityList(stateCode).subscribe(res => {
      if (res && res.length) {
        this.universityListArray = [];
        this.universityListArray = res;
        this.universityListArray = this.universityListArray.sort((a, b) => a.name > b.name ? 1 : -1);
        this.universityList = this.universityListArray.slice();
      }
    }, err => {
      this.universityList = [];
      this.universityListArray = []
    })

  }
  getState() {
    this.authService.getState().subscribe(
      (res) => {
        if (res && res.length) {
          this.stateListArray = [];
          this.stateListArray = res;
          this.stateListArray = this.stateListArray.sort((a, b) => a.name > b.name ? 1 : -1);
          this.stateList = this.stateListArray.slice();
        }
      },
      (err) => { }
    );
  }
  getData() {
    this.approvingAuthList = [];
    this.tempList = []
    let universityId = null
    if (!this.roleId) {
      this.sharedService.showValidationMessage('Please select Sub-Type !!!');
      return;
    } 
    if (this.roleId !== parseInt(this.sharedService.role['Standalone Institution Under Ministry'])) {
      if (!this.stateCode) {
        this.sharedService.showValidationMessage('Please select state !!!')
        return;
      }
    } else {
      this.stateCode = 0;
    }
    this.authorityId = this.sharedService.authorityList.find(ele => parseInt(ele.id) === this.roleId)?.authorityId;
    if (!this.isUniversity) {
      this.universityId = null
    } else {
      universityId = 'U-' + this.universityId
    }
    if (this.roleId === parseInt(this.sharedService.role['College'])) {
      if (!this.universityId) {
        this.sharedService.showValidationMessage('Please select university !!!');
        return;
      }

    }
    this.authService.getApprovingAuth(this.roleId, this.stateCode, universityId).subscribe(res => {
      this.approvingAuthList = res;
      this.isAuthority = true;
      this.approvingAuthList.forEach(element => {
        if(element.middleName === null){
          element.middleName=''
        }
        if(element.lastName === null){
          element.lastName=''
        }
          element['middleName'] = element.middleName ? element.middleName : '',
          element['lastName'] = element.lastName ? element.lastName : element.lastName,
          element['landline'] = element.landline ? element.landline : '',
          element['stdCode'] = element.stdCode ? element.stdCode : ''
        element['fullName'] = element.firstName.concat(" ", element.middleName, "", element.lastName);
        if (element.stdCode && element.landline) {
          element['phoneLandline'] = element.stdCode + '-' + element.landline
        }
        element.emailId =  element.emailId.replace(/[@.]/g, (m:any)=>m === '@'?'[at]':m === '.'?'[dot]':'')
        
      });
      this.tempList = [...this.approvingAuthList]
    }, err => {

    })
  }
  reset() {
    this.institutionType = '';
    this.universityId = '';
    this.roleId = '';
    this.stateCode = ''
    this.approvingAuthList = [];
    this.isAuthority = false;
  }
}
