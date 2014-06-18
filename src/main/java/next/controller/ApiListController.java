package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;

public class ApiListController implements Controller {
	private QuestionDao questionDao = new QuestionDao();
	private List<Question> questions;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		questions = questionDao.findAll();
		//request.setAttribute("questions", questions);
		Gson gson = new Gson();
		String json = gson.toJson(questions);
		request.setAttribute("json", json);
		return "../json.jsp";
	}

}
