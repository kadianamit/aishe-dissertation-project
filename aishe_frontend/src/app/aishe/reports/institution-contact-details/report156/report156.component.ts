/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report156',
  templateUrl: './report156.component.html',
  styleUrls: ['./report156.component.scss']
})
export class Report156Component implements OnInit,ReportChildComponent {

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


  pageSize: any = 10;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;

  constructor(private getReport:GetService,public sharedservice: SharedService) { }
  exportAsXLSX(): void {
this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.exportType="EXCEL";
    this.getReport.getReport156(this.reportFormlistofInst.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }


  getReportDataTable(): void {
this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.exportType = "JSON";
    this.getReport.getReport156(this.reportFormlistofInst.value).pipe(
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

       this. handlePageChange(this.page);
      } else {
        this.sharedservice.showError('Data is not available')
        this.tableColumns =[];
        this.summaryData = [];
        this.showPdfButton = false;
        this.Showdata12=false;
      }
    });


  }
  generatePDF(): void {
    this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.exportType="PDF";
    this.getReport.getReport156(this.reportFormlistofInst.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
  }
  ngOnInit(): void {
    console.log("inside reports156")

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

