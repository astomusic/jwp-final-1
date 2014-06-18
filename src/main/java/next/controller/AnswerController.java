package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.Controller;

public class AnswerController implements Controller {
	private AnswerDao answerDao = new AnswerDao();
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("questionId");
		long questionId = Long.parseLong(id);
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		
		Question question;
		question = questionDao.findById(questionId);
		int countOfComment = question.getCountOfComment() + 1;
		
		questionDao.updateCountOfComment(questionId, countOfComment);
		
		Answer answer = new Answer(writer, contents, questionId);
		answerDao.insert(answer);
		
		return "api";
	}

}
