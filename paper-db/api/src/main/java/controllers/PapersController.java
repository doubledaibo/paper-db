package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operators.PaperOperator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entities.Status;
import entities.Paper;

import responses.PapersResponse;
import responses.PaperIdsResponse;
import responses.ProjectsResponse;

import java.util.List;
import java.util.ArrayList;

/**
 * apis about papers.
 * 
 * @author bdai
 *
 */
@RestController
public class PapersController {
	private static final PaperOperator PAPER = PaperOperator.INSTANCE;

	@RequestMapping("/api/delete/paper")
	public Status removePaper(HttpServletRequest request,
				@RequestParam(value = "id") String idStr) {
		Integer id = null;
		try {	
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
		}
		if (id != null) {	
			if (PAPER.removePaper(id)) 
				return new Status(true, "success");
			else
				return new Status(false, 4);			
		} else 
			return new Status(false, 4);
	}

	@RequestMapping("/api/add/paper")
	public Status addPaper(HttpServletRequest request,
				@RequestParam(value = "name") String name,
				@RequestParam(value = "project") String project,
				@RequestParam(value = "content") String content,
				@RequestParam(value = "highlight") String highlight,
				@RequestParam(value = "limit") String limit,
				@RequestParam(value = "bib") String bib,
				@RequestParam(value = "year") String year) {
		if (PAPER.register(project, name, content, highlight, limit, bib, year)) {
			return new Status(true, "success");
		} else {
			return new Status(false, 2);
		}
	}
	
	@RequestMapping("/api/papers/search")
	public PaperIdsResponse searchPapers(HttpServletRequest request, 
				@RequestParam(value = "project") String project) {
		List<Integer> ids = PAPER.getIdByProject(project);	
		return new PaperIdsResponse(new Status(ids != null), ids);
	}

	@RequestMapping("/api/papers")
	public PapersResponse getPaperInfo(HttpServletRequest request,
				@RequestParam(value = "paper_ids") List<String> idStrs) {
		List<Paper> papers = new ArrayList<Paper>();
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
				papers.add(PAPER.getPaper(id));
			}
		} 
		return new PapersResponse(new Status(papers != null), papers);
	}

	@RequestMapping("/api/projects")
	public ProjectsResponse getProjects(HttpServletRequest request) {
		List<String> projects = PAPER.getAllProjects();
		return new ProjectsResponse(new Status(projects != null), projects);
	}
}
