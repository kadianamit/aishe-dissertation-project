import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { UpdateViewComponent } from '../update-view/update-view.component';
import { MatDialog } from '@angular/material/dialog';
import { jsPDF } from "jspdf";
import autoTable from 'jspdf-autotable';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/service/format-datepicker';
@Component({
  selector: 'app-system',
  templateUrl: './system.component.html',
  styleUrls: ['./system.component.scss'],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ],
})
export class SystemComponent implements OnInit {
  systemForm: FormGroup
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  listData: Array<any> = [];
  tempListData: Array<any> = [];
  searchText: any;
  showForm: boolean = false
  buttonName: string = 'Save'
  minDate: any
  maxDate: any
  constructor(public sharedService: SharedService, public authService: AuthService, public fb: FormBuilder,
    public dialog: MatDialog) {
    this.systemForm = this.fb.group({
      userName: ['', []],
      designation: ['', []],
      section: ['', []],
      roomNumber: ['', []],
      operatingSystem: ['', []],
      computerModelNumber: ['', []],
      configuration: ['', []],
      cpuSerialNumber: ['', []],
      lifeAsOn_30_06_2022_inYears: {disabled:true,value:''},
      id: [0, []],
      warrantyEndDate: ['', []],
      warrantyStartDate: ['', []],
    })
  }

  ngOnInit(): void {
    this.getSystem();
    this.maxDate = new Date(2022, 6, 30)
  }
  getSystem() {
    this.authService.getSystemDetails().subscribe(res => {
      this.listData = res.data;
      this.tempListData = [...this.listData];
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
        return (item.userName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.designation?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.section?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.roomNumber?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.computerModelNumber?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.cpuSerialNumber?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.operatingSystem?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.warrantyStartDate?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.warrantyEndDate?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.lifeAsOn_30_06_2022_inYears?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  addRecord() {
    this.showForm = true;
    this.systemForm.reset()
    this.systemForm.get('id')?.setValue(0);
    this.systemForm.get('id')?.updateValueAndValidity();
  }
  save() {
    var fromDate = ''
    var toDate = ''
    if (this.systemForm.controls['warrantyStartDate'].value !== null || this.systemForm.controls['warrantyStartDate'].value !== '') {
      let day: string = this.systemForm.controls['warrantyStartDate'].value.getDate().toString();
      day = +day < 10 ? '0' + day : day;
      let month: string = (this.systemForm.controls['warrantyStartDate'].value.getMonth() + 1).toString();
      month = +month < 10 ? '0' + month : month;
      let year = this.systemForm.controls['warrantyStartDate'].value.getFullYear();
      fromDate = `${day}-${month}-${year}`;
    }

    if (this.systemForm.controls['warrantyEndDate'].value !== null || this.systemForm.controls['warrantyEndDate'].value !== '') {
      let day: string = this.systemForm.controls['warrantyEndDate'].value.getDate().toString();
      day = +day < 10 ? '0' + day : day;
      let month: string = (this.systemForm.controls['warrantyEndDate'].value.getMonth() + 1).toString();
      month = +month < 10 ? '0' + month : month;
      let year = this.systemForm.controls['warrantyEndDate'].value.getFullYear();
      toDate = `${day}-${month}-${year}`;
    }
    let payload = {
      userName: this.systemForm.controls['userName'].value,
      designation: this.systemForm.controls['designation'].value,
      section: this.systemForm.controls['section'].value,
      roomNumber: this.systemForm.controls['roomNumber'].value,
      configuration: this.systemForm.controls['configuration'].value,
      operatingSystem: this.systemForm.controls['operatingSystem'].value,
      computerModelNumber: this.systemForm.controls['computerModelNumber'].value,
      cpuSerialNumber: this.systemForm.controls['cpuSerialNumber'].value,
      lifeAsOn_30_06_2022_inYears: this.systemForm.controls['lifeAsOn_30_06_2022_inYears'].value,
      id: this.systemForm.controls['id'].value,
      warrantyEndDate: toDate,
      warrantyStartDate: fromDate,
    }
    this.authService.saveSystemDetails(payload).subscribe(res => {
      if (res.status === 200) {
        if (this.systemForm.controls['id'].value === 0) {
          this.listData.unshift(res.data);
          this.tempListData = [...this.listData]
          this.sharedService.showSuccess();
        } else {
          const i = this.listData.findIndex((ele: any) => ele.id === this.systemForm.controls['id'].value);
          this.listData[i] = res.data
          this.sharedService.showUpdate()
        }
        this.reset();
        this.handlePageChange(this.page)
      }
    }, err => {

    })
  }
  close() {
    this.buttonName = 'Save'
    this.showForm = false;
    this.systemForm.reset();
    this.systemForm.get('id')?.setValue(0);
    this.systemForm.get('id')?.updateValueAndValidity();
  }
  reset() {
    this.buttonName = 'Save'
    this.systemForm.reset()
    this.systemForm.get('id')?.setValue(0);
    this.systemForm.get('id')?.updateValueAndValidity();
  }
  view(item: any) {
    item['page'] = 'system';
    let dialogRef = this.dialog.open(UpdateViewComponent, {
      width: '60%',
      height: '80%',
      data: { data: item, list: this.listData },
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
      }
    });
  }
  delete(item: any) {
    this.sharedService.delete().subscribe(result => {
      if (result) {
        this.authService.deleteSystem(item.id).subscribe(res => {
          if (res.status === 200) {
            this.sharedService.showDelete()
            this.getSystem()
          }
        }, err => {
    
        })
      }
    })
  }
  edit(item: any, i: number) {
    this.showForm = true
    this.systemForm.patchValue(item);
    if (item.warrantyStartDate !== null) {
      var dateAsString = item.warrantyStartDate;
      let split_dateAsString = dateAsString.split('-')
      let final_format = new Date(`${split_dateAsString[2]}-${split_dateAsString[1]}-${split_dateAsString[0]}`);
      this.systemForm.controls['warrantyStartDate'].setValue(final_format);
      var year = final_format.getFullYear();
      var month = final_format.getMonth();
      var day = final_format.getDate();
      this.minDate = new Date(year, month, day);
    }
    if (item.warrantyEndDate !== null) {
      var dateAsString = item.warrantyEndDate;
      let split_dateAsString = dateAsString.split('-')
      let final_format = new Date(`${split_dateAsString[2]}-${split_dateAsString[1]}-${split_dateAsString[0]}`);
      this.systemForm.controls['warrantyEndDate'].setValue(final_format);
    }

    this.buttonName = 'Update'
  }
  download() {
    let index = 1;
    this.listData.forEach((item) => (
      item['index'] = index++
    ));
    var columns = [{ title: "S.No.", dataKey: "index" }, { title: "User Name", dataKey: "userName" },
    { title: "Designation", dataKey: "designation" }, { title: "Section", dataKey: "section" },
    { title: "Room Number", dataKey: "roomNumber" },
    { title: "Computer Model No.", dataKey: "computerModelNumber" },
    { title: "Operating System", dataKey: "operatingSystem" },
    { title: "CPU Serial Number", dataKey: "cpuSerialNumber" },
    { title: "Warranty StartDate", dataKey: "warrantyStartDate" },
    { title: "Warranty EndDate", dataKey: "warrantyEndDate" },
    { title: "Life as on 30/06/2022(in Years)", dataKey: "lifeAsOn_30_06_2022_inYears" }]
    const doc = new jsPDF({
      orientation: 'landscape',
      unit: 'px',
      format: [1024, 1200],
    });
    doc.text('System Details', 500, 20);
    // doc.text(surveyYear, 980, 20);
    autoTable(doc, {
      theme: 'grid',
      columns: columns,
      body: this.listData,
      margin: {
        top: 30,
      },
    });

    doc.save('System Details.pdf');
  }
  handleToDate(event: any) {
    const m = event.value;
    if (m) {
      var year = m.getFullYear();
      var month = m.getMonth();
      var day = m.getDate();
      this.minDate = new Date(year, month, day);
      let finalYear = 2022 - year;
      let finalMonth = 6 - (month+1);

      this.systemForm.get('lifeAsOn_30_06_2022_inYears')?.setValue(finalYear + '.' + Math.abs(finalMonth))
    }
  }
  
}
