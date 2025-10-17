import { AfterViewInit, Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Common } from 'src/app/common/common';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.scss']
})
export class ConfirmDialogComponent implements OnInit,AfterViewInit {
  roleId:any;
  collegeName:string='';
  remarks:any;
  deaffiliation:any;
  surveyYearList: any;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<ConfirmDialogComponent>,private institutionmanagement: InstitutionmanagementService,public localService: LocalserviceService) {
      this.roleId=this.localService.getData('roleId');
  }
 ngOnInit(): void {
   if(this.data.Deaffiliation || this.data.s1==='standaloneStatus'){
    this.surveyYear();
   }else{
    this.formStatusData.controls['surveyYearValue'].clearValidators();
    this.formStatusData.controls['surveyYearValue'].updateValueAndValidity();
   }
 }
  formStatusData=new FormGroup({
    surveyYearValue:new FormControl(''),
    deaffiliation: new FormControl('',Validators.required),
    remarks:new FormControl('',[Validators.required ,Common.noWhitespaceValidator]),
    isInactiveUser:new FormControl(true,Validators.required),
  })
  ngAfterViewInit() {
    setTimeout(() => {
      this.formStatusData.patchValue({ isInactiveUser: true });
    });
  }
  onConfirmClick(): void {
    this.dialogRef.close(true);
  }
  OnStatus(){
   
    if(this.formStatusData.value.remarks ){
      this.dialogRef.close(this.formStatusData.value);
    }
   
  }
  OnStatus1(){
    if(this.formStatusData.value.deaffiliation && this.formStatusData.value.remarks && !this.formStatusData.invalid){
      this.dialogRef.close(this.formStatusData.value);
    }
  }
  onConfirmClickDelete(): void {
    if(this.remarks && this.remarks.trim().length !==0){
      this.dialogRef.close(this.remarks.trim());

    }
   
  }
  onConfirm(){
    this.dialogRef.close(true);
  }

  key(res:any){
    
    let key = Object.keys(res.data1)[0]
   this.collegeName = res.data1[key];
  return  Object.keys(res.data1)[0];
  }
  surveyYear() {
    this.institutionmanagement.getSurveyYearList().subscribe((res) => {
      this.surveyYearList = res;
      //console.log("this.surveyYearList:",this.surveyYearList)
    });
    this.formStatusData.controls['surveyYearValue'].setValidators([Validators.required]);
    this.formStatusData.controls['surveyYearValue'].updateValueAndValidity();
  }

}
