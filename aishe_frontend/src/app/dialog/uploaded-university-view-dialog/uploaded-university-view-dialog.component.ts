import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';


@Component({
  selector: 'app-uploaded-university-view-dialog',
  templateUrl: './uploaded-university-view-dialog.component.html',
  styleUrls: ['./uploaded-university-view-dialog.component.scss']
})
export class UploadedUniversityViewDialogComponent implements OnInit {
  cancelButtonText = "Close";
  element: any;
  nodalOfficerName: any;
  referenceNo: any;
  constructor(@Inject(MAT_DIALOG_DATA) public data:any,public sharedservice: SharedService,public authService: AuthService) { 
    this.element=data;
  }

  ngOnInit(): void {
    this.referenceNo=this.data.aisheCode + '-' +this.data.item.surveyYear
  }
  certificatedoc(){
  let payload = {
    instituteType: this.element.type,
    aisheCode: this.element.aisheCode,
    surveyYear: this.element.item.surveyYear,
  }
  this.authService.getOfficerList(payload).subscribe(res => {
    if (res.data && res.data.length) {
      res.data.forEach((ele: any) => {
        if (ele.universityPk.officerType === 'NO') {
          this.nodalOfficerName = ele.officerName;
          this.getCertificate()
        }
      })
    }
  }, err => {

  })
  
}

dataCaptureFormatDCF(){
  let payload = {
    aisheCode:  this.element.aisheCode,
    surveyYear: this.element.item.surveyYear,
  }
  this.authService.getDownloadFile1(payload,this.element.type )
}

techingInformation(){
  let payload = {
    aisheCode: this.element.aisheCode,
    surveyYear: this.element.item.surveyYear,
  }
  this.authService.getDownloadFile2(payload, this.element.type)
}

otherMinority(){
  let payload = {
    aisheCode:this.element.aisheCode,
    surveyYear:this.element.item.surveyYear,
  }
  if(payload.surveyYear<'2020'){
    this.authService.getDownloadFile3(payload,this.element.type)
  }else{
    this.sharedservice.showError('Survey Year must be less than 2020!')
  }
}

getCertificate() {
  let payload = {
    aisheCode:this.element.aisheCode,
    surveyYear: this.element.item.surveyYear,
    institutionName: "",
    nodalOfficerName: this.nodalOfficerName
  }
  this.authService.getCertificateDownload(payload);
}
}
