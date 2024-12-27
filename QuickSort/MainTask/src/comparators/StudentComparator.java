package comparators;

import models.Student;

import java.util.Comparator;

public class StudentComparator {

    // только для сортировки
    public static class FullComparison {
        public static Comparator<Student> getFullComparison() {
            return new ByGradeBook().thenComparing(new ByGroup().thenComparing(new ByScore()));
        }
    }

    // для сортировок и бин поиска
    public static class ByGroup implements Comparator<Student> {

        @Override
        public int compare(Student student1, Student student2) {
            return student1.getGroup().compareTo(student2.getGroup());
        }
    }

    // для сортировок и бин поиска
    public static class ByScore implements Comparator<Student> {

        @Override
        public int compare(Student student1, Student student2) {
            return student1.getAvgScore().compareTo(student2.getAvgScore());
        }
    }

    // для сортировок и бин поиска
    public static class ByGradeBook implements Comparator<Student> {

        @Override
        public int compare(Student student1, Student student2) {
            return student1.getGradeBookNumber().compareTo(student2.getGradeBookNumber());
        }
    }
}
