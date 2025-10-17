import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutMoeComponent } from './about-moe.component';

describe('AboutMoeComponent', () => {
  let component: AboutMoeComponent;
  let fixture: ComponentFixture<AboutMoeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AboutMoeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AboutMoeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
