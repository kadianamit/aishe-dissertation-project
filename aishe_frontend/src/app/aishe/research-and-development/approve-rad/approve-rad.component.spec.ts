import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveRadComponent } from './approve-rad.component';

describe('ApproveRadComponent', () => {
  let component: ApproveRadComponent;
  let fixture: ComponentFixture<ApproveRadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApproveRadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveRadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
