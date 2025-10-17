// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  downloadReportUrl:'https://demo.he.nic.in/aishe/',
  management:'https://pdf.aishe.nic.in/aishemasterservice',
  baseUrl: 'https://api1.he.nic.in/aishereports/',
  authLogin: 'https://api1.he.nic.in/auth/login',
  baseUrlCaptcha:'https://api1.he.nic.in/usermanagement/',
  otpURL: 'https://demo.he.nic.in/aisheusermanagementdemo/',
  masterUrl: `https://api1.he.nic.in/aishemasterservice`,
  baseUrlAishe: `https://api1.he.nic.in/usermanagement/`,
  //baseUrlAishe: `http://10.206.194.250:8995/usermanagement/`,
  dcfURL: 'http://localhost:4300/#/form/',
  instituMURL: 'https://api1.he.nic.in/aisheinstitutemanagement',
  aisheHome: 'https://demo.he.nic.in/aishehtml',
  instituDirectoryURL: 'https://api1.he.nic.in/aishereports/',
  programmeUrl: 'https://api1.he.nic.in/aishehibernatepostapi',
  pdf: 'https://api1.he.nic.in/aishepdf',
  baseUrlGetApi1: 'https://api1.he.nic.in/aishehibernategetapi',
  baseUrlGetApi2: 'https://api1.he.nic.in/aishejpagetapi',
  baseUrlPostApi2:'https://api1.he.nic.in/aishejpapostapi',
  baseUrlDetails:'https://api.aishe.gov.in',
  forgotComponent:'https://demo.he.nic.in/aishenew/#/datauserlogin/resetPassword/',
   baseURLRequest:'https://demo.he.nic.in/aisheusermanagementdemo/',
//  baseURLRequest:'http://10.206.194.253:8895/aisheusermanagementdemo/',
  // http://localhost:8895/aisheusermanagementdemo
  baseURLLATLONG:'https://ministry.pmgatishakti.gov.in/lat-lon-api/auth/token',
  baseURLLATLONG1:'https://ministry.pmgatishakti.gov.in/lat-lon-api/',
  aisheMain:'https://demo.he.nic.in/aishehtml',
  //baseUrlPostApi2:'http://10.206.194.250:8097/aishejpapostapi',
  //baseUrlGetApi2: 'http://10.206.194.250:9094/aishejpagetapi',
   
  // baseUrlGetApi1: 'http://10.206.194.250:8092/aishehibernategetapi',

  // baseUrlGetApi2: 'https://api1.he.nic.in/aishejpagetapi',
  // baseUrlPostApi2:'https://api1.he.nic.in/aishejpapostapi',
  // instituMURL: 'http://10.206.194.250:8082/aisheinstitutemanagement',

// local microservices for development
  aisheMasterServiceURL: 'http://localhost:9770/aishemasterservice',
  userManagementServiceURL: 'http://localhost:8995/usermanagement'

}
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
