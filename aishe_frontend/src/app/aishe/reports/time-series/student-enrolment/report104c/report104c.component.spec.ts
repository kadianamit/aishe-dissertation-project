import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Report104cComponent } from './report104c.component';

describe('Report104cComponent', () => {
  let component: Report104cComponent;
  let fixture: ComponentFixture<Report104cComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Report104cComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Report104cComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
