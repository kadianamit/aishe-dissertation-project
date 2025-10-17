import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { Menu } from '../menu';




@NgModule({
  declarations: [
    HomeComponent,

   
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers:[Menu]
})
export class HomeModule { }
