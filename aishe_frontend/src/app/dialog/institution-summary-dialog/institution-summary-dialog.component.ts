import { Component, Inject, Input, OnInit,OnChanges, SimpleChanges } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { GetService } from 'src/app/service/get/get.service';
import { SharedService } from 'src/app/shared/shared.service';
import { utility } from 'src/app/common/utility';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-institution-summary-dialog',
  templateUrl: './institution-summary-dialog.component.html',
  styleUrls: ['./institution-summary-dialog.component.scss']
})
export class InstitutionSummaryDialogComponent implements OnInit,OnChanges {
  cancelButtonText = "Close";
  searchText: any = '';
  tempListData: Array<any> = []
  listData: Array<any> = []
  instituteTypeList: Array<any> = []
  insTypeId: any = 'ALL'
  message:string='';
  tableHeaders:Array<any> = [];
  listType: any;
  // element:any={'urlData':'','listType':'','list':'','instituteTypeList':''};
  dialogRef:any;
  // if(this.element.type=='APPROVED'){
  // tableHeaders = ['SNO','Request Id','AISHE Code', 'Institute Name','Institute Type','State', 'University Name'];
  // }
  @Input() element: any={};
  previousMessage: any;
  constructor( public sharedService: SharedService,private getService: GetService,public localService:LocalserviceService,private router: Router) {

    
  }
  ngOnChanges(changes: SimpleChanges) {
    // console.log(changes)
    if (changes['element']) { // Check if message property changed
      this.previousMessage = changes['element'].previousValue || 'None'; // Store previous value
      // console.log('Previous:', this.previousMessage);
      // console.log('Current:', changes['element'].currentValue);
      this.element=changes['element'].currentValue;
      // if(this.element){
      //   this.callElementType();
      // }
      if(this.element){
        this.tempListData = this.element.list
        this.listData = this.element.list
        this.instituteTypeList = this.element.instituteTypeList
        this.listType=this.element.listType
        this.callElementType(this.element.listType);
      }
  
   
    }
  }

  ngOnInit(): void {
    // console.log(this.element);
    

    if(this.element){
      this.handlePageChange(this.sharedService.page)
      this.callElementType(this.element.listType);
    }
    
  }
  callElementType(type:any){
    switch (type) {
      case "APPROVED":
       this.message = this.element.type === 'COLLEGE'?'Total College Request Approved List':'Total Standalone Request Approved List';
        break;
      case "MOE_PENDING":
        // this.message = this.element.type === 'COLLEGE'?'Total College Pending List':'Total Standalone Pending List';
    
        this.message = this.element.type === 'COLLEGE'?'Total College Request Pending at MoE Level':'Total Standalone Request Pending at MoE Level';
        
        break;
      case "APPROVING_AUTH_APPROVED":
      // this.message = this.element.type === 'COLLEGE'?'Total College Pending List':'Total Standalone Pending List';
      this.message = this.element.type === 'COLLEGE'?'Total College Pending at UNO List':'';
      break;
      case "SNO_PENDING":
      this.message = this.element.type === 'COLLEGE'?'Total College Request Pending at SNO Level':'Total Standalone Request Pending at SNO Level';
      break;    
      case "APPROVING_AUTH_PENDING":
        this.message = this.element.type === 'COLLEGE'?'Total College Request Pending at UNO Level':'Total Standalone Request Pending at Sectorial Level';
        break;    
      case "REJECTED":
        this.message = this.element.type === 'COLLEGE'?'Total College Request Rejected List':'Total Standalone Request Rejected List';
        break;
      default:
        this.message = this.element.type === 'COLLEGE'?'Total College Request List':'Total Standalone Request List';
        break;
    }
  }
  onChange(event: any) {
    this.sharedService.pageSize = parseInt(event);
    this.handlePageChange(this.sharedService.page = 1)
  }

  async updateResults() {
    this.listData = []
    this.listData = this.searchByValue(this.tempListData);
    this.handlePageChange(this.sharedService.page = 1)
  }
  handlePageChange(event: any) {
    this.sharedService.page = event
    this.sharedService.StartLimit = ((this.sharedService.page - 1) * Number(this.sharedService.pageSize)),
      this.sharedService.EndLimit = this.sharedService.StartLimit + Number(this.sharedService.pageSize)
    var a = Math.ceil(this.listData?.length / Number(this.sharedService.pageSize));
    if (a === event) {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.listData?.length);
    } else {
      this.sharedService.pageData = Math.min(this.sharedService.StartLimit + Number(this.sharedService.pageSize), this.listData?.length - 1);
    }

  }


  searchByValue(listData: any) {
    return listData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.aisheCode?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.name?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.institutionTypeName?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.stateName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.universityName?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.requestId?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
  getFilterData(value: any) {
    if(value !== 'ALL'){
      this.listData = this.tempListData.filter(e => e.institutionTypeId === value.toString())
    }else{
    this.listData = [...this.tempListData]
    }
    this.handlePageChange(this.sharedService.page=1)
  }




downloadPDF() {
  let tableData;
  if(this.element.listType=== 'APPROVED'){
    tableData = this.listData.map((row, i,element) => [
      i+1 ,
     row.requestId, 
     row.aisheCode, 
     row.name, 
     row.institutionTypeName,
     row.stateName,
     `${row.universityName} (U-${row.universityId})`
   ]);
 }else if(this.element.type==='COLLEGE'){
  const maxLength = 30; // Define your maximum length for line breaks

  // const breakLongName = (name:any) => {
  //     // Split the name into words and join them with line breaks if they exceed maxLength
  //     let words = name.split(' ');
  //     let lines = [];
  //     let currentLine = '';

  //     words.forEach((word:any) => {
  //         if ((currentLine + word).length > maxLength) {
  //             lines.push(currentLine.trim()); // Push the current line to the lines array
  //             currentLine = word + ' '; // Start a new line with the current word
  //         } else {
  //             currentLine += word + ' '; // Add the word to the current line
  //         }
  //     });
      
  //     if (currentLine) {
  //         lines.push(currentLine.trim()); // Add the last line if any
  //     }

  //     return lines.join('\n'); // Join lines with HTML line breaks
  // };
    tableData = this.listData.map((row, i,element) => [
      i+1 ,
     row.requestId, 
    //  breakLongName(row.name.trim()), 
    row.name,
     row.institutionTypeName,
     row.stateName,
     `${row.universityName} (U-${row.universityId})`
 
   ]);
 }else if(this.element.type==='STANDALONE'){
   tableData = this.listData.map((row, i,element) => [
    i+1 ,
     row.requestId, 
     row.name, 
     row.institutionTypeName,
     row.stateName,
   
 
   ]);
 }
 if(this.element.listType=='APPROVED'){
   this.tableHeaders = ['SNO','Request Id','AISHE Code','Institute Name','Institute Type','State', 'University Name'];
   }else if(this.element.type=='COLLEGE'){
     this.tableHeaders = ['SNO','Request Id','Institute Name','Institute Type','State','University Name'];
   }else if(this.element.type=='STANDALONE'){
     this.tableHeaders = ['SNO','Request Id','Institute Name','Institute Type','State'];

   }
  let param={
    tableHeaders:this.tableHeaders,
    tableData:tableData,
    pdfName:this.message,
    downloadPdfName:this.message,
    orientationType:'landscape',
  }
  let a={  0: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
     1: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
  2: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
  3: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
  4: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
  5: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
  6: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
  7: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
  8: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
  9: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
  10: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },

}
   this.sharedService.downloadPDF(param ,a);
  
}
// Function to download the table data as an Excel file
exportAsXLSX() {
let tableData;
  if(this.element.listType=== 'APPROVED'){
     tableData = this.listData.map((row, i,element) => [
      row.requestId, 
      row.aisheCode, 
      row.name, 
      row.institutionTypeName,
      row.stateName,
      `${row.universityName} (U-${row.universityId})`
    ]);
  }else if(this.element.type=='COLLEGE'){
     tableData = this.listData.map((row, i,element) => [
      row.requestId, 
      row.name, 
      row.institutionTypeName,
      row.stateName,
      `${row.universityName} (U-${row.universityId})`
  
    ]);
  }else if(this.element.type=='STANDALONE'){
    tableData = this.listData.map((row, i,element) => [
      row.requestId, 
      row.name, 
      row.institutionTypeName,
      row.stateName,
    
  
    ]);
  }
  if(this.element.listType=='APPROVED'){
    this.tableHeaders = ['SNO','Request Id','AISHE Code', 'Institute Name','Institute Type','State','University Name'];
    }else if(this.element.type=='COLLEGE'){
      this.tableHeaders = ['SNO','Request Id','Institute Name','Institute Type','State','University Name'];
    }else if(this.element.type=='STANDALONE'){
      this.tableHeaders = ['SNO','Request Id', 'Institute Name','Institute Type','State'];

    }
 this.tableHeaders.shift();
  let param={
    tableHeaders:this.tableHeaders,
    tableData:tableData,
    // excelName:'Merged Insitutions',
    downloadExcelName:'Summary Report - HEI on Boarding',
    setHeaderCollumnWidths:[
      { wpx: 80 },//surveyYear
      { wpx: 80 }, //Oldaishecode
      { wpx: 290 },//oldInstitutionName,
      { wpx: 290 },//oldUniversityName or affilliated University
      { wpx: 80 },//state
      { wpx: 88 },// newAisheCode
      { wpx: 210 },//newInstitutionName
      { wpx: 270 },//newUniversityName or affilliated university
      { wpx: 80 },//actionBy
      { wpx: 130 },//actionTime

       ],
     headerStyle:{
      font: {
        bold: true,
        color: { rgb: 'FFFFFF' }, // White text color
      },
      fill: {
        fgColor: { rgb: '4CAF50' }, // Green background color
      },
      alignment: {
        horizontal: 'center',
        vertical: 'center'
      }
    }  
  }
 this.sharedService.downloadExcel(param);
}
viewDetail(requestId:any,surveyYear:any,type:any,index:any){
  // console.log(requestId)
  // console.log(surveyYear)
  // console.log(type)
  // console.log(index)
  if(requestId && surveyYear && (type=='MOE_PENDING' || type=='APPROVING_AUTH_PENDING' || type=='SNO_PENDING')){
    let data:any={'requestId':requestId,'surveyYear':surveyYear,'type':this.element.listType,'status':'PENDING'};
     this.localService.saveData('viewDetailOfRequestId',JSON.stringify(data));
    //  this.dialogRef.close();
    this.element.type!='STANDALONE'?this.router.navigate(['/aishe/Institution-Management']):this.router.navigate(['/aishe/Institution-Management/standalone']);
  }
  if(requestId && surveyYear && (type=='REJECTED')){
    let data:any={'requestId':requestId,'surveyYear':surveyYear,'type':this.element.listType,'status':'REJECTED'};
     this.localService.saveData('viewDetailOfRequestId',JSON.stringify(data));
    //  this.dialogRef.close();
    this.element.type!='STANDALONE'?this.router.navigate(['/aishe/Institution-Management']):this.router.navigate(['/aishe/Institution-Management/standalone']);
  }
  if(requestId && surveyYear && (type=='APPROVED')){
    let data:any={'aisheCode':requestId,'surveyYear':surveyYear,'type':this.element.listType,'status':'APPROVED'};
     this.localService.saveData('viewDetailOfRequestId',JSON.stringify(data));
    //  this.dialogRef.close();
     this.element.type!='STANDALONE'?this.router.navigate(['/aishe/Institution-Management']):this.router.navigate(['/aishe/Institution-Management/standalone']);
  }



  }




}
