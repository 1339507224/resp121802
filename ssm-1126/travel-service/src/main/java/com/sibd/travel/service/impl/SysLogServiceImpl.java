package com.sibd.travel.service.impl;

import com.sibd.travel.mapper.SysLogMapper;
import com.sibd.travel.pojo.SysLog;
import com.sibd.travel.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;
    @Override
    public List<SysLog> findAll() {
        return sysLogMapper.findAll();
    }

    @Override
    public void save(SysLog sysLog) {
        sysLogMapper.save(sysLog);
    }
}
