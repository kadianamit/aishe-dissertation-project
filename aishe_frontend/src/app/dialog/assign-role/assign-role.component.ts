import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-assign-role',
  templateUrl: './assign-role.component.html',
  styleUrls: ['./assign-role.component.scss']
})
export class AssignRoleComponent implements OnInit {
  cancelButtonText = "Close"
  allowedRoles: any = [];
  testarray: any = []
  index: number = 0;
  selectedValue: any
  constructor(public dialogRef: MatDialogRef<AssignRoleComponent>,
    @Inject(MAT_DIALOG_DATA) public element: any, public sharedService: SharedService,public localService: LocalserviceService) {
  }

  ngOnInit(): void {
    this.sharedService.getUserMapping.subscribe(res => {
      if (res !== 0) {
        // for (let i = 0; i < this.element.length; i++) {
        //   for (let j = 0; j < res.length; j++) {
        //     if (this.element[i].roleId === res[j].roleId.roleId) {
        //       this.allowedRoles.push({
        //         roleId:res[j].roleId.roleId,
        //         roleDescription:res[j].roleId.roleDescription
        //       })
        //     }else{

        //     }
        //   }

        // }
        this.allowedRoles = []
        this.allowedRoles = res.filter((role: any) => this.element.some((present: any) => present.roleId === role.roleId.roleId));
        for (let i = 0; i < this.allowedRoles.length; i++) {
          if (this.allowedRoles[i].allowedRolesList.length === 0) {
            this.testarray.push({
              roleId: this.allowedRoles[i].roleId.roleId,
              roleDescription: this.allowedRoles[i].roleId.roleDescription,
            })
            this.allowedRoles[i]['allowedRolesList'].push({
              roleId: this.allowedRoles[i].roleId.roleId,
              roleDescription: this.allowedRoles[i].roleId.roleDescription,
            })
          }

        }
      }
    })
  }
  add() {
    // let array=[]
    // if (this.index !== 0) {
    //   let array = this.allowedRoles[this.index].allowedRolesList.filter((ele: any) => ele.roleId === parseInt(this.selectedValue));
    //   this.allowedRoles[this.index].allowedRolesList = array
    // }
    // for (let i = 0; i < this.element.length; i++) {
    //   for (let j = 0; j < this.allowedRoles.length; j++) {
    //     for (let k = 0; k < this.allowedRoles[j].allowedRolesList.length; k++) {
    //       if (this.element[i].roleId === this.allowedRoles[j].allowedRolesList['0'].roleId) {
    //         this.element.splice(i, 1)
    //       }

    //     }
    //   }
    // }
    for (let i = 0; i < this.element.length; i++) {
      for (let j = 0; j < this.testarray.length; j++) {
          if (this.element[i].roleId === this.testarray[j].roleId) {
            this.element.splice(i, 1)
          }
        }
      }
      this.allowedRoles.push({
        allowedRolesList: [...this.element],
        createdAccess: false,
        readAccess: false,
        deleteAccess: false,
        updateAccess: false,
        status: 'A',
        userId: this.localService.getData('userId'),
        roleId: {
          roleId: ''
        }
      })
    }

    removeElement(value: any, i: number) {
      this.selectedValue = value;
      this.testarray.push({
roleId:parseInt(this.selectedValue)
      }) 
      this.index = i;
      let index = this.element.findIndex((ele: any) => ele.roleId === parseInt(value));
      // let array = this.allowedRoles[i].allowedRolesList.filter((ele: any) => ele.roleId === parseInt(value));
      // this.allowedRoles[i].allowedRolesList=array
      // this.element.splice(index, 1)
    }
  }
