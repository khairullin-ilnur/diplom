package ru.darkvader.utils;

/**
 * Created by Khairullin on 30/05/16.
 * Solution of the system of linear equations.
 *
 * @author Khairullin
 */
public class ResolveLinearSystems {

    private int sizeOfMatrix = 3;
    private double[][] matrix = new double[sizeOfMatrix][sizeOfMatrix + 1];
    private double eps = 0.1;

    public ResolveLinearSystems(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[] getResult() {

        // Введем вектор значений неизвестных на предыдущей итерации,
        // размер которого равен числу строк в матрице, т.е. size,
        // причем согласно методу изначально заполняем его нулями
        double[] previousVariableValues = new double[sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            previousVariableValues[i] = 0.0;
        }

        // Будем выполнять итерационный процесс до тех пор,
        // пока не будет достигнута необходимая точность
        for (int k = 0; k < 50; k++) {
            // Введем вектор значений неизвестных на текущем шаге
            double[] currentVariableValues = new double[sizeOfMatrix];

            // Посчитаем значения неизвестных на текущей итерации
            // в соответствии с теоретическими формулами
            for (int i = 0; i < sizeOfMatrix; i++) {
                // Инициализируем i-ую неизвестную значением
                // свободного члена i-ой строки матрицы
                currentVariableValues[i] = matrix[i][sizeOfMatrix];

                // Вычитаем сумму по всем отличным от i-ой неизвестным
                for (int j = 0; j < sizeOfMatrix; j++) {
                    // При j < i можем использовать уже посчитанные
                    // на этой итерации значения неизвестных
                    if (j < i) {
                        currentVariableValues[i] -= matrix[i][j] * currentVariableValues[j];
                    }

                    // При j > i используем значения с прошлой итерации
                    if (j > i) {
                        currentVariableValues[i] -= matrix[i][j] * previousVariableValues[j];
                    }
                }

                // Делим на коэффициент при i-ой неизвестной
                currentVariableValues[i] /= matrix[i][i];
            }

            // Посчитаем текущую погрешность относительно предыдущей итерации
            double error = 0.0;

            for (int i = 0; i < sizeOfMatrix; i++) {
                error += Math.abs(currentVariableValues[i] - previousVariableValues[i]);
            }

            // Если необходимая точность достигнута, то завершаем процесс
            if (error < eps) {
                break;
            }

            // Переходим к следующей итерации, так
            // что текущие значения неизвестных
            // становятся значениями на предыдущей итерации
            previousVariableValues = currentVariableValues;
        }

        return previousVariableValues;
    }

}
