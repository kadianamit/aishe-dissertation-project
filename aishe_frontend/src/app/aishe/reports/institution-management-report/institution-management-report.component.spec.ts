import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstitutionManagementReportComponent } from './institution-management-report.component';

describe('InstitutionManagementReportComponent', () => {
  let component: InstitutionManagementReportComponent;
  let fixture: ComponentFixture<InstitutionManagementReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstitutionManagementReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstitutionManagementReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
