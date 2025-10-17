import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataUserLoginComponent } from './data-user-login.component';

describe('DataUserLoginComponent', () => {
  let component: DataUserLoginComponent;
  let fixture: ComponentFixture<DataUserLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DataUserLoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DataUserLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
