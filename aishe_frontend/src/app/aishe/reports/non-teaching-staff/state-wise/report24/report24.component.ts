/* eslint-disable @angular-eslint/no-input-rename */
import { Component, Input, OnInit } from '@angular/core';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { ReportService } from 'src/app/service/reports/report.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report24',
  templateUrl: './report24.component.html',
  styleUrls: ['./report24.component.scss']
})
export class Report24Component implements OnInit,ReportChildComponent {


  reportTableData: any;
  tableColumns: any;
  Showdata12: boolean | undefined;
  isDataLoading: boolean=false;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  is2020:boolean=false;
  pageSize: any = 10;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;

  @Input("reportInfoObj")
  reportInfoObj: any;
  @Input("reportFilterForm")
  reportFilterForm: any;
  roleId: string;
  stateCodeDeafult: string;
  constructor(private reportService: ReportService,private sharedService:SharedService,public localService:LocalserviceService) { 
     this.roleId = this.localService.getData('roleId')
    console.log('00roleid',typeof(this.roleId),this.roleId)
       this.stateCodeDeafult = this.localService.getData('stateCode');
       console.log(' this.stateCodeDeafult', this.stateCodeDeafult)
  }

  getReportDataTable(): void {

    this.isDataLoading=true;
    this.reportFilterForm.value.reportType = "JSON";
      if(this.roleId === this.sharedService.role['State Nodal Officer'] &&  this.reportFilterForm.value.surveyYear >= '2021'){
        this.reportFilterForm.value.defaultState = this.stateCodeDeafult;
    }
    this.reportService.getReport24(this.reportFilterForm.value).subscribe(
        res => {

          if( res.list.length > 0 ){
            this.is2020 = this.reportFilterForm.value.surveyYear >= '2020';

            this.showPdfButton=true;
            this.Showdata12=true
          this.reportTableData=res.list.slice(0,res.list.length-1);  //take untill 2nd last index
          this.tableColumns = Object.keys(res.list[0]);
           this.summaryData = res.list[res.list.length-1]; //take summary data of last object getReport117A
           this.summaryColumns=Object.keys(this.summaryData);
           this.handlePageChange(1);
         }else{
          this.summaryData=[];
          this.summaryColumns=[];
          this.reportTableData=null;
          this.sharedService.showError('Data is not available');
          this.Showdata12=false;
          this.showPdfButton = false;
         }
        });

  }
  generatePDF(): void {

    this.reportFilterForm.value.reportType="PDF";
      if(this.roleId === this.sharedService.role['State Nodal Officer'] &&  this.reportFilterForm.value.surveyYear >= '2021'){
        this.reportFilterForm.value.defaultState = this.stateCodeDeafult;
    }
    this.reportService.getReport24(this.reportFilterForm.value).subscribe(res => {
        let byteArrays = res.byteData;
        utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
  });
  }
  onReset(): void {
    //throw new Error('Method not implemented.');
    this.Showdata12=false;
    this.showPdfButton=false;
  }
  exportAsXLSX(): void {
    this.reportFilterForm.value.reportType="EXCEL";
      if(this.roleId === this.sharedService.role['State Nodal Officer'] &&  this.reportFilterForm.value.surveyYear >= '2021'){
        this.reportFilterForm.value.defaultState = this.stateCodeDeafult;
    }
    this.reportService.getReport24(this.reportFilterForm.value).subscribe(res => {
        let byteArrays = res.byteData;
        utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
  });
  }

  ngOnInit(): void {
    console.log("inside reports24")
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
