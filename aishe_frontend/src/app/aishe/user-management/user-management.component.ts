
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from 'src/app/service/auth.service';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/service/format-datepicker';
import { SharedService } from 'src/app/shared/shared.service';
import { UserManagementService } from './user-management.service';


@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.scss'],
})
export class UserManagementComponent implements OnInit {
  constructor() {}
  ngOnInit(): void {}
}


