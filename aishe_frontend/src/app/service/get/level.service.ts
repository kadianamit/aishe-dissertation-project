import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
export class LevelService {

  constructor(private http: HttpClient) { }

  getLevelData():Observable<any>{
    return this.http.get(environment.baseUrl+'courseLevel');
  }


  getprogrammeData(data:any):Observable<any>{

return this.http.get(environment.baseUrl+`programme?courseLevel=${data}`,httpOptions);
  }
  getprogrammeData1(data:any):Observable<any>{
console.log(data);
    return this.http.get(environment.baseUrl+`programme?courseLevel=${data}`,httpOptions);
      }




   //http://10.246.76.155/aisheUser/

   getDisciplineNameByGroup(programmeId:any,groupName:any):Observable<any>{

    return this.http.get(environment.baseUrl+`disciplineNameByGroup?groupName=${groupName}&progamme=${programmeId}`,httpOptions);
   }
   //http://10.246.76.155/aisheUser/disciplineGroupByProgramme?progamme=051

   getDisciplineGroupByProgrammeData(programme:any):Observable<any>{
    return this.http.get(environment.baseUrl+`disciplineGroupByProgramme?progamme=${programme}`,httpOptions);
   }

   getReport108(data:any):Observable<any>{

let surveyYear=data.surveyYear;
let StateCode=data.addressStateCode.stateCode;
let courseMode=data.courseMode;
let disciplinGroup=data.disciplineGroup.id;
let disciplineName=data.disciplineName.id;
let level=data.level.id;
let programme=data.programme.id;
let reportType=data.reportType;
let studyMode=data.studyMode


   // return this.http.get(environment.baseUrl+`report108?courseMode=${courseMode}&disciplineGroup=${disciplinGroup}&disciplineName=${disciplineName}&level=${level}&programme=${programme}&reportType=${reportType}&stateCode=${StateCode}&studyMode=${studyMode}&surveyYear=${surveyYear}`,httpOptions);
return this.http.get(environment.baseUrl+`report108?courseMode=${courseMode }&disciplineGroup=${disciplinGroup}&disciplineName=${disciplineName}&level=${level}&programme=${programme}&reportType=${reportType}&stateCode=${StateCode}&studyMode=${studyMode}&surveyYear=${surveyYear}`,httpOptions);
   }
}
