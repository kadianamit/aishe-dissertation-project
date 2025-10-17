import { Injectable } from '@angular/core';
import { ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class CustomvalidationService {


  //  match(controlName: string, checkControlName: string): ValidatorFn {
  //   return (controls: AbstractControl) => {

  //     const control = controls.get(controlName);
  //     const checkControl = controls.get(checkControlName);
 
  //     if (checkControl?.errors && !checkControl.errors['matching']) {
  //       return null;
  //     }
  //     if (control?.value !== checkControl?.value) {
  //       controls.get(checkControlName)?.setErrors({ matching: true });
  //       return { matching: true };
  //     } else {
  //       return null;
  //     }
  //   };
  // }


 match(controlName: string, checkControlName: string): ValidatorFn {
  return (controls: AbstractControl) => {
    const control = controls.get(controlName);
    const checkControl = controls.get(checkControlName);

    if (!control || !checkControl) {
      return null;
    }

    if (checkControl.errors && !checkControl.errors['matching']) {
      return null;
    }

    if (control.value !== checkControl.value) {
      checkControl.setErrors({ matching: true });
      return { matching: true };
    } else {
      return null;
    }
  };
}
 
   patternValidator(regex: RegExp, error: ValidationErrors): ValidatorFn {
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


  // patternValidator(): ValidatorFn {
  //   return (control: AbstractControl): { [key: string]: any } => {
  //     if (!control.value) {
  //       return null;
  //     }
  //     const regex = new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$');
  //     const valid = regex.test(control.value);
  //     return valid ? null : { invalidPassword: true };
  //   };
  // }


  // MatchPassword(password: string, confirmPassword: string) {
  //   return (formGroup: FormGroup) => {
  //     const passwordControl = formGroup.controls[password];
  //     const confirmPasswordControl = formGroup.controls[confirmPassword];

  //     if (!passwordControl || !confirmPasswordControl) {
  //       return null;
  //     }

  //     if (confirmPasswordControl.errors && !confirmPasswordControl.errors) {
  //       return null;
  //     }

  //     if (passwordControl.value !== confirmPasswordControl.value) {
  //       confirmPasswordControl.setErrors({ passwordMismatch: true });
  //     } else {
  //       confirmPasswordControl.setErrors(null);
  //     }
  //   }
  // }

  userNameValidator(userControl: AbstractControl) {
    return new Promise(resolve => {
      setTimeout(() => {
        if (this.validateUserName(userControl.value)) {
          resolve({ userNameNotAvailable: true });
        } else {
          resolve(null);
        }
      }, 1000);
    });
  }

  validateUserName(userName: string) {
    const UserList = ['ankit', 'admin', 'user', 'superuser'];
    return (UserList.indexOf(userName) > -1);
  }
}

