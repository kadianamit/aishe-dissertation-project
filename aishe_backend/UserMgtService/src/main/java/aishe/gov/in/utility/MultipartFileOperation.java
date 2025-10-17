package aishe.gov.in.utility;

import aishe.gov.in.exception.EntityException;
import aishe.gov.in.masterseo.RemunerationStatementDetailEO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class MultipartFileOperation {

    /**
     * multipartFileOperation this method will check file type
     *
     * @param file           MultipartFile
     * @param approverUserId String
     * @param request        HttpServletRequest
     * @return List<RemunerationStatementDetailEO>
     * @throws IOException
     */
    public List<RemunerationStatementDetailEO> multipartFileOperation(MultipartFile file, String approverUserId, HttpServletRequest request) throws IOException {
        Sheet sheet;
        String fileAbsolutePath = file.getOriginalFilename();
        if (WDWUtil.isExcel2003(fileAbsolutePath)) {
            sheet = new HSSFWorkbook(file.getInputStream()).getSheetAt(RemunerationConstant.zero);
        } else if (WDWUtil.isExcel2007(fileAbsolutePath)) {
            sheet = new XSSFWorkbook(file.getInputStream()).getSheetAt(RemunerationConstant.zero);
        } else {
            throw new EntityException(RemunerationConstant.FILE_TYPE_EXCEPTION);
        }
        return readDataFromMultiPart(sheet, approverUserId, request);
    }

    /**
     * this method will read data from sheet and set into  List<RemunerationStatementDetailEO>
     *
     * @param sheet          Sheet
     * @param approverUserId String
     * @param request        HttpServletRequest
     * @return List<RemunerationStatementDetailEO>
     */
    List<RemunerationStatementDetailEO> readDataFromMultiPart(Sheet sheet, String approverUserId, HttpServletRequest request) {
        Timestamp currentTimestamp = DateUtils.getCurrentDateTimestamp();
        List<RemunerationStatementDetailEO> detailEOS = new ArrayList<>();
        int rowCount = RemunerationConstant.zero;
        for (Row row : sheet) {
            rowCount++;
            if (rowCount > RemunerationConstant.one) {
                RemunerationStatementDetailEO entity = new RemunerationStatementDetailEO();
                int cellCount = RemunerationConstant.zero;
                for (Cell cell : row) {
                    cellCount++;
                    switch (cellCount) {
                        case 1:
                            entity.setStatementId(cell.getStringCellValue());
                            if (CellType.BLANK== cell.getCellType() || cell.getStringCellValue().isEmpty())
                                throw new EntityException("Statement Id is Empty at row number " + row.getRowNum());
                            break;
                        case 2:
                            entity.setFormUploadId((int) cell.getNumericCellValue());
                            if (cell.getCellType() != CellType.NUMERIC || cell.getCellType() == CellType.BLANK)
                                throw new EntityException("Form Upload Id is Empty at row number " + row.getRowNum());
                            break;
                        case 9:
                            entity.setRemarks(cell.getStringCellValue());
                            break;
                        case 10:
                            if (cell.getCellType() != CellType.NUMERIC || cell.getCellType() == CellType.BLANK)
                                throw new EntityException("Transaction status is Empty at row number " + row.getRowNum());
                            entity.setTransactionStatusId(getTransactionStatusIdByName(cell.getStringCellValue()));
                            break;
                    }
                    entity.setApproverUserId(approverUserId);
                    entity.setIpAddress(IpAddressProvider.getClientIpAddr(request));
                    entity.setTimestamp(currentTimestamp);
                }
                detailEOS.add(entity);
            }
        }
        return detailEOS;
    }


    private int getTransactionStatusIdByName(String name) {
        int status = RemunerationConstant.REMUNERATION_TRANSACTION_PENDING;
        switch (name.toLowerCase()) {
            case "successful":
                status = RemunerationConstant.REMUNERATION_TRANSACTION_APPROVED;
                break;
            case "failed":
                status = RemunerationConstant.TRANSACTION_FAILED_STATUS_ID;
                break;
            case "pending":
                status = RemunerationConstant.REMUNERATION_TRANSACTION_PENDING;
                break;
        }
        return status;
    }

}
