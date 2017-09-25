package program;

import java.time.Duration;
import java.time.Instant;

public class Timer {
	private Instant start;
	private Instant end;
	private Duration duration;
	
	public Timer() {
		start = null;
		end = null;
		duration = null;
	}
	
	public void start() {
		start = Instant.now();
	}
	
	public void end() {
		if (start == null) throw new IllegalStateException("Du kan ikke slutte en timer som ikke er startet!");
		end = Instant.now();
		duration = Duration.between(start, end);
	}
	
	public String toString() {
		if (start == null) return "Timer ikke startet";
		if (duration == null) return "Timer går enda";
		return (duration.toNanos()/1000000000.0) + " sekunder";
	}
	
	public double durationSeconds() {
		if (duration == null) return -1;
		return (duration.toNanos()/1000000000.0);		
	}
	
	public Duration getDuration() {
		return duration;
	}
}
