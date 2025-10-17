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
export class ConfirmAccountService {

  constructor(private http:HttpClient) { }

  activeAccount(obj:any):Observable<any>{

 return this.http.post(environment.baseUrl + `confirm-account?userId=${obj}`,httpOptions);
  }
}
