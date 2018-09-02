package org.openskies.songbook.printer;

public class OnsongElement extends SongElement {

	public OnsongSubtype subtype;

	public OnsongElement(int line, int column, String content) {
		super(SongElementType.ONSONG, line, column, content);
		parseSubtype(content);
	}
	
	public OnsongElement(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
		parseSubtype(content);
	}

	public OnsongSubtype getSubtype() {
		return subtype;
	}

	private void parseSubtype(String c) {
		OnsongSubtype[] values = OnsongSubtype.values();
		for (OnsongSubtype osSubtype : values) {
			String type = osSubtype.name().toLowerCase() + ":";
			int length = type.length();
			if (c.toLowerCase().startsWith(type)) {
				subtype = osSubtype;
				this.setContent(this.getContent().substring(length, this.getContent().length()).trim());
			
				if (subtype==OnsongSubtype.KEY) {
					if (this.getContent().contains("[")||this.getContent().contains("]")) {
						this.setContent(this.getContent().replace("[", "").replace("]", ""));
					}
				}
			}
		}
	}

}
