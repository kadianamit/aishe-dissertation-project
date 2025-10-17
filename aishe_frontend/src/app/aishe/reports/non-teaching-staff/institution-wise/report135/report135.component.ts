/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { ReportService } from 'src/app/service/reports/report.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report135',
  templateUrl: './report135.component.html',
  styleUrls: ['./report135.component.scss']
})
export class Report135Component implements OnInit, ReportChildComponent{

  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean= false;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  is2020:boolean=false;
  pageSize: any = 10;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;

  @Input("reportInfoObj")
  reportInfoObj: any;
  @Input("reportFilterForm")
  reportFilterForm: any;
  constructor(private reportService: ReportService,private sharedService:SharedService) { }
  getReportDataTable(): void {

    this.isDataLoading=true;
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType = "JSON";

    this.reportService.getReport135(this.reportFilterForm.value).subscribe(
     res => {

       if( res.list.length > 0 ){
        this.is2020 = this.reportFilterForm.value.surveyYear >= '2020';

        this.showPdfButton=true;
        this.Showdata12=true
       this.reportTableData=res.list.slice(0,res.list.length-1);  //take untill 2nd last index
       this.tableColumns = Object.keys(res.list[0]);
        this.summaryData = res.list[res.list.length-1]; //take summary data of last object
        this.summaryColumns=Object.keys(this.summaryData);
        this.handlePageChange(1);
      }else{
        this.summaryData=[];
        this.summaryColumns=[];
        this.reportTableData=null;
        this.sharedService.showError('Data is not available');
        this.Showdata12=false;
        this.showPdfButton = false;
      }

     });
  }
  generatePDF(): void {
this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="PDF";
    this.reportService.getReport135(this.reportFilterForm.value).subscribe(res => {
        let byteArrays = res.byteData;
        utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
  });
  }
  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
    this.summaryData=[]
    this.tableColumns=[]
  }
  exportAsXLSX(): void {
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="EXCEL";
    this.reportService.getReport135(this.reportFilterForm.value).subscribe(res => {
        let byteArrays = res.byteData;
        utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
  });
  }

  ngOnInit(): void {
    console.log("inside reports135")

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
