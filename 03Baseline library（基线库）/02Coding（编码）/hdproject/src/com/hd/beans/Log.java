package com.hd.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    
    private Integer log_id;

    private Integer acc_id;

    private Boolean log_type;

    private Date log_time;

    public Integer getLog_id() {
        return log_id;
    }

    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }

    public Integer getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(Integer acc_id) {
        this.acc_id = acc_id;
    }

    public Integer getLog_type() {
    	if(log_type) return 1;
    	else return 2;
    }

    public void setLog_type(Boolean log_type) {
        this.log_type = log_type;
    }

    public String getLog_time() {
    	SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dFormat.format(log_time.getTime());
    }

    public void setLog_time(Date log_time) {
        this.log_time = log_time;
    }
}