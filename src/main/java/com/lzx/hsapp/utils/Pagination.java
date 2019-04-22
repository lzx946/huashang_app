package com.lzx.hsapp.utils;

/**
 * Created by wangdaren on 2018/1/21.
 */
public class Pagination {
        public final static int PAGE_SHOW_COUNT = 6;

        private int pageNum = 1;

        private int numPerPage = 0;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getNumPerPage()
        {
            return numPerPage > 0 ? numPerPage : PAGE_SHOW_COUNT;
        }

        public void setNumPerPage(int numPerPage) {
            this.numPerPage = numPerPage;
        }
    }

