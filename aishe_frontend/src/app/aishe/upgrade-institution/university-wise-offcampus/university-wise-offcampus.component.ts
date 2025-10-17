import { Component, OnInit } from '@angular/core';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-university-wise-offcampus',
  templateUrl: './university-wise-offcampus.component.html',
  styleUrls: ['./university-wise-offcampus.component.scss'],
})
export class UniversityWiseOffcampusComponent implements OnInit {
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  StartLimit: number = 0;
  EndLimit: number = 25;
  tempList: any[] = [];
  universityList: any[] = [];
  searchText: string = '';
  surveyYear: any;
  surveyYearList: any[] = [];
  currentSurveyYear: any;
  constructor(
    public sharedService: SharedService,
    public authService: AuthService
  ) {
    this.surveyYear = this.sharedService.currentSurveyYear;
  }

  ngOnInit() {
    // this.getUniversityList(this.surveyYear);
    //this.getSurveyYear();
    this.surveyYearList = ['2022-23', '2023-24','2024-25'];
    this.surveyYear = this.sharedService.currentSurveyfiscal
    this.getUniversityList(this.surveyYear);
  }
  getSurveyYear() {
    this.authService.getSurveyYearList().subscribe(
      (res) => {
        this.surveyYearList = ['2022-2023', ...res.sort().slice(0, 1)];
      },
      (err) => { }
    );
  }
  getUniversityList(surveyYear: any) {
    const years = surveyYear.split('-')[0];
    let payload = {
      surveyYear: +years,
      //aisheCode:aisheCode,
      responseType: 'COUNT',
    };
    this.authService.getUniversityWiseOffCampus(payload).subscribe(
      (res) => {
        if (res.masterResponse && res.masterResponse.length) {
          console.log(res.masterResponse);
          this.universityList = res.masterResponse.sort((a: any, b: any) =>
            a.aisheCode.localeCompare(b.aisheCode)
          );

          //  this.universityList = res.masterResponse.sort((a:any, b:any) => a.aisheCode - b.aisheCode);
          // this.universityList = this.universityList.filter(e=>e.collegeCount > 0)
          this.universityList.forEach((ele: any, i: number) => {
            ele['index'] = i + 1;
          });
          this.tempList = [...this.universityList];
          this.handlePageChange(this.page);
        }
      },
      (err) => { }
    );
  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange((this.page = 1));
  }
  async updateResults() {
    this.universityList = [];
    this.universityList = this.searchByValue(this.tempList);
    this.handlePageChange((this.page = 1));
  }

  searchByValue(items: any) {
    return (
      items?.filter((item: any) => {
        if (this.searchText.trim() === '') {
          return true;
        } else {
          // return (item?.universityName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          //   || (item?.count.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          //   || (item?.aisheCode.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          //   || (item?.state?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          //   || (item?.district?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          return (
            (item?.universityName?.trim().toLowerCase().includes(this.searchText) ?? false) ||
            (item?.count?.toString().toLowerCase().includes(this.searchText) ?? false) ||
            (item?.aisheCode?.toString().toLowerCase().includes(this.searchText) ??false) ||
            (item?.state?.toString().toLowerCase().includes(this.searchText) ?? false) ||
            (item?.district?.toString().toLowerCase().includes(this.searchText) ?? false)
          );
        }
      }) ?? []
    );
  }
  handlePageChange(event: any) {
    this.page = event;
    (this.StartLimit = (this.page - 1) * Number(this.pageSize)),
      (this.EndLimit = this.StartLimit + Number(this.pageSize));
    var a = Math.ceil(this.universityList.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(
        this.StartLimit + Number(this.pageSize),
        this.universityList.length
      );
    } else {
      this.pageData = Math.min(
        this.StartLimit + Number(this.pageSize),
        this.universityList.length - 1
      );
    }
  }
  openCollegeDetails(item: any) {
    if (item.count !== 0) {
      let x: any = item.aisheCode.split('-');
      var aisheId = x[1];
      const yeas = this.surveyYear.split('-')[0];
      let pay = {
        surveyYear: +yeas,
        aisheCode: item.aisheCode,
        responseType: 'LIST',
      };
      this.authService.getUniversityWiseOffCampus(pay).subscribe(
        (res) => {
          if (res.masterResponse && res.masterResponse.length) {
            res.masterResponse.forEach((ele: any, i: number) => {
              ele['index'] = i + 1;
            });
            let array = res.masterResponse.filter(
              (obj: any) => obj.isDcf !== 'false'
            );
            let arr = [array, 'offcampus1'];
            this.sharedService.showCollegeDetails(arr);
          }
        },
        (err) => { }
      );
    }
  }
  download() {
    var columns = [
      { title: 'S.No.', dataKey: 'index' },
      { title: 'AISHE CODE', dataKey: 'aisheCode' },
      { title: 'University Name', dataKey: 'universityName' },
      { title: 'University Type', dataKey: 'type' },
      { title: 'State', dataKey: 'state' },
      { title: 'District', dataKey: 'district' },
      { title: 'Off-Campus Count', dataKey: 'count' },
    ];
    var doc = new jsPDF({
      orientation: 'portrait',
      unit: 'px',
      format: [1024, 1200],
    });
    doc.setTextColor(51, 102, 170);
    doc.setFont('times');
    doc.text('University Wise Off-Campus Report', 500, 15);

    autoTable(doc, {
      columns: columns,
      body: this.universityList,
      startY: 20,
      theme: 'grid',
      margin: { top: 30 },
    });

    doc.save('University Wise Off-Campus Report.pdf');
  }
}
