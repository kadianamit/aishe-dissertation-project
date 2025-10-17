/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { ReportService } from 'src/app/service/reports/report.service';
import { SharedService } from '../../../../../shared/shared.service';
import { LocalserviceService } from 'src/app/service/localservice.service';


@Component({
  selector: 'app-report138',
  templateUrl: './report138.component.html',
  styleUrls: ['./report138.component.scss']
})
export class Report138Component implements OnInit , ReportChildComponent {

  @Input("reportInfoObj")
  reportInfoObj: any;

  @Input("reportFilterForm")
  reportFilterForm: any;
  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false ;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  is2019:boolean=false;
  is2020:boolean=false;
  pageSize: any = 10;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  is2021:boolean=false;
  roleId: any;
  stateCodeDeafult:any
  constructor(
  private reportService:ReportService,public sharedService:SharedService, public localService: LocalserviceService
    ) {  this.roleId = this.localService.getData('roleId')
    console.log('00roleid', typeof (this.roleId), this.roleId)
    this.stateCodeDeafult = this.localService.getData('stateCode');
    console.log(' this.stateCodeDeafult', this.stateCodeDeafult)
  }
  exportAsXLSX(): void {
    this.reportFilterForm.value.exportType="EXCEL";
    //  if (this.roleId === this.sharedService.role['State Nodal Officer'] && this.reportFilterForm.value.surveyYear >= '2021') {
    //   this.reportFilterForm.value.defaultState = this.stateCodeDeafult;
    //  }
    this.reportService.getReport138(this.reportFilterForm.value).subscribe(res => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  ngOnInit(): void {
    // console.log(this.reportFilterForm.value)
  }

  getReportDataTable(): void {

    // this.isDataLoading=true;
    this.reportTableData=[];
    this.reportFilterForm.value.exportType = "JSON";
    //  if (this.roleId === this.sharedService.role['State Nodal Officer'] && this.reportFilterForm.value.surveyYear >= '2021') {
    //   this.reportFilterForm.value.defaultState = this.stateCodeDeafult;
    //  }
    this.reportService.getReport138(this.reportFilterForm.value).pipe(
      finalize(() => {
        this.isDataLoading = false;
      })
      ).subscribe((res :any)=> {
        if(res.list.length > 0){
          this.is2019 = this.reportFilterForm.value.surveyYear <= '2019';
          this.is2020 = this.reportFilterForm.value.surveyYear == '2020';
          this.is2021 = this.reportFilterForm.value.surveyYear >= '2021';

          this.showPdfButton=true;
         this.Showdata12=true;
         this.reportTableData=res.list.slice(0, res.list.length-1); //to subarray except last object
         this.tableColumns = Object.keys(res.list[0]); //to get all columns name

        this.summaryData=res.list[res.list.length-1];
        this.summaryColumns=Object.keys(this.summaryData);
        this.handlePageChange(1);
         }else{
          this.reportTableData=[];
          this.tableColumns = [];
          this.summaryData = [];
          this.Showdata12=false;
          this.showPdfButton = false
          this.sharedService.showError("Data is not available");
         }
    });
  }
  generatePDF(): void {
    this.reportFilterForm.value.exportType="PDF";
    //  if (this.roleId === this.sharedService.role['State Nodal Officer'] && this.reportFilterForm.value.surveyYear >= '2021') {
    //   this.reportFilterForm.value.defaultState = this.stateCodeDeafult;
    //  }
    this.reportService.getReport138(this.reportFilterForm.value).subscribe(res => {
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

