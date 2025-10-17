import { ViewportScroller } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';
import { UpdateViewComponent } from '../update-view/update-view.component';
import { MatDialog } from '@angular/material/dialog';
import { jsPDF } from "jspdf";
import autoTable from 'jspdf-autotable';
@Component({
  selector: 'app-agency-list',
  templateUrl: './agency-list.component.html',
  styleUrls: ['./agency-list.component.scss']
})
export class AgencyListComponent implements OnInit {
  agencyForm: FormGroup
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  listData: Array<any> = [];
  tempListData: Array<any> = [];
  searchText: any;
  instituteFullName: string = '--ALL--';
  instituteMainType: string = '--ALL--'
  instituteSubType: string = '--ALL--'
  instituteListArray: Array<any> = [];
  instituteList: Array<any> = [];
  instituteMainList: Array<any> = [];
  instituteMainArray: Array<any> = [];
  instituteSubList: Array<any> = [];
  instituteSubArray: Array<any> = [];
  showForm: boolean = false;
  buttonName: string = 'Save';
  isFormInvalid: boolean = false;
  agencyCode: any;
  addUpdateIndex: any = null;
  tempIndex: any = null;
  stateList:Array<any>=[];
  variables:Array<any>=[]
  constructor(private authService: AuthService, public sharedService: SharedService, private fb: FormBuilder,
    public errorMatcher: CustomErrorStateMatcher, public viewportScroller: ViewportScroller, public dialog: MatDialog) {
    this.agencyForm = this.fb.group({
      address: ['', [Validators.required]],
      id: 0,
      instituteMainType: ['', [Validators.required]],
      instituteSubType: ['', [Validators.required]],
      shortName: ['', [Validators.required]],
      aisheCode: ['', [Validators.required]],
      instituteFullName: ['', [Validators.required]],
      stateCode:['',[Validators.required]]
    })
  }

  ngOnInit(): void {
    this.getList();
    this.getState();
  }
  getList() {
    this.instituteFullName = '--ALL--';
    this.instituteMainType = '--ALL--';
    this.instituteSubType = '--ALL--'
    this.authService.getAgencyList().subscribe(res => {
      this.listData = res.data;
      this.instituteListArray = this.listData.filter(
        (thing, i, arr) => arr.findIndex(t => t.cisoEmadedPK?.instituteFullName === thing.cisoEmadedPK?.instituteFullName) === i
      );
      this.instituteList = this.instituteListArray.slice()
      this.instituteList.unshift({
        cisoEmadedPK: {
          instituteFullName: '--ALL--'
        }
      })
      this.instituteMainArray = this.listData.filter(
        (thing, i, arr) => arr.findIndex(t => t.instituteMainType.trim() === thing.instituteMainType.trim()) === i
      );
      this.instituteMainList = this.instituteMainArray.slice()
      this.instituteMainList.unshift({
        instituteMainType: '--ALL--'

      })
      this.getSubArray()
      
      this.tempListData = [...this.listData]
      this.handlePageChange(this.page = 1)
    }, err => {

    })
  }
  getSubArray(){
    this.instituteSubList=[];
    this.instituteSubArray = this.listData.filter(
      (thing, i, arr) => arr.findIndex(t => t.instituteSubType.trim() === thing.instituteSubType.trim()) === i
    );
    this.instituteSubList = this.instituteSubArray.slice()
    this.instituteSubList.unshift({
      instituteSubType: '--ALL--'

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
          || (item.stateName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  getInstituteByName() {
    this.instituteMainType = '--ALL--'
    if (this.instituteFullName !== '--ALL--') {
      this.listData = this.tempListData.filter((ele: any) => ele.cisoEmadedPK?.instituteFullName?.trim().toLowerCase() === this.instituteFullName?.trim().toLowerCase())
    } else {
      this.listData = [...this.tempListData]
    }
    this.handlePageChange(this.page = 1)
  }
  getInstituteByMain() {
    this.instituteFullName = '--ALL--'
    if (this.instituteMainType !== '--ALL--') {
      this.listData = this.tempListData.filter((ele: any) => ele.instituteMainType?.trim().toLowerCase() === this.instituteMainType?.trim().toLowerCase())
    } else {
      this.listData = [...this.tempListData]
    }
    this.getSubArray();
    this.handlePageChange(this.page = 1)
  }
  getInstituteBySub() {
    this.instituteFullName = '--ALL--'
    if (this.instituteSubType !== '--ALL--') {
      this.listData = this.tempListData.filter((ele: any) => ele.instituteSubType?.trim().toLowerCase() === this.instituteSubType?.trim().toLowerCase())
      this.handlePageChange(this.page = 1)
    } else {
     this.getInstituteByMain();
    }
  }
  reload() {
    this.searchText = ''
    this.StartLimit = 0;
    this.EndLimit = 25;
    this.pageData = 0;
    this.pageSize = 25;
    this.page = 1;
    this.getList();
  }
  save(data: any) {
    if (this.agencyForm.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true
      return;
    } else {
      this.isFormInvalid = false
    }
    var count = 0
    for (let index = 0; index < this.listData.length; index++) {
      if (this.tempIndex !== index) {
        if (this.listData[index].cisoEmadedPK?.aisheCode === this.agencyForm.controls['aisheCode'].value.toUpperCase() && this.listData[index].cisoEmadedPK?.instituteFullName === this.agencyForm.controls['instituteFullName'].value) {
          count++
        }
      }
    }
    if (count !== 0) {
      this.sharedService.showValidationMessage('Duplicate record not allowed !!!');
      return;
    }
    let payload = {
      "address": data.address,
      "cisoEmadedPK": {
        "aisheCode": data.aisheCode,
        "instituteFullName": data.instituteFullName
      },
      "id": this.agencyForm.controls['id'].value,
      "instituteMainType": data.instituteMainType,
      "instituteSubType": data.instituteSubType,
      "matchedStatus": data.matchedStatus,
      "shortName": data.shortName,
      "stateCode":data.stateCode
    }
    this.authService.saveAgency(payload).subscribe(res => {
      if (res.status === 200) {
        if(this.buttonName === 'Save'){
          this.sharedService.showSuccess();
        }else{
          this.sharedService.showUpdate();
        }
        this.getList();
        this.reset();
      }
    })
  }
  close() {
    this.buttonName = 'Save'
    this.showForm = false;
    this.agencyForm.reset();
    this.addUpdateIndex = null;
    this.tempIndex = null
  }
  reset() {
    this.buttonName = 'Save'
    this.addUpdateIndex = null;
    this.tempIndex = null
    this.agencyForm.reset();
  }
  edit(element: any, i: number) {
    this.viewportScroller.scrollToPosition([0, 0]);
    this.addUpdateIndex = i;
    this.tempIndex = i + this.StartLimit;
    this.buttonName = 'Update'
    this.showForm = true
    this.agencyCode = element.aisheCode
    this.agencyForm.get('aisheCode')?.setValue(element.cisoEmadedPK.aisheCode);
    this.agencyForm.get('instituteFullName')?.setValue(element.cisoEmadedPK.instituteFullName);
    this.agencyForm.get('instituteMainType')?.setValue(element.instituteMainType);
    this.agencyForm.get('instituteSubType')?.setValue(element.instituteSubType);
    this.agencyForm.get('shortName')?.setValue(element.shortName);
    this.agencyForm.get('address')?.setValue(element.address);
    this.agencyForm.get('id')?.setValue(element.id);
    this.agencyForm.get('stateCode')?.setValue(element.stateCode);
  }
  delete(item: any) {
    this.sharedService.delete().subscribe(result => {
      if (result) {
        this.authService.deleteAgency(item).subscribe(res => {
          if (res.status === 200) {
            this.sharedService.showSuccessMessage('Record delete successfully');
            this.getList()
          }

        }, err => {

        })
      }
    })

  }
  addAgency() {
    this.buttonName = 'Save'
    this.agencyCode = ''
    this.showForm = true;
    this.addUpdateIndex = null;
    this.tempIndex = null
    this.agencyForm.reset();
  }
  openDialog(ele: any, i: number) {
    ele['page'] = 'Agency';
    let dialogRef = this.dialog.open(UpdateViewComponent, {
      width: '50%',
      height: 'auto',
      data: { data: ele, list: this.listData, i: this.StartLimit + i },
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {


      }
    });
  }
  download() {
    let index = 1;
    this.listData.forEach((item) => (
      item['index'] = index++,
      item.agencyCode = item.cisoEmadedPK.aisheCode,
      item.institute = item.cisoEmadedPK.instituteFullName
    ));
    var columns = [{ title: "S.No.", dataKey: "index" }, { title: "Agency Code", dataKey: "agencyCode" },
    { title: "Institute Main-Type", dataKey: "instituteMainType" },
    { title: "Institute Sub-Type", dataKey: "instituteSubType" },
    { title: "Institute Name", dataKey: "institute" },
    { title: "Short Name", dataKey: "shortName" },
    { title: "State", dataKey: "stateName" },
    { title: "Address", dataKey: "address" }]
    const doc = new jsPDF({
      orientation: 'landscape',
      unit: 'px',
      format: [1024, 1200],
    });
    doc.text('MoE Agency List', 500, 20);
    // doc.text(surveyYear, 980, 20);
    autoTable(doc, {
      theme: 'grid',
      columns: columns,
      body: this.listData,
      margin: {
        top: 30,
      },
    });

    doc.save('MoE Agency List.pdf');
  }
  getState() {
    this.authService.getState().subscribe(
      (res) => {
        if (res && res.length) {
          this.variables = [];
          this.variables = res;
          this.stateList = this.variables.slice();
        }
      },
      (err) => { }
    );
  }
}