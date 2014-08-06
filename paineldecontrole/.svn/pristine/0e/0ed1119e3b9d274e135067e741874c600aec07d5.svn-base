package br.com.util.taglib;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.util.ModelSelect;

public class DataExtenso extends SimpleTagSupport  {


	

	@Override
	public void doTag() throws JspException, IOException {

	  DateFormat dfmt = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
	  
		        String lretornoString =   dfmt.format(new Date());  
		
		PageContext context = (PageContext) this.getJspContext();
		JspWriter out = context.getOut();

		out.print(lretornoString);

	}



}
