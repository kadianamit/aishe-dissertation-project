import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { filter } from 'rxjs';
import { ResearchAndDevelopmentRemarksDialogComponent } from 'src/app/dialog/research-and-development-remarks-dialog/research-and-development-remarks-dialog.component';
import { AuthService } from 'src/app/service/auth.service';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-approve-rad',
  templateUrl: './approve-rad.component.html',
  styleUrls: ['./approve-rad.component.scss']
})
export class ApproveRadComponent implements OnInit {
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  stateName:any = "";
  ministryName:any = "";
  approveArrData:any =[]
  stateList: Array<any> = [];
  stateListArray: Array<any> = [];
  ministryArray: Array<any> = []
  ministryList: Array<any> = [];
  searchText: any;
  listData:any =[]
  requestId:any;
  tableHeaders:any;
  constructor(public sharedService : SharedService, private authService: AuthService, private dialog : MatDialog) { }

  ngOnInit(): void {
    this.getApproveRND()
    this.getStateList()
    this.getMinistryList()
  }



  onChange(event: any) {
    this.sharedService.pageSize = parseInt(event);
    this.handlePageChange(this.sharedService.page = 1);
  }
  handlePageChange(event: any) {
    this.sharedService.page = event;
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize);
    var a = Math.ceil(this.approveArrData.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.approveArrData.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.approveArrData.length - 1);
    }
  }

  
  async updateResults() {
    this.approveArrData = []
    this.approveArrData = this.searchByValue(this.listData);
    this.handlePageChange(this.sharedService.page = 1)
  }

  reset(){
    this.stateName = ''
    this.ministryName = ''
    this.getApproveRND()
  }

  searchByValue(userData: any) {
    return userData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.requestId?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.name?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.stateName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.districtName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.ministryOnosName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          // || (item.stateName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }


  getStateId(event:any){
    this.stateName=event.value;
    this.getApproveRND();
   }
   getMinistryId(event:any){
     this.ministryName=event.value;
     this.getApproveRND();
   }

  getApproveRND(){
    let payload = {
      stateCode : this.stateName,
      ministryId : this.ministryName,
      requestId : ''
    }
    this.authService.getApproveRND(payload).subscribe(res =>{
      this.approveArrData = res.rnDInstitutions
      this.listData = res.rnDInstitutions
      this.handlePageChange(this.sharedService.page = 1)
    },err=>{

    })
  }


  getStateList() {
    this.authService.getStatePrivate().subscribe(res => {
      if (res && res.length) {
        this.stateListArray = res;
        this.stateList = this.stateListArray.slice()
      }
    }, err => {

    })
  }


  getMinistryList() {
    this.authService.administrativeMinistry().subscribe(res => {
      this.ministryArray = res;
      this.ministryList = this.ministryArray.slice()
    }, err => {

    })
  }


  openApproveDialog(data:any) {
    this.dialog.open(ResearchAndDevelopmentRemarksDialogComponent, {
      width: '60%',
      height: '90%',
      data:  data,
      disableClose: true,
    }).afterClosed().subscribe((result) => {
      this.requestId = result?.requestId
      let filterArr = this.approveArrData.filter((item:any) => item.requestId !== this.requestId);
      this.approveArrData = [...filterArr]
      this.handlePageChange(this.sharedService.page=1)
    });
  }


  downloadExcel() {
    this.tableHeaders = ['SNO','RequestId', 'Institute Name','Administrative Ministry','State Name', 'Distric Name','Request Submitted Date'];
    const tableData = this.approveArrData.map((row:any, i:any) => [
      // i+1,
      row.requestId, 
      row.name, 
      row.ministryOnosName,
      row.stateName,
      row.districtName,
      row.createdDate


    ]);
   this.tableHeaders.shift();
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      // excelName:'Merged Insitutions',
      downloadExcelName:'Approve-Reject-R&D Institute',
      setHeaderCollumnWidths:[
        { wpx: 80 }, //aisheCode
        { wpx: 290 },//name,
        { wpx: 290 },//ministryOnosName
        { wpx: 140 },//stateName
        { wpx: 100 },// districtName

         ],
       headerStyle:{
        font: {
          bold: true,
          color: { rgb: 'FFFFFF' }, // White text color
        },
        fill: {
          fgColor: { rgb: '4CAF50' }, // Green background color
        },
        alignment: {
          horizontal: 'center',
          vertical: 'center'
        }
      }  
    }
   this.sharedService.downloadExcel(param);
  }
}
