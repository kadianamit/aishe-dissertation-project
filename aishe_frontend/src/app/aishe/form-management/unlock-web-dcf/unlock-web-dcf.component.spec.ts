import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnlockWebDCFComponent } from './unlock-web-dcf.component';

describe('UnlockWebDCFComponent', () => {
  let component: UnlockWebDCFComponent;
  let fixture: ComponentFixture<UnlockWebDCFComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnlockWebDCFComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnlockWebDCFComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
