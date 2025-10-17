import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }
  
  userLogin(data:any):Observable<any>{
    console.log(data);
    return this.http.post(environment.baseUrl+  'login',data);
  }
}
