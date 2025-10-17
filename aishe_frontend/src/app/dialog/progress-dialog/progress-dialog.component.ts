import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { FormControl } from "@angular/forms";
import { debounceTime, distinctUntilChanged } from "rxjs/operators";
import * as XLSX from 'xlsx-js-style';
@Component({
  selector: 'app-progress-dialog',
  templateUrl: './progress-dialog.component.html',
  styleUrls: ['./progress-dialog.component.scss']
})
export class ProgressDialogComponent implements OnInit {
  searchControl: FormControl;
  startLimit: number = 0;
  endLimit: number = 15;
  pageSize: number = 15;
  pageData: number = 0;
  page: number = 1;
  cancelButtonText = "Close";
   filteredItems: any[] = [];
  list: any[] = [];
  searchText: any = '';
  tempList: any[] = [];
  total = 0;
  obj:any
  typeName: any;
  constructor(public dialogRef: MatDialogRef<ProgressDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any, public sharedService: SharedService, public authService: AuthService) {
      this.searchControl = new FormControl();
     }

  ngOnInit(): void {
    this.typeName = this.element.payload.typeName
    this.total = this.element.array.total
    this.list = [...this.element.array]
    this.list = this.list.sort((a, b) => a.instituteName > b.instituteName ? 1 : -1);
    this.tempList = [...this.list]
    this.handlePageChange(this.page = 1)
  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1)

  }
  onPagination(event: any) {
    this.page = event
    this.getData(this.element.payload);
  }
  handlePageChange(event: any) {
    this.page = event
    this.startLimit = ((this.page - 1) * this.pageSize),
      this.endLimit = this.startLimit + this.pageSize
    var a = Math.ceil(this.list.length / this.pageSize);
    if (a === event) {
      this.pageData = Math.min(this.startLimit + this.pageSize, this.list.length);
    } else {
      this.pageData = Math.min(this.startLimit + this.pageSize, this.list.length - 1);
    }
  }

  async updateResults() {
    
    this.list = []
    this.list = this.searchByValue(this.tempList);
    // if (this.searchText.trim() === "") {
    //   this.pageSize = 15;
    //   this.total = this.element.array.total;
    // } else {
    //   this.pageSize = this.list.length;
    //   this.total = this.list.length
    // }
    this.handlePageChange(this.page = 1)

  }

  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        // item.name.trim();
        return (item.instituteName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.aisheCode.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  getData(ele: any) {
   let obj = {
      surveyYear: ele.surveyYear,
      stateCode: ele.stateCode,
      universityId: ele.universityId,
      institutionType: ele.institutionType,
      fromDate: ele.fromDate,
      toDate: ele.toDate,
      typeId: ele.typeId,
      formType: ele.formType,
      totalFormExpected: ele.totalFormExpected,
      totalFormUploaded: ele.totalFormUploaded,
      page: this.page,
      pageSize: this.pageSize,
      searchText:this.searchText
    }
    this.authService.getProgressById(obj).subscribe(res => {
      if (res.data && res.data.length) {
        this.list = res.data;
        this.tempList = [...this.list]
        this.total = this.element.array.total
        this.handlePageChange(this.page)
      }
    }, err => {

    })

  }
  public setupSearchDebouncer(): void {
    // Subscribe to `searchDecouncer$` values,
    // but pipe through `debounceTime` and `distinctUntilChanged`
    this.searchControl.valueChanges.pipe(
      debounceTime(250),
      distinctUntilChanged(),
    ).subscribe((searchText: string) => {
      this.list = []
      if (searchText.trim() === "") {
        this.pageSize = 15;
        this.total = this.element.array.total;
      } else {
        this.search(searchText,this.element.payload);
      }
    });
  }
  public search(searchText: string,ele:any): void {
    let payload = {
      surveyYear: ele.surveyYear,
      stateCode: ele.stateCode,
      universityId: ele.universityId,
      institutionType: ele.institutionType,
      fromDate: ele.fromDate,
      toDate: ele.toDate,
      typeId: ele.typeId,
      formType: ele.formType,
      totalFormExpected: ele.totalFormExpected,
      totalFormUploaded: ele.totalFormUploaded,
      page: 1,
      pageSize: this.pageSize,
      searchText:searchText
    }
    this.authService.getProgressById(payload).subscribe((results) => {
      if (results.data && results.data.length) {
        this.list = results.data;
        this.list = this.list.sort((a, b) => a.instituteName > b.instituteName ? 1 : -1);
        this.tempList = [...this.list]
        this.total = this.element.array.total
        this.handlePageChange(this.page)
      }
    });
  }

  excelDownload() {
       const wb = XLSX.utils.book_new();
       const data = this.getTableData();
       const ws: XLSX.WorkSheet = XLSX.utils.aoa_to_sheet(data);
  
       const customHeader = this.createCustomHeader();
       XLSX.utils.sheet_add_aoa(ws, customHeader, { origin: 'A1' });
  
       ws['!cols'] = [
         { wch: 10 }, // Width for the first column (e.g., 'State')
         { wch: 15 }, // Width for the second column
         { wch: 70 }, // Width for the third column
         { wch: 35 }, // Width for the fourth column
       ];
       ws['!freeze'] = { xSplit: 0, ySplit: 2, topLeftCell: 'C3' };
  
       for (var i in ws) {
         if (typeof ws[i] != 'object') continue;
         let cell = XLSX.utils.decode_cell(i);
  
         ws[i].s = {
           // styling for all cells
           font: {
             name: 'arial',
           },
           alignment: {
             vertical: 'center',
             horizontal: 'left',
             wrapText: '1', // any truthy value here
           },
  
           border: {
             right: {
               style: 'thin',
               color: '000000',
             },
             left: {
               style: 'thin',
               color: '000000',
             },
           },
         };
         if (cell.r == 0) {
           // first row
           ws[i].s.font = { bold: true, size: 16 };
           ws[i].s.border.bottom = {
             // bottom border
             style: 'thin',
             color: '000000',
           };
         }
  
         if (cell.r) {
           ws[i].s.border.bottom = {
             // bottom border
             style: 'thin',
             color: '000000',
           };
         }
       }
  
       XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
  
       XLSX.writeFile(wb, 'Progress Monitoring.xlsx');
     }
  
     getTableData(): any[][] {
       const tableData = [
         [
           'S.No.',
           'AISHE Code',
           'Institute Name',
           'Type'
         ],
       ];
  
       this.list.forEach((element, i) => {
         tableData.push([
           i+1,
           element.aisheCode,
           element.instituteName,
          this.typeName
         ]);
       });
       return tableData;
     }
  
     createCustomHeader(): any[][] {
       let header: any[][];
       header = [
         [
           'S.No.',
           'AISHE Code',
           'Institute Name',
           'Type'
         ],
       ];
  
       return header;
     }
}

