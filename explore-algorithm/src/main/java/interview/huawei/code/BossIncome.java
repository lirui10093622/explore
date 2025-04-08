package interview.huawei.code;

import java.util.*;

/**
 * boss的收入
 */
public class BossIncome {

    static Map<String, Long> incomeMap = new HashMap<>();
    static Map<String, List<String>> relationMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String id = scanner.next();
            String parentId = scanner.next();
            long income = scanner.nextLong();

            incomeMap.put(id, income);
            if (relationMap.containsKey(parentId)) {
                relationMap.get(parentId).add(id);
            } else {
                List<String> children = new LinkedList<>();
                children.add(id);
                relationMap.put(parentId, children);
            }
        }

        String root = "";
        for (String id : relationMap.keySet()) {
            if (!relationMap.values().contains(id)) {
                root = id;
            }
        }

        long income = getChildrenIncome(root);
        System.out.println(root + " " + income);
    }

    private static long getChildrenIncome(String parentId) {
        long total = 0;
        if (relationMap.containsKey(parentId)) { // 有下级
            for (String childId : relationMap.get(parentId)) {
                long income = incomeMap.get(childId) + getChildrenIncome(childId);
                total += (income / 100) * 15;
            }
        }
        return total;
    }
}
