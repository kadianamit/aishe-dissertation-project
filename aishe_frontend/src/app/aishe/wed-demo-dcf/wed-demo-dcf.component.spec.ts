import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WedDemoDcfComponent } from './wed-demo-dcf.component';

describe('WedDemoDcfComponent', () => {
  let component: WedDemoDcfComponent;
  let fixture: ComponentFixture<WedDemoDcfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WedDemoDcfComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WedDemoDcfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
