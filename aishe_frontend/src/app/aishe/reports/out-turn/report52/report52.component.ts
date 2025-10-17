import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { ExcelService } from 'src/app/service/reports/excel.service';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from '../../../../shared/shared.service';

@Component({
  selector: 'app-report52',
  templateUrl: './report52.component.html',
  styleUrls: ['./report52.component.scss']
})
export class Report52Component implements OnInit, ReportChildComponent {

  @Input()
  reportInfoObj: any;
  @Input()
  pageSize1:any;

  @Input()
  reportFormOutTurn: any;

  pageSize: any = 10;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  byteArrays:any[] = [];
  reportTableData: any;
  reportTableData1:any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false;
  summaryColumns: any;
  summaryData: any;
  serveyYear:any;
  element:any;
  hidden:any;
  showPdfButton: boolean=false;
  is2020:boolean=false;

  constructor(private getReport:GetService,private excelService:ExcelService,public sharedService:SharedService) { }

  ngOnInit(): void {

    console.log("this.report")
    // this.element = document.getElementById("mytr");
    // this.hidden = this.element.getAttribute("hidden");

    // if (this.hidden) {
    //   this.element.removeAttribute("hidden");
      
    // }
  }

  getReportDataTable(): void {

   // this.isDataLoading=true;
    this.reportFormOutTurn.value.reportType = "JSON";
    this.serveyYear= this.reportFormOutTurn.value.surveyYear;
    this.getReport.getReport52(this.reportFormOutTurn.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe(res => {
        if (res.list.length > 0) {
          this.is2020 = this.reportFormOutTurn.value.surveyYear >= '2020';
        this.Showdata12=true;
        this.showPdfButton = true;
        this.reportTableData = res.list.slice(0, res.list.length - 1);
        this.reportTableData1=res.list.slice(0, res.list.length);
        this.tableColumns = Object.keys(res.list[0]);
        this.summaryData = res.list[res.list.length - 1];
        this.summaryColumns = Object.keys(this.summaryData);
        this.handlePageChange(1);
      } else {
        this.tableColumns = [];
        this.summaryData = [];
        this.sharedService.showError('Data is not available');
        this.reportTableData = [];
        this.Showdata12=false;
        this.showPdfButton = false;
      }
    });
  }
  generatePDF(): void {
    this.reportFormOutTurn.value.reportType="PDF";
    this.getReport.getReport52(this.reportFormOutTurn.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
  }
  exportAsXLSX():void {
    this.reportFormOutTurn.value.reportType="EXCEL";
    this.getReport.getReport52(this.reportFormOutTurn.value).subscribe(res => {
          this.byteArrays = res.byteData;
          utility.downloadAsExcel(res.byteData,this.reportInfoObj.reportNumber);
    });
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
