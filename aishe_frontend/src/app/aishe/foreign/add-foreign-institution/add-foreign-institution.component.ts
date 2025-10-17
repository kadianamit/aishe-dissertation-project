import { Component, Input, OnInit, Output, EventEmitter, OnChanges } from '@angular/core';
import {
  FormGroup,
  FormBuilder,
  FormControl,
  Validators,
} from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';

@Component({
  selector: 'app-add-foreign-institution',
  templateUrl: './add-foreign-institution.component.html',
  styleUrls: ['./add-foreign-institution.component.scss'],
})
export class AddForeignInstitutionComponent implements OnInit,OnChanges {
  @Input() editData: any;
  addButton: string = 'Save Draft';
  approveReject:boolean=false;
  @Output() backData = new EventEmitter<any>();
  displayedColumns: string[] = ['position', 'name', 'weight'];
  dataSource: any = [];
  countryArr: Array<any> = [];
  filterCountryArr: Array<any> = [];
  myForm: any = FormGroup;
  paginator: MatPaginator | null | undefined;
  sort: MatSort | null | undefined;
  isFormShow: boolean = false;
  isListShow: boolean = true;
  btnVisible: boolean = true;
  PeriodicElement: any = [];
  searchText: any;
  dataSourceFilter: any = [];
  StartLimit: number = 0;
  pageData: number = 25;
  EndLimit: number = 0;
  pageSize: any = 25;
  page: number = 1;
  countryId: any = 0;
  showTable: boolean = false;
  tableSize: number[] = [25, 50, 75, 100];
  selectedValue: any = 'ALBANIA';
  flag: any;
  editFormId: any;
  isFormInvalid: boolean = false;
  isdisabled: any = false;
  filterArr: any = [];
  selectionFilter: any = [];
  roleId: any;
  selectedFilterName: string | null = null;
  countryName: any = 0;
  getCountryName: any = [];
  sortDir = 1;
  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private shared: SharedService,
    public localService: LocalserviceService,
    public errorMatcher: CustomErrorStateMatcher
  ) {
    this.roleId = this.localService.getData('roleId');
    this.filterArr = this.shared.approveDisapproveArr;
    this.myForm = this.fb.group({
      id: new FormControl(''),
      status: new FormControl(''),
      countryId: new FormControl('', Validators.required),
      foreigninstitute: new FormControl('', Validators.required),
      premiseNumber: new FormControl(''),
      address1: new FormControl(''),
      address2: new FormControl(''),
      locality: new FormControl(''),
      subLocality: new FormControl(''),
      pinCode: new FormControl(''),
      mobileNo: new FormControl('', [
        Validators.pattern('[0-9]{10}'),
      ]),
      email: new FormControl('', [Validators.required, Validators.email]),
      telephone: new FormControl(''),
      webUrl: new FormControl('', [
        Validators.required,
        Validators.pattern(
          '^((https?|ftp)://)?([\\w_-]+\\.)+[\\w-]+(/[\\w\\- ./?%&=]*)?$'
        ),
      ]),
    });
  }
  ngOnChanges() {
    if (this.editData.length > 0 && this.editData.length) {
      this.addButton = 'Update';
      this.approveReject=this.editData[0].approveRe
      this.setDataForm();
      console.log(this.editData.approveRe);
    }
  }
  ngOnInit(): void {

    this.getCountries();

    // this.getForeignInstitutesDetails(this.countryId)
    // if (this.paginator && this.sort) {
    //   this.dataSource.paginator = this.paginator;
    //   this.dataSource.sort = this.sort;
    // }
  }
  back() {
    this.backData.emit(true);
  }
  // getIndex(i: number) {
  //   let pageCorrection: number;
  //   let indexPerPage = 10;
  //   if (this.page === 1) {
  //     pageCorrection = 0;
  //   } else {
  //     pageCorrection = (this.page - 1) * indexPerPage;
  //   }
  //   return i + 1 + pageCorrection;
  // }
  setDataForm() {
    this.myForm.get('countryId')?.setValue(this.editData['0']?.country.id),
      this.myForm.get('foreigninstitute')?.setValue(this.editData['0'].name),
      this.myForm
        .get('premiseNumber')
        ?.setValue(this.editData['0'].premiseNumber);
    this.myForm.get('address1')?.setValue(this.editData['0'].addressLine1);
    this.myForm.get('address2')?.setValue(this.editData['0'].addressLine2);
    this.myForm.get('locality')?.setValue(this.editData['0'].locality);
    this.myForm.get('subLocality')?.setValue(this.editData['0'].subLocality);
    this.myForm.get('pinCode')?.setValue(this.editData['0'].pinCode);
    this.myForm.get('mobileNo')?.setValue(this.editData['0'].mobile);
    this.myForm.get('email')?.setValue(this.editData['0'].emailId);
    this.myForm.get('telephone')?.setValue(this.editData['0'].telephone);
    this.myForm.get('webUrl')?.setValue(this.editData['0'].website);
    this.myForm.get('id')?.setValue(this.editData['0'].id);
  }
  getCountries() {
    this.authService.getcountryPrivate().subscribe(
      (res) => {
        this.countryArr = res;
        const i = this.countryArr.findIndex(e=>e.id === 1)
        this.countryArr.splice(i,1)
        this.filterCountryArr = this.countryArr.slice();
      },
      (err) => {}
    );
  }

  // getForeignInstitutesDetails(countryId: any) {
  //   this.authService.getForeignInstitutes(countryId).subscribe(res => {
  //     if (res) {
  //       var draftFilter = res.filter((m: any) => m.statusId.id == 1).sort(function (a: any, b: any) {
  //         return a.country.name.localeCompare(b.country.name) || a.name.localeCompare(b.name)
  //       })
  //       var finalSubmitFilter = res.filter(
  //         (m: any) => m.statusId.id == 2).sort(function (a: any, b: any) {
  //           return a.country.name.localeCompare(b.country.name) || a.name.localeCompare(b.name)
  //         })

  //       var approvedFilter = res.filter(
  //         (m: any) => m.statusId.id == 3).sort(function (a: any, b: any) {
  //           return a.country.name.localeCompare(b.country.name) || a.name.localeCompare(b.name)
  //         })
  //       var disapprovedFilter = res.filter(
  //         (m: any) => m.statusId.id == 4).sort(function (a: any, b: any) {
  //           return a.country.name.localeCompare(b.country.name) || a.name.localeCompare(b.name)
  //         })
  //       this.dataSourceFilter = [...draftFilter, ...finalSubmitFilter, ...approvedFilter, ...disapprovedFilter].filter((m: any) => m.statusId.id != 4)
  //       this.dataSource = this.dataSourceFilter
  //       this.selectionFilter = [...draftFilter, ...finalSubmitFilter, ...approvedFilter, ...disapprovedFilter]
  //       this.page = 1
  //       this.handlePageChange(this.page);
  //       this.showTable = true;
  //     }
  //     else {
  //       console.log('1')
  //     }

  //   })

  // }

  // onSelectionCountry(event: any) {
  //   this.countryId = event.value
  //   this.getForeignInstitutesDetails(this.countryId)
  //   this.filterClear()
  // }

  // refresh() {
  //   this.selectedFilterName = null
  //   this.countryName = 0
  //   this.getForeignInstitutesDetails(0)

  // }

  // filterClear() {
  //   this.selectedFilterName = null
  // }

  // onSelectionFilter(event: any) {
  //   this.dataSource = []
  //   this.dataSourceFilter = []
  //   if (event.value == 0) {
  //     this.dataSource = this.selectionFilter.filter((m: any) => m.statusId.id != 4)
  //     this.dataSourceFilter = this.selectionFilter.filter((m: any) => m.statusId.id != 4)
  //   }
  //   else {
  //     this.dataSource = this.selectionFilter.filter((m: any) => m.statusId.id == event.value)
  //     this.dataSourceFilter = this.selectionFilter.filter((m: any) => m.statusId.id == event.value)
  //   }
  //   this.page = 1
  //   this.handlePageChange(this.page);
  //   this.showTable = true

  // }

  // handlePageChange(event: any) {
  //   this.page = event
  //   this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
  //     this.EndLimit = this.StartLimit + Number(this.pageSize)
  //   var a = Math.ceil(this.dataSource.length / Number(this.pageSize));
  //   if (a === event) {
  //     this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.dataSource.length);
  //   } else {
  //     this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.dataSource.length - 1);
  //   }

  // }

  // onChange(event: any) {
  //   this.pageSize = parseInt(event);
  //   this.handlePageChange(this.page = 1)
  // }

  // applyFilter() {
  //   this.dataSourceFilter = []
  //   this.dataSourceFilter = this.searchValue(this.dataSource);
  //   this.handlePageChange(this.page = 1)
  // }

  // searchValue(userData: any) {
  //   return userData.filter((item: any) => {
  //     if (this.searchText.trim() === '') {

  //       return true;

  //     } else {
  //       return (item.name?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase())
  //         ||
  //         item.country.name?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))

  //     }
  //   })

  // }

  // scrollToTop() {
  //   window.scrollTo(0, 0);
  // }

  // addForm() {
  //   this.isFormShow = true;
  //   this.isListShow = true
  //   this.btnVisible = false
  //   this.myForm.reset()

  // }

  // backFnc() {
  //   this.isFormShow = false;
  //   this.isListShow = true
  //   this.btnVisible = true
  //   this.isdisabled = false
  //   this.flag = ""
  //   this.myForm.reset()
  //   this.getForeignInstitutesDetails(this.countryId)
  //   this.filterClear()
  // }

  // reset() {
  //   this.myForm.reset()

  // }

  // editForm(data: any) {
  //   this.flag = 'E'
  //   this.isFormShow = true;
  //   this.isListShow = true
  //   this.btnVisible = false
  //   this.isdisabled = false
  //   this.scrollToTop()
  //   this.getForeignInstitutesDetails(0)
  //   this.authService.getForeignInstitutes(data.country.id).subscribe(res => {
  //     let filterArry = res.filter((item: any) => item.id === data.id);
  //     this.editFormId = filterArry[0].id
  //     this.myForm.get('countryId').setValue(filterArry[0].country.id),
  //       this.myForm.get('foreigninstitute').setValue(filterArry[0].name),
  //       this.myForm.get('premiseNumber').setValue(filterArry[0].premiseNumber)
  //     this.myForm.get('address1').setValue(filterArry[0].addressLine1)
  //     this.myForm.get('address2').setValue(filterArry[0].addressLine2)
  //     this.myForm.get('locality').setValue(filterArry[0].locality)
  //     this.myForm.get('subLocality').setValue(filterArry[0].subLocality)
  //     this.myForm.get('pinCode').setValue(filterArry[0].pinCode)
  //     this.myForm.get('mobileNo').setValue(filterArry[0].mobile)
  //     this.myForm.get('email').setValue(filterArry[0].emailId)
  //     this.myForm.get('telephone').setValue(filterArry[0].telephone)
  //     this.myForm.get('webUrl').setValue(filterArry[0].website)

  //   })

  // }

  // saveForm() {
  //   if (this.myForm.invalid) {
  //     this.isFormInvalid = true;
  //     this.shared.showError('Please enter required field');
  //     return;
  //   } else {
  //     this.isFormInvalid = false
  //   }
  //   let payload = {
  //     addressLine1: this.myForm.value.address1,
  //     addressLine2: this.myForm.value.address2,
  //     countryId: this.myForm.value.countryId,
  //     createdOn: "",
  //     emailId: this.myForm.value.email,
  //     id: this.editFormId == undefined ? '0' : this.editFormId == "" ? '0' : this.editFormId,
  //     locality: this.myForm.value.locality,
  //     mobile: this.myForm.value.mobileNo,
  //     name: this.myForm.value.foreigninstitute,
  //     pinCode: this.myForm.value.pinCode,
  //     premiseNumber: this.myForm.value.premiseNumber,
  //     recordStatusId: "1",
  //     subLocality: this.myForm.value.subLocality,
  //     telephone: this.myForm.value.telephone,
  //     userId: "",
  //     statusId: 1,
  //     website: this.myForm.value.webUrl
  //   }
  //   this.authService.saveForeignInstitutes(payload).subscribe(res => {
  //     if (res.status == 200) {
  //       this.shared.showSuccess()
  //       this.getCountryName = this.filterCountryArr.filter((m: any) => m.id == payload.countryId)
  //       var obj: any = {
  //         name: payload.name,
  //         country: {
  //           id: this.getCountryName[0].id,
  //           name: this.getCountryName[0].name,
  //           countryCode: this.getCountryName[0].countryCode,
  //           isdCode: this.getCountryName[0].isdCode
  //         },
  //         statusId: {
  //           status: true,
  //           name: "Draft",
  //           id: 1
  //         }
  //       }
  //       this.dataSourceFilter.unshift(obj)

  //       this.myForm.reset()
  //       this.isdisabled = true
  //       this.backFnc();

  //     }
  //     else {
  //       this.shared.showValidationMessage(res.message)
  //     }

  //   })

  // }

  // finalSubmit() {
  //   this.shared.finalSubmitConfirm().subscribe(res => {
  //     if (res) {
  //       if (this.myForm.invalid) {
  //         this.isFormInvalid = true;
  //         this.shared.showError('Please enter required field');
  //         return;
  //       } else {
  //         this.isFormInvalid = false
  //       }
  //       let payload = {
  //         addressLine1: this.myForm.value.address1,
  //         addressLine2: this.myForm.value.address2,
  //         countryId: this.myForm.value.countryId,
  //         createdOn: "",
  //         emailId: this.myForm.value.email,
  //         id: this.editFormId == undefined ? '0' : this.editFormId == "" ? '0' : this.editFormId,
  //         locality: this.myForm.value.locality,
  //         mobile: this.myForm.value.mobileNo,
  //         name: this.myForm.value.foreigninstitute,
  //         pinCode: this.myForm.value.pinCode,
  //         premiseNumber: this.myForm.value.premiseNumber,
  //         recordStatusId: "1",
  //         subLocality: this.myForm.value.subLocality,
  //         telephone: this.myForm.value.telephone,
  //         userId: "",
  //         statusId: 2,
  //         website: this.myForm.value.webUrl
  //       }
  //       this.authService.saveForeignInstitutes(payload).subscribe(res => {
  //         if (res.status == 200) {
  //           this.shared.showSuccess()
  //           this.getCountryName = this.filterCountryArr.filter((m: any) => m.id == payload.countryId)
  //           var obj: any = {
  //             name: payload.name,
  //             country: {
  //               id: this.getCountryName[0].id,
  //               name: this.getCountryName[0].name,
  //               countryCode: this.getCountryName[0].countryCode,
  //               isdCode: this.getCountryName[0].isdCode
  //             },
  //             statusId: {
  //               status: true,
  //               name: "Final Submit",
  //               id: 2
  //             }
  //           }
  //           this.dataSourceFilter.unshift(obj)

  //           // this.getForeignInstitutesDetails(this.myForm.value.countryId)
  //           this.myForm.reset()
  //           this.isdisabled = true

  //         }
  //         else {
  //           this.shared.showValidationMessage(res.message)
  //         }

  //       })
  //     }
  //   })
  // }
  more: boolean = true;
  moreInfo(val: string) {
    if (val === 'more') {
      this.more = true;
    } else {
      this.more = false;
    }
  }
  save(statusId:any) {
    if (this.myForm.invalid) {
      this.isFormInvalid = true;
      this.shared.showWarning();
      return;
    } else {
      this.isFormInvalid = false;
    }
    let payload = {
      addressLine1: this.myForm.value.address1,
      addressLine2: this.myForm.value.address2,
      countryId: this.myForm.value.countryId,
      createdOn: '',
      emailId: this.myForm.value.email,
      id: this.myForm.value.id?this.myForm.value.id:0,
      locality: this.myForm.value.locality,
      mobile: this.myForm.value.mobileNo,
      name: this.myForm.value.foreigninstitute,
      pinCode: this.myForm.value.pinCode,
      premiseNumber: this.myForm.value.premiseNumber,
      recordStatusId: '1',
      subLocality: this.myForm.value.subLocality,
      telephone: this.myForm.value.telephone,
      userId: '',
      statusId: statusId==='approveReject'?this.myForm.value.status:statusId,
      website: this.myForm.value.webUrl,
    };
    this.authService.saveForeignInstitutes(payload).subscribe(
      (res) => {
        if (res.status == 200) {
          this.shared.showSuccess();
          this.reset();
          //if (this.addButton === 'Update') {
            this.backData.emit(true);
            this.back();
         // }
        }
      },
      (err) => {
        if (
          err.error.message === 'Foreign Institutes Details Already Exist!!'
        ) {
          this.shared.showValidationMessage(err.error.message);
        }
      }
    );
  }
  reset() {
    this.myForm.reset();
  }
}
