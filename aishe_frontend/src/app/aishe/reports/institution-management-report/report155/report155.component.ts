import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';
import { NotificationService } from 'src/app/service/reports/notification.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report155',
  templateUrl: './report155.component.html',
  styleUrls: ['./report155.component.scss']
})
export class Report155Component implements OnInit {
   reportTableData: any;
  tableColumns: any;
  Showdata12: boolean =false;
  isDataLoading: boolean=false;
  summaryColumns: any;
  summaryData: any;
  showPdfButton: boolean=false;
  pageSize: any = 15;
  page: number = 1
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  @Input("reportInfoObj")
  reportInfoObj: any;
  is2021:boolean=false;
  is2019:boolean=false;
  
  @Input("reportFormlistofInst")
  reportFormlistofInst: any;
  @Input("reportFilterForm")
  reportFilterForm: any;
  is2020: any;
  constructor(private getReport:GetService, public sharedService: SharedService,public notificationService:NotificationService) { }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  exportAsXLSX(): void {
    this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    this.reportFormlistofInst.value.reportType="EXCEL";
    this.getReport.getReport155(this.reportFormlistofInst.value).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  
 
  generatePDF(): void {
    this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
    if(this.reportFormlistofInst.value.fileType===null){
      this.notificationService.showError('Please select File Type');
     return;
       }
       if(this.reportFormlistofInst.value.fileType==='PDF'){

        this.reportFormlistofInst.value.reportType=this.reportFormlistofInst.value.fileType;
    // this.reportFormlistofInst.value.reportType="PDF";
    this.getReport.getReport155(this.reportFormlistofInst.value).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
  }
  // onReset(): void {
  //   this.Showdata12=false;
  //   this.showPdfButton=false;
  // }
 
  }
}