import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StandaloneRejectedReqComponent } from './standalone-rejected-req.component';

describe('StandaloneRejectedReqComponent', () => {
  let component: StandaloneRejectedReqComponent;
  let fixture: ComponentFixture<StandaloneRejectedReqComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StandaloneRejectedReqComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StandaloneRejectedReqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
