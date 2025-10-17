/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { ExcelService } from 'src/app/service/reports/excel.service';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from '../../../../shared/shared.service';
@Component({
  selector: 'app-report145',
  templateUrl: './report145.component.html',
  styleUrls: ['./report145.component.scss']
})
export class Report145Component implements OnInit , ReportChildComponent {

  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFormOutTurn")
  reportFormOutTurn: any;


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
  pageSize: any = 15;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  constructor(private getReport:GetService,private excelService:ExcelService,public sharedService:SharedService) { }

  ngOnInit(): void {

    // this.element = document.getElementById("mytr");
    // this.hidden = this.element.getAttribute("hidden");

    if (this.hidden) {
      this.element.removeAttribute("hidden");

    }
  }

  is2020:boolean=false;
  getReportDataTable(): void {

   // this.isDataLoading=true;
   this.reportFormOutTurn.value=this.reportFormOutTurn.getRawValue();
    this.reportFormOutTurn.value.exportType = "JSON";
    this.serveyYear= this.reportFormOutTurn.value.surveyYear;
    this.getReport.getReport145(this.reportFormOutTurn.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe((res:any) => {
        if (res.list.length > 0) {
          this.is2020 = this.reportFormOutTurn.value.surveyYear > '2020';
        this.Showdata12=true;
        this.showPdfButton = true;
        this.reportTableData = res.list.slice(0, res.list.length - 1);

        this.tableColumns = Object.keys(res.list[0]);
        this.summaryData = res.list[res.list.length - 1];
        this.summaryColumns = Object.keys(this.summaryData);
        this.handlePageChange(1);
      } else {
        // this.tableColumns = [];
        // this.summaryData = [];

        this.sharedService.showError('Data is not available');
        this.reportTableData = [];
        this.Showdata12=false;
        this.showPdfButton = false;
      }
    });
  }
  generatePDF(): void {
    this.reportFormOutTurn.value=this.reportFormOutTurn.getRawValue();
    this.reportFormOutTurn.value.exportType="PDF";
    this.getReport.getReport145(this.reportFormOutTurn.value).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
  }
  exportAsXLSX():void {
    this.reportFormOutTurn.value=this.reportFormOutTurn.getRawValue();
    this.reportFormOutTurn.value.exportType="EXCEL";
    this.getReport.getReport145(this.reportFormOutTurn.value).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
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

