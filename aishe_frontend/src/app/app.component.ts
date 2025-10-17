import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { SharedService } from './shared/shared.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'aishe';
  showProfileBtn: any;
  // close() {
  //   window.close();
  // }
  // public doUnload(): void {
  //     // this.doBeforeUnload();
  //     window.sessionStorage.removeItem('coutwebDCF');
  //     window.localStorage.removeItem('coutwebDCF');
  //     this.Cookie.delete('coutwebDCF1');
  //     this.Cookie.delete('coutwebDCF');
  //     this.Cookie.delete('countLogin');
  //     window.localStorage.removeItem('openTabs1');

  // }

  constructor(
    public sharedService: SharedService,
    public router: Router,
    public Cookie: CookieService,
    public cdRef: ChangeDetectorRef
  ) {}

  store: any;
  openTabs1: any;
  ngOnInit(): void {
    setTimeout(() => {
      this.sharedService.global_loader = true;
    }, 0);
    // registerOpenTab FUNCTION working
    // const registerOpenTab = () => {
    //   let tabsOpen = 1;
    //   while (localStorage.getItem('openTab' + tabsOpen) !== null) {
    //     tabsOpen++;
    //   }
    //   localStorage.setItem('openTab' + tabsOpen, 'open');
    //   if (localStorage.getItem('openTab2') !== null) {
    //     window.alert(
    //       'This application is already running in ' +
    //         (tabsOpen - 1) +
    //         ' other browser tab(s).'
    //     );
    //     window.open("about:blank", '_self', '');
    //     window.location.href="javascript:window.close()"
    //   }
    // };

    // unregisterOpenTab FUNCTION working
    // const unregisterOpenTab = () => {
    //   let tabsOpen = 1;
    //   while (localStorage.getItem('openTab' + tabsOpen) !== null) {
    //     tabsOpen++;
    //   }
    //   localStorage.removeItem('openTab' + (tabsOpen - 1));

    // };

    // // EVENT LISTENERS working
    // window.addEventListener('load', registerOpenTab);
    // window.addEventListener('beforeunload', unregisterOpenTab);

// not working

    //  this.stor = window.localStorage;
    //    window.addEventListener('load', (event) => {

    //   this.openTabs1 = this.stor.getItem("openTabs1");

    //     if (this.openTabs1) {

    //       this.openTabs1++;
    //       this.stor.setItem("openTabs1", this.openTabs1)

    //      if(this.openTabs1 > 1) {

    //       window.open("about:blank", "_self");
    //       window.close()

    //      }
    //     }else {
    //              this.stor.setItem("openTabs1", 1)
    //          }
    //   });

    // window.addEventListener('beforeunload', (event) => {
    //   event.returnValue = `You have unsaved changes, leave anyway?`;

    //   if (this.openTabs1 > 1) {

    //     this.Cookie.delete('countLogin');
    //     window.localStorage.removeItem('openTabs1');

    //   }else {
    //            this.stor.setItem("openTabs1", 1)
    //        }

    // });
  }

  doBeforeUnload() {
    // alert('hello');
  }
  // eslint-disable-next-line @angular-eslint/use-lifecycle-interface
  ngAfterViewChecked() {
    this.cdRef.detectChanges();
  }
}
