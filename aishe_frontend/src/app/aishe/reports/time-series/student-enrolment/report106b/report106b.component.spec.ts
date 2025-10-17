import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report106bComponent } from './report106b.component';

describe('Report106bComponent', () => {
  let component: Report106bComponent;
  let fixture: ComponentFixture<Report106bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report106bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report106bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
