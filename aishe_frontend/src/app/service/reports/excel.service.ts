import { Injectable } from '@angular/core';
import * as XLSX from 'xlsx-js-style';
   


@Injectable({
  providedIn: 'root'
})
export class ExcelService {

  constructor() { }

  



  public exportExcel(reportData:any){

    let reportNumber = reportData.reportNumber;
    let headerColumns = reportData.headerColumns;
    let fieldsData:any[] = reportData.fieldsData;

    let targetTableElm = document.getElementById(reportNumber);
    // we are not using work book because we can't apply style on book so we used table sheet
        let ws = XLSX.utils.table_to_sheet(targetTableElm, <XLSX.Table2SheetOpts>{
          sheet: reportNumber,
          display: true       //to skip hidden element which has style display:none in html table to not export in excel
        });

        this.setExcelStyle(ws,headerColumns);

      if(fieldsData){
        let rowHeights=[];
        for(let i =0;i<fieldsData.length;i++) {

          if(fieldsData[i].value1){
            if(fieldsData[i].value1.length>30 )   //if first row field has length > 30 then increase row size as per length
            {
              rowHeights[i+1] = {hpx:fieldsData[i].value1.length};
            }
          }
          if(fieldsData[i].value2){
            if(fieldsData[i].value2.length>30 )   //if first row field has length > 30 then increase row size as per length
            {
              rowHeights[i+1] = {hpx:fieldsData[i].value2.length};
            }
          }
        }
        //console.log("rowHeights: ",rowHeights);
        ws['!rows'] = rowHeights;
      }


        //now create a blank excel workbook
      let wb = XLSX.utils.book_new();

      //add worksheet to workbook
      XLSX.utils.book_append_sheet(wb,ws);

        XLSX.writeFile(wb, `${reportNumber}.xlsx`);

  }

  public exportToExcel(reportNumber:string,headerColumns?:string[]){
    let targetTableElm = document.getElementById(reportNumber);
    // we are not using work book because we can't apply style on book so we used table sheet
        let ws = XLSX.utils.table_to_sheet(targetTableElm, <XLSX.Table2SheetOpts>{
          sheet: reportNumber,
          display: true       //to skip hidden element which has style display:none in html table to not export in excel
        });

        this.setExcelStyle(ws,headerColumns);

       if(reportNumber == 'Report 1'
          ||reportNumber == 'Report 2'
          ||reportNumber == 'Report 3'){

            ws['!rows'] = [{hpx:30},{hpx:30},{hpx:30}]
        }


        //now create a blank excel workbook
      let wb = XLSX.utils.book_new();

      //add worksheet to workbook
      XLSX.utils.book_append_sheet(wb,ws);

        XLSX.writeFile(wb, `${reportNumber}.xlsx`);
  }

 public setExcelStyle(ws: XLSX.WorkSheet,headerColumns?:string[]) {


   let colsWidthArray = [];


  if(headerColumns){
    for(let h=0; h < headerColumns?.length ; h++){
      colsWidthArray.push( {wch: headerColumns[h].length +5 });
      
    }
  }


  ws['!cols'] = colsWidthArray;

  //loop all cells in excel worksheet
  for (let i in ws) {

    // console.log(ws[i]);

    if (typeof ws[i] != 'object') continue;     //skip if not a cell objects

    let cell = XLSX.utils.decode_cell(i);    //get cellAddress to work with row number or column number


    if(cell.r == 0){    //set title style
          ws[i].s = {
            font: {
              name: 'arial',
              fgColor:{rgb: "red"},
              sz:"20",
              bold:true,
            },
            alignment: {
              vertical: 'center',
              horizontal: 'center',
            },
        };
    }
    else{

      //set styling for all cells
      ws[i].s = {
                font: {
                  name: 'arial',
                  color:{rgb: "red"},
                  sz:"14",
          
                },
                alignment: {
                  vertical: 'center',
                  horizontal: 'center',
                  wrapText: true,     
                  bold:true,        //true: to wrap large text within cell, false: to not wrap
                },
            };
    }

  }

}
}
