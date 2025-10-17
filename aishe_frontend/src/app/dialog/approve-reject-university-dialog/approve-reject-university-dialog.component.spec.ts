import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveRejectUniversityDialogComponent } from './approve-reject-university-dialog.component';

describe('ApproveRejectUniversityDialogComponent', () => {
  let component: ApproveRejectUniversityDialogComponent;
  let fixture: ComponentFixture<ApproveRejectUniversityDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApproveRejectUniversityDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveRejectUniversityDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
