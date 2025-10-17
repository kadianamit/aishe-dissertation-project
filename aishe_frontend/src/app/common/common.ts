// export class Common {
// }

import { FormControl, ValidationErrors } from "@angular/forms";



export class Common {

	public static EmailPattern: string =
		'^[aA-zZ0-9._%+-]+@[aA-zZ0-9.-]+.[aA-zZ]{2,4}$';

	public static nfgStateId: any = 37;

	// ************ Allowing this character set for english and some special character ************
	/*
	0x0000-0x007F 0-127 Basic Latin
	0x0080-0x00FF 128-255 Latin-1 Supplement
	0x0100-0x017F 256-383 Latin Extended-A
	0x0180-0x024F 384-591 Latin Extended-B
	0x0250-0x02AF 592-687 IPA Extensions
	0x02B0-0x02FF 688-767 Spacing Modifier Letters
	0x0300-0x036F 768-879 Combining Diacritical Marks
	0x0370-0x03FF 880-1023 Greek
	0x0400-0x04FF 1024-1279 Cyrillic
	0x0530-0x058F 1328-1423 Armenian
	0x1E00-0x1EFF 7680-7935 Latin Extended Additional
	0x1F00-0x1FFF 7936-8191 Greek Extended
	0x2000-0x206F 8192-8303 General Punctuation
	0x2070-0x209F 8304-8351 Superscripts and Subscripts
	0x20A0-0x20CF 8352-8399 Currency Symbols
	0x20D0-0x20FF 8400-8447 Combining Marks for Symbols
	0x2100-0x214F 8448-8527 Letterlike Symbols
	0x2150-0x218F 8528-8591 Number Forms
	0x2190-0x21FF 8592-8703 Arrows
	0x2200-0x22FF 8704-8959 Mathematical Operators
	0x2500-0x257F 9472-9599 Box Drawing
	0x2580-0x259F 9600-9631 Block Elements
	0x25A0-0x25FF 9632-9727 Geometric Shapes
	0x2300-0x23FF 8960-9215 Miscellaneous Technical
	0x2400-0x243F 9216-9279 Control Pictures
	0x2440-0x245F 9280-9311 Optical Character Recognition
	0x2460-0x24FF 9312-9471 Enclosed Alphanumerics
	0x2600-0x26FF 9728-9983 Miscellaneous Symbols
	0xFE20-0xFE2F 65056-65071 Combining Half Marks
	0xFE50-0xFE6F 65104-65135 Small Form Variants
	0xFEFF-0xFEFF 65279-65279 Specials
	0xFFF0-0xFFFD 65520-65533 Specials
	*/
	public static editorValidate: RegExp = new RegExp(/^[\u0000-\u00FF\u0100-\u017F\u0180-\u024F\u0250-\u02AF\u02B0-\u02FF\u0300-\u036F\u0370-\u03FF\u0400-\u04FF\u0530-\u058F\u1E00-\u1EFF\u1F00-\u1FFF\u2000-\u206F\u2070-\u209F\u20A0-\u20CF\u20D0-\u20FF\u2100-\u214F\u2150-\u218F\u2190-\u21FF\u2200-\u22FF\u2300-\u23FF\u2580-\u259F\u2580-\u259F\u25A0-\u25FF\u2400-\u243F\u2440-\u245F\u2460-\u24FF\uFE20-\uFE2F\uFE50-\uFE6F\u2600-\u26FF\uFEFF-\uFEFF\uFFF0-\uFFFD]+$/);


	public static generateUUID() {
		// Public Domain/MIT
		var d = new Date().getTime(); //Timestamp
		var d2 =
			(typeof performance !== 'undefined' &&
				performance.now &&
				performance.now() * 1000) ||
			0; //Time in microseconds since page-load or 0 if unsupported
		return 'yyxxyyxx-xxxx-4xxx-yxxx-xyyxxxyyxxyx'.replace(
			/[xy]/g,
			function (c) {
				var r = Math.random() * 16; //random number between 0 and 16
				if (d > 0) {
					//Use timestamp until depleted
					r = (d + r) % 16 | 0;
					d = Math.floor(d / 16);
				} else {
					//Use microseconds since page-load if supported
					r = (d2 + r) % 16 | 0;
					d2 = Math.floor(d2 / 16);
				}
				return (c === 'x' ? r : (r & 0x3) | 0x8).toString(16);
			}
		);
	}


	public static noWhitespaceValidator(control: FormControl): ValidationErrors | null {
		const isWhitespace = (control.value || '').trim().length === 0;
		return isWhitespace ? { 'whitespace': true } : null;
		// const isValid = !isWhitespace;
		// return isValid ? null : { 'whitespace': true };
	}
}

