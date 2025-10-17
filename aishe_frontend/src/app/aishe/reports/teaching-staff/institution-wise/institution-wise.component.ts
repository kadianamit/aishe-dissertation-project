import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-institution-wise',
  templateUrl: './institution-wise.component.html',
  styleUrls: ['./institution-wise.component.scss']
})
export class InstitutionWiseComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log("inside report-module institution-wise")
  }

}
