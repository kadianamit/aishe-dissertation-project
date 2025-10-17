import { Injectable } from '@angular/core';
import { CanDeactivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Injectable({
  providedIn: null
})
export class DeactivatedGuard implements CanDeactivate<unknown> {
  constructor(public localService: LocalserviceService,public sharedService:SharedService) {

  }
  canDeactivate(
    component: SharedService,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.localService.getData('en') === 'encryptedVal') {
      return true;
    } else {
      this.sharedService.session()
      this.localService.clearData();
      window.sessionStorage.clear();
      return false;
    }
  }

}
