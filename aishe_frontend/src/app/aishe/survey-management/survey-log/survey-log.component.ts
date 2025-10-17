import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-survey-log',
  templateUrl: './survey-log.component.html',
  styleUrls: ['./survey-log.component.scss']
})
export class SurveyLogComponent implements OnInit {
  listData: Array<any> = [];
  tempListData: Array<any> = [];
  surveyYearList: Array<any> = [];
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  searchText: any;
  surveyYear: any = '2021';
  selectedIndex: number = 0;
  actionLog: string = 'REGISTRATION';
  constructor(public authService: AuthService, public sharedService: SharedService) { }

  ngOnInit(): void {
    this.getSurveyYear();
    this.getSurveyLog();
  }
  tabSelected(value: any) {
    switch (value.index) {
      case 2: {
        this.actionLog = 'SPECIAL_PERMISSION'
        this.getSurveyLog();
        break;
      }
      case 1: {
        this.actionLog = 'CREATE_SURVEY'
        this.getSurveyLog();
        break;
      }
      default: {
        this.actionLog = 'REGISTRATION'
        this.getSurveyLog();
        break;
      }
    }
  }
  getSurveyLog() {
    this.authService.getSurveyLog(this.surveyYear === '--ALL--' ? '' : this.surveyYear, this.actionLog).subscribe(res => {
      this.listData = res.data;
      this.tempListData = [...this.listData];
      this.handlePageChange(this.page = 1)
    }, err => {

    })
  }
  getSurveyYear() {
    this.authService.getSurveyYear().subscribe(res => {
      this.surveyYearList = []
      this.surveyYearList = res;
      this.surveyYearList = this.surveyYearList.sort((a: any, b: any) => a.surveyYear - b.surveyYear);
      this.surveyYearList.splice(0, 1)
      // this.surveyYearList.unshift({
      //   surveyYear: '--ALL--',
      //   surveyYearValue: '--ALL--'
      // })
    }, err => {

    })
  }
  handlePageChange(event: any) {
    this.page = event
    this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
      this.EndLimit = this.StartLimit + Number(this.pageSize)
    var a = Math.ceil(this.listData.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.listData.length);
    } else {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.listData.length - 1);
    }
  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1)
  }

  async updateResults() {
    this.listData = []
    this.listData = this.searchByValue(this.tempListData);
    this.handlePageChange(this.page = 1)
  }
  reload() {
    this.getSurveyLog();
  }



  searchByValue(userData: any) {
    return userData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.surveyYear?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.startDate?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.endDate?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          // || (item.registrationStartDate?.toString().includes(this.searchText.trim()))
          // || (item.registrationEndDate?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          // || (item.surveyStartDate?.toString().includes(this.searchText.trim()))
          // || (item.surveyEndDate?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          // || (item.specialPermissionStartDate?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          // || (item.specialPermissionEndDate?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          // || (item.isSurveyFreezed?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
}
