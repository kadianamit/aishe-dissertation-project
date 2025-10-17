/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report12',
  templateUrl: './report12.component.html',
  styleUrls: ['./report12.component.scss']
})
export class Report12Component implements OnInit , ReportChildComponent {

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
  aisheCode: any;
  splitAisheCode: any;
  constructor(
  private reportService:ReportService,private sharedService:SharedService, public encrypt: EncryptDecrypt,public localService:LocalserviceService
  
    ) {

   this.aisheCode=localService.getData('aisheCode')
  
   this.splitAisheCode=this.aisheCode.split('-')[1]
   console.log('split',this.splitAisheCode)
     }
  exportAsXLSX(): void {
  this.reportFilterForm.value = this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="EXCEL";
    this.reportFilterForm.value.universityId=this.splitAisheCode
    this.reportService.getreport12(this.reportFilterForm.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  ngOnInit(): void {
    console.log("inside reports9")

  }

  getReportDataTable(): void {
    this.Showdata12=true;
    this.isDataLoading=true;
  this.reportFilterForm.value = this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType = "JSON";
     this.reportFilterForm.value.universityId=this.splitAisheCode
    this.reportService.getreport12(this.reportFilterForm.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe((res:any) => {
        if(res.list.length > 0){
          this.is2020 = this.reportFilterForm.value.surveyYear == '2020';

          this.Showdata12=true;
          this.showPdfButton=true;
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
  // generatePDF(): void {

  // utility.generatePDF(this.reportInfoObj.reportNumber,this.reportInfoObj.reportTitle,this.reportFilterForm,this.reportTableData);

  // }


  generatePDF(): void {
  this.reportFilterForm.value = this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="PDF";
     this.reportFilterForm.value.universityId=this.splitAisheCode
    this.reportService.getreport12(this.reportFilterForm.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }

  onReset(): void {
    //throw new Error('Method not implemented.');
    this.Showdata12=false;
    this.showPdfButton=false;
  }

}

