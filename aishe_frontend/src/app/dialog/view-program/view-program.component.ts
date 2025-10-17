import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';

@Component({
  selector: 'cfs-view-program',
  templateUrl: './view-program.component.html',
  styleUrls: ['./view-program.component.scss']
})
export class ViewProgramComponent implements OnInit {
  confirmButtonText="Close"
  showInCollege: boolean;
  showInStandalone: boolean;
  showInUniv: boolean;
  sharedData:any =[];
  sharedData2:any =[];
  stateDistrict=1;
  districName:any;
  stateName:any;
  constructor(public dialogRef: MatDialogRef<ViewProgramComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,public authService: AuthService) {
      console.log('data.similarIsVisible', data.similarIsVisible)
      if(data.similarIsVisible == true){
        this.sharedData = data?.eData
        this.sharedData2 = data?.eData;
        this.stateDistrict=data.stateDistric;
        this.districName=data.district;
        this.stateName=data.state;
      }
      
      // console.log('445', data)
      this.showInCollege = data.showInCollege;
      this.showInStandalone = data.showInStandalone;
      this.showInUniv = data.showInUniv;
     }


  ngOnInit(): void {
  }
  getDoc(code:any){

      let payload={
        'aisheCode':code?code:null,
        'stateCode':'',
        'ministry':'',
      }
      this.authService.getViewDataRND(payload).subscribe((res:any)=>{
        console.log(res)

        if(res.statusCode=='AISH001' && res.rnDInstitutions.length>0){
           this.downloadPdf(res.rnDInstitutions[0].documentFile);
        }
      })

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
  optionChange(event:any){
  if(event==1){//state
    this.sharedData2=this.sharedData.filter((data:any)=>{
      if(data.stateName==this.stateName){
        return true
      }
      return false
  });
  }
  if(event==2){//district
    this.sharedData2=this.sharedData.filter((data:any)=>{
      if(data.districtName==this.districName ){
        return true
      }
      return false
  });
  }
}

}
