import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { param } from 'jquery';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegManagementService {

  constructor(public http: HttpClient) { }

  createReg(userData: any): Observable<any> {
    return this.http.post(environment.baseUrlAishe + 'save-activity-master-new', userData)
  }
  // deleteCollegeData(userData: any): Observable<any> {
  //   //console.log(userData);
  //   return this.http.post(environment.instituMURL + `/college/deleteCollege`, userData)

  // }

  getActivityData(): Observable<any> {
    return this.http.get(environment.baseUrlAishe + 'get-activity-master');
  }
  getActivityDataParam(payload:any): Observable<any> {
    return this.http.get(environment.baseUrlAishe + 'get-activity-master',{params:payload});
  }
  deleteSurvey(payload: any): Observable<any> {
    return this.http.delete(environment.baseUrlAishe + 'delete-activity-master',{params:payload})
  }
}
