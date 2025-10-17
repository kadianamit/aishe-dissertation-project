import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { timeStamp } from 'console';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';


@Component({
  selector: 'app-programme-management',
  templateUrl: './programme-management.component.html',
  styleUrls: ['./programme-management.component.scss']
})
export class ProgrammeManagementComponent implements OnInit {

  submitted = false;
  programmeForm: FormGroup;
  BDGCForm: FormGroup;
  BDGNForm: FormGroup;
  BDGMappingForm: FormGroup;
  BDGMappingSearchForm: FormGroup;
  programmeList: any[] = [];
  levelList: any[] = [];
  bdgcList: any[] = [];
  bdgcListFilter: any[] = [];
  programmeListFilter: any[] = [];
  bdgnList: any[] = [];
  bdgnListFilter: any[] = [];
  bdgMappingList: any[] = [];

  tempprogrammeList: any[] = [];
  tempbdgcList: any[] = [];
  tempbdgnList: any[] = [];
  tempbdgMappingList: any[] = [];
  StartLimit: number = 0;
  EndLimit: number = 25;
  pageData: number = 0;
  pageSize: number = 25;
  page: number = 1;
  selectedIndex: any = 0;

  pageSizeO: number = 15;
  pageSize1: number = 15;
  pageSize2: number = 15;
  StartLimitO: number = 0;
  EndLimitO: number = 15;
  StartLimit1: number = 0;
  EndLimit1: number = 15;
  StartLimit2: number = 0;
  EndLimit2: number = 15;
  pageO: number = 1;
  page1: number = 1;
  page2: number = 1;
  pageDataO: number = 0;
  pageData1: number = 0;
  pageData2: number = 0;
  total: number = 0;

  searchText: any;

  selectedLevelValue: any;
  programmelist: any;

  levelId: any;
  programmeId: any;
  broadDisciplineGroupCategoryId: any;
  broadDisciplineGroupId: any;
  programmeManagementList: any[] = [];
  isFormInvalid: boolean = false;
  isFormInvalid1: boolean = false;
  isFormInvalid2: boolean = false;
  isFormInvalid3: boolean = false;
  levellistFilter: any[] = [];
  programmeListArray: any[] = [];
  bdgcListArray: any[] = [];
  bdgnListArray: any[] = [];
  programmeListFilterArray: any[] = [];
  bdgcListFilterArray: any[] = [];
  bdgnListFilterArray: any[] = [];
  filter:any= {}
  tableHeaders:any;
  constructor(private dialog: MatDialog, public authService: AuthService, public sharedService: SharedService, public fb: FormBuilder,) {
    this.programmeForm = this.fb.group({
      level: ['', [Validators.required]],
      programme: ['', [Validators.required]],
      // year:['',[Validators.required]],
      // month:['',[Validators.required]]

    });

    this.BDGCForm = this.fb.group({
      category: ['', [Validators.required]]

    });

    this.BDGNForm = this.fb.group({
      disciplineGroup: ['', [Validators.required]]

    });

    this.BDGMappingForm = this.fb.group({
      levelId: ['', [Validators.required]],
      programmeId: ['', [Validators.required]],
      broadDisciplineGroupCategoryId: ['', [Validators.required]],
      broadDisciplineGroupId: ['', [Validators.required]]


    });

    this.BDGMappingSearchForm = this.fb.group({
      programmeId: ['', [Validators.required]],
      broadDisciplineGroupCategoryId: ['', [Validators.required]],
      broadDisciplineGroupId: ['', [Validators.required]]


    });

  }

  ngOnInit(): void {
    this.getLevel();


  }
  getProgramMap() {
    this.authService.getProgramme().subscribe((result: any) => {
      this.programmeListArray = result
      this.programmeList = this.programmeListArray.slice()
    }, err => {

    })
  }
  getBDGCMap() {
    this.authService.getBDGC().subscribe((result: any) => {
      this.bdgcListArray = result;
      this.bdgcList = this.bdgcListArray.slice()
    }, err => {

    })
  }
  getBDGNMap() {
    this.authService.getBDGN().subscribe((result: any) => {
      this.bdgnListArray = result;
      this.bdgnList = this.bdgnListArray.slice()
    }, err => {

    })
  }

  get f(): { [key: string]: AbstractControl } {
    return this.programmeForm.controls;
  }

  get g(): { [key: string]: AbstractControl } {
    return this.BDGMappingForm.controls;
  }

  get h(): { [key: string]: AbstractControl } {
    return this.BDGMappingSearchForm.controls;
  }
  tabSelected(event: any) {
    this.selectedIndex = event.index
    this.resetFilter();
    if (this.selectedIndex === 0) {
      this.getLevel();
    }
    if (this.selectedIndex === 1) {
      this.getProgramme();
    } if (this.selectedIndex === 2) {
      this.getBDGC()
    } if (this.selectedIndex === 3) {
      this.getBDGN()
    } if (this.selectedIndex === 4) {
      this.getBDGMapping();
      this.getProgramMap();
      this.getBDGCMap();
      this.getBDGNMap();
    }
  }
  resetFilter() {
    this.searchText = '';
    this.StartLimit = 0;
    this.EndLimit = 25;
    this.page = 1;
    this.pageData = 25;
    this.BDGCForm.reset();
    this.programmeForm.reset();
    this.BDGNForm.reset();
    this.programmeId = '';
    this.levelId = ''
    this.broadDisciplineGroupCategoryId = ''
    this.broadDisciplineGroupId = ''
  }
  onChange(event: any) {
    this.pageSize = parseInt(event);
    this.handlePageChange(this.page = 1)
  }
  handlePageChange(event: any) {
    this.page = event
    this.StartLimit = ((this.page - 1) * this.pageSize),
      this.EndLimit = this.StartLimit + this.pageSize
    var a = Math.ceil(this.programmeManagementList.length / this.pageSize);
    if (a === event) {
      this.pageData = Math.min(this.StartLimit + this.pageSize, this.programmeManagementList.length);
    } else {
      this.pageData = Math.min(this.StartLimit + this.pageSize, this.programmeManagementList.length - 1);
    }
  }
  getProgramme() {
    this.authService.getProgramme().subscribe((result: any) => {
      this.programmeManagementList = []
      this.programmelist = result
      this.programmeManagementList = result;
      this.programmeManagementList.forEach(ele => {
        ele['level'] = ele.courseLevel.level
      })

      this.tempprogrammeList = [...this.programmeManagementList]
    }, err => {

    })
  }

  getLevel() {
    this.authService.getLevel().subscribe((result: any) => {
      this.programmeManagementList = [];
      this.levellistFilter = result
      this.levelList = result;
      this.programmeManagementList = result;
      this.handlePageChange(this.page = 1)
    }, err => {

    })
  }


  getBDGC() {
    this.authService.getBDGC().subscribe((result: any) => {
      this.programmeManagementList = []
      this.programmeManagementList = result
      this.bdgcList = result;
      this.tempbdgcList = [...this.bdgcList]
      this.programmeManagementList = this.programmeManagementList.sort((a, b) => a.category > b.category ? 1 : -1);
      this.handlePageChange(this.page = 1)
    }, err => {

    })
  }
  getBDGN() {
    this.authService.getBDGN().subscribe((result: any) => {
      this.programmeManagementList = []
      this.programmeManagementList = result
      this.programmeManagementList = this.programmeManagementList.sort((a, b) => a.discipline > b.discipline ? 1 : -1);
      this.bdgnList = result;
      this.tempbdgnList = [...this.bdgnList]
      this.handlePageChange(this.page = 1)
    }, err => {

    })
  }

  getFilterLevel(levelId: any) {
    this.programmeManagementList = this.tempprogrammeList.filter((ele: any) => ele.courseLevel.id === levelId);
  }

  getBDGCFilter(programId: any) {
    this.authService.getBDGCFilter(programId).subscribe((result: any) => {
      this.bdgcListFilter = result;
    }, err => {

    })
  }


  getBDGNFilter(categoryId: any) {
    this.authService.getBDGNFilter(categoryId).subscribe((result: any) => {
      this.bdgnListFilter = result;
    }, err => {

    })
  }




  getBDGMapping() {

    this.authService.getBDGMapping().subscribe((result: any) => {
      console.log(result)
      this.programmeManagementList = result;
      this.programmeManagementList.forEach(ele => {
        ele['programme'] = ele.programmeId.programme;
        ele['level'] = ele.programmeId.courseLevel.level;
        ele['discipline'] = ele.broadDisciplineGroupId.discipline;
        ele['category'] = ele.broadDisciplineGroupCategoryId.category;

        this.levellistFilter.push({ id: ele.programmeId.courseLevel.id, level: ele.programmeId.courseLevel.level })
        this.programmeListFilter.push({ id: ele.programmeId.id, programme: ele.programmeId.programme })
        this.bdgcListFilter.push({ id: ele.broadDisciplineGroupCategoryId.id, category: ele.broadDisciplineGroupCategoryId.category })
        this.bdgnListFilter.push({ id: ele.broadDisciplineGroupId.id, discipline: ele.broadDisciplineGroupId.discipline })
        this.levellistFilter = this.levellistFilter.filter(
          (thing, i, arr) => arr.findIndex(t => t.level === thing.level) === i
        );
        this.programmeListFilter = this.programmeListFilter.filter(
          (thing, i, arr) => arr.findIndex(t => t.programme === thing.programme) === i
        );

        this.bdgcListFilter = this.bdgcListFilter.filter(
          (thing, i, arr) => arr.findIndex(t => t.category === thing.category) === i
        );
        this.bdgnListFilter = this.bdgnListFilter.filter(
          (thing, i, arr) => arr.findIndex(t => t.discipline === thing.discipline) === i
        );
      })
      this.bdgMappingList = result;
      this.bdgMappingList.map(e=>{
        e['levelId']=e.programmeId.courseLevel.id
        e['programmeId'] = e.programmeId.id,
        e['broadDisciplineGroupId']=e.broadDisciplineGroupId.id,
        e['broadDisciplineGroupCategoryId']=e.broadDisciplineGroupCategoryId.id
       
      })
      this.tempbdgMappingList = [...this.bdgMappingList]
    }, err => {

    })
  }




  saveProgrammeData() {
    if (this.programmeForm.controls['programme'].value.trim() === "") {
      this.sharedService.showValidationMessage('Please enter programme');
      return;
    }
    if (this.programmeForm.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid = true;
      return;
    } else {
      this.isFormInvalid = false;
    }
    this.submitted = true;
    let array = this.programmeForm.controls['year'].value
    let payload = {
      id: null,
      courseLevelId: this.programmeForm.controls['level'].value,
      programme: this.programmeForm.controls['programme'].value,
      // month: this.programmeForm.controls['month'].value,
      // year:this.programmeForm.controls['year'].value
    }
    this.authService.addProgramme(payload).subscribe(res => {
      if (res.status === 200) {
        this.levelId = ''
        this.programmeForm.reset();
        this.sharedService.showSuccess();
        this.getProgramme();
      }
    }, err => {
      if (err.status === 422) {
        this.sharedService.showValidationMessage(
          'Program Name already exists'
        );
      }

    })
  }


  saveBDGCData() {
    if (this.BDGCForm.controls['category'].value.trim() === "") {
      this.sharedService.showValidationMessage('Please enter category');
      return;
    }
    if (this.BDGCForm.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid1 = true;
      return;
    } else {
      this.isFormInvalid1 = false;
    }
    let payload = {
      id: null,
      category: this.BDGCForm.controls['category'].value
    }
    this.authService.addBDGC(payload).subscribe(res => {
      if (res.status === 200) {
        this.BDGCForm.reset()
        this.getBDGC()
        this.sharedService.showSuccess();
      }
    }, err => {
      if (err.status === 422) {
        this.sharedService.showValidationMessage(
          'Broad Discipline Group Category already exists'
        );
      }

    })
  }



  saveBDGNData() {
    if (this.BDGNForm.controls['disciplineGroup'].value.trim() === "") {
      this.sharedService.showValidationMessage('Please enter discipline');
      return;
    }
    if (this.BDGNForm.invalid) {
      this.sharedService.showWarning();
      this.isFormInvalid2 = true;
      return;
    } else {
      this.isFormInvalid2 = false;
    }
    let payload = {
      id: null,
      disciplineGroup: this.BDGNForm.controls['disciplineGroup'].value
    }
    this.authService.addBDGN(payload).subscribe(res => {
      if (res.status === 200) {
        this.getBDGN();
        this.BDGNForm.reset()
        this.sharedService.showSuccess();
      }
    }, err => {
      if (err.status === 422) {
        this.sharedService.showValidationMessage(
          'Broad Discipline Group Name already exists'
        );
      }

    })
  }


  saveBDGMappingData() {
    if (this.BDGMappingForm.invalid) {
      this.isFormInvalid3 = true;
      this.sharedService.showWarning();
      return;
    } else[
      this.isFormInvalid3 = false
    ]
    let payload = [{
      levelId: this.BDGMappingForm.controls['levelId'].value,
      programmeId: this.BDGMappingForm.controls['programmeId'].value,
      broadDisciplineGroupCategoryId: this.BDGMappingForm.controls['broadDisciplineGroupCategoryId'].value,
      broadDisciplineGroupId: this.BDGMappingForm.controls['broadDisciplineGroupId'].value

    }]
    this.authService.addBDGMapping(payload).subscribe(res => {
      if (res.status === 200) {
        this.BDGMappingForm.reset();
        this.sharedService.showSuccess();
        this.getBDGMapping();
      }
    }, err => {
      if (err.status === 422) {
        this.sharedService.showValidationMessage(
          'Programme Mapping already exists'
        );
      }

    })
  }




  selectChangeHandlerProgramme(levelId: any) {
    this.programmeId = ''
    this.broadDisciplineGroupCategoryId = ''
    this.broadDisciplineGroupId = ''
    this.authService.getProgrammeFilter(levelId).subscribe((result: any) => {
      this.programmeListFilterArray = result
      this.programmeListFilter = this.programmeListFilterArray.slice()
    })
  }
  selectChangeHandlerBDGC(programId: any) {
    this.broadDisciplineGroupCategoryId = ''
    this.broadDisciplineGroupId = ''
    this.authService.getBDGCFilter(programId).subscribe((result: any) => {
      this.bdgcListFilterArray = result
      this.bdgcListFilter = this.bdgcListFilterArray.slice()
    })
  }

  selectChangeHandlerBDGN(categoryId: any) {
    this.broadDisciplineGroupId = ''
    this.authService.getBDGNFilter(categoryId).subscribe((result: any) => {
      this.bdgnListFilterArray = result
      this.bdgnListFilter = this.bdgnListFilterArray.slice()
    })
  }


  searchProgramme() {
    this.filter={}
    if (this.levelId) {
      this.filter['levelId'] = this.levelId
    } if (this.programmeId) {
      this.filter['programmeId'] = this.programmeId
    } if (this.broadDisciplineGroupId) {
      this.filter['broadDisciplineGroupId'] = this.broadDisciplineGroupId
    } if (this.broadDisciplineGroupCategoryId) {
      this.filter['broadDisciplineGroupCategoryId'] = this.broadDisciplineGroupCategoryId
    }
    this.programmeManagementList = this.tempbdgMappingList.filter(i =>
      Object.entries(this.filter).every(([k, v]) => i[k] === v)
    )
     this.handlePageChange(this.page = 1)
  }



  async updateResults() {
    if (this.selectedIndex === 1) {
      this.programmeManagementList = []
      this.programmeManagementList = this.searchByValue(this.tempprogrammeList);
    } else if (this.selectedIndex === 2) {
      this.programmeManagementList = []
      this.programmeManagementList = this.searchByValue(this.tempbdgcList);
    } else if (this.selectedIndex === 3) {
      this.programmeManagementList = []
      this.programmeManagementList = this.searchByValue(this.tempbdgnList);
    } else if (this.selectedIndex === 4) {
      this.programmeManagementList = []
      this.programmeManagementList = this.searchByValue(this.tempbdgMappingList);
    }
    this.handlePageChange(this.page = 1)

  }


  searchByValue(userData: any) {
    if (this.selectedIndex === 1) {
      return userData.filter((item: any) => {
        if (this.searchText.trim() === '') {
          return true;
        } else {
          return (item.programme?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
            || (item.courseLevel.level?.toLowerCase().includes(this.searchText.trim().toLowerCase()))

        }
      })
    } else if (this.selectedIndex === 2) {
      return userData.filter((item: any) => {
        if (this.searchText.trim() === '') {
          return true;
        } else {
          return (item.category?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))

        }
      })
    } else if (this.selectedIndex === 3) {
      return userData.filter((item: any) => {
        if (this.searchText.trim() === '') {
          return true;
        } else {
          // item.name.trim();
          return (item.discipline?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))

        }
      })
    } else if (this.selectedIndex === 4) {
      return userData.filter((item: any) => {
        if (this.searchText.trim() === '') {
          return true;
        } else {
          // item.name.trim();
          return (item.programmeId.courseLevel.level?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
            || (item.programmeId.programme?.toString().toLowerCase().includes(this.searchText.trim().toLowerCase()))
            || (item.broadDisciplineGroupCategoryId.category?.toLowerCase().includes(this.searchText.trim().toLowerCase()))
            || (item.broadDisciplineGroupId.discipline?.toLowerCase().includes(this.searchText.trim().toLowerCase()))

        }
      })
    }

  }
  refresh() {
    this.broadDisciplineGroupId = '';
    this.broadDisciplineGroupCategoryId = '';
    this.programmeId = '';
    this.levelId = '';
    this.filter={}
    this.getBDGMapping()
  }
    downloadExcel() {
      this.tableHeaders = ['SNO','Level', 'Programme', 'Broad Disciplne Group Category','Broad Disciplne Group Name'];
    const tableData = this.programmeManagementList.map((row, i) => [
      // i+1,
      row.level, 
      row.programme, 
      row.category, 
      row.discipline,
    ]);
   this.tableHeaders.shift();
    let param={
      tableHeaders:this.tableHeaders,
      tableData:tableData,
      // excelName:'Merged Insitutions',
      downloadExcelName:'Programmme Mapping List',
      setHeaderCollumnWidths:[
        { wpx: 80 },//level
        { wpx: 130 }, //programme
        { wpx: 290 },//category,
        { wpx: 290 },//discipline 
      ],
       headerStyle:{
        font: {
          bold: true,
          color: { rgb: 'FFFFFF' }, // White text color
        },
        fill: {
          fgColor: { rgb: '4CAF50' }, // Green background color
        },
        alignment: {
          horizontal: 'center',
          vertical: 'center'
        }
      }  
    }
   this.sharedService.downloadExcel(param);
  }
}
