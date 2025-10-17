import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-standalone-to-college',
  templateUrl: './standalone-to-college.component.html',
  styleUrls: ['./standalone-to-college.component.scss']
})
export class StandaloneToCollegeComponent implements OnInit {
  unSubscribeSubject = new Subject()
  listData: Array<any> = [];
  tempList: Array<any> = [];
  listCount:Array<any>=[];
  searchText: string = '';
  tableHeaders = ['SNO','Survey Year' ,'AISHE Code', 'Institute Name','State', 'AISHE Code','College','Action By','Action Time'];
  surveyYearOption:Array<any>=[]
  surveyYear:number=0
  standaloneToCollegeCode: any;
  constructor(public sharedService: SharedService, public authService: AuthService,public router: Router,private route:ActivatedRoute,public  localStore1:EncryptDecrypt) { }

  ngOnInit(): void {
    let standaloneToCollegeCode=this.route.snapshot.paramMap.get('id');
    if(standaloneToCollegeCode){
      this.standaloneToCollegeCode=this.localStore1?.getDecryptedValue(standaloneToCollegeCode)
    }
    this.getMergeInstitutionList();
    this.loadSurveyYear()
  }
  loadSurveyYear() {
    this.authService.getSurveyYear().subscribe((res) => {
      this.surveyYearOption = res;

    }, err => {

    })
  }
  back(){
    this.router.navigate(['/aishe/universalsearch']);
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
          || (item.actionBy?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.actionTime?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.remarks?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.stateName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }
  getMergeInstitutionList() {
    let payload = {
      surveyYear: 0,
      type: 'STANDALONE_TO_COLLEGE'
    }
    this.authService.getInstitutionDetails(payload).pipe(takeUntil(this.unSubscribeSubject)).subscribe({
      next: (res: any) => {
        if (res.masterResponse && res.masterResponse.length) {
          this.listData = res.masterResponse;
          if(this.standaloneToCollegeCode){
            this.listData = this.listData.filter(item => item.oldAisheCode.toLowerCase() === this.standaloneToCollegeCode.toLowerCase());
          }
          this.tempList = [...this.listData]
          this.handlePageChange(this.sharedService.page = 1)
        } else {
          this.listData = []
          this.tempList=[]
        }
      }, error: (error: any) => {

      }
    })
  }
  getUserDetails(userId:string){
    this.authService.getUser(userId).pipe(takeUntil(this.unSubscribeSubject)).subscribe({
      next: (res: any) => {
        this.sharedService.viewUser(res.data)
      }, error: (error: any) => {

      }
    })
  }
  downloadPDF(){
    const tableData = this.listData.map((row, i) => [
      i + 1,
      row.surveyYear,
      row.oldAisheCode,
      row.oldInstitutionName,
      row.stateName,
      row.newAisheCode,
      row.newInstitutionName,
      row.actionBy,
      row.actionTime

    ]);
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      pdfName:'Standalone to College',
      downloadPdfName:'Standalone to College.pdf',
      orientationType:'landscape',
    }
     let a={ 1: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
        2: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
        3: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
        4: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
        5: { fillColor: [228, 245, 243], textColor: [0, 0, 0] },
        6: { fillColor: [228, 245, 243], textColor: [0, 0, 0] },
        7: { fillColor: [228, 245, 243], textColor: [0, 0, 0] },
        8: { fillColor: [228, 245, 243], textColor: [0, 0, 0] }
      
  }
    this.sharedService.downloadPDF(param,a);
  }
  downloadExcel() {
    const tableData = this.listData.map((row, i) => [
      i+1,
      row.surveyYear,
      row.oldAisheCode,
      row.oldInstitutionName,
      row.stateName,
      row.newAisheCode,
      row.newInstitutionName,
      row.actionBy,
      row.actionTime
    ]);
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      downloadExcelName:'Standalone to College',
    }
   this.sharedService.downloadExcel(param)
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
}
