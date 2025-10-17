import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report32bComponent } from './report32b.component';

describe('Report32bComponent', () => {
  let component: Report32bComponent;
  let fixture: ComponentFixture<Report32bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report32bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report32bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
