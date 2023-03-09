package com.letg;

import java.util.ArrayList;
import java.util.List;

/**
 * User,test1,123456
 * User,test2,123456
 * User,test3,123456
 */
public abstract class User {
    private String username;
    private String password;


    public static boolean login(String username, String password,List<List<Object>> data){
        List<Object> userData = data.get(3);
        for (int i = 0; i < userData.size(); ++i) {
            List<String> user = (ArrayList<String>) userData.get(i);
            if(user.get(0).equals(username.trim())){
                if(user.get(1).equals(password.trim())){
                    return true;
                }
            }
        }
        return false;
    }


    public List<Object> getTeachingRequirement( List<List<Object>> data){
        return data.get(0);
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
    /*
     * login()
     * getTeachingRequirement()
     */

}
