package org.openskies.songbook.printer;

public class SongElement {
	private String type;
	private int line;
	private int column;
	private String content;

	public SongElement(String type, int line, int column, String content) {
		this.setType(type);
		this.setLine(line);
		this.setColumn(column);
		this.setContent(content);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("SongElement [type=" + type + ", line=" + line + ", column=" + column);

		if (content != null) {
			if (!content.trim().equals("")) {
				sb.append(", content=" + content);
			}
		}

		sb.append("]");

		return sb.toString();
	}

}
