import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { UnitcellDialogComponent } from 'src/app/dialog/unitcell-dialog/unitcell-dialog.component';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';

@Component({
  selector: 'app-detail-unit-cell',
  templateUrl: './detail-unit-cell.component.html',
  styleUrls: ['./detail-unit-cell.component.scss']
})
export class DetailUnitCellComponent implements OnInit {
  unitCellForm!: FormGroup;
  stateListArray: any=[];
  stateList: any;
  modeList:any=[{'value':'C','type':'Contractual'},{'value':'P','type':'Permanent'}]
  isFormInvalid:boolean=false;
  stateCode: any;
  showUnitCellDetail: any=false;
  unitCellList: any;

  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageData2: number = 0;
  pageSize: number = 25;
  page: number = 1;
  listData: Array<any> = [];
  listData2: any = [];
  tempListData: Array<any> = [];
  tempListData2: Array<any> = [];
  searchText: any = '';
  roleId:any
  moeUnitCellList: any;
  showStatewiseData:any=false;
  constructor(public fb: FormBuilder, public sharedService: SharedService, public authService: AuthService, public errorMatcher: CustomErrorStateMatcher,
    public localService: LocalserviceService, public dialog: MatDialog
  ) { 

  }
  ngOnInit(): void {
    this.stateCode=this.localService.getData('stateCode');
    this.roleId = this.localService.getData('roleId')
    this.formBuilder();
    this.getStateList();
    this.getUnitCellList();
    this.getDesignation();
  }
  formBuilder(){
    this.unitCellForm = this.fb.group({
      state :[{value:this.stateCode,disabled:true}],
      unitCell : [false,Validators.required],
      details : this.fb.array([]),
    })
  }
  get details(): FormArray {
    return this.unitCellForm.get('details') as FormArray
  }
  addUnitDetails(data?:any): void {

    if (data) {
      this.details.push(this.fillUnitDetail(data));
    } else {
      this.details.push(this.createUnitDetail());
    }
  }
  createUnitDetail(): FormGroup {
    return this.fb.group({
      officialDetail:['',[Validators.required,Validators.pattern(/^[A-Za-z .]+$/)]],
      designation :[ '',[Validators.required,Validators.pattern(/^[A-Za-z ]+$/)]],
      currentSalary:['',[Validators.required,Validators.pattern(/^\d+$/)]],
      mode:['',Validators.required],
      id:[0],
    })
  }


  fillUnitDetail(data:any): FormGroup {
    return this.fb.group({
      officialDetail:[data?.officerName ? data?.officerName : '',[Validators.required,Validators.pattern(/^[A-Za-z .]+$/)]],
      designation :[ data?.designation ? data?.designation : '',[Validators.required,Validators.pattern(/^[A-Za-z ]+$/)]],
      currentSalary:[data?.currentSalary ? data?.currentSalary : '',[Validators.required,Validators.pattern(/^\d+$/)]],
      mode:[data?.modeOfEngagement ? data?.modeOfEngagement : '',Validators.required],
      id:[data.id]
    })
  }
  deleteUnitCell(rowIndex:any) {
    this.details.removeAt(rowIndex);
  }
  clearFormArray = (formArray: FormArray) => {
    while (formArray.length !== 0) {
      formArray.removeAt(0)
    }
  }
  getUnitCellList(){
    
    
    let payload:any={};
    if(this.sharedService.role['State Nodal Officer'] === this.roleId){
      payload={
        "stateCode":this.stateCode,
      }
    }
    this.authService.getAisheUnitCell(payload).subscribe((res:any)=>{
      if(res.status==200){
        this.listData = res.data;
        this.tempListData = [...this.listData];
        this.listData2=[...this.listData];
        if(this.listData.length>0 && this.listData[0].whetherStateHavingAisheUnitCell!=false){
          this.unitCellForm.get('unitCell')?.setValue(true);
          this.isUnitCell();
        }else{
          this.unitCellForm.get('unitCell')?.setValue(false);
          this.isUnitCell();
        }
        this.handlePageChange(this.page = 1)

        const result = Object.values(
          this.listData.reduce((acc, curr) => {
            const stateName = curr.refState.name;
        
            if (!acc[stateName]) {
              acc[stateName] = {
                state: stateName,
                stateCode:curr.refState.id,
                permanent: 0,
                contractual: 0,
                totalSalary: 0,
                psalary:0,
                csalary:0,
                unitCell:curr.whetherStateHavingAisheUnitCell,
              };
            }
        
            if (curr.modeOfEngagement === 'P') {
              acc[stateName].permanent += 1;
              acc[stateName].psalary+=curr.currentSalary;
            } else if (curr.modeOfEngagement === 'C') {
              acc[stateName].contractual += 1;
              acc[stateName].csalary+=curr.currentSalary;
            }
        
            acc[stateName].totalSalary += curr.currentSalary;
        
            return acc;
          }, {} as Record<string, any>)
        );
        this.moeUnitCellList=result;
        console.log(result);
        this.handlePageChange2(this.page = 1)
      }
    })
  }
  submit(){
    if (this.unitCellForm.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true
      this.markFormGroupTouched(this.unitCellForm);
      return;
    } else {
      this.isFormInvalid = false;
      let finaldata=this.unitCellForm.getRawValue();
      let payload:any=[];
      if(finaldata.details.length>0){
        finaldata.details.forEach((element:any) => {
          payload.push({
              "currentSalary": element.currentSalary,
              "designation": element.designation,
              "id": element.id,
              "modeOfEngagement": element.mode,
              "officerName": element.officialDetail,
              "refState": {
                "id": finaldata.state,
              },
              "whetherStateHavingAisheUnitCell": finaldata.unitCell
            
          })
        });

      }else{//state having no unit cell
        if(this.listData.length>0){
        let dialogRef = this.dialog.open(UnitcellDialogComponent, {
          width: '25%',
          height: '20%',
          data: {action: this.listData.length>1?'multiple':'single' },
          disableClose: true,
        });
        dialogRef.afterClosed().subscribe((result) => {
          if(result){
            let payload2={"stateCode":this.stateCode,"whetherStateHavingAisheUnitCell":finaldata.unitCell}
            this.authService.deleteAllAisheUnitCell(payload2).subscribe((res:any)=>{
              if(res.status==200){
                this.saveAfterfalse(payload2);
                this.sharedService.showSuccess();
                this.getUnitCellList();
              }
            })
          }
        })
        }else{
          let payload2={"stateCode":this.stateCode,"whetherStateHavingAisheUnitCell":finaldata.unitCell}
          this.authService.deleteAllAisheUnitCell(payload2).subscribe((res:any)=>{
            if(res.status==200){
              this.saveAfterfalse(payload2);
              // this.sharedService.showSuccess();
              // this.getUnitCellList();
            }
          })
        }



      }

      if(finaldata.unitCell){
        if(this.listData.length>0 && this.listData[0].whetherStateHavingAisheUnitCell==false){
          let dataToDelete=this.listData[0];
          this.delteDirect(dataToDelete);
        }
        this.authService.saveOrUpdateAisheUnitCell(payload).subscribe((res:any)=>{
          if(res.status==200){
            this.sharedService.showSuccess();
            this.reset();
            // this.getUnitCellList();
          }
        })
      }

    }
  }
  saveAfterfalse(payload:any){
    payload.id=0;
    payload.refState={
      "id": this.stateCode,
    }
    delete payload.stateCode;
    let newPayload=[payload];
    this.authService.saveOrUpdateAisheUnitCell(newPayload).subscribe((res:any)=>{
      if(res.status==200){
        this.sharedService.showSuccess();
        this.reset();
        // this.getUnitCellList();
      }
    })
  }
  markFormGroupTouched(formGroup: FormGroup | FormArray) {
    Object.values(formGroup.controls).forEach(control => {
      if (control instanceof FormControl) {
        control.markAsTouched();
      } else if (control instanceof FormGroup || control instanceof FormArray) {
        this.markFormGroupTouched(control);
      }
    });
  }
  reset(){
    this.clearFormArray(this.details);
    this.getUnitCellList();
    // this.addUnitDetails();
    // this.unitCellForm.get('unitCell')?.setValue(false);
    // this.unitCellForm.reset();
  }
  getDesignation(){
    // this.authService.getDesignation().subscribe((res:any)=>{
    //   console.log(res)
    // })
  }
  getStateList(){
    this.authService.getState().subscribe(
      (res) => {
        if (res && res.length) {
          this.stateListArray = [];
          this.stateListArray = res;
          this.stateListArray = this.stateListArray.sort((a: { name: number; }, b: { name: number; }) => a.name > b.name ? 1 : -1)
          this.stateList = this.stateListArray.slice();

        }
      },
      (err) => { }
    );
  }
  handlePageChange(event: any) {
    this.page = event
    this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
      this.EndLimit = this.StartLimit + Number(this.pageSize)
    var a = Math.ceil(this.listData2.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.listData2.length);
    } else {
      this.pageData = Math.min(this.StartLimit + Number(this.pageSize), this.listData2.length - 1);
    }

  }
  handlePageChange2(event: any) {
    this.page = event
    this.StartLimit = ((this.page - 1) * Number(this.pageSize)),
      this.EndLimit = this.StartLimit + Number(this.pageSize)
    var a = Math.ceil(this.moeUnitCellList.length / Number(this.pageSize));
    if (a === event) {
      this.pageData2 = Math.min(this.StartLimit + Number(this.pageSize), this.moeUnitCellList.length);
    } else {
      this.pageData2 = Math.min(this.StartLimit + Number(this.pageSize), this.moeUnitCellList.length - 1);
    }

  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1)
  }

  async updateResults() {
    this.listData2 = []
    this.listData2 = this.searchByValue(this.tempListData);
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
  isUnitCell(){
   this.showUnitCellDetail=this.unitCellForm.get('unitCell')?.value;
   if(this.unitCellForm.get('unitCell')?.value){
    if(this.details.length==0){
      this.addUnitDetails();
    }
   }else{
    //NO data clear
    this.clearFormArray(this.details);
   }
  
  }
  delete(data:any){
    let dialogRef = this.dialog.open(UnitcellDialogComponent, {
      width: '25%',
      height: '20%',
      data: {action: 'single' },
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        let payload={"id":data.id};
        this.authService.deleteAisheUnitCell(payload).subscribe((res:any)=>{
          if(res.status==200){
            this.sharedService.showDelete();
            this.getUnitCellList();
            
          }
        })
      }
    });

  }
  delteDirect(data:any){
    let payload={"id":data.id};
    this.authService.deleteAisheUnitCell(payload).subscribe((res:any)=>{
      if(res.status==200){
        // this.sharedService.showDelete();
        this.getUnitCellList();
        
      }
    })
  }

  getStateDetail(item:any){
    this.showStatewiseData=true;
    let payload:any={};
      payload={
        "stateCode":item.stateCode,
          }
    this.authService.getAisheUnitCell(payload).subscribe((res:any)=>{
      if(res.status==200){
        this.listData2 = res.data;
        this.tempListData2 = [...this.listData2];
        this.handlePageChange(this.page = 1)
      }
    })
  }
    // Function to download the table data as an Excel file
    downloadExcel() {
     const tableHeaders = ['SNO','State','AISHE Unit Cell','Officer Name', 'Designation', 'Current Annual Salary(Rs.)','Mode of Engagement'];
      const tableData = this.listData.map((row, i) => [
        // i+1,
        row.refState.name,
        row.whetherStateHavingAisheUnitCell==true?'Yes':'No',
        row.officerName, 
        row.designation, 
        row.currentSalary, 
        row.modeOfEngagement==='P'?'Permanent':'Contractual',
  
      ]);
     tableHeaders.shift();
      let param={
        tableHeaders:tableHeaders,
        tableData:tableData,
        // excelName:'Merged Insitutions',
        downloadExcelName:'Detail of AISHE Unit Cell',
        setHeaderCollumnWidths:[
          { wpx: 170 },//state
          { wpx: 100},//whetherStateHavingAisheUnitCell
          { wpx: 150 },//officerName
          { wpx: 160 }, //designation
          { wpx: 180 },//currentSalary,
          { wpx: 150 },//modeOfEngagement 
  
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
  
}
