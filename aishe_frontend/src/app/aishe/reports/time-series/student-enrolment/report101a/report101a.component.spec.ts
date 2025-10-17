import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report101aComponent } from './report101a.component';

describe('Report101aComponent', () => {
  let component: Report101aComponent;
  let fixture: ComponentFixture<Report101aComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report101aComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report101aComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
