package com.project.schedule.constant;

public interface GeneralConstant {

    class GeneralMessageSuccessApi{
        private GeneralMessageSuccessApi(){}
        public static final String SUCCESS_GET_DATA = "Get data success";
        public static final String SUCCESS_CREATE_DATA = "Create data success";
        public static final String SUCCESS_UPDATE_DATA = "Update data success";
        public static final String SUCCESS_DELETE_DATA = "Delete data success";
    }

    class ErrorMessageApi{
        private ErrorMessageApi(){}
        public static final String ILLEGAL_ARGUMENT_SIZE_PAGE = "size index must not be less or same than zero";
        public static final String ILLEGAL_ARGUMENT_PAGE_NUMBER = "page index must not be less or same than zero";
        public static final String DATA_NOT_FOUND_IN_PAGE = "Data from page %d not found";
        public static final String DATA_NOT_FOUND = "Data with id : %s not found";
        public static final String DATA_NOT_FOUND_IN_SEARCH = "Data with maskapai %s not found";
        public static final String ILLEGAL_INPUT_PAYLOAD  = "%s must be text";
        public static final String ILLEGAL_INPUT_SAME_DATA = "%s and %s must be unique";
    }

}
