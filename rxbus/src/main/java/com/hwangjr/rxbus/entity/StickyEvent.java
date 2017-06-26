package com.hwangjr.rxbus.entity;

/**
 * Wraps a sticky event that was posted and can be consumed by subscribers in the future.
 */
public class StickyEvent {
    private final String tag;
    private final Object event;
    private int stickCount;

    /**
     * Creates a new StickyEvent.
     *
     * @param tag        event tag to post.
     * @param event      event to post.
     * @param stickCount count of subscribers to post "sticky" event to, default to 0.
     */
    public StickyEvent(String tag, Object event, int stickCount) {
        this.tag = tag;
        this.event = event;
        this.stickCount = stickCount;
    }

    public String getTag() {
        return tag;
    }

    public Object getEvent() {
        return event;
    }

    public int getStickCount() {
        return stickCount;
    }

    public void setStickCount(int stickCount) {
        this.stickCount = stickCount;
    }
}
