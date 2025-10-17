/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { ReportService } from 'src/app/service/reports/report.service';
import { SharedService } from '../../../../../shared/shared.service';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'app-report17',
  templateUrl: './report17.component.html',
  styleUrls: ['./report17.component.scss']
})
export class Report17Component implements OnInit , ReportChildComponent {

  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFilterForm")
  reportFilterForm: any;

  @Input("reportDataForm")
  reportDataForm: any;

  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false ;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  is2020:boolean=false;
  is2021:boolean=false;
  pageSize: any = 10;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  aisheCode: any;
  splitAisheCode: any;
  constructor(
  private reportService:ReportService,public sharedService:SharedService,public localService:LocalserviceService
    ) {
       this.aisheCode=localService.getData('aisheCode')
  
   this.splitAisheCode=this.aisheCode.split('-')[1]
     }
  exportAsXLSX(): void {
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
  this.reportFilterForm.value.exportType="EXCEL";
   this.reportFilterForm.value.universityId=this.splitAisheCode
    this.reportService.getReport17(this.reportFilterForm.value).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    })
  }
  ngOnInit(): void {
    console.log("inside report17")
  }

  getReportDataTable(): void {
    // this.Showdata12=true;
    // this.isDataLoading=true;
    this.reportTableData=[];
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType = "JSON";
     this.reportFilterForm.value.universityId=this.splitAisheCode
    this.reportService.getReport17(this.reportFilterForm.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe((res:any) => {
        if(res.list?.length > 0  && !!res.list){
          this.is2020 = this.reportFilterForm.value.surveyYear >= '2020';
          this.is2021 = this.reportFilterForm.value.surveyYear > '2020';
          this.showPdfButton=true;
         this.Showdata12=true;
         this.reportTableData=res.list.slice(0, res.list.length-1); //to subarray except last object
         this.tableColumns = Object.keys(res.list[0]); //to get all columns name

        this.summaryData=res.list[res.list.length-1];
        this.summaryColumns=Object.keys(this.summaryData);
        this.handlePageChange(1);
         }else{
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
     this.reportFilterForm.value.universityId=this.splitAisheCode
    console.log(this.reportFilterForm.value);
    this.reportService.getReport17(this.reportFilterForm.value).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }

  onReset(): void {
    //throw new Error('Method not implemented.');
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


