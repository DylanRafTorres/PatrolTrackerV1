
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatrolLog {
    private String officerName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public PatrolLog(String officerName, LocalDateTime startTime) {
        this.officerName = officerName;
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String start = startTime.format(formatter);
        String end = (endTime != null) ? endTime.format(formatter) : "Still on patrol";
        return "Officer: " + officerName + " | Start: " + start + " | End: " + end;
    }
}