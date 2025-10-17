import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SurveyyearService {

  constructor(private http:HttpClient) { }

  getdatasurveyyear():Observable<any>{
return this.http.get(environment.baseUrl+'getSurveyYear');
  }
   getdatasurveyyearDataUser():Observable<any>{
return this.http.get(environment.baseUrl+'getSurveyYear?isClosed=true');
  }
}
