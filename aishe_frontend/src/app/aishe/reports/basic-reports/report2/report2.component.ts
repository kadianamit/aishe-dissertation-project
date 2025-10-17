/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { ExcelService } from 'src/app/service/reports/excel.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { SharedService } from 'src/app/shared/shared.service';
// import { ExcelService } from 'src/app/service/excel.service';
// import { ReportService } from 'src/app/service/report.service';

@Component({
  selector: 'app-report2',
  templateUrl: './report2.component.html',
  styleUrls: ['./report2.component.scss']
})
export class Report2Component implements OnInit, ReportChildComponent {

  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFilterForm")
  reportFilterForm: any;


  reportTableData: any=[];
  tableColumns: any=[];
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false ;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  surveyYear: any;

  constructor(private reportService:ReportService,private excelService:ExcelService,private sharedservice:SharedService) { }
  exportAsXLSX(): void {
    this.reportFilterForm.value.reportType="EXCEL";
    this.reportService.getCollagedata(this.reportFilterForm.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  ngOnInit(): void {
    console.log("inside reports2")
  }
  getReportDataTable(): void {

    this.isDataLoading=true;
    this.reportFilterForm.value.reportType = "JSON";
    this.reportService.getReportCollegeBasicData(this.reportFilterForm.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe(res => {
        if(!res || res.length==0){
          this.reportTableData=[];
          this.tableColumns = [];
          this.sharedservice.showError("No records !!");
        }else{
          this.surveyYear=this.reportFilterForm.value.surveyYear 
          this.Showdata12=true;
          this.showPdfButton = true;
          this.reportTableData=res[0];
          this.tableColumns = Object.keys(res[0]);
        }

    });
  }
  generatePDF(): void {

    this.reportFilterForm.value.reportType="PDF";
    utility.generatePDF(this.reportInfoObj.reportNumber,this.reportInfoObj.reportTitle,this.reportFilterForm,this.reportService);
  }

  onReset(): void {
    //throw new Error('Method not implemented.');
    this.isDataLoading=false;
    this.Showdata12=false;
    this.showPdfButton=false;
    this.tableColumns=false;
    this.reportTableData=false;
  }

}
