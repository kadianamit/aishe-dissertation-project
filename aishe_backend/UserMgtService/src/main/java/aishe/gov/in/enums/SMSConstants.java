package aishe.gov.in.enums;

public interface SMSConstants {

	public static final String SMS_GATEWAY_URL_71_SERVER_DNS_BASED = "https://smsgw.sms.gov.in/failsafe/HttpLink";

	public static final String SMS_GATEWAY_URL_72_SERVER_DNS_BASED = "https://smsgw.sms.gov.in/failsafe/HttpLink";
	//public static final String SMS_GATEWAY_URL_72_SERVER_DNS_BASED = "http://smsgw72.nic.in/sendsms_nic/sendmsg.php"; //HTTP should only be used for testing. HTTPS should be used on production
	public static final String SMS_GATEWAY_URL_71_SERVER_IP_BASED = "https://smsgw.sms.gov.in/failsafe/HttpLink";

	public static final String SMS_GATEWAY_URL_72_SERVER_IP_BASED = "https://smsgw.sms.gov.in/failsafe/HttpLink";

	public static final String SMS_GATEWAY_USER_NAME = "aish.auth";
	//public static final String SMS_GATEWAY_PASSWORD = "r*73$MEu";

	//password updated
	public static final String SMS_GATEWAY_PASSWORD = "r*73%24MEu";


	public static final String SMS_GATEWAY_SENDER = "MAISHE";

	public static final int MAXIMUM_CHARACTERS_FOR_SINGLE_SMS = 160;

	public static final String DLT_ENTITY_ID = "1001448750000019149";

	/*
	 * Maximum character limits:
	 *
	 * For Single SMS:
	 *
	 * 		Bits in Character	|	Number of Maximum Allowed Characters
	 * 		7 bit characters	|	160
	 * 		8 bit characters	|	140
	 * 		16 bit characters	|	70
	 *
	 * For Concatenated SMS:
	 *
	 * 		Maximum number of segments per SMS	|	3	NOTE: The value 3 is based on the API documentation, however during testing it was found that the limit is higher
	 *
	 * 		Bits in Character	|	Number of Maximum Allowed Characters Per Segment	|	Total Number of Maximum Allowed Characters (Previous value * 3)
	 * 		7 bit characters	|	153													|	459
	 * 		8 bit characters	|	134													|	402
	 * 		16 bit characters	|	67													|	201
	 */


	//public static final int MAXIMUM_CHARACTERS_FOR_CONCATENATED_SMS = 459; //This value is based on the SMS Gateway API documentation. However during testing it was found that the actual limit is higher.

	public static final int CONCAT_VALUE = 1;
}
