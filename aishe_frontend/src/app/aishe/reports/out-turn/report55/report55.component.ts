/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { ExcelService } from 'src/app/service/reports/excel.service';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from '../../../../shared/shared.service';

@Component({
  selector: 'app-report55',
  templateUrl: './report55.component.html',
  styleUrls: ['./report55.component.scss']
})
export class Report55Component implements OnInit,ReportChildComponent {

  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFormOutTurn")
  reportFormOutTurn: any;



  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  is2020:boolean=false;
  pageSize: any = 5;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  constructor(private getReportService:GetService,private excelService:ExcelService,public sharedService:SharedService) { }
  exportAsXLSX(): void {
    this.reportFormOutTurn.value=this.reportFormOutTurn.getRawValue();
    this.reportFormOutTurn.value.reportType="EXCEL";
    this.getReportService.getReport55(this.reportFormOutTurn.value).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }

  ngOnInit(): void {
    console.log("inside reports55")

  }


  getReportDataTable(): void {

  //  this.isDataLoading=true;
  this.reportFormOutTurn.value=this.reportFormOutTurn.getRawValue();
    this.reportFormOutTurn.value.reportType = "JSON";

    this.getReportService.getReport55(this.reportFormOutTurn.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe(res => {
        if (res.list.length > 0) {
          this.is2020 = this.reportFormOutTurn.value.surveyYear >= '2020';
        this.Showdata12=true;
        this.showPdfButton = true;
        this.reportTableData = res.list.slice(0, res.list.length - 1);
        this.tableColumns = Object.keys(res.list[0]);
        this.summaryData = res.list[res.list.length - 1];
        this.summaryColumns = Object.keys(this.summaryData);
        this.handlePageChange(1);
      } else {
        this.sharedService.showError('Data is not available');
       
        this.Showdata12=false;
        this.showPdfButton = false;
        this.tableColumns = [];
        this.summaryData = [];

      }
    });


  }
  generatePDF(): void {
    this.reportFormOutTurn.value=this.reportFormOutTurn.getRawValue();
    this.reportFormOutTurn.value.reportType="PDF";
    this.getReportService.getReport55(this.reportFormOutTurn.value).subscribe(res => {
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
