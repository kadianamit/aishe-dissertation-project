import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report102aComponent } from './report102a.component';

describe('Report102aComponent', () => {
  let component: Report102aComponent;
  let fixture: ComponentFixture<Report102aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report102aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report102aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
