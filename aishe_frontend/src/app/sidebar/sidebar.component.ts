/* eslint-disable @angular-eslint/no-empty-lifecycle-method */
import { Component, OnInit, ElementRef } from '@angular/core';
import { routes, SharedService } from '../shared/shared.service';
import { SidebarService } from '../service/sidebar.service';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { LocalserviceService } from '../service/localservice.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {
  public routers: typeof routes = routes;
  loginMode: any;
  elementRef: ElementRef;
  sidebarData: any = [];
  sidebarList: any[] = [];
  roleWiseMenuData: any;
  toggle: any;
  userType: any;
  roleId: any;
  userData: any;
  showMenu: boolean = false
  surveyYear: any = [] = []
  aisheId: any;
  cmodule: any;
  smodule: any;
  umodule: any;
  showReport: boolean = false
  userId: string | null;
  datauser:boolean=false
  constructor(public sharedservice: SharedService, public sidebarService: SidebarService, public router: Router, elementRef: ElementRef,
    public authService: AuthService, public localService: LocalserviceService) {

    this.roleId = this.localService.getData('roleId')
    this.userData = this.localService.getData('userData')
    this.loginMode = this.localService.getData('loginMode')
    this.aisheId = this.localService.getData('aisheCode');
    this.userId = this.localService.getData('userId')
    this.sidebarService.setPath();
    this.elementRef = elementRef;
    if (this.userData === 'true') {
      this.userData = true
    } else {
      this.userData = false
    }
    this.sharedservice.getSidebarToggle.subscribe(res => {
      if (res == true) {
        this.toggle = true;
      }
      else {
        this.toggle = false;
      }
    });

    this.roleId = this.localService.getData('roleId');
    this.userType = this.localService.getData('userType')

    // this.sharedservice.getRoleList.subscribe(res => {
    //   if (res !== 0) {

    //     let array = res.filter((e: any) => e.levelId === 1 || e.levelId === 0)
    //     if(array.length !== 0){
    //       if()
    //       this.showReport=true
    //     }
    //   }
    // })

    if (this.roleId === this.sharedservice.role['College']
      || this.roleId === this.sharedservice.role['Polytechnic']
      || this.roleId === this.sharedservice.role['Nursing (Diploma) Institute']
      || this.roleId === this.sharedservice.role['Teacher Training (Diploma) Institute']
      || this.roleId === this.sharedservice.role['Standalone Institution Under Ministry']
      || this.roleId === this.sharedservice.role['PGDM']
      || this.roleId === this.sharedservice.role['Paramedical Institute']
      || this.roleId === this.sharedservice.role['Hotel Management and Catering Institute']
      || this.roleId === this.sharedservice.role['SysAdmin']
      || this.roleId === this.sharedservice.role['University']
      || this.roleId === this.sharedservice.role['Ministry of Education']
      || this.roleId === this.sharedservice.role['State Nodal Officer']
      || this.roleId === this.sharedservice.role['State Paramedical Council']
      || this.roleId === this.sharedservice.role['Pharmacy Institutions']
      || this.roleId === this.sharedservice.role['Instititions under Rehabilitation Council of India'])
      {
      this.showMenu = true
    }
    if ((this.roleId === this.sharedservice.role['State Nodal Officer'] || this.roleId === this.sharedservice.role['Ministry of Education'] || this.roleId === this.sharedservice.role['SysAdmin']) || this.roleId === this.sharedservice.role['University'] ) {
      this.datauser = true
    } if (this.userData) {
      this.datauser = true
    }
    this.getInstitutionManagementRole();
    // console.log(this.showMenu)
    // console.log(this.userData)
  }
  // ngOnInit() {
  // this.sharedservice.dataChange.subscribe(res => {
  //   this.roleWiseMenuData = res;
  //   this.sidebarData = res.menuDetails;
  //   this.getMenuData();
  // })
  // console.log(' ');
  // }
  param: any
  changePassword() {

    this.sharedservice.openChangePass(this.userId)
  }

  menuClicked(data: any) {
    // console.log(data);
    this.localService.saveData('menu', data.menu)
  }

  subMenuClicked(item: any) {
    // console.log(item);
    this.localService.saveData('subMenu', item.submenu)
  }



  getMenuData() {
    if (this.sidebarList.length) {
      this.sidebarList.length = 0;
    }

    if (this.sidebarData && this.sidebarData.length) {
      for (let i = 0; i < this.sidebarData.length; i++) {
        const fi = this.sidebarList.findIndex(ele => ele.menu == this.sidebarData[i].menu);
        if (fi >= 0) {
          this.sidebarList[fi].list.push(this.sidebarData[i]);
        } else {
          this.sidebarList.push({
            menu: this.sidebarData[i].menu,
            list: [this.sidebarData[i]]
          });
        }
      }
    }
  }

  newMenu() {
    if (this.elementRef.nativeElement.querySelectorAll('.show').length > 0) {
      this.elementRef.nativeElement.querySelectorAll('.show')[0].classList.remove("show");
    }
  }

  // toggle for report
  toggleReportAccordian() {
    $("nav-link").toggle();
    $("#collapseOne").toggle();
    if ($(".reportA").hasClass("collapsed")) {
      $(".reportA").removeClass("collapsed");
    } else {
      $(".reportA").addClass("collapsed");
    }
  }

  toggleReportAccordianTwo() {
    $("#collapseTwo").toggle();
    if ($(".reportC").hasClass("collapsed")) {
      $(".reportC").removeClass("collapsed");
    } else {
      $(".reportC").addClass("collapsed");
    }
    $(".hidedcf1").css("display", "none");
    $('.userM').css("display", "none");
    $('.reportF').css("display", "none");
    $('.reportD').css("display", "none");
  }
  toggleReportAccordianTwoM() {

    $("#collapseTwoM").toggle();
    if ($(".reportM").hasClass("collapsed")) {
      $(".reportM").removeClass("collapsed");
    } else {
      $(".reportM").addClass("collapsed");
    }
    $(".reportB").css("display", "none");
    $('.userM').css("display", "none");
    $('.reportF').css("display", "none");
    $('.reportD').css("display", "none");
  }
  toggleReportAccordianF() {
    $("#collapseTwoF").toggle();
    if ($(".reportF").hasClass("collapsed")) {
      $(".reportF").removeClass("collapsed");
    } else {
      $(".reportF").addClass("collapsed");
    }
    $(".reportB").css("display", "none");
    $('.reportD').css("display", "none");
    $('.userM').css("display", "none");
    $(".hidedcf1").css("display", "none");
  }

  toggleReportAccordianD() {
    $("#collapseTwoDE").toggle();
    if ($(".reportDE").hasClass("collapsed")) {
      $(".reportDE").removeClass("collapsed");
    } else {
      $(".reportDE").addClass("collapsed");
    }
    // $(".reportB").css("display", "none");
    // $('.userM').css("display", "none");
    // $(".hidedcf1").css("display", "none");
  }


  // toggle institute
  toggleSidebarTab() {
    $("nav-link").toggle();
    $(".reportA").addClass("collapsed");
    $(".hidedcf1").css("display", "none");
    $(".reportB").css("display", "none");
    $('.userM').css("display", "none");
    $('.reportD').css("display", "none");
  }
  toggleForAllUser(collapse: any, menu: any) {
    $("nav-link").toggle();
    $(collapse).toggle();
    if ($(menu).hasClass("collapsed")) {
      $(menu).removeClass("collapsed");
    } else {
      $(menu).addClass("collapsed");
    }
    $(".reportB").css("display", "none");
    $(".hidedcf1").css("display", "none");
    $('.reportF').css("display", "none");
    $('.reportD').css("display", "none");


  }
  toggleForAll(collapse: any, menu: any) {
    $("nav-link").toggle();
    $(collapse).toggle();
    if ($(menu).hasClass("collapsed")) {
      $(menu).removeClass("collapsed");
    } else {
      $(menu).addClass("collapsed");
    }
    $(".reportB").css("display", "none");
    $(".hidedcf1").css("display", "none");
    $('.userM').css("display", "none");
    $('.reportF').css("display", "none");
    $('.reportD').css("display", "none");
  }

  hidedropdown() {
    if (window.matchMedia('(max-width: 767px)').matches) {
      var element = document.getElementsByClassName('hidedcf1') as HTMLCollectionOf<HTMLElement>;
      element[0].style.display = 'none';

    }
  }
  getInstituteDetail() {
    if (this.roleId !== this.sharedservice.role['SysAdmin'] && this.roleId !== this.sharedservice.role['State Nodal Officer']
      && this.roleId !== this.sharedservice.role['Ministry of Education']) {
      let payload = {
        "aisheCode": this.aisheId
      }
      this.authService.getInstituteEditDetail(payload).subscribe((res) => {

        if (this.loginMode === 'C' || this.loginMode === 'S') {
          this.getManagementType(res, res[0]?.ownership?.id, res[0]?.management?.id)
        } else {
          this.getOwnershipStatus(res,res[0]?.ownership?.id)
        }


      });
    } else {
      this.router.navigate([this.routers.FillWEBDCF]);
    }
  }
  getSurveyYear() {
    //Check for instruction detail and profile detail

    if (this.roleId !== this.sharedservice.role['SysAdmin'] && this.roleId !== this.sharedservice.role['State Nodal Officer']
      && this.roleId !== this.sharedservice.role['Ministry of Education']) {
      this.authService.getSurveyYearList().subscribe(res => {
        let surveyYearList = res
        // if (surveyYearList['0'] === '2023-2024') {
        //   this.sharedservice.alert(true);
        //   return;
        // }
        surveyYearList.forEach((element: any) => {
          if (element === this.sharedservice.currentSurveyfic) {
            var splitSurvey = element.substring(0, 5)
            var a = element.substring(7, 9);
            this.surveyYear = [splitSurvey + a];
          }
        });
        if (this.surveyYear.length === 0) {
          let aisheCode = this.localService.getData('aisheCode')
          this.authService.findEligible(aisheCode, this.sharedservice.currentSurveyYear).subscribe(res => {
            if (res) {
              if (res['Institute Details For CSY'].specialPermission) {
                this.router.navigate([this.routers.FillWEBDCF]);
              } else {
                this.sharedservice.alert(true);
                return;
              }
            }
          }, err => {

          })
          // this.sharedservice.alert()
        } else {
          this.router.navigate([this.routers.FillWEBDCF]);
        }

      }, err => {

      })



    } else {
      this.router.navigate([this.routers.FillWEBDCF]);
    }
  }
  logout() {
    let ele = {
      logout: true
    }
    this.sharedservice.reLogin(ele)

  }
  getInstitutionManagementRole() {
    this.authService.getInstitutionManagementRole(this.roleId).subscribe((res) => {
      if (res) {
        this.sharedservice.setSidebarAccessibility(res)
        this.cmodule = res[0].cmodule;
        this.smodule = res[0].smodule;
        this.umodule = res[0].umodule;
      }
    });
  }
  getManagementType(data: any, ownership: any, managementId: any) {
    this.authService.getManagementTypeOwnershipData(this.loginMode).subscribe(res => {
      let managementTypeList = []
      managementTypeList = res;
      let array = managementTypeList.find((e: any) => e.id === managementId);
      // if (!array) {
      //   this.sharedservice.showValidationMessage('Please update Institution Detail!')
      //   return
      // }
      if (this.loginMode === 'C') {
        let ownershipStatusList = []
        ownershipStatusList = [...array.ownershipForClg]
        let owner = ownershipStatusList.find(o => o.id === ownership)
        if (!owner) {
          this.sharedservice.showValidationMessage('Please update Institution Detail!')
          return
        }
        this.validate(data)
      }else{
        this.getOwnershipStatus(data,ownership)
      }

    }, err => {

    })
  }
  getOwnershipStatus(data:any,ownership:any) {
    this.authService.getOwnership(this.loginMode).subscribe(res => {
      let ownershipStatusList = []
      ownershipStatusList = res;
      let owner = ownershipStatusList.find((o:any) => o.id === ownership)
      if (!owner) {
        this.sharedservice.showValidationMessage('Please update Institution Detail!')
        return
      }
      this.validate(data)
    }, err => {

    })
  }
  validate(res: any) {
    if(this.loginMode === 'C' || this.loginMode === 'S'){
      if(res['0']?.isOtherAffiliatingUniversityStatuatoryBody === null){
        this.sharedservice.showValidationMessage('Please update Institution Detail!')
        return
      }
    }
    if (res[0]?.locationId === "R") {
      if (res[0]?.blockId === null || res[0]?.blockId === '') {
        this.sharedservice.showValidationMessage('Please update Institution Detail and Edit Registration Detail!')
        return
      }
    }
    if (res[0]?.locationId === "U") {
      if (res[0]?.ulbId === null || res[0]?.ulbId === '') {
        this.sharedservice.showValidationMessage('Please update Institution Detail and Edit Registration Detail!')
        return
      }
    }
    if (res[0]?.subDistrict === null || res[0]?.subDistrict === '') {
      this.sharedservice.showValidationMessage('Please update Sub-District on Institution Detail page !')
      return
    }

    // const ta = res[0]?.area ? res[0]?.area : 0
    // const ca = res[0]?.constructedArea ? res[0]?.constructedArea * 0.00024711 : 0

    // if (ca > ta) {
    //   this.sharedservice.showValidationMessage('Total Constructed Area should be less than Total Area !!!');
    //   return;
    // }

    if (this.loginMode === 'U' || this.loginMode === 'S') {
      if (res[0]?.area === 0 || res[0]?.constructedArea === 0) {
        this.sharedservice.showValidationMessage('Total Area and Total Constructed Area should not be zero(0) !!!');
        return;
      }
    } else {
      if (res[0]?.typeId !== '2') {
        if (res[0]?.area === 0 || res[0]?.constructedArea === 0) {
          this.sharedservice.showValidationMessage('Total Area and Total Constructed Area should not be zero(0) !!!');
          return;
        }
      }
    }

    if (!(res[0]?.name &&
      res[0]?.instituteType &&

      res[0]?.ownership?.id &&


      res[0]?.headOfficerEmail &&
      res[0]?.headOfficerMobile &&
      res[0]?.headOfficerName &&
      res[0]?.nodalOfficerName &&
      res[0]?.nodalOfficerDesignation &&
      res[0]?.nodalOfficerEmail &&
      res[0]?.nodalOfficerMobile &&

      res[0]?.addressLine1 &&
      res[0]?.city &&
      res[0]?.state?.stateCode &&
      res[0]?.district?.distCode &&
      res[0]?.state?.name &&
      res[0]?.district?.name &&

      res[0]?.pinCode &&
      res[0]?.latitude &&
      res[0]?.longitude &&

      res[0]?.nodalOfficerName)) {
      this.sharedservice.showValidationMessage('Please update Institution Detail and Edit Registration Detail!')
      return
    } else {
      if (this.loginMode === 'C') {
        // if (!(res[0]?.management?.id && res[0]?.universityId)) {
        //   this.sharedservice.showValidationMessage('Please update Institution Detail and Edit Registration Detail!')
        //   return
        // } else {
          this.getSurveyYear();
        // }
      } if (this.loginMode === 'S') {
        // if (!(res[0]?.management?.id)) {
        //   this.sharedservice.showValidationMessage('Please update Institution Detail and Edit Registration Detail!')
        //   return
        // } 
        // else {
          this.getSurveyYear();
        // }
      } else {
        this.getSurveyYear();
      }
    }
  }
}

