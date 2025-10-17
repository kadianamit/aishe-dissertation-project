import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dailogmessage',
  templateUrl: './dailogmessage.component.html',
  styleUrls: ['./dailogmessage.component.css']
})
export class DailogmessageComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data:any) { 
  }

  ngOnInit(): void {
  }

}
