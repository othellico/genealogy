package com.claudiosignorini.genealogy.bootstrap;

import java.io.IOException;

class BootstrapException extends RuntimeException {

    BootstrapException(String message) {
        super(message);
    }

    BootstrapException(String message, Throwable cause) {
        super(message, cause);
    }
}
