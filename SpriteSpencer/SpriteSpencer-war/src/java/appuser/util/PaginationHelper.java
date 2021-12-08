package appuser.util;

import javax.faces.model.DataModel;

/**
 * This class assists in setting the size and number of the page of the appUser view pages
 * @author Spencer Stroud - automatic creation
 */
public abstract class PaginationHelper {

    private int pageSize;
    private int page;
    
    /**
     * Constructor, setup the page's size
     * @param pageSize 
     */
    public PaginationHelper(int pageSize) {
        this.pageSize = pageSize;
    }

    public abstract int getItemsCount();

    public abstract DataModel createPageDataModel();
    
    /**
     * Get the current's page's first item
     * @return first page item of the current page
     */
    public int getPageFirstItem() {
        return page * pageSize;
    }

    /**
     * Get the current's page's last item
     * @return last page item of the current page
     */
    public int getPageLastItem() {
        int i = getPageFirstItem() + pageSize - 1;
        int count = getItemsCount() - 1;
        if (i > count) {
            i = count;
        }
        if (i < 0) {
            i = 0;
        }
        return i;
    }

    /**
     * Check if this page has a next page
     * @return true: if there is a page after the current one, false: if there is no page after the current one
     */
    public boolean isHasNextPage() {
        return (page + 1) * pageSize + 1 <= getItemsCount();
    }

    /**
     * Set the current page number to the next page
     */
    public void nextPage() {
        if (isHasNextPage()) {
            page++;
        }
    }

    /**
     * Check if this page has a previous page
     * @return true: if there is a page before the current one, false: if there is no page before the current one
     */
    public boolean isHasPreviousPage() {
        return page > 0;
    }

    /**
     * Set the current page number to the previous page
     */
    public void previousPage() {
        if (isHasPreviousPage()) {
            page--;
        }
    }
    
    /**
     * Get the pages' size
     * @return the page's size in integer
     */
    public int getPageSize() {
        return pageSize;
    }

}
