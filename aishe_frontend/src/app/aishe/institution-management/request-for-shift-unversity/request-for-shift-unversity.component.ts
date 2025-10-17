import { SharedService } from 'src/app/shared/shared.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { error } from 'console';

@Component({
  selector: 'app-request-for-shift-unversity',
  templateUrl: './request-for-shift-unversity.component.html',
  styleUrls: ['./request-for-shift-unversity.component.scss'],
})
export class RequestForShiftUnversityComponent implements OnInit {
  surveyYearList: any;

  instiForm = new FormGroup({
    aisheCodeValue: new FormControl(),
    surveyYearValue: new FormControl('', Validators.required),
    stateValue: new FormControl('', Validators.required),
    collegeName: new FormControl({ value: '', disable: true }),
    universityValue: new FormControl('', Validators.required),
    reasonName: new FormControl(),
    districtValue: new FormControl(),
    oldAffiliatedUniversity: new FormControl(),
    DeAffiliateDe: new FormControl(),
  });
  stateList: any;
  filterStateList: any;
  roleId: any;
  UniSurveyYear: any;
  state: any;
  universityList: any;
  filtereduniversityList: any;
  dataDeaffiliation: any;
  universityId12: any;
  filterDeaffiliation: any;
  userId1:any;
  stateLgdCode:any;
  districtId:any
  constructor(
    private institutionmanagement: InstitutionmanagementService,
    public localService: LocalserviceService,
    private route: ActivatedRoute,
    private sharedService: SharedService,
    private authService: AuthService,
    public incy:EncryptDecrypt,
  ) {
    this.roleId = this.localService.getData('roleId');
    this.userId1=this.localService.getData("userId")
  }

  ngOnInit(): void {
    this.instiForm.controls['aisheCodeValue'].disable();
    this.instiForm.controls['collegeName'].disable();
    this.instiForm.controls['stateValue'].disable();
    this.instiForm.controls['districtValue'].disable();
    this.instiForm.controls['DeAffiliateDe'].disable();
    this.instiForm.controls['reasonName'].disable();
    this.instiForm.controls['oldAffiliatedUniversity'].disable();
    const UniversityId = this.localService.getData('stateLevelBody');
    this.universityId12 = UniversityId;
    this.sharedService.getUserDetails.subscribe((res) => {
      if (res !== 0) {
        this.stateLgdCode=res.stateCode;
        this.districtId=res.districtCode;
        this.instiForm.get('aisheCodeValue')?.setValue(res.aisheCode);
        this.instiForm.get('collegeName')?.setValue(res.instituteName);
        this.instiForm.get('stateValue')?.setValue(res.stateName);
        this.instiForm.get('districtValue')?.setValue(res.districtName);
        this.instiForm.get('DeAffiliateDe')?.setValue(res.stateLevelBody);
      } else {
        this.getUserDetails();
      }
    });

    this.surveyYear();
    this.getDeaffiliationReason();
    this.loadUniversityData();

   
  }
  getUserDetails() {

    this.authService.getUser(this.userId1).subscribe((res: any) => {
      if (res.status === 200) {
        this.stateLgdCode=res.data.stateCode;
        this.districtId=res.data.districtId;
        this.instiForm.get('aisheCodeValue')?.setValue(res.data.aisheCode);
        this.instiForm.get('collegeName')?.setValue(res.data.instituteName);
        this.instiForm.get('stateValue')?.setValue(res.data.stateName);
        this.instiForm.get('districtValue')?.setValue(res.data.districtName);
        this.instiForm.get('DeAffiliateDe')?.setValue(res.data.stateLevelBody);

        this.universityId12 = res.data.stateLevelBody11;
      }
    });
  }
  findOnData() {
    if(this.instiForm.invalid){
      this.sharedService.showWarning();
      return;
    }
    this.instiForm.controls['aisheCodeValue'].enable();
    this.instiForm.controls['collegeName'].enable();
    this.instiForm.controls['stateValue'].enable();
    this.instiForm.controls['districtValue'].enable();
    this.instiForm.controls['DeAffiliateDe'].enable();
    this.instiForm.controls['reasonName'].enable();
    this.instiForm.controls['oldAffiliatedUniversity'].enable()
    const years = this.instiForm.controls['surveyYearValue'].value;
    const survey = years.split('-')[0];

    let pay = {
      affiliatingUniversityId: this.instiForm.controls['universityValue'].value,
      aisheCode: this.instiForm.controls['aisheCodeValue'].value,
      deaffiliatingSurveyYear: +survey,
      deaffiliatingUniversityId: this.instiForm.controls['DeAffiliateDe'].value,
      districtCode: this.districtId,
      instituteName: this.instiForm.controls['collegeName'].value,
      //isDcfApplicable: true,
      stateCode: this.stateLgdCode
     // typeId: 'string',
    };

    this.authService.requestShiftUniversit(pay).subscribe((res) => {

      if (res.statusCode === "AISH002") {
        this.sharedService.showError(res.statusDesc)
      }if(res.statusCode==='AISH001') {
        this.sharedService.showSuccessMessage('Submitted Successfully !!!')
      }
      if(res.status==422){
        this.sharedService.showError(res.message)
      }
    },error => {
      this.sharedService.showError(error.error.message)
    })


      this.instiForm.controls['aisheCodeValue'].disable();
      this.instiForm.controls['collegeName'].disable();
      this.instiForm.controls['stateValue'].disable();
      this.instiForm.controls['districtValue'].disable();
      this.instiForm.controls['DeAffiliateDe'].disable();
      this.instiForm.controls['reasonName'].disable();
      this.instiForm.controls['oldAffiliatedUniversity'].disable();

  }
  getDeaffiliationReason() {
    this.institutionmanagement.getReasonDeaffileate().subscribe((res) => {
      const data = res.reasonId.slice();
      const res1 = data.filter((item: any) => item.id === 1);
      this.dataDeaffiliation = res1[0].name;
      this.instiForm.controls['reasonName'].setValue(this.dataDeaffiliation);
      this.filterDeaffiliation = this.dataDeaffiliation.slice();
    });
  }
  surveyYear() {
    this.institutionmanagement.getSurveyYearListPrivate().subscribe((res) => {
      this.surveyYearList = res;
    });
  }

  loadUniversityData() {
    this.institutionmanagement.getUniversity('', 0).subscribe((res) => {
      if (res) {
        const ds = res;
        const data = ds.slice();
        const temp = data.filter(
          (item: any) => item.id === this.universityId12
        );
        const temp1 = data.filter(
          (item: any) => item.id !== this.universityId12
        );
        this.universityList = temp1;
        this.instiForm.get('oldAffiliatedUniversity')?.setValue(temp[0].name);
        this.filtereduniversityList = this.universityList.slice();
      
      }
    });
  }
}
