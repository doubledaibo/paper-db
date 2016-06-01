package operators;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import entities.Paper;

import java.util.List;

public enum PaperOperator {
	INSTANCE;
	private JdbcTemplate template = null;

	
	PaperOperator() {
		template = Database.INSTANCE.getTemplate();
	}

	/**
	 * Check whether a name is valid.
	 */
/*	public boolean checkName(String name) {
		String q = "SELECT count(id) FROM papers WHERE name = ?;";
		return template.queryForObject(q, Integer.class, name) == 0;
	}
*/

	/**
	 * Get all projects
	 * 
	 */
	public List<String> getAllProjects() {
		String q = "SELECT DISTINCT project FROM papers;";
		return template.queryForList(q, String.class);
	}

	/**
	 * Get paper id by related project.
	 * @return Null for no account.
	 */
	public List<Integer> getIdByProject(String project) {
		String q = "SELECT id FROM papers WHERE project = ? ORDER BY year;";	
		List<Integer> ids = null;	
		try {
			ids = template.queryForList(q, Integer.class, project);
		} catch (EmptyResultDataAccessException e) {
		}
		return ids;
	}
	
	/**
	 * Register an paper.
	 * @return False for paper already exist.
	 */
	public boolean register(String project, String name, String content, String highlight, String limit, String bib, String year) {
		String q = "INSERT IGNORE INTO papers(project, name, content, highlight, limitation, bib, year)"
				+ " VALUES (?, ? ,?, ?, ?, ?, ?);";
		return template.update(q, project, name, content, highlight, limit, bib, year) > 0;
	}

	/**
	 * Get paper by id.
	 * @return Paper
	 */
	public Paper getPaper(Integer id) {
		String q = "SELECT * FROM papers WHERE id = ?;";
		Paper paper = null;
		paper = template.queryForObject(q, new Paper.PaperMapper(), id);
		return paper;
	}

	/**
	 * Remove paper by id.
	 * @return False for delete unsuccessful.
	 */
	public boolean removePaper(Integer id) {
		String q = "DELETE FROM papers WHERE id = ?;";
		return template.update(q, id) > 0;
	}
}
