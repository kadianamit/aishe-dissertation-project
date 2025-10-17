import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { ReportService } from 'src/app/service/reports/report.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report14',
  templateUrl: './report14.component.html',
  styleUrls: ['./report14.component.scss']
})
export class Report14Component implements OnInit, ReportChildComponent {

  @Input()
  reportInfoObj: any;

  @Input()
  reportFilterForm: any;

  @Input()
  reportDataForm: any;

  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false ;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;

  constructor(
  private reportService:ReportService,private sharedService:SharedService
    ) { }
  exportAsXLSX(): void {
  this.reportFilterForm.value = this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.reportType="EXCEL";
    this.reportService.getStateWiseNumOfUniversityOfferingDLPdf(this.reportFilterForm.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  ngOnInit(): void {
    console.log("inside report14")
  }

  getReportDataTable(): void {

    this.isDataLoading=true;
  this.reportFilterForm.value = this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.reportType = "JSON";
    this.reportService.getStateWiseNumOfUniversityOfferingDL(this.reportFilterForm.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe(res => {
        if(res.length > 0){

          this.showPdfButton=true;
         this.Showdata12=true;
          this.reportTableData=res;
          if(this.reportFilterForm.value.addressStateCode.stateCode==='ALL' && this.reportFilterForm.value.surveyYear >= '2021'){
         this.reportTableData=res.slice(0, res.length-1); //to subarray except last object
          }
         this.tableColumns = Object.keys(res[0]); //to get all columns name

        this.summaryData=res[res.length-1];
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
    this.reportFilterForm.value.reportType="PDF";
    this.reportService.getStateWiseNumOfUniversityOfferingDLPdf(this.reportFilterForm.value).subscribe(res => {
      let byteArrays = res.byteData;
      utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
});
    //utility.generatePDF(this.reportInfoObj.reportNumber,this.reportInfoObj.reportTitle,this.reportFilterForm,this.reportService); 

  }

  onReset(): void {
    //throw new Error('Method not implemented.');
    this.Showdata12=false;
    this.showPdfButton=false;
  }

}
