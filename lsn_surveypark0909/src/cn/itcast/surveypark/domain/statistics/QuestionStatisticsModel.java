package cn.itcast.surveypark.domain.statistics;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.surveypark.domain.Question;

/**
 * 问题统计模型
 */
public class QuestionStatisticsModel {
	private Question question;

	private int count;

	private List<OptionStatisticsModel> osms = new ArrayList<OptionStatisticsModel>();

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<OptionStatisticsModel> getOsms() {
		return osms;
	}

	public void setOsms(List<OptionStatisticsModel> osms) {
		this.osms = osms;
	}
}
