import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StartRegComponent } from './start-reg.component';

describe('StartRegComponent', () => {
  let component: StartRegComponent;
  let fixture: ComponentFixture<StartRegComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StartRegComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StartRegComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
