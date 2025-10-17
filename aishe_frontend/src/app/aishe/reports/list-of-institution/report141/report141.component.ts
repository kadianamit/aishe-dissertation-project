import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report141',
  templateUrl: './report141.component.html',
  styleUrls: ['./report141.component.scss']
})
export class Report141Component implements OnInit, ReportChildComponent {

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

  @Input()
  reportInfoObj: any;

  @Input()
  reportFormlistofInst: any;
  constructor(private getReport:GetService,public sharedService: SharedService) { }
  exportAsXLSX(): void {
    this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.reportType="EXCEL";
    this.getReport.getReport141(this.reportFormlistofInst.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }

  getReportDataTable(): void {

   this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.reportType = "JSON";
    this.getReport.getReport141(this.reportFormlistofInst.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe(res => {
        if (res.list.length > 0) {
        this.Showdata12=true;
        this.showPdfButton = true;
        this.reportTableData = res.list.slice(0, res.list.length);

//             const keysToRemove = [
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
      // this.summaryColumns = Object.keys(this.summaryData);
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
    this.getReport.getReport141(this.reportFormlistofInst.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  onReset(): void {
    this.Showdata12=false;
    this.showPdfButton=false;
  }
  ngOnInit(): void {
    console.log("inside reports141")

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
