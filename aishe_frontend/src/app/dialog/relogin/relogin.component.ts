import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { routes } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-relogin',
  templateUrl: './relogin.component.html',
  styleUrls: ['./relogin.component.scss']
})
export class ReloginComponent implements OnInit {
  public routers: typeof routes = routes;
  confirmButtonText = "Login";
  constructor(public dialogRef: MatDialogRef<ReloginComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any, public router: Router,public localService: LocalserviceService) { }

  ngOnInit(): void {
  }
  login() {
    if (this.localService.getData('userData') !== 'true') {
      // window.location.reload()
      this.router.navigate([this.routers.INSTITUTIONALLOGIN])
      this.localService.clearData();
      this.dialogRef.close();
    } else {
      this.router.navigate([this.routers.DATAUSERLOGIN])
      this.localService.clearData();
      this.dialogRef.close()
    }
  }
  async reload(url: string): Promise<boolean> {
    await this.router.navigateByUrl('/', { skipLocationChange: true });
    return this.router.navigateByUrl(url);
  }
  dataUser() {
    this.router.navigate([this.routers.DATAUSERLOGIN])
    this.localService.clearData();
    this.dialogRef.close()
  }
  logout() {
    if (this.localService.getData('userData') !== 'true') {
      this.router.navigate([this.routers.INSTITUTIONALLOGIN])
    } else {
      this.router.navigate([this.routers.DATAUSERLOGIN])
    }

    this.localService.clearData();
    window.localStorage.clear();
    this.dialogRef.close()
  }
}
