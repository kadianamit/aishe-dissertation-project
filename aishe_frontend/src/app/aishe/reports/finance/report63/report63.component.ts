/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from 'src/app/shared/shared.service';
@Component({
  selector: 'app-report63',
  templateUrl: './report63.component.html',
  styleUrls: ['./report63.component.scss']
})
export class Report63Component implements OnInit,ReportChildComponent {

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
  constructor(private getReport:GetService,public sharedservice: SharedService) { }
  exportAsXLSX(): void {
     this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.exportType="EXCEL";
    this.getReport.getReport63(this.reportFormlistofInst.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }


  getReportDataTable(): void {
 this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.exportType = "JSON";
    this.getReport.getReport63(this.reportFormlistofInst.value).pipe(
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
       this.handlePageChange(1)
      } else {
        this.reportTableData =[];
        this.tableColumns=[];
        this.summaryData=[];
        this.sharedservice.showError('Data is not available')
        this.Showdata12=false;
        this.showPdfButton = false;
        
      }
    });


  }
  generatePDF(): void {
     this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.exportType="PDF";
    this.getReport.getReport63(this.reportFormlistofInst.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
  }
  ngOnInit(): void {
    console.log("inside reports63")

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
