import { Component, OnInit } from '@angular/core';

import { animationFrameScheduler, distinctUntilChanged, endWith, interval, map, takeWhile } from 'rxjs';
import { InstitutionmanagementService } from '../service/institutionmanagement/institutionmanagement.service';


@Component({
  selector: 'app-institution-directory',
  templateUrl: './institution-directory.component.html',
  styleUrls: ['./institution-directory.component.scss']
})
export class InstitutionDirectoryComponent implements OnInit {
  college_count:any;
  standalone_count:any
  univerity_count:any
  instituteOfNationalImportanceCount:any;
  nationalUniversity_count:any;
  obj:any;
  //selectedYearValue: any = "2020";
  source=interval(.00001);
  constructor(private institution:InstitutionmanagementService) {




  }

  ngOnInit(): void {

    console.log();
    this.getCount();
  }

  count1:any
  count2:any
  count3:any
  getCount(){
    //if(this.selectedYearValue){
      //this.localService.saveData("selectedYearValue1",this.selectedYearValue);
// alert(this.selectedYearValue);

    // let surveyyear=this.selectedYearValue;
    // console.log("sy"+surveyyear);
    //this.localService.saveData('surveyYear1', surveyyear),
   // this.institutionmanagement.getData(surveyyear).subscribe((res)=>{
     //this.userData=res.universityCollegeListBean;

    // console.log("res.universityCollegeListBean",res.universityCollegeListBean);
    //})
    //}
    this.institution.institutionCount().subscribe((res)=>{


      this.obj=res;
      this.college_count=res.collegeTypeCount.totalColleges
      this.standalone_count=res.standaloneTypeCount.totalStandalone
      this.univerity_count=res.universityTypeCount.totalUniversity
      this.instituteOfNationalImportanceCount=res.instituteOfNationalImportanceCount.totalInstitutes

       this.count1=this.counter(this.univerity_count)
       this.count2=this.counter(this.college_count)
       this.count3=this.counter(this.standalone_count)

    })
  }

  counter(count:number){
  

    let startTime = animationFrameScheduler.now();

    return interval(0, animationFrameScheduler).pipe(
      // calculate elapsed time
      map(() => animationFrameScheduler.now() - startTime),
      // calculate progress
      map((elapsedTime) => elapsedTime / 500),   //decrease this value to make faster counting
      // complete when progress is greater than 1
      takeWhile((progress) => progress <= 1),
      // apply quadratic ease-out
      // for faster start and slower end of counting
      map((x: number): number => x * (2 - x)),
      // calculate current count
      map((progress) => Math.round(progress * count)),
      // make sure that last emitted value is count
      endWith(count),
      distinctUntilChanged()
    );
  }


  }





