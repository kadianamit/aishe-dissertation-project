/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { ReportService } from 'src/app/service/reports/report.service';
import {ExcelService} from 'src/app/service/reports/excel.service'
import { HttpHeaders } from '@angular/common/http';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report10',
  templateUrl: './report10.component.html',
  styleUrls: ['./report10.component.scss']
})
export class Report10Component implements OnInit, ReportChildComponent {

  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFilterForm")
  reportFilterForm: any;

  @Input("reportDataForm")
  reportDataForm: any;

  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | any;
  isDataLoading: boolean=false ;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  reportFormlistofInst: any;
  getReport: any;
  is2019:boolean=false;
  is2020:boolean=false;
  excel:any[]=[];  
  constructor(
  private reportService:ReportService, public ExcelService:ExcelService,private sharedService:SharedService
    ) { }
  ngOnInit(): void {
    console.log("inside reports10")

  }

  getReportDataTable(): void {
    this.Showdata12=true;
    this.isDataLoading=true;
  this.reportFilterForm.value = this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType = "JSON";
    this.reportService.getReport10(this.reportFilterForm.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe((res:any) => {
        if(res.list.length > 0){
          this.is2019 = this.reportFilterForm.value.surveyYear <= '2019';
          this.is2020 = this.reportFilterForm.value.surveyYear >= '2020';

          this.showPdfButton=true;
         this.Showdata12=true;
         this.reportTableData=res.list.slice(0, res.list.length-1); //to subarray except last object
         this.tableColumns = Object.keys(res.list[0]); //to get all columns name

        this.summaryData=res.list[res.list.length-1];
        this.summaryColumns=Object.keys(this.summaryData);
         }else{
          this.sharedService.showError('Data is not available');
          this.Showdata12=false;
          this.showPdfButton = false;

         }
    });
    
  }
  generatePDF(): void {
  this.reportFilterForm.value = this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="PDF";
    //console.log(this.reportFilterForm.value);
    this.reportService.getReport10(this.reportFilterForm.value).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);

    });
  }

  exportAsXLSX():void {  
  this.reportFilterForm.value = this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="EXCEL";
    this.reportService.getReport10(this.reportFilterForm.value).subscribe((res:any) => {
      let byteArrays = res.byteData;
      utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);

});
}


  onReset(): void {
    //throw new Error('Method not implemented.');
    this.Showdata12=false;
    this.showPdfButton=false;
  }

}


