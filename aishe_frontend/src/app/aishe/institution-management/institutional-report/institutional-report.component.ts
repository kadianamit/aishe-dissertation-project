import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { forkJoin } from 'rxjs';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-institutional-report',
  templateUrl: './institutional-report.component.html',
  styleUrls: ['./institutional-report.component.scss']
})
export class InsitutionalReportComponent implements OnInit {
  data: any;
  obj: any;
  surveyYear: any;
  page: number = 1;
  standAloneReport: boolean = false;
  collegeReport: boolean = false;
  universityReport: boolean = false;
  arr: any;
  allData: any;
  tableData: any;
  type: any;
  Details: any;
  details: Array<any> = [{ heading: 'Institution Details', subHeading: 'Institution Details', tableHeading: 'Institute Name', instituteType: 'Institute Type' }, { heading: 'College Information', subHeading: 'College Details', tableHeading: 'University', instituteType: 'College Name' }]
  tableData1: any;
  dataSimilarCollege: any[] = [];
  userId: any;
  roleId: any;
  @Input()
  instiFormData: any;

  array: any

  reportsDataUniversity = ['Add', 'Edit', 'Delete', 'Convert to College']

  reportsDataStandlone = ['Edit', 'Inactive', 'Active', 'Upgrade To College', 'Upgrade To University', 'Approved/Rejected List']

  reportsDataCollege = ['Edit', 'Delete', 'Deaffilation College', 'Affiliation College', 'Upgrade College', 'Merge College', 'Approved/Rejected List']
  reportsDataCollege2 = ['Edit', 'Deaffilation College', 'Affiliation College', 'Upgrade College', 'Merge College', 'Approved/Rejected List']
  alldata: any;
  surveryYear: any;
  showDataTable: boolean = false;
  isApproveList: boolean = false;
  showSimilarCollege: boolean = false;
  approve: any;
  dataCollege: any;


  constructor(public sharedservice: SharedService,
    private institutionmanagement: InstitutionmanagementService, private dialog: MatDialog, public localService: LocalserviceService) { }

  ngOnInit(): void {
    this.userId = this.localService.getData('userId');
    this.roleId = this.localService.getData('roleId');
    this.findData(null);
  }


  findData(type: any) {
    if (type === 'UniversityReport' || type === 'StandaloneReport' || type === 'CollegeReport') {
      this.showDataTable = false;
      if (!this.instiFormData.value.surveyYearValue) {
        this.sharedservice.showError('Please Select Survey Year');
        return;
      } else if (type === 'UniversityReport') {

          let toDate:any=null;let fromDate:any=null;
          if(this.instiFormData.value.toDate!=null && this.instiFormData.value.toDate!=''){
          toDate=this.formatDate(this.instiFormData.value.toDate)
          }
          if(this.instiFormData.value.fromDate!=null && this.instiFormData.value.fromDate!=''){
            fromDate=this.formatDate(this.instiFormData.value.fromDate)
          }
        this.institutionmanagement.getUniversityReportWithDate(this.instiFormData.value.surveyYearValue.substr(0, 4), this.userId,toDate,fromDate).subscribe((res: any) => {
          this.data = res.counterMap;
          this.approve = res;
          this.universityReport = true;
          this.standAloneReport = false;
          this.collegeReport = false;
          this.data = Object.entries(this.data).map((item: any) => ([item[1]]))
          for (let x = 0; x <= 3; x++) {
            this.data[x].push(this.reportsDataUniversity[x]);
          }
          this.tableData1 = res.resultDataMap;
        });

      }
      else if (type === 'StandaloneReport') {
        if(this.roleId==1|| this.roleId==26){
          this.userId='ALL';
        }
        let toDate:any=null;let fromDate:any=null;
        if(this.instiFormData.value.toDate!=null && this.instiFormData.value.toDate!=''){
        toDate=this.formatDate(this.instiFormData.value.toDate)
        }
        if(this.instiFormData.value.fromDate!=null && this.instiFormData.value.fromDate!=''){
          fromDate=this.formatDate(this.instiFormData.value.fromDate)
        }
        let x = this.institutionmanagement.getStandAloneReportWithDate(this.instiFormData.value.surveyYearValue.substr(0, 4), this.userId, this.roleId,toDate,fromDate)
        let y = this.institutionmanagement.getApprovalStandaloneList(this.userId, this.instiFormData.value.surveyYearValue.substr(0, 4))
        const requestArray = [];
        requestArray.push(x);
        requestArray.push(y);
        forkJoin(requestArray).subscribe(res => {
          // console.log(res)
          this.data = res[0].counterMap;
          this.standAloneReport = true;
          this.collegeReport = false;
          this.universityReport = false;
          // console.log(this.data)
          // console.log(this.reportsDataStandlone)
          if (this.roleId == '26' || this.roleId == '1') {
            this.data['approveCount'] = res[1].count.toString();
          }
          this.data = Object.entries(this.data).map((item: any) => ([item[1]]))
          let counter: any = this.roleId == '26' || this.roleId == '1' ? '5' : '3';

          for (let x = 0; x <= counter; x++) {
            this.data[x].push(this.reportsDataStandlone[x]);
          }
          res[0].resultDataMap['approve'] = res[1].approvedStatusByUniversityListBean;
          this.tableData1 = res[0].resultDataMap;
        });

      }
      else if (type === 'CollegeReport') {
        if(this.roleId==1|| this.roleId==26){
          this.userId='ALL';
        }
        let toDate:any=null;let fromDate:any=null;
        if(this.instiFormData.value.toDate!=null && this.instiFormData.value.toDate!=''){
        toDate=this.formatDate(this.instiFormData.value.toDate)
        }
        if(this.instiFormData.value.fromDate!=null && this.instiFormData.value.fromDate!=''){
          fromDate=this.formatDate(this.instiFormData.value.fromDate)
        }
        let x = this.institutionmanagement.getCollegeReportWithDate(this.instiFormData.value.surveyYearValue.substr(0, 4), this.userId, this.roleId,toDate,fromDate)
        let y = this.institutionmanagement.getApprovalUniversityList(this.userId, this.instiFormData.value.surveyYearValue.substr(0, 4))

        const requestArray = [];
        requestArray.push(x);
        requestArray.push(y);
        forkJoin(requestArray).subscribe(res => {
          this.data = res[0].counterMap;
          this.collegeReport = true;
          this.standAloneReport = false;
          this.universityReport = false;
          if (this.roleId == '26' || this.roleId == '1' || this.roleId == '7' || this.roleId == '6') {
            this.data['approveCount'] = res[1].count.toString();
          }
          this.data = Object.entries(this.data).map((item: any) => ([item[1]]))
          let counter: any = this.roleId == '26' || this.roleId == '1' ? '6' : '2';
          for (let x = 0; x <= counter; x++) {
            if (this.roleId == '7' || this.roleId == '6') {
              this.data[x].push(this.reportsDataCollege2[x]);
            } else {
              this.data[x].push(this.reportsDataCollege[x]);
            }
          }
          if (this.roleId == '7' || this.roleId == '6') {
            this.data[3].push(this.reportsDataCollege[6])
          }
          res[0].resultDataMap['approve'] = res[1].approvedStatusByUniversityListBean;
          this.tableData1 = res[0].resultDataMap;

        })

      }

    }
  }

  allDataView(data: any, type: any) {
    this.showDataTable = true;
    this.collegeReport = false;
    this.standAloneReport = false;
    this.universityReport = false;
    this.type = type;
    this.Details = type == 'college' ? this.details[1] : this.details[0];
    if (type === 'standalone') {
      this.institutionmanagement.getStandalonRequestDetails(data.requestId, this.instiFormData.value.surveyYearValue.split('-')[0]).subscribe((res: any) => {
        this.obj = res.standaloneApprovalRequestDetailsBean;
        if (res.standaloneApprovalRequestDetailsBean.similarCollege.length > 0) {
          this.showSimilarCollege = true;
          this.dataSimilarCollege = res.standaloneApprovalRequestDetailsBean.similarCollege;
        }
        if (res.statusCode === 'AISH099') {
          this.showDataTable = false;
          this.sharedservice.showError(res.statusDesc);
        }
        if (this.obj) {
          this.dataCollege = Object.values(this.obj);

          this.showDataTable = true;
          this.isApproveList = true;
        }
      })
    } else if (type === 'college') {
      this.institutionmanagement.getCollegeApprovalRequestDetails(data.requestId, this.instiFormData.value.surveyYearValue.split('-')[0]).subscribe((res: any) => {
        this.obj = res.collegeApprovalRequestDetailsBean;
        if (res.collegeApprovalRequestDetailsBean.similarCollege.length > 0) {
          this.showSimilarCollege = true;
          this.dataSimilarCollege = res.collegeApprovalRequestDetailsBean.similarCollege;
        }
        if (res.statusCode === 'AISH099') {
          this.showDataTable = false;
          this.sharedservice.showError(res.statusDesc);
        }
      })
    }
  }





  dataGet(e: any) {
    this.page = 1
    if (e == 'Add') this.tableData = this.tableData1.UniversityAddLog
    if (e == 'Edit') this.tableData = this.tableData1.UniversityEditLog ?? this.tableData1.EditStandalone ?? this.tableData1.EditCollege;
    if (e == 'Delete') this.tableData = this.tableData1.UniversityDeleteLog ?? this.tableData1.DeleteCollege
    if (e == 'Convert to College') this.tableData = this.tableData1.UniversityConvertToCollegeLog
    if (e == 'Upgrade To College') this.tableData = this.tableData1.UpgradeToCollege
    if (e == 'Upgrade To University') this.tableData = this.tableData1.UpgradeToUniversity
    if (e == 'Inactive') this.tableData = this.tableData1.InactiveStandalone;
    if (e == 'Active') this.tableData = this.tableData1.ActiveStandalone;
    if (e == 'Deaffilation College') this.tableData = this.tableData1.DeaffilationCollege;
    if (e == 'Merge College') this.tableData = this.tableData1.MergeCollege;
    if (e == 'Upgrade College') this.tableData = this.tableData1.UpgradeCollege;
    if (e == 'Affiliation College') this.tableData = this.tableData1.AffiliationCollege;
    if (e === 'Approved/Rejected List') this.tableData = this.tableData1.approve;

  }

  resetData() {
    this.showDataTable = false;
    this.isApproveList = false;
    this.standAloneReport = false;
    this.collegeReport = false;
    this.universityReport = false;
    this.tableData = [];
    this.tableData1 = [];
  }

  back(): void {
    this.type === 'standalone' ? this.standAloneReport = true : this.collegeReport = true;
    this.showDataTable = false;
    this.showSimilarCollege = false;
  }
  formatDate(date: string | Date): string {
    const parsedDate = new Date(date);

    if (isNaN(parsedDate.getTime())) {
      throw new Error('Invalid date');
    }

    const day = String(parsedDate.getDate()).padStart(2, '0');
    const month = String(parsedDate.getMonth() + 1).padStart(2, '0'); // Months are 0-based
    const year = parsedDate.getFullYear();

    return `${day}/${month}/${year}`;
  }
}
