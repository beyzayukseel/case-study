package com.beyzanuryuksel.amadeuscasestudy.exception;


public class BusinessLogicException {

    public static class NotFoundException extends BaseException {
        public NotFoundException(String message) {
            super(message);
        }
    }

    public static class CanNotDeletedException extends BaseException {
        public CanNotDeletedException(String message) {
            super(message);
        }
    }

    public static class NotUpdatedException extends BaseException {
        public NotUpdatedException(String message) {
            super(message);
        }
    }

    public static class NotValidDateEnteredException extends BaseException {
        public NotValidDateEnteredException(String message) {
            super(message);
        }
    }
}
