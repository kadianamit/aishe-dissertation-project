import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InstitutionService {
  getReport127(stateCode: any) {
    return this.http.get(environment.baseUrl + 'api/institutions/' + stateCode);
  }

  constructor(private http: HttpClient) {

  }


  getInstitutes(stateCode: any): Observable<any> {
    return this.http.get(environment.baseUrl + 'institutions/' + stateCode);
  }

  getInstByStateBodyId(stateCode: any): Observable<any> {
    return this.http.get(environment.baseUrl + 'instbystatebodyid/' + stateCode);
  }

  getInstbyStateCodeAndBody(surveyYear:any,stateCode: any, bodyTypeId: any): Observable<any> {
    return this.http.get(environment.baseUrl + 'instbystatecodenstatebody/statecode/'+ stateCode +'/statebodyid/'+ bodyTypeId+'/surveyYear/'+surveyYear );
  }
}
