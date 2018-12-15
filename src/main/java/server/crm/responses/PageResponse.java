package server.crm.responses;

import server.crm.responses.base.BaseResponse;

public class PageResponse extends BaseResponse {
    private int pageNumber;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
