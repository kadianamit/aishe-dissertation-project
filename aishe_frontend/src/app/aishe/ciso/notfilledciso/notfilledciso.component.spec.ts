import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotfilledcisoComponent } from './notfilledciso.component';

describe('NotfilledcisoComponent', () => {
  let component: NotfilledcisoComponent;
  let fixture: ComponentFixture<NotfilledcisoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotfilledcisoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NotfilledcisoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
