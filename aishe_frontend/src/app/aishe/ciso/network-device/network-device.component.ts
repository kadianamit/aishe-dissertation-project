import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-network-device',
  templateUrl: './network-device.component.html',
  styleUrls: ['./network-device.component.scss']
})
export class NetworkDeviceComponent implements OnInit {
  selectedIndex:number=0;
  constructor() { }

  ngOnInit(): void {
  }
  tabSelected(event:any){
    this.selectedIndex = event.index
  }
}
