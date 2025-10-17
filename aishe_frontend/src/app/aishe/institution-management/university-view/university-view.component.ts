import { Component, Input, OnInit } from '@angular/core';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import { data } from 'jquery';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { MatDialog } from '@angular/material/dialog';
import { UploadedUniversityViewDialogComponent } from 'src/app/dialog/uploaded-university-view-dialog/uploaded-university-view-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';

@Component({
  selector: 'app-university-view',
  templateUrl: './university-view.component.html',
  styleUrls: ['./university-view.component.scss'],
})
export class UniversityViewComponent implements OnInit {
  @Input()
  instiFormData: any;
  universityData: any;
  obj: any;
  showSimilarCollege: boolean = false;
  isView: boolean = false;
  dataCollege: any;
  showTable: boolean = false;
  userDataTable: any[] = [];
  showDataTable: boolean = false;
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
  lastUpdatedDcfDetails: any;
  aisheCode: any;
  nodalOfficerName: any;
  panelOpenState = false;
  universityId: any;
  universityCode: any;
  constructor(private dialog: MatDialog,
    public sharedservice: SharedService, private localService: LocalserviceService,
    private institutionmanagement: InstitutionmanagementService, public router: Router, public authService: AuthService, private route: ActivatedRoute, public localStore1: EncryptDecrypt
  ) {
    this.roleId = this.localService.getData('roleId');
   
    // let universityCode = this.route.snapshot.paramMap.get('id');
    //   this.universityCode=this.localStore1.getDecryptedValue(universityCode)
    //   console.log(this.universityCode); 
    //   if(this.universityCode){

    //   }
  }

  ngOnInit(): void {
    let universityCode = this.route.snapshot.paramMap.get('id');
   if(universityCode){
    this.universityCode=this.localStore1?.getDecryptedValue(universityCode)
    console.log(this.universityCode); 
    if (this.instiFormData) {
      this.openDetails(this.instiFormData.value.searchText)
    }
   }
  
   
    //console.log('University', this.instiFormData.value);
    this.findUniversityData();

  }
  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }


  resetdata(): void {
    this.userDataTable = [];
    this.page = 1;
    this.handlePageChange(this.page);
    this.searchText = null;
    this.pageSize = 10;
  }
  findUniversityData() {
    this.searchText = null;
    if (!this.instiFormData.value.surveyYearValue && this.instiFormData.value.searchText == null) {
      this.sharedservice.showError('Please Select Survey Year');
      return;
    }
    // if (this.instiFormData.value.stateValue==null) {
    //   this.sharedservice.showError('Please Select State');
    //   return;
    // }

    let data = {
      stateValue: this.instiFormData.value.stateValue ? this.instiFormData.value?.stateValue?.trim() : '',
      surveyYearValue: this.instiFormData.value.surveyYearValue.split('-')[0],
      universityType: this.instiFormData.value.universityType ? this.instiFormData.value?.universityType.trim() : '',
      searchText: this.instiFormData.value.searchText ? this.instiFormData.value.searchText.trim() : null,
    };
    console.log(data)
    console.log(this.instiFormData.value)
    this.institutionmanagement.getViewUniversity(data).subscribe((res) => {
      this.userDataTable = res?.universityListBean;
      this.showTable = true;
      this.page = 1;
      this.handlePageChange(this.page);

      if (res.statusCode == 'AISH099') {
        this.sharedservice.showError(res.statusDesc);
      }
      //console.log(this.userDataTable);
    }, (error) => {
      console.log(error);
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

  back1(): void {

    if (this.universityCode) {
      this.router.navigate(['/aishe/universalsearch']);
    } else {
      this.showDataTable = false;
      this.showTable = true;
      this.isView = false;
    }
  }

  openDetails(data: any) {
    let idR = data.aishecode ? data.aishecode : data;

    this.institutionmanagement
      .getUniversityDetails(
        idR,
        this.instiFormData.value.surveyYearValue ? this.instiFormData.value.surveyYearValue.split('-')[0] : 0
      )
      .subscribe((res) => {
        this.obj = res.collegeApprovalRequestDetailsBean;

        if (res.statusCode === 'AISH099') {
          this.showDataTable = false;
          this.showTable = true;

          this.sharedservice.showError(
            'University Details are not available for this survey year.'
          );
        }
        if (res.statusCode === 'AISH001') {
          if (this.obj) {
            this.dataCollege = Object.values(this.obj);
          }
          this.showDataTable = true;
          this.showTable = false;
          this.isView = true;
          if (this.sharedservice.role.SysAdmin == this.roleId) {
            this.getLastUploadedDCFDetail(this.obj.id);
          }

        }
      });
  }

  getLastUploadedDCFDetail(aisheCode: any) {
    this.aisheCode = 'U-' + aisheCode.toUpperCase();
    let payload: any = { 'aisheCode': 'U-' + aisheCode.toUpperCase() }
    this.authService.getDetailOfLastUpdateDCF(payload).subscribe((res: any) => {
      this.lastUpdatedDcfDetails = res;
    })
  }
  getDownloadData(aisheCode: any, loginMode: any, docType: any, updatedRecord: any) {
    if (docType === 'CERTIFICATE') {

      let payload = {
        instituteType: 'U',
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
