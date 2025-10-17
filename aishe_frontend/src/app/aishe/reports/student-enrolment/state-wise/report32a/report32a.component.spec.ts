import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report32aComponent } from './report32a.component';

describe('Report32aComponent', () => {
  let component: Report32aComponent;
  let fixture: ComponentFixture<Report32aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report32aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report32aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
