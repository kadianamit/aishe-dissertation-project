import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AuthService } from 'src/app/service/auth.service';
import { LocalserviceService } from 'src/app/service/localservice.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-foreign-dashboard',
  templateUrl: './foreign-dashboard.component.html',
  styleUrls: ['./foreign-dashboard.component.scss']
})
export class ForeignDashboardComponent implements OnInit {

  constructor(private authService: AuthService, private fb: FormBuilder, public shared: SharedService, public localService: LocalserviceService) {}
  alldata: Array<any> = [];
  tempList: Array<any> = [];
  filteredCountries:any=[];
  searchText:any=''
  StartLimit: number = 0;
  pageData: number = 25;
  EndLimit: number = 0;
  pageSize: any = 25;
  page: number = 1;
  ngOnInit(): void {
   this.callCountriesAPI();
  }
  callCountriesAPI(){
    let payload = {
      countryId: 0,
      statusId: 3
    }
    this.authService.getForeignInstitutesList(payload).subscribe(res => {
      if (res && res.length) {
        this.alldata = res.sort(function (a: any, b: any) {
          return a.country.name.localeCompare(b.country.name) || a.name.localeCompare(b.name)
        })
        this.tempList = [...this.alldata]
        // console.log(this.alldata);
        const countryCounts = this.alldata.reduce((acc, item) => {
          const countryName = item?.country?.name || 'Unknown';
          acc[countryName] = (acc[countryName] || 0) + 1;
          return acc;
        }, {} as { [key: string]: number });
        
        const countryCountsArray = Object.entries(countryCounts).map(([country, count]) => ({ country, count }));
        this.filteredCountries=countryCountsArray;
        this.tempList =[...this.filteredCountries]
        
      } else {
        this.filteredCountries=[]
        this.alldata = [];
        this.tempList = []
      }
      this.handlePageChange(this.shared.page = 1)
    }, err => {

    })
  }
  handlePageChange(event: any) {
    this.shared.page = event
    this.shared.StartLimit = ((this.shared.page - 1) * Number(this.shared.pageSize)),
      this.shared.EndLimit = this.shared.StartLimit + Number(this.shared.pageSize)
    var a = Math.ceil(this.filteredCountries.length / Number(this.shared.pageSize));
    if (a === event) {
      this.shared.pageData = Math.min(this.shared.StartLimit + Number(this.shared.pageSize), this.filteredCountries.length);
    } else {
      this.shared.pageData = Math.min(this.shared.StartLimit + Number(this.shared.pageSize), this.filteredCountries.length - 1);
    }

  }

  onChange(event: any) {
    this.shared.pageSize = parseInt(event);
    this.handlePageChange(this.shared.page = 1)
  }
  async updateResults() {
    this.filteredCountries = [];
    this.filteredCountries = this.searchByValue(this.tempList);
    this.handlePageChange((this.shared.page = 1));
  }
  searchByValue(data: any) {
    return data.filter((item: any) => {
      if (this.searchText.trim() === '') {
        return true;
      } else {
        return (
          item.country?.toString().trim().toLowerCase().includes(this.searchText.trim().toLowerCase()) 
        );
      }
    });
  }

}
