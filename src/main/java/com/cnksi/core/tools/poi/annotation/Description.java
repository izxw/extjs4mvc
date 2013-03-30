package com.cnksi.core.tools.poi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述数据库字段备注信息
 * 
 * 可用于
 * 
 * 1、Excel导出时的Header 
 * 
 * 2、前台程序页面上的Table和Form的显示值
 * 
 * @author dell
 *
 */

/**
 * @author dell
 *
 */
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Description
{

	/**
	 * 定义该字段的备注信息
	 *
	 * 
	 * @return
	 */
	String comment() default "";

	/**
	 * 定义Excel中Cell的宽度,实际宽度为 size * 256,size默认值 8
	 * 
	 * <pre>时间格式设置18,电话号码:18</pre>
	 * 
	 * @return
	 */
	int size() default 8;

	/**
	 * 定义Excel中的导出顺序
	 * @return
	 */
	int order() default 0;

	/**
	 * 是否在Excel中导出,默认TRUE
	 * 
	 * @return
	 */
	boolean excel() default true; //定义是否允许导出该字段值到Excel 

	/**
	 * 区分单张表导出多个Excel文档
	 * 
	 * @return
	 */
	String exportType() default "a";

	/**
	 * obj.字段
	 * @return
	 */
	String field() default "";

}

/**
 * 元注解是指注解的注解。包括  @Retention @Target @Document @Inherited四种
 * 
 * 1.1、@Retention: 定义注解的保留策略
 *		
 *		@Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
 *		@Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
 *		@Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到 
 *
 * 1.2、@Target：定义注解的作用目标
 * 
 * 	@Target(ElementType.TYPE)   //接口、类、枚举、注解
 *	@Target(ElementType.FIELD) //字段、枚举的常量
 * 	@Target(ElementType.METHOD) //方法
 * 	@Target(ElementType.PARAMETER) //方法参数
 * 	@Target(ElementType.CONSTRUCTOR)  //构造函数
 * 	@Target(ElementType.LOCAL_VARIABLE)//局部变量
 * 	@Target(ElementType.ANNOTATION_TYPE)//注解
 * 	@Target(ElementType.PACKAGE) ///包   
 * 	由以上的源码可以知道，他的elementType 可以有多个，一个注解可以为类的，方法的，字段的等等
 * 
 * 1.3、@Document：说明该注解将被包含在javadoc中
 * 
 * 1.4、@Inherited：说明子类可以继承父类中的该注解\
 * 
 * 3 注解是定义好了，那么怎么来得到，解析注解呢？
 * 	  java的反射机制可以帮助，得到注解
 */
