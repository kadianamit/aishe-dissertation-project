import { FormGroup } from "@angular/forms";
import { ReportService } from "../service/reports/report.service";
import { props } from "./props";
import { Router } from "@angular/router";
export class utility {

    loginUserObject: any;
    public href: any;
    static href: string;
    static router: any;
    //static router: any;

    constructor(private router: Router) {

        this.href = this.router.url;

        // console.log(this.router.url);

    }

    public static generateCaptcha() {
        let captcha: string = "";
        let counts: number = props.alphaNumeric.length;
        for (var i = 0; i < 6; i++) {
            let alphaNumericIndex: number = Math.floor(Math.random() * counts);
            captcha += props.alphaNumeric.charAt(alphaNumericIndex);
        }
        return captcha;
    }

    public static getLoginlocalStorageUserData(): any {

        const userObj = localStorage.getItem('loginUserObj');
        const userObj1 = "india"
        if (null != userObj) {
            return JSON.parse(userObj); //convert String JSON to user JSON object
        }

        return userObj1;


    }

    public static downloadPdf(base64: string, reportNumber: string) {
        const byteArray = new Uint8Array(atob(base64).split("").map(char => char.charCodeAt(0)));

        const pdfData = new Blob([byteArray], { type: "application/pdf" });
        const fileURL = URL.createObjectURL(pdfData);   //create random url to render on browser
        // window.open(fileURL);

        let pdfName = reportNumber + ".pdf";
        const url = URL.createObjectURL(pdfData);
        const a = document.createElement('a');
        a.href = url;
        a.style.display = 'none';
        a.download = pdfName;
        a.setAttribute('download', pdfName);
        document.body.append(a);
        a.click();
        a.remove();
        URL.revokeObjectURL(url);

    }

    public static downloadAsExcel(base64: string, reportNumber: string): void {

        const byteArray = new Uint8Array(atob(base64).split("").map(char => char.charCodeAt(0)));

        const pdfData = new Blob([byteArray], { type: 'application/octet-stream' });
        const fileURL = URL.createObjectURL(pdfData);   //create random url to render on browser
        // window.open(fileURL);

        let pdfName = reportNumber + ".xlsx";
        // Construct the 'a' element
        let link = document.createElement("a");
        link.download = pdfName;
        link.target = "_blank";

        // Construct the URI
        link.href = fileURL;
        document.body.appendChild(link);
        link.click();

        // Cleanup the DOM
        document.body.removeChild(link);



    }


    public static generatePDF(reportId: string, reportName: string, formRef: FormGroup, reportService: ReportService) {

        let byteArrays: any;
        let valueYear = parseInt(formRef.value['surveyYear']);
        // console.log("surveyYear: ", valueYear);
        // console.log("reportName: ", reportName);

        //console.log("reportName:formRefformRef upendra", formRef);

        if (reportName == 'Category & Gender-Wise Number of Teacher In Institution' && reportId.trim() === "Report 20") {


            formRef.value.reportType = "PDF";
            reportService.getreport20Data(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });

        }

        //call university report api
        if (reportName.includes('University Report')) {
            // let state = formRef.value.addressStateCode;
            // let university = formRef.value.universityCode.code;
            formRef.value.year = valueYear;
            // console.log("state: " + state);
            // console.log("university: " + university);
            // console.log('upendra',formRef.value);
            //call here university report api
            reportService.getReport(formRef.value).subscribe(res => {
                // console.log("report response ghj", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        }
        //call college report api
        else if (reportName.includes('College Report')) {

            // let state = formRef.value.addressStateCode;
            // let university = formRef.value.universityCode.code;
            formRef.value.year = valueYear;
            // console.log("state: " + state);

            //call here college report api

            reportService.getCollagedata(formRef.value).subscribe(res => {
                // console.log("getCollagedata response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        }
        else if (reportName.includes('Standalone Institution Report')) {

            formRef.value.year = valueYear;
            // console.log("formInstitute: ", formRef.value);

            //call here college report api

            reportService.getStandaloneInstituteReport(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        }
        if (reportName == 'State-Wise Number of Institutions') {
            console.log("numberInstitutions : ", formRef.value);
            reportService.getNumberOfInstStateWiseReport(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        } else if (reportName == 'State-Wise Number of Institutions - Urban') {
            reportService.getNumberOfUrbanInstStateWiseReport(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        } else if (reportName == 'State-Wise Number of Institutions - Rural') {
            reportService.getNumberOfRuralInstStateWiseReport(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        } else if (reportName == 'State & Specialisation - Wise Number Of Institutions') {
            reportService.getStateAndSpecWiseInstPdf(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        } else if (reportName == 'Management-Wise Number Of Institutions Attached With University') {
            reportService.getMgmtwiseNumberOfInstitutionUniversityPdf(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        } else if (reportName == 'State-Wise Number Of University Offering Education Through Distance Mode') {
            reportService.getStateWiseNumOfUniversityOfferingDLPdf(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        } else if (reportName == 'District & Category & Gender-Wise Teacher - ALL') {
            reportService.getDistrictCategoryAndGenderWiseTeacherAllPdf(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });

        } else if (reportName == 'State & Category & Gender-Wise Number of Teacher') {
            reportService.getStateCategoryAndGenderWiseTeachingStaffPdf(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        }
        else if (reportName == 'State & Post & Gender-Wise Number of Teacher') {


            reportService.getStatePostAndGenderWiseTeachingStaffpdf(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        }
        else if (reportName == 'Category & Gender-Wise Number of Teacher In Institution') {
            // reportService.getStateWiseNumOfUniversityOfferingDLPdf(formRef.value).subscribe(res => {
            //     // console.log("report response ", res);
            //     byteArrays = res.byteData;

            //     this.downloadPdf(byteArrays);

            // });
        } else if (reportName == 'District & Category & Gender-Wise Teacher - University' || reportId == "Report 116A") {

            reportService.getDistrictCategoryAndGenderWiseTeacherUniversityPdf(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });

        } else if (reportName == 'District & Category & Gender-Wise Teacher - Standalone Institutions' || reportId == "Report 116C") {

            formRef.value.reportType = "PDF";
            reportService.getdistrictCategoryAndGenderWiseTeacherStandaloneInstitute(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });

        } else if (reportName == 'District & Category & Gender-Wise Teacher - College' || reportId == 'Report 116B') {
            reportService.getdistrictCategoryAndGenderWiseTeacherCollegePdf(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        } else if (reportId.trim() == "Report 19") {

            formRef.value.reportType = "PDF";
            reportService.getReport19(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });

        }
        else if (reportId.trim() == "Report 23") {

            formRef.value.reportType = "PDF";
            reportService.getReport23(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });

        } else if (reportId.trim() == "Report 24") {

            formRef.value.reportType = "PDF";
            reportService.getReport24(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });

        } else if (reportId.trim() == "Report 117") {

            formRef.value.reportType = "PDF";
            reportService.getReport117(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });

        } else if (reportId.trim() == "Report 117A") {

            formRef.value.reportType = "PDF";
            reportService.getReport117A(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        } else if (reportId.trim() == "Report 27") {

            formRef.value.reportType = "PDF";
            reportService.getReport27(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });
        }
        else if (reportId.trim() == "Report 117B") {

            formRef.value.reportType = "PDF";
            reportService.getReport117B(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });

        }
        else if (reportId.trim() == "Report 28") {

            formRef.value.reportType = "PDF";
            reportService.getReport28(formRef.value).subscribe(res => {
                // console.log("report response ", res);
                byteArrays = res.byteData;

                this.downloadPdf(byteArrays, reportId);

            });

        } else if (reportId.trim() == "Report 117C") {

            formRef.value.reportType = "PDF";

            reportService.getReport117C(formRef.value).subscribe(res => {
                byteArrays = res.byteData;
                this.downloadPdf(byteArrays, reportId);
            });

        }
    }

}
