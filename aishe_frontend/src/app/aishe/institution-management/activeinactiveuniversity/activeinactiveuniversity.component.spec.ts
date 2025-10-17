import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveinactiveuniversityComponent } from './activeinactiveuniversity.component';

describe('ActiveinactiveuniversityComponent', () => {
  let component: ActiveinactiveuniversityComponent;
  let fixture: ComponentFixture<ActiveinactiveuniversityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActiveinactiveuniversityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActiveinactiveuniversityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
