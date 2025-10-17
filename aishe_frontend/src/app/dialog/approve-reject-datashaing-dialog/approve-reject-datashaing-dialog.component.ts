import { AfterViewInit, ChangeDetectorRef, Component, Inject, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators  } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-approve-reject-datashaing-dialog',
  templateUrl: './approve-reject-datashaing-dialog.component.html',
  styleUrls: ['./approve-reject-datashaing-dialog.component.scss']
})
export class ApproveRejectDatashaingDialogComponent implements OnInit,AfterViewInit {
  myForm!: FormGroup;
  isSubmitted: boolean=false;
  showHide=true;
  requestLetter:any;
  requestLetterName:any;
  constructor(@Inject(MAT_DIALOG_DATA) public data:any,public sharedservice: SharedService,public authService: AuthService, private dialogRef: MatDialogRef<ApproveRejectDatashaingDialogComponent>,private fb: FormBuilder,private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    // console.log("dialog",this.data);
    if(this.data.data){
      this.showHide=(this.data.data.statusId==2 || this.data.data.statusId==3)?false:true; 
      this.createForm();
      this.getDocument();
    }

  }
  getDocument(){
        this.requestLetter=this.data.doc.requestLetter;
        this.requestLetterName=this.data.doc.requestLetterName;
    // let payload={"id":this.data.id}
    // this.authService.getDataSharing(payload).subscribe((res:any)=>{
    //   if(res.status==200){
    //     // this.requestLetter=res.data[0].requestLetter;
    //     // this.requestLetterName=res.data[0].requestLetterName;
    //   }
    // })
  }
  ngAfterViewInit() {
    this.requestLetter=null;
    this.requestLetterName=null;
    this.cdr.detectChanges(); // Manually trigger change detection
  }
  createForm(){
    this.myForm = this.fb.group({
      action: ['', Validators.required],
      remarks: ['', Validators.required],  
    });
  }
  errorHandling(controlName: string, errorName: string) {
    return this.myForm.controls[controlName].hasError(errorName);
  }
  cancel(){
    this.dialogRef.close({ success: false });
  }
  onSubmit(){
    if (this.myForm.invalid) {
      this.isSubmitted = true;
    } else {
      let formdata=this.myForm.getRawValue();
      let payload:any;

        payload={
          "remarks": formdata.remarks,
          "requestId": this.data.data.requestId,
          "statusId": formdata.action,//2 for approved and 3 for reject
         }
        this.authService.approveRejectDataSharing(payload).subscribe((res:any)=>{
          if(res){
            this.sharedservice.showSuccessMessage(res.message);
            this.dialogRef.close({ success: true });
          }
        })
    }
   

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
}
