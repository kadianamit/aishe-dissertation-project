import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report146',
  templateUrl: './report146.component.html',
  styleUrls: ['./report146.component.scss']
})
export class Report146Component implements OnInit, ReportChildComponent {

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
  is2021:boolean=false;
  is2019:boolean=false;
  is2020:boolean=false;

  @Input()
  reportInfoObj: any;

  @Input()
  reportFormlistofInst: any;
surveyYear2019: any;
  constructor(private getReport:GetService,public sharedService: SharedService) { }
  exportAsXLSX(): void {
    this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.reportType="EXCEL";
    this.getReport.getReport146(this.reportFormlistofInst.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }

  getReportDataTable(): void {
this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.reportType = "JSON";
    this.getReport.getReport146(this.reportFormlistofInst.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe(res => {
        if (res.list.length > 0) {
          // this.is2021 = this.reportFormlistofInst.value.surveyYear >= '2021';
          // this.is2020 = this.reportFormlistofInst.value.surveyYear === '2020';
          // this.is2019 = this.reportFormlistofInst.value.surveyYear <= '2019';
          this.surveyYear2019=this.reportFormlistofInst.value?.surveyYear;
        this.Showdata12=true;
        this.showPdfButton = true;
        this.reportTableData = res.list.slice(0, res.list.length);
//         const keysToRemove = [
//   "Head Name",
//   "Head Email",
//   "Head Mobile",
//   "Nodal Office Name",
//   "Nodal Officer Email",
//   "Nodal Officer Mobile"
// ];
//        this.reportTableData.forEach((obj:any) => {
//   keysToRemove.forEach(key => delete obj[key]);
// });
        this.tableColumns = Object.keys(res.list[0]);
        // this.summaryData = res.list[res.list.length - 1];
        this.summaryColumns = Object.keys(this.summaryData);
        this.handlePageChange(1)
      } else {
        this.Showdata12=false;
        this.showPdfButton = false;
        this.reportTableData = [];
        this.showPdfButton = false;
        this.sharedService.showError('Data is not available');
      }
    });


  }
  generatePDF(): void {
    this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.reportType="PDF";
    this.getReport.getReport146(this.reportFormlistofInst.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
  }

  ngOnInit(): void {
    console.log("inside reports146")

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
