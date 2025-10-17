import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatDatepickerModule } from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatTableModule } from '@angular/material/table';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import {MatMenuModule} from '@angular/material/menu';
import {MatChipsModule} from '@angular/material/chips';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatListModule} from '@angular/material/list';
import {MatBadgeModule} from '@angular/material/badge';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatDividerModule } from '@angular/material/divider';
//  import { NgxMatSelectSearchModule } from 'ngx-mat-select-search';
import { ReactiveFormsModule } from '@angular/forms';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectFilterModule } from 'mat-select-filter';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule } from '@angular-material-components/datetime-picker';
import { AngularEditorModule } from '@kolkov/angular-editor';
@NgModule({
   imports: [
      CommonModule,
      MatButtonModule,
      MatToolbarModule,
      MatIconModule,
      MatSidenavModule,
      MatBadgeModule,
      MatListModule,
      MatGridListModule,
      MatFormFieldModule,
      MatInputModule,
      MatSelectModule,
      MatRadioModule,
      MatDatepickerModule,
      MatNativeDateModule,
      MatChipsModule,
      MatTooltipModule,
      MatTableModule,
      MatPaginatorModule,
      MatMenuModule,
      MatAutocompleteModule,
      MatSortModule,
      MatCheckboxModule,
      MatTabsModule,
      MatSlideToggleModule,
      MatDialogModule,
      MatCardModule,
      MatDividerModule,
      MatProgressBarModule ,
      MatProgressSpinnerModule,
      MatExpansionModule,
      MatSelectFilterModule,
      ReactiveFormsModule,
      NgxMatDatetimePickerModule,
      NgxMatTimepickerModule ,
      AngularEditorModule,
      
   ],
   exports: [
      MatButtonModule,
      MatToolbarModule,
      MatIconModule,
      MatSidenavModule,
      MatBadgeModule,
      MatListModule,
      MatGridListModule,
      MatInputModule,
      MatSelectModule,
      MatRadioModule,
      MatDatepickerModule,
      MatFormFieldModule,
      MatChipsModule,
      MatTooltipModule,
      MatTableModule,
      MatPaginatorModule,
      MatMenuModule,
      MatAutocompleteModule,
      MatSortModule,
      MatCheckboxModule,
      MatTabsModule,
      MatSlideToggleModule,
      MatDialogModule,
      MatCardModule,
      MatDividerModule,
      MatNativeDateModule,
      MatProgressBarModule ,
      MatProgressSpinnerModule,
      MatExpansionModule,
      MatSelectFilterModule,
      ReactiveFormsModule,
      NgxMatDatetimePickerModule,
      NgxMatTimepickerModule,
      AngularEditorModule,
      
   ],
   providers: [
      MatDatepickerModule,
   ]
})
export class AngularMaterialModule { }