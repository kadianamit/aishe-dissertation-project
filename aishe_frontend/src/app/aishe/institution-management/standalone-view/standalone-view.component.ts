import { Component, Input, OnInit } from '@angular/core';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import { Sort } from '@angular/material/sort';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { AuthService } from 'src/app/service/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { UploadedUniversityViewDialogComponent } from '../../../dialog/uploaded-university-view-dialog/uploaded-university-view-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';

@Component({
  selector: 'app-standalone-view',
  templateUrl: './standalone-view.component.html',
  styleUrls: ['./standalone-view.component.scss']
})
export class StandaloneViewComponent implements OnInit {

  @Input()
  instiFormData: any;
  @Input()
  selectedIndexEdit: any

  showTable: boolean = false;
  userDataTable: any[] = [];
  showDataTable: boolean = false;
  obj: any
  isView: boolean = false;
  dataCollege: any
  tabledata: boolean = false;
  userData1: any = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  roleId: any;
  showStandalone: boolean = false;
  tableSize: number[] = [10, 20, 30, 40, 50];
  sortedData: any;
  isFormInvalid: any;
  nodalOfficerName: any;
  aisheCode: any;
  lastUpdatedDcfDetails: any;
  panelOpenState = false;
  hasOtherMinorityData: any;
  standaloneCode: any;
  viewDetailOnly:any=true;

  constructor(private dialog: MatDialog, private institutionmanagement: InstitutionmanagementService,public router: Router,private route: ActivatedRoute,public localStore1:EncryptDecrypt, public sharedservice: SharedService, public localService: LocalserviceService, public authService: AuthService) {
    this.roleId = this.localService.getData('roleId');
  }

  ngOnInit(): void {
    let standaloneCode = this.route.snapshot.paramMap.get('id');
    if(standaloneCode){
     this.standaloneCode=this.localStore1?.getDecryptedValue(standaloneCode)
    //  console.log(this.standaloneCode); 
     
      if (this.instiFormData) {
        this.openDetails(this.instiFormData.value.searchText)
      }
   
    }
if(!this.standaloneCode){
  this.ViewData();
}
   
  }

  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  ViewData() {
    // console.log(this.instiFormData)
    this.searchText = null;
    var formData;
    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
      if(this.localService.getData('viewDetailOfRequestId')){
        this.instiFormData.controls['surveyYearValue'].setValue('');
        this.instiFormData.controls['stateValue'].setValue('');
        this.instiFormData.controls['standaloneType'].setValue('')
      }
    }
    if (!this.instiFormData.value.surveyYearValue && this.instiFormData.value.searchText == null) {
      this.sharedservice.showError('Please Select Survey Year');
      return;
    }
    if ((!this.instiFormData.value.stateValue && this.localService.getData('roleId') == '26' || !this.instiFormData.value.stateValue && this.localService.getData('roleId') == '1') && this.instiFormData.value.searchText == null) {
      this.sharedservice.showError('Please Select State');
      return;
    }

    formData = {
      "standaloneType": this.instiFormData.value.standaloneType,
      "surveyYearValue": this.instiFormData.value.surveyYearValue.split('-')[0],
      "standaloneValue": this.instiFormData.value.standaloneValue,
      "selectedStateCode": this.instiFormData.value.stateValue && this.localService.getData('roleId') == '26' || this.instiFormData.value.stateValue && this.localService.getData('roleId') == '1' ? this.instiFormData.value.stateValue : this.localService.getData('stateCode'),
      "districtValue": '',
      "searchText": this.instiFormData.value.searchText ? this.instiFormData.value.searchText.trim() : '',
    };

    let roleId = this.localService.getData('roleId');

    if (formData.selectedStateCode === 'ALL') {
      formData.selectedStateCode = ''
    }
    if (formData.standaloneType === 'ALL') {
      formData.standaloneType = ''
    }
    if (roleId === '22') {
      formData.selectedStateCode = '';
    }
    // console.log(formData)
    this.institutionmanagement.getStandaloneViewData(formData).subscribe((res) => {
      // this.showTable = true;
      // this.userDataTable =res.standaloneCollegeListBean;
      // this.page = 1;
      // this.handlePageChange(this.page);
      if (res.statusCode === "AISH001") {
        this.userDataTable = res.standaloneCollegeListBean
        this.sortedData = res.standaloneCollegeListBean.slice();
        if(this.localService.getData('viewDetailOfRequestId')){
          this.openDetails(this.userDataTable[0]);
          this.viewDetailOnly=false;
        }
        this.page = 1;
        this.handlePageChange(this.page);

      }
      this.showTable = true;


      if (res.statusCode === 'AISH099' || res.statusCode === 'AISH024' || res.statusCode === 'AISH025') {
        this.userDataTable = [];
        this.page = 1;
        this.handlePageChange(this.page);
        this.sharedservice.showError(res.statusDesc);
      }
    })

  }



  back1() {
    if(this.standaloneCode){
      this.router.navigate(['/aishe/universalsearch']);
    }else{
    this.showDataTable = false;
    this.showTable = true
    this.isView = false;
  }
}
backToSummaryReport(){
  // console.log(JSON.parse(this.localService.getData('searchStateData'))); 
  let backURL=JSON.parse(this.localService.getData('searchStateData'));
  backURL.status=true;
  this.localService.saveData('searchStateData',JSON.stringify(backURL))
  this.router.navigate([backURL.backurl])
 }

  resetdata() {
    this.searchText = [];
    this.pageSize = 10;
    this.userDataTable = [];
    this.searchText = null;
    this.page = 1;
    this.handlePageChange(this.page);
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

  openDetails(data: any): void {
    let idR = data.aishecode?data.aishecode:data
    // console.log("aoe Cat upendra",this.instiFormData.value.surveyYearValue.split('-')[0]);
    this.institutionmanagement.getStandaloneFullDetails(idR, this.instiFormData.value.surveyYearValue ? this.instiFormData.value.surveyYearValue.split('-')[0] : 0).subscribe((res) => {
      this.obj = res.collegeApprovalRequestDetailsBean



      if (res.statusCode === 'AISH099') {

        this.showDataTable = false;
        this.showTable = true

        this.sharedservice.showError("Standalone Details are not available for this survey year.");
      }
      if (res.statusCode === 'AISH001') {
        if (this.obj) {
          this.dataCollege = Object.values(this.obj);
        }
        this.showDataTable = true
        this.showTable = false;
        this.isView = true;
        if (this.sharedservice.role.SysAdmin == this.roleId) {
          this.getLastUploadedDCFDetail(this.obj.id);
        }
      }

    });
  }
  editUser(data: any) {

  }
  sortData(sort: Sort) {
    const data = this.userDataTable.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'statename':
          return this.compare(a.statename, b.statename, isAsc);
        case 'districtname':
          return this.compare(a.districtname, b.districtname, isAsc);
        default:
          return 0;
      }
    });
  }


  compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
  downloadPdf(data: any, fileName: string) {
    let uint8_data = _base64ToArrayBuffer(data);
    var ba = new Uint8Array(uint8_data);
    var blob = new Blob([ba], { type: 'application/pdf' });
    if (window.navigator && (window.navigator as any).msSaveOrOpenBlob) {
      (window.navigator as any).msSaveOrOpenBlob(blob);
    } else {
      var a = document.createElement("a");
      document.body.appendChild(a);
      var fileURL = URL.createObjectURL(blob);
      a.href = fileURL;
      a.download = fileName;
      //filename
      a.click();
    }
    function _base64ToArrayBuffer(base64: string) {
      var binary_string = window.atob(base64);
      var len = binary_string.length;
      var bytes = new Uint8Array(len);
      for (var i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
      }
      return bytes.buffer;
    }
  }
  getLastUploadedDCFDetail(aisheCode: any) {
    this.aisheCode = 'S-' + aisheCode.toUpperCase();
    let payload: any = { 'aisheCode': 'S-' + aisheCode.toUpperCase() }
    this.authService.getDetailOfLastUpdateDCF(payload).subscribe((res: any) => {
      this.lastUpdatedDcfDetails = res;
    })
  }
  getDownloadData(aisheCode: any, loginMode: any, docType: any, updatedRecord: any) {
    if (docType === 'CERTIFICATE') {

      let payload = {
        instituteType: 'S',
        aisheCode: aisheCode,
        surveyYear: updatedRecord.surveyYear,
      }
      this.authService.getOfficerList(payload).subscribe(res => {
        if (res.data && res.data.length) {
          res.data.forEach((ele: any) => {
            if (ele.universityPk.officerType === 'NO') {
              this.nodalOfficerName = ele.officerName;
              this.getCertificate(aisheCode, updatedRecord)
            }
          })
        }
      }, err => {

      })

    } else if (docType === 'DATA CAPTURE FORMAT(DCF)') {

      let payload = {
        aisheCode: aisheCode,
        surveyYear: updatedRecord.surveyYear,
      }
      this.authService.getDownloadFile1(payload, loginMode)
    } else if (docType === 'TEACHING INFORMATION FORMAT(TIF)') {

      let payload = {
        aisheCode: aisheCode,
        surveyYear: updatedRecord.surveyYear,
      }
      this.authService.getDownloadFile2(payload, loginMode)
    } else {
      let payload = {
        aisheCode: aisheCode,
        surveyYear: updatedRecord.surveyYear,
      }
      if (payload.surveyYear < '2020') {
        this.authService.getDownloadFile3(payload, loginMode)
      } else {
        this.sharedservice.showError('Survey Year must be less than 2020!')
      }

    }
  }
  getCertificate(aisheCode: any, updatedRecord: any) {
    let payload = {
      aisheCode: aisheCode,
      surveyYear: updatedRecord.surveyYear,
      institutionName: "",
      nodalOfficerName: this.nodalOfficerName
    }
    this.authService.getCertificateDownload(payload);
  }

  updateRecord(item: any, aisheCode: any, type: any) {
    let payload = {
      instituteType: type,
      aisheCode: aisheCode,
      currentSurveyYear: item.surveyYear
    }
    this.authService.getOtherMinority(payload).subscribe(res => {
      const hasOtherMinorityData = res.hasOtherMinorityData
      this.dialog.open(UploadedUniversityViewDialogComponent,
        {
          width: '50%',
          height: '50%',
          data: { item, aisheCode, type, hasOtherMinorityData},
  
        });
    }, err => {

    })
    
  }

}
