import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';

@Component({
  selector: 'app-document-management',
  templateUrl: './document-management.component.html',
  styleUrls: ['./document-management.component.scss']
})
export class DocumentManagementComponent implements OnInit {
  uploadForm: FormGroup
  @ViewChild('fileInput', { static: false }) myVar1: ElementRef | undefined;
  fileSizeUnit: number = 5120;
  myFiles: string[] = [];
  myFilesName: any = '';
  fileSizeExceed: any;
  uploadedMedia: Array<any> = [];
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  listData: Array<any> = [];
  tempListData: Array<any> = [];
  searchText: any = '';
  showForm: boolean = false
  buttonName: string = 'Save'
  isFormInvalid:boolean=false;
  blob: any;
  changeDoc: boolean = false;
  fileSize:any=0
  documentMaster: Array<any> = []
  constructor(public fb: FormBuilder, public sharedService: SharedService, public authService: AuthService, public errorMatcher: CustomErrorStateMatcher,
    public localService: LocalserviceService
  ) {
    this.uploadForm = this.fb.group({
      id: ['', []],
      title: ['', [Validators.required]],
      typeId: ['', [Validators.required]],
    })
  }

  ngOnInit(): void {
    this.getDocumentMaster();
    this.getList()
  }
  getDocumentMaster() {
    this.authService.getDoumentType().subscribe(res => {
      this.documentMaster = res.data;
    }, err => {

    })
  }
  getList() {
    this.authService.uploadInternalDocs().subscribe(res => {
      this.listData = res.data;
      this.tempListData = [...this.listData];
      this.handlePageChange(this.page = 1)
console.log();
    }, err => {

    })
  }
  getDocById(id: any, action: any) {
    this.authService.getDocumentById(id).subscribe(res => {
      if (action === 'view') {
        this.viewPdf(res?.data[0]?.document, res?.data[0]?.name)
      } else {
        this.myFilesName = res?.data[0]?.name;
        this.download(res?.data[0]?.document)
      }

    }, err => {

    })
  }
  uploadDocument() {
    if (this.uploadForm.invalid) {
      this.isFormInvalid = true;
      this.sharedService.showWarning();
      return;
    } else {
      this.isFormInvalid = false;
    }
    if (this.fileSizeExceed) {
      this.sharedService.showValidationMessage('File size should be less than 5MB.')
      return;
    }

    const formdata: FormData = new FormData();
    if (this.changeDoc) {
      for (var i = 0; i < this.myFiles.length; i++) {
        formdata.append('file', this.myFiles[i]);
      }
    } else {
      if (!this.blob) {
        this.sharedService.showValidationMessage('Please upload document !!!')
        return;
      }
      formdata.append('file', this.blob, this.myFilesName);
    }
    let payload = {
      'typeId': this.uploadForm.controls['typeId'].value,
      'title': this.uploadForm.controls['title'].value,
      'id': this.uploadForm.controls['id'].value
    }

    formdata.append('requestVo', JSON.stringify(payload))
    this.authService.uploadInternalFile(formdata).subscribe(res => {
      if (res.status === 200) {
        if (this.buttonName === 'Save') {
          this.sharedService.showSuccess();
        } else {
          this.sharedService.showUpdate();
        }
        this.reset()
        this.getList()
      }
    }, err => {

    })
  }

  async getFileDetails(e: any) {
    // const buffer = await readChunk(e.target.file, { length: 4100 });
    // console.log(await fileTypeFromBuffer(buffer));
    this.myFiles = [];
    this.myFilesName = '';
    this.uploadedMedia = [];
    for (var i = 0; i < e.target.files.length; i++) {
      if (e.target.files[i].size > 5242880) {
        this.fileSizeExceed = true;
        this.sharedService.showValidationMessage('File size should be less than 5MB.')
        return;
      }
      // let size: number = e.target.files[i].size / 1024 / 1024 / 1024 / 1024 / 1024
      // size = parseInt(size.toFixed(2));
      // if (size > 2) {
      //   this.fileSizeExceed = true;
      //   this.sharedService.showValidationMessage('File size should be less than 5MB.')
      //   return;
      // }
      else {
        this.changeDoc = true;
        this.fileSizeExceed = false;
        this.myFiles.push(e.target.files[i]);
        this.myFilesName += e.target.files[i].name;
      }
      if (!(e.target.files.length - 1 === i)) {
        this.myFilesName += ',';
      }
    }
    const target = e.target as HTMLInputElement;
    this.processFiles(target.files);
  }
  processFiles(files: any) {
    for (const file of files) {
      if (file.type != 'application/pdf') {
        this.sharedService.showValidationMessagePDF(
          'Please upload pdf file only!!!'
        );
        this.myFilesName = '';
        this.myFiles = [];
        return;
      }
      var reader = new FileReader();
      reader.readAsDataURL(file); // read file as data url
      reader.onload = (event: any) => {
        // called once readAsDataURL is completed
        this.myFilesName = file.name;
        this.uploadedMedia.push({
          FileName: file.name,
          FileSize:
            this.getFileSize(file.size) + ' ' + this.getFileSizeUnit(file.size),
          FileType: file.type,
          FileUrl: event.target.result,
          FileProgessSize: 0,
          FileProgress: 0,
          ngUnsubscribe: new Subject<any>(),
        });

        this.startProgress(file, this.uploadedMedia.length - 1);
      };
    }
  }
  async startProgress(file: any, index: number) {
    let filteredFile = this.uploadedMedia
      .filter((u: any, index: number) => index === index)
      .pop();

    if (filteredFile != null) {
      let fileSize = this.getFileSize(file.size);
      let fileSizeInWords = this.getFileSizeUnit(file.size);

      for (var f = 0; f < fileSize + fileSize * 0.0001; f += fileSize * 0.01) {
        filteredFile.FileProgessSize = f.toFixed(2) + ' ' + fileSizeInWords;
        var percentUploaded = Math.round((f / fileSize) * 100);
        filteredFile.FileProgress = percentUploaded;
        await this.fakeWaiter(Math.floor(Math.random() * 35) + 1);
      }
    }
  }

  fakeWaiter(ms: number) {
    return new Promise((resolve) => {
      setTimeout(resolve, ms);
    });
  }
  getFileSize(fileSize: number): number {
    this.fileSize = fileSize;
    //log / access file size in bytes
    //  console.log(this.fileSize + ' Bytes');

    //log / access file size in Mb
    //  console.log(this.fileSize/1024/1024 + ' MB');

    if (this.fileSize / 1024 / 1024 > 4) {
      // console.log(this.fileSize/1024/1024 > 4)
    }
    if (fileSize > 0) {
      if (fileSize < this.fileSizeUnit * this.fileSizeUnit) {
        fileSize = parseFloat((fileSize / this.fileSizeUnit).toFixed(2));
      } else if (
        fileSize <
        this.fileSizeUnit * this.fileSizeUnit * this.fileSizeUnit
      ) {
        fileSize = parseFloat(
          (fileSize / this.fileSizeUnit / this.fileSizeUnit).toFixed(2)
        );
      }
    }

    return fileSize;
  }

  getFileSizeUnit(fileSize: number) {
    let fileSizeInWords = 'bytes';

    if (fileSize > 0) {
      if (fileSize < this.fileSizeUnit) {
        fileSizeInWords = 'bytes';
      } else if (fileSize < this.fileSizeUnit * this.fileSizeUnit) {
        fileSizeInWords = 'KB';
      } else if (
        fileSize <
        this.fileSizeUnit * this.fileSizeUnit * this.fileSizeUnit
      ) {
        fileSizeInWords = 'MB';
      }
    }

    return fileSizeInWords;
  }
  removeImage(idx: number) {
    this.uploadedMedia = this.uploadedMedia.filter((u, index) => index !== idx);
  }
  handlePageChange(event: any) {
    this.page = event
    this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
      this.EndLimit = this.StartLimit + Number(this.pageSize)
    var a = Math.ceil(this.listData.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.listData.length);
    } else {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.listData.length - 1);
    }

  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1)
  }

  async updateResults() {
    this.listData = []
    this.listData = this.searchByValue(this.tempListData);
    this.handlePageChange(this.page = 1)
  }

  searchByValue(userData: any) {
    return userData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.name?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.type?.type?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.createdDateTime?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.documentTittle?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  onKeypressEvent(event: any, inputLength: number) {
    if (event.target.value.length + 1 > inputLength) {
      event.preventDefault();
    }
  }
  addRecord() {
    this.showForm = true;
    this.uploadForm.reset();
    this.uploadForm.get('id')?.setValue(0);
    this.uploadForm.get('id')?.updateValueAndValidity();
    this.myFiles = [];
    this.myFilesName = '';
    this.uploadedMedia = [];
  }
  close() {
    this.buttonName = 'Save'
    this.showForm = false;
    this.uploadForm.reset();
    this.uploadForm.get('id')?.setValue(0);
    this.uploadForm.get('id')?.updateValueAndValidity();
    this.myFiles = [];
    this.myFilesName = '';
    this.uploadedMedia = [];
    this.changeDoc=false
  }
  reset() {
    this.buttonName = 'Save'
    this.uploadForm.reset()
    this.uploadForm.get('id')?.setValue(0);
    this.uploadForm.get('id')?.updateValueAndValidity();
    this.myFiles = [];
    this.myFilesName = '';
    this.uploadedMedia = [];
    this.changeDoc=false
  }

  delete(item: any) {
    this.sharedService.delete().subscribe(result => {
      if (result) {
        this.authService.deleteInternalDocument(item.id).subscribe(res => {
          if (res.status === 200) {
            this.reset()
            this.sharedService.showDelete()
            this.getList()
          }
        }, err => {

        })
      }
    })
  }
  edit(item: any, i: number) {
    this.showForm = true
    this.buttonName = 'Update'
    this.uploadForm.get('title')?.setValue(item.documentTittle);
    this.uploadForm.get('typeId')?.setValue(item?.type?.id);
    this.uploadForm.get('id')?.setValue(item?.id);
    this.getDocById(item.id, 'edit')
    // this.myFilesName = item.fileName;
    // this.download(item.documentFile)

  }
  download(data: any) {
    let uint8_data = _base64ToArrayBuffer(data);
    var ba = new Uint8Array(uint8_data);
    this.blob = new Blob([ba], { type: 'application/pdf' });
    function _base64ToArrayBuffer(base64: any) {
      var binary_string = window.atob(base64);
      var len = binary_string.length;
      var bytes = new Uint8Array(len);
      for (var i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
      }
      return bytes.buffer;
    }
  }

  downloadPdf(data: any, fileName: string) {
    let uint8_data = _base64ToArrayBuffer(data);
    var ba = new Uint8Array(uint8_data);
    var blob = new Blob([ba], { type: 'application/pdf' });
    if (window.navigator && (window.navigator as any).msSaveOrOpenBlob) {
      (window.navigator as any).msSaveOrOpenBlob(blob);
    } else {
      var a = document.createElement("a");
      document.body.appendChild(a);
      var fileURL = URL.createObjectURL(blob);
      a.href = fileURL;
      a.download = fileName;
      
      a.click();
    }
    function _base64ToArrayBuffer(base64: string) {
      var binary_string = window.atob(base64);
      var len = binary_string.length;
      var bytes = new Uint8Array(len);
      for (var i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
      }
      return bytes.buffer;
    }
  }
  tab:any
  viewPdf(data:any,fileName:string){
     let uint8_data = _base64ToArrayBuffer(data);
    var ba = new Uint8Array(uint8_data);
    var blob = new Blob([ba], { type: 'application/pdf' });
    const url = window.URL.createObjectURL(blob);
    this.tab = window.open(url,fileName);
    this.tab.location.href = url;

   function _base64ToArrayBuffer(base64: string) {
      var binary_string = window.atob(base64);
      var len = binary_string.length;
      var bytes = new Uint8Array(len);
      for (var i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
      }
      return bytes.buffer;
    }
    // let file = new Blob([data], { type: 'application/pdf' });            
    // var fileURL = URL.createObjectURL(file);
    // window.open(fileURL);
  }
}
