import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { ViewProgramComponent } from 'src/app/dialog/view-program/view-program.component';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';

@Component({
  selector: 'app-reject-rad',
  templateUrl: './reject-rad.component.html',
  styleUrls: ['./reject-rad.component.scss']
})
export class RejectRadComponent implements OnInit {
  rnDInstitutionsReject: any=[];
  rndForm!:FormGroup;
  searchText: string = '';
  tempList: any;
  variables: any;
  stateList: any;
  isFormInvalid: boolean = false;
  stateCode:any;
  ministry:any;
  ministryList: any;
  variables1:any;
  tableHeaders:any;

  constructor(public sharedService: SharedService, public router: Router, public authService: AuthService, private route: ActivatedRoute, public localStore1: EncryptDecrypt, public localService: LocalserviceService,public dialog: MatDialog, public errorMatcher: CustomErrorStateMatcher,private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getViewData();
    this.createForm();
    this.getState();
    this.getadministrativeMinistry();
  }
   getViewData(){
    let payload={
       'stateCode':this.stateCode?this.stateCode:'',
       'ministry':this.ministry?this.ministry:'',
      'status':'2'

    }
    this.authService.getRejectDataRND(payload).subscribe((res:any)=>{
      if(res.statusCode=='AISH001' && res.rnDInstitutions.length>0){
         this.rnDInstitutionsReject=res.rnDInstitutions;
         this.tempList = [...this.rnDInstitutionsReject];
         this.handlePageChange(this.sharedService.page=1)

      }else{
        this.rnDInstitutionsReject=[];
        this.tempList=[];
        this.handlePageChange(this.sharedService.page=1)
      }
    })
   }
  //  openDialog(ele: any, i: number) {
  //   let dialogRef = this.dialog.open(ViewProgramComponent, {
  //     width: '50%',
  //     height: 'auto',
  //     data: { data: ele,viewRnD:true },
  //     disableClose: true,
  //   });
  //   dialogRef.afterClosed().subscribe((result:any) => {
  //     if (result) {
      

  //     }
  //   });
  // }
  onChange(event: any) {
    this.sharedService.pageSize = parseInt(event);
    this.handlePageChange(this.sharedService.page = 1);
  }
  handlePageChange(event: any) {
    this.sharedService.page = event;
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize);
    var a = Math.ceil(this.rnDInstitutionsReject.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.rnDInstitutionsReject.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.rnDInstitutionsReject.length - 1);
    }
  }  
  async updateResults() {
    this.rnDInstitutionsReject = []
    this.rnDInstitutionsReject = this.searchByValue(this.tempList);
    this.handlePageChange(this.sharedService.page = 1)
  }
  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        // item.name.trim();
        return (item.aisheCode?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.name?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.ministryOnosName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.stateName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.districtName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }
  getState() {
    this.authService.getState().subscribe(
      (res) => {
        if (res && res.length) {
          this.variables = [];
          this.variables = res;
          this.stateList = this.variables.slice();
        }
      },
      (err) => { }
    );
  }
  createForm(){
    this.rndForm = this.fb.group({
      stateCode: [''],
      ministry: [''],
    })
  }
  getStateId(event:any){
   this.stateCode=event.value;
   this.getViewData();
  }
  getMinistryId(event:any){
    this.ministry=event.value;
    this.getViewData();
  }
  getadministrativeMinistry(){
    this.authService.administrativeMinistry().subscribe((res:any)=>{
      this.variables1 = [];
      this.variables1 = res;
      this.ministryList=this.variables1.slice();

    })
  }
  reset(){
     this.ministry='';
     this.stateCode='';
     this.rndForm.reset();
     this.createForm();
     this.getViewData();
  }
  downloadExcel() {
    this.tableHeaders = ['SNO','Request Id', 'Institution Name', 'Administrative Ministry','State Name','Remark', 'Date/Time'];
    const tableData = this.rnDInstitutionsReject.map((row:any, i:any) => [
      // i+1,
      row.requestId, 
      row.name, 
      row.ministryOnosName, 
      row.stateName,
      row.remarks,
      row.createdDate
    ]);
   this.tableHeaders.shift();
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      downloadExcelName:'Reject R&D Institute List',
      setHeaderCollumnWidths:[
        { wpx: 70 },//requestId
        { wpx: 170 }, //name
        { wpx: 290 },//ministryOnosName,
        { wpx: 110 },//stateName 
        { wpx: 200 },//remarks
        { wpx: 140 },// createdDate
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

