/* eslint-disable @angular-eslint/no-input-rename */
/* eslint-disable @angular-eslint/no-output-rename */
import { Component, Inject, Input, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { PostService } from 'src/app/service/post/post.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { SharedService } from '../../../../../shared/shared.service';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'app-report40',
  templateUrl: './report40.component.html',
  styleUrls: ['./report40.component.scss']
})
export class Report40Component implements OnInit,ReportChildComponent {

  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFilterForm")
  reportFilterForm: any;


  @Output("isDataLoading")
  isDataLoading:boolean=false;

  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  is2020:boolean=false;
  pageSize: any = 10;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  aisheCode: any;
  splitAisheCode: any;
  constructor(@Inject(MAT_DIALOG_DATA) public obj: any,
  private reportService:ReportService,public sharedService:SharedService,public localService:LocalserviceService
                ) {
                   this.aisheCode=localService.getData('aisheCode')
              
               this.splitAisheCode=this.aisheCode.split('-')[1]
                 }
  exportAsXLSX(): void {
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="EXCEL";
     this.reportFilterForm.value.universityId=this.splitAisheCode;
    this.reportService.getReport40(this.reportFilterForm.value).subscribe((res:any) => {
        let byteArrays = res.byteData;
        utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
  });
  }

  getReportDataTable(): void {
    // console.log(this.reportFilterForm.value);
    // this.Showdata12=true;
    // this.isDataLoading=true;
    this.reportTableData=[]
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType = "JSON";
     this.reportFilterForm.value.universityId=this.splitAisheCode;
    this.reportService.getReport40
    (this.reportFilterForm.value).pipe(
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
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="PDF";
     this.reportFilterForm.value.universityId=this.splitAisheCode;
    this.reportService.getReport40(this.reportFilterForm.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
  }
  ngOnInit(): void {
    console.log("inside reports40")

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
