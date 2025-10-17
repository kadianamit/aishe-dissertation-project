import { Injectable } from '@angular/core';
import {
  mergeMap,
  delay,
  retryWhen,
  finalize,
  map,
  throwError,
  catchError,
} from 'rxjs';

export const maxRetries = 2;
export const delayMs = 2000;
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { SharedService } from '../shared/shared.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { MatDialog } from '@angular/material/dialog';
import { SessionExpireDialogComponent } from '../dialog/session-expire-dialog/session-expire-dialog.component';

@Injectable()
export class Interceptor implements HttpInterceptor {
  private totalRequests = 0;

  constructor(public sharedService: SharedService, public router: Router,public dialog: MatDialog) { }

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    this.totalRequests++;
    this.sharedService.global_loader = true;

    if (request.url !== environment.baseURLLATLONG && request.url !== environment.baseURLLATLONG1) {
      request = request.clone({
        setHeaders: {
          'Access-Control-Allow-Origin': '*',
          'Authorization': `Bearer ${sessionStorage.getItem('token')}`,
        },
      });
    }

    return next.handle(request).pipe(
      finalize(() => {
        this.totalRequests--;
        if (this.totalRequests === 0) {
          this.sharedService.global_loader = false;
        }
      }),
      map((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
        }
        return event;
      }),
      catchError((error: HttpErrorResponse) => {
        if (error.error instanceof ErrorEvent) {
          this.sharedService.global_loader = false;
          // A client-side or network error occurred. Handle it accordingly.
          console.error(
            'An error occurred:Wrong password. Try again or click Forgot password to reset it.',
            error.error.message
          );
        }
        if (error.error.message === 'Problem Occurred!') {
          this.sharedService.verifyOTP('Invalid OTP');
        }else if (error.error.message === 'Please Verify Your Email or Mobile Or Both!!') {
          this.sharedService.showValidationMessage('Please Verify Your Email or Mobile Or Both!!')
        }else if (error.error.message === 'Enter Otp For Mobile and Email Both!!') {
          this.sharedService.showValidationMessage('Enter Otp For Mobile and Email Both!!')
        }else if(error.status == 402){
          this.sharedService.global_loader = false;
          this.sharedService.session()
        
        }
        if(error.error.message === 'Incorrect file type, PDF required.'){
          this.sharedService.showValidationMessagePDF('Incorrect file type, PDF required')
        }
        return throwError(error);
      })
    );
  }
  
}

// retryWhen((error) => {
//   this.sharedService.global_loader = false;
//   return error.pipe(
//     mergeMap((error, index) => {
//       this.sharedService.global_loader = false;
//       if (error.status == 500) {
//         return of(error).pipe(delay(delayMs));
//       }
//       // if (index < maxRetries && error.status == 500) {
//       //   return of(error).pipe(delay(delayMs));
//       // }
//       throw error;
//     })
//   )
//   })
//)
//}
//}
