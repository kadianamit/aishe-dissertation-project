import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { GetService } from 'src/app/service/get/get.service';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-standalone-rejected-req',
  templateUrl: './standalone-rejected-req.component.html',
  styleUrls: ['./standalone-rejected-req.component.scss']
})
export class StandaloneRejectedReqComponent implements OnInit {
 @Input() requestDetail:any;
  @Output() statusChanged = new EventEmitter<boolean>();
  requestId: any;
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['requestDetail']) {
      console.log('Parent message changed:', changes['requestDetail'].currentValue);
      // You can run any logic here when input changes
      this.requestId=changes['requestDetail'].currentValue;
      this.editUser('',true);
    }
  }
 @Input()
  instiFormData: any;
  userDataTable: any[] = [];
  tableSize: number[] = [10, 20, 30, 40, 50];
  showTable: boolean = false;
  searchText: any;
  SimilarDataTableDataTable: any[] = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  page: number = 1;
  roleId: any;
  isSubmitted = false;
  obj: any;
  dataEmail:any;
  requestIs: any;
  isStatusId:any = 1;
  surveyYear: any;
  isApproved: boolean = false;
  showSimilarDataTable: boolean = false;
  showTablesApproveReject: boolean = false;
  surveyYearList: any;

  

  approvalResponse: any;
  requestStatus: any;
  isFormInvalid: any;

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
  roleId1:any;
  showView: boolean=true;
  takeAction!: boolean;
  tableHeaders:any;
  viewDetailOnly: boolean=true;
  constructor(
    private institutionmanagement: InstitutionmanagementService,
    public sharedservice: SharedService,
    private dialog: MatDialog,
    private instManagementService: InstitutionmanagementService,public localService:LocalserviceService,
    private getService : GetService,public router: Router
  ) {
    this.roleId1 = this.localService.getData('roleId');
    this.roleId=this.localService.getData('roleId');
  }

  ngOnInit(): void {

  }


  back() {
    this.showTablesApproveReject = false;
    this.statusChanged.emit(false);
    this.showTable = true;
    this.isApproved = false;
    this.isSubmitted = false;

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

  editUser(data: any,showView?:any) {
    this.requestId
    this.isSubmitted = false;
    this.approvalResponse=data?.approvalresp;
    let surveyYearCheck=0;
    let formdata = {
      RequestId: this.requestId,
      surveyYear: 0,
    };
    this.requestIs = data.id;
    this.surveyYear = surveyYearCheck;
    this.showTablesApproveReject = true;
    this.institutionmanagement
      .getStandaloneApprovalReject(formdata)
      .subscribe((res) => {
        this.obj = res.standaloneApprovalRequestDetailsBean;
        if(this.localService.getData('viewDetailOfRequestId')){
          this.viewDetailOnly=false;
          }
    

        if (
          res.standaloneApprovalRequestDetailsBean.similarCollege.length > 0
        ) {
          this.SimilarDataTableDataTable =
            res.standaloneApprovalRequestDetailsBean.similarCollege;
          this.showSimilarDataTable = true;
     
        }
        else{
          this.SimilarDataTableDataTable = []
        }
        if(showView){
          this.showTablesApproveReject = true;
          this.showTable = false;
          this.isApproved = true;
          this.showView=true;
        }else{
          this.showTablesApproveReject = true;
          this.showTable = false;
          this.isApproved = true;
          this.showView=false;
        }



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
    this.requestStatus=msg;
    // console.log(msg)
    // console.log(this.obj?.stateBodyTypeName)
  //  if(msg.UNO==='Pending' && this.obj?.stateBodyTypeName!='Other'){
  //   this.userForm.disable()
  //  }else{
  //   this.userForm.enable();
  //  }
  
  }

    downloadExcel() {
      this.tableHeaders = ['SNO','Request Id', 'Institute Name', 'Institution Type','State Name','District Name','Request Submitted'];
      const tableData = this.userDataTable.map((row:any, i:any) => [
        // i+1,
        row.id, 
        row.collegeName, 
        row.collegeType, 
     
        row.stateName,
        row.districtName,
        row.requestSubmittedOn,
      ]);
    
     this.tableHeaders.shift();
      let param={
        tableHeaders:this.tableHeaders,
        tableData:tableData,
        // excelName:'Merged Insitutions',
        downloadExcelName:'Standalone Approve/Reject Request',
        setHeaderCollumnWidths:[
          { wpx: 70 },//Request Id
          { wpx: 440 }, //College Name
          { wpx: 120 },//collegeType,
          { wpx: 88 },//stateName
          { wpx: 120 },//districtName
          { wpx: 125 },// requestSubmittedOn
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
