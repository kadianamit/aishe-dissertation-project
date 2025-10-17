import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';

@Injectable({
  providedIn: 'root'
})
export class LocalserviceService {
  key = "0123456789123456";

  constructor() { }

  public saveData(key: string, value: string) {
    sessionStorage.setItem(key, this.encrypt(value));
  }

  public getData(key: string) {
    let data = sessionStorage.getItem(key) || "";
    return this.decrypt(data);
  }
  public removeData(key: string) {
    sessionStorage.removeItem(key);
  }

  public clearData() {
    sessionStorage.clear();
  }

  private encrypt(txt: string): string {
    let a = txt?txt.toString():''
    return CryptoJS.AES.encrypt(a, this.key).toString();
  }

  private decrypt(txtToDecrypt: string) {
    let b = txtToDecrypt?txtToDecrypt.toString():''
    return CryptoJS.AES.decrypt(b, this.key).toString(CryptoJS.enc.Utf8);
  }
}
