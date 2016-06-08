package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Note {
	
	public static class NoteMapper implements RowMapper<Note> {
		@Override
		public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
			Note note = new Note();
			note.setId(rs.getInt("id"));
			note.setProject(rs.getString("project"));
			note.setContent(rs.getString("content"));
			return note;
		}
	}
	
	private Integer id = null;
	private String content = null;
	private String project = null;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public String getProject() {
		return project;
	}
	
	public String getContent() {
		return content;
	}
	
}
