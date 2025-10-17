import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report125aComponent } from './report125a.component';

describe('Report125aComponent', () => {
  let component: Report125aComponent;
  let fixture: ComponentFixture<Report125aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report125aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report125aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
