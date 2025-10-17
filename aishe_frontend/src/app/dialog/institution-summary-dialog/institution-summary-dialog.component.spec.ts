import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstitutionSummaryDialogComponent } from './institution-summary-dialog.component';

describe('InstitutionSummaryDialogComponent', () => {
  let component: InstitutionSummaryDialogComponent;
  let fixture: ComponentFixture<InstitutionSummaryDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstitutionSummaryDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstitutionSummaryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
