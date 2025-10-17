import { Component, OnInit, ViewChild } from '@angular/core';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';
import { ApproveRejectForeignComponent } from './approve-reject-foreign/approve-reject-foreign.component';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-foreign',
  templateUrl: './foreign.component.html',
  styleUrls: ['./foreign.component.scss'],
})
export class ForeignComponent implements OnInit {
  @ViewChild(ApproveRejectForeignComponent)
  approveRejectComponent!: ApproveRejectForeignComponent;
  selectedIndex: number = 0;
  editKey: Boolean = false;
  editData: any[] = [];
  roleId: any
  selectedRejectIndex: any
  Pending: boolean = false;
  list: Array<any> = [];

  constructor(public localService: LocalserviceService, public shared: SharedService, public authService: AuthService) {
    this.roleId = this.localService.getData('roleId');
    if (this.roleId === this.shared.role['Ministry of Education'] || this.roleId === this.shared.role['SysAdmin']) {
      this.selectedRejectIndex = 4
    } else {
      this.selectedRejectIndex = 3
    }
  }

  ngOnInit(): void {
    // this.getForeignInstitutesDetails()
  }
  getForeignInstitutesDetails() {
    let payload = {
      countryId: 0,
      statusId: 3
    }
    this.authService.getForeignInstitutesList(payload).subscribe(res => {
      if (res && res.length) {
        this.list = res;
        const countMap = this.list.reduce((acc, item) => {
          acc[item.country?.name] = (acc[item.country?.name] || 0) + 1;
          return acc;
        }, {});
        // const countedItems = Object.keys(countMap).map((name) => ({
        //   name,
        //   count: countMap[name]
        // }));
        // // console.log(countedItems)
        // return countedItems;
       
      }

    }, err => {

    })
  }
  tabSelected(event: any) {
    this.selectedIndex = event.index;
    if (this.selectedIndex === 0) {
      this.editKey = false;
      this.Pending = false;
      this.editData = [];
    }
    if (this.selectedIndex === 0) {
      this.editKey = false;
      this.Pending = true;
      // this.editData = [];
    }
    if (this.selectedIndex === 2) {
      this.editKey = false;
      this.Pending = false;
      this.editData = [];
    }
  }
  recieve(e: any) {
    this.editData = [];
    if (e) {
      this.editData.push(e);
      //this.editKey = true;
    }


  }
  back(e: any) {
    if (e) {
      this.editKey = false;
      this.approveRejectComponent.getForeignInstitutesDetails();
      this.editData = [];
    }
  }

  recievePen(e: any) {
    this.editData = [];
    if (e) {
      this.editData.push(e);
      this.editKey = true;
    }


  }
}
