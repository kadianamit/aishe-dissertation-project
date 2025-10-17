import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const httpOptions={
  headers:new HttpHeaders({
    'Access-Control-Allow-Origin':'*'
  })
}

@Injectable({
  providedIn: 'root'
})
export class UniversityService {

  constructor(private http:HttpClient) { }

  getdataaishe(obj:any):Observable<any>{
  
   return this.http.get(environment.baseUrl+`getUniversity?stateCode=${obj}`, httpOptions);
  }
  
  getUniversityTypes():Observable<any>{
  
    return this.http.get(environment.baseUrl+'getUniversityType');
   }
   getfindUniversityData1(surveyYear:string,stateObj:any,universityType:string):Observable<any>{
  
    let stateId=stateObj.stateCode?stateObj.stateCode:stateObj;
  
    return this.http.get(environment.baseUrl+`getUniversityByYearTypeAndState?stateCode=${stateId}&surveyYear=${surveyYear}&university=${universityType}`,httpOptions);
    //return this.http.get(environment.baseUrl+`findUniversityByYearAndState?stateCode=${stateId}&surveyYear=${surveyYear}`,httpOptions);
   }
   getfindUniversityDataSate(data:any,obj:any):Observable<any>{
    let stateId=obj.stateCode;
  
  let surveyYear=data;
    return this.http.get(environment.baseUrl+`findUniversityByYearAndState?stateCode=${stateId}&surveyYear=${surveyYear}`,httpOptions);
   }
}
