import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { UpdateViewComponent } from '../update-view/update-view.component';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.scss']
})
export class ApplicationComponent implements OnInit {
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  listData: Array<any> = [];
  tempListData: Array<any> = [];
  searchText: any;
  departmentList: Array<any> = [];
  departmentListArray: Array<any> = [];
  departmentName:string='--ALL--'
  constructor(public authService: AuthService, public sharedService: SharedService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.websiteDetails()
  }
  websiteDetails() {
    this.authService.getWebsiteDetails().subscribe(res => {
      this.listData = res.data;
      this.departmentListArray = this.listData.filter(
        (thing, i, arr) => arr.findIndex(t => t.departmentName === thing.departmentName) === i
      );
      this.departmentList = this.departmentListArray.slice()
      this.departmentList.unshift({
        departmentName: '--ALL--'
        
      })
      this.tempListData = [...this.listData]
      this.handlePageChange(this.page = 1)
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

  searchByValue(userData: any) {
    return userData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.applicationName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.remarks?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.websiteUrl?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.departmentName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  goToWebsite(url: any) {
    alert(`You are being redirected to ${url}`)
    window.open(url, '_blank');
  }
  openDialog(ele: any, action: any, i: number) {
    ele['page'] = 'Website';
    ele['addOrUpdate'] = action
    let dialogRef = this.dialog.open(UpdateViewComponent, {
      width: '50%',
      height: 'auto',
      data: { data: ele, list: this.listData, i: this.StartLimit + i },
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.websiteDetails()

      }
    });
  }
  aopenDialog(action: any) {
    let ele = {
      page: 'Website',
      addOrUpdate: action
    }
    let dialogRef = this.dialog.open(UpdateViewComponent, {
      width: '50%',
      height: 'auto',
      data: { data: ele, list: this.listData },
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.websiteDetails()

      }
    });
  }
  delete(id: any) {
    this.sharedService.delete().subscribe(result => {
      if (result) {
        this.authService.deleteWebsite(id).subscribe(res => {
          if (res.status === 200) {

            this.sharedService.showSuccessMessage('Record delete successfully');
            this.websiteDetails()
          }

        }, err => {

        })
      }
    })

  }
  getDepartmentByName() {
    if (this.departmentName !== '--ALL--') {
      this.listData = this.tempListData.filter((ele: any) => ele.departmentName?.trim().toLowerCase() === this.departmentName?.trim().toLowerCase())
    } else {
      this.listData = [...this.tempListData]
    }
    this.handlePageChange(this.page = 1)
  }
  reload(){
    this.searchText=''
    this.StartLimit = 0;
    this.EndLimit = 25;
    this.pageData = 0;
    this.pageSize = 25;
    this.page = 1;
    this.websiteDetails();
  }
}
