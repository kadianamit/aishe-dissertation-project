import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SharedService } from 'src/app/shared/shared.service';
import { jsPDF } from 'jspdf';
import autoTable from 'jspdf-autotable';
@Component({
  selector: 'app-college-details',
  templateUrl: './college-details.component.html',
  styleUrls: ['./college-details.component.scss'],
})
export class CollegeDetailsComponent implements OnInit {
  cancelButtonText = 'Close';
  searchText: string = '';
  collegeList: any[] = [];
  tempList: any = [];
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  StartLimit: number = 0;
  EndLimit: number = 25;
  offCampus: string = '';
  constructor(
    public dialogRef: MatDialogRef<CollegeDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any,
    public sharedService: SharedService
  ) {
    this.offCampus = this.element[1];
    if (this.offCampus === 'offcampus1') {
      this.collegeList = this.element[0];
      this.tempList = [...this.element[0]];
      this.handlePageChange((this.page = 1));
    } else {
      this.collegeList = this.element;
      this.tempList = [...this.element];
      this.handlePageChange((this.page = 1));
    }
  }

  ngOnInit(): void {
    this.offCampus = this.element[1];
  }

  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange((this.page = 1));
  }
  async updateResults(data: any) {
    if (data==='offcampus1') {
      this.collegeList = [];
      this.collegeList = this.searchByValueOff(this.tempList);
      this.handlePageChange((this.page = 1));
    } else {
      this.collegeList = [];
      this.collegeList = this.searchByValue(this.tempList);
      this.handlePageChange((this.page = 1));
    }
  }
  searchByValueOff(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (
          item.offCampusAisheCode
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.offCampusName
            .toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.state
            .toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.district
            .toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.aisheCode
            .toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase())
        );
      }
    });
  }
  searchByValue(items: any) {
    return items.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (
          item.collegeName
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.id
            .toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.stateName
            .toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.districtName
            .toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.aisheCode
            .toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase())
        );
      }
    });
  }
  handlePageChange(event: any) {
    this.page = event;
    (this.StartLimit = (this.page - 1) * Number(this.pageSize)),
      (this.EndLimit = this.StartLimit + Number(this.pageSize));
    var a = Math.ceil(this.collegeList.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(
        this.StartLimit + Number(this.pageSize),
        this.collegeList.length
      );
    } else {
      this.pageData = Math.min(
        this.StartLimit + Number(this.pageSize),
        this.collegeList.length - 1
      );
    }
  }
  download() {
    if (this.offCampus === 'offcampus1') {
      var columns = [
        { title: 'S.No.', dataKey: 'index' },
        { title: 'AISHE CODE', dataKey: 'aisheCode' },
        { title: 'Institution Name', dataKey: 'universityName' },
        { title: 'Institution Type', dataKey: 'type' },
        { title: 'State', dataKey: 'state' },
        { title: 'District', dataKey: 'district' },
      ];
    } else {
      var columns = [
        { title: 'S.No.', dataKey: 'index' },
        { title: 'AISHE CODE', dataKey: 'aisheCode' },
        { title: 'Institution Name', dataKey: 'collegeName' },
        { title: 'Affiliated University', dataKey: 'universityName' },
        { title: 'Institution Type', dataKey: 'collegeType' },
        { title: 'State', dataKey: 'stateName' },
        { title: 'District', dataKey: 'districtName' },
      ];
    }
    var doc = new jsPDF({
      orientation: 'portrait',
      unit: 'px',
      format: [1024, 1200],
    });
    doc.setTextColor(51, 102, 170);
    doc.setFont('times');
    doc.text('Off-Campus', 500, 15);

    autoTable(doc, {
      columns: columns,
      body: this.collegeList,
      startY: 20,
      theme: 'grid',
      margin: { top: 30 },
      // didDrawPage: function (data: any) {
      //   doc.text((data.pageCount).toString(), pageWidth / 2, pageHeight - 10, { align: 'center' });
      // }
    });

    doc.save('Off-Campus.pdf');
  }
}
