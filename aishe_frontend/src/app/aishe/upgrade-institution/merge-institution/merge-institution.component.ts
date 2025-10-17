import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';

import * as XLSX from 'xlsx-js-style';
import * as FileSaver from 'file-saver';
import { takeUntil } from 'rxjs/internal/operators/takeUntil';
import { Subject } from 'rxjs/internal/Subject';
import { param } from 'jquery';
import { ActivatedRoute, Router } from '@angular/router';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';

@Component({
  selector: 'app-merge-institution',
  templateUrl: './merge-institution.component.html',
  styleUrls: ['./merge-institution.component.scss']
})
export class MergeInstitutionComponent implements OnInit {
  unSubscribeSubject = new Subject()
  listData: Array<any> = [];
  tempList: Array<any> = [];
  listCount: Array<any> = [];
  searchText: string = ''
  selectedIndex: number = 0;
  surveyYearOption:Array<any>=[]
  tableHeaders = ['SNO','Survey Year', 'AISHE Code', 'Institute Name','Affilliated University','State', 'New AISHE Code','New Institute Name','Affilliated University','Action By','Action Time'];
  surveyYear: number = 0
  mergeCode: any;


  constructor(public sharedService: SharedService, public router: Router, public authService: AuthService,private route:ActivatedRoute,public  localStore1:EncryptDecrypt) { }

  ngOnInit(): void {
let mergeCode=this.route.snapshot.paramMap.get('id');
if(mergeCode){
  this.mergeCode=this.localStore1?.getDecryptedValue(mergeCode)
}


    this.getMergeInstitutionList()
    this.loadSurveyYear();
  }
  loadSurveyYear() {
    this.authService.getSurveyYear().subscribe((res) => {
      this.surveyYearOption = res;
    }, err => {
    })
  }
  tabSelected(event: any) {
    if (event.index === 0) {
      this.getMergeInstitutionList()
    } else if (event.index === 1) {
      this.surveyYear=0
      this.getMergeCountList()
    }
  }
  getMergeInstitutionList() {
    let payload = {
      surveyYear: this.surveyYear,
      type: 'MERGE_COLLEGE'
    }
    this.authService.getInstitutionDetails(payload).subscribe(res => {
      if (res.masterResponse && res.masterResponse.length) {
        this.listData = res.masterResponse;
        if(this.mergeCode){
          this.listData = this.listData.filter(item => item.oldAisheCode.toLowerCase() === this.mergeCode.toLowerCase());
        }
        this.tempList = [...this.listData];
        this.handlePageChange(this.sharedService.page = 1)
      } else {
        this.listData = [];
        this.tempList = []
      }
    }, err => {

    })
  }
  getMergeCountList() {
    let payload = {
      surveyYear: 0,
      type: 'MERGE_COLLEGE_COUNT'
    }
    this.authService.getInstitutionDetails(payload).subscribe(res => {
      if (res.masterResponse && res.masterResponse.length) {
        this.listCount = res.masterResponse;
      } else {
        this.listCount = [];
      }
    }, err => {

    })
  }
  handlePageChange(event: any) {
    this.sharedService.page = event;
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize);
    var a = Math.ceil(this.listData.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.listData.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.listData.length - 1);
    }
  }
  onChange(event: any) {
    this.sharedService.pageSize = parseInt(event);
    this.handlePageChange(this.sharedService.page = 1);
  }
  async updateResults() {
    this.listData = []
    this.listData = this.searchByValue(this.tempList);
    this.handlePageChange(this.sharedService.page = 1)
  }

  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        // item.name.trim();
        return (item.surveyYear?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.oldAisheCode?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.oldInstitutionName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.newAisheCode?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.newInstitutionName?.includes(this.searchText.trim().toLowerCase()))
          || (item.newUniversityName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.stateName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.oldUniversityName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.actionBy?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.actionTime?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }

  downloadPDF() {
    const tableData = this.listData.map((row, i) => [
      i + 1,
      row.surveyYear,
      row.oldAisheCode,
      row.oldInstitutionName,
      row.oldUniversityName,
      row.stateName,
      row.newAisheCode,
      row.newInstitutionName,
      row.newUniversityName,
      row.actionBy,
      row.actionTime

    ]);
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      pdfName:'Merged Institutions List',
      downloadPdfName:'List of Merge Insitutions.pdf',
      orientationType:'landscape',
    }
    let a={  1: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    2: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    3: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    4: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    5: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    6: { fillColor: [228, 245, 243], textColor: [0, 0, 0] },
    7: { fillColor: [228, 245, 243], textColor: [0, 0, 0] },
    8: { fillColor: [228, 245, 243], textColor: [0, 0, 0] },
    9: { fillColor: [228, 245, 243], textColor: [0, 0, 0] },
    10: { fillColor: [228, 245, 243], textColor: [0, 0, 0] },
  
}
     this.sharedService.downloadPDF(param ,a);
    
  }
  // Function to download the table data as an Excel file
  downloadExcel() {
    const tableData = this.listData.map((row, i) => [
      // i+1,
      row.surveyYear, 
      row.oldAisheCode, 
      row.oldInstitutionName, 
      row.oldUniversityName,
      row.stateName,
      row.newAisheCode,
      row.newInstitutionName,
      row.newUniversityName,
      row.actionBy,
      row.actionTime

    ]);
   this.tableHeaders.shift();
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      // excelName:'Merged Insitutions',
      downloadExcelName:'List of Merge Insitutions',
      setHeaderCollumnWidths:[
        { wpx: 80 },//surveyYear
        { wpx: 80 }, //Oldaishecode
        { wpx: 290 },//oldInstitutionName,
        { wpx: 290 },//oldUniversityName or affilliated University
        { wpx: 80 },//state
        { wpx: 88 },// newAisheCode
        { wpx: 210 },//newInstitutionName
        { wpx: 270 },//newUniversityName or affilliated university
        { wpx: 80 },//actionBy
        { wpx: 130 },//actionTime

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

  
  getData(surveyYear: number) {
    this.surveyYear = surveyYear
    this.selectedIndex = 0
  }
  getFilterData(value:any){
    this.searchText='';
    if(value !== 0){
      this.listData =  this.tempList.filter(e=>e.surveyYear === value)
    }else{
      this.listData =  [...this.tempList]
    }
    this.handlePageChange(this.sharedService.page = 1)

  }
  getUserDetails(userId:string){
    this.authService.getUser(userId).pipe(takeUntil(this.unSubscribeSubject)).subscribe({
      next: (res: any) => {
        this.sharedService.viewUser(res.data)
      }, error: (error: any) => {

      }
    })
  }
  back(){
    this.router.navigate(['/aishe/universalsearch']);
  }
}
