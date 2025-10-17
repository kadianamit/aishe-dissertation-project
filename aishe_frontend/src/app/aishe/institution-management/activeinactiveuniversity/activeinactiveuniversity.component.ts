import { Component, Input, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { ConfirmDialogComponent } from "src/app/dialog/confirm-dialog/confirm-dialog.component";
import { InstitutionmanagementService } from "src/app/service/institutionmanagement/institutionmanagement.service";
import { LocalserviceService } from "src/app/service/localservice.service";
import { SharedService } from "src/app/shared/shared.service";

@Component({
  selector: "app-activeinactiveuniversity",
  templateUrl: "./activeinactiveuniversity.component.html",
  styleUrls: ["./activeinactiveuniversity.component.scss"]
})
export class ActiveinactiveuniversityComponent implements OnInit {
  @Input() instiFormData: any;
  count: any = 0;
  userDataTable: any[] = [];
  tabledata: boolean = false;
  userData1: any = [];
  StartLimit: number = 0;
  pageData: number = 10;
  EndLimit: number = 0;
  pageSize: any = 10;
  searchText: any;
  page: number = 1;
  isStatus1: boolean = false;
  showTable: boolean = false;
  tableSize: number[] = [10, 20, 30, 40, 50];
  dataDeaffiliation: any;
  constructor(
    private institutionmanagement: InstitutionmanagementService,
    public sharedservice: SharedService,
    private dialog: MatDialog,
    public localService: LocalserviceService
  ) {}

  ngOnInit(): void {
    this.findData();
    this.getDeaffiliationReason();
  }
  applyFilter(filterValue: string) {
    this.page = 1;
    this.handlePageChange(this.page);
    //this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  resetdata() {
    this.userDataTable = [];
    this.searchText = null;
    this.pageSize = 10;
    this.page = 1;
    this.handlePageChange(this.page);
  }
  findData() {
    if (this.count > 0) {
      this.searchText = null;
      if (!this.instiFormData.value.status) {
        this.sharedservice.showError("Please Select Status");
        return;
      }
      if (!this.instiFormData.value.surveyYearValue) {
        this.sharedservice.showError("Please Select Survey Year");
        return;
      }
      if (
        (!this.instiFormData.value.stateValue &&
          this.localService.getData("roleId") == "26") ||
        (!this.instiFormData.value.stateValue &&
          this.localService.getData("roleId") == "1")
      ) {
        this.sharedservice.showError("Please Select State");
        return;
      }

      this.institutionmanagement
        .getActiveInactiveStatusData(this.instiFormData.value)
        .subscribe(res => {
          if (res.statusCode === "AISH099") {
            this.sharedservice.showError(res.statusDesc);
            this.userDataTable = [];
            this.showTable = true;
            this.page = 1;
            this.handlePageChange(this.page);
          }
          if (res.statusCode === "AISH001") {
            this.userDataTable = res.universityListBean;
            this.showTable = true;
            this.page = 1;
            this.handlePageChange(this.page);
          }
        });
    } else {
      this.count++;
    }
  }
  statusActive(data: any) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: "26%",
      data: {
        message: "Are you sure you want to Active?",
        s1: "standaloneStatus"
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        let formData = {
          id: data.aishecode,
          ipAddress: "string",
          name: data.name.trim(),
          remarks: result.remarks.trim(),
          reasonId: result.deaffiliation,
          surveyYear: result?.surveyYearValue.split("-")[0],
          userId: this.localService.getData("userId"),
         // status: "I"
        };

        this.institutionmanagement
          .postUniversityStatusActiveData(formData)
          .subscribe(result => {
            if (result.statusCode === "AISH001") {
              this.sharedservice.showSuccessMessage(
                "University has been Activated Successfully."
              );
              this.findData();
            }
            if (result.statusCode === "AISH099") {
              this.sharedservice.showError(result.statusDesc);
            }
            if (result.statusCode === "AISH025") {
              this.sharedservice.showError(result.statusDesc);
            }
          });
      }
    });
  }
  getDeaffiliationReason() {
    this.institutionmanagement.getReasonDeaffileate().subscribe(res => {
      this.dataDeaffiliation = res.reasonId;
    });
  }
  statusInActive(data: any) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: "26%",
      data: {
        message: "Are you sure you want to InActive?",
        s1: "standaloneStatus"
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        let formData = {
          districtCode: "string",
          id: data.aishecode,
          ipAddress: "string",
          location: "string",
          name: data.name,
          remarks: result.remarks,
          stateCode: "string",
          surveyYear: result?.surveyYearValue.split("-")[0],
          typeId: "string",
          userId: this.localService.getData("userId"),
          status:"I"
        };

        this.institutionmanagement
          .postUniversityStatusData(formData)
          .subscribe(result => {
            if (result.statusCode === "AISH001") {
              this.findData();
              this.sharedservice.showSuccessMessage(
                "University has been Inactivated Successfully."
              );
            }
            if (result.statusCode === "AISH099") {
              this.sharedservice.showError(result.statusDesc);
              this.findData();
            }
            if (result.statusCode === "AISH025") {
              this.sharedservice.showError(result.statusDesc);
              this.findData();
            }
            if (result.statusCode === "AISH024") {
              this.sharedservice.showError(result.statusDesc);
              this.findData();
            }
          });
      }
    });
  }

  onChange(event: any): void {
    this.pageSize = parseInt(event);
    this.page = 1;
    this.handlePageChange(this.page);
    //this.getData1();
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
