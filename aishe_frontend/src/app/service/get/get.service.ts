import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
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
export class GetService {

  constructor(private http: HttpClient,public localService: LocalserviceService) {}
  getReport106C(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let studyMode = data.studyMode;
    let categoryType = data.socialCategory;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = !data.programme.programme?'ALL':data.programme.programme;
    let universityName = data.universityName.name;
    let universityId = data.universityName.id;
    let stateBodyId = data.bodyType.id;
    let bodyTypeName = data.bodyType.type;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;

    return this.http.get(
      environment.baseUrl +
        `api/report-106C?universityId=${universityId}&universityName=${universityName}&levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&programmeName=${programmeName}&courseMode=${courseMode}&studyMode=${studyMode}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&courseModeName=${courseModeName}&categoryType=${categoryType}&stateBodyId=${stateBodyId}&bodyTypeName=${bodyTypeName}&studyModeName=${studyModeName}&managementId=${managementId}&managementTypeName=${managementTypeName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport106B(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let studyMode = data.studyMode;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let levelId = data.level.id;
    let levelName =  data.level.name;
    let programmeId = data.programme.id;
    let programmeName = !data.programme.programme?'ALL':data.programme.programme;
    let collegeTypeName = data.collegeType.type;
    let collegeInstitutionTypeId = data.collegeType.id;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let universityName = data.universityName.name;
    let universityId = data.universityName.id;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;

    return this.http.get(
      environment.baseUrl +
        `api/report-106B?studyModeName=${studyModeName}&studyMode=${studyMode}&levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&programmeName=${programmeName}&collegeTypeName=${collegeTypeName}&collegeInstitutionTypeId=${collegeInstitutionTypeId}&managementId=${managementId}&managementTypeName=${managementTypeName}&universityType=${universityType}&universityTypeName=${universityTypeName}&universityId=${universityId}&universityName=${universityName}&stateCode=${stateCode}&stateName=${stateName}&courseMode=${courseMode}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport106A(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let studyMode = data.studyMode;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = !data.programme.programme?'ALL':data.programme.programme;

    return this.http.get(
      environment.baseUrl +
        `api/report-106A?universityType=${universityType}&levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&programmeName=${programmeName}&universityTypeName=${universityTypeName}&studyMode=${studyMode}&courseMode=${courseMode}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport106(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let studyMode = data.studyMode;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = !data.programme.programme?'ALL':data.programme.programme;

    return this.http.get(
      environment.baseUrl +
        `api/report-106?studyMode=${studyMode}&courseMode=${courseMode}&levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&programmeName=${programmeName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport105C(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let socialCategoryName = !obj?.socialCategory ? 'ALL' : obj.socialCategory;
    let studyMode = data.studyMode;
    let categoryType = data.socialCategory;
    let religiousCategoryName = !obj.religiousCategory
      ? 'ALL'
      : obj.religiousCategory;
    let stateBodyId = data.bodyType.id;
    let bodyTypeName = data.bodyType.type;
    let standaloneInstitutionId = 'ALL';
    let institutionName = 'ALL';
    return this.http.get(
      environment.baseUrl +
        `api/report-105C?stateName=${stateName}&stateCode=${stateCode}&courseMode=${courseMode}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&courseModeName=${courseModeName}&categoryType=${categoryType}&stateBodyId=${stateBodyId}&bodyTypeName=${bodyTypeName}&studyModeName=${studyModeName}&standaloneInstitutionId=${standaloneInstitutionId}&institutionName=${institutionName}&managementId=${managementId}&managementTypeName=${managementTypeName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport105B(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let studyMode = data.studyMode;
    let socialCategoryName = !obj?.socialCategory ? 'ALL' : obj.socialCategory;
    let categoryType = data.socialCategory;
    let religiousCategoryName = !obj.religiousCategory
      ? 'ALL'
      : obj.religiousCategory;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let universityName = data.universityName.name;
    let universityId = data.universityName.id;
    let collegeTypeName = data.collegeType.type;
    let collegeInstitutionTypeId = data.collegeType.id;
    let collegeName = data.collegeName.name;

    return this.http.get(
      environment.baseUrl +
        `api/report-105B?collegeTypeName=${collegeTypeName}&categoryType=${categoryType}&collegeInstitutionTypeId=${collegeInstitutionTypeId}&collegeName=${collegeName}&managementId=${managementId}&managementTypeName=${managementTypeName}&universityType=${universityType}&universityTypeName=${universityTypeName}&universityId=${universityId}&universityName=${universityName}&stateCode=${stateCode}&stateName=${stateName}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&courseMode=${courseMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport105A(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let socialCategoryName = !obj?.socialCategory ? 'ALL' : obj.socialCategory;
    let studyMode = data.studyMode;
    let categoryType = data.socialCategory;
    let religiousCategoryName = !obj.religiousCategory
      ? 'ALL'
      : obj.religiousCategory;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let universityName = data.universityName.name;
    let universityId = data.universityName.id;

    return this.http.get(
      environment.baseUrl +
        `api/report-105A?categoryType=${categoryType}&universityType=${universityType}&universityTypeName=${universityTypeName}&universityId=${universityId}&universityName=${universityName}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&courseMode=${courseMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport105(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let socialCategoryName = !obj?.socialCategory ? 'ALL' : obj.socialCategory;
    let studyMode = data.studyMode;
    let categoryType = data.socialCategory;
    let religiousCategoryName = !obj.religiousCategory
      ? 'ALL'
      : obj.religiousCategory;

    return this.http.get(
      environment.baseUrl +
        `api/report-105?categoryType=${categoryType}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&courseMode=${courseMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport104C(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let studyMode = data.studyMode;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = !data.programme.programme?'ALL':data.programme.programme;
    let categoryType = data.socialCategory;
    let stateBodyId = data.bodyType.id;
    let bodyTypeName = data.bodyType.type;
    let institutionName = 'ALL';

    return this.http.get(
      environment.baseUrl +
        `api/report-104C?levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&programmeName=${programmeName}&stateCode=${stateCode}&stateName=${stateName}&courseMode=${courseMode}&studyMode=${studyMode}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&courseModeName=${courseModeName}&categoryType=${categoryType}&stateBodyId=${stateBodyId}&bodyTypeName=${bodyTypeName}&studyModeName=${studyModeName}&institutionName=${institutionName}&managementId=${managementId}&managementTypeName=${managementTypeName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport104B(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let collegeInstitutionId = data.collegeType.id;
    let collegeInstitutionTypeId = data.collegeType.id;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let collegeTypeName = data.collegeType.type;
    let studyMode = data.studyMode;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = !data.programme.programme?'ALL':data.programme.programme;
    let universityId=data.universityId?data.universityId:'ALL'
    return this.http.get(
      environment.baseUrl +
        `api/report-104B?levelId=${levelId}&levelName=${levelName}&universityId=${universityId}&programmeId=${programmeId}&programmeName=${programmeName}&collegeTypeName=${collegeTypeName}&collegeInstitutionTypeId=${collegeInstitutionTypeId}&collegeInstitutionId=${collegeInstitutionId}&managementId=${managementId}&managementTypeName=${managementTypeName}&universityType=${universityType}&universityTypeName=${universityTypeName}&stateCode=${stateCode}&stateName=${stateName}&studyMode=${studyMode}&courseMode=${courseMode}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport104A(data: any, obj: any) {
    console.log(data);
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let religiousCategoryName = !obj.religiousCategory
      ? 'ALL'
      : obj.religiousCategory;
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let socialCategoryName = !obj?.socialCategory ? 'ALL' : obj.socialCategory;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let studyMode = data.studyMode;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = data.programme.programme? data.programme.programme:'ALL';
    return this.http.get(
      environment.baseUrl +
        `api/report-104A?universityTypeName=${universityTypeName}&universityType=${universityType}&levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&programmeName=${programmeName}&stateCode=${stateCode}&stateName=${stateName}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&courseMode=${courseMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport104(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    // studyMode == '1' ? 'REGULAR' : studyMode == '2' ? 'DISTANCE' : 'ALL';
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;

    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let categoryType = data.socialCategory;
    let exportType = data.exportType;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = !data.programme.programme?'ALL':data.programme.programme;

    return this.http.get(
      environment.baseUrl +
        `api/report-104?stateCode=${stateCode}&stateName=${stateName}&studyMode=${studyMode}&courseMode=${courseMode}&levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&programmeName=${programmeName}&categoryType=${categoryType}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport103C(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let religiousCategoryName = !obj.religiousCategory
      ? 'ALL'
      : obj.religiousCategory;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let socialCategoryName = !obj?.socialCategory ? 'ALL' : obj.socialCategory;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let categoryType = data.socialCategory;
    let exportType = data.exportType;
    let institutionName = data.institution.name;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let stateBodyId = data.bodyType.id;
    let bodyTypeName = data.bodyType.type;
    let standaloneInstitutionId = 'ALL';
    return this.http.get(
      environment.baseUrl +
        `api/report-103C?stateCode=${stateCode}&stateName=${stateName}&courseMode=${courseMode}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&courseModeName=${courseModeName}&categoryType=${categoryType}&stateBodyId=${stateBodyId}&bodyTypeName=${bodyTypeName}&studyModeName=${studyModeName}&standaloneInstitutionId=${standaloneInstitutionId}&institutionName=${institutionName}&managementId=${managementId}&managementTypeName=${managementTypeName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport103B(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let religiousCategoryName = !obj.religiousCategory
      ? 'ALL'
      : obj.religiousCategory;
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let socialCategoryName = !obj?.socialCategory ? 'ALL' : obj.socialCategory;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let categoryType = data.socialCategory;
    let exportType = data.exportType;
    let universityName = data.universityName.name;
    let universityId = data.universityName.id;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let collegeInstitutionId = data.collegeType.id;
    let collegeTypeName = data.collegeType.type;
    let collegeInstitutionTypeId = data.collegeType.id;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    return this.http.get(
      environment.baseUrl +
        `api/report-103B?collegeTypeName=${collegeTypeName}&categoryType=${categoryType}&collegeInstitutionTypeId=${collegeInstitutionTypeId}&collegeInstitutionId=${collegeInstitutionId}&managementId=${managementId}&managementTypeName=${managementTypeName}&universityType=${universityType}&universityTypeName=${universityTypeName}&universityId=${universityId}&universityName=${universityName}&stateCode=${stateCode}&stateName=${stateName}&religiousCategoryName=${religiousCategoryName}&courseMode=${courseMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport103A(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let religiousCategoryName = !obj.religiousCategory
      ? 'ALL'
      : obj.religiousCategory;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    // studyMode == '1' ? 'REGULAR' : studyMode == '2' ? 'DISTANCE' : 'ALL';
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let socialCategoryName = !obj?.socialCategory ? 'ALL' : obj.socialCategory;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let categoryType = data.socialCategory;
    let exportType = data.exportType;
    let universityName = data.universityName.name;
    let universityId = data.universityName.id;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;

    return this.http.get(
      environment.baseUrl +
        `api/report-103A?universityType=${universityType}&categoryType=${categoryType}&universityTypeName=${universityTypeName}&universityId=${universityId}&universityName=${universityName}&stateCode=${stateCode}&stateName=${stateName}&religiousCategoryName=${religiousCategoryName}&studyModeName=${studyModeName}&courseMode=${courseMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyMode=${studyMode}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport103(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    studyMode == '1' ? 'REGULAR' : studyMode == '2' ? 'DISTANCE' : 'ALL';
    let courseModeName = !obj.courseModeName ? 'ALL' : obj.courseModeName;
    let religiousCategoryName = !obj.religiousCategory
      ? 'ALL'
      : obj.religiousCategory;
    let studyModeName = !obj.studyModeName ? 'ALL' : obj.studyModeName;
    let socialCategoryName = !obj?.socialCategory ? 'ALL' : obj.socialCategory;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let categoryType = data.socialCategory;
    let exportType = data.exportType;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = !data.programme.programme?'ALL':data.programme.programme;

    return this.http.get(
      environment.baseUrl +
        `api/report-103?stateCode=${stateCode}&categoryType=${categoryType}&religiousCategoryName=${religiousCategoryName}&socialCategoryName=${socialCategoryName}&stateName=${stateName}&studyMode=${studyMode}&courseMode=${courseMode}&levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&programmeName=${programmeName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport102C(data: any, inputObj: any) {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-102C?stateCode=${data.addressStateCode.stateCode}&stateName=${data.addressStateCode.stateName}&bodyTypeName=${data.bodyType.type}&managementTypeName=${data.managementType.managementType}&stateBodyId=${data.bodyType.id}&managementId=${data.managementType.id}&institutionId=${data.institutionName.id}&institutionName=${data.institutionName.name}&surveyYear=${data.surveyYear}&exportType=${data.exportType}&`,
      httpOptions
    );
  }
  getReport102B(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let exportType = data.exportType;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let collegeTypeName = data.collegeType.type;
    let collegeInstitutionTypeId = data.collegeType.id;
    let collegeInstitutionId = 'ALL';
    let managementId = data.managementType.id;
    let universityId = data.universityName.id;

    return this.http.get(
      environment.baseUrl +
        `api/report-102B?collegeInstitutionTypeId=${collegeInstitutionTypeId}&collegeInstitutionId=${collegeInstitutionId}&collegeTypeName=${collegeTypeName}&managementId=${managementId}&universityType=${universityType}&universityTypeName=${universityTypeName}&universityId=${universityId}&stateCode=${stateCode}&stateName=${stateName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport102A(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let exportType = data.exportType;
    let universityName = data.universityName.name;
    let universityId = data.universityName.id;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    return this.http.get(
      environment.baseUrl +
        `api/report-102A?universityName=${universityName}&universityType=${universityType}&universityTypeName=${universityTypeName}&universityId=${universityId}&stateCode=${stateCode}&stateName=${stateName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport102(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-102?stateCode=${stateCode}&stateName=${stateName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport101C(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    let courseModeName =
      obj?.courseModeName !== undefined ? obj.courseModeName : 'ALL';
    let studyModeName =
      obj?.studyModeName !== undefined ? obj.studyModeName : 'ALL';
    let categoryType = data.socialCategory;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName =data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = !data.programme.programme?'ALL':data.programme.programme;
    let stateBodyId = data.bodyType.id;
    let managementId = data.managementType.id;
    let standaloneInstitutionId = 'ALL';
    let bodyTypeName = data.bodyType.type;
    let managementTypeName = data.managementType.managementType;
    let institutionName = data.institution.name?data.institution.name:'ALL';
    

    return this.http.get(
      environment.baseUrl +
        `api/report-101C?levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&programmeName=${programmeName}&stateCode=${stateCode}&stateName=${stateName}&courseMode=${courseMode}&studyMode=${studyMode}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&courseModeName=${courseModeName}&categoryType=${categoryType}&stateBodyId=${stateBodyId}&bodyTypeName=${bodyTypeName}&studyModeName=${studyModeName}&standaloneInstitutionId=${standaloneInstitutionId}&institutionName=${institutionName}&managementId=${managementId}&managementTypeName=${managementTypeName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport101B(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let courseMode = data.courseMode;
    let courseModeName = obj?.courseModeName == 'undefined' ? '' : 'All';
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let universityName = data.universityName.name;
    let universityId = data.universityName.id;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = data.programme.programme?data.programme.programme:'ALL';
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let collegeName = data.collegeName.id;
    let collegeInstitutionTypeId = data.collegeType.id;
    let collegeTypeName = data.collegeType.type;
    let collegeInstitutionId = data.collegeName.id;

    return this.http.get(
      environment.baseUrl +
        `api/report-101B?levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&programmeName=${programmeName}&collegeTypeName=${collegeTypeName}&collegeInstitutionId=${collegeInstitutionId}&collegeInstitutionTypeId=${collegeInstitutionTypeId}&collegeName=${collegeName}&managementId=${managementId}&managementTypeName=${managementTypeName}&universityType=${universityType}&universityTypeName=${universityTypeName}&universityId=${universityId}&universityName=${universityName}&stateCode=${stateCode}&stateName=${stateName}&courseMode=${courseMode}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport101A(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    let courseModeName =
      obj?.courseModeName !== undefined ? obj.courseModeName : 'ALL';
    let studyModeName =
      obj?.studyModeName !== undefined ? obj.studyModeName : 'ALL';
    let religiousCategoryName =
      obj?.religiousCategory !== undefined ? obj.religiousCategory : 'ALL';
    let socialCategoryName =
      obj?.socialCategory !== undefined ? obj.socialCategory : 'ALL';
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let universityName = data.universityName.name;
    let universityId = data.universityName.id;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = data.programme.programme?data.programme.programme:'ALL';

    return this.http.get(
      environment.baseUrl +
        `api/report-101A?universityType=${universityType}&levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&programmeName=${programmeName}&universityTypeName=${universityTypeName}&universityId=${universityId}&universityName=${universityName}&stateCode=${stateCode}&stateName=${stateName}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&courseMode=${courseMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport101(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    let courseModeName =
      obj?.courseModeName !== undefined ? obj.courseModeName : 'ALL';
    let studyModeName =
      obj?.studyModeName !== undefined ? obj.studyModeName : 'ALL';
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let levelId = data.level.id;
    let levelName = data.level.name;
    let programmeId = data.programme.id;
    let programmeName = !data.programme.programme?'ALL':data.programme.programme;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-101?stateCode=${stateCode}&stateName=${stateName}&courseMode=${courseMode}&studyMode=${studyMode}&levelId=${levelId}&levelName=${levelName}&programmeId=${programmeId}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&courseModeName=${courseModeName}&studyModeName=${studyModeName}&surveyYear=${data.surveyYear}&exportType=${exportType}&programmeName=${programmeName}`,
      httpOptions
    );
  }
  getReport100C(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    let courseModeName =
      obj?.courseModeName !== undefined ? obj.courseModeName : 'ALL';
    let studyModeName =
      obj?.studyModeName !== undefined ? obj.studyModeName : 'ALL';
    let religiousCategoryName =
      obj?.religiousCategory !== undefined ? obj.religiousCategory : 'ALL';
    let socialCategoryName =
      obj?.socialCategory !== undefined ? obj.socialCategory : 'ALL';
    let socialCategory = data.socialCategory;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.disciplineGroup;
    let exportType = data.exportType;
    let managementId = data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let stateBodyId = 'ALL';
    let bodyTypeName = data.bodyType.type;
    let institutionName = data.institution.name;
    let standaloneInstitutionId = 'ALL';

    return this.http.get(
      environment.baseUrl +
        `api/report-100C?stateCode=${stateCode}&stateName=${stateName}&courseMode=${courseMode}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&courseModeName=${courseModeName}&categoryType=${socialCategory}&stateBodyId=${stateBodyId}&bodyTypeName=${bodyTypeName}&studyModeName=${studyModeName}&standaloneInstitutionId=${standaloneInstitutionId}&institutionName=${institutionName}&managementId=${managementId}&managementTypeName=${managementTypeName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport100B(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    let courseModeName =
      obj?.courseModeName !== undefined ? obj.courseModeName : 'ALL';
    let studyModeName =
      obj?.studyModeName !== undefined ? obj.studyModeName : 'ALL';
    let religiousCategoryName =
      obj?.religiousCategory !== undefined ? obj.religiousCategory : 'ALL';
    let socialCategoryName =
      obj?.socialCategory !== undefined ? obj.socialCategory : 'ALL';
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.disciplineGroup;
    let exportType = data.exportType;
    let universityName = data.universityName.name;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let universityId = data.universityName.id;
    let managementTypeName = data.managementType.managementType?data.managementType.managementType:'ALL';
    let managementId=data.managementType.id
    let collegeId = data.collegeName.id;
    let collegeTypeName = data.collegeName.name;
    let collegeInstitutionId = data.collegeType.id;

    return this.http.get(
      environment.baseUrl +
        `api/report-100B?collegeTypeName=${collegeTypeName}&collegeInstitutionId=${collegeInstitutionId}&collegeInstitutionTypeId=${collegeId}&collegeName=${collegeId}&managementId=${managementId}&managementTypeName=${managementTypeName}&universityName=${universityName}&universityType=${universityType}&universityTypeName=${universityTypeName}&universityId=${universityId}&stateCode=${stateCode}&stateName=${stateName}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&courseMode=${courseMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport100A(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    let courseModeName =
      obj?.courseModeName !== undefined ? obj.courseModeName : 'ALL';
    let studyModeName =
      obj?.studyModeName !== undefined ? obj.studyModeName : 'ALL';
    let religiousCategoryName =
      obj?.religiousCategory !== undefined ? obj.religiousCategory : 'ALL';
    let socialCategoryName =
      obj?.socialCategory !== undefined ? obj.socialCategory : 'ALL';
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let exportType = data.exportType;
    let universityName = data.universityName.name?data.universityName.name:'ALL';
    let universityType =data.universityType.id?data.universityType.id:'ALL';
    let universityTypeName = data.universityType.type?data.universityType.type:'ALL';
    let universityId = data.universityName.id?data.universityName.id:'All';

    return this.http.get(
      environment.baseUrl +
        `api/report-100A?universityName=${universityName}&universityType=${universityType}&universityTypeName=${universityTypeName}&universityId=${universityId}&stateCode=${stateCode}&stateName=${stateName}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&courseMode=${courseMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport139(data: any) {
    console.log(data);
    let userId = this.localService.getData('userId');
    var affiliated = data.affiliated=='Yes'?'1':data.affiliated=='No'?'2':'ALL';
    let institutionManagementId = data.managementType.id?data.managementType.id:'ALL';
    let stateCode = data.addressStateCode.stateCode?data.addressStateCode.stateCode:'ALL';
   let stateName = data.addressStateCode.stateName?data.addressStateCode.stateName:'ALL';
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    if(data.institution==="College"){
   
      return this.http.get(
        environment.baseUrl +
          `api/report-139?universityName=${data.universityName.name}&universityType=${data.universityType.id}&universityTypeName=${data.universityType.type}&universityId=${data.universityName.id}&institutionManagementId=${data.managementType.id}&institutionParentId=${data.universityName.id}&institutionTypeId=${data.collegeType.id}&institutionTypeName=${data.collegeType.type}&institutionType=${data.institution}&stateCode=${stateCode}&stateName=${stateName}&surveyYear=${surveyYear}&exportType=${exportType}`,
        httpOptions
      );
    }
    if(data.institution==="University"){
      return this.http.get(
        environment.baseUrl +
          `api/report-139?constitutedFromCollege=${affiliated}&universityType=${data.universityType.id}&universityTypeName=${data.universityType.type}&institutionType=${data.institution}&stateCode=${stateCode}&stateName=${stateName}&surveyYear=${surveyYear}&exportType=${exportType}`,
        httpOptions
      );
    }
    if(data.institution==='Standalone'){
      return this.http.get(
        environment.baseUrl +
          `api/report-139?institutionManagementId=${institutionManagementId}&institutionType=${data.institution}&stateBodyName=${data.bodyType.type}&stateBodyId=${data.bodyType.id}&stateCode=${stateCode}&stateName=${stateName}&surveyYear=${surveyYear}&exportType=${exportType}`,
        httpOptions
      );
      
    }

    return this.http.get(
      environment.baseUrl +
        `api/report-139?stateCode=${stateCode}&stateName=${stateName}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );

   
  }

  getReport140(data: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let universityType = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let affiliated = data.affiliated=='Yes' ? '1' : data.affiliated=='No' ? '2':'ALL';
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
let universityId=data.universityId?data.universityId:'ALL'
    return this.http.get(
      environment.baseUrl +
        `api/report-140?stateName=${stateName}&universityId=${universityId}&universityType=${universityType}&universityTypeName=${universityTypeName}&constitutedFromCollegeName=${affiliated}&constitutedFromCollege=${affiliated}&stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport112(data: any) {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let universityId=data.universityId?data.universityId:'ALL';
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-112?stateCode=${stateCode}&universityId=${universityId}&stateName=${stateName}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport145(data: any) {
    var studyModeName1;
    var userId;
    userId = this.localService.getData('userId');
    studyModeName1 = data.studyMode == '1' ? 'REGULAR' : data.studyMode == '2' ? 'DISTANCE' : 'ALL';
    var alldata;
    if(data.institution==='College Institution'){alldata="College";}else if(data.institution==='Standalone Institution'){alldata="Standalone";}else if(data.institution==='University'){alldata="University";}else{
      alldata="ALL";
    }
    if(alldata==="College"){
   
      return this.http.get(
        environment.baseUrl +
          `api/report-145?institutionTypeId=${data.collegeType.id}&institutionTypeName=${data.collegeType.type}&institutionParentId=ALL&institutionType=${alldata}&programmeId=${data.programme.id}&programmeName=${data.programme.programme}&levelName=${data.level.name}&levelId=${data.level.id}&managementTypeId=${data.managementType.id}&managementTypeName=${data.managementType.managementType}&collegeId=${data.collegeType.id}&universityId=${data.universityName.id}&universityType=${data.universityType.id}&universityName=${data.universityName.name}&universityTypeName=${data.universityType.type}&studyMode=${data.studyMode}&studyModeName=${studyModeName1}&stateCode=${data.addressStateCode.stateCode}&stateName=${data.addressStateCode.stateName}&surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
        httpOptions
      );
    }
    if(alldata==="University"){
      return this.http.get(
        environment.baseUrl +
          `api/report-145?institutionParentId=ALL&institutionType=${alldata}&studyMode=${data.studyMode}&programmeId=${data.programme.id}&programmeName=${data.programme.programme}&levelName=${data.level.name}&levelId=${data.level.id}&universityId=${data.universityName.id}&universityType=${data.universityType.id}&universityName=${data.universityName.name}&universityTypeName=${data.universityType.type}&studyModeName=${studyModeName1}&stateCode=${data.addressStateCode.stateCode}&stateName=${data.addressStateCode.stateName}&surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
        httpOptions
      );
      
    }
    if(alldata==="ALL"){
      return this.http.get(
        environment.baseUrl +
          `api/report-145?institutionParentId=ALL&institutionType=${alldata}&studyMode=${data.studyMode}&programmeId=${data.programme.id}&programmeName=${data.programme.programme}&levelName=${data.level.name}&levelId=${data.level.id}&studyModeName=${studyModeName1}&stateCode=${data.addressStateCode.stateCode}&stateName=${data.addressStateCode.stateName}&surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
        httpOptions
      );
      
    }
    if(alldata==='Standalone'){
      return this.http.get(
        environment.baseUrl +
          `api/report-145?institutionParentId=ALL&institutionType=${alldata}&studyMode=${data.studyMode}&stateBodyName=${data.bodyType.id}&stateBodyId=${data.bodyType.id}&programmeId=${data.programme.id}&programmeName=${data.programme.programme}&levelName=${data.level.name}&levelId=${data.level.id}&managementTypeId=${data.managementType.id}&managementTypeName=${data.managementType.managementType}&stateCode=${data.addressStateCode.stateCode}&stateName=${data.addressStateCode.stateName}&studyModeName=${studyModeName1}&surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
        httpOptions
      );
      
    }

    return this.http.get(
      environment.baseUrl +
        `api/report-145?institutionType=${alldata}&levelName=${data.level.name}&levelId=${data.level.id}&stateCode=${data.addressStateCode.stateCode}&stateName=${data.addressStateCode.stateName}&studyMode=${data.studyMode}&studyModeName=${studyModeName1}&surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }
  getReport54(data: any) {
    let userId = this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;

    return this.http.get(
      environment.baseUrl +
        `api/report-54?stateCode=${stateCode}&stateName=${stateName}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport53(data: any) {
    let userId = this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    let stateName = data.addressStateCode.stateName=='undefined' ?'null' : data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    // let universityType = data.universityType.id =='undefined' ?'null' : data.universityType.id;
    let universityType = data.universityType.type;
    let typeId = data.universityType.id;
    let universityId = data.universityId;
    
    // let typeId = 'ALL';
    return this.http.get(
      environment.baseUrl +
        `api/report-53?stateCode=${stateCode}&stateName=${stateName}&universityId=${universityId}&universityType=${universityType}&typeId=${typeId}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport150C(userData: any) {
   
    let userId = this.localService.getData('userId');

    let courseModeName = userData.courseMode == '1' ? 'GENERAL': userData.courseMode == '2'? 'SELF-FINANCING' : 'ALL';
    let courseModeid = userData.courseMode;
    let studyModeName = userData.studyMode == '1' ? 'REGULAR' : userData.studyMode == '2' ? 'DISTANCE' : 'ALL';
    let studyModeid = userData.studyMode
    return this.http.get(
      environment.baseUrl +
        `api/report-150C?managementId=${userData.managementType.id}&managementTypeName=${userData.managementType.managementType}&bodyTypeName=${userData.bodyType.type}&standaloneInstitutionId=${userData.institution.id?userData.institution.id:'ALL'}&institutionName=${userData.institution.name?userData.institution.name:'ALL'}&stateName=${userData.addressStateCode.stateName}&stateCode=${userData.addressStateCode.stateCode}&stateBodyId=${userData.bodyType.id}&courseModeName=${courseModeName}&studyModeName=${studyModeName}&courseMode=${courseModeid}&studyMode=${studyModeid}&surveyYear=${userData.surveyYear}&exportType=${userData.exportType}`,
      httpOptions
    );
  }
  getReport150B(userData: any) {
    let userId = this.localService.getData('userId');
    
    let courseModeName = userData.courseMode == '1' ? 'GENERAL': userData.courseMode == '2'? 'SELF-FINANCING' : 'ALL';
    let courseModeid = userData.courseMode;
    let studyModeName = userData.studyMode == '1' ? 'REGULAR' : userData.studyMode == '2' ? 'DISTANCE' : 'ALL';
    let studyModeid = userData.studyMode

    return this.http.get(
      environment.baseUrl +
        `api/report-150B?managementId=${userData.managementType.id}&managementTypeName=${userData.managementType.managementType}&collegeName=${userData.collegeName.name}&collegeInstitutionId=${userData.collegeName.id}&collegeTypeName=${userData.collegeType.type}&collegeInstitutionTypeId=${userData.collegeType.id}&stateName=${userData.addressStateCode.stateName}&stateCode=${userData.addressStateCode.stateCode}&universityName=${userData.universityName.name}&universityType=${userData.universityType.id}&universityTypeName=${userData.universityType.type}&universityId=${userData.universityName.id}&courseModeName=${courseModeName}&studyModeName=${studyModeName}&courseMode=${courseModeid}&studyMode=${studyModeid}&surveyYear=${userData.surveyYear}&exportType=${userData.exportType}`,
      httpOptions
    );
  }

  getReport150A(userData: any) {
    
    console.log("userData",userData);
    let userId = this.localService.getData('userId');
   
    let courseModeName = userData.courseMode == '1' ? 'GENERAL': userData.courseMode == '2'? 'SELF-FINANCING' : 'ALL';
    let courseModeid = userData.courseMode;
    let studyModeName = userData.studyMode == '1' ? 'REGULAR' : userData.studyMode == '2' ? 'DISTANCE' : 'ALL';
    let studyModeid = userData.studyMode
    return this.http.get(
      environment.baseUrl +
        `api/report-150A?stateName=${userData.addressStateCode.stateName}&stateCode=${userData.addressStateCode.stateCode}&universityName=${userData.universityName.name}&universityType=${userData.universityType.id}&universityTypeName=${userData.universityType.type}&universityId=${userData.universityName.id}&courseModeName=${courseModeName}&studyModeName=${studyModeName}&courseMode=${courseModeid}&studyMode=${studyModeid}&surveyYear=${userData.surveyYear}&exportType=${userData.exportType}`,
      httpOptions
    );
  }
  getReport150(userData: any) {
    
    let userId = this.localService.getData('userId');

    let courseModeName = userData.courseMode == '1' ? 'GENERAL': userData.courseMode == '2'? 'SELF-FINANCING' : 'ALL';
    let courseModeid = userData.courseMode;
    let studyModeName = userData.studyMode == '1' ? 'REGULAR' : userData.studyMode == '2' ? 'DISTANCE' : 'ALL';
    let studyModeid = userData.studyMode
    return this.http.get(
      environment.baseUrl +
        `api/report-150?stateName=${userData.addressStateCode.stateName}&stateCode=${userData.addressStateCode.stateCode}&courseModeName=${courseModeName}&studyModeName=${studyModeName}&courseMode=${courseModeid}&studyMode=${studyModeid}&surveyYear=${userData.surveyYear}&exportType=${userData.exportType}`,
      httpOptions
    );
  }

  getReport65(data: any): Observable<any> {
    var userId =this.localService.getData('userId');
    var surveyYear = data.surveyYear
    var stateBodyId =  data.bodyType.id;;
    var institutionManagementId = data.managementType.id;
    var institutionTypeId =data.institution=='College Institution'? data.collegeType.type: data.institution=='University'? data.universityType.id:'ALL';
   var stateName = data.addressStateCode.stateName
   var institutionId =data.institution=='College Institution'?data.collegeType.id : data.institution=='Standalone Institution'?data.bodyType.id: data.institution=='University'?data.universityName.id:'ALL'
   var institutionCategory =data.institution
   var stateCode =data.addressStateCode.stateCode
   var exportType  = data.exportType
   var institutionParentId = data.universityName.id
   var url
    if(data.institution == 'College Institution'){
      url = `api/report-65?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionTypeId=${institutionTypeId}&institutionCategory=${institutionCategory}&stateBodyId=ALL&stateName=${stateName}&institutionManagementId=${institutionManagementId}&institutionParentId=${institutionParentId}`
    } 
    else if(data.institution =='University'){
      url =`api/report-65?stateCode=${stateCode}&institutionTypeId=${institutionTypeId}&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=ALL&stateName=${stateName}`
    } 
    else if(data.institution =='Standalone Institution'){
      url =`api/report-65?stateCode=${stateCode}&institutionTypeId=ALL&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=${stateBodyId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}`
    } else {
      url = `api/report-65?stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionCategory=${institutionCategory}&stateBodyId=${stateBodyId}&institutionId=${institutionId}&stateName=${stateName}&institutionManagementId=${institutionManagementId}&institutionParentId=${institutionParentId}`
    }
    return this.http.get(
      environment.baseUrl + url,
      httpOptions
    );
  }

  getReport10(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-10?surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }

  getReport44(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-44?surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }

  getReport45(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-45?surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }

  getReport46(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-46?surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }

  getReport47A(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-47A?surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }
  getReport47B(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-47B?surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }
  getReport47C(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-47C?surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }
  getReport48(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    var surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    var stateCode = data.addressStateCode.stateCode;
    var exportType = data.exportType;
    var universityTypeId = data.universityType.id;
    return this.http.get(
      environment.baseUrl +
        `api/report-48?surveyYear=${surveyYear}&stateName=${stateName}&stateCode=${stateCode}&universityTypeId=${universityTypeId}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport49(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    var surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    var stateCode = data.addressStateCode.stateCode;
    var exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-49?surveyYear=${surveyYear}&stateName=${stateName}&stateCode=${stateCode}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport50(data: any): Observable<any> {
    var userId = this.localService.getData('userId');
    if(data.institution=='College Institution'){
      return this.http.get(
        environment.baseUrl +
          `api/report-50?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionManagementId=${data.managementType.id}&institutionTypeId=${data.collegeType.id}&institutionParentId=${data.universityName.id}&institutionCategory=${data.institution}&exportType=${data.exportType}`,
        httpOptions
      );
    }
    if(data.institution=='Standalone Institution'){
      return this.http.get(
        environment.baseUrl +
          `api/report-50?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionManagementId=${data.managementType.id}&institutionTypeId=${data.bodyType.id}&institutionCategory=${data.institution}&exportType=${data.exportType}`,
        httpOptions
      );
    }
    if(data.institution=='University'){
      return this.http.get(
        environment.baseUrl +
          `api/report-50?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionTypeId=${data.universityType.id}&institutionCategory=${data.institution}&exportType=${data.exportType}`,
        httpOptions
      );
    }
    return this.http.get(
      environment.baseUrl +
        `api/report-50?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionCategory=${data.institution}&exportType=${data.exportType}`,
      httpOptions
    );
  }

  getReport51(data: any): Observable<any> {
    var userId = this.localService.getData('userId');
 
    if(data.institution=='College Institution'){
      return this.http.get(
        environment.baseUrl +
          `api/report-51?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionManagementId=${data.managementType.id}&institutionTypeId=${data.collegeType.id}&institutionParentId=${data.universityName.id}&institutionCategory=${data.institution}&exportType=${data.exportType}`,
        httpOptions
      );
    }
    if(data.institution=='Standalone Institution'){
      return this.http.get(
        environment.baseUrl +
          `api/report-51?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionManagementId=${data.managementType.id}&institutionTypeId=${data.bodyType.id}&institutionCategory=${data.institution}&exportType=${data.exportType}`,
        httpOptions
      );
    }
    if(data.institution=='University'){
      return this.http.get(
        environment.baseUrl +
          `api/report-51?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionTypeId=${data.universityType.id}&institutionCategory=${data.institution}&exportType=${data.exportType}`,
        httpOptions
      );
    }
    return this.http.get(
      environment.baseUrl +
        `api/report-51?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionCategory=${data.institution}&exportType=${data.exportType}`,
      httpOptions
    );
  }

  getReport51A(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-51A?surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }

  getReport51B(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-51B?surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }
  getReport42(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
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
      bodyType = data.bodyType.id == '' ? 'NULL' : 'NULL';
      managementType = data.managementType.id == '' ? 'NULL' : 'NULL';
      collegeType = data.collegeType.id == '' ? 'NULL' : 'NULL';
      universityTypeCode = data.universityType.id;
      universityName = data.universityName.id == '' ? 'NULL' : 'NULL';
      institution = data.institution;
    } else if (data.institution == 'College Institution') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = data.bodyType.id == '' ? 'NULL' : 'NULL';
      managementType = data.managementType.id;
      collegeType = data.collegeType.id;
      universityTypeCode = data.universityType.id == '' ? 'NULL' : 'NULL';
      universityName = data.universityName.id;
      institution = data.institution;
    } else if (data.institution == 'Standalone Institution') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = data.bodyType.id;
      managementType = data.managementType.id;
      collegeType = data.collegeType.id == '' ? 'NULL' : 'NULL';
      universityTypeCode = data.universityType.id == '' ? 'NULL' : 'NULL';
      universityName = data.universityName.id == '' ? 'NULL' : 'NULL';
      institution = data.institution;
    } else {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = data.bodyType.id;
      managementType = data.managementType.id;
      collegeType = data.collegeType.id == '' ? 'NULL' : 'NULL';
      universityTypeCode = data.universityType.id == '' ? 'NULL' : 'NULL';
      universityName = data.universityName.id == '' ? 'NULL' : 'NULL';
      institution = 'ALL';
    }
    return this.http.get(
      environment.baseUrl +
        `report42?collegeType=${collegeType}&instType=${institution}&mgmtType=${managementType}&reportType=${reportType}&standAloneBodyType=${bodyType}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityName=${universityName}&universityType=${universityTypeCode}`,
      httpOptions
    );
  }

  getReport38C(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    var surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    var stateCode = data.addressStateCode.stateCode;
    var exportType = data.exportType;
    var institutionCategoryName = data.institution;
    var institutionCategory = data.institution;
    var districtCode = data.districtsName.districtCode?data.districtsName.districtCode:'ALL';
    var districtName = data.districtsName.districtName?data.districtsName.districtName:'ALL';

    return this.http.get(
      environment.baseUrl +
        `api/report-38C?institutionCategoryName=${institutionCategoryName}&districtCode=${districtCode}&districtName=${districtName}&stateName=${stateName}&stateCode=${stateCode}&institutionCategory=${institutionCategory}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport127(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    var surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    var stateCode = data.addressStateCode.stateCode;
    var exportType = data.exportType;
    var institutionCategory = 'ALL';

    return this.http.get(
      environment.baseUrl +
        `api/report-127?stateName=${stateName}&stateCode=${stateCode}&institutionCategory=${institutionCategory}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport127A(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let universityTypeCode = data.universityType.id;
    let institutionCategory = 'ALL';
    let universityTypeId = data.universityType.id;
    let universityTypeName = data.universityType.type;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-127A?universityType=${universityTypeCode}&universityTypeName=${universityTypeName}&stateCode=${stateCode}&universityTypeId=${universityTypeId}&institutionCategory=${institutionCategory}&stateName=${stateName}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport127B(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let exportType = data.exportType;
    let managementId=data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let collegeId = data.collegeType.id;
    let collegeTypeName=data.collegeType.type;
    let universityType = data.universityType.id;
    let universityTypeName=data.universityType.type
    let universityName=data.universityName.name;
    let universityId=data.universityName.id;
    // let universityId=data.universityId;

    return this.http.get(
      environment.baseUrl +
        `api/report-127B?stateCode=${stateCode}&stateName=${stateName}&surveyYear=${surveyYear}&exportType=${exportType}&universityTypeName=${universityTypeName}&universityId=${universityId}&universityType=${universityType}&managementTypeName=${managementTypeName}&collegeInstitutionTypeId=${collegeId}&universityName=${universityName}&collegeTypeName=${collegeTypeName}&managementId=${managementId}`,
      httpOptions
    );
  }

  getReport127C(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let exportType = data.exportType;
    let managementId=data.managementType.id;
    let managementTypeName = data.managementType.managementType;
    let bodyType = 'ALL';
    let institutionCategory = data.institution;
    let institutionCategoryName = data.institution;

    return this.http.get(
      environment.baseUrl +
        `api/report-127C?bodyType=${bodyType}&bodyTypeName=${bodyType}&stateBodyId=${bodyType}&stateCode=${stateCode}&stateName=${stateName}&surveyYear=${surveyYear}&managementId=${managementId}&managementTypeName=${managementTypeName}&institutionCategory=${institutionCategory}&institutionCategoryName=${institutionCategoryName}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport4(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    var surveyYear = data.surveyYear;
    var stateCode = data.addressStateCode.stateCode;
    var exportType = data.exportType;
    let universityType = data.universityType.type;
    let universityTypeId = data.universityType.id;

    return this.http.get(
      environment.baseUrl +
        `api/report-4?surveyYear=${surveyYear}&universityType=${universityType}&universityTypeId=${universityTypeId}&stateCode=${stateCode}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport5(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-5?surveyYear=${data.surveyYear}&exportType=${data.exportType}`,
      httpOptions
    );
  }

  getReport142(data: any) {
    
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
   let institutionType = data.institution;
let universityId=data.universityId?data.universityId:'ALL'
    return this.http.get(
      environment.baseUrl +
        `api/report-142?stateName=${stateName}&stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}&institutionType=${institutionType}&universityId=${universityId}`,
      httpOptions
    );
  }

  getReport38A(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `report38A?surveyYear=${data.surveyYear}&reportType=${data.reportType}`,
      httpOptions
    );
  }

  getReport38B(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `report38B?surveyYear=${data.surveyYear}&reportType=${data.reportType}`,
      httpOptions
    );
  }

  getReport109B(userData: any): Observable<any> {
    
    let userId = this.localService.getData('userId');
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let collegeType = userData.collegeType.id;
    let universityTypeId = userData.universityType.id;
    let collegeCode =
      userData.collegeName.id ? userData.collegeName.id :"ALL";
    let reportType = userData.reportType;
    let managementType = userData.managementType.id;
    let religiousCat = userData.religiousCategory;
    let categoryType = userData.socialCategory;
    let courseMode = userData.courseMode;
    let studyMode = userData.studyMode?userData.studyMode:'ALL';
    let universityId = userData.universityName.id?userData.universityName.id:userData.universityId;
    // let universityId = userData.universityId;


    return this.http.get(
      environment.baseUrl +
        `report109B?collegeCode=${collegeCode}&collegeTypeId=${collegeType}&courseMode=${courseMode}&mgmtId=${managementType}&religiousCat=${religiousCat}&reportType=${reportType}&socialCat=${categoryType}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityTypeId=${universityTypeId}&studyMode=${studyMode}&universityId=${universityId}`,
      httpOptions
    );
  }

  getReport115(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let StateCode = data.addressStateCode.stateCode;
    let religiousCat = data.religiousCategory;
    let universityType = data.universityType.id;
    let socialCategory = data.socialCategory;
    let reportType = data.reportType;
    let programme = data.programme.id;
    let level = data.level.id;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    return this.http.get(
      environment.baseUrl +
        `report115?categoryType=${socialCategory}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&level=${level}&programme=${programme}&religiousCat=${religiousCat}&reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityType=${universityType}`,
      httpOptions
    );
  }

  getReport109C(userData: any): Observable<any> {
    
    let userId = this.localService.getData('userId');
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let instituteId = userData.institution.id?userData.institution.id:'ALL';
    let reportType = userData.reportType;
    let managementType = userData.managementType.id;
    let religiousCat = userData.religiousCategory;
    let categoryType = userData.socialCategory;
    let courseMode =  userData.courseMode?userData.courseMode:'ALL';
    let studyMode = userData.studyMode?userData.studyMode:'ALL';
    let bodyType = userData.bodyType.id;
    return this.http.get(
      environment.baseUrl +
        `report109C?bodyType=${bodyType}&collegeCode=${instituteId}&courseMode=${courseMode}&mgmtId=${managementType}&religiousCat=${religiousCat}&reportType=${reportType}&socialCat=${categoryType}&stateCode=${StateCode}&studyMode=${studyMode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReport52(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let surveyYear = userData.surveyYear;
    let reportType = userData.reportType;
    return this.http.get(
      environment.baseUrl +
        `report52?reportType=${reportType}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReport55(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    var surveyYear = '';
    var stateCode = '';
    var reportType = '';
    var bodyType = '';
    var managementType = '';
    var collegeType = '';
    var universityTypeCode = '';
    var universityName = '';
    var institution = '';
    if (data.institution == 'ALL') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode?data.addressStateCode.stateCode:"ALL";
      reportType = data.reportType;
      bodyType = data.bodyType.id ?data.bodyType.id:'ALL';
      managementType = 'ALL';
      universityTypeCode = 'ALL';
      universityName = 'ALL';
      collegeType = data.institution?data.institution:"ALL";
      institution = data.institution?data.institution:"ALL";
    }
    if (data.institution == 'University') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = data.bodyType.id ?data.bodyType.id:'ALL';
      managementType = data.managementType.id ? data.managementType.id : 'ALL';
      collegeType = data.collegeType.id ? data.collegeType.id  : 'ALL';
      universityTypeCode = data.universityType.id;
      universityName = data.universityName.id?data.universityName.id  : 'ALL';
      institution = data.institution;
    }
    if (data.institution == 'College Institution') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = data.bodyType.id ? data.bodyType.id : 'ALL';
      managementType = data.managementType.id;
      collegeType = data.collegeType.id;
      universityTypeCode = data.universityType.id ?data.universityType.id  : 'ALL';
      universityName = data.universityName.id;
      institution = data.institution;
    }
    if (data.institution == 'Standalone Institution') {
      surveyYear = data.surveyYear;
      stateCode = data.addressStateCode.stateCode;
      reportType = data.reportType;
      bodyType = data.bodyType.id;
      managementType = data.managementType.id;
      collegeType = data.collegeType.id ? data.collegeType.id : 'ALL';
      universityTypeCode = data.universityType.id? data.universityType.id : 'ALL';
      universityName = data.universityName.id? data.universityName.id : 'ALL';
      institution = data.institution;
    }
    return this.http.get(
      environment.baseUrl +
        `report55?collegeType=${collegeType}&instType=${institution}&mgmtType=${managementType}&reportType=${reportType}&standAloneBodyType=${bodyType}&stateCode=${stateCode}&surveyYear=${surveyYear}&universityName=${universityName}&universityType=${universityTypeCode}`,
      httpOptions
    );
  }

  getReport55a(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let surveyYear = userData.surveyYear;
    let reportType = userData.reportType;
    let StateCode = userData.addressStateCode.stateCode;
    return this.http.get(
      environment.baseUrl +
        `report55A?reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReport55b(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    return this.http.get(
      environment.baseUrl +
        `report55B?reportType=${reportType}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getReport110(userData: any): Observable<any> {
   
    let userId = this.localService.getData('userId');
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let courseMode = userData.courseMode;
    let studyMode = userData.studyMode;
    let reportType = userData.reportType;
    let disciplineGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let levelIda = userData.level.id;
    return this.http.get(
      environment.baseUrl +
        `report110?levelId=${levelIda}&studyMode=${studyMode}&surveyYear=${surveyYear}&stateCode=${StateCode}&reportType=${reportType}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&courseMode=${courseMode}`,
      httpOptions
    );
  }

  getReport110A(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let courseMode = userData.courseMode;

    let studyMode = userData.studyMode;
    let disciplineGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let levelId = userData.level.id;
    let reportType = userData.reportType;
    let StateCode = userData.addressStateCode.stateCode;
    let surveyYear = userData.surveyYear;
    let universityId = userData.universityName.id;
    let universityTypeId = userData.universityType.id;
    return this.http.get(
      environment.baseUrl +
        `report110A?universityId=${universityId}&universityTypeId=${universityTypeId}&courseMode=${courseMode}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&levelId=${levelId}&reportType=${reportType}&stateCode=${StateCode}&studyMode=${studyMode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReport110B(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let surveyYear = userData.surveyYear;
    let courseMode = userData.courseMode;
    let levelId = userData.level.name;
    let disciplineGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let collegeId = userData.collegeName.id;
    let collegeType = userData.collegeType.id;
    let universityId = userData.universityName.id;
    let universityTypeId = userData.universityType.id;
    let managementType = userData.managementType.id;
    let reportType = userData.reportType;
    let StateCode = userData.addressStateCode.stateCode;
    return this.http.get(
      environment.baseUrl +
        `report110B?collegeInstId=${collegeId}&collegeInstTypeId=${collegeType}&courseMode=${courseMode}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&levelId=${levelId}&managementId=${managementType}&reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityId=${universityId}&universityTypeId=${universityTypeId}`,
      httpOptions
    );
  }

  getReport110C(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let courseMode = userData.courseMode;
    let disciplineGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let managementType = userData.managementType.id;
    let levelId = userData.level.id;
    let reportType = userData.reportType;
    let StateCode = userData.addressStateCode.stateCode;
    let surveyYear = userData.surveyYear;
    let bodyType = userData.bodyType.id;
    let instId = userData.institution.id?userData.institution.id:'ALL';
    let studyMode=userData.studyMode;
   
    return this.http.get(
      environment.baseUrl +
        `report110C?courseMode=${courseMode}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&levelId=${levelId}&managementId=${managementType}&reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}&instId=${instId}&statebodyId=${bodyType}&studyMode=${studyMode}`,
      httpOptions
    );
  }

  getReport64(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    return this.http.get(
      environment.baseUrl +
        `report64?reportType=${reportType}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReport143(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let exportType = userData.exportType;
    let StateCode = userData.addressStateCode.stateCode;
    let stateName = userData.addressStateCode.stateName;
    let surveyYear = userData.surveyYear;
    let courseMode = userData.courseMode;
    let courseModeName =
      courseMode == '1'
        ? 'GENERAL'
        : courseMode == '2'
        ? 'SELF-FINANCING'
        : 'ALL';
    let studyMode = userData.studyMode;
    let studyModeName =
      studyMode == '1' ? 'REGULAR' : studyMode == '2' ? 'DISTANCE' : 'ALL';
    let universityType = 'ALL';
    let institutionCategory = userData.institution;
    if(userData.institution=="College"){
      return this.http.get(
        environment.baseUrl +
          `api/report-143?stateCode=${userData.addressStateCode.stateCode}&stateName=${userData.addressStateCode.stateName}&courseMode=${ userData.courseMode}&studyMode=${ userData.studyMode}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&universityTypeName=${userData.universityType.type}&universityName=${userData.universityName.name}&universityType=${userData.universityType.id}&universityId=${userData.universityName.id}&collegeTypeName=${userData.collegeType.type}&collegeId=${userData.collegeType.id}&managementTypeName=${userData.managementType.managementType}&managementTypeId=${userData.managementType.id}&surveyYear=${userData.surveyYear}&exportType=${userData.exportType}&institutionType=${userData.institution}&institutionTypeName=${userData.institution}&institutionManagementId=${userData.managementType.id}`,
        httpOptions
      )
    }
    if(userData.institution=="Standalone"){
      return this.http.get(
        environment.baseUrl +
          `api/report-143?stateCode=${userData.addressStateCode.stateCode}&stateName=${userData.addressStateCode.stateName}&courseMode=${ userData.courseMode}&studyMode=${ userData.studyMode}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&managementTypeName=${userData.managementType.managementType}&managementTypeId=${userData.managementType.id}&stateBodyId=${userData.bodyType.id}&surveyYear=${userData.surveyYear}&exportType=${userData.exportType}&institutionType=${userData.institution}&institutionTypeName=${userData.institution}&stateBodyName=${userData.bodyType.type}&institutionManagementId=${userData.managementType.id}`,
        httpOptions
      )
    }
    if(userData.institution=="University"){
      return this.http.get(
        environment.baseUrl +
          `api/report-143?stateCode=${userData.addressStateCode.stateCode}&stateName=${userData.addressStateCode.stateName}&courseMode=${ userData.courseMode}&studyMode=${ userData.studyMode}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&universityTypeName=${userData.universityType.type}&universityType=${userData.universityType.id}&surveyYear=${userData.surveyYear}&exportType=${userData.exportType}&institutionType=${userData.institution}&institutionTypeName=${userData.institution}`,
        httpOptions
      )
    }
    return this.http.get(
      environment.baseUrl +
        `api/report-143?surveyYear=${surveyYear}&courseMode=${courseMode}&exportType=${exportType}&studyMode=${studyMode}&stateName=${stateName}&&courseModeName=${courseModeName}&institutionType=${userData.institution}&studyModeName=${studyModeName}&universityId=${universityType}&institutionId=${institutionCategory}&stateCode=${StateCode}`,
      httpOptions
    );
  }

  getReport113(userData: any): Observable<any> {
    
    var userId = this.localService.getData('userId');
    if(userData.institution=="College"){
    
      return this.http.get(
        environment.baseUrl +
          `api/report-113?surveyYear=${userData.surveyYear}&universityId=${userData.universityName.id}&universityName=${userData.universityName.name}&institutionTypeName=${userData.collegeType.type}&institutionType=${userData.institution}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&universityType=${userData.universityType.id}&institutionTypeId=${userData.collegeType.id}&institutionParentId=${userData.universityName.id}&institutionManagementId=${userData.managementType.id}&stateCode=${userData.addressStateCode.stateCode}&universityTypeName=${userData.universityType.type}`,
        httpOptions
      );
  }
  if(userData.institution=="Standalone"){
    
    return this.http.get(
      environment.baseUrl +
        `api/report-113?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&constitutedFromColleges=${userData.constitutedFromCollege?userData.constitutedFromCollege:"ALL"}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&institutionManagementId=${userData.managementType.id}&stateBodyId=${userData.bodyType.id}&stateBodyName=${userData.bodyType.type}&stateCode=${ userData.addressStateCode.stateCode}`,
      httpOptions
    );
  }
  if(userData.institution=="University"){
    
    return this.http.get(
      environment.baseUrl +
        `api/report-113?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&constitutedFromColleges=${userData.constitutedFromCollege?userData.constitutedFromCollege:"ALL"}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&universityType=${userData.universityType.id}&stateCode=${userData.addressStateCode.stateCode}&universityTypeName=${userData.universityType.type}`,
      httpOptions
    );
  }
    return this.http.get(
      environment.baseUrl +
        `api/report-113?surveyYear=${userData.surveyYear}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&institutionType=${userData.institution}&stateCode=${userData.addressStateCode.stateCode}`,
      httpOptions
    );
  }

  getReport114(userData: any): Observable<any> {
    var userId = this.localService.getData('userId');
    if(userData.institution=="College"){
    
      return this.http.get(
        environment.baseUrl +
          `api/report-114?surveyYear=${userData.surveyYear}&institutionTypeName=${userData.collegeType.type}&institutionType=${userData.institution}&exportType=${userData.exportType}&universityType=${userData.universityType.id}&institutionTypeId=${userData.collegeType.id}&institutionManagementId=${userData.managementType.id}&universityTypeName=${userData.universityType.type}`,
        httpOptions
      );
  }
  if(userData.institution=="Standalone"){
    
    return this.http.get(
      environment.baseUrl +
        `api/report-114?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&constitutedFromColleges=${userData.constitutedFromCollege}&exportType=${userData.exportType}&institutionManagementId=${userData.managementType.id}&stateBodyId=${userData.bodyType.id}&stateBodyName=${userData.bodyType.type}`,
      httpOptions
    );
  }
  if(userData.institution=="University"){
    
    return this.http.get(
      environment.baseUrl +
        `api/report-114?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&exportType=${userData.exportType}&universityType=${userData.universityType.id}&universityTypeName=${userData.universityType.type}`,
      httpOptions
    );
  }
    return this.http.get(
      environment.baseUrl +
        `api/report-114?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&exportType=${userData.exportType}`,
      httpOptions
    );
  }
  getReport118(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    if(userData.institution=="College"){
    
      return this.http.get(
        environment.baseUrl +
          `api/report-118?surveyYear=${userData.surveyYear}&stateName=${userData.addressStateCode.stateName}&stateCode=${userData.addressStateCode.stateCode}&universityId=${userData.universityName.id}&universityName=${userData.universityName.name}&institutionTypeName=${userData.collegeType.type}&institutionType=${userData.institution}&exportType=${userData.exportType}&universityType=${userData.universityType.id}&institutionTypeId=${userData.collegeType.id}&institutionManagementId=${userData.managementType.id}&universityTypeName=${userData.universityType.type}`,
        httpOptions
      );
  }
  if(userData.institution=="Standalone"){
    
    return this.http.get(
      environment.baseUrl +
        `api/report-118?surveyYear=${userData.surveyYear}&stateName=${userData.addressStateCode.stateName}&stateCode=${userData.addressStateCode.stateCode}&institutionType=${userData.institution}&exportType=${userData.exportType}&institutionManagementId=${userData.managementType.id}&stateBodyId=${userData.bodyType.id}&stateBodyName=${userData.bodyType.type}`,
      httpOptions
    );
  }
  if(userData.institution=="University"){
    
    return this.http.get(
      environment.baseUrl +
        `api/report-118?surveyYear=${userData.surveyYear}&constitutedFromColleges=${userData.constitutedFromCollege?userData.constitutedFromCollege:"ALL"}&stateName=${userData.addressStateCode.stateName}&stateCode=${userData.addressStateCode.stateCode}&institutionType=${userData.institution}&exportType=${userData.exportType}&universityType=${userData.universityType.id}&universityTypeName=${userData.universityType.type}`,
      httpOptions
    );
  }

    return this.http.get(
      environment.baseUrl +
        `api/report-118?surveyYear=${userData.surveyYear}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&institutionType=${userData.institution}&stateCode=${userData.addressStateCode.stateCode}`,
      httpOptions
    );
  }

  getReport119(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    if(userData.institution=="College"){
    
      return this.http.get(
        environment.baseUrl +
          `api/report-119?surveyYear=${userData.surveyYear}&institutionTypeName=${userData.collegeType.type}&institutionType=${userData.institution}&exportType=${userData.exportType}&universityType=${userData.universityType.id}&institutionTypeId=${userData.collegeType.id}&institutionManagementId=${userData.managementType.id}&universityTypeName=${userData.universityType.type}`,
        httpOptions
      );
  }
  if(userData.institution=="Standalone"){
    
    return this.http.get(
      environment.baseUrl +
        `api/report-119?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&constitutedFromColleges=${userData.constitutedFromCollege}&exportType=${userData.exportType}&institutionManagementId=${userData.managementType.id}&stateBodyId=${userData.bodyType.id}&stateBodyName=${userData.bodyType.type}`,
      httpOptions
    );
  }
  if(userData.institution=="University"){
    
    return this.http.get(
      environment.baseUrl +
        `api/report-119?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&exportType=${userData.exportType}&universityType=${userData.universityType.id}&universityTypeName=${userData.universityType.type}`,
      httpOptions
    );
  }
    return this.http.get(
      environment.baseUrl +
        `api/report-119?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&exportType=${userData.exportType}`,
      httpOptions
    );
  }

  getReport128(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    if(userData.institution=="College"){
    
        return this.http.get(
          environment.baseUrl +
            `api/report-128?surveyYear=${userData.surveyYear}&universityId=${userData.universityName.id}&universityName=${userData.universityName.name}&institutionTypeName=${userData.collegeType.type}&institutionType=${userData.institution}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&universityType=${userData.universityType.id}&institutionTypeId=${userData.collegeType.id}&institutionParentId=${userData.universityName.id}&constitutedFromCollege=${userData.constitutedFromCollege?userData.constitutedFromCollege:"ALL"}&institutionManagementId=${userData.managementType.id}&stateCode=${userData.addressStateCode.stateCode}&universityTypeName=${userData.universityType.type}`,
          httpOptions
        );
    }
    if(userData.institution=="Standalone"){
      
      return this.http.get(
        environment.baseUrl +
          `api/report-128?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&institutionManagementId=${userData.managementType.id}&stateBodyId=${userData.bodyType.id}&stateBodyName=${userData.bodyType.type}&stateCode=${ userData.addressStateCode.stateCode}`,
        httpOptions
      );
    }
    if(userData.institution=="University"){
      
      return this.http.get(
        environment.baseUrl +
          `api/report-128?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&constitutedFromCollege=${userData.constitutedFromCollege?userData.constitutedFromCollege:"ALL"}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&universityType=${userData.universityType.id}&stateCode=${userData.addressStateCode.stateCode}&universityTypeName=${userData.universityType.type}`,
        httpOptions
      );
    }
    return this.http.get(
      environment.baseUrl +
        `api/report-128?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&exportType=${userData.exportType}&stateCode=${userData.addressStateCode.stateCode}&stateName=${userData.addressStateCode.stateName}`,
      httpOptions
    );
  }

  getReport130(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    return this.http.get(
      environment.baseUrl +
        `api/report-130?surveyYear=${userData.surveyYear}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}
        &universityId=${userData.universityName.id}&universityName=${ userData.universityName.name}&managementTypeName=${userData.managementType.managementType}&institutionTypeId=${userData.collegeType.id}&collegeTypeName=${userData.collegeType.type}&collegeInstitutionTypeId=${userData.collegeType.id}&stateCode=${userData.addressStateCode.stateCode}&universityType=${userData.universityType.id}&universityTypeName=${userData.universityType.type}&managementId=${userData.managementType.id}`,
      httpOptions
    );
  }

  getReport132(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    if(userData.institution=="College"){
    
      return this.http.get(
        environment.baseUrl +
          `api/report-132?surveyYear=${userData.surveyYear}&universityId=${userData.universityName.id}&universityName=${userData.universityName.name}&institutionTypeName=${userData.collegeType.type}&institutionType=${userData.institution}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&universityType=${userData.universityType.id}&institutionTypeId=${userData.collegeType.id}&institutionParentId=${userData.universityName.id}&institutionManagementId=${userData.managementType.id}&stateCode=${userData.addressStateCode.stateCode}&universityTypeName=${userData.universityType.type}`,
        httpOptions
      );
  }
  if(userData.institution=="Standalone"){
    
    return this.http.get(
      environment.baseUrl +
        `api/report-132?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&constitutedFromColleges=${userData.constitutedFromCollege}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&institutionManagementId=${userData.managementType.id}&stateBodyId=${userData.bodyType.id}&stateBodyName=${userData.bodyType.type}&stateCode=${ userData.addressStateCode.stateCode}`,
      httpOptions
    );
  }
  if(userData.institution=="University"){
    
    return this.http.get(
      environment.baseUrl +
        `api/report-132?surveyYear=${userData.surveyYear}&institutionType=${userData.institution}&constitutedFromCollege=${userData.constitutedFromCollege}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&universityType=${userData.universityType.id}&stateCode=${userData.addressStateCode.stateCode}&universityTypeName=${userData.universityType.type}`,
      httpOptions
    );
  }
    return this.http.get(
      environment.baseUrl +
        `api/report-132?surveyYear=${userData.surveyYear}&exportType=${userData.exportType}&stateName=${userData.addressStateCode.stateName}&stateCode=${userData.addressStateCode.stateCode}`,
      httpOptions
    );
  }

  getReport133(data: any, obj: any): Observable<any> {
    let courseMode = data.courseMode;
    let categoryType = data.socialCategory;
    let socialCategory = data.socialCategory;
    let religiousCategory = data.religiousCategory;
    let userId = this.localService.getData('userId');
    let stateCode = data.addressStateCode.stateCode;
    let studyMode = data.studyMode;
    let stateName = data.addressStateCode.stateName;
    let socialCategoryName =
      socialCategory == '1'
        ? 'SC'
        : socialCategory == '2'
        ? 'ST'
        : socialCategory == '3'
        ? 'OBC'
        : 'ALL';
    let religiousCategoryName =
      religiousCategory == '1'
        ? 'Muslim'
        : religiousCategory == '2'
        ? 'OTHER MINORITY'
        : religiousCategory == '3'
        ? 'PWD'
        : 'ALL';
    let studyModeName =
      studyMode == '1' ? 'REGULAR' : studyMode == '2' ? 'DISTANCE' : 'ALL';
    let courseModeName =
      courseMode == '1'
        ? 'GENERAL'
        : courseMode == '2'
        ? 'SELF-FINANCING'
        : 'ALL';
    let levelId = data.level.id;
    let levelName = data.level.name;
    let institutionCategory = 'ALL';
    let institutionTypeId = 'ALL';
    let institutionType = data.institution;
    let institutionTypeName = 'ALL';
    let universityTypeName = data.universityType.type;
    let universityName = data.universityName.name;
    let universityType = data.universityType.id;
    let universityId = data.universityName.id;
    let collegeTypeName = 'ALL';
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-133?surveyYear=${surveyYear}&courseMode=${courseMode}&exportType=${exportType}&studyMode=${studyMode}&stateName=${stateName}&universityTypeName=${universityTypeName}&collegeTypeName=${collegeTypeName}&socialCategoryName=${socialCategoryName}&socialCategory=${socialCategory}&universityName=${universityName}&universityType=${universityType}&courseModeName=${courseModeName}&studyModeName=${studyModeName}&universityId=${universityId}&institutionTypeId=${institutionTypeId}&religiousCategoryName=${religiousCategoryName}&religiousCategory=${religiousCategory}&levelId=${levelId}&levelName=${levelName}&socialCategory=${socialCategory}&stateCode=${stateCode}&religiousCategory=${religiousCategory}&institutionType=${institutionType}&institutionTypeName=${institutionTypeName}&institutionCategory=${institutionCategory}&categoryType=${categoryType}`,
      httpOptions
    );
  }

  getReport134(data: any, obj: any) {
    let userId = this.localService.getData('userId');
    let stateCode = data.addressStateCode.stateCode;
    let stateName = data.addressStateCode.stateName;
    let institutionType = data.institution;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    let universityTypeName = data.universityType.type;
    let universityName = data.universityName.name;
    let universityType = data.universityType.id;
    let universityId = data.universityName.id;
    let institutionTypeId = '';
    let institutionTypeName = '';
    if(data.institution==='College'){
       institutionTypeId = data.collegeType.id;
       institutionTypeName = data.collegeType.type;
    }
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    let studyModeName =
      studyMode == '1' ? 'REGULAR' : studyMode == '2' ? 'DISTANCE' : 'ALL';
    let courseModeName =
      courseMode == '1'
        ? 'GENERAL'
        : courseMode == '2'
        ? 'SELF-FINANCING'
        : 'ALL';
    let socialCategory = data.socialCategory;
    let religiousCategory = data.religiousCategory;
    let socialCategoryName =
      socialCategory == '1'
        ? 'SC'
        : socialCategory == '2'
        ? 'ST'
        : socialCategory == '3'
        ? 'OBC'
        : 'ALL';
    let religiousCategoryName =
      religiousCategory == '1'
        ? 'Muslim'
        : religiousCategory == '2'
        ? 'OTHER MINORITY'
        : religiousCategory == '3'
        ? 'PWD'
        : 'ALL';
        
  if(data.institution ==='College'){
    return this.http.get(
      environment.baseUrl +
        `api/report-134?surveyYear=${surveyYear}&exportType=${exportType}&stateName=${stateName}&stateCode=${stateCode}&institutionType=${institutionType}&universityTypeName=${universityTypeName}&universityName=${universityName}&universityType=${universityType}&universityId=${universityId}&studyMode=${studyMode}&courseMode=${courseMode}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&socialCategory=${socialCategory}&religiousCategory=${religiousCategory}&socialCategoryName=${socialCategoryName}&religiousCategoryName=${religiousCategoryName}&institutionName=${institutionTypeName}&institutionTypeId=${institutionTypeId}`,
      httpOptions
    );
   
  }else{
    return this.http.get(
      environment.baseUrl +
        `api/report-134?surveyYear=${surveyYear}&exportType=${exportType}&stateName=${stateName}&stateCode=${stateCode}&institutionType=${institutionType}&universityTypeName=${universityTypeName}&universityName=${universityName}&universityType=${universityType}&universityId=${universityId}&studyMode=${studyMode}&courseMode=${courseMode}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&socialCategory=${socialCategory}&religiousCategory=${religiousCategory}&socialCategoryName=${socialCategoryName}&religiousCategoryName=${religiousCategoryName}`,
      httpOptions
    );
  }
  

  }

  getReport134A(data: any): Observable<any> {
    
    let userId = this.localService.getData('userId');
    let exportType = data.exportType;
    let stateCode = data.addressStateCode.stateCode;
    let stateName = data.addressStateCode.stateName;
    let surveyYear = data.surveyYear;
    let courseMode = data.courseMode;
   
  
    let courseModeName =
      courseMode == '1'
        ? 'GENERAL'
        : courseMode == '2'
        ? 'SELF-FINANCING'
        : 'ALL';
    
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineCategory=data.disciplineName.id;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let levelId = data.level.id;
    let universityType=data.universityType.id;
    let universityTypeName=data.universityType.type;
   {
      return this.http.get(
        environment.baseUrl +
          `api/report-134A?surveyYear=${surveyYear}&courseMode=${courseMode}&exportType=${exportType}&stateName=${stateName}&disciplineGroup=${disciplineGroup}&disciplineGroupName=${disciplineGroupName}&universityType=${universityType}&universityTypeName=${universityTypeName}&courseModeName=${courseModeName}&disciplineCategory=${disciplineCategory}&disciplineCategoryName=${disciplineCategoryName}&levelId=${levelId}&stateCode=${stateCode}`,
        httpOptions
      );
    }
  
  }

  getReport151(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let stateCode = data.addressStateCode.stateCode;
    let stateName = data.addressStateCode.stateName;
    let studyMode = data.studyMode;
    let institutionTypeId = '';
    let institutionTypeName = '';
    if(data.institution==='College'){
       institutionTypeId = data.collegeType.id;
       institutionTypeName = data.collegeType.type;
    }
    
    let studyModeName =
      studyMode == '1' ? 'REGULAR' : studyMode == '2' ? 'DISTANCE' : 'ALL';
    let categoryType = "ALL";
    let exportType = data.exportType;
    let surveyYear = data.surveyYear;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineName = data.disciplineName.id;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let institutionCategory= "ALL";
   
 
    if(data.institution==='College'){
      return this.http.get(
        environment.baseUrl +
          `api/report-151?surveyYear=${surveyYear}&disciplineCategoryName=${disciplineCategoryName}&exportType=${exportType}&studyMode=${studyMode}&stateName=${stateName}&disciplineGroupName=${disciplineGroupName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&stateCode=${stateCode}&categoryType=${institutionCategory}&institutionCategory=${institutionCategory}&institutionName=${institutionTypeName}&institutionTypeId=${institutionTypeId}`,
        httpOptions
      );
      }else{
      return this.http.get(
        environment.baseUrl +
          `api/report-151?surveyYear=${surveyYear}&disciplineCategoryName=${disciplineCategoryName}&exportType=${exportType}&studyMode=${studyMode}&stateName=${stateName}&disciplineGroupName=${disciplineGroupName}&disciplineGroup=${disciplineGroup}&institutionCategory=${categoryType}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&stateCode=${stateCode}&categoryType=${categoryType}`,
        httpOptions
      );
    }
    
  }

  getReport152(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let exportType = data.exportType;
    let stateCode = data.addressStateCode.stateCode;
    let stateName = data.addressStateCode.stateName;
    let surveyYear = data.surveyYear;
    let institutionCategory = "ALL";
    return this.http.get(
      environment.baseUrl +
        `api/report-152?surveyYear=${surveyYear}&institutionCategory=${institutionCategory}&stateName=${stateName}&stateCode=${stateCode}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport62(data: any): Observable<any> {
    
    var userId = this.localService.getData('userId');
    if(data.institution=='College Institution'){
      return this.http.get(
        environment.baseUrl +
          `api/report-62?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionManagementId=${data.managementType.id}&institutionParentId=${data.universityName.id}&institutionTypeId=${data.collegeType.id}&institutionCategory=${data.institution
          }&exportType=${data.exportType}`,
        httpOptions
      );
          }
          if(data.institution=='Standalone Institution'){
            return this.http.get(
              environment.baseUrl +
                `api/report-62?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionManagementId=${data.managementType.id}&institutionTypeId=${data.bodyType
                  .id}&institutionCategory=${data.institution
                }&exportType=${data.exportType}`,
              httpOptions
            );
          }
          if(data.institution=='University'){
            return this.http.get(
              environment.baseUrl +
                `api/report-62?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionTypeId=${data.universityType.id}&institutionCategory=${data.institution
                }&exportType=${data.exportType}`,
              httpOptions
            );
          }
    return this.http.get(
      environment.baseUrl +
        `api/report-62?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionCategory=${data.institution}&exportType=${data.exportType}`,
      httpOptions
    );
  }
  getReport63(data: any): Observable<any> {
    console.log(data);
    var userId = this.localService.getData('userId');
    if(data.institution=='College Institution'){
      return this.http.get(
        environment.baseUrl +
          `api/report-63?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionManagementId=${data.managementType.id}&institutionParentId=${data.universityName.id}&institutionTypeId=${data.collegeType.id}&institutionCategory=${data.institution
          }&exportType=${data.exportType}`,
        httpOptions
      );
          }
          if(data.institution=='Standalone Institution'){
            return this.http.get(
              environment.baseUrl +
                `api/report-63?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionManagementId=${data.managementType.id}&institutionTypeId=${data.bodyType
                  .id}&institutionCategory=${data.institution
                }&exportType=${data.exportType}`,
              httpOptions
            );
          }
          if(data.institution=='University'){
            return this.http.get(
              environment.baseUrl +
                `api/report-63?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionTypeId=${data.universityType.id}&institutionCategory=${data.institution
                }&exportType=${data.exportType}`,
              httpOptions
            );
          }
    return this.http.get(
      environment.baseUrl +
        `api/report-63?surveyYear=${data.surveyYear}&stateName=${data.addressStateCode.stateName}&stateCode=${data.addressStateCode.stateCode}&institutionCategory=${data.institution}&exportType=${data.exportType}`,
      httpOptions
    );
  }

  getReport59(data: any): Observable<any> {
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
     let universityId = data.universityName.id;
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let universityType = data.universityType.id?data.universityType.id:'ALL'
    let universityTypeName = data.universityType.type?data.universityType.type:'ALL'
    let stateCode = data.addressStateCode.stateCode;
    
    return this.http.get(
      environment.baseUrl +
        `api/report-59?stateName=${stateName}&universityType=${universityType}&universityId=${universityId}&universityTypeName=${universityTypeName}&stateCode=${stateCode}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport60(data: any): Observable<any> {
    let universityId = data.universityName.id?data.universityName.id:'ALL';
    let collegeInstitutionId = data.collegeName.id?data.collegeName.id.toString():'ALL';
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    
    let userId = this.localService.getData('userId');
    let stateCode = data.addressStateCode.stateCode;
    let typeId = 'ALL';

    return this.http.get(
      environment.baseUrl +
        `api/report-60?surveyYear=${surveyYear}&stateCode=${stateCode}&universityId=${universityId}&typeId=${typeId}&collegeInstitutionId=${collegeInstitutionId}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport61(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let standaloneInstitutionId = 'ALL';
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    let stateCode = data.addressStateCode.stateCode;
    let stateName = data.addressStateCode.stateName;
    let stateBodyId = data.bodyType.id?data.bodyType.id:"ALL";
    let institutionTypeId = 'ALL';
    return this.http.get(
      environment.baseUrl +
        `api/report-61?surveyYear=${surveyYear}&stateCode=${stateCode}&stateName=${stateName}&institutionTypeId=${institutionTypeId}&stateBodyId=${stateBodyId}&standaloneInstitutionId=${standaloneInstitutionId}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport96(data: any): Observable<any> {
   // let institutionCategory = data.institution?data.institution: 'ALL'; // all
   let stateBodyId = data.bodyType.id//standalone
   let institutionId =data.collegeName.id !== undefined?data.collegeName.id : data.universityName.id !== undefined? data.universityName.id:'ALL'  //all
   let universityType =data.universityType.id; //college and univee
   let universityTypeName = data.universityType.type; //college and univee
    let institutionType = data.institution.split('Institution')[0].trim()
   //let institutionCategory = data.institution=='College Institution'?data.institution :data.institution =='Standalone Institution'?data.institution :data.institution =='University'?data.institution:'ALL' //college
    let institutionParentId = data.universityName.id //college;
    let institutionTypeId = data.institution =='University'?data.universityType.id  :data.institution =='College Institution'?data.universityType.id: data.institution=='Standalone Institution'?data.bodyType.id:'ALL';
    let institutionManagementId = data.managementName.id // cokeege or standalone;
    let exportType = data.exportType;
    let userId = this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let stateCode = data.addressStateCode.stateCode;
    let stateName = data.addressStateCode.stateName;
    let institutionTypeName = data.institution=='Standalone Institution'?data.bodyType.type:data.institution=='College Institution'?data.collegeType.type: 'ALL';
    let url;
    if(data.institution=='College Institution'){
    url = `api/report-96?surveyYear=${surveyYear}&stateCode=${stateCode}&stateName=${stateName}&institutionId=${institutionId}&institutionType=${institutionType}&institutionTypeName=${institutionTypeName}&universityType=${universityType}&institutionParentId=${institutionParentId}&institutionManagementId=${institutionManagementId}&exportType=${exportType}`
    }else if( data.institution== 'University'){
       url = `api/report-96?surveyYear=${surveyYear}&stateCode=${stateCode}&universityTypeName=${universityTypeName}&stateName=${stateName}&institutionTypeName=${institutionTypeName}&institutionTypeId=${institutionTypeId}&universityType=${universityType}&institutionType=${institutionType}&institutionId=${institutionId}&exportType=${exportType}`
    }else{
      url = `api/report-96?surveyYear=${surveyYear}&stateCode=${stateCode}&universityTypeName=${universityTypeName}&institutionTypeName=${institutionTypeName}&stateName=${stateName}&institutionTypeId=${institutionTypeId}&institutionType=${institutionType}&institutionId=${institutionId}&universityType=${universityType}&exportType=${exportType}`
    }
   
   return this.http.get(
      environment.baseUrl +
        url,
      httpOptions
    );
  }

  getReport161(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-161?surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport162(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-162?surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport163(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-163?surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport91(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let roleId = this.localService.getData('roleId');
    let institutionType = data.institution;
    let universityId="ALL";  
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    let startDate=data.datePickerStart;
    let endDate=data.datePickerEnd
    return this.http.get(
      environment.baseUrl +
        `api/report-91?surveyYear=${surveyYear}&roleId=${roleId}&institutionType=${institutionType}&startDate=${startDate}&endDate=${endDate}&universityId=${universityId}&stateName=${stateName}&stateCode=${stateCode}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport91A(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let roleId = this.localService.getData('roleId');
    let institutionType = data.institution;
    let universityId="ALL";  
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    let startDate=data.datePickerStart;
    let endDate=data.datePickerEnd
    return this.http.get(
      environment.baseUrl +
        `api/report-91A?surveyYear=${surveyYear}&roleId=${roleId}&institutionType=${institutionType}&startDate=${startDate}&endDate=${endDate}&universityId=${universityId}&stateName=${stateName}&stateCode=${stateCode}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport97(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let countryId = '1';
    let countryName = 'INDIA';
    let districtCode = data.districtsName.districtCode==undefined? 'ALL':data.districtsName.districtCode;
    let districtName = data.districtsName.districtName==undefined? 'ALL':data.districtsName.districtName;
    let typeId = 'ALL';
    let typeIdName = 'ALL';
    let stateName = data.addressStateCode.stateName==undefined ? 'ALL':data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode==undefined ? 'ALL':data.addressStateCode.stateCode;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-97?surveyYear=${surveyYear}&stateName=${stateName}&stateCode=${stateCode}&countryId=${countryId}&countryName=${countryName}&districtCode=${districtCode}&districtName=${districtName}&typeId=${typeId}&typeIdName=${typeIdName}&exportType=${exportType}`,
      httpOptions
    );
  }
  getReport156(data: any): Observable<any> {
    
    let userId = this.localService.getData('userId');
    let managementIdName = data.managementType.managementType==='null'?'ALL':data.managementType.managementType;
    let managementId = data.managementType.id?data.managementType.id:'ALL';
    let collegeInstitutionTypeId = data.collegeType.id?data.collegeType.id:'ALL';
    let collegeTypeName = data.collegeType.type?data.collegeType.type:'ALL';
    let modificationType = 'ALL';
    let modificationTypeName = 'ALL';
    let stateName = data.addressStateCode.stateName?data.addressStateCode.stateName:'ALL';
    let stateCode = data.addressStateCode.stateCode?data.addressStateCode.stateCode:'ALL';
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    var institutionType  = data.institution
    if(data.institution == 'University'){
    var universityType = data.universityType.id;
    var universityTypeName = data.universityType.type;
    }
    if(data.institution == 'Standalone'){
      var bodyType = data.bodyType.id
      var bodyTypeName = data.bodyType.type
    }
    var url;
  
    if(data.institution == 'College'){
     url =  `api/report-156?surveyYear=${surveyYear}&stateName=${stateName}&institutionType=${institutionType}&stateCode=${stateCode}&managementId=${managementId}&collegeInstitutionTypeId=${collegeInstitutionTypeId}&managementIdName=${managementIdName}&exportType=${exportType}&collegeTypeName=${collegeTypeName}&modificationType=${modificationType}&modificationTypeName=${modificationTypeName}`
    }else if( data.institution == 'University'){
      url =  `api/report-156?surveyYear=${surveyYear}&stateName=${stateName}&institutionType=${institutionType}&stateCode=${stateCode}&exportType=${exportType}&universityType=${universityType}&universityTypeName=${universityTypeName}`
    }else if(data.institution == 'Standalone'){
      url =  `api/report-156?surveyYear=${surveyYear}&stateName=${stateName}&institutionType=${institutionType}&stateCode=${stateCode}&managementId=${managementId}&managementIdName=${managementIdName}&exportType=${exportType}&modificationType=${modificationType}&modificationTypeName=${modificationTypeName}&stateBodyId=${bodyType}&stateBodyName=${bodyTypeName}`      
    }

    return this.http.get(
      environment.baseUrl +
      url ,
      httpOptions
    );
  }

  getReport167(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let districtCode = data.districtsName.districtCode? data.districtsName.districtCode:'ALL';
  let stateCode = data.addressStateCode.stateCode?data.addressStateCode.stateCode:'ALL';
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-167?surveyYear=${surveyYear}&stateCode=${stateCode}&districtCode=${districtCode}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport144(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let courseMode = userData.courseMode;
    var courseModeName = courseMode == '1' ? 'GENERAL' : courseMode == '2' ? 'SELF-FINANCING' : 'ALL';
    let studyMode = userData.studyMode;
     var studyModeName =
      studyMode == '1' ? 'REGULAR' : studyMode == '2' ? 'DISTANCE' : 'ALL';
      
    if(userData.institution=="College"){
      return this.http.get(
        environment.baseUrl +
          `api/report-144?courseMode=${ userData.courseMode}&studyMode=${ userData.studyMode}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&universityTypeName=${userData.universityType.type}&universityName=${userData.universityName.name}&universityType=${userData.universityType.id}&universityId=${userData.universityName.id}&collegeTypeName=${userData.collegeType.type}&collegeId=${userData.collegeType.id}&managementTypeName=${userData.managementType.managementType}&managementTypeId=${userData.managementType.id}&surveyYear=${userData.surveyYear}&exportType=${userData.exportType}&institutionType=${userData.institution}&institutionTypeName=${userData.institution}&institutionManagementId=${userData.managementType.id}`,
        httpOptions
      )
    }
    if(userData.institution=="Standalone"){
      return this.http.get(
        environment.baseUrl +
          `api/report-144?courseMode=${ userData.courseMode}&studyMode=${ userData.studyMode}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&managementTypeName=${userData.managementType.managementType}&managementTypeId=${userData.managementType.id}&stateBodyId=${userData.bodyType.id}&surveyYear=${userData.surveyYear}&exportType=${userData.exportType}&institutionType=${userData.institution}&institutionTypeName=${userData.institution}&stateBodyName=${userData.bodyType.type}&institutionManagementId=${userData.managementType.id}`,
        httpOptions
      )
    }
    if(userData.institution=="University"){
      return this.http.get(
        environment.baseUrl +
          `api/report-144?courseMode=${ userData.courseMode}&studyMode=${ userData.studyMode}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&universityTypeName=${userData.universityType.type}&universityType=${userData.universityType.id}&surveyYear=${userData.surveyYear}&exportType=${userData.exportType}&institutionType=${userData.institution}&institutionTypeName=${userData.institution}`,
        httpOptions
      )
    }
    return this.http.get(
      environment.baseUrl +
        `api/report-144?surveyYear=${userData.surveyYear}&courseMode=${courseMode}&courseModeName=${courseModeName}&exportType=${userData.exportType}&studyMode=${studyMode}&studyModeName=${studyModeName}&institutionType=${userData.institution}`,
      httpOptions
    );
  }

  getReport66(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let collegeType = userData.collegeType.id;
    let universityName = userData.universityName.id;
    let universityType = userData.universityType.id;
    let StateCode = userData.addressStateCode.stateCode;
    return this.http.get(
      environment.baseUrl +
        `report66?collegeType=${collegeType}&reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityName=${universityName}&universityType=${universityType}`,
      httpOptions
    );
  }
  getReport148(data: any,file:any): Observable<any> {
    let stateName=data.addressStateCode.stateName?data.addressStateCode.stateName:'ALL';
    let stateCode=data.addressStateCode.stateCode?data.addressStateCode.stateCode:'ALL';
    return this.http.get(
      environment.baseUrl +
        `api/report-148?stateName=${stateName}&startDate=${data.datePickerStart}&endDate=${data.datePickerEnd}&statusId=${data.status}&stateCode=${stateCode}&exportType=${file}&institutionType=College`,
      httpOptions
    );
  }
  getReport149(data: any,file:any): Observable<any> {
    let stateName=data.addressStateCode.stateName?data.addressStateCode.stateName:'ALL';
    let stateCode=data.addressStateCode.stateCode?data.addressStateCode.stateCode:'ALL';
    return this.http.get(
      environment.baseUrl +
        `api/report-149?stateName=${stateName}&startDate=${data.datePickerStart}&endDate=${data.datePickerEnd}&statusId=${data.status}&stateCode=${stateCode}&exportType=${file}&institutionType=Standalone`,
      httpOptions
    );
  }
  getReport164(data: any,file:any) {
    let stateName=data.addressStateCode.stateName?data.addressStateCode.stateName:'ALL';
    let stateCode=data.addressStateCode.stateCode?data.addressStateCode.stateCode:'ALL';
    let districtCode=data.districtsName.districtCode?data.districtsName.districtCode:'ALL';
    let districtName=data.districtsName.districtName?data.districtsName.districtName:'ALL';
    return this.http.get(environment.baseUrl +`api/report-164?surveyYear=${data.surveyYear}&stateName=${stateName}&stateCode=${stateCode}&districtCode=${districtCode}&districtName=${districtName}&exportType=${file}`,httpOptions);
  }
  getReport154(data: any,file:any) {
    let stateName=data.addressStateCode.stateName?data.addressStateCode.stateName:'ALL';
    let stateCode=data.addressStateCode.stateCode?data.addressStateCode.stateCode:'ALL';
    let districtCode=data.districtsName.districtCode?data.districtsName.districtCode:'ALL';
    let districtName=data.districtsName.districtName?data.districtsName.districtName:'ALL';
   
    return this.http.get(environment.baseUrl +`api/report-154?surveyYear=${data.surveyYear}&stateName=${stateName}&stateCode=${stateCode}&districtCode=${districtCode}&districtName=${districtName}&exportType=${file}`,httpOptions);
  }

  getReport155(data:any){
   
    let reportType = data.reportType;
    let surveyYear = data.surveyYear;
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let universityTypeName=data.universityType.type;
    let universityType=data.universityType.id;

        return this.http.get(environment.baseUrl +`api/report-155?stateName=${stateName}&stateCode=${stateCode}&modificationTypeName=ALL&modificationType=ALL&universityTypeName=${universityTypeName}&universityType=${universityType}&surveyYear=${surveyYear}&exportType=${reportType}`,httpOptions);
  }
  getReport141(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let collegeType = userData.collegeType.id;

    let universityName = userData.universityName.id;
    let universityType = userData.universityType.id;
    let StateCode = userData.addressStateCode.stateCode;
    return this.http.get(
      environment.baseUrl +
        `report141?reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityType=${universityType}`,
      httpOptions
    );
  }
  getReport120(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let disciplineGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
   // let studyMode = userData.studyMode;
    let levelId = userData.level.id;
    let reportType = userData.reportType;
    let stateCode = userData.addressStateCode.stateCode;
    let surveyYear = userData.surveyYear;
    var studyMode=userData.studyMode;
   // var courseMode=userData.courseMode=='GENERAL' ? '1':userData.courseMode=='SELF-FINANCING'?'2':'ALL';
    return this.http.get(
      environment.baseUrl +
        `report120?disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&levelId=${levelId}&reportType=${reportType}&stateCode=${stateCode}&studyMode=${studyMode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getReport146(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let bodyType = userData.bodyType.id;
    let StateCode = userData.addressStateCode.stateCode;

    return this.http.get(
      environment.baseUrl +
        `report146?reportType=${reportType}&stateBodyType=${bodyType}&stateCode=${StateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getReport121(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let studyMode = userData.studyMode?userData.studyMode:'ALL';
    let StateCode = userData.addressStateCode.stateCode;

    return this.http.get(
      environment.baseUrl +
        `report121?reportType=${reportType}&studyMode=${studyMode}&stateCode=${StateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getReport121A(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let studyMode = userData.studyMode?userData.studyMode:'ALL';
    let StateCode = userData.addressStateCode.stateCode;
    let universityId = userData.universityName.id;
    let universityTypeId = userData.universityType.id;
    return this.http.get(
      environment.baseUrl +
        `report121A?reportType=${reportType}&studyMode=${studyMode}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityId=${universityId}&universityType=${universityTypeId}`,
      httpOptions
    );
  }
  getReport121B(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let collegeId = userData.collegeName.id;
    let collegeType = userData.collegeType.id;
    let StateCode = userData.addressStateCode.stateCode;
    let universityId = userData.universityName.id;
    let universityTypeId = userData.universityType.id;
    let managementType = userData.managementType.id;
    return this.http.get(
      environment.baseUrl +
        `report121B?reportType=${reportType}&managementId=${managementType}&collegeInstId=${collegeId}&collegeInstTypeId=${collegeType}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityId=${universityId}&universityType=${universityTypeId}`,
      httpOptions
    );
  }

  getReport121C(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let bodyType = userData.bodyType.id;
    let managementType = userData.managementType.id;
    let studyMode = userData.studyMode?userData.studyMode:'ALL';
    let instId = userData.institution.id?userData.institution.id:'ALL';

    return this.http.get(
      environment.baseUrl +
        `report121C?managementId=${managementType}&reportType=${reportType}&standAloneInstId=${instId}&stateBodyId=${bodyType}&stateCode=${StateCode}&studyMode=${studyMode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReport126(userData: any): Observable<any> {
    
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    var studyMode=userData.studyMode;
    var courseMode=userData.courseMode;
    let disciplineGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let levelId = userData.level.id;
    let religiousCat = userData.religiousCategory;
    let categoryType = userData.socialCategory;

    return this.http.get(
      environment.baseUrl +
        `report126?boardDisciplineGrpCatId=${disciplineGroup}&boardDisciplineGrpName=${disciplineName}&categoryType=${categoryType}&courseModeId=${courseMode}&levelId=${levelId}&modeId=${studyMode}&religsCat=${religiousCat}&reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getReport126A(userData: any): Observable<any> {
    
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    var studyMode=userData.studyMode;
    var courseMode=userData.courseMode;
    let religiousCat = userData.religiousCategory;
    let categoryType = userData.socialCategory;
    let disciplineGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let levelId = userData.level.id;
    let universityId = userData.universityName.id;
    let universityTypeId = userData.universityType.id;

    return this.http.get(
      environment.baseUrl +
        `report126A?courseMode=${courseMode}&disciplineGrpCatId=${disciplineGroup}&disciplineName=${disciplineName}&reportType=${reportType}&stateCode=${StateCode}&studyMode=${studyMode}&surveyYear=${surveyYear}&universityId=${universityId}&universityType=${universityTypeId}&religiousCat=${religiousCat}&socialCat=${categoryType}&levelId=${levelId}`,
      httpOptions
    );
  }

  getReport126B(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let courseMode = userData.courseMode;
    let religiousCat = userData.religiousCategory;
    let categoryType = userData.socialCategory;
    let disciplineGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let levelId = userData.level.id;
    let universityId = userData.universityName.id;
    let universityTypeId = userData.universityType.id;
    let managementType = userData.managementType.id;
    let collegeId = userData.collegeName.id;
    let collegeType = userData.collegeType.id;
    let studyMode = userData.studyMode?userData.studyMode:"ALL";

    return this.http.get(
      environment.baseUrl +
        `report126B?collegeInstId=${collegeId}&collegeInstTypeId=${collegeType}&courseMode=${courseMode}&disciplineGrpCatId=${disciplineGroup}&disciplineName=${disciplineName}&levelId=${levelId}&managementId=${managementType}&religousCat=${religiousCat}&reportType=${reportType}&socialCat=${categoryType}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityId=${universityId}&universityType=${universityTypeId}&studyMode=${studyMode}`,
      httpOptions
    );
  }

  getReport126C(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let bodyType = userData.bodyType.id;
    let instId = userData.institution.id;
    let managementType = userData.managementType.id;
    let courseMode = userData.courseMode;
    let religiousCat = userData.religiousCategory;
    let categoryType = userData.socialCategory;
    let disciplineGroup = userData.disciplineGroup.id;
    let disciplineName = userData.disciplineName.id;
    let studyMode = userData.studyMode;
    let levelId = userData.level.id;
    let standAloneInstId="ALL"

    return this.http.get(
      environment.baseUrl +
        `report126C?&courseMode=${courseMode}&disciplineGrpCatId=${disciplineGroup}&disciplineName=${disciplineName}&levelId=${levelId}&religiousCat=${religiousCat}&socialCat=${categoryType}&standAloneInstId=${standAloneInstId}&stateBodyId=${bodyType}&stateCode=${StateCode}&studyMode=${studyMode}&surveyYear=${surveyYear}&reportType=${reportType}&managementId=${managementType}`,
      httpOptions
    );
  }

  getReport56(userData: any): Observable<any> {
    
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode?userData.addressStateCode.stateCode:'ALL';
    let universityId = userData.universityName.id?userData.universityName.id:"ALL";

    return this.http.get(
      environment.baseUrl +
        `report56?reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityName=${universityId}`,
      httpOptions
    );
  }
  getReport57(userData: any): Observable<any> {
    console.log('ailjdxie',userData);
       let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode==null?"ALL":userData.addressStateCode.stateCode?userData.addressStateCode.stateCode:'ALL';
    var collegeId = userData.collegeName==null?"ALL":userData.collegeName.id?userData.collegeName.id:'ALL';
    var universityId =userData.universityName===null?"ALL": userData.universityName.id?userData.universityName.id:"ALL";
    return this.http.get(
      environment.baseUrl +
        `report57?collegeName=${collegeId}&reportType=${reportType}&surveyYear=${surveyYear}&univesityName=${universityId}&stateCode=${StateCode}`,
      httpOptions
    );
  }
  getReport58(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode?userData.addressStateCode.stateCode:'ALL';
    let institution = userData.institutionName==null?"ALL":userData.institutionName.id? userData.institutionName.id:'ALL';
    let bodyType = userData.bodyType.id?userData.bodyType.id:'ALL';
    return this.http.get(
      environment.baseUrl +
        `report58?bodyType=${bodyType}&institutionName=${institution}&reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getReport111(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode?userData.addressStateCode.stateCode:'ALL';
    return this.http.get(
      environment.baseUrl +
        `report111?reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }
  getReport111A(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let universityTypeId = userData.universityType.id;
    let affiliated = userData.affiliated;

    return this.http.get(
      environment.baseUrl +
        `report111A?affiliated=${affiliated}&reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityType=${universityTypeId}`,
      httpOptions
    );
  }
  getReport111B(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let managementType = userData.managementType.id;
    let universityTypeId = userData.universityType.id;
    let universityId = userData.universityName.id;
    let collegeType = userData.collegeType.id;

    return this.http.get(
      environment.baseUrl +
        `report111B?collegeType=${collegeType}&mngtType=${managementType}&reportType=${reportType}&stateCode=${StateCode}&surveyYear=${surveyYear}&universityName=${universityId}&universityType=${universityTypeId}`,
      httpOptions
    );
  }
  getReport111C(userData: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let reportType = userData.reportType;
    let surveyYear = userData.surveyYear;
    let StateCode = userData.addressStateCode.stateCode;
    let managementType = userData.managementType.id;
    let bodyType = userData.bodyType.id;

    return this.http.get(
      environment.baseUrl +
        `report111C?mngtType=${managementType}&reportType=${reportType}&stateBodyId=${bodyType}&stateCode=${StateCode}&surveyYear=${surveyYear}`,
      httpOptions
    );
  }

  getReport131(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let collegeType = data.collegeType.id?data.collegeType.id:'ALL';
    let bodyType = data.bodyType.id?data.bodyType.id:'ALL';
    let universityTypeCode = data.universityType.id?data.universityType.id:'ALL';
    let universityId =data.universityName.id?data.universityName.id:'ALL';
    let managementType = data.managementType.id?data.managementType.id:'ALL';
    let institution = data.institution=='College'?'College Institution':data.institution=='Standalone'?'Standalone Institution':data.institution=='University'?'University':'ALL';
var constFromFlag=data.affiliated

return this.http.get(
  environment.baseUrl +
    `report131?constFromFlag=${constFromFlag}&instCat=${institution}&instMgmtId=${managementType}&instId=ALL&stateBodyId=${bodyType}&instTypeId=${collegeType}&reportType=${data.reportType}&stateCode=${data.addressStateCode.stateCode}&surveyYear=${data.surveyYear}&universityId=${universityId}&universityType=${universityTypeCode}`,
  httpOptions
);
  }

  getReport100(data: any, obj: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let stateName = data.addressStateCode.stateName;
    let stateCode = data.addressStateCode.stateCode;
    let studyMode = data.studyMode;
    let courseMode = data.courseMode;
    let courseModeName =
      obj?.courseModeName !== undefined ? obj.courseModeName : 'ALL';
    let studyModeName =
      obj?.studyModeName !== undefined ? obj.studyModeName : 'ALL';
    let religiousCategoryName =
      obj?.religiousCategory !== undefined ? obj.religiousCategory : 'ALL';
    let socialCategoryName =
      obj?.socialCategory !== undefined ? obj.socialCategory : 'ALL';
    let disciplineGroupName = data.disciplineGroup.disciplinGrouCategory;
    let disciplineGroup = data.disciplineGroup.id;
    let disciplineName = data.disciplineName.id;
    let disciplineCategoryName = data.disciplineName.disciplineGroup;
    let exportType = data.exportType;
    return this.http.get(
      environment.baseUrl +
        `api/report-100?stateCode=${stateCode}&stateName=${stateName}&religiousCategoryName=${religiousCategoryName}&studyMode=${studyMode}&courseMode=${courseMode}&socialCategoryName=${socialCategoryName}&disciplineGroup=${disciplineGroup}&disciplineName=${disciplineName}&studyModeName=${studyModeName}&courseModeName=${courseModeName}&disciplineGroupName=${disciplineGroupName}&disciplineCategoryName=${disciplineCategoryName}&surveyYear=${data.surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }

  getReport122(data: any): Observable<any> {
    let userId = this.localService.getData('userId');
    let groupId = data.staffGroup.id;
    let groupName = data.staffGroup.id;
    let staffTypeId = data.staffType.id;
    let staffType = data.staffType.id;
    let surveyYear = data.surveyYear;
    let exportType = data.exportType;

    return this.http.get(
      environment.baseUrl +
        `api/report-122?groupId=${groupId}&groupName=${groupName}&staffTypeId=${staffTypeId}&staffType=${staffType}&surveyYear=${surveyYear}&exportType=${exportType}`,
      httpOptions
    );
  }
    //Zip file Downloads
    

    getUnitLevelReportZip(surveyYear: String,stateCode:any) {  
      window.open(`${environment.baseUrl}zipDownload/download?surveyYear=${surveyYear}`)   

      // window.open(`${environment.baseUrl}zipDownload/download?surveyYear=${surveyYear}&stateCode=${stateCode}`)   
      // return this.http.get(`${environment.baseUrl}zipDownload/download?surveyYear=${surveyYear}`);
     
    }

    getPooledDataReportZip(surveyYear: String) {  
      window.open(`${environment.baseUrl}zipDownload/pooledDataDownloade?surveyYear=${surveyYear}`)      
    }

    universityComparisonDataZip(surveyYear:any): Observable<any>{
      return this.http.get(
        environment.baseUrl +`api/comparison-report?surveyYear=${surveyYear}&institutionType=University&exportType=EXCEL`,
        httpOptions
      );   
    }
    collegeComparisonDataZip(surveyYear:any): Observable<any>{

      return this.http.get(
        environment.baseUrl +`api/comparison-report?surveyYear=${surveyYear}&institutionType=College&exportType=EXCEL`,
        httpOptions
      ); 
         
    }
    standaloneComparisonDataZip(surveyYear:any): Observable<any>{
      return this.http.get(
        environment.baseUrl +`api/comparison-report?surveyYear=${surveyYear}&institutionType=Standalone&exportType=EXCEL`,
        httpOptions
      ); 
      // window.open(`${environment.baseUrl}api/comparison-report?surveyYear=${surveyYear}&institutionType=Standalone&exportType=EXCEL`)     
    }
    getDcfReportZip(type: any, surveyYear: any) {
      window.open(`${environment.baseUrl}zipDownload/dcfData?type=${type}&surveyYear=${surveyYear}`)
      // const url = `${environment.baseUrl}/zipDownload/dcfData?type=${type}&surveyYear=${surveyYear}`;
      // this.http.get(url, { responseType: 'blob' }).subscribe(
      //   (blob) => {
      //     const downloadUrl = window.URL.createObjectURL(blob);
      //     window.open(downloadUrl);
      //     window.URL.revokeObjectURL(downloadUrl);
      //   },
      //   (error) => {
      //     console.error('Error fetching the ZIP file:', error);
      //   }
      // );
    }
    
    getKnowYourKycZip(type:any, surveyYear:any){
      window.open(`${environment.baseUrl}zipDownload/KnowYourInstitute?type=${type}&surveyYear=${surveyYear}`)
    }

    scholarshipScheme(data: any,stateCode:any): Observable<any> {
      return this.http.get(environment.masterUrl + `/api/scholarship-scheme?stateCode=${stateCode}&surveyYear=${data.surveyYear}`,httpOptions)
    }

    scholarshipSchemePrivate(data: any,stateCode:any): Observable<any> {
      return this.http.get(environment.masterUrl + `/api/scholarship-scheme-private?stateCode=${stateCode}&surveyYear=${data.surveyYear}`,httpOptions)
    }
    downloadFile(data:any): Observable<any> {
      return this.http.get(environment.baseUrlAishe + `webdcf-log-reports?action=${data.logReport}&orderBy=${data.orderBy}&surveyYear=${data.surveyYear}&stateCode=${data.stateCode}&instituteType=${data.instituteType}`, {
        responseType: 'blob', // Specify the response type as a blob
        observe: 'response', // Get the full HTTP response for headers
      });
    }




  getInstituteDataDemo(payload:any): Observable<any> {
    let endPoint = ''
    if(payload.loginMode === 'C'){
      endPoint = 'college/basicinfo'
    }else if(payload.loginMode === 'S'){
      endPoint = 'standalone/basicinfo'
    } else{
      endPoint = 'university/basicinfo'
    }
    return this.http.get<any>(environment.baseUrlGetApi1 + `/api/${endPoint}?aisheCode=${payload.aisheCode}&currentSurveyYear=${payload.currentSurveyYear}&latestSurveyYear=${payload.latestSurveyYear}`)

  }


  getStrength(payload:any): Observable<any> {
    return this.http.get<any>(`${environment.baseUrlGetApi1}/teaching-staff-sanctioned-strength-webdcf?aisheCode=${payload.aisheCode}&currentSurveyYear=${payload.currentSurveyYear}&instituteType=${payload.instituteType}`)
  }


  getTeashingStaff(payload:any){
    return this.http.get<any>(`${environment.baseUrlGetApi1}/api/teacher-info-webdcf`,{params:payload})

  }
  



  getAffliatedData(payload:any): Observable<any> {
    return this.http.get(`${environment.baseUrlGetApi1}/universityaffiliatedspecialityinstitutioncounteo?aisheCode=${payload.aisheCode}&currentSurveyYear=${payload.currentSurveyYear}&latestSurveyYear=${payload.latestSurveyYear}`)
  }

  getOfficerList(payload:any): Observable<any> {
    return this.http.get(`${environment.baseUrlGetApi1}/api/persondetailswebdcf?instituteType=${payload.instituteType}&aisheCode=${payload.aisheCode}&latestSurveyYear=${payload.latestSurveyYear}&currentSurveyYear=${payload.currentSurveyYear}`)
  }


  getInfraStructure(payload:any) {
    return this.http.get<any>(`${environment.baseUrlGetApi1}/api/infrastructure?aisheCode=${payload.aisheCode}&latestSurveyYear=${payload.latestSurveyYear}&currentSurveyYear=${payload.currentSurveyYear}`);
  }

  getInstitutionBankDetails(payload:any): Observable<any> {
    return this.http.get(`${environment.baseUrlGetApi1}/institutebankaccount`, { params: payload })
  }


  getBroadDisNameS(id:any){
    return this.http.get<any>(`${environment.masterUrl}/broad-name/categ/${id}`)
  }
  getNIRFCategory(){
    return this.http.get<any>(`${environment.masterUrl}/api/ref-nirf-discipline-category`)
  }
  
  
  getNIRFCategoryPrivate(){
    return this.http.get<any>(`${environment.masterUrl}/api/ref-nirf-discipline-category-private`)
  }


  getSimilarInsitute(payload:any){
    return this.http.get<any>(`${environment.baseUrlGetApi1}/similar-institute-under-state?requestId=${payload.requestId}&underState=${payload.underState}`)
  }








  


}
