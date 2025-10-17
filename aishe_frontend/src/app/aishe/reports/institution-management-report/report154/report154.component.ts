import { Component, Input, OnInit } from '@angular/core';
import { finalize } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { ReportChildComponent } from 'src/app/interface/ReportChildComponent';
import { GetService } from 'src/app/service/get/get.service';
import { NotificationService } from 'src/app/service/reports/notification.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-report154',
  templateUrl: './report154.component.html',
  styleUrls: ['./report154.component.scss']
})
export class Report154Component implements OnInit { reportTableData: any;
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

@Input() reportFormlistofInst: any;
@Input("reportFilterForm")
reportFilterForm: any;
is2020: any;
constructor(private getReport:GetService, public sharedService: SharedService,public notificationService:NotificationService) { }
exportAsXLSX(): void {
  this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
  let file ="EXCEL";
  this.getReport.getReport154(this.reportFormlistofInst.value,file).subscribe((res:any) => {
        let byteArrays = res.byteData;
        utility.downloadAsExcel(byteArrays,this.reportInfoObj.reportNumber);
  });
}

getReportDataTable(): void {
  this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
  let file = "JSON";
  this.getReport.getReport154(this.reportFormlistofInst.value,file).pipe(
   
    ).subscribe((res:any) => {
      if (res.list.length > 0) {
      
      this.Showdata12=true;
      this.showPdfButton = true;
      this.reportTableData = res.list.slice();
     this.handlePageChange(1)
    } else {
      this.Showdata12=false;
      this.tableColumns = [];
      this.showPdfButton = false;
      this.sharedService.showError('Data is not available');
    }
  });


}

generatePDF(): void {
  this.reportFormlistofInst.value=this.reportFormlistofInst.getRawValue();
  let file = "PDF";
    this.getReport.getReport154(this.reportFormlistofInst.value,file).subscribe((res:any) => {
          let byteArrays = res.byteData;
          utility.downloadPdf(byteArrays,this.reportInfoObj.reportNumber);
    });
 
  
}
onReset(): void {
  this.Showdata12=false;
  this.showPdfButton=false;
}
ngOnInit(): void {

  console.log("inside reports154")

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
