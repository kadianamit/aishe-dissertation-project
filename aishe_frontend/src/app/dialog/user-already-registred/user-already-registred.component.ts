import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import { ResearchAndDevelopmentRemarksDialogComponent } from '../research-and-development-remarks-dialog/research-and-development-remarks-dialog.component';

@Component({
  selector: 'app-user-already-registred',
  templateUrl: './user-already-registred.component.html',
  styleUrls: ['./user-already-registred.component.scss']
})
export class UserAlreadyRegistredComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,

    private dialogRef: MatDialogRef<ResearchAndDevelopmentRemarksDialogComponent>,
    public authService: AuthService,
    private cdr: ChangeDetectorRef,
    public localService: LocalserviceService,
    private sharedService: SharedService,
    public dialog: MatDialog,
    public fb: FormBuilder
  ) {

  }

  ngOnInit(): void {
    console.log(this.data)
  }
  onCancel(): void {
    const requestId = '';
    this.dialogRef.close({ success: false, requestId });
  }
  onConfirmClick(): void {
    this.dialogRef.close({success: true});
  }
  save(){
    this.onConfirmClick();
  }
  maskMobileNumber(number:any) {
    const lastThreeDigits = number.slice(-3);
    return `*******${lastThreeDigits}`;
  }
  maskEmail(email: string): string {
    const [localPart, domain] = email.split('@');
    const maskedLocalPart = localPart.slice(0, 2) + '*'.repeat(localPart.length - 2);
    return `${maskedLocalPart}@${domain}`;
  }
}
