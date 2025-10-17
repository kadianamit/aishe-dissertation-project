import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LocalserviceService } from '../localservice.service';
const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Authorization': ''
  })
}
@Injectable({
  providedIn: 'root'
})
export class PostService {
  scholarshipSchemePostdata(data: any): Observable<any> {
    return this.http.post(environment.programmeUrl + '/api/scholarship-scheme', data);
  }

  constructor(private http: HttpClient,public localService: LocalserviceService) { }

  getReport35b(data: any): Observable<any> {
    data.universityType = data.universityType.id;
    data.universityName = data.universityName.id;
    return this.http.post(environment.baseUrl + 'report35B', data);

  }

  getReport39(data: any): Observable<any> {
    data.universityType = data.universityType.id;
    data.universityName = data.universityName.id;
    return this.http.post(environment.baseUrl + 'report39', data);
  }


  getReport107(data: any): Observable<any> {
    let userData = {
      "categoryType": data.socialCategory,
      "courseMode": data.courseMode,
      "diciplineGroup": data.disciplineGroup.id,
      "diciplineName": data.disciplineName.id,
      "religiousCat": data.religiousCategory,
      "reportType": data.reportType,
      "stateCode": data.addressStateCode.stateCode,
      "studyMode": data.studyMode,
      "surveyYear": data.surveyYear,
      "userId": this.localService.getData('userId'),
    }

    return this.http.post(environment.baseUrl + 'report107', userData);

  }
  getReport107A(data: any): Observable<any> {
    let userData = {
      "categoryType": data.socialCategory,
      "courseMode": data.courseMode,
      "diciplineGroup": data.disciplineGroup.id,
      "diciplineName": data.disciplineName.id,
      "religiousCat": data.religiousCategory,
      "reportType": data.reportType,
      "stateCode": data.addressStateCode.stateCode,
      "studyMode": data.studyMode,
      "surveyYear": data.surveyYear,
      "universityId": data.universityName.id,
      "universityType": data.universityType.id,
      "userId": this.localService.getData('userId'),
    }
    return this.http.post(environment.baseUrl + 'report107A', userData);

  }

  getReport109(data: any): Observable<any> {

    let userData = {
      "categoryType": data.socialCategory,
      "courseMode": data.courseMode,
      "diciplineGroup": data.disciplineGroup.id,
      "diciplineName": data.disciplineName.id,
      "modeId": "string",
      "religiousCat": data.religiousCategory,
      "reportType": data.reportType,
      "staffType": "string",
      "stateCode": data.addressStateCode.stateCode,
      "studyMode": data.studyMode,
      "surveyYear": data.surveyYear,
      "typeId": "string",
      "universityId": "string",
      "universityType": "string"

    }
    return this.http.post(environment.baseUrl + 'report109', userData);
  }

  getReport109A(data: any): Observable<any> {

    let userData = {
      "categoryType": data.socialCategory,
      "collegeId": 0,
      "collegeInstTypeid": "string",
      "courseMode": data.courseMode,
      "diciplineGroup": data.disciplineGroup.id,
      "diciplineName": data.disciplineName.id,
      "managementId": data.managementType.id,
      "modeId": data.courseMode,
      "religiousCat": data.religiousCategory,
      "reportType": data.reportType,
      "staffType": "ALL",
      "stateBodyId": "string",
      "stateCode": data.addressStateCode.stateCode,
      "studyMode": data.studyMode,
      "surveyYear": data.surveyYear,
      "typeId": data.universityType.id,
      "universityId": data.universityName.id,
      "universityType": data.universityType.id,
    }


    return this.http.post(environment.baseUrl + 'report109A', userData);
  }

  getReport107B(data: any): Observable<any> {
    let userData = {
      "categoryType": data.socialCategory,
      "collegeId": 0,
      "collegeInstTypeid": data.collegeType.id,
      "courseMode": data.courseMode,
      "diciplineGroup": data.disciplineGroup.id,
      "diciplineName": data.disciplineName.id,
      "managementId": data.managementType.id,
      "modeId": data.courseMode,
      "religiousCat": data.religiousCategory,
      "reportType": data.reportType,
      "staffType": "ALL",
      "stateCode": data.addressStateCode.stateCode,
      "studyMode": data.studyMode,
      "surveyYear": data.surveyYear,
      "typeId": "ALL",
      "universityId": data.universityName.id,
      "universityType": data.universityType.id,
      "userId": this.localService.getData('userId'),
    }
    return this.http.post(environment.baseUrl + 'report107B', userData);
  }

  getReport107C(data: any): Observable<any> {
    let userData = {
      "categoryType": data.socialCategory,
      "collegeId": 0,
      "collegeInstTypeid": data.collegeType.id,
      "courseMode": data.courseMode,
      "diciplineGroup": data.disciplineGroup.id,
      "diciplineName": data.disciplineName.id,
      "managementId": data.managementType.id,
      "modeId": data.courseMode,
      "religiousCat": data.religiousCategory,
      "reportType": data.reportType,
      "staffType": "ALL",
      "stateBodyId": data.bodyType.id,
      "stateCode": data.addressStateCode.stateCode,
      "studyMode": data.studyMode,
      "surveyYear": data.surveyYear,
      "typeId": "ALL",
      "universityId": data.universityName.id,
      "universityType": data.universityType.id,
      "userId": this.localService.getData('userId')
    }
    return this.http.post(`${environment.baseUrl}report107C`, userData)
  }
 saveSpecialPermissions(data:any):Observable<any>{
  return this.http.post(`${environment.baseUrlAishe}assignspecialpermission?instituteType=${data.instituteType}&aisheCode=${data.aisheCode}&username=${data.username}&surveyYear=${data.surveyYear}&specialPermission=${data.specialPermission}&userKey=${data.userKey}&remarks=${data.remarks}`,httpOptions)
 }
 unlockDCF(data:any):Observable<any>{
  return this.http.post(`${environment.baseUrlAishe}api/webdcf-unlock`,data)

 }



}


