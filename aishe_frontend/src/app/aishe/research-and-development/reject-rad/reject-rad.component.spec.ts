import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RejectRadComponent } from './reject-rad.component';

describe('RejectRadComponent', () => {
  let component: RejectRadComponent;
  let fixture: ComponentFixture<RejectRadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RejectRadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RejectRadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
