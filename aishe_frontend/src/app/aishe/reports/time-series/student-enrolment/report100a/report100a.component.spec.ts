import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report100aComponent } from './report100a.component';

describe('Report100aComponent', () => {
  let component: Report100aComponent;
  let fixture: ComponentFixture<Report100aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report100aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report100aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
