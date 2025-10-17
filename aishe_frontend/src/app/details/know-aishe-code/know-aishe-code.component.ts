import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import { CustomErrorStateMatcher } from 'src/app/shared/validation';

@Component({
  selector: 'app-know-aishe-code',
  templateUrl: './know-aishe-code.component.html',
  styleUrls: ['./know-aishe-code.component.scss'],
})
export class KnowAisheCodeComponent implements OnInit {
  universityListArray: Array<any> = [];
  universityList: Array<any> = [];
  stateListArray: Array<any> = [];
  stateList: Array<any> = [];
  districtListArray: Array<any> = [];
  districtList: Array<any> = [];
  institutionType: any = 'ALL';
  universityId: any;
  stateCode: any;
  districtCode: any;
  distCode: any;
  institutionTypeList: Array<any> = [];
  tempList: Array<any> = [];
  list: Array<any> = [];
  institutionTypeId: any;
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  searchText: any;
  tableHeaders: Array<any> = [];
  tableData: Array<any> = [];
  ministryArray: any = [];
  ministryList: any = [];
  ministryId: any;

  constructor(
    public authService: AuthService,
    public sharedService: SharedService,
    public errorMatcher: CustomErrorStateMatcher
  ) {}

  ngOnInit(): void {
    this.getInstituteType('U');
  }
  getMinistryList() {
    this.authService.administrativeMinistry().subscribe(
      (res) => {
        this.ministryArray = res;
        this.ministryList = this.ministryArray.slice();
      },
      (err) => {}
    );
  }
  getInstituteType(value: any) {
    this.list = [];
    this.distCode = '';
    this.stateCode = '';
    this.institutionTypeId = '';
    this.universityId = '';
    this.ministryId = '';

    if (value === 'R') {
      this.getMinistryList();
      const pa = 'R';
      this.stateCode = 'ALL';
      this.getState(pa);
    } else {
      this.getState(null);

      if (value === 'C') {
        value = 'U';
      }
      this.authService.institutionType(value).subscribe(
        (res) => {
          this.institutionTypeList = res;
        },
        (err) => {}
      );
    }
  }
  getUniversityByType(value: any) {
    this.universityList = this.universityListArray.filter(
      (ele: any) => ele.typeId === value
    );
  }
  getUniversity(stateCode: any) {
    //this.distCode = '';
    //this.universityId = '';
    // if (this.stateCode) {
    //this.institutionTypeId = '';
    //  this.getDistrict(this.stateCode);
    // }
    this.authService.getUniversityList(this.stateCode).subscribe(
      (res) => {
        if (res && res.length) {
          this.universityListArray = [];
          this.universityListArray = res;
          this.universityListArray = this.universityListArray.sort((a, b) =>
            a.name > b.name ? 1 : -1
          );
          this.universityList = this.universityListArray.slice();
        }
      },
      (err) => {
        this.universityList = [];
        this.universityListArray = [];
      }
    );
  }
  getState(value: any) {
    this.authService.getState().subscribe(
      (res) => {
        if (res && res.length) {
          this.stateListArray = [];
          this.stateListArray = res;
          this.stateListArray = this.stateListArray.sort((a, b) =>
            a.name > b.name ? 1 : -1
          );
          if (value === 'R') {
            this.stateListArray.unshift({
              distCode: '0',
              stateCode: 'ALL',
              name: 'ALL',
              sno: 0,
              lgdDistCode: 0,
            });
            this.stateList = this.stateListArray.slice();
          } else {
            this.stateList = this.stateListArray.slice();
          }
        }
      },
      (err) => {}
    );
  }
  getDistrict(stateCode: any) {
    this.authService.getDistrict(stateCode).subscribe(
      (res) => {
        if (res && res.length) {
          this.districtListArray = [];
          this.districtListArray = res;
          this.districtListArray = this.districtListArray.sort((a, b) =>
            a.name > b.name ? 1 : -1
          );

          if (this.institutionType === 'R') {
            this.districtListArray.unshift({
              distCode: 'ALL',
              stateCode: 'ALL',
              name: 'ALL',
              sno: 'ALL',
              lgdDistCode: 'ALL',
            });
            this.districtList = this.districtListArray.slice();
          } else {
            this.districtList = this.districtListArray.slice();
          }
        } else {
          this.districtListArray=[];
          this.districtListArray.unshift({
            distCode: 'ALL',
            stateCode: 'ALL',
            name: 'ALL',
            sno: 'ALL',
            lgdDistCode: 'ALL',
          });
          this.districtList = this.districtListArray.slice();
        }
      },
      (err) => {}
    );
  }
  getData() {
    this.searchText = '';
    this.StartLimit = 0;
    this.EndLimit = 25;
    this.pageData = 0;
    this.pageSize = 25;
    this.page = 1;
    if (!this.stateCode) {
      this.sharedService.showValidationMessage('Please Select State !!!');
      return;
    }
    let payload;
    if (this.ministryId) {
      payload = {
        stateCode: this.stateCode,
        districtCode: this.distCode ? this.distCode : null,
        institutionCategory: this.institutionType,
        subCategory: this.ministryId ? this.ministryId : null,
      };
    } else {
      payload = {
        stateCode: this.stateCode,
        districtCode: this.distCode ? this.distCode : null,
        institutionCategory: this.institutionType,
        subCategory: this.institutionTypeId ? this.institutionTypeId : null,
        universityId: this.universityId ? this.universityId : null,
        status: 'All',
      };
    }
    this.authService.getAisheCode(payload).subscribe(
      (res) => {
        if (res.serviceResponse.data !== 'No Data Found') {
          this.list = res.serviceResponse.data;
          this.list = this.institutionType ==='R'
          ? this.list.sort((a, b) => {
              const codeA = a.aishecode ?? "";
              const codeB = b.aishecode ?? "";
              return codeA > codeB ? 1 : codeA < codeB ? -1 : 0;
            })
          : this.list.sort((a, b) => {
              const nameA = a.institutionName?.trim() || "";
              const nameB = b.institutionName?.trim() || "";
              return nameA > nameB ? 1 : nameA < nameB ? -1 : 0;
            })
          //   this.list = this.list.sort((a, b) => {
          //     if (!a.institutionName) return 1;
          //     if (!b.institutionName) return -1;
          //     return a.institutionName.localeCompare(b.institutionName);
          // });
          this.tempList = [...this.list];
          this.handlePageChange((this.page = 1));
        } else {
          this.list = [];
          this.tempList = [];
        }
      },
      (err) => {}
    );
  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange((this.page = 1));
  }

  async updateResults() {
    this.list = [];
    this.list = this.searchByValue(this.tempList);
    this.handlePageChange((this.page = 1));
  }

  searchByValue(userData: any) {
    return userData.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (
          item.aishecode
            ?.toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.institutionName
            ?.toString()
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.instType
            ?.toString()
            .trim()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.state
            ?.toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.affiliatingUniv
            ?.toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.district
            ?.toString()
            .toLowerCase()
            .includes(this.searchText.trim().toLowerCase()) ||
          item.status
            ?.toString()
            .toLowerCase()
            .includes(this.searchText.trim()) ||
          item.institutionCategory
            ?.toString()
            .toLowerCase()
            .includes(this.searchText.trim())
        );
      }
    });
  }
  handlePageChange(event: any) {
    this.page = event;
    (this.StartLimit = (this.page - 1) * Number(this.pageSize)),
      (this.EndLimit = this.StartLimit + Number(this.pageSize));
    var a = Math.ceil(this.list.length / Number(this.pageSize));
    if (a === event) {
      this.pageData = Math.min(
        this.StartLimit + Number(this.pageSize),
        this.list.length
      );
    } else {
      this.pageData = Math.min(
        this.StartLimit + Number(this.pageSize),
        this.list.length - 1
      );
    }
  }
  reset() {
    this.stateCode = '';
    this.distCode = '';
    this.institutionType = 'ALL';
    this.institutionTypeId = '';
    this.universityId = '';
    this.list = [];
    this.StartLimit = 0;
    this.EndLimit = 25;
    this.pageData = 0;
    this.pageSize = 25;
    this.page = 1;
  }
  downloadPDF() {
    if (this.institutionType !== 'ALL'&&this.institutionType !== 'R') {
      this.tableHeaders = [
        'SNO',
        'AISHE Code',
        this.institutionType === 'S' || this.institutionType === 'C'
          ? 'Institution Name'
          : 'University Name',
        this.institutionType === 'C'
          ? 'Affiliating University'
          : this.institutionType === 'S'
          ? 'Institution Type'
          : 'University Type',
        'State',
        'District',
        'Status',
      ];
    } else if (this.institutionType === 'R') {
      this.tableHeaders = [
        'S.No',
        'AISHE Code',
        'Institute Name',
        'Administrative Ministry',
        'State',
        'District',
      ];
    } else {
      this.tableHeaders = [
        'SNO',
        'Category',
        'AISHE Code',
        'Institute Name',
        'State',
        'District',
        'Status',
        'Name of Affiliated University',
        'Type of Institute',
      ];
    }
    // if (this.institutionType !== 'ALL' && this.institutionType !== 'R') {
    //   this.tableHeaders = [
    //     'SNO',
    //     'Category',
    //     'AISHE Code',
    //     this.institutionType === 'S' || this.institutionType === 'C'
    //       ? 'Institution Name'
    //       : 'University Name',
    //     this.institutionType === 'C'
    //       ? 'Affiliating University'
    //       : this.institutionType === 'S'
    //       ? 'Institution Type'
    //       : 'University Type',
    //     'State',
    //     'District',
    //     'Status',
    //   ];
    // } else {
    //   this.tableHeaders = [
    //     'SNO',
    //     'Category',
    //     'AISHE Code',
    //     'Institute Name',
    //     'Name of Affiliated University',
    //     '	Type of Institute',
    //     'State',
    //     'District',
    //     'Status',
    //   ];
    // }
    if (this.institutionType !== 'ALL'&& this.institutionType !== 'R') {
      this.tableData = this.list.map((row, i) => [
        i + 1,
        row.institutionCategory,
        row.aishecode,
        row.institutionName,
        this.institutionType === 'C'
          ? row.affiliatingUniv
          : this.institutionType === 'S'
          ? row.instType
          : row.instType,
        row.state,
        row.district,
        row.status === 'Eligible' ? 'Active' : 'Inactive',
      ]);
    } else if (this.institutionType === 'R') {
      this.tableData = this.list.map((row, i) => [
        i + 1,
        row.aishecode,
        row.institutionName,
        row.instType,
        row.state,
        row.district,
      ]);
      console.log( this.tableData)
    } else {
      this.tableData = this.list.map((row, i) => [
        i + 1,
        row.institutionCategory,
        row.aishecode,
        row.institutionName,
        row.affiliatingUniv === 'Null' ? '' : row.affiliatingUniv,
        row.instType,
        row.state,
        row.district,
        row.status === 'Eligible' ? 'Active' : 'Inactive',
      ]);
    }

    let param = {
      tableHeaders: this.tableHeaders,
      tableData: this.tableData,
      pdfName: 'AISHE Code List',
      downloadPdfName: 'AISHE Code List.pdf',
      orientationType: 'landscape',
    };
    let a = {
      1: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
      2: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
      3: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
      4: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
      5: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
      6: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
      7: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
      8: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
      9: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
      10: { fillColor: [247, 247, 235], textColor: [0, 0, 0] },
    };
    this.sharedService.downloadPDF(param, a);
  }

  downloadExcel() {
    if (this.institutionType !== 'ALL' && this.institutionType !== 'R') {
      this.tableHeaders = [
        'SNO',
        'Category',
        'AISHE Code',
        this.institutionType === 'S' || this.institutionType === 'C'
          ? 'Institution Name'
          : 'University Name',
        this.institutionType === 'C'
          ? 'Affiliating University'
          : this.institutionType === 'S'
          ? 'Institution Type'
          : 'University Type',
        'State',
        'District',
        'Status',
      ];
    } else if (this.institutionType === 'R') {
      this.tableHeaders = [
        'SNO',
        'AISHE Code',
        'Institute Name',
        'State',
        'District',
        'Administrative Ministry'
      ];
    } else {
      this.tableHeaders = [
        'SNO',
        'Category',
        'AISHE Code',
        'Institute Name',
        'Name of Affiliated University',
        '	Type of Institute',
        'State',
        'District',
        'Status',
      ];
    }
    if (this.institutionType !== 'ALL' && this.institutionType !== 'R') {
      this.tableData = this.list.map((row, i) => [
        // i+1,
        row.institutionCategory,
        row.aishecode,
        row.institutionName,
        this.institutionType === 'C'
          ? row.affiliatingUniv
          : this.institutionType === 'S'
          ? row.instType
          : row.instType,
        row.state,
        row.district,
        row.status === 'Eligible' ? 'Active' : 'Inactive',
      ]);
    } else if (this.institutionType === 'R') {
      this.tableData = this.list.map((row, i) => [
        // i+1,
        row.aishecode,
        row.institutionName,
        row.state,
        row.district,
        row.instType
      ]);
    } else {
      this.tableData = this.list.map((row, i) => [
        // i+1,
        row.institutionCategory,
        row.aishecode,
        row.institutionName,
        row.affiliatingUniv === 'Null' ? '' : row.affiliatingUniv,
        row.instType,
        row.state,
        row.district,
        row.status === 'Eligible' ? 'Active' : 'Inactive',
      ]);
    }

    this.tableHeaders.shift();
    let param = {
      tableHeaders: this.tableHeaders,
      tableData: this.tableData,
      // excelName:'Merged Insitutions',
      downloadExcelName: 'AISHE Code',
      setHeaderCollumnWidths: [
        { wpx: 80 }, //surveyYear
        { wpx: 80 }, //Oldaishecode
        { wpx: 290 }, //oldInstitutionName,
        { wpx: 290 }, //oldUniversityName or affilliated University
        { wpx: 80 }, //state
        { wpx: 88 }, // newAisheCode
        { wpx: 210 }, //newInstitutionName
        { wpx: 270 }, //newUniversityName or affilliated university
        { wpx: 80 }, //actionBy
        { wpx: 130 }, //actionTime
      ],
      headerStyle: {
        font: {
          bold: true,
          color: { rgb: 'FFFFFF' }, // White text color
        },
        fill: {
          fgColor: { rgb: '4CAF50' }, // Green background color
        },
        alignment: {
          horizontal: 'center',
          vertical: 'center',
        },
      },
    };
    this.sharedService.downloadExcel(param);
  }
}
