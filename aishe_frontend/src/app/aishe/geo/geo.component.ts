import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-geo',
  templateUrl: './geo.component.html',
  styleUrls: ['./geo.component.scss']
})
export class GeoComponent implements OnInit {
  lat!: number
  lon!: number
  latLongToken: any
  state: string='';
  district: string='';
  taluka: string='';
  village:string=''
  message:string=''
  constructor(public auth: AuthService,public sharedService:SharedService) { }

  ngOnInit(): void {
    this.getToken()
  }
  getToken() {
    this.auth.latLongToken().subscribe(res => {
      this.latLongToken = res.token;
    }, err => {

    })
  }
  verify() {
    let payload = {
      lat: this.lat,
      lon: this.lon
    }
    this.auth.validateLatLong(payload, this.latLongToken).subscribe(res => {
     if(this.isBlankObject(res)){
      this.message = 'BISAG-N API return blank object'
      }else{
        this.message=''
        this.state = res.state,
        this.district = res.district,
        this.taluka = res.taluka,
        this.village=res.village
      }
      
    }, err => {

    })
  }
  isBlankObject(obj: any): boolean {
    for (let prop in obj) {
      if (obj.hasOwnProperty(prop)) {
        return false;
      }
    }
    return true;
  }
}
