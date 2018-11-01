package ru.otus.jsp.handler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.Random;

public class RandomTagHandler extends BodyTagSupport {

    private Integer size;

    private Long seed;

    private int threshold = Integer.MAX_VALUE;

    @Override
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();

            out.println("<font size=\"" + size + "\">");
            out.print((seed == null ? new Random() : new Random(seed)).nextInt(threshold));
            out.print("</font>");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

//    @Override
//    public void doInitBody() throws JspException {
//
//    }

    @Override
    public int doAfterBody() throws JspException {
//        return EVAL_BODY_AGAIN;
        return super.doAfterBody();
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSeed(Long seed) {
        this.seed = seed;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
