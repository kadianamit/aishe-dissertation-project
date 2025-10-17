/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from '../../../../../shared/shared.service';

@Component({
  selector: 'app-report38c',
  templateUrl: './report38c.component.html',
  styleUrls: ['./report38c.component.scss']
})
export class Report38cComponent implements ReportChildComponent {

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
  is2020:boolean=false;
  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFilterForm")
  reportFilterForm: any;
  constructor(private getReport:GetService,public sharedService:SharedService) { }
  exportAsXLSX(): void {
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="EXCEL";
    this.getReport.getReport38C(this.reportFilterForm.value).subscribe((res:any) => {
        let byteArrays = res.byteData;
        utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
  });
  }


  getReportDataTable(): void {
    this.reportTableData=[];
   // console.log(this.reportFilterForm.value);
    this.Showdata12=true;
    this.isDataLoading=true;
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType = "JSON";
    this.getReport.getReport38C
    (this.reportFilterForm.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe(res => {
        if (res.list.length > 0) {
          this.is2020 = this.reportFilterForm.value.surveyYear >= '2020';
        this.Showdata12=true;
        this.showPdfButton = true;
        // this.reportTableData = res.list.slice(0, res.list.length - 1);
        this.reportTableData = res.list;

        this.tableColumns = Object.keys(res.list[0]);
        // this.summaryData = res.list[res.list.length - 1];
      //  this.summaryColumns = Object.keys(this.summaryData);
       this.handlePageChange(this.page);
      } else {
        this.sharedService.showError('Data is not available');
        this.Showdata12=false;
        this.showPdfButton = false;
      }
    });


  }
  generatePDF(): void {
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="PDF";
    this.getReport.getReport38C(this.reportFilterForm.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
  }
 // ngOnInit(): void {
   /// console.log("inside reports38C")
   //  console.log(this.reportFilterForm);

//  }
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
