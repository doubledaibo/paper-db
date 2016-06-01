package responses;

import entities.Status;
import java.util.List;

public class PaperIdsResponse {
	private Status status = null;
	private List<Integer> paperIds = null;

	public PaperIdsResponse(Status status, List<Integer> paperIds) {
		setStatus(status);
		setIntegers(paperIds);
	}  

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setIntegers(List<Integer> paperIds) {
		this.paperIds = paperIds;
	}

	public Status getStatus() {
		return status;
	}

	public List<Integer> getPaperIds() {
		return paperIds;
	}
}
