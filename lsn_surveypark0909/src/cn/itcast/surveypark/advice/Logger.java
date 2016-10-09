package cn.itcast.surveypark.advice;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.surveypark.domain.Log;
import cn.itcast.surveypark.domain.User;
import cn.itcast.surveypark.service.LogService;
import cn.itcast.surveypark.util.StringUtil;

/**
 * ��־��¼��
 */
public class Logger {
	private LogService logService ;
	
	//ע��logService
	public void setLogService(LogService logService) {
		this.logService = logService;
	}


	/**
	 * ��¼��־ 
	 */
	public Object record(ProceedingJoinPoint pjp){
		Log log = new Log();
		try {
			ActionContext ac = ActionContext.getContext();
			//operator
			if(ac != null){
				HttpServletRequest req = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
				if(req != null){
					User user = (User) req.getSession().getAttribute("user");
					if(user != null){
						log.setOperator("" + user.getId() + ":" + user.getEmail());
					}
				}
			}
			
			//operName,������
			String methodName = pjp.getSignature().getName();
			log.setOperName(methodName);
			
			//operParams,���������б�
			Object[] args = pjp.getArgs();
			log.setOperParams(StringUtil.arr2Str(args));
			
			//����Ŀ�����ķ���
			Object ret = pjp.proceed();
			
			//operResult,�ɹ�
			log.setOperResult("success");
			
			//resultMsg,�����Ϣ
			if(ret != null){
				log.setResultMsg(ret.toString());
			}
			return ret ;
		} catch (Throwable e) {
			log.setOperResult("failure") ;
			log.setResultMsg(e.getMessage());
		}
		finally{
			logService.saveEntity(log);
		}
		return null ;
	}
}
