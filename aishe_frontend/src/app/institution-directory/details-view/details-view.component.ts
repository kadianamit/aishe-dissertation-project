import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-details-view',
  templateUrl: './details-view.component.html',
  styleUrls: ['./details-view.component.scss']
})
export class DetailsViewComponent implements OnInit {

  dataDirectory:any;
  id:any
  name:any;
  state:any;
  district: any;
  pincode: any;
  website: any;
  type: any;
  yearOfEstablishment: any;
  location: any;
  specialization: any;
  affiliatingUniversityWithAisheCode: any;
  staffQuarterTeaching: any;
  staffQuarterNonTeaching: any;
  staffQuarterTotal: any;
  faculty:any;
  department: any;
  placedMale:any;
  placedFemale: any;
  hostelBoys: any;
  hostelGirls: any;
  universityList:any;
  hostelCapacity:any;
  regularModeProg: any;
  distanceModeProg: any;
  privateModeProg: any;
  nonTeachingStaffSanctioned: any;
  nonTeachingStaffInPosition: any;
  teachingStaffSanctionedStrength: any;
  teachingStaffInPosition: any;
  intake: any;
  enrollmentRegular: any;
  enrollmentDistance: any;
  enrollmentPrivate: any;
  maleForeign: any;
  femaleForeign: any;
  address1: any;
  isCollege:any;
  isUniversity:any;
  isStandalone:any
  isNationalImportance:any;
  userData:any
  affiliatingUniversityName:any;
  affiliatingUniversityId:any;
  totalPlaced:any
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
  public sharedservice: SharedService,
  public institutionM: InstitutionmanagementService,
  private route: ActivatedRoute,
  private dialogRef: MatDialogRef<DetailsViewComponent>) { }

  ngOnInit(): void {//dataValue

    this.findData();
  
  }
findData(){
  let data=this.data.dataValue.aisheCode.replace(/\D/g, '');
  let survery=2020;
 this.userData=this.data.user;



  this.institutionM.getUniversityDetailsDirectory(this.userData,data,survery).subscribe((res)=>{
    
    this.universityList = res.institutionDetailsBean;
this.dataDirectory = res.institutionDetailsBean.id

  this.id= res.institutionDetailsBean.id;

  this.affiliatingUniversityName= res.institutionDetailsBean.affiliatingUniversityName;
  this.affiliatingUniversityId= res.institutionDetailsBean.affiliatingUniversityId;
this.name= res.institutionDetailsBean.name;
    this.state= res.institutionDetailsBean.state;
    this.district= res.institutionDetailsBean.district;
    this.pincode= res.institutionDetailsBean.pincode;
    this.website= res.institutionDetailsBean.website;
    this.type= res.institutionDetailsBean.type;
    this.yearOfEstablishment= res.institutionDetailsBean.yearOfEstablishment;
    this.location= res.institutionDetailsBean.location;
    this.specialization= res.institutionDetailsBean.specialization;
    this.affiliatingUniversityWithAisheCode= res.institutionDetailsBean.affiliatingUniversityWithAisheCode;
    this.staffQuarterTeaching= res.institutionDetailsBean.staffQuarterTeaching;
    this.staffQuarterNonTeaching= res.institutionDetailsBean.staffQuarterNonTeaching;
    this.staffQuarterTotal= res.institutionDetailsBean.staffQuarterTotal;
    this.faculty= res.institutionDetailsBean.faculty;
    this.department= res.institutionDetailsBean.department;
    this.placedMale= res.institutionDetailsBean.placedMale;
    this.placedFemale= res.institutionDetailsBean.placedFemale;
    this.hostelBoys= res.institutionDetailsBean.hostelBoys;
    this.hostelGirls= res.institutionDetailsBean.hostelGirls;
    this.hostelCapacity= res.institutionDetailsBean.hostelCapacity;
    this.regularModeProg= res.institutionDetailsBean.regularModeProg;
    this.distanceModeProg= res.institutionDetailsBean.distanceModeProg;
    this.privateModeProg= res.institutionDetailsBean.privateModeProg;
    this.nonTeachingStaffSanctioned= res.institutionDetailsBean.nonTeachingStaffSanctioned;
    this.nonTeachingStaffInPosition= res.institutionDetailsBean.nonTeachingStaffInPosition;
    this.teachingStaffSanctionedStrength= res.institutionDetailsBean.teachingStaffSanctionedStrength;
    this.teachingStaffInPosition= res.institutionDetailsBean.teachingStaffInPosition;
    this.intake= res.institutionDetailsBean.intake;
    this.enrollmentRegular= res.institutionDetailsBean.enrollmentRegular;
    this.enrollmentDistance= res.institutionDetailsBean.enrollmentDistance;
    this.enrollmentPrivate= res.institutionDetailsBean.enrollmentPrivate;
    this.maleForeign= res.institutionDetailsBean.maleForeign;
    this.femaleForeign= res.institutionDetailsBean.femaleForeign;
    this.address1= res.institutionDetailsBean.address1;
    this.totalPlaced = res.institutionDetailsBean.totalPlaced
//console.log("details",this.dataDirectory)
  });
  //https://api1.he.nic.in/aisheinstitutemanagement/institutionDirectory /getUniversityDetails?id=0099&surveyYear=2021

}

onConfirmClick(): void {
  this.dialogRef.close(true);
}
}
