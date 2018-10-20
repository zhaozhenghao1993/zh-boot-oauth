package net.zhenghao.zh.server.entity;

/**
 * 授权登陆请求封装类
 */
public class SysAuthorizeEntity {

    private String response_type;
    private String client_id;
    private String state;
    private String redirect_uri;
    private String username;
    private String password;

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRedirect_uri(String redirect_uri) {

        this.redirect_uri = redirect_uri;
    }


}
