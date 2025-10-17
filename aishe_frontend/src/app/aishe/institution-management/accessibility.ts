import { Injectable } from "@angular/core";
import { SharedService } from "src/app/shared/shared.service";
@Injectable({
  providedIn:"root"
})
export class Accessibility {

  constructor(public shearedService:SharedService){
  //  console.log( shearedService.role['Ministry of Education'])
  }

  public canAdd(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && type=='U'){////Ministry of Education(MoE)(1,26)
        return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
    }
    return false;
  }

  public canEdit(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && (type=='U' || type=='C' || type=='S')){//Ministry of Education(1,26)
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
    }
    if(roleName=='SNO' && (type=='C' || type=='S')){//State Nodal Officer(SNO)//6
        return  rollId === this.shearedService.role['State Nodal Officer']  ;
    }
    if(roleName=='UNO' && (type=='C')){//University(UNO)//7 
        return  rollId === this.shearedService.role['University'] ;
    }
    if(roleName=='DTE' && (type=='S')){//Directorate of Technical Education(DTE)//8 
        return  rollId === this.shearedService.role['Directorate of Technical Education'] ;
    }
    if(roleName=='SNB' && (type=='S')){//State Nursing Body/Council(SNB)//9 
        return  rollId === this.shearedService.role['State Nursing Body/Council'] ;
    }
    if(roleName=='SCERT' && (type=='S')){//StateCouncilofEducationalResearch&Training(SCERT)//10 
        return  rollId === this.shearedService.role['State Council of Educational Research and Training'];
    }
    return false
 
  }

  public canDelete(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && (type=='U' || type=='C' || type=='S')){//Ministry of Education(1,26)
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
    }
    return false; 
  }

  public canCollegeDeaffiliate(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && type=='C'){//Ministry of Education(MoE)
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
  }
    return false;
  }

  public canCollegeAffiliate(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && type=='C'){//Ministry of Education(MoE)
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
  }
    return false;
  }

  public canConvertCollegeToUniversity(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && type=='C'){//Ministry of Education(MoE)
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
    }
    return false;
  }

  public canConvertUniversityToCollege(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && type=='C'){//Ministry of Education(MoE)
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
    }
    return false;
  }
  public canConvertStandaloneToUniversity(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && type=='C'){//Ministry of Education(MoE)
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
    }
    return false;
  }

  public canConvertStandaloneToCollege(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && type=='C'){//Ministry of Education(MoE)
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
    }
    return false;
  }
  public canMergeCollege(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && type=='C'){//Ministry of Education
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
    }
    return false;
  }

  public canApproveReject(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && (type=='U' || type=='C' || type=='S')){//Ministry of Education
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
    }
    if(roleName=='SNO' && (type=='C' || type=='S')){//State Nodal Officer(SNO)
      return  rollId === this.shearedService.role['State Nodal Officer']  ;
    }
    if(roleName=='UNO' && (type=='C')){//University(UNO)
      return  rollId === this.shearedService.role['University'] ;
    }
    if(roleName=='DTE' && (type=='S')){//Directorate of Technical Education(DTE)
      return  rollId === this.shearedService.role['Directorate of Technical Education'] ;
    }
    if(roleName=='SNB' && (type=='S')){//State Nursing Body/Council(SNB)
      return  rollId === this.shearedService.role['State Nursing Body/Council'] ;
    }
    if(roleName=='SCERT' && (type=='S')){//StateCouncilofEducationalResearch&Training(SCERT)
      return  rollId === this.shearedService.role['State Council of Educational Research and Training'];
    }
    return false;
  }
  public inactive(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' && (type=='U' || type=='S')){//Ministry of Education
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] ;
  }
    return false;
  }
  public searchRequest(rollId: any,roleName:any,type:any): boolean {
    if(roleName=='MoE' || roleName=='DEO'){//Ministry of Education(MoE) OR Data entry operator(DEO)(1,26,16)
      return rollId === this.shearedService.role['Ministry of Education'] || rollId === this.shearedService.role['SysAdmin'] || rollId === this.shearedService.role['Data Entry Operator'] ;
  }
    return false;
  }

}
// const universityPermissions = Accessibility.canEdit('26','MoE','U'); 
// const universityPermissions11 = Accessibility.canEdit('26','MoE','C'); 
// const universityPermissions12 = Accessibility.canEdit('26','MoE','S'); 

