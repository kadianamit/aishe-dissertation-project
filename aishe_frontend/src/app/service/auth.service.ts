import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, Observer, retry, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SharedService } from '../shared/shared.service';
import { LocalserviceService } from './localservice.service';
import { data } from 'jquery';
const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    Authorization: '',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  global_loader: boolean = false;
  constructor(
    public toastrService: ToastrService,
    public http: HttpClient,
    public sharedService: SharedService,
    public localService: LocalserviceService
  ) { }

  getToken(userId: any, password: any): Observable<any> {
    var data = {
      username: userId,
      password: password,
    };
    return this.http.post(`${environment.authLogin}`, data);
  }

  latLongToken(): Observable<any> {
    let data = {
      username: 'ministry',
      password: '%K=jz:^r~PK?zHb@',
    };
    return this.http.post(`${environment.baseURLLATLONG}`, data);
  }

  validateLatLong(payload: any, token: any): Observable<any> {
    const httpOptions1 = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`,
      }),
    };
    return this.http.post(
      `${environment.baseURLLATLONG1}`,
      payload,
      httpOptions1
    );
  }

  getDataUserToken(userId: any, password: any, usertype: any): Observable<any> {
    var data = {
      username: userId,
      password: password,
      usertype: usertype,
    };
    return this.http.post(`${environment.authLogin}`, data);
  }
  getCaptchaText() {
    return this.http.get(environment.baseUrlCaptcha + `getCaptcha`, {
      responseType: 'json',
    });
  }
  verifyGetCaptcha(text: any, encodeCaptcha: any): Observable<any> {
    return this.http.get(
      environment.baseUrlCaptcha +
      `verifycaptchaaishe?captchaText=${text}&data=${encodeCaptcha}`
    );
  }
  getUser(userId: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/user-private?userId=${userId}`
    );
  }
  getUserNoToken(userId: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/user?userId=${userId}`
    );
  }
  getReportRemunerationStatus() {
    return this.http.get<any>(
      `${environment.baseUrlAishe}api/ref_remuneration-status`
    );
  }
  saveRemunerationStatus(data: any) {
    return this.http.post<any>(
      `${environment.baseUrlAishe}api/change-status`,
      data
    );
  }

  sendForgotPasswordEmail(email: any): Observable<any> {
    return this.http.post(
      `${environment.otpURL}/api/forgot-password?email=${email}`,
      httpOptions
    );
  }

  getDeaffiliationCollegeList(data: any): Observable<any> {
    let surveyYear = data.surveyYear;
    let aisheCode = data.aisheCode;
    if (aisheCode == undefined || aisheCode === null) {
      aisheCode = '';
    }
    let stateCode = data.selectedStateCode;
    let universityCode = data.selectedUniversityCode;
    if (universityCode == undefined) {
      universityCode = '';
    }
    // console.log(surveyYear);
    // console.log(aisheCode);

    // console.log(universityCode);
    // console.log(stateCode);

    return this.http.get(
      `${environment.instituMURL}/college/getDeaffiliationCollegeList?surveyYear=${surveyYear}&universityId=${universityCode}&aisheCode=${aisheCode}`,
      httpOptions
    );
  }
  forgotPassword(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/verifyforgotpassword`,
      payload
    );
  }
  // changePassword(payload: any): Observable<any> {
  //   return this.http.post(
  //     `${environment.baseUrlAishe}api/reset-password`,
  //     payload
  //   );
  // }
  changePassword(payload: any): Observable<any> {
    return this.http.put(
      `${environment.baseUrlAishe}api/changepassword`,
      payload
    );
  }
  changePasswordDataUser(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlPostApi2}/changePassword`,
      payload
    );
  }
  resertdataUserPass(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlPostApi2}/reset-password`,
      payload
    );
  }
  // auditLog(payload:any):Observable<any>{
  //   return this.http.post(`${environment.baseUrlMou}userlog`,payload)

  // }
  // auditList():Observable<any>{
  //   var userId = this.localService.getData('userId')

  //   return this.http.get(`${environment.baseUrlMou}userlog?userId=${userId}`)
  // }
  sendEmail(email: any, latestId: any): Observable<any> {
    return this.http.post<any>(
      `${environment.otpURL}send-otp-to-email?email=${email}&latestId=${latestId}`,
      httpOptions
    );
  }
  sendMobile(mobile: any, latestId: any): Observable<any> {
    return this.http.post<any>(
      `${environment.otpURL}send-otp-to-mobile?mobileNumber=${mobile}&latestId=${latestId}`,
      httpOptions
    );
  }
  verifyEOTP(email: any, otp: any): Observable<any> {
    return this.http.post<any>(
      `${environment.otpURL}verify-email-otp?email=${email}&otp=${otp}`,
      httpOptions
    );
  }
  verifyMOTP(mobile: any, otp: any): Observable<any> {
    return this.http.post<any>(
      `${environment.otpURL}verify-mobile-otp?mobile=${mobile}&otp=${otp}`,
      httpOptions
    );
  }
  getState(): Observable<any> {
    return this.http.get<any>(`${environment.masterUrl}/api/state`);
  }
  getStatePrivate(): Observable<any> {
    return this.http.get<any>(`${environment.masterUrl}/api/state-private`);
  }
  getDistrict(stateCode: any): Observable<any> {
    return this.http.get<any>(
      `${environment.masterUrl}/api/district/${stateCode}`
    );
  }
  getSubDistrictList(payload: any): Observable<any> {
    return this.http.get<any>(`${environment.masterUrl}/api/ref_sub_district`, {
      params: payload,
    });
  }
  updateUserRecord(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/user`,
      payload,
      httpOptions
    );
  }

  getCollegeType(): Observable<any> {
    return this.http.get<any>(`${environment.masterUrl}/api/university-type`);
  }
  getStandAloneBody(): Observable<any> {
    return this.http.get<any>(`${environment.masterUrl}/api/bodytype`);
  }
  affiliatingUniv(stateCode: any, year: any): Observable<any> {
    return this.http.get<any>(
      `${environment.masterUrl}/api/university?stateCode=${stateCode}&year=${year}`
    );
  }
  otherAffiliatingUniv(stateCode: any, year: any): Observable<any> {
    return this.http.get<any>(
      `${environment.masterUrl}/api/university?stateCode=${stateCode}&year=${year}`
    );
  }

  userExist(userId: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}useridalreadyexist?userId=${userId}`
    );
  }
  snoExist(roleId: any, stateCode: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}snoalreadyexist?roleId=${roleId}&stateCode=${stateCode}`
    );
  }

  findEligible(aisheCode: any, csy: any): Observable<any> {
    const httpOptions1 = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        Authorization: `Bearer ${sessionStorage.getItem('token')}`,
      }),
    };
    return this.http.get(
      `${environment.baseUrlAishe}aishecodedetailscsy?aisheCode=${aisheCode}&currentSurveyYear=${csy}`,
      httpOptions1
    );
  }
  checkAisheCode(aisheCode: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}userdetailsasaishecode?aisheCode=${aisheCode}`
    );
  }
  getSurveyYearList(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}opensurveyyearforaishe`);
  }
  getSurveyYearListPrivate(): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}opensurveyyearforaishe-private`
    );
  }

  getSurveyYear(): Observable<any> {
    return this.http.get(`${environment.instituMURL}/apis/getSurveyYear`);
  }
  getProgress(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/progressMonitoring?surveyYear=${payload.surveyYear}&stateCode=${payload.stateCode}&universityId=${payload.universityId}&fromDate=${payload.fromDate}&toDate=${payload.toDate}&institutionType=${payload.institutionType}&roleId=${payload.roleId}`
    );
  }
  getProgressById(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/progressMonitoringByType?surveyYear=${payload.surveyYear}&stateCode=${payload.stateCode}&universityId=${payload.universityId}&fromDate=${payload.fromDate}&toDate=${payload.toDate}&institutionType=${payload.institutionType}&type=${payload.typeId}&formType=${payload.formType}`
    );
  }
  getWebdcfLogReports(payload: any) {
    return this.http.get(
      `${environment.baseUrlAishe}webdcf-log-reports?action=${payload.action}&instituteType=${payload.instituteType}&surveyYear=${payload.surveyYear}&stateCode=${payload.stateCode}`,
      { responseType: 'blob' }
    );
  }
  getLevelAction(roleId: any, approvingAuthority: any): Observable<any> {
    if (approvingAuthority) {
      return this.http.get(
        `${environment.masterUrl}/api/user-permission?approvingAuthority=${approvingAuthority}`
      );
    } else {
      return this.http.get(
        `${environment.masterUrl}/api/user-permission?roleId=${roleId}`
      );
    }
  }

  getUserRole(): Observable<any> {
    return this.http.get(`${environment.masterUrl}/api/user-role`);
  }
  getUserRoleId(level: any): Observable<any> {
    return this.http.get(
      `${environment.masterUrl}/api/user-role?levelId=${level}`
    );
  }
  // approvDisapproved(payload: any): Observable<any> {
  //   return this.http.post(`${environment.baseUrlAishe}api/user/approval`, payload)
  // }
  approvDisapproved(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/user/approve-disapprove`,
      payload
    );
  }
  getSummary(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/progressMonitoringByBasicCount?institutionType=ALL&surveyYear=${payload.surveyYear}`
    );
  }
  getDayWiseMonitering(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/progressMonitoringByBasicCount?surveyYear=${payload.surveyYear}&institutionType=ALL&fromDate=${payload.fromDate}&toDate=${payload.toDate}`
    );
  }
  getStartEndYear(value: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}getYear?surveyYear=${value}`
    );
  }

  getProgramme(): Observable<any> {
    return this.http.get<any>(`${environment.masterUrl}/api/program-name`);
  }

  getLevel(): Observable<any> {
    return this.http.get<any>(`${environment.masterUrl}/api/course-level`);
  }

  getBDGC(): Observable<any> {
    return this.http.get<any>(`${environment.masterUrl}/api/broad-category`);
  }
  getProgrammeFilter(levelId: any): Observable<any> {
    return this.http.get<any>(
      `${environment.masterUrl}/api/program-name/${levelId}`
    );
  }
  getBDGCFilter(programId: any): Observable<any> {
    return this.http.get<any>(
      `${environment.masterUrl}/api/broad-category/program-name?id=${programId}`
    );
  }

  getBDGNFilter(categoryId: any): Observable<any> {
    return this.http.get<any>(
      `${environment.masterUrl}/api/broad-name/categ/${categoryId}`
    );
  }

  getBDGN(): Observable<any> {
    return this.http.get<any>(`${environment.masterUrl}/api/broad-name`);
  }

  getBDGMapping(): Observable<any> {
    return this.http.get<any>(
      `${environment.masterUrl}/api/broad-group-category-mapping`
    );
  }

  addProgramme(payload: any): Observable<any> {
    return this.http.post(
      `${environment.programmeUrl}/api/program-name`,
      payload
    );
  }

  deleteSurveyYearsData(payload: any): Observable<any> {
    console.log(payload);
    return this.http.post(
      `${environment.programmeUrl}/api/delete-survey-data`,
      payload
    );
  }

  addBDGC(payload: any): Observable<any> {
    return this.http.post(
      `${environment.programmeUrl}/api/broad-discipline-group-category`,
      payload
    );
  }
  addBDGN(payload: any): Observable<any> {
    return this.http.post(
      `${environment.programmeUrl}/api/broad-discipline-group`,
      payload
    );
  }

  addBDGMapping(payload: any): Observable<any> {
    return this.http.post(
      `${environment.programmeUrl}/api/broad-discipline-group-category-mapping`,
      payload
    );
  }
  saveUserRegistration(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/userregistration`,
      payload
    );
  }
  saveUserRegistrationNew(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/userregistration-new`,
      payload
    );
  }
  saveUserRegistrationNewPrivate(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/userregistration-private`,
      payload
    );
  }
  upload(file: any, userId: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}documentuploaduser?userId=${userId}`,
      file
    );
  }

  uploadNew(file: any, userId: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}documentuploaduser-new?userId=${userId}`,
      file
    );
  }

  getDownloadFile1(payload: any, loginMode: any) {
    if (loginMode === 'U') {
      var endPoint = 'udcf';
    } else if (loginMode === 'S') {
      var endPoint = 'sdcf';
    } else {
      var endPoint = 'cdcf';
    }
    window.location.href = `${environment.pdf}/api/client/${endPoint}?aisheCode=${payload.aisheCode}&surveyYear=${payload.surveyYear}`;
  }
  getCertificateDownload(payload: any) {
    window.location.href = `${environment.pdf}/api/client/certificate-dcf?aisheCode=${payload.aisheCode}&surveyYear=${payload.surveyYear}&institutionName=${payload.institutionName}&nodalOfficerName=${payload.nodalOfficerName}`;
  }

  getDownloadFile2(payload: any, loginMode: any) {
    let endPoint;
    if (payload.surveyYear < '2020') {
      window.location.href = `${environment.pdf}/api/client/till-2019-common-dcf-teacher-otherminority?aisheCode=${payload.aisheCode}&surveyYear=${payload.surveyYear}&documentType=teacher`;
    } else {
      if (loginMode === 'U') {
        endPoint = 'u-teacher-dcf';
      } else if (loginMode === 'S') {
        endPoint = 's-teacher-dcf';
      } else {
        endPoint = 'c-teacher-dcf';
      }
      window.location.href = `${environment.pdf}/api/client/${endPoint}?aisheCode=${payload.aisheCode}&surveyYear=${payload.surveyYear}`;
    }
  }
  getDownloadFile3(payload: any, loginMode: any) {
    if (payload.surveyYear < '2020') {
      window.location.href = `${environment.pdf}/api/client/till-2019-common-dcf-teacher-otherminority?aisheCode=${payload.aisheCode}&surveyYear=${payload.surveyYear}&documentType=other minority`;
    }
  }
  getDownloadFiletill2019(payload: any, type: any) {
    window.location.href = `${environment.pdf}/api/client/till-2019-common-dcf-teacher-otherminority?aisheCode=${payload.aisheCode}&surveyYear=${payload.surveyYear}&documentType=${type}`;
  }
  getOfficerList(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlGetApi1}/api/persondetailswebdcf?instituteType=${payload.instituteType}&aisheCode=${payload.aisheCode}&latestSurveyYear=${payload.surveyYear}&currentSurveyYear=${payload.surveyYear}`
    );
  }
  getLockStatus(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlGetApi1}/api/lockdata?surveyYear=${payload.surveyYear}&aisheCode=${payload.aisheCode}&instituteType=${payload.instituteType}`
    );
  }
  getUniversity(surveyYear: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}collegecountuniversitywise?surveyYear=${surveyYear}`
    );
  }
  getUniversityCount(): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}collegecountuniversitywise`
    );
  }

  getUniversityWiseOffCampus(payLoad: any): Observable<any> {
    return this.http.get(
      `${environment.instituMURL}/apis/off-campus?aisheCode=${payLoad.aisheCode}&responseType=${payLoad.responseType}&surveyYear=${payLoad.surveyYear}`
    );
  }
  collegeDetailsByUnivId(universityId: any, surveyYear: any): Observable<any> {
    return this.http.get(
      `${environment.instituMURL}/college/getUniversityCollegeList?universityId=${universityId}`
    );
  }
  getApprovingAuth(
    roleId: any,
    stateCode: any,
    universityId: any
  ): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}approving-authority?roleId=${roleId}&stateCode=${stateCode}&universityId=${universityId}`
    );
  }
  getUniversityList(stateCode: any): Observable<any> {
    return this.http.get(
      `${environment.masterUrl}/api/university?stateCode=${stateCode}&year=0`
    );
  }
  getUniversityListRemunaration(stateCode: any): Observable<any> {
    return this.http.get(
      `${environment.masterUrl}/api/university?stateCode=${stateCode}&year=${0}`
    );
  }
  getAllData(data: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/remuneration-report?surveyYear=${data.surveyYear}&aisheCode=${data.aisheCode}&institutionType=${data.institutionType}&stateCode=${data.stateCode}&districtCode=${data.districtCode}&fromDate=${data.fromDate}&toDate=${data.toDate}&instituteSubType=${data.categoryType}&universityId=${data.universityId}&exportType=${data.exportType}&status=${data.status}`
    );
  }
  getInstituteData(aisheCode: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}createdinstitutedetailsaishecode?aisheCode=${aisheCode}`
    );
  }
  getInstituteDetail(aisheCode: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}userdetailsasaishecodeall?aisheCode=${aisheCode}`
    );
  }
  updateUser(payload: any): Observable<any> {
    return this.http.post(`${environment.baseUrlAishe}api/userupdate`, payload);
  }
  institutionType(value: any): Observable<any> {
    return this.http.get(
      `${environment.masterUrl}/api/institutiontypes?type=${value}`
    );
  }
  getAisheCode(payload: any): Observable<any> {
    if (payload.institutionCategory === 'R') {
      return this.http.get(
        `${environment.masterUrl}/api/knowYourAisheCodeList?stateCode=${payload.stateCode}&districtCode=${payload.districtCode}&institutionCategory=${payload.institutionCategory}&subCategory=${payload.subCategory}`
      );
    }
    return this.http.get(
      `${environment.masterUrl}/api/knowYourAisheCodeList?stateCode=${payload.stateCode}&districtCode=${payload.districtCode}&institutionCategory=${payload.institutionCategory}&universityId=${payload.universityId}&subCategory=${payload.subCategory}&status=${payload.status}`
    );
  }
  getRequestId(requestId: any): Observable<any> {
    return this.http.get(
      `${environment.instituMURL}/apis/track-your-request?requestId=${requestId}`
    );
  }
  getCisoList(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}api/ciso`);
  }
  // getInstituteName(aisheCode: any): Observable<any> {
  //   return this.http.get(
  //     `${environment.baseUrlAisheCode}api/institution?aisheCode=${aisheCode}`
  //   );
  // }

  saveCisoForm(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/ciso-info-data`,
      payload,
      httpOptions
    );
  }
  deleteCISO(item: any): Observable<any> {
    return this.http.delete(
      `${environment.baseUrlAishe}api/delete-ciso-info?agencyCode=${item.agencyCode}&post=${item.post}`
    );
  }
  assignSpecialPermission(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}assignspecialpermission?instituteType=${payload.instituteType}&aisheCode=${payload.aisheCode}&username=${payload.userId}&specialPermission=${payload.specialPermission}&userKey=MOEUSERONLY&remarks=${payload.remarks}`,
      httpOptions
    );
  }
  getAgencyName(agencyType: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/ciso-by-category?category=${agencyType}`
    );
  }
  getWebsiteDetails(): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/ciso-application-details`
    );
  }
  saveUpdateWebsite(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/ciso-save-application-details`,
      payload
    );
  }
  deleteWebsite(id: any): Observable<any> {
    return this.http.delete(
      `${environment.baseUrlAishe}api/delete-ciso-application-details?id=${id}`
    );
  }
  getAgencyList(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}api/ciso-hei-md`);
  }
  deleteAgency(item: any): Observable<any> {
    return this.http.delete(
      `${environment.baseUrlAishe}api/delete-ciso-hei-md?agencyCode=${item.cisoEmadedPK.aisheCode}&instituteFullName=${item.cisoEmadedPK.instituteFullName}`
    );
  }
  saveAgency(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/ciso-hei-md`,
      payload,
      httpOptions
    );
  }
  getNotFilledCISO(): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/ciso-hei-md-notinciso`
    );
  }
  getNapix() {
    return this.http.get(
      `https://delhigw.napix.gov.in/nic/hediv/aishe-api/aisheCodeDetailsForNSP?aisheCode=C-12346`
    );
  }
  userRoleMapping(): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe
      }userrolemapping?userId=${this.localService.getData('userId')}`
    );
  }
  getPrevillage(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}userrole`);
  }
  getPrevillagePrivate(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}userrole-private`);
  }
  getSurveyAction(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}surveyaction`);
  }
  getSurveyLog(surveyYear: any, actionLog: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}getSurveyLog?surveyYear=${surveyYear}&actionLog=${actionLog}`
    );
  }
  saveSurvey(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}saveupdatesurveymasternew`,
      payload
    );
  }
  getSurveyData(surveyYear: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}surveymasternew?surveyYear=${surveyYear}`
    );
  }
  getIsSurveyFreezed(value: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}surveymasternew?isSurveyFreezed=${value}`
    );
  }
  getNetworkSwitch(): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/ciso-switchnetworkdetails`
    );
  }
  getServerDetails(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}api/ciso-serverdetails`);
  }
  getSystemDetails(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}api/ciso-systemdetails`);
  }

  saveSwitchDetails(data: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/ciso-switchnetworkdetails`,
      data
    );
  }
  saveServerDetails(data: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/ciso-serverdetails`,
      data
    );
  }
  saveSystemDetails(data: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/ciso-systemdetails`,
      data
    );
  }
  deleteSystem(id: any): Observable<any> {
    return this.http.delete(
      `${environment.baseUrlAishe}api/delete-ciso-systemdetails?systemId=${id}`
    );
  }
  deleteSwitch(id: any): Observable<any> {
    return this.http.delete(
      `${environment.baseUrlAishe}api/delete-ciso-switchnetworkdetails?networkId=${id}`
    );
  }
  deleteServer(id: any): Observable<any> {
    return this.http.delete(
      `${environment.baseUrlAishe}api/delete-ciso-serverdetails?serverId=${id}`
    );
  }
  getDateTime(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}localdatetime`);
  }

  getUserData(data: any): Observable<any> {
    return this.http.post(`${environment.baseUrl}login`, data);
  }
  getDataUserById(emailId: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlPostApi2}/getUserData?emailId=${emailId}`
    );
  }
  regiData(data: any): Observable<any> {
    return this.http.post(environment.baseUrlPostApi2 + '/registerUser', data);
  }
  emailVerify(obj: any): Observable<any> {
    return this.http.post(
      environment.baseUrl + `checkEmailExist?emailId=${obj}`,
      httpOptions
    );
  }
  editUserPorfile(id: any): Observable<any> {
    return this.http.get(environment.baseUrl + 'UserByUserId/' + id);
  }
  getusertype(): Observable<any> {
    return this.http.get(environment.baseUrl + 'getUserType');
  }

  getInstituteEditDetail(payload: any): Observable<any> {
    return this.http.get(
      environment.baseUrlGetApi1 +
      `/institute-detail?aisheCode=${payload.aisheCode}`
    );
  }
  userMasterRequestByAisheCode(payload: any): Observable<any> {
    return this.http.post(
      environment.baseUrlAishe +
      `api/user-master-request-by-aisheCode?aisheCode=${payload.aisheCode}`,
      httpOptions
    );
  }
  getInstituteEditDetailPrivate(payload: any): Observable<any> {
    return this.http.get(
      environment.baseUrlGetApi1 +
      `/institute-detail-private?aisheCode=${payload.aisheCode}`
    );
  }
  getManagementTypeData(): Observable<any> {
    return this.http.get<any>(`${environment.masterUrl}/api/management`);
  }
  getManagementTypeOwnershipData(data: any): Observable<any> {
    return this.http.get<any>(
      `${environment.masterUrl}/api/management?institutionCategory=${data}`
    );
  }

  otherAffiliatingUniversity(year: any): Observable<any> {
    return this.http.get<any>(
      `${environment.masterUrl}/api/university?year=${year}`
    );
  }

  statutoryBody(): Observable<any> {
    const httpOptions1 = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        // Authorization: `Bearer ${sessionStorage.getItem("token")}`
      }),
    };
    return this.http.get(
      `${environment.masterUrl}/api/statutory-body-public`,
      httpOptions1
    );
  }

  ministryName(): Observable<any> {
    const httpOptions1 = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        // Authorization: `Bearer ${sessionStorage.getItem("token")}`
      }),
    };
    return this.http.get(`${environment.masterUrl}/api/ministry`, httpOptions1);
  }
  getGender(): Observable<any> {
    return this.http.get<any>(`${environment.masterUrl}/api/gender`);
  }
  getOwnership(data: any): Observable<any> {
    return this.http.get<any>(
      `${environment.masterUrl}/api/ownership?institutionCategory=${data}`
    );
  }
  getUrban(distCode: any): Observable<any> {
    return this.http.get<any>(
      `${environment.masterUrl}/api/urbanlocalbody?district=${distCode}`
    );
  }
  getBlock(distCode: any) {
    return this.http.get<any>(
      `${environment.masterUrl}/api/block?district=${distCode}`
    );
  }

  getcountry(): Observable<any> {
    return this.http.get(`${environment.masterUrl}/api/country`);
  }

  getcountryPrivate(): Observable<any> {
    return this.http.get(`${environment.masterUrl}/api/country-private`);
  }
  getUniversityEditReg(currentSurveyYear: any) {
    return this.http.get<any>(
      `${environment.masterUrl}/api/university?year=${currentSurveyYear}&stateCode=ALL`
    );
  }
  uploadCisoFile(payload: any, file: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/ciso-document?id=${payload.id}&title=${payload.title}&documentType=${payload.documentType}&uploadedBy=${payload.uploadedBy}`,
      file
    );
  }
  uploadCisoFileList(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}api/ciso-document`);
  }
  deleteDocument(id: any): Observable<any> {
    return this.http.delete(
      `${environment.baseUrlAishe}api/delete-ciso-document?documentId=${id}`
    );
  }
  forgotDataUserPassword(obj: any): Observable<any> {
    return this.http.post(
      environment.baseUrlPostApi2 + `/forgot_password?emailId=${obj}`,
      httpOptions
    );
  }
  getNTAAnswer(ins: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}nta-questionnaire?aisheCode=${ins}`
    );
  }
  saveNTAAnswer(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}nta-questionnaire`,
      payload
    );
  }

  surveyYearsRemuneration(aisheCode: any, surveyYear: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/check-eligibility-by-survey-year?aisheCode=${aisheCode}&surveyYear=${surveyYear}`
    );
  }

  saveRemuneration(data: any, formdata: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/bankDetails?surveyYear=${data.surveyYear
      }&userId=${this.localService.getData('userId')}&accountHolderName=${data.accountHolderName
      }&accountNumber=${data.accountNumber}&bankAddress=${data.bankAddress
      }&bankName=${data.bankName}&ifsc_code=${data.ifsc_code}&pan=${data.pan
      }&aisheCode=${this.localService.getData('aisheCode')}&pincode=${data.pincode
      }`,
      formdata
    );
  }

  getAccountDetails(survey: any, aisheCode: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/bankDetails?surveyYear=${survey}&aisheCode=${aisheCode}`
    );
  }
  getUniversityRemu(universityId: any, survey: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/get-affiliated-college-count?universityId=${universityId}&surveyYear=${survey}`
    );
  }
  getRemunerationStatus(survey: any, userId: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}api/getRemunerationStatus?surveyYear=${survey}&userId=${userId}`
    );
  }
  getInstitutionManagementRole(rollId: any): Observable<any> {
    return this.http.get(
      environment.masterUrl + '/api/institution-by-role?roleId=' + rollId,
      httpOptions
    );
  }

  institutionCheck(payload: any): Observable<any> {
    return this.http.get<any>(
      `${environment.baseUrlAishe}institutenameexistinaishe?districtCode=${payload.districtCode}&instituteName=${payload.instituteName}`
    );
  }

  saveRequestForm(payload: any): Observable<any> {
    // return this.http.post(
    //   `${environment.baseURLRequest}requestforaddinstitute?instituteTypeCorS=${payload.instituteTypeCorS}&instituteName=${payload.instituteName}
    // &stateCode=${payload.stateCode}&districtCode=${payload.districtCode}&admissionYear=${payload.admissionYear}&collegeTypeId=${payload.collegeTypeId}&managementId=${payload.managementId}&universityId=${payload.universityId}&isAffiliatedToOtherUniversity=${payload.isAffiliatedToOtherUniversity}&otherAffiliatedUniversityName=${payload.otherAffiliatedUniversityName}&admissionProcessCompleted=${payload.admissionProcessCompleted}&locationId=${payload.locationId}&type=${payload.type}&roleId=${payload.roleId}&isDeclarationAccepted=${payload.isDeclarationAccepted}&surveyYear=${payload.surveyYear}&personLine1=${payload.personLine1}&personLine2=${payload.personLine2}&personCity=${payload.personCity}&personStateCode=${payload.personStateCode}&personDistrictCode=${payload.personDistrictCode}&directorName=${payload.directorName}&directorEmail=${payload.directorEmail}&directorMobile=${payload.directorMobile}&directorGender=${payload.directorGender}&directorDesignation=${payload.directorDesignation}&personName=${payload.personName}&personDesignation=${payload.personDesignation}&personEmail=${payload.personEmail}&personMobile=${payload.personMobile}&personLandline=${payload.personLandline}&stdCode=${payload.stdCode}&personGender=${payload.personGender}&userId=${payload.userId}&bcryptPassword=${payload.bcryptPassword}&firstName=${payload.firstName}&middleName=${payload.middleName}&lastName=${payload.lastName}&personPincode=${payload.personPincode}&stateBodyId=${payload.stateBodyId}&lattitude=${payload.lattitude}&longitude=${payload.longitude}&websiteUrl=${payload.websiteUrl}&yearOfEstablishment=${payload.yearOfEstablishment}&stateBodyTypeId=${payload.stateBodyTypeId}
    // &subDistrictId=${payload.subDistrictId}&ulbId=${payload.ulbId}&blockId=${payload.blockId}&isGeospatialDataVerified=${payload.isGeospatialDataVerified}&otherAffiliatingUniversity=${payload.otherAffiliatingUniversity}&statuatoryBody=${payload.statuatoryBody}&isOtherAffiliatingUniversityStatuatoryBody=${payload.isOtherAffiliatingUniversityStatuatoryBody}&ministryId=${payload.ministryId}`,
    //   payload.file);
    // let formData: FormData = new FormData();
    // formData.append('responseVo', payload.instituteTypeCorS);
    // formData.append('instituteName', payload.instituteName);
    // formData.append('stateCode', payload.stateCode);
    // formData.append('collegeTypeId', payload.collegeTypeId);
    // formData.append('managementId', payload.managementId);
    // formData.append('universityId', payload.universityId);
    // formData.append('isAffiliatedToOtherUniversity', payload.isAffiliatedToOtherUniversity);
    // formData.append('otherAffiliatedUniversityName', payload.otherAffiliatedUniversityName);
    // formData.append('admissionProcessCompleted', payload.admissionProcessCompleted);
    // formData.append('locationId', payload.locationId);
    // formData.append('type', payload.type);
    // formData.append('roleId', payload.roleId);
    // formData.append('isDeclarationAccepted', payload.isDeclarationAccepted);
    // formData.append('surveyYear', payload.surveyYear);
    // formData.append('personLine1', payload.personLine1);
    // formData.append('personLine2', payload.personLine2);
    // formData.append('personCity', payload.personCity);
    // formData.append('personStateCode', payload.personStateCode);
    // formData.append('personDistrictCode', payload.personDistrictCode);
    // formData.append('directorName', payload.directorName);
    // formData.append('directorEmail', payload.directorEmail);
    // formData.append('directorMobile', payload.directorMobile);
    // formData.append('directorGender', payload.directorGender);
    // formData.append('directorDesignation', payload.directorDesignation);
    // formData.append('personName', payload.personName);
    // formData.append('personDesignation', payload.personDesignation);
    // formData.append('personEmail', payload.personEmail);
    // formData.append('personMobile', payload.personMobile);
    // formData.append('personLandline', payload.personLandline);
    // formData.append('stdCode', payload.stdCode);
    // formData.append('personGender', payload.personGender);
    // formData.append('userId', payload.userId);
    // formData.append('bcryptPassword', payload.bcryptPassword);
    // formData.append('firstName', payload.firstName);
    // formData.append('middleName', payload.middleName);
    // formData.append('lastName', payload.lastName);
    // formData.append('personPincode', payload.personPincode);
    // formData.append('stateBodyId', payload.stateBodyId);
    // formData.append('lattitude', payload.lattitude);
    // formData.append('longitude', payload.longitude);
    // formData.append('websiteUrl', payload.websiteUrl);
    // formData.append('yearOfEstablishment', payload.yearOfEstablishment);
    // formData.append('stateBodyTypeId', payload.stateBodyTypeId);
    // formData.append('subDistrictId', payload.subDistrictId);
    // formData.append('ulbId', payload.ulbId);
    // formData.append('blockId', payload.blockId);
    // formData.append('isGeospatialDataVerified', payload.isGeospatialDataVerified);
    // formData.append('otherAffiliatingUniversity', payload.otherAffiliatingUniversity);
    // formData.append('otherAffiliatingUniversity', payload.otherAffiliatingUniversity);
    // formData.append('statuatoryBody', payload.statuatoryBody);
    // formData.append('isOtherAffiliatingUniversityStatuatoryBody', payload.isOtherAffiliatingUniversityStatuatoryBody);
    // formData.append('ministryId', payload.ministryId);
    // formData.append('file', payload.file)

    return this.http.post<any>(
      `${environment.baseURLRequest}requestforaddinstitute`,
      payload
    );
  }
  editRnDInstitute(payload: any): Observable<any> {
    return this.http.post<any>(
      `${environment.instituMURL}/apis/edit-rnd-institution`,
      payload
    );
  }
  getAdmissionYearList(): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}surveyyearforaddinstituterequest`
    );
  }
  sendMessage(requestId: any, instituteName: string): Observable<any> {
    return this.http.get(
      `${environment.otpURL}sendmailandsmsforrequestid?requestId=${requestId}&instituteName=${instituteName}`
    );
  }
  getAisheCodeDetails(aisheCode: any, institutionType: any): Observable<any> {
    return this.http.get(
      `${environment.baseURLRequest}checkalready-aishecode?aisheCode=${aisheCode}&institutionType=${institutionType}`
    );
  }

  subBodyType(id: any): Observable<any> {
    return this.http.get(`${environment.masterUrl}/api/ref-statebody-type`);
  }
  getForeignInstitutes(countryId: any): Observable<any> {
    return this.http.get(
      `${environment.masterUrl}/api/foreign-institute-by-country/${countryId}`
    );
  }
  getForeignInstitutesList(payload: any): Observable<any> {
    return this.http.get(
      `${environment.masterUrl}/api/foreign-institute-by-country/${payload.countryId}?status=${payload.statusId}`
    );
  }

  saveForeignInstitutes(body: any): Observable<any> {
    return this.http.post<any>(
      `${environment.programmeUrl}/api/foreigninstitutes`,
      body
    );
  }
  getEmailSMSData(payload: any): Observable<any> {
    return this.http.get<any>(`${environment.baseUrlAishe}contact-detail`, {
      params: payload,
    });
  }
  getInstituteType(type: any) {
    return this.http.get<any>(
      `${environment.masterUrl}/institutiontype?type=${type}`
    );
  }

  saveEnrolEstimation(obj: any): Observable<any> {
    return this.http.post<any>(
      `${environment.programmeUrl}/api/enrollment-estimation-state-district-wise`,
      obj
    );
  }

  getEnrolEstimation(stateCode: any): Observable<any> {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/enrollment-estimation-state-district-wise`,
      { params: stateCode }
    );
  }
  downalodEstimationReport(payload: any) {
    // window.location.href = `${environment.pdf}/api/client/till-2019-common-dcf-teacher-otherminority?aisheCode=${payload.aisheCode}&surveyYear=${payload.surveyYear}&documentType=other minority`

    window.location.href = `${environment.pdf}/api/college-Estimated-Enrollment?surveyYear=${payload.surveyYear}&districtCode=${payload.districtCode}`;
  }
  getDistrictWiseEnrol(payload: any): Observable<any> {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/api/district-wise-enrollment`,
      {
        params: payload,
      }
    );
  }
  // getUniversalSearch(payload: any): Observable<any> {
  //   return this.http.get<any>(`${environment.baseUrlGetApi1}/institute-detail`, { params: payload })
  // }
  getUniversalSearch(payload: any): Observable<any> {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/universal-search`,
      { params: payload }
    );

    //     return this.http.get<any>(
    //       `
    // https://c-api.aishe.nic.in/aishehibernategetapi/universal-search`,
    //       { params: payload }
    //     );
  }
  getDetailOfLastUpdateDCF(payload: any): Observable<any> {
    return this.http.get<any>(`${environment.baseUrlGetApi1}/form-upload`, {
      params: payload,
    });
  }
  getSearchbyAISHE(payload: any) {
    const headers = new HttpHeaders({
      'NIC-Client-ID': '2c88ac630e02eefca150502b58f2fa0e',
      accept: 'application/json',
    });
    const params = {
      aisheCode: payload.aisheCode,
      surveyYear: payload.surveyYear,
    };
    const apiUrl =
      'https://delhigw.napix.gov.in/nic/hediv/basicInfoByAisheCode/instituteBasicInfoByAisheCode';
    return this.http.get(apiUrl, { headers, params });
  }
  // getState(): Observable<any> {
  //   return this.http.get<any>(`${environment.masterUrl}state`)
  // }
  // getDistrict(stateCode: any): Observable<any> {
  //   return this.http.get<any>(`${environment.masterUrl}district/${stateCode}`)
  // }
  // getManagementType(): Observable<any> {
  //   return this.http.get<any>(`${environment.masterUrl}ownership`)
  // }
  // getCollegeType(): Observable<any> {
  //   return this.http.get<any>(`${environment.masterUrl}university-type`)
  // }
  // getStandAloneBody(): Observable<any> {
  //   return this.http.get<any>(`${environment.masterUrl}bodytype`)
  // }
  // affiliatingUniv(stateCode: any, year: any): Observable<any> {
  //   return this.http.get<any>(`${environment.masterUrl}university?stateCode=${stateCode}&year=${year}`)
  // }
  // otherAffiliatingUniv(stateCode: any, year: any): Observable<any> {
  //   return this.http.get<any>(`${environment.masterUrl}university?stateCode=${stateCode}&year=${year}`)
  // }
  sendEmailToNadals(file: any, data: any, ccAllowed: any): Observable<any> {
    return this.http.post(
      `${environment.otpURL}send-email-in-bulk`,
      file,
      httpOptions
    );
  }
  getProgrammeList(payload: any) {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/api/courses-webdcf`,
      { params: payload }
    );
  }
  booleanValue(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlGetApi1}/hasregionalanddistance?instituteType=${payload.loginMode}&aisheCode=${payload.aisheCode}&currentSurveyYear=${payload.currentSurveyYear}`
    );
  }
  getIndianLanguageList() {
    return this.http.get<any>(`${environment.masterUrl}/api/langauge`);
  }
  getStatuatoryBodyProgram(): Observable<any> {
    return this.http.get(`${environment.masterUrl}/api/program-statuary`);
  }
  getEnrollmentList(payload: any): Observable<any> {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/api/enrollment-webdcf?&aisheCode=${payload.aisheCode}&currentSurveyYear=${payload.currentSurveyYear}&modeId=${payload.modeId}`
    );
  }
  getOffCentre1(universityId: any, surveyYear: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlGetApi1}/off-campus/off-campus?universityId=${universityId}&surveyYear=${surveyYear}`
    );
  }

  getForeignStudentList(payload: any) {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/api/foreign-enrollment-webdcf?currentSurveyYear=${payload.currentSurveyYear}&aisheCode=${payload.aisheCode}&modeId=${payload.modeId}`
    );
  }

  getEnrollmentListO(payload: any): Observable<any> {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/off-campus/enrollment?&aisheCode=${payload.id}&surveyYear=${payload.currentSurveyYear}`
    );
  }
  getEnrollmentRegionalList(payload: any) {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/api/rc-enrollment-webdcf?&aisheCode=${payload.aisheCode}&currentSurveyYear=${payload.currentSurveyYear}`
    );
  }
  getOtherMinorityBreakup(payload: any) {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/api/otherMinority-enrollment-webdcf?&aisheCode=${payload.aisheCode}&currentSurveyYear=${payload.currentSurveyYear}&enrollmentId=${payload.enrollmentId}`
    );
  }

  getFacultyList(payload: any) {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/api/faculty-webdcf?aisheCode=${payload.aisheCode}&latestSurveyYear=${payload.latestSurveyYear}&currentSurveyYear=${payload.currentSurveyYear}&instituteType=${payload.loginMode}`
    );
  }
  getDepartmentList(payload: any) {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/api/department-webdcf?aisheCode=${payload.aisheCode}&latestSurveyYear=${payload.latestSurveyYear}&currentSurveyYear=${payload.currentSurveyYear}&instituteType=${payload.loginMode}`
    );
  }
  getRegionalCenter(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlGetApi1}/api/university/getUniversityRegionalCenters?aisheCode=${payload.aisheCode}&latestSurveyYear=${payload.latestSurveyYear}&currentSurveyYear=${payload.currentSurveyYear}`
    );
  }
  getInstitutionDetails(payload: any): Observable<any> {
    return this.http.get(
      `${environment.instituMURL}/apis/combined-operation/${payload.surveyYear}?type=${payload.type}`
    );
  }
  getDeleteInstitutionDetails(payload: any): Observable<any> {
    return this.http.get(
      `${environment.instituMURL}/apis/combined-operation/${payload.surveyYear}?type=${payload.type}`
    );
  }
  getSurveyWiseMerge(payload: any): Observable<any> {
    return this.http.get(
      `${environment.instituMURL}/apis/merge-college-full-detail`,
      { params: payload }
    );
  }
  getParticipatedIns(payload: any): Observable<any> {
    return this.http.get(
      `${environment.instituMURL}/apis/participated-institution`,
      { params: payload }
    );
  }

  getUserDetailsByAisheCode(payload: any): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}api/user-status`, {
      params: payload,
    });
  }
  getUserReqAishe(aisheCode: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/user-master-request-by-aisheCode`,
      aisheCode
    );
  }
  getTitleList(): Observable<any> {
    return this.http.get(`${environment.masterUrl}/api/ref-title`);
  }
  getTitleListPrivate(): Observable<any> {
    return this.http.get(`${environment.masterUrl}/api/ref-title-private`);
  }

  getMissingUser(institutionType: string): Observable<any> {
    return this.http.post<any>(
      `${environment.baseUrlAishe}api/active-institute-inactive-user-or-pending?institutionType=${institutionType}`,
      httpOptions
    );
  }
  getOtherMinority(payload: any): Observable<any> {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/hasregionalanddistance`,
      {
        params: payload,
      }
    );
  }
  userLogout(): Observable<any> {
    return this.http.post<any>(
      `${environment.programmeUrl}/api/user-logout`,
      httpOptions
    );
  }
  getFeedBackList(Obj: any, currentSurveyYear: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlGetApi2}/api/feedback/${currentSurveyYear}`
    );
  }
  getFeedBackPost(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlPostApi2}/api/feedback`,
      payload
    );
  }
  getPageStatus(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlGetApi2}/api/feedback/${payload.year}?stateCode=${payload.stateCode}&roleId=${payload.roleId}`
    );
  }
  saveFeedBackForm(payload: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlPostApi2}/api/feedback`,
      payload
    );
  }
  administrativeMinistry(): Observable<any> {
    return this.http.get(`${environment.masterUrl}/api/ref-ministry-onos`);
  }
  approveReq(payload: any): Observable<any> {
    return this.http.post(
      `${environment.instituMURL}/apis/rd-institution-approved-by-moe`,
      payload
    );
  }
  getInstituteNameExist(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseURLRequest}institutenameexistinaishe`,
      { params: payload }
    );
  }
  getViewDataRND(payload: any) {
    if (payload.stateCode != '' && payload.ministry != '') {
      return this.http.get(
        `${environment.instituMURL}/apis/view-rd-institutions?ministryOnosId=${payload.ministry}&stateCode=${payload.stateCode}`
      );
    } else if (payload.ministry == '' && payload.stateCode != '') {
      return this.http.get(
        `${environment.instituMURL}/apis/view-rd-institutions?stateCode=${payload.stateCode}`
      );
    } else if (payload.stateCode == '' && payload.ministry != '') {
      return this.http.get(
        `${environment.instituMURL}/apis/view-rd-institutions?ministryOnosId=${payload.ministry}`
      );
    } else if (payload.aisheCode != null) {
      return this.http.get(
        `${environment.instituMURL}/apis/view-rd-institutions?aisheCode=${payload.aisheCode}`
      );
    } else {
      return this.http.get(
        `${environment.instituMURL}/apis/view-rd-institutions`
      );
    }
  }

  getRejectDataRND(payload: any) {
    if (payload.stateCode != '' && payload.ministry != '') {
      return this.http.get(
        `${environment.instituMURL}/apis/pending-rd-institution-request?ministryOnosId=${payload.ministry}&stateCode=${payload.stateCode}&status=${payload.status}`
      );
    } else if (payload.ministry == '' && payload.stateCode != '') {
      return this.http.get(
        `${environment.instituMURL}/apis/pending-rd-institution-request?stateCode=${payload.stateCode}&status=${payload.status}`
      );
    } else if (payload.stateCode == '' && payload.ministry != '') {
      return this.http.get(
        `${environment.instituMURL}/apis/pending-rd-institution-request?ministryOnosId=${payload.ministry}&status=${payload.status}`
      );
    } else {
      return this.http.get(
        `${environment.instituMURL}/apis/pending-rd-institution-request?status=${payload.status}`
      );
    }
  }
  // pending-rd-institution-request

  getApproveRND(payload: any) {
    return this.http.get<any>(
      `${environment.instituMURL}/apis/pending-rd-institution-request?stateCode=${payload.stateCode}&ministryOnosId=${payload.ministryId}&requestId=${payload.requestId}`
    );
  }

  rejectApproveRND(data: any) {
    return this.http.post<any>(
      `${environment.instituMURL}/apis/rd-institution-approved-by-moe`,
      data
    );
  }

  editUser(payload: any, isUpdated: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}api/userupdate?isUpdated=${isUpdated}`,
      payload
    );
  }
  getCurrentDateTime() {
    return this.http.get<any>(
      `${environment.baseUrlGetApi1}/api/server-datetime`
    );
  }
  getStateWiseProgress(pay: any): Observable<any> {
    if (pay.stateCode && pay.type && pay.surveyYear) {
      return this.http.get(
        `${environment.baseUrlAishe}api/progress-monitoring-state-wise-count?surveyYear=${pay.surveyYear}&type=${pay.type}&stateCode=${pay.stateCode}`
      );
    }
    return this.http.get(
      `${environment.baseUrlAishe}api/progress-monitoring-state-wise-count?surveyYear=${pay.surveyYears}`
    );
  }
  getSummaryData1(payLoad: any): Observable<any> {
    return this.http.get(
      `${environment.instituMURL}/apis/approved-request-detail?endDate=${payLoad.endDate}&listType=${payLoad.listType}&responseType=${payLoad.responseType}&startDate=${payLoad.startDate}&surveyYear=${payLoad.surveyYear}&type=${payLoad.type}`
    );
  }
  getInstitutioSummaryReport(obj: any, data: any) {
    let url;
    if (data === 'ApprovedReject') {
      url = '/apis/approved-request-detail';
    } else {
      url = '/apis/request_detail';
    }

    return this.http.get<any>(`${environment.instituMURL}${url}`, {
      params: obj,
    });
  }
  requestShiftUniversit(payLoad: any): Observable<any> {
    return this.http.post(
      `${environment.instituMURL}/college/institution-management-new-request`,
      payLoad
    );
  }
  approveRejectUniversit(payLoad: any): Observable<any> {
    return this.http
      .get(
        `${environment.instituMURL}/college/institution-management-new-request`,
        { params: payLoad }
      )
      .pipe(
        catchError((error) => {
          //console.error('Error occurred:', error);
          return throwError(
            () => new Error('Failed to fetch data. Please try again later.')
          );
        })
      );
  }
  uploadInternalDocs(): Observable<any> {
    return this.http.get(`${environment.baseUrlGetApi1}/api/document`);
  }
  uploadInternalFile(formdata: any): Observable<any> {
    return this.http.post(`${environment.programmeUrl}/api/document`, formdata);
  }
  getDoumentType(): Observable<any> {
    return this.http.get<any>(`${environment.baseUrlGetApi1}/api/ref-document`);
  }
  getDocumentById(id: any): Observable<any> {
    return this.http.get(`${environment.baseUrlGetApi1}/api/document?id=${id}`);
  }
  deleteInternalDocument(id: any): Observable<any> {
    return this.http.post(
      `${environment.programmeUrl}/api/delete-document?id=${id}`,
      httpOptions
    );
  }
  approveUniversity(data: any): Observable<any> {
    return this.http.post(
      `${environment.instituMURL}/college/institution-management-request-update-status`,
      data
    );
  }
  getDataSharing(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}data-sharing/api-user-information`,
      { params: payload }
    );
  }
  approveRejectDataSharing(payLoad: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}data-sharing/update-status-api-user-information`,
      payLoad
    );
  }
  getInstitutionCount(payload: any): Observable<any> {
    return this.http.get(
      `${environment.instituMURL}/institutionDirectory /getInstitutionCount`,
      { params: payload }
    );
  }
  saveOrUpdateAisheUnitCell(payLoad: any): Observable<any> {
    return this.http.post(
      `${environment.baseUrlAishe}aishe-unit-cell`,
      payLoad
    );
  }
  getAisheUnitCell(payload: any): Observable<any> {
    return this.http.get(
      `${environment.baseUrlAishe}aishe-unit-cell`,
      { params: payload }
    );
  }
  getDesignation(): Observable<any> {
    return this.http.get(
      `${environment.masterUrl}/api/teacher-designation`,
    );
  }
  deleteAisheUnitCell(payload: any) {
    return this.http.delete(
      `${environment.baseUrlAishe}aishe-unit-cell?id=${payload.id}`
    );
  }
  deleteAllAisheUnitCell(payload: any) {
    return this.http.delete(
      `${environment.baseUrlAishe}aishe-unit-cell?whetherStateHavingAisheUnitCell=${payload.whetherStateHavingAisheUnitCell}&stateCode=${payload.stateCode}`
    );
  }

  getPendingIns(payload: any,message:any) {
    if (message === 'Standalone') {
      return this.http.get<any>(`${environment.instituMURL}/standalone/pending-standalone-requests?`,
        { params: payload })
    } else {
      return this.http.get(`${environment.instituMURL}/college/pending-college-requests?`,{params:payload})
       
    }

  }
getRejectedRequest(payload:any,message:any){
 
return this.http.get<any>(`${environment.instituMURL}/apis/rejected-request?`,{params:payload})

}
getRejectedRequestList(payload:any,message:any){
 
return this.http.get<any>(`${environment.instituMURL}/apis/rejected-request?`,{params:payload})

}
  // http://10.206.194.250:8082/aisheinstitutemanagement/apis/rejected-request?listType=TOTAL&responseType=COUNT&surveyYear=0&type=COLLEGE 
}
