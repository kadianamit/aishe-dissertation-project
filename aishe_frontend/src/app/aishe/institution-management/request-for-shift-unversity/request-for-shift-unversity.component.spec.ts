import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestForShiftUnversityComponent } from './request-for-shift-unversity.component';

describe('RequestForShiftUnversityComponent', () => {
  let component: RequestForShiftUnversityComponent;
  let fixture: ComponentFixture<RequestForShiftUnversityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestForShiftUnversityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestForShiftUnversityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
