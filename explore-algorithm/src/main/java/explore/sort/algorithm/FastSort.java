package explore.sort.algorithm;

/**
 * 快速排序
 * 时间复杂度
 * 平均：O(nlog2n) 最好：O(nlog2n) 最坏：O(n^2)
 *
 * 空间复杂度
 * O(log2n)
 */
public class FastSort {

    public static void sort(int[] array) {
        doSort(array, 0, array.length - 1);
    }

    public static void doSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            doSort(array, low, pi - 1);
            doSort(array, pi + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[low];
        int i = low + 1;
        int j = high;

        while (true) {
            while (i <= j && array[i] <= pivot) {
                i++;
            }
            while (i <= j && array[j] >= pivot) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            } else {
                break;
            }
        }

        array[low] = array[j];
        array[j] = pivot;
        return j;
    }
}