import { Component, Input, OnInit } from '@angular/core';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-standalone-approve-list',
  templateUrl: './standalone-approve-list.component.html',
  styleUrls: ['./standalone-approve-list.component.scss']
})
export class StandaloneApproveListComponent implements OnInit {

  @Input()
  instiFormData:any;
   userId:any;
   obj:any;
   surveyYear:any
   searchText:any;
   page: number = 1;
   pageSize: any = 10;
   pageData: number = 5;
   EndLimit: number = 15;
   StartLimit: number = 0;
   tableSize: number[] = [5, 10, 15, 20];
   showTable:boolean=false
 showDataTable:boolean=false;
 dataCollege:any;
 showSimilarCollege:boolean = false;
   count: number = 0;
   alldata: any;
   isApproveList1:boolean=false;
   dataSimilarCollege:any[] = [];
   constructor(private institutionmanagement:InstitutionmanagementService,
     public sharedservice: SharedService,public localService:LocalserviceService) {

     }

   ngOnInit(): void {
     this.approvalUniversity();

   }
   applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
   back1(): void {
     this.showTable=true
     this.showDataTable=false;
     this.isApproveList1=false;
     this.showSimilarCollege=false;
 this.dataSimilarCollege=[];

   }
   resetData(){
    this.alldata=[];
    this.searchText=null;
    this.pageSize=10;
    this.page = 1;
    this.handlePageChange(this.page);
   }

   approvalUniversity(){
    this.searchText = null;
    if(this.instiFormData.value.surveyYearValue==null){
      this.sharedservice.showError('Please Select Survey Year');
      return;
    }
    if (this.instiFormData.value.surveyYearValue) {
      this.surveyYear = this.instiFormData.value.surveyYearValue.split('-')[0];
      this.userId = this.localService.getData('userId')
      this.institutionmanagement.getApprovalStandaloneList(this.userId, this.surveyYear).subscribe((res: any) => {
        this.alldata = res.approvedStatusByUniversityListBean;
  
        if (res.statusCode == "AISH099") {
          this.sharedservice.showError("No records !!");
          this.showTable = false;
          this.alldata = [];
          this.instiFormData.reset();
          this.showDataTable = false;
        }
        else if (res.statusCode == "AISH001") {
          this.sharedservice.showSuccessMessage('Processed successfully');
          this.showTable = true;
          this.showDataTable = false;
          this.page = 1;
          this.handlePageChange(this.page);
        }
  
      });
    }

  }
  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
  }
  handlePageChange(event: any): void {
    this.page = event;
    let fgt = parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * fgt),
      (this.EndLimit = this.StartLimit + fgt);
    var a = Math.ceil(this.alldata.length / fgt);
    if (a === event) {
      this.pageData = Math.min(
        this.StartLimit + fgt,
        this.alldata.length
      );
    } else {
      this.pageData = Math.min(
        this.StartLimit + fgt,
        this.alldata.length
      );
    }
  }
  allDataView(data: any) {

    let idR = data.requestId
    this.institutionmanagement.getStandalonRequestDetails(idR, this.instiFormData.value.surveyYearValue.split('-')[0]).subscribe((res) => {
      this.obj = res.standaloneApprovalRequestDetailsBean


      if (res.standaloneApprovalRequestDetailsBean.similarCollege.length > 0) {
        this.showSimilarCollege = true;
        this.dataSimilarCollege = res.standaloneApprovalRequestDetailsBean.similarCollege;

      }
      if (res.statusCode === 'AISH099') {

        this.showDataTable = false;
        this.showTable = true
        this.sharedservice.showError(res.statusDesc);
      }
      if (this.obj) {
        this.dataCollege = Object.values(this.obj);
        this.showTable = false
        this.showDataTable = true;
        this.isApproveList1 = true;
      }
    });
  }


}
