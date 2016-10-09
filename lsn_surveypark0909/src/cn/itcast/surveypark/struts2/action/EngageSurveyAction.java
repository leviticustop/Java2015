package cn.itcast.surveypark.struts2.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.surveypark.datasource.SurveyToken;
import cn.itcast.surveypark.domain.Answer;
import cn.itcast.surveypark.domain.Page;
import cn.itcast.surveypark.domain.Survey;
import cn.itcast.surveypark.service.SurveyService;
import cn.itcast.surveypark.util.StringUtil;
import cn.itcast.surveypark.util.ValidateUtil;

/**
 * EngageSurveyAction
 */
@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware ,SessionAware,ParameterAware{

	private static final long serialVersionUID = -8720618560342098629L;
	
	private static final String CURRENT_SURVEY = "current_survey" ;
	
	private static final String ALL_PARAMS_MAP = "all_params_map" ;
	
	private List<Survey> surveys ;
	
	@Resource
	private SurveyService surveyService ;

	//����sc
	private ServletContext sc;
	
	private Integer sid ;
	
	private Page currPage ;
	
	private Integer currPid ;

	public Integer getCurrPid() {
		return currPid;
	}
	public void setCurrPid(Integer currPid) {
		this.currPid = currPid;
	}

	//����session��map
	private Map<String, Object> sessionMap;

	//�����ύ����map
	private Map<String, String[]> paramsMap;
	
	public Page getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Page currPage) {
		this.currPage = currPage;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public List<Survey> getSurveys() {
		return surveys;
	}
	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}
	/**
	 * ��ѯ���п��Բ���ĵ��� 
	 */
	public String findAllAvailableSurveys(){
		this.surveys = surveyService.findAllAvailableSurveys();
		return "engageSurveyListPage" ;
	}
	
	/**
	 * ȡ��logoͼ���url��ַ
	 */
	public String getImageUrl(String logoPhotoPath){
		if(ValidateUtil.isValid(logoPhotoPath)){
			String realPath = sc.getRealPath(logoPhotoPath);
			if(new File(realPath).exists()){
				return  sc.getContextPath() + logoPhotoPath ;
			}
		}
		return sc.getContextPath() + "/question.bmp" ;
	}
	
	//ע��sc
	public void setServletContext(ServletContext context) {
		this.sc = context ;
	}
	
	/**
	 * ���������ڷ��� 
	 */
	public String entry(){
		this.currPage = surveyService.getFirstPage(sid);
		//���Survey-->session
		sessionMap.put(CURRENT_SURVEY, currPage.getSurvey());
		//��ʼ�����в�����map
		sessionMap.put(ALL_PARAMS_MAP, new HashMap<Integer, Map<String,String[]>>());
		return "engageSurveyPage" ;
	}
	
	//ע��session map
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session ;
	}
	
	/**
	 * ����������
	 */
	public String doEngageSurvey(){
		String submitName = getSubmitName();
		//��һ��
		if(submitName.endsWith("pre")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getPrePage(currPid);
			return "engageSurveyPage" ;
		}
		//��һ��
		else if(submitName.endsWith("next")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getNextPage(currPid);
			return "engageSurveyPage" ;
		}
		//���
		else if(submitName.endsWith("done")){
			mergeParamsIntoSession();
			//�����Ƶ���ǰ�߳�
			SurveyToken token = new SurveyToken();
			token.setCurrentSurvey(getCurrentSurvey());
			SurveyToken.bindingToken(token);
			
			//TODO �����
			surveyService.saveAnswers(processAnswers());
			
			return "engageSurveyAction" ;
		}
		//�˳�
		else if(submitName.endsWith("exit")){
			clearSessionData();
			return "engageSurveyAction" ;
		}
		return null ;
	}
	
	/**
	 * �����
	 */
	private List<Answer> processAnswers() {
		//����ʽ��ѡ��ť
		Map<Integer, String> matrixRadioMap = new HashMap<Integer, String>();
		
		List<Answer> answers = new ArrayList<Answer>();
		Answer a = null ;
		String key = null ;
		String[] value = null ;
		for(Map<String,String[]> map : getAllParamsMapInSession().values()){
			for(Entry<String, String[]> entry : map.entrySet()){
				key = entry.getKey();
				value = entry.getValue();
				//��ѡ����q��ͷ�Ĳ���
				if(key.startsWith("q")){
					//����other,Ҳ����"_"
					if(!key.contains("other") && !key.contains("_")){
						a = new Answer();
						a.setAnswerIds(StringUtil.arr2Str(value));//answerids
						a.setQuestionId(getQid(key));//questionid
						a.setSurveyId(getCurrentSurvey().getId());//surveyid
						//����������
						String[] otherValue = map.get(key+"other");
						a.setOtherAnswer(StringUtil.arr2Str(otherValue));//otheranswer
						answers.add(a);
					}
					//�������ʽ��ѡ��ť
					else if(key.contains("_")){
						//����
						Integer qid = getMatrixRadioQid(key);
						String oldValue = matrixRadioMap.get(qid);
						if(oldValue == null){
							matrixRadioMap.put(qid, StringUtil.arr2Str(value));
						}
						else{
							matrixRadioMap.put(qid, oldValue + "," + StringUtil.arr2Str(value));
						}
					}
				}
			}
		}
		//�����������ʽ��ѡ��ť
		processMatrixRadioAnswers(answers,matrixRadioMap);
		return answers;
	}
	
	/**
	 * �����������ʽ��ѡ��ť
	 */
	private void processMatrixRadioAnswers(List<Answer> answers,
			Map<Integer, String> matrixRadioMap) {
		Integer key = null ;
		String value = null ;
		Answer a = null ;
		for(Entry<Integer, String> entry : matrixRadioMap.entrySet()){
			key = entry.getKey();
			value = entry.getValue();
			a = new Answer();
			a.setAnswerIds(value);//answerids
			a.setQuestionId(key);
			a.setSurveyId(getCurrentSurvey().getId());//surveyid
			answers.add(a);
		}
	}
	/**
	 * �õ�����ʽ��ѡ��ť����id:q12_0 --> 12 
	 */
	private Integer getMatrixRadioQid(String key) {
		return Integer.parseInt(key.substring(1, key.indexOf("_")));
	}
	//ȡ�õ���
	private Survey getCurrentSurvey() {
		return (Survey) sessionMap.get(CURRENT_SURVEY);
	}
	//��ȡ����id:q12-->12
	private Integer getQid(String key) {
		return Integer.parseInt(key.substring(1));
	}
	/**
	 * ���session����,�ͷ���Դ
	 */
	private void clearSessionData() {
		sessionMap.remove(CURRENT_SURVEY);
		sessionMap.remove(ALL_PARAMS_MAP);
	}
	/**
	 * �������ϲ���session��
	 */
	private void mergeParamsIntoSession() {
		Map<Integer, Map<String,String[]>> allParamsMap = getAllParamsMapInSession();
		allParamsMap.put(currPid, paramsMap);
	}
	
	/**
	 * ���sesson�����в�����map
	 */
	@SuppressWarnings("unchecked")
	private Map<Integer, Map<String, String[]>> getAllParamsMapInSession() {
		return (Map<Integer, Map<String, String[]>>) sessionMap.get(ALL_PARAMS_MAP);
	}
	/**
	 * �ô��ύ��ť������
	 */
	private String getSubmitName() {
		for(String name : paramsMap.keySet()){
			if(name.startsWith("submit_")){
				return name ;
			}
		}
		return null;
	}
	
	/**
	 * ע�����в���
	 */
	public void setParameters(Map<String, String[]> parameters) {
		this.paramsMap = parameters ;
	}
	
	/**
	 * ����ѡ�б��
	 */
	public String setTag(String name,String value,String tag){
		Integer pid = this.currPage.getId();
		Map<String,String[]> map = this.getAllParamsMapInSession().get(pid);
		String[] oldValues = map.get(name);
		if(StringUtil.contains(oldValues,value)){
			return tag ;
		}
		return "" ;
	}
	
	public String setText(String name){
		Integer pid = this.currPage.getId();
		Map<String,String[]> map = this.getAllParamsMapInSession().get(pid);
		String[] oldValues = map.get(name);
		if(ValidateUtil.isValid(oldValues)){
			return " value='" + oldValues[0] + "'" ;
		}
		return "" ;
	}
	
}