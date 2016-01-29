package com.emrekoca.camel.domain;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", crlf = "UNIX", skipFirstLine = true```)
public class User {
	@DataField(pos = 1)
	public String shakleeID;

	@DataField(pos = 2)
	public String uplineID;

	@DataField(pos = 3)
	public int language;

	@DataField(pos = 4)
	public String test;

	@DataField(pos = 5)
	public String test2;

	@DataField(pos = 6)
	public String test3;

	@Override
	public String toString() {
		return "User [shakleeID=" + shakleeID + ", uplineID=" + uplineID + ", language=" + language + ", test=" + test
				+ ", test2=" + test2 + ", test3=" + test3 + "]";
	}

}
