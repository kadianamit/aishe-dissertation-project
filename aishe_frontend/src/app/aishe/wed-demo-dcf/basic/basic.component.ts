import { Component, OnInit } from '@angular/core';
import { GetService } from 'src/app/service/get/get.service';
import { InstitutionContactDetailsComponent } from '../../reports/institution-contact-details/institution-contact-details.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { SharedService } from 'src/app/shared/shared.service';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { DatePipe } from '@angular/common';
import { LocalserviceService } from 'src/app/service/localservice.service';


@Component({
  selector: 'app-basic',
  templateUrl: './basic.component.html',
  styleUrls: ['./basic.component.scss']
})
export class BasicComponent implements OnInit {
  aisheCode: any
  selectedIndex: any = 0;
  filter: any = {}
  tableArray: any = []
  officerListArray: any = []
  infrastructure!: FormGroup;
  bankDetailsArray: any = {}
  ownerShipArray = [
    { "id": 1, "type": "Government" },
    { "id": 2, "type": "Trust" },
    { "id": 3, "type": "Society" },
    { "id": 4, "type": "Section 8 Company" },
    { "id": 5, "type": "Section 25 Company" },
    { "id": 6, "type": "Others" }
  ]
  loginMode: any;


  constructor(private getService: GetService, private formBuilder: FormBuilder, public localService: LocalserviceService) {
    if (this.localService.getData('loginMode') && this.localService.getData('aisheCode')) {
      this.aisheCode = this.localService.getData('aisheCode')
      this.getInstituteData()
    }
  }

  ngOnInit(): void {

    this.infrastructure = this.formBuilder.group({
      id: [0, []],
      playGround: [false, []],
      playgroundCount: [null, []],
      auditorium: [false, []],
      auditoriumCount: [null, []],
      auditoriumCapacitySeats: [null, []],
      theatre: [false, []],
      theatresCount: [null, []],
      library: [false, []],
      librarayCount: [null, []],
      liberaryArea: [null, []],
      bookCount: [null, []],
      journalCount: [null, []],
      laboratory: [false, []],
      laboratoryCount: [null, []],
      whetherFireFightingEquipmentsAvailableInLab: [false, []],
      conferenceHall: [false, []],
      conferenceHallCount: [null, []],
      healthCenter: [false, []],
      healthCenterCount: [null, []],
      gymCenter: [false, []],
      gymCount: [null, []],
      indoorStadium: [false, []],
      indoorStadiumCount: [null, []],
      commonRoom: [false, []],
      commonRoomCount: [null, []],
      compCenter: [false, []],
      computerCenterCount: [null, []],
      cafeteria: [false, []],
      cafeCount: [null, []],
      guestHouse: [false, []],
      guestHouseCount: [null, []],
      girlsCommonRoom: [false, []],
      girlRoomCount: [null, []],

      noOfToiletsTotal: [null, []],
      noOfToiletsDisabledMales: [null, []],
      noOfToiletsDisabledFemales: [null, []],
      noOfToiletsGirls: [null, []],
      totalClassroomAndSeminarHalls: [null, []],
      totalComputersForAcademicWork: [null, []],
      incubationCentres: [null, []],

      dateOfIqacEstablishment: ['', []],
      iqacContactDetails: ['', []],
      whetherInstitutionHasIqac: [false, []],

      totalNoBuilding: [null, []],
      numberOfMedicalPractitioner: [null, []],
      numberOfNursingStaff: [null, []],
      numberOfWatercooler: [null, []],
      isDrinkingWaterFacilityAvailable: [false, []],
      whetherExaminationHallWithComputer: [false, []],
      numberOfComputerInExaminationHall: [null, []],
      whetherPowerBackupGeneratorAvailable: [false, []],
      internetBandwidth: [null, []],
      numberOfStartUpsHei: [null, []],
      rainWaterHarvestingCapacityLiters: [null, []],

      // checkbox
      handRails: [false, []],
      solarPower: [false, []],
      connectivityNkn: [false, []],
      connectivityNmeict: [false, []],
      campusFriendly: [false, []],
      disabledGirlToilet: [false, []],
      attachedRampClassLibrary: [false, []],
      grievanceRedressal: [false, []],
      vigilanceCell: [false, []],
      oppportunityCell: [false, []],
      sexualHarrassmentCell: [false, []],
      studentCounselor: [false, []],
      firstAidRoom: [false, []],
      girlsToilet: [false, []],
      skillCenter: [false, []],
      girlSelfDefenceClass: [false, []],
      antiRaggingCell: [false, []],

      // radio
      disasterMgmt: [true, []],
      capacityBuilding: [false, []],
      vulnerableAssessChecks: [false, []],
      mockDrillPrograms: [false, []]
    })
  }





  getInstituteData() {
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    this.loginMode = x[0]
    let payload = {
      loginMode: loginMode?.toUpperCase(),
      aisheCode: this.aisheCode?.toUpperCase(),
      currentSurveyYear: 2022,
      latestSurveyYear: 2021
    }
    this.getService.getInstituteDataDemo(payload).subscribe(res => {
      this.localService.saveData('loginMode', payload.loginMode)
      this.localService.saveData('aisheCode', payload.aisheCode)
      let ownerShip = this.ownerShipArray.filter(item => item.id == res.data?.ownershipStatus)
      

      this.filter = {
        institutionName: res.data?.name,
        instituteType: res.data?.instituteType?.type,
        ownershipStatus: ownerShip[0]?.type,
        location: res.data?.location,
        addLine1: res.data?.addressline1,
        addLine2: res.data?.addressline2,
        city: res.data?.city,
        blockCityTown: res.data?.blockCityTown,
        pinCode: res.data?.pinCode,
        totalArea: res.data?.area,
        constructArea: res.data?.constructedArea,
        longitude: res.data?.longitude,
        latitude: res.data?.latitude,
        website: res.data?.website,
        blockId: res.data?.blockId,
        ulbId: res.data?.ulbId,
        lgdDistCode: res.data?.districtCode.lgdDistCode,
        dName: res.data?.districtCode.name,
        sno: res.data?.districtCode.sno,
        lgdCode: res.data?.stateCode.lgdCode,
        sName: res.data?.stateCode.name,
        subDistrictId: res?.subDistrict?.id,
        nameOfTrustSocietyCompany: res?.nameOfTrustSocietyCompany,
        addressOfTrustSocietyCompany: res?.addressOfTrustSocietyCompany

      }
      this.localService.saveData('institutionName', this.filter.institutionName)
      this.getAffliatedData()
      this.getOfficerList()
      this.getInfraStructure()
      this.getInstitutionBankDetails()

    }, err => {

    })
  }


  getAffliatedData() {
    let payload = {
      aisheCode: this.aisheCode?.toUpperCase(),
      currentSurveyYear: 2022,
      latestSurveyYear: 2021
    }
    this.getService.getAffliatedData(payload).subscribe(res => {
      this.tableArray = res.data
    })
  }


  getOfficerList() {
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      instituteType: loginMode?.toUpperCase(),
      aisheCode: this.aisheCode?.toUpperCase(),
      currentSurveyYear: 2022,
      latestSurveyYear: 2021
    }
    this.getService.getOfficerList(payload).subscribe(res => {
      this.officerListArray = {
        officerDesignation: res.data[0].officerDesignation,
        officerEmail: res.data[0].officerEmail,
        officerMobile: res.data[0].officerMobile,
        officerName: res.data[0].officerName,
        officerTelephone: res.data[0].officerTelephone,
        aisheCode: res.data[0].universityPk.aisheCode,
      }
    })
  }





  getInfraStructure() {
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode?.toUpperCase(),
      currentSurveyYear: 2022,
      latestSurveyYear: 2021
    }
    this.getService.getInfraStructure(payload).subscribe(res => {
      this.infrastructure.patchValue(res.data)
      this.infrastructure.disable();
    })
  }

  getInstitutionBankDetails() {
    let x: any = this.aisheCode?.toUpperCase()?.split('-');
    var aisheId = x[1];
    var loginMode = x[0];
    let payload = {
      aisheCode: this.aisheCode?.toUpperCase(),
      instituteCategory: loginMode?.toUpperCase(),
      surveyYear: 2022
    }
    this.getService.getInstitutionBankDetails(payload).subscribe(res => {
      if (res && res.length) {
        this.bankDetailsArray = {
          accountHolderName: res[0].accountHolderName,
          accountNumber: res[0].accountNumber,
          bankName: res[0].bankName,
          bankAddress: res[0].bankAddress,
          ifscCode: res[0].ifscCode,
          typeOfAccount: "Saving"

        }
      }

    })
  }





}
