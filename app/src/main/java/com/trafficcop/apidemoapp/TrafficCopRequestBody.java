package com.trafficcop.apidemoapp;

public class TrafficCopRequestBody {
    private String appId;
    private String navigatorLanguage;
    private Long timezoneOffset;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNavigatorLanguage() {
        return navigatorLanguage;
    }

    public void setNavigatorLanguage(String navigatorLanguage) {
        this.navigatorLanguage = navigatorLanguage;
    }

    public Long getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(Long timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }
}