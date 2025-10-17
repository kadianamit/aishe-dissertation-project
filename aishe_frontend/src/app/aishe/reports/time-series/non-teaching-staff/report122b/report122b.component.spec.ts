import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report122bComponent } from './report122b.component';

describe('Report122bComponent', () => {
  let component: Report122bComponent;
  let fixture: ComponentFixture<Report122bComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report122bComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report122bComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
