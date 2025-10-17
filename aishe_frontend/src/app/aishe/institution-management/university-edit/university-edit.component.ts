import { Component, Input, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  AbstractControl,
} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { error } from 'console';

@Component({
  selector: 'app-university-edit',
  templateUrl: './university-edit.component.html',
  styleUrls: ['./university-edit.component.scss'],
})
export class UniversityEditComponent implements OnInit {
  @Input()
  instiFormData: any;
  surveyYear1: any;
  id: any;
  dataCheck: boolean = false;
  Name1: any;
  NameData: any;
  stateName: any;
  districtName: any;
  location1: any;
  Standalonetype: any;
  standalonTypeData: any;
  universityTypeData: any;
  universityTypeV: any;
  isEdit: boolean = false;
  showEdit = false;
  showTable: boolean = false;
  userDataTable: any[] = [];
  managmentData: any;
  filteredmanagementT: any = [];
  universityTypefilter: any = [];
  userData: any;
  dataDistrict: any;
  filteredDistrict: any;
  standalonTypefilter: any;
  filteredState: any = [];
  tabledata: boolean = false;
  userData1: any = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  roleId: any;
  locationData: any;
  showStandalone: boolean = false;
  tableSize: number[] = [10, 20, 30, 40, 50];
  submitted = false;
  arr: any;
  temp: any;
  subtype: any = [];
  validPattern = /^[a-zA-Z !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{1,250}$/;
  form = new FormGroup({
    Name: new FormControl('', [
      Validators.required,
      Validators.pattern(this.validPattern),
    ]),
    stateName: new FormControl('', [Validators.required]),
    subType: new FormControl([]),
    districtName: new FormControl('', [Validators.required]),
    location: new FormControl('', []),
    universityType: new FormControl(),
    remarks: new FormControl('', [
      Validators.required,
      Validators.maxLength(2000),
      // Validators.minLength(3)
    ]),
  });

  constructor(
    public sharedservice: SharedService,
    private institutionmanagement: InstitutionmanagementService,
    private dialog: MatDialog,
    public localService: LocalserviceService
  ) {}
  subTypeLength:number=0
  ngOnInit(): void {
    this.findData();
    this.getStateData();
    this.loadUniversityType();
    this.getLocation();
    // this.form.get('subType')?.valueChanges.subscribe(updatedValue => {
    //     this.subTypeLength = updatedValue.length;
    // });
  }

  getLocation() {
    this.institutionmanagement.getLocation().subscribe((res) => {
      this.locationData = res.location;
    });
  }
  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  resetdata(): void {
    this.userDataTable = [];
    this.page = 1;
    this.handlePageChange(this.page);
    this.searchText = null;
    this.pageSize = 10;
  }
  findData() {
    this.searchText = null;
    if (!this.instiFormData.value.surveyYearValue) {
      this.sharedservice.showError('Please Select Survey Year');
      return;
    }
    // if (!this.instiFormData.value.stateValue) {
    //   this.sharedservice.showError('Please Select State');
    //   return;
    // }

    let data = {
      stateValue: this.instiFormData.value.stateValue
        ? this.instiFormData.value.stateValue?.trim()
        : '',
      surveyYearValue: this.instiFormData.value.surveyYearValue.split('-')[0],
      universityType: this.instiFormData.value.universityType
        ? this.instiFormData.value?.universityType?.trim()
        : '',
    };
    this.institutionmanagement.getViewUniversity(data).subscribe((res) => {
      this.userDataTable = res.universityListBean;
      this.showTable = true;
      this.page = 1;
      this.handlePageChange(this.page);
      if (res.statusCode == 'AISH099') {
        this.sharedservice.showError(res.statusDesc);
      }
    });
  }
  alphaNumberOnly(e: any) {
    // Accept only alpha numerics, not special characters
    var regex = new RegExp('[a-zA-Z ,]');
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
      if (e.target.value.substr(-1) === ' ' && e.code === 'Space') {
        e.preventDefault();
      }
      return true;
    }

    e.preventDefault();
    return false;
  }
  compareFn(t1: any, t2: any): boolean {
    return t1 && t2 ? t1 === t2 : t1 === t2;
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  findManagmentType() {
    let payload = { type: 'U' };
    this.institutionmanagement.getmanagmentType2(payload).subscribe((res) => {
      this.managmentData = res;
      //console.log(res)
      this.filteredmanagementT = this.managmentData.slice();
    });
  }

  getStateData() {
    this.institutionmanagement.getState().subscribe((res) => {
      this.userData = res;
      this.filteredState = this.userData.slice();
    });
  }
  districtfilter() {
    this.dataCheck = true;
    this.form.controls['districtName'].reset();
  }
  getdistrictName() {
    if (this.form.value.stateName) {
      let stateName1 = this.userData.filter(
        (data: any) => data.name === this.form.value.stateName
      );

      this.institutionmanagement
        .getdistrict(stateName1[0].stateCode)
        .subscribe((res) => {
          this.dataDistrict = res;
          this.filteredDistrict = this.dataDistrict.slice();
          // console.log('this.form.value.stateName', this.dataDistrict);
        });
      // console.log('this.form.value.stateName', this.form.value.stateName);
    }
  }

  loadUniversityType() {
    this.institutionmanagement.getUniversityType().subscribe(
      (res) => {
        // this.universityTypeData = res;
        // this.universityTypefilter = this.universityTypeData.slice();
        this.universityTypeData =
          res?.filter((id: any) => id.id !== '12') || [];
        this.universityTypefilter = [...this.universityTypeData];
        console.log(this.universityTypefilter);
        this.temp = [...res];
      },
      (error) => {}
    );
  }



  editUser(data: any) {
    if (data.isDcfApplicable != 'false') {
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        width: '34%',
        data: {
          message1: 'edit',
          surveyYear: this.instiFormData.value.surveyYearValue,
        },
      });
      return;
    }
    this.showTable = false;
    this.showEdit = true;
    this.isEdit = true;
    this.surveyYear1 = this.instiFormData.value.surveyYearValue;
    this.id = data.aishecode;
    //this.NameData = data.name;
    this.form.get('Name')?.setValue(data.name);
    this.Name1 = data.name;
    this.stateName = data.statename;
    this.location1 = data.location;
    this.universityTypeV = data.universityType;
    this.districtName = data.districtname;
    this.form.controls['location'].setValue(this.location1);
    this.form.controls['universityType'].setValue(this.universityTypeV);
    this.form.controls['districtName'].setValue(this.districtName);
    this.form.controls['stateName'].setValue(this.stateName);
    if(data?.subTypes?.length>0){
      this.subTypeLength=data?.subTypes.length
      this.form
      ?.get('subType')
      ?.setValue(data?.subTypes.map((item: any) => item.id));
      this.loadSubType(data.universityType);

    }else{
      this.loadSubType(data?.universityType);
    }

    this.getdistrictName();

  }
  loadSubType(e: any): void {

    this.subtype=[];
    const e1 = this.temp?.filter((item: any) => item.type === e);
    this.arr =
      e1[0]?.id === '01'
        ? ['07', '12']
        : e1[0]?.id === '07'
        ? ['01', '12']
        : [];
    const data =
      this.temp?.filter((element: any) => this.arr.includes(element.id)) || [];
    this.subtype = [...data];

    let subTypeControl = this.form.get('subType');

    if (this.subtype.length > 0) {
      subTypeControl?.setValidators([Validators.required]);
    } else {
      subTypeControl?.clearValidators();
      this.form.get('subType')?.reset();
    }

    subTypeControl?.updateValueAndValidity();
  }
  backClicked() {
    this.showTable = true;
    this.isEdit = false;
    this.showEdit = false;
    this.submitted = false;
    this.form.reset();
  }
  checkForWhiteSpace() {
    let aremark = this.form.get('remarks')?.value?.trim();
    this.form.get('remarks')?.setValue(aremark);
  }
  submit() {
    this.checkForWhiteSpace();
    this.submitted = true;

    if (!this.form.valid) {
      this.sharedservice.showError('Invalid details, Please provide valid details.');
      return;
    }

    const stateName1 = this.userData.find((data: any) => data.name === this.form.value.stateName);
    const districtName1 = this.dataDistrict.find((person: any) => person.name === this.form.value.districtName);
    const universityType1 = this.universityTypeData.find((p: any) => p.type === this.form.value.universityType);

    const trimmedName = this.form.value?.Name?.trim() ?? '';
    const trimmedRemarks = this.form.value?.remarks?.trim() ?? '';

    if (
      this.Name1 === trimmedName &&
      this.stateName === this.form.value.stateName &&
      this.districtName === this.form.value.districtName &&
      this.universityTypeV === this.form.value.universityType &&
      trimmedRemarks && this.subTypeLength === this.form.value.subType.length
    ) {
      this.dialog.open(ConfirmDialogComponent, {
        width: '20%',
        data: { message: 'Details of the Institute are same as previous!!!', s1: '12' },
      });
      return;
    }

    if (
      trimmedName ||
      this.form.value.stateName ||
      this.form.value.districtName ||
      this.form.value.universityType ||
      trimmedRemarks ||
      this.form.value.location || this.subTypeLength !== this.form.value.subType.length
    ) {
      //this.subTypeLength = 0
      const type =
        Array.isArray(this.form.value?.subType) && Array.isArray(this.temp)
          ? this.temp
              .filter((item: any) => this.form.value.subType.includes(item?.id))
              .map((item: any) => item?.type) ?? []
          : [];

      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        data: {
          message: 'Details of the Institute are as below, Please confirm the details.',
          state: this.form.value.stateName ?? 'N/A',
          district: this.form.value.districtName ?? 'N/A',
          collegeType12: this.form.value.universityType ?? 'N/A',
          subtype: type.length ? type.join(', ') : "",
          Name: trimmedName,
          remarks: trimmedRemarks,
          universityType: 'universityType',
        },
      });

      const userData = {
        districtCode: districtName1?.distCode ?? '',
        id: this.id ?? '',
        name: trimmedName,
        remarks: trimmedRemarks,
        stateCode: stateName1?.stateCode ?? '',
        typeId: universityType1?.id ?? '',
        subTypes: Array.isArray(this.form.value.subType) ? this.form.value.subType : [],
        surveyYear: this.instiFormData.value?.surveyYearValue?.split('-')?.[0] ?? '',
        userId: this.localService.getData('userId') ?? '',
      };

      dialogRef.afterClosed().subscribe((confirmed: boolean) => {
        if (confirmed) {
          this.institutionmanagement.postUniversityEditData(userData).subscribe((res) => {
            this.submitted = false;

            switch (res.statusCode) {
              case 'AISH011':
                this.sharedservice.showError('Editing University Data not allowed');
                break;
              case 'AISH001':
                this.sharedservice.showSuccessMessage('University Data Edited Successfully');
                this.findData();
                this.backClicked();
                this.form.reset();
                break;
              case 'AISH102':
                this.sharedservice.showError('University Name Already Exists');
                break;
              case 'AISH002':
                this.sharedservice.showError(res.statusDesc);
                break;
              default:
                this.sharedservice.showError('An unexpected error occurred.');
            }
          });
        }
      });
    }
  }



  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
    //this.findData();
  }

  handlePageChange(event: number) {
    this.page = event;
    let fgh = parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * fgh),
      (this.EndLimit = this.StartLimit + fgh);
    var a = Math.ceil(this.userDataTable.length / fgh);
    if (a === event) {
      this.pageData = Math.min(
        this.StartLimit + fgh,
        this.userDataTable.length
      );
    } else {
      this.pageData = Math.min(
        this.StartLimit + fgh,
        this.userDataTable.length
      );
    }
  }
}
