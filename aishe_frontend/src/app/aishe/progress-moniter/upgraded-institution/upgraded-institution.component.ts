import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upgraded-institution',
  templateUrl: './upgraded-institution.component.html',
  styleUrls: ['./upgraded-institution.component.scss']
})
export class UpgradedInstitutionComponent implements OnInit {
  selectedIndex:number=0
  constructor() { }

  ngOnInit(): void {
  }
  tabSelected(event:any){

  }
}
