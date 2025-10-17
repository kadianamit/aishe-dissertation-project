import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KnowAisheCodeComponent } from './know-aishe-code.component';

describe('KnowAisheCodeComponent', () => {
  let component: KnowAisheCodeComponent;
  let fixture: ComponentFixture<KnowAisheCodeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KnowAisheCodeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KnowAisheCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
