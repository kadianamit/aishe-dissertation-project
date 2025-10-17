import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { GetService } from 'src/app/service/get/get.service';
import { PostService } from 'src/app/service/post/post.service';
import { SharedService } from 'src/app/shared/shared.service';
import { ViewportScroller } from '@angular/common';
import { LocalserviceService } from 'src/app/service/localservice.service';
@Component({
  selector: 'app-scholarship',
  templateUrl: './scholarship.component.html',
  styleUrls: ['./scholarship.component.scss']
})
export class ScholarshipComponent implements OnInit {
  submitted: boolean = false;
  surveyYearOption: any
  filterSurveyYearOption: any
  stateCode: any = 'ALL';
  scholarshiplist: Array<any> = [];
  isFormInvalid: boolean = false;
  scholarshipForm: FormGroup | any;
  userId: any
  encrypted: any;
  stateListArray: any[] = [];
  tempScholarshiplist: Array<any> = []
  stateList: any[] = [];
  searchText: any;
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  roleId: any;
  addOrEditIndex: any=null;
  buttonName:string='Add'
  showCard:boolean=false;
  constructor(public sharedService: SharedService, private authService: AuthService,
    public postService: PostService, private fb: FormBuilder, public getService: GetService, public router: Router, public route: ActivatedRoute,
    public viewportScroller: ViewportScroller,public localService: LocalserviceService) {

    this.scholarshipForm = this.fb.group({
      id: 0,
      surveyYear: ['2022', []],
      scholarshipScheme: ['', [Validators.required]],
      stateId:['',[]]

    })
    this.roleId = this.localService.getData('roleId')
    if (this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['SysAdmin']) {
      this.scholarshipForm.get('stateId').setValidators(Validators.required)
      this.scholarshipForm.get('stateId').updateValueAndValidity()
      this.getState()
    } else {
      this.scholarshipForm.get('stateId').clearValidators()
      this.scholarshipForm.get('stateId').updateValueAndValidity()
      this.stateCode = this.localService.getData('stateCode')
      this.getScholarshipdata()
    }
    this.loadSurveyYear();
  }

  ngOnInit(): void { }

  loadSurveyYear() {
    this.authService.getSurveyYearListPrivate().subscribe((res) => {
      let surveyYearList = res
      surveyYearList.forEach((element: any) => {
        if (element === "2022-2023") {
          var splitSurvey = element.substring(0, 5)
          var a = element.substring(7, 9);
          this.surveyYearOption = [splitSurvey + a];
        }
      });
      this.filterSurveyYearOption = this.surveyYearOption.slice()
    })
  }

  getScholarshipdata() {
    this.getService.scholarshipSchemePrivate(this.scholarshipForm.value, this.stateCode).subscribe((res: any) => {
      this.scholarshiplist = []
      this.scholarshiplist = res;
      this.scholarshiplist = this.scholarshiplist.filter(e=>e.id !== 99999)
      this.tempScholarshiplist = [...this.scholarshiplist];
      this.handlePageChange(this.page = 1)
    }, err => {

    });
  }

  delete(item: any) {
    this.sharedService.delete().subscribe(res => {
      if (res) {
        let payload = {
          "id": item.id,
          "schemeName": item.schemeName,
          "stateId": this.stateCode,
          "status": false,
          "surveyYear": item.surveyYear,
        }
        this.postService.scholarshipSchemePostdata(payload).subscribe((res: any) => {
          if (res.status === 200) {
            this.getScholarshipdata();
            this.sharedService.showDelete();
          }
        }, err => {

        })
      }
    })
  }

  scholarshipPostdata() {
    if (this.scholarshipForm.invalid) {
      this.submitted = true;
      this.sharedService.showWarning()
      return;
    } else {
      this.submitted = false;
    }

    for (let index = 0; index < this.scholarshiplist.length; index++) {
      if(this.roleId === '1' || this.roleId === '26'){
        if (this.addOrEditIndex !== index) {
          if(this.scholarshipForm.value.stateId.toString() === this.scholarshiplist[index].state?.stateCode.toString()){
            if (this.scholarshipForm.value.scholarshipScheme.toUpperCase().trim() === this.scholarshiplist[index].schemeName.toUpperCase().trim('')) {
              this.sharedService.showValidationMessage('Duplicate record not allowed !!!');
              return;
            }
          }
          
        }
      }else{
        if (this.addOrEditIndex !== index) {
          if (this.scholarshipForm.value.scholarshipScheme.toUpperCase().trim() === this.scholarshiplist[index].schemeName.toUpperCase().trim('')) {
            this.sharedService.showValidationMessage('Duplicate record not allowed !!!');
            return;
          }
        }
      }
      
    }
    let payload =
    {
      "id": this.scholarshipForm.value.id,
      "schemeName": this.scholarshipForm.value.scholarshipScheme,
      "stateId": this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['SysAdmin']?this.scholarshipForm.value.stateId:this.stateCode,
      "status": true,
      "surveyYear": this.scholarshipForm.value.surveyYear,
    };
    this.postService.scholarshipSchemePostdata(payload).subscribe((res: any) => {
      if (res.status === 200) {
        this.getScholarshipdata();
        this.reset()
        this.sharedService.showSuccess();
      }
    }, err => {

    })
  }
  edit(item: any,i:number) {
    this.addOrEditIndex = i
    this.buttonName = 'Update'
    this.scholarshipForm.get('id').setValue(item.id)
    this.scholarshipForm.get('scholarshipScheme').setValue(item.schemeName)
    this.scholarshipForm.get('surveyYear').setValue(item.surveyYear)
    if(this.roleId === this.sharedService.role['Ministry of Education'] || this.roleId === this.sharedService.role['SysAdmin']){
      this.scholarshipForm.get('stateId').setValue(item.state.stateCode)
    }
    this.showForm(true);
    this.viewportScroller.scrollToPosition([0, 0]);
  }
  getState() {
    this.authService.getStatePrivate().subscribe(
      (res) => {
        if (res && res.length) {
          this.stateListArray = [];
          this.stateListArray = res;
          this.stateListArray = this.stateListArray.sort((a, b) => a.name > b.name ? 1 : -1);
          this.stateList = this.stateListArray.slice();
          this.getScholarshipdata()
        }
      },
      (err) => { }
    );
  }

  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1)
  }

  async updateResults() {
    this.scholarshiplist = []
    this.scholarshiplist = this.searchByValue(this.tempScholarshiplist);
    this.handlePageChange(this.page = 1)
  }



  searchByValue(userData: any) {
    return userData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.schemeName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.surveyYear?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.state?.name?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  handlePageChange(event: any) {
    this.page = event
    this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
      this.EndLimit = this.StartLimit + Number(this.pageSize)
    var a = Math.ceil(this.scholarshiplist.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.scholarshiplist.length);
    } else {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.scholarshiplist.length - 1);
    }

  }
  showForm(value:boolean){
    this.showCard=value
  }
  close(){
    this.showCard=false;
    this.reset();
  }
  reset() {
    this.buttonName='Add'
    this.scholarshipForm.get('scholarshipScheme').setValue('')
    this.scholarshipForm.get('id').setValue(0)
    this.scholarshipForm.get('stateId').setValue('')
    this.addOrEditIndex=null
  }
}
