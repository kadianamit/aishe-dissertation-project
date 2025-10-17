import { Component, Input, OnInit } from '@angular/core';

import { SharedService } from 'src/app/shared/shared.service';

import { Location } from '@angular/common';

import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import { FormGroup, FormControl, AbstractControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DeleteDialogComponent } from 'src/app/dialog/delete-dialog/delete-dialog.component';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'app-deletecollege',
  templateUrl: './deletecollege.component.html',
  styleUrls: ['./deletecollege.component.scss']
})
export class DeletecollegeComponent implements OnInit {
  @Input()
  instiFormData: any;
  state:any
  id:any
  showEditTables:boolean = false
  surveyYear1:any
  count: number = 0;
  dataDistrict:any
  universityList:any;
  UniSurveyYear:any
  tabledata: boolean = true;
  userData1: any = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  hostel: any[] = [];
  roleId:any;
  userData: any = [];
  userDataTable:any = [];
  isEdit:boolean=false
  managmentData:any;
  managementType:any;
  filteredmanagementT:any[]=[];
  tableSize: number[] = [10, 20, 30, 40, 50];
  submitted = false;
  usercollegeTypeData: any[] = [];
  dataUniversityId1: any[] = [];
  surveyYearList: any;
  selectedYearValue: any = '';
  showTable: boolean=false;
  surveyYearValue:any
  collegeTypeData:any
  collegeName: any | null;
  collegeType: any | null;
  districtName: any | null;
  stateName: any | null;
  collegeName1:any
  filteredcollegeTypeData:any
  filtereduniversityList:any
  filteredState:any
  form = new FormGroup({
    collegeName: new FormControl(),
    collegeType: new FormControl(),
    stateName: new FormControl(),
    districtName: new FormControl(),
    remarks: new FormControl('', [Validators.required,Validators.maxLength(255),
      // Validators.minLength(3)
    ]),
    managementType:new FormControl(),
  });
  deleteData: boolean | undefined;
  constructor(
    private _location: Location,
    public sharedservice: SharedService,
    private dialog: MatDialog,
    private institutionmanagement: InstitutionmanagementService,public localService:LocalserviceService
  ) {}




  ngOnInit(): void {
    this.roleId = this.localService.getData('roleId')
    this.findData()
  }
  deleteCollegeData(){
    this.findData();
  }
  findData1(){
    this.userDataTable = [];
    this.pageSize = 10;
    this.searchText=null;
    this.page = 1;
    this.handlePageChange(this.page);
  }


  findData(){
    this.searchText=null;
       if(this.instiFormData.value.aisheCodeValue){
        var aisheId=this.instiFormData.value.aisheCodeValue
       }
    // if(!this.instiFormData.value.surveyYearValue){
    //   this.sharedservice.showError('Please Select Survey Year')
    //   return;
    // }
    if(!this.instiFormData.value.aisheCodeValue){
      if(!this.instiFormData.value.stateValue){
        this.sharedservice.showError('Please Select State')
        return;
      }
    }

    // if(!this.instiFormData.value.universityValue){
    //   this.sharedservice.showError('Please Select University Name')
    //   return;
    // }
    var formData;
    if(this.roleId==='26' || this.roleId==='1'){
      formData={
       "collegeType":this.instiFormData.value.collegeType,
       "surveyYearValue":this.instiFormData.value.surveyYearValue?this.instiFormData.value.surveyYearValue:'',
       "universityValue":this.instiFormData.value.universityValue?this.instiFormData.value.universityValue:'',
       "locationType":this.instiFormData.value.collegeLocation?this.instiFormData.value.collegeLocation:'',
       "selectedStateCode":this.instiFormData.value.stateValue?this.instiFormData.value.stateValue.trim():'',
       "districtValue":this.instiFormData.value.districtValue?this.instiFormData.value.districtValue.trim():'',
       "aisheCodeValue":aisheId?aisheId:'',
       "reasonId":this.instiFormData.value.reasonId ?this.instiFormData.value.reasonId:'',
     };
   }

       // console.log('this.localService.getData(',formData)

        this.institutionmanagement.getDeaffiliationCollegeList(formData).subscribe((res) =>{

          this.showTable = true;
          this.userDataTable =res.deaffiliationCollegeListBeans

          this.isEdit=false;
          this.page = 1;
          this.handlePageChange(this.page);
          if(res.statusCode=="AISH099"){
            this.findData1();
            this.sharedservice.showError(res.statusDesc)
          }

        })
      }
      // resetData1(){
      //   this.userData=[];
      //   this.searchText=null;
      //   this.page = 1;
      //   this.handlePageChange(this.page);
      // }

      back(): void {
        this.showTable = true;
        // this.deleteData = false;
        // this.isDelete = false;
      }

      deleteUser(event:any) {{
          const dialogRef = this.dialog.open(DeleteDialogComponent, {
            width:'38%',
            data: {
              message1: 'delete',
              surveyYear:this.instiFormData.value.surveyYearValue,
              data:[event,'DeleteCollege'],
            },
          });


        dialogRef.afterClosed().subscribe((confirmed) => {
          if (confirmed) {
            let data = {
              id: event.id,
              ipAddress: 'string',
              remarks: confirmed,
              // surveyYear: this.instiFormData.value.surveyYearValue?this.instiFormData.value.surveyYearValue.split('-')[0]:'',
              surveyYear:event.surveyYear,
              userId: this.localService.getData('userId'),
            };


        this.institutionmanagement.deleteCollegeData(data).subscribe((res:any) => {
          if (res.statusCode == 'AISH001') {
            this.sharedservice.showSuccessMessage('College has been deleted Successfully');
            this.showTable = true;
            this.deleteData = false;
            this.findData()
          }
          if(res.statusCode==='AISH024'){
            this.sharedservice.showError(res.statusDesc);
          }
          if(res.statusCode==='AISH025'){
            this.sharedservice.showError(res.statusDesc);
          }
          if(res.statusCode=='AISH011'){
            this.sharedservice.showError(res.statusDesc)
          }
        }
        
        );
      }
      } ) }

}

onChange(event: any): void {
  this.pageSize = parseInt(event);
  this.page = 1;
  this.handlePageChange(this.page);
  //this.getData1();
}

handlePageChange(event: number) {
  this.page = event;
  let fgh = parseInt(this.pageSize);
  (this.StartLimit = (this.page - 1) * fgh),
    (this.EndLimit = this.StartLimit + fgh);
  var a = Math.ceil(this.userDataTable.length / fgh);
  if (a === event) {
    this.pageData = Math.min(this.StartLimit + fgh, this.userDataTable.length);
  } else {
    this.pageData = Math.min(this.StartLimit + fgh, this.userDataTable.length);
  }
}

}


