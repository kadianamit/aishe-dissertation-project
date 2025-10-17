import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-research-and-development-remarks-dialog',
  templateUrl: './research-and-development-remarks-dialog.component.html',
  styleUrls: ['./research-and-development-remarks-dialog.component.scss'],
})
export class ResearchAndDevelopmentRemarksDialogComponent implements OnInit {
  remarks: any = '';
  dataArray: any = [];
  alldocumentsvalid: any;
  approveR: any;
  isDocErrorVisisble: boolean = false;
  isRemarksErrorVisisble: boolean = false;
  isApproveErrorVisisble: boolean = false;
  isDocVisisble: boolean = true;
  approveList: any;
  userForm: FormGroup;
  isFormInvalid: boolean | undefined;
  requestId: any;
  dataEmail: any;
  email: any;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,

    private dialogRef: MatDialogRef<ResearchAndDevelopmentRemarksDialogComponent>,
    public authService: AuthService,
    private cdr: ChangeDetectorRef,
    public localService: LocalserviceService,
    private sharedService: SharedService,
    public dialog: MatDialog,
    private instManagementService: InstitutionmanagementService,
    public fb: FormBuilder
  ) {
    this.userForm = this.fb.group({
      approveR: [0, []],
      aRemarks: ['', [Validators.required]],
      alldocumentsvalid: ['', []],
    });

    this.approveList = [
      {
        id: 0,
        name: '--Please Select--',
      },
      {
        id: 1,
        name: 'Approve',
      },
      {
        id: 2,
        name: 'Reject',
      },
    ];
  }

  ngOnInit(): void {
    this.getApproveRND();
  }

  statusId(id: any) {
    if (id === '1') {
      this.isDocVisisble = true;
    }
    if (id === '2') {
      this.isDocVisisble = false;
    }
  }

  getApproveRND() {
    let payload = {
      stateCode: '',
      ministryId: '',
      requestId: this.data.requestId,
    };
    this.authService.getApproveRND(payload).subscribe((res) => {
      this.dataArray = res.rnDInstitutions[0];
      this.email = res.rnDInstitutions[0].nodalOfficerEmail;
    });
  }
  statusCheck(data: any) {
    if (data.value === 1) {
      this.isDocVisisble = true;
      this.userForm
        .get('alldocumentsvalid')
        ?.setValidators([Validators.required]);
      this.userForm.get('alldocumentsvalid')?.updateValueAndValidity();
    } else {
      this.isDocVisisble = false;
      this.userForm.get('alldocumentsvalid')?.clearValidators();
      this.userForm.get('alldocumentsvalid')?.updateValueAndValidity();
    }
  }
  //validation and payload
  private isFormInvalidInput(): boolean {
    const approveR = this.userForm.controls['approveR'].value;
    return this.userForm.invalid || approveR === 0;
  }
  private createPayload() {
    return {
      approverRoleId: this.localService.getData('roleId'),
      remark: this.userForm.controls['aRemarks'].value,
      requestId: this.data.requestId,
      statusId: this.userForm.controls['approveR'].value,
    };
  }
  //save data
  save() {
    if ((this.isFormInvalid = this.isFormInvalidInput())) {
      this.sharedService.showError('All fields are required!');
      return;
    }
    if (this.userForm.valid) {
      const payload = this.createPayload();
      this.authService.rejectApproveRND(payload).subscribe(
        (res) => {
          if (res.statusCode === 'AISH001') {
            this.requestId = res?.college?.requestId;
            this.handleApprovalResponse(res);
          } else {
            this.sharedService.showError(res.statusDesc);
          }
        },
        (err) => {
          this.sharedService.errorMessage(err.error.message);
        }
      );
    }
  }
// respon
  private handleApprovalResponse(res: any) {
    const isApproved = this.userForm.controls['approveR'].value === 1;
    //const isRejected = this.userForm.controls['approveR'].value === 2;
    const college = res?.college;
    // message body
    this.dataEmail = {
      body: isApproved
        ? `The request for the Request id: <B>${college.requestId}</B> has been approved.
               The new AISHE CODE allotted to the institution <B>${college?.name}</B> is <B>R-${college?.id}</B>.`
        : `The request for the Request id: <B>${college.requestId}</B> has been <B>Rejected</B>
           with remarks:- <B>${this.userForm.controls['aRemarks'].value}</B>`,
      emailAddress: this.email,
      subject: 'Request approval status',
    };

    // Show success message
    this.sharedService.showSuccessMessage(
      'Your request has been processed successfully!!'
    );
    this.showDialog(college);

    // Send email notification
    this.sendMail(this.dataEmail);
  }

  private showDialog(college: any) {
    const message1 =
      college?.id === 0
        ? 'Your request has been rejected successfully!!'
        : 'Your request has been approved successfully!!';

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        message1,
        data1: college,
        action: '1',
        showData: 'approvalReject',
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.onCancel();
      }
    });
  }
  onCancel(): void {
    const requestId = this.requestId;
    this.dialogRef.close({ success: false, requestId });
  }
  downloadPdf(base64: string) {
    const byteArray = new Uint8Array(
      atob(base64)
        .split('')
        .map((char) => char.charCodeAt(0))
    );

    const pdfData = new Blob([byteArray], { type: 'application/pdf' });
    const fileURL = URL.createObjectURL(pdfData); //create random url to render on browser
    //window.open(fileURL);

    let pdfName = 'viewDocument.pdf';
    // Construct the 'a' element
    let link = document.createElement('a');
    // link.download = pdfName;
    link.target = '_blank';

    // Construct the URI
    link.href = fileURL;
    document.body.appendChild(link);
    link.click();

    // Cleanup the DOM
    document.body.removeChild(link);
  }

  sendMail(dataEmail: any) {
    this.instManagementService.postSendemail(dataEmail).subscribe((res) => {
      // if (res) {
      //   alert(this.dataEmail.body)
      // }
    });
  }
}
