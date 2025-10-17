
import { Injectable } from "@angular/core";
import { SharedService, routes } from "./shared/shared.service";
@Injectable({
    providedIn:"root"
})
export class Menu {
    public routers: typeof routes = routes;

    constructor(public sharedService: SharedService) { }
    public menuList = [
        {
            name: 'Home',
            route: this.routers.Home,
            icon: 'fas fa-home',
            roles: [this.sharedService.role['Ministry of Education'], this.sharedService.role['SysAdmin'], this.sharedService.role['State Nodal Officer'],
            this.sharedService.role['National Council for Hotel Management and Catering Technology']],
            subMenu: []
        },
        {
            name: 'Institution Details',
            route: this.routers.Home,
            icon: 'fas fa-home',
            roles: [this.sharedService.role['College'], this.sharedService.role['University'], this.sharedService.role['Polytechnic'], this.sharedService.role['Nursing (Diploma) Institute'],
            this.sharedService.role['Teacher Training (Diploma) Institute'], this.sharedService.role['Standalone Institution Under Ministry'], this.sharedService.role['PGDM'], this.sharedService.role['Paramedical Institute'],
            this.sharedService.role['Hotel Management and Catering Institute']],
            subMenu: []
        },
        {
            name: 'Dashboard',
            route: this.routers.Dashboard,
            icon: 'fa fa-dashboard',
            roles: [this.sharedService.role['Ministry of Education'], this.sharedService.role['SysAdmin']],
            subMenu: []
        },
        {
            name: 'User Management',
            route: '',
            icon: 'fa-solid fa-users',
            roles: [],
            subMenu: [
                {
                    name: 'User Management',
                    role: []
                },
                {
                    name: 'New Registration',
                    role: [this.sharedService.role['Ministry of Education'], this.sharedService.role['SysAdmin']]
                }
            ]
        },
        {
            name: 'Web DCF',
            route: '',
            icon: 'fa-solid fa-users',
            roles: [],
            subMenu: [
                {
                    name: 'Fill Web-DCF',
                    role: []
                },
                {
                    name: 'Form Management',
                    role: [this.sharedService.role['Ministry of Education'], this.sharedService.role['SysAdmin']]
                },
                {
                    name: 'Download Document',
                    role: [this.sharedService.role['Ministry of Education'], this.sharedService.role['SysAdmin']]
                }
            ]
        },

    ]
    // public menuList = [

    //     { name: 'Home', route: this.routers.HOME, subMenu: [] },
    //     { name: 'About MoE', route: this.routers.ABOUTMOE, subMenu: [] },
    //     { name: 'Survey', route: '', subMenu: [
    //             { name: 'About Survey', route: this.routers.ABOUTSURVEY,subMenu1:[] },
    //             { name: 'Survey Guidelines ', route: '',subMenu1:[{name:'Danish',route:''}]},
    //             { name: 'Guidelines for Data User ', route: '',subMenu1:[] },
    //             { name: 'Nodal Officers for approval Institution ', route: '',subMenu1:[] },
    //             { name: 'Doubts and Clarification ', route: '',subMenu1:[] },
    //             { name: 'Important Instruction ', route: '',subMenu1:[] },
    //         ]
    //     },
    //     {
    //         name: 'Directory of Institutions', route: '',
    //         subMenu: [
    //             { name: 'University ', route: '' },
    //             { name: 'College ', route: '' },
    //             { name: 'Stantalone ', route: '' },

    //         ]
    //     },
    //     {
    //         name: 'Documents and Reports', route: '',
    //         subMenu: [
    //             { name: 'Reports ', route: '' },
    //             { name: 'Task Force and Committes ', route: '' },
    //             { name: 'Appeal to Institutions ', route: '' },

    //         ]
    //     },
    //     { name: 'State/UTs Nodal Officer', route: '' ,subMenu: []},
    //     { name: 'Contact Us', route: this.routers.CONTACT ,subMenu: []},

    // ]   
    // HOME="";
    // ABOUTMOE="";
    // SURVEY="";
    // ABOUTSURVEY="";
    // CONTACTUS="";
    // ABOUTCONTACTUS="";
    // SURVEYGUIDELINES="";
    // GUIDELINESDATAUSER="";
    // NODALOFFICERSAPPROVALINSTITUTION="";
    // DOUBTSCLARIFICATION="";
    // IMPORTANTINSTRUCTION="";
    // DIRECTORYINSTITUTIONS="";
    // UNIVERSITY="";
    // COLLEGE="";
    // STANDALONE="";
    // DOCUMENTSREPORTS="";
    // REPORTS="";
    // COLLEGE="";
    // COLLEGE="";
    // COLLEGE="";
    // COLLEGE="";
    // COLLEGE="";
    // COLLEGE="";
}
