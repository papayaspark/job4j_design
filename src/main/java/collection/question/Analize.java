package collection.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        if (previous == null || current == null) {
            return new Info(-1, -1, -1);
        }
        int add = 0, del = 0, change = 0;
        Info info = new Info(add, change, del);
        HashMap<Integer, User> previousMap = new HashMap<>();
        previous.forEach(user -> previousMap.put(user.getId(), user));
        HashMap<Integer, User> currentMap = new HashMap<>();
        current.forEach(user -> currentMap.put(user.getId(), user));
        for (var v : previousMap.entrySet()) {
            if (!currentMap.containsKey(v.getKey())) {
                del++;
                info.setDeleted(del);
            }
            if (currentMap.containsKey(v.getKey())) {
                    if (!v.getValue().equals(currentMap.get(v.getKey()))) {
                change++;
                info.setChanged(change);
            }
            }
        }
        for (var v : currentMap.entrySet()) {
            if (!previousMap.containsKey(v.getKey())) {
                add++;
                info.setAdded(add);
            }
        }
        return info;
    }
}

