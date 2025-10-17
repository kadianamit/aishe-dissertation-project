import { Component, ElementRef, Inject, OnInit, Pipe, PipeTransform, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { SharedService } from 'src/app/shared/shared.service';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-email-sms-dialog',
  templateUrl: './email-sms-dialog.component.html',
  styleUrls: ['./email-sms-dialog.component.scss']
})
export class EmailSmsDialogComponent implements OnInit {
  selectedList: any;
  allSelected: any;

  uploadedFiles: any[] = [];
  uploadedProgressReport: any[] = [];
  formData: any;
  fileName: any;
  fileNameProgReport: any;
  preview: any;
  currentFile: any;
  sendEmailForm!:FormGroup;
  isSubmitted: boolean=false;

  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  tableSize: number[] = [10, 20, 30, 40, 50];
  nodalOfficerEmails: any;
  institutionHeadEmail: any;
  @ViewChild('fileInput', { static: false }) fileInput!: ElementRef;
  nodalOfficerEmail: any;
  ccAllowed: any;
  tempArrCCTO: any;
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
  private dialogRef: MatDialogRef<ConfirmDialogComponent>,public authService: AuthService,public sharedService: SharedService,private fb: FormBuilder) { }

  editorConfig: AngularEditorConfig = {
    editable: true,
      spellcheck: true,
      height: '100',
      minHeight: '100',
      maxHeight: 'auto',
      width: 'auto',
      minWidth: '0',
      translate: 'yes',
      enableToolbar: true,
      showToolbar: true,
      placeholder: 'Enter text here...',
      defaultParagraphSeparator: '',
      defaultFontName: '',
      defaultFontSize: '',
      fonts: [
        {class: 'arial', name: 'Arial'},
        {class: 'times-new-roman', name: 'Times New Roman'},
        {class: 'calibri', name: 'Calibri'},
        {class: 'comic-sans-ms', name: 'Comic Sans MS'}
      ],
      customClasses: [
      {
        name: 'quote',
        class: 'quote',
      },
      {
        name: 'redText',
        class: 'redText'
      },
      {
        name: 'titleText',
        class: 'titleText',
        tag: 'h1',
      },
    ],
    // uploadUrl: 'v1/image',
    // upload: (file: File) => { ... },
    uploadWithCredentials: false,
    sanitize: true,
    toolbarPosition: 'top',
    toolbarHiddenButtons: [
      ['bold', 'italic'],
      ['fontSize']
    ]
};

  ngOnInit(): void {
    // console.log(this.data);
    this.ccAllowed=this.data.cc;
    this.selectedList=this.data.array;

    // this.institutionHeadEmail = this.selectedList.map((ele:any) => ele.institutionHeadEmail);
    this.institutionHeadEmail = this.selectedList.filter((ele: any) => ele.institutionHeadEmail !== null && ele.institutionHeadEmail !== undefined && ele.institutionHeadEmail !== '')
    .map((ele: any) => ele.institutionHeadEmail);


    // this.nodalOfficerEmail = this.selectedList.map((ele:any) => ele.nodalOfficerEmail);
          this.nodalOfficerEmail = this.selectedList.filter((ele: any) => ele.nodalOfficerEmail !== null && ele.nodalOfficerEmail !== undefined && ele.nodalOfficerEmail !== '')
      .map((ele: any) => ele.nodalOfficerEmail);

    this.createForm();

  }
  createForm(){
    this.sendEmailForm = this.fb.group({
      emails:['',[Validators.required]],
      subject:['',[Validators.required]],
      ccto:['',[Validators.required]],
      message:['',[Validators.required]],
      uploadDoc:[''],
    })

    if(this.institutionHeadEmail.length>0 && this.ccAllowed){
      let getHeadEmails='';
        this.institutionHeadEmail.forEach((element:any) => {
             getHeadEmails+=element+',';
        });
      getHeadEmails= getHeadEmails.replace(/,$/, '');
      this.sendEmailForm.get('ccto')?.setValue(getHeadEmails);
    }else{
      if(!this.ccAllowed){
        this.sendEmailForm.get('ccto')?.setValidators(null);
        this.sendEmailForm.get('ccto')?.updateValueAndValidity();
      }

    }
    if(this.nodalOfficerEmail.length>0){
      let getNodalOfficerEmail='';  
      this.nodalOfficerEmail.forEach((element:any) => {
        getNodalOfficerEmail+=element+',';
      });
      getNodalOfficerEmail= getNodalOfficerEmail.replace(/,$/, '');
      this.sendEmailForm.get('emails')?.setValue(getNodalOfficerEmail);
      this.sendEmailForm.get('emails')?.disable();
    }
  }
  checkIfAllSelected() {
    this.allSelected = this.selectedList.every((item:any) => item.selected);
  }
  handlePageChange(event: any) {
    this.sharedService.page = event
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize)
    var a = Math.ceil(this.selectedList.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.selectedList.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.selectedList.length - 1);
    }

  }
  onUpload(event: any) {
    this.uploadedFiles = [];
    this.fileName='';
    const file = event.target.files[0];
    if (file) {
      const allowedTypes = ['image/jpeg', 'image/jpg', 'image/gif', 'application/pdf'];
      const maxSize = 2 * 1024 * 1024; // 2 MB
      if (!allowedTypes.includes(file.type)) {
        this.sharedService.showError('Only .jpeg, .jpg, .gif, and .pdf files are allowed.');
        this.clearFileInput();
        this.fileName='';
      } else if (file.size > maxSize) {
        this.sharedService.showError('File size should be less than 2 MB.');
        this.uploadedFiles = [];
        this.clearFileInput();
      } else {
        // this.uploadedFiles = file;
   
          this.uploadedFiles.push(file); 
            this.preview = '';
            this.currentFile = file;
      
            const reader = new FileReader();
      
            reader.onload = (e: any) => {
              //  console.log(e.target.result);
              this.preview = e.target.result;
            };
      
            reader.readAsDataURL(this.currentFile);
            this.fileName = this.uploadedFiles[0].name;
      }
    }

  }

  clearFileInput() {
    this.fileName = null;
    this.fileInput.nativeElement.value = '';
  }

  submit(){
    if (this.sendEmailForm.invalid) {
      this.sharedService.showError("Please Enter the required Fields");
      this.isSubmitted = true;
    }
    else{

      // this.nodalOfficerEmails = this.selectedList.filter((ele: any) => ele.nodalOfficerEmail !== null && ele.nodalOfficerEmail !== undefined && ele.nodalOfficerEmail !== '')
      // .map((ele: any) => ele.nodalOfficerEmail);
      // console.log("nodalOfficerEmail /// emails",this.nodalOfficerEmail);
      // console.log("institutionHeadEmail//// ccto",this.institutionHeadEmail);
      if(this.nodalOfficerEmail.length>0){
        this.sendEmailForm.get('emails')?.setValue(this.nodalOfficerEmail);
      }
      
      // this.institutionHeadEmail = this.selectedList.filter((ele: any) => ele.institutionHeadEmail !== null && ele.institutionHeadEmail !== undefined && ele.institutionHeadEmail !== '')
      // .map((ele: any) => ele.institutionHeadEmail);
      if(this.institutionHeadEmail.length>0){
        if(this.sendEmailForm.get('ccto')?.value){
          this.tempArrCCTO= this.sendEmailForm.get('ccto')?.value?.split(',');
          // this.sendEmailForm.get('ccto')?.setValue(tempArr);
        }

      }

      this.formData = new FormData();
      if (this.uploadedFiles.length) {
        this.uploadedFiles.forEach((file) => {
          this.formData.append('file', file, file.name);
        })
      }
      // console.log(this.sendEmailForm.getRawValue());
  
          // this.formData.append('recipientsCC',this.sendEmailForm.getRawValue().ccto);
          // this.formData.append('recipientsTO',this.sendEmailForm.getRawValue().emails);
          // this.formData.append('subject',this.sendEmailForm.getRawValue().subject);
          // this.formData.append('body',this.sendEmailForm.getRawValue().message);
          this.formData.append('requestVo',JSON.stringify(
            { 
              'recipientsCC':this.sendEmailForm.getRawValue().ccto?this.tempArrCCTO:null,
              'recipientsTO':this.sendEmailForm.getRawValue().emails,
              'subject':this.sendEmailForm.getRawValue().subject,
              'body':this.sendEmailForm.getRawValue().message
            }));
          // console.log(this.formData)
      this.authService.sendEmailToNadals(this.formData,this.sendEmailForm.getRawValue(),this.ccAllowed).subscribe((res)=>{
        if(res.status==200){
          let totalSendEmail=res.data?.['Sent To'].length;
          let totalNotSentEmail=res.data?.['Sent Not To'].length;
          this.sharedService.showSuccessMessage('Total successfully sent emails are '+totalSendEmail+' and Total not sent emails are '+totalNotSentEmail+'.');
          this.closed();
        }
      },err=>{

      })
    }
  }
  applyFilter(filterValue: string) {

    this.page = 1;
    this.handlePageChange(this.page);
    this.selectedList.filter = filterValue.trim().toLowerCase();
  }
  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
  }
  closed() {
    this.dialogRef.close(true)
  }
  errorHandling(controlName: string, errorName: string) {
    return this.sendEmailForm.controls[controlName].hasError(errorName);
  }
}
