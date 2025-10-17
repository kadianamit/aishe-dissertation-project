import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';
import * as XLSX from 'xlsx-js-style';
@Component({
  selector: 'app-state-wise-monitoring',
  templateUrl: './state-wise-monitoring.component.html',
  styleUrls: ['./state-wise-monitoring.component.scss'],
})
export class StateWiseMonitoringComponent implements OnInit {
  surveyYear: any;
  surveyYearList: any[] = [];
  variables: any[] = [];
  stateList: any[] = [];
  stateWiseMoniterList: any[] = [];
  filteredItems: any[] = [];
  list: any[] = [];
  StartLimit: number = 0;
  universityExpectedForm1: number = 0
  universitySubmitForm1: any = 0;
  pendingFormU1: any = 0;
  collegeExpectedForm1: any = 0;
  collegeSubmitForm1: any = 0;
  pendingFormCo1: any = 0;
  standaloneExpectedForm1: any = 0;
  standaloneSubmitForm1: any = 0;
  pendingFormStandalone1: any = 0;
  percentageU1: number = 0
  pencentageFormCollege1: any = 0
  percentageFormStandalone1: any = 0


  constructor(
    public authService: AuthService,
    public sharedService: SharedService,
  ) {
  }

  ngOnInit(): void {
    this.getSurveyYear();
    this.getStateMoniter(this.sharedService.currentSurveyYear);
    this.surveyYear = (this.sharedService.currentSurveyYear.toString());
  }
  getSurveyYear() {
    this.authService.getSurveyYear().subscribe(
      (res) => {
        const data = res.filter((item: any) => { return item.surveyYear !== '2025' })
        this.surveyYearList = data;

      },
      (err) => { }
    );
  }

  getState() {
    this.authService.getStatePrivate().subscribe(
      (res) => {
        if (res && res.length) {
          this.variables = [];
          this.variables = res;
          this.stateList = this.variables.slice();
        }
      },
      (err) => { }
    );
  }

  resetfield() {
    this.filteredItems = [];
    this.stateWiseMoniterList = [];
    this.universityExpectedForm1 = 0
    this.universitySubmitForm1 = 0;

    this.pendingFormU1 = 0;
    this.collegeExpectedForm1 = 0;
    this.collegeSubmitForm1 = 0;
    this.pendingFormCo1 = 0;
    this.standaloneExpectedForm1 = 0;
    this.standaloneSubmitForm1 = 0;
    this.pendingFormStandalone1 = 0;

    this.percentageU1 = 0
    this.pencentageFormCollege1 = 0;
    this.percentageFormStandalone1 = 0;
  }
  getStateMoniter(data: any) {
    this.resetfield()

    if (!this.surveyYear && !data) {
      this.sharedService.showValidationMessage('Please select survey year');
      return;
    }

    let payload = {
      surveyYears: data,
    };
    this.authService.getStateWiseProgress(payload).subscribe((res) => {
      if (res.data && res.data.length) {
        this.list = [];
        this.list = res.data;

        const dataM = this.list.map((item: any) => {
          const percentageFormStandalone = item?.standaloneExpectedForm
            ? Math.round((item?.standaloneSubmitForm / item?.standaloneExpectedForm) * 100)
            : 0;

          const pencentageFormCollege = item?.collegeExpectedForm
            ? Math.round((item.collegeSubmitForm / item?.collegeExpectedForm) * 100)
            : 0;

          const percentageU = item?.universityExpectedForm
            ? Math.round((item.universitySubmitForm / item.universityExpectedForm) * 100)
            : 0;

          return {
            stateName: item?.stateName || '',
            stateCode: item?.stateCode || '',
            universityExpectedForm: item?.universityExpectedForm || 0,
            universitySubmitForm: item?.universitySubmitForm || 0,
            percentageU: percentageU,
            pendingFormU: (item?.universityExpectedForm || 0) - (item?.universitySubmitForm || 0),
            collegeExpectedForm: item?.collegeExpectedForm || 0,
            collegeSubmitForm: item?.collegeSubmitForm || 0,
            pencentageFormCollege: pencentageFormCollege,
            pendingFormCo: (item?.collegeExpectedForm || 0) - (item?.collegeSubmitForm || 0),
            standaloneExpectedForm: item?.standaloneExpectedForm || 0,
            standaloneSubmitForm: item?.standaloneSubmitForm || 0,
            pendingFormStandalone: (item?.standaloneExpectedForm || 0) - (item?.standaloneSubmitForm || 0),
            percentageFormStandalone: percentageFormStandalone,
          };
        });

        this.stateWiseMoniterList = [...dataM];

        this.universityExpectedForm1 = this.stateWiseMoniterList.reduce((acc, curr) => acc + (curr.universityExpectedForm || 0), 0);
        this.universitySubmitForm1 = this.stateWiseMoniterList.reduce((acc, curr) => acc + (curr.universitySubmitForm || 0), 0);
        this.pendingFormU1 = this.stateWiseMoniterList.reduce((acc, curr) => acc + (curr.pendingFormU || 0), 0);
        this.collegeExpectedForm1 = this.stateWiseMoniterList.reduce((acc, curr) => acc + (curr.collegeExpectedForm || 0), 0);
        this.collegeSubmitForm1 = this.stateWiseMoniterList.reduce((acc, curr) => acc + (curr.collegeSubmitForm || 0), 0);
        this.pendingFormCo1 = this.stateWiseMoniterList.reduce((acc, curr) => acc + (curr.pendingFormCo || 0), 0);
        this.standaloneExpectedForm1 = this.stateWiseMoniterList.reduce((acc, curr) => acc + (curr.standaloneExpectedForm || 0), 0);
        this.standaloneSubmitForm1 = this.stateWiseMoniterList.reduce((acc, curr) => acc + (curr.standaloneSubmitForm || 0), 0);
        this.pendingFormStandalone1 = this.stateWiseMoniterList.reduce((acc, curr) => acc + (curr.pendingFormStandalone || 0), 0);

        this.percentageU1 = this.universityExpectedForm1
          ? Math.round((this.universitySubmitForm1 / this.universityExpectedForm1) * 100)
          : 0;
        this.pencentageFormCollege1 = this.collegeExpectedForm1
          ? Math.round((this.collegeSubmitForm1 / this.collegeExpectedForm1) * 100)
          : 0;
        this.percentageFormStandalone1 = this.standaloneExpectedForm1
          ? Math.round((this.standaloneSubmitForm1 / this.standaloneExpectedForm1) * 100)
          : 0;

        this.filteredItems = [...dataM];
      } else {
        this.stateWiseMoniterList = [];
      }
    });
  }

  openPop(data: any, en: any, text: string,type:any) {
    if (!data) {
      return;
    }
    let textVal = '';
    const surveyVal = this.surveyYearList.find(e=>e.surveyYear === this.surveyYear)?.surveyYearValue
    const stateName = this.stateList.find(e=>e.surveyYear === data.stateCode)?.name
    if (text === 'expected') {
      textVal = `(Expected Forms in AISHE ${surveyVal})`
    } else if (text === 'submitted') {
      textVal = `(Submitted Forms in AISHE ${surveyVal})`
    } else {
      textVal = `(Not Submitted Forms in AISHE ${surveyVal})`
    }
    let payload = {
      stateCode: data.stateCode,
      surveyYear: this.surveyYear,
      enum: en,
      text: textVal,
      typeName:type,
      stateName:stateName?stateName:''

    }
    this.sharedService.StateWiseMoniterDialog(data, payload);
  }
  excelDownload() {
    const wb = XLSX.utils.book_new();
    const data = this.getTableData();
    const ws: XLSX.WorkSheet = XLSX.utils.aoa_to_sheet(data);

    const customHeader = this.createCustomHeader();
    XLSX.utils.sheet_add_aoa(ws, customHeader, { origin: 'A1' });
    let merge = [];

    // Specify the merged cells range
    merge = [
      { s: { r: 0, c: 1 }, e: { r: 0, c: 4 } }, // Merge cells for "University"
      { s: { r: 0, c: 5 }, e: { r: 0, c: 8 } }, // Merge cells for "College"
      { s: { r: 0, c: 9 }, e: { r: 0, c: 12 } }, //
    ];
    // Apply style and set values in the merged range
    for (let col = 1; col <= 12; col++) {
      const cellAddress = XLSX.utils.encode_cell({ r: 0, c: col });
      if (col >= 1 && col < 5) {
        // Set value for the merged cell
        ws[cellAddress].v = 'University';
      } else if (col >= 5 && col <= 8) {
        // Set value for the merged cell

        ws[cellAddress].v = 'College';
      } else if (col >= 8 && col <= 12) {
        // Set value for the merged cell
        ws[cellAddress].v = 'Standalone';
      }
    }

    // Add the merges property to the worksheet
    ws['!merges'] = merge;
    // Set column widths
    ws['!cols'] = [
      { wch: 20 }, // Width for the first column (e.g., 'State')
      { wch: 15 }, // Width for the second column
      { wch: 15 }, // Width for the third column
      { wch: 10 }, // Width for the fourth column
      { wch: 15 }, // And so on for other columns
      { wch: 15 },
      { wch: 15 },
      { wch: 10 },
      { wch: 15 },
      { wch: 15 },
      { wch: 15 },
      { wch: 10 },
      { wch: 15 },
    ];
    ws['!freeze'] = { xSplit: 0, ySplit: 2, topLeftCell: 'C3' };

    for (var i in ws) {
      if (typeof ws[i] != 'object') continue;
      let cell = XLSX.utils.decode_cell(i);

      ws[i].s = {
        // styling for all cells
        font: {
          name: 'arial',
        },
        alignment: {
          vertical: 'center',
          horizontal: 'center',
          wrapText: '1', // any truthy value here
        },
        border: {
          right: {
            style: 'thin',
            color: '000000',
          },
          left: {
            style: 'thin',
            color: '000000',
          },
        },
      };
      if (cell.r == 0) {
        // first row
        ws[i].s.font = { bold: true, size: 16 };
        ws[i].s.border.bottom = {
          // bottom border
          style: 'thin',
          color: '000000',
        };
      }
      if (cell.r == 1) {
        ws[i].s.font = { bold: true, size: 16 };
        ws[i].s.border.bottom = {
          // bottom border
          style: 'thin',
          color: '000000',
        };
      }
      if (cell.r) {
        ws[i].s.border.bottom = {
          // bottom border
          style: 'thin',
          color: '000000',
        };
      }

      if ((cell.r == 0 && cell.c == 1) || (cell.r == 0 && cell.c == 4)) {
        ws[i].s.fill = {
          // background color
          patternType: 'solid',
          fgColor: { rgb: 'E9E9E9' },
          bgColor: { rgb: '000000' },
        };
      }
      if (
        (cell.r == 1 && cell.c == 1) ||
        (cell.r == 1 && cell.c == 1) ||
        (cell.r == 1 && cell.c == 2) ||
        (cell.r == 1 && cell.c == 3) ||
        (cell.r == 1 && cell.c == 4)
      ) {
        ws[i].s.fill = {
          // background color
          patternType: 'solid',
          fgColor: { rgb: 'E9E9E9' },
          bgColor: { rgb: '000000' },
        };
      }
      if (
        (cell.r == 0 && cell.c == 5) ||
        (cell.r == 0 && cell.c == 6) ||
        (cell.r == 0 && cell.c == 7) ||
        (cell.r == 0 && cell.c == 8)
      ) {
        ws[i].s.fill = {
          // background color
          patternType: 'solid',
          fgColor: { rgb: 'C3C3C3' },
          bgColor: { rgb: '000000' },
        };
      }

      if (
        (cell.r == 1 && cell.c == 5) ||
        (cell.r == 1 && cell.c == 6) ||
        (cell.r == 1 && cell.c == 7) ||
        (cell.r == 1 && cell.c == 8)
      ) {
        ws[i].s.fill = {
          // background color
          patternType: 'solid',
          fgColor: { rgb: 'C3C3C3' },
          bgColor: { rgb: '000000' },
        };
      }

      if (cell.r === this.filteredItems.length + 2) {
        ws[i].s.font = { bold: true, size: 16 };
        ws[i].s.fill = {
          patternType: 'solid',
          fgColor: { rgb: 'C3C3C3' },
          bgColor: { rgb: 'C3C3C3' },
        };
      }
    }

    XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

    XLSX.writeFile(wb, 'StateWiseMonitoring.xlsx');
  }

  getTableData(): any[][] {
    const tableData = [
      [
        'State',
        'Forms Expected',
        'Forms Submitted',
        '(%)',
        'Not Submitted',
        'Forms Expected',
        'Forms Submitted',
        '(%)',
        'Not Submitted',
        'Forms Expected',
        'Forms Submitted',
        '(%)',
        'Not Submitted',
      ],
      [],
    ];

    this.filteredItems.forEach((element, i) => {
      tableData.push([
        element.stateName,
        element.universityExpectedForm,
        element.universitySubmitForm,
        element.percentageU,
        element.pendingFormU,
        element.collegeExpectedForm,
        element.collegeSubmitForm,
        element.pencentageFormCollege,
        element.pendingFormCo,
        element.standaloneExpectedForm,
        element.standaloneSubmitForm,
        element.percentageFormStandalone,
        element.pendingFormStandalone,
      ]);
    });

    tableData.push(['Total', this.universityExpectedForm1, this.universitySubmitForm1, this.percentageU1, this.pendingFormU1,
      this.collegeExpectedForm1, this.collegeSubmitForm1, this.pencentageFormCollege1, this.pendingFormCo1,
      this.standaloneExpectedForm1, this.standaloneSubmitForm1, this.percentageFormStandalone1, this.pendingFormStandalone1]);
    return tableData;
  }

  createCustomHeader(): any[][] {
    let header: any[][];
    header = [
      [
        '',
        'University',
        '',
        '',
        '',
        '',
        'College',
        '',
        '',
        '',
        '',
        'Standalone',
      ],
      [
        'State',
        'Forms Expected',
        'Forms Submitted',
        '(%)',
        'Not Submitted',
        'Forms Expected',
        'Forms Submitted',
        '(%)',
        'Not Submitted',
        'Forms Expected',
        'Forms Submitted',
        '(%)',
        'Not Submitted',
      ],
    ];

    return header;
  }
}
