/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { LevelService } from 'src/app/service/get/level.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { SharedService } from '../../../../../shared/shared.service';

@Component({
  selector: 'app-report108',
  templateUrl: './report108.component.html',
  styleUrls: ['./report108.component.scss']
})
export class Report108Component implements OnInit, ReportChildComponent {


  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  is2021:any;
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

  constructor(private courseLevel:LevelService,private ReportService:ReportService,public sharedService:SharedService) { }
  exportAsXLSX(): void {
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.reportType="EXCEL";
    this.ReportService.getReport108(this.reportFilterForm.value).subscribe((res:any) => {
        let byteArrays = res.byteData;
        utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
  });
  }
  ngOnInit(): void {
    console.log("inside report108")
  }


  getReportDataTable(): void {
    this.reportTableData=[]
    // this.Showdata12=true;
    // this.isDataLoading=true;
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.reportType = "JSON";
    console.log(this.reportFilterForm.value);
    this.ReportService.getReport108(this.reportFilterForm.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
    ).subscribe(res => {
      if (res.list.length > 0) {
        this.is2020 = this.reportFilterForm.value.surveyYear >= '2020';
        this.is2021=this.reportFilterForm.value.surveyYear
        this.showPdfButton = true;
        this.Showdata12=true;
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
    this.reportFilterForm.value.reportType="PDF";
    this.ReportService.getReport108(this.reportFilterForm.value).subscribe(res => {
        let byteArrays = res.byteData;
        utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
  });
  }


  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
    this.tableColumns = [];
    this.summaryData = [];
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
