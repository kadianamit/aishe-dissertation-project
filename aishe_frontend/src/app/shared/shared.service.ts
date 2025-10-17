import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { AlertDialogComponent } from '../dialog/alert-dialog/alert-dialog.component';
import { ChangePasswordComponent } from '../dialog/change-password/change-password.component';
import { CollegeDetailsComponent } from '../dialog/college-details/college-details.component';
import { DeleteDialogComponent } from '../dialog/delete-dialog/delete-dialog.component';
import { PasswordComponent } from '../dialog/password/password.component';
import { ProgressDialogComponent } from '../dialog/progress-dialog/progress-dialog.component';
import { ReloginComponent } from '../dialog/relogin/relogin.component';
import { SuccessMessageComponent } from '../dialog/success-message/success-message.component';
import { UserDetailComponent } from '../dialog/user-detail/user-detail.component';
import { AssignRoleComponent } from '../dialog/assign-role/assign-role.component';
import { AnnexureComponent } from '../dialog/annexure/annexure.component';
import { NewRequestDialogComponent } from '../dialog/new-request-dialog/new-request-dialog.component';
import { RemunerationStatusDialogComponent } from '../dialog/remuneration-status-dialog/remuneration-status-dialog.component';
import { ForeignInstituteDialogComponent } from '../dialog/foreign-institute-dialog/foreign-institute-dialog.component';
import { ErrorComponent } from '../error/error.component';
import { InstitutionSummaryDialogComponent } from '../dialog/institution-summary-dialog/institution-summary-dialog.component';
import { ViewProgramComponent } from '../dialog/view-program/view-program.component';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import * as XLSX from 'xlsx-js-style';
import * as FileSaver from 'file-saver';
import { ViewUserDialogComponent } from '../dialog/view-user-dialog/view-user-dialog.component';
import { SessionExpireDialogComponent } from '../dialog/session-expire-dialog/session-expire-dialog.component';
import { UpdateUserDialogComponent } from '../dialog/update-user-dialog/update-user-dialog.component';
import { StateWiseMonitoringDialogComponent } from '../dialog/state-wise-monitoring-dialog/state-wise-monitoring-dialog.component';
import { ApproveRejectUniversityDialogComponent } from '../dialog/approve-reject-university-dialog/approve-reject-university-dialog.component';
// import { Overlay } from '@angular/cdk/overlay/overlay';

@Injectable()
export class SharedService {
  setFacultyDep(payload: { faculty: boolean; department: boolean }) {
    throw new Error('Method not implemented.');
  }
  getprogrammeList() {
    throw new Error('Method not implemented.');
  }
  getuserManagement() {
    throw new Error('Method not implemented.');
  }
  private sessionExpSubject = new Subject<string>();
  sessionExpInfo$ = this.sessionExpSubject.asObservable();

  pageSizeOptions = [15, 25, 50];
  StartLimit: number = 0;
  EndLimit: number = 50;
  pageSize: any = 50;
  page: number = 1;
  pageData: number = 0;
  global_loader: boolean = false;
  global_notification: any[] = [];
  searchTxt: any;
  loginButton = new BehaviorSubject<any>(0);
  getLoginButton = this.loginButton.asObservable();

  sidebarToggle = new BehaviorSubject<any>({});
  getSidebarToggle = this.sidebarToggle.asObservable();
  // latestSurvey: number = 2021
  // currentSurveyYear: number = 2022
  // latestSurveyfic: string = '2021-22'
  // currentSurveyfic: string = '2022-2023'
  // currentSurveyfiscal: string = '2022-23'
  // latestSurveyFiscal: string = '2021-22'

  // latestSurvey: number = 2022;
  // currentSurveyYear: number = 2023;
  // latestSurveyfic: string = '2022-23';
  // currentSurveyfic: string = '2023-2024';
  // currentSurveyfiscal: string = '2023-24';
  // latestSurveyFiscal: string = '2022-23';

   latestSurvey: number = 2023;
  currentSurveyYear: number = 2024;
  latestSurveyfic: string = '2023-24';
  currentSurveyfic: string = '2024-2025';
  currentSurveyfiscal: string = '2024-25';
  latestSurveyFiscal: string = '2023-24';


  approveDisapproveArr: any = [
    { id: 0, name: 'All' },
    { id: 1, name: 'Pending' },
    { id: 2, name: 'Final Submit' },
    { id: 3, name: 'Approved' },
    { id: 4, name: 'Disapproved' },
  ];

  constructor(
    public toastrService: ToastrService,
    public dialog: MatDialog,
    public httpClient: HttpClient
  ) { }
  userId = new BehaviorSubject<any>(0);
  getUserDetails = this.userId.asObservable();

  setUserDetails(data: any) {
    if (data) {
      this.userId.next(data);
    }
  }

  userMapping = new BehaviorSubject<any>(0);
  getUserMapping = this.userMapping.asObservable();

  setUserMapping(data: any) {
    if (data) {
      this.userMapping.next(data);
    }
  }

  langId = new BehaviorSubject<any>(0);
  getLangId = this.langId.asObservable();

  setLangId(data: any) {
    if (data) {
      this.langId.next(data);
    }
  }

  sidebarAccessibility = new BehaviorSubject<any>(0);
  getsidebarAccessibility = this.sidebarAccessibility.asObservable();

  setSidebarAccessibility(data: any) {
    if (data) {
      this.sidebarAccessibility.next(data);
    }
  }

  setLoginButton(data: any) {
    this.loginButton.next(data);
  }

  setSidebar(data: any) {
    this.sidebarToggle.next(data);
  }

  setRoleList(data: any) {
    this.roleListLevel.next(data);
  }
  getTokenInfoData(message: string) {
    this.sessionExpSubject.next(message); // Emit a new value
  }

  roleListLevel = new BehaviorSubject<any>(0);
  getRoleList = this.roleListLevel.asObservable();

  // remuneration enum
  public remuneration = [
    { id: 'ALL', name: 'ALL' },
    { id: 'PENDING', name: 'Pending' },
    { id: 'APPROVED', name: 'Approved' },
    { id: 'REJECT', name: 'Reject' },
    { id: 'DEFERRED', name: 'Deferred' },
    { id: 'ACCOUNT_DETAIL_LOCKED', name: 'A/C Details Locked' },

    { id: 'BANK_ADVISE_SENT', name: 'Bank Advise Sent' },
    { id: 'PAYMENT_SUCCESSFUL', name: 'Payment Successful' },
    { id: 'PAYMENT_PENDING', name: 'Payment Pending' },
    {
      id: 'ERRONEOUS_BANK_ACCOUNT',
      name: 'Erroneous Bank Account(Payment Failed)',
    },
    { id: 'ERRONEOUS_IFSC_CODE', name: 'Erroneous IFSC Code(Payment Failed)' },
    {
      id: 'ERRONEOUS_ACCOUNT_AND_IFSC',
      name: 'Erroneous Bank Account and IFSC Code both(Payment Failed)',
    },
    { id: 'OTHER_REASONS', name: 'Other Reasons(Payment Failed)' },
    { id: 'OTHERS', name: 'Others' },
  ];

  // remunerations end
  public Contact = [
    {
      contactName: 'Email',
      id: 15,
    },
    {
      contactName: 'Mobile No',
      id: 27,
    },
    {
      contactName: 'Telephone',
      id: 36,
    },
    {
      contactName: 'Residential Phone',
      id: 43,
    },
    {
      contactName: 'URL',
      id: 58,
    },
  ];
  public status = [
    {
      name: 'Active',
      id: 1,
    },
    {
      name: 'InActive',
      id: 2,
    },
    {
      name: 'Expired',
      id: 3,
    },
    {
      name: 'Under Process For Renewal',
      id: 4,
    },
    {
      name: 'Other',
      id: 5,
    },
  ];

  public paymentMonth = [
    {
      monthName: 'January',
    },
    {
      monthName: 'February',
    },
    {
      monthName: 'March',
    },
    {
      monthName: 'April',
    },
    {
      monthName: 'May',
    },
    {
      monthName: 'June',
    },
    {
      monthName: 'July',
    },
    {
      monthName: 'August',
    },
    {
      monthName: 'September',
    },
    {
      monthName: 'October',
    },
    {
      monthName: 'November',
    },
    {
      monthName: 'December',
    },
  ];
  acronym = 2;
  name: 1 | undefined;
  short_name = 4;
  public courseDurationYear = [{id:0,value:[0]}, {id:1,value:[1]}, {id:2,value:[1,2]}, {id:3,value:[1,2,3]}, {id:4,value:[1,2,3,4]}, {id:5,value:[1,2,3,4,5]}, {id:6,value:[1,2,3,4,5,6]}];
  public courseDurationMonth = [{id:0,value:[0]}, {id:1,value:[1]}, {id:2,value:[1,2]}, {id:3,value:[1,2,3]}, {id:4,value:[1,2,3,4]}, {id:5,value:[1,2,3,4,5]}, {id:6,value:[1,2,3,4,5,6]}, 
  {id:7,value:[1,2,3,4,5,6,7]}, {id:8,value:[1,2,3,4,5,6,7,8]}, {id:9,value:[1,2,3,4,5,6,7,8,9]}, {id:10,value:[1,2,3,4,5,6,7,8,9,10]}, {id:11,value:[1,2,3,4,5,6,7,8,9,10,11]}];
  wrongRequestId() {
    this.toastrService.error('Request Id does not exists!');
  }
  showLoginSuccess() {
    this.toastrService.success('Login Sucessfully');
  }
  showSuccess() {
    this.toastrService.success('This record has been saved successfully!!!');
  }
  showUpdate() {
    this.toastrService.success('This record has been updated successfully!!!');
  }
  showDelete() {
    this.toastrService.success('This record has been deleted successfully!!!');
  }
  showWarning() {
    this.toastrService.error('Please enter required field!!!');
  }
  userDetails() {
    this.toastrService.success('User Details update successfully');
  }
  showLogin() {
    this.toastrService.success(
      'This record has been saved/updated successfully!!!'
    );
  }
  apiNotRespond() {
    this.toastrService.error(
      `Server could not respond,Please try again later!!!`
    );
  }

  showValidationMessagePDF(message: any) {
    this.toastrService.error(message);
  }
  showError(message: any) {
    this.toastrService.error(message);
  }
  showValidationMessage(message: any) {
    this.toastrService.error(message);
  }
  showSuccessMessage(message: any) {
    this.toastrService.success(message);
  }
  // showUnexceptedError(message:any){
  //   this.toastrService.error(message);
  // }
  // showNotAccessError(message: any){
  //   this.toastrService.error(message);
  // }
  // showNoRecordError(message: any){
  //   this.toastrService.error(message);
  // }
  onPanelClose() {
    this.searchTxt = '';
  }
  public monthList = [
    {
      id: 0,
      name: 'January',
    },
    {
      id: 1,
      name: 'February',
    },
    {
      id: 2,
      name: 'March',
    },
    {
      id: 3,
      name: 'April',
    },
    {
      id: 4,
      name: 'May',
    },
    {
      id: 5,
      name: 'June',
    },
    {
      id: 6,
      name: 'July',
    },
    {
      id: 7,
      name: 'August',
    },
    {
      id: 8,
      name: 'September',
    },
    {
      id: 9,
      name: 'October',
    },
    {
      id: 10,
      name: 'November',
    },
    {
      id: 11,
      name: 'December',
    },
  ];

  public roleList = [
    {
      roleId: 21,
      name: 'PGDM Institutes',
      id: 7,
    },
    {
      roleId: 13,
      name: 'Technical/Polytechnic',
      id: 2,
    },
    {
      roleId: 14,
      name: 'Nursing',
      id: 3,
    },
    {
      roleId: 15,
      name: 'Teacher Training',
      id: 4,
    },
    {
      roleId: 20,
      name: 'Institutes under Ministries',
      id: 5,
    },
    {
      roleId: 23,
      name: 'Paramedical',
      id: 1,
    },
    {
      roleId: 24,
      name: 'Hotel Management and Catering',
      id: 6,
    },
    {
      roleId: 12,
      name: 'College',
      id: 12,
    },
    {
      roleId:29,
      name:'Instititions under Rehabilitation Council of India',
      id:9
    },{
      roleId:28,
      name:'Pharmacy Institutions',
      id:8
    }
  ];
  public documentList = [
    'CERTIFICATE',
    'DATA CAPTURE FORMAT(DCF)',
    'TEACHING INFORMATION FORMAT(TIF)',
    'Other Minority',
  ];
  public institutionList = [
    { name: 'IIT(Indian Institute of Technology)', id: 'IIT' },
    { name: 'IIIT(Indian Institute of Information Technology)', id: 'IIIT' },
    { name: 'NIT(National Institute of Technology)', id: 'NIT' },
    { name: 'IIM(Indian Institute of Management")', id: 'IIM' },
    {
      name: 'IISER(Indian Institute of Science Education & Research)',
      id: 'IISER',
    },
    {
      name: 'IIEST(Indian Institute of Engineering Science and Technology)',
      id: 'IIEST',
    },
    { name: 'NIPER(National Institute of Pharmaceutical)', id: 'NIPER' },
    { name: 'NID(National Institute of Design)', id: 'NID' },
    { name: 'ISI(Indian Statistical Institute)', id: 'ISI' },
    { name: 'AIIMS(All India Institute of Medical Science)', id: 'AIIMS' },
    { name: 'NIFT(National Institute of Fashion Technology)', id: 'NIFT' },
    { name: 'SPA(School of Planning & Architecture)', id: 'SPA' },
    { name: 'Central University', id: 'UNIVERSITY' },
  ];
  // IIT("indian institute of technology"),
  // NIT("national institute of technology"),
  // IIIT("indian institute of information technology"),
  // SPA("school of planning & architecture"),
  // IIM("indian institute of management"),
  // IISER("indian institute of science education & research"),
  // IIEST("indian institute of engineering science and technology"),
  // NIPER("national institute of pharmaceutical"),
  // NID("national institute of design"),
  // ISI("indian statistical institute"),
  // NIFT("national institute of fashion technology"),
  // AIIMS("all india institute of medical science"),
  // UNIVERSITY("university");

  public role = {
    'Ministry of Education': '1',
    'SysAdmin': '26',
    'State Nodal Officer': '6',
    'University Grants Commission': '2',
    'All India Council for Technical Education': '3',
    'Indian Nursing Council': '4',
    'National Council For Teacher Education': '5',
    'National Paramedical Council': '18',
    'Apex User': '19',
    'National Council for Hotel Management and Catering Technology': '22',
    'University': '7',
    'Directorate of Technical Education': '8',
    'State Nursing Body/Council': '9',
    'State Council of Educational Research and Training': '10',
    'State Paramedical Council': '11',
    'College': '12',
    'Polytechnic': '13',
    'Nursing (Diploma) Institute': '14',
    'Teacher Training (Diploma) Institute': '15',
    'Standalone Institution Under Ministry': '20',
    'PGDM': '21',
    'Paramedical Institute': '23',
    'Hotel Management and Catering Institute': '24',
    'Data Entry Operator': '16',
    'Pharmacy Institutions':'28',
    'Instititions under Rehabilitation Council of India':'29'
  };
  public authorityList = [
    {
      id: this.role['University'],
      name: 'University Nodal Officer',
      authorityId: [
        this.role['State Nodal Officer'],
        this.role['Ministry of Education'],
      ],
    },
    {
      id: this.role['Directorate of Technical Education'],
      name: 'Directorate of Technical Education',
      authorityId: [
        this.role['All India Council for Technical Education'],
        this.role['State Nodal Officer'],
      ],
    },
    {
      id: this.role['State Nursing Body/Council'],
      name: 'State Nursing Body/Council',
      authorityId: [
        this.role['Indian Nursing Council'],
        this.role['State Nodal Officer'],
      ],
    },
    {
      id: this.role['State Council of Educational Research and Training'],
      name: 'State Council of Educational Research and Training',
      authorityId: [
        this.role['National Council For Teacher Education'],
        this.role['State Nodal Officer'],
      ],
    },
    {
      id: this.role['College'],
      name: 'College',
      authorityId: [this.role['State Nodal Officer'], this.role['University']],
    },
    {
      id: this.role['Polytechnic'],
      name: 'Polytechnic',
      authorityId: [
        this.role['Directorate of Technical Education'],
        this.role['State Nodal Officer'],
        this.role['All India Council for Technical Education'],
      ],
    },
    {
      id: this.role['Nursing (Diploma) Institute'],
      name: 'Nursing (Diploma) Institute',
      authorityId: [
        this.role['State Nursing Body/Council'],
        this.role['State Nodal Officer'],
        this.role['Indian Nursing Council'],
      ],
    },
    {
      id: this.role['Teacher Training (Diploma) Institute'],
      name: 'Teacher Training (Diploma) Institute',
      authorityId: [
        this.role['State Council of Educational Research and Training'],
        this.role['State Nodal Officer'],
        this.role['National Council For Teacher Education'],
      ],
    },
    {
      id: this.role['Standalone Institution Under Ministry'],
      name: 'Standalone Institution Under Ministry',
      authorityId: [this.role['Ministry of Education']],
    },
    {
      id: this.role['PGDM'],
      name: 'PGDM',
      authorityId: [
        this.role['Directorate of Technical Education'],
        this.role['State Nodal Officer'],
        this.role['All India Council for Technical Education'],
        this.role['Ministry of Education'],
      ],
    },
    {
      id: this.role['Paramedical Institute'],
      name: 'Paramedical Institute',
      authorityId: [
        this.role['State Paramedical Council'],
        this.role['State Nodal Officer'],
        this.role['National Paramedical Council'],
        this.role['Ministry of Education'],
      ],
    },
    {
      id: this.role['Hotel Management and Catering Institute'],
      name: 'Hotel Management and Catering Institute',
      authorityId: [
        this.role['State Nodal Officer'],
        this.role[
        'National Council for Hotel Management and Catering Technology'
        ],
        this.role['Ministry of Education'],
      ],
    },
  ];

  public AnnexureHeader = [
    'S.No.',
    'Exam Centre Area for 250 Candidates',
    'Particulars',
    'Sq ft/Candidate (approx)',
    'Candidates',
    'Total space in Sq ft',
  ];
  public Annexure = [
    {
      exam: 'Exam Centre Entry and Waiting area',
      particulars: 'Candidate',
      squareCandidate: 3,
      candidates: 250,
      totalSpace: 750,
    },
    {
      exam: 'Frisking area for males and female',
      particulars: 'Fixed',
      squareCandidate: 1,
      candidates: 20,
      totalSpace: 20,
    },
    {
      exam: 'Candidate Registration/ Seat Allocation- 5 Desk @100Sq. Ft+ Standing area',
      particulars: 'Fixed',
      squareCandidate: 1,
      candidates: 100,
      totalSpace: 100,
    },
    {
      exam: 'Candidate Exam Lab @ 6 Sq. Ft per Candidate',
      particulars: 'Candidate',
      squareCandidate: 6,
      candidates: 250,
      totalSpace: 1500,
    },
    {
      exam: '40% area for circulation area for invigilator movement and invigilator chairs',
      particulars: 'Candidate',
      squareCandidate: '-',
      candidates: '-',
      totalSpace: 600,
    },
    {
      exam: 'Common Area for Drinking Water Facility',
      particulars: 'Fixed',
      squareCandidate: 1,
      candidates: 50,
      totalSpace: 50,
    },
    {
      exam: 'Separate toilet for males and female',
      particulars: 'Fixed',
      squareCandidate: 1,
      candidates: 200,
      totalSpace: 200,
    },
    {
      exam: 'Server Room',
      particulars: 'Fixed',
      squareCandidate: 1,
      candidates: 100,
      totalSpace: 100,
    },
    {
      exam: 'Power Backup (DG set and/or UPS)',
      particulars: 'Fixed',
      squareCandidate: 1,
      candidates: 100,
      totalSpace: 100,
    },
  ];
  public annexure(): Observable<boolean> {
    return this.dialog
      .open(AnnexureComponent, {
        data: '',
        disableClose: true,
      })
      .afterClosed();
  }
  public savePopUp(ele: any): Observable<boolean> {
    return this.dialog
      .open(SuccessMessageComponent, {
        data: ele,
        disableClose: true,
      })
      .afterClosed();
  }
  public delete(): Observable<boolean> {
    return this.dialog
      .open(DeleteDialogComponent, {
        data: '',
        disableClose: true,
      })
      .afterClosed();
  }
  public showCollegeDetails(ele: any): Observable<boolean> {
    return this.dialog
      .open(CollegeDetailsComponent, {
        width: '70%',
        height: '100%',
        data: ele,
        disableClose: true,
      })
      .afterClosed();
  }

  public userDetail(ele: any): Observable<boolean> {
    return this.dialog
      .open(UserDetailComponent, {
        // width: '60%',
        // height: '100%',
        data: ele,
        // disableClose: true,
      })
      .afterClosed();
  }
  public viewUser(ele: any): Observable<boolean> {
    return this.dialog
      .open(ViewUserDialogComponent, {
        width: '60%',
        data: ele,
        disableClose: true,
      })
      .afterClosed();
  }
  public alert(value: boolean): Observable<boolean> {
    return this.dialog
      .open(AlertDialogComponent, {
        data: value,
        disableClose: true,
      })
      .afterClosed();
  }

  public year = [2021, 2022];
  onPanleClosed() {
    this.searchTxt = '';
  }
  progressMoniterDialog(array: any, payload: any): Observable<boolean> {
    return this.dialog
      .open(ProgressDialogComponent, {
        width: '70%',
        height: 'auto',
        data: { array, payload },
        disableClose: true,
      })
      .afterClosed();
  }
  StateWiseMoniterDialog(array: any, payload: any): Observable<boolean> {
    return this.dialog
      .open(StateWiseMonitoringDialogComponent, {
        width: '70%',
        height: 'auto',
        data: { array, payload },
        disableClose: true,
      })
      .afterClosed();
  }
  public remunerationStatus(ele: any): Observable<any> {
    return this.dialog
      .open(RemunerationStatusDialogComponent, {
        width: '40%',
        data: ele,
        disableClose: true,
      })
      .afterClosed();
  }
  public openChangePass(ele: any): Observable<boolean> {
    return this.dialog
      .open(ChangePasswordComponent, {
        width: '30%',
        data: ele,
        disableClose: true,
      })
      .afterClosed();
  }
  public openPassword(data: any): Observable<boolean> {
    return this.dialog
      .open(PasswordComponent, {
        width: '60%',
        height: 'auto',
        data: data,
        disableClose: true,
      })
      .afterClosed();
  }
  public reLogin(ele: any): Observable<boolean> {
    return this.dialog
      .open(ReloginComponent, {
        data: ele,
        disableClose: true,
      })
      .afterClosed();
  }

  public assign(item: any[]): Observable<boolean> {
    return this.dialog
      .open(AssignRoleComponent, {
        data: item,
        width: 'auto',
        height: 'auto',
        disableClose: true,
      })
      .afterClosed();
  }

  public newRequest(ele: any): Observable<boolean> {
    return this.dialog
      .open(NewRequestDialogComponent, {
        data: ele,
        disableClose: true,
        // width: "60%",
      })
      .afterClosed();
  }

  public finalSubmitConfirm(): Observable<boolean> {
    return this.dialog
      .open(ForeignInstituteDialogComponent, {
        data: '',
        disableClose: true,
      })
      .afterClosed();
  }
  public instituteSummaryReport(
    ele: Array<any>,
    type: string,
    listType: string,
    instituteTypeList: Array<any>,
    surveyYear: any,
    data: any
  ): Observable<boolean> {
    return this.dialog
      .open(InstitutionSummaryDialogComponent, {
        width: '80%',
        height: '95%',
        data: {
          list: ele,
          type: type,
          listType: listType,
          instituteTypeList: instituteTypeList,
          surveyYear: surveyYear,
          urlData: data,
        },
        disableClose: true,
      })
      .afterClosed();
  }
  public errorMessage(ele: any): Observable<boolean> {
    return this.dialog
      .open(ErrorComponent, {
        data: ele,
        disableClose: true,
      })
      .afterClosed();
  }
  public viewProgram(data: any): Observable<boolean> {
    return this.dialog
      .open(ViewProgramComponent, {
        // scrollStrategy: this.overlay.scrollStrategies.noop(),
        disableClose: true,
        width: '50%',
        // maxHeight:'4?00px',
        height: 'auto',
        data: data,
      })
      .afterClosed();
  }

  // public updateUser(data: any): Observable<boolean> {
  //   return
  // }

  public session(): void {
    if (this.dialog.openDialogs.length === 0) {
      const dialogRef = this.dialog.open(SessionExpireDialogComponent, {
        data: '',
        disableClose: true,
      });
      dialogRef.afterClosed().subscribe((result) => {
        // this.dialog.openDialogs.length=0; // Pizza!
      });
    }
  }

  public approveReject(data: any): Observable<any> {
    return this.dialog
      .open(ApproveRejectUniversityDialogComponent, {
        disableClose: true,
        width: '65%',
        height: 'auto',
        data: data,
      }).afterClosed();
  }
  sendEOTP() {
    this.toastrService.success(`Email OTP sent successfully!!!`);
  }
  sendMOTP() {
    this.toastrService.success(`Mobile OTP sent successfully!!!`);
  }
  verifyMOTP() {
    this.toastrService.success(`Mobile OTP verify successfully!!!`);
  }
  verifyEOTP() {
    this.toastrService.success(`Email OTP verify successfully!!!`);
  }
  verifyOTP(message: any) {
    this.toastrService.error(message);
  }
  public institutionTypeList = ['C', 'S', 'U'];
  public institutionType = [
    {
      name: 'COLLEGE',
      id: 'C',
    },
    {
      name: 'STANDALONE',
      id: 'S',
    },
    {
      name: 'UNIVERSITY',
      id: 'U',
    },
  ];

  public questionList = [
    {
      question: 'Name of the Institution',
    },
  ];
  public modeList = [
    {
      id: 1,
      name: 'Regular',
    },
    {
      id: 2,
      name: 'Distance',
    },
    {
      id: 3,
      name: 'Private',
    },
    {
      id: 4,
      name: 'Off Campus Centres',
    },
    {
      id: 5,
      name: 'Online',
    },
  ];

  menuList: any = [
    { route: routes.Home, name: 'Institution Details', icon: 'fas fa-home' },
    {
      route: routes.UserManagement,
      name: 'User Management',
      icon: 'fa-solid fa-users',
    },

    { route: '', name: 'Web DCF', icon: 'fa-solid fa-list-check' },
    {
      route: routes.InstitutionManagement,
      name: 'Institution Management',
      icon: 'fa-solid fa-building-columns',
    },
    {
      route: routes.EditRegistration,
      name: 'Contact Details of the Institution',
      icon: 'fas fa-edit fa-sm fa-fw',
    },
    {
      route: routes.ChangePassword,
      name: 'Change Password',
      icon: 'fa-solid fa-lock',
    },
    { route: '', name: 'Reports', icon: 'fa-solid fa-laptop-file' },
    { route: routes.Reports, name: 'Reports', icon: 'fa-solid fa-laptop-file' },
    {
      route: routes.UniversityWiseCount,
      name: 'University Wise College Report',
      icon: 'fa-solid fa-laptop-file',
    },
    { route: '', name: 'Web DCF Progress', icon: 'fa-solid fa-bars-progress' },
    {
      route: routes.ProgressMonitoringWebDCF,
      name: 'Monitoring',
      icon: 'fa-solid fa-bars-progress',
    },
    {
      route: routes.Summary,
      name: 'Summary',
      icon: 'fa-solid fa-bars-progress',
    },
    {
      route: routes.ProgressMonitoring,
      name: 'Day Wise Progress',
      icon: 'fa-solid fa-bars-progress',
    },
    {
      route: routes.ProgrammeManagement,
      name: 'Programme Management',
      icon: 'fa-solid fa-users',
    },
    {
      route: routes.UserDirectory,
      name: 'User Directory',
      icon: 'fa-solid fa-address-book',
    },
    {
      route: routes.StandaloneInstitution,
      name: 'Standalone Management',
      icon: 'fa-solid fa-bars-progress',
    },
    {
      route: routes.InstitutionManagement,
      name: 'College Management',
      icon: 'fa-solid fa-building-columns',
    },
    {
      route: routes.university,
      name: 'University Management',
      icon: 'fa-solid fa-building-columns',
    },
    {
      route: routes.FillWEBDCF,
      name: 'Fill Web-DCF',
      icon: 'fa-solid fa-list-check',
    },
    {
      route: routes.DOCUMENTDOWNLOAD,
      name: 'Download Document',
      icon: 'fa-solid fa-list-check',
    },
    {
      route: routes.NewRegistartion,
      name: 'New User Registration',
      icon: 'fa-solid fa-list-check',
    },
    { route: routes.Dashboard, name: 'Dashboard', icon: 'fa fa-dashboard' },
    { route: '', name: 'Audit Log', icon: 'fa-solid fa-list-check' },
    { route: '', name: 'Logout', icon: 'fas fa-sign-out-alt' },
    {
      route: routes.unlockWebDcf,
      name: 'Form Management',
      icon: 'fa-solid fa-list-check',
    },
    {
      route: routes.registration,
      name: 'HEI Onboarding',
      icon: 'fa-solid fa-building-columns',
    },
    {
      route: routes.universalSearch,
      name: 'Universal Search',
      icon: 'fa-solid fa-list-check',
    },
    {
      route: routes.emailManagement,
      name: 'Email Management',
      icon: 'fa-solid fa-list-check',
    },
    // { 'route': routes.registration, 'name': 'HEI on Boarding', 'icon': 'fa-solid fa-building-columns' },
    {
      route: routes.dcfDemoBasic,
      name: 'Common Data for ONOD',
      icon: 'fa-solid fa-list-check',
    },
    {
      route: routes.stateWiseMonitoring1,
      name: 'State Wise Monitoring',
      icon: '',
    },
  ];

  instituationSidebar = [
    {
      menuName: 'Home',
      rollId: this.role['SysAdmin'],
    },
  ];
  getSurveyYear(surveyYear: any) {
    let splitSurvey = surveyYear.toString().substring(2, 4);
    let intSurvey = parseInt(splitSurvey);
    let a = intSurvey + 1;
    let b = surveyYear + '-' + a;
    return b;
  }

  downloadPDF(param: any, a: any) {
    // const doc = new jsPDF();
    const doc = new jsPDF({
      orientation: param.orientationType,
      unit: 'mm',
      format: 'a3',
      compress: true, // Enable compression to reduce PDF size
    });
    var today = new Date();
    var date =
      today.getDate() +
      '-' +
      (today.getMonth() + 1) +
      '-' +
      today.getFullYear();
    var time =
      today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
    var CurrentDateTime = date + ' ' + time;
    doc.setFont('times');

    doc.setFontSize(16);
    doc.text(param.pdfName, doc.internal.pageSize.getWidth() / 2, 15, {
      align: 'center',
    });
    (doc as any).autoTable({
      head: [param.tableHeaders],
      body: param.tableData,
      startY: 20,
      headStyles: {
        6: { fillColor: [66, 105, 100], textColor: [255, 255, 255] },
      },
      columnStyles: a,
      didDrawPage: (data: any) => {
        const totalPages = doc.internal.pages.length - 1;
        const currentPage = doc.internal.pages.length - 1;

        doc.setFontSize(12);
        doc.text(
          `Page: ${currentPage} of ${totalPages}`,
          30,
          doc.internal.pageSize.getHeight() - 10
        );
        doc.text(
          `Generated on: ${CurrentDateTime}`,
          90,
          doc.internal.pageSize.getHeight() - 10
        );
      },
      margin: { top: 20 }, // Adjust margin if needed
    });

    // Save the PDF
    doc.save(param.downloadPdfName);
  }
  downloadExcel(param: any) {
    // Create a worksheet from the table data
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(param.tableData);

    // Manually set the headers in the worksheet
    XLSX.utils.sheet_add_aoa(worksheet, [param.tableHeaders], { origin: 'A1' });

    // Add the data below the headers
    XLSX.utils.sheet_add_json(worksheet, param.tableData, {
      origin: 'A2',
      skipHeader: true,
    });

    // Apply styles to the header row
    const headerStyle = param.headerStyle;

    // Apply styles to the header row
    const headerRange = XLSX.utils.decode_range(worksheet['!ref'] as string);

    for (let col = headerRange.s.c; col <= headerRange.e.c; col++) {
      const cellAddress = XLSX.utils.encode_cell({ r: 0, c: col });
      const cell = worksheet[cellAddress];

      if (cell) {
        cell.s = headerStyle; // Apply header style
      }
    }
    // Set column widths to prevent overlap
    worksheet['!cols'] = param.setHeaderCollumnWidths;

    // Create a new workbook and add the worksheet to it
    const workbook: XLSX.WorkBook = {
      Sheets: { 'Institution Details': worksheet },
      SheetNames: ['Institution Details'],
    };

    // Generate an Excel file (as a Blob)
    const excelBuffer: any = XLSX.write(workbook, {
      bookType: 'xlsx',
      type: 'array',
    });

    // Save the Excel file
    this.saveAsExcelFile(excelBuffer, param.downloadExcelName);
  }

  // Helper function to save the file
  saveAsExcelFile(buffer: any, fileName: string): void {
    const EXCEL_TYPE =
      'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
    const data: Blob = new Blob([buffer], { type: EXCEL_TYPE });
    const currentTimeInMillis = new Date().getTime();
    const date = new Date(currentTimeInMillis);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    const formattedDate = `${day}-${month}-${year}`;
    FileSaver.saveAs(data, `${fileName}${formattedDate}.xlsx`);
  }

  public pageDetailsU = [
    {
      column: 'basic_detail',
      getColumn: 'basic_detail',
      menuName: 'Basic Information',
      subMenu: 'NA',
      tabName: 'Basic Details',
      // 'path': `${routes.BasicInfo}`,
      selectedIndex: 0,
    },
    {
      column: 'officer_details',
      getColumn: 'officerDetails',
      menuName: 'Basic Information',
      subMenu: 'NA',
      tabName: 'Officer Details',
      // 'path': `${routes.BasicInfo}`,
      selectedIndex: 1,
    },
    {
      column: 'address',
      getColumn: 'address',
      menuName: 'Basic Information',
      subMenu: 'NA',
      tabName: 'Address',
      // 'path': `${routes.BasicInfo}`,
      selectedIndex: 2,
    },
    {
      column: 'residential_facility',
      getColumn: 'residentialFacility',
      menuName: 'Basic Information',
      subMenu: 'NA',
      tabName: 'Residential Facility',
      // 'path': `${routes.BasicInfo}`,
      selectedIndex: 3,
    },
    {
      column: 'bank_details',
      getColumn: 'bank_details',
      menuName: 'Basic Information',
      subMenu: 'NA',
      tabName: 'Institution Bank Details',
      // 'path': `${routes.BasicInfo}`,
      selectedIndex: 6,
    },
    {
      column: 'off_campus',
      getColumn: 'offCampus',
      menuName: 'Basic Information',
      subMenu: 'NA',
      tabName: 'Off Campus Centres',
      // 'path': `${routes.BasicInfo}`,
      selectedIndex: 5,
    },
    {
      column: 'regional_center',
      getColumn: 'regional_center',
      menuName: 'Regional Centre & Off-Shore Centre',
      subMenu: 'NA',
      tabName: 'Regional Centre',
      // 'path': `${routes.RegionalCenter}`,
      selectedIndex: 0,
    },
    {
      column: 'off_shore_center_college',
      getColumn: 'off_shore_center_college',
      menuName: 'Regional Centre & Off-Shore Centre',
      subMenu: 'NA',
      tabName: 'Off-Shore Centre/College',
      // 'path': `${routes.RegionalCenter}`,
      selectedIndex: 1,
    },
    {
      column: 'list_of_faculty',
      getColumn: 'list_of_faculty',
      menuName: 'Faculty,Department & Programme',
      subMenu: 'List of Faculty',
      tabName: 'Faculty',
      // 'path': `${routes.FacultyDept}`,
      selectedIndex: 0,
    },
    {
      column: 'list_of_department',
      getColumn: 'list_of_department',
      menuName: 'Faculty,Department & Programme',
      subMenu: 'List of Department',
      tabName: 'Department',
      // 'path': `${routes.FacultyDept}`,
      selectedIndex: 1,
    },
    {
      column: 'regular_programme',
      getColumn: 'regularProgramme',
      menuName: 'Faculty,Department & Programme',
      subMenu: 'Details of Programme',
      tabName: 'Regular Mode',
      // 'path': `${routes.ListOfProgramme}`,
      selectedIndex: 0,
    },
    {
      column: 'distance_programme',
      getColumn: 'distanceProgramme',
      menuName: 'Faculty,Department & Programme',
      subMenu: 'Details of Programme',
      tabName: 'Distance Mode',
      // 'path': `${routes.ListOfProgramme}`,
      selectedIndex: 1,
    },
    {
      column: 'private_programme',
      getColumn: 'privateProgramme',
      menuName: 'Faculty,Department & Programme',
      subMenu: 'Details of Programme',
      tabName: 'Private Mode',
      // 'path': `${routes.ListOfProgramme}`,
      selectedIndex: 2,
    },
    {
      column: 'off_campus_programme',
      getColumn: 'offCampusProgramme',
      menuName: 'Faculty,Department & Programme',
      subMenu: 'Details of Programme',
      tabName: 'Off Campus Centres',
      // 'path': `${routes.ListOfProgramme}`,
      selectedIndex: 4,
    },
    {
      column: 'online_program',
      getColumn: 'onlineProgram',
      menuName: 'Faculty,Department & Programme',
      subMenu: 'Details of Programme',
      tabName: 'Online Mode',
      // 'path': `${routes.ListOfProgramme}`,
      selectedIndex: 3,
    },

    {
      column: 'regular_enrollment',
      getColumn: 'regularEnrollment',
      menuName: 'Student Enrolment',
      subMenu: 'NA',
      tabName: 'Regular Mode',
      // 'path': `${routes.Enrolment}`,
      selectedIndex: 0,
    },
    {
      column: 'distance_enrollment',
      getColumn: 'distanceEnrollment',
      menuName: 'Student Enrolment',
      subMenu: 'NA',
      tabName: 'Distance Mode',
      // 'path': `${routes.Enrolment}`,
      selectedIndex: 1,
    },
    {
      column: 'private_enrollment',
      getColumn: 'privateEnrollment',
      menuName: 'Student Enrolment',
      subMenu: 'NA',
      tabName: 'Private Mode',
      // 'path': `${routes.Enrolment}`,
      selectedIndex: 1,
    },
    {
      column: 'off_campus_enrollment',
      getColumn: 'offCampusEnrollment',
      menuName: 'Student Enrolment',
      subMenu: 'NA',
      tabName: 'Off Campus Centres',
      // 'path': `${routes.Enrolment}`,
      selectedIndex: 1,
    },
    {
      column: 'online_enrollment',
      getColumn: 'onlineEnrollment',
      menuName: 'Student Enrolment',
      subMenu: 'NA',
      tabName: 'Online Mode',
      // 'path': `${routes.Enrolment}`,
      selectedIndex: 1,
    },
    {
      column: 'distance_enrollment_rc',
      getColumn: 'distanceEnrollmentRc',
      menuName: 'Student Enrolment',
      subMenu: 'NA',
      tabName: 'Regional Centre(s) Distance Mode',
      // 'path': `${routes.Enrolment}`,
      selectedIndex: 2,
    },

    {
      column: 'foreign_enrollment',
      getColumn: 'foreignEnrollment',
      menuName: 'Foreign Student Enrolment',
      subMenu: 'NA',
      tabName: 'NA',
      // 'path': `${routes.ForeignStudentEnrolment}`,
      selectedIndex: 0,
    },
    {
      column: 'foreign_enrollment_distance',
      getColumn: 'foreignEnrollmentDistance',
      menuName: 'Foreign Student Enrolment',
      subMenu: 'NA',
      tabName: 'Distance Mode',
      // 'path': `${routes.ForeignStudentEnrolment}`,
      selectedIndex: 0,
    },
    {
      column: 'foreign_enrollment_offcampus',
      getColumn: 'foreignEnrollmentOffcampus',
      menuName: 'Foreign Student Enrolment',
      subMenu: 'NA',
      tabName: 'Off Campus Centres',
      // 'path': `${routes.ForeignStudentEnrolment}`,
      selectedIndex: 0,
    },
    {
      column: 'foreign_enrollment_online',
      getColumn: 'foreignEnrollmentOnline',
      menuName: 'Foreign Student Enrolment',
      subMenu: 'NA',
      tabName: 'Online Mode',
      // 'path': `${routes.ForeignStudentEnrolment}`,
      selectedIndex: 0,
    },

    {
      column: 'regular_exam',
      getColumn: 'regularExam',
      menuName: 'Examination Result',
      subMenu: 'NA',
      tabName: 'Regular Mode',
      // 'path': `${routes.ExaminationResult}`,
      selectedIndex: 0,
    },
    {
      column: 'distance_exam',
      getColumn: 'distanceExam',
      menuName: 'Examination Result',
      subMenu: 'NA',
      tabName: 'Distance Mode',
      // 'path': `${routes.ExaminationResult}`,
      selectedIndex: 1,
    },
    {
      column: 'exam_result_private_external',
      getColumn: 'privateExam',
      menuName: 'Examination Result',
      subMenu: 'NA',
      tabName: 'Private Mode',
      // 'path': `${routes.ExaminationResult}`,
      selectedIndex: 2,
    },
    {
      column: 'off_campus_exam',
      getColumn: 'offCampusExam',
      menuName: 'Examination Result',
      subMenu: 'NA',
      tabName: 'Off Campus Centres',
      // 'path': `${routes.ExaminationResult}`,
      selectedIndex: 4,
    },
    {
      column: 'online_exam',
      getColumn: 'onlineExam',
      menuName: 'Examination Result',
      subMenu: 'NA',
      tabName: 'Online Mode',
      // 'path': `${routes.ExaminationResult}`,
      selectedIndex: 0,
    },
    {
      column: 'distance_exam_rc',
      getColumn: 'distanceExamRc',
      menuName: 'Examination Result',
      subMenu: 'NA',
      tabName: 'Regional Centre(s) Distance Mode',
      // 'path': `${routes.ExaminationResult}`,
      selectedIndex: 3,
    },

    {
      column: 'placement_details',
      getColumn: 'placement_details',
      menuName: 'Examination Result',
      subMenu: 'Placement Details',
      tabName: 'NA',
      // 'path': `${routes.PlacementDetails}`,
      selectedIndex: 0,
    },
    {
      column: 'teaching_staff',
      getColumn: 'teaching_staff',
      menuName: 'Staff Information',
      subMenu: 'Teaching Staff Information',
      tabName: 'Staff Details',
      // 'path': `${routes.StaffInfo}`,
      selectedIndex: 0,
    },
    {
      column: 'teaching_staff_sanctioned_strength',
      getColumn: 'teaching_staff_sanctioned_strength',
      menuName: 'Staff Information',
      subMenu: 'Teaching Staff Information',
      tabName: 'Designation-wise Sanctioned Strength',
      // 'path': `${routes.StaffInfo}`,
      selectedIndex: 1,
    },

    {
      column: 'non_teaching_staff_detail',
      getColumn: 'non_teaching_staff_detail',
      menuName: 'Staff Information',
      subMenu: 'Non Teaching Information',
      tabName: 'NA',
      // 'path': `${routes.NonTeachingInfo}`,
      selectedIndex: 0,
    },
    {
      column: 'financial_info_income',
      getColumn: 'financial_info_income',
      menuName: 'Financial Information',
      subMenu: 'NA',
      tabName: 'Income',
      // 'path': `${routes.Finance}`,
      selectedIndex: 0,
    },
    {
      column: 'financial_info_expenditure',
      getColumn: 'financial_info_expenditure',
      menuName: 'Financial Information',
      subMenu: 'NA',
      tabName: 'Expenditure',
      // 'path': `${routes.Finance}`,
      selectedIndex: 1,
    },
    {
      column: 'infra',
      getColumn: 'infra',
      menuName: 'Infrastructure',
      subMenu: 'NA',
      tabName: 'NA',
      // 'path': `${routes.Infrastructure}`,
      selectedIndex: 0,
    },
    {
      column: 'scholarship',
      getColumn: 'scholarship',
      menuName: 'Scholarships Fellowship Loans',
      subMenu: 'NA',
      tabName: 'Scholarships',
      // 'path': `${routes.ScholarShip}`,
      selectedIndex: 0,
    },
    {
      column: 'fellowship',
      getColumn: 'fellowship',
      menuName: 'Scholarships Fellowship Loans',
      subMenu: 'NA',
      tabName: 'Fellowships',
      // 'path': `${routes.ScholarShip}`,
      selectedIndex: 1,
    },
    {
      column: 'education_loan',
      getColumn: 'education_loan',
      menuName: 'Scholarships Fellowship Loans',
      subMenu: 'NA',
      tabName: 'Education Loans',
      // 'path': `${routes.ScholarShip}`,
      selectedIndex: 2,
    },
    {
      column: 'fee_reimbursement',
      getColumn: 'fee_reimbursement',
      menuName: 'Scholarships Fellowship Loans',
      subMenu: 'NA',
      tabName: 'Tuition Fee Reimbursement',
      // 'path': `${routes.ScholarShip}`,
      selectedIndex: 3,
    },
    {
      column: 'accreditation',
      getColumn: 'accreditation',
      menuName: 'Accreditation',
      subMenu: 'NA',
      tabName: 'NA',
      // 'path': `${routes.Accreditation}`,
      selectedIndex: 0,
    },

    {
      column: 'regulatory_info',
      getColumn: 'regulatory_info',
      menuName: 'Regulatory Information',
      subMenu: 'NA',
      tabName: 'NA',
      // 'path': `${routes.Regulatory}`,
      selectedIndex: 0,
    },
    {
      column: 'nep_introduction',
      getColumn: 'nepIntroduction',
      menuName: 'NEP Information',
      subMenu: 'NEP Basic Info',
      tabName: 'Introduction',
      // 'path': `${routes.NEP}`,
      selectedIndex: 0,
    },
    {
      column: 'nep_abc',
      getColumn: 'nepAbc',
      menuName: 'NEP Information',
      subMenu: 'NEP Basic Info',
      tabName: 'Academic Bank of Credits (ABC)',
      // 'path': `${routes.NEP}`,
      selectedIndex: 1,
    },
    {
      column: 'nep_multiple_entry_exit',
      getColumn: 'nepMultipleEntryExit',
      menuName: 'NEP Information',
      subMenu: 'NEP Basic Info',
      tabName: 'Multiple Entry/Exit',
      // 'path': `${routes.NEP}`,
      selectedIndex: 2,
    },
    {
      column: 'nep_interenship',
      getColumn: 'nepInterenship',
      menuName: 'NEP Information',
      subMenu: 'NEP Basic Info',
      tabName: 'Internship/Apprenticeship',
      // 'path': `${routes.NEP}`,
      selectedIndex: 3,
    },
    {
      column: 'nep_academic_research',
      getColumn: 'nepAcademicResearch',
      menuName: 'NEP Information',
      subMenu: 'NEP Basic Info',
      tabName: 'Academic and Research',
      // 'path': `${routes.NEP}`,
      selectedIndex: 4,
    },
    {
      column: 'nep_ranking_excellence',
      getColumn: 'nepRankingExcellence',
      menuName: 'NEP Information',
      subMenu: 'NEP Basic Info',
      tabName: 'Ranking Excellence',
      // 'path': `${routes.NEP}`,
      selectedIndex: 5,
    },
    {
      column: 'nep_multi_disciplinary',
      getColumn: 'nepMultiDisciplinary',
      menuName: 'NEP Information',
      subMenu: 'Course/Programme',
      tabName: 'Multidisciplinary',
      // 'path': `${routes.NEP}`,
      selectedIndex: 0,
    },
    {
      column: 'nep_dual_programme',
      getColumn: 'nepDualProgramme',
      menuName: 'NEP Information',
      subMenu: 'Course/Programme',
      tabName: 'Dual Programme',
      // 'path': `${routes.NEP}`,
      selectedIndex: 1,
    },
    {
      column: 'nep_joint_programme',
      getColumn: 'nepJointProgramme',
      menuName: 'NEP Information',
      subMenu: 'Course/Programme',
      tabName: 'Joint Programme',
      // 'path': `${routes.NEP}`,
      selectedIndex: 2,
    },
    {
      column: 'nep_iks',
      getColumn: 'nepIks',
      menuName: 'NEP Information',
      subMenu: 'Course/Programme',
      tabName: 'IKS',
      // 'path': `${routes.NEP}`,
      selectedIndex: 3,
    },
    {
      column: 'nep_detail_industry_internship_apprentice_regular',
      getColumn: 'nepDetailIndustryInternshipApprenticeRegular',
      menuName: 'NEP Industry, Internship and Apprenticeship',
      subMenu: 'Industry, Internship and Apprenticeship',
      tabName: 'Regular',
      // 'path': `${routes.NEPIIAP}`,
      selectedIndex: 0,
    },
    {
      column: 'nep_detail_industry_internship_apprentice_distance',
      getColumn: 'nepDetailIndustryInternshipApprenticeDistance',
      menuName: 'NEP Industry, Internship and Apprenticeship',
      subMenu: 'Industry, Internship and Apprenticeship',
      tabName: 'Distance',
      // 'path': `${routes.NEPIIAP}`,
      selectedIndex: 1,
    },
    {
      column: 'nep_detail_industry_internship_apprentice_offcampus',
      getColumn: 'nepDetailIndustryInternshipApprenticeOffcampus',
      menuName: 'NEP Industry, Internship and Apprenticeship',
      subMenu: 'Industry, Internship and Apprenticeship',
      tabName: 'OffCampus',
      // 'path': `${routes.NEPIIAP}`,
      selectedIndex: 2,
    },
    {
      column: 'nep_detail_industry_internship_apprentice_online',
      getColumn: 'nepDetailIndustryInternshipApprenticeOnline',
      menuName: 'NEP Industry, Internship and Apprenticeship',
      subMenu: 'Industry, Internship and Apprenticeship',
      tabName: 'Online',
      // 'path': `${routes.NEPIIAP}`,
      selectedIndex: 3,
    },
  ];
}

export enum routes {
  Home = '/aishe/home',
  Dashboard = '/aishe/dashboard',
  UserManagement = '/aishe/userManagement/user',
  unlockWebDcf = '/aishe/formManagement/unlockWebDcf',
  FillWEBDCF = '/aishe/formManagement/fill_web_dcf',
  DOCUMENTDOWNLOAD = '/aishe/formManagement/document',
  SurveyManagement = '/notworking',
  InstitutionManagement = '/aishe/Institution-Management',
  Reports = '/aishe/reports',
  InstitutionReports = '/aishe/reportsInst',
  ReportsWebDCF = '/aishe/reports2020',
  SurveyGuidelines = '/notworking',
  ProgressMonitoring = '/notworking',
  ProgressMonitoringWebDCF = '/aishe/progress',
  RemunerationManagement = '/notworking',
  AuditTrail = '/notworking',
  DocumentEmailManagement = '/notworking',
  NewUserRegistraiton = '/notworking',
  UserDirectory = '/notworking',
  WebDCFFeedback = '/notworking',
  ProgrammeManagement = '/aishe/programme-management',
  RequestAddInstitute = '/requestAddInstitute',
  EditRegistration = '/aishe/editRegistration',
  ChangePassword = '/ChangePassword',
  Summary = '/aishe/progress/summary',
  Monitering = '/aishe/progress/monitering',
  DayWise = '/aishe/progress/day-wise',
  StandaloneInstitution = '/aishe/Institution-Management/standalone',
  university = '/aishe/Institution-Management/university',
  registration = '/aishe/registration-management',
  NewRegistartion = '/aishe/userManagement/userRegistration',
  AddCISO = '/aishe/ciso/add',
  ViewCISO = '/aishe/ciso/view',
  Website = '/aishe/ciso/application',
  AgencyList = '/aishe/ciso/agencyList',
  NotFilledCISO = '/aishe/ciso/notFilledCISO',
  systemDetails = '/aishe/ciso/systemDetails',
  serverDetails = '/aishe/ciso/serverDetails',
  switchDetails = '/aishe/ciso/switchDetails',
  uploadFile = '/aishe/ciso/uploadFile',
  Survey = '/aishe/surveyManagement/surveyAction',
  SurveyLog = '/aishe/surveyManagement/surveyLog',
  FORGOTDATAUSER = '/datauserlogin/forgotPassword',
  USERREGISTRATION = '/userRegistration/datauser',
  DATAUSERLOGIN = '/datauserlogin',
  INSTITUTIONALLOGIN = '/institutionallogin',
  NTA = '/aishe/ntaQuestionnaire',
  remunerationManagement = '/aishe/remunerationManagement',
  NEP = '',
  PlacementDetails = '',
  ExaminationResult = '',
  ScholarShip = '/aishe/scholarship',
  ForeignIns = '/aishe/foreign_ins',
  ForeignInsApprove = '/aishe/foreign_ins/approve',
  MoEMang = '/aishe/moe_management',
  reportSummary = '/aishe/Institution-Management/summary',
  requestShiftUnversity = '/aishe/requestForShiftUnversity',
  approveRejectUniversity = '/aishe/approveRejectUniversity',
  reportSummary1 = '/aishe/Institution-Management/summaryar',
  SimilarInstitute = '/aishe/Institution-Management/checkSimilar',
  ENROLMENTESTIMATION = '/aishe/enrolmentEstimation',
  universalSearch = '/aishe/universalsearch',
  searchbyAishe = '/aishe/searchbyAishe',
  emailManagement = '/aishe/emailSmsMang',
  geo = '/aishe/geoLocation',
  dcfDemoBasic = '/aishe/dcf_demo/basic',
  dcfDemoPrograme = '/aishe/dcf_demo/programe',
  dcfDemoEnrolment = '/aishe/dcf_demo/enrolment',
  dcfDemoforeignEnrolment = '/aishe/dcf_demo/foreignEnrolment',
  dcfDemoteaching = '/aishe/dcf_demo/teaching',
  collegeToUniv = '/aishe/report/collegeToUniversity',
  standaloneToUniv = '/aishe/report/standaloneToUniversity',
  standaloneToCollege = '/aishe/report/standaloneToCollege',
  inactiveStandalone = '/aishe/report/inactiveStandalone',
  inactiveDeaffilliate = '/aishe/report/inactiveDeaffilliate',
  UniversityWiseCount = '/aishe/report/universityWiseCollege',
  UniversityWiseOffcampus = '/aishe/report/universityWiseOffcampus',
  Merge = '/aishe/report/merge',
  ParticipatedSurvey = '/aishe/report/participatedSurvey',
  heiFeedback = '/aishe/report/feedback_list_hei',
  snoFeedback = '/aishe/report/feedback_list_sno',

  missingUser = '/aishe/report/missingUser',
  Delete = '/aishe/report/delete-report',
  Suggestion = '/aishe/feedback',
  ResearchDeveloment = '/aishe/research_and_development',
  viewRND = '/aishe/research_and_development/view',
  approveRND = '/aishe/research_and_development/approveRND',

  // ENROLMENTESTIMATION='/aishe/enrolmentEstimation',
  // StaffInfo = '/app/form/staff',
  // NonTeachingInfo = '/app/form/nonteaching',
  // Finance = '/app/form/financial',
  // Infrastructure = '/app/form/infrastructure',
  // ScholarShip = '/app/form/scholarships-loans-accreditation',
  // OffShoreCenter = '/app/form/off-shore',
  // Regulatory = '/app/form/regulatory',
  stateWiseMonitoring1 = '/aishe/progress/stateWiseMonitoring',
  documentManagement = '/aishe/documentManagement',
  DataSharingRequest = '/aishe/datasharingreq',
  detailUnitCell='/aishe/detailUnitCell'
}
