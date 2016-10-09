package cn.itcast.surveypark.annotation;

@java.lang.annotation.Target(value={java.lang.annotation.ElementType.METHOD})
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
public @interface RightDesc {
	
	public abstract String name() ;
	
	public abstract String desc();
}
