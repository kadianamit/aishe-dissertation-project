import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private toastr: ToastrService) { }

  showSuccess(message:string){
    this.toastr.success(message)
}

showError(message:string){
    this.toastr.error(message)
}

showInfo(message:string, title:string){
    this.toastr.info(message, title)
}
showValidationMessage(message: any) {
  this.toastr.error(message);
}
showWarning(message:string, title:string){
    this.toastr.warning(message, title)
}
}
