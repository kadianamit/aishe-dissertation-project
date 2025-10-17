import { HttpHeaders, HttpClient } from '@angular/common/http';
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
export class RegisterService {

  constructor(private http:HttpClient) { }

  regiData(data:any):Observable<any>{

    console.log('update data',data);
 
    let userData={
      "addressDistrictCode": data.addressDistrictCode.districtCode,
      "addressStateCode": data.addressStateCode.stateCode,
      "country": data.country.countryId,
      "emailId": data.emailId,
      "firstName": data.firstName,
      "lastName": data.lastName,
      "phoneMobile": data.phoneMobile,
      "stdCode": data.stdCode,
      "userPassword": data.userPassword,
      "userType": data.userType.id,
    }
return this.http.post(environment.baseUrl +'registerUser', userData);
  }
  emailVerify(obj:any):Observable<any>{
return this.http.post(environment.baseUrl + `checkEmailExist?emailId=${obj}`,httpOptions);
  }

  editUserPorfile(id:any):Observable<any>{
    //https://demo.he.nic.in/aisheUser/UserByUserId/4
    //console.log('/https://demo.he.nic.in/aisheUser/UserByUserId/', id);
return this.http.get(environment.baseUrl + 'UserByUserId/' + id);
  }
}
