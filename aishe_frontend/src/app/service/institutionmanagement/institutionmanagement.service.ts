import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LocalserviceService } from '../localservice.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
@Injectable({
  providedIn: 'root',
})
export class InstitutionmanagementService {



  constructor(public http: HttpClient, public localService: LocalserviceService) { }

  postUpgradeUniversity(userData: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/college/convertCollegeToUniversity', userData)
  }
  deleteCollegeData(userData: any): Observable<any> {
    //console.log(userData);
    return this.http.post(environment.instituMURL + `/college/deleteCollege`, userData)

  }

  getLocation(): Observable<any> {
    return this.http.get(environment.instituMURL + '/apis/getLocation');
  }

  getData(surveyYear: any): Observable<any> {
    let stateCode = this.localService.getData('stateCode');
    let districtId = this.localService.getData('districtCode');
    let aisheCode: any = this.localService.getData('aisheCode');
    let aisheCode1 = aisheCode.replace(/\D/g, '');

    return this.http.get(environment.instituMURL + `/college/getUniversityCollegeList?surveyYear=${surveyYear}&universityId=${aisheCode1}`);
  }

  postUniversityEditData(userData: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/university/editUniversity', userData)
  }

  postUniversityAddData(userData: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/university/addUniversity', userData)
  }

  mergeCollegesData(userData: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/college/mergeCollege', userData)
  }

  upGradeStandaloneToCollege(userData: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/standalone/convertStandaloneToCollege', userData)
  }

  upgradeStandalonetoUniversity(userData: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/standalone/convertStandaloneToUniversity', userData)
  }

  deleteUniversityData(userData: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/university/deleteUniversity', userData)
  }
  getUniversityDetails(aisheCode: any, surveyYear: any): Observable<any> {

    return this.http.get(environment.instituMURL + `/university/getUniversityFullDetails/${aisheCode}/${surveyYear}`);
  }

  getUniversityDetailsDirectory(userData1: any, data: any, survery: any): Observable<any> {
    if (userData1 == "University") {
      return this.http.get(`${environment.instituMURL}/institutionDirectory /getUniversityDetails?id=${data}&surveyYear=${survery}`);
    }
    if (userData1 == 'College') {
      return this.http.get(`${environment.instituMURL}/institutionDirectory /getCollegeDetails?id=${data}&surveyYear=${survery}`);
    }
    if (userData1 == 'Standalone') {
      return this.http.get(`${environment.instituMURL}/institutionDirectory /getStandaloneDetails?id=${data}&surveyYear=${survery}`);
    }
    return this.http.get(`${environment.instituMURL}/institutionDirectory /getUniversityDetails?id=${data}&surveyYear=${survery}`);
  }

  getViewUniversity(data: any): Observable<any> {

    if (data.surveyYearValue && data.universityType && data.stateValue === null) {
      return this.http.get(`${environment.instituMURL}/university/getViewUniversity?surveyYear=${data.surveyYearValue}&universityType=${data.universityType}`);
    }
    if (data.surveyYearValue && data.stateValue && data.universityType === null) {

      return this.http.get(`${environment.instituMURL}/university/getViewUniversity?surveyYear=${data.surveyYearValue}&stateCode=${data.stateValue}`);
    }
    if (data.surveyYearValue && (data.stateValue=='' || data.stateValue) && (data.universityType=='' || data.universityType) && data.searchText==null) {
      return this.http.get(`${environment.instituMURL}/university/getViewUniversity?stateCode=${data.stateValue}&surveyYear=${data.surveyYearValue}&universityType=${data.universityType}`);

    }
    if(data.searchText){
      return this.http.get(`${environment.instituMURL}/university/getViewUniversity?searchText=${data.searchText}`);
    }
    return this.http.get(``);
  }

  getStandaloneFullDetails(aisheCode: any, surveyYear: any): Observable<any> {
    return this.http.get(`${environment.instituMURL}/standalone/getStandaloneFullDetails/${aisheCode}/${surveyYear}`);

  }
  getCollegeDetails(id: any, survey: any): Observable<any> {
    return this.http.get(`${environment.instituMURL}/college/getCollegeFullDetails/${id}/${survey}`);
  }
  getStandaloneApprovalReject(data: any): Observable<any> {
    return this.http.get(`${environment.instituMURL}/standalone/standaloneApprovalRequestDetails/${data.RequestId}/${data.surveyYear}`);
  }

  getApprovalStandaloneList(id: any, survey: any): Observable<any> {

    return this.http.get(`${environment.instituMURL}/standalone/getApprovedOrRejectedStandaloneList/${id}/${survey}`)
  }
  getStandalonRequestDetails(reasonId: any, survey: any): Observable<any> {

    return this.http.get(`${environment.instituMURL}/standalone/standaloneApprovalRequestDetails/${reasonId}/${survey}`);
  }
  getStandaloneApprovalList(data: any): Observable<any> {
    if (data.requestId === null) {
      return this.http.get(`${environment.instituMURL}/standalone/getStandaloneApprovalList?approvalRoleId=${data.approvalRoleId}&stateCode=${data.stateCode}&surveyYear=${data.surveyYear}&districtCode=${data.districtValue}&statebodyid=${data.standaloneType}&toDate=${data.toDate?data.toDate:null}&fromDate=${data.fromDate?data.fromDate:null}`);
    }
    return this.http.get(`${environment.instituMURL}/standalone/getStandaloneApprovalList?approvalRoleId=${data.approvalRoleId}&requestId=${data.requestId}&stateCode=${data.stateCode}&surveyYear=${data.surveyYear}&districtCode=${data.districtValue}&statebodyid=${data.standaloneType}&toDate=${data.toDate?data.toDate:null}&fromDate=${data.fromDate?data.fromDate:null}`);
  }
  getStandaloneApprovalListMoE(data: any): Observable<any> {
    if (data.requestId === null) {
      return this.http.get(`${environment.instituMURL}/standalone/pending-standalone-requests?approvalRoleId=${data.approvalRoleId}&stateCode=${data.stateCode}&surveyYear=${data.surveyYear}&districtCode=${data.districtValue}&stateBody=${data.standaloneType}&stateBodyTypeId=${data.stateBodyTypeId?data.stateBodyTypeId:''}&toDate=${data.toDate?data.toDate:null}&fromDate=${data.fromDate?data.fromDate:null}`);
    }
    return this.http.get(`${environment.instituMURL}/standalone/pending-standalone-requests?approvalRoleId=${data.approvalRoleId}&requestId=${data.requestId}&stateCode=${data.stateCode}&surveyYear=${data.surveyYear}&districtCode=${data.districtValue}&stateBody=${data.standaloneType}&stateBodyTypeId=${data.stateBodyTypeId?data.stateBodyTypeId:''}&toDate=${data.toDate?data.toDate:null}&fromDate=${data.fromDate?data.fromDate:null}`);
  }

  postStandaloneEditData(data: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/standalone/editStandalone', data)
  }

  getDeaffiliationCollegeList12(data: any): Observable<any> {

    return this.http.get(`${environment.instituMURL}/college/getDeaffiliationCollegeList?aisheCode=${data.aisheCodeValue}&surveyYear=${data.surveyYearValue}&universityId=${data.universityValue}&stateCode=${data.stateValue}&collegeType=${data.collegeType}&districtCode=${data.districtValue == null ? '' : data.districtValue}&reasonId=${data.reasonId}`);
  }

  getDeaffiliationCollegeList(data: any): Observable<any> {

    return this.http.get(`${environment.instituMURL}/college/getDeaffiliationCollegeList?reasonId=${data?.reasonId}&stateCode=${data.selectedStateCode}&surveyYear=${data.surveyYearValue}&universityId=${data.universityValue}&collegeType=${data.collegeType}&districtCode=${data.districtValue}&aisheCode=${data.aisheCodeValue}`);
  }

  getinstitutionCollegeData1(data: any): Observable<any> {

    let roleId = this.localService.getData('roleId');
    var universityId;
    if (this.localService.getData('aisheCode')) {
      let aisheCode: any = this.localService.getData('aisheCode');
      let aisheCode1 = aisheCode.replace(/\D/g, '');
      universityId = roleId == '7' ? aisheCode1 : data.universityValue
    } else {
      universityId = data.universityValue;
    }
    if (data.collegeType && data.aisheCodeValue == null) {
      //college without location and collegeType
      return this.http.get(`${environment.instituMURL}/college/getUniversityCollegeList?surveyYear=${data.surveyYearValue}&collegeType=${data.collegeType}&universityId=${universityId}&location=${data.collegeLocation}&stateCode=${data.selectedStateCode}`);
    }
    if (data.aisheCodeValue) {
      return this.http.get(`${environment.instituMURL}/college/getUniversityCollegeList?surveyYear=${data.surveyYearValue}&id=${data.aisheCodeValue}&collegeType=${data.collegeType}&stateCode=${data.selectedStateCode}&universityId=${universityId}`);
    }
    if(data.searchText){
      return this.http.get(`${environment.instituMURL}/college/getUniversityCollegeList?searchText=${data.searchText}`);
    }
    //College with location
    return this.http.get(`${environment.instituMURL}/college/getUniversityCollegeList?surveyYear=${data.surveyYearValue}&universityId=${universityId}&location=${data.collegeLocation}&stateCode=${data.selectedStateCode}&collegeType=${data.collegeType} `);

  }



  getInstitutionDetail(): Observable<any> {
    let aisheCode: any = this.localService.getData('aisheCode');
    let surveyYear = this.localService.getData('surveyYear');
    return this.http.get(
      environment.instituMURL +
      `/getInstitutionDetail?currentSurveyYear=${surveyYear}&aisheCode=${aisheCode}`,
      httpOptions
    );
  }

  getStandaloneViewData(data: any): Observable<any> {

    if (data.standaloneType === null) {
      return this.http.get(`${environment.instituMURL}/standalone/getViewStandalone?stateCode=${data.selectedStateCode}&surveyYear=${data.surveyYearValue}&roleId=${this.localService.getData('roleId')}`)
    }
    if(data.aisheCodeValue){
       let aisheCode=data.aisheCodeValue.split("-")[1];
      return this.http.get(`${environment.instituMURL}/standalone/getViewStandalone?surveyYear=${data.surveyYearValue}&roleId=${this.localService.getData('roleId')}&id=${aisheCode}`)
    }
    if(data.searchText){
      return this.http.get(`${environment.instituMURL}/standalone/getViewStandalone?searchText=${data.searchText}&roleId=${this.localService.getData('roleId')}`)
    }

    return this.http.get(`${environment.instituMURL}/standalone/getViewStandalone?stateCode=${data.selectedStateCode}&statebodyid=${data.standaloneType}&surveyYear=${data.surveyYearValue}&roleId=${this.localService.getData('roleId')}&districtCode=${data.districtValue}`)

  }

  getStandalonData(stateCode: any, surveyYear: any, standalonetype: any): Observable<any> {
    if (standalonetype) {
      return this.http.get(`${environment.instituMURL}/apis/getStandaloneInstMaster?stateBodyId=${standalonetype}&stateCode=${stateCode}&surveyYear=${surveyYear}`)
    }
    return this.http.get(`${environment.instituMURL}/apis/getStandaloneInstMaster?&stateCode=${stateCode}&surveyYear=${surveyYear}`)
  }

  getStandalonInstitutionTypeData(): Observable<any> {
    return this.http.get(`${environment.baseUrl}getBodyType`);
  }
  getCollegeType(): Observable<any> {
    return this.http.get(
      environment.masterUrl + '/api/university-type',
      httpOptions
    );
  }
  getCollegeTypePrivate(): Observable<any> {
    return this.http.get(
      environment.masterUrl + '/api/university-type-private',
      httpOptions
    );
  }
  getInstitutionManagementRole(rollId:any): Observable<any> {
    return this.http.get(environment.masterUrl + '/api/institution-by-role?roleId='+rollId, httpOptions);
  }

  getState(): Observable<any> {
    return this.http.get(environment.masterUrl + '/api/state', httpOptions);
  }
  getStatePrivate(): Observable<any> {
    return this.http.get(environment.masterUrl + '/api/state-private', httpOptions);
  }
  getDistrict(id: any): Observable<any> {
    return this.http.get(environment.masterUrl + '/api/district/' + id, httpOptions)
  }
  getRegStateBodyType(): Observable<any> {
    return this.http.get(environment.masterUrl + '/api/ref-statebody-type', httpOptions)
  }

  getUniversity(stateCode: any, UniSurveyYear: any): Observable<any> {
    let stateCode1
    if(stateCode.trim()){
      stateCode1=stateCode;
    }else{
        stateCode1="ALL"
    }
    return this.http.get(
      environment.masterUrl +
      `/api/university?year=${UniSurveyYear}&stateCode=${stateCode1}`,
      httpOptions
    );
  }
  getSurveyYearList(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}opensurveyyearforaishe`);
  }
  getSurveyYearListPrivate(): Observable<any> {
    return this.http.get(`${environment.baseUrlAishe}opensurveyyearforaishe-private`);
  }
  getYearList(): Observable<any> {
    return this.http.get(environment.instituMURL + '/apis/getSurveyYear');
  }
  getdistrict(data: any): Observable<any> {
    return this.http.get(
      environment.masterUrl + `/api/district/${data}`,
      httpOptions
    );
  }

  getReasonDeaffileate(): Observable<any> {
    return this.http.get(environment.instituMURL + '/apis/getReasonId');
  }
  getReasonForInactive(): Observable<any> {
    return this.http.get(environment.instituMURL + '/apis/getReasonId?instituteCategory=s');
  }

  postDeaffiliateData(data: any): Observable<any> {
    return this.http.post(
      environment.instituMURL + '/college/deaffiliationCollege',
      data
    );
  }
  postDeaffiliateData1(pay:any): Observable<any>{
    return this.http.post(
      environment.baseUrlAishe + 'saveDeaffiliationAndAffiliation',
      pay
    );
    //usermanagement/saveDeaffiliationAndAffiliation
  }
  postEditCollege(data: any): Observable<any> {

    return this.http.post(
      environment.instituMURL + '/college/editCollege',
      data
    );

  }

  postCollegeAffilication(data: any): Observable<any> {
    return this.http.post(environment.instituMURL + "/college/getCollegeAffilication", data);

  }

  getapproveCollegeList1(year: any, data: any): Observable<any> {
    // console.log('stateValue', year);
    // console.log(data)
    let roleId:any = this.localService.getData('roleId');
    // var state;
    // if(year.stateValue){
    //   state=year.stateValue;
    // }else{
    //   state= this.localService.getData('stateCode');
    // }


    // console.log(roleId);
    // if((this.localService.getData('stateCode') && roleId !='26') || (this.localService.getData('stateCode') && roleId !='1')){
    //   state= this.localService.getData('stateCode')? this.localService.getData('stateCode'):year.stateValue;
    // }else{

    // }
    if(roleId=='6' && data.pendingLevel==1){
      roleId='1';
    }
    if(roleId=='7' && data.pendingLevel==1){
      roleId='1';
    }

    let aisheCode: any = this.localService.getData('aisheCode');
    let aisheCode1 = aisheCode.replace(/\D/g, '');

    // if(year.RequestId===null && year.surveyYearApprove && year.stateValue){

    //   return this.http.get(environment.instituMURL+`/college/getCollegeApprovalList?approverRoleId=${roleId}&surveyYear=${year.surveyYearApprove}&stateCode=${year.stateValue}`)
    // }
    if (data.RequestId) {
      return this.http.get(environment.instituMURL + `/college/getCollegeApprovalList?approverRoleId=${roleId}&requestId=${year.RequestId}&surveyYear=${year.surveyYearApprove == null ? '' : year.surveyYearApprove}&stateCode=${year.stateValue == null ? '' : year.stateValue.trim()}&universityId=${year.universityValue == null ? '' : year.universityValue.trim()}`)
    }else if(roleId=="7"){
      return this.http.get(environment.instituMURL + `/college/getCollegeApprovalList?approverRoleId=${roleId}&surveyYear=${year.surveyYearApprove == null ? '' : year.surveyYearApprove}&stateCode=${year.stateValue == null ? '' : year.stateValue.trim()}&universityId=${year.universityValue == null ? '' : year.universityValue.trim()}&districtCode=${data.districtValue == null ? '' : data.districtValue}&collegeType=${data.collegeType==null?'':data.collegeType}`)
    }else if( (year.stateValue || year.stateValue.trim()=='') && roleId=="6"){
      return this.http.get(environment.instituMURL + `/college/getCollegeApprovalList?approverRoleId=${roleId}&surveyYear=${year.surveyYearApprove == null ? '' : year.surveyYearApprove}&stateCode=${year.stateValue == null ? '' : year.stateValue.trim()}&universityId=${year.universityValue == null ? '' : year.universityValue.trim()}&districtCode=${data.districtValue == null ? '' : data.districtValue}&collegeType=${data.collegeType==null?'':data.collegeType}`)
    }else if( (year.stateValue || year.stateValue.trim()=='') && roleId=="1"){
      return this.http.get(environment.instituMURL + `/college/getCollegeApprovalList?approverRoleId=${roleId}&surveyYear=${year.surveyYearApprove == null ? '' : year.surveyYearApprove}&stateCode=${year.stateValue == null ? '' : year.stateValue.trim()}&universityId=${year.universityValue == null ? '' : year.universityValue.trim()}&districtCode=${data.districtValue == null ? '' : data.districtValue}&collegeType=${data.collegeType==null?'':data.collegeType}&toDate=${year.toDate?year.toDate:null}&fromDate=${year.fromDate?year.fromDate:null}`)
    }


    return this.http.get(environment.instituMURL + `/college/getCollegeApprovalList?approverRoleId=${roleId}&surveyYear=${year.surveyYearApprove == null ? '' : year.surveyYearApprove}&stateCode=${data.stateValue == null ? '' : data.stateValue.trim()}&universityId=${data.universityValue == null ? '' : data.universityValue.trim()}&districtCode=${data.districtValue == null ? '' : data.districtValue}&collegeType=${data.collegeType==null?'':data.collegeType}&toDate=${year.toDate?year.toDate:null}&fromDate=${year.fromDate?year.fromDate:null}`)


  }
  getapproveCollegeList1MoE(year: any, data: any): Observable<any> {
    let roleId:any =this.localService.getData('roleId');
    if(data.pendingLevel==3){//UNO level
        roleId=7;
    }
    if(data.pendingLevel==4 && roleId=='6'){//find MoE level at SNO
      roleId=1;//MoE
    }else if(data.pendingLevel==4 && (roleId=='26' || roleId=='1')){//SNO level
       roleId=6;
    }

    let aisheCode: any = this.localService.getData('aisheCode');
    let aisheCode1 = aisheCode.replace(/\D/g, '');

    if (data.RequestId) {
      return this.http.get(environment.instituMURL + `/college/pending-college-requests?approverRoleId=${roleId}&requestId=${year.RequestId}&surveyYear=${year.surveyYearApprove == null ? '' : year.surveyYearApprove}&stateCode=${year.stateValue == null ? '' : year.stateValue.trim()}&universityId=${year.universityValue == null ? '' : year.universityValue.trim()}`)
    }else if(roleId=="7"){
      return this.http.get(environment.instituMURL + `/college/pending-college-requests?approverRoleId=${roleId}&surveyYear=${year.surveyYearApprove == null ? '' : year.surveyYearApprove}&stateCode=${year.stateValue == null ? '' : year.stateValue.trim()}&universityId=${year.universityValue == null ? '' : year.universityValue.trim()}&districtCode=${data.districtValue == null ? '' : data.districtValue}&collegeType=${data.collegeType==null?'':data.collegeType}&toDate=${year.toDate?year.toDate:null}&fromDate=${year.fromDate?year.fromDate:null}`)
    }else if( (year.stateValue || year.stateValue.trim()=='') && roleId=="6"){
      return this.http.get(environment.instituMURL + `/college/pending-college-requests?approverRoleId=${roleId}&surveyYear=${year.surveyYearApprove == null ? '' : year.surveyYearApprove}&stateCode=${year.stateValue == null ? '' : year.stateValue.trim()}&universityId=${year.universityValue == null ? '' : year.universityValue.trim()}&districtCode=${data.districtValue == null ? '' : data.districtValue}&collegeType=${data.collegeType==null?'':data.collegeType}&toDate=${year.toDate?year.toDate:null}&fromDate=${year.fromDate?year.fromDate:null}`)
    }else if( (year.stateValue || year.stateValue.trim()=='') && roleId=="1"){
      return this.http.get(environment.instituMURL + `/college/pending-college-requests?approverRoleId=${roleId}&surveyYear=${year.surveyYearApprove == null ? '' : year.surveyYearApprove}&stateCode=${year.stateValue == null ? '' : year.stateValue.trim()}&universityId=${year.universityValue == null ? '' : year.universityValue.trim()}&districtCode=${data.districtValue == null ? '' : data.districtValue}&collegeType=${data.collegeType==null?'':data.collegeType}&toDate=${year.toDate?year.toDate:null}&fromDate=${year.fromDate?year.fromDate:null}`)
    }


    return this.http.get(environment.instituMURL + `/college/pending-college-requests?approverRoleId=${roleId}&surveyYear=${year.surveyYearApprove == null ? '' : year.surveyYearApprove}&stateCode=${data.stateValue == null ? '' : data.stateValue.trim()}&universityId=${data.universityValue == null ? '' : data.universityValue.trim()}&districtCode=${data.districtValue == null ? '' : data.districtValue}&collegeType=${data.collegeType==null?'':data.collegeType}&toDate=${year.toDate?year.toDate:null}&fromDate=${year.fromDate?year.fromDate:null}`)


  }

  getgetapproveCollegeListInstit(data: any): Observable<any> {
    return this.http.get(`${environment.instituMURL}/college/getCollegeApprovalList?approverRoleId=${data.roleId}&stateCode=${data.stateValue}&surveyYear=${data.surveyYearValue}&universityId=${data.universityValue}`);
  }


  getapproveCollegeList(year: any) {
    let surveyYear = year;

    let stateCode = this.localService.getData('stateCode');
    let aisheCode: any = this.localService.getData('aisheCode');
    let aisheCode1 = aisheCode.replace(/\D/g, '');
    return this.http.get(environment.instituMURL + `/college/getCollegeApprovalList?surveyYear=${surveyYear}&universityId=${aisheCode1}`);
  }

  getapprovalHistoryData(data: any): Observable<any> {
    return this.http.get(environment.instituMURL + `/college/getCollegeApprovalHistoryList/${data}`)
  }

  getCollegeApprovalRequestDetails(data: any, survey: any): Observable<any> {

    return this.http.get(environment.instituMURL + `/college/CollegeApprovalRequestDetails/${data}/${survey}`);
  }
  getRequestId(requestId: any): Observable<any> {
    return this.http.get(`${environment.instituMURL}/apis/track-your-request?requestId=${requestId}`)
  }

  poststandaloneApprovedBySNO(data: any): Observable<any> {

    return this.http.post(`${environment.instituMURL}/standalone/standaloneApprovedByMoe`, data)
    //https://api1.he.nic.in/aisheinstitutemanagement/standalone/standaloneApprovedByMoe

  }
  postcollageApprovedByUniversity(data: any): Observable<any> {
    // console.log(data)
    let surveyYear=data?.surveyYear?.split('-')[0];
    let userData = {
      "approverId": this.localService.getData('userId'),
      "approverRoleId": this.localService.getData('roleId'),
      "surveyYear":(this.localService.getData('roleId')=='26' || this.localService.getData('roleId')=='1')? surveyYear:data.currentSurveyYear,
      "remark": data.aremarks,
      "requestId": data.requestId,
      "statusId": data.approveR,
      "name":data.Name?data.Name:null,
      "stateCode":data.stateName?data.stateName:null,
      "districtCode":data.districtName?data.districtName:null,
      "instituteType":data.instituteType?data.instituteType.toString():null,
      "managementId":data.managementType?data.managementType:null,
      "universityId":data.universityId?data.universityId:null,
    }


    return this.http.post(environment.instituMURL + "/college/collageApprovedByUniversity", userData);

  }


  getApprovalUniversityList(data: any, year: any): Observable<any> {
    return this.http.get(environment.instituMURL + `/college/getApprovedByUniversityList/${data}/${year}`)
  }

  institutionCount(): Observable<any> {
    return this.http.get(environment.instituMURL + `/institutionDirectory /getInstitutionCount?surveyYear`)
  }

  getNationalUniversity(data: any): Observable<any> {
    // https://api1.he.nic.in/aisheinstitutemanagement/institutionDirectory /getNationalUniversity?universityName=all
    return this.http.get(environment.instituMURL + `/institutionDirectory /getNationalUniversity?universityName=${data.universityName}&surveyYear=2020`)
  }

  getStates(): Observable<any> {
    return this.http.get(environment.baseUrl + `country/01/states`);
  }

  getDistricts(data: any): Observable<any> {
    return this.http.get(environment.baseUrl + `states/${data}/districts`)
  }

  getUniversityType(): Observable<any> {
    return this.http.get(environment.baseUrl + `getUniversityType`);
  }

  // getUniversityTypeForInstitution(): Observable<any> {
  //   return this.http.get(environment.masterUrl + `/api/university-type`);
  // }

  getmanagmentType(): Observable<any> {
    return this.http.get(`${environment.baseUrl}getManagmentType`);
  }
  getmanagmentType2(data:any): Observable<any> {
    return this.http.get(`${environment.masterUrl}/api/management?institutionCategory=${data.type}`);
  }

  getCollegType(): Observable<any> {
    return this.http.get(environment.baseUrl + `getCollegeType`);
  }

  getUniversityName(state: any, univType: any): Observable<any> {
    return this.http.get(environment.baseUrl +
      `getUniversityByYearTypeAndState?stateCode=${state}&surveyYear=2021&university=${univType}`)
  }
  getUniversityList(data: any): Observable<any> {
    let stateCode = data.state;
    let districtCode = data.district;
    let typeId = data.universityType;
    return this.http.get(environment.instituMURL + `/institutionDirectory /getUniversityList?stateCode=${stateCode}&districtcode=${districtCode}&typeid=${typeId}`);
  }
  getCollegeList(data: any): Observable<any> {
    return this.http.get(environment.instituMURL + `/institutionDirectory /getCollegeList?districtcode=${data.district}&stateCode=${data.state}&typeid=${data.collegeType}`);

  }
  getStandaloneList(data: any): Observable<any> {
    return this.http.get(environment.instituMURL + `/institutionDirectory /getStandaloneList?districtcode=${data.district}&stateCode=${data.state}&typeid=${data.standaloneType}`);
  }
  getBodyType(): Observable<any> {
    return this.http.get(environment.baseUrl + `getBodyType`);
  }

  getDashboardData(surveyYear: any):Promise<any[] | undefined> {
    return this.http.get<any[]>(`${environment.baseUrlGetApi1}/getAllDashBoardData/${surveyYear}`).toPromise()
  }
  // getDashboardData(surveyYear):Promise<any[]> {
  //   return this.http.get<any[]>(environment.dashboardApi+`/getDashBoardData/${surveyYear}/${this.aisheCode}`).toPromise();

  // }
  getStandaloneStatusData(data: any): Observable<any> {
    if (!data.standaloneType) {
      return this.http.get(`${environment.instituMURL}/standalone/getActiveAndInactiveStandalone?stateCode=${data.selectedStateCode}&surveyYear=${data.surveyYearValue}&status=${data.status}`)
    }
    return this.http.get(`${environment.instituMURL}/standalone/getActiveAndInactiveStandalone?stateCode=${data.selectedStateCode}&surveyYear=${data.surveyYearValue}&status=${data.status}&statebodyid=${data.standaloneType}`)
  }


  getActiveInactiveStatusData(data: any): Observable<any> {
    var url;
    if (data.status === 'active') {
      url = `/university/getViewUniversity?stateCode=${data.stateValue.trim()}&surveyYear=${data.surveyYearValue.substr(0, 4)}`
      if (!!data.universityType) {
        url = `/university/getViewUniversity?stateCode=${data.stateValue.trim()}&surveyYear=${data.surveyYearValue.substr(0, 4)}&universityType=${data.universityType.trim()}`
      }

    } else if (data.status === 'inactive') {
      url = `/university/viewInActiveUniversity?stateCode=${data.stateValue.trim()}&surveyYear=${data.surveyYearValue.substr(0, 4)}&status=${"I"}`
      if (!!data.universityType) {
        url = `/university/viewInActiveUniversity?stateCode=${data.stateValue.trim()}&surveyYear=${data.surveyYearValue.substr(0, 4)}&universityType=${data.universityType.trim()}&status=${"I"}`
      }
    }
    return this.http.get(`${environment.instituMURL}` + url)
  }

  postStandaloneStatusData(data: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/standalone/inactivateStandalone', data)

  }
  postUniversityStatusData(data: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/university/inActiveUniversity', data)

  }
  postStandaloneStatusActiveData(data: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/standalone/activateStandalone', data)
  }

  postUniversityStatusActiveData(data: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/university/activateUniversity', data)
  }
  postConverttoCollege(data: any): Observable<any> {
    return this.http.post(environment.instituMURL + '/university/convertUniversityToCollege', data)
  }

  // INSTITUTION MANAGEMENT REPORTS

  getUniversityReport(surveyYear: any, userId: any): Observable<any> {
    return this.http.get(environment.instituMURL + `/university/getUniversityLog?surveyYear=${surveyYear}&userId=${userId}`)
  }
  getUniversityReportWithDate(surveyYear: any, userId: any,toDate:any,fromDate:any): Observable<any> {
    return this.http.get(environment.instituMURL + `/university/getUniversityLog?surveyYear=${surveyYear}&userId=${userId}&toDate=${toDate?toDate:null}&fromDate=${fromDate?fromDate:null}`)
  }

  getStandAloneReport(surveyYear: any, userId: any,roleId:any): Observable<any> {
    return this.http.get(environment.instituMURL + `/standalone/standaloneActionLog?surveyYear=${surveyYear}&userId=${userId}&roleId=${roleId}`)
  }
  getStandAloneReportWithDate(surveyYear: any, userId: any,roleId:any,toDate:any,fromDate:any): Observable<any> {
    return this.http.get(environment.instituMURL + `/standalone/standaloneActionLog?surveyYear=${surveyYear}&userId=${userId}&roleId=${roleId}&toDate=${toDate?toDate:null}&fromDate=${fromDate?fromDate:null}`)
  }

  getCollegeReport(surveyYear: any, userId: any,roleId:any): Observable<any> {
    return this.http.get(environment.instituMURL + `/college/collegeActionLog?surveyYear=${surveyYear}&userId=${userId}&roleId=${roleId}`)
  }
  getCollegeReportWithDate(surveyYear: any, userId: any,roleId:any,toDate:any,fromDate:any): Observable<any> {
    return this.http.get(environment.instituMURL + `/college/collegeActionLog?surveyYear=${surveyYear}&userId=${userId}&roleId=${roleId}&toDate=${toDate?toDate:null}&fromDate=${fromDate?fromDate:null}`)
  }
  getSubRoll(rollId: any): Observable<any> {
   let roleId = this.localService.getData('roleId');
   let booleanValue=true;
   booleanValue=roleId=='22'?false:true;
    return this.http.get(environment.masterUrl + `/api/user-permission?approvingAuthority=${booleanValue}&roleId=${rollId}`)
  }
  postSendemail(data: any): Observable<any> {
    return this.http.post(environment.otpURL + 'send-any-email', data);
  }
  getRejected(payload:any):Observable<any>{
    return this.http.get(`${environment.instituMURL}/apis/request_detail`,{params:payload})
  }
  //https://demo.he.nic.in/aisheusermanagementdemo/send-any-email
}
