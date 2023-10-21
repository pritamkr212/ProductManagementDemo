package com.Management.ProductListing.service;

import com.Management.ProductListing.model.EventLog;
import com.Management.ProductListing.repository.EventLogRepository;
import com.Management.ProductListing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventLogServiceImp implements EventLogService {
    @Autowired
    private EventLogRepository eventLogRepository;
    @Override
    public EventLog saveEvent(EventLog eventLog) {
        return eventLogRepository.save(eventLog);
    }
}
