import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import * as XLSX from 'xlsx-js-style';
@Component({
  selector: 'app-state-wise-monitoring-dialog',
  templateUrl: './state-wise-monitoring-dialog.component.html',
  styleUrls: ['./state-wise-monitoring-dialog.component.scss'],
})
export class StateWiseMonitoringDialogComponent implements OnInit {
  searchControl: FormControl;
  stateName:any=''
  startLimit: number = 0;
  pageSize: number = 25;
  page: number = 1;
  endLimit = this.pageSize;
  pageData = this.pageSize;
  filteredItems: any[] = [];
  cancelButtonText = 'Close';
  list: any[] = [];
  searchText: any = '';
  tempList: any[] = [];
  total = 0;
  obj: any;
  constructor(
    public dialogRef: MatDialogRef<StateWiseMonitoringDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any,
    public sharedService: SharedService,
    public authService: AuthService
  ) {
    this.searchControl = new FormControl();
  }

  ngOnInit(): void {
     this.stateName = this.element.array.stateName;
    this.listData();
  }

  listData() {
    this.list=[];
    const pay = {
      surveyYear: this.element.payload.surveyYear,
      type: this.element.payload.enum,
      stateCode: this.element.payload.stateCode,
    };
    this.authService.getStateWiseProgress(pay).subscribe((res) => {
      if (res) {
        this.list = res.data;
        this.tempList = [...this.list];
        this.filteredItems=[...res.data]
        this.handlePageChange((this.page = 1));
      }
    });
  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange((this.page = 1));
  }


  handlePageChange(event: any): void {
    if (this.tempList.length < 15) {
      this.endLimit = this.tempList.length;
    } else {
      this.page = event;
      this.startLimit = (this.page - 1) * this.pageSize;
      this.endLimit = Math.min(this.startLimit + this.pageSize, this.tempList.length);
      const totalPages = Math.ceil(this.tempList.length / this.pageSize);
      this.pageData = totalPages === this.page ? this.tempList.length : this.endLimit;
    }
  }


  async updateResults() {
    this.list = [];
    this.list = this.searchByValue(this.tempList);
    this.handlePageChange((this.page = 1));
  }

  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        // item.name.trim();
        return (
          item.name
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.type
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.aisheCode
            .toString()
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase())
        );
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

     XLSX.writeFile(wb, 'StateWiseMonitoring.xlsx');
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

     this.filteredItems.forEach((element, i) => {
       tableData.push([
         i+1,
         element.aisheCode,
         element.name,
         element.type,
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
