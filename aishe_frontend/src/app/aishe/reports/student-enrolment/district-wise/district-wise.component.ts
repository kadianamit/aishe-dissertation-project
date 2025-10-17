import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-district-wise',
  templateUrl: './district-wise.component.html',
  styleUrls: ['./district-wise.component.scss']
})
export class DistrictWiseComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log("inside module district-wise")
  }

}
