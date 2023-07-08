package cash.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// ServletContextListener

@WebListener
public class BootListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("실행확인 : ServletContextListener.contextInitialized()");
    	
    	// application 속성 영역에 "currentCounter" 속성변수 초기화
    	ServletContext application = sce.getServletContext();
    	application.setAttribute("currentCounter", 0); // 현재 접속자 최초 0
    	
    	try {
    		Class.forName("org.mariadb.jdbc.Driver");
    	} catch (ClassNotFoundException e) {
    		System.out.println("DB에러");
    		e.printStackTrace();
		}
    	
    	System.out.println("로딩성공");
    }
	
}
