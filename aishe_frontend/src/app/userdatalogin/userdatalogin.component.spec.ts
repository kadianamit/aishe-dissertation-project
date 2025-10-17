import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserdataloginComponent } from './userdatalogin.component';

describe('UserdataloginComponent', () => {
  let component: UserdataloginComponent;
  let fixture: ComponentFixture<UserdataloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserdataloginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserdataloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
