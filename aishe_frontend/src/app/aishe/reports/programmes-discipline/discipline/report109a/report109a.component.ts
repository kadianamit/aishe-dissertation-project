import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { PostService } from 'src/app/service/post/post.service';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { SharedService } from '../../../../../shared/shared.service';

@Component({
  selector: 'app-report109a',
  templateUrl: './report109a.component.html',
  styleUrls: ['./report109a.component.scss']
})
export class Report109aComponent implements OnInit, ReportChildComponent {

  @Input()
  reportInfoObj: any;

  @Input()
  reportFilterForm: any;


  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false ;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  is2020:boolean=false;
  pageSize: any = 15;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;

  constructor(private PostService:PostService,public sharedService:SharedService) { }
  exportAsXLSX(): void {
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.reportType="EXCEL";
    this.PostService.getReport109A(this.reportFilterForm.value).subscribe((res:any) => {
        let byteArrays = res.byteData;
        utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
  });
  }
  ngOnInit(): void {
    console.log("inside reports109a")

  }
  getReportDataTable(): void {

    this.isDataLoading=true;
    this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.reportType = "JSON";
    this.PostService.getReport109A(this.reportFilterForm.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe(res => {
        if (res.list.length > 0) {
          this.is2020 = this.reportFilterForm.value.surveyYear >= '2020';

        this.Showdata12=true;
        this.showPdfButton = true;
        this.reportTableData = res.list.slice(0, res.list.length - 1);
        this.tableColumns = Object.keys(res.list[0]);
        this.summaryData = res.list[res.list.length - 1];
        this.summaryColumns = Object.keys(this.summaryData);
      } else {
        // this.tableColumns = [];
        // this.summaryData = [];
        this.sharedService.showError('Data is not available');
        this.Showdata12=false;
        this.showPdfButton = false;
      }
    });
  }
  generatePDF(): void {
this.reportFilterForm.value=this.reportFilterForm.getRawValue();
    this.reportFilterForm.value.reportType="PDF";
    this.PostService.getReport109A(this.reportFilterForm.value).subscribe(res => {
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
