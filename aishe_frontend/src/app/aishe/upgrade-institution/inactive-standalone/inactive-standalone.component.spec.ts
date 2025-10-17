import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InactiveStandaloneComponent } from './inactive-standalone.component';

describe('InactiveStandaloneComponent', () => {
  let component: InactiveStandaloneComponent;
  let fixture: ComponentFixture<InactiveStandaloneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InactiveStandaloneComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InactiveStandaloneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
