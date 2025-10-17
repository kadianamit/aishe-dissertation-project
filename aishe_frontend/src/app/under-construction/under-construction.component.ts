import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { routes } from '../shared/shared.service';

@Component({
  selector: 'app-under-construction',
  templateUrl: './under-construction.component.html',
  styleUrls: ['./under-construction.component.scss']
})
export class UnderConstructionComponent implements OnInit {

  public routers: typeof routes = routes;
  constructor(public router:Router) { }

  ngOnInit(): void {
  }
  home(){
    this.router.navigate([this.routers.Home])
  }
}
