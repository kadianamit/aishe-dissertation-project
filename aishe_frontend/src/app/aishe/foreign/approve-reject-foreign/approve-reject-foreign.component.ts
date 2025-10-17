import { map } from 'rxjs';
import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
} from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';

@Component({
  selector: 'app-approve-reject-foreign',
  templateUrl: './approve-reject-foreign.component.html',
  styleUrls: ['./approve-reject-foreign.component.scss'],
})
export class ApproveRejectForeignComponent implements OnInit,OnChanges {
  @Output() passData = new EventEmitter<any>();
  @Input() Pending:any;
  myForm: FormGroup;
  alldata: Array<any> = [];
  tempList: Array<any> = [];
  searchText: string = '';
  countryArr: Array<any> = [];
  filterCountryArr: Array<any> = [];
  isEdit: boolean = false;
  isFormInvalid: boolean = false;
  constructor(
    public shared: SharedService,
    public authService: AuthService,
    public fb: FormBuilder,
    public errorMatcher: CustomErrorStateMatcher
  ) {
    this.myForm = this.fb.group({
      id: 0,
      countryId: new FormControl('', Validators.required),
      foreigninstitute: new FormControl('', Validators.required),
      premiseNumber: new FormControl(''),
      address1: new FormControl(''),
      address2: new FormControl(''),
      locality: new FormControl(''),
      subLocality: new FormControl(''),
      pinCode: new FormControl(''),
      mobileNo: new FormControl('', [
        Validators.required,
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
      status: new FormControl('', Validators.required),
    });
  }

ngOnChanges(){
 console.log( this.Pending);
}
  ngOnInit(): void {
    this.getForeignInstitutesDetails();
    this.getCountries();
  }
  onSelectionCountry(event: any) {
    if (event.value === 0) {
      this.alldata = [...this.tempList];
    } else {
      let array = this.tempList.filter(
        (e) => e.countryId === event.value.toString()
      );
      this.alldata = array.slice();
    }
    this.handlePageChange((this.shared.page = 1));
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
  getForeignInstitutesDetails() {
    let payload = {
      countryId: 0,
      statusId: 1,
    };
    this.authService.getForeignInstitutes(payload.countryId).subscribe(
      (res) => {
        if (res && res.length) {
          var draftFilter = res
            .filter((m: any) => m.statusId.id == 1)
            .sort(function (a: any, b: any) {
              return (
                a.country.name.localeCompare(b.country.name) ||
                a.name.localeCompare(b.name)
              );
            });
          var finalSubmitFilter = res
            .filter((m: any) => m.statusId.id == 2)
            .sort(function (a: any, b: any) {
              return (
                a.country.name.localeCompare(b.country.name) ||
                a.name.localeCompare(b.name)
              );
            });
          this.alldata = [...draftFilter, ...finalSubmitFilter];
          this.tempList = [...this.alldata];
          this.handlePageChange((this.shared.page = 1));
        } else {
          this.alldata = [];
          this.tempList = [];
        }
      },
      (err) => {}
    );
  }

  handlePageChange(event: any) {
    this.shared.page = event;
    (this.shared.StartLimit =
      (this.shared.page - 1) * Number(this.shared.pageSize)),
      (this.shared.EndLimit =
        this.shared.StartLimit + Number(this.shared.pageSize));
    var a = Math.ceil(this.alldata.length / Number(this.shared.pageSize));
    if (a === event) {
      this.shared.pageData = Math.min(
        this.shared.StartLimit + Number(this.shared.pageSize),
        this.alldata.length
      );
    } else {
      this.shared.pageData = Math.min(
        this.shared.StartLimit + Number(this.shared.pageSize),
        this.alldata.length - 1
      );
    }
  }

  onChange(event: any) {
    this.shared.pageSize = parseInt(event);
    this.handlePageChange((this.shared.page = 1));
  }

  async updateResults() {
    this.alldata = [];
    this.alldata = this.searchByValue(this.tempList);
    this.handlePageChange((this.shared.page = 1));
  }
  searchByValue(data: any) {
    return data.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (
          item.name
            ?.toString()
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.country?.name
            ?.toString()
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase())
        );
      }
    });
  }
  openDialog(item: any) {
    this.isEdit = true;
    //const approveRe='ApproveReject';
    const temp= {
        ...item,
        approveRe:this.Pending?true:false
      }


    this.passData.emit(temp);
    // this.myForm.get('countryId')?.setValue(item?.country.id),
    //   this.myForm.get('foreigninstitute')?.setValue(item.name),
    //   this.myForm.get('premiseNumber')?.setValue(item.premiseNumber)
    // this.myForm.get('address1')?.setValue(item.addressLine1)
    // this.myForm.get('address2')?.setValue(item.addressLine2)
    // this.myForm.get('locality')?.setValue(item.locality)
    // this.myForm.get('subLocality')?.setValue(item.subLocality)
    // this.myForm.get('pinCode')?.setValue(item.pinCode)
    // this.myForm.get('mobileNo')?.setValue(item.mobile)
    // this.myForm.get('email')?.setValue(item.emailId)
    // this.myForm.get('telephone')?.setValue(item.telephone)
    // this.myForm.get('webUrl')?.setValue(item.website)
    // this.myForm.get('id')?.setValue(item.id)
  }
  save() {
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
      id: this.myForm.value.id,
      locality: this.myForm.value.locality,
      mobile: this.myForm.value.mobileNo,
      name: this.myForm.value.foreigninstitute,
      pinCode: this.myForm.value.pinCode,
      premiseNumber: this.myForm.value.premiseNumber,
      recordStatusId: '1',
      subLocality: this.myForm.value.subLocality,
      telephone: this.myForm.value.telephone,
      userId: '',
      statusId: this.myForm.value.status,
      website: this.myForm.value.webUrl,
    };
    this.authService.saveForeignInstitutes(payload).subscribe(
      (res) => {
        if (res.status == 200) {
          let ele = {
            foreigninstitute: true,
            status: this.myForm.value.status,
          };
          this.shared.savePopUp(ele).subscribe((res) => {
            if (res) {
              const i = this.tempList.findIndex(
                (e) => e.id === this.myForm.value.id
              );
              if (i > -1) {
                this.tempList.splice(i, 1);
                this.alldata = [...this.tempList];
                this.handlePageChange((this.shared.page = 1));
                this.back();
              }
            }
          });
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
  back() {
    this.isEdit = false;
    this.myForm.reset();
  }
  more: boolean = true;
  moreInfo(val: string) {
    if (val === 'more') {
      this.more = true;
    } else {
      this.more = false;
    }
  }
}
