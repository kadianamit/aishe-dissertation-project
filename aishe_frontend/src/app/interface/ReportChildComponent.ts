export interface ReportChildComponent {
    reportTableData: any;
    tableColumns: any;
    Showdata12: false | boolean | undefined;
    isDataLoading:boolean; //to set true, false in child but access in parent using child?.isDataLoading for progress bar

    summaryColumns: any;
    summaryData: any;
    showPdfButton: boolean;

    getReportDataTable(): void;
    generatePDF(): void;
    onReset():void;
    exportAsXLSX(): void;
}