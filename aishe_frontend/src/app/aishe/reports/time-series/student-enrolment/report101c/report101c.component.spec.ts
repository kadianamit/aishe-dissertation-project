import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report101cComponent } from './report101c.component';

describe('Report101cComponent', () => {
  let component: Report101cComponent;
  let fixture: ComponentFixture<Report101cComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report101cComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report101cComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
