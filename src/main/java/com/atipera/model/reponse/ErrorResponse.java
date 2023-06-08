package com.atipera.model.reponse;

public class ErrorResponse {
    private final Integer status;
    private final String Message;

    public ErrorResponse(Integer status, String Message) {
        this.status = status;
        this.Message = Message;
    }

    public static String response(Integer code) {

        String message = switch (code) {
            case 404 -> "User not found";
            case 406 -> "Unsupported content type";
            case 500 -> "Internal Server Error";
            default -> "";
        };
        return new ErrorResponse(code, message).toString();
    }

    @Override
    public String toString() {
        return String.format("{\"status\": \"%s\", \"Message\": \"%s\"}", this.status, this.Message);
    }
}
