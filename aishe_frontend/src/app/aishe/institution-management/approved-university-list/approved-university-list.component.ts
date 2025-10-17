import { Component, Input, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import { PagingConfig } from 'src/app/model/paging-config.model';
import { LocalserviceService } from 'src/app/service/localservice.service';
@Component({
  selector: 'app-approved-university-list',
  templateUrl: './approved-university-list.component.html',
  styleUrls: ['./approved-university-list.component.scss']
})
export class ApprovedUniversityListComponent implements OnInit {

  @Input()
 instiFormData:any;
  userId:any;
  obj:any;
  surveryYear:any
  searchText:any;
  page: number = 1;
  pageSize: any = 5;
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
  isApproveList:boolean=false;
  dataSimilarCollege:any[]= [];
  isFormInvalid: any;
  constructor(private location: Location,
    private institutionmanagement:InstitutionmanagementService,
    public sharedservice: SharedService,public localService:LocalserviceService) {
     // this.sharedservice.global_loader = true;
    }

  ngOnInit(): void {
    this.approvalUniversity();
  }
  back1(): void {
    this.showTable=true
    this.showDataTable=false;
    this.isApproveList=false;
    this.dataSimilarCollege=[];
    this.showSimilarCollege=false;
    // this.sharedservice.global_loader = true;
    // this.location.back();
  }
  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  resetForm1(){
    this.alldata=[];
    this.page = 1;
    this.pageSize= 5;
    this.searchText= null;
    this.handlePageChange(this.page);
  }
  approvalUniversity(){
    // console.log(this.instiFormData)
    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    this.searchText=null;
    this.userId = this.localService.getData('userId')
    if(this.instiFormData.value.surveyYearValue){
    this.surveryYear=this.instiFormData.value.surveyYearValue.split('-')[0]
    }else{
      this.sharedservice.showError("Please Select Survey Year");
    }

    if (this.surveryYear) {
      this.institutionmanagement.getApprovalUniversityList(this.userId, this.surveryYear).subscribe((res: any) => {
        this.alldata = res.approvedStatusByUniversityListBean;

        if (res.statusCode == "AISH099") {
          this.sharedservice.showError("No records !!");
          this.showTable = true;

        }
        else if (res.statusCode == "AISH001") {
          this.sharedservice.showSuccessMessage('Processed successfully');
          this.showTable = true;
          this.showDataTable = false;
          this.isApproveList = false;
        }

      });
    } else {
      this.sharedservice.showError("Please Select Survey Year");
    }
  }

      onChange(event: any): void {
        this.pageSize = parseInt(event);
        this.page=1;
        this.handlePageChange(this.page);
      }
      handlePageChange(event: any): void {
        this.page = event;
        let fgt=parseInt(this.pageSize);
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
      allDataView(data:any){
        this.showTable=false
        this.showDataTable=true;
        this.isApproveList=true;
        let idR=data.requestId
        this.institutionmanagement.getCollegeApprovalRequestDetails(idR ,this.instiFormData.value.surveyYearValue.split('-')[0]).subscribe((res)=>{
        this.obj=res.collegeApprovalRequestDetailsBean

        if(res.collegeApprovalRequestDetailsBean.similarCollege.length > 0){
        this.showSimilarCollege=true;
        this.dataSimilarCollege=res.collegeApprovalRequestDetailsBean.similarCollege;

        //console.log(this.dataSimilarCollege)

        }
        if(res.statusCode==='AISH099'){

        this.showDataTable=false;
        this.showTable=true

        this.sharedservice.showError(res.statusDesc);
      }
      // if(this.obj){
      //   this.dataCollege=Object.values(this.obj);
      // }
   });
      }


    }
