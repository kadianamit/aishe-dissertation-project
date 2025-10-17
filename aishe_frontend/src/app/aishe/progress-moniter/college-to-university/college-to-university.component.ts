import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-college-to-university',
  templateUrl: './college-to-university.component.html',
  styleUrls: ['./college-to-university.component.scss']
})
export class CollegeToUniversityComponent implements OnInit {
  listData: Array<any> = [];
  tempList:Array<any>=[];
  searchText:string=''
  constructor(public sharedService: SharedService) { }

  ngOnInit(): void {
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
        return (item.facultyName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.departmentName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.name.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.jobStatus.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.dateOfChangeInJobStatusString?.includes(this.searchText.trim().toLowerCase()))
          || (item.designationName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }
}
