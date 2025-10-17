import { Component, Input, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { ConfirmDialogComponent } from "src/app/dialog/confirm-dialog/confirm-dialog.component";
import { InstitutionmanagementService } from "src/app/service/institutionmanagement/institutionmanagement.service";
import { LocalserviceService } from "src/app/service/localservice.service";
import { SharedService } from "src/app/shared/shared.service";

@Component({
  selector: "app-university-delete",
  templateUrl: "./university-delete.component.html",
  styleUrls: ["./university-delete.component.scss"]
})
export class UniversityDeleteComponent implements OnInit {
  @Input() instiFormData: any;
  userDataTable: any;
  showTable: boolean = false;
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  tableSize: number[] = [10, 20, 30, 40, 50];
  submitted = false;
  constructor(
    public sharedservice: SharedService,
    private institutionmanagement: InstitutionmanagementService,
    private dialog: MatDialog,
    public localService: LocalserviceService
  ) {}

  ngOnInit(): void {
    // console.log(this.instiFormData.value);
    this.findData();
  }
  resetdata(): void {
    this.userDataTable = [];
    this.page = 1;
    this.handlePageChange(this.page);
    this.searchText = null;
    this.pageSize = 10;
  }
  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);

    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  findData() {
    this.searchText = null;
    if (!this.instiFormData.value.surveyYearValue) {
      this.sharedservice.showError("Please Select Survey Year");
      return;
    }
    if (!this.instiFormData.value.stateValue) {
      this.sharedservice.showError("Please Select State");
      return;
    }

    let data = {
      stateValue: this.instiFormData.value.stateValue
        ? this.instiFormData.value.stateValue.trim()
        : "",
      surveyYearValue: this.instiFormData.value.surveyYearValue.split("-")[0],
      universityType: this.instiFormData.value.universityType
        ? this.instiFormData.value.universityType.trim()
        : ""
    };
    this.institutionmanagement.getViewUniversity(data).subscribe(res => {
      this.userDataTable = res.universityListBean;
      this.showTable = true;
      this.page = 1;
      this.handlePageChange(this.page);
      if (res.statusCode == "AISH099") {
        this.sharedservice.showError(res.statusDesc);
      }
    });
  }
  deleteUser(data: any) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: "26%",
      data: {
        message: "Are you sure you want to delete?",
        s1: "UniversityDelete"
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        let formData = {
          districtCode: data.districtname,
          id: data.aishecode,
          ipAddress: "string",
          name: data.name.trim(),
          remarks: result.trim(),
          stateCode: data.statename,
          surveyYear: this.instiFormData.value.surveyYearValue.split("-")[0],
          typeId: data.universityType,
          userId: this.localService.getData("userId"),
          status:"D",
        };

        this.institutionmanagement
          .deleteUniversityData(formData)
          .subscribe(result => {
            if (result.statusCode === "AISH001") {
              this.findData();
              this.sharedservice.showSuccessMessage(
                "University has been deleted Successfully."
              );
            }
            if (result.statusCode === "AISH024") {
              this.sharedservice.showError(result.statusDesc);
            }
            if (result.statusCode === "AISH025") {
              this.sharedservice.showError(result.statusDesc);
            }
          });
      }
    });
  }
  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
    //this.findData();
  }

  handlePageChange(event: number) {
    this.page = event;
    let fgh = parseInt(this.pageSize);
    (this.StartLimit = (this.page - 1) * fgh), (this.EndLimit =
      this.StartLimit + fgh);
    var a = Math.ceil(this.userDataTable.length / fgh);
    if (a === event) {
      this.pageData = Math.min(
        this.StartLimit + fgh,
        this.userDataTable.length
      );
    } else {
      this.pageData = Math.min(
        this.StartLimit + fgh,
        this.userDataTable.length
      );
    }
  }
}
