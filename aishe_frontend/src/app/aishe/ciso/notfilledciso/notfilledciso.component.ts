import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { jsPDF } from "jspdf";
import autoTable from 'jspdf-autotable';
@Component({
  selector: 'app-notfilledciso',
  templateUrl: './notfilledciso.component.html',
  styleUrls: ['./notfilledciso.component.scss']
})
export class NotfilledcisoComponent implements OnInit {
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  listData: Array<any> = [];
  tempListData: Array<any> = [];
  searchText: any;
  instituteFullName: string = '--ALL--';
  instituteSubType: string = '--ALL--'
  instituteListArray: Array<any> = [];
  instituteList: Array<any> = [];
  instituteSubList: Array<any> = [];
  instituteSubArray: Array<any> = [];
  constructor(public authService: AuthService, public sharedService: SharedService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getList()
  }
  getList() {
    this.authService.getNotFilledCISO().subscribe(res => {
      this.listData = res.data;
      this.instituteListArray = this.listData.filter(
        (thing, i, arr) => arr.findIndex(t => t.cisoEmadedPK?.instituteFullName === thing.cisoEmadedPK?.instituteFullName) === i
      );
      this.instituteList = this.instituteListArray.slice()
      this.instituteList.unshift({
        cisoEmadedPK:{
          instituteFullName: '--ALL--'
        }
      })
      this.instituteSubArray = this.listData.filter(
        (thing, i, arr) => arr.findIndex(t => t.instituteSubType === thing.instituteSubType) === i
      );
      this.instituteSubList = this.instituteSubArray.slice()
      this.instituteSubList.unshift({
          instituteSubType: '--ALL--'
        
      })
      this.tempListData = [...this.listData]
      this.handlePageChange(this.page = 1)
    }, err => {

    })
  }
  handlePageChange(event: any) {
    this.page = event
    this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
      this.EndLimit = this.StartLimit + Number(this.pageSize)
    var a = Math.ceil(this.listData.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.listData.length);
    } else {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.listData.length - 1);
    }

  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1)
  }

  async updateResults() {
    this.listData = []
    this.listData = this.searchByValue(this.tempListData);
    this.handlePageChange(this.page = 1)
  }

  searchByValue(userData: any) {
    return userData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.instituteSubType?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.cisoEmadedPK?.aisheCode?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.instituteMainType?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.cisoEmadedPK?.instituteFullName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.address?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  getInstituteByName() {
    this.instituteSubType = '--ALL--'
    if (this.instituteFullName !== '--ALL--') {
      this.listData = this.tempListData.filter((ele: any) => ele.cisoEmadedPK?.instituteFullName?.trim().toLowerCase() === this.instituteFullName?.trim().toLowerCase())
    } else {
      this.listData = [...this.tempListData]
    }
    this.handlePageChange(this.page = 1)
  }
  getInstituteBySub() {
    this.instituteFullName = '--ALL--'
    if (this.instituteSubType !== '--ALL--') {
      this.listData = this.tempListData.filter((ele: any) => ele.instituteSubType?.trim().toLowerCase() === this.instituteSubType?.trim().toLowerCase())
    } else {
      this.listData = [...this.tempListData]
    }
    this.handlePageChange(this.page = 1)
  }
  reload() {
    this.searchText=''
    this.StartLimit = 0;
    this.EndLimit = 25;
    this.pageData = 0;
    this.pageSize = 25;
    this.page = 1;
    this.getList();
  }
  download() {
    let index = 1;
    this.listData.forEach((item) => (
      item['index'] = index++,
      item.agencyCode = item.cisoEmadedPK.aisheCode,
      item.institute = item.cisoEmadedPK.instituteFullName
      ));
    var columns = [{ title: "S.No.", dataKey: "index" }, { title: "Agency Code", dataKey: "agencyCode" },
    { title: "Institute Sub-Type", dataKey: "instituteSubType" }
    , { title: "Institute Main-Type", dataKey: "instituteMainType" },{ title: "Institute Name", dataKey: "institute" },
    { title: "Short Name", dataKey: "shortName" },
    { title: "Address", dataKey: "address" }]
    const doc = new jsPDF({
      orientation: 'landscape',
      unit: 'px',
      format: [1024, 1200],
    });
    doc.text('Not Filled CISO', 500, 20);
    // doc.text(surveyYear, 980, 20);
    autoTable(doc, {
      theme: 'grid',
      columns: columns,
      body: this.listData,
      margin: {
        top: 30,
      },
    });

    doc.save('Not Filled CISO.pdf');
  }
}
