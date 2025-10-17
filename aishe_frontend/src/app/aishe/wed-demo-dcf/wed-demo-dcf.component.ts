import { Component, OnDestroy, OnInit } from '@angular/core';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'app-wed-demo-dcf',
  templateUrl: './wed-demo-dcf.component.html',
  styleUrls: ['./wed-demo-dcf.component.scss']
})
export class WedDemoDcfComponent implements OnInit, OnDestroy {

  constructor(public localService:LocalserviceService) { }

  ngOnInit(): void {
  }
  ngOnDestroy(): void {
    this.localService.removeData('aisheCode')
    this.localService.removeData('loginMode')
  }
}
