import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CountrystatecityService {

  constructor(private http: HttpClient) {}



  getusertype(): Observable<any> {
    return this.http.get(environment.baseUrl+'getUserType');
  }

  getcountry():Observable<any>{

return this.http.get(environment.baseUrl+'country');

  }
  getstate(obj:any):Observable <any>{
return this.http.get(environment.baseUrl+'country/' + obj + '/states');
  }
  getdist(obj:any):Observable<any>{
return this.http.get(environment.baseUrl+'states/' + obj + '/districts');
  }

  getUniversity(obj:any):Observable<any>{
return this.http.get('',obj);
  }
}
