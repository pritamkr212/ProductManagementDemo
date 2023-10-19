package com.Management.ProductListing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;
@Entity
public class EventLog {
    @Id
    private String id; // An identifier for the log entry
    private Instant createTime; // Timestamp when the event started
    private Instant finishTime; // Timestamp when the event finished
    private String user; // User who initiated the event
    private String apiEndpoint; // API endpoint that was accessed
    private int status; // Status of the event (e.g., "Success" or "Failure")
    private long responseTime; // Response time in milliseconds

    public EventLog(String id, Instant createTime, Instant finishTime, String user, String apiEndpoint, int status, long responseTime) {
        this.id = id;
        this.createTime = createTime;
        this.finishTime = finishTime;
        this.user = user;
        this.apiEndpoint = apiEndpoint;
        this.status = status;
        this.responseTime = responseTime;
    }
    public EventLog(){}
    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Instant finishTime) {
        this.finishTime = finishTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public void setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return "EventLog{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                ", user='" + user + '\'' +
                ", apiEndpoint='" + apiEndpoint + '\'' +
                ", status='" + status + '\'' +
                ", responseTime=" + responseTime +
                '}';
    }
}
