import { Component, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-survey-participant',
  templateUrl: './survey-participant.component.html',
  styleUrls: ['./survey-participant.component.scss']
})
export class SurveyParticipantComponent implements OnInit {
  unSubscribeSubject = new Subject()
  listData: Array<any> = [];
  tempList: Array<any> = [];
  listCount:Array<any>=[];
  searchText: string = ''
  institutionCategory: any='ALL';
  inSurveyYear: any;
  notInSurveyYear: any;
  surveyYearList:Array<any>=[]
  constructor(public sharedService: SharedService, public authService: AuthService) { }

  ngOnInit(): void {
    this.getSurveyYear()
  }
  getSurveyYear(){
    this.authService.getSurveyYear().pipe(takeUntil(this.unSubscribeSubject)).subscribe({next:(res)=> {
      this.surveyYearList = res
    },error(err) {
      
    },})
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
        return (item.newAisheCode?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.newInstitutionName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }
  getInstitutionList() {
    if(!(this.inSurveyYear && this.notInSurveyYear)){
      this.sharedService.showValidationMessage('Plaese enter Participated Survey & Not Participated Survey year');
      return;
    }
    let payload = {
      institutionCategory: this.institutionCategory,
      inSurveyYear:this.inSurveyYear,
      notInSurveyYear:this.notInSurveyYear
    }
    this.authService.getParticipatedIns(payload).pipe(takeUntil(this.unSubscribeSubject)).subscribe({
      next: (res: any) => {
        if (res.masterResponse && res.masterResponse.length) {
          this.listData = res.masterResponse;
          this.tempList = [...this.listData]
          this.handlePageChange(this.sharedService.page = 1)
        } else {
          this.listData = []
          this.tempList=[]
        }
      }, error: (error: any) => {

      }
    })
  }
  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
}
