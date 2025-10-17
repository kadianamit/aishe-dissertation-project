import { Component, OnDestroy, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs';
import { Subject } from 'rxjs/internal/Subject';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-missing-user',
  templateUrl: './missing-user.component.html',
  styleUrls: ['./missing-user.component.scss']
})
export class MissingUserComponent implements OnInit,OnDestroy {
  unSubscribeSubject = new Subject()
  listData: Array<any> = [];
  tempList: Array<any> = [];
  listCount: Array<any> = [];
  searchText: string = ''
  selectedIndex: number = 0;
  institutionType: string = 'UNIVERSITY'
  tableHeaders = ['SNO', 'AISHE Code', 'Institute Name','State', 'Nodal Officer Name','Email','Mobile','Status'];


  constructor(public sharedService: SharedService, public authService: AuthService) { }

  ngOnInit(): void {
    this.getMissingUserList()
  }
  tabSelected(event: any) {
    if (event.index === 0) {
      this.institutionType = 'UNIVERSITY'
      this.getMissingUserList();
    } else if (event.index === 1) {
      this.institutionType = 'COLLEGE'
      this.getMissingUserList()
    } else {
      this.institutionType = 'STANDALONE'
      this.getMissingUserList()
    }
  }
  getMissingUserList() {
    this.authService.getMissingUser(this.institutionType).pipe(takeUntil(this.unSubscribeSubject)).subscribe({
      next: (res: any) => {
        this.listData = res.data;
        this.tempList=[...this.listData]
        this.handlePageChange(this.sharedService.page=1)
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
        return (item.aisheCode?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.name?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.stateName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.nodalOfficerName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.nodalOfficerEmail?.includes(this.searchText.trim().toLowerCase()))
          || (item.nodalOfficerMobile?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  ngOnDestroy(): void {
    this.unSubscribeSubject?.unsubscribe()
  }
  downloadPDF(){
    const tableData = this.listData.map((row, i) => [
      i + 1,
      row.aisheCode,
      row.name,
      row.stateName,
      row.nodalOfficerName,
      row.nodalOfficerEmail,
      row.nodalOfficerMobile,
      row.nodalOfficerStatus,

    ]);
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      pdfName:'Missing User In HEIs',
      downloadPdfName:'Missing User in HEIs',
      orientationType:'landscape',
    }
     let a={ 1: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
        2: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
        3: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
        4: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
        5: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
        6: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
        7: { fillColor: [247, 247, 235], textColor: [0, 0, 0] }
      
  }
    this.sharedService.downloadPDF(param,a);
  }
  downloadExcel() {
    const tableData = this.listData.map((row, i) => [
      // i+1,
      row.aisheCode,
      row.name,
      row.stateName,
      row.nodalOfficerName,
      row.nodalOfficerEmail,
      row.nodalOfficerMobile,
      row.nodalOfficerStatus,

    ]);
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      downloadExcelName:'Missing User in HEIs'
    }
   this.sharedService.downloadExcel(param)
  }
}
