import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-reject-foreign-list',
  templateUrl: './reject-foreign-list.component.html',
  styleUrls: ['./reject-foreign-list.component.scss']
})
export class RejectForeignListComponent implements OnInit {
  alldata: Array<any> = [];
  tempList: Array<any> = [];
  searchText:string=''
  countryArr: Array<any> = []
  filterCountryArr: Array<any> = [];
  constructor(public shared:SharedService,public authService:AuthService) { }

  ngOnInit(): void {
    this.getForeignInstitutesDetails();
    this.getCountries()
  }
  onSelectionCountry(event: any) {
    if (event.value === 0) {
      this.alldata = [...this.tempList];
    } else {
      let array = this.tempList.filter(e => e.countryId === (event.value).toString())
      this.alldata = array.slice()
    }
    this.handlePageChange(this.shared.page=1)

  }
  getCountries() {
    this.authService.getcountryPrivate().subscribe(res => {
      this.countryArr = res
      const i = this.countryArr.findIndex(e=>e.id === 1)
      this.countryArr.splice(i,1)
      this.filterCountryArr = this.countryArr.slice()
    }, err => {

    })
  }
  getForeignInstitutesDetails() {
    let payload = {
      countryId: 0,
      statusId: 4
    }
    this.authService.getForeignInstitutesList(payload).subscribe(res => {
      if (res && res.length) {
        this.alldata = res.sort(function (a: any, b: any) {
          return a.country.name.localeCompare(b.country.name) || a.name.localeCompare(b.name)
        })
        this.tempList = [...this.alldata]
        this.handlePageChange(this.shared.page = 1)
      } else {
        this.alldata = [];
        this.tempList=[]
      }

    }, err => {

    })
  }

  handlePageChange(event: any) {
    this.shared.page = event
    this.shared.StartLimit = ((this.shared.page - 1) * Number(this.shared.pageSize)),
      this.shared.EndLimit = this.shared.StartLimit + Number(this.shared.pageSize)
    var a = Math.ceil(this.alldata.length / Number(this.shared.pageSize));
    if (a === event) {
      this.shared.pageData = Math.min(this.shared.StartLimit + Number(this.shared.pageSize), this.alldata.length);
    } else {
      this.shared.pageData = Math.min(this.shared.StartLimit + Number(this.shared.pageSize), this.alldata.length - 1);
    }

  }

  onChange(event: any) {
    this.shared.pageSize = parseInt(event);
    this.handlePageChange(this.shared.page = 1)
  }

  async updateResults() {
    this.alldata = []
    this.alldata = this.searchByValue(this.tempList);
    this.handlePageChange(this.shared.page = 1)
  }
  searchByValue(data: any) {
    return data.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (item.name?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
          || (item.country?.name?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()))
      }
    })
  }
}

