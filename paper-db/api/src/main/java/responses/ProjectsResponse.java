package responses;

import entities.Status;

import java.util.List;

public class ProjectsResponse {
	private Status status = null;
	private List<String> projects = null;

	public ProjectsResponse(Status status, List<String> projects) {
		setStatus(status);
		setPapers(projects);
	}  

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setPapers(List<String> projects) {
		this.projects = projects;
	}

	public Status getStatus() {
		return status;
	}

	public List<String> getProjects() {
		return projects;
	}
}
