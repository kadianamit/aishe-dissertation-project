package aishe.gov.in.mastersvo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ReportVo {
    private  String reportType;
    private byte[] bytes;
    private Object data;

    public ReportVo(String reportType, byte[] bytes) {
        this.reportType = reportType;
        this.bytes = bytes;
    }
}
