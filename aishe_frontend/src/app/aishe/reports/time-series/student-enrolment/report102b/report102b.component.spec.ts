import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report102bComponent } from './report102b.component';

describe('Report102bComponent', () => {
  let component: Report102bComponent;
  let fixture: ComponentFixture<Report102bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report102bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report102bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
