/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Main exception class to be used by REST service This is necessary because we can automatically fetch those exceptions
 * in a client application
 *
 * @author wkoller
 */
public class JacqRestException extends WebApplicationException {

    public JacqRestException() {
    }

    public JacqRestException(String message) {
        super(message);
    }

    public JacqRestException(Response response) {
        super(response);
    }

    public JacqRestException(String message, Response response) {
        super(message, response);
    }

    public JacqRestException(int status) {
        super(status);
    }

    public JacqRestException(String message, int status) {
        super(message, status);
    }

    public JacqRestException(Response.Status status) {
        super(status);
    }

    public JacqRestException(String message, Response.Status status) {
        super(message, status);
    }

    public JacqRestException(Throwable cause) {
        super(cause);
    }

    public JacqRestException(String message, Throwable cause) {
        super(message, cause);
    }

    public JacqRestException(Throwable cause, Response response) {
        super(cause, response);
    }

    public JacqRestException(String message, Throwable cause, Response response) {
        super(message, cause, response);
    }

    public JacqRestException(Throwable cause, int status) {
        super(cause, status);
    }

    public JacqRestException(String message, Throwable cause, int status) {
        super(message, cause, status);
    }

    public JacqRestException(Throwable cause, Response.Status status) throws IllegalArgumentException {
        super(cause, status);
    }

    public JacqRestException(String message, Throwable cause, Response.Status status) throws IllegalArgumentException {
        super(message, cause, status);
    }

}
