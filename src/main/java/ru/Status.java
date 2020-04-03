package ru;

/**
 * Created by: Yaroslav Skrebets <iam@sonicxd2.ru>
 * Date: 17.12.2019: 20:59
 */
public class Status {
    private final boolean delivered;
    private final long time;

    public Status(boolean delivered, long time) {
        this.delivered = delivered;
        this.time = time;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public long getTime() {
        return time;
    }
}
