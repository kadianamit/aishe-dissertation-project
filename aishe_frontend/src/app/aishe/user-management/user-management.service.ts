import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  constructor(public httpClient: HttpClient,public localService: LocalserviceService) { }
  getUserStatusList(): Observable<any> {
    return this.httpClient.get(`${environment.masterUrl}/api/user-status`)
  }

  getuserListL3filter(userStatus: any, page: any, pageSize: any): Observable<any> {
    let roleId = this.localService.getData('roleId')
    let aisheCode: any = this.localService.getData('aisheCode')
    let universityId = aisheCode.split("-")
    //let pageSize = this.pageSizeOptions
    // console.log('page page',pageSize);


    if (roleId?.match("7")) {
      return this.httpClient.get(`${environment.baseUrlAishe}api/user/list?roleId=12&userStatus=${userStatus}&instituteType=COLLEGE&sortBy=BY_INSTITUTION&page=${page}&pageSize=${pageSize}`)
    } else if (roleId?.match("12")) {
      return this.httpClient.get(`${environment.baseUrlAishe}api/user/list?roleId=${roleId}&userStatus=2&instituteType=COLLEGE&sortBy=BY_INSTITUTION&page=${page}&pageSize=${pageSize}`)
    }
    else {
      return this.httpClient.get(`${environment.baseUrlAishe}api/user/list?roleId=${roleId}&instituteType=STANDALONE&aishecode=${aisheCode}&sortBy=BY_INSTITUTION&page=${page}&pageSize=${pageSize}`)
    }
  }

  getuserSno(payload: any): Observable<any> {

    return this.httpClient.get(`${environment.baseUrlAishe}api/user/list?stateCode=${payload.stateCode}&sortBy=BY_USERID&instituteType=${payload.instituteType}&aishecode=${payload.aisheCode}&roleId=${payload.roleId}&userStatus=${payload.userStatus}`)
  }
  getuserListL2(payload: any): Observable<any> {
    return this.httpClient.get(`${environment.baseUrlAishe}api/user/list?roleId=${payload.roleId}&instituteType=${payload.instituteType}&aishecode=${payload.aisheCode}&sortBy=BY_USERID&universityId=${payload.stateLevelBody}&stateCode=${payload.stateCode}&userStatus=${payload.userStatus}&formUploadStatus=${payload.formUploadStatus}&fromDate=${payload.fromDate}&toDate=${payload.toDate}`)
  }

  getuserListL3(payload: any): Observable<any> {
    return this.httpClient.get(`${environment.baseUrlAishe}api/user/list?roleId=${payload.roleId}&stateCode=${payload.stateCode}&instituteType=${payload.instituteType}&aishecode=${payload.aisheCode}&universityId=${payload.universityId}&sortBy=BY_USERID&fromDate=${payload.fromDate}&toDate=${payload.toDate}&userStatus=${payload.userStatus}`)
  }

  getuserListDeo(payload: any): Observable<any> {
    return this.httpClient.get(`${environment.baseUrlAishe}api/user/list?roleId=${payload.roleId}&deoRoleId=${payload.deoRoleId}&instituteType=${payload.instituteType}&aishecode=${payload.aisheCode}&sortBy=BY_USERID&userStatus=${payload.userStatus}&stateCode=${payload.stateCode}`)

  }
  getUserListLevel1(payload: any): Observable<any> {
    return this.httpClient.get(`${environment.baseUrlAishe}api/user/list?roleId=${payload.roleId}&userStatus=${payload.userStatus}&sortBy=BY_USERID&stateCode=${payload.stateCode}&isApproved=${payload.isApproved}`)
  }
  getAffliatedUniversity(payload:any):Observable<any>{
    return this.httpClient.get(`${environment.baseUrlAishe}api/user/list?roleId=${payload.roleId}&stateCode=${payload.stateCode}&sortBy=BY_USERID`)
  }


  activeInactiveMoE(userId:any,activeMoeUser:any):Observable<any>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
      })
    }
    return this.httpClient.post(`${environment.baseUrlAishe}api/update-moe-view-user?userId=${userId}&activeMoeUser=${activeMoeUser}`,httpOptions)
  }
}
