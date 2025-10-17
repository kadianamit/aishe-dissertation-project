import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SharedService } from 'src/app/shared/shared.service';
import { DomSanitizer } from '@angular/platform-browser';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { MatSelectChange } from '@angular/material/select';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { filter } from 'rxjs';
@Component({
  selector: 'app-prefilled-details',
  templateUrl: './prefilled-details.component.html',
  styleUrls: ['./prefilled-details.component.scss']
})
export class PrefilledDetailsComponent implements OnInit {

  count: number = 0
  imgsrc = '/assets/img/avatar.png'
  userDetail: FormGroup;
  editReg!: FormGroup;
  detailsSubscription: any
  addressLine1: any;
  addressLine2: any;
  aisheCode: any;
  bcryptPassword: any;
  cityName: any;
  districtCode: any;
  districtName: any;
  email: any;
  firstName: any;
  instituteName: any;
  lsy: any;
  mobile: any;
  gender:any;
  roleId: any;
  stateCode: any;
  stateName: any;
  userId: any;
  userType: any;
  isVisible: any = null
  userData: string | null;
  pincodeRegex: RegExp = /^[1-9]{1}[0-9]{5}$/;
  phonePattern = /^[0-9]{10,10}$/;
  pattLog = /^[0-9]+(?:\.[0-9]+)?$/;
  emailRegex: RegExp = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
  websitePattern: RegExp = /^(http[s]?:\/\/){0,1}(www\.){0,1}[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}[\.]{0,1}/
  aisheId: any;
  instituteCategory: string | null;
  userEmail: any;
  userMobile: any;
  universityList: any[] = [];
  isFormInvalid: boolean | undefined;
  emailVerified: boolean = false;
  mobileVerified: boolean = false;
  blockList: Array<any> = []
  variable: Array<any> = [];
  urbanBodyList: Array<any> = [];
  variable1: Array<any> = [];
  filterAffVariable: Array<any> = [];
  managementTypeList: Array<any> = [];
  selectedAreas: Array<any> = [];
  ownershipStatusList: Array<any> = [];
  stateList: Array<any> = []
  districtList: Array<any> = []
  affilatedUniArr: Array<any> = []
  statutoryArr: Array<any> = []
  selectedBodies: Array<any> = []
  affilatedUniversityEditArr: any = []
  statutoryEditArr: any = []
  affilatedValue: any = [];
  statuatoryBodyValue: any = [];
  searchTerm: string = '';
  msg: string = ''
  // lastName: any;
  // middleName: any;
  showInUniv: boolean;
  showInCollege: boolean;
  showInStandalone: boolean;
  finalSubmit: any;
  lastName: any;
  middleName: any;
  showPrefilledDetails: boolean | undefined
  name: any;
  hideButton: boolean | undefined;
  latLongToken: any;
  stateLgdId: any;
  districtLgdId: any;
  blockLgdId: any;
  subDistrictLgd: any;
  isGeospatialDataVerified: boolean = false;
  subDistrictList: Array<any> = [];
  variable2: Array<any> = []
  islgdDistrictVerified: boolean = false;
  isLgdStateVerified: boolean = false;
  isLgdSubDistrictVerified: boolean = false
  isBisagVerified: boolean = false
  filteredStatutoryArr: Array<any> = []
  statutoryValueArr: Array<any> = []
  affilatedValueArr: Array<any> = []
  body: Array<any> = []
  selectedStatutoryIds: Array<any> = []
  ministryArr: Array<any> = []
  userTypeId: any;
  affListVisibleArray: any = []
  statutoryListVisibleArray: any = []
  itsChekcBox: any;
  itsSearchValueUncheck: any;
  otherAffiliatingUniversityRadio: any;
  ministryList:Array<any>=[]
  universityArr:Array<any>=[]
  constructor(public sharedService: SharedService, public fb: FormBuilder, public router: Router, public _d: DomSanitizer, public auth: AuthService,
    public localService: LocalserviceService) {
    // this.aisheId = this.localService.getData('aisheCode');
    // this.roleId = this.localService.getData('roleId');
    // this.finalSubmit = this.localService.getData('final')
    this.instituteCategory = this.localService.getData('loginMode');
    if (this.instituteCategory === "C") {
      this.showInUniv = false;
      this.showInCollege = true;
      this.showInStandalone = false;
    }
    else if (this.instituteCategory === "S") {
      this.showInUniv = false;
      this.showInCollege = false;
      this.showInStandalone = true;
    }
    else {
      this.showInUniv = true;
      this.showInCollege = false;
      this.showInStandalone = false;
    }
    this.editReg = this.fb.group({
      id: 0,
      name: { value: '', disabled: true },
      instituteType: { value: '', disabled: true },
      typeId: ['', []],
      managementType: { value: '', disabled: true },
      ownershipType: ['', [Validators.required]],
      directorName: ({ value: '', disabled: true }),
      directordesignation: ({ value: '', disabled: true }),
      directorEmail: ({ value: '', disabled: true }),
      directorMobile: ({ value: '', disabled: true }),
      directorTelephone: ({ value: '', disabled: true }),
      nodalOfficerName: ({ value: '', disabled: true }),
      designation: ({ value: '', disabled: true }),
      email: ({ value: '', disabled: true }),
      mobileNo: ({ value: '', disabled: true }),
      landLine: ({ value: '', disabled: true }),
      location: [''],
      addLine1: ['', [Validators.required]],
      addLine2: ['', []],
      city: ['', [Validators.required]],
      country: [{ value: 'India', disabled: true }],
      stateCode: [{ value: '', disabled: true }],
      districtCode: [{ value: '', disabled: true }],
      subDistrictId: ['', [Validators.required]],
      blockId: [''],
      ulbId: [''],
      pinCode: ['', [Validators.required, Validators.pattern(this.pincodeRegex)]],
      longitude: ['', [Validators.required]],
      latitude: ['', [Validators.required]],
      aisheCode: [{ value: '', disabled: true }],
      website: ['', [Validators.pattern(this.websitePattern)]],
      stateName: { value: '', disabled: true },
      districtName: { value: '', disabled: true },
      area: ['', [Validators.required]],
      constructedArea: ['', Validators.required],
      emailOtp: [''],
      mobileOtp: ['', []],
      universityId: [{ value: '', disabled: true }],
      // nodalOfficerTitleId:({value:'',disabled:true}),
      // headOfficerTitleId:({value:'',disabled:true}),
      nodalOfficerTitle: ({ value: '', disabled: true }),
      headOfficerTitle: ({ value: '', disabled: true }),
      statuatoryBody: [{ value: '' }],
      affilatedUniArrBody: [[]],
      otherAffiliatingUniversity: [{ value: '' }],
      ministryId: [''],
      isOtherAffiliatingUniversityStatuatoryBody: ['']


    })
    this.userDetail = this.fb.group({
      addressLine1: ({ value: '', disabled: true }),
      addressLine2: ({ value: '', disabled: true }),
      aisheCode: ({ value: '', disabled: true }),
      bcryptPassword: ({ value: '', disabled: true }),
      cityName: ({ value: '', disabled: true }),
      districtCode: ({ value: '', disabled: true }),
      districtName: ({ value: '', disabled: true }),
      email: ({ value: '', disabled: true }),
      firstName: ({ value: '', disabled: true }),
      name: ({ value: '', disabled: true }),
      instituteName: ({ value: '', disabled: true }),
      lsy: ({ value: '', disabled: true }),
      mobile: ({ value: '', disabled: true }),
      genderName: ({ value: '', disabled: true }),
      roleId: ({ value: '', disabled: true }),
      stateCode: ({ value: '', disabled: true }),
      stateName: ({ value: '', disabled: true }),
      userId: ({ value: '', disabled: true }),
      userType: ({ value: '', disabled: true }),
      countryName: ({ value: 'India', disabled: true })
    })
    this.userData = this.localService.getData('userData');
    if (this.userData !== 'true') {
      if (this.showInCollege || this.showInStandalone) {
        this.editReg.get('managementType')?.setValidators(Validators.required)
        this.editReg.get('managementType')?.updateValueAndValidity()
      }



      this.detailsSubscription = this.sharedService.getUserDetails.subscribe(res => {
        if (res != 0) {
          this.roleId = res.roleId.toString()
          this.aisheId = res.aisheCode
          if (this.roleId) {
            this.getuserRole()
          }
          if (this.instituteCategory === 'C' || this.instituteCategory === 'S' || this.instituteCategory === 'U') {
            this.editReg.get('aisheCode')?.setValue(this.aisheId)
            this.finalSubmit = res.final
            this.showPrefilledDetails = false
            if (this.instituteCategory === 'C' || this.instituteCategory === 'S') {
              this.getManagementType();
            } else {
              this.getOwnershipStatus();

            }

            this.getUniversity();
            this.getToken()
            this.getInstituteDetail();


          } else {
            this.showPrefilledDetails = true
            this.userDetail.patchValue(res);
            // this.firstName = res.firstName;
            // this.lastName = res.lastName;
            // this.middleName = res.middleName;
            // let result = res.firstName.concat(" ", res.middleName ? res.middleName : '', " ", res.lastName ? res.lastName : '');
            this.userDetail.get('name')?.setValue(res.name)
            this.userType = this.localService.getData('userType');
            this.userDetail.get('userType')?.setValue(this.userType)
          }
        }
      })

    } else {
      this.showPrefilledDetails = true
      this.userType = this.localService.getData('userTypeName');
      this.userDetail.get('userType')?.setValue(this.userType)
      // console.log('dhanjay', this.userDetail.get('userType'));
      let lastName = this.localService.getData('lastName')
      let result = this.localService.getData('firstName')?.concat(" ", lastName ? lastName : '',)
      let user = {
        name: result,
        userId: this.localService.getData('email'),
        mobile: this.localService.getData('mobile'),
        genderName:this.localService.getData('genderName'),
        email: this.localService.getData('email'),
        stateName: this.localService.getData('stateName'),
        districtName: this.localService.getData('districtName'),
        countryName: this.localService.getData('countryName')
      }
      this.userDetail.patchValue(user);
    }


  }

  ngOnInit() {



    if (!sessionStorage.getItem('token'))
      this.router.navigate(['/'])
  }

  ngOnDestroy(): void {
    if (this.detailsSubscription) {
      this.detailsSubscription.unsubscribe()
    }
  }

  fileChange(e: any) {
    const file = e.srcElement.files[0];
    this.imgsrc = window.URL.createObjectURL(file);

  }
  getInstituteDetail() {
    let payload = {
      "aisheCode": this.aisheId
    }
    this.auth.getInstituteEditDetail(payload).subscribe((res: any) => {
      this.subDistrictLgd = res['0']?.subDistrict?.id
      if (!this.subDistrictLgd) {
        let ele = {
          savePrefilled: false,
          showState: false,
          updateSubDis: true
        }
        this.sharedService.errorMessage(ele);
      }

      // this.editReg.get('nodalOfficerTitleId')?.setValue(res[0]?.nodalOfficerTitleId?.id)
      // this.editReg.get('headOfficerTitleId')?.setValue(res[0]?.headOfficerTitleId?.id)
      // this.editReg.get('nodalOfficerTitle')?.setValue(res[0]?.nodalOfficerTitleId?.name)
      // this.editReg.get('headOfficerTitle')?.setValue(res[0]?.headOfficerTitleId?.name)
      this.editReg.get('name')?.setValue(res[0]?.name)
      this.editReg.get('id')?.setValue(res[0]?.id)
      this.editReg.get('instituteType')?.setValue(res[0]?.instituteType);
      this.editReg.get('typeId')?.setValue(res[0]?.typeId);
      this.userTypeId = res[0]?.typeId;
      this.editReg.get('managementType')?.setValue(res[0]?.management?.id);
      // this.ownershipType(res[0]?.management?.id)
      this.editReg.get('ownershipType')?.setValue(res[0]?.ownership?.id);

      this.editReg.get('directordesignation')?.setValue(res[0]?.headOfficerDesignation);
      this.editReg.get('directorEmail')?.setValue(res[0]?.headOfficerEmail);
      this.editReg.get('directorMobile')?.setValue(res[0]?.headOfficerMobile);
      this.editReg.get('directorName')?.setValue(res[0]?.headOfficerName);
      this.editReg.get('directorTelephone')?.setValue(res[0]?.headOfficerTelephone);

      this.editReg.get('nodalOfficerName')?.setValue(res[0]?.nodalOfficerName);
      this.editReg.get('designation')?.setValue(res[0]?.nodalOfficerDesignation);
      this.editReg.get('email')?.setValue(res[0]?.nodalOfficerEmail);
      this.editReg.get('mobileNo')?.setValue(res[0]?.nodalOfficerMobile);
      this.editReg.get('landLine')?.setValue(res[0]?.nodalOfficerTelephone);

      this.editReg.get('location')?.setValue(res[0]?.locationId);

      this.editReg.get('addLine1')?.setValue(res[0]?.addressLine1);
      this.editReg.get('addLine2')?.setValue(res[0]?.addressLine2);
      this.editReg.get('city')?.setValue(res[0]?.city);
      this.editReg.get('country')?.setValue('INDIA');
      this.editReg.get('stateId')?.setValue(res[0]?.state?.stateCode);
      this.stateCode = res[0]?.state?.stateCode
      this.editReg.get('districtCode')?.setValue(res[0]?.district?.distCode);
      this.districtName = res[0]?.district?.name
      this.editReg.get('stateCode')?.setValue(res[0]?.state?.stateCode);
      this.stateName = res[0]?.state?.name
      this.editReg.get('stateName')?.setValue(res[0]?.state?.name);
      this.editReg.get('districtName')?.setValue(res[0]?.district?.name);
      this.editReg.get('blockId')?.setValue(res[0]?.blockId?.id);
      this.editReg.get('ulbId')?.setValue(res[0]?.ulbId?.id);
      this.editReg.get('pinCode')?.setValue(res[0]?.pinCode);
      this.editReg.get('latitude')?.setValue(res[0]?.latitude);
      this.editReg.get('longitude')?.setValue(res[0]?.longitude);
      this.editReg.get('area')?.setValue(res[0]?.area);
      this.editReg.get('constructedArea')?.setValue(res[0]?.constructedArea);
      this.editReg.get('website')?.setValue(res[0]?.website);
      this.editReg.get('universityId')?.setValue(res[0]?.universityId);
      this.editReg.get('subDistrictId')?.setValue(res[0]?.subDistrict?.id)
      this.isLocation(res[0]?.locationId);
      this.name = res[0]?.nodalOfficerName;
      this.stateLgdId = res['0']?.state.lgdCode,
        this.districtLgdId = res['0']?.district.lgdDistCode
      let filterStatuatoryId = res[0]?.statuatoryBody?.map((item: any) => item.id)
      let filterAffId = res[0]?.otherAffiliatingUniversity?.map((item: any) => item.affiliatingUnivId)
      this.editReg.get('statuatoryBody')?.setValue(filterStatuatoryId)
      this.editReg.get('affilatedUniArrBody')?.setValue(filterAffId)
      this.editReg.get('ministryId')?.setValue(res[0]?.ministryId?.id)
      this.editReg.get('isOtherAffiliatingUniversityStatuatoryBody')?.setValue(res[0]?.isOtherAffiliatingUniversityStatuatoryBody),

        this.isVisible = res[0]?.isOtherAffiliatingUniversityStatuatoryBody == true ? true : false
      this.otherAffiliatingUniversityRadio = res[0]?.isOtherAffiliatingUniversityStatuatoryBody;


      this.affilatedUniversityEditArr = res[0]?.otherAffiliatingUniversity
      this.statutoryEditArr = res[0]?.statuatoryBody
      this.affListVisibleArray = res[0]?.otherAffiliatingUniversity
      this.statutoryListVisibleArray = res[0]?.statuatoryBody
      if (res['0']?.locationId === 'R') {
        this.blockLgdId = res['0']?.blockId?.lgdBlockCode
      }


      // let nameDetail=res[0]?.nodalOfficerName.split(" ");
      // this.firstName=nameDetail[0];
      // this.middleName=nameDetail[1];
      // this.lastName=nameDetail[2];
      this.editReg.disable()
      // if (this.finalSubmit) {
      //    this.editReg.disable()
      // }
      this.isGeospatialDataVerified = res[0]?.isGeospatialDataVerified;
      this.isBisagVerified = res['0']?.isBisagVerified,
        this.islgdDistrictVerified = res[0]?.islgdDistrictVerified;
      this.isLgdStateVerified = res['0']?.isLgdStateVerified;
      this.isLgdSubDistrictVerified = res['0']?.isLgdSubDistrictVerified
      if (this.isBisagVerified) {
        this.editReg.get('latitude')?.disable();
        this.editReg.get('longitude')?.disable();
        this.editReg.get('latitude')?.updateValueAndValidity();
        this.editReg.get('longitude')?.updateValueAndValidity();
      }
      this.getSubDistrict()
      this.getAffilatedUni()
      this.statutoryBody()
      this.ministryName();
      this.ownershipType(res[0]?.management?.id)
    });

  }
  getSubDistrict() {
    let payload = {
      lgdStateCode: this.stateLgdId,
      lgdDistrictCode: this.districtLgdId
    }
    this.auth.getSubDistrictList(payload).subscribe(res => {
      this.variable2 = res;
      this.subDistrictList = this.variable2.slice();
      if (this.subDistrictList.length === 0) {
        this.subDistrictList.push({ id: 99999, name: 'Not Applicable' })
      }
    }, err => {

    })
  }
  getToken() {
    this.auth.latLongToken().subscribe(res => {
      this.latLongToken = res.token;
    }, err => {

    })
  }

  validateLatitudeLongitude() {
    if (this.editReg.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false
    }
    // const ta = this.editReg.controls['area'].value ? this.editReg.controls['area'].value : 0
    // const ca = this.editReg.controls['constructedArea'].value ? this.editReg.controls['constructedArea'].value * 0.00024711 : 0

    // if (ca > ta) {
    //   this.sharedService.showValidationMessage('Total Constructed Area should be less than Total Area !!!');
    //   return;
    // }
    if (this.instituteCategory === 'U' || this.instituteCategory === 'S') {
      if (this.editReg.controls['area'].value === '0' || this.editReg.controls['constructedArea'].value === '0') {
        this.sharedService.showValidationMessage('Total Area and Total Constructed Area should not be zero(0) !!!');
        return;
      }
    } else {
      if (this.editReg.controls['typeId'].value !== '2') {
        if (this.editReg.controls['area'].value === '0' || this.editReg.controls['constructedArea'].value === '0') {
          this.sharedService.showValidationMessage('Total Area and Total Constructed Area should not be zero(0) !!!');
          return;
        }
      }
    }

    var latitude = null;
    var longitude = null;
    if (!this.isBisagVerified) {
      if (this.editReg.controls['latitude'].value || this.editReg.controls['longitude'].value) {
        let c: boolean = this.validationLatLong(this.editReg.controls['latitude'].value, this.editReg.controls['longitude'].value);
        if (!c) {
          return;
        } else {
          latitude = this.editReg.controls['latitude'].value
          longitude = this.editReg.controls['longitude'].value
        }
      }
    }

    if (!this.isBisagVerified && (this.latLongToken)) {
      let payload = {
        lat: latitude,
        lon: longitude
      }
      this.auth.validateLatLong(payload, this.latLongToken).subscribe(res => {
        if (res.stateId && res.districtId && res.talukaId) {
          if (res.stateId === '00') {
            this.count++
            if (this.count === 2) {
              this.count = 0
              this.saveEditReg();
              return;
            }
          }
          this.isLgdStateVerified = parseInt(res.stateId) === parseInt(this.stateLgdId) ? true : false
          this.islgdDistrictVerified = parseInt(res.districtId) === parseInt(this.districtLgdId) ? true : false
          this.isLgdSubDistrictVerified = parseInt(res.talukaId) === this.editReg.controls['subDistrictId'].value ? true : false



          if (this.editReg.controls['subDistrictId'].value !== 99999) {
            let subDistrict = this.subDistrictList.find(e => e.id === this.editReg.controls['subDistrictId'].value).name
            if ((parseInt(res.stateId) !== this.stateLgdId || parseInt(res.districtId) !== this.districtLgdId || parseInt(res.talukaId) !== this.editReg.controls['subDistrictId'].value)) {
              this.count++;
              if (this.count === 1) {
                let ele = {
                  showState: true,
                  stateName: res.state,
                  districtName: res.district,
                  subDistrict: res.taluka,
                  latitude: payload.lat,
                  longitude: payload.lon,
                  enteredState: this.editReg.controls['stateName'].value,
                  enteredDistrict: this.editReg.controls['districtName'].value,
                  enterdSubDistrict: subDistrict
                }
                this.sharedService.errorMessage(ele);
              } else {
                this.isGeospatialDataVerified = false
                this.count = 0
                this.saveEditReg();
                return;
              }

            } else {
              this.isGeospatialDataVerified = true
              this.count = 0
              this.saveEditReg();
              return;
            }
          } else {
            if ((parseInt(res.stateId) !== this.stateLgdId || parseInt(res.districtId) !== this.districtLgdId)) {
              this.count++;
              if (this.count === 1) {
                let ele = {
                  showState: true,
                  stateName: res.state,
                  districtName: res.district,
                  subDistrict: res.taluka,
                  latitude: payload.lat,
                  longitude: payload.lon,
                  enteredState: this.editReg.controls['stateName'].value,
                  enteredDistrict: this.editReg.controls['districtName'].value,
                  enterdSubDistrict: ''
                }
                this.sharedService.errorMessage(ele);
              } else {
                this.isGeospatialDataVerified = false
                this.count = 0
                this.saveEditReg();
                return;
              }
            } else {
              this.count = 0
              this.isGeospatialDataVerified = true
              this.saveEditReg();
              return;
            }
          }

        }

        // if api return {} object.
        if (this.isBlankObject(res)) {
          this.count++
          if (this.count === 1) {
            let ele = {
              savePrefilled: true
            }
            this.sharedService.errorMessage(ele);
          } else {
            this.count = 0
            this.saveEditReg()
          }
        }

      }, err => {
        this.count++
        if (this.count === 1) {
          let ele = {
            savePrefilled: true
          }
          this.sharedService.errorMessage(ele);
        } else {
          this.count = 0
          this.saveEditReg()
        }
      })
    } else {
      this.saveEditReg();
    }
  }
  isBlankObject(obj: any): boolean {
    for (let prop in obj) {
      if (obj.hasOwnProperty(prop)) {
        return false;
      }
    }
    return true;
  }

  saveEditReg() {
    let ownership = this.ownershipStatusList.find(o => o.id === this.editReg.controls['ownershipType'].value)
    if(this.instituteCategory === 'C' || this.instituteCategory === 'S'){
      let management = this.managementTypeList.find(e => e.id === this.editReg.controls['managementType'].value)
      if (!management) {
        this.editReg.get('managementType')?.setValue(null)
      }
    }
    if(!ownership){
      this.editReg.get('ownershipType')?.setValue(null)
    }
    if (this.editReg.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false
    }
    if (this.affilatedValueArr.length == 0 && this.affilatedUniversityEditArr == null) {
      this.affilatedValue = null
    }
    else if (this.affilatedValueArr.length == 0 && this.affilatedUniversityEditArr != null) {
      this.affilatedValue = this.affilatedUniversityEditArr
    }
    else {
      this.affilatedValue = this.affilatedValueArr
    }

    if (this.statutoryValueArr.length == 0 && this.statutoryEditArr == null) {
      this.statuatoryBodyValue = null
    }
    else if (this.statutoryValueArr.length == 0 && this.statutoryEditArr != null) {
      this.statuatoryBodyValue = this.statutoryEditArr
    }
    else {
      this.statuatoryBodyValue = this.statutoryValueArr

    }
    if (this.otherAffiliatingUniversityRadio == null && (this.showInCollege == true ||  this.showInStandalone == true)) {
      this.msg = 'Please Select University/Statutory body Yes/No'
      return
    }
    if ((this.isVisible == true) && ((this.statuatoryBodyValue == null || this.statuatoryBodyValue.length == 0) && (this.affilatedValue == null || this.affilatedValue.length == 0))) {
      this.msg = 'Please Select At least One Affiliated Body'
      this.sharedService.showValidationMessage('Must Be Selected At least One Affiliated Body.');

      return
    }

    if ((this.isVisible == false) && ((this.roleId === '20') || (this.roleId === '7' && this.userTypeId == '01' || this.userTypeId == '07')) && (this.editReg.controls['ministryId'].value == null)) {
      this.editReg.get('ministryId')?.setValidators(Validators.required)
      this.editReg.get('ministryId')?.updateValueAndValidity()
      this.sharedService.showValidationMessage('Please Select Ministry');

      return
    }

    
    
    
    var latitude = null;
    var longitude = null;

    // if (this.editReg.controls['latitude'].value || this.editReg.controls['longitude'].value) {
    //   let c: boolean = this.validationLatLong(this.editReg.controls['latitude'].value, this.editReg.controls['longitude'].value);
    //   if (!c) {
    //     return;
    //   } else {
    //     latitude = this.editReg.controls['latitude'].value
    //     longitude = this.editReg.controls['longitude'].value
    //   }
    // }
    // this.affilatedValue?.forEach((e:any) => {
    //   e.affiliatingUnivId  = 'U-'+ e.affiliatingUnivId
    //   // if (!e.affiliatingUnivId.startsWith('U-')) {
    //   //   e.affiliatingUnivId = 'U-' + e.affiliatingUnivId;
    //   // }
     
    // });


    let data = this.editReg.getRawValue();
    let payload = {};
    payload = {
      "managementId": data.managementType,
      "ownerShipId": data.ownershipType?.toString(),
      "instituteHeadDesignation": this.editReg.controls['directordesignation'].value,
      "instituteHeadEmail": this.editReg.controls['directorEmail'].value,
      "instituteHeadMobile": this.editReg.controls['directorMobile'].value,
      "instituteHeadName": this.editReg.controls['directorName'].value,
      "instituteHeadTelephone": this.editReg.controls['directorTelephone'].value?.toString(),
      "nodalOfficerName": this.editReg.controls['nodalOfficerName'].value,
      "nodalOfficerDesignation": this.editReg.controls['designation'].value,
      "email": this.editReg.controls['email'].value,
      "mobile": this.editReg.controls['mobileNo'].value,
      "phoneLandline": this.editReg.controls['landLine'].value,
      "locationId": data.location,
      "addressLine1": data.addLine1,
      "addressLine2": data.addLine2,
      "city": data.city,
      "stateId": data.stateCode,
      "stateCode": data.stateCode,
      "districtId": data.districtCode,
      'blockId': data.blockId,
      'ulbId': data.ulbId,
      "pinCode": data.pinCode,
      "latitude": data.latitude,
      "longitude": data.longitude,
      "area": data.area,
      "constructedArea": data.constructedArea,
      "aisheCode": this.aisheId,
      "userId": this.localService.getData('userId'),
      "name": this.name,
      // "middleName": this.middleName,
      // "lastName": this.lastName,
      "mobileVerified": true,
      "emailVerified": true,
      "website": data.website,
      "universityId": data.universityId,
      "subDistrictId": data.subDistrictId,
      "isGeospatialDataVerified": this.isGeospatialDataVerified,
      'islgdDistrictVerified': this.islgdDistrictVerified,
      'isLgdStateVerified': this.isLgdStateVerified,
      'isLgdSubDistrictVerified': this.isLgdSubDistrictVerified,
      //  'nodalOfficerTitleId':this.editReg.controls['nodalOfficerTitleId'].value,
      //  'headOfficerTitleId':this.editReg.controls['headOfficerTitleId'].value,
      'nodalOfficerTitleId': null,
      'headOfficerTitleId': null,
      'otherAffiliatingUniversity': this.affilatedValue,
      'statuatoryBody': this.statuatoryBodyValue,
      'ministryId': this.editReg.controls['ministryId'].value,
      'isOtherAffiliatingUniversityStatuatoryBody': this.editReg.controls['isOtherAffiliatingUniversityStatuatoryBody'].value
    }
    this.auth.updateUserRecord(payload).subscribe((res: any) => {
      if (res.status === 201) {
        this.sharedService.showSuccess();
        this.hideButton = false
        this.msg = ''
        this.getInstituteDetail();

      }

    }, err => {

    })
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
      if (parseInt(lat[0]) < 6 || parseInt(lat[0]) > 38 || l < 5 || l > 10) {
        this.sharedService.showValidationMessage('Latitude [Range: 6.00000 - 38.00000] ,Values must contain minimum of 5 digits after the decimal point.')
        return false;
      }
    } if (longitude) {
      var long = longitude.toString().split('.');

      let lg = 0;

      if (long[1]) {
        lg = long[1].length;
      }
      if (parseInt(long[0]) < 68 || parseInt(long[0]) > 98 || lg < 5 || lg > 10) {
        this.sharedService.showValidationMessage('Longitude [Range: 68.00000 - 98.00000] ,Values must contain minimum of 5 digits after the decimal point.')

        return false;
      }
    }

    return true;
  }


  isInstitutionAfilated(value: any) {
    if (value == true) {
      this.isVisible = true
      this.otherAffiliatingUniversityRadio = true
      this.msg = ''
      this.editReg.get('isOtherAffiliatingUniversityStatuatoryBody')?.setValue(true)
    }
    else {
      this.otherAffiliatingUniversityRadio = false
      this.isVisible = false
      this.editReg.get('isOtherAffiliatingUniversityStatuatoryBody')?.setValue(false)
      // this.editReg.get('ministryId')?.setValue(null)
      this.affilatedValueArr = []
      this.affilatedUniversityEditArr = null
      this.statutoryValueArr = []
      this.statutoryEditArr = null
      this.msg = ''
    }
  }



  isLocation(value: any) {
    if (value === 'R') {
      this.editReg.get('blockId')?.setValidators(Validators.required)
      this.editReg.get('blockId')?.updateValueAndValidity()
      this.editReg.get('ulbId')?.clearValidators()
      this.editReg.get('ulbId')?.updateValueAndValidity()
      this.getBlockList(this.editReg.controls['districtCode'].value)
    } else {
      this.editReg.get('ulbId')?.setValidators(Validators.required)
      this.editReg.get('ulbId')?.updateValueAndValidity()
      this.editReg.get('blockId')?.clearValidators()
      this.editReg.get('blockId')?.updateValueAndValidity()
      this.getUrbanBodyList(this.editReg.controls['districtCode'].value)
    }
  }
  getBlockList(distCode: any) {
    this.blockList = [];
    this.auth.getBlock(distCode).subscribe(res => {
      this.variable = res;
      this.blockList = this.variable.slice();

    }, err => {

    })
  }
  getBlockLgd(value: any) {
    if (value !== 99999) {
      this.blockLgdId = this.blockList.find(e => e.id === value).lgdBlockCode
    }

  }
  getUrbanBodyList(distCode: any) {
    this.urbanBodyList = []
    this.auth.getUrban(distCode).subscribe(res => {
      this.variable1 = res
      this.urbanBodyList = this.variable1.slice();
    }, err => {

    })
  }
  getManagementType() {
    this.auth.getManagementTypeOwnershipData(this.instituteCategory).subscribe(res => {
      this.managementTypeList = res;
      // if (this.showInStandalone) {
      //   let index = this.managementTypeList.findIndex(ele => ele.management === 'University')
      //   this.managementTypeList.splice(index, 1)
      // }
    }, err => {

    })

  }


  ownershipType(data: any) {
    if (this.instituteCategory === 'C') {
      this.ownershipStatusList = []
      const da = this.managementTypeList.filter(res => res.id === data);
      const tem = da[0].ownershipForClg;
      this.ownershipStatusList = [...tem];
    } else {
      if (this.instituteCategory === 'S' || this.instituteCategory === 'U') {
        this.getOwnershipStatus()
      }
    }
  }


  getAffilatedUni() {
    this.auth.otherAffiliatingUniversity(2024).subscribe(res => {
      this.filterAffVariable = res
      this.affilatedUniArr = this.filterAffVariable.slice();
      this.affilatedUniArr.forEach((e:any) =>{
        e.id = 'U-' + e.id
      })
    },err=>{

    })
  }



  statutoryBody() {
    this.auth.statutoryBody().subscribe(res => {
      let data = res
      let filterArray = data.filter((item: any) => item.id !== '01')

      let filterState = filterArray.filter((item: any) => item.stateCode === this.stateCode);
      let filterArr = filterArray.filter((item: any) => item.stateCode === '00')
      this.statutoryArr = [...filterState, ...filterArr].sort((a: any, b: any) =>
        a.statutoryBody.localeCompare(b.statutoryBody))




      //   }
      // else{
      //   let filterState = filterArray.filter((item:any) => item.stateCode === this.stateCode && item.standaloneBodyTypeId === this.userTypeId)
      //   let filterArr = filterArray.filter((item:any) => item.stateCode === '00')
      //     this.statutoryArr = [...filterState, ...filterArr].sort((a:any, b:any) => 
      //       a.statutoryBody.localeCompare(b.statutoryBody))
      //   }




      // let filterArr = filterArray.filter((item:any) => item.stateCode === this.stateCode && item.standaloneBodyTypeId === this.userTypeId)
      // this.statutoryArr = filterArr.sort((a:any, b:any) => 
      //   a.statutoryBody.localeCompare(b.statutoryBody)
      // );


      // }



    })
  }


  ministryName() {
    this.auth.ministryName().subscribe(res => {
      this.ministryList = res;
      this.ministryArr = this.ministryList.slice()
    })
  }


  getStatutoryBody(event: any) {
    const selectedIds = event.value.map((id: any) => id.toString());
    const filterArr = this.statutoryArr.filter((item) => selectedIds.includes(item.id));
    if (selectedIds.length === 0) {
      // Clear the array when no values are selected
      this.statutoryValueArr = [];
      this.statutoryEditArr = null
      this.statutoryListVisibleArray = []
    }
    else {
      for (let i = 0; i < filterArr.length; i++) {
        const selectedValue = filterArr[i]; // assuming event.value represents a single selection
        const existingIndex = this.statutoryValueArr.findIndex((item: any) => item.id === selectedValue.id);
        if (existingIndex === -1) {
          let obj = {
            id: selectedValue.id,
            statuatoryBody: selectedValue.statutoryBody,
            // stateCode : selectedValue.stateCode,
            // standaloneBodyTypeId : this.roleId == 12 ? null : selectedValue.standaloneBodyTypeId
          };
          this.statutoryValueArr.push(obj);
          this.statutoryEditArr?.push(obj);
          this.statutoryListVisibleArray = this.statutoryEditArr
        }
      }
      this.statutoryValueArr = this.statutoryValueArr.filter((item: any) =>
        selectedIds.includes(item.id))
      this.statutoryEditArr = this.statutoryValueArr.filter((item: any) =>
        selectedIds.includes(item.id));
      this.statutoryListVisibleArray = this.statutoryEditArr
    }

  }



  //   this.affilatedUniArr.forEach((item) => {
  //     const isChecked = selectedIds.includes(item.id);
  //     console.log(`University ID: ${item.id}, Checked: ${isChecked}`);
  //   });


  toggleAllSegments(e: any, data: any) {
    this.itsChekcBox = e.source._selected
    this.itsSearchValueUncheck = data.id
  }


  getAffiliatingBody(event: any) {
    const selectedIds = event.value.map((id: any) => id.toString());
    const filterArr = this.affilatedUniArr.filter((item) => selectedIds.includes(item.id));
    
    if (selectedIds.length < 0) {
      this.affilatedValueArr = [];
      this.affilatedUniversityEditArr = [];
      this.affListVisibleArray = [];
      return
    }
    if (this.itsChekcBox) {
      // const existingIdsSet = new Set(this.affilatedValueArr.map((item: any) => item.affiliatingUnivId));
      for (let i = 0; i < filterArr.length; i++) {
        const selectedValue = filterArr[i];
        if (!this.affilatedValueArr.includes(selectedValue.id)) {
          let obj = {
            affiliatingUniv: selectedValue.universityNameState,
            affiliatingUnivDistrict: selectedValue.district?.name,
            affiliatingUnivId: selectedValue.id,
            affiliatingUnivState: selectedValue.stateCode?.name
          };

          this.affilatedValueArr.push(obj);
        }
      }
      if (this.affilatedUniversityEditArr === null) {
        this.affilatedValueArr = Array.from(
          new Set([...this.affilatedValueArr])
        );

        const uniqueArr = this.affilatedValueArr.filter(
          (item: any, index: any, self: any) =>
            index === self.findIndex((t: any) => t.affiliatingUnivId === item.affiliatingUnivId)
        );

        this.affListVisibleArray = [...uniqueArr];
        this.affilatedValueArr = [...this.affListVisibleArray]
      }
      else {

        this.affilatedUniversityEditArr = Array.from(
          new Set([...this.affilatedValueArr, ...this.affilatedUniversityEditArr])
        );


        const uniqueArr = this.affilatedValueArr.filter(
          (item: any, index: any, self: any) =>
            index === self.findIndex((t: any) => t.affiliatingUnivId === item.affiliatingUnivId)
        );

        this.affListVisibleArray = [...uniqueArr];
        this.affilatedValueArr = [...this.affListVisibleArray]
      }


    }
    else {

      if (this.affilatedValueArr.length === 0) {
        this.affilatedUniversityEditArr = this.affilatedUniversityEditArr.filter((item: any) => item.affiliatingUnivId !== this.itsSearchValueUncheck);
        this.affListVisibleArray = [...this.affilatedUniversityEditArr];
      }
      else {
        this.affilatedValueArr = this.affilatedValueArr.filter((item: any) => item.affiliatingUnivId !== this.itsSearchValueUncheck);
        this.affListVisibleArray = [...this.affilatedValueArr];
      }
    }

  }





  getOwnershipStatus() {
    this.auth.getOwnership(this.instituteCategory).subscribe(res => {
      this.ownershipStatusList = res;
    }, err => {

    })


  }
  getUniversity() {
    let currentSurveyYear = this.sharedService.currentSurveyYear;
    this.auth.getUniversityEditReg(currentSurveyYear).subscribe(res => {
      this.universityArr = res;
      this.universityList = this.universityArr.slice()
      // this.universityList = res;
      this.selectedAreas = this.universityList;
    }, err => {

    })
  }
  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
  restrictNumeric(event: { charCode: number }) {
    return event.charCode == 8 || event.charCode == 0
      ? null
      : event.charCode >= 48 && event.charCode <= 57;
  }
  getuserRole() {
    this.auth.getUserRole().subscribe(res => {
      if (res && res.length) {
        this.userType = res.find((ele: any) => ele.roleId == this.roleId).roleName;
        this.userDetail.get('userType')?.setValue(this.userType);
      }
    }, err => {

    })
  }
  showEnabled() {
    this.hideButton = true
    this.editReg.enable();
    this.editReg.get('stateName')?.disable();
    this.editReg.get('districtName')?.disable();
    this.editReg.get('countryName')?.disable();
    this.editReg.get('instituteType')?.disable();
    this.editReg.get('name')?.disable();
    this.editReg.get('universityId')?.disable()
    this.editReg.get('managementType')?.disable();

    this.editReg.get('aisheCode')?.disable();
    this.editReg.get('mobileNo')?.disable();
    this.editReg.get('email')?.disable();
    this.editReg.get('designation')?.disable();
    this.editReg.get('nodalOfficerName')?.disable();
    this.editReg.get('landLine')?.disable();
    this.editReg.get('nodalOfficerTitle')?.disable();

    this.editReg.get('directorTelephone')?.disable();

    this.editReg.get('directorMobile')?.disable();
    this.editReg.get('directorEmail')?.disable();
    this.editReg.get('directordesignation')?.disable();
    this.editReg.get('directorName')?.disable()
    this.editReg.get('headOfficerTitle')?.disable();


    if (this.isBisagVerified) {
      this.editReg.get('latitude')?.disable();
      this.editReg.get('longitude')?.disable();
      this.editReg.get('latitude')?.updateValueAndValidity();
      this.editReg.get('longitude')?.updateValueAndValidity();
    }
  }
allowOnlyDecimal(event: KeyboardEvent): void {
  const input = event.target as HTMLInputElement;
  const inputChar = event.key;
  const currentValue = input.value;
  const allowedKeys = ['Backspace', 'Tab', 'ArrowLeft', 'ArrowRight', 'Delete'];

  if (allowedKeys.includes(inputChar)) return;

  // Allow only one dot
  if (inputChar === '.') {
    if (currentValue.includes('.') || currentValue.length >= 9) {
      event.preventDefault();
    }
    return;
  }

  // Allow digits only
  if (!/^\d$/.test(inputChar) || currentValue.length >= 10) {
    event.preventDefault();
  }
}
  // showEnabled() {
  //   this.hideButton = true
  //   this.editReg.enable();
  //   this.editReg.get('stateName')?.disable();
  //   this.editReg.get('districtName')?.disable();
  //   this.editReg.get('countryName')?.disable();
  //   this.editReg.get('instituteType')?.disable();
  //   this.editReg.get('name')?.disable();
  //   this.editReg.get('universityId')?.disable()
  //   if (this.isBisagVerified) {
  //     this.editReg.get('latitude')?.disable();
  //     this.editReg.get('longitude')?.disable();
  //     this.editReg.get('latitude')?.updateValueAndValidity();
  //     this.editReg.get('longitude')?.updateValueAndValidity();
  //   }
  // }
}













