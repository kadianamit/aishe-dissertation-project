import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { jsPDF } from "jspdf";
import autoTable from 'jspdf-autotable';
@Component({
  selector: 'app-university-wise-college',
  templateUrl: './university-wise-college.component.html',
  styleUrls: ['./university-wise-college.component.scss']
})
export class UniversityWiseCollegeComponent implements OnInit {
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  StartLimit: number = 0;
  EndLimit: number = 25;
  tempList: any[] = [];
  universityList: any[] = []
  searchText: string = ''
  surveyYear: any;
  surveyYearList: any[] = [];
  currentSurveyYear: any;
  constructor(public sharedService: SharedService, public authService: AuthService) {
    this.surveyYear = this.sharedService.currentSurveyYear;
   }

  ngOnInit() {
    this.getUniversityList(this.surveyYear);
    // this.getSurveyYear();
  }
  getSurveyYear() {
    this.authService.getSurveyYearList().subscribe(res => {

      let surveyYear = res
      surveyYear.forEach((element: any) => {
        if (element <= this.sharedService.currentSurveyfic) {
          var splitSurvey = element.substring(0, 5);
          this.currentSurveyYear = element.substring(0, 4)
          var a = element.substring(7, 9);
          // this.surveyYearList.unshift([splitSurvey + a])
        }
      });
      for (let index = 2011; index < parseInt(this.currentSurveyYear) + 1; index++) {
        let i = index + 1;
        let a = index + '-' + i;
        let b = a.toString().substring(7, 9)
        this.surveyYearList.push({
          surveyYear: index + '-' + b,
          id: index
        })
      }
    }, err => {

    })
  }
  getUniversityList(surveyYear: any) {
    this.authService.getUniversityCount().subscribe(res => {
      if (res && res.length) {
        this.universityList = res;
        this.universityList = this.universityList.filter(e=>e.collegeCount > 0)
        this.universityList.forEach((ele:any,i:number)=>{
          ele['index'] = i+1
        })
        this.tempList = [...this.universityList]
        this.handlePageChange(this.page)
      }
    }, err => {

    })
  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1)
  }
  async updateResults() {
    this.universityList = []
    this.universityList = this.searchByValue(this.tempList);
    this.handlePageChange(this.page = 1)

  }

  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.universityName.trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.collegeCount.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.universityId.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.stateName.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })

  }
  handlePageChange(event: any) {
    this.page = event
    this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
      this.EndLimit = this.StartLimit + Number(this.pageSize)
    var a = Math.ceil(this.universityList.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.universityList.length);
    } else {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.universityList.length - 1);
    }
  }
  openCollegeDetails(item: any) {
    if(item.collegeCount !== 0){
    let x: any = item.universityId.split('-');
    var aisheId = x[1];
    this.authService.collegeDetailsByUnivId(aisheId,this.surveyYear).subscribe(res => {
      if (res.universityCollegeListBean && res.universityCollegeListBean.length){
        res.universityCollegeListBean.forEach((ele:any,i:number)=>{
          ele['index'] = i+1
          ele['aisheCode'] = 'C-' + ele.id
        })
        let array = res.universityCollegeListBean.filter((obj:any)=>obj.isDcf !== 'false')
        this.sharedService.showCollegeDetails(array)
      }
    }, err => {

    })
  }
}
download() {
  var columns = [{ title: "S.No.", dataKey: "index" },{ title: "AISHE CODE", dataKey: "universityId" }, 
  { title: "University Name", dataKey: "universityName" },{ title: "State", dataKey: "stateName" },{title: "College Count", dataKey: "collegeCount"}]
  var doc = new jsPDF({
    orientation: "portrait",
    unit: 'px',
    format: [1024, 1200],
  });
  doc.setTextColor(51, 102, 170);
  doc.setFont("times");
  doc.text('University Wise College Report', 500, 15);

  autoTable(doc, ({
    columns: columns,
    body: this.universityList,
    startY: 20,
    theme: 'grid',
     margin: { top: 30 },
  }))
  
  doc.save('University Wise College Report.pdf');
}
}
