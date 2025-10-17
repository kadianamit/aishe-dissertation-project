package aishe.gov.in.utility;

import aishe.gov.in.enums.Constant;
import aishe.gov.in.masterseo.NtaQuestionEO;
import aishe.gov.in.masterseo.NtaQuestionnaireEO;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Log4j2
public class HorizontalWriteNTAExcelReport {
    @Autowired
    private NullValueHandler handler;

    /**
     * @param questions
     * @param sortedCollege
     * @param universityResult
     * @param response
     */
    public void writeNTAExcel(List<NtaQuestionEO> questions, List<NtaQuestionnaireEO> sortedCollege,
                              List<NtaQuestionnaireEO> universityResult, HttpServletResponse response) {
        log.info("Writing data to excel...!!");
        Workbook workbook = new XSSFWorkbook();
        //Workbook workbook= new XSSFWorkbook();
        List<String> question = Arrays.asList("State", "District", "Institution Type", "Management Type", "Ownership", "aishe code", "latitude", "longitude");
        try (OutputStream outputStream = response.getOutputStream()) {
            Sheet hssfSheet = workbook.createSheet("University-Questionnaire for Eliciting NTA Centres and Other such CBT Exam Centres");
            Sheet hssfSheet2 = workbook.createSheet("College-Questionnaire for Eliciting NTA Centres and Other such " + "CBT Exam Centres");
            workbook.getSheetAt(0).createFreezePane(Constant.freezeRow, Constant.firstRow);
            workbook.getSheetAt(1).createFreezePane(Constant.freezeRow, Constant.firstRow);
            populateDataOnExcelFile(hssfSheet, universityResult, question, questions, workbook);
            populateDataOnExcelFile(hssfSheet2, sortedCollege, question, questions, workbook);
            response.setContentType("application/vnd.ms-excel");
            response.addHeader(Constant.CONTENT_DISPOSITION, "attachment; filename=NTA-Question-" + DateUtils.getCurrentDateTimestamp() + ".xlsx");

            workbook.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            log.error("error in Writing {}", e.getMessage());
            e.printStackTrace();
        }
    }


    void populateDataOnExcelFile(Sheet sheet, List<NtaQuestionnaireEO> data, List<String> questions, List<NtaQuestionEO> questionEOS, Workbook workbook) {
        log.info("Populating Data On Excel File");
        CellStyle style = workbook.createCellStyle();
        CellStyle subStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        Font subFont = workbook.createFont();
        int rowSize = Constant.ZERO;
        Row row = sheet.createRow(rowSize++);
        writeHeader(row, questions, questionEOS, workbook, style, font);
        List<String> list=new ArrayList<>();list.addAll(questions);
        questionEOS.stream().forEach(ntaQuestionEO -> list.add(ntaQuestionEO.getQuestion_no()));
        for (NtaQuestionnaireEO questionnaireEO : data) {
            row = sheet.createRow(rowSize++);
            excelCellOperation(row, list, questionnaireEO, workbook, subStyle, subFont);
        }
        log.info("File export successfully");
    }

    public void writeHeader(Row row, List<String> questions, List<NtaQuestionEO> questionEOS, Workbook workbook, CellStyle style, Font font) {
        Integer col = Constant.ZERO;
        for (String question : questions) {
            setHeader(workbook, col, row, style, font, question);
            col++;
        }
        for (NtaQuestionEO question : questionEOS) {
            setHeader(workbook, col, row, style, font, question.getQuestion());
            col++;
        }
    }

    private void excelCellOperation(Row row, List<String> questions, NtaQuestionnaireEO ntaQuestionnaireEO, Workbook workbook, CellStyle subStyle, Font subFont) {
        Integer col = Constant.ZERO;
        for (String question : questions) {
            switch (question) {
                case "State": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getState_name());
                    break;
                }
                case "District": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getDistrict());
                    break;
                }
                case "Institution Type": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getInstituteType());
                    break;
                }
                case "Management Type": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getManagement());
                    break;
                }
                case "Ownership": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getOwnership());
                    break;
                }
                case "aishe code": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getAisheCode());
                    break;
                }
                case "latitude": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getLatitude());
                    break;
                }
                case "longitude": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getLongitude());
                    break;
                }
                case "q1": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getInstituteName());
                    break;
                }
                case "q2": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ2());
                    break;
                }
                case "q3": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ3());
                    break;
                }
                case "q4": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ4());
                    break;
                }
                case "q5_1": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_1());
                    break;
                }
                case "q5_2": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_2());
                    break;
                }
                case "q5_3": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_3());
                    break;
                }
                case "q5_4": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_4());
                    break;
                }
                case "q5_5": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_5());
                    break;
                }
                case "q5_6": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_6());
                    break;
                }
                case "q5_7": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_7());
                    break;
                }
                case "q5_8": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_8());
                    break;
                }
                case "q5_9": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_9());
                    break;
                }
                case "q5_10": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_10());
                    break;
                }
                case "q5_11": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_11());
                    break;
                }
                case "q5_12": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_12());
                    break;
                }
                case "q5_13": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_13());
                    break;
                }
                case "q5_14": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_14());
                    break;
                }
                case "q5_15": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_15());
                    break;
                }
                case "q5_16": {
                    setCell(workbook, col, row, subStyle, subFont, ntaQuestionnaireEO.getQ5_16());
                    break;
                }

              /*  case "q6_3": {
                    Cell dataCell = row.createCell(col);
                    dataCell.setCellValue(handler.process(ntaQuestionnaireEO.getQ6_3()));
                    dataCell.setCellStyle(getDataCellStyle(workbook, subStyle, subFont, false));
                    break;
                }
                case "q6_2": {
                    Cell dataCell = row.createCell(col);
                    dataCell.setCellValue(handler.process(ntaQuestionnaireEO.getQ6_2()));
                    dataCell.setCellStyle(getDataCellStyle(workbook, subStyle, subFont, false));
                    break;
                }
                case "q6_4": {
                    Cell dataCell = row.createCell(col);
                    dataCell.setCellValue(handler.process(ntaQuestionnaireEO.getQ6_4()));
                    dataCell.setCellStyle(getDataCellStyle(workbook, subStyle, subFont, false));
                    break;
                }
                case "q6_1": {
                    Cell dataCell = row.createCell(col);
                    dataCell.setCellValue(handler.process(ntaQuestionnaireEO.getQ6_1()));
                    dataCell.setCellStyle(getDataCellStyle(workbook, subStyle, subFont, false));
                    break;
                }*/
                default:
                    break;
            }
            col++;
        }
    }


    public void setHeader(Workbook workbook, Integer colIndex, Row row, CellStyle style, Font font, String question) {
        Cell cell = row.createCell(colIndex);
        cell.setCellValue(question);
        cell.setCellStyle(getHeaderCellStyle(workbook, style, font));
    }

    public void setCell(Workbook workbook, Integer colIndex, Row row, CellStyle subStyle, Font subFont, Object value) {
        Cell dataCell = row.createCell(colIndex);
        dataCell.setCellValue(handler.process(value));
        dataCell.setCellStyle(getDataCellStyle(workbook, subStyle, subFont, false));
    }

    public CellStyle getDataCellStyle(Workbook workbook, CellStyle style, Font font, boolean header) {
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        font.setBold(false);
        font.setFontHeightInPoints((short) 9);
        style.setFont(font);
        return style;
    }

    public CellStyle getHeaderCellStyle(Workbook workbook, CellStyle style, Font font) {
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setFillPattern(FillPatternType.NO_FILL);
        style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}
