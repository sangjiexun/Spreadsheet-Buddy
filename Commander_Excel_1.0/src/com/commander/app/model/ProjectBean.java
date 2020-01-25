package com.commander.app.model;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProjectBean {

	private static ProjectBean instance;

	@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
	private LinkedList<SuperCommand> sooperCommands;

	@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
	private String projectName;
	
	@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
	private Map<Workbook,Sheet[]> workbooksMap;

	@JsonInclude
	private File directoryLoc;

	private static final String TEMP_DIR = "java.io.tmpdir";

	private ProjectBean() {

		setDirectoryPath(TEMP_DIR);

	}

	private ProjectBean(String projectName, LinkedList<SuperCommand> sooperCommands) {
		this.projectName = projectName;
		setDirectoryPath(TEMP_DIR);
		this.sooperCommands = sooperCommands;

	}

	public static ProjectBean getInstance(String projectName, File storagePath,
			LinkedList<SuperCommand> sooperCommands) {
		if (instance == null) {

			instance = new ProjectBean(projectName, sooperCommands);
		}

		return instance;

	}
	public Map<Workbook, Sheet[]> getWorkbooksMap() {
		return workbooksMap;
	}

	public void setWorkbooksMap(Map<Workbook, Sheet[]> workbooksMap) {
		this.workbooksMap = workbooksMap;
	}

	public LinkedList<SuperCommand> getSooperCommands() {
		return this.sooperCommands;
	}

	public void setSooperCommands(LinkedList<SuperCommand> sooperCommands) {
		this.sooperCommands = sooperCommands;
	}

	public String getName() {
		return this.projectName;
	}

	public void setName(String projectName) {
		this.projectName = projectName;
	}

	public void addCommand(SuperCommand supercommand) {

		this.sooperCommands.add(supercommand);

	}

	public void closeProject() {

		this.projectName = "";
		this.sooperCommands.clear();
		instance = null;

	}

	public File getDirectoryLoc() {
		return directoryLoc;
	}

	public void setDirectoryPath(String pathToSet) {
		this.directoryLoc = new File(pathToSet);

	}

	public static ProjectBean getInstance() {
		
		if(instance == null) {
			return new ProjectBean();
		}

		return instance;

	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Project Name: " + projectName;
	}

}
