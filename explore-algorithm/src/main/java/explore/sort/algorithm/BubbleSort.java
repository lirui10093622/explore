package explore.sort.algorithm;

/**
 * 冒泡排序
 * 时间复杂度
 * 平均：O(n^2) 最好：O(nlog2n) 最坏：O(n^2)
 */
public class BubbleSort {

    public static void sort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {

            boolean moved = false;

            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    moved = true;
                }
            }

            if (!moved) {
                return;
            }
        }
    }
}
