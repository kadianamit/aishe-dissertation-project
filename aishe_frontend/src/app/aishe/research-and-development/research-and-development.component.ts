import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';


@Component({
  selector: 'app-research-and-development',
  templateUrl: './research-and-development.component.html',
  styleUrls: ['./research-and-development.component.scss']
})
export class ResearchAndDevelopmentComponent implements OnInit {
  selectedIndex: number = 0
  type: string = 'research'
  listData: Array<any> = [];
  listDataCount: number = 0
  constructor(public auth: AuthService,public sharedService:SharedService) {

  }
  ngOnInit(): void {
    this.getInstituteCount()
  }
  tabSelected(event: any) {
    this.selectedIndex = event.index
  }
  getInstituteCount() {
    let payload = {
      surveyYear: '2020'
    }
    this.auth.getInstitutionCount(payload).subscribe(res => {
      this.listData = res.rndInstituteCountByMinistry
      this.listDataCount = res.rndInstituteCount
    }, err => {

    })
  }
}
