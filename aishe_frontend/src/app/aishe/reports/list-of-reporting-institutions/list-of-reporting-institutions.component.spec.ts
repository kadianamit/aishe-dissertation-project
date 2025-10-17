import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfReportingInstitutionsComponent } from './list-of-reporting-institutions.component';

describe('ListOfReportingInstitutionsComponent', () => {
  let component: ListOfReportingInstitutionsComponent;
  let fixture: ComponentFixture<ListOfReportingInstitutionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListOfReportingInstitutionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOfReportingInstitutionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
