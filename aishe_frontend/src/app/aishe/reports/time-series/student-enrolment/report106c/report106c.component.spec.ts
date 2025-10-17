import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report106cComponent } from './report106c.component';

describe('Report106cComponent', () => {
  let component: Report106cComponent;
  let fixture: ComponentFixture<Report106cComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report106cComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report106cComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
