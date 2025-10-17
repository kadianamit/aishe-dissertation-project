import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report125bComponent } from './report125b.component';

describe('Report125bComponent', () => {
  let component: Report125bComponent;
  let fixture: ComponentFixture<Report125bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report125bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report125bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
