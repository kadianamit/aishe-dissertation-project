/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { ReportService } from 'src/app/service/reports/report.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report122b',
  templateUrl: './report122b.component.html',
  styleUrls: ['./report122b.component.scss']
})
export class Report122bComponent implements OnInit, ReportChildComponent {

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
  is2020:boolean=false;

  @Input("reportInfoObj")
  reportInfoObj: any;
  @Input("reportFilterForm")
  reportFilterForm: any;
  constructor(private reportService: ReportService,public sharedService:SharedService) { }
  getReportDataTable(): void {
    this.isDataLoading=true;
    this.reportFilterForm.value.exportType = "JSON";

    this.reportService.getReport122B(this.reportFilterForm.value).subscribe(
     res => {

       if( res.list.length > 0 ){
        this.is2020 = this.reportFilterForm.value.surveyYear >= '2020';
// debugger
        this.showPdfButton=true;
        this.Showdata12=true
       this.reportTableData=res.list.slice(0,res.list.length);  //take untill 2nd last index
       this.tableColumns = Object.keys(res.list[0]);
        // this.summaryData = res.list[res.list.length-1]; //take summary data of last object
        // this.summaryColumns=Object.keys(this.summaryData);
        this.handlePageChange(1);
      }else{
        this.sharedService.showError('Data is not available');
        this.Showdata12=false;
        this.showPdfButton = false;
        // this.summaryData=[]
        // this.tableColumns=[]
      }

     });
  }
  generatePDF(): void {

    this.reportFilterForm.value.exportType="PDF";
    this.reportService.getReport122B(this.reportFilterForm.value).subscribe(res => {
        let byteArrays = res.byteData;
        utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
  });
  }
  onReset(): void {
    //throw new Error('Method not implemented.');
    this.Showdata12=false;
    this.showPdfButton=false;
    this.summaryData=[]
    this.tableColumns=[]
  }
  exportAsXLSX(): void {
    this.reportFilterForm.value.exportType="EXCEL";
    this.reportService.getReport122B(this.reportFilterForm.value).subscribe(res => {
        let byteArrays = res.byteData;
        utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
  });
  }

  ngOnInit(): void {
    console.log("inside reports122B")

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


