import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from '../../../../shared/shared.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-report149',
  templateUrl: './report149.component.html',
  styleUrls: ['./report149.component.scss']
})
export class Report149Component implements OnInit , ReportChildComponent {

  @Input("reportInfoObj")
  reportInfoObj: any;
  @Input() reportFilterForm: any;

 
  datePipe: DatePipe = new DatePipe('en-GB');

  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean = false;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean = false;
  // is2020: boolean = false;
  
  pageSize: any = 10;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;

  getReport: any;
  constructor(private getService: GetService, public sharedService: SharedService) { }
  exportAsXLSX(): void {
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
let file="EXCEL";
const date1=this.reportFilterForm.value.datePickerStart;
    this.reportFilterForm.value.datePickerStart = this.datePipe.transform(this.reportFilterForm.value.datePickerStart, 'dd/MM/YYYY');
    this.reportFilterForm.value.datePickerEnd = this.datePipe.transform(this.reportFilterForm.value.datePickerEnd, 'dd/MM/YYYY');
    this.getService.getReport149(this.reportFilterForm.value,file).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  ngOnInit(): void {
// console.log('kkl',this.reportFilterForm)
  }

  getReportDataTable(): void {
    this.isDataLoading = true;
    // this.reportFilterForm.value.exportType = "JSON";
    this.showPdfButton = true;

this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.datePickerStart = this.datePipe.transform(this.reportFilterForm.value.datePickerStart, 'dd/MM/YYYY');
    this.reportFilterForm.value.datePickerEnd = this.datePipe.transform(this.reportFilterForm.value.datePickerEnd, 'dd/MM/YYYY');
let file="JSON";
    this.getService.getReport149(this.reportFilterForm.value,file).pipe().subscribe(res => {
      if (res.list.length > 0) {
        this.Showdata12 = true;
        this.showPdfButton = true;
        this.reportTableData = res.list;
        // console.log('table',this.reportTableData);
        // this.tableColumns = Object.keys(res.list[0]);
        // this.summaryData = res.list[res.list.length - 1];
        // this.summaryColumns = Object.keys(this.summaryData);
      } else {
        // this.tableColumns = [];
        // this.summaryData = [];
        this.sharedService.showError('Data is not available');
        this.Showdata12 = false;
        this.showPdfButton = false;
      }
    });
  }
  generatePDF(): void {
this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    let file = "PDF";
       this.reportFilterForm.value.datePickerStart = this.datePipe.transform(this.reportFilterForm.value.datePickerStart, 'dd/MM/YYYY');
    this.reportFilterForm.value.datePickerEnd = this.datePipe.transform(this.reportFilterForm.value.datePickerEnd, 'dd/MM/YYYY');
    this.getService.getReport149(this.reportFilterForm.value,file).subscribe(res => {
      let byteArrays = res.byteData;
      utility.downloadPdf(byteArrays, this.reportInfoObj.reportNumber);
    });
  }

  onReset(): void {
    //throw new Error('Method not implemented.');
    this.Showdata12 = false;
    this.showPdfButton = false;
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