package javaRemoteFileStorage.Communication.Request;

import javaRemoteFileStorage.Communication.Request.Request;

public class RequestBody {
    public Request request;

    /* For File Transfers */
    public String fileLocation;
    public long fileSize;

    /* For Login */
    public String username;
    public String password;

}
