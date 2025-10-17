package aishe.gov.in.utility;

import aishe.gov.in.exception.InvalidInputException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class PdfOperation {
    public static boolean isPdf(byte[] data) {
        if (data == null || data.length < 5) return false;
        // %PDF-
        if (data[0] == 0x25 && data[1] == 0x50 && data[2] == 0x44 && data[3] == 0x46 && data[4] == 0x2D) {
            int offset = data.length - 8, count = 0; // check last 8 bytes for %%EOF with optional white-space
            boolean hasSpace = false, hasCr = false, hasLf = false;
            while (offset < data.length) {
                if (count == 0 && data[offset] == 0x25) count++; // %
                if (count == 1 && data[offset] == 0x25) count++; // %
                if (count == 2 && data[offset] == 0x45) count++; // E
                if (count == 3 && data[offset] == 0x4F) count++; // O
                if (count == 4 && data[offset] == 0x46) count++; // F
                // Optional flags for meta info
                if (count == 5 && data[offset] == 0x20) hasSpace = true; // SPACE
                if (count == 5 && data[offset] == 0x0D) hasCr = true; // CR
                if (count == 5 && data[offset] == 0x0A) hasLf = true; // LF / EOL
                offset++;
            }

            if (count == 5) {
                String version = data.length > 13 ? String.format("%s%s%s", (char) data[5], (char) data[6], (char) data[7]) : "?";
                System.out.printf("Version : %s | Space : %b | CR : %b | LF : %b%n", version, hasSpace, hasCr, hasLf);

                String content = new String(data);
                if (content.contains("<script>") || content.toLowerCase().contains("javascript:")
                        || content.toLowerCase().contains("eval(") || content.toLowerCase().contains("expression(")
                        || content.toLowerCase().contains("document.cookie") || content.toLowerCase().contains("document.write") ||
                        content.toLowerCase().contains("alert(") || content.toLowerCase().contains("onerror=")) {
                    System.out.println("Potentially malicious script detected.");
                    return false; // Block the PDF
                }
                return true;
            }
        }

        return false;
    }

   /* public static Boolean isDocx(byte[] fileData) {

        if (fileData == null || fileData.length < 8) {
            return false;
        }
       *//* if (fileData[0] == (byte) 0xD0 && fileData[1] == (byte) 0xCF &&
                fileData[2] == (byte) 0x11 && fileData[3] == (byte) 0xE0 &&
                fileData[4] == (byte) 0xA1 && fileData[5] == (byte) 0xB1 &&
                fileData[6] == (byte) 0x1A && fileData[7] == (byte) 0xE1) {
            return false;  // Identified as .doc
        }*//*

        // Check for .docx file signature (ZIP format)
        if (fileData[0] == (byte) 0x50 && fileData[1] == (byte) 0x4B &&
                fileData[2] == (byte) 0x03 && fileData[3] == (byte) 0x04) {
            return true;  // Identified as .docx
        }

        return false;
    }*/

    public static boolean checkFileName(String fileName) {
        String str = fileName.replaceAll("\\s", "");
        String[] splitted = str.split("\\.");
        List<String> filearray = Arrays.asList(splitted);
        if (!filearray.isEmpty()) {
            for (int i = 0; i < filearray.size(); i++) {
                if (filearray.get(i).equalsIgnoreCase("DOC") || filearray.get(i).equalsIgnoreCase("DOCX") || filearray.get(i).equalsIgnoreCase("JPG")
                        || filearray.get(i).equalsIgnoreCase("JPEG") || filearray.get(i).equalsIgnoreCase("CSV") || filearray.get(i).equalsIgnoreCase("BMP")
                        || filearray.get(i).equalsIgnoreCase("TXT") || filearray.get(i).equalsIgnoreCase("ZIP") || filearray.get(i).equalsIgnoreCase("EXE")
                        || filearray.get(i).equalsIgnoreCase("RTF") || filearray.get(i).equalsIgnoreCase("TIFF") || filearray.get(i).equalsIgnoreCase("PSD")
                        || filearray.get(i).equalsIgnoreCase("MOV") || filearray.get(i).equalsIgnoreCase("AVI") || filearray.get(i).equalsIgnoreCase("PICT")
                        || filearray.get(i).equalsIgnoreCase("HTML")) {
                    throw new InvalidInputException("Incorrect file ,Please Remove Extension like " + filearray.get(i).toUpperCase() + " from file");
                }
            }
        }
        return true;
    }

    public static boolean checkFileName2(String fileName) {
        String str = fileName.replaceAll("\\s", "");
        String[] splitted = str.split("\\.");
        List<String> filearray = Arrays.asList(splitted);
        if (!filearray.isEmpty()) {
            for (int i = 0; i < filearray.size(); i++) {
                if (filearray.get(i).equalsIgnoreCase("DOC")  || filearray.get(i).equalsIgnoreCase("JPG")
                        || filearray.get(i).equalsIgnoreCase("JPEG") || filearray.get(i).equalsIgnoreCase("CSV") || filearray.get(i).equalsIgnoreCase("BMP")
                        || filearray.get(i).equalsIgnoreCase("TXT") || filearray.get(i).equalsIgnoreCase("ZIP") || filearray.get(i).equalsIgnoreCase("EXE")
                        || filearray.get(i).equalsIgnoreCase("RTF") || filearray.get(i).equalsIgnoreCase("TIFF") || filearray.get(i).equalsIgnoreCase("PSD")
                        || filearray.get(i).equalsIgnoreCase("MOV") || filearray.get(i).equalsIgnoreCase("AVI") || filearray.get(i).equalsIgnoreCase("PICT")
                        || filearray.get(i).equalsIgnoreCase("HTML")) {
                    throw new InvalidInputException("Incorrect file ,Please Remove Extension like " + filearray.get(i).toUpperCase() + " from file");
                }
            }
        }
        return true;
    }


    public static boolean isWordFileValid(String filePath,byte[] bytes) {
        try {
            if (filePath.toLowerCase().endsWith(".docx")) {
                return isDOCXValid(new ByteArrayInputStream(bytes));
            } else {
                System.out.println("Unsupported file format");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to check if a .docx file is valid
    private static boolean isDOCXValid(ByteArrayInputStream fis) throws IOException {
        try {
            XWPFDocument document = new XWPFDocument(fis);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            fis.close();
        }
    }

}