package aishe.gov.in.utility;


/**
 * WDWUtil
 * @version 1.0
 * @author Dev Raj
 *
 */
public class WDWUtil {
    /**
     * isExcel2003
     * @param filePath String
     * @return boolean
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * isExcel2007
     * @param filePath String
     * @return boolean
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
