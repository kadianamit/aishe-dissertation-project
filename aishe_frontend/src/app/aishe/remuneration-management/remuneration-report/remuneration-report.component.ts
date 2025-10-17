import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { NotificationService } from 'src/app/service/reports/notification.service';
import { SharedService } from 'src/app/shared/shared.service';
import { utility } from 'src/app/common/utility';
@Component({
  selector: 'app-remuneration-report',
  templateUrl: './remuneration-report.component.html',
  styleUrls: ['./remuneration-report.component.scss']
})
export class RemunerationReportComponent implements OnInit {
  isDisabled: boolean = false;
  blob: any;
  tab: any
  role: any;
  showTable: boolean = false;
  variables: any;
  stateList: any = [];
  stateCode: any;
  districtList: any;
  variables1: any;
  institutionTypeId: any;
  institutionType: any;
  instituteType1: any;
  universityListArray: Array<any> = [];
  universityList: Array<any> = [];
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  searchText: any
  listStatus: any;
  tempList: any;
  sortDir = 1;
  dataList: Array<any> = [];
  checkedR: boolean = false;
  userFormData = new FormGroup({

  })
  institutionTypeList: any;
  constructor(public authService: AuthService, public fb: FormBuilder, public notificationService: NotificationService,
    public sharedService: SharedService
  ) {

    this.sharedService.getUserDetails.subscribe(res => {
      if (res !== 0) {
        this.role = res.roleId.toString()
      }
    })

  }
  ngOnInit(): void {
    this.userFormData = this.fb.group({
      surveyYear: ['', [Validators.required]],
      stateCode: ['', []],
      district: ['', []],
      instituteType: ['', []],
      instituteSubType: ['', []],
      aisheCode: ['', []],
      fromDate: ['', []],
      toDate: ['', []],
      universityType: ['', []],
      status: ['', []],
    })
    this.getState();
    this.remunerationSatus();
  }

  remunerationSatus(): void {
    this.authService.getReportRemunerationStatus().subscribe((res: any) => {
      if (res.status === 200) {
        this.listStatus = [{
          "id": 'ALL',
          "status": "ALL"
        }, ...res.data]

      }
    })
  }
  checkeRem(data: any) {


    if (data.checked) {
      this.dataList = [];
      this.dataList = this.tempList.map((element: any) => {
        return {
          ...element,
          institutionAisheCode: element.institutionName + "(" + element.aisheCode + ")",
          type: element.institutionType === "College Institution" ? element.type + "/" + element.affilatingUniversityName : element.type ? element.type : '',
          checked: element.statusId===1?false:data.checked
        };
      });
    } else {
      this.dataList = this.tempList.map((element: any) => {
        return {
          ...element,
          institutionAisheCode: element.institutionName + "(" + element.aisheCode + ")",
          type: element.institutionType === "College Institution" ? element.type + "/" + element.affilatingUniversityName : element.type ? element.type : '',
          checked: element.statusId===1?false:data.checked
        };
      });
    }

  }
  openStatusPup() {
    let da: any = [];
    this.dataList.forEach((element) => {
      if (element.checked) {
        da.push({
          aisheCode: element.aisheCode,
          userId: element.userId,
          surveyYear: element.surveyYear,
        })
      }

    })
    this.sharedService.remunerationStatus(da).subscribe((result) => {
      if (result && result.success) {
        this.findData();
      } else {
        // Handle cancel or unsuccessful save
      }
    });
   
  }
  getState() {
    this.authService.getStatePrivate().subscribe(
      (res) => {
        if (res && res.length) {
          this.variables = [];
          this.variables = res;
          this.stateList = [{ stateCode: 'ALL', name: 'All' }, ...this.variables.slice()];
          // this.getDistrict(this.stateCode)
        }
      },
      (err) => { }
    );
  }
  getDistrict(state: any) {
    this.districtList = [];
    this.variables1 = [];
    if (this.userFormData.controls['instituteType'].value === "C" && state) {
      this.getUniversity(state)
    }

    this.authService.getDistrict(state).subscribe(
      (res) => {
        if (res && res.length) {
          this.variables1 = res;
          this.districtList = [{ distCode: 'ALL', name: 'All' }, ...this.variables1.slice()];
        } else {
          this.districtList = [{ distCode: 'ALL', name: 'All' }, ...this.variables1.slice()];
        }
      },
      (err) => { }
    );
  }
  getInstituteSubType(value: any) {
    this.institutionTypeId = ''
    if (value === 'C') {
      value = 'U'
    }
    this.authService.institutionType(value).subscribe(res => {
      this.instituteType1 = res
      this.institutionTypeList = [{ id: 'All', type: 'All' }, ...this.instituteType1.slice()];
    }, err => {

    })
  }

  getUniversity(data: any) {

    this.authService.getUniversityListRemunaration(data).subscribe(res => {
      if (res && res.length) {
        this.universityListArray = [];
        this.universityListArray = res;
        this.universityListArray = this.universityListArray.sort((a, b) => a.name > b.name ? 1 : -1);
        this.universityList = this.universityListArray.slice();

      }
    }, err => {

    })
  }
  getUniversityByType(value: any) {
    this.getUniversity(this.userFormData.controls['stateCode'].value)
    this.universityList = this.universityListArray.filter((ele: any) => ele.typeId === value);
  }

  findData() {
    if (this.userFormData.invalid) {
      return;
    }
    let fromDate = this.userFormData.controls['fromDate']?.value?.split("-");
    let fromDate1 = fromDate?.length > 1 ? fromDate[2] + "/" + fromDate[1] + "/" + fromDate[0] : "";
    let toData = this.userFormData.controls['toDate']?.value?.split("-");
    let toData1 = toData?.length > 1 ? toData[2] + "/" + toData[1] + "/" + toData[0] : "";

    let temp = {
      surveyYear: this.userFormData.controls['surveyYear'].value,
      aisheCode: this.userFormData.controls['aisheCode'].value,
      institutionType: this.userFormData.controls['instituteType'].value === "C" ? "COLLEGE" : this.userFormData.controls['instituteType'].value === "U" ? "UNIVERSITY" : this.userFormData.controls['instituteType'].value === "S" ? "STANDALONE" : "ALL",
      stateCode: this.userFormData.controls['stateCode'].value,
      districtCode: this.userFormData.controls['district'].value,
      fromDate: fromDate1 ? fromDate1 : '',
      toDate: toData1 ? toData1 : '',
      categoryType: this.userFormData.controls['instituteSubType'].value,
      universityId: this.userFormData.controls['universityType'].value,
      status: this.userFormData.controls['status'].value,
      exportType: 'JSON'
    }
    this.authService.getAllData(temp).subscribe((res: any) => {
      if (res.data && res.data.length > 0) {
        this.dataList = res.data.map((element: any) => {
          return {
            ...element,
            institutionAisheCode: element.institutionName + " (" + element.aisheCode + ")",
            type: element.institutionType === "College Institution" ? element.type + "/" + element.affilatingUniversityName : element.type,
            checked: 0,
            percentage:(element.numberOfDcfSubmittedCollege *100)/element.numberOfAffiliatingCollege,
            RemunerationAmountUniversity:element.isThisNewUniversity===null||element.isThisNewUniversity===true?element?.amount:(((element.numberOfDcfSubmittedCollege *100)/element.numberOfAffiliatingCollege)*element.amount)/100
          };
        });
        this.tempList = [...this.dataList]
        this.showTable = true;
        this.handlePageChange(this.page = 1)
      } else {
        this.dataList = [];
        this.showTable = false;
      }


    });
  }

  onSortClick(event:any,colName:any) {
    let target = event.currentTarget,
      classList = target.classList;

    if (classList.contains('fa-chevron-up')) {
      classList.remove('fa-chevron-up');
      classList.add('fa-chevron-down');
      this.sortDir = -1;
    } else {
      classList.add('fa-chevron-up');
      classList.remove('fa-chevron-down');
      this.sortDir = 1;
    }
    this.sortArr(colName);
  }

  sortArr(colName: any) {
    this.dataList.sort((a:any, b:any) => {
      a = a[colName]?.toLowerCase();
      b = b[colName]?.toLowerCase();
      return a?.localeCompare(b) * this.sortDir;
    });
  }

  downloadExcel() {
    if (this.userFormData.invalid) {
      return;
    }
    let fromDate = this.userFormData.controls['fromDate']?.value?.split("-");
    let fromDate1 = fromDate?.length > 1 ? fromDate[2] + "/" + fromDate[1] + "/" + fromDate[0] : "";
    let toData = this.userFormData.controls['toDate']?.value?.split("-");
    let toData1 = toData?.length > 1 ? toData[2] + "/" + toData[1] + "/" + toData[0] : "";

    let temp = {
      surveyYear: this.userFormData.controls['surveyYear'].value,
      aisheCode: this.userFormData.controls['aisheCode'].value,
      institutionType: this.userFormData.controls['instituteType'].value === "C" ? "COLLEGE" : this.userFormData.controls['instituteType'].value === "U" ? "UNIVERSITY" : this.userFormData.controls['instituteType'].value === "S" ? "STANDALONE" : "ALL",
      stateCode: this.userFormData.controls['stateCode'].value,
      districtCode: this.userFormData.controls['district'].value,
      fromDate: fromDate1 ? fromDate1 : '',
      toDate: toData1 ? toData1 : '',
      categoryType: this.userFormData.controls['instituteSubType'].value,
      universityId: this.userFormData.controls['universityType'].value,
      status: this.userFormData.controls['status'].value,
      exportType: 'EXCEL',
    }
    this.authService.getAllData(temp).subscribe((res: any) => {
      if (res.bytes) {
        utility.downloadAsExcel(res.bytes, 'remuneration-report');
      }


    });
  }

  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1)
  }

  async updateResults() {
    // this.dataList = []
    this.dataList = this.searchByValue(this.tempList);
    this.handlePageChange(this.page = 1)
  }



  searchByValue(userData: any) {
    return userData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {  
        return (item.surveyYear?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.stateName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.districtName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
         // || (item.aisheCode?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.aisheCode?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.institutionType?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.institutionName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.nodalOfficerName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.type?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.bankName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase().toLowerCase()))
          || (item.pan?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.ifscCode?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.accountNumber?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.accountHolderName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.submittedOn?.toString().toLocaleLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.numberOfAffiliatingCollege?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.numberOfDcfSubmittedCollege?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.amount?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.isThisNewUniversity?.toString().toLocaleLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  handlePageChange(event: any) {
    this.page = event
    this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
      this.EndLimit = this.StartLimit + Number(this.pageSize)
    var a = Math.ceil(this.dataList.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.dataList.length);
    } else {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.dataList.length - 1);
    }

  }
  onKeypressEvent(event: any, inputLength: any) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }



  get f(): { [key: string]: AbstractControl } {
    return this.userFormData.controls;
  }




  DisablePaste(event: any) {
    event.preventDefault();
  }




  downloadPdf(data: any) {
    let uint8_data = _base64ToArrayBuffer(data);
    var ba = new Uint8Array(uint8_data);
    //this.blob = new Blob([ba], { type: 'application/pdf' });
    this.blob = new Blob([ba], { type: 'application/octet-stream' });
    function _base64ToArrayBuffer(base64: any) {
      var binary_string = window.atob(base64);
      var len = binary_string.length;
      var bytes = new Uint8Array(len);
      for (var i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
      }
      return bytes.buffer;
    }
  }


  viewPdf(data: any, fileName: string) {
    let uint8_data = _base64ToArrayBuffer(data);
    var ba = new Uint8Array(uint8_data);
    var blob = new Blob([ba], { type: 'application/pdf' });
    const url = window.URL.createObjectURL(blob);
    this.tab = window.open(url, fileName);
    this.tab.location.href = url;

    function _base64ToArrayBuffer(base64: string) {
      var binary_string = window.atob(base64);
      var len = binary_string.length;
      var bytes = new Uint8Array(len);
      for (var i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
      }
      return bytes.buffer;
    }
  }





  fakeWaiter(ms: number) {
    return new Promise((resolve) => {
      setTimeout(resolve, ms);
    });
  }




  numberOnly(e: any) {  // Accept only alpha numerics, not special characters 
    var regex = new RegExp("^[0-9 ]+$");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
      return true;
    }

    e.preventDefault();
    return false;
  }

  alphaOnly(e: any) {  // Accept only alpha numerics, not special characters 
    var regex = new RegExp("^[a-zA-Z ]+$");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
      return true;
    }

    e.preventDefault();
    return false;
  }

  clrae() {
    this.userFormData.reset();
    //  this.dataList = [];
    this.showTable = false;
  }
}
