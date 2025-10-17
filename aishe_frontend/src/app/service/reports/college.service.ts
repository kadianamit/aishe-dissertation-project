import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CollegeService {

  constructor(private http:HttpClient) {

  }

  getCollege(universityId:any):Observable <any>{
   return this.http.get(environment.baseUrl+'colleges/' + universityId);
     }

     getcollegenamebyType(surveyYear:any,universityNameId:any,collegeType:any):Observable <any>{
       return this.http.get(environment.baseUrl+'collegesByUniversityIdAndTypeId/'+collegeType+'/universityId/'+universityNameId+'/surveyYear/'+surveyYear );
     }
}
