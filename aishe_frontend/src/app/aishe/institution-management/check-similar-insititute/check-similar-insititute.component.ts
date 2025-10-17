import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { GetService } from 'src/app/service/get/get.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import Validation, { CustomErrorStateMatcher } from 'src/app/shared/validation';

@Component({
  selector: 'app-check-similar-insititute',
  templateUrl: './check-similar-insititute.component.html',
  styleUrls: ['./check-similar-insititute.component.scss']
})
export class CheckSimilarInsitituteComponent implements OnInit {
  cimilarDataArr:any =[]
  similarForm:any = FormGroup
  isFormInvalid:boolean = false
  inValidState:boolean = false
  constructor(public sharedService: SharedService, public router: Router, public authService: AuthService, private route: ActivatedRoute, public localStore1: EncryptDecrypt, public localService: LocalserviceService,public dialog: MatDialog, public errorMatcher: CustomErrorStateMatcher,private fb: FormBuilder, private getService : GetService) { 
  this.similarForm = this.fb.group({
    requestId: ["", Validators.required],
    stateDistrict: [""],
  })

  }


  ngOnInit(): void {

  }

  reset(){
    this.similarForm.reset()
  }
   


  onChange(event: any) {
    this.sharedService.pageSize = parseInt(event);
    this.handlePageChange(this.sharedService.page = 1);
  }
  handlePageChange(event: any) {
    this.sharedService.page = event;
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize);
    var a = Math.ceil(this.cimilarDataArr.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.cimilarDataArr.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.cimilarDataArr.length - 1);
    }
  }




  getSimilarInsitute(){
    let stateValid = this.similarForm.controls['stateDistrict'].value
    if(this.similarForm.invalid){
      this.sharedService.showWarning()
      this.isFormInvalid = true
      return
    }
    else{
      this.isFormInvalid = false
    }

    if(stateValid === '' || stateValid === null){
      console.log('1')
      this.inValidState = true

    }

    let payload = {
      underState : this.similarForm.controls['stateDistrict'].value,
      requestId : this.similarForm.controls['requestId'].value
    }
    console.log('payload', payload)
    this.getService.getSimilarInsitute(payload).subscribe(res =>{
      if(res.length > 0){
        this.cimilarDataArr = res
        this.inValidState = false
      }
      else{
        this.cimilarDataArr = []
        this.sharedService.showValidationMessage('Record Not Found!!')
        // this.sharedService.showWarning()
        // this.sharedService.errorMessage()
      }
      console.log('response', res.length)
    })
  }

  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }

}
