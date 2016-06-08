package operators;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import entities.Note;

import utils.Utils;

import java.util.List;

public enum NoteOperator {
	INSTANCE;
	private JdbcTemplate template = null;

	
	NoteOperator() {
		template = Database.INSTANCE.getTemplate();
	}

	/**
	 * Check whether a name is valid.
	 */
/*	public boolean checkName(String name) {
		String q = "SELECT count(id) FROM notes WHERE name = ?;";
		return template.queryForObject(q, Integer.class, name) == 0;
	}
*/

	/**
	 * Get all projects
	 * 
	 */
	public List<String> getAllProjects() {
		String q = "SELECT DISTINCT project FROM notes;";
		return template.queryForList(q, String.class);
	}

	/**
	 * Get note id by related project.
	 * @return Null for no account.
	 */
	public List<Integer> getIdByProject(String project) {
		String q = "SELECT id FROM notes WHERE project = ? ORDER BY create_time";	
		List<Integer> ids = null;	
		try {
			ids = template.queryForList(q, Integer.class, project);
		} catch (EmptyResultDataAccessException e) {
		}
		return ids;
	}
	
	/**
	 * Register an note.
	 * @return False for note already exist.
	 */
	public boolean register(String project, String content) {
		String q = "INSERT IGNORE INTO notes(project, content, create_time)"
				+ " VALUES (?, ? ,?);";
		return template.update(q, project, content, Utils.currentDateTime()) > 0;
	}

	/**
	 * Get note by id.
	 * @return Note
	 */
	public Note getNote(Integer id) {
		String q = "SELECT * FROM notes WHERE id = ?;";
		Note note = null;
		note = template.queryForObject(q, new Note.NoteMapper(), id);
		return note;
	}

	/**
	 * Remove note by id.
	 * @return False for delete unsuccessful.
	 */
	public boolean removeNote(Integer id) {
		String q = "DELETE FROM notes WHERE id = ?;";
		return template.update(q, id) > 0;
	}
}
