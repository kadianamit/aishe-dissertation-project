import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from '../../service/auth.service';
import { SharedService } from '../../shared/shared.service';
import { routes } from '../../routes';
import { Menu } from '../../menu';
import { LocalserviceService } from '../../service/localservice.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {
  public routers: typeof routes = routes;
  showProfileBtn: boolean = false;
  showLoginButton: boolean = false;
  bilingualVal: any;
  langData: { langId: any; langName: any; bilingual: string; } | any;
  languages: any = [];
  defaultLanguage: any;
  langId: any;
  languageName: any;
  userDetails: any
  userId: any;
  name: any;
  mobileNo: any;
  email: any;
  aisheCode: any
  userType: any;
  param: any;
  count: number = 0;
  instituteNameWithAishe: string | null | undefined;
  welcomeMsg: string | undefined
  surveySub: any
  instituteName: any;
  lsy: any;
  roleId: any
  checkRoute: any
  roleName: any;
  stateName: any
  userData: any;
  menuList: Array<any> = [];
  selected: any = {};
  middleName: any;
  lastName: any;
  userDetailsSubs: any
  constructor(private route: ActivatedRoute, public authService: AuthService,
    public router: Router, public sharedService: SharedService,
    public dialog: MatDialog, public Cookie: CookieService, public menu: Menu, public localService: LocalserviceService) {


    this.roleId = this.localService.getData('roleId')
    this.userId = this.localService.getData('userId')
    this.userData = this.localService.getData('userData');

    this.checkRoute = window.location.href.split('/').includes("institutionDirectory");
    this.userDetailsSubs = this.sharedService.getUserDetails.subscribe(res => {
      if (this.userData !== 'true') {
        // this.getUserRoleMapping();
        if (res != 0) {
          this.userId = res.userId;
          if (this.roleId?.toString() === this.sharedService.role['SysAdmin'] || this.roleId?.toString() === this.sharedService.role['Ministry of Education'] || this.roleId?.toString() === this.sharedService.role['State Nodal Officer']) {
            //  this.middleName = this.localService.getData('middleName');
            //  this.lastName = this.localService.getData('lastName'); 
            this.name = res.name;
          } else {
            this.name = res.name
          }
          this.mobileNo = res.mobile;
          this.email = res.email;
          this.stateName = res.stateName,
            this.param = res.aisheCode;
          this.userType = this.localService.getData('userType');
          this.instituteName = res.instituteName;
          this.roleId = res.roleId
          if (this.roleId) {
            this.getuserRole()
          }
          // if (res.minlsy) {
          //   this.lsy = res.minlsy.toString()
          //   var splitSurvey = this.lsy.substring(2, 4)
          //   var intSurvey = parseInt(splitSurvey)
          //   var a = intSurvey + 1;
          //   this.surveySub = this.lsy + '-' + a;
          // }

          let lsy = res.lsy;
          let minlsy = res.minlsy
          if (minlsy !== null || minlsy !== undefined) {
            if (lsy === this.sharedService.currentSurveyYear) {
              this.lsy = res.minlsy.toString()
              var splitSurvey = this.lsy.substring(2, 4)
              var intSurvey = parseInt(splitSurvey)
              var a = intSurvey + 1;
              this.surveySub = this.lsy + '-' + a;
            } else {
              if (lsy !== null) {
                this.lsy = res.lsy.toString()
                var splitSurvey = this.lsy.substring(2, 4)
                var intSurvey = parseInt(splitSurvey)
                var a = intSurvey + 1;
                this.surveySub = this.lsy + '-' + a;
              }
            }
          } else {
            if (lsy !== null) {
              this.lsy = res.lsy.toString()
              var splitSurvey = this.lsy.substring(2, 4)
              var intSurvey = parseInt(splitSurvey)
              var a = intSurvey + 1;
              this.surveySub = this.lsy + '-' + a;
            }
          }
        } else {
          if (this.localService.getData('userId')) {
            this.authService.getUser(this.userId).subscribe((res: any) => {
              this.sharedService.global_loader = false;
              this.userDetails = {
                addressLine1: res.data.addressLine1,
                addressLine2: res.data.addressLine2,
                aisheCode: res.data.aisheCode,
                cityName: res.data.city,
                districtCode: res.data.districtId,
                districtName: res.data.districtName,
                email: res.data.email,
                name: res.data.name,
                //  middleName: res.data.middleName ? res.data.middleName : '',
                //  lastName: res.data.lastName ? res.data.lastName : '',
                instituteName: res.data.instituteName,
                lsy: res.data.lsy,
                minlsy: res.data.minlsy,
                mobile: res.data.mobile,
                roleId: res.data.roleId,
                stateCode: res.data.stateId,
                stateName: res.data.stateName,
                userId: res.data.userId,
                phoneLandline: res.data.phoneLandline,
                stdCode: res.data.stdCode,
                latitude: res.data.latitudee,
                longitude: res.data.longitudee,
                instituteHeadDesignation: res.data.instituteHeadDesignation,
                instituteHeadEmail: res.data.instituteHeadEmail,
                instituteHeadMobile: res.data.instituteHeadMobile,
                instituteHeadName: res.data.instituteHeadName,
                instituteHeadTelephone: res.data.instituteHeadTelephone,
                final: res.data?.finalSubmit
              }
              // this.roleId = res.data.roleId
              // if(this.roleId){
              //   this.getuserRole()
              // }
              this.sharedService.setUserDetails(this.userDetails)
            }, err => {
              this.sharedService.global_loader = false;
              this.sharedService.apiNotRespond()
            })
          }

        }
      }
      else {
        this.roleName = "Data User"
        this.userId = this.localService.getData('email');
        this.mobileNo = this.localService.getData('mobile')
        // let firstName = this.localService.getData('name')
        // let lastName = this.localService.getData('lastName')
          let lastName = this.localService.getData('lastName')
          let result = this.localService.getData('firstName')?.concat(" ", lastName ? lastName : '',)
        this.stateName = this.localService.getData('stateName')
        this.name = result
        this.email = this.localService.getData('email');
      }
    })
  }


  ngOnInit() {
  }
  getuserRole() {
    this.authService.getUserRole().subscribe(res => {
      if (res && res.length) {
        // this.sharedService.setRoleList(res)
        this.roleName = res.find((ele: any) => ele.roleId === this.roleId).roleName;
        this.localService.saveData('userType', this.roleName);
      }
    }, err => {

    })
  }
  // ngDoCheck() {
  //   if (sessionStorage.getItem('token')) {
  //    let test =  this.localService.getData('change');
  //    if(test === 'true'){
  //     this.showProfileBtn = false;
  //    }else{
  //     this.showProfileBtn = true;
  //    }
  //   }
  //   else {
  //     this.showProfileBtn = false;
  //   }
  // }


  changePassword() {
    this.sharedService.openChangePass(this.param)
  }
  ngOnDestroy(): void {
    this.userDetailsSubs?.unsubscribe()
  }

  logout() {
    this.authService.userLogout().subscribe(res => {
      if (res) {
        
        this.showProfileBtn = false
        this.userDetailsSubs?.unsubscribe()
        this.sharedService.getTokenInfoData('tokenExpired');

        if (this.localService.getData('userData') !== 'true') {
          this.router.navigate(['/institutionallogin'])

        } else {
          this.router.navigate(['/datauserlogin'])
        }

        this.localService.clearData();
        window.sessionStorage.clear();
        this.sharedService.showSuccessMessage('Logout Successfully');
        // this.Cookie.delete('countLogin');
      }
    }, err => {

    })


  }
  getUserRoleMapping() {
    this.authService.userRoleMapping().subscribe(res => {
      if (res && res.length) {
        res.forEach((item: any) => (
          item['allowedRolesList'] = []
        ));
        this.sharedService.setUserMapping(res)
      }
    }, err => {

    })
  }

}
