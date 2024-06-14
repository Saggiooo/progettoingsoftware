package com.dashboardapp.models;


public record Event(String id, String name, String location, String startDate, String endDate,
                    String organizer, Float totalEarnings) {
}
