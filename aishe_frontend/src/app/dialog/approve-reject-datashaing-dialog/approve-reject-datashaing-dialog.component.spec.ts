import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveRejectDatashaingDialogComponent } from './approve-reject-datashaing-dialog.component';

describe('ApproveRejectDatashaingDialogComponent', () => {
  let component: ApproveRejectDatashaingDialogComponent;
  let fixture: ComponentFixture<ApproveRejectDatashaingDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApproveRejectDatashaingDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveRejectDatashaingDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
