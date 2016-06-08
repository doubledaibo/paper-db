package responses;

import entities.Status;
import entities.Note;

import java.util.List;

public class NotesResponse {
	private Status status = null;
	private List<Note> notes = null;

	public NotesResponse(Status status, List<Note> notes) {
		setStatus(status);
		setNotes(notes);
	}  

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public Status getStatus() {
		return status;
	}

	public List<Note> getNotes() {
		return notes;
	}
}
