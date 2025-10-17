import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { routes } from '../routes';
import { Menu } from '../menu';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  public routers: typeof routes = routes;
  menuList:Array<any> = [];
  selected: any = {};
  constructor(public router:Router,public menu:Menu) { }

  ngOnInit(): void {
    console.log(this.menu.menuList)
    // if (sessionStorage.getItem('token')) {
    //   this.router.navigate([this.routers.Home])
    // }
    
  }

  select(type:any, item:any, $event: { stopPropagation: () => any; }) {
    
    this.selected[type] = (this.selected[type] === item ? null : item);
    console.log('Item: ', item);
    $event ? $event.stopPropagation() : null;
  }
  isActive(type:any, item:any) {
    return this.selected[type] === item;
  }
}
