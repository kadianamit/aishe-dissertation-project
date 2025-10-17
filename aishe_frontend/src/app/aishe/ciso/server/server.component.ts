import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { UpdateViewComponent } from '../update-view/update-view.component';
import { MatDialog } from '@angular/material/dialog';
import { jsPDF } from "jspdf";
import autoTable from 'jspdf-autotable';
@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.scss']
})
export class ServerComponent implements OnInit {
  serverForm: FormGroup
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
  constructor(public sharedService: SharedService, public authService: AuthService, public fb: FormBuilder,
    public dialog: MatDialog) {
    this.serverForm = this.fb.group({
      applicationName: ['', []],
      serverType: ['', []],
      operatingSystem: ['', []],
      noOfCpu: ['', []],
      ramSizeInGb: ['', []],
      publicIp: ['', []],
      privateIp: ['', []],
      serverLocation: ['', []],
      id: [0, []]

    })
  }

  ngOnInit(): void {
    this.getServer();
  }
  getServer() {
    this.authService.getServerDetails().subscribe(res => {
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
        return (item.applicationName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.serverType?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.operatingSystem?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.publicIp?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.privateIp?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.serverLocation?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  addRecord() {
    this.showForm = true;
    this.serverForm.reset();
    this.serverForm.get('id')?.setValue(0);
    this.serverForm.get('id')?.updateValueAndValidity();
  }
  save() {
    this.authService.saveServerDetails(this.serverForm.value).subscribe(res => {
      if (res.status === 200) {
        if (this.serverForm.controls['id'].value === 0) {
          this.listData.unshift(res.data);
          this.tempListData = [...this.listData]
          this.sharedService.showSuccess();
        } else {
          const i = this.listData.findIndex((ele: any) => ele.id === this.serverForm.controls['id'].value);
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
    this.serverForm.reset();
    this.serverForm.get('id')?.setValue(0);
    this.serverForm.get('id')?.updateValueAndValidity();
  }
  reset() {
    this.buttonName = 'Save'
    this.serverForm.reset()
    this.serverForm.get('id')?.setValue(0);
    this.serverForm.get('id')?.updateValueAndValidity();
  }
  view(item: any) {
    item['page'] = 'server';
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
        this.authService.deleteServer(item.id).subscribe(res => {
          if (res.status === 200) {
            this.sharedService.showDelete()
            this.getServer()
          }
        }, err => {
    
        })
      }
    })
  }
  edit(item: any, i: number) {
    this.showForm = true
    this.buttonName = 'Update'
    this.serverForm.patchValue(item)
  }
  download() {
    let index = 1;
    this.listData.forEach((item) => (
      item['index'] = index++
    ));
    var columns = [{ title: "S.No.", dataKey: "index" }, { title: "Application Name", dataKey: "applicationName" },
    { title: "Server Type(Web/DB)", dataKey: "serverType" }, { title: "OS", dataKey: "operatingSystem" },
    { title: "RAM in GB", dataKey: "ramSizeInGb" },
    { title: "Public IP", dataKey: "publicIp" },
    { title: "Private IP", dataKey: "privateIp" },
    { title: "Server Location", dataKey: "serverLocation" }]
    const doc = new jsPDF({
      orientation: 'landscape',
      unit: 'px',
      format: [1024, 1200],
    });
    doc.text('Server Details', 500, 20);
    // doc.text(surveyYear, 980, 20);
    autoTable(doc, {
      theme: 'grid',
      columns: columns,
      body: this.listData,
      margin: {
        top: 30,
      },
    });

    doc.save('Server Details.pdf');
  }
}
