import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Location } from '@angular/common';
import { InstitutionmanagementService } from 'src/app/service/institutionmanagement/institutionmanagement.service';
import * as XLSX from 'xlsx-js-style';
import { SharedService } from 'src/app/shared/shared.service';
import { ActivatedRoute } from '@angular/router';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import { MatDialog } from '@angular/material/dialog';
import { DetailsViewComponent } from '../details-view/details-view.component';

@Component({
  selector: 'app-university-detail',
  templateUrl: './university-detail.component.html',
  styleUrls: ['./university-detail.component.scss'],
})
export class UniversityDetailComponent implements OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  pageSize = 5;
  pageTitle: any;
checkData:any;
  filterForm: FormGroup;
  filterStatesOption: any;
  filterdStates: any;
  filterDistrictOption: any;
  filterDistricts: any;
  filterUniversityOption: any;
  filterUniversity: any;
  filterCollegeTypeOption: any;
  filterCollegeType: any;
  filterUniversityNameOption: any;
  filterUniversityName: any;
  filterStandaloneTypeOption: any;
  filterStandaloneType: any;

  dataSource = new MatTableDataSource();
  matColumns: any;
  searchValue: string = '';
  Showdata: boolean = false;
  isUniversity: boolean = false;
  isCollege: boolean = false;
  isStandalone: boolean = false;
  isNationalImportance: boolean = false;

  constructor(
    private fb: FormBuilder,
    private location: Location,
    public sharedservice: SharedService,
    public institutionM: InstitutionmanagementService,
    private route: ActivatedRoute,
    public dialog: MatDialog
  ) {
    this.filterForm = this.fb.group({
      state: ['ALL'],
      district: ['ALL'],
      universityType: ['ALL'],
      collegeType: ['ALL'],
      universityName: ['ALL'],
      standaloneType: ['ALL'],
    });
    this.route.paramMap.subscribe((params) => {
      this.isCollege = params.get('type') == 'C';
      this.isUniversity = params.get('type') == 'U';
      this.isStandalone = params.get('type') == 'S';
      this.isNationalImportance = params.get('type') == 'INI';
    });
  }

  ngOnInit(): void {
    if (this.isUniversity) {
      this.checkData="University";
      this.route.paramMap.subscribe((params) => {
        this.filterForm.value.universityType = params.get('id');
      });
    }
    if (this.isCollege) {
      this.checkData="College";
      this.route.paramMap.subscribe((params) => {
        this.filterForm.value.collegeType = params.get('id');
      });
    }
    if (this.isStandalone) {
      this.checkData="Standalone";
      this.route.paramMap.subscribe((params) => {
        this.filterForm.value.standaloneType = params.get('id');
      });
    }
    if (this.isNationalImportance) {
      this.checkData="isNationalImportance";
      this.route.paramMap.subscribe((params) => {
        this.filterForm.value.universityName = params.get('id');
      });
    }
    this.getTable();
  }

  getTable() {
    if (this.isUniversity) {
      if (!this.filterForm.value.state) {
        this.sharedservice.showError('Please select state');
        return;
      }

      this.filterForm.value.district =
        this.filterForm.value.district == null
          ? ''
          : this.filterForm.value.district;
      this.filterForm.value.universityType =
        this.filterForm.value.universityType == null
          ? ''
          : this.filterForm.value.universityType;

      this.institutionM
        .getUniversityList(this.filterForm.value)
        .subscribe((res) => {
          if (res.institutionDirectoryDto.length > 0) {
            this.Showdata = true;
            this.dataSource = new MatTableDataSource<any>(
              res.institutionDirectoryDto
            );
            this.dataSource.paginator = this.paginator;
            this.pageTitle = res.institutionDirectoryDto[0].institutionType;
            this.pageTitle =
              this.filterForm.value.universityType == 'ALL'
                ? 'ALL UNIVERSITIES'
                : this.pageTitle;
                this.dataSource.data.forEach((item:any)=>{
                  item.aisheCode='U - '+item.aisheCode;
                });
          }
          // this.matColumns = Object.keys(res.institutionDirectoryDto[0]); //to get all columns name

          this.matColumns = [
            'aisheCode',
            'name',
            'address1',
            'stateName',
            'districtName',
            'webSite',
            'yearOfEstablishment',
            'location',
            //'specializedIn',
          ];
        });
    } else if (this.isCollege) {
      if (!this.filterForm.value.state) {
        this.sharedservice.showError('Please select state');
        return;
      }

      this.filterForm.value.district =
        this.filterForm.value.district == null
          ? ''
          : this.filterForm.value.district;
      this.filterForm.value.universityType =
        this.filterForm.value.universityType == null
          ? ''
          : this.filterForm.value.universityType;

      this.institutionM
        .getCollegeList(this.filterForm.value)
        .subscribe((res) => {
          if (res.institutionDirectoryDto.length > 0) {
            this.Showdata = true;
            this.dataSource = new MatTableDataSource<any>(
              res.institutionDirectoryDto
            );
            this.dataSource.paginator = this.paginator;
            this.pageTitle = res.institutionDirectoryDto[0].institutionType;
            this.pageTitle =
              this.filterForm.value.collegeType == 'ALL'
                ? 'ALL COLLEGE'
                : this.pageTitle;
                this.dataSource.data.forEach((item:any)=>{
                  item.aisheCode='C - '+item.aisheCode;
                });
          }
          this.matColumns = [
            'aisheCode',
            'name',
            'address1',
            'stateName',
            'districtName',
            'webSite',
            'yearOfEstablishment',
            'location',
            'manegement',
           // 'specializedIn',
            'universityName',
            'universityType',
          ];
        });
    } else if (this.isStandalone) {
      if (!this.filterForm.value.state) {
        this.sharedservice.showError('Please select state');
        return;
      }
      this.filterForm.value.district =
        this.filterForm.value.district == null
          ? ''
          : this.filterForm.value.district;
      this.filterForm.value.universityType =
        this.filterForm.value.universityType == null
          ? ''
          : this.filterForm.value.universityType;
      this.institutionM
        .getStandaloneList(this.filterForm.value)
        .subscribe((res) => {
          if (res.institutionDirectoryDto.length > 0) {
            this.Showdata = true;
            this.dataSource = new MatTableDataSource<any>(
              res.institutionDirectoryDto
            );
            this.dataSource.paginator = this.paginator;
            this.pageTitle = res.institutionDirectoryDto[0].institutionType;
            this.pageTitle =
              this.filterForm.value.standaloneType == 'ALL'
                ? 'ALL STANDALONE'
                : this.pageTitle;
                this.dataSource.data.forEach((item:any)=>{
                  item.aisheCode='S - '+item.aisheCode;
                });
          }
          this.matColumns = [
            'aisheCode',
            'name',
            'address1',
            'stateName',
            'districtName',
            'yearOfEstablishment',
            'location',
            'manegement',
            //'specializedIn',
          ];
        });
    } else if (this.isNationalImportance) {
      this.pageTitle =
        this.filterForm.value.universityName ==
        'all india institute of medical science'
          ? 'Institute of National Importance - AIIMS'
          : this.filterForm.value.universityName ==
            'indian institute of information technology'
          ? 'Institute of National Importance - IIIT'
          : this.filterForm.value.universityName ==
            'indian institute of management'
          ? 'Institute of National Importance - IIM'
          : this.filterForm.value.universityName ==
            'indian institute of engineering science and technology'
          ? 'Institute of National Importance - IISER'
          : this.filterForm.value.universityName ==
            'indian institute of technology'
          ? 'Institute of National Importance - IIT'
          : this.filterForm.value.universityName ==
            'indian statistical institute'
          ? 'Institute of National Importance - ISI'
          : this.filterForm.value.universityName ==
            'national institute of desig'
          ? 'Institute of National Importance - NID'
          : this.filterForm.value.universityName ==
            'national institute of fashion technology'
          ? 'Institute of National Importance - NIFT'
          : this.filterForm.value.universityName ==
            'national institute of technology'
          ? 'Institute of National Importance - NIT'
          : this.filterForm.value.universityName ==
            'school of planning & architecture'
          ? 'Institute of National Importance - SPA'
          : this.filterForm.value.universityName ==
            'national institute of pharmaceutical'
          ? 'Institute of National Importance - NIPER'
          : '';

      this.institutionM
        .getNationalUniversity(this.filterForm.value)
        .subscribe((res) => {
          if (res.institutionDirectoryDto.length > 0) {
            this.Showdata = true;
            this.dataSource = new MatTableDataSource<any>(
              res.institutionDirectoryDto
            );
            this.dataSource.paginator = this.paginator;
            // this.pageTitle =  res.institutionDirectoryDto[0].institutionType;
            this.dataSource.data.forEach((item:any)=>{
              item.aisheCode='U - '+item.aisheCode;
            });
          }

          this.matColumns = [
            'aisheCode',
            'name',
            'address1',
            'stateName',
            'districtName',
            'webSite',
            'yearOfEstablishment',
            'location',
           // 'specializedIn',
          ];
        });
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  back(): void {
    this.location.back();
  }
  onReset() {
    this.Showdata = false;
    this.dataSource.data = [];
    this.filterDistrictOption = [];
    this.filterDistricts = [];
    this.filterStandaloneTypeOption = [];
    this.filterStandaloneType = [];
  }

  exportData(exportType: string) {
    if (this.isUniversity) {
      let data: any = [];
      this.dataSource.data.forEach((item: any) => {
        data.push([
          item.aisheCode,
          item.name,
          item.address1 == null || item.address1 == '' || item.address1 == 'No'
            ? '-'
            : item.address1,
          item.stateName,
          item.districtName,
          item.webSite,
          item.yearOfEstablishment == null ||
          item.yearOfEstablishment == '' ||
          item.yearOfEstablishment == 'No'
            ? '-'
            : item.yearOfEstablishment,
            item.location,
          // item.specializedIn == null ||
          // item.specializedIn == '' ||
          // item.specializedIn == 'No'
          //   ? '-'
          //   : item.specializedIn,
        ]);
      });

      let columns = [
        'Aishe Code',
        'Name',
        'Address',
        'State',
        'District',
        'Website',
        'Year Of Establishment',
        'Location',
        //'Specialized In',
      ];

      if (exportType == 'pdf') {
        this.exportToPDF(columns, data);
      } else if (exportType == 'xls') {
        this.exportExcel(columns, data);
      }
    }
    if (this.isCollege) {
      let data: any = [];
      this.dataSource.data.forEach((item: any) => {
        data.push([
          item.aisheCode,
          item.name,
          item.address1 == null || item.address1 == '' || item.address1 == 'No'
            ? '-'
            : item.address1,
          item.stateName,
          item.districtName,
          item.webSite == null || item.webSite == '' || item.webSite == 'No'
            ? '-'
            : item.webSite,
          item.yearOfEstablishment == null ||
          item.yearOfEstablishment == '' ||
          item.yearOfEstablishment == 'No'
            ? '-'
            : item.yearOfEstablishment,
          item.location == null || item.location == '' || item.location == 'No'
            ? '-'
            : item.location,
          item.manegement == null ||
          item.manegement == '' ||
          item.manegement == 'No'
            ? '-'
            : item.manegement,
          // item.specializedIn == null ||
          // item.specializedIn == '' ||
          // item.specializedIn == 'No'
          //   ? '-'
          //   : item.specializedIn,
          item.universityName,
          item.universityType,
        ]);
      });
      let columns = [
        'Aishe Code',
        'Name',
        'Address',
        'State',
        'District',
        'Website',
        'Year Of Establishment',
        'Location',
        'manegement',
        //'Specialized In',
        'University Name',
        'University Type',
      ];

      if (exportType == 'pdf') {
        this.exportToPDF(columns, data);
      } else if (exportType == 'xls') {
        this.exportExcel(columns, data);
      }
    }
    if (this.isStandalone) {
      let data: any = [];
      this.dataSource.data.forEach((item: any) => {
        data.push([
          item.aisheCode,
          item.name,
          item.address1 == null || item.address1 == '' || item.address1 == 'No'
            ? '-'
            : item.address1,
          item.stateName,
          item.districtName,
          item.yearOfEstablishment == null ||
          item.yearOfEstablishment == '' ||
          item.yearOfEstablishment == 'No'
            ? '-'
            : item.yearOfEstablishment,
          item.location == null || item.location == '' || item.location == 'No'
            ? '-'
            : item.location,
          item.manegement == null ||
          item.manegement == '' ||
          item.manegement == 'No'
            ? '-'
            : item.manegement,
          // item.specializedIn == null ||
          // item.specializedIn == '' ||
          // item.specializedIn == 'No'
          //   ? '-'
          //   : item.specializedIn,
        ]);
      });
      let columns = [
        'Aishe Code',
        'Name',
        'Address',
        'State',
        'District',
        'Year Of Establishment',
        'Location',
        'Manegement',
       // 'Specialized In',
      ];

      if (exportType == 'pdf') {
        this.exportToPDF(columns, data);
      } else if (exportType == 'xls') {
        this.exportExcel(columns, data);
      }
    }
    if (this.isNationalImportance) {
      let data: any = [];
      this.dataSource.data.forEach((item: any) => {
        data.push([
          item.aisheCode,
          item.name,
          item.address1,
          item.stateName,
          item.districtName,
          // item.institutionType,
          item.webSite == null || item.webSite == '' || item.webSite == 'No'
            ? '-'
            : item.webSite,
          item.yearOfEstablishment,
          item.location,
          // item.specializedIn == null ||
          // item.specializedIn == '' ||
          // item.specializedIn == 'No'
          //   ? '-'
          //   : item.specializedIn,
          // item.uploadedYear,
          // item.addedYear,
        ]);
      });
      let columns = [
        'Aishe Code',
        'Name',
        'Address',
        'State',
        'District',
        'Website',
        'Year Of Establishment',
        'Location',
       // 'SpecializedIn',
        // 'uploadedYear',
        // 'addedYear',
      ];

      if (exportType == 'pdf') {
        this.exportToPDF(columns, data);
      } else if (exportType == 'xls') {
        this.exportExcel(columns, data);
      }
    }
  }

  private exportToPDF(columns: string[], data: any) {
    //let title = this.isUniversity ? "University Directory" : this.isCollege ? "College Directory" : this.isStandalone ? "Standalone Directory" : '';
    let surveyYear = '(As per AISHE 2022-23)'; //assign survey year
    const doc = new jsPDF({
      orientation: 'landscape',
      unit: 'px',
      format: [1024, 1200],
    });
    doc.text(this.pageTitle.toUpperCase(), 500, 20);
    doc.text(surveyYear, 980, 20);
    autoTable(doc, {
      theme: 'grid',
      columns: columns,
      body: data,
      margin: {
        top: 30,
      },
    });

    let pdfName = this.isUniversity ? 'university' : this.isCollege ? 'college' : this.isStandalone ? 'standalone'  : 'table';
    doc.save(this.pageTitle + '.pdf');
  }

  exportExcel(column: any, data: any) {
    let fileName = this.isUniversity
      ? 'university'
      : this.isCollege
      ? 'college'
      : this.isStandalone
      ? 'standalone'
      : 'table';
    //create cellStyle Object
    let titleStyle = {
      alignment: {
        horizontal: 'center',
      },
      font: {
        bold: true,
      },
    };

    let titleRow = [{ v: this.pageTitle, s: titleStyle }];
    let surveYearRow = { v: '(As per AISHE 2022-23)' }; //v: is for value   s: is for cell styling

    var wb = XLSX.utils.book_new();
    const ws: XLSX.WorkSheet = XLSX.utils.json_to_sheet([]);
    ws['!merges'] = [{ s: { c: 0, r: 0 }, e: { c: column.length - 1, r: 0 } }]; //merge first row till column length

    XLSX.utils.sheet_add_aoa(ws, [titleRow]); //add first row
    XLSX.utils.sheet_add_aoa(ws, [[surveYearRow]], { origin: 'A2' }); //add row at column A and row 2
    XLSX.utils.sheet_add_aoa(ws, [column], { origin: 'A3' }); //add row at column A and row 3
    XLSX.utils.sheet_add_json(ws, data, { origin: 'A4', skipHeader: true }); //add data only starting from column A and row 4
    XLSX.utils.book_append_sheet(wb, ws, fileName);
    XLSX.writeFile(wb, `${this.pageTitle}.xlsx`);
  }
  openURL(url: string) {
    let url1 = url.split('/').pop();

    url1 = url.replace(/\/$/, '');

    if (url1.startsWith('http')) {
      window.open(url1, '_blank');
    } else {
      window.open('//' + url1, '_blank');
    }
  }

  websiteRedirect(url: any) {
      if (url !== null){
      let text = 'This link will take you to an external web site.';
      let webUrl = url;
      if (confirm(text) == true) {
        this.openURL(webUrl);
      } else {
        text = 'You canceled!';
      }
    }
   
  }
 
  openView(element:any){

    //console.log("element: HJKU",element);
this.dialog.open(DetailsViewComponent,{
  width: '75%',
  height:'100%',
  data: {
    dataValue:element,
    user:this.checkData
  },
  
})
  }
}
