import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report106aComponent } from './report106a.component';

describe('Report106aComponent', () => {
  let component: Report106aComponent;
  let fixture: ComponentFixture<Report106aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report106aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report106aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
