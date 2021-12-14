package task;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class GreetingBodyTag extends SimpleTagSupport{
	@Override
	public void doTag() throws JspException, IOException {
		StringWriter sw = new StringWriter();
		getJspBody().invoke(sw);
		
		JspWriter out =getJspContext().getOut();
		
		Calendar c = Calendar.getInstance();
		int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
		
		if(timeOfDay >= 0 && timeOfDay < 12) {
			out.print("Hi "+sw.toString()+", Good Morning");
		}
		else if(timeOfDay >= 12 && timeOfDay < 16) {
			out.print("Hi "+sw.toString()+", Good Afternoon");
		}
		else if(timeOfDay >= 16 && timeOfDay < 21) {
			out.print("Hi "+sw.toString()+", Good Evening");
		}
		else if(timeOfDay >= 21 && timeOfDay < 24) {
			out.print("Hi "+sw.toString()+", Good Night");
		}
	}

}
