import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstitutionManagementDashboardComponent } from './institution-management-dashboard.component';

describe('InstitutionManagementDashboardComponent', () => {
  let component: InstitutionManagementDashboardComponent;
  let fixture: ComponentFixture<InstitutionManagementDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstitutionManagementDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstitutionManagementDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
