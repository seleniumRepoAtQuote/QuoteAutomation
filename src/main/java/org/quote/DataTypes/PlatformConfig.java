package org.quote.DataTypes;

public class PlatformConfig {
	String platform;
	String browser;

	public PlatformConfig() {
		super();
	}

	public PlatformConfig(String platform, String browser) {
		super();
		this.platform = platform;
		this.browser = browser;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

}
