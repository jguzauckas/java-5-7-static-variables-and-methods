public class Teacher {
    private String title;
    private String lastName;
    private String subject;
    private int minGradeLevel;
    private int maxGradeLevel;

    public Teacher() {
        title = "";
        lastName = "";
        subject = "";
        minGradeLevel = -1;
        maxGradeLevel = -1;
    }

    public Teacher(String t, String l, String s, int min, int max) {
        title = t;
        lastName = l;
        subject = s;
        minGradeLevel = min;
        maxGradeLevel = max;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String t) {
        title = t;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String l) {
        lastName = l;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String s) {
        subject = s;
    }

    public int getMinGradeLevel() {
        return minGradeLevel;
    }

    public void setMinGradeLevel(int g) {
        if (!(g < 1 || g > 12)) {
            minGradeLevel = g;
        }
    }

    public int getMaxGradeLevel() {
        return maxGradeLevel;
    }

    public void setMaxGradeLevel(int g) {
        if (!(g < 1 || g > 12)) {
            maxGradeLevel = g;
        }
    }
}
