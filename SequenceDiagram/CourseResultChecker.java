package module12Labka;

public class CourseResultChecker {

    private  double passThreshold;

    public CourseResultChecker(double passThreshold) {
        this.passThreshold = passThreshold;
    }

    public double calculateFinalResult(int[] moduleScores) {
        if (moduleScores == null || moduleScores.length == 0) {
            throw new IllegalArgumentException("Нет результатов по модулям");
        }

        int sum = 0;
        for (int score : moduleScores) {
            sum += score;
        }
        return (double) sum / moduleScores.length;
    }

    public boolean isPassed(double finalScore) {
        return finalScore >= passThreshold;
    }

    public static void main(String[] args) {
        CourseResultChecker checker = new CourseResultChecker(70.0);

        int[] scores = {80, 75, 90};
        double finalScore = checker.calculateFinalResult(scores);

        System.out.println("Итоговый результат: " + finalScore);

        if (checker.isPassed(finalScore)) {
            System.out.println("Результат удовлетворительный. Сертификат может быть выдан.");
        } else {
            System.out.println("Результат недостаточный. Требуется пересдача тестов.");
        }
    }
}
