import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report127aComponent } from './report127a.component';

describe('Report127aComponent', () => {
  let component: Report127aComponent;
  let fixture: ComponentFixture<Report127aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report127aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report127aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
