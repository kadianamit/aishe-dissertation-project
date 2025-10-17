import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, EMPTY } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LocalserviceService } from '../localservice.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
  }),
};
@Injectable({
  providedIn: 'root',
})
export class ReportService {
  constructor(public localService: LocalserviceService,public http:HttpClient){

  }
  getReport136(data: any) {
     let userId = this.localService.getData('userId');
    
    if (data.institution == 'University') {   
   return this.http.get(
    environment.baseUrl +
      `api/report-136?&staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&universityTypeName=${data.universityTypeCode.type}&universityType=${data.universityTypeCode.id}&institutionType=${data.institution}&surveyYear=${data.surveyYear}&exportType=${data.exportType}&stateCode=ALL`,
    httpOptions
  );
    }
    if (data.institution == 'College') {
     
      return this.http.get(
        environment.baseUrl +
          `api/report-136?institutionManagementId=${data.managementName.id}&institutionTypeId=${data.collegeType.id}&institutionTypeName=${data.collegeType.type}&staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&universityTypeName=${data.universityTypeCode.type}&universityType=${data.universityTypeCode.id}&institutionType=${data.institution}&surveyYear=${data.surveyYear}&exportType=${data.exportType}&stateCode=ALL`,
        httpOptions
      );
    }
    if (data.institution == 'Standalone') {
      return this.http.get(
        environment.baseUrl +
          `api/report-136?institutionManagementId=${data.managementName.id}&stateBodyId=${data.Body.id}&stateBodyName=${data.Body.type}&staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&institutionType=${data.institution}&surveyYear=${data.surveyYear}&exportType=${data.exportType}&stateCode=ALL`,
        httpOptions
      );
    }
    
    return this.http.get(
      environment.baseUrl +
        `api/report-136?staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&institutionType=${data.institution}&surveyYear=${data.surveyYear}&exportType=${data.exportType}&stateCode=ALL`,
      httpOptions
    );
  }
  getreport22A(data: any) {
    var userId =this.localService.getData('userId');
    var surveyYear = data.surveyYear
    var stateBodyId = data.Body.id;
    var institutionManagementId = data.managementName.id;
    var institutionTypeId =data.institution=='College Institution'? data.collegeType.type: data.institution=='University'? data.universityTypeCode.id:'ALL';
   var stateName = data.addressStateCode.stateName
   var institutionId =data.institution=='College Institution'?data.collegeType.id : data.institution=='Standalone Institution'?data.Body.id: data.institution=='University'?data.universityName.id:'ALL'
   var institutionCategory =data.institution
   var stateCode =data.addressStateCode.stateCode
   var exportType  = data.exportType
   var institutionParentId = data.universityName.id
   var url
    if(data.institution == 'College Institution'){
      url = `api/report-22A?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionTypeId=${institutionTypeId}&institutionCategory=${institutionCategory}&stateBodyId=ALL&stateName=${stateName}&institutionManagementId=${institutionManagementId}&institutionParentId=${institutionParentId}`
    } 
    else if(data.institution =='University'){
      url =`api/report-22A?stateCode=${stateCode}&institutionTypeId=${institutionTypeId}&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=ALL&stateName=${stateName}`
    } 
    else if(data.institution =='Standalone Institution'){
      url =`api/report-22A?stateCode=${stateCode}&institutionTypeId=ALL&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=${stateBodyId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}`
    } else {
      url = `api/report-22A?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=${stateBodyId}&institutionId=${institutionId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}&institutionParentId=${institutionParentId}`
    }
    return this.http.get(
      environment.baseUrl + url,
      httpOptions
    );
  }
  getReport137(data: any) {
    
    var userId =this.localService.getData('userId');
    var surveyYear = data.surveyYear
    var stateBodyId = data.Body.id;
    var institutionManagementId = data.managementName.id;
    var institutionTypeId =data.institution=='College Institution'? data.collegeType.type: data.institution=='University'? data.universityTypeCode.id:'ALL';
   var stateName = data.addressStateCode.stateName
   var institutionId =data.institution=='College Institution'?data.collegeType.id : data.institution=='Standalone Institution'?data.Body.id: data.institution=='University'?data.universityName.id:'ALL'
   var institutionCategory =data.institution
   var stateCode =data.addressStateCode.stateCode
   var exportType  = data.exportType
   var institutionParentId = data.universityName.id
   var affiliated = data.affiliated !==''? data.affiliated:'ALL';
   var universityType= data.institution=='University'?data.universityTypeCode.id:'ALL'
   var url
   
    if(data.institution == 'College Institution'){
      url = `api/report-137?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionTypeId=${institutionTypeId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}&institutionParentId=${institutionParentId}&constitutedFromColleges=${affiliated}&institutionType=College`
    } 
    else if(data.institution =='University'){
      url =`api/report-137?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&stateName=${stateName}&universityType=${universityType}&constitutedFromColleges=${affiliated}&institutionType=${institutionCategory}`
    } 
    else if(data.institution =='Standalone Institution'){
      url =`api/report-137?stateCode=${stateCode}&institutionTypeId=ALL&surveyYear=${surveyYear}&exportType=${exportType}&stateBodyId=${stateBodyId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}&constitutedFromColleges=${affiliated}&institutionType=Standalone`
    } else {
      url = `api/report-137?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionId=${institutionId}&stateName=${stateName}&institutionType=ALL&universityType=ALL`
    }
    return this.http.get(
      environment.baseUrl + url,
      httpOptions
    );  }
  getReport138(data: any): Observable<any> {
  
    var userId =this.localService.getData('userId');
    var surveyYear = data.surveyYear
    var stateBodyId = data.Body.id;
    var institutionManagementId = data.managementName.id;
    var institutionTypeId =data.institution=='College Institution'? data.collegeType.type: data.institution=='University'? data.universityTypeCode.id:'ALL';
   var stateName = 'ALL'
   var institutionId =data.institution=='College Institution'?data.collegeType.id : data.institution=='Standalone Institution'?data.Body.id: data.institution=='University'?data.universityName.id:'ALL'
   var institutionCategory =data.institution
   var stateCode ='ALL'
   var exportType  = data.exportType
   var institutionParentId = data.universityName.id
   var affiliated = data.institution=='University'? data.affiliated:'ALL';
   
   var url
    if(data.institution == 'College Institution'){
      url = `api/report-138?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionTypeId=${institutionTypeId}&institutionCategory=${institutionCategory}&stateBodyId=ALL&stateName=${stateName}&institutionManagementId=${institutionManagementId}&institutionParentId=${institutionParentId}&constitutedFromColleges=${affiliated}`
    } 
    else if(data.institution =='University'){
      url =`api/report-138?stateCode=${stateCode}&institutionTypeId=${institutionTypeId}&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateName=${stateName}`
    } 
    else if(data.institution =='Standalone Institution'){
      url =`api/report-138?stateCode=${stateCode}&institutionTypeId=ALL&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=${stateBodyId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}&constitutedFromColleges=${affiliated}`
    } else {
      url = `api/report-138?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=${stateBodyId}&institutionId=${institutionId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}&institutionParentId=${institutionParentId}&constitutedFromColleges=${affiliated}`
    }
    return this.http.get(
      environment.baseUrl + url,
      httpOptions
    ); 
   
}
   

  getReport37(data: any) {
    let userId =this.localService.getData('userId');
    let stateCode = data.addressStateCode.stateCode;
    let stateName = data.addressStateCode.stateName;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-37?stateName=${stateName}&stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport36(data: any) {
    // console.log('dj',data);
    let userId =this.localService.getData('userId');
    let stateCode = data.addressStateCode.stateCode;
    let TypeId =  data.universityType.id;
    let stateName = data.addressStateCode.stateName;
    let surveyYear = data.surveyYear;
    let universityType =  data.universityType.type;
    let exportType = data.exportType;
    let universityId='ALL'
    return this.http.get(
      environment.baseUrl +
        `api/report-36?stateName=${stateName}&universityId=${universityId}&stateCode=${stateCode}&universityType=${universityType}&typeId=${TypeId}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport17(data: any) {
    
    let userId =this.localService.getData('userId');
    let stateCode = data.addressStateCode.stateCode;
    let universityTypeId = data.universityTypeCode.id;
    let stateName = data.addressStateCode.stateName;
    let surveyYear = data.surveyYear;
    let universityTypeCode = data.universityTypeCode.type;
     let universityId = 'ALL';
    return this.http.get(
      environment.baseUrl +
        `api/report-17?surveyYear=${surveyYear}&stateCode=${stateCode}&stateName=${stateName}&universityType=${universityTypeCode}&universityTypeId=${universityTypeId}&universityId=${universityId}&exportType=${data.exportType}`,
      httpOptions
    );
  }
  getReport10(data: any) {
    let userId =this.localService.getData('userId');
    let stateCode=data.addressStateCode.stateCode
    return this.http.get(
      environment.baseUrl +
        `api/report-10?surveyYear=${data.surveyYear}&exportType=${data.exportType}&stateCode=${stateCode}`,
      httpOptions
    );
  }



  getReport(data: any): Observable<any> {
  
    let stateCode = data.addressStateCode.stateCode;
    let universityCode = data.universityCode.id;
    let surveyYear = data.surveyYear;
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `basicUniversityReport?stateCode=${stateCode}&surveyYear=${surveyYear}&universityCode=${universityCode}&reportType=${data.reportType}`,
      httpOptions
    );
  }

  getReport18(data: any): Observable<any> {
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    let universityTypeCode = data.universityTypeCode.type;
    let universityTypeId = data.universityTypeCode.id;
    let stateName = data.addressStateCode.stateName;
    let userId =this.localService.getData('userId');
    let universityId='ALL'
    return this.http.get(
      environment.baseUrl +
        `api/report-18?exportType=${exportType}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityType=${universityTypeCode}&universityTypeId=${universityTypeId}&stateName=${stateName}&universityId=${universityId}`,
      httpOptions
    );
  }

  getReport21(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    let stateName = data.addressStateCode.stateName;
    return this.http.get(
      environment.baseUrl +
        `api/report-21?stateName=${stateName}&stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport22(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    let stateName = data.addressStateCode.stateName;

    return this.http.get(
      environment.baseUrl +
        `api/report-22?stateName=${stateName}&stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getreport12(userData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let stateName = userData.addressStateCode.stateName;
    let exportType = userData.exportType;
   // console.log(exportType);
    let universityId = "ALL";

    return this.http.get(
      environment.baseUrl +
        `api/report-12?stateName=${stateName}&stateCode=${StateCode}&universityId=${universityId}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getCollagedata(data: any): Observable<any> {

    if (
      !data.surveyYear ||
      !data.collegeCode ||
      !data.addressStateCode ||
      !data.universityCode
    ) {
      alert('Please select all value');
      return EMPTY;
    }
    let userId =this.localService.getData('userId');
    let collegeCode = data.collegeCode.id;
    let stateCode = data.addressStateCode.stateCode;
    let universityCode = data.universityCode.id;
    let surveyYear = data.surveyYear;

    return this.http.get(
      environment.baseUrl +
        `basicCollegeReport?collegeCode=${collegeCode}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityCode=${universityCode}&reportType=${data.reportType}`,
      httpOptions
    );
  }

  getStandaloneInstituteReport(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let stateCode = data.addressStateCode.stateCode;
    let instituteCode = data.instituteCode.id;
    let surveyYear = data.surveyYear;

    return this.http.get(
      environment.baseUrl +
        `basicStandaloneReport?surveyYear=${surveyYear}&stateCode=${stateCode}&stateBodyId=ALL&standaloneCode=${instituteCode}&reportType=${data.reportType}`,
      httpOptions
    );
  }

  getInstStateWiseReport(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = yearData.selectedYear;

    return this.http.get(
      environment.baseUrl + `instStateWiseReport?surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getInstStateWiseRuralReport(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = yearData.selectedYear;

    return this.http.get(
      environment.baseUrl + `instStateWiseRuralReport?surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getInstStateWiseUrbanReport(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = yearData.selectedYear;
    return this.http.get(
      environment.baseUrl + `instStateWiseUrbanReport?surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReportUniversityBasicData(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let aisheCode = yearData.universityCode.id;
    let surveyYear = yearData.surveyYear;
    let code = surveyYear > 2020 ? 'U-' : '';
    return this.http.get(
      environment.baseUrl +
        `reportUniversityBasicData?aisheCode=${code}${aisheCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReportCollegeBasicData(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let aisheCode = yearData.collegeCode.id;
    let surveyYear = yearData.surveyYear;
    let code = surveyYear > 2020 ? 'C-' : '';
    return this.http.get(
      environment.baseUrl +
        `reportCollegeData?aisheCode=${code}${aisheCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReportStandaloneBasicData(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let aisheCode = yearData.instituteCode.id;
    let surveyYear = yearData.surveyYear;
    let code = surveyYear > 2020 ? 'S-' : '';

    return this.http.get(
      environment.baseUrl +
        `reportStandaloneBasicData?aisheCode=${code}${aisheCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReportUniversityExcel(data: any): void {
  //  console.log('called getReportUniversityExcel', data);

    if (!data.surveyYear || !data.universityCode) {
      alert('Please select all value');
      return;
    }
    let userId =this.localService.getData('userId');
    let aisheCode = data.universityCode.id;
    let surveyYear = data.surveyYear;
    let fileURL =
      environment.baseUrl +
      `download/universityReport.xlsx?aisheCode=${aisheCode}&surveyYear=${surveyYear}`;

    let link = document.createElement('a');
    // link.target = "_blank";
    link.href = fileURL;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

  getReportCollegeExcel(data: any): void {
   // console.log('called getReportCollegeExcel', data);

    if (!data.surveyYear || !data.collegeCode) {
      alert('Please select all value');
      return;
    }
    let aisheCode = data.collegeCode.id;
    let surveyYear = data.surveyYear;
    let userId =this.localService.getData('userId');
    let fileURL =
      environment.baseUrl +
      `download/collegeReport.xlsx?aisheCode=${aisheCode}&surveyYear=${surveyYear}`;

    let link = document.createElement('a');
    // link.target = "_blank";
    link.href = fileURL;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

  getReportStandaloneExcel(data: any): void {
   // console.log('called getReportStandaloneExcel', data);

    if (!data.surveyYear || !data.instituteCode) {
      alert('Please select all value');
      return;
    }
    let userId =this.localService.getData('userId');
    let aisheCode = data.instituteCode.id;
    let surveyYear = data.surveyYear;

    let fileURL =
      environment.baseUrl +
      `download/standaloneReport.xlsx?aisheCode=${aisheCode}&surveyYear=${surveyYear}`;

    let link = document.createElement('a');
    //link.target = "_blank";
    link.href = fileURL;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

  getnumberOfInstStateWiseReport(yearData: any): Observable<any> {
    // console.log("Called number of institute statewise report");
    let surveyYear = yearData.surveyYear;
    let userId =this.localService.getData('userId');
    let stateCode=yearData.addressStateCode.stateCode
    return this.http.get(
      environment.baseUrl + `numberOfInstStateWise?surveyYear=${surveyYear}&stateCode=${stateCode}`,
      httpOptions
    );
  }

  getStateWiseNumberOfInstitutionsUrban(data: any): Observable<any> {
    let surveyYear = data.surveyYear;
    let userId =this.localService.getData('userId');
    let stateCode=data.addressStateCode.stateCode
    
    return this.http.get(
      environment.baseUrl +
        `numberOfInstStateWiseUrban?surveyYear=${surveyYear}&stateCode=${stateCode}`,
      httpOptions
    );
  }
  getStateWiseNumberOfInstitutionsRural(data: any): Observable<any> {
    let surveyYear = data.surveyYear;
    let userId =this.localService.getData('userId');
    let stateCode=data.addressStateCode.stateCode
    return this.http.get(
      environment.baseUrl +
        `numberOfInstStateWiseRural?surveyYear=${surveyYear}&stateCode=${stateCode}`,
      httpOptions
    );
  }

  getNumberOfInstStateWiseReport(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = yearData.surveyYear;
 let stateCode=yearData.addressStateCode.stateCode
    return this.http.get(
      environment.baseUrl +
        `numberOfInstStateWiseReport?surveyYear=${surveyYear}&reportType=${yearData.reportType}&stateCode=${stateCode}`,
      httpOptions
    );
  }
  getNumberOfRuralInstStateWiseReport(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = yearData.surveyYear;
    let stateCode=yearData.addressStateCode.stateCode
    return this.http.get(
      environment.baseUrl +
        `numberOfRuralInstStateWiseReport?surveyYear=${surveyYear}&reportType=${yearData.reportType}&stateCode${stateCode}`,
      httpOptions
    );
  }
  getNumberOfUrbanInstStateWiseReport(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = yearData.surveyYear;
    let stateCode=yearData.addressStateCode.stateCode

    return this.http.get(
      environment.baseUrl +
        `numberOfUrbanInstStateWiseReport?surveyYear=${surveyYear}&reportType=${yearData.reportType}&stateCode${stateCode}`,
      httpOptions
    );
  }
  getStateAndSpecWiseInst(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = yearData.surveyYear;
let stateCode=yearData.addressStateCode.stateCode
    return this.http.get(
      environment.baseUrl + `stateAndSpecWiseInst?surveyYear=${surveyYear}&stateCode=${stateCode}`,
      httpOptions
    );
  }
  getStateAndSpecWiseInstPdf(yearData: any): Observable<any> {
    let surveyYear = yearData.surveyYear;
    let userId =this.localService.getData('userId');
        let stateCode=yearData.addressStateCode.stateCode
    return this.http.get(
      environment.baseUrl + `stateAndSpecWiseInstPdf?surveyYear=${surveyYear}&reportType=${yearData.reportType}&stateCode=${stateCode}`,
      httpOptions
    );
  }
  getMgmtwiseNumberOfInstitutionUniversity(data: any): Observable<any> {
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `mgmtwiseNumberOfInstitutionUniversity?stateCode=${stateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getMgmtwiseNumberOfInstitutionUniversityPdf(data: any): Observable<any> {
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `mgmtwiseNumberOfInstitutionUniversityPdf?stateCode=${stateCode}&surveyYear=${surveyYear}&reportType=${data.reportType}`,
      httpOptions
    );
  }

  getStateWiseNumOfUniversityOfferingDL(yearData: any): Observable<any> {
let surveyYear = yearData.surveyYear;
    let userId =this.localService.getData('userId');
     let stateCode = yearData.addressStateCode.stateCode;
    return this.http.get(
      environment.baseUrl +
        `stateWiseNumOfUniversityOfferingDL?surveyYear=${surveyYear}&stateCode=${stateCode}&reportType=${yearData.reportType}`,
      httpOptions
    );
  }

  getStateWiseNumOfUniversityOfferingDLPdf(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = yearData.surveyYear;
let stateCode = yearData.addressStateCode.stateCode;
    return this.http.get(
      environment.baseUrl +
        `stateWiseNumOfUniversityOfferingDLPdf?surveyYear=${surveyYear}&reportType=${yearData.reportType}&stateCode=${stateCode}`,
      httpOptions
    );
  }

  getDistrictCategoryAndGenderWiseTeacherAll(yearData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = yearData.surveyYear;

    return this.http.get(
      environment.baseUrl +
        `districtCategoryAndGenderWiseTeacherAll?surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getDistrictCategoryAndGenderWiseTeacherAllPdf(data: any): Observable<any> {
    let surveyYear = data.surveyYear;
    let stateCode = data.addressStateCode.stateCode;
    let postId = data.designation.id;
    let selectionMode = data.selectionMode;
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `districtCategoryAndGenderWiseTeacherAllPdf?postId=${postId}&selectionModeId=${selectionMode}&stateCode=${stateCode}&surveyYear=${surveyYear}&reportType=${data.reportType}`
    );
    }

  getStateCategoryAndGenderWiseTeachingStaffpdf(
    yearData: any
  ): Observable<any> {
    let surveyYear = yearData.surveyYear;
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `getStateCategoryAndGenderWiseTeachingStaff?surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getStatePostAndGenderWiseTeachingStaffpdf(yearData: any): Observable<any> {
    let surveyYear = yearData.surveyYear;
      let deafultStateCode=yearData.defaultState?yearData.defaultState:""
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `statePostAndGenderWiseTeachingStaffPdf?surveyYear=${surveyYear}&reportType=${yearData.reportType}&stateCode=${deafultStateCode}`,
      httpOptions
    );
  }

  getDistrictsData(data: any):Observable<any>{
   
    return this.http.get(`${environment.baseUrl}states/${data.stateCode}/districts`,httpOptions);
      }
  getStatePostAndGenderWiseTeachingStaffTableData(
    yearData: any
  ): Observable<any> {
    let surveyYear = yearData.surveyYear;
     let deafultStateCode=yearData.defaultState?yearData.defaultState:''
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `statePostAndGenderWiseTeachingStaff?surveyYear=${surveyYear}&stateCode=${deafultStateCode}`,
      httpOptions
    );
  }
  getStateCategoryAndGenderWiseTeachingStaffTableData(
    yearData: any
  ): Observable<any> {
    let surveyYear = yearData.surveyYear;
      let deafultStateCode=yearData.defaultState?yearData.defaultState:''
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `stateCategoryAndGenderWiseTeachingStaff?surveyYear=${surveyYear}&&stateCode=${deafultStateCode}`,
      httpOptions
    );
  }
  getDistrictCategoryAndGenderWiseTeachingStaffTableData(
    data: any
  ): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateCode = data.addressStateCode.stateCode;
    let postId = data.designation.id;
    let selectionMode = data.selectionMode;
    return this.http.get(
      environment.baseUrl +
        `districtCategoryAndGenderWiseTeacherAll?postId=${postId}&selectionModeId=${selectionMode}&stateCode=${stateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getDesignation() {
    return this.http.get(environment.baseUrl + 'designation');
  }

  getdistrictCategoryAndGenderWiseTeacherUniversity(
    data: any
  ): Observable<any> {
    let surveyYear = data.surveyYear;
    let stateCode = data.addressStateCode.stateCode;
    let postId = data.designation.id;
    let selectionMode = data.selectionMode;
    let universityTypeCode = data.universityTypeCode.id;
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `districtCategoryAndGenderWiseTeacherUniversity?postId=${postId}&selectionModeId=${selectionMode}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityType=${universityTypeCode}`,
      httpOptions
    );
  }

  getDistrictCategoryAndGenderWiseTeacherUniversityPdf(
    data: any
  ): Observable<any> {
    let surveyYear = data.surveyYear;
    let stateCode = data.addressStateCode.stateCode;
    let postId = data.designation.id;
    let selectionMode = data.selectionMode;
    let universityTypeCode = data.universityTypeCode.id;
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `districtCategoryAndGenderWiseTeacherUniversityPdf?postId=${postId}&selectionModeId=${selectionMode}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityType=${universityTypeCode}&reportType=${data.reportType}`,
      httpOptions
    );
  }

  getReport19(data: any): Observable<any> {
    
    
    var surveyYear =data.surveyYear;
    var stateCode=data.addressStateCode.stateCode;
    var reportType =data.reportType;
    var bodyType =data.Body.id;
    var mgmtId =  data.managementName.id;
    var collegeCode = data.collegeType.id;
    var universityTypeCode =data.universityTypeCode.id;

    var universityName =data.universityName.id;
    var instType = data.institution;
    var institutionCat  = data.institution
    
    var instParentId = data.institution;
    var instId= data.institution=='College Institution'?data.collegeType.id:'ALL';
    let userId =this.localService.getData('userId');
    var url;
    
    
     if(data.institution == 'University'){
   url = `report19?&instType=ALL&reportType=${reportType}&collegeType=ALL&stateCode=${stateCode}&standAloneBodyType=ALL&surveyYear=${surveyYear}&universityName=${universityName}&universityType=${universityTypeCode}&instParentId=ALL&mgmtType=ALL&institutionCat=${institutionCat}&instId=${instId}`
     }
    else if(data.institution=='College Institution'){
      url = `report19?&instType=${instType}&collegeType=${collegeCode}&reportType=${reportType}&standAloneBodyType=ALL&stateCode=${stateCode}&surveyYear=${surveyYear}&universityName=ALL&universityType=ALL&instParentId=${universityName}&instId=${instId}&institutionCat=${institutionCat}&mgmtType=${mgmtId}`     
    }
    else if(data.institution=='Standalone Institution'){
     url = `report19?&instType=${instType}&mgmtType=${mgmtId}&universityName=ALL&reportType=${reportType}&standAloneBodyType=${bodyType}&stateCode=${stateCode}&surveyYear=${surveyYear}&collegeType=ALL&universityType=ALL&instParentId=ALL&instId=ALL&institutionCat=${institutionCat}&collegeType=ALL`
    }else{
     url = `report19?collegeType=${collegeCode}&instType=${instType}&mgmtType=${mgmtId}&reportType=${reportType}&standAloneBodyType=${bodyType}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityName=${universityName}&universityType=${universityTypeCode}&instParentId=${instParentId}&instId=${instId}&institutionCat=ALL`
    }
    return this.http.get(
      environment.baseUrl + url
        ,
      httpOptions
    );
  }


  getReport165(data: any): Observable<any> {
 
    var userId =this.localService.getData('userId');
    var surveyYear = data.surveyYear
    var stateBodyId = data.Body.id;
    var institutionManagementId = data.managementName.id;
    var institutionTypeId =data.institution=='College Institution'? data.collegeType.id: data.institution=='University'? data.universityTypeCode.id:'ALL';
   var stateName = data.addressStateCode.stateName
   var institutionId =data.institution=='College Institution'?data.collegeType.id : data.institution=='Standalone Institution'?data.Body.id: data.institution=='University'?data.universityName.id:'ALL'
   var institutionCategory =data.institution
   var stateCode =data.addressStateCode.stateCode
   var exportType  = data.exportType
   var institutionParentId = data.universityName.id
   var url
    if(data.institution == 'College Institution'){
      url = `api/report-165?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionTypeId=${institutionTypeId}&institutionCategory=${institutionCategory}&stateBodyId=ALL&stateName=${stateName}&institutionManagementId=${institutionManagementId}&institutionParentId=${institutionParentId}`
    } 
    else if(data.institution =='University'){
      url =`api/report-165?stateCode=${stateCode}&institutionTypeId=${institutionTypeId}&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=ALL&stateName=${stateName}`
    } 
    else if(data.institution =='Standalone Institution'){
      url =`api/report-165?stateCode=${stateCode}&institutionTypeId=ALL&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=${stateBodyId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}`
    } else {
      url = `api/report-165?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=${stateBodyId}&institutionId=${institutionId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}&institutionParentId=${institutionParentId}`
    }
    return this.http.get(
      environment.baseUrl + url,
      httpOptions
    );
      
        //`api/report-165?stateName=${stateName}&institutionId=${institutionId}&stateBodyId=${bodyType}&institutionManagementId=${institutionManagementId}&institutionTypeId=${collegeCode}&institutionParentId=${institutionParentId}&stateCode=${stateCode}&surveyYear=${surveyYear}&institutionCategory=${institutionCategory}&exportType=${exportType}`,
 
  }

  getReport166(data: any): Observable<any> {
    var userId =this.localService.getData('userId');
    var surveyYear = data.surveyYear
    var stateBodyId = data.Body.id;
    var institutionManagementId = data.managementName.id;
    var institutionTypeId =data.institution=='College Institution'? data.collegeType.id: data.institution=='University'? data.universityTypeCode.id:'ALL';
   var stateName = data.addressStateCode.stateName
   var institutionId =data.institution=='College Institution'?data.collegeType.id : data.institution=='Standalone Institution'?data.Body.id: data.institution=='University'?data.universityName.id:'ALL'
   var institutionCategory =data.institution
   var stateCode =data.addressStateCode.stateCode
   var exportType  = data.exportType
   var institutionParentId = data.universityName.id
   var url
    if(data.institution == 'College Institution'){
      url = `api/report-166?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionTypeId=${institutionTypeId}&institutionCategory=${institutionCategory}&stateBodyId=ALL&stateName=${stateName}&institutionManagementId=${institutionManagementId}&institutionParentId=${institutionParentId}`
    } 
    else if(data.institution =='University'){
      url =`api/report-166?stateCode=${stateCode}&institutionTypeId=${institutionTypeId}&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=ALL&stateName=${stateName}`
    } 
    else if(data.institution =='Standalone Institution'){
      url =`api/report-166?stateCode=${stateCode}&institutionTypeId=ALL&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=${stateBodyId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}`
    } else {
      url = `api/report-166?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=${stateBodyId}&institutionId=${institutionId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}&institutionParentId=${institutionParentId}`
    }
    return this.http.get(
      environment.baseUrl + url,
      httpOptions
    );
  }

  getStateCategoryAndGenderWiseTeachingStaffPdf(data: any): Observable<any> {
    let surveyYear = data.surveyYear;
    let userId =this.localService.getData('userId');
    let defaultStateCode=data.defaultState?data.defaultState:''
    return this.http.get(
      environment.baseUrl +
        `stateCategoryAndGenderWiseTeachingStaffPdf?surveyYear=${surveyYear}&reportType=${data.reportType}&stateCode=${defaultStateCode}`,
      httpOptions
    );
    // return this.http.get(environment.baseUrl + `districtCategoryAndGenderWiseTeacherUniversity?postId=${postId}&selectionModeId=${selectionMode}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityType=${universityTypeCode}`, httpOptions);
  }

  // District & Category & Gender-Wise Teacher - College

  getCollegeTypeData(): Observable<any> {
    return this.http.get(environment.baseUrl + 'getCollegeType');
  }

  getManagmentTypeData(): Observable<any> {
    return this.http.get(environment.baseUrl + 'getManagmentType');
  }

  getdistrictCategoryAndGenderWiseTeacherStandaloneInstitute(
    data: any
  ): Observable<any> {
    let surveyYear = data.surveyYear;
    let stateCode = data.addressStateCode.stateCode;
    let postId = data.designation.id;
    let selectionMode = data.selectionMode;
    let reportType = data.reportType;
    let bodyType = data.Body.id;
    let mgmtId = data.managementName.id;
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `report116C?bodyType=${bodyType}&mgmtId=${mgmtId}&postId=${postId}&reportType=${reportType}&selectionModeId=${selectionMode}&stateCode=${stateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getBodyType(): Observable<any> {
    return this.http.get(environment.baseUrl + 'getBodyType');
  }

  getfindUniversityData(data: any, obj: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let stateId = obj.stateCode;
    let surveyYear = data;
    if (stateId && surveyYear) {
      return this.http.get(
        environment.baseUrl +
          `findUniversityByYearAndState?stateCode=${stateId}&surveyYear=${surveyYear}`,
        httpOptions
      );
    } else {
      return this.http.get(
        environment.baseUrl +
          `findUniversityByYearAndState?stateCode=${stateId}&surveyYear=${surveyYear}`,
        httpOptions
      );
    }
  }

  getDistrictCategoryAndGenderWiseTeacherCollegeData(
    data: any
  ): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateCode = data.addressStateCode.stateCode;
    let postId = data.designation.id;
    let selectionMode = data.selectionMode;
    let managementName = data.managementName.id;
    let collegeCode = data.collegeType.id;
    let universityTypeCode = data.universityTypeCode.id;
    let universityName = data.universityName.id;

    return this.http.get(
      environment.baseUrl +
        `districtCategoryAndGenderWiseTeacherCollege?collegeInstitutionTypeId=${collegeCode}&managementId=${managementName}&postId=${postId}&selectionModeId=${selectionMode}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityId=${universityName}&universityType=${universityTypeCode}`,
      httpOptions
    );
  }

  getDistrictCategoryAndGenderWiseTeacherCollegeDataPdf(
    data: any
  ): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateCode = data.addressStateCode.stateCode;
    let postId = data.designation.id;
    let selectionMode = data.selectionMode;
    let managementName = data.managementName.id;
    let collegeCode = data.collegeType.id;
    let universityTypeCode = data.universityTypeCode.id;
    let universityName = data.universityName.id;

    return this.http.get(
      environment.baseUrl +
        `districtCategoryAndGenderWiseTeacherCollegePdf?collegeTypeId=${collegeCode}&mgmtTypeId=${managementName}&postId=${postId}&selectionModeId=${selectionMode}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityId=${universityName}&universityType=${universityTypeCode}&reportType=${data.reportType}`,
      httpOptions

      // districtCategoryAndGenderWiseTeacherCollegePdf?surveyYear=2022&reportType=PDF&stateCode=28&postId=ALL&selectionModeId=ALL&universityType=ALL&mgmtTypeId=ALL&universityId=ALL&collegeTypeId=ALL
    );
  }

  getdistrictCategoryAndGenderWiseTeacherCollegePdf(
    data: any
  ): Observable<any> {
    let surveyYear = data.surveyYear;
    let stateCode = data.addressStateCode.stateCode;
    let postId = data.designation.id;
    let selectionMode = data.selectionMode;
    let managementName = data.managementName.id;
    let collegeCode = data.collegeType.id;
    let universityTypeCode = data.universityTypeCode.id;
    let universityName = data.universityName.id;
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `districtCategoryAndGenderWiseTeacherCollegePdf?collegeTypeId=${collegeCode}&mgmtTypeId=${managementName}&postId=${postId}&selectionModeId=${selectionMode}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityId=${universityName}&universityType=${universityTypeCode}`,
      httpOptions
    );
  }

  getNonTeacherStaffType(): Observable<any> {
    return this.http.get(environment.baseUrl + 'nonTeachingStaffType');
  }
  getreport20Data(data: any): Observable<any> {
    var surveyYear =data.surveyYear;
    var stateCode=data.addressStateCode.stateCode;
    var reportType =data.reportType;
    var bodyType =data.Body.id;
    var mgmtId =  data.managementName.id;
    var collegeCode = data.collegeType.id;
    var universityTypeCode =data.universityTypeCode.id;

    var universityName =data.universityName.id;
    var instType = data.institution;
   // var institutionCat  = data.institution
    
    //var instParentId = data.institution;
    //var instId= data.institution=='College Institution'?data.collegeType.id:'ALL';
    let userId =this.localService.getData('userId');
    var url;
    
    
     if(data.institution == 'University'){
   url = `report20?&instType=${instType}&reportType=${reportType}&collegeType=ALL&stateCode=${stateCode}&standAloneBodyType=ALL&surveyYear=${surveyYear}&universityName=${universityName}&universityType=${universityTypeCode}&mgmtType=ALL`
     }
    else if(data.institution=='College Institution'){
      url = `report20?&instType=${instType}&collegeType=${collegeCode}&reportType=${reportType}&standAloneBodyType=ALL&stateCode=${stateCode}&surveyYear=${surveyYear}&universityName=${universityName}&universityType=${universityTypeCode}&mgmtType=${mgmtId}`     
    }
    else if(data.institution=='Standalone Institution'){
     url = `report20?&instType=${instType}&mgmtType=${mgmtId}&universityName=ALL&reportType=${reportType}&standAloneBodyType=${bodyType}&stateCode=${stateCode}&surveyYear=${surveyYear}collegeType=ALL&universityType=ALL&collegeType=ALL`
    }else{
     url = `report20?collegeType=${collegeCode}&instType=${instType}&mgmtType=${mgmtId}&reportType=${reportType}&standAloneBodyType=${bodyType}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityName=${universityName}&universityType=${universityTypeCode}`
    }
    return this.http.get(
      environment.baseUrl + url
        ,
      httpOptions
    );
  }

  getReport23(data: any): Observable<any> {
    let userData = {
      staffType:data.staffType.id,
      reportType: data.reportType,
      surveyYear: data.surveyYear,
      stateCode:'ALL'
    };

    return this.http.post(environment.baseUrl + 'report23', userData);
  }
  getReport24(data: any): Observable<any> {
    let userData = {
      reportType: data.reportType,
      staffType: data.staffType.id,
      surveyYear: data.surveyYear,
       stateCode:'ALL'
    };
    return this.http.post(environment.baseUrl + 'report24', userData);
  }

  getReport117(data: any): Observable<any> {
    let surveyYear = data.surveyYear;
    let staffTypeId = data.staffType.id;
    let stateCode = data.addressStateCode.stateCode;
    let groupId = data.staffGroup.id;
    let reportType = data.reportType;
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `report117?groupId=${groupId}&surveyYear=${surveyYear}&staffTypeId=${staffTypeId}&stateCode=${stateCode}&reportType=${reportType}`,
      httpOptions
    );
  }

  getReport100(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let religiousCategoryName = data.religiousCategory;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    let courseModeName = data.courseModeName;
    let studyModeName = data.studyModeName;
    let socialCategoryName = data.socialCategory;
    let disciplineGroupName = 'ALL';
    let disciplineCategoryName = 'ALL';
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-100?stateCode=${stateCode}&stateName=${stateName}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&courseMode=${courseMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport124(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let selectionMode = data.selectionMode;
    let selectionModeId = data.selectionMode;
    let selectionModeName =
      selectionMode == '1' ? 'DIRECT' : selectionMode == '2' ? 'CAS' : 'ALL';
    let postId = data.designation.id;
    let postName = data.designation.designation;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-124?stateName=${stateName}&stateCode=${stateCode}&selectionMode=${selectionMode}&selectionModeId=${selectionModeId}&surveyYear=${surveyYear}&exportType=${exportType}&selectionModeName=${selectionModeName}&postId=${postId}&postName=${postName}`,
      httpOptions
    );
  }

  getReport124A(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let universityTypeName = data.universityType.type;
    let universityType = data.universityType.id;
    let selectionModeId = data.selectionMode;
    let selectionModeName =
      selectionModeId == '1'
        ? 'DIRECT'
        : selectionModeId == '2'
        ? 'CAS'
        : 'ALL';
    let postId = data.designation.id;
    let postName = data.designation.designation;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-124A?stateName=${stateName}&stateCode=${stateCode}&universityTypeName=${universityTypeName}&universityType=${universityType}&selectionModeId=${selectionModeId}&surveyYear=${surveyYear}&exportType=${exportType}&selectionModeName=${selectionModeName}&postId=${postId}&postName=${postName}`,
      httpOptions
    );
  }

  getReport124B(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let selectionModeId = data.selectionMode;
    let selectionModeName =
      selectionModeId == '1'
        ? 'DIRECT'
        : selectionModeId == '2'
        ? 'CAS'
        : 'ALL';
    let universityName = data.universityName.name;
    let universityId = data.universityName.id;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let collegeInstitutionTypeId = data.collegeType.id;
    let collegeTypeName = data.collegeType.type;
    let postId = data.designation.id;
    let postName = data.designation.designation;
    let universityTypeName = data.universityType.type;
    let universityType = data.universityType.id;
    let staffTypeId = data.staffType.id;
    let staffType = data.staffType.staffType;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-124B?universityName=${universityName}&universityId=${universityId}&universityTypeName=${universityTypeName}&universityType=${universityType}&selectionModeId=${selectionModeId}&surveyYear=${surveyYear}&exportType=${exportType}&selectionModeName=${selectionModeName}&postId=${postId}&postName=${postName}&managementId=${managementId}&managementTypeName=${managementTypeName}&collegeInstitutionTypeId=${collegeInstitutionTypeId}&collegeTypeName=${collegeTypeName}&staffTypeId=${staffTypeId}&staffType=${staffType}`,
      httpOptions
    );
  }

  getReport124C(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let selectionModeId = data.selectionMode;
    let selectionModeName =
      selectionModeId == '1'
        ? 'DIRECT'
        : selectionModeId == '2'
        ? 'CAS'
        : 'ALL';
    let postId = data.designation.id;
    let postName = data.designation.designation;
    let stateBodyId = data.bodyType.id;
    let bodyTypeName = data.bodyType.type;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-124C?stateBodyId=${stateBodyId}&bodyTypeName=${bodyTypeName}&selectionModeId=${selectionModeId}&surveyYear=${surveyYear}&exportType=${exportType}&selectionModeName=${selectionModeName}&postId=${postId}&postName=${postName}&managementId=${managementId}&managementTypeName=${managementTypeName}`,
      httpOptions
    );
  }
  getReport125(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let selectionModeId = data.selectionMode;
    let selectionModeName =
      selectionModeId == '1'
        ? 'DIRECT'
        : selectionModeId == '2'
        ? 'CAS'
        : 'ALL';
    let postId = data.designation.id;
    let postName = data.designation.designation;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-125?stateName=${stateName}&stateCode=${stateCode}&selectionModeId=${selectionModeId}&surveyYear=${surveyYear}&exportType=${exportType}&selectionModeName=${selectionModeName}&postId=${postId}&postName=${postName}`,
      httpOptions
    );
  }

  getReport125A(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let selectionModeId = data.selectionMode;
    let selectionModeName =
      selectionModeId == '1'
        ? 'DIRECT'
        : selectionModeId == '2'
        ? 'CAS'
        : 'ALL';
    let postId = data.designation.id;
    let postName = data.designation.designation;
    let universityTypeName = data.universityType.type;
    let universityType = data.universityType.id;
    let universityId = data.universityName.id;
    let universityName = data.universityName.name;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-125A?universityName=${universityName}&universityId=${universityId}&universityTypeName=${universityTypeName}&universityType=${universityType}&selectionModeId=${selectionModeId}&surveyYear=${surveyYear}&exportType=${exportType}&selectionModeName=${selectionModeName}&postId=${postId}&postName=${postName}&stateName=${stateName}&stateCode=${stateCode}`,
      httpOptions
    );
  }


  getReport125B(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let collegeName = data.collegeName.name;
    let stateCode = data.addressStateCode.stateCode;
    let stateName = data.addressStateCode.stateName;
    let selectionModeId = data.selectionMode;
    let selectionModeName = selectionModeId == '1' ? 'DIRECT' : selectionModeId == '2' ? 'CAS' : 'ALL';
    let postId = data.designation.id;
    let postName = data.designation.designation;
    let universityName = data.universityName.name;
    let universityId = data.universityName.id;
    let universityTypeName = data.universityType.type;
    let universityType = data.universityType.id;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let collegeInstitutionTypeId = data.collegeType.id;
    let collegeTypeName = data.collegeType.type;
    let collegeInstitutionId = data.collegeType.id;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-125B?universityName=${universityName}&universityId=${universityId}&universityTypeName=${universityTypeName}&universityType=${universityType}&selectionModeId=${selectionModeId}&surveyYear=${surveyYear}&exportType=${exportType}&selectionModeName=${selectionModeName}&postId=${postId}&postName=${postName}&stateName=${stateName}&stateCode=${stateCode}&managementId=${managementId}&managementTypeName=${managementTypeName}&collegeInstitutionTypeId=${collegeInstitutionTypeId}&collegeTypeName=${collegeTypeName}&collegeInstitutionId=${collegeInstitutionId}&collegeName=${collegeName}`,
      httpOptions
    );
  }
  getReport125C(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let postId = data.designation.id;
    let stateCode = data.addressStateCode.stateCode;
    let stateName = data.addressStateCode.stateName;
    let selectionModeId = data.selectionMode;
    let postName = data.designation.designation;
    let selectionModeName = selectionModeId == '1' ? 'DIRECT' : selectionModeId == '2' ? 'CAS' : 'ALL';
    let bodyTypeName = data.bodyType.type;
    let stateBodyId = data.bodyType.id;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let standaloneInstitutionId = 'ALL';
    let institutionName = 'STANDALONE';
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-125C?bodyTypeName=${bodyTypeName}&stateBodyId=${stateBodyId}&standaloneInstitutionId=${standaloneInstitutionId}&institutionName=${institutionName}&selectionModeId=${selectionModeId}&surveyYear=${surveyYear}&exportType=${exportType}&selectionModeName=${selectionModeName}&postId=${postId}&postName=${postName}&stateName=${stateName}&stateCode=${stateCode}&managementId=${managementId}&managementTypeName=${managementTypeName}`,
      httpOptions
    );
  }
  getReport171(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let universityName = data.universityName.id;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let universityId = data.universityName.id;
    let institutionId = data.institution;
    let institutionManagementId = data.managementType.id;
    let institutionTypeId = data.institution;
    //let institutionParentId = 'ALL';
    let institutionCategory = data.institution;
    // let constitutedFromColleges = data.affiliated;
    let constitutedFromColleges = data.constitutedFromCollege?data.constitutedFromCollege:'ALL';
    let stateCode = data.addressStateCode.stateCode;
    let institutionType = data.institution;
    let institutionTypeName = data.institution;
    let stateBodyId = data.bodyType.id;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-171?universityName=${universityName}&stateBodyId=${stateBodyId}&universityType=${universityType}&universityTypeName=${universityTypeName}&universityId=${universityId}&surveyYear=${surveyYear}&exportType=${exportType}&institutionId=${institutionId}&institutionManagementId=${institutionManagementId}&institutionTypeId=${institutionTypeId}&stateName=${stateName}&stateCode=${stateCode}&institutionParentId=${universityId}&institutionCategory=${institutionCategory}&constitutedFromColleges=${constitutedFromColleges}&institutionType=${institutionType}&institutionTypeName=${institutionTypeName}`,
      httpOptions
    );
  }

  getReport122(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
      return this.http.get(
      environment.baseUrl +
        `api/report-122?groupId=${data.staffGroup.id}&groupName=${data.staffGroup.staffGroup}&staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }

  getReport122A(data: any): Observable<any> {
    console.log(data);
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-122A?surveyYear=${data.surveyYear}&staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&exportType=${data.exportType}&groupId=${data.staffGroup.id}&groupName=${data.staffGroup.staffGroup}&universityType=${data.universityType.id}&universityTypeName=${data.universityType.type}`,
      httpOptions
    );
  }

  getReport122B(data: any): Observable<any> {
   
    let userId =this.localService.getData('userId');
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let groupId = data.staffGroup.id;
    let groupName = data.staffGroup.staffGroup;
    let staffTypeId = data.staffType.id;
    let staffType = data.staffType.staffType;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let universityId = data.universityName.id;
    let universityName = data.universityName.name;
    let collegeInstitutionTypeId = data.collegeType.id;
    let collegeTypeName = data.collegeType.type;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-122B?surveyYear=${surveyYear}&staffTypeId=${staffTypeId}&staffType=${staffType}&exportType=${exportType}&groupId=${groupId}&groupName=${groupName}&universityType=${universityType}&universityTypeName=${universityTypeName}&managementId=${managementId}&managementTypeName=${managementTypeName}&universityId=${universityId}&universityName=${universityName}&collegeInstitutionTypeId=${collegeInstitutionTypeId}&collegeTypeName=${collegeTypeName}`,
      httpOptions
    );
  }

  getReport122C(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let groupId = data.staffGroup.id;
    let groupName = data.staffGroup.staffGroup;
    let staffTypeId = data.staffType.id;
    let staffType = data.staffType.staffType;
    let stateBodyId = data.bodyType.id;
    let bodyTypeName = data.bodyType.type;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-122C?surveyYear=${surveyYear}&staffTypeId=${staffTypeId}&staffType=${staffType}&exportType=${exportType}&groupId=${groupId}&groupName=${groupName}&stateBodyId=${stateBodyId}&bodyTypeName=${bodyTypeName}&managementId=${managementId}&managementTypeName=${managementTypeName}`,
      httpOptions
    );
  }

  getReport123(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let groupId = data.staffGroup.id;
    let groupName = data.staffGroup.staffGroup;
    let staffTypeId = data.staffType.id;
    let staffType = data.staffType.staffType;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-123?surveyYear=${surveyYear}&staffTypeId=${staffTypeId}&staffType=${staffType}&exportType=${exportType}&groupId=${groupId}&groupName=${groupName}&stateName=${stateName}&stateCode=${stateCode}`,
      httpOptions
    );
  }

  getReport123A(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let groupId = data.staffGroup.id;
    let groupName = data.staffGroup.staffGroup;
    let staffTypeId = data.staffType.id;
    let staffType = data.staffType.staffType;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let universityId = data.universityName.id;
    let universityName = data.universityName.name;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-123A?surveyYear=${surveyYear}&staffTypeId=${staffTypeId}&staffType=${staffType}&exportType=${exportType}&groupId=${groupId}&groupName=${groupName}&universityType=${universityType}&universityTypeName=${universityTypeName}&stateCode=${stateCode}&stateName=${stateName}&universityId=${universityId}&universityName=${universityName}`,
      httpOptions
    );
  }
  getReport123B(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-123B?surveyYear=${data.surveyYear}&staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&exportType=${data.exportType}&groupId=${ data.staffGroup.id}&groupName=${data.staffGroup.staffGroup}&universityType=${data.universityType.id}&universityTypeName=${ data.universityType.type}&stateCode=${data.addressStateCode.stateCode}&stateName=${data.addressStateCode.stateName}&universityId=${data.universityName.id}&universityName=${data.universityName.name}&collegeTypeName=${data.collegeType.type}&collegeName=${data.collegeName.name}&collegeInstitutionId=${data.collegeName.id}&managementId=${data.managementType.id}&managementTypeName=${data.managementType.managementType}`,
      httpOptions
    );
  }
  getReport123C(data: any): Observable<any> {
    
    let userId =this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-123C?surveyYear=${data.surveyYear}&staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&exportType=${data.exportType}&groupId=${data.staffGroup.id}&groupName=${data.staffGroup.staffGroup}&stateBodyId=${data.bodyType.id}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionName=${data.institutionName.name}&bodyTypeName=${data.bodyType.type}&managementId=${data.managementType.id}&standaloneInstitutionId=${data.institutionName.id}&managementTypeName=${data.managementType.managementType}`,
      httpOptions
    );
  }

  getReport172(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let universityTypeName = data.universityType.type;
    let universityId = data.universityName.id;
    let universityType = data.universityType.id;
  
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    let institutionType = data.institution;
    let universityName = data.universityName.name;
    let staffTypeId = data.staffType.id;
    let staffTypeName = data.staffType.staffType;
    if(data.institution=='University'){
      return this.http.get(
        environment.baseUrl +
          `api/report-172?surveyYear=${surveyYear}&constitutedFromColleges=${data.constitutedFromCollege?data.constitutedFromCollege:"ALL"}&staffTypeId=${staffTypeId}&staffTypeName=${staffTypeName}&exportType=${exportType}&stateName=${stateName}&stateCode=${stateCode}&institutionType=${institutionType}&universityType=${universityType}&universityTypeName=${universityTypeName}`,
        httpOptions
      );
    }
    if(data.institution=='College'){
      return this.http.get(
        environment.baseUrl +
          `api/report-172?surveyYear=${surveyYear}&staffTypeId=${staffTypeId}&staffTypeName=${staffTypeName}&exportType=${exportType}&institutionTypeName=${data.collegeType.type}&universityName=${universityName}&stateName=${stateName}&stateCode=${stateCode}&institutionType=${institutionType}&institutionParentId=${data.universityName.id}&institutionManagementId=${data.managementType.id}&institutionTypeId=${data.collegeType.id}&universityType=${universityType}&universityId=${universityId}&universityTypeName=${universityTypeName}`,
        httpOptions
      );
    }
    if(data.institution=='Standalone'){
      return this.http.get(
        environment.baseUrl +
          `api/report-172?surveyYear=${surveyYear}&staffTypeId=${staffTypeId}&staffTypeName=${staffTypeName}&exportType=${exportType}&stateBodyId=${data.bodyType.id}&stateName=${stateName}&stateCode=${stateCode}&institutionType=${institutionType}&stateBodyName=${data.bodyType.type}&institutionManagementId=${data.managementType.id}`,
        httpOptions
      );

    }
    return this.http.get(
      environment.baseUrl +
        `api/report-172?surveyYear=${surveyYear}&staffTypeId=${staffTypeId}&staffTypeName=${staffTypeName}&exportType=${exportType}&stateName=${stateName}&stateCode=${stateCode}&institutionType=${institutionType}`,
      httpOptions
    );
  }

  getReport117A(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let staffTypeId = data.staffType.id;
    let stateCode = data.addressStateCode.stateCode;
    let groupId = data.staffGroup.id;
    let reportType = data.reportType;
    let universityTypeCode = data.universityTypeCode.id;
    return this.http.get(
      environment.baseUrl +
        `report117A?groupId=${groupId}&reportType=${reportType}&staffTypeId=${staffTypeId}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityType=${universityTypeCode}`,
      httpOptions
    );
  }

  getNonTeachingStaffGroup(): Observable<any> {
    return this.http.get(environment.baseUrl + 'nonTeachingStaffGroup');
  }

  getReport117B(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let reportType = data.reportType;
    let stateCode = data.addressStateCode.stateCode;
    let groupId = data.staffGroup.id == undefined ? null : data.staffGroup.id;
    let managementId =
      data.managementName.id == undefined ? null : data.managementName.id;
    let collegeInstTypeId =
      data.collegeType.id == undefined ? null : data.collegeType.id;
    let universityTypeCode =
      data.universityTypeCode.id == undefined
        ? null
        : data.universityTypeCode.id;
    let universityId =
      data.universityName.id == undefined ? null : data.universityName.id;
    let staffTypeId = data.staffType.id == undefined ? null : data.staffType.id;

    return this.http.get(
      environment.baseUrl +
        `report117B?collegeInstTypeId=${collegeInstTypeId}&groupId=${groupId}&managementId=${managementId}&staffTypeId=${staffTypeId}&reportType=${reportType}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityId=${universityId}&universityType=${universityTypeCode}`,
      httpOptions
    );
  }
  getReport117C(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let reportType = data.reportType;
    let Body = data.Body.id;
    let stateCode = data.addressStateCode.stateCode;
    let groupId = data.staffGroup.id == undefined ? null : data.staffGroup.id;
    let managementId =
      data.managementName.id == undefined ? null : data.managementName.id;
    let collegeInstTypeId =
      data.collegeType.id == undefined ? null : data.collegeType.id;
    let universityTypeCode =
      data.universityTypeCode.id == undefined
        ? null
        : data.universityTypeCode.id;
    let universityId =
      data.universityName.id == undefined ? null : data.universityName.id;
    let staffTypeId = data.staffType.id == undefined ? null : data.staffType.id;
    return this.http.get(
      environment.baseUrl +
        `report117C?collegeInstTypeId=${collegeInstTypeId}&groupId=${groupId}&managementId=${managementId}&reportType=${reportType}&staffTypeId=${staffTypeId}&stateBodyId=${Body}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityId=${universityId}&universityType=${universityTypeCode}`,
      httpOptions
    );
  }
  getReport27(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let staffTypeId = data.staffType.id;
    let stateCode = data.addressStateCode.stateCode;
    let reportType = data.reportType;

    return this.http.get(
      environment.baseUrl +
        `report27?reportType=${reportType}&staffTypeId=${staffTypeId}&stateCode=${stateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReport25(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let staffTypeId = data.staffType.id;
    let exportType = data.exportType;
    let staffType = data.staffType.staffType;
    let universityId= 'ALL'
    return this.http.get(
      environment.baseUrl +
        `api/report-25?exportType=${exportType}&staffTypeId=${staffTypeId}&staffType=${staffType}&surveyYear=${surveyYear}&universityId=${universityId}`,
      httpOptions
    );
  }

  getReport26(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let staffTypeId = data.staffType.id;
    let exportType = data.exportType;
    let staffType = data.staffType.staffType;
    let universityId='ALL'
    return this.http.get(
      environment.baseUrl +
        `api/report-26?exportType=${exportType}&staffType=${staffType}&staffTypeId=${staffTypeId}&surveyYear=${surveyYear}&universityId=${universityId}`,
      httpOptions
    );
  }

  getReport28(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let staffTypeId = data.staffType.id;
    let stateCode = data.addressStateCode.stateCode;
    let reportType = data.reportType;

    return this.http.get(
      environment.baseUrl +
        `report28?reportType=${reportType}&staffTypeId=${staffTypeId}&stateCode=${stateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReport29(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let staffTypeId = data.staffType.id;
    let stateCode = data.addressStateCode.stateCode;
    let exportType = data.exportType;
    let stateName = data.addressStateCode.stateName;
    let staffType = data.staffType.staffType;
    return this.http.get(
      environment.baseUrl +
        `api/report-29?exportType=${exportType}&stateName=${stateName}&staffType=${staffType}&staffTypeId=${staffTypeId}&stateCode=${stateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getReport30(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let staffTypeId = data.staffType.id;
    let stateCode = data.addressStateCode.stateCode;
    let exportType = data.exportType;
    let staffType = data.staffType.staffType;
    let stateName = data.addressStateCode.stateName;
    return this.http.get(
      environment.baseUrl +
        `api/report-30?stateName=${stateName}&stateCode=${stateCode}&staffTypeId=${staffTypeId}&staffType=${staffType}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport135(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let affiliated = data.affiliated=='Yes'?'1':data.affiliated=='No'?'2':"ALL";
    if (data.institution == 'University') {   
   return this.http.get(
    environment.baseUrl +
      `api/report-135?stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&constitutedFromColleges=${affiliated}&universityTypeName=${data.universityTypeCode.type}&universityType=${data.universityTypeCode.id}&institutionType=${data.institution}&surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
    httpOptions
  );
    }
    if (data.institution == 'College') {
     
      return this.http.get(
        environment.baseUrl +
          `api/report-135?stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionManagementId=${data.managementName.id}&institutionTypeId=${data.collegeType.id}&institutionTypeName=${data.collegeType.type}&staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&universityTypeName=${data.universityTypeCode.type}&universityType=${data.universityTypeCode.id}&institutionType=${data.institution}&surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
        httpOptions
      );
    }
    if (data.institution == 'Standalone') {
      return this.http.get(
        environment.baseUrl +
          `api/report-135?stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionManagementId=${data.managementName.id}&stateBodyId=${data.Body.id}&stateBodyName=${data.Body.type}&staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&institutionType=${data.institution}&surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
        httpOptions
      );
    }
    
    return this.http.get(
      environment.baseUrl +
        `api/report-135?stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&staffTypeId=${data.staffType.id}&staffType=${data.staffType.staffType}&institutionType=${data.institution}&surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
    // return this.http.get(
    //   environment.baseUrl +
    //     `api/report-135?stateName=${stateName}&stateCode=${stateCode}&staffTypeId=${staffTypeId}&staffType=${staffType}&surveyYear=${surveyYear}&exportType=${exportType}&institutionId=${institutionId}&stateBodyId=${bodyType}&institutionManagementId=${institutionManagementId}&institutionTypeId=${collegeCode}&institutionParentId=${institutionParentId}&institutionCategory=${institutionCategory}&constitutedFromColleges=${constitutedFromColleges}&universityTypeName=${universityTypeName}&universityId=${universityName}&universityType=${universityTypeCode}`,
    //   httpOptions
    // );
  }

  getReport4(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    var surveyYear = '';
    var stateCode = '';
    var exportType = '';
    var bodyType = '';
    var managementType = '';
    var collegeType = '';
    var universityTypeCode = '';
    var universityName = '';
    var institution = '';
    var universityTypeId = '';
    var collegeId = '';
    var collegeType = '';

    if (data.institution == 'University') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      exportType = data.exportType;
      bodyType = data.bodyType.id?data.bodyType.id: 'ALL';
      managementType = data.managementType.id? data.managementType.id: 'ALL';
      collegeType = data.collegeType.id?data.collegeType.id: 'ALL';
      universityTypeCode = data.universityType.id;
      universityName = data.universityName.id?data.universityName.id : 'ALL';
      institution = data.institution;
    }
    if (data.institution == 'College Institution') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      exportType = data.exportType;
      bodyType = data.bodyType.id?data.bodyType.id: 'ALL';
      managementType = data.managementType.id;
      collegeType = data.collegeType.id;
      universityTypeCode = data.universityType.id ? data.universityType.id : 'ALL';
      universityName = data.universityName.id;
      institution = data.institution;
    }
    if (data.institution == 'Standalone Institution') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      exportType = data.exportType;
      bodyType = data.bodyType.id;
      managementType = data.managementType.id;
      collegeType = data.collegeType.id ?data.collegeType.id  : 'ALL';
      universityTypeCode = data.universityType.id?data.universityType.id : 'ALL';
      universityName = data.universityName.id?data.universityName.id : 'ALL';
      institution = data.institution;
    }
    return this.http.get(
      environment.baseUrl +
        `api/report-4?collegeType=${collegeType}&instType=${institution}&collegeInstitutionTypeId=${collegeType}&mgmtType=${managementType}&exportType=${exportType}&standAloneBodyType=${bodyType}&stateCode=${stateCode}&managementId=${managementType}&universityTypeId=${universityTypeId}&surveyYear=${surveyYear}&universityName=${universityName}&collegeInstId=${collegeId}&universityType=${universityTypeCode}`,
      httpOptions
    );
  }

  getReport31(data: any): Observable<any> {
    data.universityType = data.universityType.id?data.universityType.id:'';
    data.universityName = data.universityName.id?data.universityName.id:'';
    return this.http.post(environment.baseUrl + 'report31', data);
  }
  getReport32(data: any): Observable<any> {
    data.universityType = data.universityType.id?data.universityType.id:'';
    data.universityName = data.universityName.id?data.universityName.id:'';
    return this.http.post(environment.baseUrl + 'report32', data);
  }

  getReport170(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-170?surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport32A(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let universityName = data.universityName.id;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-32A?stateName=${stateName}&universityType=${universityName}&universityId=${universityName}&stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport43(data: any) {
    let userId =this.localService.getData('userId');
    if(data.institution==='College Institution'){
      var institutionTypeId =data.collegeType.id?data.collegeType.id:'ALL';
    } else if (data.institution==='Standalone Institution') {
      var institutionTypeId =data.bodyType.id?data.bodyType.id:'ALL';
    } else {
      var institutionTypeId =data.universityType.id?data.universityType.id:'ALL';
    }
   // let institutionTypeId =data.universityType.id?data.universityType.id:data.collegeType.id?data.collegeType.id:data.bodyType.id?data.bodyType.id:'ALL';
    let institutionCategory = data.institution;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-43?institutionTypeId=${institutionTypeId}&institutionCategory=${institutionCategory}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport32B(data: any) {
    let userId =this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    let universityTypeCode = data.universityType.id;
    return this.http.get(
      environment.baseUrl +
        `api/report-32B?stateName=${stateName}&stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&universityId=${universityTypeCode}`,
      httpOptions
    );
  }

  getReport169(data: any) {
    let userId =this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-169?stateName=${stateName}&stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport33(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-33?surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport34(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-34?surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport39(data: any): Observable<any> {
    data.universityType = data.universityType.id;
    data.universityName = data.universityName.id;
    return this.http.post(environment.baseUrl + 'report39', data);
  }

  getReport40(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let universityType = data.universityType.type;
    let typeId = data.universityType.id;
    let exportType = data.exportType;
    let universityId='ALL'
    return this.http.get(
      environment.baseUrl +
        `api/report-40?surveyYear=${surveyYear}&stateName=${stateName}&stateCode=${stateCode}&universityType=${universityType}&typeId=${typeId}&universityId=${universityId}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport41(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-41?surveyYear=${surveyYear}&stateName=${stateName}&stateCode=${stateCode}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport35A(data: any): Observable<any> {
    data.universityType = data.universityType.id;
    data.universityName = data.universityName.id;
    return this.http.post(environment.baseUrl + 'report35A', data);
  }
  getReport35C(data: any): Observable<any> {
    data.universityType = data.universityType.id;
    data.universityName = data.universityName.id;
    return this.http.post(environment.baseUrl + 'report35C', data);
  }

  getReport108(userData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let courseMode = userData.courseMode;
    let disciplinGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let level = userData.level.id;
    let programme = userData.programme.id== undefined ?'ALL':userData.programme.id;
    let reportType = userData.reportType;
    let studyMode = userData.studyMode;
    //console.log(userData);
    return this.http.get(
      environment.baseUrl +
        `report108?courseMode=${courseMode}&disciplineGroup=${disciplinGroup}&disciplineName=${disciplineName}&level=${level}&programme=${programme}&reportType=${reportType}&stateCode=${StateCode}&studyMode=${studyMode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReport108A(userData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let courseMode = userData.courseMode;
    let disciplinGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let level = userData.level.id;
    let programme = userData.programme.id== undefined ?'ALL':userData.programme.id;
    let universityType = userData.universityType.id;
    let reportType = userData.reportType;
    let studyMode = userData.studyMode;
  //  console.log(userData);
    return this.http.get(
      environment.baseUrl +
        `report108A?courseMode=${courseMode}&disciplineGroup=${disciplinGroup}&disciplineName=${disciplineName}&level=${level}&programme=${programme}&reportType=${reportType}&stateCode=${StateCode}&studyMode=${studyMode}&surveyYear=${surveyYear}&universityType=${universityType}`,
      httpOptions
    );
  }

  getReport108B(userData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let courseMode = userData.courseMode;
    let disciplinGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let level = userData.level.id;
    let programme = userData.programme.id== undefined ?'ALL':userData.programme.id;
    let universityType = userData.universityType.id;
    let reportType = userData.reportType;
    let managementType = userData.managementType.id;
    let universityName = userData.universityName.id;
    let collegeType = userData.collegeType.id;
    let studyMode = userData.studyMode;

    return this.http.get(
      environment.baseUrl +
        `report108B?collegeType=${collegeType}&courseMode=${courseMode}&disciplineGroup=${disciplinGroup}&studyMode=${studyMode}&disciplineName=${disciplineName}&level=${level}&mngtType=${managementType}&programme=${programme}&reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityName=${universityName}&universityType=${universityType}`,
      httpOptions
    );
  }

  getReport108C(userData: any): Observable<any> {
    let userId =this.localService.getData('userId');
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let courseMode = userData.courseMode;
    let disciplinGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let level = userData.level.id;
    let programme = userData.programme.id?userData.programme.id:'ALL';
    let reportType = userData.reportType;
    let managementType = userData.managementType.id;
    let studyMode = userData.studyMode;
    let bodyType = userData.bodyType.id;
    return this.http.get(
      environment.baseUrl +
        `report108C?bodyType=${bodyType}&courseMode=${courseMode}&disciplineGroup=${disciplinGroup}&disciplineName=${disciplineName}&level=${level}&mngtType=${managementType}&programme=${programme}&reportType=${reportType}&stateCode=${StateCode}&studyMode=${studyMode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReport38(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    var surveyYear = '';
    var stateCode = '';
    var reportType = '';
    var bodyType = '';
    var managementType = '';
    var collegeType = '';
    var universityTypeCode = '';
    var universityName = '';
    var institution = '';
    if (data.institution == 'University') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = "ALL";
      managementType = "ALL";
      collegeType = 'ALL';
      universityTypeCode = data.universityType.id;
      universityName = data.universityName.id?data.universityName.id : 'ALL';
      institution = data.institution;
    } else if (data.institution == 'College Institution') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = "ALL";
      managementType = data.managementType.id?data.managementType.id:'ALL';
      collegeType = data.collegeType.id?data.collegeType.id:'ALL';
      universityTypeCode ="ALL";
      universityName = data.universityName.id?data.universityName.id:"ALL";
      institution = data.institution;
    }else {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = data.bodyType.id?data.bodyType.id:"ALL";
      managementType = data.managementType.id?data.managementType.id:'ALL';
      collegeType = "ALL";
      universityTypeCode = "ALL";
      universityName = "ALL";
      institution = data.institution;
    }
    return this.http.get(
      environment.baseUrl +
        `report38?collegeType=${collegeType}&institutioncategory=${institution}&instType=${institution}&mgmtType=${managementType}&reportType=${reportType}&standAloneBodyType=${bodyType}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityName=${universityName}&universityType=${universityTypeCode}`,
      httpOptions
    );
  }

  getReport38c(data: any): Observable<any> {
    let userId =this.localService.getData('userId');
    var surveyYear = '';
    var stateCode = '';
    var reportType = '';
    var bodyType = '';
    var managementType = '';
    var collegeType = '';
    var universityTypeCode = '';
    var universityName = '';
    var institution = '';

    if (data.institution == 'University') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = data.bodyType.id?data.bodyType.id: 'ALL';
      managementType = data.managementType.id?data.managementType.id: 'ALL';
      collegeType = data.collegeType.id?data.collegeType.id: 'ALL';
      universityTypeCode = data.universityType.id;
      universityName = data.universityName.id?data.universityName.id: 'ALL';
      institution = data.institution;
    }
    if (data.institution == 'College Institution') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = data.bodyType.id?data.bodyType.id: 'ALL';
      managementType = data.managementType.id;
      collegeType = data.collegeType.id;
      universityTypeCode = data.universityType.id?data.universityType.id: 'ALL';
      universityName = data.universityName.id;
      institution = data.institution;
    }
    if (data.institution == 'Standalone Institution') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = data.bodyType.id;
      managementType = data.managementType.id;
      collegeType = data.collegeType.id?data.collegeType.id: 'ALL';
      universityTypeCode = data.universityType.id?data.universityType.id: 'ALL';
      universityName = data.universityName.id?data.universityName.id: 'ALL';
      institution = data.institution;
    }
    return this.http.get(
      environment.baseUrl +
        `report38?collegeType=${collegeType}&instType=${institution}&mgmtType=${managementType}&reportType=${reportType}&standAloneBodyType=${bodyType}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityName=${universityName}&universityType=${universityTypeCode}`,
      httpOptions
    );
  }
}
