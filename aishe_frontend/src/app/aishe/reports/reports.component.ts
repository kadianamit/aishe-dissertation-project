import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DailogmessageComponent } from 'src/app/common/dailogmessage/dailogmessage.component';
import { routes, SharedService } from 'src/app/shared/shared.service';
import { props } from 'src/app/common/props';
import { BasicReportsComponent } from './basic-reports/basic-reports.component';
import { InfrastructureComponent } from './infrastructure/infrastructure.component';
import { ListOfInstitutionComponent } from './list-of-institution/list-of-institution.component';
import { NonTeachingStaffComponent } from './non-teaching-staff/non-teaching-staff.component';
import { NumberOfInstitutionsComponent } from './number-of-institutions/number-of-institutions.component';
import { OutTurnComponent } from './out-turn/out-turn.component';
import { ProgrammesDisciplineComponent } from './programmes-discipline/programmes-discipline.component';
import { PupilTeacherRatioComponent } from './pupil-teacher-ratio/pupil-teacher-ratio.component';
import { StudentEnrolmentComponent } from './student-enrolment/student-enrolment.component';
import { TeachingStaffComponent } from './teaching-staff/teaching-staff.component';
import { ListOfReportingInstitutionsComponent } from './list-of-reporting-institutions/list-of-reporting-institutions.component';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { GenderRatioComponent } from './gender-ratio/gender-ratio.component';
import { FinanceComponent } from './finance/finance.component'
import { UgcReportsComponent } from './ugc-reports/ugc-reports.component';
import { RemunerationComponent } from './remuneration/remuneration.component';
import { DataUserComponent } from './data-user/data-user.component';
import { OthersComponent } from './others/others.component';
import { InstitutionContactDetailsComponent } from './institution-contact-details/institution-contact-details.component';
import { ProgressMonitoringComponent } from './progress-monitoring/progress-monitoring.component';
import { TimeSeriesComponent } from './time-series/time-series.component';
import { GetService } from 'src/app/service/get/get.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { InstitutionManagementReportComponent } from './institution-management-report/institution-management-report.component';
import { RequestDetailsForAddingInstitutionComponent } from './request-details-for-adding-institution/request-details-for-adding-institution.component';
import { map, filter } from 'rxjs';
import { utility } from 'src/app/common/utility';
import { CountrystatecityService } from 'src/app/service/reports/countrystatecity.service';
import { NotificationService } from 'src/app/service/reports/notification.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.scss']
})
export class ReportsComponent {
  RequestDetailsForAddingInstitution: any
  public routers: typeof routes = routes;
  reportsData: any = props.reportsData;
  roleId: string | null;
  optionSelected: string = '';
  surveyYearOption: any
  reportSurveyYear: any;
  filterSurveyYearOption: any
  userData: string | null;
  requestData: any;
  idDNO: string | null;
  filterStatesOption: any;
  filterdStates: any;
  stateCode: any;
  stateCodeDeafult: string | null;
  stateName: any;
  constructor(private route: ActivatedRoute,
    public dialog: MatDialog, public sharedservice: SharedService, public notify: NotificationService, public countrystatecityService: CountrystatecityService, public router: Router, private getService: GetService, private surveyyearservice: SurveyyearService, public localService: LocalserviceService) {
    this.roleId = this.localService.getData('roleId')
    console.log('00roleid',typeof(this.roleId),this.roleId)
    console.log('roleid22',this.sharedservice.role['University'],typeof(this.sharedservice.role['University']) )
    this.userData = this.localService.getData('userData')
    this.stateCodeDeafult = this.localService.getData('stateCode');
    this.stateCode = 'ALL';
    console.log(' this.stateCodeDeafult', this.stateCodeDeafult)
    console.log('77', this.roleId === this.sharedservice.role['University'])
    if (this.localService.getData('userData') === 'true') {
      this.reportsData = props.userData;
      console.log('kk',this.reportsData)
    } else if (this.roleId !== '26' && this.roleId !== '1' && this.roleId !== '6') {

      this.reportsData = props.reportsData.filter(e => (e.title !== 'Data Sharing') && (e.title !== 'Remuneration'))


    } else if (this.roleId === '6') {
            console.log('5')
      // Step 1: Remove top-level "Basic Reports"
      this.reportsData = (this.reportsData || []).filter((section: any) => section.title !== 'Basic Reports' );

      // Step 2: Remove sub-sections with subTitle === 'State-wise'
      // this.reportsData = this.reportsData
      //   .map((section: any) => ({
      //     ...section,
      //     reports1: (section.reports1 || []).filter((report: any) => report.subTitle !== 'State-wise')
      //   }))
  //      this.reportsData = this.reportsData?.map((section: any) => {
  //         if (section.title === 'Student Enrolment') {
  //   return {
  //     ...section,
  //     reports1: section.reports1?.filter(
  //       (subSection: any) => subSection.subTitle !== 'State-wise'
  //     )
  //   };
  // }
  // return section;})
      // Remove empty report sections

      const blockedReportNumbersTimeSeries = [
        'Report 105', 'Report 105A', 'Report 105C',
        'Report 106', 'Report 106A', 'Report 106B', 'Report 106C',
        'Report 124', 'Report 124A', 'Report 124B', 'Report 124C',
        'Report 122', 'Report 122A', 'Report 122B', 'Report 122C'
      ];

      const blockedReportNumbersOthers = ['Report 114', 'Report 119'];
      const blockedReportNumbersUGC = ['Report 134A'];
      const blockedReportNumberPupilTeacherRatio = ['Report 164', 'Report 144']
      this.reportsData = this.reportsData?.map((section: any) => {
        if (section.title === 'Time Series') {
          return {
            ...section,
            reports1: section.reports1?.map((subSection: any) => {
              return {
                ...subSection,
                subreports: subSection.subreports?.filter(
                  (report: any) => !blockedReportNumbersTimeSeries.includes(report.reportNumber)
                )
              };
            })
          };
        } else if (section.title === 'Others') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersOthers.includes(report.reportNumber)
            )
          };
        } else if (section.title === 'UGC Reports') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersUGC.includes(report.reportNumber)
            )
          }
        } else if (section.title === 'Pupil Teacher Ratio') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumberPupilTeacherRatio.includes(report.reportNumber)
            )
          }
        }
        return section;
      });
    }else if (this.roleId === this.sharedservice.role['University']) {  
  

     this.reportsData = (this.reportsData || []).filter((section: any) => 
  !['Basic Reports', 'UGC Reports', 'Know Your Institutions(For KYC Portal)','Finance','Data User','Institution Contact Details','List Of Reporting Institutions'].includes(section.title)
);

       this.reportsData = this.reportsData
        .map((section: any) => ({
          ...section,
          reports1: (section.reports1 || []).filter((report: any) => report.subTitle !== 'State-wise' && report.subTitle !== 'District-wise')
        }))
     
       const blockedReportNumbersTeachingStaffInstitutionWise=['Report 19','Report 20','Report 21','Report 22','Report 22A','Report 137','Report 165','Report 166'];
         const blockedReportNumbersNumberOfInstitutionsTeachingStaff=['Report 6','Report 7','Report 8','Report 9','Report 10','Report 13','Report 14'];
           const blockedReportNumbersNumberOfInstitutionsNonTeachingStaff=['Report 27','Report 28','Report 29','Report 30','Report 135',];
           const blockedReportNumbersInstitutionWiseStudentEnrolment=['Report 37','Report 38','Report 41','Report 42','Report 115','Report 127','Report 127A','Report 127C'];
           const blockedReportNumbersProgrammesDiscipline=['Report 38A','Report 38B','Report 38C','Report 65','Report 109','Report 109A','Report 109C','Report 110','Report 110A','Report 110C','Report 120','Report 121','Report 121A','Report 121C','Report 126','Report 126A','Report 126C','Report 150','Report 150A','Report 150C'];
           const blockedReportNumbersOutTurn=['Report 52','Report 54','Report 55','Report 55A','Report 55B','Report 145'];
           const blockedReportNumbersGenderRatio=['Report 44','Report 45','Report 46','Report 47A','Report 47B','Report 47C','Report 48','Report 49','Report 50','Report 51A','Report 51B'];
           const blockedReportNumbersInfrastructure=['Report 58','Report 111','Report 111C'];
           const blockedReportNumbersPupilTeacherRatio=['Report 64','Report 144'];
           const blockedReportNumbersListOfInstitutions=['Report 141','Report 146'];
           const blockedReportNumbersTimeSeries=['Report 100','Report 100C','Report 101','Report 101C','Report 102','Report 102C','Report 103','Report 103C','Report 104','Report 104C','Report 105','Report 105A','Report 105B','Report 105C','Report 106','Report 106A','Report 106B','Report 106C','Report 124','Report 124A','Report 124B','Report 124C','Report 125','Report 171','Report 125C','Report 122','Report 122A','Report 122B','Report 122C','Report 123','Report 172','Report 123C'];
           const blockedReportNumbersOthers=['Report 128','Report 130','Report 132','Report 114','Report 119'];
           const blockedReportNumbersProgressMonitoring=['Report 59','Report 61','Report 96','Report 161','Report 162','Report 163'];
             const blockedReportNumbersInstitutionManagement=['Report 164'];
             const blockedReportNumbersRequestDetailsForAddingInstitution=['Report 149']

       this.reportsData = this.reportsData?.map((section: any) => {
        if (section.title === 'Number Of Institutions') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersNumberOfInstitutionsTeachingStaff.includes(report.reportNumber)
            )
          };}
          else if (section.title === 'Teaching Staff') {
          return {
            ...section,
            reports1: section.reports1?.map((subSection: any) => {
              return {
                ...subSection,
                subreports: subSection.subreports?.filter(
                  (report: any) => !blockedReportNumbersTeachingStaffInstitutionWise.includes(report.reportNumber)
                )
              };
            })
          };
        }else if (section.title === 'Non Teaching Staff') {
          return {
            ...section,
            reports1: section.reports1?.map((subSection: any) => {
              return {
                ...subSection,
                subreports: subSection.subreports?.filter(
                  (report: any) => !blockedReportNumbersNumberOfInstitutionsNonTeachingStaff.includes(report.reportNumber)
                )
              };
            })
          };
        }else if (section.title === 'Student Enrolment') {
          return {
            ...section,
            reports1: section.reports1?.map((subSection: any) => {
              return {
                ...subSection,
                subreports: subSection.subreports?.filter(
                  (report: any) => !blockedReportNumbersInstitutionWiseStudentEnrolment.includes(report.reportNumber)
                )
              };
            })
          };
        }else  if (section.title === 'Programmes & Discipline') {
          return {
            ...section,
            reports1: section.reports1?.map((subSection: any) => {
              return {
                ...subSection,
                subreports: subSection.subreports?.filter(
                  (report: any) => !blockedReportNumbersProgrammesDiscipline.includes(report.reportNumber)
                )
              };
            })
          };
        }else if (section.title === 'Out Turn') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersOutTurn.includes(report.reportNumber)
            )
             
          };
        }else if (section.title === 'Gender Ratio') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersGenderRatio.includes(report.reportNumber)
            )
             
          };
        }else if (section.title === 'Infrastructure') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersInfrastructure.includes(report.reportNumber)
            )
             
          };
        }else if (section.title === 'Pupil Teacher Ratio') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersPupilTeacherRatio.includes(report.reportNumber)
            )
             
          };
        }else if (section.title === 'List Of Institutions') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersListOfInstitutions.includes(report.reportNumber)
            )
             
          };
        }else if (section.title === 'Time Series') {
          return {
            ...section,
            reports1: section.reports1?.map((subSection: any) => {
              return {
                ...subSection,
                subreports: subSection.subreports?.filter(
                  (report: any) => !blockedReportNumbersTimeSeries.includes(report.reportNumber)
                )
              };
            })
          };
        }else if (section.title === 'Others') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersOthers.includes(report.reportNumber)
            )
             
          };
        }else if (section.title === 'Progress Monitoring') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersProgressMonitoring.includes(report.reportNumber)
            )
             
          };
        }else if (section.title === 'Institution Management') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersInstitutionManagement.includes(report.reportNumber)
            )
             
          };
        }else if (section.title === 'Request Details For Adding Institution') {
          return {
            ...section,
            reports: section.reports?.filter(
              (report: any) => !blockedReportNumbersRequestDetailsForAddingInstitution.includes(report.reportNumber)
            )
             
          };
        }
           return section;
       })
    }
    if (this.roleId !== '26' && this.roleId !== '1') {




      this.reportsData.forEach((e: any, i: any) => {
        if (e.title === 'Data Sharing') {
          e['title'] = 'Data Sharing',
            e['reports'] = [];
          e['reports'].push({
            id: 1,
            name: "Unit Level Data",
            reportNumber: "downloadUnitLevelData",
            showState: true
          })
        }
      });
      console.log('hfghffhj uhguhuh',this.reportsData)
    } else {
      this.reportsData = props.reportsData;
    }
    this.idDNO = this.route.snapshot.paramMap.get('id');
    // if(this.idDNO==='1'){
    //   this.reportsData=props.reportsData.filter(e=>(e.title === 'List Of Institutions') || (e.title ==='Institution Management') || e.title ==='Request Details For Adding Institution')
    // }else{
    //   this.reportsData=props.reportsData.filter(e=>(e.title !== 'List Of Institutions') && (e.title !=='Institution Management') && e.title !=='Request Details For Adding Institution')
    // }
    // console.log("erter",this.idDNO)
    // this.sharedservice.global_loader = true;
    this.loadSurveyYear()
  }

  loadSurveyYear() {
    this.surveyyearservice.getdatasurveyyear().subscribe((res) => {
      this.reportSurveyYear = res;

      if (this.userData) {
        console.log(res)
        // this.reportSurveyYear = res.slice(1)
        console.log('ksdk', this.reportSurveyYear)
        console.log(this.reportSurveyYear)
         this.surveyYearOption = res.slice(2)
      }else{
         this.surveyYearOption = res.slice()
      }

     
      console.log("cdscsdvd het", this.surveyYearOption);
      this.filterSurveyYearOption = this.surveyYearOption.slice()

    })
  }
  expand(report: any) {
    if (report.title === 'Know Your Institutions(For KYC Portal)') {
      if(this.userData){
         this.filterSurveyYearOption = this.surveyYearOption.slice()
      }else{
      this.filterSurveyYearOption = [this.reportSurveyYear[2]]
      }
    }

  }
  condiArray: any;
  condiArr: any[] = [];

  surveyYearCondition(report: any, year: any): void {
    report['year'] = year.surveyYear;
    if (report?.id === 1) {
      this.condiArray = year.surveyYear;
    }



  }

  showselect: boolean = false;
  selectSurveyyear(opened: boolean, title: string, report: any) {
    //  this.filterSurveyYearOption=[];

    if (title === 'Data Sharing' && opened === true) {
      //  this.filterSurveyYearOption = [];

      switch (report.id) {

        case 1:
          // this.showselect=true;

          this.filterSurveyYearOption = [];
          this.filterSurveyYearOption =  [...this.reportSurveyYear.slice()];
          break;
        case 2:
          this.filterSurveyYearOption = [];
          this.filterSurveyYearOption = [...this.reportSurveyYear.slice(0, 4)];
          console.log("WIOF FIOR", this.filterSurveyYearOption)
          break;
        case 3:
          this.filterSurveyYearOption = [];
          this.filterSurveyYearOption = [...this.reportSurveyYear.slice(0, 2)];
          break;
        case 4:
          this.filterSurveyYearOption = [];
          this.filterSurveyYearOption = [...this.reportSurveyYear.slice(0, 2)];
          break;
        case 5:
          this.filterSurveyYearOption = [];
          this.filterSurveyYearOption = [...this.reportSurveyYear.slice(0, 2)];
          break;
        case 6:
          this.filterSurveyYearOption = [];
          this.filterSurveyYearOption = [...this.reportSurveyYear.slice(4)];
          break;
        case 7:
          this.filterSurveyYearOption = [];
          this.filterSurveyYearOption = [...this.reportSurveyYear.slice(4)];
          break;
        case 8:
          this.filterSurveyYearOption = [];
          this.filterSurveyYearOption = [...this.reportSurveyYear.slice(4)];
          break;
        case 9:
          this.filterSurveyYearOption = [];
          this.filterSurveyYearOption = [...this.reportSurveyYear.slice(4)];
          break;
        // if(report.id == 1){
        // this.filterSurveyYearOption  = [];
        //  this.filterSurveyYearOption  = [...this.reportSurveyYear.slice(1)];
        // }else if(report.id == 2 ){
        // this.filterSurveyYearOption  = [];
        // this.filterSurveyYearOption = [...this.reportSurveyYear.slice(1,3)]
        // }
        // else if(report.id == 3 ){
        // this.filterSurveyYearOption  = [];
        // this.filterSurveyYearOption  = [...this.reportSurveyYear.slice(4)]
        // }else if(report.id == 4 ){
        // this.filterSurveyYearOption  = [];
        // this.filterSurveyYearOption  = [...this.reportSurveyYear.slice(4)]
        // }else if(report.id == 5 ){
        // this.filterSurveyYearOption  = [];
        // this.filterSurveyYearOption  = [...this.reportSurveyYear.slice(4)]
        // }else if(report.id == 6 ){
        // this.filterSurveyYearOption  = [];
        // this.filterSurveyYearOption  = [...this.reportSurveyYear.slice(4)]
        // }
        // }else if(title !=='Data Sharing' && opened === true){
        //     this.filterSurveyYearOption = [...this.reportSurveyYear[2]]
        //   }
        default:
          this.filterSurveyYearOption = [];
          this.filterSurveyYearOption = [...this.reportSurveyYear];
      }
    }
    // if(title === 'Data Sharing' && report.id === 1 && this.optionSelected === '2023'){
    //   this.showselect=true;
    // }

  }

  ngOnInit(): void {

    this.loadStates();
    // this.showSAA = this.route.snapshot.paramMap.get('SAA');
    // this.showSPD = this.route.snapshot.paramMap.get('SPD');

  }
  loadStates() {

    let countryCode = utility.getLoginlocalStorageUserData().country
    // console.log("countryCode ", countryCode);

    this.countrystatecityService.getstate(countryCode).subscribe((res) => {

      this.filterStatesOption = res
      // this.filterStatesOption.splice(0,0,this.allStates)

      this.filterdStates = this.filterStatesOption.slice();
      if (this.roleId === this.sharedservice.role['State Nodal Officer']) {
        this.filterdStates = this.filterStatesOption.filter((ele: any) => ele.stateCode == this.stateCodeDeafult)
        this.stateName = this.filterdStates[0]
      }

    })

  }
  showReport(reportParentName: string, reportName: string, reportNumber1: string, report: any) {

    if (reportParentName.trim() === 'Data Sharing') {

      if (report.year !== '') {
        if (reportName === 'pooledData') {
          this.getService.getPooledDataReportZip(report.year);

        } else if (reportName === 'downloadUnitLevelData') {
          // if (report.year === '2023' && this.roleId !== this.sharedservice.role['State Nodal Officer']) {
          //   if (!this.stateCode) {
          //     this.notify.showError('Please select state !!');
          //     return;
          //   }

          //   this.getService.getUnitLevelReportZip(
          //     report.year,
          //     this.stateCode.stateCode ? this.stateCode.stateCode : this.stateCode
          //   );
          // }
          // else if (this.roleId === this.sharedservice.role['State Nodal Officer']) {

          //   this.getService.getUnitLevelReportZip(
          //     report.year,
          //     this.stateCodeDeafult
          //   );
          // } else {
            this.getService.getUnitLevelReportZip(
              report.year,
              ''
            );
         


        } else if (reportName.substr(0, 15) === 'downloadDcfData') {
          this.getService.getDcfReportZip(reportName.length > 15 ? reportName.substr(reportName.length - 1, reportName.length) : '', report.year)


        } else if (reportName === 'universityComparisonData') {
          this.getService.universityComparisonDataZip(report.year).subscribe((res: any) => {
            let byteArrays = res.byteData;
            utility.downloadAsExcel(byteArrays, "UniversityComparison");
          });
        } else if (reportName === 'collegeComparisonData') {
          this.getService.collegeComparisonDataZip(report.year).subscribe((res: any) => {
            let byteArrays = res.byteData;
            utility.downloadAsExcel(byteArrays, "CollegeComparisonData");
          });;
        } else if (reportName === 'standaloneComparisonData') {
          this.getService.standaloneComparisonDataZip(report.year).subscribe((res: any) => {
            let byteArrays = res.byteData;
            utility.downloadAsExcel(byteArrays, "StandaloneComparisonData");
          })
        }

      } else {
        this.sharedservice.showError('Please Select Survey Year')
      }

    }

    else if (reportParentName.trim() === 'Know Your Institutions(For KYC Portal)') {
      if (report.year !== '') {
        this.getService.getKnowYourKycZip(reportName.substr(reportName.length - 1, reportName.length), report.year)
        // window.open(`${environment.downloadReportUrl + reportNumber1}`, '_self');
      }
      else {
        this.sharedservice.showError('Please Select Survey Year')
      }
    }

    if (reportParentName.trim() == 'Basic Reports') {
      this.dialog.open(BasicReportsComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Number Of Institutions') {
      this.dialog.open(NumberOfInstitutionsComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Teaching Staff') {
      this.dialog.open(TeachingStaffComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Non Teaching Staff') {
      this.dialog.open(NonTeachingStaffComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Out Turn') {
      this.dialog.open(OutTurnComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Infrastructure') {
      this.dialog.open(InfrastructureComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'List Of Institutions') {
      this.dialog.open(ListOfInstitutionComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Institution Management') {
      this.dialog.open(InstitutionManagementReportComponent, {
        width: '90%',
        height: '100%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Request Details For Adding Institution') {
      this.dialog.open(RequestDetailsForAddingInstitutionComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Pupil Teacher Ratio') {
      this.dialog.open(PupilTeacherRatioComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }

    if (reportParentName.trim() == 'List Of Reporting Institutions') {
      this.dialog.open(ListOfReportingInstitutionsComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }

    if (reportParentName.trim() == 'Gender Ratio') {
      this.dialog.open(GenderRatioComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Time Series') {
      this.dialog.open(TimeSeriesComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Others') {
      this.dialog.open(OthersComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }


    if (reportParentName.trim() == 'UGC Reports') {
      this.dialog.open(UgcReportsComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }

    if (reportParentName.trim() == 'Finance') {
      this.dialog.open(FinanceComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Progress Monitoring') {
      this.dialog.open(ProgressMonitoringComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }

    if (reportParentName.trim() == 'Remuneration') {
      this.dialog.open(RemunerationComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Data User') {
      this.dialog.open(DataUserComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }
    if (reportParentName.trim() == 'Institution Contact Details') {
      this.dialog.open(InstitutionContactDetailsComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }

  }
  //from report 31 below method will call on click GO
  showReportNew(reportParentName: string, reportName: string, reportNumber1: string) {

    if (reportParentName.trim() == 'Programmes & Discipline') {

      this.dialog.open(ProgrammesDisciplineComponent, {
        height: '100%',
        width: '90%',
        data: {

          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }

      });


    }
    else if (reportParentName.trim() == 'Time Series') {
      this.dialog.open(TimeSeriesComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }

    else if (reportParentName.trim() == 'Student Enrolment') {
      this.dialog.open(StudentEnrolmentComponent, {
        height: '100%',
        width: '90%',
        data: {
          reportTitle: reportName,
          reportParentTitle: reportParentName,
          reportNumber: reportNumber1,
        }
      });
    }

  }
  openUniversityComponent() {
    this.router.navigate([this.routers.UniversityWiseCount]);
  }



}
