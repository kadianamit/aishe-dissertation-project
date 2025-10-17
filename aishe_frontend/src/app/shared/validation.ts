import { Injectable } from '@angular/core';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { FormControl, NgForm, FormGroupDirective } from "@angular/forms";
import { ErrorStateMatcher } from "@angular/material/core";
@Injectable({
  providedIn: 'root'
})
export class CustomErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl, form: NgForm | FormGroupDirective | null) {
    return control && control.invalid && control.touched;
  }
}
export default class Validation {
    
  static match(controlName: string, checkControlName: string): ValidatorFn {
    return (controls: AbstractControl) => {

      const control = controls.get(controlName);
      const checkControl = controls.get(checkControlName);
 
      if (checkControl?.errors && !checkControl.errors['matching']) {
        return null;
      }
      if (control?.value !== checkControl?.value) {
        controls.get(checkControlName)?.setErrors({ matching: true });
        return { matching: true };
      } else {
        return null;
      }
    };
  }
 
  static patternValidator(regex: RegExp, error: ValidationErrors): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      if (!control.value) {
        // if control is empty return no error
        return [''];
      }

      // test the value of the control against the regexp supplied
      const valid = regex.test(control.value);

      // if true, return no error (no error), else return error passed in the second parameter
      return valid ? [''] : error;
    };
  }
}




// export const PasswordStrengthValidator = function (control: AbstractControl): ValidationErrors | any {

//     let value: string = control.value || '';
//     let msg = "";
//     if (!value) {
//       return null
//     }
  
//     let upperCaseCharacters = /[A-Z]+/g;
//     let lowerCaseCharacters = /[a-z]+/g;
//     let numberCharacters = /[0-9]+/g;
//     let specialCharacters = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
//     if (upperCaseCharacters.test(value) === false || lowerCaseCharacters.test(value) === false || numberCharacters.test(value) === false || specialCharacters.test(value) === false) {
//       return {
//         passwordStrength: 'Password must contain at least two of the following: numbers, lowercase letters, uppercase letters, or special characters.'
//       }
  
//     }
  
//     /*let upperCaseCharacters = /[A-Z]+/g
//     if (upperCaseCharacters.test(value) === false) {
//       return { passwordStrength: `Upper case required` };
//     }
  
//     let lowerCaseCharacters = /[a-z]+/g
//     if (lowerCaseCharacters.test(value) === false) {
//       return { passwordStrength: `lower case required` };
//     }
  
  
//     let numberCharacters = /[0-9]+/g
//     if (numberCharacters.test(value) === false) {
//       return { passwordStrength: `number required` };
//     }
  
//     let specialCharacters = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/
//     if (specialCharacters.test(value) === false) {
//       return { passwordStrength: `Special char required` };
//     }
//      return { 
//       passwordStrength:null  
//     }*/
  
//   }