package org.quote.DataTypes;

public class CrossBrowserLoginConfig {
	private String username = "info%40360quotellc.com"; // Your username
	private String authkey = "u03ccde45a3cdabe"; // Your authkey

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthkey() {
		return authkey;
	}

	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return username + " and " + authkey;
	}

}
