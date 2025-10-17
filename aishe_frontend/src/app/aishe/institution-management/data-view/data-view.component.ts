import { Component, Input, OnInit,OnChanges,SimpleChanges } from '@angular/core';
import { Location } from '@angular/common';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import { MatDialog } from '@angular/material/dialog';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { AuthService } from 'src/app/service/auth.service';
import { UploadedUniversityViewDialogComponent } from 'src/app/dialog/uploaded-university-view-dialog/uploaded-university-view-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';

@Component({
  selector: 'app-data-view',
  templateUrl: './data-view.component.html',
  styleUrls: ['./data-view.component.scss'],
})
export class DataViewComponent implements OnInit,OnChanges {
  @Input()
  instiFormData: any;
  showTable: boolean = false;
  tabledata: boolean = false;
  userData1: any = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  roleId: any;
  obj: any;
  dataCollege: any;
  count: number = 0;
  alldata: any = [];
  dataSimilarCollege: any[] = [];
  showSimilarCollege: boolean = false;
  showDataTable: boolean = false;
  userData: any = [];
  isView: boolean = false;
  userDataTable: any = [];
  showStandalone: boolean = false;
  tableSize: number[] = [10, 20, 30, 40, 50];
  submitted = false;
  isFormInvalid: any;
  stateCode: any;
  aisheCode: any;
  lastUpdatedDcfDetails: any;
  nodalOfficerName: any;
  panelOpenState = false;
  collegeCode: any;
  viewDetailOnly=true;
  constructor(
    private dialog: MatDialog,public authService: AuthService,
    private institutionmanagement: InstitutionmanagementService,
    public sharedservice: SharedService,public localService:LocalserviceService, public router: Router,private route: ActivatedRoute, public localStore1: EncryptDecrypt
  ) {
    this.roleId = this.localService.getData('roleId');
    this.stateCode=this.localService.getData('stateCode');
  }
  ngOnChanges(changes: SimpleChanges): void {
    // if (changes['instiFormData']) {
    //   console.log('Previous Value:', changes['instiFormData'].previousValue);
    //   console.log('Current Value:', changes['instiFormData'].currentValue);
    // }
  }

  ngOnInit(): void {
    let collegeCode = this.route.snapshot.paramMap.get('id');
    if(collegeCode){
     this.collegeCode=this.localStore1?.getDecryptedValue(collegeCode)
    //  console.log(this.collegeCode);
     if (this.instiFormData) {
       this.openDetails(this.instiFormData.value.searchText)
     }
    }
    if(!this.collegeCode){
      this.findData();
    }

  }
  back1(): void {
    if (this.collegeCode) {
      this.router.navigate(['/aishe/universalsearch']);
    } else {
    this.showDataTable = false;
    this.showTable = true;
    this.isView = false;
  }
}
backToSummaryReport(){
  // console.log(JSON.parse(this.localService.getData('searchStateData'))); 
  let viewDetailOfRequestId=JSON.parse(this.localService.getData('viewDetailOfRequestId'));
  viewDetailOfRequestId.status=null;
  this.localService.saveData('viewDetailOfRequestId',JSON.stringify(viewDetailOfRequestId));
  let backURL=JSON.parse(this.localService.getData('searchStateData'));
  backURL.status=true;
  this.localService.saveData('searchStateData',JSON.stringify(backURL))
  this.router.navigate([backURL.backurl])
 }
  applyFilter(filterValue: string) {

    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  findData1(){
    this.userDataTable = [];
    this.searchText=null;
    this.page = 1;
    this.pageSize=10;
    this.handlePageChange(this.page);
  }
  findData() {
  
    // console.log(this.instiFormData)
    this.userDataTable = [];
    this.searchText = null;
    if (this.instiFormData.invalid) {
      this.sharedservice.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
      if(this.localService.getData('viewDetailOfRequestId')){
        this.instiFormData.controls['surveyYearValue'].setValue('');
        this.instiFormData.controls['stateValue'].setValue('');
        this.instiFormData.controls['universityValue'].setValue('');
      }
    }

    if (!this.instiFormData.value.surveyYearValue && this.instiFormData.value.searchText==null) {
      this.sharedservice.showError('Please Select Survey Year');
      return;
    }
    if ((!this.instiFormData.value.stateValue && this.localService.getData('roleId') === '26'|| !this.instiFormData.value.stateValue&& this.localService.getData('roleId')=== "1") && this.instiFormData.value.searchText==null) {
      this.sharedservice.showError('Please Select State');
      return;
    }
    if (((!this.instiFormData.value.universityValue && this.localService.getData('roleId')=== '6' ||!this.instiFormData.value.universityValue && this.localService.getData('roleId')=== '26' || !this.instiFormData.value.universityValue&& this.localService.getData('roleId')=== "1"))&& this.instiFormData.value.searchText==null
    ) {
      this.sharedservice.showError('Please Select University Name');
      return;
    }
    var formData;
    //MoE
    const checkRole=['26','1'];
    const role=this.localService.getData('roleId');
    if (this.localService.getData('roleId')==='26'|| this.localService.getData('roleId')==='1') {
      formData = {
        collegeType: this.instiFormData.value.collegeType?this.instiFormData.value.collegeType:'',
        surveyYearValue: this.instiFormData.value.surveyYearValue?this.instiFormData.value.surveyYearValue.split('-')[0]:'',
        universityValue: this.instiFormData.value.universityValue?this.instiFormData.value.universityValue.trim():'',
        selectedStateCode: this.instiFormData.value.stateValue?this.instiFormData.value.stateValue.trim():'',
        locationType: this.instiFormData.value.locationType?this.instiFormData.value.locationType:'',
        collegeLocation:this.instiFormData.value.collegeLocation?this.instiFormData.value.collegeLocation:'',
        searchText:this.instiFormData.value.searchText?this.instiFormData.value.searchText.trim():null,
      };
    }
    //SNO
    if (this.localService.getData('roleId') === '6') {
      formData = {
        collegeType: this.instiFormData.value.collegeType?this.instiFormData.value.collegeType:'',
        surveyYearValue: this.instiFormData.value.surveyYearValue.split('-')[0],
        universityValue: this.instiFormData.value.universityValue,
        selectedStateCode: this.instiFormData.value.collegeLocation==null?this.localService.getData('stateCode'):this.localService.getData('stateCode'),
        locationType: this.instiFormData.value.locationType?this.instiFormData.value.locationType:'',
        collegeLocation:this.instiFormData.value.collegeLocation?this.instiFormData.value.collegeLocation:'',
        searchText:this.instiFormData.value.searchText?this.instiFormData.value.searchText.trim():null,
      };
    }
    //UNO
    if (this.localService.getData('roleId') === '7') {
      let aisheCode: any = this.localService.getData('aisheCode');
      let aisheCode1 = aisheCode.replace(/\D/g, '');
      formData = {
        collegeType: this.instiFormData.value.collegeType?this.instiFormData.value.collegeType:'',
        surveyYearValue: this.instiFormData.value.surveyYearValue.split('-')[0],
        universityValue: aisheCode1,
        selectedStateCode: this.instiFormData.value.collegeLocation?this.localService.getData('stateCode'):'',
        locationType: this.instiFormData.value.locationType?this.instiFormData.value.locationType:'',
        collegeLocation:this.instiFormData.value.collegeLocation?this.instiFormData.value.collegeLocation:'',
        searchText:this.instiFormData.value.searchText?this.instiFormData.value.searchText.trim():null,
      };
    }
    if(formData){
      if(formData.selectedStateCode === 'ALL'){
        formData.selectedStateCode=''
      }
      if(formData.universityValue === 'ALL'){
        formData.universityValue=''
      }
    this.institutionmanagement
      .getinstitutionCollegeData1(formData)
      .subscribe((res) => {
        if (res.statusCode === 'AISH099') {
          this.showTable = true;
          this.userDataTable = [];
          this.sharedservice.showError(res.statusDesc);
        }
        if (res.statusCode === 'AISH001') {
          this.showTable = true;
          this.userDataTable = res.universityCollegeListBean;
          if(this.localService.getData('viewDetailOfRequestId')){
            this.instiFormData.get('surveyYearValue').setValue('');
            this.openDetails(this.userDataTable[0]);
            this.viewDetailOnly=false;
          }
     
        }
        this.page = 1;
          this.handlePageChange(this.page);
      });

    }
  }

  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
  }

  handlePageChange(event: number) {
    this.page = event;
    let fgh = parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * fgh),
      (this.EndLimit = this.StartLimit + fgh);
    var a = Math.ceil(this.userDataTable.length / fgh);
    if (a === event) {
      this.pageData = Math.min(
        this.StartLimit + fgh,
        this.userDataTable.length
      );
    } else {
      this.pageData = Math.min(
        this.StartLimit + fgh,
        this.userDataTable.length
      );
    }
  }

  openDetails(data: any) {
    let idR = data.id?data.id:data;
    this.institutionmanagement
      .getCollegeDetails(
        idR,
        this.instiFormData.value.surveyYearValue?this.instiFormData.value.surveyYearValue.split('-')[0]:0
      )
      .subscribe((res) => {
        this.obj = res.collegeApprovalRequestDetailsBean;
        this.obj['universityName']=data.universityName


        if (res.statusCode === 'AISH099') {
          this.showDataTable = false;
          this.showTable = true;

          this.sharedservice.showError(
            'College Details are not available for this survey year.'
          );
        }
        if (res.statusCode === 'AISH001') {
          if (this.obj) {
            this.dataCollege = Object.values(this.obj);
          }
          this.showDataTable = true;
          this.showTable = false;
          this.isView = true;
          if(this.sharedservice.role.SysAdmin==this.roleId){
            this.getLastUploadedDCFDetail(this.obj.id);
          }
        }
      });
  }
  getLastUploadedDCFDetail(aisheCode:any){
    this.aisheCode='C-'+aisheCode.toUpperCase();
    let payload:any={'aisheCode':'C-'+aisheCode.toUpperCase()}
    this.authService.getDetailOfLastUpdateDCF(payload).subscribe((res:any)=>{
      this.lastUpdatedDcfDetails=res;
    })
  }
  getDownloadData(aisheCode: any, loginMode: any,docType:any,updatedRecord:any) {
    if (docType === 'CERTIFICATE') {

      let payload = {
        instituteType: 'C',
        aisheCode: aisheCode,
        surveyYear: updatedRecord.surveyYear,
      }
      this.authService.getOfficerList(payload).subscribe(res => {
        if (res.data && res.data.length) {
          res.data.forEach((ele: any) => {
            if (ele.universityPk.officerType === 'NO') {
              this.nodalOfficerName = ele.officerName;
              this.getCertificate(aisheCode,updatedRecord)
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
    } else if(docType === 'TEACHING INFORMATION FORMAT(TIF)') {

      let payload = {
        aisheCode: aisheCode,
        surveyYear: updatedRecord.surveyYear,
      }
      this.authService.getDownloadFile2(payload, loginMode)
    }else{
      let payload = {
        aisheCode: aisheCode,
        surveyYear: updatedRecord.surveyYear,
      }
      if(payload.surveyYear<'2020'){
        this.authService.getDownloadFile3(payload, loginMode)
      }else{
        this.sharedservice.showError('Survey Year must be less than 2020!')
      }

    }
  }
  getCertificate(aisheCode: any,updatedRecord:any) {
    let payload = {
      aisheCode: aisheCode,
      surveyYear: updatedRecord.surveyYear,
      institutionName: "",
      nodalOfficerName: this.nodalOfficerName
    }
    this.authService.getCertificateDownload(payload);
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
