import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackApproveStatusComponent } from './track-approve-status.component';

describe('TrackApproveStatusComponent', () => {
  let component: TrackApproveStatusComponent;
  let fixture: ComponentFixture<TrackApproveStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrackApproveStatusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrackApproveStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
