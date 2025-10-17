import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsManagSummaryComponent } from './ins-manag-summary.component';

describe('InsManagSummaryComponent', () => {
  let component: InsManagSummaryComponent;
  let fixture: ComponentFixture<InsManagSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsManagSummaryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsManagSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
