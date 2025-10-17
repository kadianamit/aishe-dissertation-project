import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversalsearchComponent } from './universalsearch.component';

describe('UniversalsearchComponent', () => {
  let component: UniversalsearchComponent;
  let fixture: ComponentFixture<UniversalsearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UniversalsearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UniversalsearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
