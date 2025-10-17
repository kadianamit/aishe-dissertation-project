import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable, Subject, of, takeUntil } from 'rxjs';
import { EmailSmsDialogComponent } from 'src/app/dialog/email-sms-dialog/email-sms-dialog.component';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-email-sms-management',
  templateUrl: './email-sms-management.component.html',
  styleUrls: ['./email-sms-management.component.scss']
})
export class EmailSmsManagementComponent implements OnInit {
  unSubscribeSubject = new Subject()
  searchText: any = ''
  tempListData: Array<any> = [];
  listData: Array<any> = [];
  stateList: Array<any> = []
  stateListArray: Array<any> = []
  districtList: Array<any> = []
  districtListArray: Array<any> = [];
  instituteTypeList: Array<any> = []
  allSelected = false;
  stateIsRequired: boolean = false;
showStateError: boolean = false;
isStateRequired: boolean = false;
  obj: any = {
    stateCode: '',
    institutionType: '',
    subCategory: '',
    surveyYear: '',
    districtCode: '',
    userType: '',
    participatedStatus: ''
  }
  selectedItemList: any;
  rollId:any;
  disableForUniversity: boolean = false;
  disableForSNO: boolean = false;
  paginatedList: any;
  ccAllowed: any = false;
  tableHeaders:any;
  tableHeaders2:any;

  constructor(public sharedService: SharedService, public authService: AuthService, private dialog: MatDialog,public localService:LocalserviceService) {
    this.rollId = this.localService.getData('roleId');
  }

  ngOnInit(): void {
    this.getState();
    this.actionByRollBased();
  }
  actionByRollBased() {
    this.obj.stateCode = 'ALL';
    if (this.sharedService.role['State Nodal Officer'] == this.rollId) {
      this.obj.stateCode = this.localService.getData('stateCode');
      this.disableForSNO = true;
    }
    if (this.sharedService.role['University'] == this.rollId) {
      this.obj.institutionType = 'COLLEGE';
      this.getInstituteType('COLLEGE');
      this.obj.stateCode = this.localService.getData('stateCode');
      this.disableForUniversity = true;
      this.getDistrictData(this.obj.stateCode);
    }
  }

  onChange(event: any) {
    this.sharedService.pageSize = parseInt(event);
    this.handlePageChange(this.sharedService.page = 1)
  }

  async updateResults() {
    this.listData = []
    this.listData = this.searchByValue(this.tempListData);
    this.handlePageChange(this.sharedService.page = 1)
  }
  handlePageChange(event: any) {
    // this.allSelected=this.allSelected==true?!this.allSelected:false;
    this.sharedService.page = event
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize)
    var a = Math.ceil(this.listData.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.listData.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.listData.length - 1);
    }
    this.paginatedList = this.listData.slice(this.sharedService.StartLimit, this.sharedService.StartLimit + this.sharedService.pageSize);

  }


  searchByValue(listData: any) {
    return listData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.aisheCode?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.name?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.nodalOfficerName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.institutionHeadName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  search() {
    if (this.stateIsRequired && (!this.obj.stateCode || this.obj.stateCode === 'ALL')) {
      this.sharedService.showValidationMessage('Please select state !!!');
      return;
  }
    if (!this.obj.surveyYear) {
      this.sharedService.showValidationMessage('Please select Survey Year!!!');
      return;
    }
    if (!this.obj.institutionType) {
      this.sharedService.showValidationMessage('Please select Institution Type !!!');
      return;
    }
    if (!this.obj.stateCode) {
      this.sharedService.showValidationMessage('Please select state !!!');
      return;
    }
    this.authService.getEmailSMSData(this.obj).pipe(takeUntil(this.unSubscribeSubject))
      .subscribe({
        next: (res: any) => {
          this.listData = res;
          this.listData.forEach((item) => item.selected = false);
          this.tempListData = [...this.listData]
          this.handlePageChange(this.sharedService.page = 1)
        },
        error: (error: any) => {
          console.error('Error:', error);
        },
      });
  }
  getInstituteType(value: string) {
if(value =='ALL'){
  this.stateIsRequired = true;
  this.isStateRequired = true;
  this.instituteTypeList.unshift({ id: '', type: 'ALL' });
}
    else if (value === 'COLLEGE') {
      
      this.stateIsRequired = false;
      this.isStateRequired = false;
      this.showStateError = false;
      if((this.sharedService.role['State Nodal Officer'] !== this.rollId)){
        this.obj.stateCode = null;
      }
   
      this.authService.getCollegeType().subscribe(res => {
        this.instituteTypeList = res;
        this.instituteTypeList.unshift({ id: '', type: 'ALL' });
      }, err => {

      })
    }
    else if (value === 'UNIVERSITY') {
      this.stateIsRequired = false;
        this.isStateRequired = false;
        this.showStateError = false;
        if((this.sharedService.role['State Nodal Officer'] !== this.rollId)){
          this.obj.stateCode = null;
        }
      this.authService.institutionType('U').subscribe(res => {
        this.instituteTypeList = res;
        this.instituteTypeList.unshift({ id: '', type: 'ALL' });
      }, err => {

      })

    }
    else {
      this.stateIsRequired = false;
        this.isStateRequired = false;
        this.showStateError = false;
        if((this.sharedService.role['State Nodal Officer'] !== this.rollId)){
          this.obj.stateCode = null;
        }
      this.authService.getStandAloneBody().subscribe(
        (res) => {
          if (res && res.length) {
            this.instituteTypeList = res;
            this.instituteTypeList.unshift({ id: '', type: 'ALL' });
          }
        },
        (err) => { }
      );
    }

  }

  getState() {
    this.authService.getState().subscribe(
      (res) => {
        if (res && res.length) {
          this.stateListArray = [];
          this.stateListArray = res;
          this.stateListArray = this.stateListArray.sort((a, b) => a.name > b.name ? 1 : -1)
          this.stateList = this.stateListArray.slice();

        }
      },
      (err) => { }
    );
  }
  getDistrictData(value: any) {
    if (this.stateIsRequired) {
      // Show error if "ALL" or no state is selected
      this.showStateError = !this.obj.stateCode || this.obj.stateCode === 'ALL';
  } else {
      this.showStateError = false;
  }
    this.authService.getDistrict(value).subscribe(
      (res) => {
        if (res && res.length) {
          this.districtListArray = [];
          this.districtListArray = res;
          this.districtListArray = this.districtListArray.sort((a, b) => a.name > b.name ? 1 : -1);
          this.districtList = this.districtListArray.slice();
          this.districtList.unshift({ distCode: '', stateCode: '', name: 'ALL', sno: 0, lgdDistCode: 0 })
        }
      },
      (err) => { }
    );
  }

  toggleAllSelection() {
    this.allSelected = this.allSelected;
    this.listData.forEach(item => item.selected = this.allSelected);
    // this.paginatedList.forEach(item => item.selected = this.allSelected);
  }
  togglePermission() {
    this.ccAllowed = this.ccAllowed;
  }
  checkIfAllSelected() {
    this.allSelected = this.listData.every(item => item.selected);

  }
  sendEmail() {
    this.selectedItemList = [];
    this.selectedItemList = this.listData.filter((item) => item.selected == true);
    if (this.selectedItemList.length > 0) {
      this.openDialog(this.selectedItemList);
    } else {
      this.sharedService.showError("Please select atleast one email");
    }

  }
  openDialog(data: any) {
    const dialogRef = this.dialog.open(EmailSmsDialogComponent, {
      width: '80%',
      height: '100%',
      data: {
        array: this.selectedItemList,
        cc: this.ccAllowed,
      },
    });
    return;

  }
  reset() {
    this.obj.institutionType = '';
    this.obj.stateCode = '';
    this.obj.subCategory = '';
    this.obj.surveyYear = '';
    this.obj.districtCode = '';
    this.obj.userType = '';
    this.obj.participatedStatus = '';
    this.actionByRollBased();
    this.listData = [];
    this.allSelected = false;
    this.handlePageChange(this.sharedService.page = 1)
  }
  downloadExcel() {
    this.tableHeaders = ['SNO', 
                        'AISHE Code', 
                        'Institute Name',
                        'Nodal Officer(NO)',
                        'Email(NO)',
                        'Mobile(NO)',
                        'Head of the Institution(HOI)',
                        'Email(HOI)',
                        'Mobile(HOI)'];
    this.tableHeaders2 = [ 'SNO', 
                          'AISHE Code', 
                          'Institute Name',
                          'Nodal Officer(NO)',
                          'Email(NO)','Mobile(NO)'];
    this.selectedItemList=[];
    this.selectedItemList = this.listData.filter((item) => item.selected == true);
    if (this.selectedItemList.length == 0) {
      this.sharedService.showError("Please select atleast one email");
      return;
    }
    const tableData = this.selectedItemList.map((row:any, i:any) => [
      // i+1,
      row.aisheCode, 
      row.name, 

      row.nodalOfficerName,
      row.nodalOfficerEmail,

      row.nodalOfficerMobile,
      row.institutionHeadName,

      row.institutionHeadEmail,
      row.institutionHeadMobile

    ]);
    const tableData2 = this.selectedItemList.map((row:any, i:any) => [
      // i+1,
      row.aisheCode, 
      row.name, 

      row.nodalOfficerName,
      row.nodalOfficerEmail,

      row.nodalOfficerMobile,
    ]);
   this.ccAllowed?this.tableHeaders.shift():this.tableHeaders2.shift();
    let param={
      tableHeaders:this.ccAllowed?this.tableHeaders:this.tableHeaders2,
      tableData:this.ccAllowed?tableData:tableData2,
      // excelName:'Merged Insitutions',
      downloadExcelName:'Selected Emails Detail',
      setHeaderCollumnWidths:[
        { wpx: 80 },//AisheCode
        { wpx: 200 }, //institutino Name

        { wpx: 150 },//nodalOfficerName
        { wpx: 150 },//nodalOfficerEmail

        { wpx: 100 },//nodalOfficerMobile
        { wpx: 150 },// institutionHeadName

        { wpx: 150 },//institutionHeadEmail
        { wpx: 100 },//institutionHeadMobile


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

