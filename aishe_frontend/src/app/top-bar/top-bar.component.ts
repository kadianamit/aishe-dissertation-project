import { Component, OnInit,OnDestroy } from '@angular/core';
import { jwtDecode } from "jwt-decode";
import { timer, Subscription, Observable, interval } from "rxjs";
import { Pipe, PipeTransform } from "@angular/core";
import { map } from "rxjs/operators";
import { SharedService } from '../shared/shared.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.scss']
})
export class TopBarComponent implements OnInit,OnDestroy {
  countDown!: any;
  countDown$: Observable<any>| null = null;
  counter = 1800;
  tick = 1000;
  future:any;
  showTime:boolean=false;

  constructor(private sharedService:SharedService,public router: Router) {
    // const token:any=sessionStorage.getItem('token');
    // if(token){
    //   this.showTime=true;
    //   const decoded: any = jwtDecode(token);
    //   // console.log(decoded)
    //   const exp = decoded.exp; 
    //   const date = new Date(exp * 1000); 
    //   const isoString = date.toISOString(); 
    //   this.future = new Date(isoString);
    //   this.countDownFunc(this.future);
    // }
   }

  ngOnInit(): void {
  //  this.getTimer();
  }
  getTimer(){
    this.sharedService.sessionExpInfo$.subscribe((res=>{
      if(res=='tokenExpired'){
        this.showTime=false;
        this.countDown$=null;
        this.countDownFunc(null);
      }
      if(res=='tokenSet'){
        const token:any=sessionStorage.getItem('token');
        if(token){
          this.showTime=true;
          const decoded: any = jwtDecode(token);
          // console.log(decoded)
          const exp = decoded.exp; 
          const date = new Date(exp * 1000); 
          const isoString = date.toISOString(); 
          this.future = new Date(isoString);
          this.countDownFunc(this.future);
        }
       
      }
    }))
  }
  ngOnDestroy(){
    this.showTime=false;
    this.countDown.unsubscribe();
  }
  countDownFunc(future:any){
  this.countDown$ = interval(1000).pipe(
      map((x) => {
        return future? Math.floor((future.getTime() - new Date().getTime()) / 1000):null;
     }));
    this.checkCount(); 
  }
  checkCount(){
    this.countDown=this.countDown$?.subscribe((res=>{
      if(res==0){
        sessionStorage.clear();
        window.sessionStorage.clear();
        this.router.navigate(['/institutionallogin']);
      }
    }))
  }

}
@Pipe({
  name: "formatTime"
})
export class FormatTimePipe implements PipeTransform {
  transform(value: number): string {
    const hours: number = Math.floor(value / 3600);
    const minutes: number = Math.floor((value % 3600) / 60);
    return ('00' + hours).slice(-2) + ':' + ('00' + minutes).slice(-2) + ':' + ('00' + Math.floor(value - minutes * 60)).slice(-2);
  }
}