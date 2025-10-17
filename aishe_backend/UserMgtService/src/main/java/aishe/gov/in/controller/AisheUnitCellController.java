package aishe.gov.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aishe.gov.in.masterseo.AisheUnitCellEo;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.AisheUnitCellService;
import aishe.gov.in.utility.ReturnResponse;

@RestController
public class AisheUnitCellController {
    
    @Autowired
    private AisheUnitCellService aisheUnitCellService;


    @GetMapping(value = "/aishe-unit-cell")
    public ResponseEntity<ReturnResponse> getAisheUnitCell(@RequestParam(required=false) String stateCode,@WithUser UserInfo userInfo) {
        List<AisheUnitCellEo> masterEO = aisheUnitCellService.getAisheUnitCell(stateCode);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/aishe-unit-cell")
    public ResponseEntity<ReturnResponse> saveOrUpdateAisheUnitCell(@RequestBody List<AisheUnitCellEo> aisheUnitCellList,@WithUser UserInfo userInfo) {
        boolean isSave = aisheUnitCellService.saveOrUpdateAisheUnitCell(aisheUnitCellList);
        ReturnResponse returnResponse = ((isSave) ? new ReturnResponse(HttpStatus.OK.value(), null, isSave) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @DeleteMapping(value = "/aishe-unit-cell")
	public ResponseEntity<ReturnResponse> deleteAisheUnitCell(
			@RequestParam(required=false) Integer id,@RequestParam(required=false) boolean whetherStateHavingAisheUnitCell,@RequestParam(required=false) String stateCode
			,@WithUser UserInfo userInfo) {
        boolean isSave = aisheUnitCellService.deleteAisheUnitCell(id,whetherStateHavingAisheUnitCell,stateCode);
        ReturnResponse returnResponse = ((isSave) ? new ReturnResponse(HttpStatus.OK.value(), "Deleted Successfully", isSave) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
}