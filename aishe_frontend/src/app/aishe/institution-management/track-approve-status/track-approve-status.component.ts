import { AfterViewInit, Component, ElementRef, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-track-approve-status',
  templateUrl: './track-approve-status.component.html',
  styleUrls: ['./track-approve-status.component.scss']
})
export class TrackApproveStatusComponent implements OnInit,OnChanges {

  @Input() requestIdData: any;
  @Input() stateBodyTypeName:any;
  @ViewChild('curtain')
  divCurtain!: ElementRef;
  @ViewChild('progrssbar')
  progressBar!: ElementRef;
  requstObject: any = {
    requestId: ''
  }
  @Output() statusEvent = new EventEmitter<any>();
  wizardSteps: string[] = ['Step 1', 'Step 2', 'Step 3', 'Step 4'];
  ifLoading: boolean = false;
  dataView: boolean = false;
  createdDate: any;
  moeStatus: any;
  snoStatus: any;
  unoStatus: any;
  snoApprovedDated: any;
  unoApprovedDated: any;
  moeApprovedDated: any;
  aisheCode: any;
  pendingStatus: any = 'Pending'
  moeactiveClass: any;
  snoactiveClass: any;
  unoactiveClass: any;
  arrayStatus: any[] = [];
  contactArray: any[] = [];
  rejectedBy: any;
  snoRemarks: any;
  moeRemarks: any;
  unoRemarks: any;
  noEmptyStrings: any;
  arrayMap: boolean = false;
  keyConatactInfo: any;
  userRole: any;
  userName: any;
  emailId: any;
  mobileNo: any;
  moehideClass: any;
  unohideClass: any;
  snohideClass: any;
  snoSecondHide: any;
  unoSecondHide: any;
  moeSecondHide: any;
  snoSecondhideClass: any;
  unoSecondAppHide: any;
  otherCase: any;
  form!:FormGroup;
  roleId:any;
  showAffiliatingBody: boolean=true;
  pendingLevelBool:any
  systemDateTime: any;
  constructor(public fb:FormBuilder, public authService: AuthService, public sharedService: SharedService, private el: ElementRef,public localService:LocalserviceService) {
  this.roleId=this.localService.getData('roleId');

   }
  ngOnChanges(changes: SimpleChanges){
    for (const propName in changes) {
      if (changes.hasOwnProperty(propName)) {
        let change = changes[propName];
        switch (propName) {
          case 'requestIdData': {
            // console.log('test',changes['requestIdData'].currentValue)
       
            this.getCurrentTimeDate(change.currentValue);
            break;
          }
        }
      }
    }
    }
  ngOnInit(): void {
  //  console.log(this.stateBodyTypeName)
  //  console.log(this.requestIdData)
   if(this.stateBodyTypeName==='Other'){
    this.snoStatus='Approved'
   }
    // this.CheckStatus(this.requestIdData)
    this.form=this.fb.group({
      requestId:['',[Validators.required, Validators.maxLength(10)]],
    })

  }
  getCurrentTimeDate(requestId:any){
    this.authService.getCurrentDateTime().subscribe((res)=>{
      if(res.status==200){
        this.systemDateTime=res.data;
        this.CheckStatus(requestId);
      }
    })
  }
  

  CheckStatus(requestId:any) {
      // console.log('1')
      this.authService.getRequestId(requestId).subscribe(res => {
         if(this.localService.getData('roleId')=='6'){
          this.statusEvent.emit({UNO:res.data.unoStatus});
         }
        
        if (res.status == 200) {
          //this case only runs if sectorial case and date if more than 30 days
          if(res.data.unoStatusId==4 && res.data.snoStatus!='Approved' && this.isBeyond30Days(res.data.createdDated) && res.data.typeId==2 && (res.data.unoStatus!='Rejected' && res.data.snoStatus!='Rejected')){
            this.pendingLevelBool=true;
            res.data.unoStatus='Approved';
            res.data.snoStatus='Pending';
          }

          this.ifLoading = true;
          this.dataView = true;
          this.createdDate = res.data.createdDated;
          this.showAffiliatingBody=(res.data.isApprovingAuthority==false)?false:true;
          this.unoStatus = this.stateBodyTypeName==='Other'?'Approved': res.data.unoStatus;
          this.unoactiveClass = res.data.unoStatus == 'Pending' || res.data.unoStatus == 'Rejected' ? '' : 'active';
          this.unohideClass = (res.data.moeStatus == 'Approved' || res.data.moeStatus == 'Rejected' || res.data.unoStatus == 'Rejected') && (res.data.unoStatus == 'Pending') ? 'hideClass' : '';
          this.unoSecondHide = (res.data.snoStatus == 'Rejected') && (res.data.unoStatus == 'Pending') ? 'hideClass' : '';
          this.unoSecondAppHide = (res.data.snoStatus == 'Approved') && (res.data.unoStatus == 'Pending') ? 'hideClass' : '';
          this.snoStatus =this.stateBodyTypeName==='NCHMCT'?'Approved': res.data.snoStatus;
          this.snoactiveClass = res.data.snoStatus == 'Pending' || res.data.snoStatus == 'Rejected' ? '' : 'active';
          this.snohideClass = (res.data.moeStatus == 'Approved' || res.data.moeStatus == 'Rejected') && (res.data.snoStatus == 'Pending') ? 'hideClass' : '';
          this.snoSecondhideClass = (res.data.unoStatus == 'Rejected') && (res.data.snoStatus == 'Pending') ? 'hideClass' : '';
          this.moeStatus = res.data.moeStatus;
          this.moeactiveClass = res.data.moeStatus == 'Pending' || res.data.moeStatus == 'Rejected' ? '' : 'active';
          this.moehideClass = (res.data.moeStatus == 'Approved' || res.data.moeStatus == 'Rejected') && (res.data.moeStatus == 'Pending') ? 'hideClass' : '';
          this.moeSecondHide = (res.data.snoStatus == 'Rejected') && (res.data.moeStatus == 'Pending') ? 'hideClass' : '';
          this.otherCase = (res.data.snoStatus == 'Approved' && res.data.unoStatus == 'Rejected' && res.data.moeStatus == 'Pending') ? 'true' : 'false';
          this.arrayStatus.push('active', this.unoactiveClass, this.snoactiveClass, this.moeactiveClass);
          // this.noEmptyStrings = this.arrayStatus.filter((str) => str !== '');
          this.snoApprovedDated = res.data.snoApprovedDated;
          this.unoApprovedDated = res.data.unoApprovedDated;
          this.moeApprovedDated = res.data.moeApprovedDated;
          this.aisheCode = res.data.aisheCode;
          this.rejectedBy = res.data.rejectedBy;
          this.snoRemarks = res.data.snoRemarks;
          this.moeRemarks = res.data.moeRemarks;
          this.unoRemarks = res.data.unoRemarks;
          this.keyConatactInfo = res.data.contactTo
          if(!this.showAffiliatingBody){
            //UNO
            // this.unoStatus = res.data.unoStatus;
            this.unoStatus='Approved'
            this.unoactiveClass = this.unoStatus == 'Pending' || this.unoStatus == 'Rejected' ? '' : 'active';
            this.unohideClass = (res.data.moeStatus == 'Approved' || res.data.moeStatus == 'Rejected' || this.unoStatus == 'Rejected') && (this.unoStatus == 'Pending') ? 'hideClass' : '';
            this.unoSecondHide = (res.data.snoStatus == 'Rejected') && (this.unoStatus == 'Pending') ? 'hideClass' : '';
            this.unoSecondAppHide = (res.data.snoStatus == 'Approved') && (this.unoStatus == 'Pending') ? 'hideClass' : '';
            
            //SNO
            this.snoStatus = res.data.snoStatus;
            this.snoactiveClass = this.snoStatus == 'Pending' || this.snoStatus == 'Rejected' ? '' : 'active';
            this.snohideClass = (res.data.moeStatus == 'Approved' || res.data.moeStatus == 'Rejected') && (this.snoStatus == 'Pending') ? 'hideClass' : '';
            this.snoSecondhideClass = (res.data.unoStatus == 'Rejected') && (this.snoStatus == 'Pending') ? 'hideClass' : '';
          }
          // if(this.pendingLevelBool===false){
          //   this.unoStatus='Pending'
          //   this.unoactiveClass = this.unoStatus == 'Pending' || this.unoStatus == 'Rejected' ? '' : 'active';
          //   this.unohideClass = (res.data.moeStatus == 'Approved' || res.data.moeStatus == 'Rejected' || this.unoStatus == 'Rejected') && (this.unoStatus == 'Pending') ? 'hideClass' : '';
          //   this.unoSecondHide = (res.data.snoStatus == 'Rejected') && (this.unoStatus == 'Pending') ? 'hideClass' : '';
          //   this.unoSecondAppHide = (res.data.snoStatus == 'Approved') && (this.unoStatus == 'Pending') ? 'hideClass' : '';
            
          //   //SNO
          //   this.snoStatus = res.data.snoStatus;
          //   this.snoactiveClass = this.snoStatus == 'Pending' || this.snoStatus == 'Rejected' ? '' : 'active';
          //   this.snohideClass = (res.data.moeStatus == 'Approved' || res.data.moeStatus == 'Rejected') && (this.snoStatus == 'Pending') ? 'hideClass' : '';
          //   this.snoSecondhideClass = (res.data.unoStatus == 'Rejected') && (this.snoStatus == 'Pending') ? 'hideClass' : '';
          // }
          if (res.data.contactTo !== null) {
            this.userRole = res.data.contactTo.userRole;
            this.userName = (res.data.contactTo.firstName ? res.data.contactTo.firstName : '') + ' ' + (res.data.contactTo.middleName ? res.data.contactTo.middleName : '') + ' ' + (res.data.contactTo.lastName ? res.data.contactTo.lastName : '');
            this.emailId = res.data.contactTo.emailId;
            this.mobileNo = res.data.contactTo.mobileNo;
            if (this.userRole == null && this.emailId == null && this.mobileNo == null) {
              this.arrayMap = false;
            }
            else {
              this.arrayMap = true;
            }

          }
          else {
            this.arrayMap = false;
          }
          let arrayMap = res.data.contactTo == 'null' ? null : res.data.contactTo;
          setTimeout(() => {
            this.setupWizard()
          }, 500);
        }
      }, err => {
        this.sharedService.wrongRequestId();
        this.dataView = false;
        this.arrayMap = false;
      })
    
    
  }

  setupWizard(): void {
    var circles = document.querySelectorAll(".circle");
    const elementArray = Array.from(circles);
    const activeElements = elementArray.filter(element => element.classList.contains('active'));
    if (activeElements.length == 1) {
      this.progressBar.nativeElement.style.width = '27%';
      this.divCurtain.nativeElement.style.width = `211.3333%`;
      if (this.unoStatus == 'Approved' && this.snoStatus == 'Pending' && this.moeStatus == 'Pending') {
        this.progressBar.nativeElement.style.width = '75%';
        this.divCurtain.nativeElement.style.width = `0%`;
      }
      
      else if (this.unoStatus == 'Pending' && this.snoStatus == 'Pending' && this.moeStatus == 'Pending') {
        this.progressBar.nativeElement.style.width = '75%';
        this.divCurtain.nativeElement.style.width = `33.666%`;
  //       //changes
  //       if(this.pendingLevelBool === false){
  //        this.progressBar.nativeElement.style.width = '75%';
  //       this.divCurtain.nativeElement.style.width = `66.667%`;
  //       }else{
  // this.progressBar.nativeElement.style.width = '75%';
  //       this.divCurtain.nativeElement.style.width = `33.666%`;
  //       }
      
      }
      else if (this.unoactiveClass != 'active' && this.snoactiveClass != 'active' && this.moeactiveClass != 'active') {
        this.progressBar.nativeElement.style.width = '27%';
        this.divCurtain.nativeElement.style.width = `177.6667%`;
      }
    }
    else if (activeElements.length == 2) {
      this.progressBar.nativeElement.style.width = '27%';
      this.divCurtain.nativeElement.style.width = `211.3333%`;
      if (this.unoStatus == 'Approved' && this.snoStatus == 'Pending' && this.moeStatus == 'Rejected') {
        this.progressBar.nativeElement.style.width = '52%';
        this.divCurtain.nativeElement.style.width = `177.6667%`;
      }
      else if (this.unoStatus == 'Approved' && this.snoStatus == 'Rejected' && this.moeStatus == 'Pending') {
        this.progressBar.nativeElement.style.width = '52%';
        this.divCurtain.nativeElement.style.width = `177.6667%`;
      }
      else if (this.snoStatus == 'Approved' && this.unoStatus == 'Rejected' && this.moeStatus == 'Pending') {
        this.progressBar.nativeElement.style.width = '75%';
        this.divCurtain.nativeElement.style.width = `66.667%`;
      }
      else if (this.unoactiveClass == 'active' && this.snoactiveClass != 'active' && this.moeactiveClass != 'active') {
        this.progressBar.nativeElement.style.width = '75%';
        this.divCurtain.nativeElement.style.width = `66.667%`;
      }
      else if (this.snoactiveClass == 'active' && this.moeactiveClass != 'active') {
        this.progressBar.nativeElement.style.width = '52%';
        this.divCurtain.nativeElement.style.width = `177.6667%`;
      }
    }
    else if (activeElements.length == 3) {
      this.progressBar.nativeElement.style.width = '52%';
      this.divCurtain.nativeElement.style.width = `177.6667%`;
      if (this.unoStatus == 'Approved' && this.snoStatus == 'Approved' && this.moeStatus == 'Pending') {
        this.progressBar.nativeElement.style.width = '75%';
        this.divCurtain.nativeElement.style.width = `177.6667%`;
      }
      else if (this.unoStatus == 'Approved' && this.snoStatus == 'Approved' && this.moeStatus == 'Rejected') {
        this.progressBar.nativeElement.style.width = '75%';
        this.divCurtain.nativeElement.style.width = `66.667%`;
      }
      else if (this.unoactiveClass == 'active' && this.snoactiveClass == 'active' && this.moeactiveClass != 'active') {
        this.progressBar.nativeElement.style.width = '52%';
        this.divCurtain.nativeElement.style.width = `177.6667%`;
      }
    }
    else {
      this.progressBar.nativeElement.style.width = '75%';
      this.divCurtain.nativeElement.style.width = `${((activeElements.length - 1) / (circles.length - 1)) * 100}%`;
    }

  }
  reset(){
    this.form.reset();
  }

 isBeyond30Days(inputDate:any) {
    // Split the input date string to handle DD/MM/YYYY format
  const dateParts = inputDate.split(/[\s/:]/); // Split by space, slash, or colon
  
  // Extract day, month, year, and time parts
  const day = parseInt(dateParts[0], 10); // DD
  const month = parseInt(dateParts[1], 10) - 1; // MM (0-based for JavaScript Date)
  const year = parseInt(dateParts[2], 10); // YYYY
  let hours:any = dateParts[3] ? parseInt(dateParts[3], 10) : 0; // Hours
  const minutes = dateParts[4] ? parseInt(dateParts[4], 10) : 0; // Minutes
  const seconds = dateParts[5] ? parseInt(dateParts[5], 10) : 0; // Seconds
  const meridian = dateParts[6]; // AM/PM
  
  // Adjust hours for AM/PM
  if (meridian === "PM" && hours < 12) {
    hours += 12;
  } else if (meridian === "AM" && hours === 12) {
    hours = 0;
  }

  // Create the Date object
  const formattedDate:any = new Date(year, month, day, hours, minutes, seconds);


  // Get the current date
  const currentDate:any = new Date(this.systemDateTime);
  // const currentDate:any=this.systemDateTime;
  // console.log(currentDate)

  // Calculate the difference in days
  const differenceInMillis = currentDate - formattedDate;
  const differenceInDays = differenceInMillis / (1000 * 60 * 60 * 24);
  // console.log(differenceInDays)
  // Return true if more than 30 days
  return differenceInDays > 30;
}
  
  
  
}
