// Find the later arrival date using overlapStart.
// Find the earlier leaving date using overlapEnd.
// If the start is after the end, they never meet, so return 0.
// Otherwise, calculate the difference in days and add 1 because both dates are inclusive.

import java.time.LocalDate;

class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {

        int year = 2023;

        LocalDate start = LocalDate.parse(year + "-" + arriveAlice);
        LocalDate end = LocalDate.parse(year + "-" + leaveAlice);
        LocalDate bobStart = LocalDate.parse(year + "-" + arriveBob);
        LocalDate bobEnd = LocalDate.parse(year + "-" + leaveBob);

        LocalDate overlapStart = start.isAfter(bobStart) ? start : bobStart;
        LocalDate overlapEnd = end.isBefore(bobEnd) ? end : bobEnd;

        if (overlapStart.isAfter(overlapEnd)) {
            return 0;
        }

        return (int) (overlapEnd.toEpochDay() - overlapStart.toEpochDay() + 1);
    }
}