import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report122aComponent } from './report122a.component';

describe('Report122aComponent', () => {
  let component: Report122aComponent;
  let fixture: ComponentFixture<Report122aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report122aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report122aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
