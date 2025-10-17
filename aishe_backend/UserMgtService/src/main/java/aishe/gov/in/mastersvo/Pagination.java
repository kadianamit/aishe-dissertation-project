package aishe.gov.in.mastersvo;


import aishe.gov.in.exception.InvalidPaginationException;

public class Pagination {

    private int count;

    private final int offset;

    private final int limit;

    private final boolean isIgnorable;

    public Pagination() {
        this.limit = 0;
        this.offset = 0;
        this.isIgnorable = true;
    }

    public Pagination(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
        this.isIgnorable = false;
    }

    public boolean isIgnorable() {
        return isIgnorable;
    }

    public int getCount() {
        return count;
    }

    public int getOffset() {
        if (offset > count)
            return count == 0 ? 0 : count - 1;
        return offset - 1;
    }

    public int getLimit() {
        if (limit > count)
            return count;
        return limit;
    }

    public void verify(int count) {
        this.count = count;
        if (this.limit <= 0 || this.offset <= 0 || this.offset == this.limit)
            throw new InvalidPaginationException("Invalid pagination params");
    }
}
