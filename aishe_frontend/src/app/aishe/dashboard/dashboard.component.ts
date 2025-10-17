import { Component, OnInit } from "@angular/core";
import { InstitutionmanagementService } from "src/app/service/institutionmanagement/institutionmanagement.service";
import { LocalserviceService } from "src/app/service/localservice.service";

import { SharedService } from "src/app/shared/shared.service";



@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.scss"],
})
export class DashboardComponent implements OnInit {
  cards: any;
  obj = Object; //to use in html page for looping column fields
  latestSurveyYear: number=0;
  currentSurveyYear: any;
  latestSurveyYearApiData: any;
  currentSurveyYearApiData: any;
  pageStatusList: any[] = [];
  pageStatus: any[] = []
  isUniversity: boolean=false;
  final_lock: boolean = false;
  surveyYearList:any[] = [];
  surveySub: any
  aisheCode: any;
  faculty: boolean = false;
  department: boolean = false;
  surveylatestSub: string | undefined;
  totalLatest:number=0
  totalCurrent:number=0
  constructor( public sharedService: SharedService,   private instManagementService: InstitutionmanagementService,public localService:LocalserviceService){}
 
  async ngOnInit() {
 
    this.aisheCode = this.localService.getData('aisheCode');
    this.latestSurveyYear=this.sharedService.latestSurvey;
  this.currentSurveyYear=this.sharedService.currentSurveyYear;
    var splitSurvey = this.currentSurveyYear.toString().substring(2, 4)
    var intSurvey = parseInt(splitSurvey)
    var a = intSurvey + 1;
    this.surveySub = this.currentSurveyYear + '-' + a;

    this.isUniversity = this.localService.getData('loginMode') == 'U';   //some card to show for university only

    if (this.latestSurveyYear === this.currentSurveyYear) {
      this.latestSurveyYearApiData = await this.instManagementService.getDashboardData(0)
    } else {
      this.latestSurveyYearApiData = await this.instManagementService.getDashboardData(this.latestSurveyYear);
    }

    this.currentSurveyYearApiData = await this.instManagementService.getDashboardData(this.currentSurveyYear)
    var splitSurveyb = this.latestSurveyYear.toString().substring(2, 4)
    var intSurveyb = parseInt(splitSurveyb)
    var b = intSurveyb + 1;
    this.surveylatestSub = this.latestSurveyYear.toString() + '-' + b;

    const regularSC = this.latestSurveyYearApiData[0].regularSC
      const regularST=  this.latestSurveyYearApiData[0].regularST
      const regularOBC = this.latestSurveyYearApiData[0].regularOBC
      const regularGeneral = this.latestSurveyYearApiData[0].regularGeneral

      const distanceSC = this.latestSurveyYearApiData[0].distanceSC
      const distanceST=  this.latestSurveyYearApiData[0].distanceST
      const distanceOBC = this.latestSurveyYearApiData[0].distanceOBC
      const distanceGeneral = this.latestSurveyYearApiData[0].distanceGeneral

      const privateSC = this.latestSurveyYearApiData[0].privateSC
      const privateST=  this.latestSurveyYearApiData[0].privateST
      const privateOBC = this.latestSurveyYearApiData[0].privateOBC
      const privateGeneral = this.latestSurveyYearApiData[0].privateGeneral

      const onlineSCEnroll = this.latestSurveyYearApiData[0].onlineSCEnroll
      const onlineSTEnroll=  this.latestSurveyYearApiData[0].onlineSTEnroll
      const onlineOBCEnroll = this.latestSurveyYearApiData[0].onlineOBCEnroll
      const onlineGeneralEnroll = this.latestSurveyYearApiData[0].onlineGeneralEnroll

      const totalRegular = regularSC+regularST+regularOBC+regularGeneral
      const totalDistance = distanceSC+distanceST+distanceOBC+distanceGeneral
      const totalPrivate = privateSC+privateST+privateOBC+privateGeneral
      const totalOnline = onlineSCEnroll+onlineSTEnroll+onlineOBCEnroll+onlineGeneralEnroll
      this.totalLatest =  totalRegular+totalDistance+totalPrivate+totalOnline


       const regularSCcurrent = this.currentSurveyYearApiData[0].regularSC
      const regularSTcurrent=  this.currentSurveyYearApiData[0].regularST
      const regularOBCcurrent = this.currentSurveyYearApiData[0].regularOBC
      const regularGeneralcurrent = this.currentSurveyYearApiData[0].regularGeneral

      const distanceSCcurrent = this.currentSurveyYearApiData[0].distanceSC
      const distanceSTcurrent=  this.currentSurveyYearApiData[0].distanceST
      const distanceOBCcurrent = this.currentSurveyYearApiData[0].distanceOBC
      const distanceGeneralcurrent = this.currentSurveyYearApiData[0].distanceGeneral

      const privateSCcurrent = this.currentSurveyYearApiData[0].privateSC
      const privateSTcurrent=  this.currentSurveyYearApiData[0].privateST
      const privateOBCcurrent = this.currentSurveyYearApiData[0].privateOBC
      const privateGeneralcurrent = this.currentSurveyYearApiData[0].privateGeneral

      const onlineSCEnrollcurrent = this.currentSurveyYearApiData[0].onlineSCEnroll
      const onlineSTEnrollcurrent=  this.currentSurveyYearApiData[0].onlineSTEnroll
      const onlineOBCEnrollcurrent = this.currentSurveyYearApiData[0].onlineOBCEnroll
      const onlineGeneralEnrollcurrent = this.currentSurveyYearApiData[0].onlineGeneralEnroll

      const totalRegularcurrent = regularSCcurrent+regularSTcurrent+regularOBCcurrent+regularGeneralcurrent
      const totalDistancecurrent = distanceSCcurrent+distanceSTcurrent+distanceOBCcurrent+distanceGeneralcurrent
      const totalPrivatecurrent = privateSCcurrent+privateSTcurrent+privateOBCcurrent+privateGeneralcurrent
      const totalOnlinecurrent = onlineSCEnrollcurrent+onlineSTEnrollcurrent+onlineOBCEnrollcurrent+onlineGeneralEnrollcurrent
      this.totalCurrent =  totalRegularcurrent+totalDistancecurrent+totalPrivatecurrent+totalOnlinecurrent
  }
}

