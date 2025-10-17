import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { SharedService } from 'src/app/shared/shared.service';
import { RegManagementService } from '../reg-management.service';
import { I } from '@angular/cdk/keycodes';

@Component({
  selector: 'app-view-reg',
  templateUrl: './view-reg.component.html',
  styleUrls: ['./view-reg.component.scss']
})
export class ViewRegComponent implements OnInit {
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  tableSize: number[] = [10, 20, 30, 40, 50];
  userDataTable:any = [];

  constructor(private fb:FormBuilder,private sharedservice:SharedService,private regMangementService:RegManagementService) {

   }

  ngOnInit(): void {
    this.getRegistionData();
  }
  getRegistionData(){
    this.regMangementService.getActivityData().subscribe((res)=>{
      if(res.data){
        this.userDataTable=res.data;
        this.userDataTable.forEach((element:any) => {
          let splitSurvey = element.surveyYear.toString().substring(2, 4);
          let intSurvey = parseInt(splitSurvey)
          let a = intSurvey + 1;
          element['surveyYearFY'] = element.surveyYear + '-' + a;
          if(element.isActive===null){
            element.addClass='red';
            element.status='Permanent Closed';
          }else if(element.isActive===false){
            element.addClass='green';
            if(element.endDateString){
              if(this.convertDateString(element.endDateString)<new Date()){
                element.status='Closed';
                element.addClass='changeStatusColor'; 
              }else{
                element.status='Active'; 
              } 
            }else{
              element.status = 'Active';
            }
             
          }else{
            element.addClass='';
            element.status='Upcoming';
          }
 
        });
      }
    })
   }
   applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  handlePageChange(event: number) {
    this.page = event;
    let fgh = parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * fgh),
      (this.EndLimit = this.StartLimit + fgh);
    var a = Math.ceil(this.userDataTable.length / fgh);
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + fgh, this.userDataTable.length);
    } else {
      this.pageData = Math.min(this.StartLimit + fgh, this.userDataTable.length);
    }
  }
  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
    //this.getData1();
  }
  convertDateString(inputDateStr:any) {
    let [datePart, timePart,aa] = inputDateStr.split(' ');
    timePart=timePart+' '+aa;
    let [day, month, year] = datePart.split('/').map(Number);
    let [time, period] = timePart.split(' ');
    let [hours, minutes, seconds] = time.split(':').map(Number);
    if (period === 'PM' && hours !== 12) {
      hours += 12;
    } else if (period === 'AM' && hours === 12) {
      hours = 0;
    }
    let date = new Date(year, month - 1, day, hours, minutes, seconds);
    // return new Date(date.toString());
    return date
  }
}
