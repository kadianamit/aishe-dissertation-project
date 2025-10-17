import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report134aComponent } from './report134a.component';

describe('Report134aComponent', () => {
  let component: Report134aComponent;
  let fixture: ComponentFixture<Report134aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report134aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report134aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
