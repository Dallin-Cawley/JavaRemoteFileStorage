package javaRemoteFileStorage.Communication.Request;

import javaRemoteFileStorage.Communication.Request.Request;

public class RequestBody {
    Request request;

    /* For File Transfers */
    String fileLocation;
    long fileSize;

    /* For Login */
    String username;
    String password;

}
