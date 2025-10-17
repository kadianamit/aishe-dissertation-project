import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs/internal/Subject';
import { takeUntil } from 'rxjs/internal/operators/takeUntil';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-survey-wise',
  templateUrl: './survey-wise.component.html',
  styleUrls: ['./survey-wise.component.scss']
})
export class SurveyWiseComponent implements OnInit {
  unSubscribeSubject = new Subject()
  surveyYear: string | null;
  listData: Array<any> = [];
  searchText: string = '';
  tempList: Array<any> = [];
  constructor(public route: ActivatedRoute, public authService: AuthService, public sharedService: SharedService) {
    this.surveyYear = this.route.snapshot.paramMap.get('year')
  }

  ngOnInit() {
    this.getSurveyWiseRecord()
  }
  getSurveyWiseRecord() {
    let payload = {
      aisheCode: 'ALL',
      surveyYear: this.surveyYear,
      type: 1
    }
    this.authService.getSurveyWiseMerge(payload).pipe(takeUntil(this.unSubscribeSubject)).subscribe({
      next: (res: any) => {
        if (res.masterResponse && res.masterResponse.length) {
          this.listData = res.masterResponse;
          this.tempList = [...this.listData]
          this.handlePageChange(this.sharedService.page = 1)
        } else {
          this.listData = []
        }
      }, error: (error: any) => {

      }
    })
  }
  handlePageChange(event: any) {
    this.sharedService.page = event;
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize);
    var a = Math.ceil(this.listData.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.listData.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.listData.length - 1);
    }
  }
  onChange(event: any) {
    this.sharedService.pageSize = parseInt(event);
    this.handlePageChange(this.sharedService.page = 1);
  }
  async updateResults() {
    this.listData = []
    this.listData = this.searchByValue(this.tempList);
    this.handlePageChange(this.sharedService.page = 1)
  }

  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        // item.name.trim();
        return (item.surveyYear?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
        || (item.oldAisheCode?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
        || (item.oldInstitutionName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
        || (item.newAisheCode?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
        || (item.newInstitutionName?.includes(this.searchText.trim().toLowerCase()))
        || (item.newUniversityName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
        || (item.stateName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
        || (item.oldUniversityName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
        || (item.actionBy?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
        || (item.actionTime?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }
}
