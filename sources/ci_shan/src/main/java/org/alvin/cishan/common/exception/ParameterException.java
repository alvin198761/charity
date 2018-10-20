package org.alvin.cishan.common.exception;

import java.util.List;

public class ParameterException extends IllegalArgumentException {
 
	private static final long serialVersionUID = 1L;
	public static final Integer PERMIT = 0;
    public static final Integer DENY = 1;
    private Integer code;
    private List<?> detail;
    private Integer state;
    private Object[] vars;
    public ParameterException(Integer code, String message) {
        super(message);
        this.code = code;
        this.state = DENY;
    }

    public ParameterException(Integer code, Object[] vars, String message) {
        super(message);
        this.vars = vars;
        this.code = code;
        this.state = DENY;
    }

    public ParameterException(Integer code, Object[] vars, String message, List<?> detail, Integer state){
        super(message);
        this.vars = vars;
        this.code = code;
        this.detail = detail;
        this.state = state;
    }

    public List<?> getDetail() {
        return detail;
    }

    public void setDetail(List<?> detail) {
        this.detail = detail;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Object[] getVars() {
        return vars;
    }

    public void setVars(Object[] vars) {
        this.vars = vars;
    }
}

