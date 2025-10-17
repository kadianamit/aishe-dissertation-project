import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ApproveRejectDatashaingDialogComponent } from 'src/app/dialog/approve-reject-datashaing-dialog/approve-reject-datashaing-dialog.component';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-datasharing',
  templateUrl: './datasharing.component.html',
  styleUrls: ['./datasharing.component.scss']
})
export class DatasharingComponent implements OnInit {
  selectedIndex: number = 0;
  searchText: string = ''
  listData: Array<any> = [];
  tempUserList: Array<any> = []
  constructor(public sharedService: SharedService, public auth: AuthService,private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getDataSharingReq();
  }
  tabSelected(event: any) {
    this.selectedIndex = event.index
      this.getDataSharingReq()
  }
  handlePageChange(event: any) {
    this.sharedService.page = event
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize)
    var a = Math.ceil(this.listData.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.listData.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.listData.length - 1);
    }

  }

  onChange(event: any) {
    this.sharedService.pageSize = parseInt(event);
    this.handlePageChange(this.sharedService.page = 1)
  }

  async updateResults() {
    this.listData = []
    this.listData = this.searchByValue(this.tempUserList);
    this.handlePageChange(this.sharedService.page = 1)
  }


  searchByValue(data: any) {
    return data.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.requestId?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.orgCategoryName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.stateName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.ministryName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.nameOfAgency?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.nameOfNodalPerson?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.submittedOn?.toString().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  payload:any={status:''}
  getDataSharingReq() {
    if(this.selectedIndex === 0){
      this.payload.status=2
    }else{
      this.payload.status=1
    }
    this.auth.getDataSharing(this.payload).subscribe(res => {
      this.listData = res.data;
      this.tempUserList = [...this.listData]
      this.handlePageChange(this.sharedService.page=1)
    }, err => {

    })
  }
  approveReject(e:any){
       this.getDocument(e);
  }
  getDocument(data:any){
    let payload={"id":data.id}
    this.auth.getDataSharing(payload).subscribe((res:any)=>{
      if(res.status==200){
        this.dialog.open(ApproveRejectDatashaingDialogComponent,
          {
            width: '50%',
            height: '90%',
            data: {data:data,doc:res.data[0]},
    
          }).afterClosed().subscribe((result) => {
           // console.log("Result",result)
         });
      }
    }, err => {
      this.dialog.open(ApproveRejectDatashaingDialogComponent,
        {
          width: '50%',
          height: '90%',
          data: {data:data,doc:null},
  
        }).afterClosed().subscribe((result) => {
         // console.log("Result",result)
       });
    })
  }
}
