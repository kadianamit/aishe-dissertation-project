import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from '../shared/shared.service';
import { LocalserviceService } from '../service/localservice.service';

@Component({
  selector: 'app-aishe',
  templateUrl: './aishe.component.html',
  styleUrls: ['./aishe.component.scss']
})
export class AisheComponent implements OnInit {
  name: string = '';
  wasClicked = false;
  toggle: any;
  menu: any;
  subMenu: any;
  icon: string = 'format_indent_decrease'
  constructor(@Inject(DOCUMENT) private document: Document, public sharedService: SharedService,public router:Router,public localService:LocalserviceService) {

  }

  ngOnInit(): void {
    if (!sessionStorage.getItem('token') && !this.localService.getData('userData')) {
       this.router.navigate(['/institutionallogin'])
    }

  }
  openMenu() {
    let className: any;
    className = document.getElementsByClassName('maintogglebtn');
    this.wasClicked = !this.wasClicked;
    if (this.wasClicked) {
      this.document.body.classList.add('sidebar-toggled');
      className[0].classList.add('toggled');
    }
    else {
      this.document.body.classList.remove('sidebar-toggled');
      className[0].classList.remove('toggled');
    }
  }

  toggleSidebar() {
    this.toggle = !this.toggle
    if (this.toggle) {
      this.icon = 'format_indent_increase'
    } else {
      this.icon = 'format_indent_decrease'
    }
    this.sharedService.setSidebar(this.toggle);
  }
  // eslint-disable-next-line @angular-eslint/no-empty-lifecycle-method, @angular-eslint/use-lifecycle-interface
  ngOnDestroy(): void {

  }
}
