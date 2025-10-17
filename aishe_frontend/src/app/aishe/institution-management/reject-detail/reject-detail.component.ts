import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { GetService } from 'src/app/service/get/get.service';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-reject-detail',
  templateUrl: './reject-detail.component.html',
  styleUrls: ['./reject-detail.component.scss']
})
export class RejectDetailComponent implements OnInit, OnChanges  {
  @Input() requestDetail:any;
  @Output() statusChanged = new EventEmitter<boolean>();
  // requestId: any;
  // constructor() { }

  // ngOnInit(): void {
  //   console.log(this.requestDetail)
  // }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['requestDetail']) {
      console.log('Parent message changed:', changes['requestDetail'].currentValue);
      // You can run any logic here when input changes
      this.requestId=changes['requestDetail'].currentValue;
      this.approveCollege('',false,false);
    }
  }
    showView:boolean=false
    @Input()
    instiFormData: any;
  
    showTablesApproveReject: boolean = false;
    id: any;
    collegeNameA: any;
    labelData1: any[] = [];
    dataCollege: any[] = [];
    similarmatTableData: boolean = false;
    SimilarDataTableDataTable:Array<any>=[]
    requestId:any;
    idR: any;
    obj: any;
    isStatusId:any = 1;
    //  valueSNO:any;
    userForm = new FormGroup({
      Name:new FormControl('',[Validators.required]),
      stateName:new FormControl('',[Validators.required]),
      districtName:new FormControl('',[Validators.required]),
      instituteType:new FormControl('',[Validators.required]),
      managementType:new FormControl('',[Validators.required]),
      universityId:new FormControl('',[Validators.required]),
  
      surveyYear:new FormControl('',[Validators.required]),
      aremarks: new FormControl('', [
        Validators.required,
        Validators.maxLength(2000),
      ]),
      approveR: new FormControl('', Validators.required),
      alldocumentsvalid: new FormControl('', Validators.requiredTrue),
      location:new FormControl('same',[]),
      universityState:new FormControl('')
    });
    isSubmitted = false;
    affiliateData1: boolean = false;
    alldata: any[] = [];
    StartLimit: number = 0;
    pageData: number = 10;
    EndLimit: number = 15;
    count: number = 0;
    pageSize: any = 10;
    searchText: any;
    page: number = 1;
    hostel: any[] = [];
    tableSize: number[] = [10, 20, 30, 40, 50];
    aisheCode: string = '';
    stateList: any;
    selectedState = '--';
    universityList: any;
    selectYear: any;
    approvalHistoryData: any;
    surveyYearValue: string = '';
    surveyYear: any = '';
    btnstate: boolean = false;
    showButton: boolean = false;
    showButton1: boolean = true;
    selectedYearValue: any = '';
    selectedStatus: any = '';
    showTable: boolean = false;
    roleId1: any;
    state: any;
    surveyYearList: any;
    filtereduniversityList: any;
    UniSurveyYear: any;
    isApprove: boolean = false;
    dataEmail: any;
    formData: any;
    Formdata = new FormGroup({
      selectedYearValue: new FormControl(),
      collegeType: new FormControl(),
      universityValue: new FormControl(),
    });
  
    institutionM: any;
    router: any;
    isFormInvalid: any;
    approvalResponse: any;
    stateCode: any;
    universityId: any;
    sortedData: any;
    requestStatus: any;
  
    submitted:any=false;
    NameData:any;
    userData:any;
    filteredState:any[] = [];
    dataDistrict:any
    filteredDistrict:any[] = [];
    dataCheck:boolean = false;
    standalonTypefilter:any[] = [];
    standalonTypeData:any;
    filteredmanagementT:any[]=[];
    managmentData:any;
    collegeTypeData: any[] = [];
    filteredcollegeTypeData: any;
    showViewDetailOnly: boolean=true;
    previousStateId: any;
    previousUniversityId: any;
    previousDistrictCode: any;
    previousFilteredState: any;
    previousBool: boolean=false;
    filteredState2: any;
    userData2: any;
    affiliatingUniversityStateCode: any;
    takeAction!: boolean;
    tableHeaders:any;
    viewDetailOnly=true;
    constructor(
      private dialog: MatDialog,
      private instManagementService: InstitutionmanagementService,
      public sharedservice: SharedService,public localService:LocalserviceService,
      private getService : GetService,private router1: Router
    ) {
      this.sharedservice.global_loader = true;
      this.roleId1 = this.localService.getData('roleId');
      this.stateCode=this.localService.getData('stateCode');
      this.universityId=this.localService.getData('universityId');
    }
  
    ngOnInit(): void {
    }

    back(): void {
      // this.sharedservice.global_loader = true;
      // this.location.back();
      this.showTablesApproveReject = false;
      this.statusChanged.emit(false);
      this.showTable = true;
      this.isApprove = false;
      this.userForm.reset();
    }
    backToSummaryReport(){
     let viewDetailOfRequestId=JSON.parse(this.localService.getData('viewDetailOfRequestId'));
     let backURL=JSON.parse(this.localService.getData('searchStateData'));
     backURL.status=true;
     viewDetailOfRequestId.status=null;
     this.localService.saveData('viewDetailOfRequestId',JSON.stringify(viewDetailOfRequestId));
     this.localService.saveData('searchStateData',JSON.stringify(backURL))
     this.router1.navigate([backURL.backurl])
    }
  
    approveCollege(event: any,approvalresp:any,showView?:any) {
      // console.log('event', event)
      this.requestId;
      this.isSubmitted = false;
      this.showTablesApproveReject = true;
      this.showTable = false;
      this.isApprove = true;
      this.approvalResponse=approvalresp;
      let surveyYear=0;
      this.instManagementService
        .getCollegeApprovalRequestDetails(
          this.requestId,
          surveyYear
        )
        .subscribe((res) => {
          this.obj = res.collegeApprovalRequestDetailsBean;
          this.userForm.controls['Name'].setValue(this.obj?.collegeName);
          this.userForm.controls['stateName'].setValue(this.obj?.stateCode);
          // this.getdistrictName();
          this.userForm.controls['districtName'].setValue(this.obj?.districtCode);
          this.userForm.controls['instituteType'].setValue(parseInt(this.obj?.collegeTypeId));
          this.userForm.controls['managementType'].setValue(this.obj?.collegeManagementTypeId);
          this.userForm.controls['universityId'].setValue(this.obj?.affiliatingUniversityAisheCode.split('-')[1]);
          
  
          // this.previousStateId=this.userForm.get('stateName')?.value;
          this.previousUniversityId=this.userForm.get('universityId')?.value;
          // this.previousDistrictCode=this.userForm.get('districtName')?.value;
          // this.previousFilteredState=this.filteredState;
          this.userForm.get('universityState')?.setValue(this.obj?.affiliatingUniversityStateCode);
          this.previousBool=false;
          this.affiliatingUniversityStateCode=this.obj?.affiliatingUniversityStateCode;


          if(this.localService.getData('viewDetailOfRequestId')){
            this.viewDetailOnly=false;
            }

          if(this.obj?.stateCode==this.obj?.affiliatingUniversityStateCode){
            this.userForm.get('location')?.setValue('same');
            this.previousBool=false;
          }else{
            this.userForm.controls['location'].setValue('others');
            let stateCode=this.userForm.get('stateName')?.value;
            if(stateCode){
              // this.userForm.get('location')?.setValue('other');
              this.filteredState2=this.filteredState.filter((data)=>data.stateCode!=stateCode);
              // console.log(this.filteredState2)
              this.userData2=this.userData.filter((data:any)=>data.stateCode!=stateCode);
              this.userForm.get('universityState')?.setValue(this.affiliatingUniversityStateCode);
              this.previousBool=true;
            }
          }
          // this.loadUniversityData();
          if (res.collegeApprovalRequestDetailsBean.similarCollege.length > 0) {
            // this.SimilarDataTableDataTable = res.collegeApprovalRequestDetailsBean.similarCollege
            this.similarmatTableData = true;
          }else{
            // this.SimilarDataTableDataTable=[]
            this.similarmatTableData = false;
          }
  
          if(showView){//show only view
           this.showViewDetailOnly=false;
          }else{//false show only with approve field
            
            this.showViewDetailOnly=true;
          }
          if(!showView){
            this.showTablesApproveReject = true;
            this.showTable = false;
            // this.isApproved = true;
            this.showView=false;
          }else{
            this.showTablesApproveReject = true;
            this.showTable = false;
            // this.isApproved = true;
            this.showView=false;
          }
        //  this.instManagementService.getRequestId(this.obj?.requestId).subscribe((res)=>{
        //   console.log(res)
        //  })
        });
  
    }
  
    downloadPdf(base64: string) {
      const byteArray = new Uint8Array(
        atob(base64)
          .split('')
          .map((char) => char.charCodeAt(0))
      );
  
      const pdfData = new Blob([byteArray], { type: 'application/pdf' });
      const fileURL = URL.createObjectURL(pdfData); //create random url to render on browser
      //window.open(fileURL);
  
      let pdfName = 'viewDocument.pdf';
      // Construct the 'a' element
      let link = document.createElement('a');
      // link.download = pdfName;
      link.target = '_blank';
  
      // Construct the URI
      link.href = fileURL;
      document.body.appendChild(link);
      link.click();
  
      // Cleanup the DOM
      document.body.removeChild(link);
    }
  
  statusReceived(msg:any){
    // console.log(msg)
   this.requestStatus=msg
   if(msg.UNO==='Pending'){
    this.userForm.disable()
   }else{
    this.userForm.enable();
   }
  
  }


    downloadExcel() {
      this.tableHeaders = ['SNO','Request Id', 'College Name', 'University Name','Request Submitted','College Type', 'State Name','District Name'];
      const tableData = this.sortedData.map((row:any, i:any) => [
        // i+1,
        row.id, 
        row.collegeName, 
        row.universityName, 
        row.requestSubmittedOn,
        row.collegeType,
        row.stateName,
        row.districtName,
      ]);
    
     this.tableHeaders.shift();
      let param={
        tableHeaders:this.tableHeaders,
        tableData:tableData,
        // excelName:'Merged Insitutions',
        downloadExcelName:'College Approve/Reject Request',
        setHeaderCollumnWidths:[
          { wpx: 80 },//Request Id
          { wpx: 400 }, //College Name
          { wpx: 400 },//University Name,
          { wpx: 160 },//Request Submitted
          { wpx: 110 },//College Type
          { wpx: 80 },// State Name
          { wpx: 80 },//District Name
  
  
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
     this.sharedservice.downloadExcel(param);
    }
  

  

}
