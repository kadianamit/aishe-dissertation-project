import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report124aComponent } from './report124a.component';

describe('Report124aComponent', () => {
  let component: Report124aComponent;
  let fixture: ComponentFixture<Report124aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report124aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report124aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
