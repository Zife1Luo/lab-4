package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

import java.util.Arrays;
import java.util.List;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        final Team team = gradeDataBase.getMyTeam();
        String[] teamMembersArray = team.getMembers();
        List<String> teamMembers = Arrays.asList(teamMembersArray);
        // Call the API to get usernames of all your team members
        float sum = 0;
        int count = 0;
        // Task 3b: Go to the MongoGradeDataBase class and implement getMyTeam.
        // Iterate through each member of the team
        for (String member : teamMembers) {
            // Retrieve the grades for this member
            Grade[] grades = gradeDataBase.getGrades(member);

            // Find the grades for the specific course and sum them
            for (Grade grade : grades) {
                if (grade.getCourse().equals(course)) {
                    sum += grade.getGrade();
                    count++;
                }
            }
        }

        // Call the API to get all the grades for the course for all your team members
        // Task 3a: Complete the logic of calculating the average course grade for
        //              your team members. Hint: the getGrades method might be useful.

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
