package entity;

import java.util.Comparator;

public class PostComparator implements Comparator<String> {
    @Override
    public int compare(String post1, String post2) {
        String[] parts1 = post1.split(":");
        String[] parts2 = post2.split(":");
        long timestamp1 = Long.parseLong(parts1[1]);
        long timestamp2 = Long.parseLong(parts2[1]);
        
        return Long.compare(timestamp2, timestamp1);
    }

}

