import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRegComponent } from './view-reg.component';

describe('ViewRegComponent', () => {
  let component: ViewRegComponent;
  let fixture: ComponentFixture<ViewRegComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewRegComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRegComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
