import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report38cComponent } from './report38c.component';

describe('Report38cComponent', () => {
  let component: Report38cComponent;
  let fixture: ComponentFixture<Report38cComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report38cComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report38cComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
