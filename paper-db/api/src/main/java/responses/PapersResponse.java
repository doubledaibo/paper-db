package responses;

import entities.Status;
import entities.Paper;

import java.util.List;

public class PapersResponse {
	private Status status = null;
	private List<Paper> papers = null;

	public PapersResponse(Status status, List<Paper> papers) {
		setStatus(status);
		setPapers(papers);
	}  

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setPapers(List<Paper> papers) {
		this.papers = papers;
	}

	public Status getStatus() {
		return status;
	}

	public List<Paper> getPapers() {
		return papers;
	}
}
