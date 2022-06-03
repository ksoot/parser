package com.ak.parser;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FileMetadata {

	private final Map<String, List<String>> headers = new HashMap<String, List<String>>();
	private final Map<String, List<String>> footers = new HashMap<String, List<String>>();
	private final Map<String, String> narration = new HashMap<String, String>();

	public FileMetadata(Map<String, List<String>> headers, Map<String, String> narration,
			Map<String, List<String>> footers) {
		this.headers.putAll(headers);
		this.footers.putAll(footers);
		this.narration.putAll(narration);
	}

	public FileMetadata() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, List<String>> getHeaders() {
		return Collections.unmodifiableMap(headers);
	}

	public Map<String, List<String>> getFooters() {
		return Collections.unmodifiableMap(footers);
	}

	public Map<String, String> getNarration() {
		return Collections.unmodifiableMap(narration);
	}

}
