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
  selector: 'app-view-rad',
  templateUrl: './view-rad.component.html',
  styleUrls: ['./view-rad.component.scss']
})
export class ViewRADComponent implements OnInit {
  rnDInstitutionsList: any=[];
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
  isEdit:any=false;
  parentData: string = "Hello from Parent!";
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
    }
    this.authService.getViewDataRND(payload).subscribe((res:any)=>{
      if(res.statusCode=='AISH001' && res.rnDInstitutions.length>0){
         this.rnDInstitutionsList=res.rnDInstitutions;
         this.tempList = [...this.rnDInstitutionsList];
         this.handlePageChange(this.sharedService.page=1)

      }else{
        this.rnDInstitutionsList=[];
        this.tempList=[];
        this.handlePageChange(this.sharedService.page=1)
      }
    })
   }
   openDialog(ele: any, i: number) {
    let dialogRef = this.dialog.open(ViewProgramComponent, {
      width: '50%',
      height: 'auto',
      data: { data: ele,viewRnD:true },
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result:any) => {
      if (result) {
      

      }
    });
  }
  onChange(event: any) {
    this.sharedService.pageSize = parseInt(event);
    this.handlePageChange(this.sharedService.page = 1);
  }
  handlePageChange(event: any) {
    this.sharedService.page = event;
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize);
    var a = Math.ceil(this.rnDInstitutionsList.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.rnDInstitutionsList.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.rnDInstitutionsList.length - 1);
    }
  }  
  async updateResults() {
    this.rnDInstitutionsList = []
    this.rnDInstitutionsList = this.searchByValue(this.tempList);
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
    this.tableHeaders = ['SNO','AISHE Code', 'Institute Name','Administrative Ministry','State Name', 'Distric Name'];
    const tableData = this.rnDInstitutionsList.map((row:any, i:any) => [
      // i+1,
      row.aisheCode, 
      row.name, 
      row.ministryOnosName,
      row.stateName,
      row.districtName,


    ]);
   this.tableHeaders.shift();
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      // excelName:'Merged Insitutions',
      downloadExcelName:'View R&D Insitutions',
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
  edit(data:any,index:any){
     this.isEdit=true;
     data.isEdit=true;
     this.parentData=data;
  }
  receiveData(boolValue:boolean){
   this.isEdit=boolValue;
   this.getViewData();
  }

}
