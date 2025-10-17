import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report125cComponent } from './report125c.component';

describe('Report125cComponent', () => {
  let component: Report125cComponent;
  let fixture: ComponentFixture<Report125cComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report125cComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report125cComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
