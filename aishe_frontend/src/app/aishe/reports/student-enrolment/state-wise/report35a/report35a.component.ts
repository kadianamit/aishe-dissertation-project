/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Inject, Input, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { ReportService } from 'src/app/service/reports/report.service';
import { SharedService } from '../../../../../shared/shared.service';

@Component({
  selector: 'app-report35a',
  templateUrl: './report35a.component.html',
  styleUrls: ['./report35a.component.scss']
})
export class Report35AComponent implements OnInit, ReportChildComponent {

  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFilterForm")
  reportFilterForm: any;

  isDataLoading:boolean=false;

  showPdfButton: boolean = false;

  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  summaryColumns: any;
  summaryData: any;
  is2020:boolean=false;
  pageSize: any = 10;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  constructor(@Inject(MAT_DIALOG_DATA) public obj: any,
  private reportService:ReportService,public sharedService:SharedService) { }
  exportAsXLSX(): void {
    this.reportFilterForm.value.reportType="EXCEL";
    this.reportService.getReport35A(this.reportFilterForm.value).subscribe((res:any) => {
        let byteArrays = res.byteData;
        utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
  });
  }
  ngOnInit(): void {
console.log("inside report35a")
  }

  showReport() {

  }

  getReportDataTable() {
    this.reportTableData=[];
    // this.Showdata12=true;
    // this.isDataLoading=true;
    this.reportFilterForm.value.reportType = "JSON";
    this.reportService.getReport35A(this.reportFilterForm.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
    ).subscribe(res => {
      if (res.list.length > 0) {
        this.is2020 = this.reportFilterForm.value.surveyYear >= '2020';

        this.Showdata12=true;
        this.showPdfButton = true;
        this.reportTableData = res.list.slice(0, res.list.length - 1);
        this.tableColumns = Object.keys(res.list[0]);
        this.summaryData = res.list[res.list.length - 1];
        this.summaryColumns = Object.keys(this.summaryData);
        this.handlePageChange(1);
      } else {
        this.reportTableData=[];
      this.tableColumns = [];
      this.summaryData = [];
      this.Showdata12=false;
      this.showPdfButton = false
      this.sharedService.showError("Data is not available");
      }
    });

  }
  generatePDF(): void {
    this.reportFilterForm.value.reportType="PDF";
      this.reportService.getReport35A(this.reportFilterForm.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }

  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
  }
  handlePageChange(event: number) {
    this.page = event;
    let fgh = parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * fgh),
      (this.EndLimit = this.StartLimit + fgh);
    var a = Math.ceil(this.reportTableData.length / fgh);
    if (a === event) {
      this.pageData = Math.min(
        this.StartLimit + fgh,
        this.reportTableData.length
      );
    } else {
      this.pageData = Math.min(
        this.StartLimit + fgh,
        this.reportTableData.length
      );
    }
  }
}
