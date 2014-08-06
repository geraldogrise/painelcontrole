package br.com.util.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FlushScript  extends SimpleTagSupport  {

    public void doTag() throws JspException, IOException {
    	
    	Object _scriptstring = ((PageContext) getJspContext()).getRequest().getAttribute("SCRIPTSTRING");
    	if ( _scriptstring != null ) {
    		PageContext context = (PageContext)this.getJspContext();
        	JspWriter out = context.getOut();
        	out.print( _scriptstring );
    	
    	}

    }
	
	
}
