import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAlreadyRegistredComponent } from './user-already-registred.component';

describe('UserAlreadyRegistredComponent', () => {
  let component: UserAlreadyRegistredComponent;
  let fixture: ComponentFixture<UserAlreadyRegistredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserAlreadyRegistredComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAlreadyRegistredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
