package com.hwan.order.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//가장먼저 해야할 관점(aspect) 클래스 정의
//AOP는 관점을 클래스로 정의하고 , 어드바이스를 관점클래스의 메서드로 구현한다
public class LoggingAspect {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	//before 어드바이스 메서드
	// 어드바이스하는 조인포인트 메서드가 호출되기 전에 호출되어 실행되는 어드바이스
	// 어드바이스하는 조인포인트가 JoinPoint타입의 매개변수로 전달
	public void logBefore(JoinPoint joinpoint) {
		// 어드바이스 기능 구현
		String message = buildJoinpoint(joinpoint);
		message += "메서드 실행 시작";
		log.info(message);
	}
	
	//after 어드바이스 메서드
	//어드바이스 하는 조인포인트 메서드의 실행이 완료된 후 결과와 관계없이 
	//호출되어 실행되는 어드바이스
	public void logAfter(JoinPoint joinpoint) {
		String message = buildJoinpoint(joinpoint);
		message += "메서드 실행 최종 종료";
		log.info(message);
	}
	
	//after-returning 어드바이스
	// 조인포인트 메서드 실행이 성공적으로 완료된후 호출되어 실행되는어드바이스
	public void logAfterReturning(JoinPoint joinpoint){
		String message = buildJoinpoint(joinpoint);
		message += "메서드 실행 정상 종료";
		log.info(message);
	}
	
	//after-throwing 어드바이스
	// 조인포인트 메서드 실행이 실패하여 메서드가 예외를 던진 후에 호출되어 실행되는
	//어드바이스
	public void logAfterThrowing(JoinPoint joinpoint){
		String message = buildJoinpoint(joinpoint);
		message += "메서드 실행 에러";
		log.info(message);
	}
	
	//조인포인트의 정보를 구할 수 있다.
	private String buildJoinpoint(JoinPoint joinpoint) {
		//getTarget() 메서드는 대상 조인포인트 객체를 리턴한다.
		//대상 조인포인트 클래스의 이름을 구한다.
		String className = joinpoint.getTarget().getClass().getName();
		
		//getSignature() 메서드는 조인포인트 메서드의 시그니처를 리턴한다
		//조인포인트 메서드 이름을 구할 수 있다. 
		String methodName = joinpoint.getSignature().getName();
		
		//리턴할 문자열
		String message = className + " 클래스의 " + methodName + "( ";
		
		//getArgs() 메서드는 조인포인트 메서드의 매개 변수 목록을 리턴한다.
		// 조인포인트 메서드 매개 변수 목록을 구할 수 있다.
		Object[] args = joinpoint.getArgs();
		for(int i = 0; i< args.length; ++i){
			Object arg = args[i];
			message += arg.getClass().getTypeName();
			if(i != args.length - 1){
				message += ", ";
			}
		}
		message += " ) ";
		
		return message;
	}
	
	//around 어드바이스 : 메서드가 호출되기 전과 후에 어드바이스 기능이 발생함
	//around 어드바이스의 경우에는 ProceddingJoinPoint 타입의 매개변수를 받는다
	public void logAround(ProceedingJoinPoint joinpoint) throws Throwable {
		long start = System.currentTimeMillis();
		log.info("===========시작=============");
		String message = buildJoinpoint(joinpoint);
		message += "메서드 실행 시작";
		log.info(message);		
		
		//ProceedingJoinPoint는 JointPoint 인터페이스로부터 확장된 인터페이스로,
		// 다음 어드바이스 또는 대상 조인포인트 메서드를 호출하는 기능을 제공하는
		// proceed() 메서드가 추가되어있다.
		joinpoint.proceed();
		
		message = buildJoinpoint(joinpoint);
		message += "메서드 실행 종료";
		long end = System.currentTimeMillis();
		long duration = end - start;
		log.info("실행 시간 : " + duration + "미리초");
		log.info("========종료==========");
		
	}
}
