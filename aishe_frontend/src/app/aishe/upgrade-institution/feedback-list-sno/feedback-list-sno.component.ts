import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/service/format-datepicker';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-feedback-list-sno',
  templateUrl: './feedback-list-sno.component.html',
  styleUrls: ['./feedback-list-sno.component.scss'],
   providers: [
      { provide: DateAdapter, useClass: AppDateAdapter },
      { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
    ]
})
export class FeedbackListSnoComponent implements OnInit {
  feedbackList: any;
  searchText: string = '';
  tempList: Array<any> = [];
  menu: any;
  menuList: any;
  submenu: any;
  subMenuList: any = [];
  page: any;
  pageList: any = [];
  subMenuListDistinct: any = [];
  maxDate1: any
  maxDate2: any;
  fromDate: any;
  minDate: any;
  toDate: any;
  pageListDistinct: any;
  msg:string=''
  snoList:any=[];
  HEIList:any=[];
  selectedIndex=0;
  filterType: any=2;
  statusList=[{'id':1,'status':'Read'},{'id':2,'status':'Un-Read'},{'id':3,'status':'Flag'}]
  statusValue:any=2;
  
  constructor(public sharedService: SharedService, public router: Router, public authService: AuthService, 
    private route: ActivatedRoute, public localStore1: EncryptDecrypt, public localService: LocalserviceService) {

  }

  ngOnInit(): void {
    this.getFeedBack();
    const currentYear = new Date().getFullYear();
    this.maxDate1 = new Date(currentYear, 10, 1);
  }
  getFeedBack() {
    
    this.feedbackList = []
    let currentSurveyYear = this.sharedService.currentSurveyYear
    let aisheCode = '';
    let payload = {

    }
    this.authService.getFeedBackList(aisheCode, currentSurveyYear).subscribe(res => {
      if (res.status == 200) {
        this.feedbackList=[];
        this.snoList=[];
        this.feedbackList = res.data;
        this.feedbackList.map((e: any) => {
          
            let msg = ''
            if(e.roleId?.toString() === this.sharedService.role['State Nodal Officer']){
              e['msg'] = 'SNO' + '(' + e.state?.name + ')'
              this.snoList.push(e);
            }
          //   if (res.roleId?.toString() === this.sharedService.role['State Nodal Officer']) {
          //     res['msg'] = 'SNO' + '(' + res.state?.name + ')'
          //     this.snoList.push(res);
          //   }
          //   if(res.roleId?.toString() === this.sharedService.role['SysAdmin']){
          //     res['msg'] = 'SysAdmin'
          //   }if(res.roleId?.toString() === this.sharedService.role['Ministry of Education']){
          //     res['msg'] = 'MOE'
          // }
        })
        this.statusValue=this.filterType;
        this.feedbackList=this.snoList;
        this.feedbackList=this.feedbackList.filter((data:any)=>data.status.id==this.filterType);
        this.tempList = [...this.feedbackList];
        this.menuList = this.feedbackList.map((res: any) => {
          return { 'menu': res.menu, 'submenu': res.submenu, 'page': res.page };
        });
        this.menuList = this.menuList.filter((ele: any) => ele.menu != '');
        this.menuList = this.menuList.filter(
          (item: any, index: any, self: any) =>
            index === self.findIndex((t: any) => t.menu === item.menu)
        );
        this.handlePageChange(this.sharedService.page = 1)
      }
    }, err => {

    })
  }
  onChange(event: any) {
    this.sharedService.pageSize = parseInt(event);
    this.handlePageChange(this.sharedService.page = 1);
  }
  handlePageChange(event: any) {
    this.sharedService.page = event;
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize);
    var a = Math.ceil(this.feedbackList.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.feedbackList.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.feedbackList.length - 1);
    }
  }
  async updateResults() {
    this.feedbackList = []
    this.feedbackList = this.searchByValue(this.tempList);
    this.handlePageChange(this.sharedService.page = 1)
  }
  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        // item.name.trim();
        return (item.aishe_code?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.institutionName?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.menu?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.submenu?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.page?.includes(this.searchText.trim().toLowerCase()))
          || (item.remarks?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.dateTime?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.msg?.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }
  getFilterData(value: any) {
    this.searchText = '';
    this.submenu = null;
    this.page = null;
    this.subMenuList = [];
    this.pageList = [];
    if (value !== 0) {
      this.feedbackList = this.tempList.filter(e => e.menu === value)
    } else {
      this.feedbackList = [...this.tempList]
    }
    this.handlePageChange(this.sharedService.page = 1)
    this.tempList.map((res: any) => {
      if (res.menu === value) {
        this.subMenuList.push(res)
      }
    })
    this.subMenuListDistinct = this.subMenuList.filter(
      (item: any, index: any, self: any) =>
        index === self.findIndex((t: any) => t.submenu === item.submenu)
    );

  }
  getFilterData1(value: any) {
    this.searchText = '';
    this.pageList = [];
    if (value !== 0) {
      this.feedbackList = this.subMenuList.filter((e: any) => e.submenu === value)
    } else {
      this.feedbackList = [...this.subMenuList]
    }
    this.handlePageChange(this.sharedService.page = 1)
    this.subMenuList.map((res: any) => {
      if (res.submenu === value) {
        this.pageList.push(res)
      }
    })
    this.pageListDistinct = this.pageList.filter(
      (item: any, index: any, self: any) =>
        index === self.findIndex((t: any) => t.page === item.page)
    );

  }
  getFilterData2(value: any) {
    if (value !== 0) {
      this.feedbackList = this.pageList.filter((e: any) => e.page === value)
    } else {
      this.feedbackList = [...this.pageList]
    }
    this.handlePageChange(this.sharedService.page = 1);

  }
  handleToDate(event: any) {
    const m = event.value;
    if (m) {
      var year = m.getFullYear();
      var month = m.getMonth();
      var day = m.getDate();
      this.minDate = new Date(year, month, day);
    }
    if (this.minDate !== 0) {
      // this.feedbackList = this.tempList.filter(e => e.dateTime >= this.formatDate(this.minDate))
      this.feedbackList = this.tempList.filter((e: any) => {
        const itemDateTime = this.parseDateTime(e.dateTime);
        const minDate = this.parseDateTime(this.formatDate(this.minDate));
        // const maxDate = this.parseDateTime(this.maxDate2);

        return itemDateTime > minDate;
      });

    } else {
      this.feedbackList = [...this.tempList]
    }
    this.handlePageChange(this.sharedService.page = 1)
    this.clearEndDate();
  }
  handleToDateMax(event: any) {
    const m = event.value;
    if (m) {
      var year = m.getFullYear();
      var month = m.getMonth();
      var day = m.getDate();
      this.maxDate2 = new Date(year, month, day);
      if (this.maxDate2 !== 0) {
        // this.feedbackList = this.tempList.filter((e:any) => e.dateTime < this.formatDate(this.maxDate2)  && this.formatDate(this.minDate)< e.dateTime )
        this.feedbackList = this.tempList.filter((e: any) => {
          const itemDateTime = this.parseDateTime(e.dateTime);
          const minDate = this.parseDateTime(this.formatDate(this.minDate));
          const maxDate = this.parseDateTime(this.formatDate(this.maxDate2));

          return itemDateTime < maxDate && minDate < itemDateTime;
        });

      } else {
        this.feedbackList = [...this.tempList]
      }
      this.handlePageChange(this.sharedService.page = 1)
    }
  }
  clearStartDate() {
    this.fromDate = null;
  }
  clearEndDate() {
    this.toDate = null
  }
  formatDate(dateString: string): string {
    const date = new Date(dateString);

    // Extract date components
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are 0-indexed
    const year = date.getFullYear();

    // Extract time components
    let hours = date.getHours();
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');

    // Determine AM/PM
    const ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12 || 12; // Convert to 12-hour format and handle 0 as 12

    const formattedTime = `${String(hours).padStart(2, '0')}:${minutes}:${seconds} ${ampm}`;
    const formattedDate = `${day}-${month}-${year} ${formattedTime}`;

    return formattedDate;
  }
  reset() {

    this.feedbackList = [...this.tempList];
    this.searchText = '';
    this.submenu = null;
    this.page = null;
    this.subMenuList = [];
    this.pageList = [];
    this.menu = '';
    this.pageListDistinct = [];
    this.subMenuListDistinct = [];
    this.clearEndDate();
    this.clearStartDate();
  }
  parseDateTime(dateTime: string): Date {
    // Convert custom date-time string to standard Date object
    const [date, time, period] = dateTime.split(' ');
    const [day, month, year] = date.split('-').map(Number);
    const [hours, minutes, seconds] = time.split(':').map(Number);

    // Adjust hours for AM/PM
    let adjustedHours = hours;
    if (period === 'PM' && hours < 12) {
      adjustedHours += 12;
    } else if (period === 'AM' && hours === 12) {
      adjustedHours = 0;
    }

    return new Date(year, month - 1, day, adjustedHours, minutes, seconds);
  }
  tabSelected(event: any) {
    if (event.index === 0) {
      // this.feedbackList=[];
      // this.filterType=2;
      // this.getFeedBack(); 
      this.showList(1);  //unread
    } else if (event.index === 1) {
      // this.HEIList=[];
      // this.filterType=2;
      // this.getFeedBack();
      this.showList(2); //read

    }else{
      // this.filterType=1;
      this.showList(3);//flag
    }
  }
  changeStatus(Obj:any,status:any){

   Obj.status=status;
   if(Obj.roleId==6){
    Obj.stateCode=Obj.state.stateCode;
   }
   delete Obj.state;
   this.authService.getFeedBackPost(Obj).subscribe((res)=>{
    if(res.status==200){
      this.feedbackList = this.feedbackList.filter((item:any) => item.id !== Obj.id);
      this.handlePageChange(this.sharedService.page)
      // this.getFeedBack();
    }
   })
  }
  showList(type:any){
    this.fromDate=null;
    this.toDate=null;
   if(type==1){
      this.filterType=2;
      this.getFeedBack();
   }
   if(type==2){
      this.filterType=1;
      this.getFeedBack();
   }
   if(type==3){
       this.filterType=3;
       this.getFeedBack();
   }
  }
}