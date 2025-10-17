import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveRejectForeignComponent } from './approve-reject-foreign.component';

describe('ApproveRejectForeignComponent', () => {
  let component: ApproveRejectForeignComponent;
  let fixture: ComponentFixture<ApproveRejectForeignComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApproveRejectForeignComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveRejectForeignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
