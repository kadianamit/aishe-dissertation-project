import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appInputNumbersOnly]'
})
export class InputNumbersOnlyDirective {

  constructor(private element: ElementRef) { }

  @HostListener('input', ['$event']) allowNubersOnly(event: any ) {
  
    // console.log(event.data);

    const initalValue = this.element.nativeElement.value;
		this.element.nativeElement.value = initalValue.replace(/[^0-9]*/g, '');
		if (initalValue !== this.element.nativeElement.value) {
			event.stopPropagation();
		}

  }

}
