package responses;

import entities.Status;
import java.util.List;

public class NoteIdsResponse {
	private Status status = null;
	private List<Integer> noteIds = null;

	public NoteIdsResponse(Status status, List<Integer> noteIds) {
		setStatus(status);
		setIntegers(noteIds);
	}  

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setIntegers(List<Integer> noteIds) {
		this.noteIds = noteIds;
	}

	public Status getStatus() {
		return status;
	}

	public List<Integer> getNoteIds() {
		return noteIds;
	}
}
