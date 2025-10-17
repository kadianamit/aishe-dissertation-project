import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EncryptDecrypt } from 'src/app/service/encrypt-decrypt';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { NotificationService } from 'src/app/service/reports/notification.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-remuneration-status-dialog',
  templateUrl: './remuneration-status-dialog.component.html',
  styleUrls: ['./remuneration-status-dialog.component.scss']
})
export class RemunerationStatusDialogComponent implements OnInit {
  cancelButtonText: string = 'Close';
  confirmButtonText = "Save";
  userId: string | null;
  formdataRemuneration: FormGroup;
  isFormInvalid: boolean = false;
  userData: any;
  statusfilter: any;
  constructor(public dialogRef: MatDialogRef<RemunerationStatusDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any, public fb: FormBuilder,
    public authService: AuthService, public sharedService: SharedService,
    public sanitizer: DomSanitizer, public encrypt: EncryptDecrypt, public router: Router, public notify: NotificationService,public localService: LocalserviceService) {
    this.userId = this.localService.getData('userId');

    this.userData = this.localService.getData('userData');
    this.formdataRemuneration = this.fb.group({
      status: ['', [Validators.required]],
      remarks: ['', []]
    });
  }
  ngOnInit(): void {

    this.remunerationSatus();//464,463,528,435,

  }

  remunerationSatus(): void {
    this.authService.getReportRemunerationStatus().subscribe((res: any) => {
      if (res.status === 200) {
        this.statusfilter = res.data.filter((item:any)=>{return item.id !==1 &&item.id !==13});
      }
    })
  }

  Save() {
    if (this.formdataRemuneration.invalid) {
      return;
    }
    if(this.element.length > 0 && this.element.length){
    let temp: any = [];
    this.element.forEach((ele: any) => {
      temp.push({
        aisheCode: ele.aisheCode,
        remarks: this.formdataRemuneration.value.remarks,
        status: this.formdataRemuneration.value.status,
        surveyYear: ele.surveyYear,
        userId: ele.userId
      })
    })

    this.authService.saveRemunerationStatus(temp).subscribe((res: any) => {
      if (res.status === 200) {
        this.sharedService.showSuccessMessage(" Status of selected record(s) is/are updated successfully.");
        this.dialogRef.close({ success: true });
      }
    })
  }else{
    this.sharedService.showValidationMessage("Please select check box.")
  }
  }


  onCancel(): void {
    // Close the dialog without saving
    this.dialogRef.close({ success: false });
  }

}
