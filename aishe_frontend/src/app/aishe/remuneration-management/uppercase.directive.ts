import {
  A,
  Z,
} from '@angular/cdk/keycodes';
import {
  Directive,
  ElementRef,
  forwardRef,
  HostListener,
  OnInit,
  Renderer2,
  Self,
} from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Directive({
  selector: '[appUppercase]',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => UppercaseDirective),
      multi: true,
    },
  ],
})
export class UppercaseDirective  {


  constructor( @Self() private _el: ElementRef, private _renderer: Renderer2) { }

  /** Trata as teclas */
  @HostListener('keyup', ['$event'])
  onKeyDown(evt: KeyboardEvent) {
    const keyCode = evt.keyCode;
    const key = evt.key;
   // console.log(evt)
    if (keyCode >= A && keyCode <= Z) {
      const value = this._el.nativeElement.value.toUpperCase();
      this._renderer.setProperty(this._el.nativeElement, 'value', value);

      evt.preventDefault();
    }
  }

 }
