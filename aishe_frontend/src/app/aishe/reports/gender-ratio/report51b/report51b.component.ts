/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';

@Component({
  selector: 'app-report51b',
  templateUrl: './report51b.component.html',
  styleUrls: ['./report51b.component.scss']
})
export class Report51bComponent implements OnInit,ReportChildComponent {

  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;

  pageSize: any = 15;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFormlistofInst")
  reportFormlistofInst: any;
  constructor(private getReport:GetService) { }
  exportAsXLSX(): void {
    this.reportFormlistofInst.value.exportType="EXCEL";
    this.getReport.getReport51B(this.reportFormlistofInst.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });  }


  getReportDataTable(): void {
    this.tableColumns =[];
    this.summaryData = [];
  this.reportTableData=[];
    this.reportFormlistofInst.value.exportType = "JSON";
    this.getReport.getReport51B(this.reportFormlistofInst.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe(res => {
        if (res.list.length > 0) {
        this.Showdata12=true;
        this.showPdfButton = true;
        // this.reportTableData = res.list.slice(0, res.list.length - 1);
        this.reportTableData = res.list;
        this.tableColumns = Object.keys(res.list[0]);
        // this.summaryData = res.list[res.list.length - 1];
      //  this.summaryColumns = Object.keys(this.summaryData);
       this.handlePageChange(1)
      } else {
        this.Showdata12=false;
        this.tableColumns =[];
        this.summaryData = [];
        this.reportTableData=[];
        this.showPdfButton = false;
      }
    });


  }
  generatePDF(): void {
    this.reportFormlistofInst.value.exportType="PDF";
    this.getReport.getReport51B(this.reportFormlistofInst.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
  }
  ngOnInit(): void {
    console.log("inside reports44")

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
