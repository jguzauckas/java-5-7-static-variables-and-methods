public class TestStudent {
    public static void main(String[] args) {
        Student student1 = new Student("John", "Guzauckas", 123456, 18);

        System.out.println(Student.getIsLearner());
        System.out.println(Student.getHasGraduated());
        System.out.println(student1);
        System.out.println();

        Student.setIsLearner(false);
        Student.setHasGraduated(true);

        System.out.println(Student.getIsLearner());
        System.out.println(Student.getHasGraduated());
        System.out.println(student1);
        System.out.println();
    }
}
