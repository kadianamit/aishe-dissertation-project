import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { GetService } from 'src/app/service/get/get.service';
import { SurveyyearService } from 'src/app/service/reports/surveyyear.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-inactive-standalone',
  templateUrl: './inactive-standalone.component.html',
  styleUrls: ['./inactive-standalone.component.scss']
})
export class InactiveStandaloneComponent implements OnInit {
  unSubscribeSubject = new Subject()
  listData: Array<any> = [];
  tempList: Array<any> = [];
  listCount: Array<any> = [];
  searchText: string = ''
  surveyYear: number = 0
  selectedIndex:number=0;
  surveyYearOption:any;
  filterSurveyYearOption:any;
  tableHeaders = ['SNO','Survey Year', 'AISHE Code', 'Institute Name','State', 'Remark','Action By','Action Time'];
  inactiveStandaloneCode: any;
  constructor(public sharedService: SharedService, public authService: AuthService,private getService: GetService, private surveyyearservice:SurveyyearService,public router: Router,private route:ActivatedRoute,public  localStore1:EncryptDecrypt) { }

  ngOnInit(): void {

    let inactiveStandaloneCode=this.route.snapshot.paramMap.get('id');
    if(inactiveStandaloneCode){
      this.inactiveStandaloneCode=this.localStore1?.getDecryptedValue(inactiveStandaloneCode)
    }
    this.getInactiveStandalone();
    this.loadSurveyYear();
  }
  loadSurveyYear() {
    this.authService.getSurveyYear().subscribe((res) => {
      this.surveyYearOption = res;

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
  getFilterData(value:any){
    this.searchText='';
    if(value !== 0){
      this.listData =  this.tempList.filter(e=>e.surveyYear === value)
    }else{
      this.listData =  [...this.tempList]
    }
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
          || (item.remarks?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.actionBy?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.actionTime?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.stateName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }
  back(){
    this.router.navigate(['/aishe/universalsearch']);
  }
  getInactiveStandalone() {
    let payload = {
      surveyYear: this.surveyYear,
      type: 'INACTIVE_STANDALONE'
    }
    this.authService.getInstitutionDetails(payload).pipe(takeUntil(this.unSubscribeSubject)).subscribe({
      next: (res: any) => {
        if (res.masterResponse && res.masterResponse.length) {
          this.listData = res.masterResponse;
          if(this.inactiveStandaloneCode){
            this.listData=this.listData.filter(item=>item.oldAisheCode.toLowerCase() === this.inactiveStandaloneCode.toLowerCase());
            }
         
          this.tempList = [...this.listData]
          this.handlePageChange(this.sharedService.page = 1)
        } else {
          this.listData = []
          this.tempList = []
        }
      }, error: (error: any) => {

      }
    })
  }
  tabSelected(event: any) {
    if (event.index === 0) {
      this.getInactiveStandalone()
    } else if (event.index === 1) {
      this.surveyYear=0
      this.getInactiveStandaloneCountList()
    }
  }
  getData(surveyYear: number) {
    this.surveyYear = surveyYear
    this.selectedIndex = 0
  }

  getInactiveStandaloneCountList() {
    let payload = {
      surveyYear: 0,
      type: 'INACTIVE_STANDALONE_COUNT'
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
  getSurveyYear(surveyYear:any){
    let splitSurvey = surveyYear.toString().substring(2, 4)
    let intSurvey = parseInt(splitSurvey)
    let a = intSurvey + 1;
    let b = surveyYear + '-' + a;
    return b;
  }
  getUserDetails(userId:string){
    this.authService.getUser(userId).pipe(takeUntil(this.unSubscribeSubject)).subscribe({
      next: (res: any) => {
        this.sharedService.viewUser(res.data)
      }, error: (error: any) => {

      }
    })
  }
  // exportAsXLSX(){
  //   if(!this.surveyYear){
  //     this.sharedService.showError('Please select a survey year');
  //     return
  //   }
  //     let excel="EXCEL"
  //     this.getService.getReport164(this.surveyYear,excel).subscribe((res:any) => {
  //       let byteArrays = res.byteData;
  //       this.downloadAsExcel1(byteArrays,"report164");
  // });
   
    
  // }
  exportAsXLSX() {
    const tableData = this.listData.map((row, i) => [
      // i+1,
      row.surveyYear, 
      row.oldAisheCode, 
      row.oldInstitutionName, 
      row.stateName,
      row.remarks,
      row.actionBy,
      row.actionTime

    ]);
   this.tableHeaders.shift();
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      // excelName:'Merged Insitutions',
      downloadExcelName:'List of Inactive Standalone',
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
  // exportAsPdf(){
  //   if(!this.surveyYear){
  //     this.sharedService.showError('Please select a survey year');
  //     return
  //   }
  //     let pdf="PDF"
  //     this.getService.getReport164(this.surveyYear,pdf).subscribe((res:any) => {
  //       let byteArrays = res.byteData;
  //       this.downloadAsPDF1(byteArrays,"report164");
  // });
   
    
  // }
  exportAsPdf() {
    const tableData = this.listData.map((row, i) => [
      i + 1,
      row.surveyYear, 
      row.oldAisheCode, 
      row.oldInstitutionName, 
      row.stateName,
      row.remarks,
      row.actionBy,
      row.actionTime

    ]);
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      pdfName:'',
      downloadPdfName:'List of Inactive Standalone',
      orientationType:'landscape',
    }
    let a={  1: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    2: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    3: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    4: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    5: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    6: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    7: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    8: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },

  
}
     this.sharedService.downloadPDF(param ,a);
    
  }

}
