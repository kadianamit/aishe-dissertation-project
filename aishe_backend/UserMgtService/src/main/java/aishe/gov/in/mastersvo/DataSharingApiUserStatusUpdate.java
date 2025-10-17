package aishe.gov.in.mastersvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataSharingApiUserStatusUpdate {
    private String requestId;
    private String remarks;
    private Integer statusId;
}