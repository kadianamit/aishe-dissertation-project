import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoniteringComponent } from './monitering.component';

describe('MoniteringComponent', () => {
  let component: MoniteringComponent;
  let fixture: ComponentFixture<MoniteringComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MoniteringComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MoniteringComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
