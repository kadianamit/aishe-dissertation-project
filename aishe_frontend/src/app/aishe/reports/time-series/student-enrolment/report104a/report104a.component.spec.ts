import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report104aComponent } from './report104a.component';

describe('Report104aComponent', () => {
  let component: Report104aComponent;
  let fixture: ComponentFixture<Report104aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report104aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report104aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
