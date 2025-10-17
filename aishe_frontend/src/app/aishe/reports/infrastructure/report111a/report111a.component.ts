/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';
import { finalize } from 'rxjs';
@Component({
  selector: 'app-report111a',
  templateUrl: './report111a.component.html',
  styleUrls: ['./report111a.component.scss']
})
export class Report111aComponent implements OnInit, ReportChildComponent {

  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportForm")
  reportForm: any;


  pageSize: any = 10;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;

  constructor(private getReport:GetService) { }
  
    exportAsXLSX(): void {
       this.reportForm.value=this.reportForm.getRawValue();
      this.reportForm.value.reportType="EXCEL";
      this.getReport.getReport111A(this.reportForm.value).subscribe(res => {
            let byteArrays = res.byteData;
            utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
      });
  }

  ngOnInit(): void {
    console.log("inside reports111a")

  }

  getReportDataTable(): void {

    this.isDataLoading=true;
     this.reportForm.value=this.reportForm.getRawValue();
    this.reportForm.value.reportType = "JSON";
    this.getReport.getReport111A(this.reportForm.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe(res => {
        if (res.list.length > 0) {
        this.Showdata12=true;
        this.showPdfButton = true;
        this.reportTableData = res.list.slice(0, res.list.length-1);
        this.tableColumns = Object.keys(res.list[0]);
        this.summaryData = res.list[res.list.length - 1];
       this.summaryColumns = Object.keys(this.summaryData);
       this.handlePageChange(1)
      } else {
        this.tableColumns = [];
        this.summaryData = [];
        this.showPdfButton = false;
        this.Showdata12=false;
      }
    });
  }
  generatePDF(): void {
     this.reportForm.value=this.reportForm.getRawValue();
    this.reportForm.value.reportType="PDF";
    this.getReport.getReport111A(this.reportForm.value).subscribe(res => {
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
