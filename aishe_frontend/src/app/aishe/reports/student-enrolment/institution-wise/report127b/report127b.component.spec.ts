import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report127bComponent } from './report127b.component';

describe('Report127bComponent', () => {
  let component: Report127bComponent;
  let fixture: ComponentFixture<Report127bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report127bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report127bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
