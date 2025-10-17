/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { GetService } from 'src/app/service/get/get.service';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { SharedService } from '../../../../../shared/shared.service';

@Component({
  selector: 'app-report101b',
  templateUrl: './report101b.component.html',
  styleUrls: ['./report101b.component.scss']
})
export class Report101bComponent implements OnInit , ReportChildComponent {
  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFilterForm")
  reportFilterForm: any;

  @Input("inputObj")
  inputObj: any;
  pageSize: any = 5;
  page: number = 1
  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false ;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  is2020:boolean=false;
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  constructor(private getService:GetService ,public sharedService:SharedService) { }
  exportAsXLSX(): void {
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="EXCEL";
    this.getService.getReport101B(this.reportFilterForm.value,this.inputObj).subscribe((res:any) => {
        let byteArrays = res.byteData;
        utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
  });
  }
  ngOnInit(): void {
    console.log("inside reports100c")

  }

  getReportDataTable(): void {

    this.isDataLoading=true;
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType = "JSON";
    this.getService.getReport101B(this.reportFilterForm.value,this.inputObj).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })

      ).subscribe((res:any) => {

        if (res.list.length > 0) {
          this.is2020 = this.reportFilterForm.value.surveyYear >= '2020';

        this.Showdata12=true;
        this.showPdfButton = true;
        this.reportTableData = res.list.slice(0, res.list.length);
        this.reportTableData = res.list;
        this.tableColumns = Object.keys(res.list[0]);
        // this.summaryData = res.list[res.list.length - 1];
        // this.summaryColumns = Object.keys(this.summaryData);
        this.handlePageChange(1);
      } else {
        this.sharedService.showError('Data is not available');
        // this.tableColumns = [];
        // this.summaryData = [];
      
        this.Showdata12=false;
        this.showPdfButton = false;
      }
    });
  }
  generatePDF(): void {
this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.exportType="PDF";
    this.getService.getReport101B(this.reportFilterForm.value,this.inputObj).subscribe((res:any) => {
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
