import { Directive, HostListener, ElementRef } from '@angular/core';

@Directive({
  selector: '[appFormvalidation]'
})
export class FormvalidationDirective {

  constructor() { }
  
  key:any;
  @HostListener('keydown', ['$event']) onKeydown(event: KeyboardEvent) {
    this.key = event.keyCode;
    console.log(this.key);
    if ((this.key >= 15 && this.key <= 64) || (this.key >= 123) || (this.key >= 96 && this.key <= 105)) {
      event.preventDefault();
    }
  }
}

// static cannotContainSpace(control: AbstractControl) : ValidationErrors | null {  
//   if((control.value as string).indexOf(' ') >= 0){  
//       return {cannotContainSpace: true}  
//   }  

//   return null;  
// }  
