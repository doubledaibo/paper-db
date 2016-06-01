package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Paper {
	
	public static class PaperMapper implements RowMapper<Paper> {
		@Override
		public Paper mapRow(ResultSet rs, int rowNum) throws SQLException {
			Paper paper = new Paper();
			paper.setId(rs.getInt("id"));
			paper.setProject(rs.getString("project"));
			paper.setName(rs.getString("name"));
			paper.setContent(rs.getString("content"));
			paper.setHighlight(rs.getString("highlight"));
			paper.setLimit(rs.getString("limitation"));
			paper.setBib(rs.getString("bib"));
			paper.setYear(rs.getString("year"));
			return paper;
		}
	}
	
	private Integer id = null;
	private String name = null;
	private String content = null;
	private String highlight = null;
	private String limit = null;
	private String bib = null;
	private String year = null;
	private String project = null;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public void setBib(String bib) {
		this.bib = bib;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

	public Integer getId() {
		return id;
	}

	public String getProject() {
		return project;
	}
	
	public String getName() {
		return name;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getHighlight() {
		return highlight;
	}

	public String getLimit() {
		return limit;
	}

	public String getBib() {
		return bib;
	}

	public String getYear() {
		return year;
	}
	
}
