public class TestTeacher {
    public static void main(String[] args) {
        Teacher teacher1 = new Teacher("Mr.", "Guzauckas", "Computer Science", 9, 12);

        System.out.println(Teacher.getIsCertified());
        System.out.println(Teacher.getMinAge());
        System.out.println(teacher1);
        System.out.println();

        Teacher.setIsCertified(false);
        Teacher.setMinAge(22);

        System.out.println(Teacher.getIsCertified());
        System.out.println(Teacher.getMinAge());
        System.out.println(teacher1);
        System.out.println();
    }
}
