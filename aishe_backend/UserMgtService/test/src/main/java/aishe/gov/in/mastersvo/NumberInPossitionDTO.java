package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NumberInPossitionDTO {
	
	@JsonProperty
	private GeneralDTO generalCategory;
	@JsonProperty
	private ScheduleCasteDTO scheduleCaste;
	@JsonProperty
	private ScheduledTribeDTO scheduledTribe;
	@JsonProperty
	private OtherBackwardCasteDTO otherBackwardCaste;
	@JsonProperty
	private TotalDTO total;
	
	public GeneralDTO getGeneralCategory() {
		return generalCategory;
	}
	public void setGeneralCategory(GeneralDTO generalCategory) {
		this.generalCategory = generalCategory;
	}
	public ScheduleCasteDTO getScheduleCaste() {
		return scheduleCaste;
	}
	public void setScheduleCaste(ScheduleCasteDTO scheduleCaste) {
		this.scheduleCaste = scheduleCaste;
	}
	public ScheduledTribeDTO getScheduledTribe() {
		return scheduledTribe;
	}
	public void setScheduledTribe(ScheduledTribeDTO scheduledTribe) {
		this.scheduledTribe = scheduledTribe;
	}
	public OtherBackwardCasteDTO getOtherBackwardCaste() {
		return otherBackwardCaste;
	}
	public void setOtherBackwardCaste(OtherBackwardCasteDTO otherBackwardCaste) {
		this.otherBackwardCaste = otherBackwardCaste;
	}
	public TotalDTO getTotal() {
		return total;
	}
	public void setTotal(TotalDTO total) {
		this.total = total;
	}
	

	
}
