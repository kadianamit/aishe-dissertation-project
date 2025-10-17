import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { UpdateViewComponent } from '../update-view/update-view.component';
import { MatDialog } from '@angular/material/dialog';
import { jsPDF } from "jspdf";
import autoTable from 'jspdf-autotable';
@Component({
  selector: 'app-switch',
  templateUrl: './switch.component.html',
  styleUrls: ['./switch.component.scss']
})
export class SwitchComponent implements OnInit {
  switchForm: FormGroup
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
    this.switchForm = this.fb.group({
      backboneLocation: ['', []],
      fastEthernet: ['', []],
      floor: ['', []],
      gigabitPort: ['', []],
      ipAddress: ['', []],
      model: ['', []],
      portNumber: ['', []],
      roomNumber: ['', []],
      serialNumber: ['', []],
      switchHostName: ['', []],
      switchPartNumber: ['', []],
      utpFiber: ['', []],
      version: ['', []],
      id: [0, []]

    })
  }

  ngOnInit(): void {
    this.getSwitch();
  }
  getSwitch() {
    this.authService.getNetworkSwitch().subscribe(res => {
      this.listData = res.data;
      this.tempListData = [...this.listData];
      this.handlePageChange(this.page=1)
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
        return (item.floor?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.roomNumber?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.switchHostName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.ipAddress?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.switchPartNumber?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.serialNumber?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.model?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.backboneLocation?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  addRecord() {
    this.showForm = true;
    this.switchForm.reset();
    this.switchForm.get('id')?.setValue(0);
    this.switchForm.get('id')?.updateValueAndValidity();
  }
  save() {
    this.authService.saveSwitchDetails(this.switchForm.value).subscribe(res => {
      if (res.status === 200) {
        if (this.switchForm.controls['id'].value === 0) {
          this.listData.unshift(res.data);
          this.tempListData = [...this.listData]
          this.sharedService.showSuccess();
        } else {
          const i = this.listData.findIndex((ele: any) => ele.id === this.switchForm.controls['id'].value);
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
    this.switchForm.reset();
    this.switchForm.get('id')?.setValue(0);
    this.switchForm.get('id')?.updateValueAndValidity();
  }
  reset() {
    this.buttonName = 'Save'
    this.switchForm.reset()
    this.switchForm.get('id')?.setValue(0);
    this.switchForm.get('id')?.updateValueAndValidity();
  }
  view(item: any) {
    item['page'] = 'switch';
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
  delete(item:any){
    this.sharedService.delete().subscribe(result => {
      if (result) {
        this.authService.deleteSwitch(item.id).subscribe(res=>{
          if(res.status === 200){
            this.sharedService.showDelete()
            this.getSwitch()
          }
          },err=>{
          
          })
      }
    })
      }
  edit(item: any, i: number) {
    this.showForm = true
    this.buttonName = 'Update'
    this.switchForm.patchValue(item)
  }
  download() {
    let index = 1;
    this.listData.forEach((item) => (
      item['index'] = index++
    ));
    var columns = [{ title: "S.No.", dataKey: "index" }, { title: "Floor", dataKey: "floor" },
    { title: "Room", dataKey: "roomNumber" }, { title: "Switch Host Name", dataKey: "switchHostName" },
    { title: "Ip Address", dataKey: "ipAddress" },
    { title: "Model", dataKey: "model" },
    { title: "Switch Part No.", dataKey: "switchPartNumber" },
    { title: "Fast Ethernet", dataKey: "fastEthernet" },
    { title: "Gigabit Port", dataKey: "gigabitPort" },
    { title: "Serial No", dataKey: "serialNumber" },
    { title: "Version", dataKey: "version" },
    { title: "Backbone", dataKey: "backboneLocation" },
    { title: "Port No", dataKey: "portNumber" },
    { title: "UTP/Fiber", dataKey: "utpFiber" }]
    const doc = new jsPDF({
      orientation: 'landscape',
      unit: 'px',
      format: [1024, 1200],
    });
    doc.text('Switch and Network details', 500, 20);
    // doc.text(surveyYear, 980, 20);
    autoTable(doc, {
      theme: 'grid',
      columns: columns,
      body: this.listData,
      margin: {
        top: 30,
      },
    });

    doc.save('Switch and Network details.pdf');
  }
}
