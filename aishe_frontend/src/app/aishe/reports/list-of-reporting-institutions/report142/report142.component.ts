/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';
import { LocalserviceService } from 'src/app/service/localservice.service';


@Component({
  selector: 'app-report142',
  templateUrl: './report142.component.html',
  styleUrls: ['./report142.component.scss']
})
export class Report142Component implements OnInit, ReportChildComponent {

  pageSize: any = 15;
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
  @Input("reportInfoObj")
  reportInfoObj: any;
aisheCode:any;
splitAisheCode:any
  @Input("reportFormlistofInst")
  reportFormlistofInst: any;
  constructor(private getReport:GetService,public localService:LocalserviceService) { 
     this.aisheCode=localService.getData('aisheCode')
                
                 this.splitAisheCode=this.aisheCode.split('-')[1]}
  exportAsXLSX(): void {
    this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.exportType="EXCEL";
    this.reportFormlistofInst.value.universityId=this.splitAisheCode;

    this.getReport.getReport142(this.reportFormlistofInst.value).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }

  getReportDataTable(): void {

    this.isDataLoading=true;
    this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.exportType = "JSON";
    this.reportFormlistofInst.value.universityId=this.splitAisheCode;
    this.getReport.getReport142(this.reportFormlistofInst.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe((res:any) => {
        if (res.list.length > 0) {
        this.Showdata12=true;
        this.showPdfButton = true;
        // this.reportTableData = res.list.slice(0, res.list.length - 1);
        this.reportTableData = res.list;

        this.tableColumns = Object.keys(res.list[0]);
        // this.summaryData = res.list[res.list.length - 1];
        // this.summaryColumns = Object.keys(this.summaryData);
        this.handlePageChange(1)
      } else {
        this.tableColumns = [];
        this.summaryData = [];
        this.showPdfButton = false;
      }
    });


  }
  generatePDF(): void {
    this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.exportType="PDF";
    this.reportFormlistofInst.value.universityId=this.splitAisheCode;

    this.getReport.getReport142(this.reportFormlistofInst.value).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
  }

  ngOnInit(): void {
    console.log("inside reports142")

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
