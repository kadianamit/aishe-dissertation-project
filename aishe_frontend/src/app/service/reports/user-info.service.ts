import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserInfoService {

  constructor(private http: HttpClient) {

  }

  getUserInfo(userId: any): Observable<any> {
    return this.http.get(environment.baseUrl + 'UserByUserId/' + userId);
  }
}
