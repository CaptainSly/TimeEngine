package com.reignleif.data;

public class Project {

	private String projectId, projectName, projectAuthor, projectVersion;

	public Project(String projectId, String projectName, String projectAuthor, String projectVersion) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectAuthor = projectAuthor;
		this.projectVersion = projectVersion;

	}

	public String getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getProjectAuthor() {
		return projectAuthor;
	}

	public String getProjectVersion() {
		return projectVersion;
	}

}
