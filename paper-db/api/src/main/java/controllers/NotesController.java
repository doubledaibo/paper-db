package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operators.NoteOperator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entities.Status;
import entities.Note;

import responses.NotesResponse;
import responses.NoteIdsResponse;
import responses.ProjectsResponse;

import java.util.List;
import java.util.ArrayList;

/**
 * apis about notes.
 * 
 * @author bdai
 *
 */
@RestController
public class NotesController {
	private static final NoteOperator NOTE = NoteOperator.INSTANCE;

	@RequestMapping("/api/delete/note")
	public Status removeNote(HttpServletRequest request,
				@RequestParam(value = "id") String idStr) {
		Integer id = null;
		try {	
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
		}
		if (id != null) {	
			if (NOTE.removeNote(id)) 
				return new Status(true, "success");
			else
				return new Status(false, 4);			
		} else 
			return new Status(false, 4);
	}

	@RequestMapping("/api/add/note")
	public Status addNote(HttpServletRequest request,
				@RequestParam(value = "project") String project,
				@RequestParam(value = "content") String content) {
		if (NOTE.register(project, content)) {
			return new Status(true, "success");
		} else {
			return new Status(false, 2);
		}
	}
	
	@RequestMapping("/api/notes/search")
	public NoteIdsResponse searchNotes(HttpServletRequest request, 
				@RequestParam(value = "project") String project) {
		List<Integer> ids = NOTE.getIdByProject(project);	
		return new NoteIdsResponse(new Status(ids != null), ids);
	}

	@RequestMapping("/api/notes")
	public NotesResponse getNoteInfo(HttpServletRequest request,
				@RequestParam(value = "note_ids") List<String> idStrs) {
		List<Note> notes = new ArrayList<Note>();
		List<Integer> ids = new ArrayList<Integer>();
		try {
			for (String idStr : idStrs) {
				Integer id = Integer.parseInt(idStr);
				ids.add(id);
			}
		} catch (NumberFormatException e) {
			ids = null;
		}
		if (ids != null) {
			for (Integer id : ids) {
				notes.add(NOTE.getNote(id));
			}
		} 
		return new NotesResponse(new Status(notes != null), notes);
	}
}
