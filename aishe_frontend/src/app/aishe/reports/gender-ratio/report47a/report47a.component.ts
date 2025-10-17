/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report47a',
  templateUrl: './report47a.component.html',
  styleUrls: ['./report47a.component.scss']
})
export class Report47aComponent implements OnInit,ReportChildComponent {

  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;

  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFormlistofInst")
  reportFormlistofInst: any;
  constructor(private getReport:GetService,public sharedService:SharedService) { }
  exportAsXLSX(): void {
    this.reportFormlistofInst.value.exportType="EXCEL";
    this.getReport.getReport47A(this.reportFormlistofInst.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }


  getReportDataTable(): void {
    this.tableColumns =[];
    this.summaryData = [];
    this.reportTableData=[];
    this.reportFormlistofInst.value.exportType = "JSON";
    this.getReport.getReport47A(this.reportFormlistofInst.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe(res => {
        if (res.list.length > 0) {
        this.Showdata12=true;
        this.showPdfButton = true;
        this.reportTableData = res.list.slice(0, res.list.length - 1);
        this.tableColumns = Object.keys(res.list[0]);
        this.summaryData = res.list[res.list.length - 1];
       this.summaryColumns = Object.keys(this.summaryData);
      } else {
        this.reportTableData=[];
        this.Showdata12=false;
        this.tableColumns =[];
        this.summaryData = [];
        this.showPdfButton = false;
        this.sharedService.showError('Data is not available');
      }
    });


  }
  generatePDF(): void {
    this.reportFormlistofInst.value.exportType="PDF";
    this.getReport.getReport47A(this.reportFormlistofInst.value).subscribe(res => {
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

}
