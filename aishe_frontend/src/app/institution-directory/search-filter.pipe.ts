import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchFilter'
})
export class SearchFilterPipe implements PipeTransform {

  transform(values: any, args: any): any { 
   
    return values?.filter((value:any)=>{
      if(!args) return values;
      let data=args.trim();
      if(data.substr(0,2).toLowerCase() ==='s-' ||data.substr(0,2).toLowerCase() ==='u-' ||data.substr(0,2).toLowerCase() ==='c-') return JSON.stringify(value).toLowerCase().includes(data.toLowerCase().substr(2,data.length))
      return JSON.stringify(value).toLowerCase().includes(data.toLowerCase())
    });
  }

}



