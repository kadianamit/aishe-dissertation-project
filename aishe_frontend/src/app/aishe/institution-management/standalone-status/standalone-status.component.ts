import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-standalone-status',
  templateUrl: './standalone-status.component.html',
  styleUrls: ['./standalone-status.component.scss']
})
export class StandaloneStatusComponent implements OnInit {

  @Input()
  instiFormData:any;
  userDataTable:any[]=[];
  tabledata: boolean = false;
  userData1: any = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  isStatus1:boolean = false;
  showTable:boolean = false;
  tableSize: number[] = [10, 20, 30, 40, 50];
  dataDeaffiliation:any;
  constructor(private institutionmanagement: InstitutionmanagementService,public sharedservice: SharedService,private dialog: MatDialog,public localService:LocalserviceService) { }

  ngOnInit(): void {
    this.findData();
    this.getDeaffiliationReason();
  }
  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
  }
  resetdata(){
    this.userDataTable=[];
    this.searchText=null;
    this.pageSize=10;
    this.page = 1;
    this.handlePageChange(this.page);
  }
  
  findData(){
    this.searchText=null;
    var formData;
    if(!this.instiFormData.value.status){
     this.sharedservice.showError('Please Select Status');
     return;
    }
    if(!this.instiFormData.value.surveyYearValue){
      this.sharedservice.showError('Please Select Survey Year');
      return;
     }
    if(!this.instiFormData.value.stateValue&& this.localService.getData('roleId')=='26'|| !this.instiFormData.value.stateValue && this.localService.getData('roleId')=='1'){
      this.sharedservice.showError('Please Select State');
      return;
     }
    formData={
      "standaloneType":this.instiFormData.value.standaloneType==' '?this.instiFormData.value.standaloneType.trim():this.instiFormData.value.standaloneType,
      "surveyYearValue":this.instiFormData.value.surveyYearValue.split('-')[0],
      "status":this.instiFormData.value.status,
      "selectedStateCode":this.instiFormData.value.stateValue && this.localService.getData('roleId')=='26'|| this.instiFormData.value.stateValue&&this.localService.getData('roleId')=='1'? this.instiFormData.value.stateValue.trim():this.localService.getData('stateCode'),
    };

    this.institutionmanagement.getStandaloneStatusData(formData).subscribe((res) =>{
      if(res.statusCode ==="AISH099"){
        this.sharedservice.showError(res.statusDesc)
        this.userDataTable=[];
        this.showTable=true;
        this.page = 1;
        this.handlePageChange(this.page);
      }
      if(res.statusCode==="AISH001"){
        this.userDataTable=res.standaloneCollegeListBean;
        this.showTable=true;
        this.page = 1;
        this.handlePageChange(this.page);
      }
    })
  }
  statusActive(data: any){

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width:'30%',
      data: {
        message: 'Are you sure you want to Active this Institute?',
        s1: 'standaloneStatus',
      },
    });
    
    dialogRef.afterClosed().subscribe(result => {
      if(result){
    let formData={
      id: data.aishecode,
      ipAddress: "string",
      name: data.name.trim(),
      remarks: result.remarks.trim(),
      reasonId: result.deaffiliation,
      surveyYear: result.surveyYearValue.split('-')[0],
      userId: this.localService.getData('userId')
    }


    this.institutionmanagement.postStandaloneStatusActiveData(formData).subscribe(result => {
    if(result.statusCode==='AISH001'){
     this.sharedservice.showSuccessMessage("Standalone has been Activated Successfully.");
     this.findData();
    }
    if(result.statusCode==='AISH099'){
      this.sharedservice.showError(result.statusDesc);
    }
    if(result.statusCode==='AISH025'){
      this.sharedservice.showError(result.statusDesc);
    }
    if(result.statusCode==='AISH012'){
      this.sharedservice.showError(result.statusDesc);
    }
    });
    }
    });
  }
  getDeaffiliationReason() {
    this.institutionmanagement.getReasonForInactive().subscribe((res) => {
      this.dataDeaffiliation = res.reasonId;
      
    });
  }
  statusInActive(data: any) {

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width:'30%',
      data: {
        Deaffiliation:this.dataDeaffiliation,
        message: 'Are you sure you want to Inactive this Institute?',
        s1: 'standaloneStatus',
      },
    });
    
    dialogRef.afterClosed().subscribe(result => {
      if(result){
    let formData={
      id: data.aishecode,
      ipAddress: "string",
      name: data.name,
      remarks: result.remarks,
      isInactiveUser:result.isInactiveUser,
      reasonId: result.deaffiliation,
      surveyYear: result.surveyYearValue.split('-')[0],
      userId: this.localService.getData('userId')
    }


    this.institutionmanagement.postStandaloneStatusData(formData).subscribe(result => {
    if(result.statusCode==='AISH001'){
      this.findData();
      this.sharedservice.showSuccessMessage("Standalone has been Inactivated Successfully.");
    
    }
    if(result.statusCode==='AISH099'){
      this.sharedservice.showError(result.statusDesc);
      this.findData();
    }
    if(result.statusCode==='AISH025'){
      this.sharedservice.showError(result.statusDesc);
      this.findData();
    }
    });
    }
    });
    
      }

  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
    //this.getData1();
  }

  handlePageChange(event: number) {
    this.page = event;
    let fgh = parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * fgh),
      (this.EndLimit = this.StartLimit + fgh);
    var a = Math.ceil(this.userDataTable.length / fgh);
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + fgh, this.userDataTable.length);
    } else {
      this.pageData = Math.min(this.StartLimit + fgh, this.userDataTable.length);
    }
  }

}
