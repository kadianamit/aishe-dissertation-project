import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StateWiseMonitoringDialogComponent } from './state-wise-monitoring-dialog.component';

describe('StateWiseMonitoringDialogComponent', () => {
  let component: StateWiseMonitoringDialogComponent;
  let fixture: ComponentFixture<StateWiseMonitoringDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StateWiseMonitoringDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StateWiseMonitoringDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
