import { ViewportScroller } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';
import { UpdateViewComponent } from '../update-view/update-view.component';
import { jsPDF } from "jspdf";
import autoTable from 'jspdf-autotable';
@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {
  cisoForm: FormGroup
  inValidAishe: boolean = false
  isFormInvalid: boolean = false;
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  listData: Array<any> = [];
  tempListData: Array<any> = [];
  agencyList: Array<any> = [];
  searchText: any;
  agencyName: string = '--ALL--'
  post: string = '--ALL--';
  showForm: boolean = false;
  buttonName: string = 'Save';
  addUpdateIndex: any = null;
  tempIndex: any = null
  agencyCode: any;
  agencyType: string = '--ALL--'
  agencyListArray: Array<any> = []
  constructor(public authService: AuthService, public sharedService: SharedService, public dialog: MatDialog,
    public fb: FormBuilder, public errorMatcher: CustomErrorStateMatcher, public viewportScroller: ViewportScroller) {
    let emailRegex: RegExp = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    let mobileNoRegex: RegExp = /^[0]?[123456789]\d{9}$/
    this.cisoForm = this.fb.group({
      name: ['', [Validators.required]],
      priority: ['', []],
      agencyCode: ['', [Validators.required]],
      agencyName: ['', [Validators.required]],
      mobile: ['', [Validators.required, Validators.pattern(mobileNoRegex)]],
      emailId: ['', [Validators.required, Validators.pattern(emailRegex)]],
      alternateEmail: ['', [Validators.pattern(emailRegex)]],
      post: ['', [Validators.required]],
      alternateMobile: ['', [Validators.pattern(mobileNoRegex)]],
      designation: ['', [Validators.required]],
      officeAddress: ['', [Validators.required]],
      remarks: ['', []]

    })
  }

  ngOnInit(): void {
    this.getCiso();
  }
  changeFun(value: any) {
    if (value !== '3') {
      this.cisoForm.get('agencyName')?.disable();
      this.cisoForm.get('agencyName')?.updateValueAndValidity()
    } else {
      this.cisoForm.get('agencyName')?.enable();
      this.cisoForm.get('agencyName')?.updateValueAndValidity()
    }
  }
  getDetails() {
    // this.agencyCode = this.cisoForm.controls['agencyCode'].value.toUpperCase();
    // let x = this.cisoForm.controls['agencyCode'].value.toUpperCase();
    // let aisheId = x[1];
    // let loginMode = x[0];
    // if (loginMode !== 'U' && loginMode !== 'S' && loginMode !== 'C') {
    //   this.inValidAishe = true
    //   return;
    // } else {
    //   this.inValidAishe = false
    // }
    // this.authService.getInstituteName(this.cisoForm.controls['agencyCode'].value.toUpperCase()).subscribe(res => {
    //   this.cisoForm.patchValue({
    //     name: '',
    //     agencyName: res.data.instituteName,
    //     mobile: '',
    //     emailId: '',
    //     alternateEmail: '',
    //     post: '',
    //     alternateMobile: '',
    //     designation: '',
    //     officeAddress: '',
    //     remarks: ''
    //   })


    // }, err => {
    //   if (err.status === 404) {
    //     this.inValidAishe = true
    //     return;
    //   }
    // })
  }
  save() {
    if (this.cisoForm.controls['priority'].value === '4') {
      if (this.cisoForm.controls['agencyCode'].value.toUpperCase() !== this.agencyCode) {
        this.sharedService.showValidationMessage('Please enter valid agency code');
        return;
      } else {
        this.agencyCode = ''
      }
    }

    if (this.cisoForm.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true
      return;
    } else {
      this.isFormInvalid = false
    }
    var count = 0
    for (let index = 0; index < this.listData.length; index++) {
      if (this.tempIndex !== index) {
        if (this.listData[index].agencyCode === this.cisoForm.controls['agencyCode'].value.toUpperCase() && this.listData[index].post === this.cisoForm.controls['post'].value) {
          count++
        }
      }
    }
    if (count !== 0) {
      this.sharedService.showValidationMessage('Duplicate record not allowed !!!');
      return;
    }

    // const duplicate = this.listData.filter((ele: any) => ele.agencyCode.toUpperCase() === this.cisoForm.controls['agencyCode'].value.toUpperCase() && ele.post === this.cisoForm.controls['post'].value)
    // if (duplicate.length !== 0) {
    //   this.sharedService.showValidationMessage('Duplicate record not allowed !!!');
    //   return;
    // }
    let payload = {

      "agencyCode": this.cisoForm.controls['agencyCode'].value,
      "agencyName": this.cisoForm.controls['agencyName'].value,
      "designation": this.cisoForm.controls['designation'].value,
      "emailId": this.cisoForm.controls['emailId'].value,
      "emailIdAlternate": this.cisoForm.controls['alternateEmail'].value,
      "mobile": this.cisoForm.controls['mobile'].value,
      "mobileAlternate": this.cisoForm.controls['alternateMobile'].value,
      "name": this.cisoForm.controls['name'].value,
      "officeAddress": this.cisoForm.controls['officeAddress'].value,
      "post": this.cisoForm.controls['post'].value,
      "priority": this.cisoForm.controls['priority'].value,
      "remarks": this.cisoForm.controls['remarks'].value

    }
    this.authService.saveCisoForm(payload).subscribe(res => {
      if (res.status === 200) {
        if(this.buttonName === 'Save'){
          this.sharedService.showSuccess();
        }else{
          this.sharedService.showUpdate();
        }
        this.getCiso();
        // if (this.buttonName === 'Save') {
        //   this.sharedService.showSuccess();
        //   this.listData.push({
        //     agencyCode: res.data?.agencyCode,
        //     agencyName: res.data?.agencyName,
        //     designation: res.data?.designation,
        //     emailId: res.data?.emailId,
        //     emailIdAlternate: res.data?.emailIdAlternate,
        //     mobile: res.data?.mobile,
        //     mobileAlternate: res.data?.mobileAlternate,
        //     name: res.data?.name,
        //     officeAddress: res.data?.officeAddress,
        //     post: res.data?.post,
        //     priority: res.data?.priority.toString(),
        //     remarks: res.data?.remarks,
        //   })
        // } else {
        //   this.sharedService.showUpdate();
        //   this.listData[this.tempIndex].agencyCode = res.data?.agencyCode,
        //     this.listData[this.tempIndex].agencyName = res.data?.agencyName,
        //     this.listData[this.tempIndex].designation = res.data?.designation,
        //     this.listData[this.tempIndex].emailId = res.data?.emailId,
        //     this.listData[this.tempIndex].emailIdAlternate = res.data?.emailIdAlternate,
        //     this.listData[this.tempIndex].mobile = res.data?.mobile,
        //     this.listData[this.tempIndex].mobileAlternate = res.data?.mobileAlternate,
        //     this.listData[this.tempIndex].name = res.data?.name,
        //     this.listData[this.tempIndex].officeAddress = res.data?.officeAddress,
        //     this.listData[this.tempIndex].post = res.data?.post,
        //     this.listData[this.tempIndex].priority = res.data?.priority,
        //     this.listData[this.tempIndex].remarks = res.data?.remarks

        // }
        // this.listData = this.listData.sort((a: any, b: any) => a.priority - b.priority);
        // this.agencyList = this.listData.filter(
        //   (thing, i, arr) => arr.findIndex(t => t.agencyName === thing.agencyName) === i
        // );
        // this.handlePageChange(this.page)
        this.reset()
        //  this.tempListData = [...this.listData];

      }
    }, err => {

    })
  }
  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
  getCiso() {
    this.authService.getCisoList().subscribe(res => {
      this.listData = res.data

      //  this.listData = this.listData.sort((a: any, b: any) => a.priority - b.priority);

      this.agencyListArray = this.listData.filter(
        (thing, i, arr) => arr.findIndex(t => t.agencyName === thing.agencyName) === i
      );
      this.agencyList = this.agencyListArray.slice()
      this.agencyList.unshift({
        agencyName: '--ALL--'
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

  getAgencyByName() {
    this.post = '--ALL--'
    if (this.agencyName !== '--ALL--') {
      this.listData = this.tempListData.filter((ele: any) => ele.agencyName?.trim().toLowerCase() === this.agencyName?.trim().toLowerCase())
    } else {
      this.listData = [...this.tempListData]
    }
    this.handlePageChange(this.page = 1)
  }
  getAgencyByPost() {
    this.agencyName = '--ALL--'
    if (this.post !== '--ALL--') {
      this.listData = this.tempListData.filter((ele: any) => ele.post?.trim().toLowerCase() === this.post?.trim().toLowerCase())
    } else {
      this.listData = [...this.tempListData]
    }
    this.handlePageChange(this.page = 1)
  }
  getAgencyNameByType(value: any) {
    if (this.agencyType === '--ALL--') {
      this.listData = [...this.tempListData];
      this.handlePageChange(this.page = 1)
    } else {
      this.authService.getAgencyName(value).subscribe(res => {
        this.listData = res.data;
        this.listData = this.listData.sort((a: any, b: any) => a.priority - b.priority);
        this.tempListData = [...this.listData]
        // this.agencyList = this.listData.filter(
        //   (thing, i, arr) => arr.findIndex(t => t.agencyName === thing.agencyName) === i
        // );
        this.handlePageChange(this.page = 1)
      }, err => {
        if (err.status === 404) {
          this.sharedService.showValidationMessage('No Record Found')
          this.listData = [];
          this.tempListData = [];
          this.handlePageChange(this.page = 1)
        }
      })
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
        return (item.agencyCode?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.agencyName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.officeAddress?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.mobile?.toString().includes(this.searchText.trim()))
          || (item.emailId?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.mobileAlternate?.toString().includes(this.searchText.trim()))
          || (item.emailIdAlternate?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.designation?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.post?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.remarks?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  openDialog(ele: any, i: number) {
    ele['page'] = 'CISO';
    let dialogRef = this.dialog.open(UpdateViewComponent, {
      width: '60%',
      height: '80%',
      data: { data: ele, list: this.listData, i: i },
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.searchText = ''
        this.getCiso()
      }
    });
  }
  delete(item: any) {
    this.sharedService.delete().subscribe(result => {
      if (result) {
        this.authService.deleteCISO(item).subscribe(res => {
          if (res.status === 200) {
            this.sharedService.showSuccessMessage('Record delete successfully');
            this.getCiso()
          }

        }, err => {

        })
      }
    })

  }
  addCiso() {
    this.agencyCode = ''
    this.showForm = true;
    this.addUpdateIndex = null;
    this.tempIndex = null
    this.cisoForm.reset();
  }
  close() {
    this.buttonName = 'Save'
    this.agencyCode = ''
    this.showForm = false;
    this.cisoForm.reset();
    this.addUpdateIndex = null;
    this.tempIndex = null
    this.cisoForm.get('agencyName')?.enable();
    this.cisoForm.get('agencyName')?.updateValueAndValidity()
    this.cisoForm.get('agencyCode')?.enable();
    this.cisoForm.get('agencyCode')?.updateValueAndValidity()
  }
  reset() {
    this.buttonName = 'Save'
    this.agencyCode = ''
    this.addUpdateIndex = null;
    this.tempIndex = null
    this.cisoForm.reset();
    this.cisoForm.get('agencyName')?.enable();
    this.cisoForm.get('agencyName')?.updateValueAndValidity()
    this.cisoForm.get('agencyCode')?.enable();
    this.cisoForm.get('agencyCode')?.updateValueAndValidity()
  }
  reload() {
    this.agencyName = '--ALL--';
    this.agencyType = '--ALL--';
    this.post = '--ALL--';
    this.StartLimit = 0;
    this.EndLimit = 25;
    this.pageData = 0;
    this.pageSize = 25;
    this.page = 1;
    this.getCiso()
  }
  edit(element: any, i: number) {
    this.viewportScroller.scrollToPosition([0, 0]);
    this.addUpdateIndex = i;
    this.tempIndex = i + this.StartLimit;
    this.buttonName = 'Update'
    if (element['emailIdAlternate'] === null || element['emailIdAlternate'] === 'null') {
      element['emailIdAlternate'] = ''
    } if (element['mobileAlternate'] === null || element['mobileAlternate'] === 'null') {
      element['mobileAlternate'] = ''
    } if (element['remarks'] === null || element['remarks'] === 'null') {
      element['remarks'] = ''
    }
    this.showForm = true
    this.agencyCode = element.agencyCode
    this.cisoForm.patchValue(element);
    this.cisoForm.get('priority')?.setValue(element.priority.toString());
    this.cisoForm.get('agencyCode')?.disable();
    this.cisoForm.get('agencyName')?.disable();
    this.cisoForm.get('agencyCode')?.updateValueAndValidity();
    this.cisoForm.get('agencyName')?.updateValueAndValidity();
  }
  // download() {
  //   var columns = [{ title: "S.No.", dataKey: "index" },{ title: "Agency Code", dataKey: "aisheCode" }, 
  //   { title: "Agency Name", dataKey: "agencyName" },{ title: "Name", dataKey: "name" },
  //   {title: "Mobile", dataKey: "mobile"},{title: "Alternate Mobile", dataKey: "mobileAlternate"},
  //   {title: "Email", dataKey: "emailId"},{title: "Alternate Email", dataKey: "emailIdAlternate"},
  //   {title: "Post", dataKey: "post"},{title: "Designation", dataKey: "designation"},
  //   {title: "Address", dataKey: "officeAddress"}]
  //   var doc = new jsPDF({
  //     orientation: "landscape",
  //     unit: 'px',
  //     format: [1024, 1200],
  //   });
  //   doc.setTextColor(51, 102, 170);
  //   doc.setFont("times");
  //   doc.text('CISO List', 500, 15);

  //   autoTable(doc, ({
  //     columns: columns,
  //     body: this.listData,
  //     startY: 20,
  //     theme: 'grid',
  //      margin: { top: 30 },
  //     // didDrawPage: function (data: any) {
  //     //   doc.text((data.pageCount).toString(), pageWidth / 2, pageHeight - 10, { align: 'center' });
  //     // }

  //   }))

  //   doc.save('CISO List.pdf');
  // }
  download() {
    let index = 1;
    this.listData.forEach((item) => (item['index'] = index++));
    var columns = [{ title: "S.No.", dataKey: "index" }, { title: "Agency Code", dataKey: "agencyCode" },
    { title: "Agency Name", dataKey: "agencyName" }, { title: "Name", dataKey: "name" },
    { title: "Mobile", dataKey: "mobile" }, { title: "Alternate Mobile", dataKey: "mobileAlternate" },
    { title: "Email", dataKey: "emailId" }, { title: "Alternate Email", dataKey: "emailIdAlternate" },
    { title: "Post", dataKey: "post" }, { title: "Designation", dataKey: "designation" },
    { title: "Address", dataKey: "officeAddress" }]
    const doc = new jsPDF({
      orientation: 'landscape',
      unit: 'px',
      format: [1024, 1200],
    });
    doc.text('CISO List', 500, 20);
    // doc.text(surveyYear, 980, 20);
    autoTable(doc, {
      theme: 'grid',
      columns: columns,
      body: this.listData,
      margin: {
        top: 30,
      },
    });

    doc.save('CISO List.pdf');
  }

}

