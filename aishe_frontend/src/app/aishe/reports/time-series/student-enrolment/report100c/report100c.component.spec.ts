import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report100cComponent } from './report100c.component';

describe('Report100cComponent', () => {
  let component: Report100cComponent;
  let fixture: ComponentFixture<Report100cComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report100cComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report100cComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
