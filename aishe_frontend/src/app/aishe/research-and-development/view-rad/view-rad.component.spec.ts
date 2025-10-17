import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRADComponent } from './view-rad.component';

describe('ViewRADComponent', () => {
  let component: ViewRADComponent;
  let fixture: ComponentFixture<ViewRADComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewRADComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRADComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
