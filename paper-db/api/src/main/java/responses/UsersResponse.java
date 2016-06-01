package responses;

import entities.Status;
import entities.User;

public class UsersResponse {
	private Status status = null;
	private User user = null;

	public UsersResponse(Status status, User user) {
		setStatus(status);
		setUser(user);
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public User getUser() {
		return user;
	}
}
