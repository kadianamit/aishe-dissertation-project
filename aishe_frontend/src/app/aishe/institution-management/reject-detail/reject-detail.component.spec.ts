import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RejectDetailComponent } from './reject-detail.component';

describe('RejectDetailComponent', () => {
  let component: RejectDetailComponent;
  let fixture: ComponentFixture<RejectDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RejectDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RejectDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
