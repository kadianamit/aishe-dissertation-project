import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { error } from 'console';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-approve-reject-university-list',
  templateUrl: './approve-reject-university-list.component.html',
  styleUrls: ['./approve-reject-university-list.component.scss'],
})
export class ApproveRejectUniversityListComponent implements OnInit {
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  listData: Array<any> = [];
  statusList: Array<any> = [];
  tempListData: Array<any> = [];
  searchText: any = '';
  showForm: boolean = false;
  affiliate = 'Affiliated';
  deaffiliate = 'Deaffiliate';

  instiForm = new FormGroup({
    aisheCodeValue: new FormControl(),
    stateValue: new FormControl('', Validators.required),
    collegeName: new FormControl({ value: '', disable: true }),
    universityValue: new FormControl('', Validators.required),
    districtValue: new FormControl(),
    oldAffiliatedUniversity: new FormControl(),
    Deaffiliatee: new FormControl(),
  });
  stateList: any;
  filterStateList: any;
  roleId: any;
  UniSurveyYear: any;
  state: any;
  universityList: any;
  filtereduniversityList: any;
  dataDeaffiliation: any;
  deAffUniversityId12: any;
  filterDeaffiliation: any;
  userId1: any;
  stateLgdCode: any;
  districtId: any;
  status: any



  constructor(
    private institutionmanagement: InstitutionmanagementService,
    public localService: LocalserviceService,
    private route: ActivatedRoute,
    public sharedService: SharedService,
    private authService: AuthService,
    public incy: EncryptDecrypt
  ) {
    this.roleId = this.localService.getData('roleId');
    this.userId1 = this.localService.getData('userId');

  }

  ngOnInit(): void {

    // this.authService.getUser(this.userId1).subscribe((res:any) => {
    //   if (res) {
    //     this.stateLgdCode = res.stateLgdCode;
    //     this.districtId = res.districtId;
    //     this.deAffUniversityId12=res.stateLevelBody;

    //     this.instiForm.get('aisheCodeValue')?.setValue(res.aisheCode);
    //     this.instiForm.get('collegeName')?.setValue(res.instituteName);
    //     this.instiForm.get('stateValue')?.setValue(res.stateName);
    //     this.instiForm.get('districtValue')?.setValue(res.districtName);
    //     this.instiForm.get('Deaffiliatee')?.setValue(res.stateLevelBody);

    // this.findOnData(res.stateLevelBody);

    // } else {
    this.getUserDetails();
    // }
    // });
    //this.loadUniversityData();

  }
  getUserDetails() {
    this.authService.getUser(this.userId1).subscribe((res: any) => {
      if (res.status === 200) {
        this.stateLgdCode = res.data.stateLgdCode;
        this.districtId = res.data.districtId;
        this.instiForm.get('aisheCodeValue')?.setValue(res.data.aisheCode);
        this.instiForm.get('collegeName')?.setValue(res.data.instituteName);
        this.instiForm.get('stateValue')?.setValue(res.data.stateName);
        this.instiForm.get('districtValue')?.setValue(res.data.districtName);
        this.instiForm.get('Deaffiliatee')?.setValue(res.data.stateLevelBody);

        // this.findOnData(res.data.stateLevelBody);
        this.deAffUniversityId12 = res.data.stateLevelBody;
        // console.log('99',this.deAffUniversityId12)

      }
    });
  }

  findOnData(data: any) {
    let payload = {}
    if (data == 'Deaffiliate') {
      payload = {
        deAffiliateUniversityId: this.deAffUniversityId12
      };
    } else {
      payload = {
        affiliateUniversityId: this.deAffUniversityId12
      };
    }
    this.authService.approveRejectUniversit(payload).subscribe(
      (res) => {
        if (res.statusCode === 'AISH001') {
          if (data === 'Affiliate') {
            this.listData = res.masterResponse.filter((e: any) => e.requestStatusId === 2)
          } else {
            this.listData = res.masterResponse.filter((e:any)=>e.requestStatusId === 1);
          }

        }
      },
      (error) => {
        // Handle error if needed
        console.error('Error:', error);
      }
    );


  }

  showActionColumn(): boolean {
    return this.listData.some(item =>
      (this.status === 'Deaffiliate' && item.requestStatusId === 1) ||
      (this.status === 'Affiliate' && item.requestStatusId === 2)
    );
  }


  edit(data: any, i: any) {
    let ele = {
      data: data,
      status: this.status
    }
    this.sharedService.approveReject(ele).subscribe((res) => {
      if (res) {
        this.findOnData(this.status)
      }
    })
  }
  delete(data: any) { }
  handlePageChange(event: any) {
    this.page = event;
    (this.StartLimit = (this.page - 1) * Number(this.pageSize)),
      (this.EndLimit = this.StartLimit + Number(this.pageSize));
    var a = Math.ceil(this.listData.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(
        this.StartLimit + Number(this.pageSize),
        this.listData.length
      );
    } else {
      this.pageData = Math.min(
        this.StartLimit + Number(this.pageSize),
        this.listData.length - 1
      );
    }
  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange((this.page = 1));
  }

  async updateResults() {
    this.listData = [];
    this.listData = this.searchByValue(this.tempListData);
    this.handlePageChange((this.page = 1));
  }

  searchByValue(userData: any) {
    return userData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (
          item.title
            ?.toString()
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.documentType
            ?.toString()
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.dateTime
            ?.toString()
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.fileName
            ?.toString()
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase())
        );
      }
    });
  }
}
function afterClose(arg0: boolean) {
  throw new Error('Function not implemented.');
}

