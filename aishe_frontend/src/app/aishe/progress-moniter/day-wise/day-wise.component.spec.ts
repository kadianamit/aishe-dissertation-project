import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DayWiseComponent } from './day-wise.component';

describe('DayWiseComponent', () => {
  let component: DayWiseComponent;
  let fixture: ComponentFixture<DayWiseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DayWiseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DayWiseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
