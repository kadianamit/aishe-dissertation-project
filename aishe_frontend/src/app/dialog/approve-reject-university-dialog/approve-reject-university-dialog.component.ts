import { SharedService } from 'src/app/shared/shared.service';
import { Console } from 'console';
import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';

@Component({
  selector: 'app-approve-reject-university-dialog',
  templateUrl: './approve-reject-university-dialog.component.html',
  styleUrls: ['./approve-reject-university-dialog.component.scss'],
})
export class ApproveRejectUniversityDialogComponent implements OnInit {
  surveyYearList: any;
  issubmit: boolean = false;
  data:any
  instiForm = new FormGroup({
    aisheCodeValue: new FormControl(),
    stateValue: new FormControl('', Validators.required),
    collegeName: new FormControl({ value: '', disable: true }),
    affiliatingUniversityName: new FormControl('', Validators.required),
    districtValue: new FormControl(),
    surveyYearValue: new FormControl('', Validators.required),
    remarks: new FormControl('', Validators.required),
  });
  constructor(
    private institutionmanagement: InstitutionmanagementService,
    public dialogRef: MatDialogRef<ApproveRejectUniversityDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any,
    public authService: AuthService,
    private sharedService: SharedService
  ) {
    this.data = this.element
    console.log(this.data)
  }

  ngOnInit(): void {
    this.reset();
    this.instiForm.get('aisheCodeValue')?.setValue('C-' + this.data.data.aisheCode);
    this.instiForm.get('stateValue')?.setValue(this.data.data.stateName);
    this.instiForm.get('collegeName')?.setValue(this.data.data.instituteName);
    this.instiForm
      .get('affiliatingUniversityName')
      ?.setValue(this.data.data.affiliatingUniversityName);
    this.instiForm.get('districtValue')?.setValue(this.data.data.districtName);
    this.instiForm.controls['aisheCodeValue'].disable();
    this.instiForm.controls['stateValue'].disable();
    this.instiForm.controls['collegeName'].disable();
    this.instiForm.controls['affiliatingUniversityName'].disable();
    this.instiForm.controls['districtValue'].disable();
    this.surveyYear();
  }
  surveyYear() {
    this.institutionmanagement.getSurveyYearListPrivate().subscribe((res) => {
      this.surveyYearList = res;
    });
  }
  save() {
    if (this.instiForm.invalid) {
      this.issubmit = true
      this.sharedService.showWarning();
      return;
    }
    if (this.instiForm.valid) {
      this.issubmit = false
      const year = this.instiForm.value.surveyYearValue.split('-')[0];
      let temp = {
        aisheCode: 'C-' + this.data.data.aisheCode,
        id: this.data.data.id,
        isOldUniversity: this.element.status === 'Deaffiliate'?true:false,
        remarks: this.instiForm.value.remarks,
        status: this.element.status === 'Deaffiliate'?2:3,
        surveyYear: +year,
      };
      this.authService.approveUniversity(temp).subscribe(
        (res) => {
          this.dialogRef.close(true);
          if (res.statusCode === 'AISH001') {
            this.sharedService.showSuccessMessage(res.statusDesc);
          }
          if (res.statusCode === 'AISH001') {
            this.sharedService.showSuccessMessage(res.statusDesc);
          }
          if (res.statusCode === 'AISH11') {
            this.sharedService.showSuccessMessage(res.statusDesc);
          }
        },
        (error) => {
          this.sharedService.showSuccessMessage(error.statusDesc);
        }
      );
    }
  }
  reset() {
    this.instiForm.controls['remarks'].reset();
    this.instiForm.controls['surveyYearValue'].reset();
  }
}
