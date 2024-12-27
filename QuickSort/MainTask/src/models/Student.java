package models;

import comparators.StudentComparator;

import java.util.Comparator;

public class Student implements Filterable<Student>{
    private final String group;
    private final Double avgScore;
    private final Integer gradeBookNumber;
    // Чтобы не передавать в методы явным образом компараторы, мы один раз прописываем по какому полю будет любое сравнение элементов данного класса и если захотим в дальнейшем поменять его, то можно поменять лишь данный параметр
    private static Comparator<Student> comp;

    static{
        comp = StudentComparator.FullComparison.getFullComparison();
    }

    private Student(String group, Double avgScore, Integer gradeBookNumber) {
        super();
        this.group = group;
        this.avgScore = avgScore;
        this.gradeBookNumber = gradeBookNumber;
    }

    public String getGroup() {
        return group;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public Integer getGradeBookNumber() {
        return gradeBookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                " group=" + group +
                ", \tavgScore=" + avgScore +
                ", \tgradeBookNumber=" + gradeBookNumber +
                '}';
    }

    public static Comparator<Student> getComp() {
        return comp;
    }

    public static void setComp(Comparator<Student> comp) {
        Student.comp = comp;
    }

    @Override
    public int compTo(Student obj) {
        return comp.compare(this, obj);
    }

    @Override
    public boolean isEven() {
        return gradeBookNumber % 2 == 0;
    }

    public static class StudentBuilder {
        private String group;
        private double avgScore;
        private int gradeBookNumber;

        public StudentBuilder setGroup(String group) {
            this.group = group;
            return this;
        }

        public StudentBuilder setScore(Double avgScore) {
            this.avgScore = avgScore;
            return this;
        }

        public StudentBuilder setGradeBookNum(Integer gradeBookNumber) {
            this.gradeBookNumber = gradeBookNumber;
            return this;
        }

        public Student build() {
            return new Student(group, avgScore, gradeBookNumber);
        }
    }
}