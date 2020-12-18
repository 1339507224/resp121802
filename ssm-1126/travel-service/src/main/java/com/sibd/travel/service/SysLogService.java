package com.sibd.travel.service;

import com.sibd.travel.pojo.SysLog;

import java.util.List;

public interface SysLogService {
    List<SysLog> findAll();

    void save(SysLog sysLog);
}
